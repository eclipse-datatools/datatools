/**
 * Created on 2004-9-28
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.editor.template;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.templates.TemplateBuffer;
import org.eclipse.jface.text.templates.TemplateVariable;
import org.eclipse.text.edits.InsertEdit;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.MultiTextEdit;
import org.eclipse.text.edits.RangeMarker;
import org.eclipse.text.edits.TextEdit;

/**
 * A template editor using the SQL formatter to format a template buffer.
 * 
 * @author Hui Cao
 *  
 */
public class SQLTemplateFormatter
{

    /**
     * Formats the template buffer.
     * 
     * @param buffer
     * @param indent
     * @throws BadLocationException
     */
    public void format(TemplateBuffer buffer, String indent) throws BadLocationException
    {
        try
        {
            indent(buffer, indent);

        }
        catch (MalformedTreeException e)
        {
            throw new BadLocationException();
        }
    }

    private void indent(TemplateBuffer templateBuffer, String indent) throws BadLocationException,
        MalformedTreeException
    {

        TemplateVariable[] variables = templateBuffer.getVariables();
        List positions = variablesToPositions(variables);

        IDocument document = new Document(templateBuffer.getString());
        MultiTextEdit root = new MultiTextEdit(0, document.getLength());
        root.addChildren((TextEdit[]) positions.toArray(new TextEdit[positions.size()]));

        // following lines
        int lineCount = document.getNumberOfLines();

        for (int line = 1; line < lineCount; line++)
        {
            IRegion region = document.getLineInformation(line);
            int offset = region.getOffset();
            if (indent == null)
            {
                continue;
            }
            TextEdit edit = new InsertEdit(offset, indent);
            root.addChild(edit);
            root.apply(document, TextEdit.UPDATE_REGIONS);
            root.removeChild(edit);

        }

        positionsToVariables(positions, variables);
        templateBuffer.setContent(document.get(), variables);
    }

    private static List variablesToPositions(TemplateVariable[] variables)
    {
        List positions = new ArrayList(5);
        for (int i = 0; i != variables.length; i++)
        {
            int[] offsets = variables[i].getOffsets();

            // trim positions off whitespace
            String value = variables[i].getDefaultValue();
            int wsStart = 0;
            while (wsStart < value.length() && Character.isWhitespace(value.charAt(wsStart)))
            {
                wsStart++;
            }

            variables[i].getValues()[0] = value.substring(wsStart);

            for (int j = 0; j != offsets.length; j++)
            {
                offsets[j] += wsStart;
                positions.add(new RangeMarker(offsets[j], 0));
            }
        }
        return positions;
    }

    private static void positionsToVariables(List positions, TemplateVariable[] variables)
    {
        Iterator iterator = positions.iterator();

        for (int i = 0; i != variables.length; i++)
        {
            TemplateVariable variable = variables[i];

            int[] offsets = new int[variable.getOffsets().length];
            for (int j = 0; j != offsets.length; j++)
            {
                offsets[j] = ((TextEdit) iterator.next()).getOffset();
            }

            variable.setOffsets(offsets);
        }
    }
}

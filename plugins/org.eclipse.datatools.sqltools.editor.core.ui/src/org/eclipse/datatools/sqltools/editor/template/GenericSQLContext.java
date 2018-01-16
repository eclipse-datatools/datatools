/**
 * Created on 2004-9-24
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.editor.template;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.datatools.sqltools.internal.FormatterUtil;
import org.eclipse.datatools.sqltools.sql.parser.ParsingResult;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.templates.DocumentTemplateContext;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.TemplateBuffer;
import org.eclipse.jface.text.templates.TemplateContextType;
import org.eclipse.jface.text.templates.TemplateException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditorPreferenceConstants;

/**
 * @author Hui Cao
 *  
 */
public class GenericSQLContext extends DocumentTemplateContext
{

    protected ParsingResult _parsingResult = null;

    /**
     * @param type
     * @param document
     * @param completionOffset
     * @param completionLength
     */
    public GenericSQLContext(TemplateContextType type, IDocument document, int completionOffset, int completionLength,
        ParsingResult result)
    {
        super(type, document, completionOffset, completionLength);
        this._parsingResult = result;
    }

    /**
     * @return
     */
    public String[] getCursorNames()
    {
        return (String[]) _parsingResult.getCursorNames(getDocument(), this.getStart()).toArray(new String[] 
        {
        }
        );
    }

    /**
     * Returns the indentation level at the position of code completion.
     */
    private int getIndentation()
    {
        int start = getStart();
        IDocument document = getDocument();
        try
        {
            IRegion region = document.getLineInformationOfOffset(start);
            String lineContent = document.get(region.getOffset(), region.getLength());
            IEclipsePreferences prefs = ((IScopeContext) new InstanceScope()).getNode(PlatformUI.PLUGIN_ID + ".editors");
            int tab = prefs.getInt(AbstractDecoratedTextEditorPreferenceConstants.EDITOR_TAB_WIDTH, 4);
            return FormatterUtil.computeIndent(lineContent, tab);
        }
        catch (BadLocationException e)
        {
            return 0;
        }
    }

    /**
     * Returns the indentation string at the position of code completion.
     */
    private String getIndentationString()
    {
        int level = getIndentation();
        StringBuffer buffer = new StringBuffer("");
        for (int i = 0; i < level; i++)
        {
            buffer.append("\t");
        }
        return buffer.toString();
    }

    /*
     * @see TemplateContext#evaluate(Template template)
     */
    public TemplateBuffer evaluate(Template template) throws BadLocationException, TemplateException
    {

        TemplateBuffer buffer = super.evaluate(template);
        SQLTemplateFormatter formatter = new SQLTemplateFormatter();
        formatter.format(buffer, getIndentationString());

        return buffer;
    }

    /*
     * @see TemplateContext#canEvaluate(Template templates)
     */
    public boolean canEvaluate(Template template)
    {
        if (getContextType() instanceof GenericSQLContextType)
        {
            String key = getKey();
            String[] types = ((GenericSQLContextType) getContextType()).getIds();
            for (int i = 0; i < types.length; i++)
            {
                if (template.matches(key, types[i])) 
                {
                    return true; 
                }
            }
            return false;
        }
        return super.canEvaluate(template);
    }

}

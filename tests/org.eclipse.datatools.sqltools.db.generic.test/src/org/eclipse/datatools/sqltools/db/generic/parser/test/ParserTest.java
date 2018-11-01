/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.db.generic.parser.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import junit.framework.TestCase;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.sqltools.sql.parser.ParseException;
import org.eclipse.datatools.sqltools.sql.parser.ParserParameters;
import org.eclipse.datatools.sqltools.sql.parser.ParsingResult;
import org.eclipse.datatools.sqltools.sql.parser.SQLParser;
import org.eclipse.datatools.sqltools.sql.parser.SQLParserConstants;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;

/**
 * Base class for all parser test cases. Subclasses need to provide a "script.sql" file under the same directory with
 * the class.
 * 
 * @author Hui Cao
 *  
 */
public abstract class ParserTest extends TestCase
{

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    protected abstract SQLParser getParser();

    /**
     * Subclass should override this method to locate the scripts.sql resource.
     */
    protected abstract ParserTest getParserTestCase();

    public void testStart()
    {
        SQLParser p = getParser();
        String sqls[] = readFiles();
        assertNotNull(sqls);
        for (int i = 0; i < sqls.length; i++)
        {
            IDocument doc = new Document(sqls[i]);
            ParserParameters parserParameters = new ParserParameters(true, SQLParserConstants.TYPE_SQL_ROOT);
            parserParameters.setProperty(ParserParameters.PARAM_CONSUME_EXCEPTION, Boolean.FALSE);
			ParsingResult result = p.parse(sqls[i], parserParameters);
			if (result.getRootNode() == null || result.getRootNode().jjtGetNumChildren() <=0)
			{
				fail("Unable to create AST.");
			}
            if (result.getExceptions().size() != 0)
            {
                ParseException pe = ((ParseException) result.getExceptions().get(0));
                String line = "";
                try
                {
                    int lineOff = doc.getLineOffset(pe.currentToken.beginLine - 1);
                    int lineLen = doc.getLineLength(pe.currentToken.beginLine - 1);
                    line = "At line " + (pe.currentToken.beginLine - 1) + ": " + doc.get(lineOff, lineLen);
                }
                catch (BadLocationException e)
                {
                    //we just ignore it
                }
                fail(line + pe.getMessage());
            }
        }
    }

    protected abstract String[] getTestFileNames();

    /**
     * read an external file
     * 
     * @param fileName
     * @param encoding
     * @return
     * @throws CoreException
     */

    private String[] readFiles()
    {
        Reader in = null;

        try
        {
            String[] files = getTestFileNames();
            String[] results = new String[files.length];

            for (int i = 0; i < files.length; i++)
            {
                in = new BufferedReader(new FileReader(files[i]), 16384);
                StringBuffer buffer = new StringBuffer(16384);
                char[] readBuffer = new char[2048];
                int n = in.read(readBuffer);
                while (n > 0)
                {
                    buffer.append(readBuffer, 0, n);
                    n = in.read(readBuffer);
                }
                results[i] = buffer.toString();
            }
            return results;

        }
        catch (IOException x)
        {
            String message = (x.getMessage() != null ? x.getMessage() : ""); //$NON-NLS-1$
            fail(message);
            return null;
        }
        finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException x)
                {
                }
            }
        }
    }
}

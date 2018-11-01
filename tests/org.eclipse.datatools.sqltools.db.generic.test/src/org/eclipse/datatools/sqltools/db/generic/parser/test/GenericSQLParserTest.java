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

import java.net.URL;

import org.eclipse.datatools.sqltools.db.generic.parser.GenericSQLParser;
import org.eclipse.datatools.sqltools.sql.parser.ParserParameters;
import org.eclipse.datatools.sqltools.sql.parser.ParsingResult;
import org.eclipse.datatools.sqltools.sql.parser.SQLParser;
import org.eclipse.datatools.sqltools.sql.parser.SQLParserConstants;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;

/**
 * @author Hui Cao
 *
 */
public class GenericSQLParserTest extends ParserTest
{

    protected ParserTest getParserTestCase()
    {
        return this;
    }

    protected SQLParser getParser()
    {
        return GenericSQLParser.getInstance();
    }
    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.ui.sqleditor.sql.parser.tests.ParserTest#getTestFileName()
     */
    protected String[] getTestFileNames()
    {
        URL url = this.getClass().getResource("scripts.sql");
        String[] files = new String[1];
        files[0]= url.getFile();
        return files;
    }
    
    public void testTerminators()
    {
    	String sql = "CREATE TABLE DD_ADDRESS (" +
                "ID INTEGER NOT NULL, " +
                "EGN TIMESTAMP, " +
                "STRING VARCHAR(255) UNICODE," + 
                "FAMILY VARCHAR(255) UNICODE, " +
                "NAME VARCHAR(255) UNICODE, " +
                "CITY VARCHAR(255) UNICODE, " +
                "NUMBER <UNKNOWN> NOT NULL " +
                "); " +
                "ALTER TABLE DD_ADDRESS ADD CONSTRAINT DD_ADDRESS_PK PRIMARY KEY (ID)";
        SQLParser p = getParser();
        IDocument doc = new Document(sql);
        ParserParameters parserParameters = new ParserParameters(true, SQLParserConstants.TYPE_SQL_ROOT);
        parserParameters.setProperty(ParserParameters.PARAM_CONSUME_EXCEPTION, Boolean.FALSE);
        ParsingResult result = p.parse(sql, parserParameters);
        if (result.getRootNode() == null)
        {
            fail("Unable to create AST.");
        }
        assertEquals("Statement Terminator is not generated correctly", result.getRootNode().jjtGetNumChildren(), 3);
    }
}

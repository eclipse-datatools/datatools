/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.db.generic.parser.test;

import java.net.URL;

import org.eclipse.datatools.sqltools.db.generic.parser.GenericSQLParser;
import org.eclipse.datatools.sqltools.sql.parser.SQLParser;

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
}

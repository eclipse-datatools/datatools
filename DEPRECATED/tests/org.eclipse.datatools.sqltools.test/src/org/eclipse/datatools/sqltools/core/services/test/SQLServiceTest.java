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
package org.eclipse.datatools.sqltools.core.services.test;

import org.eclipse.datatools.sqltools.core.services.SQLService;

import junit.framework.TestCase;

/**
 * @author Hui Cao
 */
public class SQLServiceTest extends TestCase
{

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testSplitSQLByTerminatorLine()
    {
        String[] terminators = new String[]{"go", ";"};
        String sql = "select * from test\r\n" +
                    "go\r\n" +
                    "update test set a = 1 where b = 2\r\n" +
                    " go\r\n" +
                    "insert test values(1, 2)\r\n" + 
                    ";\r\n" +
                    "delete from test\r\n";
        SQLService s = new SQLService();
        String[] groups = s.splitSQLByTerminatorLine(sql, terminators);
        assertTrue(groups.length == 4);
        assertTrue(groups[3].equals("delete from test\r\n"));
        
        sql = "select * from test\r\n";
        groups = s.splitSQLByTerminatorLine(sql, terminators);
        assertTrue(groups.length == 1);
        assertTrue(groups[0].equals("select * from test\r\n"));

        sql = "select * from test\r\n" +
        " go\r\n";
        groups = s.splitSQLByTerminatorLine(sql, terminators);
        assertTrue(groups.length == 1);
        assertTrue(groups[0].equals("select * from test\r\n"));
        
        sql = "select * from test\r\n" +
        " go";
        groups = s.splitSQLByTerminatorLine(sql, terminators);
        assertTrue(groups.length == 1);
        assertTrue(groups[0].equals("select * from test\r\n"));
    }
}

/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.parsers.sql.query.test;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author ckadner
 *
 * Run Basic Parsing tests
 */
public class AllTests {
    
    static final Class[] registredTestClasses = new Class[] {
                    TestSQLQueryParserSelect.class,
                    TestSQLQueryParserInsert.class,
                    TestSQLQueryParserUpdate.class,
                    TestSQLQueryParserDelete.class,
                    TestSQLQueryParserColumnTableReferences.class,
                    TestSQLQueryParserDataTypeResolving.class};

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for org.eclipse.datatools.sqltools.parsers.sql.query.SQLQueryParser"); //$NON-NLS-1$

		//$JUnit-BEGIN$
		for (int i = 0; i < registredTestClasses.length; i++)
        {
            Class testClass = registredTestClasses[i];
    		suite.addTestSuite(testClass);
        }
		//$JUnit-END$
		
		return suite;
	}







}

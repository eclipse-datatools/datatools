/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.parsers.sql.xml.query.test;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author ckadner
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AllTests extends org.eclipse.datatools.sqltools.parsers.sql.query.test.AllTests {

    static final Class[] registredTestClasses = new Class[] {
            TestSQLXMLQueryParser.class };

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for org.eclipse.datatools.sqltools.parsers.sql.xml.query.SQLXMLQueryParser"); //$NON-NLS-1$

        // add super test class test cases!
        suite.addTest(org.eclipse.datatools.sqltools.parsers.sql.query.test.AllTests.suite());

        //$JUnit-BEGIN$
        for (int i = 0; i < registredTestClasses.length; i++) {
            Class testClass = registredTestClasses[i];
            suite.addTestSuite(testClass);
        }
        //$JUnit-END$

        return suite;
    }

}

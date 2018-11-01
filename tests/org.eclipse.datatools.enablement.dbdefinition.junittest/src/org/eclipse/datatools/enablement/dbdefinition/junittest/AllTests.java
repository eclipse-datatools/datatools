/*******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
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
package org.eclipse.datatools.enablement.dbdefinition.junittest;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for org.eclipse.datatools.enablement.dbdefinition.junittest");
		//$JUnit-BEGIN$
		suite.addTestSuite(DB2LUWTest.class);
		suite.addTestSuite(DB2zSeriesTest.class);
		suite.addTestSuite(DB2iSeriesTest.class);
		suite.addTestSuite(OracleTest.class);
		suite.addTestSuite(SQLServerTest.class);
		suite.addTestSuite(InformixTest.class);
		suite.addTestSuite(SybaseTest.class);
		suite.addTestSuite(DerbyTest.class);
		suite.addTestSuite(MySQLTest.class);
		suite.addTestSuite(CloudscapeTest.class);
		suite.addTestSuite(IBMCloudscapeTest.class);
		suite.addTestSuite(PostgreSQLTest.class);
		suite.addTestSuite(DatabaseDefinitionGenericTest.class);
		suite.addTestSuite(PredefinedDataTypeDefinitionGenericTest.class);
		
		
		//$JUnit-END$
		return suite;
	}

}

/*******************************************************************************
 * Copyright (c) 2018 Red Hat Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.xml.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTestSuites extends TestSuite {
	public static Test suite() {
		return new AllTestSuites();
	}

	public AllTestSuites() {
		super("Unit Tests");
	    addTestSuite(org.eclipse.datatools.enablement.oda.xml.ConnectionTest.class);
//	    addTestSuite(org.eclipse.datatools.enablement.oda.xml.PerformanceTest.class);
//	    addTestSuite(org.eclipse.datatools.enablement.oda.xml.QueryTest.class);
	    addTestSuite(org.eclipse.datatools.enablement.oda.xml.RelationInformationTest.class);
//	    addTestSuite(org.eclipse.datatools.enablement.oda.xml.ResultSetTest.class);
//	    addTestSuite(org.eclipse.datatools.enablement.oda.xml.SpecialEncodingTest.class);
//	    addTestSuite(org.eclipse.datatools.enablement.oda.xml.util.date.DateUtilTest.class);
	    addTestSuite(org.eclipse.datatools.enablement.oda.xml.util.MappedTablesTest.class);
//	    addTestSuite(org.eclipse.datatools.enablement.oda.xml.util.SaxParserTest.class);
//	    addTestSuite(org.eclipse.datatools.enablement.oda.xml.util.ui.SchemaPopulationUtilTest.class);
	    addTestSuite(org.eclipse.datatools.enablement.oda.xml.util.ui.XPathPopulationUtilTest.class);
	    addTestSuite(org.eclipse.datatools.enablement.oda.xml.util.XPathParserTest.class);
	}

}

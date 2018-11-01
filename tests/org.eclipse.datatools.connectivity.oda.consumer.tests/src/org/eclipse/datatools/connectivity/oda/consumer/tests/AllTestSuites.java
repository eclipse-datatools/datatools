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

package org.eclipse.datatools.connectivity.oda.consumer.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTestSuites extends TestSuite {
	public static Test suite() {
		return new AllTestSuites();
	}

	public AllTestSuites() {
		super("Unit Tests");
//	    addTestSuite(AdvQueryTest.class);
//	    addTestSuite(AppContextTest.class);
		addTestSuite(BinaryCompatibiltyTest.class);
//	    addTestSuite(ConnectionTest.class);
		addTestSuite(ConsumerMessagesTest.class);
//	    addTestSuite(DataSetMetaDataTest.class);
//	    addTestSuite(DriverBridgeTest.class);
//	    addTestSuite(DriverTest.class);
		addTestSuite(FlatFileTestCase.class);
//	    addTestSuite(LoggerInstanceTest.class);
		addTestSuite(OdaBlobTest.class);
		addTestSuite(OdaClobTest.class);
//	    addTestSuite(OutputParametersTest.class);
//	    addTestSuite(ParameterRowSetTest.class);
//	    addTestSuite(ParamMetaDataTest.class);
//	    addTestSuite(PropertyProviderTest.class);
//	    addTestSuite(QueryTest.class);
//	    addTestSuite(ResultSetMetaDataTest.class);
//	    addTestSuite(ResultSetTest.class);
//	    addTestSuite(TraceLogConfigTest.class);
		addTestSuite(TraceLogTest.class);
	}

}

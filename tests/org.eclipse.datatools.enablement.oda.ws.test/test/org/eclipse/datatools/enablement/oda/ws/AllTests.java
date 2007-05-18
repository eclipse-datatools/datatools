/*******************************************************************************
 * Copyright (c) 2004 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.ws;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Test suite for Web Service Data Source.
 */
public class AllTests
{

	/**
	 * 
	 * @return
	 */
	public static Test suite( )
	{
		TestSuite suite = new TestSuite( "Test for org.eclipse.datatools.enablement.oda.ws" );

		suite.addTestSuite( org.eclipse.datatools.enablement.oda.ws.driver.DriverTest.class );
		suite.addTestSuite( org.eclipse.datatools.enablement.oda.ws.wsdl.WSDLAdvisorTest.class );
		suite.addTestSuite( org.eclipse.datatools.enablement.oda.ws.misc.RawMessageSenderTest.class );

		return suite;
	}

}
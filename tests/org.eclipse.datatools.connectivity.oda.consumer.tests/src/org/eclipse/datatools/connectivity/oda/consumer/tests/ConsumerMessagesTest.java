/*
 *****************************************************************************
 * Copyright (c) 2004, 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *
 ******************************************************************************
 */ 

package org.eclipse.datatools.connectivity.oda.consumer.tests;

import junit.framework.TestCase;

import org.eclipse.datatools.connectivity.oda.consumer.helper.OdaHelperException;
import org.eclipse.datatools.connectivity.oda.consumer.nls.Messages;

public class ConsumerMessagesTest extends TestCase
{
	public final void testManagerNlsMessage()
	{
	    OdaHelperException exception = 
			new OdaHelperException( Messages.helper_cannotGetNamedResultsAfterExecuteQuery, 
			                         null );
		assertEquals( "Cannot get result sets by name after executeQuery() has been called.", 
		              exception.getLocalizedMessage() );
	}
	
	public final void testManagerNlsMessage2()
	{
	    OdaHelperException exception = 
			new OdaHelperException( Messages.helper_cannotExecuteBeforePrepare, 
			                         null );
		assertEquals( "Cannot execute the statement before statement has been prepared.", 
		              exception.getMessage() );
	}
}

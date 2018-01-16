/*
 *************************************************************************
 * Copyright (c) 2005, 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.profile;

import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionFactory;
import org.eclipse.datatools.connectivity.IConnectionProfile;

public class OdaConnectionFactory implements IConnectionFactory 
{

	public IConnection createConnection( IConnectionProfile profile ) 
	{
		return new OdaConnectionWrapper( profile );
	}

	public IConnection createConnection( IConnectionProfile profile, String uid,
			String pwd ) 
	{
		// no user login control
		return createConnection( profile );
	}

}

/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.internal.core.connection;

import java.sql.Connection;

public interface IConnectionSharingEnhancedListener extends ConnectionSharingListener 
{
	/**
	 * 
	 * @param info, ConnectionInfo managing shared connection
	 * @param connection, java.sql.Connection associated with the error
	 * @param error, Throwable that implementer would like to handle
	 */
	public void onError(ConnectionInfo info, Connection connection, Throwable error);
}

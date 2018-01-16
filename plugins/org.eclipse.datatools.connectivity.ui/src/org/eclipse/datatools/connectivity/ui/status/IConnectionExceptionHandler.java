/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.ui.status;

import java.sql.SQLException;

import org.eclipse.datatools.connectivity.IConnection;

/**
 * When a connection error occurs, the SQLException generated may not always contain 
 * useful information.  
 * 
 * A ConnectionExceptionHandler can take the exception and context and use it 
 * to generate a new SQLException that contains a better message.
 *
 */
public interface IConnectionExceptionHandler 
{
	/**
	 * Generates a SQLException with a platform-specific, meaningful message
	 * 
	 * @param throwable, the original Throwable object, possibly null, to be processed.
	 * @param connection, IConnection object that generated this Throwable
	 * @return SQLException with a more user-friendly message
	 */
	public SQLException generateConnectionException(Throwable throwable, IConnection connection);
}

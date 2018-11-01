/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity;

/**
 * Base exception type for connection profile operations.
 * 
 * @author rcernich
 * 
 * Created on Jan 15, 2004
 */
public class ConnectionProfileException extends Exception {

	/**
	 * 
	 */
	public ConnectionProfileException() {
		super();
	}

	/**
	 * @param message
	 */
	public ConnectionProfileException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ConnectionProfileException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public ConnectionProfileException(Throwable cause) {
		super(cause);
	}

}

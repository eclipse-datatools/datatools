/*
 ******************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *     
 ******************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.util.logging;

/**
 *	A Filter can provide more control on what is logged.
 *  It filters LogRecords from its parent handler based on the rules 
 *  that are implemented in isLoggable().
 *	<br>
 *	<br>
 *	Each Handler can have an associated Filter and calls the 
 *	<code>isLoggable</code> method to check whether the 
 *	<code>LogRecord</code> should be published.
 */

public interface Filter
{
	/**
	 * Checks if the the <code>LogRecord</code> should be published.
	 * @param record	the log record.
	 * @return			<code>true</code> if the log record should
	 * 					be published; <code>false</code> otherwise.
	 */
    public boolean isLoggable( LogRecord record );
}

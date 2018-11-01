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
 *	<code>LogFormatter</code> is an abstract class from which all 
 *	formatter classes should implement.  It converts <code>LogRecords</code> 
 * 	into formatted strings based on <code>format()</code> rules.
 */
public abstract class LogFormatter
{
	/**
	 * Creates a <code>LogFormatter</code> instance.
	 */
    protected LogFormatter()
    {
    }

    /**
     * Format the specified <code>LogRecord</code> into a string.
     * @param record	the log record to format.
     * @return			the formatted string.
     */
    public abstract String format( LogRecord record );
}

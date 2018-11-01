/*
 ******************************************************************************
 * Copyright (c) 2004, 2006 Actuate Corporation.
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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;

/**
 * Formats a <code>LogRecord</code> into an understandable format.
 * It generates a string output of the LogRecord in the form of:
 * <br>
 * Log Level       Time        Log Message
 * <br>
 * <Exception stack trace> ...
 */
public class SimpleFormatter extends LogFormatter
{
	/**
	 * Creates an <code>SimpleFormatter</code> instance.
	 */
    public SimpleFormatter()
    {
    }

    /**
     * Formats the specified <code>LogRecord</code> to an understandable 
     * format.
     * @param record	the <code>LogRecord</code> to format.
     * @return			the formatted string.
     */
    public String format( LogRecord record )
    {
        // resulting string:
        //  Log Level       Time        Log Message
        //	<Exception stack trace> ...
        //	...
        Timestamp stamp = new Timestamp( record.getMillis() );
        StringBuffer buffer = new StringBuffer();
        buffer.append( record.getLevel().intValue() );
        buffer.append( "\t" ); //$NON-NLS-1$
        buffer.append( stamp.toString() );
        buffer.append( "\t\t" ); //$NON-NLS-1$
        buffer.append( record.getMessage() );
        buffer.append( "\n" ); //$NON-NLS-1$
        
		Throwable thrown = record.getThrown();
		if( thrown != null )
		{
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter( stringWriter );
			thrown.printStackTrace( printWriter );
			buffer.append( stringWriter.toString() );
			printWriter.close();
			// StringWriter.close() does nothing
		}
        
        return buffer.toString();
    }
}

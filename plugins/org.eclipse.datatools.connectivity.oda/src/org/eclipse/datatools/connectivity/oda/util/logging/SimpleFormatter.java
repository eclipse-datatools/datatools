//----------------------------------------------------------------------
//  SimpleFormatter.java
//
//  Copyright (c) 2004, 2005 Actuate Corporation.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
//  Actuate Corporation  - initial API and implementation
//
//  SimpleFormatter is an implementation of LogFormatter that generates
//  a string output based on the LogRecord in the form of:
//
//  Log Level       Time        Log Message
//	<Exception stack trace> ...
//	...
//----------------------------------------------------------------------

package org.eclipse.datatools.connectivity.oda.util.logging;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;

/**
 * Formats a <code>LogRecord</code> into an understandable format.
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
        buffer.append( "\t" );
        buffer.append( stamp.toString() );
        buffer.append( "\t\t" );
        buffer.append( record.getMessage() );
        buffer.append( "\n" );
        
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

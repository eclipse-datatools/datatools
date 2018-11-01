/*******************************************************************************
 * Copyright (c) 2012, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

/** tracing and statistics gathering */
public class PRSDebug
{
	public static boolean enableTracing = false;

	// TODO clean these two flags up
	public static boolean enableFileCache = false;
	public static boolean noFileCache = true;
	public static boolean traceQueries = false;

	/** DEBUG Time when this class is loaded */
	private static final long zerotime = System.currentTimeMillis();

	/** DEBUG Trace file */
	private static PrintStream traceout = null;

	/** DEBUG Sequence number for trace statements */
	private static int tracecount = 0;

	/** DEBUG Add a fixed delay (ms) for each query */
	public static int QUERY_DELAY = 0;

	/** DEBUG Add a fixed delay (ms) for each row returned from the query */
	public static int ROW_DELAY = 0;

	/** DEBUG Count the number of rows processed */
	public static int rowcount = 0;
	
	static
	{
		try
		{
			if (PRSDebug.enableTracing) {
				String highLevelQualifier = "persistentResultSet";
				File traceFile = null;
		        
		        String tempDir = System.getProperty( "java.io.tmpdir" );
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
		        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		        Random rnd = new Random(System.currentTimeMillis());
		        StringBuffer rndStr = new StringBuffer(4);
		        for(int i = 0; i < 4; i++) {
		           rndStr.append( (char) (rnd.nextInt( (int) ('z' - 'a') ) + 'a') );
		        }
		        String uniqueQualifier = sdf.format(new Date(System.currentTimeMillis())) + "-" + rndStr;
		        String filePath = tempDir + File.separator + "dsTrace." + highLevelQualifier + "." + uniqueQualifier;
		        traceFile = new File(filePath);
		        System.out.println(traceFile.getAbsolutePath());
				
		        FileOutputStream fos = new FileOutputStream( traceFile, true ); //$NON-NLS-1$
				
				PRSDebug.traceout = new PrintStream( fos );

				PRSDebug.trace( "Tracing engaged" ); //$NON-NLS-1$
			}
		}
		catch (FileNotFoundException e)
		{
			// Eat it
		}
	}

	public static void debugQueryDelay()
	{
		PRSDebug.debugDelay( QUERY_DELAY );
	}

	public static void debugRowDelay()
	{
		PRSDebug.debugDelay( ROW_DELAY );
		++rowcount;
	}

	public static void debugDelay( int delay )
	{
		try
		{
			if ( delay > 0 )
			{
				Thread.sleep( delay );
			}
		}
		catch (InterruptedException e)
		{
			// Eat it
		}
	}

	/** Print a debug message */
	public static void trace( String msg )
	{
		if ( !PRSDebug.enableTracing )
		{
			return;
		}

		long tid = Thread.currentThread().getId();
		int relativetime = (int)(System.currentTimeMillis() - zerotime);
		String pattern = "%1$04d %2$04d.%3$03d %4$03d"; //$NON-NLS-1$

		String ts = String.format( pattern, //
				Long.valueOf( tid ), //
				Integer.valueOf( relativetime / 1000 ), //
				Integer.valueOf( relativetime % 1000 ), //
				Integer.valueOf( tracecount++ % 1000 ) );

		if ( traceout != null )
		{
			traceout.println( ts + ": " + msg ); //$NON-NLS-1$
		}

		System.out.println( ts + ": " + msg ); //$NON-NLS-1$
	}

	public static String timestamp()
	{
		long diff = System.currentTimeMillis() - zerotime;

		int ms = (int)(diff % 1000);
		int secs = (int)(diff / 1000);
		int mins = secs / 60;
		secs = secs % 60;

		return String.format( "%d:%02d.%03d", //$NON-NLS-1$
				new Object[] {
						Integer.valueOf( mins ), Integer.valueOf( secs ), Integer.valueOf( ms )
				} );
	}
}

/*******************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.xml.test.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 */
public class TestUtil
{
	/**
	 * compare two text file. The comparasion will ignore the line containing
	 * "modificationDate".
	 * 
	 * @param goldenFileName
	 *            the 1st file name to be compared.
	 * @param outputFileName
	 *            the 2nd file name to be compared.
	 * @return True if two text file is same line by line
	 */
	public static boolean compareTextFile( File goldenFile, File outputFile )
			throws IOException
	{
		
		boolean same = false;
		int line = 1;

		FileInputStream golden = new FileInputStream(goldenFile);
		FileInputStream output = new FileInputStream(outputFile);
		try
		{
			InputStreamReader readerA = new InputStreamReader( golden );
			InputStreamReader readerB = new InputStreamReader( output );
			BufferedReader lineReaderA = new BufferedReader( readerA );
			BufferedReader lineReaderB = new BufferedReader( readerB );

			String strA = lineReaderA.readLine( ).trim( );
			String strB = lineReaderB.readLine( ).trim( );
			while ( strA != null && strB != null )
			{
				same = strA.trim( ).equals( strB.trim( ) );
				if ( !same )
				{
					break;
				}

				line++;
				strA = lineReaderA.readLine( );
				strB = lineReaderB.readLine( );
			}
			same = strA == null && strB == null;
			
			readerA.close( );
			readerB.close( );
			lineReaderA.close( );
			lineReaderB.close( );
			
			if ( !same )
				throw new IOException( "Not equal with the golden file at line# <"+ line + ">." ); //$NON-NLS-1$ //$NON-NLS-2$
		}
		finally
		{
			try
			{
				golden.close( );
			}
			catch (IOException ignore){}
			
			try
			{
				output.close( );
			}
			catch (IOException ignore){}
		}

		return same;
	}
}

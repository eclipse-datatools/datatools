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

package org.eclipse.datatools.enablement.oda.ws.util;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * 
 */
public class TestUtil
{

	/**
	 * compare two text file. The comparision will ignore the line containing
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
		if ( goldenFile.length( ) != outputFile.length( ) )
		{
			return false;
		}

		BufferedInputStream bfA = new BufferedInputStream( new DataInputStream( new FileInputStream( goldenFile ) ) );
		BufferedInputStream bfB = new BufferedInputStream( new DataInputStream( new FileInputStream( outputFile ) ) );

		long currentIdx = 0;
		long fileLength = goldenFile.length( );
		byte[] bf1 = new byte[2048];
		byte[] bf2 = new byte[2048];
		boolean isSame = true;
		while ( currentIdx < fileLength )
		{
			bfA.read( bf1 );
			bfB.read( bf2 );
			if ( !Arrays.equals( bf1, bf2 ) )
			{
				isSame = false;
				break;
			}
			currentIdx += 2048;
		}
		bfA.close( );
		bfB.close( );
		return isSame;
	}

	public static void writeToFile( String path, String template )
			throws IOException
	{
		File file = new File( path );

		if ( file.exists( ) )
			file.delete( );
		File parentPath = new File( file.getParent( ) );
		if ( !parentPath.exists( ) )
			parentPath.mkdir( );
		file.createNewFile( );

		FileOutputStream fos = new FileOutputStream( file );
		fos.write( template.getBytes( ) );
		fos.close( );
	}

}

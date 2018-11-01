/*******************************************************************************
 * Copyright © 2000, 2007 Sybase, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.util;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

public class FileUtil {
	/**
	 * Reads the contents of a stream and packs them into a return string
	 * @param ins The <code>InputStream</code> to read
	 * @return String The contents of the stream
	 * @throws IOException 
	 * @throws Exception If a file read error occurs
	 */
	public static String getContents(InputStream ins) throws IOException {
		StringBuffer contents = new StringBuffer();
       
		byte[] buffer = new byte[4096];
		int len;
       
		while((len = ins.read(buffer)) != -1) {
			String s = new String(buffer, 0, len);
			contents.append(s);
		}

		return contents.toString();
	}

	/**
	 * Loads a file and puts contents into a string
	 * 
	 * @param file
	 * @return
	 * @throws CoreException 
	 * @throws IOException 
	 */
	public static String getFileContents(IFile file) throws IOException, CoreException{
		String s = null;
		s = FileUtil.getContents(file.getContents());
		return s;
	}
	
	/**
	 * Loads a file and puts contents into a string
	 * 
	 * @param fileName
	 * @return
	 * @throws CoreException 
	 * @throws IOException 
	 */
	public static String getFileContents(String fileName) throws IOException, CoreException {
		IPath path = new Path(fileName);
		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);

		return FileUtil.getFileContents(file);
	}
    
	
}

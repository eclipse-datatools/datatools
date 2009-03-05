/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.jdt.classpath;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.drivers.DriverManager;
import org.eclipse.datatools.enablement.jdt.classpath.internal.ConnJDTPlugin;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;

/**
 * Container that adapts the jar list from a driver instance
 * to add the referenced jars to a Java project.
 * @author brianf
 *
 */
public class DriverClasspathContainer implements IClasspathContainer {

	// descriptive name for the container
	private String name;
	
	// local reference to the driver instance
	private DriverInstance mDriverInstance;
	
	/**
	 * @param libName
	 */
	public DriverClasspathContainer(String libName) {
		this.name= libName;
		mDriverInstance =
			DriverManager.getInstance().getDriverInstanceByName(this.name);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.IClasspathContainer#getClasspathEntries()
	 */
	public IClasspathEntry[] getClasspathEntries() {
		IClasspathEntry[] entries = getEntries();
		if (entries != null)
			return entries;
		return new IClasspathEntry[0];
		
	}
	
	/**
	 * @param inFile
	 * @return
	 */
	private IPath getPathForJavaIOFile ( java.io.File inFile ) {
		try {
			URL url = inFile.toURL();
			try {
				url = FileLocator.toFileURL(url);
	
				IPath path = new Path(url.getFile());
				
				return path;
			} catch (IOException e) {
				// do nothing
			}
		} catch (MalformedURLException e){
			
		}
		return null;
	}
	
	/**
	 * @return
	 */
	private IClasspathEntry[] getEntries() {
		ArrayList list = new ArrayList();
		mDriverInstance = DriverManager.getInstance().getDriverInstanceByID(mDriverInstance.getId());
		if (mDriverInstance != null) {
			for (int i = 0; i < mDriverInstance.getJarListAsArray().length; i++) {
				String path = mDriverInstance.getJarListAsArray()[i];
				java.io.File file = new java.io.File(path);
	
				// if it's not a jar or zip, don't process it
				if (!(file.getName().endsWith(".jar") ||
						file.getName().endsWith(".zip")))
					continue;
				
				IPath jarPath = getPathForJavaIOFile(file);
				IClasspathEntry entry = JavaCore.newLibraryEntry(
						jarPath, 
						null, 
						null);
				list.add(entry);
			}
			if (list.size() > 0) {
				return (IClasspathEntry[]) list.toArray( new IClasspathEntry[list.size()]);
			}
		}
		return new IClasspathEntry[0];
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.IClasspathContainer#getDescription()
	 */
	public String getDescription() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.IClasspathContainer#getKind()
	 */
	public int getKind() {
		return K_APPLICATION;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.IClasspathContainer#getPath()
	 */
	public IPath getPath() {
		return new Path(ConnJDTPlugin.DRIVER_CONTAINER_ID).append(this.name);
	}
}

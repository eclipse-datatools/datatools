/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Set of utility methods to process class names from jar files
 * @author brianf
 *
 */
public class DriverUtil {
	
	/**
	 * empty constructor
	 */
	private DriverUtil() {
		// empty constructor
	}

	/**
	 * Returns the number of classes in the jar. This is used for
	 * the progress monitor.
	 * 
	 * @param jarFile
	 * @return
	 * @throws Exception
	 */
	public static int countClassesToProcess ( File jarFile ) throws Exception {
		JarFile jar = new JarFile(jarFile);
		return jar.getManifest().getEntries().size();
	}
	
	/**
	 * Returns a string array of class names from the jar file.
	 * @param jarFile
	 * @param monitor
	 * @return
	 * @throws Exception
	 */
	public static String[] getDriverClassesFromJar ( File jarFile, IProgressMonitor monitor ) throws Exception {
		ArrayList list = new ArrayList();
		JarFile jar = null;
		try {
			jar = new JarFile(jarFile);
		} catch (ZipException e) {
			// must not be a zip file - return empty list
			return new String[0];
		}
		ZipFile zip = new ZipFile(jarFile);
		String taskDescription = 
			ConnectivityPlugin.getDefault().getResourceString("DriverUtil.taskName",  //$NON-NLS-1$
					new Object[]{jarFile.getName()});
		int max = zip.size(); 
		monitor.beginTask(taskDescription, max);
		Enumeration iter = jar.entries();
		while (iter.hasMoreElements()) {
			ZipEntry entry = (ZipEntry) iter.nextElement();
			monitor.worked(1);
			if (entry.getName().endsWith(".class")) { //$NON-NLS-1$
				try {
					String name = entry.getName();
					name = name.substring(0, name.length() - 6);
					name = name.replace('/', '.');
					Class testClass = getDriverClassesFromJar2(jarFile, name);
					if (testClass != null) {
						list.add(testClass.getName());	
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		return (String[]) list.toArray(new String[list.size()]);
	}

	/**
	 * Gets a Class instance from the jar of the supplied class name 
	 * @param jarFile
	 * @param classname
	 * @return
	 * @throws Exception
	 */
	private static Class getDriverClassesFromJar2 (File jarFile, String classname) throws Exception {
		Class outclass = null;
		URL[] jdbcClasspathURL = new URL[1];
		jdbcClasspathURL[0] = jarFile.toURL();

		//
		// Create a new ClassLoader using the jdbcClasspathURL and attempt to
		// load the driver. Delegate to this class' ClassLoader if the class isn't
		// found.
		//
		try {
			//
			// Check this classloader's classpath for driver...
			//
			URLClassLoader classLoader = URLClassLoader.newInstance(jdbcClasspathURL);
			Class driverClass = classLoader.loadClass(Driver.class.getName());
			
			try {
				Class testclass = classLoader.loadClass(classname);

				if (driverClass.isAssignableFrom(testclass)) {
					//	 we've got a JDBC driver
					outclass = testclass;
				}
			} catch (ClassNotFoundException cnfe) {
				//ignore
			} catch (NoClassDefFoundError err) {
				//ignore
			} catch (UnsupportedClassVersionError err) {
				String reason = err.toString();
				Exception ce = new Exception(reason);
				ce.setStackTrace(err.getStackTrace());
				ConnectivityPlugin.getDefault().log(ce);
			}
		} catch (Exception e) {
			String reason = e.toString();
			Exception ce = new Exception(reason);
			ce.setStackTrace(e.getStackTrace());
			throw ce;
		}
        
        return outclass;
	}
}

/*******************************************************************************
 * Copyright (c) 2006, 2009 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: brianf - initial API and implementation
 * 				brianf - update for 267123
 ******************************************************************************/
package org.eclipse.datatools.enablement.jdt.classpath.internal;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.drivers.DriverManager;
import org.eclipse.datatools.enablement.jdt.classpath.DriverClasspathContainer;
import org.eclipse.jdt.core.ClasspathContainerInitializer;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

/**
 * Initializes the driver classpath container 
 * @author brianf
 *
 */
public class DriverClasspathContainerInitializer extends ClasspathContainerInitializer {

	public void initialize(IPath containerPath, IJavaProject javaProject)
		throws CoreException {
		if (containerPath != null && isDriverContainer(containerPath)) {
			String name= containerPath.segment(1);
			DriverInstance di = DriverManager.getInstance().getDriverInstanceByName(name);
			if (di != null) {
				// make sure the driver instance isn't null (in case the driver hasn't been defined yet)
				IClasspathContainer container = new DriverClasspathContainer(di.getName());
				JavaCore.setClasspathContainer(containerPath, new IJavaProject[] { javaProject }, new IClasspathContainer[] {container},  null);
			}
		}
	}
	
	/**
	 * @param path
	 * @return
	 */
	private boolean isDriverContainer(IPath path) {
		return path != null && path.segmentCount() == 2 && ConnJDTPlugin.DRIVER_CONTAINER_ID.equals(path.segment(0));
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jdt.core.ClasspathContainerInitializer#getComparisonID(org.eclipse.core.runtime.IPath, org.eclipse.jdt.core.IJavaProject)
	 */
	public Object getComparisonID(IPath containerPath, IJavaProject project) {
		if (containerPath == null || project == null)
			return null;
			
		return containerPath.segment(0) + "/" + project.getPath().segment(0); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.ClasspathContainerInitializer#canUpdateClasspathContainer(org.eclipse.core.runtime.IPath, org.eclipse.jdt.core.IJavaProject)
	 */
	public boolean canUpdateClasspathContainer(IPath containerPath, IJavaProject project) {
		return isDriverContainer(containerPath);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.ClasspathContainerInitializer#getDescription(org.eclipse.core.runtime.IPath, org.eclipse.jdt.core.IJavaProject)
	 */
	public String getDescription(IPath containerPath, IJavaProject project) {
		if (isDriverContainer(containerPath)) {
			return containerPath.segment(1);
		}
		return super.getDescription(containerPath, project);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.ClasspathContainerInitializer#requestClasspathContainerUpdate(org.eclipse.core.runtime.IPath, org.eclipse.jdt.core.IJavaProject, org.eclipse.jdt.core.IClasspathContainer)
	 */
	public void requestClasspathContainerUpdate(IPath containerPath, IJavaProject project, IClasspathContainer containerSuggestion) throws CoreException {
		// empty
	}

}

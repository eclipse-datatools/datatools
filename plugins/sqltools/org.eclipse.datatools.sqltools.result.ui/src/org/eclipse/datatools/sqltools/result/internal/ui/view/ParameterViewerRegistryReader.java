/*******************************************************************************
 * Copyright 2001, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.ui.view;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.sqltools.result.ui.ExternalParameterViewerProvider;
import org.eclipse.datatools.sqltools.result.ui.view.ParameterViewerProvider;

/**
 * Registry reader for parameter table viewer
 * @author Chetan Bhatia
 */
public class ParameterViewerRegistryReader {

	public static final String EXTERNAL_PARAMETER_VIEWER = "org.eclipse.datatools.sqltools.result.parameterViewer"; //$NON-NLS-1$ //$NON-NLS-2$
	public static final String EXTERNAL_PARAMETER_VIEWER_CLASS = "class"; //$NON-NLS-1$ //$NON-NLS-2$
	public static final String EXTERNAL_PARAMETER_VIEWER_VENDOR = "vendor"; //$NON-NLS-1$
	public static final String EXTERNAL_PARAMETER_VIEWER_ID = "id"; //$NON-NLS-1$
	public static final String EXTERNAL_PARAMETER_VIEWER_DEFAULTVIEWER_NAME = "default_viewer_name"; //$NON-NLS-1$
	private static ParameterViewerRegistryReader myInstance;
	private List myViewers;

	private ParameterViewerRegistryReader() {
		// Singleton class.
	}

	/**
	 * Gets an instance of ParameterRegistryReader
	 * 
	 * @return an instance of ParameterRegistryReader
	 */
	public synchronized static ParameterViewerRegistryReader getInstance() {
		if (myInstance == null) {
			myInstance = new ParameterViewerRegistryReader();
		}
		return myInstance;
	}

	/**
	 * Gets a list of parameter viewers from extensions providers
	 * 
	 * @return a list of parameter viewers provided by extensions
	 */
	public synchronized List getParameterViewers() {
		if (myViewers == null) {
			myViewers = new ArrayList();
			retrieveViewers();
		}
		return myViewers;
	}

	/**
	 * Gets the executable for parameter viewer
	 * 
	 * @param viewerName
	 *            the name of the viewer
	 * @return the executable for the parameter viewer, or null if no match is
	 *         found. This executable extends ExternalParameterViewerProvider
	 */
	public ParameterViewerProvider getParameterViewerExecutable(
			String viewerName) {
		ExternalParameterViewerProvider exe = null;
		List viewers = getParameterViewers();
		if (viewerName != null) {
			Iterator iter = viewers.iterator();
			while (iter.hasNext()) {
				ParameterViewerDescriptor desc = (ParameterViewerDescriptor) iter
						.next();
				if (viewerName.equalsIgnoreCase(desc.getViewerID())) {
					exe = desc.getViewerProvider();
					break;
				}
			}
		}
		return exe;
	}

	/**
	 * Retrieves a list of Parameter viewers from extensions
	 */
	private void retrieveViewers() {
		try {
			IExtensionRegistry extensionRegistry = Platform
					.getExtensionRegistry();
			IExtensionPoint extensionPoint = extensionRegistry
					.getExtensionPoint(EXTERNAL_PARAMETER_VIEWER);
			if (extensionPoint != null) {
				IExtension[] extensions = extensionPoint.getExtensions();
				for (int i = 0; i < extensions.length; i++) {
					IExtension ext = extensions[i];
					IConfigurationElement[] configElements = ext
							.getConfigurationElements();
					for (int config = 0; config < configElements.length; config++) {
						ExternalParameterViewerProvider viewerProvider = (ExternalParameterViewerProvider) configElements[config]
								.createExecutableExtension(EXTERNAL_PARAMETER_VIEWER_CLASS);
						String vendor = configElements[config]
								.getAttribute(EXTERNAL_PARAMETER_VIEWER_VENDOR);
						String id = configElements[config]
								.getAttribute(EXTERNAL_PARAMETER_VIEWER_ID);
						String defaultViewerName = configElements[config]
								.getAttribute(EXTERNAL_PARAMETER_VIEWER_DEFAULTVIEWER_NAME);
						ParameterViewerDescriptor descriptor = new ParameterViewerDescriptor(
								viewerProvider, vendor, id, defaultViewerName);
						myViewers.add(descriptor);
					}
				}
			}
		} catch (CoreException ex) {
			// problem with creating executable, don't add to list
		}
	}
}

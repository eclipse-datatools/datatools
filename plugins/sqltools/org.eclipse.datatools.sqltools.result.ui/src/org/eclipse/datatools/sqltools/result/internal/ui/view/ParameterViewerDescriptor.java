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

import org.eclipse.datatools.sqltools.result.ui.ExternalParameterViewerProvider;

/**
 * Describes a parameter table viewer
 * @author Chetan Bhatia
 **/
public class ParameterViewerDescriptor {

	private ExternalParameterViewerProvider viewerProvider;
	private String vendor;
	private String viewerID;
	private String defaultViewer;
/**
 * 
 * @param viewer the parameter viewer provider
 * @param vendor the vendor name
 * @param viewerID display viewer id for this viewer
 * @param defaultViewerName the default viewer
 */
		public ParameterViewerDescriptor(ExternalParameterViewerProvider viewer,
			String vendor, String viewerID, String defaultViewerName) {
		this.viewerProvider = viewer;
		this.vendor = vendor;
		this.viewerID = viewerID;
		this.defaultViewer = defaultViewerName;
	}

	/**
	 * Gets the parameter table viewer provider
	 * @return the parameter table viewer provider
	 */
	public ExternalParameterViewerProvider getViewerProvider() {
		return viewerProvider;
	}
	/**
	 * Sets the parameter table viewer
	 * @param viewer the parameter table viewer provider
	 */

	public void setViewerProvider(ExternalParameterViewerProvider viewerProvider) {
		this.viewerProvider = viewerProvider;
	}
	/**
	 * Gets the database vendor
	 * @return the database vendor
	 */
	public String getVendor() {
		return vendor;
	}
	/**
	 * Sets the database vendor
	 * @param vendor the database vendor
	 */
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	/**
	 * Gets the viewer name
	 * @return the name of the viewer
	 */
	public String getViewerID() {
		return viewerID;
	}
	/**
	 * Sets the viewer name
	 * @param name the name of the viewer
	 */
	public void setViewerID(String viewerID) {
		this.viewerID = viewerID;
	}
	/**
	 * gets the default viewer
	 * @return the name of the default viewer
	 */
	public String getDefaultViewer() {
		return defaultViewer;
	}
	/**
	 * Sets the default viewer
	 * @param viewer the default viewer name
	 */
	public void setDefaultViewer(String defaultViewer) {
		this.defaultViewer = defaultViewer;
	}

}

/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.ui.view;

import org.eclipse.datatools.sqltools.result.ui.ExternalResultSetViewerProvider;

/**
 * Describes a result set viewer
 * @author Quy On
 */
public class ResultSetViewerDescriptor
{
	private ExternalResultSetViewerProvider resultSetViewer;
	private String vendor;
	private String viewerID;
	private String defaultViewer;
	
	/**
	 * Creates an instance of ResultSetViewerDescriptor
	 * @param viewer the result set viewer provider
	 * @param vendor the vendor name
	 * @param name the display name for this viewer
	 * @param defaultViewer the default viewer
	 */
	public ResultSetViewerDescriptor(ExternalResultSetViewerProvider viewer, String vendor,
			String name, String defaultViewer)
	{
		resultSetViewer = viewer;
		this.vendor = vendor;
		viewerID = name;
		this.defaultViewer = defaultViewer;
	}
	
	/**
	 * Sets the result set viewer
	 * @param viewer the result set viewer provider
	 */
	public void setViewerProvider(ExternalResultSetViewerProvider viewer)
	{
		resultSetViewer = viewer;
	}
	
	/**
	 * Gets the result set viewer provider
	 * @return the result set viewer provider
	 */
	public ExternalResultSetViewerProvider getViewerProvider()
	{
		return resultSetViewer;
	}
	
	/**
	 * Sets the database vendor
	 * @param vendor the database vendor
	 */
	public void setVendor(String vendor)
	{
		this.vendor = vendor;
	}
	
	/**
	 * Gets the database vendor
	 * @return the database vendor
	 */
	public String getVendor()
	{
		return vendor;
	}
	
	/**
	 * Sets the viewer name
	 * @param name the name of the viewer
	 */
	public void setViewerID(String name)
	{
		viewerID = name;
	}
	
	/**
	 * Gets the viewer name
	 * @return the name of the viewer
	 */
	public String getViewerID()
	{
		return viewerID;
	}
	
	/**
	 * Sets the default viewer
	 * @param viewer the default viewer name
	 */
	public void setDefaultViewer(String viewer)
	{
		this.defaultViewer = viewer;
	}
	
	/**
	 * gets the default viewer
	 * @return the name of the default viewer
	 */
	public String getDefaultViewer()
	{
		return defaultViewer;
	}
}

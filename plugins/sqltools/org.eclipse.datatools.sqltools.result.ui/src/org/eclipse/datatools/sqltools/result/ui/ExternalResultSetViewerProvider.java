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
package org.eclipse.datatools.sqltools.result.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.IResultSetObject;
import org.eclipse.datatools.sqltools.result.ui.view.ResultsViewControl;

/**
 * Abstract class that external viewers for the long data must implement
 * @author Quy On
 */
public abstract class ExternalResultSetViewerProvider
{
	protected TableViewer tableViewer;
	protected Composite parentComposite;
	protected int tableStyle;
	protected IResultInstance resultInstance;
	protected IResultSetObject resultSetObject;
	protected ResultsViewControl resultViewControl;
	protected boolean showRowCount;
	protected MenuManager menuManager;
	
	
	/**
	 * Creates an instance of ExternalResultSetViewer
	 */
	public ExternalResultSetViewerProvider()
	{
		super();
		tableStyle = SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL;
	}
	
	/*
	 * Sets the parent of the tableViewer
	 * @param parent the parent Composite for the table viewer
	 */
	public void setParentComposite(Composite parent)
	{
		parentComposite = parent;
	}
	
	/**
	 * Sets the style for the table viewer
	 * @parent style table viewer style
	 */
	public void setStyle(int style)
	{
		tableStyle = style;
	}
	
	/**
	 * Sets the IResultInstance instance
	 * @param instance the IResultInstance
	 */
	public void setResultInstance(IResultInstance instance)
	{
		resultInstance = instance;
	}
	
	/**
	 * Sets the IResultSetObject 
	 * @parent result the IResultSetObject containing the result
	 */
	public void setResult(IResultSetObject result)
	{
		resultSetObject = result;
	}
	
	/**
	 * Sets whether or not to show the row number in the results view
	 * @param showRow whether or not to show the row count
	 */
	public void setShowRow(boolean showRow)
	{
		showRowCount = showRow;
	}	
	
	/**
	 * Sets the Results view control
	 * @param control the result view control
	 */
	public void setResultViewControl(ResultsViewControl control)
	{
		resultViewControl = control;
	}
	
	/**
	 * Configures the viewer.
	 * The default implementation creates a ResultSetViewer.  Subclasses should extend
	 * add additional menus, listeners and label and content providers
	 */
	public void configureViewer()
	{
		tableViewer = new ExternalResultSetViewer(parentComposite, tableStyle, resultInstance,
				resultSetObject, showRowCount, resultViewControl);
		menuManager = ((ExternalResultSetViewer)tableViewer).getMenuManager();
	}
	
	/**
	 * Configures the result set viewer
	 * @param parent the parent composite	 
	 * @param instance the IResultInstance
	 * @param result the IResultSetObject
	 * @param showRowCount whether or not to show the row numbers in the result
	 * @param control the ResultsViewControl
	 */
	public void configureViewer(Composite parent, IResultInstance instance,
			IResultSetObject result, boolean showRowCount, ResultsViewControl control)
	{
		parentComposite = parent;		
		resultInstance = instance;
		resultSetObject = result;
		this.showRowCount = showRowCount;
		resultViewControl = control;
		configureViewer();			
	}
	
	/**
	 * Gets the menu manger
	 * @return the menuManager for the viewer
	 */
	protected MenuManager getMenuManager()
	{
		return menuManager;
	}	
	
	/**
	 * Gets the result set viewer
	 * @return the result set viewer
	 */
	public TableViewer getViewer()
	{
		return tableViewer;
	}
	
	/**
	 * Gets this viewers table
	 * @return this viewers underlying table
	 */
	public Table getTable()
	{
		if (tableViewer != null)
		{
			return tableViewer.getTable();
		}
		return null;
	}
}

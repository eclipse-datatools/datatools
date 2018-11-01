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

import org.eclipse.datatools.sqltools.result.IResultSetObject;
import org.eclipse.datatools.sqltools.result.internal.ui.viewer.ResultSetViewer;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.ui.view.ResultsViewControl;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.jface.action.MenuManager;

/**
 * Extends ResultSetViewer to allow extensions to inherit the default result set viewer
 * @author Quy On
 */
public class ExternalResultSetViewer extends ResultSetViewer
{
	/**
     * Creates a result set viewer.
     * 
     * @param parent the parent   
     * @param style the viewer style  
     * @param instance the result instance
     * @param result the result set object
     * @param showRowCount the show row count option
     * @param resultsViewControl the control for the results view
     */
    public ExternalResultSetViewer(Composite parent, int style, IResultInstance instance, IResultSetObject result,
        boolean showRowCount, ResultsViewControl resultsViewControl)
    {
    	super(parent, style, instance, result, showRowCount, resultsViewControl);
    }
    
    /**
     * Gets the Menu manager
     * @return the menu manager from super
     */
    public MenuManager getMenuManager()
    {
    	return super.getMenuManager();
    }
    
    protected void doUpdateItem(Widget widget, Object element, boolean fullMap)
    {
    	super.doUpdateItem(widget, element, fullMap);
    }
}

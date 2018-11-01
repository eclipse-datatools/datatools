/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Sybase, Inc. - updated to work in 3.2 navigator framework
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.actions.popup;

import java.util.Iterator;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCCatalog;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.util.resources.ResourceLoader;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.SWT;
import org.eclipse.ui.actions.ActionFactory;


/**
 * @author wliu, brianf
 *  
 */
public class RevisedRefreshAction extends RevisedAbstractAction
{
    private IStructuredSelection selection;
	private StructuredViewer aViewer;
    
    /**
     * Constructor
     * @param viewer
     */
    public RevisedRefreshAction( StructuredViewer viewer ) {
    	super();
    	this.setText(ResourceLoader.INSTANCE.queryString("DATATOOLS.SERVER.UI.EXPLORER.REFRESH"));//$NON-NLS-1$
    	this.setToolTipText(this.getText());
		this.setActionDefinitionId(ActionFactory.REFRESH.getId());
		this.setAccelerator(SWT.F5);
		this.aViewer = viewer;
    }

    private void refreshCatalogObject (Object [] catalogObjects)
    {
        for (int i = 0, n = catalogObjects.length; i < n; i++)
        {
	        if (catalogObjects[i]  instanceof ICatalogObject)
	        {
	            ((ICatalogObject)catalogObjects[i]).refresh();
	        }
        }
    }
    
    private void refreshVirtualNode (IVirtualNode virtualNode)
    {
		Object object = virtualNode.getParent();
		
		// to get around the unused catalog, we skip it and go to the database
		// hack for bug 248366 BTF
		if (object != null && 
				object instanceof JDBCCatalog && 
					((JDBCCatalog)object).getName().trim().length() == 0)
			object = ((JDBCCatalog)object).getCatalogDatabase();
		
		virtualNode.removeAllChildren();
		if (object instanceof ICatalogObject)
		{
			refreshCatalogObject((ICatalogObject) object);
		}
		else if (object instanceof IVirtualNode)
		{
			refreshVirtualNode((IVirtualNode) object);
		}
	}

    private void refreshCatalogObject (ICatalogObject catalogObject)
    {
        refreshCatalogObject (new ICatalogObject [] {catalogObject});
    }
    
    protected void setSelection(ISelection selection)
    {
        if (selection instanceof IStructuredSelection)
        {
            this.selection = (IStructuredSelection) selection;
        }
    }

    protected ISelection getSelection()
    {
        return selection;
    }

    public void selectionChanged (IAction action, ISelection sel)
	{
        if (sel instanceof IStructuredSelection && action != null)
        {
			if (((IStructuredSelection) sel).size() == 1)
			{
				action.setEnabled(true);
			}
			else
			{
				action.setEnabled(false);
			}
		}
		setSelection (sel);
	}

    public void run()
    {
    	if (this.aViewer != null) {
    		setSelection(this.aViewer.getSelection());
    	}
    	
        for (Iterator iterator = ((IStructuredSelection)getSelection()).iterator(); iterator.hasNext();)
        {
            Object selectedObject = iterator.next();
            if (selectedObject instanceof IVirtualNode)
            {
                refreshVirtualNode ((IVirtualNode)selectedObject);
            }
            else if (selectedObject instanceof ICatalogObject)
            {
                refreshCatalogObject ((ICatalogObject)selectedObject);
            }
        }
    }
}

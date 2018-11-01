/*******************************************************************************
 * Copyright (c) 2001, 2004, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.ddlgen.internal.ui.actions.popup;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.icons.ImageDescription;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources.ResourceLoader;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.sqltools.ddlgen.internal.ui.wizards.FEWizard;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.navigator.CommonViewer;

public class ForwardEngineerAction extends Action
{
    private static final String TEXT = ResourceLoader.getResourceLoader().queryString("GENERATE_DDL_MENU_TEXT");
    private static final ImageDescriptor descriptor = ImageDescription.getGenerateCodeDescriptor();
    
    protected SelectionChangedEvent event;
	protected CommonViewer viewer;

	public ForwardEngineerAction()
	{
		this.setImageDescriptor(descriptor);
		this.setDisabledImageDescriptor(descriptor);
		this.setText(TEXT);
		this.setToolTipText(TEXT);
	}

	public void setCommonViewer(CommonViewer viewer)
	{
		this.viewer = viewer;
	}

	public void selectionChanged(SelectionChangedEvent event)
	{
		this.event = event;
	}
	
    private void addSQLObject (List linkedList, Object selected)
    {
        if (selected instanceof SQLObject)
        {
            linkedList.add(selected);
        }
    }
    
	protected List getMultipleSelection ()
	{
		List linkedList = new LinkedList ();
		if (this.event.getSelection() instanceof IStructuredSelection)
		{
			for (Iterator i = ((IStructuredSelection)this.event.getSelection()).iterator(); i.hasNext();)
			{
				Object nextSelected = i.next();
				if (nextSelected instanceof IVirtualNode)
                {
                    Object[] children = ((IVirtualNode)nextSelected).getChildrenArray();
				    for (int j = 0, n = children.length;  j < n; j++) 
                    {
                        addSQLObject (linkedList, children[j]);
                    }
                }
                else
                {
                    addSQLObject (linkedList, nextSelected);
                }
			}
		}
		return linkedList;
	}
	
	public void run()
	{
		try
		{
			List list = this.getMultipleSelection();
			if (list.size() > 0)
			{
				Wizard wizard = new FEWizard(list);
				WizardDialog dialog = new WizardDialog(this.viewer.getControl().getShell(), wizard);
				dialog.create();
				dialog.open();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

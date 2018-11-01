/*******************************************************************************
 * Copyright (c) 2001, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.data.internal.ui.editor;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.popup.AbstractAction;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.data.internal.ui.DataUIPlugin;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.datatools.sqltools.data.internal.core.editor.TableEditorFilterRegistryReader;
import org.eclipse.datatools.sqltools.data.internal.core.editor.ITableEditorResultFilter;

public class EditTableDataAction extends AbstractAction
{
    private static final String TEXT = Messages.getString("TableDataEditMenu"); //$NON-NLS-1$

    protected Table table = null;

    protected void initialize()
    {    	
    	initializeAction(null, null, TEXT, TEXT);
    }

    public void run()
    {    	
    	if (table == null)
            return;
    	
    	// check for extensions here
    	boolean keepGoing = true;
    	TableEditorFilterRegistryReader filterReader = TableEditorFilterRegistryReader.getInstance();
    	if (filterReader.isExtenionFound() && filterReader.isMatchingVendor(table))
    	{    		
    		ITableEditorResultFilter filterExecutable = filterReader.createTableEditorResultFilterExecutable();
    		if (filterExecutable.isUseExternalFilterDialog())
    		{
    			// use external filter dialog to get filter
    			filterExecutable.setTable(table);
    			keepGoing = filterExecutable.open();
    			filterReader.setFilterCanceled(!keepGoing);
    		}
    	}
    	if (keepGoing)
    	{
    		// launch editor when either there is no extension or user did not cancel
	        IWorkbenchPage workbenchPage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	
	        try
	        {
                workbenchPage.openEditor(new TableDataEditorInput(table), getEditorId());
	        }
	        catch (PartInitException e)
	        {
	            DataUIPlugin.getDefault().writeLog(IStatus.ERROR, 0, e.getMessage(), e);
	        }
    	}
    }

    public void selectionChanged(SelectionChangedEvent event)
    {
        table = null;

        if (event.getSelection() instanceof IStructuredSelection)
            if (((IStructuredSelection) event.getSelection()).getFirstElement() instanceof Table)
                table = (Table) ((IStructuredSelection) event.getSelection()).getFirstElement();

        boolean b = true;
        b &= table != null && table.getColumns().size() > 0;
        b &= DataUIPlugin.isGroupIDOK(table);
        setEnabled(b);
    }
    
    protected String getEditorId() 
    {
        return "org.eclipse.datatools.sqltools.data.internal.ui.editor.tableDataEditor"; //$NON-NLS-1$
    }
}

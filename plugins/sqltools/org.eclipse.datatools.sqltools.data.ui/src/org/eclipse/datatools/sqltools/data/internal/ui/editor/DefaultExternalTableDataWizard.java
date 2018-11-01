/*******************************************************************************
 * Copyright (c) 2001, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.data.internal.ui.editor;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.sqltools.data.internal.core.DataCorePlugin;
import org.eclipse.datatools.sqltools.data.internal.core.editor.IRowData;
import org.eclipse.datatools.sqltools.data.internal.core.editor.ITableData;
import org.eclipse.datatools.sqltools.data.internal.core.editor.ITableData2;
import org.eclipse.datatools.sqltools.data.internal.core.editor.RowDataImpl;
import org.eclipse.datatools.sqltools.data.internal.ui.DataUIPlugin;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.custom.BusyIndicator;

/**
 * The default implementation for the external table data editor.
 * @author sschaer
 */
public class DefaultExternalTableDataWizard extends Wizard implements IExternalTableDataEditor{

    protected boolean successfulPerformFinish = false;
	
    /** the default page */
    protected DefaultExternalTableDataWizardPage defaultPage;
	
    /** The reference to the TableDataEditor */
    protected ITableDataEditor editor;

    /**
     */
    public DefaultExternalTableDataWizard(){
        super();
    }

    /**
     * initializes the wizard and opens the wizard dialog 
     * @see org.eclipse.datatools.sqltools.data.internal.ui.editor.IExternalTableDataEditor#externalEdit(org.eclipse.datatools.sqltools.data.internal.ui.editor.TableDataEditor)
     */
    public void externalEdit(ITableDataEditor editor) {
        init(editor);   
        refreshValueFromDatabase();
        WizardDialog dialog = new WizardDialog(editor.getEditorSite().getShell(), this);
        dialog.setMinimumPageSize(300,275);
        dialog.create();
        // TC: wizard page sized should determine the wizard size
        //dialog.getShell().setSize(500,350);
        dialog.open(); 
    }
    
    protected boolean refreshValueFromDatabase()
    {
        final int col = editor.getCursor().getColumn();
        final IRowData row = editor.getRow();
        
        IRunnableWithProgress runnable= new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
			    try {			        
			    	if (row != null)
			        {
			        	ITableData td = ((RowDataImpl)row).getTable();
			        	if ( td.getColumnDataAccessor(col).isSnippet( row.getValue(col), td.getColumnType(col) ) )
			        		((RowDataImpl)row).doRefresh(col, false);
			        }
			    } catch (Exception ex) {
			        throw new InvocationTargetException(ex);
			    }
				if (monitor.isCanceled())
					throw new InterruptedException();
			}
		};
            
		try {
		    editor.getSite().getWorkbenchWindow().run(true, true, runnable);
        } catch (InterruptedException ex) {
            return false;
        } catch (InvocationTargetException ex) {
            Throwable t = ex.getCause();
            IStatus status = new Status(IStatus.WARNING, DataCorePlugin.ID	,
                    IStatus.OK, t.getMessage(), t);
            ErrorDialog.openError(editor.getSite().getShell(), Messages.getString("DefaultExternalTableDataWizard.RetrieveValueError"), //$NON-NLS-1$
                    Messages.getString("DefaultExternalTableDataWizard.RetrieveValueMessage"),  //$NON-NLS-1$
                    
                    status);    
            DataUIPlugin.getDefault().writeLog(status.getSeverity(), IStatus.OK, status.getMessage(), t);
            return false;
        }
        
        editor.getTableViewer().refresh((RowDataImpl)editor.getRow());
        editor.getCursor().redraw();

    	return true;
    }
    
    /**
     * initializes the wizard with the TableDataEditor
     * @param editor
     */
    protected void init(ITableDataEditor editor){
	    // input validation
        if (editor == null) {
            throw new IllegalArgumentException(Messages.getString("DefaultExternalTableDataEditorWizard.InitError"));  //$NON-NLS-1$
        }
        this.editor = editor;
        int columnIndex = editor.getCursor().getColumn();
        try{
            String type = null;
            if (editor.getTableData() instanceof ITableData2) {
        	type = ((Column)((ITableData2)editor.getTableData()).getResultColumns().get(columnIndex)).getDataType().getName();
            }
            else {
        	type = ((Column)editor.getSqlTable().getColumns().get(columnIndex)).getDataType().getName();
            }
            setWindowTitle(Messages.getString("DefaultExternalTableDataEditorWizard.Title", new Object[]{type}));  //$NON-NLS-1$
        } catch (Exception e){
            setWindowTitle(Messages.getString("DefaultExternalTableDataEditorWizard.DefaultTitle"));  //$NON-NLS-1$
        }
		setDefaultPageImageDescriptor(DataUIPlugin.getDefault().getPngImageDescriptor("ext_table_editor"));  //$NON-NLS-1$
	}

    /**
     * creates the single page for this default wizard.
     * Instead of overwriting this method, subclasses should overwrite
     * createDefaultPage()
     * @see #createDefaultPage()
     */
	public void addPages() {
		if (getPageCount()==0) {
			defaultPage = createDefaultPage();
			addPage(defaultPage);
		}
		defaultPage.init(editor);
	}	
	
	/**
     * Creates the default page. 
     * Subclasses should overwrite this method if they want to provide 
     * a different default page
	 * @return the defaultPage
	 */
    protected DefaultExternalTableDataWizardPage createDefaultPage(){
    	return new DefaultExternalTableDataWizardPage("NamePage"); //$NON-NLS-1$
    }
	
	/**
     * Updates the cell data by calling updateRowData() on the default page
	 * @see org.eclipse.jface.wizard.IWizard#performFinish()
     * @see DefaultExternalTableDataWizardPage#updateRowData()
	 */
	public boolean performFinish() {
		BusyIndicator.showWhile(null, new Runnable(){
			public void run(){
               successfulPerformFinish = defaultPage.updateRowData(); 
			}
		});			
		return successfulPerformFinish;
	}
}

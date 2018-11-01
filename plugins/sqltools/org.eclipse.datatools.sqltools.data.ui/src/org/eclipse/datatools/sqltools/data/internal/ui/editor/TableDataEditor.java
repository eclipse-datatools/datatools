/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.data.internal.ui.editor;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.core.connection.DatabaseConnectionRegistry;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfoImpl;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.sqltools.data.internal.core.DataCorePlugin;
import org.eclipse.datatools.sqltools.data.internal.core.common.Output;
import org.eclipse.datatools.sqltools.data.internal.core.editor.IRowData;
import org.eclipse.datatools.sqltools.data.internal.core.editor.ITableData;
import org.eclipse.datatools.sqltools.data.internal.core.editor.TableDataImpl;
import org.eclipse.datatools.sqltools.data.internal.ui.DataUIPlugin;
import org.eclipse.datatools.sqltools.data.internal.ui.OutputItemAdapter;
import org.eclipse.datatools.sqltools.result.OperationCommand;
import org.eclipse.datatools.sqltools.result.ResultsViewAPI;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

/**
 * This editor allows borwsing and editing the data stored in a SQL table.
 * The logic for accessing and altering the data is implemented in TableDataImpl.
 * @author groux
 */
public class TableDataEditor extends EditorPart implements ITableDataEditor
{
    /** JFace table viewer */
    protected TableViewer tableViewer;
    
    /** Application model for the table data */
    protected ITableData tableData;
    
    /** SQLObject for the table */
    protected org.eclipse.datatools.modelbase.sql.tables.Table sqlTable;
    
    /** Editor dirty */
    protected boolean dirty = false;
    
    /** Spreadsheet-like cursor */
    protected TableDataTableCursor cursor;
    
    protected TableDataEditorSelectionProvider selectionProvider;
    
    private boolean askConfirmation;    
    private Set dirtyBackgroundSet;
    
    public TableDataEditor() {    	
    	dirtyBackgroundSet = new HashSet();
    }
    
    public void init(IEditorSite site, IEditorInput input) throws PartInitException {
        if (!(input instanceof TableDataEditorInput))
            throw new PartInitException("Invalid Input: Must be TableEditorInput"); //$NON-NLS-1$
        
        setSite(site);
        setInput(input);
        sqlTable = ((TableDataEditorInput)input).getTable();
        
        this.setPartName(sqlTable.getName());
    }
    
    public void createPartControl(Composite parent) {
        
        tableViewer = new TableViewer(parent, SWT.HIDE_SELECTION);
        tableViewer.getTable().setHeaderVisible(true);      
        tableViewer.getTable().setLinesVisible(true);
        tableViewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));

        tableViewer.setLabelProvider( new TableDataLabelProvider() );
        tableViewer.setContentProvider( new TableDataContentProvider() );                
        
        try {
            tableData = new TableDataImpl(sqlTable);
            configureTable();
            tableViewer.setInput(tableData);            
        } 
        catch (CoreException core) {         	
        	String title = Messages.getString("TableDataEditor.ErrorInitializingEditor"); //$NON-NLS-1$
        	ErrorDialog.openError(tableViewer.getControl().getShell(), title, null, core.getStatus());
        	//return;
        } 
        catch (Exception ex) {          	
        	DataUIPlugin.getDefault().writeLog(IStatus.ERROR, 0, ex.getMessage(), ex);
            displayException(Messages.getString("TableDataEditor.ErrorInitializingEditor"), ex); //$NON-NLS-1$
        }
        
        cursor = new TableDataTableCursorExternalEditingSupport(tableViewer, this);
        if (tableViewer.getTable().getItemCount()>0)
        	cursor.setSelection(0,0);

        selectionProvider = new TableDataEditorSelectionProvider(this);
        
        createContextMenu();
    }
    
    public void dispose() {
        if (tableData!=null)
            tableData.dispose();
        super.dispose();
    }
    
    public void doSave(IProgressMonitor monitor)
    {        
        // Add confirmation from user before removing rows from a table
    	if (askConfirmation)
    	{
    		boolean okay = MessageDialog.openQuestion(tableViewer.getControl().getShell(),
    				Messages.getString("TableDataEditor.RemoveRowsConfirmation.title"),
    				Messages.getString("TableDataEditor.RemoveRowsConfirmation.message" , 
    						new Object[] {sqlTable.getSchema().getName() + 
    						"." + sqlTable.getName()}));
    		if (!okay)
    		{    			
    			return;
    		}
	        else {
    			askConfirmation = false;
    		}
    	}
    	
    	OperationCommand item = initDbOutputItem();
    	try {            
	        //int ret = tableData.save(new OutputItemAdapter(item));
	        ResultsViewAPI resultsView = ResultsViewAPI.getInstance();
	        resultsView.createNewInstance(item, null);
	        resultsView.updateStatus(item, OperationCommand.STATUS_STARTED);
	        int ret = tableData.save(new OutputItemAdapter(item));	
	        resultsView.updateStatus(item, ret);
	        if (ret==Output.STATUS_SUCCEEDED || ret==Output.STATUS_WARNING) {
	            setDirty(false);
	            revertToOriginalBackground();
	        }
	        else {
	            monitor.setCanceled(true);
	        }
    	}
    	catch (Exception ex) {
    		ResultsViewAPI.getInstance().updateStatus(item, OperationCommand.STATUS_CRITICAL_ERROR);
    		DataUIPlugin.getDefault().writeLog(IStatus.ERROR, 0, ex.getMessage(), ex);
    		displayException(Messages.getString("TableDataEditor.ErrorWhileSaving"), ex); //$NON-NLS-1$
    		monitor.setCanceled(true);
    	}
    	finally {
            ResultsViewAPI.getInstance().saveDetailResults(item);
        }
    }
    
    protected OperationCommand initDbOutputItem()
    {
      /*  String qualifiedTableName = DataCorePlugin.getQualifiedTableName(sqlTable);
        
        OutputItem item = OutputViewAPI.getInstance().findOutputItem(qualifiedTableName, OutputItem.ACTION_EDIT, true);
        if (item==null) {
            item = new OutputItem(OutputItem.STATUS_IN_PROGRESS, OutputItem.ACTION_EDIT, sqlTable.getName(), qualifiedTableName);
            OutputViewAPI.getInstance().addOutputItem(item, true);            
        } else {
            OutputViewAPI.getInstance().resetOutputItem(item, true);
            OutputViewAPI.getInstance().updateStatus(item, OutputItem.STATUS_IN_PROGRESS, true);
        }
        
        return item; */
    	String qualifiedTableName = DataCorePlugin.getQualifiedTableName(sqlTable);
        
        int actionType = OperationCommand.ACTION_EDIT;
        String displayStr = qualifiedTableName;
        String consumerName = null;  
        Database database = sqlTable.getSchema().getCatalog() != null ?
        		sqlTable.getSchema().getCatalog().getDatabase():
        		sqlTable.getSchema().getDatabase();        
        ConnectionInfo connInfo = DatabaseConnectionRegistry.getConnectionForDatabase(database);
        String connectionProfieName = 
        	((ConnectionInfoImpl)connInfo).getConnectionProfile().getName();
        
        String databaseName = database.getName();
        
        OperationCommand oCommand = new OperationCommand(actionType, displayStr,
        		consumerName, connectionProfieName, databaseName);     
        
        return oCommand;
    }

    public boolean isSaveAsAllowed() {
        return false;
    }
    
    public void doSaveAs() {
    }

    public boolean isDirty() {
        return dirty;
    }
    
    public void setDirty(boolean value) {    	
        dirty = value;
        firePropertyChange(PROP_DIRTY);
    }
    
    public void doRevert() {
        tableData.revert();
        tableViewer.refresh();
        cursor.redraw();
        setDirty(false);
        revertToOriginalBackground();
    }
    
    public void doRefresh() {
        if (getEditorSite().getPage().saveEditor(this, true))
	        try {
	            tableData = new TableDataImpl(sqlTable);
	            tableViewer.setInput(tableData);          
	            cursor.redraw();
	            setDirty(false);
	        } catch (Exception ex) {
	            DataUIPlugin.getDefault().writeLog(IStatus.ERROR, 0, ex.getMessage(), ex);
	            displayException(Messages.getString("TableDataEditor.ErrorRefreshing"), ex); //$NON-NLS-1$
	        }
    }
    
    public IRowData getRow()
    {
        IRowData rowData = null;
        
        TableItem row = cursor.getRow();
        if (row != null) {
            Object obj = row.getData();
            if (obj instanceof IRowData) {
                rowData = (IRowData) obj;
            }
        }
        
        return rowData;
    }
    
    public IRowData getOrCreateRow()
    {
        IRowData row = getRow();
        if (row==null) {
            IRowData newRow = tableData.insertRow();
            tableViewer.insert(newRow, tableViewer.getTable().getItemCount()-1);  
            cursor.setSelection(tableViewer.getTable().getItemCount()-2, cursor.getColumn());  
            cursor.redraw();
            return newRow;
        }
        return row;
    }
    
    public void doInsertRow() {
    	cursor.setSelection(tableViewer.getTable().getItemCount()-1, 0);
        doUpdateValue();            	
    }
    
    public void doUpdateValue() {
        cursor.edit();
    }
    
    public void doSetNull() {
        IRowData row = getOrCreateRow();
        row.updateValue(cursor.getColumn(), null);
        tableViewer.refresh(row);
        cursor.redraw();
        setDirty(true);
    }
    
//    public void setDefault() {
//        Object row = cursor.getRow().getData();
//        if (row instanceof IRowData) {
//            Column col = (Column)sqlTable.getColumns().get(cursor.getColumn());
//            if (col.getDefaultValue()!=null) {
//                Object o = DataDeserializer.deserialize(col.getDefaultValue(), tableData.getColumnType(cursor.getColumn()));
//    	        ((IRowData)row).updateValue(cursor.getColumn(), o);
//    	        tableViewer.refresh(row);
//    	        cursor.redraw();
//    	        setDirty(true);
//            }
//        }
//    }
//    
//    public boolean getColumnHasDefault()
//    {
//        Column col = (Column)sqlTable.getColumns().get(cursor.getColumn());
//        return col.getDefaultValue()!=null;
//    }
    
    public void doDelete() {
        IRowData row = getRow();
        if (row!=null) {
            dirtyBackgroundSet.remove(cursor.getRow());
	        tableData.deleteRow(row);
	        tableViewer.remove(row);
	        setDirty(true);
	        askConfirmation = true;
        }
    }
    
    protected void createContextMenu() {
        
        final TableDataEditorActionBarContributor contributor = DataUIPlugin.getDefault().getTableDataEditorContributor();
       
        // Create menu manager.
        MenuManager menuMgr = new MenuManager();
        menuMgr.setRemoveAllWhenShown(true);
        menuMgr.addMenuListener(new IMenuListener() {
            public void menuAboutToShow(IMenuManager mgr) {
                mgr.add(contributor.revertAction);
                mgr.add(contributor.refreshAction);
                mgr.add(new Separator());
                mgr.add(contributor.updateAction);
                mgr.add(contributor.setNullAction);
//                mgr.add(contributor.setDefaultAction);
                mgr.add(contributor.insertAction);
                mgr.add(contributor.deleteAction);
                mgr.add(new Separator());
                mgr.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
                mgr.add(new Separator());
                mgr.add(contributor.saveAction);
 //               mgr.add( getEditorSite().getActionBars().getGlobalActionHandler(ActionFactory.REFRESH.getId()) );
            }
        });

        // Create menu.
        Menu menu = menuMgr.createContextMenu(tableViewer.getTable());
        tableViewer.getTable().setMenu(menu);
        cursor.setMenu(menu);
        
        // Register menu for extension.
        getEditorSite().registerContextMenu(menuMgr, selectionProvider);
        
    }

    public void setFocus() {
        cursor.setFocus();
        
    }

    protected void configureTable()
    {
        TextCellEditor textEditor = new TableDataCellEditor(this, tableViewer.getTable());
        
        CellEditor[] editors = new CellEditor[tableData.getColumnCount()];
        String[] properties = new String[tableData.getColumnCount()];

        for (int i=0; i<tableData.getColumnCount(); ++i) {
            TableColumn col = new TableColumn(tableViewer.getTable(), SWT.NONE);
            col.setWidth(100);
            col.setText(tableData.getColumnHeader(i));
            col.pack();
            editors[i] = textEditor;
            properties[i] = tableData.getColumnName(i);
        }

        tableViewer.setCellEditors(editors);
        tableViewer.setColumnProperties(properties);
        tableViewer.setCellModifier(new TableDataCellModifier(this, tableViewer));
        
        tableViewer.getTable().pack();
    }


    public ITableData getTableData() {
        return tableData;
    }
    
    public org.eclipse.datatools.modelbase.sql.tables.Table getSqlTable() {
        return sqlTable;
    }
    
    public TableDataTableCursor getCursor() {
        return cursor;
    }
    
    public boolean isReadonly() {
        return tableData.isReadonly();
    }
    
    protected void displayException(String msg, Exception ex) {
        IStatus warning = new Status(IStatus.ERROR, DataUIPlugin.PLUGIN_ID, 1, ex.toString(), ex);
        ErrorDialog.openError(tableViewer.getControl().getShell(), msg, null, warning);
    }

	public TableDataEditorSelectionProvider getSelectionProvider() {
		return selectionProvider;
	}
	
	/**
	 * Marks the background of the table item and column to indicate that it is dirty
	 * @param columnIndex the index of column to change color
	 * @param item the TableItem to change the background color	 
	 */
	public void setDirtyBackground(int columnIndex, TableItem item)
	{
		int itemCount = tableViewer.getTable().getItemCount();
		if (item != null) 
		{			
			if (tableViewer.getTable().indexOf(item) == itemCount - 1)
			{
				item = tableViewer.getTable().getItem(itemCount - 2);
			}
			Display display = Display.getCurrent();
			item.setBackground(columnIndex, display.getSystemColor(SWT.COLOR_YELLOW));			
			dirtyBackgroundSet.add(item);
		}
	}
	
	/**
	 * Reverts the dirty background items to the original color
	 */
	protected void revertToOriginalBackground()
	{
		Display display = Display.getCurrent();
		int columns = tableViewer.getTable().getColumnCount();
		Iterator iter = dirtyBackgroundSet.iterator();
		while(iter.hasNext())
		{
			TableItem item = (TableItem)iter.next();
			for (int i=0;i<columns;i++)
			{
				item.setBackground(i, display.getSystemColor(SWT.COLOR_WHITE));
			}			
		}		
		dirtyBackgroundSet.clear();
	}
	
	/**
	 * Removes the dirty indicator by reseting the background color to white
	 * @param columnIndex the index of the column to change color
	 * @param item the TableItem to reset the background color
	 */
	public void removeDirtyBackground(int columnIndex, TableItem item)
	{
		if (item != null)
		{
			Display display = Display.getCurrent();
			item.setBackground(columnIndex, display.getSystemColor(SWT.COLOR_WHITE));			
			dirtyBackgroundSet.remove(item);
		}
	}
	
	public TableViewer getTableViewer() {
	    return this.tableViewer;
	}
}

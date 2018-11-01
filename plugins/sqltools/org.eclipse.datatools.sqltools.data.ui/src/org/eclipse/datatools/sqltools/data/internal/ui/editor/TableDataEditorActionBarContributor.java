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

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.sqltools.data.internal.core.editor.IRowData;
import org.eclipse.datatools.sqltools.data.internal.core.editor.ITableData2;
import org.eclipse.datatools.sqltools.data.internal.ui.DataUIPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.EditorActionBarContributor;

public class TableDataEditorActionBarContributor extends EditorActionBarContributor {        
 
    protected EditorAction insertAction;
    protected CursorAction updateAction;
    protected CursorAction deleteAction;
    protected CursorAction setNullAction;
//    protected CursorAction setDefaultAction;
    protected EditorAction revertAction;
    protected EditorAction refreshAction;
    protected EditorAction saveAction;
    
    public TableDataEditorActionBarContributor() { 
        DataUIPlugin.getDefault().setTableDataEditorContributor(this);
        
        insertAction = new EditorAction(Messages.getString("TableDataEditorActionBarContributor.InsertRow")) { //$NON-NLS-1$
            public void run() { editor.doInsertRow(); }
            public boolean isEnabled() { return !isEditorReadonly(); }
        };
        updateAction = new CursorAction(Messages.getString("TableDataEditorActionBarContributor.UpdateRow")) { //$NON-NLS-1$
            public void run() { editor.doUpdateValue(); }
            public boolean isEnabled() { return !isEditorReadonly(); }
        };
        updateAction.setAccelerator(SWT.F2); //$NON-NLS-1$
        deleteAction = new CursorAction(Messages.getString("TableDataEditorActionBarContributor.DeleteRow")) { //$NON-NLS-1$
            public void run() { editor.doDelete(); }
            public boolean isEnabled() { return !isEditorReadonly() && 
                editor!=null && editor.getCursor().getRow()!=null && 
                editor.getCursor().getRow().getData() instanceof IRowData; }
        };
        deleteAction.setActionDefinitionId("org.eclipse.ui.edit.delete"); //$NON-NLS-1$
        setNullAction = new CursorAction(Messages.getString("TableDataEditorActionBarContributor.SetNull")) { //$NON-NLS-1$
            public void run() { editor.doSetNull(); }
            public boolean isEnabled() { return !isEditorReadonly() && isColumnNullable(); }
        };
//        setDefaultAction = new CursorAction(Messages.getString("TableDataEditorActionBarContributor.setDefault")) { //$NON-NLS-1$
//            public void run() { editor.setDefault(); }
//            public boolean isEnabled() { return !isEditorReadonly() && editor.getColumnHasDefault(); }
//        };
        revertAction = new EditorAction(Messages.getString("TableDataEditorActionBarContributor.Revert")) { //$NON-NLS-1$
            public void run() { editor.doRevert(); }
            public boolean isEnabled() { return isEditorDirty(); }
        };
        revertAction.setActionDefinitionId("org.eclipse.ui.file.revert"); //$NON-NLS-1$
        refreshAction = new EditorAction(Messages.getString("TableDataEditorActionBarContributor.Refresh")) { //$NON-NLS-1$
            public void run() { editor.doRefresh(); }
        }; 
        refreshAction.setActionDefinitionId("org.eclipse.ui.file.refresh"); //$NON-NLS-1$
        saveAction = new EditorAction(Messages.getString("TableDataEditorActionBarContributor.Save")) { //$NON-NLS-1$
            public void run() { editor.doSave(new NullProgressMonitor()); }
            public boolean isEnabled() { return isEditorDirty(); }
        };
        saveAction.setActionDefinitionId("org.eclipse.ui.file.save"); //$NON-NLS-1$
    }
    
    public void setActiveEditor(IEditorPart targetEditor) {
        ITableDataEditor editor = null;
        if (targetEditor instanceof ITableDataEditor)
            editor = (ITableDataEditor)targetEditor;
        insertAction.setActiveEditor(editor);
        updateAction.setActiveEditor(editor);
        deleteAction.setActiveEditor(editor);
        setNullAction.setActiveEditor(editor);
//        setDefaultAction.setActiveEditor(editor);
        revertAction.setActiveEditor(editor);
        refreshAction.setActiveEditor(editor);
        saveAction.setActiveEditor(editor);      
    }
    
    public void contributeToMenu(IMenuManager menuManager) {
        IMenuManager editMenu = menuManager.findMenuUsingPath( IWorkbenchActionConstants.M_EDIT );
        if (editMenu != null) {
            editMenu.add( new Separator() );
            editMenu.add( insertAction );
            editMenu.add( updateAction );
            editMenu.add( setNullAction );
//            editMenu.add( setDefaultAction );
            editMenu.add( deleteAction );
        }
        
        IActionBars bars = getActionBars();
        bars.setGlobalActionHandler(ActionFactory.DELETE.getId(), deleteAction);
        bars.setGlobalActionHandler(ActionFactory.REVERT.getId(), revertAction);
        bars.setGlobalActionHandler(ActionFactory.REFRESH.getId(), refreshAction);
    }
    
    public void contributeToToolBar(IToolBarManager toolBarManager) {        
    }

    public void contributeToCoolBar(ICoolBarManager coolBarManager) {
        
    }
    
    public void contributeToStatusLine(IStatusLineManager statusLineManager) {
//        ContributionItem ci = new ContributionItem(){
//            public void fill(Composite parent) {
//                Label sep= new Label(parent, SWT.SEPARATOR);
//            }
//        };
//        ci.setParent(statusLineManager);
//        ci.setVisible(true);
//        statusLineManager.add(ci);
//        ci = new ContributionItem(){
//            public void fill(Composite parent) {
//                Label l = new Label(parent, SWT.NONE);
//                l.setText("test");
//                StatusLineLayoutData ld = new StatusLineLayoutData();
//                ld.widthHint = 100;                
//                l.setLayoutData(ld);
//            }
//        };
//        ci.setParent(statusLineManager);
//        ci.setVisible(true);
//        statusLineManager.add(ci);


    }
    

    public IAction getRevertAction() {
        return this.revertAction;
    }

    public IAction getRefreshAction() {
        return this.refreshAction;
    }

    public IAction getUpdateAction() {
        return this.updateAction;
    }

    public IAction getSetNullAction() {
        return this.setNullAction;
    }

    public IAction getInsertAction() {
        return this.insertAction;
    }

    public IAction getSaveAction() {
        return this.saveAction;
    }

    public IAction getDeleteAction() {
        return this.deleteAction;
    }
    
}

class EditorAction extends Action implements IPropertyListener
{
    protected ITableDataEditor editor = null;
    
    public EditorAction(String text) {
        super(text);
    }
    
    public void setActiveEditor(ITableDataEditor editor) {
        setActiveEditor1(editor);
        setEnabled(isEnabled());
    }
    
    public void setActiveEditor1(ITableDataEditor editor) {
        if (this.editor!=null)
            this.editor.removePropertyListener(this);
        this.editor = editor;
        if (editor!=null)
            editor.addPropertyListener(this);
    }

    public void propertyChanged(Object source, int propId) {
        if (propId==IEditorPart.PROP_DIRTY)
            setEnabled(isEnabled());        
    }
    
    public boolean isEnabled() {
        return true;
    }
    
    public boolean isEditorReadonly() {
        return editor==null || editor.isReadonly();
    }
    
    public boolean isEditorDirty() {
        if (editor==null)
            return false;
        else
            return editor.isDirty();
    }
}

abstract class CursorAction extends EditorAction implements ISelectionChangedListener {
    
    public CursorAction(String text) {
        super(text);
    }
    
    public void setActiveEditor1(ITableDataEditor editor) {
        if (editor!=null)
            editor.getSelectionProvider().removeSelectionChangedListener(this);
        super.setActiveEditor1(editor);
        if (this.editor!=null)
            this.editor.getSelectionProvider().addSelectionChangedListener(this);
    }

    public void selectionChanged(SelectionChangedEvent event) {
        setEnabled(isEnabled());
    }
    
    public boolean isColumnNullable()
    {
        if (editor==null)
            return false;
        int col = editor.getCursor().getColumn();
        if (editor.getTableData() instanceof ITableData2) {
            return ((Column)((ITableData2)editor.getTableData()).getResultColumns().get(col)).isNullable();
        }
        else {
            return ((Column)editor.getSqlTable().getColumns().get(col)).isNullable();
        }
    }
}
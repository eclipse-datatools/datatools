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

import org.eclipse.datatools.sqltools.data.internal.core.editor.IRowData;
import org.eclipse.datatools.sqltools.data.internal.core.editor.ITableData;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorPart;

/**
 * This interface should be implemented by editors that allows browsing and editing 
 * the data stored in a SQL table.
 */
public interface ITableDataEditor extends IEditorPart {
    public ITableData getTableData();
    
    public org.eclipse.datatools.modelbase.sql.tables.Table getSqlTable();
    
    public TableDataTableCursor getCursor();
    
    public boolean isReadonly();
    
    public IRowData getRow();
    
    public IRowData getOrCreateRow();
    
    public void setDirtyBackground(int columnIndex, TableItem item);
    
    public void setDirty(boolean value);
    
    public void doRevert();
    
    public void doDelete();
    
    public void doInsertRow();
    
    public void doUpdateValue();
    
    public void doSetNull();
    
    public void doRefresh();
    
    public TableViewer getTableViewer();
    
    public TableDataEditorSelectionProvider getSelectionProvider();

}

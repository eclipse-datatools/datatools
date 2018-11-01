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

import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.sqltools.data.internal.core.editor.IRowData;
import org.eclipse.datatools.sqltools.data.internal.core.editor.ITableData2;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.ui.IActionFilter;

/**
 * Represents a selected cell of the Table Data editor. 
 * This class is used to expose the table data editor selection, 
 * and to allow adding context actions using an object contribution.
 * 
 * The TableDataCell contains the row object and the column index.
 * If the cell corresponds to an actual table value, the row is an IRowTable data.
 * If the cell cell is part of the 'insertion row' then the row is just a dummy Object.
 * 
 * The actual cell value can be accessed using getValue().
 * The class also implements IActionFilter so it can be used for object contrinution enablement.
 * 
 * @author groux
 */
public class TableDataCell implements IActionFilter {

	protected ITableDataEditor editor;
    protected Object row;
    protected int col;
    
    public TableDataCell(ITableDataEditor editor, Object row, int col)
    {
    	this.editor = editor;
        this.row = row;
        this.col = col;
    }

    public int getCol() {
		return col;
	}

    public Object getRow() {
		return row;
	}
    
    public ITableDataEditor getEditor() {
		return editor;
	}

    /**
     * Returns the actual value of the cell (may be null) or null if the cell is part of the insertion row.
     * @return
     */
    public Object getValue() {
    	if (row instanceof IRowData)
    		return ((IRowData)row).getValue(col);
    	else
    		return null;
    }
    

    /**
     * Tests whether the specific value matches the state of the cell.
     * The following properties are supported:
     *  - vendor: Product name of the underlying database
     *  - version: Version name of the underlying database
     *  - dataType: Data type name of the column (precision/scale/length are not part of the name)
     *  - nullable: true if the column is nullable, false otherwise
     *  - insertionCell: true if the cell is part of the insertion row
     */
	public boolean testAttribute(Object target, String name, String value) {
		
		if (name.equals("vendor")) { //$NON-NLS-1$
			Database db = editor.getSqlTable().getSchema().getCatalog() != null ?
					editor.getSqlTable().getSchema().getCatalog().getDatabase():
					editor.getSqlTable().getSchema().getDatabase();
			String vendor = db.getVendor();
			return value.equals(vendor);
		} else if (name.equals("version")) { //$NON-NLS-1$
			Database db = editor.getSqlTable().getSchema().getCatalog() != null ?
					editor.getSqlTable().getSchema().getCatalog().getDatabase():
					editor.getSqlTable().getSchema().getDatabase();
			String version = db.getVersion();
			return value.equals(version);
		} else if (name.equals("dataType")) {//$NON-NLS-1$
			Column sqlCol = null;
			if (editor.getTableData() instanceof ITableData2) {
				sqlCol = (Column) ((ITableData2)editor.getTableData()).getResultColumns().get(col);
			}
			else {
				sqlCol = (Column) editor.getSqlTable().getColumns().get(col);
			}
			return value.equals(sqlCol.getDataType().getName());
		} else if (name.equals("nullable")) { //$NON-NLS-1$
			Column sqlCol = null;
			if (editor.getTableData() instanceof ITableData2) {
				sqlCol = (Column) ((ITableData2)editor.getTableData()).getResultColumns().get(col);
			}
			else {
				sqlCol = (Column) editor.getSqlTable().getColumns().get(col);
			}
			return new Boolean(value).booleanValue() == sqlCol.isNullable();
		} else if (name.equals("insertionCell")) { //$NON-NLS-1$	
			boolean b = row instanceof IRowData;
			return new Boolean(value).booleanValue()!=b;
		} else
			return false;
	}

}

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

package org.eclipse.datatools.sqltools.data.internal.core.editor;

import java.sql.SQLException;
import java.util.Vector;

import org.eclipse.datatools.sqltools.data.internal.core.common.IColumnDataAccessor;
import org.eclipse.datatools.sqltools.data.internal.core.common.Output;


public interface ITableData {    
    
    public boolean isReadonly();
    
    public int getColumnCount();
    public String getColumnName(int col);   
    public int getColumnType(int col);
    public String getColumnHeader(int col);
    public IColumnDataAccessor getColumnDataAccessor(int col);
    
    public Vector getRows();

    public IRowData insertRow();
    public void deleteRow(IRowData row);
    
    public int save(Output output) throws SQLException;
    public void revert();
    
    public void dispose();
}

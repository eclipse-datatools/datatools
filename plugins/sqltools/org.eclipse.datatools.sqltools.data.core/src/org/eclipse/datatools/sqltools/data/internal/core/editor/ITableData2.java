/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.data.internal.core.editor;

import java.sql.Connection;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.tables.Table;


public interface ITableData2 extends ITableData {
    public String getQualifiedTableName();

    public Connection getConnection();

    public Table getSQLTable();

    public String getQuotedColumnName(int col);

    public String getColumnTypeName(int col);

    public int[] getKeyColumns();
    
    public List getResultColumns();
}
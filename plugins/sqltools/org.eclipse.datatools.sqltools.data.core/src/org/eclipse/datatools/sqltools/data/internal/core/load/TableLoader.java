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

package org.eclipse.datatools.sqltools.data.internal.core.load;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.data.internal.core.DataCorePlugin;
import org.eclipse.datatools.sqltools.data.internal.core.common.data.DataDeserializer;
import org.eclipse.datatools.sqltools.data.internal.core.common.data.PreparedStatementWriter;


// Possible ways to improve performance:
// - use batch updates when available in the driver
// - use updatable resultset
public class TableLoader
{
    protected Table table;
    
    protected Connection con = null;
    protected String tableName = null;
    protected PreparedStatement insertStmt = null;
    
    protected int[] colTypes;
    protected String[] colNames;
    
    protected int deletedRows = 0;
    protected int insertedRows = 0;
    protected int failedRows = 0;
    protected HashSet errorColumns = new HashSet();
    
    protected String failedError;

    public TableLoader(Table table) 
    {
        this.table = table;
    }
    
    public void open() throws SQLException
    {
        con = ((ICatalogObject)table).getConnection();
        tableName = DataCorePlugin.getQualifiedTableName(table);
        
        String q = "insert into " + tableName + " values(?"; //$NON-NLS-1$ //$NON-NLS-2$
        for (int i=0; i<table.getColumns().size()-1; ++i)
            q += ",?"; //$NON-NLS-1$
        q += ")"; //$NON-NLS-1$
        
        insertStmt = con.prepareStatement(q);
        
        Statement stmt = con.createStatement();
        ResultSetMetaData md = stmt.executeQuery("select * from " + tableName).getMetaData(); //$NON-NLS-1$
        int cc = md.getColumnCount();
        colNames = new String[cc];
        colTypes = new int[cc];
        for (int i=0; i<colNames.length; ++i) {
            colNames[i] = md.getColumnName(i+1);
            colTypes[i] = md.getColumnType(i+1);
        }
        stmt.close();  
    }
    
    public void close() throws SQLException
    {
        if (insertStmt!=null)
            insertStmt.close();
    }
    
    public void emptyTable() throws SQLException
    {
        Statement st = null;
        try {
            st = con.createStatement();
            String q = "delete from " + tableName; //$NON-NLS-1$
            deletedRows = st.executeUpdate(q);
        } finally {
            if (st!=null)
                st.close();
        }
    }
    
    public void loadRow(String[] row) throws SQLException, DataFormatException, IOException
    {
        if (row.length!=table.getColumns().size())
            throw new DataFormatException("Unexpected number of values"); //$NON-NLS-1$
        
        
        try {
            for (int i=0; i<colTypes.length; ++i) {
	            Object o = DataDeserializer.deserialize(row[i], colTypes[i]);
	            setVal(o, insertStmt, i);
            }
            insertStmt.executeUpdate();
        	insertedRows++;
        } catch (Exception ex) {
    		failedRows++;
    		failedError = ex.toString();
        }
         
    }
    
    public void setVal(Object val, PreparedStatement pst, int column) throws SQLException, IOException
    {  
        try {
            PreparedStatementWriter.write(pst, column, colTypes[column], val);
        } catch (Exception ex) {
            errorColumns.add(colNames[column]);
	        pst.setNull(column+1, colTypes[column]);
	    }
    }
    
    public int getDeletedRows() {
        return deletedRows;
    }
    public int getFailedRows() {
        return failedRows;
    }
    public int getInsertedRows() {
        return insertedRows;
    }
    public HashSet getErrorColumns() {
        return errorColumns;
    }
    
    public String getFailedRowError(){
    	return failedError;
    }
}

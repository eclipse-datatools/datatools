/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.data.internal.core.editor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.util.RDBCorePluginConstants;
import org.eclipse.datatools.modelbase.sql.constraints.ReferenceConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.TableConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.data.internal.core.DataCorePlugin;
import org.eclipse.datatools.sqltools.data.internal.core.common.IColumnDataAccessor;
import org.eclipse.datatools.sqltools.data.internal.core.common.Output;
import org.eclipse.emf.common.util.EList;


/**
 * Implementation of the ISqlTableData based on JDBC 1.0 API (should work with any driver).
 * The table is queried through a 'select * from ...' statement and the data is stored in a bidimensional
 * array of objects.
 * Reading data is done by directly accessing the im-memory array.
 * Modifying the data is done by issuing insert/update/delete statements, using one of the possible unique
 * constraints of the table to uniquely identify the row.
 * If no unique constraints is available, all the columns will be used, which doesn't garentee that a single
 * column will be modified.
 * 
 * @author groux
 */
public class TableDataImpl implements ITableData {

    protected Table sqlTable;
    protected Connection con;  
    
    /** Vector<SqlRowImpl> Table data. */
    protected Vector rows = new Vector();  
    
    /** Column types as definies in java.sql.Types. */
    protected int[] colTtypes;
    
    /** Column names. */
    protected String[] colNames;
    
    /** Column Type names */
    protected String[] colTypeNames;
    
    /** Column data accessor. */
    IColumnDataAccessor[] colDataAccessor;
    
    /** Indices of the columns that will be used as part of the key for the insert/update/delete statements. 
     * Only if readonly == false; */
    protected int[] key = null;
    
    /** The table is readonly because it is not a base table (view). */
    protected boolean readonly;
    
    

    public TableDataImpl(Table sqlTable) throws SQLException, IOException, Exception
    {
        super();
        this.sqlTable = sqlTable;        
        con = ((ICatalogObject)sqlTable).getConnection();
        
        if (sqlTable instanceof BaseTable) {
            findKey((BaseTable) sqlTable);
            readonly = false;
        } else {
            readonly = true;
        }
        
        Statement stmt = con.createStatement();
        boolean setLimit = RDBCorePlugin.getDefault().getPluginPreferences().getBoolean(RDBCorePluginConstants.LIMIT_ROWS_RETRIEVED);
        if (setLimit) {
            int integer = RDBCorePlugin.getDefault().getPluginPreferences().getInt(RDBCorePluginConstants.MAX_ROW_RETRIEVED);
            stmt.setMaxRows(integer);
        } else {
            stmt.setMaxRows(0);
        }
        
        colDataAccessor = new IColumnDataAccessor[sqlTable.getColumns().size()];
        for (int i=0; i<sqlTable.getColumns().size(); ++i) {
            Column sqlCol = (Column) sqlTable.getColumns().get(i);
            colDataAccessor[i] = DataCorePlugin.getDefault().newColumnDataAccessor(sqlCol);
        }
        
        String selectStmt = computeSelectStatement();
        ResultSet rs = stmt.executeQuery(selectStmt);
        
        ResultSetMetaData rsmd = rs.getMetaData();
        int cc = rsmd.getColumnCount();
        colTtypes = new int[cc];
        colNames = new String[cc];  
        colTypeNames = new String[cc];
        for (int i=0; i<cc; ++i) {
            colTtypes[i] = rsmd.getColumnType(i+1);
            colNames[i] = rsmd.getColumnName(i+1); 
            colTypeNames[i] = rsmd.getColumnTypeName(i+1);
        }
        
        while (rs.next()) {
            Object[] a = new Object[cc];
            for (int col=0; col<cc; ++col)
                a[col] = colDataAccessor[col].read(rs, col, colTtypes[col], true);
            RowDataImpl row = new RowDataImpl(this, RowDataImpl.STATE_ORIGINAL, a);
            rows.add(row);
        }
        rs.close();
        stmt.close();
    }
    
    protected String computeSelectStatement()
    {
        StringBuffer sb = new StringBuffer("SELECT"); //$NON-NLS-1$
        for (int i=0; i<sqlTable.getColumns().size(); ++i) {
        	if (i==0)
                sb.append(" "); //$NON-NLS-1$
            else
                sb.append(", "); //$NON-NLS-1$
            sb.append( colDataAccessor[i].getSelectExpr() );
        }
        sb.append(" FROM "); //$NON-NLS-1$
        sb.append(getQualifiedTableName());
        return sb.toString();
    }
    
    protected void findKey(BaseTable baseTable)
    {
        EList constraints = baseTable.getConstraints();
        
        ReferenceConstraint chosenConstr = null;
        Iterator it = constraints.iterator();
        while (it.hasNext()) {
            TableConstraint constr = (TableConstraint)it.next();
            if (constr instanceof UniqueConstraint) {
                UniqueConstraint uniqueConstr = (UniqueConstraint)constr;
	            if (chosenConstr==null || uniqueConstr.getMembers().size()<chosenConstr.getMembers().size())
	                chosenConstr = uniqueConstr;
            }
        }
            
        if (chosenConstr==null) {
            EList cols = sqlTable.getColumns();
            key = new int[cols.size()];
            for (int i=0; i<cols.size(); ++i) {
                key[i] = i;
            } 
        } else {
	        EList cols = chosenConstr.getMembers();
	        key = new int[cols.size()];
	        for (int i=0; i<cols.size(); ++i) {
	            Column col = (Column)cols.get(i);
	            key[i] = col.getTable().getColumns().indexOf(col);
	        }          
        }
    }
    
    public void dispose() {
    }
    
    public int getColumnCount() {
        return sqlTable.getColumns().size();
    }
    
    public String getColumnHeader(int col) {
        Column sqlCol = (Column) sqlTable.getColumns().get(col);
        return sqlCol.getName() + " [" + getFormattedTypeName(sqlCol) + "]";  //$NON-NLS-1$//$NON-NLS-2$
    }
    
    public static String getFormattedTypeName(Column sqlCol)
    {
        Table table = sqlCol.getTable();
        Schema schema = table.getSchema();
        Database db = schema.getCatalog() != null ?
        	schema.getCatalog().getDatabase():
        	schema.getDatabase();        
        DatabaseDefinition dbDef = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(db);
        DataType dt = sqlCol.getDataType();
        if (dt != null) {
            if (dt instanceof PredefinedDataType)
                return dbDef .getPredefinedDataTypeFormattedName((PredefinedDataType) dt);
            else if (dt instanceof UserDefinedType)
                return DataCorePlugin.getQualifiedUDTName((UserDefinedType)dt);
            else
                return dt.getName();
        } else {
            return ""; //$NON-NLS-1$
        }
    }
    
    public String getColumnName(int col)
    {
        Column sqlCol = (Column) sqlTable.getColumns().get(col);
        return sqlCol.getName();
    }
    
    public String getQuotedColumnName(int col)
    {
    	Database db = sqlTable.getSchema().getDatabase() != null?
    					sqlTable.getSchema().getDatabase():sqlTable.getSchema().getCatalog().getDatabase();
    	
        return DataCorePlugin.quoteIdentifier(db, getColumnName(col));
    }
    
    public int getColumnType(int col)
    {
        return colTtypes[col];
    }
    
    /**
     * Gets the name of the column data type as returned by the database
     * @return the name of the column data type
     */
    public String getColumnTypeName(int col)
    {
    	return colTypeNames[col];
    }
    
    public IColumnDataAccessor getColumnDataAccessor(int col)
    {
    	return colDataAccessor[col];
    }
    
    public Vector getRows()
    {
        Vector v = new Vector();
        Iterator it = rows.iterator();
        while (it.hasNext()) {
            RowDataImpl row = (RowDataImpl)it.next();
            if (row.getState()!=RowDataImpl.STATE_DELETED)
                v.add(row);
        }
        return v;
    }
    
    public int[] getKeyColumns() {
        return key;
    }
    
    public Connection getConnection() {
        return con;
    }
    
    public int save(Output output) throws SQLException
    {        
    	boolean setAutoCommitAllowed = true;
    	boolean autocomit = con.getAutoCommit();
    	try
        {
        	con.setAutoCommit(false);
        	con.commit();
        }
        catch(SQLException ex)
        {
        	// some databases do not allow setAutoCommit()
        	setAutoCommitAllowed = false;
        }
        
        int res;
        TableDataSaveStatus status = new TableDataSaveStatus();
        try {         
	        Iterator it = rows.iterator();
	        while (it.hasNext()) {
	            RowDataImpl row = (RowDataImpl)it.next();
	            row.save(status, output);
	        }
	        if (setAutoCommitAllowed)
	        {
	        	con.commit();
	        	con.setAutoCommit(autocomit);
	        }
	        res = (status.duplicateRow) ? Output.STATUS_WARNING : Output.STATUS_SUCCEEDED;
        } catch (Exception ex) {
            output.write( ex.toString() );
            if (setAutoCommitAllowed)
            {
            	con.rollback();
            	con.setAutoCommit(autocomit);
            }
            res = Output.STATUS_FAILED;
            status.reset();
        }
        
        if (res==Output.STATUS_SUCCEEDED || res==Output.STATUS_WARNING)
            resetRowsToOriginal();
        
        writeOutput(output, res, status);

        return res;
    }
    
    public void revert()
    {
        int i = 0;
        while (i<rows.size()) {
            RowDataImpl row = (RowDataImpl)rows.elementAt(i);
            if (row.getState()==RowDataImpl.STATE_UPDATED || row.getState()==RowDataImpl.STATE_DELETED) {
                row.revertToOriginal();
                ++i;
            } else if (row.getState()==RowDataImpl.STATE_INSERTED)
                rows.remove(i);
            else if (row.getState()==RowDataImpl.STATE_ORIGINAL)
                ++i;
        }
    }
    
    protected void resetRowsToOriginal()
    {
        int i = 0;
        while (i<rows.size()) {
            RowDataImpl row = (RowDataImpl)rows.elementAt(i);
            if (row.getState()==RowDataImpl.STATE_UPDATED || row.getState()==RowDataImpl.STATE_INSERTED) {
                row.resetToOriginal();
                ++i;
            } else if (row.getState()==RowDataImpl.STATE_DELETED)
                rows.remove(i);
            else if (row.getState()==RowDataImpl.STATE_ORIGINAL)
                ++i;
        }  
    }
    
    protected void writeOutput(Output output, int res, TableDataSaveStatus status)
    {
        String endl = System.getProperty("line.separator"); //$NON-NLS-1$
        
        if (res==Output.STATUS_SUCCEEDED || res==Output.STATUS_WARNING)
            output.write( Messages.getString("TableDataImpl.DataSuccessfullySaved") ); //$NON-NLS-1$
        else
            output.write( Messages.getString("TableDataImpl.ErrorSavingData") ); //$NON-NLS-1$
        
        if (status.duplicateRow)
            output.write( Messages.getString("TableDataImpl.DuplicateRows") ); //$NON-NLS-1$
        
        String msg = ""; //$NON-NLS-1$
        msg += Messages.getString("TableDataImpl.Inserted") + String.valueOf(status.inserted) + Messages.getString("TableDataImpl.rows") + endl; //$NON-NLS-1$ //$NON-NLS-2$
        msg += Messages.getString("TableDataImpl.Updated") + String.valueOf(status.updated) + Messages.getString("TableDataImpl.rows") + endl; //$NON-NLS-1$ //$NON-NLS-2$
        msg += Messages.getString("TableDataImpl.Deleted") + String.valueOf(status.deleted) + Messages.getString("TableDataImpl.rows"); //$NON-NLS-1$ //$NON-NLS-2$
        output.write( msg );
    }

    public void deleteRow(IRowData row)
    {
        if (((RowDataImpl)row).getState()==RowDataImpl.STATE_INSERTED)
            rows.remove(row);
        else
            ((RowDataImpl)row).setState(RowDataImpl.STATE_DELETED);
    }
    
    public IRowData insertRow() {
        Object data[] = new Object[getColumnCount()];
        IRowData row = new RowDataImpl(this, RowDataImpl.STATE_INSERTED, data);
        rows.add(row);
        return row;
    }
    
    public boolean isReadonly() {
        return readonly;
    }
    
    public String getQualifiedTableName()
    {
        //return DataCorePlugin.getQualifiedTableName(sqlTable);
    	StringBuffer sb = new StringBuffer(50);
        org.eclipse.datatools.modelbase.sql.schema.Database db =
            sqlTable.getSchema().getCatalog() != null ?
                                    sqlTable.getSchema().getCatalog().getDatabase():
                                    sqlTable.getSchema().getDatabase();

        RDBCorePlugin plugin = RDBCorePlugin.getDefault();

        DatabaseDefinition dbDefinition = plugin.getDatabaseDefinitionRegistry().getDefinition(db);

        if (dbDefinition.supportsSchema()) 
          sb.append(DataCorePlugin.quoteIdentifier(con,sqlTable.getSchema().getName())).append(".");

        sb.append(DataCorePlugin.quoteIdentifier(con, sqlTable.getName()));    		 
        return sb.toString();    		 
    }
    
    /**
     * Gets the SQL Model table
     * @return the SQL Model table
     */
    public Table getSQLTable()
    {
    	return sqlTable;
    }
}

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

package org.eclipse.datatools.sqltools.data.internal.core.editor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.modelbase.sql.constraints.ReferenceConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.TableConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.helper.ISQLObjectNameHelper;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;
import org.eclipse.datatools.sqltools.data.internal.core.DataCorePlugin;
import org.eclipse.datatools.sqltools.data.internal.core.common.IColumnDataAccessor;
import org.eclipse.datatools.sqltools.data.internal.core.common.Output;
import org.eclipse.datatools.sqltools.result.ResultsViewAPI;
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
public class TableDataImpl implements ITableData2 {

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
    
    /** The actual columns in the result */
    protected List resultColumns;
    
    private static final String EXTERNAL_SQL_OBJECT_NAME_HELPER = "org.eclipse.datatools.modelbase.sql.sqlObjectNameHelper"; //$NON-NLS-1$
    private static final String EXTERNAL_SQL_OBJECT_NAME_HELPER_DBTYPE = "databaseType"; //$NON-NLS-1$
    private static final String EXTERNAL_SQL_OBJECT_NAME_HELPER_CLASS = "class"; //$NON-NLS-1$
    
    

    public TableDataImpl(Table sqlTable) throws SQLException, IOException, Exception
    {
        super();
        this.sqlTable = sqlTable;        
        con = ((ICatalogObject)sqlTable).getConnection();
        
        if (sqlTable instanceof BaseTable) {
            findKey((BaseTable) sqlTable);
            readonly = false;
        }
        // If the target table is a view table, determine whether it can be edited.
        else if (sqlTable instanceof ViewTable)
        {	
            readonly = true;
            try
            {
                if (sqlTable.isUpdatable())
                {
                    findViewKey((ViewTable) sqlTable);
                    readonly = false;
                }
            }
            catch(UnsupportedOperationException uoe)
            {
                readonly = true;
            }
        } 
        else {
            readonly = true;
        }
        resultColumns = new ArrayList();
        
        // If external filter exists then construct differently
        TableEditorFilterRegistryReader filterRegistryReader = TableEditorFilterRegistryReader.getInstance();
        if (filterRegistryReader.isExtenionFound() && !filterRegistryReader.isFilterCanceled()
        		&& filterRegistryReader.isMatchingVendor(sqlTable))
        {
        	constructFilteredTableData();
        }
        else
        { // do it the original way        
	        Statement stmt = con.createStatement();
	        int maxRowsPrefence = ResultsViewAPI.getInstance().getMaxRowPreference();
	        if (maxRowsPrefence >= 0)
	        {
	        	stmt.setMaxRows(maxRowsPrefence);
	        }
	        
	        colDataAccessor = new IColumnDataAccessor[sqlTable.getColumns().size()];
	        for (int i=0; i<sqlTable.getColumns().size(); ++i) {
	            Column sqlCol = (Column) sqlTable.getColumns().get(i);
	            resultColumns.add(sqlCol);
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
    }
    
    /**
     * Finds a "view key" for the given view table. The view key is all the columns of the table.
     * The view key is stored in the key var as an array of column indexes.
     * 
     * @param viewTable the view table for which the key is needed.
     */
    protected void findViewKey(ViewTable viewTable)
    {
        EList cols = viewTable.getColumns();
        key = new int[cols.size()];
        for (int i=0; i<cols.size(); ++i)
        {
            key[i] = i;
        } 
    }

    /**
     * Constructs TableDataImpl when user opt to filter the table results being returned     
     */
    protected void constructFilteredTableData() throws SQLException, IOException, Exception
    {
    	String selectStmt = null;
        ResultSet rs = null;
        Statement stmt = null;
        
        TableEditorFilterRegistryReader filterRegistryReader = TableEditorFilterRegistryReader.getInstance();
        ITableEditorResultFilter filterClass = filterRegistryReader.getTableEditorResultFilter();
    	if (filterClass.isReturningResultSet())
    	{
    		rs = filterClass.getResultSet();
    	}
    	else
    	{
    		selectStmt = filterClass.getSQLQueryString();
    		stmt = con.createStatement();
    		rs = stmt.executeQuery(selectStmt);
    	}
    	ResultSetMetaData rsmd = rs.getMetaData();
        colDataAccessor = new IColumnDataAccessor[rsmd.getColumnCount()];
        List filterdColumnNames = new ArrayList(rsmd.getColumnCount());
        for (int x=0;x<rsmd.getColumnCount();x++)
        {
        	filterdColumnNames.add(rsmd.getColumnName(x+1));
        }
        int index = 0;
        for (int i=0;i<sqlTable.getColumns().size();++i) 
        {
        	// check name against column name that is actually in the 
        	// filtered result before creating
            Column sqlCol = (Column) sqlTable.getColumns().get(i);            
            if (sqlCol != null && filterdColumnNames.contains(sqlCol.getName()))
            {
            	resultColumns.add(sqlCol);
            	colDataAccessor[index++] = DataCorePlugin.getDefault().newColumnDataAccessor(sqlCol);
            }            
        }
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
        if (stmt != null)
        {
        	stmt.close();
        }

    }
    
    protected String computeSelectStatement()
    {
    	Database database = null;
    	StringBuffer sb = new StringBuffer("SELECT"); //$NON-NLS-1$
        for (int i=0; i<sqlTable.getColumns().size(); ++i) {
        	if (i==0)
                sb.append(" "); //$NON-NLS-1$
            else
                sb.append(", "); //$NON-NLS-1$
            sb.append( colDataAccessor[i].getSelectExpr() );
        }
        sb.append(" FROM "); //$NON-NLS-1$
        // Get the qualified form of the table name from the name handler, if one
        // is registered.  Otherwise qualify it locally.
        String tableName = null;
        database = getDatabase(sqlTable.getSchema());
        String quote = "\""; //$NON-NLS-1$
        try {
        	quote = con.getMetaData().getIdentifierQuoteString();
        }
        catch (Exception ex)  {
            // ignore
        }        
        ISQLObjectNameHelper nameProvider = getSQLObjectNameHelper(database);
        if (nameProvider != null) {
            nameProvider.setIdentifierQuoteString(quote);
            tableName = nameProvider.getQualifiedNameInSQLFormat(sqlTable);
        }
        if (tableName == null) {
            tableName = getQualifiedTableName();
        }
        sb.append(tableName);        
        
        return sb.toString();
    }
    
    private Database getDatabase (Schema schema)
    {
        return schema.getCatalog() == 
        	null ? schema.getDatabase() : schema.getCatalog().getDatabase();
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
        //return sqlTable.getColumns().size();
    	return resultColumns.size();
    }
    
    public String getColumnHeader(int col) {
        //Column sqlCol = (Column) sqlTable.getColumns().get(col);
        Column sqlCol = (Column) resultColumns.get(col);
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
        //Column sqlCol = (Column) sqlTable.getColumns().get(col);
        Column sqlCol = (Column) resultColumns.get(col);
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
    
    /**
     * Gets a SQL Object name helper for the given database, if any.
     * 
     * @param database the current database
     * @return the name helper, or null if none found for the current database
     */
    private ISQLObjectNameHelper getSQLObjectNameHelper(Database database) {
        ISQLObjectNameHelper nameHelper = null;
        
        if (database != null) {
            /* Get the current database type. */
            String currentDBVendor = database.getVendor();
            
            /* Get an array of extenders of the SQL Object Name Handler extension point. */
            IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
            IExtensionPoint nameHandlerExtensionPoint = 
                extensionRegistry.getExtensionPoint(TableDataImpl.EXTERNAL_SQL_OBJECT_NAME_HELPER);
            IExtension [] nameHandlerExtensions = nameHandlerExtensionPoint.getExtensions();

            /* Scan the array to get an extender registered for the current database type, if any. 
             * Stop on the first one found. */
            int i = 0;
            while (i < nameHandlerExtensions.length && nameHelper == null) {
                IExtension ext = nameHandlerExtensions[i];
                IConfigurationElement [] configElements = ext.getConfigurationElements();
                int j = 0;
                while (j < configElements.length && nameHelper == null) {
                    String extVendor = configElements[j].getAttribute(TableDataImpl.EXTERNAL_SQL_OBJECT_NAME_HELPER_DBTYPE);
                    if (currentDBVendor.equalsIgnoreCase(extVendor)) {
                        try {
                            Object executableExtension = 
                                configElements[j].createExecutableExtension(TableDataImpl.EXTERNAL_SQL_OBJECT_NAME_HELPER_CLASS);
                            if (executableExtension instanceof ISQLObjectNameHelper) {
                                nameHelper = (ISQLObjectNameHelper) executableExtension;
                            }
                        }
                        catch(CoreException ex) {
                            // ignore error
                        }
                    }
                    j++;
                }
                i++;
            }
        }

        return nameHelper;
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
    
    public List getResultColumns() {
    	return resultColumns;
    }
}

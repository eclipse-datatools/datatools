/*******************************************************************************
 * Copyright (c) 2001, 2012 IBM Corporation and others.
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.sqltools.data.internal.core.common.Output;

import com.ibm.icu.util.StringTokenizer;

/**
 * This implementation of IRowData extends the AbstractRowData that handles the row life cycle,
 * and adds a save method that commits changes to the database though the use of DML statement.
 * @author groux
 */
public class RowDataImpl extends AbstractRowData
{
    protected ITableData2 table;
    private String GRAPHIC = "GRAPHIC";
    private String VARGRAPHIC = "VARGRAPHIC";
    
    public RowDataImpl(ITableData2 table, int state, Object[] data)
    {
        super(state, data);
        this.table = table;
    }
    
    public ITableData getTable()
    {
        return table;
    }
   
    public void save(TableDataSaveStatus status, Output output) throws SQLException, IOException
    {
        switch (state) {
	        case STATE_UPDATED:
	            doUpdate(status, output);
	            break;
	        case STATE_INSERTED:
	            doInsert(status, output);
	            break;
	        case STATE_DELETED:
	            doDelete(status, output);
	            break;
        	case STATE_ORIGINAL:
        	default:        	    
        }
    }
    
    protected void doInsert(TableDataSaveStatus status, Output output) throws SQLException, IOException
    {
        // Write query
        Vector exprs = computeValuesClause();
        String q = "insert into " + table.getQualifiedTableName(); //$NON-NLS-1$
        q += formatClause(" (", computeInsertColList(exprs), ", ", ")");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
        q += formatClause(" values(", exprs, ", ", ")");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$        
        PreparedStatement pst = table.getConnection().prepareStatement(q);
        StmtLog stmtLog = new StmtLog(q);
        
        // Set query arguments
        setValuesClauseArguments(pst, stmtLog);
        
        // Execute query
        output.write(stmtLog.toString());
        pst.executeUpdate();      
        pst.close();
                
        status.inserted += 1;
    }
    
    protected Vector computeInsertColList(Vector exprs)
    {    	
    	Vector cols = new Vector();
    	int index = -1;
    	for (int i=0; i<table.getColumnCount(); ++i) {
    		Column sqlCol = (Column) table.getResultColumns().get(i);
    		//Omit the always generated columns and columns where default value needs to be used.
    		if ( isColumnIncluded(sqlCol, i) ) {
    			index++;
    			if (exprs.elementAt(index)!=null) {    				
            		cols.add(table.getQuotedColumnName(i));            		
    			}
    		}                            
        }        
        return cols;
    }
    
    protected void doUpdate(TableDataSaveStatus status, Output output) throws SQLException, IOException
    {
        // Write query
        String q = "update " + table.getQualifiedTableName(); //$NON-NLS-1$
        q += formatClause(" set ", computeSetClause(), ", ", "");  //$NON-NLS-1$ //$NON-NLS-2$//$NON-NLS-3$
        q += formatClause(" where ", computeWhereClause(), " and ", "");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
        PreparedStatement pst = table.getConnection().prepareStatement(q);
        StmtLog stmtLog = new StmtLog(q);
        
        // Set query arguments
        setSetClauseArguments(pst, stmtLog);
        setWhereClauseArguments(pst, stmtLog);
        
        // Execute query
        output.write(stmtLog.toString());
        int n = pst.executeUpdate();      
        pst.close();
                
        status.updated += n;
        if (n!=1)
            status.duplicateRow = true;
    }
    
    protected void doDelete(TableDataSaveStatus status, Output output) throws SQLException, IOException
    {
        // Write query
        String q = "delete from " + table.getQualifiedTableName(); //$NON-NLS-1$
        q += formatClause(" where ", computeWhereClause(), " and ", "");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
        PreparedStatement pst = table.getConnection().prepareStatement(q);
        StmtLog stmtLog = new StmtLog(q);
        
        // Set query arguments
        setWhereClauseArguments(pst, stmtLog);
        
        // Execute query    
        output.write(stmtLog.toString());
        int n = pst.executeUpdate();      
        pst.close();
        
        status.deleted += n;
        if (n!=1)
            status.duplicateRow = true;
    }
    
    public void doRefresh(int col, boolean snippet) throws SQLException, IOException, Exception
    {
        if (state==STATE_INSERTED)
            return;
        
        // Write query        
        String q = "select " + table.getQuotedColumnName(col); //$NON-NLS-1$
        q += " from " + table.getQualifiedTableName(); //$NON-NLS-1$
        q += formatClause(" where ", computeWhereClause(), " and ", "");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
        PreparedStatement pst = table.getConnection().prepareStatement(q);
        StmtLog stmtLog = new StmtLog(q);
        
        // Set query arguments
        setWhereClauseArguments(pst, stmtLog);
        
        // Execute query  
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {            
            Object o = table.getColumnDataAccessor(col).read(rs, 0, table.getColumnType(col), snippet);
            if (newData[col]==oldData[col])
                newData[col] = o;
            oldData[col] = o;
                    
	        if (rs.next())
	            throw new Exception(stmtLog.toString() + Messages.getString("RowDataImpl.refreshTooManyRows")); //$NON-NLS-1$
        } else {
            throw new Exception(stmtLog.toString() + Messages.getString("RowDataImpl.RefreshNoRow")); //$NON-NLS-1$
        }
        
        rs.close();
        pst.close();
    }
    
    protected Vector computeValuesClause()
    {
        Vector exprs = new Vector();        
        for (int col=0; col<newData.length; ++col) {        	
        	Column sqlCol = (Column) table.getResultColumns().get(col);
        	//Omit the always generated columns and columns where default value needs to be used.
        	if ( isColumnIncluded(sqlCol, col) ) {
        		exprs.add( table.getColumnDataAccessor(col).getValuesExpr(newData[col]) );
        	}         	
        }
        return exprs;
    }

    protected void setValuesClauseArguments(PreparedStatement pst, StmtLog stmtLog) throws SQLException, IOException
    {       
    	for (int col=0; col<newData.length; ++col) {
		Column sqlCol = (Column) table.getResultColumns().get(col);
			//Omit the always generated columns and columns where default value needs to be used.
    		if ( isColumnIncluded(sqlCol, col) ) {
	            String[] args = table.getColumnDataAccessor(col).writeValuesExprArgs(pst, stmtLog.getArgsCount(),
	            		newData[col], table.getColumnType(col));
	            
	            // add G to GRAPHIC and VARGRAPHIC
	            if (args != null && (table.getColumnType(col) == java.sql.Types.CHAR ||
	            		table.getColumnType(col) == java.sql.Types.VARCHAR)) {            	
	            	for (int i=0;i<args.length;i++) {
	            		if (GRAPHIC.equalsIgnoreCase(table.getColumnTypeName(col)) ||
	            				VARGRAPHIC.equalsIgnoreCase(table.getColumnTypeName(col))) {
	            			args[i] = "G" + args[i];
	            		}            		
	            	}
	            } 	                        
	            stmtLog.addArgs(args);
    		}
        }
    }
    
    protected Vector computeSetClause()
    {
        Vector assignments = new Vector();
        for (int col=0; col<newData.length; ++col) {
            if (oldData[col]!=newData[col])
                    assignments.add( table.getColumnDataAccessor(col).getSetAss(newData[col]) );
        }
        return assignments;
    }
    
    protected void setSetClauseArguments(PreparedStatement pst, StmtLog stmtLog) throws SQLException, IOException
    {
        for (int col=0; col<newData.length; ++col)
            if (oldData[col]!=newData[col]) {
                String[] args = table.getColumnDataAccessor(col).writeSetAssArgs(pst, stmtLog.getArgsCount(),
                		newData[col], table.getColumnType(col));
                stmtLog.addArgs(args);
            }
    }
    
    protected Vector computeWhereClause()
    {
        Vector conds = new Vector();
        int[] keyColumns = table.getKeyColumns();
        for (int i=0; i<keyColumns.length; ++i)
            conds.add(table.getColumnDataAccessor(keyColumns[i]).getWhereCond(oldData[keyColumns[i]]));
        return conds;
    }
    
    protected void setWhereClauseArguments(PreparedStatement pst, StmtLog stmtLog) throws SQLException, IOException
    {
        int[] keyColumns = table.getKeyColumns();
        for (int i=0; i<keyColumns.length; ++i) {
            int col = keyColumns[i];
            String[] args = table.getColumnDataAccessor(col).writeWhereCondArgs(pst, stmtLog.getArgsCount(),
            		oldData[col], table.getColumnType(col));
            stmtLog.addArgs(args);
        }
    }
    
    protected String formatClause(String start, Vector elements, String sep, String end)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(start);
        
        boolean first = true;
        for (int i=0; i<elements.size(); ++i) {
            String s = (String)elements.elementAt(i);
            if (s!=null && !s.equals("")) {
                if (!first)
                    sb.append(sep);
                sb.append( s );
                first = false;
            }             
        }
        
        if (first)
            return ""; //$NON-NLS-1$
        
        sb.append(end);
        return sb.toString();
    }
    
    protected boolean isColumnIncluded(Column sqlCol, int colIndex)
    {    	
    	if ( sqlCol.getIdentitySpecifier() == null  //
    			&& sqlCol.getGenerateExpression() == null //
				&& !this.useDefaultValue(sqlCol, colIndex) )
    	{
    		return true;
    	}
    	
    	return false;
    }
    
    protected boolean useDefaultValue(Column sqlCol, int colIndex)
    {
    	if (this.getValue(colIndex) != null //user has given some value in UI, no default value here.
    			|| sqlCol.getDefaultValue() == null ||
    			sqlCol.getDefaultValue().trim().equalsIgnoreCase("NULL")) //there is no default value for column
    		return false;
    	if (sqlCol.getDefaultValue() != null) {
    		if (this.state == STATE_ORIGINAL)
    			return true ; //no UI mods
    		if (this.state == STATE_UPDATED)
    			return this.newData[colIndex] == null ? false : true;
    		if (this.state == STATE_INSERTED) //row to be inserted, but user has not given any values for column with default
    			return this.newData[colIndex] == null ? true : false;
    	}
    	return false ; //no default value and no value in UI either
    }
}


class StmtLog
{
    protected String query;
    protected Vector values = new Vector();
    
    public StmtLog(String query) throws SQLException
    {
        this.query = query;
    }
    
    public void addArgs(String[] args)
    {
        for (int i=0; i<args.length; ++i)
            values.add(args[i]);
    }
    
    public String toString()
    {
        StringTokenizer st = new StringTokenizer(query, "?", true); //$NON-NLS-1$
        StringBuffer sb = new StringBuffer(query.length());
        int curVal = 0;
        while (st.hasMoreTokens()) {
            String s = st.nextToken();
            if (s.equals("?")) { //$NON-NLS-1$
                if (values.size()>curVal)
                    sb.append(values.elementAt(curVal).toString());
                curVal++;                
            } else
                sb.append(s);
            
        }
        return sb.toString();
    }
    
    public int getArgsCount()
    {
        return values.size();
    }
    
}


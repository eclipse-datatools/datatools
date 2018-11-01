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
package org.eclipse.datatools.sqltools.data.internal.core.common;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.util.RDBCorePluginConstants;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.sqltools.data.internal.core.DataCorePlugin;
import org.eclipse.datatools.sqltools.data.internal.core.common.data.DataDeserializer;
import org.eclipse.datatools.sqltools.data.internal.core.common.data.DataSerializer;
import org.eclipse.datatools.sqltools.data.internal.core.common.data.PreparedStatementWriter;
import org.eclipse.datatools.sqltools.data.internal.core.common.data.ResultSetReader;
import org.eclipse.datatools.sqltools.data.internal.core.editor.TableDataImpl;

/**
 * This is the default implementation of the IColumnDataAccessor that is used when no
 * contributuion is made to the columnDataAcessor extension point.
 * 
 * This class relies on the JDBC driver to get data in and out from the database.
 * 
 * @author groux
 */
public class DefaultColumnDataAccessor implements IColumnDataAccessor
{

	protected Column sqlCol;
	
    protected int lobLimit;
	
	public DefaultColumnDataAccessor()
	{
		lobLimit = RDBCorePlugin.getDefault().getPluginPreferences().getInt(RDBCorePluginConstants.MAX_LOB_LENGTH);
	    if (lobLimit<=0)
	        lobLimit = -1;
	}
    
	public void initialize(Column sqlCol)
	{
		this.sqlCol = sqlCol;
	}
    
    public String getSelectExpr()
    {
        String colName = sqlCol.getName();        
        java.sql.Connection conn = ((ICatalogObject)sqlCol.getTable()).getConnection();        
        colName = DataCorePlugin.quoteIdentifier(conn, colName);        
        return colName;
    }
    
	public Object read(ResultSet rs, int col, int type, boolean snippet) throws SQLException, IOException
	{
        return ResultSetReader.read(rs, col, snippet?lobLimit:-1);
	}
	
    public boolean isSnippet(Object val, int type)
    {
        if (val==null)
            return false;
        
        if (type==Types.BLOB)
            return ((byte[])val).length==lobLimit;
        else if (type==Types.CLOB)
            return ((String)val).length()==lobLimit;
        else
            return false;
    }
	
	public String getValuesExpr(Object val)
	{
	    return "?"; //$NON-NLS-1$
	}
	
	public String[] writeValuesExprArgs(PreparedStatement pst, int start, Object val, int type)
		throws SQLException, IOException
	{
        PreparedStatementWriter.write(pst, start, type, val);
        return new String[] {argString(getLabel(val, type), type)};
	}
	
	public String getSetAss(Object val)
	{
	    return getQuotedColumnName() + "=?";  //$NON-NLS-1$
	}
	
	public String[] writeSetAssArgs(PreparedStatement pst, int start, Object val, int type)
		throws SQLException, IOException
	{
        PreparedStatementWriter.write(pst, start, type, val);
        return new String[] {argString(getLabel(val, type), type)};
	}
	
	public String getWhereCond(Object val)
	{
	    String s = getQuotedColumnName();
        if (val==null) {
            s += " is null"; //$NON-NLS-1$   	
	    } else {
            //s += "=CAST(? AS "; //$NON-NLS-1$
            //s += TableDataImpl.getFormattedTypeName(sqlCol) + ")"; //$NON-NLS-1$ //$NON-NLS-2$
	    	// removed CAST AS since some database may not support CAST
	    	s += "=?"; //$NON-NLS-1$	    	
        }
        return s;
	}
	
	public String[] writeWhereCondArgs(PreparedStatement pst, int start, Object val, int type)
		throws SQLException, IOException
	{
        if (val!=null) {
		    PreparedStatementWriter.write(pst, start, type, val);
	        return new String[] {argString(getLabel(val, type), type)};
        } else {
            return new String[] {};
        }
	}
	
	protected String getQuotedColumnName()
    {
		Database db = sqlCol.getTable().getSchema().getCatalog() != null ?
				sqlCol.getTable().getSchema().getCatalog().getDatabase():
				sqlCol.getTable().getSchema().getDatabase();
		return DataCorePlugin.quoteIdentifier(db, sqlCol.getName());
    }
	
	protected String argString(String arg, int type)
	{
	    String s = arg;
        if (s==null)
            return "null"; //$NON-NLS-1$
        if (s.length()>20)
            s = s.substring(0, 20) + "..."; //$NON-NLS-1$
        if (DataSerializer.needsQuote(type))
            s = "'" + s + "'";  //$NON-NLS-1$//$NON-NLS-2$
        return s;
	}


	public String getLabel(Object val, int type)
	{
		return serialize(val, type);
	}


	public String serialize(Object val, int type)
	{
        return DataSerializer.serialize(val, type);
	}


	public Object deserialize(String val, int type)
	{
		return DataDeserializer.deserialize(val, type);
	}
	
	public boolean supportsInlineEdit()
	{
		return true;
	}
	
    
}

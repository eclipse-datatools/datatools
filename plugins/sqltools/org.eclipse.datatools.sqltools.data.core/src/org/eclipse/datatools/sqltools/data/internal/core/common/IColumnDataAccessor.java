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

import org.eclipse.datatools.modelbase.sql.tables.Column;

/**
 * IColumnDataAccessor defines the interface to access data stored in a given table column.
 * This interface is currently used for the table data editor but could be extended to be used
 * with the Extract/Load feature.
 *
 * It provides methods to read from and write to the database through the JDBC driver.
 * It also provides methods to serialize or deserialize a value as a string.
 * These methodes are used to edit some data in the table editor, when supportsInlineEdit() is true.
 *
 * The value can be represented by an aribitrary Object. The object is usually the one returned
 * by the JDBC driver but it could be a custom object, to handle any specific need. 
 * 
 * @author groux
 */
public interface IColumnDataAccessor 
{
	
	/** Initialize the object after its creation. */
	public void initialize(Column sqlCol);
	
	/////////// Reading from the database
	
    /** Returns the SELECT expression corresponding to the column. */
    public String getSelectExpr();
    
	/** Reads a value from the result set and return in as an Object. */
	public Object read(ResultSet rs, int col, int type, boolean snippet) throws SQLException, IOException;
	
	/** Return true if the supplyied value is a snippet and not the full value as stored in the database */
	public boolean isSnippet(Object val, int type);
	
	/////////// Updating the database
	
	/** Returns the SQL expression (including optional '?'), to be used in the VALUES clause.
	 * Return null if the value should be ommited (eg: default/auto-generated column). */
	public String getValuesExpr(Object val);
	
	/** Writes the prepared statement arguments, corresponding to getValuesExpr(). */
	public String[] writeValuesExprArgs(PreparedStatement pst, int start, Object val, int type)
		throws SQLException, IOException;
	
	/** Returns the SQL assignment (including optional '?'), to be used in the SET clause.
	 * Return null if the column should not be set. */
	public String getSetAss(Object val);
	
	/** Writes the prepared statement arguments, corresponding to getSetAss(). */
	public String[] writeSetAssArgs(PreparedStatement pst, int start, Object val, int type)
		throws SQLException, IOException;
	
	/** Returns the SQL condition (including optional '?'), to be used in the WHERE clause.
	 * Return null if the column should not participate in the WHERE clause (eg: CLOB column) */
	public String getWhereCond(Object val);
	
	/** Writes the prepared statement arguments, corresponding to getWhereCond(). */
	public String[] writeWhereCondArgs(PreparedStatement pst, int start, Object val, int type)
		throws SQLException, IOException;
	
	/////////// Serialization

    /** Serializes an object as a string (for inline editing). */
	public String serialize(Object val, int type);
	
	/** Deserializes a string to an object (for inline editing). */
	public Object deserialize(String val, int type);
	
	/////////// Editing

    /** Returns a short text to represent the provided object. */
    public String getLabel(Object val, int type);
    
	/** Returns true if the provided value can be edited inline using the serialization methods. */
	public boolean supportsInlineEdit();

}

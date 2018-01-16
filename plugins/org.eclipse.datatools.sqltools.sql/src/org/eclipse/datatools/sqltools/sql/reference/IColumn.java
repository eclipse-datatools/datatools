/**
 * Created on Dec 15, 2004
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.sql.reference;


/**
 * @author Li Huang
 * 
 * The <code>Column</code> models a column of a table.
 */

public interface IColumn
{

    /**
     * Returns the column name.
     * @return
     */
    public String getName();
    /**
	 * Returns the default value or null if none.
	 * @return String
	 */
    public String getDefaultValue();

    /**
	 * Returns the width of the column.
	 * This depends on the data type.
	 * @return int
	 */
    public int getWidth();

    /**
	 * Return the scale of the column.
	 * The number of digits after the decimal point 
	 * for numeric data type columns, and zero for all other data types.
	 * This despends on the data type.
	 * @return int
	 */
    public int getScale();
    /**
	 * Returns the string represenation of the columns dataType.
	 * @return String
	 */	
    public String getTypeName();

    /**
	 * Returns true if this <code>IColumn</code> is part of a
	 * foreign key constraint.
	 * @return boolean
	 */
    public boolean isForeignKey();

    /**
	 * Returns true if this <code>IColumn</code> is nullable.
	 * @return boolean
	 */
    public boolean isNullable();

    /**
	 * Returns true if this <code>IColumn</code> is part of a
	 * primary key constraint.
	 * @return boolean
	 */
    public boolean isPrimaryKey();

    /**
	 * Returns true if this <code>IColumn</code> is part of a
	 * unique constraint.
	 * @return boolean
	 */
    public boolean isUnique();

    /**
	 * Returns the explanatory remarks set on the column or null if none
	 * @return String
	 */
    public String getRemarks();

    /**
	 * Return the user type.
	 * If the column is defined on a user-defined data type, 
	 * the data type is held here.
	 * @return String
	 */
    public String getUserType();

    /**
	 * Return the owner table name.
	 * @return String
	 */
    public String getOwnerTable();

    /**
     * Return the owner table.
     * @return table
     */
    public ITable getTable();

    /**
     * Set the owner table.
     * @param table
     */
    public void setTable(ITable table);
}

/**
 * Created on Mar 08, 2005
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.sql.reference;

/**
 * @author Li Huang
 *
 */
public interface ITable
{


	
	public String getOwner();
	
	public String getName();
    /**
	 * Returns the tableType.
	 * @return int
	 */
    public int getTableType();

    /**
	 * Test if this table has alias name.
     * @return  <tt>true</tt> if this table has alias name;
     *          <tt>false</tt> otherwise.
	 */
    public boolean hasAliasName();

    /**
	 * Return alias name of this table. 
	 * @return alias name
	 */
    public String getAliasName();

    /**
	 * Set alias name of this table;
	 * @param aliasName
	 */
    public void setAliasName(String aliasName);


}

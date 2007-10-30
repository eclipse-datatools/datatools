/*******************************************************************************
 * Copyright ©  2007 Sybase, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.model;


/**
 * This interface defines the set of properties which define a SQL
 * statement in a string.
 *
 * @author Jeremy Lindop
 */
public interface ISQLStatementInfo  {

    /**
     * Gets the SQL string for this ISQLStatementInfo.
     * 
     * @return  the SQL
     */
    public String getSQL();
      
    /**
     * Sets the sql string for this ISQLStatementInfo.
     * 
     */
    public void setSQL(String sql);
      
    /**
     * Gets the ISQLDialectInfo for this ISQLStatementInfo.
     * 
     * @return  the ISQLDialectInfo
     */
    public ISQLDialectInfo getSQLDialectInfo();
      
    /**
     * Sets the ISQLDialectInfo for this ISQLStatementInfo.
     * 
     */
    public void setSQLDialectInfo(ISQLDialectInfo sqlDialectInfo);
      

	/**
	 * Encodes the given <code>ISQLStatementInfo</code> object for persistence.
	 * @see org.eclipse.datatools.sqltools.sqlbuilder.model.SQLStatementInfo#decode(String)
	 * @return encoded String
	 */
	public String encode();

}

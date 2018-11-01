/*******************************************************************************
 * Copyright ©  2007 Sybase, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.model;


/**
 * This interface defines the set of properties which define a SQL
 * statement in a string. The DialectInfo is
 * optional within a SQLStatementInfo and should only be used if the dialect
 * in which the statement was created is different from that of the current
 * connection.
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
     * Gets the ISQLDialectInfo for this ISQLStatementInfo. The DialectInfo is
     * optional within a SQLStatementInfo and should only be used if the dialect
     * in which the statement was created is different from that of the current
     * connection.
     * 
     * @return  the ISQLDialectInfo
     */
    public ISQLDialectInfo getSQLDialectInfo();
      
    /**
     * Sets the ISQLDialectInfo for this ISQLStatementInfo. The DialectInfo is
     * optional within a SQLStatementInfo and should only be used if the dialect
     * in which the statement was created is different from that of the current
     * connection.
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

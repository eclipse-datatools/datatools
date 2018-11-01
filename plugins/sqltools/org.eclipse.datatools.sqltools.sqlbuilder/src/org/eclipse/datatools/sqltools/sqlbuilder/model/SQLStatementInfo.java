/*******************************************************************************
 * Copyright © 2000, 2007 Sybase, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.sqlbuilder.model;

import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;

/**
 * This class defines the set of properties which define a SQL
 * statement in a string.  The DialectInfo object is optional and should only
 * be used if the dialect in which the statement was created is
 * different from that of the current connection.
 * 
 * @author Jeremy Lindop
 */
public class SQLStatementInfo implements ISQLStatementInfo{

	protected String _sql = null;
	protected ISQLDialectInfo _sqlDialectInfo = null;

	/**
	 * Constructor for SQLStatementInfo
	 * 
	 */
	public SQLStatementInfo(){
		this(null, null);
	}
	
	/**
	 * Constructor for SQLStatementInfo
	 */
	public SQLStatementInfo(String sql){
		this(sql, null);
	}

	/**
	 * Constructor for SQLStatementInfo
	 */
	private SQLStatementInfo(String sql, ISQLDialectInfo sqlDialectInfo){
		_sql = sql;
		_sqlDialectInfo = sqlDialectInfo;
	}
	
	/**
	 * Implements {@link org.eclipse.datatools.sqltools.sqlbuilder.model.ISQLStatementInfo#getSQL()}
	 */
	public String getSQL() {
		return _sql;
	}

	/**
	 * Implements {@link org.eclipse.datatools.sqltools.sqlbuilder.model.ISQLStatementInfo#setSQL(String)}
	 */
    public void setSQL(String sql)
    {
    	_sql = sql;
    }
	
	/**
	 * Implements {@link org.eclipse.datatools.sqltools.sqlbuilder.model.ISQLStatementInfo#getSQLDialectInfo()}
	 */
	public ISQLDialectInfo getSQLDialectInfo() {
		return _sqlDialectInfo;
	}

	/**
	 * Implements {@link org.eclipse.datatools.sqltools.sqlbuilder.model.ISQLStatementInfo#setSQLDialectInfo(ISQLDialectInfo)}
	 */
    public void setSQLDialectInfo(ISQLDialectInfo sqlDialectInfo)
    {
    	_sqlDialectInfo = sqlDialectInfo;
    }

	/**
	 * Implements {@link org.eclipse.datatools.sqltools.sqlbuilder.model.ISQLStatementInfo#encode()}
	 * @see decode()
	 */
	public String encode() {
		StringBuffer code = new StringBuffer("");
		if (_sqlDialectInfo != null){
			code.append(_sqlDialectInfo.encode());
		}
		else {
			SQLDialectInfo tmpSQLDialectInfo = new SQLDialectInfo();
			code.append(tmpSQLDialectInfo.encode());
		}

		code.append(";");

		if (_sql != null){
			code.append(_sql);
		}
		
		return code.toString();
	}

	/**
	 * Decodes a <code>SQLStatementInfo</code> from an encoded String.
	 * @see encode()
	 * @param code encoded <code>SQLStatementInfo</code> object.
	 * @return <code>SQLStatementInfo</code> object
	 */
	public static SQLStatementInfo decode(String code)
	{
		SQLStatementInfo sqlStatementInfo = new SQLStatementInfo();
		

        if (code == null || !code.matches(".*;.*"))
		{
			SQLBuilderPlugin.getPlugin().getLogger().writeTrace( "Cannot decode SQLStatementInfo <" + code + ">");
		}
        else {
    		int i = 0;
    		int j = code.indexOf(';');
    		if (j > i){
    			String sDialectInfo = code.substring(i, j);
    			ISQLDialectInfo sqlDialectInfo = SQLDialectInfo.decode(sDialectInfo);
    			sqlStatementInfo._sqlDialectInfo = sqlDialectInfo;
    		}
    		   		
    		i = j + 1;
    		if (i < code.length()){
    			String sSQL = code.substring(i);
    			sqlStatementInfo._sql = sSQL;
    		}
        }
	    return sqlStatementInfo;
	}
	
}

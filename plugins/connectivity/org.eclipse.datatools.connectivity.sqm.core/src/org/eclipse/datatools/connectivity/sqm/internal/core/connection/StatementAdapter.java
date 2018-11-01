/*******************************************************************************
 * Copyright (c) 2001, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.internal.core.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;

public class StatementAdapter implements Statement {
	private ConnectionAdapter conection;
	private Statement statement;

	public StatementAdapter(ConnectionAdapter connection, Statement statement) {
		this.conection = connection;
		this.statement = statement;
	}
	public void addBatch(String arg0) throws SQLException {
		statement.addBatch(arg0);
	}
	public void cancel() throws SQLException {
		statement.cancel();
	}
	public void clearBatch() throws SQLException {
		statement.clearBatch();
	}
	public void clearWarnings() throws SQLException {
		statement.clearWarnings();
	}
	public void close() throws SQLException {
		statement.close();
	}
	public boolean equals(Object arg0) {
		return statement.equals(arg0);
	}
	public boolean execute(String arg0) throws SQLException {
		return statement.execute(arg0);
	}
	public boolean execute(String arg0, int arg1) throws SQLException {
		return statement.execute(arg0, arg1);
	}
	public boolean execute(String arg0, int[] arg1) throws SQLException {
		return statement.execute(arg0, arg1);
	}
	public boolean execute(String arg0, String[] arg1) throws SQLException {
		return statement.execute(arg0, arg1);
	}
	public int[] executeBatch() throws SQLException {
		return statement.executeBatch();
	}
	public ResultSet executeQuery(String arg0) throws SQLException {
		try {
			ResultSet resultSet = statement.executeQuery(arg0); 
			return new ResultSetAdapter(this, resultSet);
		}
		catch(SQLException e) {
	        notifySQLExceptionHandler(e);
		    IStatus status = new Status(IStatus.ERROR, RDBCorePlugin.getSymbolicName(), IStatus.ERROR,
		            e.getClass().getName(),
		            e);
			RDBCorePlugin.getDefault().getLog().log(status);
			ConnectionInfoImpl info =  (ConnectionInfoImpl) this.conection.getConnectionInfo();
			info.onSQLException(this.conection, e);
	        throw e;
		}
	}
	public int executeUpdate(String arg0) throws SQLException {
		return statement.executeUpdate(arg0);
	}
	public int executeUpdate(String arg0, int arg1) throws SQLException {
		return statement.executeUpdate(arg0, arg1);
	}
	public int executeUpdate(String arg0, int[] arg1) throws SQLException {
		return statement.executeUpdate(arg0, arg1);
	}
	public int executeUpdate(String arg0, String[] arg1) throws SQLException {
		return statement.executeUpdate(arg0, arg1);
	}
	public Connection getConnection() throws SQLException {
		return this.conection;
	}
	public int getFetchDirection() throws SQLException {
		return statement.getFetchDirection();
	}
	public int getFetchSize() throws SQLException {
		return statement.getFetchSize();
	}
	public ResultSet getGeneratedKeys() throws SQLException {
		return statement.getGeneratedKeys();
	}
	public int getMaxFieldSize() throws SQLException {
		return statement.getMaxFieldSize();
	}
	public int getMaxRows() throws SQLException {
		return statement.getMaxRows();
	}
	public boolean getMoreResults() throws SQLException {
		return statement.getMoreResults();
	}
	public boolean getMoreResults(int arg0) throws SQLException {
		return statement.getMoreResults(arg0);
	}
	public int getQueryTimeout() throws SQLException {
		return statement.getQueryTimeout();
	}
	public ResultSet getResultSet() throws SQLException {
		return statement.getResultSet();
	}
	public int getResultSetConcurrency() throws SQLException {
		return statement.getResultSetConcurrency();
	}
	public int getResultSetHoldability() throws SQLException {
		return statement.getResultSetHoldability();
	}
	public int getResultSetType() throws SQLException {
		return statement.getResultSetType();
	}
	public int getUpdateCount() throws SQLException {
		return statement.getUpdateCount();
	}
	public SQLWarning getWarnings() throws SQLException {
		return statement.getWarnings();
	}
	public int hashCode() {
		return statement.hashCode();
	}
	public void setCursorName(String arg0) throws SQLException {
		statement.setCursorName(arg0);
	}
	public void setEscapeProcessing(boolean arg0) throws SQLException {
		statement.setEscapeProcessing(arg0);
	}
	public void setFetchDirection(int arg0) throws SQLException {
		statement.setFetchDirection(arg0);
	}
	public void setFetchSize(int arg0) throws SQLException {
		statement.setFetchSize(arg0);
	}
	public void setMaxFieldSize(int arg0) throws SQLException {
		statement.setMaxFieldSize(arg0);
	}
	public void setMaxRows(int arg0) throws SQLException {
		statement.setMaxRows(arg0);
	}
	public void setQueryTimeout(int arg0) throws SQLException {
		statement.setQueryTimeout(arg0);
	}
	public String toString() {
		return statement.toString();
	}
	
	private void notifySQLExceptionHandler (SQLException sqlexception) {
	    ArrayList handlers = getSQLExceptionHandler();
	    for (int i = 0; i < handlers.size(); i++) {
	        SQLExceptionHandler handler = (SQLExceptionHandler) handlers.get(i);
	        handler.handleException(sqlexception);
	    }
	}
	    
	private ArrayList getSQLExceptionHandler(){
	    if (handers != null) 
	        return handers;
	    handers = new ArrayList();
	    IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
	    IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("org.eclipse.datatools.connectivity.sqm.core", "sqlexceptionHandler"); //$NON-NLS-1$ //$NON-NLS-2$
	    IExtension[] extensions = extensionPoint.getExtensions();
	    for(int i=0; i<extensions.length; ++i) {
	        IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
	        for(int j=0; j<configElements.length; ++j) {
	            if(configElements[j].getName().equals("handler")) { //$NON-NLS-1$
	                try {
	                    SQLExceptionHandler handler = (SQLExceptionHandler) configElements[j].createExecutableExtension("class"); //$NON-NLS-1$
	                    handers.add(handler);
	                } catch(CoreException e) {
	                    IStatus status = new Status(IStatus.ERROR, RDBCorePlugin.getSymbolicName(), IStatus.ERROR,
	                            "The error was detected when creating the exception handler ", e); //$NON-NLS-1$
	                    RDBCorePlugin.getDefault().getLog().log(status);
	                }
	            }            
	        }
	    }

	    return handers;
	}
	    
	private static ArrayList handers = null;

    public <T> T unwrap(Class<T> iface) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }
    
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }
    
    public boolean isClosed() throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }
    
    public boolean isCloseOnCompletion() throws SQLException
    {
    	return this.statement.isCloseOnCompletion();
    }
    
    public void closeOnCompletion() throws SQLException
    {
    	this.statement.closeOnCompletion();
    }
    
    public void setPoolable(boolean poolable) throws SQLException {
        // TODO Auto-generated method stub
    }
    
    public boolean isPoolable() throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }
}

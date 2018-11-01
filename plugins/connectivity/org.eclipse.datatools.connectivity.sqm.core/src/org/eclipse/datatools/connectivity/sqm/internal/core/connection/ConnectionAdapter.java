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

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;



public class ConnectionAdapter implements Connection {
	private ConnectionInfoImpl info;
	private Connection connection;
	
	public ConnectionAdapter(ConnectionInfo info, Connection connection) {
		this.info = (ConnectionInfoImpl) info;
		this.connection = connection;
	}

	public ConnectionInfo getConnectionInfo() {
		return this.info;
	}
	
	public Connection getNativeConnection() {
		return this.connection;
	}
	
	public Statement createStatement() throws SQLException {
	    try {
	        Statement s = connection.createStatement();		
	        return new StatementAdapter(this, s);
	    }
	    catch(SQLException e) {
		    IStatus status = new Status(IStatus.ERROR, RDBCorePlugin.getSymbolicName(), IStatus.ERROR,
		            e.getClass().getName(),
		            e);
			RDBCorePlugin.getDefault().getLog().log(status);
			this.info.onSQLException(this, e);
	        throw e;
	    }
	}

	public PreparedStatement prepareStatement(String arg0) throws SQLException {
		return connection.prepareStatement(arg0);
	}

	public CallableStatement prepareCall(String arg0) throws SQLException {
		return connection.prepareCall(arg0);
	}

	public String nativeSQL(String arg0) throws SQLException {
		return nativeSQL(arg0);
	}

	public void setAutoCommit(boolean arg0) throws SQLException {
		connection.setAutoCommit(arg0);
	}

	public boolean getAutoCommit() throws SQLException {
		return connection.getAutoCommit();
	}

	public void commit() throws SQLException {
		connection.commit();
	}

	public void rollback() throws SQLException {
		connection.rollback();
	}

	public void close() throws SQLException {
		connection.close();
	}

	public boolean isClosed() throws SQLException {
		return connection.isClosed();
	}

	public DatabaseMetaData getMetaData() throws SQLException {
		return connection.getMetaData();
	}

	public void setReadOnly(boolean arg0) throws SQLException {
		connection.setReadOnly(arg0);
	}

	public boolean isReadOnly() throws SQLException {
		return connection.isReadOnly();
	}

	public void setCatalog(String arg0) throws SQLException {
		connection.setCatalog(arg0);
	}

	public String getCatalog() throws SQLException {
		return connection.getCatalog();
	}

	public void setTransactionIsolation(int arg0) throws SQLException {
		connection.setTransactionIsolation(arg0);
	}

	public int getTransactionIsolation() throws SQLException {
		return connection.getTransactionIsolation();
	}

	public SQLWarning getWarnings() throws SQLException {
		return connection.getWarnings();
	}

	public void clearWarnings() throws SQLException {
		connection.clearWarnings();
	}

	public Statement createStatement(int arg0, int arg1) throws SQLException {
		return connection.createStatement(arg0, arg1);
	}

	public PreparedStatement prepareStatement(String arg0, int arg1, int arg2) throws SQLException {
		return connection.prepareStatement(arg0, arg1, arg2);
	}

	public CallableStatement prepareCall(String arg0, int arg1, int arg2) throws SQLException {
		return connection.prepareCall(arg0, arg1, arg2);
	}

	public Map getTypeMap() throws SQLException {
		return connection.getTypeMap();
	}

	public void setTypeMap(Map arg0) throws SQLException {
		connection.setTypeMap(arg0);
	}

	public void setHoldability(int arg0) throws SQLException {
		connection.setHoldability(arg0);
	}

	public int getHoldability() throws SQLException {
		return connection.getHoldability();
	}

	public Savepoint setSavepoint() throws SQLException {
		return connection.setSavepoint();
	}

	public Savepoint setSavepoint(String arg0) throws SQLException {
		return connection.setSavepoint(arg0);
	}

	public void rollback(Savepoint arg0) throws SQLException {
		connection.rollback(arg0);
	}

	public void releaseSavepoint(Savepoint arg0) throws SQLException {
		connection.releaseSavepoint(arg0);
	}

	public Statement createStatement(int arg0, int arg1, int arg2) throws SQLException {
		return connection.createStatement(arg0, arg1, arg2);
	}

	public PreparedStatement prepareStatement(String arg0, int arg1, int arg2, int arg3) throws SQLException {
		return connection.prepareStatement(arg0, arg1, arg2, arg3);
	}

	public CallableStatement prepareCall(String arg0, int arg1, int arg2, int arg3) throws SQLException {
		return prepareCall(arg0, arg1, arg2, arg3);
	}

	public PreparedStatement prepareStatement(String arg0, int arg1) throws SQLException {
		return prepareStatement(arg0, arg1);
	}

	public PreparedStatement prepareStatement(String arg0, int[] arg1) throws SQLException {
		return prepareStatement(arg0, arg1);
	}

	public PreparedStatement prepareStatement(String arg0, String[] arg1) throws SQLException {
		return prepareStatement(arg0, arg1);
	}

    public <T> T unwrap(Class<T> iface) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    public Clob createClob() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public Blob createBlob() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public NClob createNClob() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public SQLXML createSQLXML() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean isValid(int timeout) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        // TODO Auto-generated method stub
        
    }

    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        // TODO Auto-generated method stub
        
    }

    public String getClientInfo(String name) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public Properties getClientInfo() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }
    
    public int getNetworkTimeout() throws SQLException
    {
    	return this.connection.getNetworkTimeout();
    }
    
    public void setNetworkTimeout( final Executor executor, final int milliseconds ) throws SQLException
    {
    	this.connection.setNetworkTimeout( executor, milliseconds );
    }
    
    public String getSchema() throws SQLException
    {
    	return this.connection.getSchema();
    }

    public void setSchema( final String schema ) throws SQLException
    {
    	this.connection.setSchema( schema );
    }
    
    public void abort( final Executor executor ) throws SQLException
    {
    	this.connection.abort( executor );
    }
    
}

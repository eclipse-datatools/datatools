/***********************************************************************************************************************
 * Copyright (c) 2005 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.internal.core;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfoImpl;
import org.eclipse.datatools.sqltools.core.ConnectionException;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.IControlConnectionManager;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.dbitem.IDBItem;
import org.eclipse.datatools.sqltools.core.dbitem.IItemWithCode;
import org.eclipse.datatools.sqltools.core.profile.Messages;
import org.eclipse.datatools.sqltools.core.profile.NoSuchProfileException;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.editor.contentassist.model.IDatatype;


/**
 * This class is used as base class for ASAControlConnection and ASEControlConnection.
 * 
 * @author Yang Liu
 */
public abstract class AbstractControlConnection implements IControlConnection
{

	protected IConnection _connection = null;
    protected Set                     _skipConnections   = new HashSet();

    protected DatabaseIdentifier _databaseIdentifier;

    protected String                   _dbUserName         = null;

    /**
     * this map is used for internal proc info cache. key is ProcIdentifier.
     * the refresh() method will discard everything in the cache.
     * 
     * ProcIdentifier --> IDBItem
     */
    private Map			_procInfoCache = new HashMap();

    protected ControlConnectionManager _manager;
    
    /**
     * Constructs an AbstractControlConnection.
     * @param manager {@link EditorCorePlugin#getControlConnectionManager()}
     * @param databaseIdentifier contains connection profile info and database name
     */
    public AbstractControlConnection(IControlConnectionManager manager, DatabaseIdentifier databaseIdentifier)
    {
    	Assert.isTrue(manager instanceof ControlConnectionManager);
        this._manager = (ControlConnectionManager)manager;
        this._databaseIdentifier = databaseIdentifier;
    }

    public IControlConnectionManager getManager()
    {
        return _manager;
    }

    public DatabaseIdentifier getDatabaseIdentifier()
    {
        return _databaseIdentifier;
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     */
    public Object getAdapter(Class adapter)
    {
        // child class should override this method.
        return null;
    }

    /**
     * @param name The _profileName to set.
     */
    public void renameProfile(String name)
    {
        //call fire... to update controlconnection listeners, such as external client view
        IControlConnection controlConnection = (IControlConnection) _manager._controlConnectionMap.get(_databaseIdentifier);
        _manager._controlConnectionMap.remove(_databaseIdentifier);
        _databaseIdentifier.setProfileName(name);
        _manager._controlConnectionMap.put(_databaseIdentifier, controlConnection);
        _manager.fireRefreshed(this);
    }

    public String getDatabaseName()
    {
        return _databaseIdentifier.getDBname();
    }
    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.core.IControlConnection#getDbUsername()
     */
    public String getDbUsername() throws SQLException
    {
        if (this._dbUserName == null)
        {
            _dbUserName = this.getReusableConnection().getMetaData().getUserName();
        }
        return this._dbUserName;
    }

    /**
     * Checks if the Text of the procedural object is hidden
     * This is applicable for ASE only
     * @return true if text hidden, else false
     */
    public boolean isTextHidden(DatabaseIdentifier databaseIdentifier, String dbObjectName, int dbObjectType) 
    {
        return false;
    }


    public void fireChange()
    {
        _manager.fireRefreshed(this);
    }

    /**
     * Disposes the resources.
     * Child class can override this method, but normally should call super.dispose()
     */
    protected void dispose()
    {
    }

    /**
     * Whether it's ok to disconnect this control connection.
     * 
     */
    public boolean okToDisconnect()
    {
        return true;
    }

    public boolean disconnect()
    {
        return disconnect(false);
    }

    public boolean disconnect(boolean force)
    {
        dispose();

        _manager.remove(this);
        return true;
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.core.IControlConnection#saveStoredProcedure(com.sybase.stf.dmp.core.ProcIdentifier, java.lang.String)
     */
    public void createStoredProcedure(String src) throws SQLException
    {
        // we will try to use a new connection so can have transaction
        Connection con;
        con = getReusableConnection();
        Statement stmt = con.createStatement();
        try 
        {
            try
            {
                stmt.executeUpdate(src);
                refresh();
            }
            catch(SQLException ex)
            {
                // we failed to create the new stored procedure
                throw ex;    // throw the original exception out, so caller can get the error.
            }
        }
        finally
        {
            stmt.close();
        }
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.core.IControlConnection#getProcSource(com.sybase.stf.dmp.core.ProcIdentifier)
     */
    public String getProcSource(ProcIdentifier proc) throws SQLException
    {
        IDBItem item = getDBItem(proc);
        if (item instanceof IItemWithCode)
        {
            return ((IItemWithCode)item).getCode();
        }
        else
        {
            // should not happen
            throw new SQLException(Messages.getString("AbstractControlConnection.invalid.store.procedure.description")); //$NON-NLS-1$
        }
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.core.IControlConnection#saveStoredProcedure(com.sybase.stf.dmp.core.ProcIdentifier, java.lang.String)
     */
    public void saveStoredProcedure(ProcIdentifier proc, String code) throws SQLException
    {
        IDBItem item = getDBItem(proc);
        if (item instanceof IItemWithCode)
        {
            ((IItemWithCode)item).save(code);
            _manager.fireRefreshed(this, proc);
        }
        else
        {
            // should not happen
            throw new SQLException(Messages.getString("AbstractControlConnection.invalid.store.procedure.description")); //$NON-NLS-1$
        }
    }

    public IDBItem getDBItem(ProcIdentifier procIdentifier)
    {
        IDBItem item = (IDBItem)this._procInfoCache.get(procIdentifier);
        if (item == null)
        {
            item = createDBItem(procIdentifier);
            if (item != null) _procInfoCache.put(procIdentifier, item);
        }
        return item;
    }

    protected ProcIdentifier findSPUDF(String owner, String name)
    {
        for (Iterator iter = _procInfoCache.keySet().iterator(); iter.hasNext();)
        {
            ProcIdentifier proc = (ProcIdentifier) iter.next();
            if (proc.getType()==ProcIdentifier.TYPE_SP || proc.getType()==ProcIdentifier.TYPE_UDF)
            {
                if (proc.getOwnerName().equals(owner) && proc.getProcName().equals(name))
                return proc;
            }
        }
        return null;
    }

    protected Map getDBItemCache()
    {
        return _procInfoCache;
    }

    /**
     * Subclass should implement this method to create db item based on ProcIdentifier. 
     * @param proc
     */
    abstract protected IDBItem createDBItem(ProcIdentifier proc);


    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.core.IControlConnection#refresh()
     */
    public void refresh()
    {
        for (Iterator iter = _procInfoCache.values().iterator(); iter.hasNext();)
        {
            IDBItem item = (IDBItem) iter.next();
            item.dispose();
        }
        _procInfoCache.clear();
    }

    public void refresh(ProcIdentifier procIdentifier)
    {
        IDBItem item = (IDBItem) _procInfoCache.get(procIdentifier);
        if (item != null)
        {
            item.dispose();
        }
        _procInfoCache.remove(procIdentifier);
    }


    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.core.IControlConnection#convertToInternalConnId(java.lang.String, java.lang.String)
     */
    public String convertToInternalConnId(String externalId, String exteranlName) throws ConnectionException
    {
        return externalId;
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.core.IControlConnection#createConnection(int[])
     */
    public Connection createConnection(int[] connId) throws SQLException, CoreException, NoSuchProfileException
    {
        //using connection pool for debugging may produce unexpected results
    	IConnectionProfile profile = ProfileUtil.getProfile(getDatabaseIdentifier().getProfileName());
        Connection con = ProfileUtil.createConnection(profile, getDatabaseIdentifier().getDBname());
        if (connId != null && connId.length == 1)
        {
            connId[0] = 0;
        }
        return con;
    }

    
    public IConnection getIConnection() {
    	if (_connection == null)
    	{
        	IConnectionProfile profile;
			try {
				profile = ProfileUtil.getProfile(getDatabaseIdentifier().getProfileName());
				_connection = ProfileUtil.createIConnection(profile, "java.sql.Connection");
			} catch (NoSuchProfileException e) {
				EditorCorePlugin.getDefault().log(e);
			}
    	}
		return _connection;
	}
    
    

	public Connection getReusableConnection() {
		IConnection c = getIConnection();
        if (c != null)
        {
            Object rawConn = c.getRawConnection();
            if (rawConn instanceof Connection)
            {
                return (Connection) rawConn;
            }
            else if (rawConn instanceof ConnectionInfoImpl)
            {
            	return (Connection) ((ConnectionInfoImpl) rawConn).getSharedConnection();
            }
        }
		
		return null;
	}

	/*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.core.IControlConnection#registerSkip(int)
     */
    public void registerSkip(int connid)
    {
        Integer id = new Integer(connid);
        _skipConnections.add(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.core.IControlConnection#unregisterSkip(int)
     */
    public void unregisterSkip(int connid)
    {
        _skipConnections.remove(new Integer(connid));
    }

    protected boolean shouldSkip(int connid)
    {
        return _skipConnections.contains(new Integer(connid));
    }


    /**
     * get the Datatype object of a user-defined datatype
     * @param typeName, name of a user-defined datatype
     * @return
     */
    protected abstract IDatatype getUserDataType(String typeName) throws SQLException;    

    public boolean supportsDebugging()
    {
        return false;
    }

    public IDatatype getTypeByNameStr(String nameStr) throws Exception
    {
        return null;
    }
}

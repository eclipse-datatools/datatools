/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.core;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObjectListener;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfoImpl;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.DatabaseConnectionRegistry;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Event;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.IControlConnectionListener;
import org.eclipse.datatools.sqltools.core.IControlConnectionManager;
import org.eclipse.datatools.sqltools.core.IResourceDisposeListener;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.ServerIdentifier;
import org.eclipse.datatools.sqltools.core.profile.NoSuchProfileException;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;
import org.eclipse.osgi.util.NLS;


/**
 * @author Yang Liu
 */
public class ControlConnectionManager implements IControlConnectionManager, ICatalogObjectListener
{

    // XXX: org.eclipse.jface.util.ListenerList depends on jface, which is UI. But this class
    // do not depend on UI. In the future we may want to use another ListenerList.
    ListenerList _listeners            = new ListenerList();

    /**
     * databaseIdentifier --> IControlConnection mapping
     */ 
    public Map          _controlConnectionMap = new HashMap();

    Map                        _serverControlConnectionMap = new Hashtable();

    Map                        _skippedConnections         = new Hashtable();

    Map                        _resourceDisposeListeners   = new Hashtable();
    
    public ControlConnectionManager() {
	}
    
    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.core.IControlConnectionManager#getControlConnection(java.lang.String)
     */
    public IControlConnection getOrCreateControlConnection(DatabaseIdentifier databaseIdentifier) throws SQLException,
        NoSuchProfileException
    {
        if (databaseIdentifier == null)
        {
            throw new NoSuchProfileException(databaseIdentifier.toString());
        }

        IControlConnection con = null;
        con = (IControlConnection )_controlConnectionMap.get(databaseIdentifier);

        if (con == null)
        {
        	SQLDevToolsConfiguration conf = SQLToolsFacade.getConfiguration(databaseIdentifier, null);
            con = conf.getConnectionService().createControlConnection(databaseIdentifier);
            //TODO differentiate unknow server type and other exception
            if (con == null)
            {
                throw new SQLException(NLS.bind(Messages.ControlConnectionManager_unknownServerType, (new Object[]{databaseIdentifier.getProfileName()}))); 
            }
            _controlConnectionMap.put(databaseIdentifier, con);

            // Add server
            ServerIdentifier serverId = ProfileUtil.getServerIdentifier(databaseIdentifier);
            if(serverId == null)
            {
                // Should not happen
                return null;
            }

            // we don't support generic JDBC profile
            if(serverId.getHost().length() != 0 && serverId.getPort().length() != 0)
            {
                HashSet controlConnections = (HashSet) _serverControlConnectionMap.get(serverId);
                if (controlConnections != null)
                {
                    if (!controlConnections.contains(con))
                    {
                        controlConnections.add(con);
                    }
                }
                else
                {
                    HashSet newServerConnections = new HashSet();
                    newServerConnections.add(con);
                    _serverControlConnectionMap.put(serverId, newServerConnections);
                }
            }
            this.fireAdded(con);
        }
        fireRefreshed(con);
        return con;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.core.IControlConnectionManager#checkControlConnection(java.lang.String)
     */
    public IControlConnection[] getControlConnection(String profileName)
    {
        return getControlConnections(profileName);
    }
    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.core.IControlConnectionManager#getControlConnections()
     */
    public synchronized IControlConnection[] getControlConnections()
    {
        return (IControlConnection[]) _controlConnectionMap.values().toArray(
            new IControlConnection[_controlConnectionMap.size()]);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.core.IControlConnectionManager#hasControlConnection()
     */
    public boolean hasControlConnection()
    {
        return !_controlConnectionMap.isEmpty();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.core.IControlConnectionManager#addControlConnectionListener(com.sybase.stf.dmp.core.IControlConnectionListener)
     */
    public void addControlConnectionListener(IControlConnectionListener listener)
    {
        _listeners.add(listener);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.core.IControlConnectionManager#removeControlConnectionListener(com.sybase.stf.dmp.core.IControlConnectionListener)
     */
    public void removeControlConnectionListener(IControlConnectionListener listener)
    {
        _listeners.remove(listener);
    }

    /**
     * Notifies the <code>IControlConnectionListener</code>s that a new <code>IControlConnection</code> is added into this manager.
     * @param con newly added <code>IControlConnection</code>
     */
    public void fireAdded(IControlConnection con)
    {
        Object[] listeners = _listeners.getListeners();
        for (int i = 0; i < listeners.length; i++)
        {
            IControlConnectionListener l = (IControlConnectionListener) listeners[i];
            l.controlConnectionAdded(con);
        }
    }

    /**
     * Notifies the <code>IControlConnectionListener</code>s that a <code>IControlConnection</code> is removed from this manager.
     * @param con removed <code>IControlConnection</code>
     */
    public void fireDetached(IControlConnection con)
    {
        Object[] listeners = _listeners.getListeners();
        for (int i = 0; i < listeners.length; i++)
        {
            IControlConnectionListener l = (IControlConnectionListener) listeners[i];
            l.controlConnectionDetached(con);
        }
    }

    /**
     * Notifies the <code>IControlConnectionListener</code>s that the <code>IControlConnection</code> has refreshed.
     * @param con refreshed <code>IControlConnection</code>
     */
    public void fireRefreshed(IControlConnection con)
    {
        Object[] listeners = _listeners.getListeners();
        for (int i = 0; i < listeners.length; i++)
        {
            IControlConnectionListener l = (IControlConnectionListener) listeners[i];
            l.controlConnectionRefreshed(con);
        }
    }

    /**
     * Notifies the <code>IControlConnectionListener</code>s that a <code>ProcIdentifier</code> managed by the <code>IControlConnection</code> has refereshed.
     * @param con <code>IControlConnection</code> used by the <code>ProcIdentifier</code> object
     * @param proc <code>ProcIdentifier</code>
     */
    public void fireRefreshed(IControlConnection con, ProcIdentifier proc)
    {
        Object[] listeners = _listeners.getListeners();
        for (int i = 0; i < listeners.length; i++)
        {
            IControlConnectionListener l = (IControlConnectionListener) listeners[i];
            l.controlConnectionRefreshed(con, proc);
        }
    }
    

    /**
     * This should only be called from AbstractControlConnection.
     * @param connection
     */
    protected void remove(AbstractControlConnection connection)
    {
        _controlConnectionMap.remove(connection.getDatabaseIdentifier());
        cleanUpServerRelatedMap(connection);
        fireDetached(connection);
    }

    private synchronized void cleanUpServerRelatedMap(AbstractControlConnection connection)
    {
        // clean up
        Set servers = _serverControlConnectionMap.keySet();
        Iterator serverIter = servers.iterator();
        ArrayList shouldRemoveServers = new ArrayList();
        while(serverIter.hasNext())
        {
            ServerIdentifier serverIdentifier = (ServerIdentifier) serverIter.next();
            HashSet conns = (HashSet) _serverControlConnectionMap.get(serverIdentifier);
            if(conns.contains(connection) && conns.size() == 1)
            {
                shouldRemoveServers.add(serverIdentifier);
            }
            else
            {
                conns.remove(connection);
            }
        }

        Iterator iter = shouldRemoveServers.iterator();
        while(iter.hasNext())
        {
            ServerIdentifier serverIdentifier = (ServerIdentifier)iter.next();
            _serverControlConnectionMap.remove(serverIdentifier);
            _skippedConnections.remove(serverIdentifier);
        }
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.core.IControlConnectionManager#shutdown()
     */
    public void shutdown()
    {
        _serverControlConnectionMap.clear();
        _skippedConnections.clear();
        List list = new ArrayList(this._controlConnectionMap.values());
        for (Iterator iter = list.iterator(); iter.hasNext();)
        {
            IControlConnection con = (IControlConnection) iter.next();
            con.disconnect(true);
        }
    }

    public IControlConnection[] getControlConnections(String profileName)
    {
        List controlConnections = new ArrayList();

        //Get key set
        Set databaseIdentifierSet = _controlConnectionMap.keySet();
        Iterator iterator = databaseIdentifierSet.iterator();
        List databaseIdentifiers = new ArrayList();
        while(iterator.hasNext())
        {
            databaseIdentifiers.add(iterator.next());
        }

        if (databaseIdentifiers.size() < 1)
        {
            return new IControlConnection[0];
        }

        for (int i = 0; i < databaseIdentifiers.size(); i++)
        {
            DatabaseIdentifier databaseIdentifier = ((DatabaseIdentifier) databaseIdentifiers.get(i));
            if (databaseIdentifier.getProfileName().equals(profileName))
            {
                controlConnections.add(_controlConnectionMap.get(databaseIdentifier));
            }
        }
        return (IControlConnection[]) controlConnections.toArray(
            new IControlConnection[controlConnections.size()]);
    }

    public IControlConnection getControlConnection(DatabaseIdentifier databaseIdentifier)
    {
        IControlConnection controlConnection = (IControlConnection) _controlConnectionMap.get(databaseIdentifier);
        if (controlConnection == null)
        {
        	IControlConnection[] cc = getControlConnection(databaseIdentifier.getProfileName());
        	if (cc != null && cc.length > 0)
        	{
        		controlConnection = cc[0];
        	}
        }
		return controlConnection;
    }

    public HashSet getSkippedConnections(ServerIdentifier serverIdentifier)
    {
        if(serverIdentifier == null)
        {
            return null;
}
        return (HashSet)_skippedConnections.get(serverIdentifier);
    }
    public void registerSkippedConnection(ServerIdentifier serverIdentifier, int connid)
    {
        if(serverIdentifier == null)
        {
            return;
        }
        HashSet skippedConnections = (HashSet)_skippedConnections.get(serverIdentifier);
        if(skippedConnections == null)
        {
            skippedConnections = new HashSet();
            skippedConnections.add(new Integer(connid));
            _skippedConnections.put(serverIdentifier, skippedConnections);
        }
        else
        {
            skippedConnections.add(new Integer(connid));
        }
    }

    public void unregisterSkippedConnection(ServerIdentifier serverIdentifier, int connid)
    {
        if(serverIdentifier == null)
        {
            return;
        }
        HashSet skippedConnections = (HashSet)_skippedConnections.get(serverIdentifier);
        if(skippedConnections != null)
        {
            skippedConnections.remove(new Integer(connid));
        }
    }

    public HashSet getControlConnections(ServerIdentifier serverIdentifier)
    {
        return (HashSet)_serverControlConnectionMap.get(serverIdentifier);
    }

    public Map getServerConnectionMap()
    {
        return _serverControlConnectionMap;
    }

    public void addResourceDisposeListener(String profileName, IResourceDisposeListener resourceDisposeListener)
    {
        if(resourceDisposeListener == null || profileName == null || profileName.trim().length() == 0)
        {
            return;
        }
        if(_resourceDisposeListeners.get(profileName) == null)
        {
            _resourceDisposeListeners.put(profileName, resourceDisposeListener);
        }
    }

    public void removeResourceDisposeListener(String profileName)
    {
        if(profileName == null || profileName.trim().length() == 0)
        {
            return;
        }
        _resourceDisposeListeners.remove(profileName);
    }

    public void fireDispose(String profileName)
    {
        if(profileName == null || profileName.trim().length() == 0)
        {
            return;
        }
        IResourceDisposeListener listener = (IResourceDisposeListener)_resourceDisposeListeners.get(profileName);
        if(listener != null)
        {
            listener.dispose();
        }
    }

	public void notifyChanged(ICatalogObject dmElement, int eventType) {
		//TODO When we can handle other sql objects than the procedrual objects, remove this evaluation
		if (!(dmElement instanceof Routine || dmElement instanceof Trigger || dmElement instanceof Event))
		{
			return;
		}
		Database catalogDatabase = dmElement.getCatalogDatabase();
		ConnectionInfo connInfo = DatabaseConnectionRegistry.getInstance()
				.getConnectionForDatabase(catalogDatabase);
		if (connInfo instanceof ConnectionInfoImpl) {
			IConnectionProfile connectionProfile = ((ConnectionInfoImpl) connInfo)
					.getConnectionProfile();
			DatabaseIdentifier dbid = new DatabaseIdentifier(connectionProfile.getName(), catalogDatabase.getName());
			ServerIdentifier serverId = ProfileUtil.getServerIdentifier(dbid);
			HashSet cons = getControlConnections(serverId); 
			if (cons != null && cons.size() > 0)
			{
				ProcIdentifier procId = SQLDevToolsUtil.getProcIdentifier((SQLObject)dmElement);
				for (Iterator it = cons.iterator(); it.hasNext();) {
					IControlConnection con = (IControlConnection) it.next();
					if (con != null)
					{
						con.refresh(procId);
					}
				}
			}
		}
	}
}

/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.core;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.IControlConnectionListener;
import org.eclipse.datatools.sqltools.core.IControlConnectionManager;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.profile.NoSuchProfileException;
import org.eclipse.jface.util.ListenerList;
import org.eclipse.osgi.util.NLS;


/**
 * @author Yang Liu
 */
public class ControlConnectionManager implements IControlConnectionManager
{

    // XXX: org.eclipse.jface.util.ListenerList depends on jface, which is UI. But this class
    // do not depend on UI. In the future we may want to use another ListenerList.
    ListenerList _listeners            = new ListenerList();

    /**
     * databaseIdentifier --> IControlConnection mapping
     */ 
    public Map          _controlConnectionMap = new HashMap();

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
            con = SQLToolsFacade.getConfiguration(databaseIdentifier, null).getConnectionService().createControlConnection(databaseIdentifier);
            //TODO differentiate unknow server type and other exception
            if (con == null)
            {
                throw new SQLException(NLS.bind(Messages.ControlConnectionManager_unknownServerType, (new Object[]{databaseIdentifier.getProfileName()}))); 
            }
            _controlConnectionMap.put(databaseIdentifier, con);
            this.fireAdded(con);
        }
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
    public void remove(IControlConnection connection)
    {
        _controlConnectionMap.remove(connection.getDatabaseIdentifier());
        fireDetached(connection);
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.core.IControlConnectionManager#shutdown()
     */
    public void shutdown()
    {
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
            return null;
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
        return (IControlConnection) _controlConnectionMap.get(databaseIdentifier);
    }

}

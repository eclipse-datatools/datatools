/**
 * Created on 2006-5-17
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.sqltools.editor.core.connection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.datatools.connectivity.ConnectEvent;

/**
 * This class is to manage all connect event listeners in SQL Dev Tools. 
 * @author Idull
 */
public class SQLToolsConnectListenersManager
{
    private static SQLToolsConnectListenersManager _manager;
    private ListenerList _listeners;

    private SQLToolsConnectListenersManager()
    {
        _listeners = new ListenerList();
    }

    public synchronized static SQLToolsConnectListenersManager getInstance()
    {
        if(_manager == null)
        {
            _manager = new SQLToolsConnectListenersManager();
        }
        return _manager;
    }

    public void addConnectListener(ISQLToolsConnectListener listener)
    {
        if(listener == null)
        {
            return;
        }
        _listeners.add(listener);
    }

    public void removeConnectListener(ISQLToolsConnectListener listener)
    {
        if(listener == null)
        {
            return;
        }
        _listeners.remove(listener);
    }

    public boolean fireOkToClose(ConnectEvent event)
    {
        boolean okToClose = true;
        Object[] listeners = _listeners.getListeners();
        for (int i = 0; i < listeners.length; i++)
        {
            try
            {
                okToClose = ((ISQLToolsConnectListener) listeners[i]).okToClose(event);
            }
            catch (Exception e)
            {
            }
            if (!okToClose)
            {
                return okToClose;
            }
        }
        return okToClose;
    }

    public void fireAboutToClose(ConnectEvent event) throws CoreException
    {
        Object[] listeners = _listeners.getListeners();
        for (int i = 0; i < listeners.length; i++)
        {
            try
            {
                ((ISQLToolsConnectListener) listeners[i]).aboutToClose(event);
            }
            catch (Exception e)
            {
            }
        }
    }

    public void fireProfileConnected(ConnectEvent event) throws CoreException
    {
        Object[] listeners = _listeners.getListeners();
        for (int i = 0; i < listeners.length; i++)
        {
            try
            {
                ((ISQLToolsConnectListener) listeners[i]).profileConnected(event);
            }
            catch (Exception e)
            {
            }
        }
    }

    public void fireCloseConnection(ConnectEvent event) throws CoreException
    {
        Object[] listeners = _listeners.getListeners();
        for (int i = 0; i < listeners.length; i++)
        {
            try
            {
                ((ISQLToolsConnectListener) listeners[i]).closeConnection(event);
            }
            catch (Exception e)
            {
            }
        }
    }
}

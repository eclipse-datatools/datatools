/*******************************************************************************
 * Copyright (c) 2005 -- 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.sqltools.core.profile;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.datatools.connectivity.IConnectionProfile;

/**
 * This class is to manage all profile listeners in SQL Dev Tools. Since Connectivity layer defined <code>IProfileListener</code>,
 * <code>IProfileListener1</code>, to reduce the coupling and make the interface between SQL Dev Tools and connectivity layer
 * clear, we define our own profile listeners manager, thus we will have only one class which implements
 * <code>IProfileListener</code>, <code>IProfileListener1</code>, other classes which need to be notified when
 * profile events occur may need to implement <code>ISQLToolsProfileListener</code> instead.
 * 
 * @author Idull
 */
public class SQLToolsProfileListenersManager
{
    private static SQLToolsProfileListenersManager _manager;
    private ListenerList                      _listeners;

    private SQLToolsProfileListenersManager()
    {
        _listeners = new ListenerList();
    }

    public synchronized static SQLToolsProfileListenersManager getInstance()
    {
        if (_manager == null)
        {
            _manager = new SQLToolsProfileListenersManager();
        }
        return _manager;
    }

    public void addProfileListener(ISQLToolsProfileListener listener)
    {
        if(listener == null)
        {
            return;
        }
        _listeners.add(listener);
    }

    public void removeProfileListener(ISQLToolsProfileListener listener)
    {
        if(listener == null)
        {
            return;
        }
        _listeners.remove(listener);
    }

    public void fireProfileAdded(IConnectionProfile profile)
    {
        Object[] listeners = _listeners.getListeners();
        for (int i = 0; i < listeners.length; i++)
        {
            try
            {
                ((ISQLToolsProfileListener) listeners[i]).profileAdded(profile);
            }
            catch (Exception e)
            {
            }
        }
    }

    public void fireProfileDeleted(IConnectionProfile profile)
    {
        Object[] listeners = _listeners.getListeners();
        for (int i = 0; i < listeners.length; i++)
        {
            try
            {
                ((ISQLToolsProfileListener) listeners[i]).profileDeleted(profile);
            }
            catch (Exception e)
            {
            }
        }
    }

    public void fireProfileChanged(IConnectionProfile profile, String oldName, String oldDesc, Boolean oldAutoConnect,
            boolean onlyNameChanged, ConnectProfile oldProfile)
    {
        Object[] listeners = _listeners.getListeners();
        for (int i = 0; i < listeners.length; i++)
        {
            try
            {
                ((ISQLToolsProfileListener) listeners[i]).profileChanged(profile, oldName, oldDesc, oldAutoConnect,
                        onlyNameChanged, oldProfile);
            }
            catch (Exception e)
            {
            }
        }
    }
}

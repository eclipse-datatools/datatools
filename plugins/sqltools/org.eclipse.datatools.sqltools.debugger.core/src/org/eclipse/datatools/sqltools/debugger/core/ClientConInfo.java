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
package org.eclipse.datatools.sqltools.debugger.core;


import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.ServerIdentifier;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;


/**
 * This class represents information of a client connection.
 */
public class ClientConInfo
{
    private int                _connId;            // ASA connid or ASE spid
    private String             _user;
    private Object             _status = null;     // its type will depends on server type

    /**
     * The debug handler related with this client connection
     */
    private IDebugHandler      _debugHandler;

    private DatabaseIdentifier _databaseIdentifier;

    private ServerIdentifier   _serverIdentifier;

    public ClientConInfo(IDebugHandler debugHandler, DatabaseIdentifier databaseIdentifier, int connid, String user)
    {
        _debugHandler = debugHandler;
        _databaseIdentifier = databaseIdentifier;
        this._connId = connid;
        this._user = user;
        _serverIdentifier = ProfileUtil.getServerIdentifier(_databaseIdentifier);
    }

    public int getConnId()
    {
        return _connId;
    }

    public String getUser()
    {
        return _user;
    }

    /**
     * update client connection status.
     * 
     * @param object server type specific status object
     * @return true if status is changed from previous state.
     */
    public boolean updateStatus(String db, String user, Object status)
    {
        boolean changed = false;
        if(!equals(db, _databaseIdentifier.getDBname()))
        {
            changed = true;
            _databaseIdentifier.setDBname(db);
        }
        if (!equals(user, _user))
        {
            changed = true;
            _user = user;
        }
        if (_status == null)
        {
            _status = status;
            return changed || status != null;
        }
        else
        {
            if (!_status.equals(status))
            {
                _status = status;
                changed = true;
            }
            return changed;
        }
    }

    /**
     * @return
     */
    public Object getStatus()
    {
        return _status;
    }

    private boolean equals(String s1, String s2)
    {
        return s1 == null ? s2 == null : s1.equals(s2);
    }

    /**
     * help method.
     * @return
     */
    public boolean isAttached()
    {
        if(_debugHandler != null)
        {
            return _debugHandler.isAttached(getConnId());
        }
        return false;
    }

    public IDebugHandler getDebugHandler()
    {
        return _debugHandler;
    }

    public ServerIdentifier getServerIdentifier()
    {
        return _serverIdentifier;
    }

    public DatabaseIdentifier getDatabaseIdentifier()
    {
        return _databaseIdentifier;
    }

    public void setStatus(Object status)
    {
        _status = status;
    }
}

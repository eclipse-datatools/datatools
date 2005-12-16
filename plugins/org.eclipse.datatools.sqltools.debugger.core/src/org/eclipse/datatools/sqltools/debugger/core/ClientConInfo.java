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
package org.eclipse.datatools.sqltools.debugger.core;

/**
 * This class represents information of a client connection, which refers to those connections not created in data
 * tools.
 */
public class ClientConInfo
{
    private String             _connId;

    private String             _database;
    private String             _user;

    private Object             _status = null;          // its type will depends on server type

    private IDebuggerControlConnection _parentControlConnection;

    public ClientConInfo(IDebuggerControlConnection control, String connid, String db, String user)
    {
        this._parentControlConnection = control;
        this._connId = connid;
        this._database = db;
        this._user = user;
    }

    public IDebuggerControlConnection getParentControlConnection()
    {
        return _parentControlConnection;
    }

    public String getConnId()
    {
        return _connId;
    }

    /**
     * get the current database name this client con is for.
     * 
     * @return
     */
    public String getDatabaseName()
    {
        return _database;
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
        if (!equals(db, _database))
        {
            changed = true;
            _database = db;
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
        IDebuggerControlConnection parent = getParentControlConnection();
        if (parent != null )
        {
            return parent.isAttached(getConnId());
        }
        else return false;
    }
}

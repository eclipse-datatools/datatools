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

package org.eclipse.datatools.sqltools.core;


/**
 * Uniquely identifies a database.
 * 
 * Note: we treat it as differnet database if it is from a different profile connection.
 * 
 * @author Yang Liu
 * @author Hui Cao
 */
public class DatabaseIdentifier
{
    private String			 _profileName;
    private String           _dbname;

    /**
     * Constructs a <code>DatabaseIdentifier</code> object using the given connection profile name and database name.
     */
    public DatabaseIdentifier(String profileName, String dbname)
    {
        this._profileName = profileName;
        this._dbname = dbname;
        if (this._dbname == null)
        {
            this._dbname = ""; 
        }
    }

    /**
     * Constructs a <code>DatabaseIdentifier</code> object using the given connection profile name.
     */
    public DatabaseIdentifier(String profileName)
    {
        this(profileName, ""); //$NON-NLS-1$
    }

    public String getProfileName()
    {
        return _profileName;
    }

    public String getDBname()
    {
        return _dbname;
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof DatabaseIdentifier)
        {
            DatabaseIdentifier di = (DatabaseIdentifier) obj;
            return _profileName.equals(di._profileName) && this._dbname.equals(di._dbname);
        }
        else
        {
            return false;
        }
    }

    public void setProfileName(String profileName)
    {
        this._profileName = profileName;
    }

    public void setDBname(String dbName)
    {
    	this._dbname = dbName;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    public int hashCode()
    {
        return _profileName.hashCode() + _dbname.hashCode();	// FIXME: case sensitive?
    }
    
    public String toString()
    {
        return _profileName + ":" + _dbname;
    }
}

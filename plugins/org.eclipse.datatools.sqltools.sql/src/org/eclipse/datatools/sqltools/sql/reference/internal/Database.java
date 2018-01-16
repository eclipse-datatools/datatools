/**
 * Created on March 17, 2005
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.sql.reference.internal;

import org.eclipse.datatools.sqltools.sql.reference.DBObject;
import org.eclipse.datatools.sqltools.sql.reference.IDatabase;

/**
 * @author Li Huang
 *
 */
public class Database extends DBObject implements IDatabase
{

    /**
     * The database type. 
     */
    public static final String ASA = "ASA";
    public static final String ASE = "ASE";

    private String _databaseType = null;

    public Database(String name, String databaseType)
    {
        super(null, name, DATABASE);
        _databaseType = databaseType;

    }

    public Database(String owner, String name, String databaseType)
    {
        super(owner, name, DATABASE);
        _databaseType = databaseType;

    }


    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.ui.sqleditor.sql.IDatabase#getDatabaseType()
     */
    public String getDatabaseType()
    {
        return _databaseType;
    }


}

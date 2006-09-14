/**
 * Created on Dec 15, 2004
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.sql.reference.internal;

import org.eclipse.datatools.sqltools.sql.reference.DBObject;
import org.eclipse.datatools.sqltools.sql.reference.IDatabase;
import org.eclipse.datatools.sqltools.sql.reference.IProcedure;

/**
 * @author Li Huang
 *  
 */
public class Procedure extends DBObject implements IProcedure
{

    private Database _database;
    public Procedure(String owner, String name, int type)
    {
        super(owner, name, type);
    }

    public Procedure(String owner, String name, int type, Database database)
    {
        super(owner, name, type);
        _database = database;
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.ui.sqleditor.sql.IProcedure#getDatabase()
     */
    public IDatabase getDatabase()
    {
        return _database;
    }




}

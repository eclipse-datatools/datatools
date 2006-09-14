/**
 * Created on Mar 08, 2005
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.sql.reference.internal;

import org.eclipse.datatools.sqltools.sql.reference.DBObject;
import org.eclipse.datatools.sqltools.sql.reference.ITable;

/**
 * @author Li Huang
 *
 */
public class Table extends DBObject implements ITable
{

    private String _aliasName = null;
    private boolean _hasAliasName = false;

    public Table()
    {

    }
    public Table(String owner, String name, int type)
    {
        super(owner, name, type);
    }
    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.ui.sqleditor.sql.ITable#getTableType()
     */
    public int getTableType()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.ui.sqleditor.sql.ITable#hasAliasName()
     */
    public boolean hasAliasName()
    {
        return _hasAliasName;
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.ui.sqleditor.sql.ITable#getAliasName()
     */
    public String getAliasName()
    {
        return _aliasName;
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.ui.sqleditor.sql.ITable#setAliasName()
     */
    public void setAliasName(String aliasName)
    {
        if (aliasName != null)
        {
            this._hasAliasName = true;
        }
        this._aliasName = aliasName;
    }

}

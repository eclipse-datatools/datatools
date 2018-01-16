/**
 * Created on Dec 15, 2004
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.sql.reference.internal;

import org.eclipse.datatools.sqltools.sql.reference.DBObject;
import org.eclipse.datatools.sqltools.sql.reference.IColumn;
import org.eclipse.datatools.sqltools.sql.reference.ITable;

/**
 * @author Li Huang
 *  
 */
public class Column extends DBObject implements IColumn
{

    private int     _width;
    private int     _scale;
    private boolean _nullable;
    private boolean _primaryKey;
    private boolean _foreignKey;
    private boolean _unique;
    private int     _keysInitialized;
    private String  _defaultValue;
    private String  _typeName;
    private String  _remarks;
    private String  _userTypeName;
    private String  _ownerTable;
    private String  _name;
    private Table   _table;

    public Column(String name, String owner, int type)
    {
        super(name, owner, type);
    }

    public Column(String ownerTable, String name, String typeName, int width, int scale, boolean nulls, boolean pkey,
        String userTypeName, String remarks, String defaultValue)
    {
        super();
        this._ownerTable = ownerTable;
        this._name = name;
        this._typeName = typeName;
        this._width = width;
        this._scale = scale;
        this._nullable = nulls;
        this._primaryKey = pkey;
        this._userTypeName = userTypeName;
        this._remarks = remarks;
        this._defaultValue = defaultValue;
    }

    public String getName()
    {
        return _name;
    }

    public String getDefaultValue()
    {
        return _defaultValue;
    }

    public int getWidth()
    {
        return _width;
    }

    public int getScale()
    {
        return _scale;
    }

    public String getTypeName()
    {
        return _typeName;
    }

    public boolean isForeignKey()
    {
        return _foreignKey;
    }

    public boolean isNullable()
    {
        return _nullable;
    }

    public boolean isPrimaryKey()
    {
        return _primaryKey;
    }

    public boolean isUnique()
    {
        return _unique;
    }

    public String getRemarks()
    {
        return _remarks;
    }

    public String getUserType()
    {
        return _userTypeName;
    }

    public String getOwnerTable()
    {
        return _ownerTable;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.ui.sqleditor.sql.IColumn#getTable()
     */
    public ITable getTable()
    {
        return _table;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.ui.sqleditor.sql.IColumn#setTable(com.sybase.stf.dmp.ui.sqleditor.sql.internal.Table)
     */
    public void setTable(ITable table)
    {
        this._table = (Table)table;
    }

}

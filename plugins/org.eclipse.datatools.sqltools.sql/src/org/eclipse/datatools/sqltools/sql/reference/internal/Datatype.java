/*
 * Created on 2005-6-1 Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.sqltools.sql.reference.internal;

import org.eclipse.datatools.sqltools.sql.reference.DBObject;
import org.eclipse.datatools.sqltools.sql.reference.IDatatype;

/**
 * @author dyang
 *  
 */
public class Datatype extends DBObject implements IDatatype
{
    private boolean   _isUDT;
    private IDatatype _baseType;
    private Object    _defaultValue;
    private int       _length;
    private boolean   _allowNull;
    private int		  _precision;
    private int 	  _scale;

    /**
     * 
     * @param owner
     * @param name
     * @param isUDT
     * @param baseType
     */
    public Datatype(String owner, String name, boolean isUDT, IDatatype baseType)
    {
        super(owner, name, DBObject.DATATYPE);
        this._isUDT = isUDT;
        this._baseType = baseType;
        this._length = 0;
        this._precision = 0;
        this._scale = 0;
    }

    /**
     * 
     * @param owner
     * @param name
     * @param isUDT
     * @param baseType
     * @param length
     * @param precision
     * @param scale
     * @param allownull
     */
    public Datatype(String owner, String name, boolean isUDT, IDatatype baseType, int length, int precision, int scale, boolean allownull)
    {
        super(owner, name, DBObject.DATATYPE);
        this._isUDT = isUDT;
        this._baseType = baseType;
        this._length = length;
        this._precision = precision;
        this._scale = scale;
        this._allowNull = allownull;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.ui.sqleditor.sql.IDatatype#isUDT()
     */
    public boolean isUDT()
    {
        return this._isUDT;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.ui.sqleditor.sql.IDatatype#getBaseType()
     */
    public IDatatype getBaseType()
    {
        return this._baseType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.ui.sqleditor.sql.IDatatype#getDefaultValue()
     */
    public Object getDefaultValue()
    {
        return this._defaultValue;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.ui.sqleditor.sql.IDatatype#getLength()
     */
    public int getLength()
    {
        return this._length;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.ui.sqleditor.sql.IDatatype#allowNull()
     */
    public boolean allowNull()
    {
        return this._allowNull;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.ui.sqleditor.sql.IDatatype#setAllowNull(boolean)
     */
    public void setAllowNull(boolean allowNull)
    {
        this._allowNull = allowNull;

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.ui.sqleditor.sql.IDatatype#setBaseType(com.sybase.stf.dmp.ui.sqleditor.sql.IDatatype)
     */
    public void setBaseType(IDatatype baseType)
    {
        this._baseType = baseType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.ui.sqleditor.sql.IDatatype#setDefaultValue(java.lang.Object)
     */
    public void setDefaultValue(Object defaultValue)
    {
        this._defaultValue = defaultValue;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.ui.sqleditor.sql.IDatatype#setLength()
     */
    public void setLength(int length)
    {
        this._length = length;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.ui.sqleditor.sql.IDatatype#setUDT(boolean)
     */
    public void setUDT(boolean isUDT)
    {
        this._isUDT = isUDT;
    }


    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.ui.sqleditor.sql.IDatatype#getPrecision()
     */
    public int getPrecision()
    {
        return this._precision;
    }
    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.ui.sqleditor.sql.IDatatype#getScale()
     */
    public int getScale()
    {
        return this._scale;
    }
    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.ui.sqleditor.sql.IDatatype#setPrecision(int)
     */
    public void setPrecision(int precision)
    {
        this._precision = precision;
    }
    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.ui.sqleditor.sql.IDatatype#setScale(int)
     */
    public void setScale(int scale)
    {
        this._scale = scale;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        String name = _name;
        String oldName = name;
        if(this._isUDT)
        {
            name = ((DBObject)getBaseType()).getName();
            oldName = name;
        }
        if(_length != 0 && _scale == 0 && _precision == 0)
        {
            name = name+"("+_length+")";
        }
        else if(_precision != 0 && _scale != 0)
        {
            name = name+"("+_precision+","+_scale+")";
        }
        else if(_precision != 0)
        {
            name = name+"("+_precision+")";
        }
        //        if(com.sybase.stf.dmp.ui.objectviewer.Utils.Datatype.checkIfFormatValid(name))
        //        {
        //            return name;
        //        }
        return name;
        //        return oldName;
    }
}

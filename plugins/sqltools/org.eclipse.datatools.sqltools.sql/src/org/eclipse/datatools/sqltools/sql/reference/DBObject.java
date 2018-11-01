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

package org.eclipse.datatools.sqltools.sql.reference;

/**
 * Represents a database object used in content assist.
 * @author Li Huang
 *
 */
public class DBObject 
{
    public static final int COLUMN                  = 00;
    /** standard table type */
    public final static int TABLE                   = 10;
    /** view type, value */
    public final static int VIEW                    = 12;
    /** system table type */
    public final static int SYSTABLE                = 13;
    /** system view type */
    public final static int SYSVIEW                 = 14;

    /** local temporary table type */
    public final static int LOCAL_TEMPORARY_TABLE   = 15;
    /** global temporary table type */
    public final static int GLOBAL_TEMPORARY_TABLE  = 16;

    public static final int TABLE_ALIAS             = 17;

    public static final int DATABASE                = 30;

    public static final int STORED_PROCEDURE        = 41;
    public static final int FUNCTION                = 42;
    public static final int SYSTEM_STORED_PROCEDURE = 43;
    //ASA only
    public static final int SYSTEM_FUNCTION         = 44;

    public static final int TRIGGER = 50;

    public static final int EVENT = 60;

    public static final int DATATYPE = 70;

    protected String          _name;
    protected int             _type;
    protected String          _owner;
    protected String          _text;               // used to save source for logic objects (sp, trigger, udf, event)

    public DBObject(String owner, String name, int type)
    {
        _name = name;
        _owner = owner;
        _type = type;
    }

    public DBObject()
    {

    }

    public String getName() 
    {
        return _name;
    }

    public void setName(String name) 
    {
        this._name = name;
    }

    public int getType() 
    {
        return _type;
    }

    public void setType(int type) 
    {
        this._type = type;
    }

    public String getOwner() 
    {
        return _owner;
    }

    public void setOwner(String _owner) 
    {
        this._owner = _owner;
    }
}

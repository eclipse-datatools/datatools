/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    linsong - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ase.catalog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProcedure;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.ecore.EClass;

public class ASEUtil
{
	
	public static final String PERMISSION_DELETE_ACTION = "delete";
	public static final String PERMISSION_INSERT_ACTION = "insert";
	public static final String PERMISSION_REFERENCE_ACTION = "references";
	public static final String PERMISSION_SELECT_ACTION = "select";
	public static final String PERMISSION_UPDATE_ACTION = "update";
	public static final String PERMISSION_EXECUTE_ACTION = "execute";
	public static final String PERMISSION_OTHER_ACTION = "other";
	
    public static final String VERSION_15              = "15";
    public static final String VERSION_1253            = "12.5.3";
    public static final String VERSION_12503           = "12.5.0.3";

    static String              VALID_NAME_PRE15        = "";
    // used for 15.0 and up
    static String              VALID_NAME_15           = ",255";

    static String              ENABLE_ENCRYPTED_COL    = "enable encrypted columns";

    public static final List   SYSTEM_DEFINED_SEGMENTS = new ArrayList();
    static
    {
        SYSTEM_DEFINED_SEGMENTS.add("default");
        SYSTEM_DEFINED_SEGMENTS.add("logsegment");
        SYSTEM_DEFINED_SEGMENTS.add("system");
    }
    
    public static Object findSP(Collection list, String name, int number)
    {
        Object object = null;
        for (Iterator it = list.iterator(); it.hasNext();)
        {
            SQLObject sqlObject = (SQLObject) it.next();
            if (sqlObject instanceof SybaseASEProcedure)
            {
                if (sqlObject.getName().equals(name) && ((SybaseASEProcedure) sqlObject).getGroupNumber() == number)
                {
                    object = sqlObject;
                    break;
                }
            }
        }
        return object;
    }

    public static String getFullQuatifiedName(SQLObject obj)
    {
        StringBuffer sb = new StringBuffer(256);
        if (obj instanceof Table)
        {
        	Table table = (Table) obj;
            Schema schema = table.getSchema();
            Catalog cataLog = schema.getCatalog();

            sb.append(cataLog.getName()).append('.').append(schema.getName()).append('.').append(table.getName());
        }

        // TODO: deal with other database object types

        return sb.toString();
    }

    public static SQLObject getSQLObject(Collection sqlObjs, String objName)
    {
    	Iterator it = sqlObjs.iterator();
        while (it.hasNext())
        {
        	SQLObject obj = (SQLObject) it.next();
            if (obj.getName().equals(objName))
            {
                return obj;
            }
        }
        return null;
    }
    
    public static SQLObject getSQLObject(Collection sqlObjs, String objName, EClass clazz)
    {
    	Iterator it = sqlObjs.iterator();
        while (it.hasNext())
        {
        	SQLObject obj = (SQLObject) it.next();
            if (obj.getName().equals(objName) && clazz.equals(obj.eClass()))
            {
                return obj;
            }
        }
        return null;
    }
 
}

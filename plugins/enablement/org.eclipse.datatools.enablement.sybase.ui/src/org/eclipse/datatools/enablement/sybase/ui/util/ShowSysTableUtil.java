/*******************************************************************************
 * Copyright (c) 2004, 2005, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.sybase.ui.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.enablement.sybase.util.SystemTableParameters;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;

/**
 * @author Hao-yue
 * 
 */
public class ShowSysTableUtil
{
    public static String KEY_NAME        = "SHOW_SYS_TABLES_MAP";
    private static Map   showSysTableMap = null;

    public static String getKey(Schema schema)
    {
        StringBuffer sb = new StringBuffer("");
        Catalog clg = schema.getCatalog();
        IConnectionProfile connProfile = ModelUtil.getConnectionProfile(clg.getDatabase());
        sb.append(getKeyPrefix(connProfile.getName())).append("DATABASE_").append(clg.getName()).append("_").append(
                "SCHEMA_").append(schema.getName());
        return sb.toString();
    }

    public static String getKeyPrefix(String profileName)
    {
        return "PROFILE_" + profileName.hashCode() + "_";
    }

    private static void setShowSysTableMap(final Map map)
    {
    	SystemTableParameters.getInstance().setMap(map);
    }

    public static Map getShowSysTableMap()
    {
    	showSysTableMap = SystemTableParameters.getInstance().getMap();    	
    		
        return showSysTableMap;
    }

    public static boolean isShowSysTableOn(Schema schema)
    {
        Map map = getShowSysTableMap();
        Boolean b = (Boolean) map.get(getKey(schema));
        if (b == null)
        {
            return false;
        }
        return b.booleanValue();
    }

    public static void removeShowSysTableMapEntry(String profileName)
    {
        Map newMap = new HashMap();
        Map map = ShowSysTableUtil.getShowSysTableMap();
        Iterator i = map.keySet().iterator();
        while (i.hasNext())
        {
            String key = (String) i.next();
            if (!key.startsWith(ShowSysTableUtil.getKeyPrefix(profileName)))
            {
                newMap.put(key, map.get(key));
            }
        }
        setShowSysTableMap(newMap);
    }

    public static void changeShowSysTableMapEntry(String profileName, String oldName)
    {
        Map newMap = new HashMap();
        Map map = ShowSysTableUtil.getShowSysTableMap();
        Iterator i = map.keySet().iterator();
        while (i.hasNext())
        {
            String key = (String) i.next();
            String prefix = ShowSysTableUtil.getKeyPrefix(oldName);
            if (key.startsWith(prefix))
            {
                String postfix = key.substring(prefix.length());
                newMap.put(getKeyPrefix(profileName) + postfix, map.get(key));
            }
            else
            {
                newMap.put(key, map.get(key));
            }
        }
        setShowSysTableMap(newMap);
    }
}

package org.eclipse.datatools.enablement.sybase.asa.catalog;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

public class ASAUtil {
	public static final String PERMISSION_DELETE_ACTION = "delete";
	public static final String PERMISSION_INSERT_ACTION = "insert";
	public static final String PERMISSION_REFERENCE_ACTION = "reference";
	public static final String PERMISSION_SELECT_ACTION = "select";
	public static final String PERMISSION_UPDATE_ACTION = "update";
	public static final String PERMISSION_ALTER_ACTION = "alter";
    public static final String PERMISSION_EXECUTE_ACTION = "execute";
    
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
}

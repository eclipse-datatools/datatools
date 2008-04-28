package org.eclipse.datatools.enablement.ase;

import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.enablement.sybase.IGenericDdlConstants;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;
import org.eclipse.emf.ecore.EObject;

public class SybaseASESQLUtil implements IGenericDdlConstants, ISybaseASEDdlConstants
{
    static public Catalog getContainedCatalog(EObject obj)
    {
        EObject container = ContainmentServiceImpl.INSTANCE.getContainer(obj);
        while (!(container instanceof Catalog) && container != null)
        {
            obj = container;
            container = ContainmentServiceImpl.INSTANCE.getContainer(obj);
        }
        return (Catalog)container;
    }

    static public Schema getContainedSchema(EObject obj)
    {
    	if (obj instanceof Trigger)
    	{
    		return ((Trigger)obj).getSchema();
    	}
        EObject container = ContainmentServiceImpl.INSTANCE.getContainer(obj);
        while (!(container instanceof Schema) && container != null)
        {
            obj = container;
            container = ContainmentServiceImpl.INSTANCE.getContainer(obj);
        }
        return (Schema)container;
    }
    
    static public String getUseDbStatement(Catalog catalog, DatabaseIdentifier dbId)
    {
    	return new StringBuffer(64).append(USE).append(SPACE).append(SQLDevToolsUtil.quoteWhenNecessary(catalog.getName(),dbId)).toString();
    }

    /**
     * Returns the statement to impersonate another user. Used when system procedure is used for objects in another schema. e.g. sp_rename 
     * @param schema
     * @return
     */
    static public String getSetNewUserStatement(Schema schema)
    {
        if(schema.getName().trim().equals(DATABASE_OWNER))
        {//if user name is dbo, we only need setuser
            return EMPTY_STRING;
        }
        else
        {
            return new StringBuffer(64).append(SETUSER).append(SPACE).append(SINGLE_QUOTE).append(schema.getName())
                    .append(SINGLE_QUOTE).toString();
        }
    }
    
    /**
     * Restore user
     * @param schema TODO
     * @return
     */
    static public String getSetUserDBOStatement(Schema schema)
    {
        if(schema.getName().trim().equals(DATABASE_OWNER))
        {
            return EMPTY_STRING;
        }
        else
        {
            return SETUSER;
        }
    }
    
}

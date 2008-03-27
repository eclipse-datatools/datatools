package org.eclipse.datatools.enablement.ase.deltaddl;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.datatools.enablement.ase.SybaseASESQLUtil;
import org.eclipse.datatools.enablement.sybase.ddl.SybaseDdlScript;
import org.eclipse.datatools.enablement.sybase.deltaddl.SybaseDeltaDdlGeneration;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.emf.ecore.EObject;

public class SybaseASEDeltaDdlGeneration extends SybaseDeltaDdlGeneration
{
    
    /**
     * add 'use db' statement as the most first statement. 
     * Return true if 'use db' statement is added successfully, otherwise, return false
     */
    protected boolean polishScripts(Map changeMap, SybaseDdlScript script)
    {
        if (script.getStatements() == null || script.getStatements().length == 0)
        {
            return false; // no need to polish empty statement
        }
        boolean useDBAdded = false;
        Set keySet = changeMap.keySet();
        Iterator it = keySet.iterator();
        Catalog catalog = null;
        Schema schema = null;
        boolean genSetUser = generateSetUser();
        while(it.hasNext())
        {
            EObject key = (EObject)it.next();
//            Integer flag = (Integer)changeMap.get(key);
            if(catalog == null)
            {
                catalog = SybaseASESQLUtil.getContainedCatalog(key);
            }
            if (genSetUser && schema == null)
            {
                schema = SybaseASESQLUtil.getContainedSchema(key);
            }
        }
        
        if(catalog != null)
        {
            script.addUseDatabaseDropStatements(SybaseASESQLUtil.getUseDbStatement(catalog,(DatabaseIdentifier)getParameter()));
            useDBAdded = true;
        }
        if (schema != null)
        {
            String setNewUser = SybaseASESQLUtil.getSetNewUserStatement(schema);
            String setUserDbo = SybaseASESQLUtil.getSetUserDBOStatement(schema);
            if(!setNewUser.equals(""))
            {
                script.addUseDatabaseDropStatements(setNewUser);
                script.addCleanUpStatements(setUserDbo);
            }
        }
        return useDBAdded;
    }
}

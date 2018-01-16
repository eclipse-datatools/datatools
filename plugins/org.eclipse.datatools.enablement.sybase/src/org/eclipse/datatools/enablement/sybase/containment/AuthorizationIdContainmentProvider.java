package org.eclipse.datatools.enablement.sybase.containment;

import org.eclipse.datatools.connectivity.sqm.core.containment.AbstractContainmentProvider;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Logical containment provider for authorization identifier
 * 
 * @author Idull
 * 
 */
public class AuthorizationIdContainmentProvider extends AbstractContainmentProvider
{
    public String getGroupId(EObject obj)
    {
        return "authidgroup";
    }

    
    public EObject getContainer(EObject obj)
    {
        EObject container = super.getContainer(obj);
        if (container != null)
        {
            return container;
        }
        
        if (obj instanceof SybaseAuthorizationIdentifier)
        {
            return ((SybaseAuthorizationIdentifier)obj).getSqlContainer();
        }
        
        AuthorizationIdentifier authid = (AuthorizationIdentifier) obj;
        EList ownedSchemas = authid.getOwnedSchema();
        if (ownedSchemas != null && !ownedSchemas.isEmpty())
        {
            Schema schema = (Schema) ownedSchemas.get(0);
            return schema;
        }
        
        return null;
    }

}

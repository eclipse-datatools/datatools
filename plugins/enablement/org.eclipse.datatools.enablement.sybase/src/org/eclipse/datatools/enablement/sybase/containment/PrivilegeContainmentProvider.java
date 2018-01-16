package org.eclipse.datatools.enablement.sybase.containment;

import org.eclipse.datatools.connectivity.sqm.core.containment.AbstractContainmentProvider;
import org.eclipse.emf.ecore.EObject;

/**
 * Logical containment provider for privilege
 * 
 * @author Idull
 */
public class PrivilegeContainmentProvider extends AbstractContainmentProvider
{
    public String getGroupId(EObject obj)
    {
        return "privilegegroup";
    }
}

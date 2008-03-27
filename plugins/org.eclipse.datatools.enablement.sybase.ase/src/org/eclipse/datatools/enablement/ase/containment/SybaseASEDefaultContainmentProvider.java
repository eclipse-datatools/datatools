package org.eclipse.datatools.enablement.ase.containment;

import org.eclipse.datatools.connectivity.sqm.core.containment.AbstractContainmentProvider;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDefault;
import org.eclipse.emf.ecore.EObject;

/**
 * Sybase ASE Default containment provider
 * 
 * @author renj
 */
public class SybaseASEDefaultContainmentProvider extends AbstractContainmentProvider
{
    public EObject getContainer(EObject obj)
    {
        return ((SybaseASEDefault) obj).getSchema();
    }

    public String getGroupId(EObject obj)
    {
        return DBEventGroupID.ASEDEFAULT;
    }
}

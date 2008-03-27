package org.eclipse.datatools.enablement.ase.containment;

import org.eclipse.datatools.connectivity.sqm.core.containment.AbstractContainmentProvider;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule;
import org.eclipse.emf.ecore.EObject;

/**
 * Sybase ASE Rule containment provider
 * 
 * @author renj
 */
public class SybaseASERuleContainmentProvider extends AbstractContainmentProvider
{
    public EObject getContainer(EObject obj)
    {
        return ((SybaseASERule) obj).getSchema();
    }

    public String getGroupId(EObject obj)
    {
        return DBEventGroupID.ASERULE;
    }
}

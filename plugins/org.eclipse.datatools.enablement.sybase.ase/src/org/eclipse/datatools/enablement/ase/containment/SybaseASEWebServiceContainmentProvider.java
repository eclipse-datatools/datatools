package org.eclipse.datatools.enablement.ase.containment;

import java.util.Collection;

import org.eclipse.datatools.connectivity.sqm.core.containment.AbstractContainmentProvider;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase;
import org.eclipse.emf.ecore.EObject;

public class SybaseASEWebServiceContainmentProvider extends
	AbstractContainmentProvider {

	public Collection getContainedElements(EObject obj) {
		SybaseASEDatabase database = (SybaseASEDatabase) obj;
	    Collection children = super.getContainedElements(obj);
	    children.addAll(database.getWebServices());
	    return children;
	}

	public String getGroupId(EObject obj) {
		return DBEventGroupID.ASEWEBSERVICE;
	}
}

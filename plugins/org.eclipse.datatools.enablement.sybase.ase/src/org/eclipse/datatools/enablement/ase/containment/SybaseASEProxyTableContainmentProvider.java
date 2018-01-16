package org.eclipse.datatools.enablement.ase.containment;

import org.eclipse.datatools.connectivity.sqm.internal.core.containment.TableContainmentProvider;
import org.eclipse.emf.ecore.EObject;

public class SybaseASEProxyTableContainmentProvider extends
		TableContainmentProvider {

	public String getGroupId(EObject obj) {
		return DBEventGroupID.ASEPROXYTABLE;
	}
}

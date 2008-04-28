package org.eclipse.datatools.enablement.sybase.asa.containment;

import java.util.Collection;

import org.eclipse.datatools.connectivity.sqm.internal.core.containment.TableContainmentProvider;
import org.eclipse.emf.ecore.EObject;

public class SybaseASAProxyTableContainmentProvider extends
		TableContainmentProvider {

	public Collection getContainedElements(EObject obj) {
		return super.getContainedElements(obj);
	}

	public String getGroupId(EObject obj) {
		return DBEventGroupID.ASAPROXYTABLE;
	}
}

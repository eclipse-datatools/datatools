package org.eclipse.datatools.enablement.ase.containment;

import java.util.Collection;

import org.eclipse.datatools.connectivity.sqm.internal.core.containment.GroupID;
import org.eclipse.datatools.connectivity.sqm.internal.core.containment.UserDefinedTypeContainmentProvider;
import org.eclipse.emf.ecore.EObject;

public class SybaseASEUserDefinedTypeContainmentProvider extends
		UserDefinedTypeContainmentProvider {

	public Collection getContainedElements(EObject obj) {
		return super.getContainedElements(obj);
	}

	public String getGroupId(EObject obj) {
		return GroupID.USER_DEFINED_TYPE;
	}
}

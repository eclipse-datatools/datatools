package org.eclipse.datatools.enablement.ase.containment;

import java.util.Collection;

import org.eclipse.datatools.connectivity.sqm.internal.core.containment.GroupID;
import org.eclipse.datatools.connectivity.sqm.internal.core.containment.SchemaContainmentProvider;
import org.eclipse.emf.ecore.EObject;

public class SybaseASESchemaContainmentProvider extends
		SchemaContainmentProvider {

	public Collection getContainedElements(EObject obj) {
		return super.getContainedElements(obj);
	}

	public String getGroupId(EObject obj) {
		return GroupID.SCHEMA;
	}

}

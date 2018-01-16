package org.eclipse.datatools.enablement.sybase.asa.containment;

import java.util.Collection;

import org.eclipse.datatools.connectivity.sqm.internal.core.containment.DatabaseContainmentProvider;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASADatabase;
import org.eclipse.emf.ecore.EObject;

public class SybaseASADatabaseContainmentProvider extends
		DatabaseContainmentProvider {

	public Collection getContainedElements(EObject obj) {
		Collection children = super.getContainedElements(obj);
		SybaseASADatabase db = (SybaseASADatabase) obj;
		children.addAll(db.getEvents());
		return children;
	}

	public String getGroupId(EObject obj) {
		return DBEventGroupID.ASADB;
	}
}

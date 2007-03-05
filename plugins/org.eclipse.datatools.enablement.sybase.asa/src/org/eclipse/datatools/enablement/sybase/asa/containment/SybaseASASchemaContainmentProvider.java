package org.eclipse.datatools.enablement.sybase.asa.containment;

import java.util.Collection;

import org.eclipse.datatools.connectivity.sqm.internal.core.containment.GroupID;
import org.eclipse.datatools.connectivity.sqm.internal.core.containment.SchemaContainmentProvider;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.ecore.EObject;

public class SybaseASASchemaContainmentProvider extends
		SchemaContainmentProvider {

	public Collection getContainedElements(EObject obj) {
		Collection children = super.getContainedElements(obj);
		Schema schema = (Schema)obj;
		children.addAll(schema.getTables());
		children.addAll(schema.getRoutines());
		children.addAll(schema.getSequences());
		children.addAll(schema.getUserDefinedTypes());
		children.addAll(schema.getAssertions());
		children.addAll(schema.getCharSets());
		return children;
	}

	public String getGroupId(EObject obj) {
		return GroupID.SCHEMA;
	}

}

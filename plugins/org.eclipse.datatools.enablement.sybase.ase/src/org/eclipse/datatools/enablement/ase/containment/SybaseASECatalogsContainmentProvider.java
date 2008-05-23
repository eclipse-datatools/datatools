package org.eclipse.datatools.enablement.ase.containment;

import java.util.Collection;

import org.eclipse.datatools.connectivity.sqm.core.containment.AbstractContainmentProvider;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;


public class SybaseASECatalogsContainmentProvider extends
		AbstractContainmentProvider {

    public Collection getContainedElements(EObject obj) {
        Catalog catalog = (Catalog) obj;
        Collection children = super.getContainedElements(obj);
        children.addAll(catalog.getSchemas());
        return children;
    }

    public EObject getContainer(EObject obj) {
        return ((Catalog) obj).getDatabase();
    }

    public EStructuralFeature getContainmentFeature(EObject obj) {
        return SQLSchemaPackage.eINSTANCE.getDatabase_Catalogs();
    }

    public String getGroupId(EObject obj) {
        return DBEventGroupID.CATALOG;
    }
}

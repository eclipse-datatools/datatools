/**
 * Author: dpchou
 * Purpose: As demo code for EclipseWorld 2005, not liable for any consequences
 * as the result of using this code. 
 */
package org.eclipse.datatools.enablement.sybase.asa.containment;

import java.util.Collection;

import org.eclipse.datatools.connectivity.sqm.core.containment.AbstractContainmentProvider;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;


public class SybaseASAEventContainmentProvider  extends AbstractContainmentProvider {

	public Collection getContainedElements(EObject obj) {
		return super.getContainedElements(obj);
	}

	public boolean isDisplayableElement(EObject obj) {
		return super.isDisplayableElement(obj);
	}

	public EObject getContainer(EObject obj) {
	    Database database = ((SybaseASABaseEvent) obj).getDatabase();
        if (database != null)
            return (EObject) database.getCatalogs().get(0);
        else
            return null;	    
	}

	public EStructuralFeature getContainmentFeature(EObject obj) {
		return SQLSchemaPackage.eINSTANCE.getDatabase_Events();
	}

	public String getGroupId(EObject obj) {
		return DBEventGroupID.DBEVENT;
	}

}

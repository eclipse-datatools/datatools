package org.eclipse.datatools.enablement.sybase.asa.containment;

import java.util.Collection;

import org.eclipse.datatools.connectivity.sqm.core.containment.AbstractContainmentProvider;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASAWebServiceContainmentProvider extends
	AbstractContainmentProvider {

	public Collection getContainedElements(EObject obj) {
		return super.getContainedElements(obj);
	}

	public boolean isDisplayableElement(EObject obj) {
		return super.isDisplayableElement(obj);
	}

	public EObject getContainer(EObject obj) {
		return ((SybaseASAWebService) obj).getDatabase();
	}

	public EStructuralFeature getContainmentFeature(EObject obj) {
		return SybaseasabasesqlmodelPackage.eINSTANCE.getSybaseASABaseDatabase_WebServices();
	}

	public String getGroupId(EObject obj) {
		return DBEventGroupID.ASAWEBSERVICE;
	}
}

package org.eclipse.datatools.enablement.sybase.asa.containment;

import java.util.Collection;

import org.eclipse.datatools.connectivity.sqm.core.containment.AbstractContainmentProvider;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABasePredefinedDataType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASADatabase;
import org.eclipse.emf.ecore.EObject;

public class SybaseASAPredefinedTypeContainmentProvider extends
	AbstractContainmentProvider {

	public Collection getContainedElements(EObject obj) {
		SybaseASADatabase database = (SybaseASADatabase) obj;
	    Collection children = super.getContainedElements(obj);
	    children.addAll(database.getDataTypes());
	    return children;
	}

	public String getGroupId(EObject obj) {
		return DBEventGroupID.ASADATATYPE;
	}
	
	public EObject getContainer(EObject obj) 
	{
		if(obj instanceof SybaseASABasePredefinedDataType)
		{
			return (EObject)((SybaseASABasePredefinedDataType)obj).getDatabase().getCatalogs().get(0);
		}
		return obj.eContainer();
	}
}

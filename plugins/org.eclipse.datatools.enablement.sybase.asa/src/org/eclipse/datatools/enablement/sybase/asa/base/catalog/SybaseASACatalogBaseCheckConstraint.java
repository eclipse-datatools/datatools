package org.eclipse.datatools.enablement.sybase.asa.base.catalog;

import java.lang.ref.SoftReference;
import java.sql.Connection;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.enablement.sybase.asa.baseloaders.CheckConstraintASALoader;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.constraints.impl.CheckConstraintImpl;
import org.eclipse.datatools.modelbase.sql.expressions.SearchCondition;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASACatalogBaseCheckConstraint extends CheckConstraintImpl implements ICatalogObject,IAdaptable 
{
	
	private static final long serialVersionUID = 9022615295148300491L;
	
	protected Boolean CCInfoLoaded = Boolean.FALSE;
	
	private SoftReference checkConstraintLoaderRef;
	
	public Database getCatalogDatabase() {
		return this.getBaseTable().getSchema().getDatabase();
	}

	public Connection getConnection() {
		return ((ICatalogObject)getCatalogDatabase()).getConnection();
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		switch(eDerivedStructuralFeatureID(eFeature))
		{
		case SQLConstraintsPackage.CHECK_CONSTRAINT__SEARCH_CONDITION:
			getSearchCondition();
			break;
		}
		return super.eIsSet(eFeature);
	}
	
	public void refresh() {
		synchronized (CCInfoLoaded) {
			if(CCInfoLoaded.booleanValue())
			{
				CCInfoLoaded = Boolean.FALSE;
			}
		}
		RefreshManager.getInstance().referesh(this);
	}

	public SearchCondition getSearchCondition() {
		synchronized (CCInfoLoaded) {
			if(!CCInfoLoaded.booleanValue())
			{
				getCheckConstraintLoader().loadCCInfo(CheckConstraintASALoader.TABLE_CHECK_CONSTRAINT_TYPE);
				CCInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getSearchCondition();
	}
		
	private CheckConstraintASALoader getCheckConstraintLoader()
	{
		CheckConstraintASALoader loader = checkConstraintLoaderRef == null ? null
				: (CheckConstraintASALoader) checkConstraintLoaderRef.get();
		
		if(loader == null)
		{
			loader = createCheckConstraintLoader();
			checkConstraintLoaderRef = new SoftReference(loader);
		}
		
		return loader;
	}
	
	private CheckConstraintASALoader createCheckConstraintLoader()
	{
		return new CheckConstraintASALoader(this);
	}

	public Object getAdapter(Class adapter) {
		Object adapterObject=Platform.getAdapterManager().getAdapter(this, adapter);
		if(adapterObject==null){
			adapterObject=Platform.getAdapterManager().loadAdapter(this, adapter.getName());
		}
		return adapterObject; 
	}
}

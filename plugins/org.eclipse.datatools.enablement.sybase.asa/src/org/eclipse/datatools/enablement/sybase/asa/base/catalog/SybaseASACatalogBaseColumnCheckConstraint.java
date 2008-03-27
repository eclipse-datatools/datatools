package org.eclipse.datatools.enablement.sybase.asa.base.catalog;

import java.lang.ref.SoftReference;
import java.sql.Connection;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.enablement.sybase.asa.baseloaders.CheckConstraintASALoader;
import org.eclipse.datatools.enablement.sybase.asa.baseloaders.ColumnCheckConstraintASALoader;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseColumnCheckConstraintImpl;
import org.eclipse.datatools.modelbase.sql.expressions.SearchCondition;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASACatalogBaseColumnCheckConstraint extends SybaseASABaseColumnCheckConstraintImpl implements ICatalogObject,IAdaptable
{
	private static final long serialVersionUID = -305794559657625178L;
	
	protected Boolean CCInfoLoaded = Boolean.FALSE;
	
	private SoftReference checkConstraintLoaderRef;
	
	public Database getCatalogDatabase() {
		return this.getColumn().getTable().getSchema().getDatabase();
	}

	public Connection getConnection() {
		return ((ICatalogObject)getCatalogDatabase()).getConnection();
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
	
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch(eDerivedStructuralFeatureID(eFeature))
		{
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN_CHECK_CONSTRAINT__SEARCH_CONDITION:
			getSearchCondition();
			break;
		}
		return super.eIsSet(eFeature);
	}
	
	public SearchCondition getSearchCondition() {
		synchronized (CCInfoLoaded) {
			if(!CCInfoLoaded.booleanValue())
			{
				getCheckConstraintLoader().loadCCInfo(CheckConstraintASALoader.COLUMN_CHECK_CONSTRAINT_TYPE);
				CCInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getSearchCondition();
	}


	private ColumnCheckConstraintASALoader getCheckConstraintLoader()
	{
		ColumnCheckConstraintASALoader loader = checkConstraintLoaderRef == null ? null
				: (ColumnCheckConstraintASALoader) checkConstraintLoaderRef.get();
		
		if(loader == null)
		{
			loader = createCheckConstraintLoader();
			checkConstraintLoaderRef = new SoftReference(loader);
		}
		
		return loader;
	}
	
	private ColumnCheckConstraintASALoader createCheckConstraintLoader()
	{
		return new ColumnCheckConstraintASALoader(this);
	}

	public Object getAdapter(Class adapter) {
		Object adapterObject=Platform.getAdapterManager().getAdapter(this, adapter);
		if(adapterObject==null){
			adapterObject=Platform.getAdapterManager().loadAdapter(this, adapter.getName());
		}
		return adapterObject;
	}
	
}

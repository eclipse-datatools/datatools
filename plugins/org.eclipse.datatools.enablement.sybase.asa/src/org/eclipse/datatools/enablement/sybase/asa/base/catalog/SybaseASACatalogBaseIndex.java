package org.eclipse.datatools.enablement.sybase.asa.base.catalog;

import java.lang.ref.SoftReference;
import java.sql.Connection;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.enablement.sybase.asa.baseloaders.IndexInfoASALoader;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDBSpace;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseIndexImpl;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASACatalogBaseIndex extends SybaseASABaseIndexImpl implements ICatalogObject
{
	private static final long serialVersionUID = -7809985229115506992L;
	
	private SoftReference groupLoaderRef;
	protected Boolean indexInfoLoaded = Boolean.FALSE;
	
	public Database getCatalogDatabase() {
		return this.getTable().getSchema().getDatabase();
	}

	public Connection getConnection() {
		return ((ICatalogObject)getCatalogDatabase()).getConnection();
	}

	public void refresh() {
        if(isNeedRefresh())
        {
            synchronized (indexInfoLoaded) {
                if(indexInfoLoaded.booleanValue())
                {
                    indexInfoLoaded = Boolean.FALSE;
                }
            }
            RefreshManager.getInstance().referesh(this);
        }
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		switch(eDerivedStructuralFeatureID(eFeature))
		{
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX__MEMBERS:
			getMembers();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX__UNIQUE:
			isUnique();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX__CLUSTERED:
			isClustered();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX__DB_SPACE:
			getDbSpace();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX__DESCRIPTION:
			getDescription();
			break;
		}
		return super.eIsSet(eFeature);
	}
	
	public EList getMembers() {
		synchronized (indexInfoLoaded) {
			if(!indexInfoLoaded.booleanValue())
			{
				loadIndexInfo();
				indexInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getMembers();
	}
	
	public boolean isUnique() {
		synchronized (indexInfoLoaded) {
			if(!indexInfoLoaded.booleanValue())
			{
				loadIndexInfo();
				indexInfoLoaded = Boolean.TRUE;
			}
		}
		return super.isUnique();
	}

	public boolean isClustered() {
		synchronized (indexInfoLoaded) {
			if(!indexInfoLoaded.booleanValue())
			{
				loadIndexInfo();
				indexInfoLoaded = Boolean.TRUE;
			}
		}
		return super.isClustered();
	}
	
	public SybaseASABaseDBSpace getDbSpace() {
		synchronized (indexInfoLoaded) {
			if(!indexInfoLoaded.booleanValue())
			{
				loadIndexInfo();
				indexInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getDbSpace();
	}

	public String getDescription() {
		synchronized (indexInfoLoaded) {
			if(!indexInfoLoaded.booleanValue())
			{
				loadIndexInfo();
				indexInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getDescription();
	}
	
	private void loadIndexInfo() {
		getIndexLoader().loadIndexInfo(super.getMembers());
	}
	
	
	private IndexInfoASALoader getIndexLoader()
	{
		IndexInfoASALoader loader = groupLoaderRef == null ? null
				: (IndexInfoASALoader) groupLoaderRef.get();
		
		if(loader == null)
		{
			loader = createIndexInfoLoader();
			groupLoaderRef = new SoftReference(loader);
		}
		
		return loader;
	}
	
	protected IndexInfoASALoader createIndexInfoLoader()
	{
		return new IndexInfoASALoader(this);
	}

	private boolean isNeedRefresh()
    {
        if(indexInfoLoaded.booleanValue())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}

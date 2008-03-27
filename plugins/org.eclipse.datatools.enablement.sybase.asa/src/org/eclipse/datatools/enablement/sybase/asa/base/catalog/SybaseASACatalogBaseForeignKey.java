package org.eclipse.datatools.enablement.sybase.asa.base.catalog;

import java.sql.Connection;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.enablement.sybase.asa.baseloaders.SybaseASABaseForeignKeyLoader;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseForeignKeyImpl;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.ReferentialActionType;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASACatalogBaseForeignKey extends SybaseASABaseForeignKeyImpl implements ICatalogObject,IAdaptable 
{
	
	private static final long serialVersionUID = 9022615295148300491L;

	protected Boolean FKInfoLoaded = Boolean.FALSE;
	
	public Database getCatalogDatabase() {
		return this.getBaseTable().getSchema().getDatabase();
	}

	public Connection getConnection() {
		return ((ICatalogObject)getCatalogDatabase()).getConnection();
	}

	public void refresh() {
		synchronized (FKInfoLoaded) {
			if(FKInfoLoaded.booleanValue())
			{
				FKInfoLoaded = Boolean.FALSE;
			}
		}
		RefreshManager.getInstance().referesh(this);
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		switch(eDerivedStructuralFeatureID(eFeature))
		{
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY__MEMBERS:
			getMembers();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY__CLUSTERED:
			isClustered();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY__REFERENCED_MEMBERS:
			getReferencedMembers();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY__ON_UPDATE:
			getOnUpdate();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY__ON_DELETE:
			getOnDelete();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY__REFERENCED_TABLE:
			getReferencedTable();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY__ROLE_NAME:
			getRoleName();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY__DESCRIPTION:
			getDescription();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY__UNIQUE_CONSTRAINT:
			getUniqueConstraint();
			break;
//		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY__CHECK_ON_COMMIT:
//			isCheckOnCommit();
//			break;
            //TODO: getUniqueIndex support
		}
		return super.eIsSet(eFeature);
	}
	
	public EList getMembers() {
		synchronized (FKInfoLoaded) {
			if(!FKInfoLoaded.booleanValue())
			{
				loadFKInfo();
				FKInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getMembers();
	}

	public boolean isClustered() {
		synchronized (FKInfoLoaded) {
			if(!FKInfoLoaded.booleanValue())
			{
				loadFKInfo();
				FKInfoLoaded = Boolean.TRUE;
			}
		}
		return super.isClustered();
	}
	
	public EList getReferencedMembers() {
		synchronized (FKInfoLoaded) {
			if(!FKInfoLoaded.booleanValue())
			{
				loadFKInfo();
				FKInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getReferencedMembers();
	}
	
	public ReferentialActionType getOnUpdate() {
		synchronized (FKInfoLoaded) {
			if(!FKInfoLoaded.booleanValue())
			{
				loadFKInfo();
				FKInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getOnUpdate();
	}
	
	public ReferentialActionType getOnDelete() {
		synchronized (FKInfoLoaded) {
			if(!FKInfoLoaded.booleanValue())
			{
				loadFKInfo();
				FKInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getOnDelete();
	}

	public BaseTable getReferencedTable() {
		synchronized (FKInfoLoaded) {
			if(!FKInfoLoaded.booleanValue())
			{
				loadFKInfo();
				FKInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getReferencedTable();
	}

	public String getRoleName() {
		synchronized (FKInfoLoaded) {
			if(!FKInfoLoaded.booleanValue())
			{
				loadFKInfo();
				FKInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getRoleName();
	}
	
//	public boolean isCheckOnCommit() {
//		synchronized (FKInfoLoaded) {
//			if(!FKInfoLoaded.booleanValue())
//			{
//				loadFKInfo();
//				FKInfoLoaded = Boolean.TRUE;
//			}
//		}
//		return super.isCheckOnCommit();
//	}

	public String getDescription() {
		synchronized (FKInfoLoaded) {
			if(!FKInfoLoaded.booleanValue())
			{
				loadFKInfo();
				FKInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getDescription();
	}
	
	public UniqueConstraint getUniqueConstraint() {
		synchronized (FKInfoLoaded) {
			if(!FKInfoLoaded.booleanValue())
			{
				loadFKInfo();
				FKInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getUniqueConstraint();
	}
	
	private void loadFKInfo()
	{
		new SybaseASABaseForeignKeyLoader(this).loadFKInfo(super.getMembers(), super.getReferencedMembers());
	}
	
	public Object getAdapter(Class adapter) {
		Object adapterObject=Platform.getAdapterManager().getAdapter(this, adapter);
		if(adapterObject==null){
			adapterObject=Platform.getAdapterManager().loadAdapter(this, adapter.getName());
		}
		return adapterObject;
	}	
}

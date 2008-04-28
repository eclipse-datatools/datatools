package org.eclipse.datatools.enablement.sybase.asa.base.catalog;

import java.lang.ref.SoftReference;
import java.sql.Connection;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.containment.GroupID;
import org.eclipse.datatools.enablement.sybase.VirtualNodeAdapter;
import org.eclipse.datatools.enablement.sybase.asa.baseloaders.SchemaASABaseLoader;
import org.eclipse.datatools.enablement.sybase.asa.catalog.ASAUtil;
import org.eclipse.datatools.enablement.sybase.asa.containment.DBEventGroupID;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseSchemaImpl;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.internal.refresh.ICatalogObject2;
import org.eclipse.datatools.sqltools.internal.refresh.RefreshManager2;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASACatalogBaseSchema extends SybaseASABaseSchemaImpl implements ICatalogObject2{

	private static final long serialVersionUID = -4758818420058402657L;
	
	protected Boolean tablesLoaded = Boolean.FALSE;
	protected Boolean routinesLoaded = Boolean.FALSE;
	protected Boolean userDefintDatatypesLoaded = Boolean.FALSE;
	private Boolean ownerLoaded = Boolean.FALSE;
	
	protected SoftReference schemaLoaderRef = null;

	public Database getCatalogDatabase() {
		 return getCatalog().getDatabase();
	}

	public Connection getConnection() {
		return ((ICatalogObject)getCatalogDatabase()).getConnection();
	}

    public void refresh() {
        refresh(null);
    }
    
    public void refresh(String context) {
        if (!needsRefresh(context))
        {
            return;
        }
        if (context == null || context.equals(GroupID.TABLE) || context.equals(GroupID.VIEW))
        {
            synchronized (tablesLoaded)
            {
                if (tablesLoaded.booleanValue())
                {
                    tablesLoaded = Boolean.FALSE;
                }
            }
        }
        if (context == null || context.equals(GroupID.PROCEDURE))
        {
            synchronized (routinesLoaded)
            {
                if (routinesLoaded.booleanValue())
                {
                    routinesLoaded = Boolean.FALSE;
                }
            }
        }
        if (context == null || context.equals(GroupID.USER_DEFINED_TYPE))
        {
            synchronized (userDefintDatatypesLoaded)
            {
                if (userDefintDatatypesLoaded.booleanValue())
                {
                    userDefintDatatypesLoaded = Boolean.FALSE;
                }
            }
        }

        if (context == null || context.equals(DBEventGroupID.ASAOWNER))
        {
            synchronized (ownerLoaded)
            {
                if (ownerLoaded.booleanValue())
                    ownerLoaded = Boolean.FALSE;
            }
        }
        
        RefreshManager2.getInstance().referesh(this, context);
    }
    
    public boolean needsRefresh(String context)
    {
        if (context != null)
        {
            if (context.equals(GroupID.PROCEDURE))
            {
                return routinesLoaded.booleanValue();
            }
            else if (context.equals(GroupID.TABLE) || context.equals(GroupID.VIEW))
            {
                return tablesLoaded.booleanValue();
            }
            else if (context.equals(GroupID.USER_DEFINED_TYPE))
            {
                return userDefintDatatypesLoaded.booleanValue();
            }
            else if (context.equals(DBEventGroupID.ASAOWNER))
            {
                return ownerLoaded.booleanValue();
            }
        }
        return tablesLoaded.booleanValue() || routinesLoaded.booleanValue() || userDefintDatatypesLoaded.booleanValue()
                || ownerLoaded.booleanValue();
    }
    
    public String getRefreshContext(Object obj)
    {
        if (obj instanceof IAdaptable)
        {
        	VirtualNodeAdapter adapter = (VirtualNodeAdapter)((IAdaptable)obj).getAdapter(VirtualNodeAdapter.class);
			if (adapter != null)
			{
				return adapter.getGroupId();
			}
        }
        else if (obj instanceof Integer)
        {
            int i = ((Integer)obj).intValue();
            switch(i)
            {
                case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_SCHEMA__TABLES:
                    return GroupID.TABLE;
                case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_SCHEMA__ROUTINES:
                    return GroupID.PROCEDURE;
                case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_SCHEMA__USER_DEFINED_TYPES:
                    return GroupID.USER_DEFINED_TYPE;
                case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_SCHEMA__OWNER:
                    return DBEventGroupID.ASAOWNER;
                default:
                    return null;
            }
            
        }
        else if (obj instanceof Table)
        {
            return GroupID.TABLE;
        }
        else if (obj instanceof Routine)
        {
            return GroupID.PROCEDURE;
        }
        else if (obj instanceof UserDefinedType)
        {
            return GroupID.USER_DEFINED_TYPE;
        }
        else if (obj instanceof AuthorizationIdentifier)
        {
            return DBEventGroupID.ASAOWNER;
        }
        return null;
    }
    
	public AuthorizationIdentifier getOwner() {
		synchronized (ownerLoaded) {
            if (!ownerLoaded.booleanValue())
            {
            	AuthorizationIdentifier authId = (AuthorizationIdentifier)ASAUtil.getSQLObject(((SybaseASABaseDatabase)this.getDatabase()).getAuthorizationIds(), this.getName());
            	super.setOwner(authId);
            }
        }
		return super.getOwner();
	}
	
	protected SchemaASABaseLoader createSchemaLoader()
	{
		return new SchemaASABaseLoader(this);
	}
	
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch(eDerivedStructuralFeatureID(eFeature))
		{
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE:
			getTables();
			break;
		case SQLRoutinesPackage.ROUTINE:
			getRoutines();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_USER_DEFINED_TYPE:
			getUserDefinedTypes();
			break; 
		}
		return super.eIsSet(eFeature);
	}

	public EList getRoutines() {
		synchronized (routinesLoaded) {
			if(!routinesLoaded.booleanValue())
			{
				this.getSchemaLoader().loadRoutines(super.getRoutines());
				routinesLoaded = Boolean.TRUE;
			}
		}
		return super.getRoutines();
	}
	
	public EList getTables() {
		synchronized (tablesLoaded) {
			if(!tablesLoaded.booleanValue())
			{
				this.getSchemaLoader().loadTables(super.getTables());
				tablesLoaded = Boolean.TRUE;
			}
		}
		return super.getTables();
	}
	
	public EList getUserDefinedTypes() {
		synchronized (userDefintDatatypesLoaded) {
			if(!userDefintDatatypesLoaded.booleanValue())
			{
				this.getSchemaLoader().loadUDTs(super.getUserDefinedTypes());
				userDefintDatatypesLoaded = Boolean.TRUE;
			}
		}
		return super.getUserDefinedTypes();
	}
	
	protected SchemaASABaseLoader getSchemaLoader()
	{
		SchemaASABaseLoader loader = schemaLoaderRef == null ? null
				: (SchemaASABaseLoader) schemaLoaderRef.get();
		
		if(loader == null)
		{
			loader = createSchemaLoader();
			schemaLoaderRef = new SoftReference(loader);
		}
		
		return loader;
	}
	
	public EList getTriggers()
    {
        this.getSchemaLoader().batchLoadTriggers();
        return super.getTriggers();
    }
	
	public EList getIndices()
    {
	    this.getSchemaLoader().batchLoadIndices();
        return super.getIndices();
    }
	
//	/**
//	 * @generated NOT
//	 */
//	public NotificationChain basicSetCatalog(Catalog newCatalog,
//			NotificationChain msgs) {
//		if (catalog != null && catalog.getDatabase() != null) {
//			ConnectionInfo connectionInfo = DatabaseConnectionRegistry
//					.getInstance().getConnectionForDatabase(
//							catalog.getDatabase());
//			connectionInfo.removeFilterListener(filterListener);
//		}
//		if (newCatalog != null && newCatalog.getDatabase() != null) {
//			ConnectionInfo connectionInfo = DatabaseConnectionRegistry
//					.getInstance().getConnectionForDatabase(
//							newCatalog.getDatabase());
//			connectionInfo.addFilterListener(filterListener);
//		}
//		return super.basicSetCatalog(newCatalog, msgs);
//	}
//
//	private void handleFilterChanged(String filterKey) {
//		boolean refresh = false;
//		ConnectionInfo conInf = DatabaseConnectionRegistry.getInstance()
//				.getConnectionForDatabase(getCatalogDatabase());
//		if (tablesLoaded.booleanValue()
//				&& filterKey.equals(getTableFilterKey())
//				|| (conInf != null
//						&& conInf.getFilter(getTableFilterKey()) == null && (ConnectionFilter.TABLE_FILTER
//						.equals(filterKey) || filterKey.equals(getCatalog()
//						.getName()
//						+ ConnectionFilter.FILTER_SEPARATOR
//						+ ConnectionFilter.TABLE_FILTER)))) {
//			tablesLoaded = Boolean.FALSE;
//			refresh = true;
//		}
//		if (routinesLoaded.booleanValue()
//				&& filterKey.equals(getRoutineFilterKey())
//				|| (conInf != null
//						&& conInf.getFilter(getRoutineFilterKey()) == null && (ConnectionFilter.STORED_PROCEDURE_FILTER
//						.equals(filterKey) || filterKey.equals(getCatalog()
//						.getName()
//						+ ConnectionFilter.FILTER_SEPARATOR
//						+ ConnectionFilter.STORED_PROCEDURE_FILTER)))) {
//			routinesLoaded = Boolean.FALSE;
//			refresh = true;
//		}
//		if (userDefintDatatypesLoaded.booleanValue()
//				&& filterKey.equals(getUDTFilterKey())
//				|| (conInf != null
//						&& conInf.getFilter(getUDTFilterKey()) == null && (ConnectionFilter.USER_DEFINED_TYPE_FILTER
//						.equals(filterKey) || filterKey.equals(getCatalog()
//						.getName()
//						+ ConnectionFilter.FILTER_SEPARATOR
//						+ ConnectionFilter.USER_DEFINED_TYPE_FILTER)))) {
//			userDefintDatatypesLoaded = Boolean.FALSE;
//			refresh = true;
//		}
//		if (refresh) {
//			RefreshManager.getInstance().referesh(this);
//		}
//	}
//	
//	private String getTableFilterKey() {
//		return getCatalog().getName() + ConnectionFilter.FILTER_SEPARATOR
//				+ getName() + ConnectionFilter.FILTER_SEPARATOR
//				+ ConnectionFilter.TABLE_FILTER;
//	}
//	
//	private String getRoutineFilterKey() {
//		return getCatalog().getName() + ConnectionFilter.FILTER_SEPARATOR
//				+ getName() + ConnectionFilter.FILTER_SEPARATOR
//				+ ConnectionFilter.STORED_PROCEDURE_FILTER;
//	}
//	
//	private String getUDTFilterKey() {
//		return getCatalog().getName() + ConnectionFilter.FILTER_SEPARATOR
//				+ getName() + ConnectionFilter.FILTER_SEPARATOR
//				+ ConnectionFilter.USER_DEFINED_TYPE_FILTER;
//	}
//	
//	private transient ConnectionFilterListener filterListener = new ConnectionFilterListener() {
//
//		public void connectionFilterAdded(String filterKey) {
//			handleFilterChanged(filterKey);
//		}
//
//		public void connectionFilterRemoved(String filterKey) {
//			handleFilterChanged(filterKey);
//		}
//
//	};	
}

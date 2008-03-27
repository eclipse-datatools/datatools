package org.eclipse.datatools.enablement.sybase.asa.catalog;

import java.lang.ref.SoftReference;
import java.sql.Connection;
import java.sql.SQLException;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.enablement.sybase.asa.JDBCASAPlugin;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASABaseCatalog.ISybaseASABaseCatalogDatabase;
import org.eclipse.datatools.enablement.sybase.asa.baseloaders.SybaseASABaseDatabaseLoader;
import org.eclipse.datatools.enablement.sybase.asa.loaders.SybaseASADatabaseLoader;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EncryptionInfo;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.JavaSupportType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseasasqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl.SybaseASADatabaseImpl;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;


public class SybaseASACatalogDatabase extends SybaseASADatabaseImpl implements ICatalogObject, IAdaptable, ISybaseASABaseCatalogDatabase{
	private static final long serialVersionUID = 3257562914901669687L;
	
	protected Connection connection = null;
	protected String dbName = null;
	
	protected Boolean schemasLoaded = Boolean.FALSE;
	protected Boolean webServicesLoaded = Boolean.FALSE;
	protected Boolean dataTypesLoaded = Boolean.FALSE;
	protected Boolean eventsLoaded = Boolean.FALSE;
	protected Boolean authIdsLoaded = Boolean.FALSE;
	protected Boolean dbspacesLoaded = Boolean.FALSE;
	protected Boolean dbInfo1Loaded = Boolean.FALSE;
	protected Boolean dbInfo2Loaded = Boolean.FALSE;
	protected Boolean catalogsLoaded = Boolean.FALSE;
	
	private SoftReference databaseLoaderRef = null;
//	private transient ConnectionFilterListener filterListener;

	public SybaseASACatalogDatabase(Connection connection) {
		if(connection == null) {
			System.err.println("null connection"); //$NON-NLS-1$
			throw new RuntimeException();
		}
		this.connection = connection;
		try {
			this.dbName = this.connection.getCatalog();
			setName(dbName);
		} catch (SQLException e) {
			JDBCASAPlugin.getDefault().log(e);
		}
	}

	/**
	 * This constructor is specially used for 'working set' functionality.
	 * DON'T USE IT.
	 *
	 */
	public SybaseASACatalogDatabase(){
		
	}
	
	public Connection getConnection() {
		return this.connection;
	}
	
	public Database getCatalogDatabase() {
		return this;		
	}

	public void refresh() {
		synchronized (schemasLoaded) {
			if(schemasLoaded.booleanValue())
				schemasLoaded = Boolean.FALSE;
		}
		synchronized (webServicesLoaded) {
			if(webServicesLoaded.booleanValue())
				webServicesLoaded = Boolean.FALSE;
		}
		synchronized (dataTypesLoaded) {
			if(dataTypesLoaded.booleanValue())
				dataTypesLoaded = Boolean.FALSE;
		}
		synchronized (eventsLoaded) {
			if(eventsLoaded.booleanValue())
				eventsLoaded = Boolean.FALSE;
		}
		synchronized (authIdsLoaded) {
			if(authIdsLoaded.booleanValue())
				authIdsLoaded = Boolean.FALSE;
		}
		synchronized (dbInfo1Loaded) {
			if(dbInfo1Loaded.booleanValue())
				dbInfo1Loaded = Boolean.FALSE;
		}
		synchronized (dbInfo2Loaded) {
			if(dbInfo2Loaded.booleanValue())
				dbInfo2Loaded = Boolean.FALSE;
		}
		synchronized (dbspacesLoaded) {
			if(dbspacesLoaded.booleanValue())
				dbspacesLoaded = Boolean.FALSE;
		}
		synchronized (catalogsLoaded) {
			if(catalogsLoaded.booleanValue())
				catalogsLoaded = Boolean.FALSE;
		}
		RefreshManager.getInstance().referesh(this);
	}
	
	public void refreshEvent()
	{
		synchronized (eventsLoaded) {
			if(eventsLoaded.booleanValue())
				eventsLoaded = Boolean.FALSE;
		}
	}
	
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch(eDerivedStructuralFeatureID(eFeature))
		{
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__SCHEMAS:
			this.getSchemas();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__WEB_SERVICES:
			this.getWebServices();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__EVENTS:
			this.getEvents();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__DATA_TYPES:
			this.getDataTypes();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__AUTHORIZATION_IDS:
			this.getAuthorizationIds();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__DB_SPACES:
			this.getDbSpaces();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__DATABASE_FILE_NAME:
			this.getDatabaseFileName();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__LOG_FILE_NAME:
			this.getLogFileName();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__MIRROR_FILE_NAME:
			this.getMirrorFileName();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__CASE_SENSITIVE:
			this.isCaseSensitive();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__COLLATION:
			this.getCollation();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__BLANK_PADDING_ON:
			this.isBlankPaddingOn();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__CHECK_SUM_ON:
			this.isCheckSumOn();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__JCONNECT_ON:
			this.isJConnectOn();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__PAGE_SIZE:
			this.getPageSize();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__ENCRYPTION_INFO:
			this.getEncryptionInfo();
			break;
		case SybaseasasqlmodelPackage.SYBASE_ASA_DATABASE__ASE_COMPATIBLE:
			this.isASECompatible();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__CATALOGS:
			this.getCatalogs();
			break;
        case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE__PASSWORD_CASE_SENSITIVE:
            this.getPasswordCaseSensitive();
            break;
		}
		return super.eIsSet(eFeature);
	}	
	
	public EList getCatalogs() {
		synchronized (catalogsLoaded) {
			if(!this.catalogsLoaded.booleanValue())
			{
				EList catalogs = super.getCatalogs(); 
				if(catalogs.size() > 0)
				{
				    SybaseASACatalog catalog = (SybaseASACatalog)catalogs.get(0);
				    catalog.refresh();
				}
				else
				{
    				SybaseASACatalog catalog = new SybaseASACatalog();
    				catalog.setName(dbName);
    				catalogs.add(catalog);
				}
                catalogsLoaded = Boolean.TRUE;
			}
		}
		return super.getCatalogs();
	}
	
	public EList getWebServices() {
		synchronized (webServicesLoaded) {
			if(!this.webServicesLoaded.booleanValue()) 
			{
				this.getDatabaseLoader().loadWebServices(super.getWebServices());
				this.webServicesLoaded = Boolean.TRUE;
			}
		}
		
		return super.getWebServices();
	}
	
	public EList getSchemas() {

        Catalog catalog = (Catalog) this.getCatalogs().get(0);
        catalog.getSchemas();

		return super.getSchemas();
	}

	public EList getDataTypes() {
		synchronized (dataTypesLoaded) {
			if(!this.dataTypesLoaded.booleanValue()) 
			{
				this.getDatabaseLoader().loadDataTypes(super.getDataTypes());
				this.dataTypesLoaded = Boolean.TRUE;
			}
		}
		return super.getDataTypes();
	}
	
	public EList getEvents() {
		synchronized (eventsLoaded) {
			if(!this.eventsLoaded.booleanValue()) 
			{
				this.getDatabaseLoader().loadEvents(super.getEvents());
				this.eventsLoaded = Boolean.TRUE;
			}
		}
		return super.getEvents();
	}
	
	public EList getAuthorizationIds() {
		synchronized (authIdsLoaded) 
		{
			if(!this.authIdsLoaded.booleanValue())
			{
				this.getDatabaseLoader().loadAuthIds(super.getAuthorizationIds());
				this.authIdsLoaded = Boolean.TRUE;
			}
		}
		return super.getAuthorizationIds();
	}
	
	public EList getDbSpaces() {
		synchronized (dbspacesLoaded) {
			if(!dbspacesLoaded.booleanValue())
			{
				this.getDatabaseLoader().loadDbSpaces(super.getDbSpaces());
				dbspacesLoaded = Boolean.TRUE;
			}
		}
		return super.getDbSpaces();
	}

	public String getLogFileName() {
		synchronized (dbInfo1Loaded) {
			if(!dbInfo1Loaded.booleanValue())
			{
				this.getDatabaseLoader().loadDbInfo1();
				this.dbInfo1Loaded = Boolean.TRUE;
			}
		}
		return super.getLogFileName();
	}
	
	public String getDatabaseFileName() {
		synchronized (dbInfo1Loaded) {
			if(!dbInfo1Loaded.booleanValue())
			{
				this.getDatabaseLoader().loadDbInfo1();
				this.dbInfo1Loaded = Boolean.TRUE;
			}
		}
		return super.getDatabaseFileName();
	}
	
	public String getMirrorFileName() {
		synchronized (dbInfo1Loaded) {
			if(!dbInfo1Loaded.booleanValue())
			{
				this.getDatabaseLoader().loadDbInfo1();
				this.dbInfo1Loaded = Boolean.TRUE;
			}
		}
		return super.getMirrorFileName();
	}
	
	public boolean isCaseSensitive() {
		synchronized (dbInfo1Loaded) {
			if(!dbInfo1Loaded.booleanValue())
			{
				this.getDatabaseLoader().loadDbInfo1();
				this.dbInfo1Loaded = Boolean.TRUE;
			}
		}
		return super.isCaseSensitive();
	}
	
	public int getPageSize() {
		synchronized (dbInfo1Loaded) {
			if(!dbInfo1Loaded.booleanValue())
			{
				this.getDatabaseLoader().loadDbInfo1();
				this.dbInfo1Loaded = Boolean.TRUE;
			}
		}
		return super.getPageSize();
	}
	
	public String getCollation() {
		synchronized (dbInfo1Loaded) {
			if(!dbInfo1Loaded.booleanValue())
			{
				this.getDatabaseLoader().loadDbInfo1();
				this.dbInfo1Loaded = Boolean.TRUE;
			}
		}
		return super.getCollation();
	}
	
	public boolean isBlankPaddingOn() {
		synchronized (dbInfo1Loaded) {
			if(!dbInfo1Loaded.booleanValue())
			{
				this.getDatabaseLoader().loadDbInfo1();
				this.dbInfo1Loaded = Boolean.TRUE;
			}
		}
		return super.isBlankPaddingOn();
	}
	
	public boolean isJConnectOn() {
		synchronized (dbInfo2Loaded) {
			if(!dbInfo2Loaded.booleanValue())
			{
				this.getDatabaseLoader().loadDbInfo2();
				this.dbInfo2Loaded = Boolean.TRUE;
			}
				
		}
		return super.isJConnectOn();
	}
	
	public boolean isCheckSumOn() {
		synchronized (dbInfo1Loaded) {
			if(!dbInfo1Loaded.booleanValue())
			{
				this.getDatabaseLoader().loadDbInfo1();
				this.dbInfo1Loaded = Boolean.TRUE;
			}
		}
		return super.isCheckSumOn();
	}
	
	public JavaSupportType getJavaSupport() {
		synchronized (dbInfo2Loaded) {
			if(!dbInfo2Loaded.booleanValue())
			{
				this.getDatabaseLoader().loadDbInfo2();
				this.dbInfo2Loaded = Boolean.TRUE;
			}
		}
		return super.getJavaSupport();
	}
	
	public Boolean getPasswordCaseSensitive() {
        synchronized (dbInfo1Loaded) {
            if(!dbInfo1Loaded.booleanValue())
            {
                this.getDatabaseLoader().loadDbInfo1();
                this.dbInfo1Loaded = Boolean.TRUE;
            }
        }
        return super.getPasswordCaseSensitive();
	}
	
	public EncryptionInfo getEncryptionInfo() {
		synchronized (dbInfo1Loaded) {
			if(!dbInfo1Loaded.booleanValue())
			{
				this.getDatabaseLoader().loadDbInfo1();
				this.dbInfo1Loaded = Boolean.TRUE;
			}
		}
		return super.getEncryptionInfo();
	}

	protected SybaseASABaseDatabaseLoader getDatabaseLoader()
	{
		SybaseASABaseDatabaseLoader loader = databaseLoaderRef == null ? null
				: (SybaseASABaseDatabaseLoader) databaseLoaderRef.get();
		
		if(loader == null)
		{
			loader = createDatabaseLoader();
			databaseLoaderRef = new SoftReference(loader);
		}
		
		return loader;
	}
	
//	public class FilterListener implements ConnectionFilterListener {
//
//		public void connectionFilterAdded(String filterKey) {
//			handleFilterChanged(filterKey);
//		}
//
//		public void connectionFilterRemoved(String filterKey) {
//			handleFilterChanged(filterKey);
//		}
//	}
	
//	private void handleFilterChanged(String filterKey) {
//		boolean refresh = false;
//		ConnectionInfo conInf = DatabaseConnectionRegistry.getInstance()
//				.getConnectionForDatabase(this);
//		if (schemasLoaded.booleanValue()
//				&& (filterKey.equals(getSchemaFilterKey()) || (conInf != null
//						&& ConnectionFilter.SCHEMA_FILTER.equals(filterKey) && conInf
//						.getFilter(getSchemaFilterKey()) == null))) {
//			schemasLoaded = Boolean.FALSE;
//			refresh = true;
//		}
//		if (refresh) {
//			RefreshManager.getInstance().referesh(this);
//		}
//	}
	
//	private String getSchemaFilterKey() {
//		return this.getName() + ConnectionFilter.FILTER_SEPARATOR
//				+ ConnectionFilter.SCHEMA_FILTER;
//	} 
	
	
	public boolean isASECompatible() {
		synchronized (dbInfo2Loaded) {
			if(!dbInfo2Loaded.booleanValue())
			{
				getDatabaseLoader().loadDbInfo2();
				this.dbInfo2Loaded = Boolean.TRUE;
			}
		}
		return super.isASECompatible();
	}
	
	protected SybaseASABaseDatabaseLoader createDatabaseLoader()
	{
		return new SybaseASADatabaseLoader(this);
	}

	public Object getAdapter(Class adapter) {
		Object adapterObject=Platform.getAdapterManager().getAdapter(this, adapter);
		if(adapterObject==null){
			adapterObject=Platform.getAdapterManager().loadAdapter(this, adapter.getName());
		}
		return adapterObject;
	}

}

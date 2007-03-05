package org.eclipse.datatools.enablement.sybase.asa.base.catalog;

import java.lang.ref.SoftReference;
import java.sql.Connection;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilterListener;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.DatabaseConnectionRegistry;
import org.eclipse.datatools.enablement.sybase.asa.baseloaders.SchemaASABaseLoader;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseSchemaImpl;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.impl.SchemaImpl;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASACatalogBaseSchema extends SybaseASABaseSchemaImpl implements ICatalogObject{

	private static final long serialVersionUID = -4758818420058402657L;
	
	protected Boolean tablesLoaded = Boolean.FALSE;
	protected Boolean routinesLoaded = Boolean.FALSE;
	protected Boolean userDefintDatatypesLoaded = Boolean.FALSE;
	
	protected SoftReference schemaLoaderRef = null;

	public Database getCatalogDatabase() {
		return this.getDatabase();
	}

	public Connection getConnection() {
		return ((ICatalogObject)getCatalogDatabase()).getConnection();
	}

	public void refresh() {
		synchronized (tablesLoaded) {
			if(tablesLoaded.booleanValue())
				tablesLoaded = Boolean.FALSE;
		}
		synchronized (routinesLoaded) {
			if(routinesLoaded.booleanValue())
				routinesLoaded = Boolean.FALSE;
		}
		synchronized (userDefintDatatypesLoaded) {
			if(userDefintDatatypesLoaded.booleanValue())
				userDefintDatatypesLoaded = Boolean.FALSE;
		}
		RefreshManager.getInstance().referesh(this);
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
	
	/**
	 * @generated NOT
	 */
	public NotificationChain basicSetCatalog(Catalog newCatalog,
			NotificationChain msgs) {
		if (catalog != null && catalog.getDatabase() != null) {
			ConnectionInfo connectionInfo = DatabaseConnectionRegistry
					.getInstance().getConnectionForDatabase(
							catalog.getDatabase());
			connectionInfo.removeFilterListener(filterListener);
		}
		if (newCatalog != null && newCatalog.getDatabase() != null) {
			ConnectionInfo connectionInfo = DatabaseConnectionRegistry
					.getInstance().getConnectionForDatabase(
							newCatalog.getDatabase());
			connectionInfo.addFilterListener(filterListener);
		}
		return super.basicSetCatalog(newCatalog, msgs);
	}

	private void handleFilterChanged(String filterKey) {
		boolean refresh = false;
		ConnectionInfo conInf = DatabaseConnectionRegistry.getInstance()
				.getConnectionForDatabase(getCatalogDatabase());
		if (tablesLoaded.booleanValue()
				&& filterKey.equals(getTableFilterKey())
				|| (conInf != null
						&& conInf.getFilter(getTableFilterKey()) == null && (ConnectionFilter.TABLE_FILTER
						.equals(filterKey) || filterKey.equals(getCatalog()
						.getName()
						+ ConnectionFilter.FILTER_SEPARATOR
						+ ConnectionFilter.TABLE_FILTER)))) {
			tablesLoaded = Boolean.FALSE;
			refresh = true;
		}
		if (routinesLoaded.booleanValue()
				&& filterKey.equals(getRoutineFilterKey())
				|| (conInf != null
						&& conInf.getFilter(getRoutineFilterKey()) == null && (ConnectionFilter.STORED_PROCEDURE_FILTER
						.equals(filterKey) || filterKey.equals(getCatalog()
						.getName()
						+ ConnectionFilter.FILTER_SEPARATOR
						+ ConnectionFilter.STORED_PROCEDURE_FILTER)))) {
			routinesLoaded = Boolean.FALSE;
			refresh = true;
		}
		if (userDefintDatatypesLoaded.booleanValue()
				&& filterKey.equals(getUDTFilterKey())
				|| (conInf != null
						&& conInf.getFilter(getUDTFilterKey()) == null && (ConnectionFilter.USER_DEFINED_TYPE_FILTER
						.equals(filterKey) || filterKey.equals(getCatalog()
						.getName()
						+ ConnectionFilter.FILTER_SEPARATOR
						+ ConnectionFilter.USER_DEFINED_TYPE_FILTER)))) {
			userDefintDatatypesLoaded = Boolean.FALSE;
			refresh = true;
		}
		if (refresh) {
			RefreshManager.getInstance().referesh(this);
		}
	}
	
	private String getTableFilterKey() {
		return getCatalog().getName() + ConnectionFilter.FILTER_SEPARATOR
				+ getName() + ConnectionFilter.FILTER_SEPARATOR
				+ ConnectionFilter.TABLE_FILTER;
	}
	
	private String getRoutineFilterKey() {
		return getCatalog().getName() + ConnectionFilter.FILTER_SEPARATOR
				+ getName() + ConnectionFilter.FILTER_SEPARATOR
				+ ConnectionFilter.STORED_PROCEDURE_FILTER;
	}
	
	private String getUDTFilterKey() {
		return getCatalog().getName() + ConnectionFilter.FILTER_SEPARATOR
				+ getName() + ConnectionFilter.FILTER_SEPARATOR
				+ ConnectionFilter.USER_DEFINED_TYPE_FILTER;
	}
	
	private transient ConnectionFilterListener filterListener = new ConnectionFilterListener() {

		public void connectionFilterAdded(String filterKey) {
			handleFilterChanged(filterKey);
		}

		public void connectionFilterRemoved(String filterKey) {
			handleFilterChanged(filterKey);
		}

	};	
}

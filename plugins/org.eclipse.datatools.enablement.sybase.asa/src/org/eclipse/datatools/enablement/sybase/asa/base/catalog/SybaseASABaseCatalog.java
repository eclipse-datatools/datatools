package org.eclipse.datatools.enablement.sybase.asa.base.catalog;

import java.lang.ref.SoftReference;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilterListener;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.DatabaseConnectionRegistry;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCSchemaLoader;
import org.eclipse.datatools.enablement.sybase.asa.JDBCASAPlugin;
import org.eclipse.datatools.enablement.sybase.asa.baseloaders.SybaseASABaseDatabaseLoader;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.impl.CatalogImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASABaseCatalog extends CatalogImpl implements ICatalogObject
{

	private static final long serialVersionUID = 3372967146783478978L;

	protected Boolean schemaLoaded = Boolean.FALSE;
	private SoftReference schemaLoaderRef;
	
	private transient ConnectionFilterListener filterListener;
	
	public Database getCatalogDatabase() {
		return getDatabase();
	}

	public Connection getConnection() 
	{
		return ((ICatalogObject)getCatalogDatabase()).getConnection();
	}

	public void refresh() {
		synchronized (schemaLoaded) {
			if(schemaLoaded.booleanValue())
				schemaLoaded = Boolean.FALSE;
		}
		RefreshManager.getInstance().referesh(this);
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		return super.eIsSet(eFeature);
	}	
	
	public EList getSchemas() {
		synchronized (schemaLoaded) {
			if(!this.schemaLoaded.booleanValue()) 
			{
				loadSchemas(super.getSchemas());
				this.schemaLoaded = Boolean.TRUE;
				
				if (filterListener == null) {
					ConnectionInfo connectionInfo = DatabaseConnectionRegistry
							.getInstance().getConnectionForDatabase(
									getCatalogDatabase());
					filterListener = new FilterListener();
					connectionInfo.addFilterListener(filterListener);
				}
			}
		}
		return super.getSchemas();
	}
	
	public void loadSchemas(EList schemaConstainmentList) {
		try {
			boolean deliver = database.eDeliver();
			database.eSetDeliver(false);
			
			List existingSchemas = new ArrayList(schemaConstainmentList.size());
			existingSchemas.addAll(schemaConstainmentList);
			getSchemaLoader().clearSchemas(schemaConstainmentList);
			getSchemaLoader().loadSchemas(schemaConstainmentList, existingSchemas);

			database.eSetDeliver(deliver);
		}
		catch (Exception e) {
			JDBCASAPlugin.getDefault().log(e);
		}
	}
	
	private JDBCSchemaLoader getSchemaLoader() {
		if (schemaLoaderRef == null || schemaLoaderRef.get() == null) {
			schemaLoaderRef = new SoftReference(createSchemaLoader());
		}
		return (JDBCSchemaLoader) schemaLoaderRef.get();
	}
	
	protected JDBCSchemaLoader createSchemaLoader() {
		return new SybaseASABaseDatabaseLoader.ASABaseSchemaLoader(this);
	}
	
	public static class ASABaseSchemaLoader extends JDBCSchemaLoader {

		public ASABaseSchemaLoader(ICatalogObject catalogObject) {
			super(catalogObject);
		}

		protected Schema createSchema() {
			return new SybaseASACatalogBaseSchema();
		}
	}
	
	public class FilterListener implements ConnectionFilterListener {
	
			public void connectionFilterAdded(String filterKey) {
				handleFilterChanged(filterKey);
			}
	
			public void connectionFilterRemoved(String filterKey) {
				handleFilterChanged(filterKey);
			}
		}
		
		private void handleFilterChanged(String filterKey) {
			boolean refresh = false;
			ConnectionInfo conInf = DatabaseConnectionRegistry.getInstance()
					.getConnectionForDatabase(this.getDatabase());
			if (schemaLoaded.booleanValue()
					&& (filterKey.equals(getSchemaFilterKey()) || (conInf != null
							&& ConnectionFilter.SCHEMA_FILTER.equals(filterKey) && conInf
							.getFilter(getSchemaFilterKey()) == null))) {
				schemaLoaded = Boolean.FALSE;
				refresh = true;
			}
			if (refresh) {
				RefreshManager.getInstance().referesh(this);
			}
		}
		
		private String getSchemaFilterKey() {
			return this.getName() + ConnectionFilter.FILTER_SEPARATOR
					+ ConnectionFilter.SCHEMA_FILTER;
		} 
}

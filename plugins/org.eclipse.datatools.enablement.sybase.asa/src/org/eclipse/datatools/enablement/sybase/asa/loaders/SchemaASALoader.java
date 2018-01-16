package org.eclipse.datatools.enablement.sybase.asa.loaders;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableLoader;
import org.eclipse.datatools.enablement.sybase.asa.baseloaders.SchemaASABaseLoader;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogIndex;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogSchema;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogTable;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogTempTable;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.tables.Table;

public class SchemaASALoader extends SchemaASABaseLoader{

	public SchemaASALoader(SybaseASACatalogSchema catalogSchema)
	{
		super(catalogSchema);
	}
	
	protected Index createCatalogIndex()
    {
        return new SybaseASACatalogIndex();
    }
	
	protected JDBCTableLoader createTableLoader() {
		return new ASATableLoader(catalogObj);
	}
	
	public static class ASATableLoader extends ASABaseTableLoader {

		public ASATableLoader(ICatalogObject catalogObject) {
			super(catalogObject);
		}

		protected void initTableFacotaries()
		{
			super.registerTableFactory(BASE_TABLE, new ASATableFactory());
			super.registerTableFactory(TABLE_VIEW, new ASABaseViewFactory());
			super.registerTableFactory(GLOBAL_TEMP_TABLE,
					new ASAGlobalTempTableFactory());
			super.registerTableFactory(PROXY_TABLE,
					new ASABaseProxyTableFactory());
		}
		
		public static class ASATableFactory extends TableFactory
		{
			protected Table newTable() {
				return new SybaseASACatalogTable();
			}
		}
		
		public static class ASAGlobalTempTableFactory extends TableFactory
		{
			protected Table newTable() {
				return new SybaseASACatalogTempTable();
			}
		}
	}
	
}

package org.eclipse.datatools.enablement.sybase.asa.loaders;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCSchemaLoader;
import org.eclipse.datatools.enablement.sybase.asa.baseloaders.SybaseASABaseDatabaseLoader;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogDatabase;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogSchema;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASADatabase;
import org.eclipse.datatools.modelbase.sql.schema.Schema;

public class SybaseASADatabaseLoader extends SybaseASABaseDatabaseLoader{

	public SybaseASADatabaseLoader(SybaseASACatalogDatabase catalogDatabase)
	{
		super(catalogDatabase);
	}
	
	protected void processDbInfo2ResutSet(ResultSet rs) throws SQLException {
		boolean isASECompatible = rs.getString(4).equalsIgnoreCase("Y");
		((SybaseASADatabase)database).setASECompatible(isASECompatible);
		super.processDbInfo2ResutSet(rs);
	}
	
    protected JDBCSchemaLoader createSchemaLoader() {
        return new ASASchemaLoader(super.catalogObject);
    }
    
    static public class ASASchemaLoader extends JDBCSchemaLoader {

        public ASASchemaLoader(ICatalogObject catalogObject) {
            super(catalogObject, null);
        }

        protected Schema createSchema() {
            return new SybaseASACatalogSchema();
        }
    }
	
}

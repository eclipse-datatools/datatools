package org.eclipse.datatools.enablement.msft.internal.sqlserver.loaders;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableColumnLoader;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.IdentitySpecifier;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.tables.Column;

public class SQL2005ColumnLoader extends JDBCTableColumnLoader {

	public SQL2005ColumnLoader() {
		super(null);
	}

	protected void initialize(Column column, ResultSet rs) throws SQLException {
		// Get the basic stuff right
		super.initialize(column, rs);
		
		String typeName = rs.getString(COLUMN_TYPE_NAME).toUpperCase();
		if (typeName.indexOf("IDENTITY") >= 0) {
			
			final Database database = this.getCatalogObject().getCatalogDatabase();
			final DatabaseDefinition databaseDefinition = RDBCorePlugin
					.getDefault().getDatabaseDefinitionRegistry()
					.getDefinition(database);
			final DataModelElementFactory factory = databaseDefinition
					.getDataModelElementFactory();
			IdentitySpecifier identitySpecifier = (IdentitySpecifier) factory
					.create(SQLSchemaPackage.eINSTANCE
							.getIdentitySpecifier());
			column.setIdentitySpecifier(identitySpecifier);
		}
	}
	
}

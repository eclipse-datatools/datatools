package org.eclipse.datatools.enablement.sybase.asa.catalog;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogProvider;
import org.eclipse.datatools.enablement.sybase.asa.JDBCASAPlugin;
import org.eclipse.datatools.modelbase.sql.schema.Database;

public class SybaseASACatalogProvider implements ICatalogProvider, IExecutableExtension {
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		this.product = config.getAttribute("product"); //$NON-NLS-1$
		this.version = config.getAttribute("version"); //$NON-NLS-1$
	}

	public Database getCatalogDatabase(Connection connection) {
		SybaseASACatalogDatabase database = new SybaseASACatalogDatabase(connection);
		retrieveRealVersion(connection);
		database.setVendor(this.product);
		database.setVersion(this.version);
		return database;
	}
	
	private String product;
	private String version;
	
	private void retrieveRealVersion(Connection connection)
	{
		try
		{
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT @@version");
			while(rs.next())
			{
				this.version = rs.getString(1);
				if(version.startsWith("10"))
				{
				    this.version = "10.x";
				}
                else
				{
                    //to handle more versions
					this.version = "9.x";
				}
			}
		}
		catch(SQLException e)
		{
			JDBCASAPlugin.getDefault().log(e);
		}
	}
}

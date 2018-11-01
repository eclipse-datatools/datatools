/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    linsong - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ase.catalog;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogProvider;
import org.eclipse.datatools.enablement.ase.JDBCASEPlugin;
import org.eclipse.datatools.modelbase.sql.schema.Database;

public class SybaseASECatalogProvider implements ICatalogProvider, IExecutableExtension {
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		this.product = config.getAttribute("product"); //$NON-NLS-1$
		this.version = config.getAttribute("version"); //$NON-NLS-1$
	}

	public Database getCatalogDatabase(Connection connection) {
		SybaseASECatalogDatabase database = new SybaseASECatalogDatabase(connection);
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
				if(version.startsWith("Adaptive Server Enterprise/15"))
				{
					this.version = "15.x";
				}
                else
				{
                    //to handle more versions
                	this.version = "12.x";
				}
			}
		}
		catch(SQLException e)
		{
			JDBCASEPlugin.getDefault().log(e);
		}
	}
}

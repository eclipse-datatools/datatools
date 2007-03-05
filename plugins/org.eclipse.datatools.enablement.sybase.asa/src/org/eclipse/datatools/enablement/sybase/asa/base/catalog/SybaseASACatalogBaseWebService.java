
package org.eclipse.datatools.enablement.sybase.asa.base.catalog;

import java.sql.Connection;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogDatabase;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASAWebServiceImpl;
import org.eclipse.datatools.modelbase.sql.schema.Database;


public class SybaseASACatalogBaseWebService extends SybaseASAWebServiceImpl implements ICatalogObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3257570602843189304L;

	public void refresh() {
		RefreshManager.getInstance().referesh(this);
	}

	public Connection getConnection() {
		Database database = this.getCatalogDatabase();
		return ((SybaseASACatalogDatabase) database).getConnection();
	}

	public Database getCatalogDatabase() {
		return this.getDatabase();
	}
	
}

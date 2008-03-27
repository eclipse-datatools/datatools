package org.eclipse.datatools.enablement.sybase.asa.internal.ui.connection;

import org.eclipse.datatools.connectivity.drivers.jdbc.JDBCPasswordPropertyPersistenceHook;

public class SybaseASADBPropertiesPersistenceHook extends
JDBCPasswordPropertyPersistenceHook {

	public String getConnectionPropertiesPageID() {
		return "org.eclipse.datatools.enablement.sybase.asa.profileProperties";
	}

}

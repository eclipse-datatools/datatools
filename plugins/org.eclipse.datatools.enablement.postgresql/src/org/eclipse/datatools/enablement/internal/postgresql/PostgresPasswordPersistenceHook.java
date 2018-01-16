package org.eclipse.datatools.enablement.internal.postgresql;

import org.eclipse.datatools.connectivity.drivers.jdbc.JDBCPasswordPropertyPersistenceHook;

public class PostgresPasswordPersistenceHook extends
		JDBCPasswordPropertyPersistenceHook {

	public String getConnectionPropertiesPageID() {
		return "org.eclipse.datatools.enablement.postgresql.profile.profileProperties";//$NON-NLS-1$
	}

}

/*******************************************************************************
 * Copyright (c) 2009 Sybase, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.mysql.catalog.loaders;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.datatools.connectivity.sqm.loader.JDBCRoutineLoader;
import org.eclipse.datatools.connectivity.sqm.loader.Messages;
import org.eclipse.datatools.modelbase.sql.schema.Schema;

import com.ibm.icu.text.MessageFormat;

public class MySqlRoutineLoader extends JDBCRoutineLoader {

	public MySqlRoutineLoader() {
		super(null, null);
	}
	
	protected ResultSet createResultSet() throws SQLException {
		try {
			Schema schema = getSchema();
			return getCatalogObject().getConnection().getMetaData()
					.getProcedures(schema.getDatabase().getName(),
							schema.getName(), getJDBCFilterPattern());
		}
		catch (RuntimeException e) {
			SQLException error = new SQLException(
					MessageFormat
							.format(
									Messages.Error_Unsupported_DatabaseMetaData_Method,
									new Object[] { "java.sql.DatabaseMetaData.getProcedures()"})); //$NON-NLS-1$
			error.initCause(e);
			throw error;
		}
	}
}

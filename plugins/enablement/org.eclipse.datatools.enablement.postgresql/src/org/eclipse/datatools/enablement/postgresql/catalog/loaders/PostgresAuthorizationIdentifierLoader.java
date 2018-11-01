/*******************************************************************************
 * Copyright (c) 2011 Zenika
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: queinnec - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.postgresql.catalog.loaders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.loader.IConnectionFilterProvider;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCBaseLoader;
import org.eclipse.datatools.connectivity.sqm.loader.SchemaObjectFilterProvider;
import org.eclipse.datatools.enablement.postgresql.model.impl.PostgresUserImpl;
import org.eclipse.datatools.modelbase.sql.accesscontrol.User;

/**
 * This class adds the ability to retrieve a list of authorization identifiers from a PostgreSQL database.
 * 
 * @author pierre.queinnec@zenika.com
 */
public class PostgresAuthorizationIdentifierLoader extends JDBCBaseLoader {

	private static final String USER_QUERY = "SELECT * FROM pg_user;"; //$NON-NLS-1$
	private static final String USER_NAME = "usename";

	public PostgresAuthorizationIdentifierLoader() {
		this(null);
	}

	public PostgresAuthorizationIdentifierLoader(ICatalogObject catalogObject) {
		this(catalogObject, new SchemaObjectFilterProvider(ConnectionFilter.SEQUENCE_FILTER));
	}

	/**
	 * @param catalogObject
	 * @param connectionFilterProvider
	 */
	public PostgresAuthorizationIdentifierLoader(ICatalogObject catalogObject,
			IConnectionFilterProvider connectionFilterProvider) {

		super(catalogObject, connectionFilterProvider);
	}

	public void clearAuthorizationIdentifiers(List existingAuthorizationIds) {
		existingAuthorizationIds.clear();
	}

	public void loadAuthorizationIdentifiers(List containmentList, List existingAuthorizationIds) throws SQLException {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			// initActiveFilter();

			stmt = getCatalogObject().getConnection().prepareStatement(USER_QUERY);
			rs = createResultSet(stmt);

			while (rs.next()) {
				String userName = rs.getString(USER_NAME);

				if (userName == null || isFiltered(userName)) {
					continue;
				}

				User user = (User) getAndRemoveSQLObject(existingAuthorizationIds, userName);

				if (user == null) {
					user = processRow(rs);
					if (user != null) {
						containmentList.add(user);
					}
				} else {
					containmentList.add(user);
					if (user instanceof ICatalogObject) {
						((ICatalogObject) user).refresh();
					}
				}
			}
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

			} catch (SQLException e) {
				// ignored

			} finally {
				try {
					if (stmt != null) {
						stmt.close();
					}
				} catch (SQLException e) {
					// ignored
				}
			}
		}
	}

	protected User processRow(ResultSet rs) throws SQLException {
		// User user = SQLAccessControlFactory.eINSTANCE.createUser();
		User user = new PostgresUserImpl();
		String userName = rs.getString(USER_NAME).trim();
		user.setName(userName);
		user.setLabel(userName);

		return user;
	}

	protected ResultSet createResultSet(PreparedStatement stmt) throws SQLException {
		try {
			return stmt.executeQuery();

		} catch (RuntimeException e) {
			SQLException error = new SQLException(
					"Error while retrieving database information (authorization identifiers)");
			error.initCause(e);
			throw error;
		}
	}

}

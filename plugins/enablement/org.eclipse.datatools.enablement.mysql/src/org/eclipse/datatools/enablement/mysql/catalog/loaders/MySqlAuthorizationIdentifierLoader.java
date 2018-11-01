/*******************************************************************************
 * Copyright (c) 2012 Zenika
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: queinnec - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.mysql.catalog.loaders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.loader.IConnectionFilterProvider;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCBaseLoader;
import org.eclipse.datatools.connectivity.sqm.loader.SchemaObjectFilterProvider;
import org.eclipse.datatools.enablement.mysql.model.impl.MySqlUserImpl;
import org.eclipse.datatools.modelbase.sql.accesscontrol.User;

/**
 * This class adds the ability to retrieve a list of authorization identifiers from a MySQL database.
 * 
 * @author pierre.queinnec@zenika.com
 */
public class MySqlAuthorizationIdentifierLoader extends JDBCBaseLoader {

	private static final String USER_QUERY = "SELECT * FROM mysql.user;"; //$NON-NLS-1$
	private static final String USER_NAME = "User";
	private static final String HOST_NAME = "Host";

	public MySqlAuthorizationIdentifierLoader() {
		this(null);
	}

	public MySqlAuthorizationIdentifierLoader(ICatalogObject catalogObject) {
		this(catalogObject, new SchemaObjectFilterProvider(ConnectionFilter.SEQUENCE_FILTER));
	}

	/**
	 * @param catalogObject
	 * @param connectionFilterProvider
	 */
	public MySqlAuthorizationIdentifierLoader(ICatalogObject catalogObject,
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
				String userDisplayName = this.toDisplayLabel(rs);

				if (userDisplayName == null || isFiltered(userDisplayName)) {
					continue;
				}

				User user = (User) getAndRemoveSQLObject(existingAuthorizationIds, userDisplayName);

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

	protected String toDisplayLabel(ResultSet rs) throws SQLException {
		String userName = rs.getString(USER_NAME).trim();
		String hostName = rs.getString(HOST_NAME).trim();
		String displayName = userName + " [" + hostName + ']';

		return displayName;
	}

	protected User processRow(ResultSet rs) throws SQLException {
		User user = new MySqlUserImpl();
		String displayName = this.toDisplayLabel(rs);

		// user.setName(userName);
		user.setName(displayName);
		user.setLabel(displayName);

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

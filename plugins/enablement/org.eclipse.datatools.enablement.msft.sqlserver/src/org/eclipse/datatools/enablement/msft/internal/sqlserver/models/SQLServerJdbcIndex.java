/*******************************************************************************
 * Copyright (c) 2008 NexB Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Anton Safonov and Ahti Kitsik - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.msft.internal.sqlserver.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCIndex;
import org.eclipse.datatools.enablement.msft.internal.sqlserver.loaders.SQLCatalogUtils;
import org.eclipse.datatools.enablement.msft.internal.sqlserver.loaders.SQLs;

public class SQLServerJdbcIndex extends JDBCIndex {

	private Boolean loadedExtendedIndexInfo = Boolean.FALSE;

	private boolean padIndex;
	private boolean statisticsNoRecompute;
	private boolean ignoreDupKey;
	private boolean allowRowLocks;
	private boolean allowPageLocks;
	private boolean uniqueConstraint;
	private boolean primaryKey;

	public boolean isPadIndex() {
		synchronized (loadedExtendedIndexInfo) {
			if (!loadedExtendedIndexInfo.booleanValue()) {
				loadExtendedIndexInfo();
			}
		}

		return padIndex;
	}

	public boolean isStatisticsNoRecompute() {
		synchronized (loadedExtendedIndexInfo) {
			if (!loadedExtendedIndexInfo.booleanValue()) {
				loadExtendedIndexInfo();
			}
		}
		return statisticsNoRecompute;
	}

	public boolean isIgnoreDupKey() {
		synchronized (loadedExtendedIndexInfo) {
			if (!loadedExtendedIndexInfo.booleanValue()) {
				loadExtendedIndexInfo();
			}
		}
		return ignoreDupKey;
	}

	public boolean isAllowRowLocks() {
		synchronized (loadedExtendedIndexInfo) {
			if (!loadedExtendedIndexInfo.booleanValue()) {
				loadExtendedIndexInfo();
			}
		}
		return allowRowLocks;
	}

	public boolean isAllowPageLocks() {
		synchronized (loadedExtendedIndexInfo) {
			if (!loadedExtendedIndexInfo.booleanValue()) {
				loadExtendedIndexInfo();
			}
		}
		return allowPageLocks;
	}

	public boolean isUniqueConstraint() {
		synchronized (loadedExtendedIndexInfo) {
			if (!loadedExtendedIndexInfo.booleanValue()) {
				loadExtendedIndexInfo();
			}
		}
		return uniqueConstraint;
	}

	public boolean isPrimaryKey() {
		synchronized (loadedExtendedIndexInfo) {
			if (!loadedExtendedIndexInfo.booleanValue()) {
				loadExtendedIndexInfo();
			}
		}
		return primaryKey;
	}

	synchronized private void loadExtendedIndexInfo() {
		if (!(this.getTable() instanceof SQLServerJdbcTable))
			return;

		Connection conn = this.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String oldCatalog = null;
		try {
			oldCatalog = conn.getCatalog();
			conn.setCatalog(this.getTable().getSchema().getCatalog().getName());
			stmt = conn.prepareStatement(SQLs.INDEX_INFO_QUERY);
			stmt.setInt(1, ((SQLServerJdbcTable) this.getTable()).getId());
			stmt.setString(2, this.getName());
			rs = stmt.executeQuery();
			while (rs.next()) {
				padIndex = rs.getBoolean(1);
				statisticsNoRecompute = rs.getBoolean(2);
				ignoreDupKey = rs.getBoolean(3);
				allowRowLocks = rs.getBoolean(4);
				allowPageLocks = rs.getBoolean(5);
				uniqueConstraint = rs.getBoolean(6);
				primaryKey = rs.getBoolean(7);
			}
		} catch (SQLException e) {
			// TODO: log properly, e.g. JDBCASEPlugin.getDefault().log(e);
			e.printStackTrace();
		} finally {
			SQLCatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
		}

		this.loadedExtendedIndexInfo = Boolean.TRUE;
	}

}

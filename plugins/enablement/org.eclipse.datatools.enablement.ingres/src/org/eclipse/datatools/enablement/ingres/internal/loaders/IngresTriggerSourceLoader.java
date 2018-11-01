/*******************************************************************************
 * Copyright (c) 2008 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.loaders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.loader.IConnectionFilterProvider;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCBaseLoader;
import org.eclipse.datatools.modelbase.sql.routines.Source;

/**
 * Class for loading the source of a trigger from an Ingres database.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresTriggerSourceLoader extends JDBCBaseLoader {

	private static final String TRIGGER_SOURCE_QUERY = "SELECT DISTINCT rule_name, rule_owner, system_use, table_name, text_sequence, text_segment FROM iirules WHERE table_name = ? AND rule_owner = ? AND rule_name = ? ORDER BY text_sequence";

	private static final String TRIGGER_SOURCE_SEGMENT = "text_segment";

	public IngresTriggerSourceLoader() {
		this(null);
	}

	public IngresTriggerSourceLoader(ICatalogObject catalogObject) {
		this(catalogObject, null);
	}

	public IngresTriggerSourceLoader(ICatalogObject catalogObject,
			IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
	}

	/**
	 * Loads the source for a rule and stores the result in the
	 * given source object.
	 * 
	 * @param tableName table
	 * @param ruleOwner schema
	 * @param ruleName rule
	 * @param source result
	 */
	public void loadSource(String tableName, String ruleOwner, String ruleName,
			Source source) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			stmt = getCatalogObject().getConnection().prepareStatement(
					TRIGGER_SOURCE_QUERY);
			stmt.setString(1, tableName);
			stmt.setString(2, ruleOwner);
			stmt.setString(3, ruleName);
			rs = stmt.executeQuery();

			StringBuffer sourceBuf = new StringBuffer();
			while (rs.next()) {
				sourceBuf.append(rs.getString(TRIGGER_SOURCE_SEGMENT));
			}

			source.setBody(sourceBuf.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			LoaderHelper.close(rs);
			LoaderHelper.close(stmt);
		}
	}

}

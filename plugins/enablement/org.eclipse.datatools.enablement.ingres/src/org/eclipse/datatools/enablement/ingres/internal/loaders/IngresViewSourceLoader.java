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
 * Class for loading the source of a view from an Ingres database.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresViewSourceLoader extends JDBCBaseLoader {

	private static final String VIEW_SOURCE_QUERY = "SELECT text_segment FROM iiviews WHERE table_name=? AND table_owner=? ORDER BY text_sequence";

	private static final String VIEW_SOURCE_SEGMENT = "text_segment";

	public IngresViewSourceLoader() {
		this(null);
	}

	public IngresViewSourceLoader(ICatalogObject catalogObject) {
		this(catalogObject, null);
	}

	public IngresViewSourceLoader(ICatalogObject catalogObject,
			IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
	}

	/**
	 * Loads the source for a view and stores the result in the
	 * given source object.
	 * 
	 * @param viewOwner schema
	 * @param viewName view
	 * @param source result object
	 */
	public void loadSource(String viewOwner, String viewName, Source source) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			stmt = getCatalogObject().getConnection().prepareStatement(
					VIEW_SOURCE_QUERY);
			stmt.setString(1, viewName);
			stmt.setString(2, viewOwner);
			rs = stmt.executeQuery();

			StringBuffer sourceBuf = new StringBuffer();
			while (rs.next()) {
				sourceBuf.append(rs.getString(VIEW_SOURCE_SEGMENT));
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

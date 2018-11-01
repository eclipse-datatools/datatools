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
 * Class for loading the source of a procedure from an Ingres database.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresProcedureSourceLoader extends JDBCBaseLoader {

	private static final String PROCEDURE_SOURCE_QUERY = "SELECT text_segment FROM iiprocedures WHERE procedure_name=? AND procedure_owner=? ORDER BY text_sequence";

	private static final String PROCEDURE_SOURCE_SEGMENT = "text_segment";

	public IngresProcedureSourceLoader() {
		this(null);
	}

	public IngresProcedureSourceLoader(ICatalogObject catalogObject) {
		this(catalogObject, null);
	}

	public IngresProcedureSourceLoader(ICatalogObject catalogObject,
			IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
	}

	/**
	 * Loads the source for a procedure and stores the result in the
	 * given source object.
	 * 
	 * @param procedureOwner schema
	 * @param procedureName procedure
	 * @param source result
	 */
	public void loadSource(String procedureOwner, String procedureName,
			Source source) {
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			stmt = getCatalogObject().getConnection().prepareStatement(
					PROCEDURE_SOURCE_QUERY);
			stmt.setString(1, procedureName);
			stmt.setString(2, procedureOwner);
			rs = stmt.executeQuery();

			StringBuffer sourceBuf = new StringBuffer();
			while (rs.next()) {
				sourceBuf.append(rs.getString(PROCEDURE_SOURCE_SEGMENT));
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

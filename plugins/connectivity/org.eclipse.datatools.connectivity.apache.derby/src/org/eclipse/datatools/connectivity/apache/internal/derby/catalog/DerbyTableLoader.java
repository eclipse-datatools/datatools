/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.apache.internal.derby.catalog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.loader.IConnectionFilterProvider;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableLoader;
import org.eclipse.datatools.connectivity.sqm.loader.SchemaObjectFilterProvider;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Table;

public class DerbyTableLoader extends JDBCTableLoader {

	public static final String DERBY_TYPE_SYNONYM = "A"; //$NON-NLS-1$
	public static final String DERBY_TYPE_TABLE = "T"; //$NON-NLS-1$
	public static final String DERBY_TYPE_VIEW = "V"; //$NON-NLS-1$
	public static final String DERBY_TYPE_SYSTEM = "S"; //$NON-NLS-1$

	private String currentSchema;

	public DerbyTableLoader() {
		super(null);
	}
	
	/**
	 * Constructor
	 * @param catalogObject
	 */
	public DerbyTableLoader(ICatalogObject catalogObject) {
		this(catalogObject, new SchemaObjectFilterProvider(
				ConnectionFilter.TABLE_FILTER));
	}

	/**
	 * Constructor
	 * @param catalogObject
	 * @param connectionFilterProvider
	 */
	public DerbyTableLoader(ICatalogObject catalogObject,
			IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
		unregisterTableFactory(TYPE_TABLE);
		unregisterTableFactory(TYPE_VIEW);
		unregisterTableFactory(TYPE_SYSTEM_TABLE);
		unregisterTableFactory(TYPE_GLOBAL_TEMPORARY);
		unregisterTableFactory(TYPE_LOCAL_TEMPORARY);
		
		registerTableFactory(DERBY_TYPE_TABLE, new DerbyTableFactory());
		registerTableFactory(DERBY_TYPE_VIEW, new DerbyViewFactory());
		registerTableFactory(DERBY_TYPE_SYSTEM, new DerbyTableFactory());
		registerTableFactory(DERBY_TYPE_SYNONYM, new SynonymTableFactory());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.sqm.loader.JDBCTableLoader#createResultSet()
	 */
	protected ResultSet createResultSet() throws SQLException {
		Schema schema = (Schema) getCatalogObject();
		String query ="SELECT TABLENAME AS TABLE_NAME, TABLETYPE AS TABLE_TYPE FROM " + //$NON-NLS-1$
			"SYS.SYSTABLES A,SYS.SYSSCHEMAS B WHERE A.SCHEMAID=B.SCHEMAID AND B.SCHEMANAME='" + schema.getName() + //$NON-NLS-1$ 
			"' AND ( TABLETYPE='A' OR TABLETYPE='T' OR TABLETYPE='S' OR TABLETYPE='V' )"; //$NON-NLS-1$
		if (getSQLFilterExpression() != null && getSQLFilterExpression().length() > 0) {
			String filter = " AND TABLENAME " + getSQLFilterExpression();
			query = query + filter;
		}
		Statement s = getCatalogObject().getConnection().createStatement();
		currentSchema = DerbySchemaLoader.setSchema(s, "SYS");
		ResultSet r = s.executeQuery(query);
		return r;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.sqm.loader.JDBCTableLoader#closeResultSet(java.sql.ResultSet)
	 */
	protected void closeResultSet(ResultSet rs) {
		Statement s = null;
		try {
			s = rs.getStatement();
		} catch (SQLException e) {
		}
		
		super.closeResultSet(rs);
		
		try {
			DerbySchemaLoader.setSchema(s, currentSchema);
		} catch (SQLException e) {
		}
	}

	/**
	 * Table factory for Synonyms
	 * @author brianf
	 *
	 */
	public static class SynonymTableFactory extends TableFactory {

		/* (non-Javadoc)
		 * @see org.eclipse.datatools.connectivity.sqm.loader.JDBCTableLoader.TableFactory#newTable()
		 */
		protected Table newTable() {
			return new DerbyCatalogSynonym();
		}
	}

	public static class DerbyTableFactory extends TableFactory {
		protected Table newTable() {
			return new DerbyCatalogTable();
		}
	}

	public static class DerbyViewFactory extends ViewFactory {
		protected Table newTable() {
			return new DerbyCatalogView();
		}
		
	}
}

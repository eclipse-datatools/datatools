/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.loader;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.jdbc.JDBCColumn;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.ReferenceDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Base loader implementation for loading a database's catalog objects. This
 * class may be specialized as necessary to meet a particular vendor's needs.
 * 
 * @author rcernich
 * 
 * Created on Aug 28, 2006
 */
public class JDBCTableColumnLoader extends JDBCBaseLoader {

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getColumns()
	 */
	public static final String COLUMN_COLUMN_NAME = "COLUMN_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getColumns()
	 */
	public static final String COLUMN_DATA_TYPE = "DATA_TYPE"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getColumns()
	 */
	public static final String COLUMN_TYPE_NAME = "TYPE_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getColumns()
	 */
	public static final String COLUMN_COLUMN_SIZE = "COLUMN_SIZE"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getColumns()
	 */
	public static final String COLUMN_DECIMAL_DIGITS = "DECIMAL_DIGITS"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getColumns()
	 */
	public static final String COLUMN_NULLABLE = "NULLABLE"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getColumns()
	 */
	public static final String COLUMN_REMARKS = "REMARKS"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getColumns()
	 */
	public static final String COLUMN_COLUMN_DEF = "COLUMN_DEF"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getColumns()
	 */
	public static final String COLUMN_SCOPE_CATALOG = "SCOPE_CATALOG"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getColumns()
	 */
	public static final String COLUMN_SCOPE_SCHEMA = "SCOPE_SCHEMA"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getColumns()
	 */
	public static final String COLUMN_SCOPE_TABLE = "SCOPE_TABLE"; //$NON-NLS-1$

	private Pattern mUDTNameMatcherPattern;
	private boolean mCatalogAtStart;

	/**
	 * @param catalogObject the Database object upon which this loader operates.
	 */
	public JDBCTableColumnLoader(ICatalogObject catalogObject) {
		this(catalogObject, null);
	}

	public JDBCTableColumnLoader(
									ICatalogObject catalogObject,
									IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
		assert (catalogObject instanceof Table);
	}

	/**
	 * @param existingCatalogs the catalog objects which were previously loaded
	 * @return
	 * @throws SQLException
	 */
	public List loadColumns() throws SQLException {
		List retVal = new ArrayList();
		ResultSet rs = null;
		try {
			initActiveFilter();
			for (rs = createResultSet(); rs.next();) {
				Column column = processRow(rs);
				if (column != null) {
					retVal.add(column);
				}
			}
			return retVal;
		}
		finally {
			if (rs != null) {
				closeResultSet(rs);
			}
		}
	}

	public void clearColumns(List columns) {
		columns.clear();
	}

	protected ResultSet createResultSet() throws SQLException {
		Table table = getTable();
		Schema schema = table.getSchema();
		return getCatalogObject().getConnection().getMetaData().getColumns(
				schema.getCatalog().getName(), schema.getName(),
				table.getName(), null);
	}

	protected void closeResultSet(ResultSet rs) {
		try {
			rs.close();
		}
		catch (SQLException e) {
		}
	}

	protected Column processRow(ResultSet rs) throws SQLException {
		String columnName = rs.getString(COLUMN_COLUMN_NAME);
		if (columnName == null || isFiltered(columnName)) {
			return null;
		}
		Column column = createColumn();
		initialize(column, rs);
		return column;
	}

	protected Column createColumn() {
		return new JDBCColumn();
	}

	protected void initialize(Column column, ResultSet rs) throws SQLException {
		column.setName(rs.getString(COLUMN_COLUMN_NAME));
		column.setDescription(rs.getString(COLUMN_REMARKS));
		column.setDefaultValue(rs.getString(COLUMN_COLUMN_DEF));

		initColumnType(column, rs);

		column
				.setNullable(rs.getInt(COLUMN_NULLABLE) == DatabaseMetaData.attributeNullable);
	}

	protected void initColumnType(Column column, ResultSet rs)
			throws SQLException {
		// db definition types are always upper case: make sure the typeName is
		// upper too
		String typeName = rs.getString(COLUMN_TYPE_NAME).toUpperCase();
		int typeCode = rs.getInt(COLUMN_DATA_TYPE);

		// See if it's a predefined type
		List pdtds = getDatabaseDefinition()
				.getPredefinedDataTypeDefinitionsByJDBCEnumType(typeCode);
		if (pdtds.size() > 0) {
			PredefinedDataTypeDefinition pdtd = null;
			for (Iterator it = pdtds.iterator(); pdtd == null && it.hasNext();) {
				PredefinedDataTypeDefinition curPDTD = (PredefinedDataTypeDefinition) it
						.next();
				for (Iterator nameIt = curPDTD.getName().iterator(); nameIt
						.hasNext();) {
					String name = (String) nameIt.next();
					if (typeName.equals(name)) {
						pdtd = curPDTD;
						break;
					}
				}
			}

			if (pdtd == null) {
				// See if we can find one for the named type
				pdtd = getDatabaseDefinition().getPredefinedDataTypeDefinition(
						typeName);
			}

			if (pdtd != null
					|| (pdtd == null && typeCode != Types.OTHER && typeCode != Types.REF)) {
				if (pdtd == null) {
					// If we still couldn't find it, use the first element
					// that maps to the JDBC type
					pdtd = (PredefinedDataTypeDefinition) pdtds.get(0);
				}

				PredefinedDataType pdt = getDatabaseDefinition()
						.getPredefinedDataType(pdtd);
				if (pdtd.isLengthSupported()) {
					EStructuralFeature feature = pdt.eClass()
							.getEStructuralFeature("length"); //$NON-NLS-1$
					pdt.eSet(feature,
							new Integer(rs.getInt(COLUMN_COLUMN_SIZE)));
				}
				if (pdtd.isPrecisionSupported()) {
					EStructuralFeature feature = pdt.eClass()
							.getEStructuralFeature("precision"); //$NON-NLS-1$
					pdt.eSet(feature,
							new Integer(rs.getInt(COLUMN_COLUMN_SIZE)));
				}
				if (pdtd.isScaleSupported()) {
					EStructuralFeature feature = pdt.eClass()
							.getEStructuralFeature("scale"); //$NON-NLS-1$
					pdt.eSet(feature, new Integer(rs
							.getInt(COLUMN_DECIMAL_DIGITS)));
				}
				column.setDataType(pdt);
				return;
			}
		}

		// Couldn't find predefined type.  Try looking for a ref or udt.
		if (typeName == null)
			return;

		// see if we can locate a UDT
		if (Types.REF == typeCode) {
			ReferenceDataType ref = createReferenceDataType();
			if (ref == null) {
				// TODO: Add some logging maybe?
				return;
			}
			UserDefinedType udt = findUserDefinedType(typeName);
			Table table = findScopedTable(rs.getString(COLUMN_SCOPE_CATALOG),
					rs.getString(COLUMN_SCOPE_SCHEMA), rs
							.getString(COLUMN_SCOPE_TABLE));
			initReferenceDataType(ref, udt, table);
			column.setDataType(ref);
		}
		else {
			UserDefinedType udt = findUserDefinedType(typeName);
			column.setDataType(udt);
		}
	}

	protected ReferenceDataType createReferenceDataType() {
		return null;
	}

	protected void initReferenceDataType(ReferenceDataType ref,
			UserDefinedType udt, Table scopeTable) {
		if (udt instanceof StructuredUserDefinedType) {
			ref.setReferencedType((StructuredUserDefinedType) udt);
		}
		else {
			// TODO: Add some logging?
		}
		ref.setScopeTable(scopeTable);
	}

	protected Table getTable() {
		return (Table) getCatalogObject();
	}

	protected DatabaseDefinition getDatabaseDefinition() {
		return RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry()
				.getDefinition(getCatalogObject().getCatalogDatabase());
	}

	protected Matcher getUDTNameMatcher(String name) throws SQLException {
		if (mUDTNameMatcherPattern == null) {
			// pattern match
			// catalog at start
			// <catalog><catalog separator><schema name><unknown separator><udt
			// name>
			// <schema name><unknown separator><udt name><catalog
			// separator><catalog>
			DatabaseMetaData dbmd = getCatalogObject().getConnection()
					.getMetaData();
			String namePattern = "([a-zA-Z0-9_" + dbmd.getExtraNameCharacters()
					+ "]*)";
			String catalogSeparator = dbmd.getCatalogSeparator();
			String regex = namePattern + "." + namePattern;
			if (name.indexOf(catalogSeparator) < 0) {
				regex = regex + "()";
			}
			else if (dbmd.isCatalogAtStart()) {
				mCatalogAtStart = true;
				regex = namePattern + catalogSeparator + regex;
			}
			else {
				regex = regex + catalogSeparator + namePattern;
			}
			mUDTNameMatcherPattern = Pattern.compile(regex);
		}
		return mUDTNameMatcherPattern.matcher(name);
	}

	protected UserDefinedType findUserDefinedType(String typeName) {
		Matcher matcher;
		try {
			matcher = getUDTNameMatcher(typeName);
		}
		catch (SQLException e) {
			return null;
		}

		if (matcher.matches()) {
			String catalogName;
			String schemaName;
			String udtName;
			if (mCatalogAtStart) {
				catalogName = matcher.group(1);
				schemaName = matcher.group(2);
				udtName = matcher.group(3);
			}
			else {
				schemaName = matcher.group(1);
				udtName = matcher.group(2);
				catalogName = matcher.group(3);
			}
			if (udtName == null) {
				return null;
			}
			if (catalogName == null) {
				catalogName = new String();
			}
			if (schemaName == null) {
				schemaName = new String();
			}

			Database db = getCatalogObject().getCatalogDatabase();
			for (Iterator catIt = db.getCatalogs().iterator(); catIt.hasNext();) {
				Catalog catalog = (Catalog) catIt.next();
				if (catalogName.equals(catalog.getName())) {
					for (Iterator schemIt = catalog.getSchemas().iterator(); schemIt
							.hasNext();) {
						Schema schema = (Schema) schemIt.next();
						if (schemaName.equals(schema.getName())) {
							for (Iterator udtIt = schema.getUserDefinedTypes()
									.iterator(); udtIt.hasNext();) {
								UserDefinedType udt = (UserDefinedType) udtIt
										.next();
								if (udtName.equals(udt.getName())) {
									// found it
									return udt;
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

	protected Table findScopedTable(String catalogScope, String schemaScope,
			String tableScope) {
		if (tableScope == null) {
			return null;
		}
		if (catalogScope == null) {
			catalogScope = getTable().getSchema().getCatalog().getName();
			try {
				if (getCatalogObject().getConnection().getMetaData()
						.supportsCatalogsInTableDefinitions()) {
					catalogScope = new String();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (schemaScope == null) {
			schemaScope = getTable().getSchema().getName();
			try {
				if (getCatalogObject().getConnection().getMetaData()
						.supportsSchemasInTableDefinitions()) {
					schemaScope = new String();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}

		Database db = getCatalogObject().getCatalogDatabase();
		for (Iterator catIt = db.getCatalogs().iterator(); catIt.hasNext();) {
			Catalog catalog = (Catalog) catIt.next();
			if (catalogScope.equals(catalog.getName())) {
				for (Iterator schemIt = catalog.getSchemas().iterator(); schemIt
						.hasNext();) {
					Schema schema = (Schema) schemIt.next();
					if (schemaScope.equals(schema.getName())) {
						for (Iterator tableIt = schema.getTables().iterator(); tableIt
								.hasNext();) {
							Table table = (Table) tableIt.next();
							if (tableScope.equals(table.getName())) {
								// found it
								return table;
							}
						}
					}
				}
			}
		}
		return null;
	}

}

/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: rcernich - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.loader;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCColumn;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.ReferenceDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.TimeDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.ibm.icu.text.MessageFormat;

/**
 * Base loader implementation for loading a table's column objects. This class
 * may be specialized as necessary to meet a particular vendor's needs.
 * 
 * @since 1.0
 */
public class JDBCTableColumnLoader extends JDBCBaseLoader {

	/**
	 * The column name containing the column's name.
	 * 
	 * @see java.sql.DatabaseMetaData.getColumns()
	 */
	public static final String COLUMN_COLUMN_NAME = "COLUMN_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the column's data type.
	 * 
	 * @see java.sql.DatabaseMetaData.getColumns()
	 */
	public static final String COLUMN_DATA_TYPE = "DATA_TYPE"; //$NON-NLS-1$

	/**
	 * The column name containing the column's type name.
	 * 
	 * @see java.sql.DatabaseMetaData.getColumns()
	 */
	public static final String COLUMN_TYPE_NAME = "TYPE_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the column's size.
	 * 
	 * @see java.sql.DatabaseMetaData.getColumns()
	 */
	public static final String COLUMN_COLUMN_SIZE = "COLUMN_SIZE"; //$NON-NLS-1$

	/**
	 * The column name containing the column's decimal digits.
	 * 
	 * @see java.sql.DatabaseMetaData.getColumns()
	 */
	public static final String COLUMN_DECIMAL_DIGITS = "DECIMAL_DIGITS"; //$NON-NLS-1$

	/**
	 * The column name containing the column's nullable attribute.
	 * 
	 * @see java.sql.DatabaseMetaData.getColumns()
	 */
	public static final String COLUMN_NULLABLE = "NULLABLE"; //$NON-NLS-1$

	/**
	 * The column name containing the column's description.
	 * 
	 * @see java.sql.DatabaseMetaData.getColumns()
	 */
	public static final String COLUMN_REMARKS = "REMARKS"; //$NON-NLS-1$

	/**
	 * The column name containing the column's default value.
	 * 
	 * @see java.sql.DatabaseMetaData.getColumns()
	 */
	public static final String COLUMN_COLUMN_DEF = "COLUMN_DEF"; //$NON-NLS-1$

	/**
	 * The column name containing the catalog name of the column's UDT reference
	 * type.
	 * 
	 * @see java.sql.DatabaseMetaData.getColumns()
	 */
	public static final String COLUMN_SCOPE_CATALOG = "SCOPE_CATALOG"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name of the column's UDT reference
	 * type.
	 * 
	 * @see java.sql.DatabaseMetaData.getColumns()
	 */
	public static final String COLUMN_SCOPE_SCHEMA = "SCOPE_SCHEMA"; //$NON-NLS-1$

	/**
	 * The column name containing the table name of the column's UDT reference
	 * type.
	 * 
	 * @see java.sql.DatabaseMetaData.getColumns()
	 */
	public static final String COLUMN_SCOPE_TABLE = "SCOPE_TABLE"; //$NON-NLS-1$

	private Pattern mUDTNameMatcherPattern;
	private boolean mCatalogAtStart;

	/**
	 * This constructs the loader using no filtering.
	 * 
	 * @param catalogObject the Table object upon which this loader operates.
	 */
	public JDBCTableColumnLoader(ICatalogObject catalogObject) {
		this(catalogObject, null);
	}

	/**
	 * @param catalogObject the Table object upon which this loader operates.
	 * @param connectionFilterProvider the filter provider used for filtering
	 *        the "column" objects being loaded
	 */
	public JDBCTableColumnLoader(
									ICatalogObject catalogObject,
									IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
		if (catalogObject != null)
			assert (catalogObject instanceof Table);
	}

	/**
	 * @return a collection of Column objects
	 * 
	 * @throws SQLException if an error occurred during loading.
	 * 
	 * @deprecated see {@link #loadColumns(List, Collection)}
	 */
	public List loadColumns() throws SQLException {
		List retVal = new ArrayList();
		loadColumns(retVal, Collections.EMPTY_SET);
		return retVal;
	}

	/**
	 * Loads the "column" objects from the database. This method uses the result
	 * set from createResultSet() to load the "column" objects from the server.
	 * This method first checks the name of the "column" to determine whether or
	 * not it should be filtered. If it is not filtered, it checks to see if an
	 * object with that name was loaded previously. If it finds an existing
	 * object, it refreshes that object and adds it to the containment list. If
	 * the named object does not exist, the result set is passed to
	 * processRow(). Column objects are created using the factory method,
	 * createColumn() and initialized through the initialize() method.
	 * 
	 * This method should only be overridden as a last resort when the desired
	 * behavior cannot be acheived by overriding createResultSet(),
	 * closeResultSet(), processRow(), createColumn() and initialize().
	 * 
	 * @param containmentList the containment list held by parent
	 * @param existingColumns the catalog objects which were previously loaded
	 * 
	 * @throws SQLException if an error occurred during loading.
	 */
	public void loadColumns(List containmentList, Collection existingColumns)
			throws SQLException {
		ResultSet rs = null;
		try {
			initActiveFilter();
			for (rs = createResultSet(); rs.next();) {
				String columnName = rs.getString(COLUMN_COLUMN_NAME);
				if (columnName == null || isFiltered(columnName)) {
					continue;
				}
				Column column = (Column) getAndRemoveSQLObject(existingColumns,
						columnName);
				if (column == null) {
					column = processRow(rs);
					if (column != null) {
						containmentList.add(column);
					}
				}
				else {
					initialize(column, rs);
					containmentList.add(column);
					if (column instanceof ICatalogObject) {
						((ICatalogObject) column).refresh();
					}
				}
			}
		}
		finally {
			if (rs != null) {
				closeResultSet(rs);
			}
		}
	}

	/**
	 * Removes the specified schema from the model.
	 * 
	 * @param schemas the schemas to be removed from the model.
	 */
	public void clearColumns(List columns) {
		columns.clear();
	}

	/**
	 * Creates a result set to be used by the loading logic. The default version
	 * uses of the JDBC DatabaseMetaData.getColumns() to create the result set.
	 * This method may be overridden to use a vendor specific query. However,
	 * the default logic requires the columns named by the "COLUMN_*" fields.
	 * Keep this in mind if you plan to reuse the default logic (e.g.
	 * initialize())
	 * 
	 * @return a result containing the information used to initialize Column
	 *         objects
	 * 
	 * @throws SQLException if an error occurs
	 */
	protected ResultSet createResultSet() throws SQLException {
		try {
			Table table = getTable();
			Schema schema = table.getSchema();
			return getCatalogObject().getConnection().getMetaData().getColumns(
					schema.getCatalog().getName(), schema.getName(),
					table.getName(), null);
		}
		catch (RuntimeException e) {
			SQLException error = new SQLException(MessageFormat.format(
					Messages.Error_Unsupported_DatabaseMetaData_Method,
					new Object[] { "java.sql.DatabaseMetaData.getColumns()"})); //$NON-NLS-1$
			error.initCause(e);
			throw error;
		}
	}

	/**
	 * Closes the result set used for catalog object loading. This method is
	 * implemented as rs.close(). However, if you used a Statement object to
	 * create the result set, this is where you would close that Statement.
	 * 
	 * @param rs the result set to close. This will be the result set created by
	 *        createResultSet().
	 */
	protected void closeResultSet(ResultSet rs) {
		try {
			rs.close();
		}
		catch (SQLException e) {
		}
	}

	/**
	 * Processes a single row in the result set. By default, this method invokes
	 * createColumn() followed by initialize(), finally returning the newly
	 * created, initialized Column object.
	 * 
	 * @param rs the result set
	 * @return a new Column object
	 * @throws SQLException if anything goes wrong
	 */
	protected Column processRow(ResultSet rs) throws SQLException {
		Column column = createColumn();
		initialize(column, rs);
		return column;
	}

	/**
	 * Returns a new Column object. By default, this method returns a new
	 * JDBCColumn.
	 * 
	 * @return a new Column object.
	 */
	protected Column createColumn() {
		return new JDBCColumn();
	}

	/**
	 * Used to initialize a newly created Column object. By default, this method
	 * initializes the name, description, default value, data type and nullable
	 * attribute of the Column. This method may be overridden to initialize any
	 * vendor specific properties.
	 * 
	 * @param column a newly created Column object
	 * @param rs the result set containing the information
	 * @throws SQLException if anything goes wrong
	 */
	protected void initialize(Column column, ResultSet rs) throws SQLException {
		column.setName(rs.getString(COLUMN_COLUMN_NAME));
		column.setDescription(rs.getString(COLUMN_REMARKS));
		column.setDefaultValue(rs.getString(COLUMN_COLUMN_DEF));

		initColumnType(column, rs);

		column
				.setNullable(rs.getInt(COLUMN_NULLABLE) == DatabaseMetaData.attributeNullable);
	}

	/**
	 * Initializes the type of the Column object. This method will resolve any
	 * dependencies necessary depending on whether the object is typed as a user
	 * defined type or predefined data type.
	 * 
	 * @param column a Column
	 * @param rs the result set containing the information
	 * @throws SQLException if anything goes wrong
	 */
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
					EStructuralFeature feature = null;
				    if (pdt instanceof TimeDataType) {
				        feature = pdt.eClass().getEStructuralFeature("fractionalSecondsPrecision"); //$NON-NLS-1$
				    }
				    else {
				        feature = pdt.eClass().getEStructuralFeature("precision"); //$NON-NLS-1$
				     }
				     if (feature != null) {
				          pdt.eSet(feature, new Integer(rs.getInt(COLUMN_COLUMN_SIZE)));                                        
				     }
				}
				if (pdtd.isScaleSupported()) {
					EStructuralFeature feature = pdt.eClass()
							.getEStructuralFeature("scale"); //$NON-NLS-1$
					if (feature != null) { // MISSING IF STATEMENT
						pdt.eSet(feature, new Integer(rs
							.getInt(COLUMN_DECIMAL_DIGITS)));
					}
				}
				column.setDataType(pdt);
				return;
			}
		}

		// Couldn't find predefined type. Try looking for a ref or udt.
		if (typeName == null) {
			column.setDataType(null);
			return;
		}

		// see if we can locate a UDT
		if (Types.REF == typeCode) {
			ReferenceDataType ref = createReferenceDataType();
			if (ref == null) {
				// TODO: Add some logging maybe?
				column.setDataType(null);
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

	/**
	 * Creates a new ReferenceDataType. Default implementation returns null.
	 * 
	 * @return a new ReferenceDataType
	 */
	protected ReferenceDataType createReferenceDataType() {
		return null;
	}

	/**
	 * Initializes a new ReferenceDataType. Associates the specified UDT with
	 * the new reference type.
	 * 
	 * @param ref a new reference data type
	 * @param udt a structured user defined type
	 * @param scopeTable the table to which the reference is scoped
	 */
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

	/**
	 * Utility method.
	 * 
	 * @return returns the catalog object being operated upon as a Table (i.e.
	 *         (Table) getCatalogObject()).
	 */
	protected Table getTable() {
		return (Table) getCatalogObject();
	}

	/**
	 * Utility method. Retrieves the DatabaseDefinition that applies to the
	 * catalog object.
	 * 
	 * @return the DatabaseDefinition for the catalog object
	 */
	protected DatabaseDefinition getDatabaseDefinition() {
		return RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry()
				.getDefinition(getCatalogObject().getCatalogDatabase());
	}

	/**
	 * Utility method. This method is used to create a Matcher that will be used
	 * for finding a referenced UDT. The Matcher accounts for the naming scheme
	 * used by the database (e.g. whether the catalog name is placed at the
	 * beginning or end of a fully qualified object name).
	 * 
	 * @param name the UDT name
	 * @return a Matcher
	 * @throws SQLException if anything goes wrong
	 */
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

	/**
	 * Used by initType() to resolve a UDT.
	 * 
	 * @param typeName the UDT name being searched for
	 * @return the UDT, if found; null otherwise.
	 */
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

	/**
	 * Resloves the table scoped to a referenced UDT.
	 * 
	 * @param catalogScope catalog containing the scoped table
	 * @param schemaScope schema containing the scoped table
	 * @param tableScope the scoped table's name
	 * @return the scoped table; null if it couldn't be found
	 */
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

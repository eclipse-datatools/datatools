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
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.datatypes.AttributeDefinition;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.ReferenceDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesFactory;
import org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.ibm.icu.text.MessageFormat;

/**
 * Base loader implementation for loading a UDT's attribute objects. This class
 * may be specialized as necessary to meet a particular vendor's needs.
 * 
 * @since 1.0
 */
public class JDBCUDTAttributeLoader extends JDBCBaseLoader {

	/**
	 * The column name containing the attribute's name.
	 * 
	 * @see java.sql.DatabaseMetaData.getAttributes()
	 */
	public static final String COLUMN_ATTR_NAME = "ATTR_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the attribute's data type.
	 * 
	 * @see java.sql.DatabaseMetaData.getAttributes()
	 */
	public static final String COLUMN_DATA_TYPE = "DATA_TYPE"; //$NON-NLS-1$

	/**
	 * The column name containing the attribute's type name.
	 * 
	 * @see java.sql.DatabaseMetaData.getAttributes()
	 */
	public static final String COLUMN_ATTR_TYPE_NAME = "ATTR_TYPE_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the attribute's size.
	 * 
	 * @see java.sql.DatabaseMetaData.getAttributes()
	 */
	public static final String COLUMN_ATTR_SIZE = "ATTR_SIZE"; //$NON-NLS-1$

	/**
	 * The column name containing the attribute's decimal digits.
	 * 
	 * @see java.sql.DatabaseMetaData.getAttributes()
	 */
	public static final String COLUMN_DECIMAL_DIGITS = "DECIMAL_DIGITS"; //$NON-NLS-1$

	/**
	 * The column name containing the attribute's nullable.
	 * 
	 * @see java.sql.DatabaseMetaData.getAttributes()
	 */
	public static final String COLUMN_NULLABLE = "NULLABLE"; //$NON-NLS-1$

	/**
	 * The column name containing the attribute's description.
	 * 
	 * @see java.sql.DatabaseMetaData.getAttributes()
	 */
	public static final String COLUMN_REMARKS = "REMARKS"; //$NON-NLS-1$

	/**
	 * The column name containing the attribute's default value.
	 * 
	 * @see java.sql.DatabaseMetaData.getAttributes()
	 */
	public static final String COLUMN_ATTR_DEF = "ATTR_DEF"; //$NON-NLS-1$

	/**
	 * The column name containing the attribute's scope catalog.
	 * 
	 * @see java.sql.DatabaseMetaData.getAttributes()
	 */
	public static final String COLUMN_SCOPE_CATALOG = "SCOPE_CATALOG"; //$NON-NLS-1$

	/**
	 * The column name containing the attribute's scope schema.
	 * 
	 * @see java.sql.DatabaseMetaData.getAttributes()
	 */
	public static final String COLUMN_SCOPE_SCHEMA = "SCOPE_SCHEMA"; //$NON-NLS-1$

	/**
	 * The column name containing the attribute's scope table.
	 * 
	 * @see java.sql.DatabaseMetaData.getAttributes()
	 */
	public static final String COLUMN_SCOPE_TABLE = "SCOPE_TABLE"; //$NON-NLS-1$

	private Pattern mUDTNameMatcherPattern;
	private boolean mCatalogAtStart;

	/**
	 * This constructs the loader using no filtering.
	 * 
	 * @param catalogObject the UDT object upon which this loader operates.
	 */
	public JDBCUDTAttributeLoader(ICatalogObject catalogObject) {
		this(catalogObject, null);
	}

	/**
	 * @param catalogObject the UDT object upon which this loader operates.
	 * @param connectionFilterProvider the filter provider used for filtering
	 *        the "column" objects being loaded
	 */
	public JDBCUDTAttributeLoader(
									ICatalogObject catalogObject,
									IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
		if (catalogObject != null)
			assert (catalogObject instanceof UserDefinedType);
	}

	/**
	 * Loads the "attribute" objects from the database. This method uses the
	 * result set from createResultSet() to load the "attribute" objects from
	 * the server. Row handling for the result set is delegated to processRow().
	 * AttributeDefinition objects are created using the factory method,
	 * createAttributeDefinition().
	 * 
	 * This method should only be overridden as a last resort when the desired
	 * behavior cannot be acheived by overriding createResultSet(),
	 * closeResultSet(), processRow(), createAttributeDefinition() and
	 * initialize().
	 * 
	 * @return a collection of AttributeDefinition objects
	 * 
	 * @throws SQLException if an error occurred during loading.
	 */
	public List loadAttributeDefinitions() throws SQLException {
		List retVal = new ArrayList();
		ResultSet rs = null;
		try {
			initActiveFilter();
			for (rs = createResultSet(); rs.next();) {
				AttributeDefinition attrDef = processRow(rs);
				if (attrDef != null) {
					retVal.add(attrDef);
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

	/**
	 * Removes the specified attribute definitions from the model.
	 * 
	 * @param attrDefs the attribute definitions to be removed from the model.
	 */
	public void clearAttributeDefinitions(List attrDefs) {
		attrDefs.clear();
	}

	/**
	 * Creates a result set to be used by the loading logic. The default version
	 * uses of the JDBC DatabaseMetaData.getAttributes() to create the result
	 * set. This method may be overridden to use a vendor specific query.
	 * However, the default logic requires the columns named by the "COLUMN_*"
	 * fields. Keep this in mind if you plan to reuse the default logic (e.g.
	 * initialize())
	 * 
	 * @return a result containing the information used to initialize
	 *         AttributeDefinition objects
	 * 
	 * @throws SQLException if an error occurs
	 */
	protected ResultSet createResultSet() throws SQLException {
		try {
			UserDefinedType udt = getUserDefinedType();
			Schema schema = udt.getSchema();
			return getCatalogObject().getConnection().getMetaData().getAttributes(
					schema.getCatalog().getName(), schema.getName(), udt.getName(),
					"%");
		}
		catch (RuntimeException e) {
			SQLException error = new SQLException(
					MessageFormat
							.format(
									Messages.Error_Unsupported_DatabaseMetaData_Method,
									new Object[] { "java.sql.DatabaseMetaData.getAttributes()"})); //$NON-NLS-1$
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
	 * Processes a single row in the result set. By default, this method
	 * determines whether or not the named attribute definition is filtered,
	 * invokes createAttributeDefinition() followed by initialize(), finally
	 * returning the newly created, initialized AttributeDefinition object.
	 * 
	 * @param rs the result set
	 * @return a new AttributeDefinition object
	 * @throws SQLException if anything goes wrong
	 */
	protected AttributeDefinition processRow(ResultSet rs) throws SQLException {
		String attrDefName = rs.getString(COLUMN_ATTR_NAME);
		if (attrDefName == null || isFiltered(attrDefName)) {
			return null;
		}

		AttributeDefinition attrDef = createAttributeDefinition();
		initialize(attrDef, rs);

		return attrDef;
	}

	/**
	 * Returns a new AttributeDefinition object. By default, this method returns
	 * a new AttributeDefinitionImpl.
	 * 
	 * @return a new AttributeDefinition object.
	 */
	protected AttributeDefinition createAttributeDefinition() {
		return SQLDataTypesFactory.eINSTANCE.createAttributeDefinition();
	}

	/**
	 * Used to initialize a newly created AttributeDefinition object. By
	 * default, this method initializes the name, description, default value,
	 * data type and nullable attribute of the Column. This method may be
	 * overridden to initialize any vendor specific properties.
	 * 
	 * @param attrDef a newly created Column object
	 * @param rs the result set containing the information
	 * @throws SQLException if anything goes wrong
	 */
	protected void initialize(AttributeDefinition attrDef, ResultSet rs)
			throws SQLException {
		attrDef.setName(rs.getString(COLUMN_ATTR_NAME));
		attrDef.setDescription(rs.getString(COLUMN_REMARKS));
		attrDef.setDefaultValue(rs.getString(COLUMN_ATTR_DEF));

		initAttributeDefinitionType(attrDef, rs);
	}

	/**
	 * Initializes the type of the AttributeDefinition object. This method will
	 * resolve any dependencies necessary depending on whether the object is
	 * typed as a user defined type or predefined data type.
	 * 
	 * @param attrDef a AttributeDefinition
	 * @param rs the result set containing the information
	 * @throws SQLException if anything goes wrong
	 */
	protected void initAttributeDefinitionType(AttributeDefinition attrDef,
			ResultSet rs) throws SQLException {
		// db definition types are always upper case: make sure the typeName is
		// upper too
		String typeName = rs.getString(COLUMN_ATTR_TYPE_NAME).toUpperCase();
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
					// Use the first element by default
					pdtd = (PredefinedDataTypeDefinition) pdtds.get(0);
				}

				PredefinedDataType pdt = getDatabaseDefinition()
						.getPredefinedDataType(pdtd);
				if (pdtd.isLengthSupported()) {
					EStructuralFeature feature = pdt.eClass()
							.getEStructuralFeature("length"); //$NON-NLS-1$
					pdt.eSet(feature, new Integer(rs.getInt(COLUMN_ATTR_SIZE)));
				}
				if (pdtd.isPrecisionSupported()) {
					EStructuralFeature feature = pdt.eClass()
							.getEStructuralFeature("precision"); //$NON-NLS-1$
					pdt.eSet(feature, new Integer(rs.getInt(COLUMN_ATTR_SIZE)));
				}
				if (pdtd.isScaleSupported()) {
					EStructuralFeature feature = pdt.eClass()
							.getEStructuralFeature("scale"); //$NON-NLS-1$
					pdt.eSet(feature, new Integer(rs
							.getInt(COLUMN_DECIMAL_DIGITS)));
				}
				attrDef.setDataType(pdt);
				return;
			}
		}

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
			attrDef.setDataType(ref);
		}
		else {
			UserDefinedType udt = findUserDefinedType(typeName);
			attrDef.setDataType(udt);
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
	 * @return returns the catalog object being operated upon as a
	 *         UserDefinedType (i.e. (UserDefinedType) getCatalogObject()).
	 */
	protected UserDefinedType getUserDefinedType() {
		return (UserDefinedType) getCatalogObject();
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
			catalogScope = new String();
		}
		if (schemaScope == null) {
			schemaScope = new String();
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

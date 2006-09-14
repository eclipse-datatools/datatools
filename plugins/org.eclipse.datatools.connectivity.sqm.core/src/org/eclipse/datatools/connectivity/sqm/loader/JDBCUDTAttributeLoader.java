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

/**
 * Base loader implementation for loading a database's catalog objects. This
 * class may be specialized as necessary to meet a particular vendor's needs.
 * 
 * @author rcernich
 * 
 * Created on Aug 28, 2006
 */
public class JDBCUDTAttributeLoader extends JDBCBaseLoader {

	/**
	 * The column name containing the catalog name.
	 * 
	 * @see java.sql.DatabaseMetaData.getColumns()
	 */
	public static final String COLUMN_TYPE_CAT = "TYPE_CAT"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getColumns()
	 */
	public static final String COLUMN_TYPE_SCHEM = "TYPE_SCHEM"; //$NON-NLS-1$

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
	public static final String COLUMN_ATTR_NAME = "ATTR_NAME"; //$NON-NLS-1$

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
	public static final String COLUMN_ATTR_TYPE_NAME = "ATTR_TYPE_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getColumns()
	 */
	public static final String COLUMN_ATTR_SIZE = "ATTR_SIZE"; //$NON-NLS-1$

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
	public static final String COLUMN_NUM_PREC_RADIX = "NUM_PREC_RADIX"; //$NON-NLS-1$

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
	public static final String COLUMN_ATTR_DEF = "ATTR_DEF"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getColumns()
	 */
	public static final String COLUMN_CHAR_OCTET_LENGTH = "CHAR_OCTET_LENGTH"; //$NON-NLS-1$

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getColumns()
	 */
	public static final String COLUMN_ORDINAL_POSITION = "ORDINAL_POSITION"; //$NON-NLS-1$

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

	/**
	 * The column name containing the schema name.
	 * 
	 * @see java.sql.DatabaseMetaData.getColumns()
	 */
	public static final String COLUMN_SOURCE_DATA_TYPE = "SOURCE_DATA_TYPE"; //$NON-NLS-1$

	private Pattern mUDTNameMatcherPattern;
	private boolean mCatalogAtStart;

	/**
	 * @param catalogObject the Database object upon which this loader operates.
	 */
	public JDBCUDTAttributeLoader(ICatalogObject catalogObject) {
		this(catalogObject, null);
	}

	public JDBCUDTAttributeLoader(
									ICatalogObject catalogObject,
									IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
		assert (catalogObject instanceof UserDefinedType);
	}

	/**
	 * @param existingCatalogs the catalog objects which were previously loaded
	 * @return
	 * @throws SQLException
	 */
	public List loadAttributeDefinitions(List existingAttributeDefinitions)
			throws SQLException {
		List retVal = new ArrayList(existingAttributeDefinitions.size());
		ResultSet rs = null;
		try {
			for (rs = createResultSet(); rs.next();) {
				AttributeDefinition attrDef = processRow(rs,
						existingAttributeDefinitions);
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
			clearAttributeDefinitions(existingAttributeDefinitions);
		}
	}

	protected void clearAttributeDefinitions(List attrDefs) {
		attrDefs.clear();
	}

	protected ResultSet createResultSet() throws SQLException {
		UserDefinedType udt = getUserDefinedType();
		Schema schema = udt.getSchema();
		return getCatalogObject().getConnection().getMetaData().getAttributes(
				schema.getCatalog().getName(), schema.getName(), udt.getName(),
				"%");
	}

	protected void closeResultSet(ResultSet rs) {
		try {
			rs.close();
		}
		catch (SQLException e) {
		}
	}

	protected AttributeDefinition processRow(ResultSet rs,
			List existingAttributeDefinitions) throws SQLException {
		String attrDefName = rs.getString(COLUMN_ATTR_NAME);
		if (attrDefName == null || isFiltered(attrDefName)) {
			return null;
		}
		AttributeDefinition attrDef = null;
		for (Iterator it = existingAttributeDefinitions.iterator(); attrDef != null
				&& it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof AttributeDefinition
					&& attrDefName
							.equals(((AttributeDefinition) obj).getName())) {
				attrDef = (AttributeDefinition) obj;
			}
		}
		if (attrDef == null) {
			attrDef = createAttributeDefinition();
		}
		initialize(attrDef, rs);
		return attrDef;
	}

	protected AttributeDefinition createAttributeDefinition() {
		return SQLDataTypesFactory.eINSTANCE.createAttributeDefinition();
	}

	protected void initialize(AttributeDefinition attrDef, ResultSet rs)
			throws SQLException {
		attrDef.setName(rs.getString(COLUMN_ATTR_NAME));
		attrDef.setDescription(rs.getString(COLUMN_REMARKS));
		attrDef.setDefaultValue(rs.getString(COLUMN_ATTR_DEF));

		initAttributeDefinitionType(attrDef, rs);
	}

	protected void initAttributeDefinitionType(AttributeDefinition attrDef,
			ResultSet rs) throws SQLException {
		String typeName = rs.getString(COLUMN_TYPE_NAME);
		int typeCode = rs.getInt(COLUMN_DATA_TYPE);
		PredefinedDataType pdt = null;

		// See if it's a predefined type
		List pdts = getDatabaseDefinition()
				.getPredefinedDataTypeDefinitionsByJDBCEnumType(typeCode);
		if (typeName == null && pdts.size() > 0) {
			pdt = (PredefinedDataType) pdts.get(0);
		}
		else {
			for (Iterator it = pdts.iterator(); pdt == null && it.hasNext();) {
				PredefinedDataType curPDT = (PredefinedDataType) it.next();
				if (typeName.equals(curPDT.getName())) {
					pdt = curPDT;
				}
			}
		}
		if (pdt == null) {
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
				Table table = findScopedTable(rs
						.getString(COLUMN_SCOPE_CATALOG), rs
						.getString(COLUMN_SCOPE_SCHEMA), rs
						.getString(COLUMN_SCOPE_TABLE));
				initReferenceDataType(ref, udt, table);
				attrDef.setDataType(ref);
			}
			else {
				UserDefinedType udt = findUserDefinedType(typeName);
				attrDef.setDataType(udt);
			}
		}
		else {
			PredefinedDataTypeDefinition pdtd = getDatabaseDefinition()
					.getPredefinedDataTypeDefinition(pdt.getName());
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
				pdt
						.eSet(feature, new Integer(rs
								.getInt(COLUMN_DECIMAL_DIGITS)));
			}
			attrDef.setDataType(pdt);
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

	protected UserDefinedType getUserDefinedType() {
		return (UserDefinedType) getCatalogObject();
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

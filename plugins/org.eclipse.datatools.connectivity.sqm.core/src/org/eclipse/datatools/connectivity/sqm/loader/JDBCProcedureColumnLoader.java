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

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.routines.Parameter;
import org.eclipse.datatools.modelbase.sql.routines.ParameterMode;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.RoutineResultTable;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesFactory;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.TypedElement;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesFactory;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Base loader implementation for loading a SP's parameter objects. This class
 * may be specialized as necessary to meet a particular vendor's needs.
 * 
 * @since 1.0
 */
public class JDBCProcedureColumnLoader extends JDBCBaseLoader {

	/**
	 * The column name containing the column's name.
	 * 
	 * @see java.sql.DatabaseMetaData.getProcedureColumns()
	 */
	public static final String COLUMN_COLUMN_NAME = "COLUMN_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the column's type.
	 * 
	 * @see java.sql.DatabaseMetaData.getProcedureColumns()
	 */
	public static final String COLUMN_COLUMN_TYPE = "COLUMN_TYPE"; //$NON-NLS-1$

	/**
	 * The column name containing the column's data type.
	 * 
	 * @see java.sql.DatabaseMetaData.getProcedureColumns()
	 */
	public static final String COLUMN_DATA_TYPE = "DATA_TYPE"; //$NON-NLS-1$

	/**
	 * The column name containing the column's type name.
	 * 
	 * @see java.sql.DatabaseMetaData.getProcedureColumns()
	 */
	public static final String COLUMN_TYPE_NAME = "TYPE_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the column's precision.
	 * 
	 * @see java.sql.DatabaseMetaData.getProcedureColumns()
	 */
	public static final String COLUMN_PRECISION = "PRECISION"; //$NON-NLS-1$

	/**
	 * The column name containing the column's length.
	 * 
	 * @see java.sql.DatabaseMetaData.getProcedureColumns()
	 */
	public static final String COLUMN_LENGTH = "LENGTH"; //$NON-NLS-1$

	/**
	 * The column name containing the column's scale.
	 * 
	 * @see java.sql.DatabaseMetaData.getProcedureColumns()
	 */
	public static final String COLUMN_SCALE = "SCALE"; //$NON-NLS-1$

	/**
	 * The column name containing the column's nullable attribute.
	 * 
	 * @see java.sql.DatabaseMetaData.getProcedureColumns()
	 */
	public static final String COLUMN_NULLABLE = "NULLABLE"; //$NON-NLS-1$

	/**
	 * The column name containing the column's remarks.
	 * 
	 * @see java.sql.DatabaseMetaData.getProcedureColumns()
	 */
	public static final String COLUMN_REMARKS = "REMARKS"; //$NON-NLS-1$

	private Pattern mUDTNameMatcherPattern;
	private boolean mCatalogAtStart;

	/**
	 * This constructs the loader using no filtering.
	 * 
	 * @param catalogObject the Procedure object upon which this loader
	 *        operates.
	 */
	public JDBCProcedureColumnLoader(ICatalogObject catalogObject) {
		this(catalogObject, null);
	}

	/**
	 * @param catalogObject the Procedure object upon which this loader
	 *        operates.
	 * @param connectionFilterProvider the filter provider used for filtering
	 *        the "column" objects being loaded
	 */
	public JDBCProcedureColumnLoader(
										ICatalogObject catalogObject,
										IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
		assert (catalogObject instanceof Procedure);
	}

	/**
	 * Loads the "parameter" objects for the SP. This method uses the result set
	 * from createResultSet() to load the "parameter" objects from the server.
	 * Row handling for the result set is delegated to processRow(). Parameter
	 * objects are created using the factory method, createParameter(). If a
	 * result set is returned by the SP, createRoutineResultTable() is used to
	 * create the container object, while createColumn() is used to create the
	 * columns in the result table.
	 * 
	 * This method should only be overridden as a last resort when the desired
	 * behavior cannot be acheived by overriding other methods.
	 * 
	 * @return List of Parameter objects. If a result set is created by this
	 *         routine, the last element in the list will be a
	 *         RoutineResultTable object
	 * 
	 * @throws SQLException if anything goes wrong
	 */
	public List loadColumns() throws SQLException {
		List retVal = new ArrayList();
		ResultSet rs = null;
		try {
			initActiveFilter();
			RoutineResultTable rst = null;
			for (rs = createResultSet(); rs.next();) {
				TypedElement element = processRow(rs);
				if (element instanceof Column) {
					if (rst == null) {
						rst = createRoutineResultTable();
					}
					rst.getColumns().add(element);
				}
				else {
					retVal.add(element);
				}
			}
			if (rst != null) {
				retVal.add(rst);
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
	 * @param columns removes the parameters.
	 */
	public void clearColumns(List columns) {
		columns.clear();
	}

	/**
	 * Creates a result set to be used by the loading logic. The default version
	 * uses of the JDBC DatabaseMetaData.getProcedureColumns() to create the
	 * result set. This method may be overridden to use a vendor specific query.
	 * However, the default logic requires columns named according to the
	 * "COLUMN_*" fields. Keep this in mind if you plan to reuse the default
	 * logic (e.g. initialize())
	 * 
	 * @return a result containing the information used to initialize Parameter
	 *         objects
	 * 
	 * @throws SQLException if anything goes wrong
	 */
	protected ResultSet createResultSet() throws SQLException {
		Procedure procedure = getProcedure();
		Schema schema = procedure.getSchema();
		return getCatalogObject().getConnection().getMetaData()
				.getProcedureColumns(schema.getCatalog().getName(),
						schema.getName(), procedure.getName(), null);
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
	 * determines whether or not the named object is filtered. If not, it
	 * invokes either createParameter() or createColumn() (depending on whether
	 * or not the object represents a parameter or procedure result column),
	 * followed by initParameter() or initColumn(), finally returning the newly
	 * created, initialized Parameter or Column object.
	 * 
	 * @param rs the result set
	 * @return a new Parameter or Column object
	 * @throws SQLException if anything goes wrong
	 */
	protected TypedElement processRow(ResultSet rs) throws SQLException {
		String columnName = rs.getString(COLUMN_COLUMN_NAME);
		if (columnName == null || isFiltered(columnName)) {
			return null;
		}
		TypedElement retVal = null;
		if (rs.getShort(COLUMN_COLUMN_TYPE) == DatabaseMetaData.procedureColumnResult) {
			retVal = createColumn();
			initColumn((Column) retVal, rs);
		}
		else {
			retVal = createParameter();
			initParameter((Parameter) retVal, rs);
		}
		return retVal;
	}

	/**
	 * @return a new Parameter object. Default is ParameterImpl.
	 */
	protected Parameter createParameter() {
		return SQLRoutinesFactory.eINSTANCE.createParameter();
	}

	/**
	 * @return a new Column object. Default is ColumnImpl.
	 */
	protected Column createColumn() {
		return SQLTablesFactory.eINSTANCE.createColumn();
	}

	/**
	 * @return a new RoutineResultTable object. Default is
	 *         RoutineResultTableImpl.
	 */
	protected RoutineResultTable createRoutineResultTable() {
		return SQLRoutinesFactory.eINSTANCE.createRoutineResultTable();
	}

	/**
	 * Used to initialize a newly created parameter object. By default, this
	 * method initializes the name, description, type and direction
	 * (in/out/inout). This method may be overridden to initialize any vendor
	 * specific properties.
	 * 
	 * @param parameter a newly created Parameter object
	 * @param rs the result set containing the information
	 * @throws SQLException if anything goes wrong
	 */
	protected void initParameter(Parameter parameter, ResultSet rs)
			throws SQLException {
		parameter.setName(rs.getString(COLUMN_COLUMN_NAME));
		parameter.setDescription(rs.getString(COLUMN_REMARKS));
		switch (rs.getShort(COLUMN_COLUMN_TYPE)) {
		case DatabaseMetaData.procedureColumnIn:
			parameter.setMode(ParameterMode.IN_LITERAL);
			break;
		case DatabaseMetaData.procedureColumnInOut:
			parameter.setMode(ParameterMode.INOUT_LITERAL);
			break;
		case DatabaseMetaData.procedureColumnOut:
			parameter.setMode(ParameterMode.OUT_LITERAL);
			break;
		case DatabaseMetaData.procedureColumnReturn: // Shouldn't happen
			// TODO: log error
		case DatabaseMetaData.procedureColumnUnknown:
		default:
			// Default to in
			parameter.setMode(ParameterMode.IN_LITERAL);
			break;
		}
		initType(parameter, rs);
	}

	/**
	 * Used to initialize a newly created column object. By default, this method
	 * initializes the name, description, type and nullable. This method may be
	 * overridden to initialize any vendor specific properties.
	 * 
	 * @param column a newly created Column object
	 * @param rs the result set containing the information
	 * @throws SQLException if anything goes wrong
	 */
	protected void initColumn(Column column, ResultSet rs) throws SQLException {
		column.setName(rs.getString(COLUMN_COLUMN_NAME));
		column.setDescription(rs.getString(COLUMN_REMARKS));

		initType(column, rs);

		column
				.setNullable(rs.getInt(COLUMN_NULLABLE) == DatabaseMetaData.attributeNullable);
	}

	/**
	 * Initializes the type of the Parameter or Column object. This method will
	 * resolve any dependencies necessary depending on whether the object is
	 * typed as a user defined type or predefined data type.
	 * 
	 * @param element a Parameter or Column
	 * @param rs the result set containing the information
	 * @throws SQLException if anything goes wrong
	 */
	protected void initType(TypedElement element, ResultSet rs)
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
					// Use the first element by default
					pdtd = (PredefinedDataTypeDefinition) pdtds.get(0);
				}

				PredefinedDataType pdt = getDatabaseDefinition()
						.getPredefinedDataType(pdtd);
				if (pdtd.isLengthSupported()) {
					EStructuralFeature feature = pdt.eClass()
							.getEStructuralFeature("length"); //$NON-NLS-1$
					pdt.eSet(feature, new Integer(rs.getInt(COLUMN_LENGTH)));
				}
				if (pdtd.isPrecisionSupported()) {
					EStructuralFeature feature = pdt.eClass()
							.getEStructuralFeature("precision"); //$NON-NLS-1$
					pdt.eSet(feature, new Integer(rs.getInt(COLUMN_PRECISION)));
				}
				if (pdtd.isScaleSupported()) {
					EStructuralFeature feature = pdt.eClass()
							.getEStructuralFeature("scale"); //$NON-NLS-1$
					pdt.eSet(feature, new Integer(rs.getInt(COLUMN_SCALE)));
				}
				element.setDataType(pdt);
				return;
			}
		}

		if (typeName == null)
			return;

		// see if we can locate a UDT
		UserDefinedType udt = findUserDefinedType(typeName);
		element.setDataType(udt);
	}

	/**
	 * Utility method.
	 * 
	 * @return returns the catalog object being operated upon as a Procedure
	 *         (i.e. (Procedure) getCatalogObject()).
	 */
	protected Procedure getProcedure() {
		return (Procedure) getCatalogObject();
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

}

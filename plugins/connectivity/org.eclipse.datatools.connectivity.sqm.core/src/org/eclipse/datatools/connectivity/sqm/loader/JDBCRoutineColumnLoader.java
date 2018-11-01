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
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCColumn;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCParameter;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.routines.Parameter;
import org.eclipse.datatools.modelbase.sql.routines.ParameterMode;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.routines.RoutineResultTable;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesFactory;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.TypedElement;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.ibm.icu.text.MessageFormat;

/**
 * Base loader implementation for loading a SP's parameter objects. This class
 * may be specialized as necessary to meet a particular vendor's needs.
 * 
 * @since 1.0
 */
public class JDBCRoutineColumnLoader extends JDBCBaseLoader {

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
	public JDBCRoutineColumnLoader(ICatalogObject catalogObject) {
		this(catalogObject, null);
	}

	/**
	 * @param catalogObject the Procedure object upon which this loader
	 *        operates.
	 * @param connectionFilterProvider the filter provider used for filtering
	 *        the "column" objects being loaded
	 */
	public JDBCRoutineColumnLoader(
										ICatalogObject catalogObject,
										IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
		if (catalogObject != null)
			assert (catalogObject instanceof Routine);
	}

	/**
	 * Loads the "parameter" objects for the SP. This method uses the result set
	 * from createParametersResultSet() to load the "parameter" objects from the server.
	 * Row handling for the result set is delegated to processRow(). Parameter
	 * objects are created using the factory method, createParameter().
	 * 
	 * This method should only be overridden as a last resort when the desired
	 * behavior cannot be acheived by overriding other methods.
	 * 
	 * @return List of Parameter objects.
	 * 
	 * @throws SQLException if anything goes wrong
	 */
	public void loadParameters(List parameterContainmentList,
			Collection existingParameters) throws SQLException {
		ResultSet rs = null;
		try {
			initActiveFilter();
			for (rs = createParametersResultSet(); rs.next();) {
				if (!isParameter(rs)) {
					continue;
				}
				String parameterName = rs.getString(COLUMN_COLUMN_NAME);
				if (parameterName == null || isFiltered(parameterName)) {
					continue;
				}
				Parameter parameter = (Parameter) getAndRemoveSQLObject(
						existingParameters, parameterName);
				if (parameter == null) {
					parameter = processParameterColumnRow(rs);
					if (parameter != null) {
						parameterContainmentList.add(parameter);
					}
				}
				else {
					initParameter(parameter, rs);
					parameterContainmentList.add(parameter);
					if (parameter instanceof ICatalogObject) {
						((ICatalogObject) parameter).refresh();
					}
				}
			}
		}
		finally {
			if (rs != null) {
				closeParametersResultSet(rs);
			}
		}
	}

	/**
	 * Loads the "routine result table" objects for the SP. This method uses the
	 * result set from createRoutineResultTableResultSet() to load the "routine result table"
	 * objects from the server. Row handling for the result set is delegated to
	 * processRoutineResultTableColumnRow().
	 * 
	 * This method should only be overridden as a last resort when the desired
	 * behavior cannot be acheived by overriding other methods.
	 * 
	 * @return List of Parameter objects.
	 * 
	 * @throws SQLException if anything goes wrong
	 */
	public List loadRoutineResultTables() throws SQLException {
		ResultSet rs = null;
		try {
			List retVal = new ArrayList();
			initActiveFilter();
			for (rs = createRoutineResultTableResultSet(); rs.next();) {
				if (isParameter(rs)) {
					continue;
				}
				int resultTableIndex = getRoutineResultTableIndex(rs);
				if (resultTableIndex >= retVal.size()) {
					while (retVal.size() <= resultTableIndex) {
						retVal.add(null);
					}
				}
				
				RoutineResultTable rst = (RoutineResultTable) retVal
						.get(resultTableIndex);
				if (rst == null) {
					rst = createRoutineResultTable();
					initRoutineResultTable(rst, rs);
					retVal.set(resultTableIndex, rst);
				}
				Column column = processRoutineResultTableColumnRow(rs);
				if (column != null) {
					rst.getColumns().add(column);
				}
			}
			return retVal;
		}
		finally {
			if (rs != null) {
				closeRoutineResultTableResultSet(rs);
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
	 * Creates a result set containing the procedure parameters which will be
	 * used by the loading logic. The default version uses of the JDBC
	 * DatabaseMetaData.getProcedureColumns() to create the result set. This
	 * method may be overridden to use a vendor specific query. However, the
	 * default logic requires columns named according to the "COLUMN_*" fields.
	 * Keep this in mind if you plan to reuse the default logic (e.g.
	 * initialize())
	 * 
	 * @return a result containing the information used to initialize Parameter
	 *         objects
	 * 
	 * @throws SQLException if anything goes wrong
	 */
	protected ResultSet createParametersResultSet() throws SQLException {
		try {
			Routine routine = getRoutine();
			Schema schema = routine.getSchema();
			return getCatalogObject().getConnection().getMetaData()
					.getProcedureColumns(schema.getCatalog().getName(),
							schema.getName(), routine.getName(), null);
		}
		catch (RuntimeException e) {
			SQLException error = new SQLException(
					MessageFormat
							.format(
									Messages.Error_Unsupported_DatabaseMetaData_Method,
									new Object[] { "java.sql.DatabaseMetaData.getProcedureColumns()"})); //$NON-NLS-1$
			error.initCause(e);
			throw error;
		}
	}

	/**
	 * Creates a result set containing result table columns which will be used
	 * by the loading logic. The default version uses of the JDBC
	 * DatabaseMetaData.getProcedureColumns() to create the result set. This
	 * method may be overridden to use a vendor specific query. However, the
	 * default logic requires columns named according to the "COLUMN_*" fields.
	 * Keep this in mind if you plan to reuse the default logic (e.g.
	 * initialize())
	 * 
	 * @return a result containing the information used to initialize Parameter
	 *         objects
	 * 
	 * @throws SQLException if anything goes wrong
	 */
	protected ResultSet createRoutineResultTableResultSet() throws SQLException {
		return createParametersResultSet();
	}

	/**
	 * Closes the result set used for catalog object loading. This method is
	 * implemented as rs.close(). However, if you used a Statement object to
	 * create the result set, this is where you would close that Statement.
	 * 
	 * @param rs the result set to close. This will be the result set created by
	 *        createResultSet().
	 */
	protected void closeParametersResultSet(ResultSet rs) {
		try {
			rs.close();
		}
		catch (SQLException e) {
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
	protected void closeRoutineResultTableResultSet(ResultSet rs) {
		try {
			rs.close();
		}
		catch (SQLException e) {
		}
	}

	/**
	 * Processes a single row in the result set. This method invokes
	 * createParameter(), followed by initParameter(), finally returning the
	 * newly created, initialized Parameter object.
	 * 
	 * @param rs the result set
	 * @return a new Parameter object
	 * @throws SQLException if anything goes wrong
	 */
	protected Parameter processParameterColumnRow(ResultSet rs)
			throws SQLException {
		Parameter retVal = createParameter();
		initParameter(retVal, rs);
		return retVal;
	}
	
	/**
	 * Processes a single row in the result set. This method invokes
	 * createColumn(), followed by initColumn(), finally returning the newly
	 * created, initialized Column object.
	 * 
	 * @param rs the result set
	 * @return a new Column object
	 * @throws SQLException if anything goes wrong
	 */
	protected Column processRoutineResultTableColumnRow(ResultSet rs)
			throws SQLException {
		Column retVal = createColumn();
		initColumn(retVal, rs);
		return retVal;
	}
	
	/**
	 * Used to determine whether or not the data in the current row represents a
	 * parameter or a result set column. This logic is required when processing
	 * results from DatabaseMetaData.getProcedureColumns(). This method returns
	 * the following:
	 * 
	 * rs.getShort(COLUMN_COLUMN_TYPE) != DatabaseMetaData.procedureColumnResult
	 * 
	 * @param rs the result set
	 * @return true if the data in the current row represents a parameter.
	 * @throws SQLException if anything goes wrong
	 */
	protected boolean isParameter(ResultSet rs) throws SQLException {
		return rs.getShort(COLUMN_COLUMN_TYPE) != DatabaseMetaData.procedureColumnResult;
	}

	/**
	 * @return a new Parameter object. Default is ParameterImpl.
	 */
	protected Parameter createParameter() {
		return new JDBCParameter();
	}

	/**
	 * @return a new Column object. Default is ColumnImpl.
	 */
	protected Column createColumn() {
		return new JDBCColumn();
	}

	/**
	 * @return a new RoutineResultTable object. Default is
	 *         RoutineResultTableImpl.
	 */
	protected RoutineResultTable createRoutineResultTable() {
		return SQLRoutinesFactory.eINSTANCE.createRoutineResultTable();
	}
	
	/**
	 * Returns the zero based index of the result table referenced by the data
	 * in the current row of the result set.
	 * 
	 * The default implementation always returns zero as
	 * DatabaseMetaData.getProcedureColumns() does not provide information about
	 * the result tables (just the columns within them).
	 * 
	 * @param rs the result set
	 * 
	 * @return the corresponding, zero based index.
	 */
	protected int getRoutineResultTableIndex(ResultSet rs) {
		return 0;
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
		case DatabaseMetaData.procedureColumnReturn:
			parameter.setMode(ParameterMode.OUT_LITERAL);
			break;
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
	 * Used to initialize a newly created result table object. By default, this
	 * method does noting. This method may be overridden to initialize any
	 * vendor specific properties.
	 * 
	 * @param resultTable a newly created RoutineResultTable object
	 * @param rs the result set containing the information
	 * @throws SQLException if anything goes wrong
	 */
	protected void initRoutineResultTable(RoutineResultTable resultTable,
			ResultSet rs) {
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

		if (typeName == null) {
			element.setDataType(null);
			return;
		}

		// see if we can locate a UDT
		UserDefinedType udt = findUserDefinedType(typeName);
		element.setDataType(udt);
	}

	/**
	 * Utility method.
	 * 
	 * @return returns the catalog object being operated upon as a Routine
	 *         (i.e. (Routine) getCatalogObject()).
	 */
	protected Routine getRoutine() {
		return (Routine) getCatalogObject();
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

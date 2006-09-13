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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.jdbc.JDBCDistinctUDT;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.jdbc.JDBCStructuredUDT;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.ecore.EClass;

/**
 * Base loader implementation for loading a database's catalog objects. This
 * class may be specialized as necessary to meet a particular vendor's needs.
 * 
 * @author rcernich
 * 
 * Created on Aug 28, 2006
 */
public class JDBCUserDefinedTypeLoader extends JDBCBaseLoader {

	/**
	 * The column name containing the table name.
	 * 
	 * @see java.sql.DatabaseMetaData.getUDTs()
	 */
	public static final String COLUMN_TYPE_NAME = "TYPE_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the catalog name.
	 * 
	 * @see java.sql.DatabaseMetaData.getUDTs()
	 */
	public static final String COLUMN_CLASS_NAME = "CLASS_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the catalog name.
	 * 
	 * @see java.sql.DatabaseMetaData.getUDTs()
	 */
	public static final String COLUMN_DATA_TYPE = "DATA_TYPE"; //$NON-NLS-1$

	/**
	 * The column name containing the catalog name.
	 * 
	 * @see java.sql.DatabaseMetaData.getUDTs()
	 */
	public static final String COLUMN_REMARKS = "REMARKS"; //$NON-NLS-1$

	/**
	 * The column name containing the catalog name.
	 * 
	 * @see java.sql.DatabaseMetaData.getUDTs()
	 */
	public static final String COLUMN_BASE_TYPE = "BASE_TYPE"; //$NON-NLS-1$

	private IUDTFactory mDistinctTypeFactory;
	private IUDTFactory mJavaTypeFactory;
	private IUDTFactory mStructTypeFactory;

	/**
	 * @param catalogObject the Database object upon which this loader operates.
	 */
	public JDBCUserDefinedTypeLoader(ICatalogObject catalogObject) {
		this(catalogObject, new SchemaObjectFilterProvider(
				ConnectionFilter.USER_DEFINED_TYPE_FILTER));
	}

	public JDBCUserDefinedTypeLoader(
										ICatalogObject catalogObject,
										IConnectionFilterProvider connectionFilterProvider) {
		this(catalogObject, connectionFilterProvider, new DistinctTypeFactory(
				RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry()
						.getDefinition(catalogObject.getCatalogDatabase())),
				new StructTypeFactory(), null);
	}

	public JDBCUserDefinedTypeLoader(
										ICatalogObject catalogObject,
										IConnectionFilterProvider connectionFilterProvider,
										IUDTFactory distinctTypeFactory,
										IUDTFactory structTypeFactory,
										IUDTFactory javaTypeFactory) {
		super(catalogObject, connectionFilterProvider);
		assert (catalogObject instanceof Schema);

		mDistinctTypeFactory = distinctTypeFactory;
		mJavaTypeFactory = javaTypeFactory;
		mStructTypeFactory = structTypeFactory;
	}

	/**
	 * @param existingCatalogs the catalog objects which were previously loaded
	 * @return
	 * @throws SQLException
	 */
	public List loadUDTs(List existingUDTs) throws SQLException {
		List retVal = new ArrayList(existingUDTs.size());
		ResultSet rs = null;
		try {
			for (rs = createResultSet(); rs.next();) {
				UserDefinedType udt = processRow(rs, existingUDTs);
				if (udt != null) {
					retVal.add(udt);
				}
			}
			return retVal;
		}
		finally {
			if (rs != null) {
				closeResultSet(rs);
			}
			clearUDTs(existingUDTs);
		}
	}

	protected void clearUDTs(List existingUDTs) {
		existingUDTs.clear();
	}

	protected ResultSet createResultSet() throws SQLException {
		Schema schema = getSchema();
		return getCatalogObject().getConnection().getMetaData().getUDTs(
				schema.getCatalog().getName(), schema.getName(),
				getJDBCFilterPattern(), null);
	}

	protected void closeResultSet(ResultSet rs) {
		try {
			rs.close();
		}
		catch (SQLException e) {
		}
	}

	protected UserDefinedType processRow(ResultSet rs, List existingUDTs)
			throws SQLException {
		String udtName = rs.getString(COLUMN_TYPE_NAME);
		if (udtName == null || isFiltered(udtName)) {
			return null;
		}
		UserDefinedType udt = null;

		IUDTFactory udtFactory = null;
		switch (rs.getInt(COLUMN_DATA_TYPE)) {
		case Types.JAVA_OBJECT:
			udtFactory = mJavaTypeFactory;
			break;
		case Types.STRUCT:
			udtFactory = mStructTypeFactory;
			break;
		case Types.DISTINCT:
			udtFactory = mDistinctTypeFactory;
			break;
		}
		if (udtFactory == null) {
			return null;
		}

		EClass udtClass = udtFactory.getUDTEClass();
		for (Iterator it = existingUDTs.iterator(); udt != null && it.hasNext();) {
			Object obj = it.next();
			if (udtName.equals(((UserDefinedType) obj).getName())
					&& udtClass.isSuperTypeOf(((UserDefinedType) obj).eClass())) {
				udt = (UserDefinedType) obj;
			}
		}
		if (udt == null) {
			udt = udtFactory.createUDT(rs);
		}
		else {
			((ICatalogObject) udt).refresh();
			existingUDTs.remove(udt);
		}
		return udt;
	}

	protected Schema getSchema() {
		return (Schema) getCatalogObject();
	}

	public static interface IUDTFactory {

		EClass getUDTEClass();

		UserDefinedType createUDT(ResultSet rs) throws SQLException;
	}

	public static class DistinctTypeFactory extends StructTypeFactory {

		private DatabaseDefinition mDatabaseDefinition;

		public DistinctTypeFactory(DatabaseDefinition databaseDefinition) {
			mDatabaseDefinition = databaseDefinition;
		}

		public EClass getUDTEClass() {
			return SQLDataTypesPackage.eINSTANCE.getDistinctUserDefinedType();
		}

		protected UserDefinedType newUDT() {
			return new JDBCDistinctUDT();
		}

		protected DatabaseDefinition getDatabaseDefinition() {
			return mDatabaseDefinition;
		}

		protected void initialize(UserDefinedType udt, ResultSet rs)
				throws SQLException {
			super.initialize(udt, rs);

			List preDefinedTypes = getDatabaseDefinition()
					.getPredefinedDataTypesByJDBCEnumType(
							rs.getShort(COLUMN_BASE_TYPE));
			if (preDefinedTypes.size() > 0) {
				// Assume first type listed is the appropriately named type.
				((DistinctUserDefinedType) udt)
						.setPredefinedRepresentation((PredefinedDataType) preDefinedTypes
								.get(0));
			}
		}
	}

	public static class StructTypeFactory implements IUDTFactory {

		public EClass getUDTEClass() {
			return SQLDataTypesPackage.eINSTANCE.getStructuredUserDefinedType();
		}

		public UserDefinedType createUDT(ResultSet rs) throws SQLException {
			UserDefinedType retVal = newUDT();
			initialize(retVal, rs);
			return retVal;
		}

		protected UserDefinedType newUDT() {
			return new JDBCStructuredUDT();
		}

		protected void initialize(UserDefinedType udt, ResultSet rs)
				throws SQLException {
			udt.setName(rs.getString(COLUMN_TYPE_NAME));
			udt.setDescription(rs.getString(COLUMN_REMARKS));
		}
	}

}

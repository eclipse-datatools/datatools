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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCDistinctUDT;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCStructuredUDT;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.ecore.EClass;

import com.ibm.icu.text.MessageFormat;

/**
 * Base loader implementation for loading a database's catalog objects. This
 * class may be specialized as necessary to meet a particular vendor's needs.
 * 
 * @since 1.0
 */
public class JDBCUserDefinedTypeLoader extends JDBCBaseLoader {

	/**
	 * The column name containing the UDT's name.
	 * 
	 * @see java.sql.DatabaseMetaData.getUDTs()
	 */
	public static final String COLUMN_TYPE_NAME = "TYPE_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the UDT's class name.
	 * 
	 * @see java.sql.DatabaseMetaData.getUDTs()
	 */
	public static final String COLUMN_CLASS_NAME = "CLASS_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the UDT's data type.
	 * 
	 * @see java.sql.DatabaseMetaData.getUDTs()
	 */
	public static final String COLUMN_DATA_TYPE = "DATA_TYPE"; //$NON-NLS-1$

	/**
	 * The column name containing the UDT's description.
	 * 
	 * @see java.sql.DatabaseMetaData.getUDTs()
	 */
	public static final String COLUMN_REMARKS = "REMARKS"; //$NON-NLS-1$

	/**
	 * The column name containing the UDT's base type.
	 * 
	 * @see java.sql.DatabaseMetaData.getUDTs()
	 */
	public static final String COLUMN_BASE_TYPE = "BASE_TYPE"; //$NON-NLS-1$

	private IUDTFactory mDistinctTypeFactory;
	private IUDTFactory mJavaTypeFactory;
	private IUDTFactory mStructTypeFactory;

	/**
	 * This constructs the loader using the default DistinctTypeFactory
	 * StructTypeFactory, no Java type factory, and uses the
	 * ConnectionFilter.USER_DEFINED_TYPE_FILTER filter.
	 * 
	 * @param catalogObject the Schema object upon which this loader operates.
	 */
	public JDBCUserDefinedTypeLoader(ICatalogObject catalogObject) {
		this(catalogObject, new SchemaObjectFilterProvider(
				ConnectionFilter.USER_DEFINED_TYPE_FILTER));
	}

	/**
	 * This constructs the loader using the default DistinctTypeFactory
	 * StructTypeFactory, and no Java type factory.
	 * 
	 * @param catalogObject the Schema object upon which this loader operates.
	 */
	public JDBCUserDefinedTypeLoader(
										ICatalogObject catalogObject,
										IConnectionFilterProvider connectionFilterProvider) {
		this(catalogObject, connectionFilterProvider, new DistinctTypeFactory(
				RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry()
						.getDefinition(catalogObject.getCatalogDatabase())),
				new StructTypeFactory(), null);
	}

	/**
	 * @param catalogObject the Schema object upon which this loader operates.
	 * @param connectionFilterProvider the filter provider used for filtering
	 *        the "UDT" objects being loaded
	 * @param distinctTypeFactory factory for distinct UDTs
	 * @param structTypeFactory factory for struct UDTs
	 * @param javaTypeFactory factory for Java UDTs
	 */
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
	 * @return a collection of UserDefinedType objects
	 * 
	 * @throws SQLException if an error occurred during loading.
	 * 
	 * @deprecated see {@link #loadUDTs(List, Collection)}
	 */
	public List loadUDTs() throws SQLException {
		List retVal = new ArrayList();
		loadUDTs(retVal, Collections.EMPTY_SET);
		return retVal;
	}

	/**
	 * Loads the "UDT" objects from the database. This method uses the result
	 * set from createResultSet() to load the "UDT" objects from the server.
	 * This method first checks the name of the "UDT" to determine whether or
	 * not it should be filtered. If it is not filtered, it checks to see if an
	 * object with that name was loaded previously. If it finds an existing
	 * object, it refreshes that object and adds it to the containment list. If
	 * the named object does not exist, the result set is passed to
	 * processRow(). UserDefinedType objects are created and initialized using
	 * one of the registered factories.
	 * 
	 * This method should only be overridden as a last resort when the desired
	 * behavior cannot be acheived by overriding createResultSet(),
	 * closeResultSet(), processRow(), and a specialized Struct, Distinct and
	 * Java type factories.
	 * 
	 * @param existingUDTs the catalog objects which were previously loaded
	 * @param containmentList the containment list held by parent
	 * @throws SQLException if an error occurred during loading.
	 */
	public void loadUDTs(List containmentList, Collection existingUDTs)
			throws SQLException {
		ResultSet rs = null;
		try {
			initActiveFilter();
			for (rs = createResultSet(); rs.next();) {
				String udtName = rs.getString(COLUMN_TYPE_NAME);
				if (udtName == null || isFiltered(udtName)) {
					continue;
				}
				UserDefinedType udt = (UserDefinedType) getAndRemoveSQLObject(
						existingUDTs, udtName);
				if (udt == null) {
					udt = processRow(rs);
					if (udt != null) {
						containmentList.add(udt);
					}
				}
				else {
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
					if (udtFactory != null) {
						udtFactory.initialize(udt, rs);
					}
					containmentList.add(udt);
					if (udt instanceof ICatalogObject) {
						((ICatalogObject) udt).refresh();
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
	 * Removes the specified UDTs from the model.
	 * 
	 * @param existingUDTs the UDTs to be removed from the model.
	 */
	public void clearUDTs(List existingUDTs) {
		existingUDTs.clear();
	}

	/**
	 * Creates a result set to be used by the loading logic. The default version
	 * uses of the JDBC DatabaseMetaData.getUDTs() to create the result set.
	 * This method may be overridden to use a vendor specific query. However,
	 * the default logic requires the columns named by the "COLUMN_*" fields.
	 * Keep this in mind if you plan to reuse the default logic (e.g.
	 * StructTypeFactory.initialize())
	 * 
	 * @return a result containing the information used to initialize Routine
	 *         objects
	 * 
	 * @throws SQLException if an error occurs
	 */
	protected ResultSet createResultSet() throws SQLException {
		try {
			Schema schema = getSchema();
			return getCatalogObject().getConnection().getMetaData().getUDTs(
					schema.getCatalog().getName(), schema.getName(),
					getJDBCFilterPattern(), null);
		}
		catch (RuntimeException e) {
			SQLException error = new SQLException(MessageFormat.format(
					Messages.Error_Unsupported_DatabaseMetaData_Method,
					new Object[] { "java.sql.DatabaseMetaData.getUDTs()"})); //$NON-NLS-1$
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
	 * determines whether or not the UDT is a struct, distinct or Java type and
	 * invokes createUDT() on the appropriate factory., returning the newly
	 * created, initialized UserDefinedType object.
	 * 
	 * @param rs the result set
	 * @return a new UserDefinedType object
	 * @throws SQLException if anything goes wrong
	 */
	protected UserDefinedType processRow(ResultSet rs) throws SQLException {
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

		return udtFactory.createUDT(rs);
	}

	/**
	 * Utility method.
	 * 
	 * @return returns the catalog object being operated upon as a Schema (i.e.
	 *         (Schema) getCatalogObject()).
	 */
	protected Schema getSchema() {
		return (Schema) getCatalogObject();
	}

	/**
	 * Interface for providing creation logic for UDTs.
	 */
	public static interface IUDTFactory {

		/**
		 * @return the EClass used to represent the UDT objects created by this
		 *         factory. This is used to identify existing objects in the
		 *         model during a refresh (e.g. to reuse the object, preventing
		 *         external references from breaking).
		 */
		EClass getUDTEClass();

		/**
		 * Creates and initializes a UDT object based on the meta-data in the
		 * result set.
		 * 
		 * @param rs the result set
		 * @return a new, initialized UserDefinedType object.
		 * @throws SQLException if anything goes wrong
		 */
		UserDefinedType createUDT(ResultSet rs) throws SQLException;
		
		/**
		 * Initializes a UDT object based on the meta-data in the result set.
		 * The UDT object may be a new UDT object requiring initialization or an
		 * existing UDT object that is being reinitialized.
		 * 
		 * @param udt the UDT to initialize
		 * @param rs the result set
		 * @throws SQLException if anything goes wrong
		 */
		void initialize(UserDefinedType udt, ResultSet rs) throws SQLException;
	}

	/**
	 * Base factory implementation for distinct UDTs.
	 */
	public static class DistinctTypeFactory extends StructTypeFactory {

		private DatabaseDefinition mDatabaseDefinition;

		public DistinctTypeFactory(DatabaseDefinition databaseDefinition) {
			mDatabaseDefinition = databaseDefinition;
		}

		/**
		 * @see org.eclipse.datatools.connectivity.sqm.loader.JDBCUserDefinedTypeLoader.StructTypeFactory#getUDTEClass()
		 * 
		 * @return SQLDataTypesPackage.eINSTANCE.getDistinctUserDefinedType()
		 */
		public EClass getUDTEClass() {
			return SQLDataTypesPackage.eINSTANCE.getDistinctUserDefinedType();
		}

		/**
		 * Internal factory method. The default implementation returns a new
		 * JDBCDistinctUDT object.
		 * 
		 * @return a new UserDefinedType object
		 * 
		 * @see org.eclipse.datatools.connectivity.sqm.loader.JDBCUserDefinedTypeLoader.StructTypeFactory#newUDT()
		 */
		protected UserDefinedType newUDT() {
			return new JDBCDistinctUDT();
		}

		/**
		 * @return the DatabaseDefinition associated with this object
		 */
		protected DatabaseDefinition getDatabaseDefinition() {
			return mDatabaseDefinition;
		}

		/**
		 * Initializes the new UserDefinedType object using the meta-data in the
		 * result set. This method initializes the name, description and type of
		 * the UDT.
		 * 
		 * @param udt a new UserDefinedType object
		 * @param rs the result set
		 * @throws SQLException if anything goes wrong
		 * 
		 * @see org.eclipse.datatools.connectivity.sqm.loader.JDBCUserDefinedTypeLoader.StructTypeFactory#initialize(org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType,
		 *      java.sql.ResultSet)
		 */
		public void initialize(UserDefinedType udt, ResultSet rs)
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

	/**
	 * Base factory implementation for struct UDTs.
	 */
	public static class StructTypeFactory implements IUDTFactory {

		/**
		 * @see org.eclipse.datatools.connectivity.sqm.loader.JDBCUserDefinedTypeLoader.IUDTFactory#getUDTEClass()
		 * 
		 * @return SQLDataTypesPackage.eINSTANCE.getStructuredUserDefinedType()
		 */
		public EClass getUDTEClass() {
			return SQLDataTypesPackage.eINSTANCE.getStructuredUserDefinedType();
		}

		/**
		 * Creates and initializes a new UserDefinedType object from the
		 * meta-data in the result set.
		 * 
		 * @see org.eclipse.datatools.connectivity.sqm.loader.JDBCUserDefinedTypeLoader.IUDTFactory#createUDT(java.sql.ResultSet)
		 */
		public UserDefinedType createUDT(ResultSet rs) throws SQLException {
			UserDefinedType retVal = newUDT();
			initialize(retVal, rs);
			return retVal;
		}

		/**
		 * Internal factory method. The default implementation returns a new
		 * JDBCStructuredUDT object.
		 * 
		 * @return a new UserDefinedType object
		 */
		protected UserDefinedType newUDT() {
			return new JDBCStructuredUDT();
		}

		/**
		 * Initializes the new UserDefinedType object using the meta-data in the
		 * result set. This method initializes the name and description of the
		 * UDT.
		 * 
		 * @param udt a new UserDefinedType object
		 * @param rs the result set
		 * @throws SQLException if anything goes wrong
		 */
		public void initialize(UserDefinedType udt, ResultSet rs)
				throws SQLException {
			udt.setName(rs.getString(COLUMN_TYPE_NAME));
			udt.setDescription(rs.getString(COLUMN_REMARKS));
		}
	}

}

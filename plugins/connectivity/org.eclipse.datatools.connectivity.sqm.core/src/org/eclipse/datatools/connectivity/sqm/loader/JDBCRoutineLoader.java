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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCProcedure;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCUserDefinedFunction;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.ecore.EClass;

import com.ibm.icu.text.MessageFormat;

/**
 * Base loader implementation for loading a database's routine (SP, UDF)
 * objects. This class may be specialized as necessary to meet a particular
 * vendor's needs.
 * 
 * @since 1.0
 */
public class JDBCRoutineLoader extends JDBCBaseLoader {

	/**
	 * The column name containing the routine's name.
	 * 
	 * @see java.sql.DatabaseMetaData.getProcedures()
	 */
	public static final String COLUMN_PROCEDURE_NAME = "PROCEDURE_NAME"; //$NON-NLS-1$

	/**
	 * The column name containing the routine's description.
	 * 
	 * @see java.sql.DatabaseMetaData.getProcedures()
	 */
	public static final String COLUMN_REMARKS = "REMARKS"; //$NON-NLS-1$

	/**
	 * The column name containing the routine's type (SP/UDF).
	 * 
	 * @see java.sql.DatabaseMetaData.getProcedures()
	 */
	public static final String COLUMN_PROCEDURE_TYPE = "PROCEDURE_TYPE"; //$NON-NLS-1$

	private IRoutineFactory mUserDefinedFunctionFactory;
	private IRoutineFactory mProcedureFactory;

	/**
	 * This constructs the loader using the default ProcedureFactory and
	 * UserDefinedFunctionFactory and uses the
	 * ConnectionFilter.STORED_PROCEDURE_FILTER filter.
	 * 
	 * @param catalogObject the Schema object upon which this loader operates.
	 */
	public JDBCRoutineLoader(ICatalogObject catalogObject) {
		this(catalogObject, new SchemaObjectFilterProvider(
				ConnectionFilter.STORED_PROCEDURE_FILTER));
	}

	/**
	 * This constructs the loader using the default ProcedureFactory and
	 * UserDefinedFunctionFactory.
	 * 
	 * @param catalogObject the Schema object upon which this loader operates.
	 * @param connectionFilterProvider the filter provider used for filtering
	 *        the "routine" objects being loaded
	 */
	public JDBCRoutineLoader(
								ICatalogObject catalogObject,
								IConnectionFilterProvider connectionFilterProvider) {
		this(catalogObject, connectionFilterProvider,
				new UserDefinedFunctionFactory(), new ProcedureFactory());
	}

	/**
	 * @param catalogObject the Schema object upon which this loader operates.
	 * @param connectionFilterProvider the filter provider used for filtering
	 *        the "routine" objects being loaded
	 * @param udfFactory factory for creating UDF objects
	 * @param spFactory factory for creating SP objects
	 */
	public JDBCRoutineLoader(
								ICatalogObject catalogObject,
								IConnectionFilterProvider connectionFilterProvider,
								IRoutineFactory udfFactory,
								IRoutineFactory spFactory) {
		super(catalogObject, connectionFilterProvider);
		if (catalogObject != null)
			assert (catalogObject instanceof Schema);

		mUserDefinedFunctionFactory = udfFactory;
		mProcedureFactory = spFactory;
	}

	/**
	 * @return a collection of Routine objects
	 * 
	 * @throws SQLException if an error occurred during loading.
	 * 
	 * @deprecated see {@link #loadRoutines(List, Collection)}
	 */
	public List loadRoutines() throws SQLException {
		List retVal = new ArrayList();
		loadRoutines(retVal, Collections.EMPTY_SET);
		return retVal;
	}

	/**
	 * Loads the "routine" objects from the database. This method uses the
	 * result set from createResultSet() to load the "routine" objects from the
	 * server. This method first checks the name of the "routine" to determine
	 * whether or not it should be filtered. If it is not filtered, it checks to
	 * see if an object with that name was loaded previously. If it finds an
	 * existing object, it refreshes that object and adds it to the containment
	 * list. If the named object does not exist, the result set is passed
	 * processRow(). Routine objects are created and initialized using one of
	 * the registered factories.
	 * 
	 * This method should only be overridden as a last resort when the desired
	 * behavior cannot be acheived by overriding createResultSet(),
	 * closeResultSet(), processRow(), and a specialized SP and UDF factories.
	 * 
	 * @param containmentList the containment list held by parent
	 * @param existingRoutines the catalog objects which were previously loaded
	 * @throws SQLException if an error occurred during loading.
	 */
	public void loadRoutines(List containmentList, Collection existingRoutines)
			throws SQLException {
		ResultSet rs = null;
		try {
			initActiveFilter();
			for (rs = createResultSet(); rs.next();) {
				String routineName = rs.getString(COLUMN_PROCEDURE_NAME);
				if (routineName == null || isFiltered(routineName)) {
					continue;
				}
				Routine routine = (Routine) getAndRemoveSQLObject(
						existingRoutines, routineName);
				if (routine == null) {
					routine = processRow(rs);
					if (routine != null) {
						containmentList.add(routine);
					}
				}
				else {
					if (isProcedure(rs)) {
						mProcedureFactory.initialize(routine, rs);
					}
					else {
						mUserDefinedFunctionFactory.initialize(routine, rs);
					}
					containmentList.add(routine);
					if (routine instanceof ICatalogObject) {
						((ICatalogObject) routine).refresh();
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
	 * Removes the specified routines from the model.
	 * 
	 * @param routines the routines to be removed from the model.
	 */
	public void clearRoutines(List routines) {
		routines.clear();
	}

	/**
	 * @return the procedure factory used by this object
	 */
	public IRoutineFactory getProcedureFactory() {
		return mProcedureFactory;
	}

	/**
	 * Sets the procedure factory used by this object
	 */
	public void setProcedureFactory(IRoutineFactory procedureFactory) {
		mProcedureFactory = procedureFactory;
	}

	/**
	 * Sets the UDF factory used by this object
	 */
	public IRoutineFactory getUserDefinedFunctionFactory() {
		return mUserDefinedFunctionFactory;
	}

	/**
	 * @return the UDF factory used by this object
	 */
	public void setUserDefinedFunctionFactory(
			IRoutineFactory userDefinedFunctionFactory) {
		mUserDefinedFunctionFactory = userDefinedFunctionFactory;
	}

	/**
	 * Creates a result set to be used by the loading logic. The default version
	 * uses of the JDBC DatabaseMetaData.getProcedures() to create the result
	 * set. This method may be overridden to use a vendor specific query.
	 * However, the default logic requires the columns named by the "COLUMN_*"
	 * fields. Keep this in mind if you plan to reuse the default logic (e.g.
	 * ProcedureFactory.initialize())
	 * 
	 * @return a result containing the information used to initialize Routine
	 *         objects
	 * 
	 * @throws SQLException if an error occurs
	 */
	protected ResultSet createResultSet() throws SQLException {
		try {
			Schema schema = getSchema();
			return getCatalogObject().getConnection().getMetaData()
					.getProcedures(schema.getCatalog().getName(),
							schema.getName(), getJDBCFilterPattern());
		}
		catch (RuntimeException e) {
			SQLException error = new SQLException(
					MessageFormat
							.format(
									Messages.Error_Unsupported_DatabaseMetaData_Method,
									new Object[] { "java.sql.DatabaseMetaData.getProcedures()"})); //$NON-NLS-1$
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
	 * determines whether or not the routine is a SP or UDF and invokes
	 * createRoutine() on the appropriate factory, returning the newly created,
	 * initialized Routine object.
	 * 
	 * @param rs the result set
	 * @return a new Routine object
	 * @throws SQLException if anything goes wrong
	 */
	protected Routine processRow(ResultSet rs) throws SQLException {
		IRoutineFactory routineFactory = isProcedure(rs) ? mProcedureFactory
				: mUserDefinedFunctionFactory;
		return routineFactory.createRoutine(rs);
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
	 * Used by processRow() to determine whether or not the meta-data represents
	 * a SP or UDF.
	 * 
	 * @param rs the result set
	 * @return true if the meta-data represents a SP; fals if it represents a
	 *         UDF
	 * @throws SQLException if anything goes wrong
	 */
	protected boolean isProcedure(ResultSet rs) throws SQLException {
		return rs.getShort(COLUMN_PROCEDURE_TYPE) == DatabaseMetaData.procedureNoResult;
	}

	/**
	 * Interface for providing creation logic for routines.
	 */
	public static interface IRoutineFactory {

		/**
		 * @return the EClass used to represent the routine objects created by
		 *         this factory. This is used to identify existing objects in
		 *         the model during a refresh (e.g. to reuse the object,
		 *         preventing external references from breaking).
		 */
		EClass getRoutineEClass();

		/**
		 * Creates and initializes a routine object based on the meta-data in
		 * the result set.
		 * 
		 * @param rs the result set
		 * @return a new, initialized Routine object.
		 * @throws SQLException if anything goes wrong
		 */
		Routine createRoutine(ResultSet rs) throws SQLException;
		
		/**
		 * Initializes a routine object based on the meta-data in the result
		 * set. The routine object may be a new routine requiring initialization
		 * or an existing routine that is being reinitialized.
		 * 
		 * @param table the table to initialize
		 * @param rs the result set
		 * @throws SQLException if anything goes wrong
		 */
		void initialize(Routine routine, ResultSet rs) throws SQLException;
	}

	/**
	 * Base factory implementation for SP.
	 */
	public static class ProcedureFactory implements IRoutineFactory {

		/**
		 * @see org.eclipse.datatools.connectivity.sqm.loader.JDBCRoutineLoader.IRoutineFactory#getRoutineEClass()
		 * 
		 * @return SQLRoutinesPackage.eINSTANCE.getProcedure()
		 */
		public EClass getRoutineEClass() {
			return SQLRoutinesPackage.eINSTANCE.getProcedure();
		}

		/**
		 * Creates and initializes a new Procedure object from the meta-data in
		 * the result set.
		 * 
		 * @see org.eclipse.datatools.connectivity.sqm.loader.JDBCRoutineLoader.IRoutineFactory#createRoutine(java.sql.ResultSet)
		 */
		public Routine createRoutine(ResultSet rs) throws SQLException {
			Routine retVal = newRoutine();
			initialize(retVal, rs);
			return retVal;
		}

		/**
		 * Internal factory method. The default implementation returns a new
		 * JDBCProcedure object.
		 * 
		 * @return a new Routine object
		 */
		protected Routine newRoutine() {
			return new JDBCProcedure();
		}

		/**
		 * Initializes the new Routine object using the meta-data in the result
		 * set. This method initializes the name and description of the
		 * procedure.
		 * 
		 * @param routine a new Routine object
		 * @param rs the result set
		 * @throws SQLException if anything goes wrong
		 */
		public void initialize(Routine routine, ResultSet rs)
				throws SQLException {
			routine.setName(rs.getString(COLUMN_PROCEDURE_NAME));
			routine.setDescription(rs.getString(COLUMN_REMARKS));
		}

	}

	/**
	 * Base factory implementation for UDF.
	 */
	public static class UserDefinedFunctionFactory extends ProcedureFactory {

		/**
		 * @see org.eclipse.datatools.connectivity.sqm.loader.JDBCRoutineLoader.ProcedureFactory#getRoutineEClass()
		 * 
		 * @return SQLRoutinesPackage.eINSTANCE.getUserDefinedFunction()
		 */
		public EClass getRoutineEClass() {
			return SQLRoutinesPackage.eINSTANCE.getUserDefinedFunction();
		}

		/**
		 * Internal factory method. The default implementation returns a new
		 * JDBCUserDefinedFunction object.
		 * 
		 * @return a new Routine object
		 */
		protected Routine newRoutine() {
			return new JDBCUserDefinedFunction();
		}
	}

}

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
import org.eclipse.datatools.modelbase.sql.routines.Parameter;
import org.eclipse.datatools.modelbase.sql.routines.ParameterMode;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;

/**
 * Base loader implementation for loading a SP's parameter objects. This class
 * may be specialized as necessary to meet a particular vendor's needs.
 * 
 * @since 1.0
 */
public class JDBCProcedureColumnLoader extends JDBCRoutineColumnLoader {

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
		if (catalogObject != null)
			assert (catalogObject instanceof Procedure);
	}

	/**
	 * Note, if a result set is returned by the SP, it will be added to the end
	 * of the returned list.
	 * 
	 * @return a collection of Parameter objects
	 * 
	 * @throws SQLException if an error occurred during loading.
	 * 
	 * @deprecated see {@link #loadParameters(List, Collection)},
	 *             {@link #loadRoutineResultTables()}
	 */
	public List loadColumns() throws SQLException {
		List retVal = new ArrayList();
		loadParameters(retVal, Collections.EMPTY_SET);
		List routineResultTables = loadRoutineResultTables();
		if (routineResultTables.size() > 0) {
			retVal.add(routineResultTables.get(0));
		}
		return retVal;
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

}

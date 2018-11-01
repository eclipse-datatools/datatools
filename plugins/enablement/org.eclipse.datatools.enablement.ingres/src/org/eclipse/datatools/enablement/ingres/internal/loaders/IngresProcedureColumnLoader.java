/*******************************************************************************
 * Copyright (c) 2008 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.loaders;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.datatools.connectivity.sqm.loader.JDBCProcedureColumnLoader;
import org.eclipse.datatools.modelbase.sql.routines.Parameter;
import org.eclipse.datatools.modelbase.sql.routines.ParameterMode;

/**
 * Class for loading procedure columns from an Ingres database.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresProcedureColumnLoader extends JDBCProcedureColumnLoader {

	public IngresProcedureColumnLoader() {
		super(null);
	}

	/**
	 * This implementation treats all parameters as INOUT (it is not distinguish
	 * between INOUT/OUT/IN at the moment).
	 * 
	 * @see org.eclipse.datatools.connectivity.sqm.loader.JDBCProcedureColumnLoader#initParameter(org.eclipse.datatools.modelbase.sql.routines.Parameter,
	 *      java.sql.ResultSet)
	 */
	protected void initParameter(Parameter parameter, ResultSet rs)
			throws SQLException {
		// TODO is there a way to differentiate between in/out/inout?
		super.initParameter(parameter, rs);
		parameter.setMode(ParameterMode.INOUT_LITERAL);
	}

}

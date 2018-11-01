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

import org.eclipse.datatools.connectivity.sqm.loader.JDBCRoutineLoader;
import org.eclipse.datatools.enablement.ingres.internal.catalog.IngresProcedure;
import org.eclipse.datatools.modelbase.sql.routines.Routine;

/**
 * Class for loading procedures from an Ingres database.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresRoutineLoader extends JDBCRoutineLoader {

	public IngresRoutineLoader() {
		super(null);
		setProcedureFactory(new IngresProcedureFactory());
	}

	/**
	 * Used by processRow() to determine whether or not the meta-data represents
	 * a SP or UDF.
	 * 
	 * @param rs the result set
	 * @return returns always TRUE
	 * @throws SQLException if anything goes wrong

	 * @see org.eclipse.datatools.connectivity.sqm.loader.JDBCRoutineLoader#isProcedure(java.sql.ResultSet)
	 */
	protected boolean isProcedure(ResultSet rs) throws SQLException {
		// handle every routine as SP
		return true;
	}

	public static class IngresProcedureFactory extends ProcedureFactory {

		protected Routine newRoutine() {
			return new IngresProcedure();
		}
	}

}

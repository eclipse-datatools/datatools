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
package org.eclipse.datatools.enablement.ingres.internal.ui.core;

import java.sql.ParameterMetaData;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.dbitem.ParameterDescriptor;
import org.eclipse.datatools.sqltools.core.internal.dbitem.SQLObjectItem;

/**
 * An Ingres related SQL object item implementation.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresSQLObjectItem extends SQLObjectItem {

	private static final Pattern RESULT_ROW_PATTERN = Pattern
			.compile(
					"(\\s*)(create)(.*)(result)(\\s*)(row)(.*)(begin)(.*)", Pattern.DOTALL | Pattern.MULTILINE | Pattern.CASE_INSENSITIVE); //$NON-NLS-1$

	public IngresSQLObjectItem(ProcIdentifier proc, SQLObject routine,
			IControlConnection controlConn) {
		super(proc, routine, controlConn);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.sqltools.core.internal.dbitem.SQLObjectItem#getParameterDescriptor()
	 */
	public ParameterDescriptor[] getParameterDescriptor() throws SQLException {
		ParameterDescriptor[] pds = super.getParameterDescriptor();

		if (pds != null) {
			for (int i = 0; i < pds.length; i++) {
				if (isRowProducing()) {
					pds[i].setParmType(ParameterMetaData.parameterModeIn);
				} else {
					pds[i].setParmType(ParameterMetaData.parameterModeInOut);
				}
			}
		}

		return pds;
	}

	/**
	 * Determine if the wrapped routine is row producing.
	 * 
	 * @return <code>true</code> if the routine is rowproducing,
	 *         <code>false</code> otherwise
	 */
	private boolean isRowProducing() {
		boolean result = true;
		if (_routine instanceof Routine) {
			Routine ingresProcedure = (Routine) _routine;
			if (ingresProcedure.getSource() != null
					&& ingresProcedure.getSource().getBody() != null) {
				Matcher matcher = RESULT_ROW_PATTERN.matcher(ingresProcedure
						.getSource().getBody());
				result = matcher.matches();
			}
		}
		return result;
	}

}

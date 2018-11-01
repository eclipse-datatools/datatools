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

import java.util.Map;

import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.ProcIdentifierImpl;

/**
 * An Ingres related proc identifier implementation.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresProcIdentifierImpl extends ProcIdentifierImpl {

	public IngresProcIdentifierImpl(int type, DatabaseIdentifier db, Map map) {
		super(type, db, map);
	}

	/**
	 * Overridden to avoid database identifiers within procedure calls.
	 * 
	 * @see org.eclipse.datatools.sqltools.core.ProcIdentifierImpl#getCallableStringWithoutGroupNumber(boolean)
	 */
	public String getCallableStringWithoutGroupNumber(boolean quoted_id) {
		// Ingres cannot handle database identifiers within procedure call
		// statements.
		// Statements like the following will not work "{?=call
		// database.owner.procedure()}"

		String result = super.getCallableStringWithoutGroupNumber(quoted_id);

		// remove the database name from the statement
		if (this.getDatabaseName() != null
				&& this.getDatabaseName().length() > 0) {
			result = result.substring(result.indexOf(".") + 1);
		}

		return result;
	}

}

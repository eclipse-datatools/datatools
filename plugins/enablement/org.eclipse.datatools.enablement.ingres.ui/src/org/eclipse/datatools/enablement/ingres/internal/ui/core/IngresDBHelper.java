/*******************************************************************************
 * Copyright (c) 2006, 2007 Ingres Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ingres Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ingres.internal.ui.core;

import java.util.Map;

import org.eclipse.datatools.sqltools.core.DBHelper;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;

/**
 * An Ingres related database helper implementation.
 * 
 * @author enrico.schenk@ingres.com
 */
public class IngresDBHelper extends DBHelper {

	public ProcIdentifier getProcIdentifier(
			DatabaseIdentifier databaseIdentifier, int dbObjectType, Map map) {
		return new IngresProcIdentifierImpl(dbObjectType, databaseIdentifier,
				map);
	}

}

/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.db.derby.core.services;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.datatools.sqltools.core.DBHelper;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.db.derby.core.DerbyProcIdentifier;

/**
 * 
 * @author hcao
 *
 */
public class DerbyHelper extends DBHelper {

	public ProcIdentifier getProcIdentifier(DatabaseIdentifier databaseIdentifier, String dbObjectName, int dbObjectType, String tableName, String ownerName) {
        Map map = new HashMap();

        //don't put it null values which will cause problem when encoding/decoding
        if (ownerName != null)
        {
        	map.put(ProcIdentifier.PROP_OWNER, ownerName);
        }
        if (dbObjectName != null)
        {
        	map.put(ProcIdentifier.PROP_NAME, dbObjectName);
        }
        if (tableName != null)
        {
        	map.put(ProcIdentifier.PROP_TABLENAME, tableName);
        }

        return new DerbyProcIdentifier(dbObjectType, databaseIdentifier, map);
	}
}

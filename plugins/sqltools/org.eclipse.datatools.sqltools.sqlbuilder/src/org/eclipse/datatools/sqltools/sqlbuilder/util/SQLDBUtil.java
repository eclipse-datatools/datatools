/*******************************************************************************
 * Copyright © 2001, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.sqlbuilder.util;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;

public class SQLDBUtil {

	public static DatabaseDefinition getDatabaseDefinition(ISQLEditorConnectionInfo connInfo){
    	DatabaseDefinition dbDef = null;
    	Database database = connInfo.getDatabase();
    	if (database != null){
    		dbDef = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(database);
    	}
		return dbDef;
	}
	
}

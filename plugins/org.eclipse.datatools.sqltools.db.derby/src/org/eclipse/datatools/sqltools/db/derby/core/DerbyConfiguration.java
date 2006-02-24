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
package org.eclipse.datatools.sqltools.db.derby.core;

import org.eclipse.datatools.sqltools.core.DBHelper;
import org.eclipse.datatools.sqltools.core.services.ConnectionService;
import org.eclipse.datatools.sqltools.core.services.ExecutionService;
import org.eclipse.datatools.sqltools.core.services.SQLService;
import org.eclipse.datatools.sqltools.db.derby.core.services.DerbyConnectionService;
import org.eclipse.datatools.sqltools.db.derby.core.services.DerbyExecutionService;
import org.eclipse.datatools.sqltools.db.derby.core.services.DerbyHelper;
import org.eclipse.datatools.sqltools.db.derby.core.services.DerbySQLService;
import org.eclipse.datatools.sqltools.db.generic.GenericDBConfiguration;

/**
 * @author Hui Cao
 *
 */
public class DerbyConfiguration extends GenericDBConfiguration
{
    private static DerbyConfiguration _instance = null;

    // for eclipse to load this class, we must declare it as public
    public DerbyConfiguration()
    {
        _instance = this;
    }

    public static DerbyConfiguration getInstance()
    {
        return _instance;
    }

	public ConnectionService getConnectionService() {
		return new DerbyConnectionService();
	}

	public SQLService getSQLService() {
		return new DerbySQLService();
	}

	public DBHelper getDBHelper() {
		return new DerbyHelper();
	}

	public ExecutionService getExecutionService()
	{
		return new DerbyExecutionService();
	}
    
}

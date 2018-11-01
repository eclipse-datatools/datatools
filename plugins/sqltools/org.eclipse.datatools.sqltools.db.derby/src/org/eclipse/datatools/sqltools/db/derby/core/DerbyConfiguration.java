/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.db.derby.core;

import org.eclipse.datatools.sqltools.core.DBHelper;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
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
    public static final String[] DERBY_ALIASES = new String[]{"Derby","Apache Derby"};
    
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
    
	public boolean recognize(String product, String version)
	{
		DatabaseVendorDefinitionId targetid = new DatabaseVendorDefinitionId(product, version);
		for (int i = 0; i < DERBY_ALIASES.length; i++) {
			DatabaseVendorDefinitionId id = new DatabaseVendorDefinitionId(DERBY_ALIASES[i], getDatabaseVendorDefinitionId().getVersion());
			if (id.equals(targetid))
			{
				return true;
			}
		}
		return false;
	}
	
	public String[] getAssociatedConnectionProfileType()
	{
		return new String[]{"org.eclipse.datatools.connectivity.db.derby.embedded.connectionProfile"};
	}
}

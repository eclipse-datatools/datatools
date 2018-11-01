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
package org.eclipse.datatools.sqltools.db.generic;

import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.services.SQLService;
import org.eclipse.datatools.sqltools.db.generic.service.GenericSQLService;

/**
 * @author Hui Cao
 * 
 */
public class GenericDBConfiguration extends SQLDevToolsConfiguration {

	public DatabaseVendorDefinitionId getDatabaseVendorDefinitionId() {
		// TODO Auto-generated method stub
		return super.getDatabaseVendorDefinitionId();
	}

	public void setDatabaseVendorDefinitionId(DatabaseVendorDefinitionId dbdefinitionId) {
		// TODO Auto-generated method stub
		super.setDatabaseVendorDefinitionId(dbdefinitionId);
	}
	
	public SQLService getSQLService() {
		return new GenericSQLService();
	}
    
	/**
	 * Always returns true since this configuration class can be used
	 * to handle all kinds of databases.
	 * @param product
	 * @param version
	 * @return
	 */
	public boolean recognize(String product, String version)
	{
		return true;
	}
	
	public String[] getAssociatedConnectionProfileType()
	{
		return new String[]{"org.eclipse.datatools.connectivity.db.generic.connectionProfile"};
	}
}

/*******************************************************************************
 * Copyright (c) 2004, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.luw.catalog;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.impl.RelationalRemoteDataSetImpl;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.util.TableToRemoteDataSetHelper;

/**
 * @author Geetika
 *
 **/
public class LUWCatalogRelationalRemoteDataSet extends RelationalRemoteDataSetImpl{
	String remoteSchemaName = null;
	String remoteTableName = null;
	
	public LUWCatalogRelationalRemoteDataSet() {
		super();
		this.eAdapters().add(TableToRemoteDataSetHelper.INVERSE_TABLE_ADAPTER);

		// A side effect of the add is to set the target of the INVERSE_TABLE_ADAPTER 
		// to point back to <<this>>. This causes garbage collection problems because 
		// INVERSE_TABLE_ADAPTER is a singleton. Need to unset the target reference...
		TableToRemoteDataSetHelper.INVERSE_TABLE_ADAPTER.setTarget(null);
	}
	

	//only created if ecattable is created, in which case it will get set
	//nothing to do to help - I can't get up to the right nickname to force a table load of the remote table
	/**
	 * @return Returns the remoteSchemaName.
	 */
	public String getRemoteSchemaName() {
		return remoteSchemaName;
	}
	/**
	 * @param remoteSchemaName The remoteSchemaName to set.
	 */
	public void setRemoteSchemaName(String remoteSchemaName) {
		this.remoteSchemaName = remoteSchemaName;
	}
	/**
	 * @return Returns the remoteTableName.
	 */
	public String getRemoteTableName() {
		return remoteTableName;
	}
	/**
	 * @param remoteTableName The remoteTableName to set.
	 */
	public void setRemoteTableName(String remoteTableName) {
		this.remoteTableName = remoteTableName;
	}
}

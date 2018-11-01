/*******************************************************************************
 * Copyright (c) 2005, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.luw.model.util;

import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.RelationalRemoteServer;
import org.eclipse.datatools.enablement.ibm.db2.model.util.ReverseNavigationHelper;

/**
 * @author ellersr
 *
 * Helper to provide the ability to navigate the <code>RelationalRemoteServer.database</code> relationship
 * in the inverse direction. (I.e. navigate from an SQL Database object back to an 
 * LUW RelationalRemoteServer object, even though the relationship RelationalRemoteServer to
 * Database is modeled to be one directional.)
 */
public class DatabaseToRemoteServerHelper extends ReverseNavigationHelper {

	
	protected static final EReference databaseRef = LUWPackage.eINSTANCE.getRelationalRemoteServer_Database();

	/**
	 * The singleton instance of <code>INVERSE_DATABASE_ADAPTER</code> must be attached to 
	 * any instance of RelationalRemoteServer for which reverse navigation is required.
	 * This adapter should be attached at the time the RelationalRemoteServer object is 
	 * created.
	 */
	public static final InverseAdapter INVERSE_DATABASE_ADAPTER = new InverseAdapter(databaseRef, SINGLE);

	
	/**
	 * Helper method to get the LUW RelationalRemoteServer object that points to
	 * a specified instance of a SQL Database object via the <code>RelationalRemoteServer.database</code> relationship.
	 * @param db The SQL Database object
	 * @return The corresponding LUW RelationalRemoteServer object
	 */
	public static RelationalRemoteServer getRemoteServer(Database db) {
		return (RelationalRemoteServer) INVERSE_DATABASE_ADAPTER.getOppositeEnd(db);
	}
	

}

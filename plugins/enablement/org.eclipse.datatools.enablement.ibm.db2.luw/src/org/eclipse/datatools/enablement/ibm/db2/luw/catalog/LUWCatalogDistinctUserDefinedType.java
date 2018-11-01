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

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.enablement.ibm.catalog.IDatabaseObject;
import org.eclipse.datatools.modelbase.sql.datatypes.impl.DistinctUserDefinedTypeImpl;
import org.eclipse.datatools.modelbase.sql.schema.Database;

public class LUWCatalogDistinctUserDefinedType extends DistinctUserDefinedTypeImpl implements ICatalogObject,IDatabaseObject {
	public void refresh() {
		RefreshManager.getInstance().referesh(this);
	}

	public boolean isSystemObject() {
		return false;
	}

	public Connection getConnection() {
		Database database = this.getCatalogDatabase();
		return ((LUWCatalogDatabase) database).getConnection();
	}
	
	public Database getCatalogDatabase() {
		return this.getSchema().getDatabase();		
	}
	
	public void refresh(int refreshType){
	}

	public ICatalogObject[] getImpacted(){
		Collection impacts = this.getImpactedObjects();
		ICatalogObject[] objs = new ICatalogObject[impacts.size()];
		impacts.toArray(objs);
		return objs;
	}
	
	public Collection getStatistics(){
		return new ArrayList();
	}


	private Collection getImpactedObjects(){
		Collection impacts = new ArrayList();
		Connection connection = this.getConnection();

		impacts.addAll(LUWCatalogStructuredUserDefinedType.getImpactedTriggers(connection, this));
		impacts.addAll(LUWCatalogStructuredUserDefinedType.getImpactedTables(connection, this));
		impacts.addAll(LUWCatalogStructuredUserDefinedType.getImpactedConstraints(connection, this));
		impacts.addAll(LUWCatalogStructuredUserDefinedType.getImpactedRoutines(connection, this));
		impacts.addAll(LUWCatalogStructuredUserDefinedType.getImpactedPackages(connection, this));

		return impacts;
	}

}

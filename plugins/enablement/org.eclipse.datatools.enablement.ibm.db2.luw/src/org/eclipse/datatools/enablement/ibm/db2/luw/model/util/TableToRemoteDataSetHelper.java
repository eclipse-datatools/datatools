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

import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.datatools.enablement.ibm.db2.luw.model.LUWPackage;
import org.eclipse.datatools.enablement.ibm.db2.luw.model.RelationalRemoteDataSet;
import org.eclipse.datatools.enablement.ibm.db2.model.util.ReverseNavigationHelper;

/**
 * @author ellersr
 *
 * Helper to provide the ability to navigate the <code>RelationalRemoteDataSet.table</code> relationship
 * in the inverse direction. (I.e. navigate from an SQL BaseTable object back to an 
 * LUW RelationalRemoteDataSet object, even though the relationship RelationalRemoteDataSet to
 * BaseTable is modeled to be one directional.)
 */
public class TableToRemoteDataSetHelper extends ReverseNavigationHelper {

	
	protected static final EReference tableRef = LUWPackage.eINSTANCE.getRelationalRemoteDataSet_Table();	

	/**
	 * The singleton instance of <code>INVERSE_TABLE_ADAPTER</code> must be attached to 
	 * any instance of RelationalRemoteDataSet for which reverse navigation is required.
	 * This adapter should be attached at the time the RelationalRemoteDataSet object is 
	 * created.
	 */
	public static final InverseAdapter INVERSE_TABLE_ADAPTER = new InverseAdapter(tableRef, SINGLE);


	/**
	 * Helper method to get the LUW RelationalRemoteDataSet object that points to
	 * a specified instance of a SQL BaseTable object via the <code>RelationalRemoteDataSet.table</code> relationship.
	 * @param tab The SQL BaseTable object
	 * @return The corresponding LUW RelationalRemoteDataSet object
	 */
	public static RelationalRemoteDataSet getRemoteDataSet(BaseTable tab) {
		return (RelationalRemoteDataSet) INVERSE_TABLE_ADAPTER.getOppositeEnd(tab);
	}
	
}

/*******************************************************************************
 * Copyright (c) 2011, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.util;

import org.eclipse.datatools.enablement.ibm.catalog.util.ICatalogQuery;
import org.eclipse.datatools.enablement.ibm.util.IQueryMap.QueryType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public interface IClientStrategy
{
	/**
	 * The IClientStrategy has insider knowledge on the type of query to 
	 * construct given the database and feature.  
	 * 
	 * It is responsible for setting the appropriate filters as required by the
	 * implementing strategy.  The filter will be used for the WHERE clause
	 * 
	 * @param object, calling object initiating the loading operation
	 * @param feature, mapping to specific feature to be loaded
	 * @return, array of objects representing the queries to be executed
	 */
	public ICatalogQuery[] getCatalogQueries(EObject object, EStructuralFeature feature);
	
	/**
	 * 
	 * @param object, calling object initiating the loading operation
	 * @param queryType, QueryType for an adhoc item to be loaded that is not associated
	 * with a feature
	 * @return, array of objects representing the queries to be executed
	 */
	public ICatalogQuery[] getCatalogQueries(EObject object, QueryType queryType);
	
}

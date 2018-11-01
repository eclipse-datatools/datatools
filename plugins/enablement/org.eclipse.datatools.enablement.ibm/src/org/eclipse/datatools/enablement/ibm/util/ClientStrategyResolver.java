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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class ClientStrategyResolver
{
	private static ClientStrategyResolver resolver;
	
	private ClientStrategyResolver()
	{
	}
	
	public static synchronized ClientStrategyResolver getInstance()
	{
		if (resolver == null) {
			resolver = new ClientStrategyResolver();
		}
		return resolver;
	}
	
	@SuppressWarnings("unchecked")
	public EList getObjects(
			EObject object, EStructuralFeature feature, ClientConfiguration configuration) 
			throws ClientStrategyResolverException
	{
		Object result = getObject(object, feature, configuration);
		
		if(result instanceof EList) {
			return (EList)result;
		}
		
		throw new ClientStrategyResolverException("A list was expected, but only one object was returned");
	}
	
	public Object getObject(EObject object, EStructuralFeature feature, ClientConfiguration configuration)
	{
		IClientStrategy strategy = 
			ClientStrategyService.getInstance().getClientStrategy(object, configuration);
		if (strategy != null) {
			ICatalogQuery[] queries = strategy.getCatalogQueries(object, feature);		
			realizeQueries(object, queries);
		}
		
		return object.eGet(feature);
	}

	public void loadObject(EObject object, QueryType queryType, ClientConfiguration configuration)
	{
		IClientStrategy strategy = 
			ClientStrategyService.getInstance().getClientStrategy(object, configuration);
		if (strategy == null) {
			return;
		}
		ICatalogQuery[] queries = strategy.getCatalogQueries(object, queryType);
		
		realizeQueries(object, queries);
	}

	private void realizeQueries(EObject object, ICatalogQuery[] queries) 
	{
		if (queries != null && queries.length > 0) {
			QueryExecutionEngine.realizeQueries(object, queries);
		}
	}
}

/*
 *************************************************************************
 * Copyright (c) 2011 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.enablement.oda.xml.ui.utils;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.datatools.connectivity.oda.util.ResourceIdentifiers;

public class ResourceIdentifiersUtil
{

	public static ResourceIdentifiers getResourceIdentifiers(
			org.eclipse.datatools.connectivity.oda.design.ResourceIdentifiers resourceIdentifiers )
	{
		ResourceIdentifiers ri = new ResourceIdentifiers( );
		ri.setApplResourceBaseURI( resourceIdentifiers.getApplResourceBaseURI( ) );
		ri.setDesignResourceBaseURI( resourceIdentifiers.getDesignResourceBaseURI( ) );
		return ri;
	}

	public static Map<String, Object> getAppContext( Object resourceIdentifiers )
	{
		HashMap<String, Object> appContext = new HashMap<String, Object>( );
		if ( resourceIdentifiers != null )
			appContext.put( ResourceIdentifiers.ODA_APP_CONTEXT_KEY_CONSUMER_RESOURCE_IDS,
					resourceIdentifiers );

		return appContext;
	}
}

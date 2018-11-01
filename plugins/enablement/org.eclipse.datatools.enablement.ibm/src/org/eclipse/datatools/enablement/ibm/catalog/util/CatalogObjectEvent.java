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
package org.eclipse.datatools.enablement.ibm.catalog.util;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;

public class CatalogObjectEvent 
{
	public ICatalogObject element;
	public EVENT_TYPE type;
	
	public enum EVENT_TYPE {
		ELEMENT_REFRESH, ELEMENT_REUSE
	}
	
	public CatalogObjectEvent(ICatalogObject element, EVENT_TYPE type)
	{
		this.element = element;
		this.type = type;
	}
}

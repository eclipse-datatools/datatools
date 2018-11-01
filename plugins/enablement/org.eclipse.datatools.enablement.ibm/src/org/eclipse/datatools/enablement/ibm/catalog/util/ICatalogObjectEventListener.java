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

public interface ICatalogObjectEventListener 
{
	/**
	 * 
	 * @param event, encapsulates the object type and the event
	 * that triggered the notification
	 */
	public void notifyChanged(CatalogObjectEvent event);
}

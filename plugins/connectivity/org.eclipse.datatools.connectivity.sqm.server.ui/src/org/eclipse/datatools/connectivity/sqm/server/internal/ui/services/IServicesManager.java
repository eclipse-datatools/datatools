/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.services;


/**
 * @author ljulien
 */
public interface IServicesManager
{
	public static final IServicesManager INSTANCE = new ServicesManager();
	public IServerExplorerRefreshService getServerExplorerRefreshService ();
	public IServerExplorerContentService getServerExplorerContentService ();
	public IServerExplorerNavigationService getServerExplorerNavigationService ();
}

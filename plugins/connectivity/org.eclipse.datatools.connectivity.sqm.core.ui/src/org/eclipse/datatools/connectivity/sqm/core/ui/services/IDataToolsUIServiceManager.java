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
package org.eclipse.datatools.connectivity.sqm.core.ui.services;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.services.IVirtualNodeServiceFactory;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.services.DataToolsUIServiceManager;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.services.IColumnHelperService;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.services.IDecorationService;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.services.IExplorerSorterService;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.services.IForeignKeyHelperService;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.services.ILabelService;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.services.IMarkerNavigationService;


/**
 * @author ljulien
 */
public interface IDataToolsUIServiceManager
{
	public static final DataToolsUIServiceManager INSTANCE = new DataToolsUIServiceManager ();
	
	public IDecorationService getDecorationService ();
	public IColumnHelperService getColumnHelperService ();
	public IForeignKeyHelperService getForeignKeyHelperService ();
	public IVirtualNodeServiceFactory getVirtualNodeServiceFactory ();
	public ILabelService getLabelService (Object element);
	public void refreshColumnDecorationService ();
	public IMarkerNavigationService getMarkerNavigationService ();
	public IExplorerSorterService getExplorerSorterService ();
}

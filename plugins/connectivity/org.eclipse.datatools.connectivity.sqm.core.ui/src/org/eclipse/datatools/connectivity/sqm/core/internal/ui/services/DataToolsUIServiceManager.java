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
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.services;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.helpers.ColumnHelper;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.helpers.ForeignKeyHelper;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.VirtualNodeServiceFactory;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.decorators.IBookmarkDecorationService;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.decorators.IColumnDecorationService;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.services.IExplorerInteractionService;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.services.IVirtualNodeServiceFactory;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources.DataToolsUIConstants;
import org.eclipse.datatools.connectivity.sqm.core.ui.services.IDataToolsUIServiceManager;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IDecoratorManager;
import org.eclipse.ui.PlatformUI;


/**
 * @author ljulien
 */
public class DataToolsUIServiceManager implements IDataToolsUIServiceManager, IDecorationService
{
	/**
	 * Model Explorer Interaction service
	 */
	private IExplorerInteractionService interactionService;
	private LabelService labelService;
	private IMarkerNavigationService markerNavigationService;
	private IExplorerSorterService explorerSorter;
	
	/**
	 * Initialize our provider
	 */
	public DataToolsUIServiceManager ()
	{
		this.markerNavigationService = new MarkerNavigationService ();
		this.explorerSorter = new ExplorerSorterProvider ();
	}
	
	/**
	 * @param interactionService
	 */
	public void setModelExplorerInteractionService (IExplorerInteractionService interactionService)
	{
		this.interactionService = interactionService;
	}
	
	/**
	 * @return
	 */
	public IExplorerInteractionService getModelExplorerInteractionService ()
	{
		return this.interactionService;
	}
	
	/**
	 * @see org.eclipse.datatools.connectivity.sqm.core.internal.ui.services.IDecorationService#getColumnDecorationService()
	 */
	public IColumnDecorationService getColumnDecorationService ()
	{
		IDecoratorManager decoratorManager = PlatformUI.getWorkbench().getDecoratorManager();
		return (IColumnDecorationService) decoratorManager.getBaseLabelProvider(
			DataToolsUIConstants.COLUMN_DECORATION_SERVICE); 
	}

	public IBookmarkDecorationService getBookmarkDecorationService ()
	{
	    IDecoratorManager decoratorManager = PlatformUI.getWorkbench().getDecoratorManager();
	    return (IBookmarkDecorationService) decoratorManager.getBaseLabelProvider(
	            DataToolsUIConstants.BOOKMARK_DECORATION_SERVICE);
	}

    public IMarkerNavigationService getMarkerNavigationService()
    {
        return this.markerNavigationService;
    }

	public void refreshColumnDecorationService ()
	{
	    IDecoratorManager decoratorManager = PlatformUI.getWorkbench().getDecoratorManager();
	    decoratorManager.update(DataToolsUIConstants.COLUMN_DECORATION_SERVICE);
	}
	
	/**
	 * Will provide some column related helper
	 * @return
	 */
	public IColumnHelperService getColumnHelperService ()
	{
		return new ColumnHelper();
	}

	/**
	 * @see org.eclipse.datatools.connectivity.sqm.core.ui.services.IDataToolsUIServiceManager#getForeignKeyHelperService()
	 */
	public IForeignKeyHelperService getForeignKeyHelperService()
	{
		return new ForeignKeyHelper();
	}

	/**
	 * @see org.eclipse.datatools.connectivity.sqm.core.ui.services.IDataToolsUIServiceManager#getDecorationService()
	 */
	public IDecorationService getDecorationService()
	{
		return this;
	}

	/**
	 * @see org.eclipse.datatools.connectivity.sqm.core.ui.services.IDataToolsUIServiceManager#getVirtualNodeServiceFactory()
	 */
	public IVirtualNodeServiceFactory getVirtualNodeServiceFactory()
	{
		return VirtualNodeServiceFactory.INSTANCE;
	}

    /**
     * @see org.eclipse.datatools.connectivity.sqm.core.ui.services.IDataToolsUIServiceManager#getImageService(java.lang.Object)
     */
    public Image getImageService(Object element)
    {
        return getLabelService(element).getIcon();
    }

    public ILabelService getLabelService(Object element)
    {
        if (labelService == null)
        {
            labelService = new LabelService ();
        }
        labelService.setElement (element);
        return labelService;
    }

    public IExplorerSorterService getExplorerSorterService ()
    {
        return explorerSorter;
    }
}

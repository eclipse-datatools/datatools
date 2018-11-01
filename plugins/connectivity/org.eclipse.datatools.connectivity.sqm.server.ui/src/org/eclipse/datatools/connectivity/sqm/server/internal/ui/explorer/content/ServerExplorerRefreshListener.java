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
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.content;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObjectListener;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.services.IServerExplorerContentService;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.services.IServicesManager;


/**
 * @author ljulien
 */
public class ServerExplorerRefreshListener implements ICatalogObjectListener
{
    /**
     * Will get notified whenever the Server Explorer needs to be refreshed
     */
    public void notifyChanged(ICatalogObject dmElement, int eventType)
    {
        if (eventType == ICatalogObjectListener.EventTypeEnumeration.ELEMENT_REFRESH)
        {
        	IServerExplorerContentService service = IServicesManager.INSTANCE.getServerExplorerContentService();
        	if (service != null)
        		service.refreshNode(dmElement);
        }
    }
}

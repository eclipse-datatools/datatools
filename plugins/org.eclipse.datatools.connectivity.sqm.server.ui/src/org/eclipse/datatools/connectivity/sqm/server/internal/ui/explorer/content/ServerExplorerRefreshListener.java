/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.content;

import org.eclipse.datatools.connectivity.sqm.internal.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.internal.core.rte.ICatalogObjectListener;
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
        	//TODO:Need to refresh DSE nodes
 //           IServicesManager.INSTANCE.getServerExplorerContentService().refreshNode(dmElement);
        }
    }
}

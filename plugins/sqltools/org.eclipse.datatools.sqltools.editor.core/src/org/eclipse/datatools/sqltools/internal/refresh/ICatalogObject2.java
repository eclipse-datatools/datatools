/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.refresh;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;

/**
 * Extends ICatalogObject to provide fine-grained refresh operation
 * @author Hui Cao
 */
public interface ICatalogObject2 extends ICatalogObject
{
    /**
     * Refreshes according to the context.
     * @param context an string to indicate what to refresh; null or empty means all features. @see group Id
     */
    public void refresh(String context);
    
    /**
     * Returns whether needs to refresh
     * @param context an string to indicate what to refresh; null or empty means all features  
     * @return
     */
    public boolean needsRefresh(String context);

    /**
     * 
     * @param obj a containment object or an IVirtualNode which represents a group of containment objects
     * @return
     */
    public String getRefreshContext(Object obj);
}

/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.asa.internal.ui.decorator;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.decorators.impl.AbstractDecorationService;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseProxyTable;
import org.eclipse.datatools.enablement.sybase.ui.util.DSEUtil;
import org.eclipse.jface.viewers.IDecoration;

/**
 * 
 * @author lihuang
 * 
 */
public class ProxyTableDecorationService extends AbstractDecorationService
{

    public void decorate(Object element, IDecoration decoration)
    {

        if (element instanceof SybaseASABaseProxyTable)
        {
            SybaseASABaseProxyTable table = (SybaseASABaseProxyTable) element;
            
            decoration.addSuffix(" [" + table.getRemoteObjectLocation() + "]");    
        }
    }
}

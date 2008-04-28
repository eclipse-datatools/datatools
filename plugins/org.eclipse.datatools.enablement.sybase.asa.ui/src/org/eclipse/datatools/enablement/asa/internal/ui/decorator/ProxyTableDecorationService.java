/**
 * Created on 2007-06-18
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
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
            if(DSEUtil.checkIsShowOwner(element)){
                String owner = table.getSchema().getOwner().getName();
                StringBuffer suffix = new StringBuffer()
                                        .append(" (")
                                        .append(owner)
                                        .append(")")
                                        .append(" [")
                                        .append(table.getRemoteObjectLocation())
                                        .append("]");

                decoration.addSuffix(suffix.toString());                
            }
            else{
                decoration.addSuffix(" [" + table.getRemoteObjectLocation() + "]");    
            }
        }
    }
}

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
package org.eclipse.datatools.enablement.sybase.ase.internal.ui.decorator;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.decorators.impl.AbstractDecorationService;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.ProxyTableExternalType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProxyTable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebServiceTable;
import org.eclipse.datatools.enablement.sybase.ase.ui.ASEImages;
import org.eclipse.jface.viewers.IDecoration;

/**
 * 
 * @author lihuang
 * @author Shifeng Yu
 */
public class ProxyTableDecorationService extends AbstractDecorationService {

	public void decorate(Object element, IDecoration decoration) {
		
       /**
         * ASE has four kinds of proxy table. Each proxy table has a special icon,
         */
        if (element instanceof SybaseASEProxyTable && !(element instanceof SybaseASEWebServiceTable))
        {
            SybaseASEProxyTable table = (SybaseASEProxyTable) element;
            decoration.addSuffix(" ["+table.getExternalPath()+"]");
            switch (table.getExternalType().getValue())
            {
                case ProxyTableExternalType.FILE:
                    decoration.addOverlay(ASEImages.DESC_FILEPROXYTABLE);
                    break;
                case ProxyTableExternalType.DIRECTORY:
                    decoration.addOverlay(ASEImages.DESC_DIRPROXYTABLE);
                    break;
                case ProxyTableExternalType.PROCEDURE:
                    decoration.addOverlay(ASEImages.DESC_RPCPROXYTABLE);
                    break;
                case ProxyTableExternalType.TABLE:
                    decoration.addOverlay(ASEImages.DESC_REMOTETABLE);
                    break;
            }
        }
	}
}

/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.sybase.ase.providers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinitionRegistry;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebServiceTable;
import org.eclipse.datatools.enablement.sybase.ui.util.DSEContentProviderUtil;
import org.eclipse.datatools.modelbase.sql.tables.Table;

/**
 * 
 * @author lihuang
 *
 */
public class ASEContentProviderUtil extends DSEContentProviderUtil
{

    public static Object[] getWebServiceTableChildren(Object parent)
    {
        if (!(parent instanceof SybaseASEWebServiceTable))
        {
            return new Object[0];
        }
        

//        DatabaseDefinitionRegistry dbRegistry = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry();
//        DatabaseDefinition df = dbRegistry.getDefinition(((Table) parent).getSchema().getDatabase());

        List collection = new ArrayList(5);
        collection.add(nodeFactory.makeColumnNode(COLUMN, COLUMN, parent));

        return getArrays(parent, collection);
    }
    
}

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
package org.eclipse.datatools.enablement.ase.deltaddl;

import java.util.Map;

import org.eclipse.datatools.enablement.sybase.deltaddl.SybaseDeltaDdlGeneration;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * @author David Cui
 */
public class SybaseASECheckDeltaDdlGenProvider extends SybaseASEConstraintDeltaDdlGenProvider
{
    /* (non-Javadoc)
     * @see org.eclipse.datatools.enablement.sybase.deltaddl.AbstractDeltaDdlGenProvider#analyze(org.eclipse.datatools.modelbase.sql.schema.SQLObject, java.util.Map)
     */
    
    public void analyze(SQLObject element, Map changeMap, Map changeRecords)
    {
        if(!(element instanceof CheckConstraint))
            return;
        Integer flag = (Integer)changeMap.get(element);
        if((flag.intValue() & SybaseDeltaDdlGeneration.MODIFIED) != 0)
        {
            flag = new Integer(SybaseDeltaDdlGeneration.CREATE | SybaseDeltaDdlGeneration.DROP);
            changeMap.put(element, flag);
        }
    }
}

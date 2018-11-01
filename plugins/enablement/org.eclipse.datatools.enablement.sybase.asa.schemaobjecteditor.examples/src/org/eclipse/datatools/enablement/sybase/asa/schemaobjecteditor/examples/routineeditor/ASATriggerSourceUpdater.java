/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;


/**
 * 
 * @author Hui Cao
 * 
 */
public class ASATriggerSourceUpdater extends TriggerSourceUpdater
{
    public ASATriggerSourceUpdater(Trigger trigger, DatabaseDefinition definition)
    {
        super(trigger, definition);
    }
    
    public void init()
    {
    }
    
}

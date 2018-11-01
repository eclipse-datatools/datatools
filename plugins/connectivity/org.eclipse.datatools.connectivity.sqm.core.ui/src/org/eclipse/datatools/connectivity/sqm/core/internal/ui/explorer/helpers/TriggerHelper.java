/*******************************************************************************
 * Copyright (c) 2001, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.helpers;

import java.text.MessageFormat;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources.ResourceLoader;
import org.eclipse.datatools.modelbase.sql.tables.ActionTimeType;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;

/**
 * @author ljulien
 */
public class TriggerHelper
{
    public static final TriggerHelper INSTANCE = new TriggerHelper ();

    private static final String FORMAT = " [{0} / {1}]"; //$NON-NLS-1$
    private static final String SEPARATION = " - "; //$NON-NLS-1$
    private static final String BLANK = ""; //$NON-NLS-1$
    private static final ResourceLoader resource = ResourceLoader.getResourceLoader();
    private static final String UPDATE = resource.queryString("DATATOOLS.CORE.UI.TRIGGER_UPDATE.DECORATION"); //$NON-NLS-1$
    private static final String DELETE = resource.queryString("DATATOOLS.CORE.UI.TRIGGER_DELETE.DECORATION"); //$NON-NLS-1$
    private static final String INSERT = resource.queryString("DATATOOLS.CORE.UI.TRIGGER_INSERT.DECORATION"); //$NON-NLS-1$
    private static final String BEFORE = resource.queryString("DATATOOLS.CORE.UI.TRIGGER_BEFORE.DECORATION"); //$NON-NLS-1$
    private static final String AFTER = resource.queryString("DATATOOLS.CORE.UI.TRIGGER_AFTER.DECORATION"); //$NON-NLS-1$
    private static final String INSTEADOF = resource.queryString("DATATOOLS.CORE.UI.TRIGGER_INSTEADOF.DECORATION"); //$NON-NLS-1$

    private TriggerHelper () 
    {
    }
    
    public String getDecoration (Trigger trigger)
    {
        String triggerType = null;
        String triggerTime = null;
        if (trigger.isInsertType())
        {
            triggerType = INSERT;
        }
        if (trigger.isDeleteType())
        {
            if (triggerType == null)
            {
                triggerType = DELETE;
            }
            else
            {
                triggerType += SEPARATION + DELETE;
            }
        }
        if (trigger.isUpdateType())
        {
            if (triggerType == null)
            {
                triggerType = UPDATE;
            }
            else
            {
                triggerType += SEPARATION + UPDATE;
            }
        }
        
        ActionTimeType type = trigger.getActionTime();
        if (type == ActionTimeType.AFTER_LITERAL)
        {
            triggerTime = AFTER;
        }
        else if (type == ActionTimeType.BEFORE_LITERAL)
        {
            triggerTime = BEFORE;
        }
        else if (type == ActionTimeType.INSTEADOF_LITERAL)
        {
            triggerTime = INSTEADOF;
        }
        return MessageFormat.format(FORMAT, new String [] {triggerType != null ? triggerType : BLANK, triggerTime != null ? triggerTime : BLANK});
    }
}

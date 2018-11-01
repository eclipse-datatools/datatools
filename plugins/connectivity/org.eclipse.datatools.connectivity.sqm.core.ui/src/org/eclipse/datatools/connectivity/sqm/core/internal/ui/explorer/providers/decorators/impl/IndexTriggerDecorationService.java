/*******************************************************************************
 * Copyright (c) 2005, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.decorators.impl;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.helpers.IndexHelper;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.helpers.TriggerHelper;
import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.decorators.IIndexTriggerDecorationService;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.jface.viewers.IDecoration;

/**
 * @author ljulien
 */
public class IndexTriggerDecorationService extends AbstractDecorationService implements IIndexTriggerDecorationService
{
    private String getDecoration (Object element)
    {
        if (element instanceof Trigger)
        {
            return TriggerHelper.INSTANCE.getDecoration ((Trigger)element);
        }
        else if (element instanceof Index)
        {
            return IndexHelper.INSTANCE.getDecoration ((Index)element);
        }
        return null;
    }
    
    public void decorate(Object element, IDecoration decoration)
    {
        decoration.addSuffix(getDecoration(element));
    }
}

/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.pages.triggers;

import java.util.Iterator;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseActionTime;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTrigger;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils.SybaseImages;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;


/**
 * "Triggers" label provider
 * 
 * @author Idull
 */
public class TriggersViewerLabelProvider implements ITableLabelProvider
{
    private DatabaseIdentifier _databaseIdentifier;

    public TriggersViewerLabelProvider(DatabaseIdentifier identifier)
    {
        super();
        _databaseIdentifier = identifier;
    }

    public Image getColumnImage(Object element, int columnIndex)
    {
        if(columnIndex == TriggersSectionMetaData.NAME_COL)
        {
            return SybaseImages.get(SybaseImages.IMG_TRIGGER);
        }
        return null;
    }

    public String getColumnText(Object element, int columnIndex)
    {
        if (!(element instanceof SybaseASABaseTrigger))
        {
            return ""; //$NON-NLS-1$
        }
        SybaseASABaseTrigger trigger = (SybaseASABaseTrigger) element;
        switch (columnIndex)
        {
            case TriggersSectionMetaData.NAME_COL:
                return trigger.getName();
            case TriggersSectionMetaData.LEVEL_COL:
                if (trigger.getSybaseASABaseActionTime().getValue() == SybaseASABaseActionTime.ASE)
                {
                    return ""; //$NON-NLS-1$
                }
                return trigger.getActionGranularity().getName();
            case TriggersSectionMetaData.TRIGGER_TIME_COL:
                if (trigger.getSybaseASABaseActionTime().getValue() == SybaseASABaseActionTime.ASE)
                {
                    return ""; //$NON-NLS-1$
                }
                return trigger.getSybaseASABaseActionTime().getName();
            case TriggersSectionMetaData.EVENT_COL:
                StringBuffer sb = new StringBuffer(""); //$NON-NLS-1$
                if (trigger.isInsertType() && !trigger.isUpdateColumnType())
                {
                    sb.append(Messages.TriggersViewerLabelProvider_insert).append(","); //$NON-NLS-2$
                }
                if (trigger.isUpdateType() && !trigger.isUpdateColumnType())
                {
                    sb.append(Messages.TriggersViewerLabelProvider_update).append(","); //$NON-NLS-2$
                }
                if (trigger.isDeleteType() && !trigger.isUpdateColumnType())
                {
                    sb.append(Messages.TriggersViewerLabelProvider_delete).append(","); //$NON-NLS-2$
                }
                if (trigger.isUpdateColumnType())
                {
                    sb.append(Messages.TriggersViewerLabelProvider_updateof);
                    Iterator iter = trigger.getTriggerColumn().iterator();
                    while (iter.hasNext())
                    {
                        Column col = (Column) iter.next();
                        sb.append(col.getName()).append(","); //$NON-NLS-1$
                    }
                }
                if (sb.toString().length() > 0)
                {
                    return sb.toString().substring(0, sb.toString().length() - 1);
                }
                return ""; //$NON-NLS-1$
            case TriggersSectionMetaData.ORDER_COL:
                if (trigger.getSybaseASABaseActionTime().getValue() == SybaseASABaseActionTime.ASE)
                {
                    return ""; //$NON-NLS-1$
                }
                return Integer.toString(trigger.getOrder());
            default:
                break;
        }
        return ""; //$NON-NLS-1$
    }

    public void addListener(ILabelProviderListener listener)
    {

    }

    public void dispose()
    {

    }

    public boolean isLabelProperty(Object element, String property)
    {
        return false;
    }

    public void removeListener(ILabelProviderListener listener)
    {

    }
}

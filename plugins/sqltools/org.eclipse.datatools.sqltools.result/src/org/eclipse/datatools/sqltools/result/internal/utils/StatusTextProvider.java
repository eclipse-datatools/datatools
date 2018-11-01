/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.utils;

import org.eclipse.datatools.sqltools.result.OperationCommand;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.model.ResultItem;
import org.eclipse.osgi.util.NLS;

/**
 * Utilility class to get some status related message
 * @author Dafan Yang
 */
public class StatusTextProvider
{
    static String _LINESEPARATOR = System.getProperty("line.separator"); //$NON-NLS-1$
    
    /**
     * Gets the update count message given the update count
     * @param updateCount the update count
     * @return the update count message
     */
    public static String getUpdateCountText(int updateCount)
    {
        if(updateCount == 1)
        {
            return NLS.bind(Messages.StatusTextProvider_update_count_single, new Object[] 
			{
			    String.valueOf(updateCount)
			}) + _LINESEPARATOR; //$NON-NLS-1$
        }
        else
        {
            return NLS.bind(Messages.StatusTextProvider_update_count_complex, new Object[]
			{
			    String.valueOf(updateCount)
			}) + _LINESEPARATOR; //$NON-NLS-1$
        }
    }
    
    /**
     * Returns the status text for showing it in "status" tab
     * @param instance the given result instance
     * @return the status text for showing it in "status" tab
     */
    public static String getStatusText(IResultInstance instance)
    {
        StringBuffer buff = new StringBuffer(""); //$NON-NLS-1$
        
        // By default, the display string of this result is displayed in the "Status" tab
        buff.append(instance.getOperationCommand().getDisplayString()).append(_LINESEPARATOR).append(_LINESEPARATOR);
        int count = instance.getItemCount();
        for(int i = 0; i<count; i++)
        {
            ResultItem item = instance.getItem(i);
            if(item.getResultType() == ResultItem.STATUS_TEXT)
            {
                buff.append((String)item.getResultObject());
            }
            if(item.getResultType() == ResultItem.UPDATE_COUNT)
            {
                buff.append(getUpdateCountText(((Integer)item.getResultObject()).intValue()));
            }
        }
        return buff.toString();
    }
    
    /**
     * Returns the full history header for saving purpose
     * @param instance the given result instance
     * @return the full history header for saving purpose
     */
    public static String getHistoryHeader(IResultInstance instance)
    {
        StringBuffer buff = new StringBuffer(""); //$NON-NLS-1$
        buff.append(instance.getOperationCommand().getDisplayString()).append(_LINESEPARATOR);
        buff.append(Messages.StatusTextProvider_action_type).append(
                OperationCommand.getActionString(instance.getOperationCommand().getActionType()))
                .append(_LINESEPARATOR);
        buff.append(Messages.StatusTextProvider_profile_name).append(instance.getOperationCommand().getProfileName())
                .append(_LINESEPARATOR);
        buff.append(Messages.StatusTextProvider_database).append(instance.getOperationCommand().getDatabaseName())
                .append(_LINESEPARATOR);
        buff.append(Messages.StatusTextProvider_time).append(instance.getExecuteTime()).append(_LINESEPARATOR);
        buff.append(_LINESEPARATOR);
        int count = instance.getItemCount();
        for (int i = 0; i < count; i++)
        {
            ResultItem item = instance.getItem(i);
            if (item.getResultType() == ResultItem.STATUS_TEXT)
            {
                buff.append((String) item.getResultObject());
            }
        }
        return buff.toString();
    }
    
    /**
     * Returns the simple history header for saving purpose
     * @param instance the given result instance
     * @return the simple history header for saving purpose
     */
    public static String getSimpleHeader(IResultInstance instance)
    {
        StringBuffer buff = new StringBuffer(""); //$NON-NLS-1$
        buff.append(instance.getOperationCommand().getDisplayString()).append(_LINESEPARATOR);
        buff.append(_LINESEPARATOR);
        int count = instance.getItemCount();
        for (int i = 0; i < count; i++)
        {
            ResultItem item = instance.getItem(i);
            if (item.getResultType() == ResultItem.STATUS_TEXT)
            {
                buff.append((String) item.getResultObject());
            }
        }
        return buff.toString();
    }
}

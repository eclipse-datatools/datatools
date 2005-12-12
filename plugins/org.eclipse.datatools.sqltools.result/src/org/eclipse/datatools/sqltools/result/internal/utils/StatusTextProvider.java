/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.utils;

import org.eclipse.datatools.sqltools.result.internal.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.internal.model.ResultItem;

/**
 * Utilility class to get some status related message
 * @author Dafan Yang
 */
public class StatusTextProvider
{
    static String _LINESEPARATOR = System.getProperty("line.separator");
    
    /**
     * Get the update count message given the update count
     * @param updateCount the update count
     * @return
     */
    public static String getUpdateCountText(int updateCount)
    {
        if(updateCount == 1)
        {
            return Messages.getString("StatusTextProvider.update.count.single", String.valueOf(updateCount)) + _LINESEPARATOR;
        }
        else
        {
            return Messages.getString("StatusTextProvider.update.count.complex", String.valueOf(updateCount)) + _LINESEPARATOR;
        }
    }
    
    /**
     * 
     * @param instance
     * @return
     */
    public static String getStatusText(IResultInstance instance)
    {
        StringBuffer buff = new StringBuffer("");
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
}

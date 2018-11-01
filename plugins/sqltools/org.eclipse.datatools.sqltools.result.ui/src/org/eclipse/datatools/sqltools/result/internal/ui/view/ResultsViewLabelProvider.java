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
package org.eclipse.datatools.sqltools.result.internal.ui.view;

import org.eclipse.datatools.sqltools.result.OperationCommand;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.ui.ResultViewUIUtil;
import org.eclipse.datatools.sqltools.result.ui.view.ResultsViewControl;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * This is the label provider for the history results table
 * 
 * @author Dafan Yang
 */
public class ResultsViewLabelProvider implements ITableLabelProvider
{
    private static final String TAB = "\t";
    ResultsViewControl _resultsViewControl;
    
    public ResultsViewLabelProvider(ResultsViewControl resultsViewControl)
    {
        super();
        _resultsViewControl = resultsViewControl;
    }

    public Image getColumnImage(Object element, int columnIndex)
    {
        if (columnIndex == ResultHistoryHelper.STATUS_INDEX)
        {
            IResultInstance instance = (IResultInstance) element;
            int status = instance.getStatus();
            return ResultViewUIUtil.getOperationCommandStatusImage(status);
        }
        return null;
    }

    /**
     * Since the columns to display can be configured, label provider should be aware of the current columns setting
     */
    public String getColumnText(Object element, int columnIndex)
    {
        if (element instanceof IResultInstance)
        {
            IResultInstance instance = (IResultInstance) element;
            int columnDisplayIndex = ResultHistoryHelper.getConfigurableColumnIndex(columnIndex, _resultsViewControl.getUsePreferences());
            switch (columnDisplayIndex)
            {
                case ResultHistoryHelper.STATUS_INDEX:
                    int status = instance.getStatus();
                    return OperationCommand.getStatusString(status); 
                case ResultHistoryHelper.OPER_INDEX:
                    return compact(instance.getOperationCommand());
                case ResultHistoryHelper.FREQ_INDEX:
                    return Integer.toString(instance.getFrequency());
                case ResultHistoryHelper.DATE_INDEX:
                    return instance.getExecuteTime();
                case ResultHistoryHelper.ACTION_INDEX:
                    return OperationCommand.getActionString(instance.getOperationCommand().getActionType());
                case ResultHistoryHelper.CONSUMER_INDEX:
                    return instance.getOperationCommand().getConsumerName();
                case ResultHistoryHelper.PROFILE_INDEX:
                    return instance.getOperationCommand().getProfileName();
                default:
                    return "";
            }
        }
        else
        {
            // Should not happen
            return Messages.ResultViewLabelProvider_unknown; 
        }
    }
    
    /**
     * Returns the column text
     */
    public String getConfigurableColumnText(Object element, int columnIndex)
    {
        if (element instanceof IResultInstance)
        {
            int columnDisplayIndex = ResultHistoryHelper.getConfigurableColumnIndex(columnIndex, _resultsViewControl.getUsePreferences());
            IResultInstance instance = (IResultInstance) element;
            switch (columnDisplayIndex)
            {
                case ResultHistoryHelper.STATUS_INDEX:
                    int status = instance.getStatus();
                    return OperationCommand.getStatusString(status); 
                case ResultHistoryHelper.OPER_INDEX:
                    return compact(instance.getOperationCommand());
                case ResultHistoryHelper.FREQ_INDEX:
                    return Integer.toString(instance.getFrequency());
                case ResultHistoryHelper.DATE_INDEX:
                    return instance.getExecuteTime();
                case ResultHistoryHelper.ACTION_INDEX:
                    return OperationCommand.getActionString(instance.getOperationCommand().getActionType());
                case ResultHistoryHelper.CONSUMER_INDEX:
                    return instance.getOperationCommand().getConsumerName();
                case ResultHistoryHelper.PROFILE_INDEX:
                    return instance.getOperationCommand().getProfileName();
                default:
                    return "";
            }
        }
        else
        {
            // Should not happen
            return Messages.ResultViewLabelProvider_unknown; 
        }
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

    /**
     * The command may be multiple line or very long, we may want to compact it to be single line
     * 
     * @param operation the operation command instance
     * @return the string after being compacted
     */
    protected String compact(OperationCommand operation)
    {
        String r = operation.getDisplayString().replaceAll(System.getProperty("line.separator"), "  ");
        return r.replaceAll(TAB, " ");
    }
}

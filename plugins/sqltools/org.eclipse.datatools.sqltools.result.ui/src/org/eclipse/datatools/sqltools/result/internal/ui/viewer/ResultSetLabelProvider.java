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
package org.eclipse.datatools.sqltools.result.internal.ui.viewer;

import org.eclipse.datatools.sqltools.result.IResultSetObject;
import org.eclipse.datatools.sqltools.result.IResultSetRow;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.ui.PreferenceConstants;
import org.eclipse.datatools.sqltools.result.internal.ui.utils.PreferenceUtil;
import org.eclipse.datatools.sqltools.result.internal.utils.HexHelper;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.datatools.sqltools.result.ui.view.ResultsViewControl;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * Label provider for result set viewer
 * 
 * @author Dafan Yang
 *  
 */
public class ResultSetLabelProvider implements ITableLabelProvider
{
    private IResultSetObject _result;
    private boolean         _showRowCount;
    private ResultsViewControl _resultsViewControl;

    /**
     *  
     */
    public ResultSetLabelProvider(IResultSetObject result, boolean showRowCount, ResultsViewControl resultsViewControl)
    {
        _result = result;
        _showRowCount = showRowCount;
        _resultsViewControl = resultsViewControl;
    }

    public Image getColumnImage(Object element, int columnIndex)
    {
        if (columnIndex == 0 && _showRowCount)
        {
            return null;//for the row count column, should use image
        }
        return null;
    }

    public String getColumnText(Object element, int columnIndex)
    {
        if (_showRowCount)
        {
            if (columnIndex == 0)
            {
                return Integer.toString(((Integer) element).intValue() + 1);
            }
            columnIndex--;
        }
        IResultSetRow rowdata = _result.getRowData(((Integer) element).intValue());
        if(rowdata == null)
        {
            //should not happen
            IPreferenceStore store = ResultsViewUIPlugin.getDefault().getPreferenceStore();
            return PreferenceUtil.getString(store, PreferenceConstants.SQL_RESULTS_VIEW_NULL_STRING, _resultsViewControl.getUsePreferences());
        }
        Object data = rowdata.getData(columnIndex);
        if (data == null)
        {
            IPreferenceStore store = ResultsViewUIPlugin.getDefault().getPreferenceStore();
            return PreferenceUtil.getString(store, PreferenceConstants.SQL_RESULTS_VIEW_NULL_STRING, _resultsViewControl.getUsePreferences());
        }
        else
        {
            String outValue = ""; //$NON-NLS-1$
            if (data instanceof byte[])
            {
                byte[] os = (byte[]) data;
                outValue = HexHelper.toHexString(os);
            }
            else
            {
                outValue = data.toString();
            }
            if(outValue.length() > ResultSetViewer.LONG_BOUNDARY)
            {
                return Messages.ResultSetLabelProvider_longdata; 
            }
            return outValue;
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

}

/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.commonui;

import org.eclipse.datatools.sqltools.common.core.tableviewer.DataSerializer;
import org.eclipse.datatools.sqltools.common.core.tableviewer.IRowData;
import org.eclipse.datatools.sqltools.common.ui.tableviewer.TableDataLabelProvider;

/**
 * 
 * @author Hui Cao
 * 
 */
public class ParameterVariableSectionLabelProvider extends TableDataLabelProvider
{
    public String getColumnText(Object element, int columnIndex)
    {

        if (!(element instanceof IRowData))
        {
            return ""; //$NON-NLS-1$ //$NON-NLS-2$
        }

        IRowData row = (IRowData) element;

        Object o = row.getValue(columnIndex);
        String s = DataSerializer.serialize(o, row.getTableData().getColumnType(columnIndex));
//        if ( s == null)
//        {
//            //TODO move the preference location 455892
//            IPreferenceStore store = DebuggerCoreExtPlugin.getDefault().getPreferenceStore();
//            return store.getString(PreferenceConstants.TABLE_VIEW_DISPLAY_NULL); //$NON-NLS-1$
//        }
//        else
//        {
//            return s;
//        }

        return (s == null) ? "" : s; //$NON-NLS-1$

    }
}

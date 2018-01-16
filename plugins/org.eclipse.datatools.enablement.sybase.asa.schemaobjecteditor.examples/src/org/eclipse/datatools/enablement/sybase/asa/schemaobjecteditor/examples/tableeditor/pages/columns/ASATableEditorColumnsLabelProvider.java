/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.pages.columns;

import org.eclipse.datatools.sqltools.common.core.tableviewer.DataSerializer;
import org.eclipse.datatools.sqltools.common.core.tableviewer.IRowData;
import org.eclipse.datatools.sqltools.common.ui.tableviewer.TableDataLabelProvider;

/**
 * Columns viewer label provider
 * 
 * @author Idull
 */
public class ASATableEditorColumnsLabelProvider extends TableDataLabelProvider
{

    public String getColumnText(Object element, int columnIndex)
    {
        if (!(element instanceof IRowData))
        {
            return ""; //$NON-NLS-1$
        }
        if (columnIndex == ASATableEditorColumnsTableData.PRI_KEY_COLUMN
                || columnIndex == ASATableEditorColumnsTableData.NULLABLE_COLUMN
                || columnIndex == ASATableEditorColumnsTableData.UNIQUE_COLUMN)
        {
            return ""; //$NON-NLS-1$
        }
        IRowData row = (IRowData) element;

        Object o = row.getValue(columnIndex);
        String s = DataSerializer.serialize(o, row.getTableData().getColumnType(columnIndex));

        return (s == null) ? "" : s; //$NON-NLS-1$
    }

}

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
package org.eclipse.datatools.sqltools.result.internal.ui.export.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.sqltools.result.IResultSetObject;
import org.eclipse.datatools.sqltools.result.IResultSetRow;
import org.eclipse.datatools.sqltools.result.internal.ui.PreferenceConstants;
import org.eclipse.datatools.sqltools.result.internal.ui.view.ColumnAlignedResultItem;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;

/**
 * Action to copy the selected row(s)
 * 
 * @author Dafan Yang
 */
public class CopyRowsAction extends Action
{
    private TableViewer _table;
    private IResultSetObject _resultObj;

    public CopyRowsAction(String text, TableViewer table, IResultSetObject resultObj)
    {
        super(text);
        _table = table;
        _resultObj = resultObj;
    }

    public void run()
    {
        List rows = new ArrayList();
        if ( _table == null || _resultObj == null )
        {
            return;
        }
        ISelection s = _table.getSelection();
        if ( !(s instanceof IStructuredSelection) )
        {
            return;
        }
        IStructuredSelection ss = (IStructuredSelection) s;
        Iterator i = ss.iterator();
        while (i.hasNext())
        {
            Object object = i.next();
            if ( !(object instanceof Integer) )
            {
                continue;
            }
            IResultSetRow row = _resultObj.getRowData(((Integer) object).intValue());
            if ( row == null )
            {
                continue;
            }
            rows.add(row);
        }
        if(rows.size()==0)
        {
            return;
        }
        StringBuffer sb = new StringBuffer("");
        Iterator iter = rows.iterator();
        while(iter.hasNext())
        {
            IResultSetRow row = (IResultSetRow)iter.next();
            sb.append(ColumnAlignedResultItem.getDisplayData(_resultObj, row, ResultsViewUIPlugin.getDefault()
                    .getPreferenceStore().getString(PreferenceConstants.SQL_RESULTS_VIEW_NULL_STRING)));
            sb.append(ColumnAlignedResultItem.getLineSeparator());
        }
        
        Clipboard clipboard = new Clipboard(Display.getCurrent());
        Transfer[] transfer = new Transfer[]
        { TextTransfer.getInstance()};
        clipboard.setContents(new Object[]
        { sb.toString()}, transfer);
    }

}

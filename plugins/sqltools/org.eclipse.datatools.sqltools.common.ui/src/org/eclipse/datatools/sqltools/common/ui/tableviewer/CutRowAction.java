/**
 * Created on 2006-02-09
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.ui.tableviewer;

import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.common.core.tableviewer.IRowData;
import org.eclipse.datatools.sqltools.common.core.tableviewer.ITableData;
import org.eclipse.datatools.sqltools.common.ui.internal.Activator;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * 
 * @author lihuang
 * 
 */
public class CutRowAction extends Action
{

    protected Clipboard             _clipboard;
    protected IRowData            _removedRow;

    private AccessibleTableViewer _accessibleTableViewer;

    public CutRowAction(AccessibleTableViewer accessibleTableViewer, Clipboard clipboard)
    {
        setText(Messages.CutRowAction_text);
        _accessibleTableViewer = accessibleTableViewer;
        setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_CUT));
        _clipboard = clipboard;
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, HelpUtil.getContextId(IHelpConstants.CUT_ROW_ACTION, Activator.getDefault().getBundle().getSymbolicName()));
    }

    public void setViewer(AccessibleTableViewer accessibleTableViewer)
    {
        _accessibleTableViewer = accessibleTableViewer;
    }

    public void run()
    {
        if (_clipboard == null)
        {
            return;
        }

        TableDataTableCursor cursor = _accessibleTableViewer.getCursor();
        if (cursor.getVisible() == false || cursor.getRow() == null || !(cursor.getRow().getData() instanceof IRowData))
        {
            return;
        }

        // remove the selected IRowData from ITableData
        if (_accessibleTableViewer.getInput() instanceof ITableData)
        {
            ITableData tableData = (ITableData) _accessibleTableViewer.getInput();
            _removedRow = (IRowData) cursor.getRow().getData();
            _accessibleTableViewer.remove(_removedRow);
            tableData.deleteRow(_removedRow);


            // put the cut IRowData on clipboard
            Object[] data = new Object[]
            {
                _removedRow
            }
            ;
            Transfer[] transfers = new Transfer[]
            {
                ObjectTransfer.getInstance()
            }
            ;
            _clipboard.setContents(data, transfers);
            _accessibleTableViewer.refresh();
        }

    }

    public void updateEnablement()
    {
        setEnabled(false);
        if (_clipboard == null)
        {
            return;
        }

        if (_accessibleTableViewer==null||_accessibleTableViewer.getCursor().isDisposed()||_accessibleTableViewer.getCursor().getRow() == null || !(_accessibleTableViewer.getCursor().getRow().getData() instanceof IRowData) )
        {
            return;
        }
        setEnabled(true);
    }

}

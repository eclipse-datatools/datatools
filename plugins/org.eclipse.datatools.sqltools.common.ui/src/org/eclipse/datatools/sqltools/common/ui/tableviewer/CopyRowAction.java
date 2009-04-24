/**
 * Created on 2006-02-09
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.ui.tableviewer;


import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.common.core.tableviewer.IRowData;
import org.eclipse.datatools.sqltools.common.core.tableviewer.RowData;
import org.eclipse.datatools.sqltools.common.ui.internal.Activator;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * 
 * @author lihuang
 * 
 */
public class CopyRowAction extends Action
{
    private Clipboard             _clipboard;

    private AccessibleTableViewer _accessibleTableViewer;

    public CopyRowAction(AccessibleTableViewer accessibleTableViewer, Clipboard clipboard)
    {
        setText(Messages.CopyRowAction_text); 
        _accessibleTableViewer = accessibleTableViewer;
        setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
        _clipboard = clipboard;
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, HelpUtil.getContextId(IHelpConstants.COPY_ROW_ACTION, Activator.getDefault().getBundle().getSymbolicName()));
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

        RowData rowData = (RowData) cursor.getRow().getData();
        Object rData = null;

        try
        {
            rData = rowData.clone();
            // put IRowData on clipboard
            Object[] data = new Object[]
            {
                rData
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
        catch(Exception ex)
        {
            IStatus warning = new Status(IStatus.ERROR, Activator.PLUGIN_ID, 1,
                Messages.CopyRowAction_can_not_copy_info, ex); 
            ErrorDialog.openError(_accessibleTableViewer.getControl().getShell(), Messages.CopyRowAction_can_not_copy_title, null, warning); 
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

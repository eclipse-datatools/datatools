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
import org.eclipse.datatools.sqltools.common.core.tableviewer.ITableData;
import org.eclipse.datatools.sqltools.common.core.tableviewer.RowData;
import org.eclipse.datatools.sqltools.common.ui.internal.Activator;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * 
 * @author lihuang
 * 
 */
public class PasteRowAction extends Action
{

    protected Clipboard             _clipboard;
    protected int                   _insertIndex;

    protected AccessibleTableViewer _accessibleTableViewer;

    public PasteRowAction(AccessibleTableViewer accessibleTableViewer, Clipboard clipboard)
    {
        setText(Messages.PasteRowAction_text); 
        _accessibleTableViewer = accessibleTableViewer;
        setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
        _clipboard = clipboard;
        setEnabled(false);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, HelpUtil.getContextId(IHelpConstants.PASTE_ROW_ACTION, Activator.getDefault().getBundle().getSymbolicName()));
    }

    public void setViewer(AccessibleTableViewer accessibleTableViewer)
    {
        _accessibleTableViewer = accessibleTableViewer;
    }

    public void run()
    {
        if(!((ITableData)_accessibleTableViewer.getInput()).isNewRowDataAllowed())
        {
            return;
        }

        if (_clipboard == null || _accessibleTableViewer.getCursor().getVisible() == false)
        {
            return;
        }
        RowData rowData = (RowData) _clipboard.getContents(ObjectTransfer.getInstance());

        ITableData tableData = (ITableData) _accessibleTableViewer.getInput();

        Object rData = null;

        try
        {
            rData = rowData.clone();
            ((RowData)rData).setTableData(tableData);
            TableItem selectedItem = _accessibleTableViewer.getCursor().getRow(); 

            _insertIndex = selectedItem != null ? _accessibleTableViewer.getTable().indexOf(selectedItem) : 0; 
            tableData.insertRow((IRowData)rData, _insertIndex);
            _accessibleTableViewer.refresh();
        }
        catch(Exception ex)
        {
            IStatus warning = new Status(IStatus.ERROR, Activator.PLUGIN_ID, 1,
                Messages.PasteRowAction_can_not_paste_info, ex); 
            ErrorDialog.openError(_accessibleTableViewer.getControl().getShell(), Messages.PasteRowAction_can_not_paste_title, null, warning); 
        }
    }

    public void updateEnablement()
    {
        setEnabled(false);
        if (_clipboard == null)
        {
            return;
        }

        // Paste if clipboard contains pastable markers
        IRowData rowData = (IRowData) _clipboard.getContents(ObjectTransfer.getInstance());
        if (rowData == null)
        {
            return;
        }
        setEnabled(true);
    }
}

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
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * 
 * @author lihuang
 * 
 */
public class RemoveRowAction extends Action
{

    private AccessibleTableViewer _accessibleTableViewer;

    public RemoveRowAction(AccessibleTableViewer accessibleTableViewer)
    {
        setText(Messages.ReomveRowAction_text);
        _accessibleTableViewer = accessibleTableViewer;
        setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, HelpUtil.getContextId(IHelpConstants.REMOVE_ROW_ACTION, Activator.getDefault().getBundle().getSymbolicName()));

    }

    public void run()
    {
        if (_accessibleTableViewer.getCursor().getRow() == null || !(_accessibleTableViewer.getCursor().getRow().getData() instanceof IRowData))
        {
            return;
        }

        // remove the selected IRowData from ITableData
        if (_accessibleTableViewer.getInput() instanceof ITableData)
        {
            _accessibleTableViewer.doDelete();
            _accessibleTableViewer.refresh();
        }

    }

}

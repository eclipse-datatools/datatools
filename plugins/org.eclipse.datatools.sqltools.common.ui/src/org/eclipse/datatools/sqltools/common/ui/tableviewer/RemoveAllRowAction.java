/**
 * Created on 2006-02-09
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.ui.tableviewer;

import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.common.core.tableviewer.ITableData;
import org.eclipse.datatools.sqltools.common.ui.internal.Activator;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.PlatformUI;

/**
 * 
 * @author lihuang
 * 
 */
public class RemoveAllRowAction extends Action
{

    private AccessibleTableViewer _accessibleTableViewer;

    public RemoveAllRowAction(AccessibleTableViewer accessibleTableViewer)
    {
        setText(Messages.ReomveAllRowAction_text);
        _accessibleTableViewer = accessibleTableViewer;
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, HelpUtil.getContextId(IHelpConstants.REMOVE_ALL_ROW_ACTION, Activator.getDefault().getBundle().getSymbolicName()));


    }

    public void run()
    {

        // remove the selected IRowData from ITableData
        if (_accessibleTableViewer.getInput() instanceof ITableData)
        {
            _accessibleTableViewer.doDeleteAll(true);
            _accessibleTableViewer.refresh();
        }

    }

}

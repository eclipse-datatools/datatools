/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.sybase.ui;

/**
 * 
 * @author Cong Chen
 */
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.enablement.sybase.ui.SybaseUIPlugin;
import org.eclipse.datatools.enablement.sybase.IHelpConstants;
import org.eclipse.datatools.enablement.sybase.ui.util.DSEUtil;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;

public class ShowOwnerAction extends Action implements IObjectActionDelegate
{

    private Object       selectedElement;
    private CommonViewer viewer;

    public ShowOwnerAction()
    {
        PlatformUI.getWorkbench().getHelpSystem().setHelp(
                this,
                HelpUtil.getContextId(IHelpConstants.SHOW_OWNER_ACTION, SybaseUIPlugin.getDefault().getBundle()
                        .getSymbolicName()));
    }

    public void setActivePart(IAction action, IWorkbenchPart part)
    {
        IViewPart viewPart = part.getSite().getWorkbenchWindow().getActivePage().findView(DSEUtil.DSEID);
        viewer = ((CommonNavigator) viewPart).getCommonViewer();
    }

    public void run(IAction action)
    {
        SybaseDatabaseProfileSettingManager manager = SybaseDatabaseProfileSettingManager.getInstance();
        selectedElement = ((StructuredSelection) viewer.getSelection()).getFirstElement();
        if (selectedElement instanceof IConnectionProfile)
        {
            manager.setShowOwner(((IConnectionProfile) selectedElement).getInstanceID(), action.isChecked());
        }
        else
        {
            IConnectionProfile profile = DSEUtil.findParentProfileBySelectionElement();
            manager.setShowOwner(profile.getInstanceID(), ((SQLObject) selectedElement).getName(), action.isChecked());
        }
        viewer.refresh(selectedElement, true);
    }

    public void selectionChanged(IAction action, ISelection selection)
    {
        Object selectedObj = ((StructuredSelection) selection).getFirstElement();
        SybaseDatabaseProfileSettingManager manager = SybaseDatabaseProfileSettingManager.getInstance();
        if (selectedObj instanceof IConnectionProfile)
        {
            action.setChecked(manager.isShowOwner(((IConnectionProfile) selectedObj).getInstanceID()));
        }
        else if (selectedObj instanceof SQLObject)
        {
            IConnectionProfile profile = DSEUtil.findParentProfileBySelectionElement();
            action.setChecked(manager.isShowOwner(profile.getInstanceID(), ((SQLObject) selectedObj).getName()));
        }
    }

}

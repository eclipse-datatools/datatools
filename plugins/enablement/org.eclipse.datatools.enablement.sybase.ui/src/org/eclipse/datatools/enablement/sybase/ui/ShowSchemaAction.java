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
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
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

public class ShowSchemaAction extends Action implements IObjectActionDelegate, IContextProvider
{

    private Object                  selectedElement;
    private CommonViewer            viewer;
    private ContextProviderDelegate _contextProviderDelegate = new ContextProviderDelegate(SybaseUIPlugin.getDefault()
                                                                     .getBundle().getSymbolicName());

    public ShowSchemaAction()
    {
        PlatformUI.getWorkbench().getHelpSystem().setHelp(
                this,
                HelpUtil.getContextId(IHelpConstants.SHOW_SCHEMA_ACTION, SybaseUIPlugin.getDefault().getBundle()
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
            manager.setShowSchema(((IConnectionProfile) selectedElement).getInstanceID(), action.isChecked());
        }
        else
        {
            IConnectionProfile profile = DSEUtil.findParentProfileBySelectionElement();
            manager.setShowSchema(profile.getInstanceID(), ((SQLObject) selectedElement).getName(), action.isChecked());
        }
        viewer.refresh(selectedElement, true);
    }

    public void selectionChanged(IAction action, ISelection selection)
    {
        Object selectedObject = ((StructuredSelection) selection).getFirstElement();
        SybaseDatabaseProfileSettingManager manager = SybaseDatabaseProfileSettingManager.getInstance();
        if (selectedObject instanceof IConnectionProfile)
        {
            action.setChecked(manager.isShowSchema(((IConnectionProfile) selectedObject).getInstanceID()));
        }
        else if (selectedObject instanceof SQLObject)
        {
            IConnectionProfile profile = DSEUtil.findParentProfileBySelectionElement();
            action.setChecked(manager.isShowSchema(profile.getInstanceID(), ((SQLObject) selectedObject).getName()));
        }

    }

    public IContext getContext(Object target)
    {
        return _contextProviderDelegate.getContext(target);
    }

    public int getContextChangeMask()
    {
        return _contextProviderDelegate.getContextChangeMask();
    }

    public String getSearchExpression(Object target)
    {
        return _contextProviderDelegate.getSearchExpression(target);
    }

}

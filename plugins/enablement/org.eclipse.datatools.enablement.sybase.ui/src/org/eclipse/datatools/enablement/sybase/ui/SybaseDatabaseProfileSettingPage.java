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
import org.eclipse.datatools.enablement.sybase.IHelpConstants;
import org.eclipse.datatools.enablement.sybase.ui.util.DSEUtil;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;

public class SybaseDatabaseProfileSettingPage extends PreferencePage implements IWorkbenchPreferencePage,
        IContextProvider
{

    private Button                  btn_showSchema;
    private Button                  btn_showOwner;
    private boolean                 initSetting_showSchema;
    private boolean                 initSetting_showOwner;
    private ContextProviderDelegate _contextProviderDelegate = new ContextProviderDelegate(SybaseUIPlugin.getDefault()
                                                                     .getBundle().getSymbolicName());

    protected Control createContents(Composite parent)
    {
        IPreferenceStore store = SybaseUIPlugin.getDefault().getPreferenceStore();
        btn_showSchema = new Button(parent, SWT.CHECK);
        btn_showSchema.setText(JDBCProfileMessages.getString("SybaseDatabaseProfileSettingPage.showSchema"));//$NON-NLS-1$
        btn_showOwner = new Button(parent, SWT.CHECK);
        btn_showOwner.setText(JDBCProfileMessages.getString("SybaseDatabaseProfileSettingPage.showOwner"));//$NON-NLS-1$

        initSetting_showSchema = store.getBoolean(SybaseDatabaseProfileSettingManager.PREFERENCE_SHOW_SCHEMA);
        initSetting_showOwner = store.getBoolean(SybaseDatabaseProfileSettingManager.PREFERENCE_SHOW_OWNER);

        btn_showSchema.setSelection(initSetting_showSchema);
        btn_showOwner.setSelection(initSetting_showOwner);

        parent.getShell().setData(HelpUtil.CONTEXT_PROVIDER_KEY, this);
        HelpUtil.setHelp(parent.getShell(), HelpUtil.getContextId(IHelpConstants.PREFERENCES_PROFILE_SETTING, SybaseUIPlugin
                .getDefault().getBundle().getSymbolicName()));
        return new Composite(parent, SWT.NULL);
    }

    public void init(IWorkbench arg0)
    {

    }

    public boolean performOk()
    {
        IPreferenceStore store = SybaseUIPlugin.getDefault().getPreferenceStore();
        store.setValue(SybaseDatabaseProfileSettingManager.PREFERENCE_SHOW_SCHEMA, btn_showSchema.getSelection());
        store.setValue(SybaseDatabaseProfileSettingManager.PREFERENCE_SHOW_OWNER, btn_showOwner.getSelection());

        syncDatabaseProfileSettingManager(btn_showSchema.getSelection(), btn_showOwner.getSelection());

        CommonNavigator navigator = DSEUtil.getDSEView(PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                .getActivePage().getViewReferences());
        if (navigator != null)
        	navigator.getCommonViewer().refresh(true);

        return super.performOk();
    }

    protected void performApply()
    {
        syncDatabaseProfileSettingManager(btn_showSchema.getSelection(), btn_showOwner.getSelection());
    }

    protected void performDefaults()
    {
        btn_showSchema.setSelection(false);
        btn_showOwner.setSelection(true);
        super.performDefaults();
    }

    public boolean performCancel()
    {
        syncDatabaseProfileSettingManager(initSetting_showSchema, initSetting_showOwner);
        return super.performCancel();
    }

    private void syncDatabaseProfileSettingManager(boolean isShowSchema, boolean isShowOwner)
    {
        SybaseDatabaseProfileSettingManager manager = SybaseDatabaseProfileSettingManager.getInstance();
        manager.setShowSchemaGlobal(isShowSchema);
        manager.setShowOwnerGlobal(isShowOwner);
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

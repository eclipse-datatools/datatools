/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.preferences;

import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.SQLFilePreferenceConstants;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.SqlscrapbookPlugin;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.connection.AbstractConnectionInfoComposite;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.connection.ConnectionInfoComposite2;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqleditor.internal.IHelpContextIds;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;

/**
 * Preference page for SQL files/scrapbooks
 * @author Hui Cao
 */
public class SQLScrapbookPage extends PreferencePage implements IWorkbenchPreferencePage, Listener
{
    private IPreferenceStore _store                 = getPreferenceStore();

    private AbstractConnectionInfoComposite connBar;
    private Button _checkboxOverride = null;
    private Button _checkboxDefaultOpen = null;
    private Combo  _comboCommitMode = null; 
    private Label  _lableCommitMode = null;
    
    protected Control createContents(Composite parent)
    {
        PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), HelpUtil.getContextId(IHelpContextIds.PREFERENCES_SQL_FILES, SQLEditorPlugin.getDefault().getBundle().getSymbolicName()));

        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout compositeLayout = new GridLayout(1, true);
        compositeLayout.marginHeight = 0;
        compositeLayout.marginWidth = 0;
        composite.setLayout(compositeLayout);

        connBar = new ConnectionInfoComposite2(composite, SWT.NONE, this, null, null, AbstractConnectionInfoComposite.STYLE_SEPARATE_TYPE_NAME
                | AbstractConnectionInfoComposite.STYLE_SINGLE_GROUP | AbstractConnectionInfoComposite.STYLE_LAZY_INIT, PreferenceMessages.SQLFilePage_default_connection);
        connBar.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
        
        _checkboxOverride = new Button(composite, SWT.CHECK);
        _checkboxOverride.setText(PreferenceMessages.SQLFilePage_override_profile); //$NON-NLS-1$
        _checkboxOverride.addListener(SWT.Selection, this);
        
        _checkboxDefaultOpen = new Button(composite, SWT.CHECK);
        _checkboxDefaultOpen.setText(PreferenceMessages.SQLFilePage_default_open);
        _checkboxDefaultOpen.addListener(SWT.Selection, this);
        
        Composite modeComposite = new Composite(composite, SWT.NONE);
        modeComposite.setLayout(new GridLayout(2, true));
        _lableCommitMode = new Label(modeComposite, SWT.NONE);
        _lableCommitMode.setText(PreferenceMessages.SQLFilePage_commit_mode);
        
        _comboCommitMode = new Combo(modeComposite, SWT.NONE | SWT.READ_ONLY);
        _comboCommitMode.addListener(SWT.Selection, this);
        _comboCommitMode.add(PreferenceMessages.SQLFilePage_commit_auto);
        _comboCommitMode.add(PreferenceMessages.SQLFilePage_commit_manual);
        
        init();
        
        return composite;
    }

    private void init()
    {
        String dftConn = _store.getString(PreferenceConstants.SQLEDITOR_CONNECTION_INFO);
        ISQLEditorConnectionInfo connInfo = SQLEditorConnectionInfo.DEFAULT_SQLEDITOR_CONNECTION_INFO;
        if (dftConn != null && !dftConn.equals(""))
        {
            connInfo = SQLEditorConnectionInfo.decode(dftConn);
        }
        connBar.init(connInfo.getDatabaseVendorDefinitionId().toString(), connInfo.getConnectionProfileName(), connInfo.getDatabaseName());
        
        _checkboxOverride.setSelection(_store.getBoolean(PreferenceConstants.SQLFILE_SAVE_CONNECTION_INFO));
        
        _checkboxDefaultOpen.setSelection(_store.getBoolean(SQLFilePreferenceConstants.DEFAULT_OPEN));
        
        _comboCommitMode.select(_store.getInt(SQLFilePreferenceConstants.CONNECTION_COMMIT_MODE));
    }

    public void handleEvent(Event event)
    {
        // TODO Auto-generated method stub
        
    }

    public void init(IWorkbench workbench)
    {
        // TODO Auto-generated method stub
        
    }

    /*
     * Returns preference store that belongs to the our plugin.
     */
    protected IPreferenceStore doGetPreferenceStore()
    {
        return SqlscrapbookPlugin.getDefault().getPreferenceStore();
    }
    
    /**
     * Stores the values of the controls back to the preference store. This is called when the user presses the OK or
     * Apply button.
     */
    public boolean performOk()
    {
        _store.setValue(PreferenceConstants.SQLEDITOR_CONNECTION_INFO, connBar.getConnectionInfo().encode());
        _store.setValue(PreferenceConstants.SQLFILE_SAVE_CONNECTION_INFO, _checkboxOverride.getSelection());
        _store.setValue(SQLFilePreferenceConstants.DEFAULT_OPEN, _checkboxDefaultOpen.getSelection());
        _store.setValue(SQLFilePreferenceConstants.CONNECTION_COMMIT_MODE, _comboCommitMode.indexOf(_comboCommitMode.getText()));
        return super.performOk();
    }

    /*
     * This is called when the Defaults button is pressed.
     * 
     * @see PreferencePage.performDefaults()
     */
    protected void performDefaults()
    {
        super.performDefaults();
        
        String dftConn = _store.getDefaultString(PreferenceConstants.SQLEDITOR_CONNECTION_INFO);
        ISQLEditorConnectionInfo connInfo = SQLEditorConnectionInfo.DEFAULT_SQLEDITOR_CONNECTION_INFO;
        if (dftConn != null && !dftConn.equals(""))
        {
            connInfo = SQLEditorConnectionInfo.decode(dftConn);
        }
        connBar.init(connInfo.getDatabaseVendorDefinitionId().toString(), connInfo.getConnectionProfileName(), connInfo.getDatabaseName());
        
        _checkboxOverride.setSelection(_store.getDefaultBoolean(PreferenceConstants.SQLFILE_SAVE_CONNECTION_INFO));
        
        _checkboxDefaultOpen.setSelection(_store.getDefaultBoolean(SQLFilePreferenceConstants.DEFAULT_OPEN));
        
        int dftMode = _store.getDefaultInt(SQLFilePreferenceConstants.CONNECTION_COMMIT_MODE);
        _comboCommitMode.select(dftMode);
    }

}

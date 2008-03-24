/**
 * Created on 2005-3-14
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.sqleditor.preferences;

import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.common.ui.preferences.AbstractDBPreferenceFieldPage;
import org.eclipse.datatools.sqltools.common.ui.util.SWTUtils;
import org.eclipse.datatools.sqltools.sqleditor.internal.IHelpContextIds;
import org.eclipse.datatools.sqltools.sqleditor.internal.PreferenceConstants;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;

/**
 * @author Li Huang
 *  
 */
public class CodeAssistPage extends AbstractDBPreferenceFieldPage
{

    private Button _insertSingleProposal;

    private Button _showSystemTables;
    private Button _showSystemProcedures;
    private Button _showOwnerOfTable;

    private Button _enableAuto;
    private Label  _autoActivationDelay;
    private Text   _autoActivationDelayText;
    private Label  _autoActivationTriggers;
    private Text   _autoActivationTriggersText;

    private Button _showSystemViews;

    private IPreferenceStore _store = getPreferenceStore();

    public CodeAssistPage()
    {
        setDescription(PreferenceMessages.CodeAssist_description); 
        setPreferenceStore(SQLEditorPlugin.getDefault().getPreferenceStore());
    }

    private Composite createGeneralPage(Composite parent)
    {

        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout gridLayout = new GridLayout(1, true);
        gridLayout.marginHeight = 0;
        gridLayout.marginWidth = 0;
        composite.setLayout(gridLayout);
        GridData gridData = new GridData(GridData.FILL_BOTH);
        composite.setLayoutData(gridData);


        //Insert single proposals automatically
        _insertSingleProposal = SWTUtils.createCheckBox(composite, PreferenceMessages.CodeAssist_insertSingleProposals, 2);

        //show database objects group
        Group databaseGroup = SWTUtils.createGroup(composite, PreferenceMessages.CodeAssist_showSystemGroup, 2);
        _showSystemTables = SWTUtils.createCheckBox(databaseGroup, PreferenceMessages.CodeAssist_showSystemTables, 2);
		// show system views
        _showSystemViews = SWTUtils.createCheckBox(databaseGroup, PreferenceMessages.CodeAssist_showSystemViews, 2);
        _showSystemProcedures = SWTUtils.createCheckBox(databaseGroup, PreferenceMessages.CodeAssist_showSystemProcedures, 2);
        _showOwnerOfTable = SWTUtils.createCheckBox(databaseGroup, PreferenceMessages.CodeAssist_showOwnerOfTable, 2);

        //Auto activation group

        Group autoActivationGroup = SWTUtils.createGroup(composite, PreferenceMessages.CodeAssist_autoActivationGroup, 2);
        _enableAuto = SWTUtils.createCheckBox(autoActivationGroup, PreferenceMessages.CodeAssist_enableAuto, 2);
        _enableAuto.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                updateActivationGroup();
            }
        }
        );
        _autoActivationDelay = SWTUtils.createLabel(autoActivationGroup, PreferenceMessages.CodeAssist_autoActivationDelay, 1, 2);
        _autoActivationDelayText = SWTUtils.createTextBox(autoActivationGroup, 1, 25);
        _autoActivationTriggers = SWTUtils.createLabel(autoActivationGroup, PreferenceMessages.CodeAssist_autoActivationTriggers, 1, 2);
        _autoActivationTriggersText = SWTUtils.createTextBox(autoActivationGroup, 1, 25);

        return composite;
    }

    /*
     * Initializes states of the controls from the preference store.
     */
    protected void initializeValues()
    {
        super.initializeValues();
        _insertSingleProposal.setSelection(_store.getBoolean(PreferenceConstants.INSERT_SINGLE_PROPOSALS_AUTO));

        _showSystemTables.setSelection(_store.getBoolean(PreferenceConstants.SHOW_SYSTEM_TABLES));
        _showSystemProcedures.setSelection(_store.getBoolean(PreferenceConstants.SHOW_SYSTEM_PROCEDURES));
        _showOwnerOfTable.setSelection(_store.getBoolean(PreferenceConstants.SHOW_OWNER_OF_TABLE));

        _enableAuto.setSelection(_store.getBoolean(PreferenceConstants.ENABLE_AUTO_ACTIVATION));
        _autoActivationDelayText.setText(Integer.toString(_store.getInt(PreferenceConstants.AUTO_ACTIVATION_DELAY)));
        _autoActivationTriggersText.setText(_store.getString(PreferenceConstants.AUTO_ACTIVATION_TRIGGER));

        _showSystemViews.setSelection(_store.getBoolean(PreferenceConstants.SHOW_SYSTEM_VIEWS));

        updateEnablement();
    }


    /**
     * Stores the values of the controls back to the preference store. This is called when the user presses the OK or
     * Apply button.
     */
    public boolean performOk()
    {

        _store.setValue(PreferenceConstants.INSERT_SINGLE_PROPOSALS_AUTO, _insertSingleProposal.getSelection());

        _store.setValue(PreferenceConstants.SHOW_SYSTEM_TABLES, _showSystemTables.getSelection());
        _store.setValue(PreferenceConstants.SHOW_SYSTEM_PROCEDURES, _showSystemProcedures.getSelection());
        _store.setValue(PreferenceConstants.SHOW_OWNER_OF_TABLE, _showOwnerOfTable.getSelection());


        _store.setValue(PreferenceConstants.ENABLE_AUTO_ACTIVATION, _enableAuto.getSelection());
        _store.setValue(PreferenceConstants.AUTO_ACTIVATION_DELAY, Integer.parseInt(_autoActivationDelayText.getText()));
        _store.setValue(PreferenceConstants.AUTO_ACTIVATION_TRIGGER, _autoActivationTriggersText.getText());

        _store.setValue(PreferenceConstants.SHOW_SYSTEM_VIEWS, _showSystemViews.getSelection());

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

        _insertSingleProposal.setSelection(_store.getDefaultBoolean(PreferenceConstants.INSERT_SINGLE_PROPOSALS_AUTO));

        _showSystemTables.setSelection(_store.getDefaultBoolean(PreferenceConstants.SHOW_SYSTEM_TABLES));
        _showSystemProcedures.setSelection(_store.getDefaultBoolean(PreferenceConstants.SHOW_SYSTEM_PROCEDURES));
        _showOwnerOfTable.setSelection(_store.getDefaultBoolean(PreferenceConstants.SHOW_OWNER_OF_TABLE));

        _enableAuto.setSelection(_store.getDefaultBoolean(PreferenceConstants.ENABLE_AUTO_ACTIVATION));
        _autoActivationDelayText.setText(Integer.toString(_store.getDefaultInt(PreferenceConstants.AUTO_ACTIVATION_DELAY)));
        _autoActivationTriggersText.setText(_store.getDefaultString(PreferenceConstants.AUTO_ACTIVATION_TRIGGER));

        _showSystemViews.setSelection(_store.getDefaultBoolean(PreferenceConstants.SHOW_SYSTEM_VIEWS));

        updateEnablement();
    }

    /*
     * Returns preference store that belongs to the our plugin.
     */
    protected IPreferenceStore doGetPreferenceStore()
    {
        return SQLEditorPlugin.getDefault().getPreferenceStore();
    }




    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(IWorkbench workbench)
    {


    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
     */
    protected Control createContents(Composite parent)
    {
        PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), HelpUtil.getContextId(IHelpContextIds.PREFERENCES_CODE_ASSIST, SQLEditorPlugin.getDefault().getBundle().getSymbolicName()));

        Composite page = createGeneralPage(parent);

        initializeValues();
        // composite for vendor specific tabs
        Composite tabs = new Composite(page, SWT.NONE);
        tabs.setLayoutData(new GridData(GridData.FILL_BOTH));
        GridLayout gdl =new GridLayout();
        gdl.marginLeft = 0;
        gdl.marginWidth = 0;
        gdl.marginRight = 0;
        gdl.marginBottom = 0;
        tabs.setLayout(gdl);
        super.createContents(tabs); 

        return page;
    }

    /*
     * Enable controls according to selection
     */
    private void updateEnablement()
    {
        updateActivationGroup();
    }


    private void updateActivationGroup()
    {
        boolean activationEnabled = _enableAuto.getSelection();
        _autoActivationDelayText.setEnabled(activationEnabled);
        _autoActivationTriggersText.setEnabled(activationEnabled);
    }

    public String getPreferencePageId()
    {
        return "org.eclipse.datatools.sqltools.sqleditor.preferences.codeassist";
    }

}

/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

/**
 * TODO add preference page to set the default connection profile type
 * This type of composite separates the connection profile type drop down and the profile name drop down
 * <p>
 * Supported styles:
 * <li>STYLE_CREATE_PROFILE</li>
 * <li>STYLE_MUST_CONNECT</li>
 * <li>STYLE_LAZY_INIT</li>
 * <li>STYLE_SEPARATE_TYPE_NAME(always)</li>
 * <li>STYLE_SINGLE_GROUP/STYLE_LABEL_GROUP</li>
 * <li>STYLE_SHOW_STATUS</li>
 * </p>
 * @author Hui Cao
 * 
 */
public class ConnectionInfoComposite2 extends AbstractConnectionInfoComposite implements SelectionListener,
Listener {
    private Label _labelName = null;

    private Combo _comboProfileName = null;

    private Combo _comboType = null;

    private Label _labelType = null;

    private Label _labelStatus = null;
    
    private Label _labelDbName = null;

    private Combo _combodbName = null;

    private Button _create = null;

    public ConnectionInfoComposite2(Composite parent, int style, Listener listener, ISQLEditorConnectionInfo connInfo,
            Collection supportedDBDefinitionNames, int infoStyle)
    {
        super(parent, style, listener, connInfo, supportedDBDefinitionNames, STYLE_SEPARATE_TYPE_NAME | infoStyle);
        createContents();
        if ((_style & STYLE_LAZY_INIT ) == 0)
        {
            init();
        }        
    }

    protected Control createContents() {
        this.setLayoutData(new GridData(GridData.FILL_BOTH));
        org.eclipse.swt.layout.GridLayout gridLayout1 = new GridLayout();
        gridLayout1.marginWidth = 0;
        gridLayout1.marginHeight = 0;
        
        Group group = null;
        if ((_style & STYLE_SINGLE_GROUP) > 0 )
        {
            //STYLE_SINGLE_GROUP
            gridLayout1.numColumns = 1;
            group = new Group(this, SWT.NONE);
            group.setText(Messages.ConnectionInfoGroup_profile_group);
        }
        else
        {
            gridLayout1.numColumns = 2;    
            Label prefix = new Label(this, SWT.NONE);
            prefix.setText(Messages.ConnectionInfoGroup_profile);
            prefix.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER, GridData.VERTICAL_ALIGN_END, false, true));
            group = new Group(this, SWT.NONE);
        }
        this.setLayout(gridLayout1);
        createGroupContents(group);

        return this;
    }
    
    private void createGroupContents(Group group)
    {
        GridData gridData = new GridData(SWT.FILL, GridData.VERTICAL_ALIGN_BEGINNING, true, false);
        group.setLayoutData(gridData);
        
        // add controls to composite as necessary
        org.eclipse.swt.layout.GridLayout gridLayout1 = new GridLayout();
        org.eclipse.swt.layout.GridData gridData3 = new org.eclipse.swt.layout.GridData();

        gridLayout1.marginWidth = 5;
        gridLayout1.marginHeight = 5;

        if ((_style & STYLE_SHOW_STATUS) > 0)
        {
            gridLayout1.numColumns = 8;
        }
        else
        {
            gridLayout1.numColumns = 6;
        }
        group.setLayout(gridLayout1);
        
        _labelType = new Label(group, SWT.NONE);
        _labelType.setText(Messages.ConnectionInfoGroup_type); //$NON-NLS-1$
        createComboType(group);

        _labelName = new Label(group, SWT.NONE);
        _labelName.setText(Messages.ConnectionInfoGroup_name); //$NON-NLS-1$
        //connectivity layer does not associate profile with database defintions,
        //so we have to use UIComponentService to create those wizards
        Composite compositeName = new Composite(group, SWT.NONE);
        gridData3.grabExcessHorizontalSpace = true;
        gridData3.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        compositeName.setLayoutData(gridData3);
        createComboProfileName(compositeName);

        _labelDbName = new Label(group, SWT.NONE);
        _labelDbName.setText(Messages.ConnectionInfoGroup_database);
        createComboDbName(group);

        if ((_style & STYLE_SHOW_STATUS) > 0)
        {
            Label label = new Label(group, SWT.NONE);
            label.setText(Messages.ConnectionInfoGroup_status);
            
            _labelStatus = new Label(group, SWT.NONE);
            _labelStatus.setText(getStatus());
        }
    }

    /**
     * This method initializes _comboType
     * 
     */
    private void createComboType(Composite composite) {
        // com.sybase.stf.servers.core.category.database
        org.eclipse.swt.layout.GridData gridData4 = new org.eclipse.swt.layout.GridData();
        _comboType = new Combo(composite, SWT.NONE | SWT.READ_ONLY);
        gridData4.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData4.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        gridData4.grabExcessHorizontalSpace = true;
        _comboType.setLayoutData(gridData4);
        _comboType.setVisibleItemCount(20);

        _comboType.addSelectionListener(this);

    }

    private void createComboProfileName(Composite composite) {
        org.eclipse.swt.layout.GridLayout gridLayout2 = new GridLayout();
        if ((_style & STYLE_CREATE_PROFILE) > 0)
        {
            gridLayout2.numColumns = 2;
        }
        else
        {
            gridLayout2.numColumns = 1;
        }
        gridLayout2.marginHeight = 0;
        gridLayout2.marginWidth = 0;
        composite.setLayout(gridLayout2);

        org.eclipse.swt.layout.GridData gridData5 = new org.eclipse.swt.layout.GridData();
        _comboProfileName = new Combo(composite, SWT.NONE | SWT.READ_ONLY);
        gridData5.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData5.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        gridData5.grabExcessHorizontalSpace = true;
        _comboProfileName.setVisibleItemCount(30);
        _comboProfileName.setLayoutData(gridData5);

        if ((_style & STYLE_CREATE_PROFILE) > 0)
        {
            _create = new Button(composite, SWT.PUSH);
            _create.setText(Messages.SelectProfileDialog_create); //$NON-NLS-1$
            _create.addListener(SWT.Selection, this);
        }

        _comboProfileName.addSelectionListener(this);
    }

    private void createComboDbName(Composite composite) {
        org.eclipse.swt.layout.GridData gridData5 = new org.eclipse.swt.layout.GridData();
        _combodbName = new Combo(composite, SWT.READ_ONLY);
        gridData5.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData5.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
        gridData5.grabExcessHorizontalSpace = true;
        _combodbName.setVisibleItemCount(30);
        _combodbName.setLayoutData(gridData5);

        if (_comboProfileName.getSelectionIndex() == -1) {
            _combodbName.setEnabled(false);
        }

        _combodbName.addFocusListener(new FocusListener()
        {

            public void focusGained(FocusEvent e)
            {
                initDBNames();
            }

            public void focusLost(FocusEvent e)
            {

            }
        });

        _combodbName.addSelectionListener(this);

    }

    /**
     * Tries to set the database vendor definition combo box when a connection
     * profile name is specified (during initialization or connection profile
     * creation)
     */
    private void initTypebyProfile(String profileName) {
        if (profileName == null || profileName.equals(""))
        {
            return;
        }
        SQLDevToolsConfiguration factory = SQLToolsFacade
                .getConfigurationByProfileName(profileName);
        if (factory != null) {
            String dbDefName = factory.getDatabaseVendorDefinitionId()
                    .toString();
            if (!dbDefName.equals(_comboType.getText()))
            {
                _comboType.setText(dbDefName);
            }
        }
    }

    /**
     * Refreshes the connection profile name combo box
     * TODO add all profiles when type is null or undefined so that user can select profile first
     * @param dbVendorName
     */
    private void initProfileNames(String dbVendorName, String initialProfName) {
        ArrayList rightProfiles = new ArrayList();
        IConnectionProfile profiles[] = ProfileManager.getInstance().getProfiles();
        if (dbVendorName == null || dbVendorName.equals("") || dbVendorName.equals(DATABASE_VENDOR_DEFINITION_ID.toString())) { //$NON-NLS-1$
            for (int i = 0; i < profiles.length; i++) {
                rightProfiles.add(profiles[i].getName());
            }
        }
        else
        {
            DatabaseVendorDefinitionId selectedDbVendorId = new DatabaseVendorDefinitionId(dbVendorName);
            SQLDevToolsConfiguration selectedConfig = SQLToolsFacade.getConfiguration(null, selectedDbVendorId);
            SQLDevToolsConfiguration defaultConfig = SQLToolsFacade.getDefaultConfiguration();
            // there will be only one instance for each type of configuration, so we
            // can use == here
            boolean isDefault = selectedConfig == defaultConfig;
            

            for (int i = 0; i < profiles.length; i++) {
                DatabaseVendorDefinitionId dbVendorId = ProfileUtil
                        .getDatabaseVendorDefinitionId(profiles[i].getName());
                if (selectedDbVendorId.equals(dbVendorId)) {
                    rightProfiles.add(profiles[i].getName());
                }else if (isDefault)
                {
                    SQLDevToolsConfiguration config = SQLToolsFacade.getConfiguration(null, dbVendorId);
                    if (selectedConfig == config)
                    {
                        rightProfiles.add(profiles[i].getName());
                    }
                }
            }

        }

        Collections.sort(rightProfiles);

        rightProfiles.add(0, new String("")); //$NON-NLS-1$
        _comboProfileName.setItems((String[]) rightProfiles
                .toArray(new String[] {}));
        if (initialProfName != null) {
            for (Iterator iter = rightProfiles.iterator(); iter.hasNext();) {
                if (iter.next().equals(initialProfName)) {
                    _comboProfileName.setText(initialProfName);
                    break;
                }

            }
        }
    }

    void initDBNames()
    {
        _combodbName.removeAll();
        if (_profileName != null)
        {
            List list = ProfileUtil.getDatabaseList(_profileName, _mustConnect);
            Iterator iterator = list.iterator();
            while (iterator.hasNext())
            {
                String dbname = iterator.next().toString();
                _combodbName.add(dbname);
            }
        }
        if (_dbName != null)
        {
            if (_combodbName.getItemCount() == 0)
            {
                _combodbName.add(_dbName);
            }
            _combodbName.setText(_dbName);
        }
        _combodbName.add("", 0);
    }


    protected void readControlValues() {
        if (_comboType.getText() != null && !"".equals(_comboType.getText())) //$NON-NLS-1$
        {
            _dbVendorId = new DatabaseVendorDefinitionId(_comboType.getText());
        }
        else
        {
            _dbVendorId = DATABASE_VENDOR_DEFINITION_ID;
        }
        // set _profileName to "" has no meaning
        if (_comboProfileName.getText() != null
                && !"".equals(_comboProfileName.getText())) { //$NON-NLS-1$
            _profileName = _comboProfileName.getText();
        } else {
            _profileName = null;
        }
        if (_combodbName != null && _combodbName.getText() != null
                && !"".equals(_combodbName.getText())) { //$NON-NLS-1$
            _dbName = _combodbName.getText();
        }
        else
        {
            _dbName = null;
        }

    }

    public Combo getDbNamesControl()
    {
        return _combodbName;
    }

    public Combo getProfileNamesControl()
    {
        return _comboProfileName;
    }

    public void init(String dbVendorName, String initialProfName, String initialDBName)
    {
        setConnectionInfo(dbVendorName, initialProfName, initialDBName);
        //init type
        if (_supportedDBDefinitionNames == null)
        {
            _supportedDBDefinitionNames = SQLToolsFacade.getSupportedDBDefinitionNames();
        }
        _comboType.setItems((String[]) _supportedDBDefinitionNames
                .toArray(new String[0]));
        _comboType.add("", 0);//add empty type to the first element to group all connection profiles
        if (_profileName != null) {
            initTypebyProfile(_profileName);
        } else if (_supportedDBDefinitionNames.contains(_dbVendorId.toString())) {
            _comboType.setText(_dbVendorId.toString());
        } else if (_supportedDBDefinitionNames.size() > 0) {
            _comboType.select(0);
        }
        
        //init name
        initProfileNames(_dbVendorId.toString(), _profileName);

        //init db
        IConnectionProfile connectionProfile = ProfileManager.getInstance().getProfileByName(_profileName);
        if (ProfileUtil.isDatabaseProfile(connectionProfile))
        {
            _combodbName.setEnabled(true);
        }
        else
        {
            _combodbName.setEnabled(false);
        }        
        initDBNames();
        
        if (_labelStatus != null)
        {
            _labelStatus.setText(getStatus());
        }
    }

    public void widgetDefaultSelected(SelectionEvent e)
    {
        // TODO Auto-generated method stub
        
    }

    public void widgetSelected(SelectionEvent e)
    {
        readControlValues();
        if (e.widget == _comboType) {
            initProfileNames(_comboType.getText(), null);
        }
        else if (e.widget == _comboProfileName)
        {
            initTypebyProfile(_profileName);
            // TODO when DatabaseDefinition.supportsCatalog is introduced, adjust _combodbName accordingly
            if (_comboProfileName.getSelectionIndex() != -1)
            {
                if (_combodbName != null)
                {
                    _combodbName.removeAll();
                    _dbName = null;

                    if (_profileName != null)
                    {
                        IConnectionProfile connectionProfile = ProfileManager.getInstance().getProfileByName(
                                _profileName);
                        if (ProfileUtil.isDatabaseProfile(connectionProfile))
                        {
                            _combodbName.setEnabled(true);
                        }
                        else
                        {
                            _combodbName.setEnabled(false);
                        }
                    }
                    else
                    {
                        _combodbName.setEnabled(false);
                    }
                }
            }
            else
            {
                if (_combodbName != null)
                {
                    _combodbName.removeAll();
                    _combodbName.setEnabled(false);
                }
            }
        }
        updateFields();
        notifyListener();        
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
     */
    public void handleEvent(Event event) {
        if (event.widget == _create) {
            SQLDevToolsConfiguration f = SQLToolsFacade.getConfigurationByVendorIdentifier(_dbVendorId);
            if (f == null)
            {
                return;
            }
            IWizard wizard = f.getUIComponentService().getProfileWizard();
            String[] currentNames = getCurrentProfileNames();
            WizardDialog dlg = new WizardDialog(getShell(), wizard);
            int id = dlg.open();
            if (id != IDialogConstants.CANCEL_ID)
            {
                // refresh all the profile info so that we can select the newly
                // created one
                String[] newNames = getCurrentProfileNames();
                String newProfile = getNewProfileName(currentNames, newNames);
                if (newProfile != null)
                {
                    init(_dbVendorId.toString(), newProfile, null);
                    updateFields();
                }
                notifyListener();
            }
        }
    }

    private String getStatus()
    {
        if (_connInfo!= null && _connInfo.isConnected())
        {
            return Messages.ConnectionInfoGroup_status_connected;
        }
        else
        {
            return Messages.ConnectionInfoGroup_status_disconnected;
        }
    }
    
    public void refreshConnectionStatus()
    {
        if (_labelStatus != null)
        {
            _labelStatus.setText(getStatus());
        }
    }
}

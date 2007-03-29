/***********************************************************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorConnectionInfo;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

/**
 * A reusable component for user to specify database vendor definition, (optional) connection profile name, (optional)
 * database name. This composite won't create connections.
 * 
 * @author Hui Cao
 * 
 */
public class ConnectionInfoComposite extends Composite implements SelectionListener, Listener
{
    protected static final DatabaseVendorDefinitionId DATABASE_VENDOR_DEFINITION_ID = SQLToolsFacade
                                                                                            .getDefaultDatabaseVendorDefinitionId();

    protected Pattern                                 PROFILE_AND_TYPE              = Pattern.compile("(.*)--(.*)\\z");

    private GridLayout                                _layout                       = null;
    
    private Label                                     _labelName                    = null;

    private Combo                                     _comboProfileName             = null;

    private Button                                    _create                       = null;

    private Label                                     _labelDbName                  = null;

    private Combo                                     _combodbName                  = null;

    protected String                                  _profileName                  = null;

    protected String                                  _dbName                       = null;

    protected DatabaseVendorDefinitionId              _dbVendorId                   = DATABASE_VENDOR_DEFINITION_ID;

    // The listener to notify of events
    protected Listener                                _listener;

    protected ISQLEditorConnectionInfo                _connInfo                     = null;

    private boolean                                   _mustConnect                  = false;

    private boolean                                   _createProfile                = false;

    /**
     * The supported database definition name list. If it is null, the default database definition name list will be
     * returned.
     */
    private Collection                                _supportedDBDefinitionNames   = null;

    public ConnectionInfoComposite(Composite parent, Listener listener, String profileName, String dbName)
    {
        this(parent, listener, new SQLEditorConnectionInfo(null, profileName, dbName), null, false, false, null, false);
    }

    /**
     * 
     * Creates a new Connection Info Group with the given profileName, dbName, supportedDBDefinitionNames. After the
     * construction, clients should call init() to populate the controls.
     * 
     * @param parent The parent widget of the group
     * @param connInfo The connection information
     * @see SQLEditorConnectionInfo
     * @param supportedDBDefinitionNames The supported datbase definition name list.
     * @param mustConnect if the connection profile must connect or not to retrieve the database list.
     * @param createProfiel if the create connection profile button show be visible
     * @param layout The GridLayout used to customize this composite's layout, can be null.
     * @param lazyInit Tells the composite whether to populates the controls when asked to do so @see init()
     */
    public ConnectionInfoComposite(Composite parent, Listener listener, ISQLEditorConnectionInfo connInfo,
            Collection supportedDBDefinitionNames, boolean mustConnect, boolean createProfile, GridLayout layout, boolean lazyInit)
    {
        super(parent, SWT.NONE);
        if (connInfo != null)
        {
            this._profileName = connInfo.getConnectionProfileName();
            this._dbName = connInfo.getDatabaseName();
            if (connInfo.getDatabaseVendorDefinitionId() == null)
            {
                this._dbVendorId = DATABASE_VENDOR_DEFINITION_ID;
            }
            else
            {
                this._dbVendorId = connInfo.getDatabaseVendorDefinitionId();
            }
        }

        this._listener = listener;
        _supportedDBDefinitionNames = supportedDBDefinitionNames;
        this._mustConnect = mustConnect;
        this._createProfile = createProfile;
        this._layout = layout;
        createContents();
        if (!lazyInit)
        {
            init();
        }
    }

    /**
     * Returns the <code>ISQLEditorConnectionInfo</code> object specified by user. This should be called after
     * {@link #finish()}.
     * 
     * @return <code>ISQLEditorConnectionInfo</code> containing all the information specified by user
     */
    public ISQLEditorConnectionInfo getConnectionInfo()
    {
        return _connInfo;
    }

    protected Control createContents()
    {
        // add controls to composite as necessary
        GridLayout gridLayout2 = new GridLayout();
        GridData gridData3 = new GridData();
        GridData gridData4 = new GridData();
        GridData gridData5 = new GridData();
        GridData gridData6 = new GridData();

        gridData6.horizontalAlignment = GridData.HORIZONTAL_ALIGN_FILL;
        this.setLayoutData(gridData6);
        if (_layout == null)
        {
            _layout = new GridLayout();
            _layout.marginWidth = 0;
            _layout.numColumns = 4;
            _layout.marginHeight = 0;
            _layout.marginWidth = 12;
        }
        this.setLayout(_layout);

        _labelName = new Label(this, SWT.NONE);
        _labelName.setText(Messages.SelectProfileDialog_profile_name); //$NON-NLS-1$
        gridData4.horizontalAlignment = GridData.END;
        _labelName.setLayoutData(gridData4);
        // connectivity layer does not associate profile with database defintions,
        // so we have to use UIComponentService to create those wizards
        Composite compositeName = new Composite(this, SWT.NONE);
        gridData3.grabExcessHorizontalSpace = true;
        gridData3.horizontalAlignment = GridData.FILL;
        compositeName.setLayoutData(gridData3);
        gridLayout2.numColumns = 2;
        gridLayout2.marginHeight = 0;
        gridLayout2.marginWidth = 0;
        compositeName.setLayout(gridLayout2);
        createComboProfileName(compositeName);

        if (_createProfile)
        {
            _create = new Button(compositeName, SWT.PUSH);
            _create.setText(Messages.SelectProfileDialog_create); //$NON-NLS-1$
            _create.addListener(SWT.Selection, this);
        }
        _labelDbName = new Label(this, SWT.NONE);
        _labelDbName.setText(Messages.ConnectionInfoGroup_database_name);
        gridData5.horizontalAlignment = GridData.END;
        _labelDbName.setLayoutData(gridData5);
        createComboDbName(this);

        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetSelected(SelectionEvent e)
    {
        if (e.widget == _comboProfileName)
        {
            // TODO when DatabaseDefinition.supportsCatalog is introduced, adjust _combodbName accordingly
            if (_comboProfileName.getSelectionIndex() != -1)
            {
                if (_combodbName != null)
                {
                    _combodbName.removeAll();
                    _dbName = null;

                    // for non-database profile, this combo box is always
                    // disabled.
                    readControlValues();
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
     * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetDefaultSelected(SelectionEvent e)
    {
        // TODO Auto-generated method stub

    }

    /**
     * Looks up the control and get the values
     */
    protected void readControlValues()
    {
        String text = _comboProfileName.getText();
        if (text != null && !"".equals(text)) { //$NON-NLS-1$
            Matcher m = PROFILE_AND_TYPE.matcher(text);
            if (m.find())
            {
                _dbVendorId = new DatabaseVendorDefinitionId(m.group(1));
                String profileName = m.group(2);
                _profileName = profileName == null || profileName.equals("") ? null : profileName;
            }
            else
            {
                _dbVendorId = new DatabaseVendorDefinitionId(text);
                _profileName = null;
            }
        }
        else
        {
            _dbVendorId = DATABASE_VENDOR_DEFINITION_ID;
            _profileName = null;
        }
        if (_combodbName != null && _combodbName.getText() != null && !"".equals(_combodbName.getText())) { //$NON-NLS-1$
            _dbName = _combodbName.getText();
        }
    }

    /**
     * 
     */
    protected void updateFields()
    {

        readControlValues();
        _connInfo = new SQLEditorConnectionInfo(_dbVendorId, _profileName, _dbName);
    }

    /**
     * 
     */
    private void notifyListener()
    {
        // fire an event so the parent can update its controls
        if (_listener != null)
        {
            Event changeEvent = new Event();
            changeEvent.type = SWT.Selection;
            changeEvent.widget = this;
            _listener.handleEvent(changeEvent);
        }
    }

    /**
     * This method initializes _comboProfileName
     * 
     */
    private void createComboProfileName(Composite composite)
    {
        GridData gridData5 = new GridData();
        _comboProfileName = new Combo(composite, SWT.NONE | SWT.READ_ONLY);
        gridData5.horizontalAlignment = GridData.FILL;
        gridData5.verticalAlignment = GridData.CENTER;
        gridData5.grabExcessHorizontalSpace = true;
        _comboProfileName.setLayoutData(gridData5);
        _comboProfileName.setVisibleItemCount(30);
        _comboProfileName.addSelectionListener(this);
    }

    private void createComboDbName(Composite composite)
    {
        GridData gridData5 = new GridData();
        // see bug 132294, when database name can not be loaded, we should allow
        // user to input manually. But here no database won't prevent user from editing
        _combodbName = new Combo(composite, SWT.READ_ONLY);
        gridData5.horizontalAlignment = GridData.FILL;
        gridData5.verticalAlignment = GridData.CENTER;
        gridData5.grabExcessHorizontalSpace = true;
        _combodbName.setVisibleItemCount(30);
        _combodbName.setLayoutData(gridData5);

        if (_comboProfileName.getSelectionIndex() == -1)
        {
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
     * Initializes the controls
     */
    public void init()
    {
        init(_dbVendorId.toString(), _profileName, _dbName);
    }

    /**
     * Refreshes the connection profile name combo box
     * 
     * @param dbVendorName
     */
    public void init(String dbVendorName, String initialProfName, String initialDBName)
    {
        setConnectionInfo(dbVendorName, initialProfName, initialDBName);
        
        IConnectionProfile profiles[] = ProfileManager.getInstance().getProfiles();
        if (_supportedDBDefinitionNames == null)
        {
            _supportedDBDefinitionNames = SQLToolsFacade.getSupportedDBDefinitionNames();
        }

        ArrayList rightProfiles = new ArrayList();
        String selectedName = null;

        for (int i = 0; i < profiles.length; i++)
        {
            DatabaseVendorDefinitionId dbVendorId = ProfileUtil
                    .getDatabaseVendorDefinitionId(profiles[i].getName());
            if (_supportedDBDefinitionNames.contains(dbVendorId.toString()))
            {
                String itemName = constructItemName(dbVendorId.toString(), profiles[i].getName());
                rightProfiles.add(itemName);
                if (_profileName != null && profiles[i].getName().equals(_profileName))
                {
                    selectedName = itemName;
                }
            }
        }
        for (Iterator it = _supportedDBDefinitionNames.iterator(); it.hasNext();)
        {
            String name = (String) it.next();
            rightProfiles.add(constructItemName(name, ""));
        }

        Collections.sort(rightProfiles);

        _comboProfileName.setItems((String[]) rightProfiles.toArray(new String[]
        {}));

        if (selectedName == null)
        {
            if (_dbVendorId != null)
            {
                selectedName = constructItemName(_dbVendorId.toString(), "");
            }
            else
            {
                SQLDevToolsConfiguration defaultConfig = SQLToolsFacade.getDefaultConfiguration();
                selectedName = constructItemName(defaultConfig.getDatabaseVendorDefinitionId().toString(), "");
            }
        }

        if (selectedName != null)
        {
            _comboProfileName.setText(selectedName);
        }
        else if (_comboProfileName.getItemCount() > 0)
        {
            _comboProfileName.select(0);
        }

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
        if (_dbName != null && !_dbName.equals(""))
        {
            _combodbName.setText(_dbName);
        }

        updateFields();
    }

    private void initDBNames()
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
    }

    private void setConnectionInfo(String dbVendorName, String initialProfName, String initialDBName)
    {
        _profileName = initialProfName;
        _dbName = initialDBName;
        if (dbVendorName == null)
        {
            _dbVendorId = DATABASE_VENDOR_DEFINITION_ID;
        }
        else
        {
            _dbVendorId = new DatabaseVendorDefinitionId(dbVendorName);
        }
        _connInfo = new SQLEditorConnectionInfo(_dbVendorId, _profileName, _dbName);
        
    }
    
    private String constructItemName(String type, String name)
    {
        if (name == null || name.trim().equals(""))
        {
            return type;
        }
        return type + "--" + name;
    }

    /**
     * Returns the ProfileNames control for this Connection Info group.
     * <p>
     * May return <code>null</code> if the control has not been created yet.
     * </p>
     * 
     * @return the ProfileNames control or <code>null</code>
     */
    public Combo getProfileNamesControl()
    {
        return _comboProfileName;
    }

    /**
     * Returns the DbNames control for this Connection Info group.
     * <p>
     * May return <code>null</code> if the control has not been created yet.
     * </p>
     * 
     * @return the DbNames control or <code>null</code>
     */
    public Combo getDbNamesControl()
    {
        return _combodbName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
     */
    public void handleEvent(Event event)
    {
        if (event.widget == _create)
        {
            SQLDevToolsConfiguration f = SQLToolsFacade.getConfigurationByVendorIdentifier(_dbVendorId);
            if (f == null)
            {
                return;
            }
            // FIXME: should invoke NewConnectionProfileWizard specific to the
            // database vendor definition
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

    private String[] getCurrentProfileNames()
    {
        IConnectionProfile profiles[] = ProfileManager.getInstance().getProfiles();
        String[] currentNames = new String[profiles.length];
        for (int i = 0; i < profiles.length; i++)
        {
            currentNames[i] = profiles[i].getName();
        }
        return currentNames;
    }

    /**
     * Finds the first profile name that does not exist in currentNames. This is used
     * 
     * @param currentNames
     * @param newNames
     * @return
     */
    private String getNewProfileName(String[] currentNames, String[] newNames)
    {
        if (currentNames != null && newNames != null)
        {
            for (int i = 0; i < newNames.length; i++)
            {
                boolean found = false;
                for (int j = 0; j < currentNames.length; j++)
                {
                    if (newNames[i].equals(currentNames[j]))
                    {
                        found = true;
                        break;
                    }
                }
                if (!found)
                {
                    return newNames[i];
                }
            }
        }
        if (currentNames == null && newNames != null && newNames.length > 0)
        {
            return newNames[0];
        }
        return null;
    }

}

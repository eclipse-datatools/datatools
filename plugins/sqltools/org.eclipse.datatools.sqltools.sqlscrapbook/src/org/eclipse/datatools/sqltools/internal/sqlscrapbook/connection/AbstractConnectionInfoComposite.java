/***********************************************************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLDevToolsUIConfiguration;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLToolsUIFacade;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorConnectionInfo;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * Super class for composites to select the connection information. This class do not handle the various styles, which should be take care of by concrete subclasses.
 * By default this class assumes the style is STYLE_SHOW_STATUS | STYLE_SEPARATE_TYPE_NAME | STYLE_LABEL_GROUP.
 * @author Hui Cao
 */
public abstract class AbstractConnectionInfoComposite extends Composite
{
    /**
     * Style constant indicates the "Create..." button should be added following the connection profile drop down list.
     */
    public static final int STYLE_CREATE_PROFILE = 1;
    /**
     * Style constant indicates Connection profile status label should be shown
     */
    public static final int STYLE_SHOW_STATUS = 1<<1; 
    /**
     * Style constant indicates multiple lines layout
     */
    public static final int STYLE_MULTI_LINE = 1<<2; 
    /**
     * Style constant indicates connection profile type and name drop down should be separated
     */
    public static final int STYLE_SEPARATE_TYPE_NAME = 1<<3; 
    /**
     * Style constant indicates the controls should be placed into a group with a group title
     */
    public static final int STYLE_SINGLE_GROUP = 1<<4; 
    /**
     * Style constant indicates a lable and a group which contains all the other controls is desired
     */
    public static final int STYLE_LABEL_GROUP = 1<<5; 
    /**
     * Style constant indicates a lable and a group which contains all the other controls is desired
     */
    public static final int STYLE_MUST_CONNECT = 1<<6; 
    /**
     * Style constant indicates a lable and a group which contains all the other controls is desired
     */
    public static final int STYLE_LAZY_INIT = 1<<7; 
    /**
     * Style constant indicates connection commit mode label should be shown in Connection Status
     */
    public static final int STYLE_SHOW_COMMIT_MODE = 1<<8;
    
    protected static final DatabaseVendorDefinitionId DATABASE_VENDOR_DEFINITION_ID = SQLToolsFacade
                                                                                                .getNonSpecificDatabaseVendorDefinitionId();
    protected String _profileName = null;
    protected String _dbName = null;
    protected DatabaseVendorDefinitionId _dbVendorId = DATABASE_VENDOR_DEFINITION_ID;
    protected ISQLEditorConnectionInfo _connInfo = null;
    // The listener to notify of events
    protected Listener                                _listener;
    protected int _style = STYLE_SHOW_STATUS | STYLE_SEPARATE_TYPE_NAME | STYLE_LABEL_GROUP;
    /**
     * The supported database definition name list. If it is null, the default database definition name list will be
     * returned. This collection should only be used for display, while _supportedDBDefinitionIds should be used for
     * comparison.
     */
    protected Collection _supportedDBDefinitionNames = null;
    protected Collection _supportedDBDefinitionIds = null;

    public AbstractConnectionInfoComposite(Composite parent, int style)
    {
        super(parent, style);
    }

    public AbstractConnectionInfoComposite(Composite parent, int style, Listener listener, ISQLEditorConnectionInfo connInfo, 
            Collection supportedDBDefinitionNames, int infoStyle)
    {
        super(parent, style);
        setup(connInfo);

        this._listener = listener;
        setSupportedDBDefinitionNames(supportedDBDefinitionNames );
        this._style = infoStyle;
    }

    /**
     * Initializes instance fields
     * @param connInfo
     */
	private void setup(ISQLEditorConnectionInfo connInfo) {
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
	}

    protected void updateFields()
    {

        readControlValues();
        _connInfo = new SQLEditorConnectionInfo(_dbVendorId, _profileName, _dbName);
    }

    protected void initDBNames()
    {
        String profileName = getProfileNamesControl().getText();
        if(profileName != null && profileName.length() > 0 && getProfileTypeControl().getText() != null)
        {
            initProfileNames(getProfileTypeControl().getText(), null);
            getProfileNamesControl().setText(profileName);
        }
        
        getDbNamesControl().removeAll();
        if (_profileName != null)
        {
            List list = ProfileUtil.getDatabaseList(_profileName, false);
            Iterator iterator = list.iterator();
            while (iterator.hasNext())
            {
                String dbname = iterator.next().toString();
                getDbNamesControl().add(dbname);
            }
        }
        if (_dbName != null)
        {
            if (getDbNamesControl().getItemCount() == 0)
            {
                getDbNamesControl().add(_dbName);
            }
            getDbNamesControl().setText(_dbName);
        }
        getDbNamesControl().add("", 0);
    }


    protected void readControlValues() {
        if (getProfileTypeControl().getText() != null && !"".equals(getProfileTypeControl().getText())) //$NON-NLS-1$
        {
            _dbVendorId = new DatabaseVendorDefinitionId(getProfileTypeControl().getText());
        }
        else
        {
            _dbVendorId = DATABASE_VENDOR_DEFINITION_ID;
        }
        // set _profileName to "" has no meaning
        if (getProfileNamesControl().getText() != null
                && !"".equals(getProfileNamesControl().getText())) { //$NON-NLS-1$
            _profileName = getProfileNamesControl().getText();
        } else {
            _profileName = null;
        }
        if (getDbNamesControl() != null && getDbNamesControl().getText() != null
                && !"".equals(getDbNamesControl().getText())) { //$NON-NLS-1$
            _dbName = getDbNamesControl().getText();
        }
        else
        {
            _dbName = null;
        }

    }
    
    public void setInfoStyle(int style)
    {
        _style = style; 
    }
    
    public int getInfoStyle()
    {
        return _style;
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

    /**
     * 
     */
    protected void notifyListener()
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
     * Initializes the controls
     */
    public void init()
    {
        init(_dbVendorId.toString(), _profileName, _dbName);
    }

    /**
     * Initializes the controls
     * @param connInfo
     */
    public void init(ISQLEditorConnectionInfo connInfo)
    {
    	setup(connInfo);
    	init();
    }
    
    /**
     * Initializes the controls
     * 
     * @param dbVendorName
     */
    public void init(String dbVendorName, String initialProfName, String initialDBName)
    {
        setConnectionInfo(dbVendorName, initialProfName, initialDBName);
        //init type
        if (_supportedDBDefinitionNames == null)
        {
            setSupportedDBDefinitionNames(SQLToolsFacade.getAllAvailableDBDefinitionNames());
        }
        getProfileTypeControl().setItems((String[]) _supportedDBDefinitionNames
                .toArray(new String[0]));
        getProfileTypeControl().add("", 0);//add empty type to the first element to group all connection profiles
        if (_profileName != null) {
            initTypebyProfile(_profileName);
        } else if (_supportedDBDefinitionNames.contains(_dbVendorId.toString())) {
            getProfileTypeControl().setText(_dbVendorId.toString());
        } else if (_supportedDBDefinitionNames.size() > 0) {
            getProfileTypeControl().select(0);
        }
        
        //init name
        initProfileNames(_dbVendorId.toString(), _profileName);

        //init db
        IConnectionProfile connectionProfile = ProfileManager.getInstance().getProfileByName(_profileName);
        if (ProfileUtil.isDatabaseProfile(connectionProfile))
        {
            getDbNamesControl().setEnabled(true);
        }
        else
        {
            getDbNamesControl().setEnabled(false);
        }        
        initDBNames();
        
    }

    /**
     * Tries to set the database vendor definition combo box when a connection
     * profile name is specified (during initialization or connection profile
     * creation)
     */
    protected void initTypebyProfile(String profileName) {
        if (profileName == null || profileName.equals(""))
        {
            return;
        }
        DatabaseVendorDefinitionId cacheId = ProfileUtil.getDatabaseVendorDefinitionId(profileName, true, true);
        DatabaseVendorDefinitionId currentDbId = new DatabaseVendorDefinitionId(getProfileTypeControl().getText());
        if (!cacheId.equals(currentDbId))
        {
            DatabaseVendorDefinitionId matchId = findDatabaseVendorDefinitionId(cacheId);
            if (matchId != null)
            {
                getProfileTypeControl().setText(matchId.toString());
            }
            else
            {
                //don't use cached product name and version. e.g. the product name for Derby returned by JDBC driver is "Apache Derby",
                //which is different with the database definition. 
                DatabaseVendorDefinitionId driverId = ProfileUtil.getDatabaseVendorDefinitionId(profileName, false, false);
                matchId = findDatabaseVendorDefinitionId(driverId);
                if (matchId == null)
                {
                	matchId = SQLToolsFacade.getDefaultDatabaseVendorDefinitionId();
                }
                if (matchId != null)
                {
                    getProfileTypeControl().setText(matchId.toString());
                }
            }
        }
    }
    
    protected DatabaseVendorDefinitionId findDatabaseVendorDefinitionId(DatabaseVendorDefinitionId dbId)
    {
        for (Iterator it = _supportedDBDefinitionIds.iterator(); it.hasNext();)
        {
            DatabaseVendorDefinitionId existId = (DatabaseVendorDefinitionId) it.next();
            if (existId.equals(dbId))
            {
                return existId;
            }
        }
        return null;
    }

    /**
     * Refreshes the connection profile name combo box 
     * 
     * @param dbVendorName
     */
    protected void initProfileNames(String dbVendorName, String initialProfName) {
        ArrayList rightProfiles = new ArrayList();
        IConnectionProfile profiles[] = ProfileUtil.getProfiles();//filter out non-supported profiles
        if (dbVendorName == null || dbVendorName.equals("") || dbVendorName.equals(DATABASE_VENDOR_DEFINITION_ID.toString())) { //$NON-NLS-1$
            for (int i = 0; i < profiles.length; i++) {
                rightProfiles.add(profiles[i].getName());
            }
        }
        else
        {
            DatabaseVendorDefinitionId selectedDbVendorId = new DatabaseVendorDefinitionId(dbVendorName);
            boolean isDefault = selectedDbVendorId.equals(SQLToolsFacade.getDefaultDatabaseVendorDefinitionId());
            for (int i = 0; i < profiles.length; i++) {
                DatabaseVendorDefinitionId cacheId = ProfileUtil.getDatabaseVendorDefinitionId(profiles[i], true,
                        true);
                DatabaseVendorDefinitionId driverId = ProfileUtil.getDatabaseVendorDefinitionId(profiles[i], false,
                        false);
                if (selectedDbVendorId.equals(cacheId)) {
                    rightProfiles.add(profiles[i].getName());
                }
                else if (selectedDbVendorId.equals(driverId))
                {
                    //try to see whether this profile belongs to other db definition type
                    if (_supportedDBDefinitionIds.contains(cacheId))
                    {
                        continue;
                    }
                    else
                    {
                        rightProfiles.add(profiles[i].getName());
                    }
                }
                else if (isDefault)
                {
                    //try to see whether this profile belongs to other db definition type
                    if (_supportedDBDefinitionIds.contains(cacheId) || _supportedDBDefinitionIds.contains(driverId))
                    {
                        continue;
                    }
                    else
                    {
                        rightProfiles.add(profiles[i].getName());
                    }
                }
            }

        }

        Collections.sort(rightProfiles);

        rightProfiles.add(0, new String("")); //$NON-NLS-1$
        getProfileNamesControl().setItems((String[]) rightProfiles
                .toArray(new String[] {}));
        if (initialProfName != null) {
            for (Iterator iter = rightProfiles.iterator(); iter.hasNext();) {
                if (iter.next().equals(initialProfName)) {
                    getProfileNamesControl().setText(initialProfName);
                    break;
                }

            }
        }
    }


    protected void setConnectionInfo(String dbVendorName, String initialProfName, String initialDBName)
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

    public abstract Combo getProfileTypeControl();
    
    /**
     * Returns the ProfileNames control for this Connection Info group.
     * <p>
     * May return <code>null</code> if the control has not been created yet.
     * </p>
     * 
     * @return the ProfileNames control or <code>null</code>
     */
    public abstract Combo getProfileNamesControl();

    /**
     * Returns the button control to create profiles
     * <p>
     * May return <code>null</code> if the control has not been created yet.
     * </p>
     * 
     */
    public abstract Button getCreateButton();

    /**
     * Returns the DbNames control for this Connection Info group.
     * <p>
     * May return <code>null</code> if the control has not been created yet.
     * </p>
     * 
     * @return the DbNames control or <code>null</code>
     */
    public abstract Combo getDbNamesControl();

    protected String[] getCurrentProfileNames()
    {
        IConnectionProfile profiles[] = ProfileUtil.getProfiles();
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
    protected String getNewProfileName(String[] currentNames, String[] newNames)
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

    /**
     * Refreshes the connection status label. Subclasses should override if they support STYLE_SHOW_STATUS.
     */
    public void refreshConnectionStatus()
    {
    }
    
    public void widgetDefaultSelected(SelectionEvent e)
    {
        // TODO Auto-generated method stub
        
    }

    public void widgetSelected(SelectionEvent e)
    {
        readControlValues();
        if (e.widget == getProfileTypeControl()) {
            initProfileNames(getProfileTypeControl().getText(), null);
            updateDBNamesControl();
        }
        else if (e.widget == getProfileNamesControl())
        {
            initTypebyProfile(_profileName);
            updateDBNamesControl();
        }
        updateFields();
        notifyListener();        
    }

	protected void updateDBNamesControl() {
		// TODO when DatabaseDefinition.supportsCatalog is introduced, adjust _combodbName accordingly
		if (getProfileNamesControl().getSelectionIndex() != -1)
		{
		    if (getDbNamesControl() != null)
		    {
		        getDbNamesControl().removeAll();
		        _dbName = null;

		        if (_profileName != null)
		        {
		            IConnectionProfile connectionProfile = ProfileManager.getInstance().getProfileByName(
		                    _profileName);
		            if (ProfileUtil.isDatabaseProfile(connectionProfile))
		            {
		                getDbNamesControl().setEnabled(true);
		            }
		            else
		            {
		                getDbNamesControl().setEnabled(false);
		            }
		        }
		        else
		        {
		            getDbNamesControl().setEnabled(false);
		        }
		    }
		}
		else
		{
		    if (getDbNamesControl() != null)
		    {
		        getDbNamesControl().removeAll();
		        getDbNamesControl().setEnabled(false);
		    }
		}
		//180481 populate database names in focusGained does not work on linux
		if (getDbNamesControl() != null && getDbNamesControl().isEnabled())
		{
		    initDBNames();
		}
	}

    
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
     */
    public void handleEvent(Event event) {
        if (event.widget == getCreateButton()) {
            SQLDevToolsUIConfiguration f = SQLToolsUIFacade.getConfigurationByVendorIdentifier(_dbVendorId);
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
                    ProfileUtil.connectProfile(newProfile);
                    init(_dbVendorId.toString(), newProfile, null);
                    updateFields();
                }
                notifyListener();
            }
        }
    }
    
    protected void setSupportedDBDefinitionNames(Collection names)
    {
        if (names == null)
        {
        	DBDefinitionFilterRegistry reg = DBDefinitionFilterRegistry.getInstance();
        	ArrayList filtered = reg.getFilteredDefinitions();
        	if (filtered != null && !filtered.isEmpty())
        	{
        		Collection ids = SQLToolsFacade.getAllAvailableDBDefinitionIds();
        		for (Iterator it = ids.iterator(); it.hasNext();) {
					DatabaseVendorDefinitionId id = (DatabaseVendorDefinitionId) it.next();
					if (filtered.contains(id))
					{
						it.remove();
					}
				}
        		names = new TreeSet();
                for (Iterator it = ids.iterator(); it.hasNext();)
                {
                    DatabaseVendorDefinitionId id = (DatabaseVendorDefinitionId) it.next();
                    names.add(id.toString());
                }
        	}
        	else
        	{
        		names = SQLToolsFacade.getAllAvailableDBDefinitionNames();
        	}
        }
        _supportedDBDefinitionNames = names;
        _supportedDBDefinitionIds = new HashSet();
        for (Iterator it = names.iterator(); it.hasNext();)
        {
            String name = (String) it.next();
            DatabaseVendorDefinitionId id = new DatabaseVendorDefinitionId(name);
            _supportedDBDefinitionIds.add(id);
        }
    }

}

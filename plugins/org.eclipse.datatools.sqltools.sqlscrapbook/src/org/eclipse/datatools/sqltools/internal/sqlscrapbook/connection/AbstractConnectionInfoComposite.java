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

import java.util.Collection;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorConnectionInfo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * Super class for composites to select the connection information. This class do not handle the various styles, which should be take care of by concrete subclasses.
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
    
    protected static final DatabaseVendorDefinitionId DATABASE_VENDOR_DEFINITION_ID = SQLToolsFacade
                                                                                                .getNonSpecificDatabaseVendorDefinitionId();
    protected String _profileName = null;
    protected String _dbName = null;
    protected DatabaseVendorDefinitionId _dbVendorId = DATABASE_VENDOR_DEFINITION_ID;
    protected ISQLEditorConnectionInfo _connInfo = null;
    protected boolean _mustConnect = false;
    // The listener to notify of events
    protected Listener                                _listener;
    protected int _style = STYLE_SHOW_STATUS | STYLE_SEPARATE_TYPE_NAME | STYLE_LABEL_GROUP;
    /**
     * The supported database definition name list. If it is null, the default database definition name list will be
     * returned.
     */
    protected Collection _supportedDBDefinitionNames = null;

    public AbstractConnectionInfoComposite(Composite parent, int style)
    {
        super(parent, style);
    }

    public AbstractConnectionInfoComposite(Composite parent, int style, Listener listener, ISQLEditorConnectionInfo connInfo, 
            Collection supportedDBDefinitionNames, int infoStyle)
    {
        super(parent, style);
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
        this._style = infoStyle;
    }

    protected void updateFields()
    {

        readControlValues();
        _connInfo = new SQLEditorConnectionInfo(_dbVendorId, _profileName, _dbName);
    }

    /**
     * Looks up the control and get the values
     */
    protected abstract void readControlValues();
    
    public void setInfoStyle(int style)
    {
        _style = style; 
    }
    
    public int getInfoStyle()
    {
        return _style;
    }

    
    public boolean isMustConnect()
    {
        return _mustConnect;
    }

    public void setMustConnect(boolean connect)
    {
        _mustConnect = connect;
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
     * Refreshes the connection profile name combo box
     * 
     * @param dbVendorName
     */
    public abstract void init(String dbVendorName, String initialProfName, String initialDBName);

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
    
}

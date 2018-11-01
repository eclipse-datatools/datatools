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
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.connection;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.sqltools.common.ui.util.SWTUtils;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqleditor.EditorConstants;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorConnectionInfo;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.SWT;
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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * A reusable component for user to specify database vendor definition,
 * (optional) connection profile name, (optional) database name and whether to
 * connect.
 * 
 * @author Hui Cao
 * 
 */
public class ConnectionInfoGroup extends AbstractConnectionInfoComposite implements SelectionListener,
		Listener {
	private Label _labelName = null;

	private Combo _comboProfileName = null;

	private Combo _comboType = null;

	private Label _labelType = null;

	private Label _labelDbName = null;

	private Combo _combodbName = null;

	private Button _checkBoxConnect = null;

	private Text _textWarning = null;

	private Button _create = null;

	private boolean _isConnected = false;

	private boolean _showWarning = true;

	/**
	 * @param parent
	 *            The parent widget of the group.
	 * @param listener
	 *            A listener to forward events to. Can be null if no listener is
	 *            required.
     * @param showWarning if warining is shown or not.
	 */
	public ConnectionInfoGroup(Composite parent, Listener listener,
			boolean showWarning) {
		this(parent, listener, null, null, showWarning, false);
	}

	public ConnectionInfoGroup(Composite parent, Listener listener,
			String profileName, String dbName, boolean showWarning,
			boolean mustConnect) {
		this(parent, listener, new SQLEditorConnectionInfo(null, profileName, dbName), showWarning, false);
	}

	/**
	 * @param parent
	 *            The parent widget of the group.
	 * @param listener
	 *            A listener to forward events to. Can be null if no listener is
	 *            required.
	 * @param profileName
	 *            the old profile name
     * @param showWarning if warining is shown or not.
     * @param mustConnect if the connection profile must connect or not.
	 */
	public ConnectionInfoGroup(Composite parent, Listener listener,
			ISQLEditorConnectionInfo connInfo, boolean showWarning,
			boolean mustConnect) {
        this(parent, listener, connInfo, showWarning, mustConnect, null);
	}

    /**
     * 
     * Creates a new Connection Info Group with the given profileName, dbName, supportedDBDefinitionNames.
     * 
     * @param parent The parent widget of the group
     * @param connInfo The connection information @see SQLEditorConnectionInfo
     * @param showWarning if warining is shown or not.
     * @param mustConnect if the connection profile must connect or not.
     * @param supportedDBDefinitionNames The supported datbase definition name list. 
     */
    public ConnectionInfoGroup(Composite parent, Listener listener,
            ISQLEditorConnectionInfo connInfo, boolean showWarning,
            boolean mustConnect, Collection supportedDBDefinitionNames) {
        super(parent, SWT.NONE, listener, connInfo, supportedDBDefinitionNames, STYLE_MULTI_LINE | STYLE_CREATE_PROFILE | STYLE_SEPARATE_TYPE_NAME | (mustConnect? STYLE_MUST_CONNECT : 0));
        this._showWarning = showWarning;
        createContents();
        if ((_style & STYLE_LAZY_INIT ) == 0)
        {
            init();
        }        
        
    }
	
    /**
     * @deprecated Get connection status from SQLEditorConnectionInfo
     * @return
     */
    public boolean isConnected()
    {
        return _isConnected;
    }

	protected Control createContents() {
		// add controls to composite as necessary
		org.eclipse.swt.layout.GridLayout gridLayout1 = new GridLayout();
		org.eclipse.swt.layout.GridLayout gridLayout2 = new GridLayout();
		org.eclipse.swt.layout.GridData gridData2 = new org.eclipse.swt.layout.GridData();
		org.eclipse.swt.layout.GridData gridData3 = new org.eclipse.swt.layout.GridData();
		org.eclipse.swt.layout.GridData gridData6 = new org.eclipse.swt.layout.GridData();

		this.setLayoutData(new GridData(GridData.FILL_BOTH));
		gridLayout1.marginWidth = 0;
		gridLayout1.numColumns = 2;
		this.setLayout(gridLayout1);

		_labelType = new Label(this, SWT.NONE);
		_labelType.setText(Messages.SelectProfileDialog_profile_type); //$NON-NLS-1$
		createComboType(this);

		_labelName = new Label(this, SWT.NONE);
		_labelName.setText(Messages.SelectProfileDialog_profile_name); //$NON-NLS-1$
		//connectivity layer does not associate profile with database defintions,
		//so we have to use UIComponentService to create those wizards
		Composite compositeName = new Composite(this, SWT.NONE);
		gridData3.grabExcessHorizontalSpace = true;
		gridData3.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		compositeName.setLayoutData(gridData3);
		gridLayout2.numColumns = 2;
		gridLayout2.marginHeight = 0;
		gridLayout2.marginWidth = 0;
		compositeName.setLayout(gridLayout2);
		createComboProfileName(compositeName);
		_create = new Button(compositeName, SWT.PUSH);
		_create.setText(Messages.SelectProfileDialog_create); //$NON-NLS-1$
		_create.addListener(SWT.Selection, this);

		_labelDbName = new Label(this, SWT.NONE);
		_labelDbName.setText(Messages.ConnectionInfoGroup_database_name);
		createComboDbName(this);

		if (!((_style & STYLE_MUST_CONNECT) == STYLE_MUST_CONNECT )) {
			_checkBoxConnect = new Button(this, SWT.CHECK);
			gridData2.horizontalSpan = 2;
			_checkBoxConnect.setLayoutData(gridData2);
			_checkBoxConnect.setText(Messages.SelectProfileDialog_donot_connect); //$NON-NLS-1$
			_checkBoxConnect.addListener(SWT.Selection, this);
			if (_listener != null) {
				_checkBoxConnect.addListener(SWT.Selection, _listener);
			}
		}
		if (_showWarning) {
			_textWarning = new Text(this, SWT.MULTI | SWT.WRAP);
			gridData6.grabExcessHorizontalSpace = true;
			gridData6.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
			gridData6.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
			gridData6.horizontalSpan = 2;
			gridData6.verticalSpan = 2;
			_textWarning.setLayoutData(gridData6);
			_textWarning.setEditable(false);
			_textWarning.setText(Messages.SelectProfileDialog_warning); //$NON-NLS-1$
			_textWarning.setVisible(false);
			// setSize to hold the warning
			this.setSize(new org.eclipse.swt.graphics.Point(460, 469));
		}

		return this;
	}

	/**
	 * Accepts user's input and generates a
	 * <code>ISQLEditorConnectionInfo</code> object.
	 * 
	 * @see #getConnectionInfo()
	 * 
	 */
	public void finish() {
		updateFields();
		if (!canFinish()) {
			return;
		}

		if (!((_style & STYLE_MUST_CONNECT) == STYLE_MUST_CONNECT ) && _checkBoxConnect.getSelection()) {
			_isConnected = false;
		} else if (!SWTUtils.notEmpty(_comboProfileName)) {
			_isConnected = false;
		} else {
			// connect using the profile
			IConnectionProfile profile = ProfileManager.getInstance()
					.getProfileByName(_profileName);
			if (profile == null) {
				_isConnected = false;
			}
			else
			{
				try {
					ProfileUtil.getOrCreateReusableConnection(new DatabaseIdentifier(
							_profileName, _dbName));
					_isConnected = true;
					String user = ProfileUtil.getProfileUserName(new DatabaseIdentifier(_profileName, _dbName), true);
					_connInfo.setDefaultSchemaName(user);
				} catch (Exception e) {
					String statusmsg = e.getMessage();
					if (statusmsg == null) {
						statusmsg = ""; //$NON-NLS-1$
					}
					IStatus status = new Status(IStatus.ERROR,
							EditorCorePlugin.PLUGIN_ID, 0, statusmsg, e);
					Shell shell = getShell();
					String title = Messages.SelectProfileDialog_error_jdbc_title; //$NON-NLS-1$
					String msg = Messages.SelectProfileDialog_error_jdbc_message; //$NON-NLS-1$
					ErrorDialog.openError(shell, title, msg, status);
					
				}
			}
		}
		if (_isConnected)
		{
			_connInfo.setProfileStatus(EditorConstants.CP_STATUS_CONNECTED);
		}
		else
		{
			_connInfo.setProfileStatus(EditorConstants.CP_STATUS_DISCONNECTED);
		}
	}
    
    public void widgetSelected(SelectionEvent e)
    {
        super.widgetSelected(e);
        updateConnectCheckBox();
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
	 */
	public void handleEvent(Event event) {
		super.handleEvent(event);
		if (event.widget == _checkBoxConnect) {
            updateConnectCheckBox();
		}
	}
    
    public void init(String dbVendorName, String initialProfName, String initialDBName)
    {
        super.init(dbVendorName, initialProfName, initialDBName);
        updateConnectCheckBox();
    }
    
    void updateConnectCheckBox()
    {
        if (_checkBoxConnect != null)
        {
            _checkBoxConnect.setEnabled(_profileName != null && !_connInfo.isConnected());
            if (_showWarning) {
                _textWarning.setVisible(_checkBoxConnect.isEnabled() && _checkBoxConnect.getSelection());
            }
        }
    }

	/**
	 * Validates user's input to see whether the information is complete.
	 * 
	 * @return true if so, otherwise false
	 */
	public boolean canFinish() {
		if ((_style & STYLE_MUST_CONNECT) == STYLE_MUST_CONNECT  && !SWTUtils.notEmpty(_comboProfileName)) {
			return false;
		}
		return true;
	}

	public String getWarning() {
		if (!((_style & STYLE_MUST_CONNECT) == STYLE_MUST_CONNECT)  && _checkBoxConnect.getSelection()) {
			return Messages.SelectProfileDialog_warning;
		}
		return null;
	}

	/**
	 * This method initializes _comboProfileName
	 * 
	 */
	private void createComboProfileName(Composite composite) {
		org.eclipse.swt.layout.GridData gridData5 = new org.eclipse.swt.layout.GridData();
		_comboProfileName = new Combo(composite, SWT.NONE | SWT.READ_ONLY);
		gridData5.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData5.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
		gridData5.grabExcessHorizontalSpace = true;
		_comboProfileName.setLayoutData(gridData5);

		initProfileNames(_comboType.getText(), _profileName);
		_comboProfileName.addSelectionListener(this);
	}

	private void createComboDbName(Composite composite) {
		org.eclipse.swt.layout.GridData gridData5 = new org.eclipse.swt.layout.GridData();
		// the combo used to be created using SWT.NONE to resolve bug 132294,
        // but now that empty database name is allowed, it's changed back to readonly 
		_combodbName = new Combo(composite, SWT.READ_ONLY);
		gridData5.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData5.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
		gridData5.grabExcessHorizontalSpace = true;
		_combodbName.setVisibleItemCount(20);
		_combodbName.setLayoutData(gridData5);

		if (_comboProfileName.getSelectionIndex() == -1) {
			_combodbName.setEnabled(false);
		}

		// for non-database profile, this combo box is always disabled.
		String profileName = _comboProfileName.getText();
		if (profileName != null) {
			IConnectionProfile connectionProfile = ProfileManager.getInstance()
					.getProfileByName(profileName);
			if (ProfileUtil.isDatabaseProfile(connectionProfile)) {
				_combodbName.setEnabled(true);
			} else {
				_combodbName.setEnabled(false);
			}
		}
        
		_combodbName.addSelectionListener(this);
		if (_dbName != null) {
			_combodbName.add(_dbName);
			_combodbName.setText(_dbName);
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

        if (_supportedDBDefinitionNames == null)
		{
            _supportedDBDefinitionNames = SQLToolsFacade.getAllAvailableDBDefinitionNames();
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
		_comboType.addSelectionListener(this);

	}

    /**
     * Returns the ProfileType control for this Connection Info group.
     * <p>
     * May return <code>null</code> if the control
     * has not been created yet.
     * </p>
     *
     * @return the ProfileType control or <code>null</code>
     */
    public Combo getProfileTypeControl()
    {
        return _comboType;
    }
    
    /**
     * Returns the ProfileNames control for this Connection Info group.
     * <p>
     * May return <code>null</code> if the control
     * has not been created yet.
     * </p>
     *
     * @return the ProfileNames control or <code>null</code>
     */
    public Combo getProfileNamesControl()
    {
        return _comboProfileName;
    }
    
    public Button getCreateButton()
    {
        return _create;
    }
    
    /**
     * Returns the DbNames control for this Connection Info group.
     * <p>
     * May return <code>null</code> if the control
     * has not been created yet.
     * </p>
     *
     * @return the DbNames control or <code>null</code>
     */
    public Combo getDbNamesControl()
    {
        return _combodbName;
    }

}

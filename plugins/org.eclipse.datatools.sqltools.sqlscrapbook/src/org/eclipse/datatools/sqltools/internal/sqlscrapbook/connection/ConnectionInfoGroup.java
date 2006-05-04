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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.db.generic.ui.NewConnectionProfileWizard;
import org.eclipse.datatools.sqltools.common.ui.util.SWTUtils;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorConnectionInfo;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
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
public class ConnectionInfoGroup extends Composite implements SelectionListener,
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

	private String _profileName = null;

	private String _dbName = null;

	private DatabaseVendorDefinitionId _dbVendorId = SQLDevToolsConfiguration
			.getDefaultInstance().getDatabaseVendorDefinitionId();

	private boolean _isConnected = false;

	private boolean _showWarning = true;

	private boolean _mustConnect = true;

	// The listener to notify of events
	private Listener _listener;

	private ISQLEditorConnectionInfo _connInfo = null;

	/**
	 * @param parent
	 *            The parent widget of the group.
	 * @param listener
	 *            A listener to forward events to. Can be null if no listener is
	 *            required.
	 */
	public ConnectionInfoGroup(Composite parent, Listener listener,
			boolean showWarning) {
		this(parent, listener, null, null, showWarning, false);
	}

	public ConnectionInfoGroup(Composite parent, Listener listener,
			String profileName, String dbName, boolean showWarning,
			boolean mustConnect) {
		this(parent, listener, null, showWarning, false);
	}

	/**
	 * @param parent
	 *            The parent widget of the group.
	 * @param listener
	 *            A listener to forward events to. Can be null if no listener is
	 *            required.
	 * @param profileName
	 *            the old profile name
	 * @param dbType
	 *            the initial selected db type, only useful when profileName is
	 *            null
	 */
	public ConnectionInfoGroup(Composite parent, Listener listener,
			ISQLEditorConnectionInfo connInfo, boolean showWarning,
			boolean mustConnect) {
		super(parent, SWT.NONE);
		if (connInfo != null)
		{
			this._profileName = connInfo.getConnectionProfileName();
			this._dbName = connInfo.getDatabaseName();
			this._dbVendorId = connInfo.getDatabaseVendorDefinitionId();
		}
		this._listener = listener;
		this._showWarning = showWarning;
		this._mustConnect = mustConnect;
		createContents();
		updateFields();
	}

	/**
	 * Returns the <code>ISQLEditorConnectionInfo</code> object specified by
	 * user. This should be called after {@link #finish()}.
	 * 
	 * @return <code>ISQLEditorConnectionInfo</code> containing all the
	 *         information specified by user
	 */
	public ISQLEditorConnectionInfo getConnectionInfo() {
		return _connInfo;
	}
	
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
		//hide the "create" button for now since we can't invoke the right wizard
//		Composite compositeName = new Composite(this, SWT.NONE);
//		gridData3.grabExcessHorizontalSpace = true;
//		gridData3.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
//		compositeName.setLayoutData(gridData3);
//		gridLayout2.numColumns = 2;
//		gridLayout2.marginHeight = 0;
//		gridLayout2.marginWidth = 0;
//		compositeName.setLayout(gridLayout2);
		createComboProfileName(this);
//		_create = new Button(compositeName, SWT.PUSH);
//		_create.setText(Messages.getString("SelectProfileDialog.create")); //$NON-NLS-1$
//		_create.addListener(SWT.Selection, this);

		_labelDbName = new Label(this, SWT.NONE);
		_labelDbName.setText(Messages.ConnectionInfoGroup_database_name);
		createComboDbName(this);

		if (!_mustConnect) {
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
		_connInfo = new SQLEditorConnectionInfo(_dbVendorId, _profileName, _dbName);

		if (!_mustConnect && _checkBoxConnect.getSelection()) {
			_isConnected = false;
		} else if (!SWTUtils.notEmpty(_comboProfileName)) {
			_isConnected = false;
		} else {
			// connect using the profile
			IConnectionProfile profile = ProfileManager.getInstance()
					.getProfileByName(_profileName);
			if (profile == null) {
				return;
			}

			try {
				ProfileUtil.getReusableConnection(new DatabaseIdentifier(
						_profileName, _dbName));
				_isConnected = true;
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

				return;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	public void widgetSelected(SelectionEvent e) {
		if (e.widget == _comboType) {
			refreshProfileNames(_comboType.getText(), null);
		} else if (e.widget == _comboProfileName) {
			canFinish();
		}
		updateFields();

		notifyListener();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
	 */
	public void handleEvent(Event event) {
		if (event.widget == _create) {
			SQLDevToolsConfiguration f = SQLToolsFacade.getConfigurationByDBDefName(_comboType.getText());
			if (f == null) {
				return;
			}
			// FIXME: should invoke NewConnectionProfileWizard specific to the
			// database vendor definition
			NewConnectionProfileWizard wizard = new NewConnectionProfileWizard();
			String[] currentNames = getCurrentProfileNames();
			WizardDialog dlg = new WizardDialog(getShell(), wizard);
			int id = dlg.open();
			if (id != IDialogConstants.CANCEL_ID) {
				// refresh all the profile info so that we can select the newly
				// created one
				String[] newNames = getCurrentProfileNames();
				String newProfile = getNewProfileName(currentNames, newNames);
				if (newProfile != null) {
					selectTypebyProfile(newProfile);
					refreshProfileNames(_comboType.getText(), newProfile);
					canFinish();
				}
				notifyListener();
			}
		} else if (event.widget == _checkBoxConnect) {

			_isConnected = !_checkBoxConnect.getSelection();
			if (_showWarning) {
				_textWarning.setVisible(_checkBoxConnect.getSelection());
			}
		}
	}

	/**
	 * Validates user's input to see whether the information is complete.
	 * 
	 * @return true if so, otherwise false
	 */
	public boolean canFinish() {
		IConnectionProfile connectionProfile = ProfileManager.getInstance()
				.getProfileByName(_profileName);
		// OK is disabled if profileName is selected and database name is empty.
		if (SWTUtils.notEmpty(_comboType)
				&& !SWTUtils.notEmpty(_comboProfileName)) {
			return true;
		} else if ((SWTUtils.notEmpty(_comboProfileName) && SWTUtils
				.notEmpty(_combodbName))) {
			return true;
		}
		// For Replication Server
		else if (SWTUtils.notEmpty(_comboType)
				&& !ProfileUtil.isDatabaseProfile(connectionProfile)) {
			return true;
		} else {
			return false;
		}
	}

	public String getWarning() {
		if (!_mustConnect && _checkBoxConnect.getSelection()) {
			return Messages.SelectProfileDialog_warning;
		}
		return null;
	}

	/**
	 * Finds the first profile name that does not exist in currentNames. This is
	 * used
	 * 
	 * @param currentNames
	 * @param newNames
	 * @return
	 */
	private String getNewProfileName(String[] currentNames, String[] newNames) {
		if (currentNames != null && newNames != null) {
			for (int i = 0; i < newNames.length; i++) {
				boolean found = false;
				for (int j = 0; j < currentNames.length; j++) {
					if (newNames[i].equals(currentNames[j])) {
						found = true;
						break;
					}
				}
				if (!found) {
					return newNames[i];
				}
			}
		}
		if (currentNames == null && newNames != null && newNames.length > 0) {
			return newNames[0];
		}
		return null;
	}

	private String[] getCurrentProfileNames() {
		IConnectionProfile profiles[] = ProfileManager.getInstance()
				.getProfiles();
		String[] currentNames = new String[profiles.length];
		for (int i = 0; i < profiles.length; i++) {
			currentNames[i] = profiles[i].getName();
		}
		return currentNames;
	}

	/**
	 * 
	 */
	private void updateFields() {
		if (_comboType.getText() != null && !"".equals(_comboType.getText())) //$NON-NLS-1$
		{
			_dbVendorId = new DatabaseVendorDefinitionId(_comboType.getText());
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

	}

	/**
	 * 
	 */
	private void notifyListener() {
		// fire an event so the parent can update its controls
		if (_listener != null) {
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
	private void createComboProfileName(Composite composite) {
		org.eclipse.swt.layout.GridData gridData5 = new org.eclipse.swt.layout.GridData();
		_comboProfileName = new Combo(composite, SWT.NONE | SWT.READ_ONLY);
		gridData5.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData5.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
		gridData5.grabExcessHorizontalSpace = true;
		_comboProfileName.setLayoutData(gridData5);

		_comboProfileName.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				if (_comboProfileName.getSelectionIndex() != -1) {
					if (_combodbName != null) {
						_combodbName.removeAll();

						// for non-database profile, this combo box is always
						// disabled.
						String profileName = _comboProfileName.getText();
						IConnectionProfile connectionProfile = ProfileManager
								.getInstance().getProfileByName(profileName);
						if (ProfileUtil.isDatabaseProfile(connectionProfile)) {
							_combodbName.setEnabled(true);
						} else {
							_combodbName.setEnabled(false);
						}
					}
				} else {
					if (_combodbName != null) {
						_combodbName.removeAll();
						_combodbName.setEnabled(false);
					}
				}

			}
		});
		refreshProfileNames(_comboType.getText(), _profileName);
		_comboProfileName.addSelectionListener(this);
	}

	private void createComboDbName(Composite composite) {
		org.eclipse.swt.layout.GridData gridData5 = new org.eclipse.swt.layout.GridData();
		// see bug 132294, when database name can not be loaded, we should allow
		// user to input manually.
		_combodbName = new Combo(composite, SWT.NONE);
		gridData5.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData5.verticalAlignment = org.eclipse.swt.layout.GridData.CENTER;
		gridData5.grabExcessHorizontalSpace = true;
		_combodbName.setVisibleItemCount(20);
		_combodbName.setLayoutData(gridData5);

		if (_comboProfileName.getSelectionIndex() == -1) {
			_combodbName.setEnabled(false);
		}

		_combodbName.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent e) {
				_combodbName.removeAll();
				String profileName = _comboProfileName.getText();
				if (profileName != null) {
					_combodbName.removeAll();
					List list = ProfileUtil.getDatabaseList(profileName);
					Iterator iterator = list.iterator();
					while (iterator.hasNext()) {
						String dbname = iterator.next().toString();
						_combodbName.add(dbname);
					}
				}

			}

			public void focusLost(FocusEvent e) {

			}
		});

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

		Collection supportedDBDefinitionNames = SQLToolsFacade
				.getSupportedDBDefinitionNames();
		_comboType.setItems((String[]) supportedDBDefinitionNames
				.toArray(new String[0]));
		if (_profileName != null) {
			selectTypebyProfile(_profileName);
		} else if (supportedDBDefinitionNames.contains(_dbVendorId.toString())) {
			_comboType.setText(_dbVendorId.toString());
		} else if (supportedDBDefinitionNames.size() > 0) {
			_comboType.setText((String) supportedDBDefinitionNames.iterator()
					.next());
		}
		_comboType.addSelectionListener(this);

	}

	/**
	 * Tries to set the database vendor definition combo box when a connection
	 * profile name is specified (during initialization or connection profile
	 * creation)
	 */
	private void selectTypebyProfile(String profileName) {
		SQLDevToolsConfiguration factory = SQLToolsFacade
				.getConfigurationByProfileName(profileName);
		if (factory != null) {
			String dbDefName = factory.getDatabaseVendorDefinitionId()
					.toString();
			_comboType.setText(dbDefName);

		}
	}

	/**
	 * Refreshes the connection profile name combo box
	 * 
	 * @param dbVendorName
	 */
	private void refreshProfileNames(String dbVendorName, String initialProfName) {
		if (dbVendorName == null || dbVendorName.equals("")) { //$NON-NLS-1$
			//$NON-NLS-1$
			return;
		}
		DatabaseVendorDefinitionId selectedDbVendorId = new DatabaseVendorDefinitionId(dbVendorName);
		
		IConnectionProfile profiles[] = ProfileManager.getInstance()
				.getProfiles();
		;
		ArrayList rightProfiles = new ArrayList();

		for (int i = 0; i < profiles.length; i++) {
			DatabaseVendorDefinitionId dbVendorId = ProfileUtil
					.getDatabaseVendorDefinitionId(profiles[i].getName());
			if (selectedDbVendorId.equals(dbVendorId)) {
				rightProfiles.add(profiles[i].getName());
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
		updateFields();
		canFinish();
	}

}

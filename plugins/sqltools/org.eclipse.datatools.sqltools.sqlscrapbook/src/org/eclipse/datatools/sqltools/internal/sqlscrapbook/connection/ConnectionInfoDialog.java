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

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ui.actions.ConnectAction;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.SqlscrapbookPlugin;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.preferences.PreferenceConstants;
import org.eclipse.datatools.sqltools.sqleditor.internal.IHelpContextIds;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.JFaceColors;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * @author Hui Cao
 * 
 */
public class ConnectionInfoDialog extends Dialog implements Listener {
	protected ConnectionInfoGroup _group = null;

	private boolean _isConnected = false;

	private boolean _mustConnect = false;

	/**
	 * Dialog style used to determine whether to show _checkboxOverride
	 */
	private boolean _promptOverride = false;

	/**
	 * Whether override the files's original connection info
	 */
	private boolean _override = false;

	private ISQLEditorConnectionInfo _connInfo = null;

	private Button _checkboxOverride = null;
	
	private String _title = Messages.SelectProfileDialog_title;
	
	/**
	 * @param parentShell
	 */
	public ConnectionInfoDialog(Shell parentShell) {
		this(parentShell, null);
	}

	/**
	 * @param parentShell
	 */
	public ConnectionInfoDialog(Shell parentShell,
	        ISQLEditorConnectionInfo connInfo) {
	    this(parentShell, connInfo, false, Messages.SelectProfileDialog_title);
	}
	
	/**
	 * @param parentShell
	 * @param connInfo
	 * @param promptOverride whether to show the check box prompting user to override the files's original connection info
	 */
	public ConnectionInfoDialog(Shell parentShell,
	        ISQLEditorConnectionInfo connInfo, boolean promptOverride, String title) {
	    super(parentShell);
	    setShellStyle(SWT.CLOSE | SWT.MAX | SWT.TITLE | SWT.BORDER
                | SWT.APPLICATION_MODAL | SWT.RESIZE | getDefaultOrientation());
	    
	    this._connInfo = connInfo;
	    this._promptOverride = promptOverride;
	    this._title = title;
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

	public boolean isConnected() {
		return _isConnected;
	}

	/**
	 * Returns whether the user chooses to override the files's original connection info. 
	 * @return
	 */
	public boolean overrideConnectionInfo()
    {
        return _override;
    }

	/**
	 * Sets whether user can finish this dialog without connecting.
	 * @param mustConnect true if user has to connect, default is false.
	 */
	public void setMustConnect(boolean mustConnect) {
		this._mustConnect = mustConnect;
	}

	protected Control createDialogArea(Composite parent) {
		Composite composite = createOuterArea(parent);
		createConnectionArea(composite);
		return composite;
	}

    protected void createConnectionArea(Composite composite)
    {
        if (_connInfo == null || _connInfo.getConnectionProfile() == null ) {
			Label label = new Label(composite, SWT.NONE);
// BZ168411: Disabling error message for no cp selected because of problems on Linux
//           To be resolved post DTP 1.0
//			label.setText(Messages.SelectProfileDialog_noprofile);
			label.setForeground(JFaceColors.getErrorText(label.getDisplay()));
		}else 
		{
			String profileName = _connInfo.getConnectionProfileName();
			if (_connInfo.getConnectionProfile() == null) {
				Label label = new Label(composite, SWT.NONE);
				
				label.setText(NLS.bind(Messages.SelectProfileDialog_wrongprofile, profileName));
				label.setForeground(JFaceColors.getErrorText(label.getDisplay()));
			}
		}
		_group = new ConnectionInfoGroup(composite, this, _connInfo, true,
				_mustConnect);
		if (_promptOverride)
		{
		    _checkboxOverride = new Button(composite, SWT.CHECK);
		    _checkboxOverride.setText(Messages.SelectProfileDialog_override_profile); //$NON-NLS-1$
		    _checkboxOverride.setToolTipText(Messages.SelectProfileDialog_override_profile_tooltip); //$NON-NLS-1$
		    _checkboxOverride.setSelection(SqlscrapbookPlugin.getDefault().getPreferenceStore().getBoolean(PreferenceConstants.SQLFILE_SAVE_CONNECTION_INFO));
		    _checkboxOverride.addListener(SWT.Selection, this);
		}
		
		checkOK();
    }

    protected Composite createOuterArea(Composite parent)
    {
        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent.getShell(),
				HelpUtil.getContextId(IHelpContextIds.ATTACHING_PROFILE, SQLEditorPlugin.getDefault().getBundle().getSymbolicName()));

		Composite composite = (Composite) super.createDialogArea(parent);
        return composite;
    }

	/*
	 * (non-Javadoc) Method declared in Window.
	 */
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(_title);
	}

	protected void okPressed() {
	    if(_mustConnect && _group.getConnectionInfo()!=null && !_group.getConnectionInfo().isConnected())
        {
            try
            {
                IConnectionProfile profile = ProfileUtil.getProfile(_group.getConnectionInfo().getConnectionProfileName());
                ConnectAction connAction = new ConnectAction();
                connAction.connect(profile, null);
            }
            catch (Exception e)
            {
                return;
            }
        }
        if (!_mustConnect || (_group.getConnectionInfo()!=null && _group.getConnectionInfo().isConnected()))
        {
            if (!_group.canFinish())
            {
                return;
            }
            _group.finish();
            _isConnected = _group.isConnected();
            _connInfo = _group.getConnectionInfo();
            super.okPressed();
        }
	}

	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		checkOK();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
	 */
	public void handleEvent(Event event) {
		if (event.widget == _group) {
			checkOK();
		}
		else if (event.widget == _checkboxOverride) {
		    _override = _checkboxOverride.getSelection();
		}
	}

	protected boolean checkOK() {
		boolean enabled = _group.canFinish();
		if (getButton(IDialogConstants.OK_ID) != null) {
			getButton(IDialogConstants.OK_ID).setEnabled(enabled);
		}
		return enabled;
	}

}

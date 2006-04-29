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

import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqleditor.internal.IHelpContextIds;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.JFaceColors;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
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
	private ConnectionInfoGroup _group = null;

	private boolean _isConnected = false;

	private boolean _mustConnect = false;

	private ISQLEditorConnectionInfo _connInfo = null;

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
		super(parentShell);
		this._connInfo = connInfo;
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
	 * Sets whether user can finish this dialog without connecting.
	 * @param mustConnect true if user has to connect, default is false.
	 */
	public void setMustConnect(boolean mustConnect) {
		this._mustConnect = mustConnect;
	}

	protected Control createDialogArea(Composite parent) {
		PlatformUI.getWorkbench().getHelpSystem().setHelp(parent.getShell(),
				IHelpContextIds.ATTACHING_PROFILE);

		Composite composite = (Composite) super.createDialogArea(parent);
		if (_connInfo == null || _connInfo.getConnectionProfile() == null ) {
			Label label = new Label(composite, SWT.NONE);
			label.setText(Messages.SelectProfileDialog_noprofile);
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
		checkOK();
		return composite;
	}

	/*
	 * (non-Javadoc) Method declared in Window.
	 */
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(Messages.SelectProfileDialog_title); 
	}

	protected void okPressed() {
		if (!_group.canFinish()) {
			return;
		}
		_group.finish();
		_isConnected = _group.isConnected();
		_connInfo = _group.getConnectionInfo();
		super.okPressed();
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
	}

	private boolean checkOK() {
		boolean enabled = _group.canFinish();
		if (getButton(IDialogConstants.OK_ID) != null) {
			getButton(IDialogConstants.OK_ID).setEnabled(enabled);
		}
		return enabled;
	}

}

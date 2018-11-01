/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.actions;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.enablement.sybase.ISybaseJDBCConnectionProfileConstants;
import org.eclipse.datatools.enablement.sybase.asa.JDBCASAProfileMessages;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * Dialog to support creating multiple profiles if the ASA
 * server exposes multiple databases.
 * @author brianf
 *
 */
public class MultiASADialog extends TitleAreaDialog {

	// ui pieces
	private Button mCheckButton;
	private CheckboxTableViewer mList;
	private Button mSelectAll;
	private Button mDeselectAll;

	// cached items
	public Properties mCacheProps = null;
	private IConnectionProfile mProfile = null;
	private String[] mDBNames = null;

	/**
	 * @param parentShell
	 */
	public MultiASADialog(Shell parentShell) {
		super(parentShell);
        setShellStyle( getShellStyle() | SWT.RESIZE );
	}
	
	/**
	 * @param profile
	 */
	public void setProfile ( IConnectionProfile profile) {
		this.mProfile = profile;
	}
	
	/**
	 * @return
	 */
	public IConnectionProfile getProfile() {
		return this.mProfile;
	}

    /* (non-Javadoc)
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    protected void configureShell(Shell shell)
    {
        super.configureShell(shell);
        shell.setText(JDBCASAProfileMessages.getString("MultiASADialog.dialog.title")); //$NON-NLS-1$
    }

	/*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     */
	protected Control createDialogArea(Composite parent) {
		Composite content = (Composite) super.createDialogArea(parent);
        GridData gd = new GridData();
        GridLayout gl = new GridLayout(2, true);
        content.setLayout(gl);
        // Text box.
        gd.grabExcessHorizontalSpace = true;
        gd.grabExcessVerticalSpace = true;
        gd.verticalAlignment = GridData.FILL;
        gd.horizontalAlignment = GridData.FILL;
        
		this.mCheckButton = new Button( content, SWT.PUSH);
        GridData pingData = new GridData();
        pingData.horizontalSpan = 2;
        pingData.horizontalAlignment = GridData.BEGINNING;
        this.mCheckButton.setLayoutData(pingData);
        this.mCheckButton.setText(JDBCASAProfileMessages.getString("MultiASAWizardPage.checkButton.label")); //$NON-NLS-1$
        this.mCheckButton.addSelectionListener( new SelectionListener () {

			public void widgetSelected(SelectionEvent e) {
				Connection conn = JDBCHelper.getConnectionForProfile(getShell(), mProfile);
				if (conn != null) {
					String[] dbs = JDBCHelper.getDBNamesFromASA(conn);
					mList.setInput(dbs);
					mList.getTable().setEnabled(true);
					if (dbs == null) {
						updateOKButton(false);
						mList.getTable().setEnabled(false);
					}
					else if (dbs.length == 1) {
						List list = Arrays.asList(mList.getCheckedElements());
						mDBNames = (String[]) list.toArray(new String[list.size()]);
						setErrorMessage(JDBCASAProfileMessages.getString("MultiASAWizardPage.OnlyOneDBMessage")); //$NON-NLS-1$
						updateOKButton(false);
						mList.getTable().setEnabled(false);
					}
					else {
						setErrorMessage(null);
						updateOKButton(true);
						mList.getTable().setEnabled(true);
					}
					
					/************************** added by mzhou for cr 417911-1 *************************/
					//show the database specified in the original db connnection profile as selected element in the db list
					String dbName = (String) mProfile.getProperties(mProfile.getProviderId()).get(ISybaseJDBCConnectionProfileConstants.PROP_DB_NAME);
					if(dbName != null) {
						for(int i=0; i<dbs.length; i++) {
							if(dbName.equals(dbs[i])) {
								mList.setChecked(dbName, true);
							}
						}
					}
					/************************** added by mzhou for cr 417911-1 *************************/
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		
        Label label = new Label(content, SWT.NONE);
        GridData labelData = new GridData();
        labelData.horizontalSpan = 2;
        label.setLayoutData(labelData);
        label.setText(JDBCASAProfileMessages.getString("MultiASAWizardPage.Label.ChooseDbs")); //$NON-NLS-1$
        
        mList = CheckboxTableViewer.newCheckList(content, SWT.BORDER | SWT.MULTI );
        GridData listData = new GridData(SWT.FILL, SWT.FILL, true, true);
        listData.horizontalSpan = 2;
        mList.setContentProvider( new IStructuredContentProvider() {

        	private String[] mDatabases = null;
        	
			public void dispose() {
			}

			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
				if (newInput instanceof String[]) {
					mDatabases = (String[]) newInput;
				}
			}

			public Object[] getElements(Object inputElement) {
				return mDatabases;
			}});
        mList.setLabelProvider(new LabelProvider());
        mList.getTable().setLayoutData(listData);
        mList.getTable().setEnabled(false);
        mList.addCheckStateListener( new ICheckStateListener() {

			public void checkStateChanged(CheckStateChangedEvent event) {
				List list = Arrays.asList(mList.getCheckedElements());
				mDBNames = (String[]) list.toArray(new String[list.size()]);
			}
		});
        
		mSelectAll = new Button(content, SWT.PUSH);
		mSelectAll.setText(JDBCASAProfileMessages.getString("MultiASAWizardPage.selectAllButton.label")); //$NON-NLS-1$
		GridData gridData = new GridData();
		gridData.horizontalSpan = 1;
		gridData.horizontalAlignment = SWT.FILL;
		gridData.verticalAlignment = SWT.TOP;
		mSelectAll.setLayoutData(gridData);
		mSelectAll.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent event) {
				selectAll();
			}
		});

		mDeselectAll = new Button(content, SWT.PUSH);
		mDeselectAll.setText(JDBCASAProfileMessages.getString("MultiASAWizardPage.deselectAllButton.label")); //$NON-NLS-1$
		gridData = new GridData();
		gridData.horizontalSpan = 1;
		gridData.horizontalAlignment = SWT.FILL;
		gridData.verticalAlignment = SWT.TOP;
		mDeselectAll.setLayoutData(gridData);
		mDeselectAll.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent event) {
				unselectAll();
			}
		});
		
		setTitle(JDBCASAProfileMessages.getString("MultiASADialog.dialog.message.title")); //$NON-NLS-1$
		setMessage(JDBCASAProfileMessages.getString("MultiASADialog.dialog.message")); //$NON-NLS-1$

		return content;
	}
	
	/**
	 * 
	 */
	private void selectAll() {
		String[] names = (String[]) mList.getInput();
		mList.setCheckedElements(names);
		List list = Arrays.asList(mList.getCheckedElements());
		mDBNames = (String[]) list.toArray(new String[list.size()]);
	}
	
	/**
	 * 
	 */
	private void unselectAll() {
		String[] names = (String[]) mList.getInput();
		for (int i = 0; i < names.length; i++) {
			mList.setChecked(names[i], false);
		}
		List list = Arrays.asList(mList.getCheckedElements());
		mDBNames = (String[]) list.toArray(new String[list.size()]);
	}

	/**
	 * 
	 */
	public void clearList() {
		mList.setInput(new String[0]);
        mList.getTable().setEnabled(false);
		mCacheProps.clear();
		mCacheProps = null;
	}

	/**
	 * @return
	 */
	public String[] getDBNames() {
		return mDBNames;
	}

	/**
	 * @param enabled
	 */
	public void updateOKButton ( boolean enabled ) {
		Button okButton = getButton(IDialogConstants.OK_ID);
		if (okButton != null)
			okButton.setEnabled(enabled);
	}
}

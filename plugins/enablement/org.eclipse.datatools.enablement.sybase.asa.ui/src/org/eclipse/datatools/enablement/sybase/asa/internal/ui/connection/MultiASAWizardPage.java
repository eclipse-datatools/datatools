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
package org.eclipse.datatools.enablement.sybase.asa.internal.ui.connection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.eclipse.datatools.connectivity.ConnectionProfileConstants;
import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ConnectionProfile;
import org.eclipse.datatools.connectivity.internal.ui.wizards.BaseWizardPage;
import org.eclipse.datatools.connectivity.ui.wizards.NewConnectionProfileWizard;
import org.eclipse.datatools.enablement.sybase.ISybaseJDBCConnectionProfileConstants;
import org.eclipse.datatools.enablement.sybase.asa.IJDBCASAConnectionProfileConstants;
import org.eclipse.datatools.enablement.sybase.asa.JDBCASAProfileMessages;
import org.eclipse.datatools.enablement.sybase.asa.actions.JDBCHelper;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CheckboxTableViewer;
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
import org.eclipse.swt.widgets.Label;

/**
 * Wizard page to support creating multiple profiles if the ASA
 * server exposes multiple databases.
 * @author brianf
 *
 */
public class MultiASAWizardPage extends BaseWizardPage {

	// cached properties
	public Properties mCacheProps = null;
	
	// UI pieces
	private Button mCheckButton;
	private CheckboxTableViewer mList;
	private Button mSelectAll;
	private Button mDeselectAll;

	/**
	 * @param pageName
	 */
	public MultiASAWizardPage(String pageName) {
		super(pageName);
		setTitle(JDBCASAProfileMessages.getString("MultiASAWizardPage.title")); //$NON-NLS-1$
		setDescription(JDBCASAProfileMessages.getString("MultiASAWizardPage.description")); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		Composite content = new Composite(parent,SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		layout.makeColumnsEqualWidth = true;
		content.setLayout(layout);

		this.mCheckButton = new Button( content, SWT.PUSH);
        GridData pingData = new GridData();
        pingData.horizontalSpan = 2;
        pingData.horizontalAlignment = GridData.BEGINNING;
        this.mCheckButton.setLayoutData(pingData);
        this.mCheckButton.setText(JDBCASAProfileMessages.getString("MultiASAWizardPage.checkButton.label")); //$NON-NLS-1$
		
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

        this.mCheckButton.addSelectionListener( new SelectionListener () {

			public void widgetSelected(SelectionEvent e) {
				Connection conn = getConnectionForTempProfile();
				if (conn != null) {
					String[] dbs = JDBCHelper.getDBNamesFromASA(conn);
					mList.setInput(dbs);
					mList.getTable().setEnabled(true);
					if (dbs.length == 1) {
						mSelectAll.setEnabled(false);
						mDeselectAll.setEnabled(false);						
						mList.getTable().setEnabled(false);
						mList.setAllChecked(true);
					}
					else {
						mSelectAll.setEnabled(true);
						mDeselectAll.setEnabled(true);
						mList.getTable().setEnabled(true);
						mList.setAllChecked(false);
					}
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		
		setControl(content);
	}

	/**
	 * @return
	 */
	private Connection getConnectionForTempProfile() {
		IConnectionProfile cp = getTemporaryProfile();
		IConnection con = cp.createConnection(ConnectionProfileConstants.PING_FACTORY_ID);
		if (con != null && con.getConnectException() == null) {
			Connection conn = (Connection) con.getRawConnection();
			return conn;
		}
		else {
			MessageDialog.openError(getShell(), 
					JDBCASAProfileMessages.getString("MultiASAWizardPage.Error.title.CannotConnect"),  //$NON-NLS-1$
					JDBCASAProfileMessages.getString("MultiASAWizardPage.Error.msg.CannotConnect")); //$NON-NLS-1$
		}
		return null;
	}
	
	/**
	 * @return
	 */
	private IConnectionProfile getTemporaryProfile() {
		ConnectionProfile tempProfile = new ConnectionProfile("tempJDBCForPing","empty",IJDBCASAConnectionProfileConstants.PROVIDER_ID); //$NON-NLS-1$ //$NON-NLS-2$
		NewConnectionProfileWizard wiz = (NewConnectionProfileWizard) getWizard();
		Properties props = wiz.getProfileProperties();
		mCacheProps = props;
        tempProfile.setBaseProperties(props);
        return tempProfile;
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
	 * 
	 */
	private void selectAll() {
		String[] names = (String[]) mList.getInput();
		mList.setCheckedElements(names);
	}
	
	/**
	 * 
	 */
	private void unselectAll() {
		String[] names = (String[]) mList.getInput();
		for (int i = 0; i < names.length; i++) {
			mList.setChecked(names[i], false);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.internal.ui.wizards.ISkippable#getSkippable()
	 */
	public boolean getSkippable() {
		NewConnectionProfileWizard wiz = (NewConnectionProfileWizard) getWizard();
		Properties props = wiz.getProfileProperties();
		String dbname = props.getProperty(ISybaseJDBCConnectionProfileConstants.PROP_DB_NAME);
		if (dbname != null && dbname.length() > 0) 
			return true;
		return false;
	}
	
	/**
	 * @return
	 */
	public String[] getDBNames() {
        Object[] checked = mList.getCheckedElements();
        List list = Arrays.asList(checked);
		return (String[]) list.toArray( new String[ list.size() ]);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.internal.ui.wizards.ISummaryDataSource#getSummaryData()
	 */
	public List getSummaryData() {
		List data = new ArrayList();

		Object[] checked = mList.getCheckedElements();
		if (checked != null && checked.length > 0) {
			//if only one database selected, set 'database name' prop in summary.
			if (checked.length == 1) {
				data
						.add(new String[] {
								JDBCASAProfileMessages
										.getString("JDBCASAPropertyWizardPage.summary.databaseName.label"), //$NON-NLS-1$
								getDBNames()[0] });
			// if multiple databases selected, set 'selected databases' property in summary.
			} else {
				data.add(new String[] {
						JDBCASAProfileMessages
								.getString("MultiASAWizardPage.Summary.Label"), //$NON-NLS-1$
						"" }); //$NON-NLS-1$

				for (int i = 0; i < checked.length; i++) {
					data.add(new String[] { "", //$NON-NLS-1$
							(String) checked[i] });
				}
			}
		//if no database selected, set the 'database name' prop to the database name collected in the previous wizard page.    
		} else {
		    
		    NewConnectionProfileWizard wiz = (NewConnectionProfileWizard) getWizard();
	        Properties props = wiz.getProfileProperties();
	        String dbname = props.getProperty(ISybaseJDBCConnectionProfileConstants.PROP_DB_NAME);
		    
			data
					.add(new String[] {
							JDBCASAProfileMessages
									.getString("JDBCASAPropertyWizardPage.summary.databaseName.label"), //$NON-NLS-1$
									dbname });
		}

		return data;
	}
}

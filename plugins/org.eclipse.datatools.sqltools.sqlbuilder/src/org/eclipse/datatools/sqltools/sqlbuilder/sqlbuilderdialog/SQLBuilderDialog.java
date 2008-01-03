/*******************************************************************************
 * Copyright © 2000, 2007 Sybase, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.sqlbuilderdialog;

import java.util.HashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.sqltools.common.ui.dialog.SQLPainterDlg;
import org.eclipse.datatools.sqltools.core.profile.NoSuchProfileException;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.core.services.UIComponentService;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.result.ui.view.ResultsViewControl;
import org.eclipse.datatools.sqltools.sqlbuilder.IExecuteSQLListener;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.ParseException;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilder;
import org.eclipse.datatools.sqltools.sqlbuilder.input.SQLBuilderEditorInput;
import org.eclipse.datatools.sqltools.sqlbuilder.model.IOmitSchemaInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.OmitSchemaInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLBuilderConnectionInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLStatementInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.util.SQLFileUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * This dialog hosts the SQLBuilder and the ResultsView in a dialog.
 * 
 * @author Jeremy Lindop
 */
public class SQLBuilderDialog extends SQLPainterDlg 
	implements IExecuteSQLListener {

	public static final String DIALOG_TITLE = "SQL Builder";
	public static final String TAB_EDITOR = "Edit";
	public static final String TAB_RESULTS = "SQL Results";

	CTabFolder _tabFolder;
	CTabItem _tabItemResults;
	
	private SQLBuilder _sqlBuilder = null;
	private IFile _file = null;
	private ISQLEditorConnectionInfo _connectionInfo = null;

	ResultsViewControl _resultsViewControl;
	
	
    public SQLBuilderDialog(Shell parentShell, String statementType, String statement, String profileName, String database,
    		String parametersType, String parameter, String table, HashMap info)
    {
        super(parentShell, statementType, statement, profileName, database, parametersType, parameter, table, info);
        setShellStyle(SWT.RESIZE | SWT.TITLE | SWT.CLOSE | SWT.BORDER
				| SWT.APPLICATION_MODAL);

    }
	
	/**
	 * Overrides {@link org.eclipse.datatools.sqltools.common.ui.dialog.SQLPainterDlg.load()}
	 */
	public String load() {
		/*
		 * Create the SQLBuilder part of the dialog.
		 */
		_sqlBuilder = new SQLBuilder();
        try {
			_connectionInfo = new SQLBuilderConnectionInfo(ProfileUtil.getProfile(_profileName));
		} catch (NoSuchProfileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			// Get file from info param 
			_file = null;
			if (_info != null && (!_info.isEmpty())){
				Object value = _info.get(UIComponentService.KEY_FILE);
				if (value instanceof IFile){
					_file = (IFile) value;
				}
			}
			// Get omitSchemaInfo from file if available
			IOmitSchemaInfo omitSchemaInfo;
			if (_file != null){
				omitSchemaInfo = SQLFileUtil.getOmitSchemaInfo(_file);
			}
			else
			{
				omitSchemaInfo = new OmitSchemaInfo();
				omitSchemaInfo.initFromPreferences();
			}
			
			SQLBuilderEditorInput input = new SQLBuilderEditorInput(_connectionInfo, new SQLStatementInfo(_statement), omitSchemaInfo);
			_sqlBuilder.setInput(input);
			
		} catch (PartInitException e) {
			e.printStackTrace();
			return null;
		}
		catch (ParseException e){
			String sMessage = e.getLocalizedMessage() + " " + Messages.SQLQueryBuilderDialogParseFailOnOpenAskUserMessage;
			if (! MessageDialog.openQuestion(getParentShell(),
					Messages.SQLQueryBuilderDialogParseFailOnOpenAskUserTitle, sMessage)){
				return null;
			}
		}

		this.setBlockOnOpen(true);
		if (this.open() == Dialog.OK){
			return getSQL();
		}
		else {
			return null;
		}
	}
	
	/**
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	public void configureShell(Shell shell) {
		super.configureShell(shell);

		shell.setText(DIALOG_TITLE);

		// Make dialog 2/3 size of main Eclipse window
		shell.setSize(shell.computeSize(
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell()
						.getBounds().width * 2 / 3,
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell()
						.getBounds().height * 2 / 3));

	}

	/**
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	public Control createDialogArea(Composite parent) {
		// TODO
		if (_statement == null) {
			Shell shell = new Shell();
			MessageDialog.openInformation(shell, Messages._UI_SQLQUERYBUILDERDIALOG_NAME,
					Messages._UI_SQLQUERYBUILDERDIALOG_NAME + " Dialog was not passed an input to open.");
			return null;
		}

		/*
		 * Set dialog title
		 */
		getShell().setText(DIALOG_TITLE);
		
		/*
		 * Create controls to host the SQLBuilder
		 */
		Composite topComposite = createMainComposite(parent);
		 _tabFolder = createTabFolder(topComposite, SWT.BOTTOM);
		CTabItem tabEditor = createTabItem(_tabFolder, SWT.NONE, 0, TAB_EDITOR);
		Composite editComposite = createMainComposite(_tabFolder);
		tabEditor.setControl(editComposite);

		_sqlBuilder.createClient(editComposite);
		_sqlBuilder.addExecuteSQLListener(this);

		/*
		 * Create controls to host the results view
		 */
		_tabItemResults = createTabItem(_tabFolder, SWT.NONE, 1, TAB_RESULTS);
		Composite resultsComposite = createMainComposite(_tabFolder);
		_tabItemResults.setControl(resultsComposite);

		/*
		 * Add the results view
		 */
		_resultsViewControl = new ResultsViewControl(null);
		/*
		 * Tell the results view to use preferences
		 */
		_resultsViewControl.setUsePreferences(true);
		try {
			_resultsViewControl.init(null, null);
		} catch (PartInitException e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
		_resultsViewControl.createPartControl(resultsComposite);	
		GridData gd = new GridData(GridData.FILL, GridData.FILL, true, true);
		_resultsViewControl.getControl().setLayoutData(gd);
		
		filterResultsView(_connectionInfo.getConnectionProfile());

		
		
		return topComposite;
	}


	/**
	 * 
	 * @param connectionProfile
	 */
	private void filterResultsView(IConnectionProfile connectionProfile) {
        _resultsViewControl.addResultHistoryFilter(
    			new ResultsHistoryFilter(connectionProfile.getName()));

	}

	/**
	 * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				false);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, true);
	}

	/**
	 * @see org.eclipse.jface.dialogs.Dialog#buttonPressed(int)
	 */
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID) {
			if (_file != null){
				_sqlBuilder.saveOmitSchemaInfo(_file);
			}
			setReturnCode(Dialog.OK);
			close();
		} else if (buttonId == IDialogConstants.CANCEL_ID) {
			setReturnCode(Dialog.CANCEL);
			close();
		}
	}

	/*
	 * Returns whether the SQL in the dialog has changed.
	 */
	public boolean isDirty() {
		String sCurrentSQL = _sqlBuilder.getSQL();
		if (sCurrentSQL == null){
			sCurrentSQL = "";
		}
		return (! sCurrentSQL.equals(_statement));
	}

	/**
	 * Returns the SQL statement from the dialog
	 * @return
	 */
	public String getSQL() {
		return _sqlBuilder.getSQL();
	}
	
	/*
	 * Utility function to create Composite
	 */
	private Composite createMainComposite(Composite parent) {
		Composite mainComposite = new Composite(parent, SWT.NONE);

		GridLayout mainCompLayout = new GridLayout(1, true);
		mainComposite.setLayout(mainCompLayout);

		GridData gd = new GridData(GridData.FILL, GridData.FILL, true, true);
		mainComposite.setLayoutData(gd);

		return mainComposite;
	}

	/*
	 * Utility function to create TabFolder
	 */
	private CTabFolder createTabFolder(Composite parent, int style) {
		CTabFolder tabFolder = new CTabFolder(parent, style);

		GridLayout mainCompLayout = new GridLayout(1, true);
		tabFolder.setLayout(mainCompLayout);

		GridData gd = new GridData(GridData.FILL, GridData.FILL, true, true);
		tabFolder.setLayoutData(gd);

		return tabFolder;
	}

	/*
	 * Utility function to create TabItem
	 */
	private CTabItem createTabItem(CTabFolder tabFolder, int style, int tabNum,
			String name) {
		CTabItem tabItem = new CTabItem(tabFolder, style, tabNum);
		tabItem.setText(name);
		return tabItem;
	}

	/**
	 * @see org.eclipse.jface.window.Window#close()
	 */
	public boolean close() {
		_sqlBuilder.removeExecuteSQLListener(this);
		_resultsViewControl.dispose();
		return super.close();
	}

	public void executedSQL() {
		_tabFolder.setSelection(_tabItemResults);
	}


}

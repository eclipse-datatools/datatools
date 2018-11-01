/*******************************************************************************
 * Copyright 2000, 2008 Sybase, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *     Actuate Corporation - refactoring to allow extended class to use other type of input
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.sqlbuilderdialog;

import java.util.HashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.modelbase.sql.query.helper.StatementHelper;
import org.eclipse.datatools.sqltools.sql.ui.dialogs.SQLPainterDlg;
import org.eclipse.datatools.sqltools.core.profile.NoSuchProfileException;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.core.services.UIComponentService;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.result.ResultsViewAPI;
import org.eclipse.datatools.sqltools.result.ResultsViewPlugin;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.datatools.sqltools.result.ui.view.ResultsViewControl;
import org.eclipse.datatools.sqltools.sqlbuilder.IExecuteSQLListener;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.ParseException;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilder;
import org.eclipse.datatools.sqltools.sqlbuilder.input.ISQLBuilderEditorInput;
import org.eclipse.datatools.sqltools.sqlbuilder.input.SQLBuilderEditorInput;
import org.eclipse.datatools.sqltools.sqlbuilder.model.IOmitSchemaInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.OmitSchemaInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLBuilderConnectionInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLStatementInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.util.SQLFileUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ViewerFilter;
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

	public static final String DIALOG_TITLE = Messages._UI_SQLQUERYBUILDERDIALOG_NAME;
	public static final String TAB_EDITOR = Messages.SQLQueryBuilderDialogEditTabName;
	public static final String TAB_RESULTS = Messages.SQLQueryBuilderDialogResultsTabName;
	private static final String LEFT_BRACKET = " <";    //$NON-NLS-1$
    private static final String RIGHT_BRACKET = ">";    //$NON-NLS-1$
    private static final String EMPTY_STRING = "";    //$NON-NLS-1$
	
	CTabFolder _tabFolder;
	CTabItem _tabItemResults;
	
	private SQLBuilder _sqlBuilder = null;
	private IFile _file = null;
	private ISQLEditorConnectionInfo _connectionInfo = null;
    private ISQLBuilderEditorInput _editorInput = null;

	ResultsViewControl _resultsViewControl;
	ViewerFilter[] _resultViewFilters;
	
    public SQLBuilderDialog(Shell parentShell, String statementType, String statement, String profileName, String database,
    		String parametersType, String parameter, String table, HashMap info)
    {
        super(parentShell, statementType, statement, profileName, database, parametersType, parameter, table, info);
        initDialog();
    }
    
    /**
     * Constructor with no editor input information.
     * Sub-class must then use {@link #setInput(ISQLBuilderEditorInput)} to specify input information.
     * @param parentShell
     *            the parent shell, or <code>null</code> to create a top-level shell
     */
    protected SQLBuilderDialog(Shell parentShell) {
        super(parentShell, null, null, null, null, null, null);
        initDialog();
    }
	
    private void initDialog()
    {
        setShellStyle(SWT.RESIZE | SWT.TITLE | SWT.CLOSE | SWT.BORDER
                | SWT.APPLICATION_MODAL);

        /*
         * Create the SQLBuilder part of the dialog.
         */
        _sqlBuilder = new SQLBuilder();
    }
    
	protected SQLBuilder getSQLBuilder()
    {
        return _sqlBuilder;
    }

    /**
	 * Overrides {@link org.eclipse.datatools.sqltools.common.ui.dialog.SQLPainterDlg.load()}
	 */
	public String load() {
        try {
			_connectionInfo = new SQLBuilderConnectionInfo(ProfileUtil.getProfile(_profileName));
		} catch (NoSuchProfileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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
		
		SQLBuilderEditorInput input;
		if (_statement != null && _statement.length() > 0){
			input = new SQLBuilderEditorInput(_connectionInfo, new SQLStatementInfo(_statement), omitSchemaInfo);
		}
		else {
			input = new SQLBuilderEditorInput(_connectionInfo, StatementHelper.STATEMENT_TYPE_SELECT);
			input.setOmitSchemaInfo(omitSchemaInfo);
		}

		boolean isInputLoaded = setInput(input);
		if( ! isInputLoaded )
		{
            String sMessage = Messages.SQLQueryBuilderDialogParseFailOnOpenAskUserMessage ;
            boolean bContinue = MessageDialog.openQuestion(getParentShell(),
                    Messages.SQLQueryBuilderDialogParseFailOnOpenAskUserTitle, sMessage);
            if( ! bContinue )
                return null;
		}

		// Update statement type
		if (_statementType != null) {
			int statementType = StatementHelper.STATEMENT_TYPE_SELECT;
			if (_statementType != null && _statementType.equalsIgnoreCase("UPDATE")) {
				statementType = StatementHelper.STATEMENT_TYPE_UPDATE;
			} else if (_statementType != null && _statementType.equalsIgnoreCase("DELETE")) {
				statementType = StatementHelper.STATEMENT_TYPE_DELETE;
			} else if (_statementType != null && _statementType.equalsIgnoreCase("INSERT")) {
				statementType = StatementHelper.STATEMENT_TYPE_INSERT;
			}
			_sqlBuilder.getDomainModel().initializeFromType(statementType);
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
     * Sets the SQLBuilderDialog's editor input. The caller should continue creating
     * the dialog only if setInput returns true.
     * @param editorInput
     * @return true if the input was loaded, false otherwise.
     */
    protected boolean setInput(ISQLBuilderEditorInput editorInput){
        _editorInput = editorInput;

        try {
            _sqlBuilder.setInput(_editorInput);
            return true;
        } catch (PartInitException e) {
            e.printStackTrace();
            return false;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
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
        if (_editorInput == null) {
            Shell shell = new Shell();
            MessageDialog.openInformation(shell, DIALOG_TITLE,
                    Messages.SQLQueryBuilderDialogNoInput);
            return null;
        }

		/*
		 * Set dialog title
		 */
        String dialogTitle = DIALOG_TITLE;
        if( _editorInput.getName() != null && _editorInput.getName().length() > 0 )
            dialogTitle += LEFT_BRACKET + _editorInput.getName() + RIGHT_BRACKET;
		parent.getShell().setText(dialogTitle);
        
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
		_resultsViewControl = new ResultsViewControl();
		ResultsViewPlugin.getDefault().getResultManager().removeResultManagerListener(new ResultsViewUIPlugin.ResultViewControlListener());
		ResultsViewPlugin.getDefault().getResultManager().addResultManagerListener(_resultsViewControl);
		/*
		 * Tell the results view to use preferences
		 */
		_resultsViewControl.setUsePreferences(true);
		try {
			_resultsViewControl.init();
		} catch (PartInitException e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
		_resultsViewControl.createPartControl(resultsComposite);	
		GridData gd = new GridData(GridData.FILL, GridData.FILL, true, true);
		_resultsViewControl.getControl().setLayoutData(gd);
		
		filterResultsView(_editorInput.getConnectionInfo().getConnectionProfile());

		// set focus on the editor tab
        _tabFolder.setSelection(tabEditor);
		return topComposite;
	}


	/**
	 * 
	 * @param connectionProfile
	 */
	private void filterResultsView(IConnectionProfile connectionProfile) {
        // if no filter is specified, use default filter for specified profile name
	    ViewerFilter[] filters = _resultViewFilters;
	    if( filters == null || filters.length == 0 )
	    {
	        filters = new ViewerFilter[] { 
	                    new ResultsHistoryFilter(connectionProfile.getName()) };
	    }
	    
	    for( int i=0; i < filters.length; i++ )
	    {
	        _resultsViewControl.addResultHistoryFilter( filters[i] );
	    }
	}

	protected void setResultViewFilters( ViewerFilter[] filters ) {
	    _resultViewFilters = filters;
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
			sCurrentSQL = EMPTY_STRING;
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
		ResultsViewPlugin.getDefault().getResultManager().removeResultManagerListener(_resultsViewControl);
		_resultsViewControl.dispose();
		ResultsViewPlugin.getDefault().getResultManager().addResultManagerListener(new ResultsViewUIPlugin.ResultViewControlListener());
		return super.close();
	}

	public void executedSQL() {
		_tabFolder.setSelection(_tabItemResults);
	}

	/**
	 * Returns the tab folder control that hosts the SQLBuilder and results view.
	 * @return
	 */
	protected CTabFolder getTabFolder() {
		return _tabFolder;
	}

}

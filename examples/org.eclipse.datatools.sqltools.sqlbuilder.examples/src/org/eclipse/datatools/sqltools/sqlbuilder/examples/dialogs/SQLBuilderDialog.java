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
package org.eclipse.datatools.sqltools.sqlbuilder.examples.dialogs;

import java.io.IOException;
import java.io.StringWriter;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.sqltools.result.ui.view.ResultsViewControl;
import org.eclipse.datatools.sqltools.sqlbuilder.IContentChangeListener;
import org.eclipse.datatools.sqltools.sqlbuilder.ISQLBuilderEditorInput;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilder;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderEditorInput;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderFileEditorInput;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderInputFactory;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderStorageEditorInput;
import org.eclipse.datatools.sqltools.sqlbuilder.examples.util.ResultsHistoryFilter;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorStorage;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.XMLMemento;

/**
 * This class demonstrates how to host the SQLBuilder in a dialog. See notes
 * under the relevant methods for more details.
 * 
 * @author Jeremy Lindop
 */
public class SQLBuilderDialog extends Dialog implements IContentChangeListener {

	public static final int I_SAVE_ID = IDialogConstants.CLIENT_ID;
	public static final String I_SAVE_LABEL = "Save";

	public static final String DIALOG_TITLE = "SQL Builder Dialog";
	public static final String TAB_EDITOR = "Edit";
	public static final String TAB_RESULTS = "SQL Results";

	private SQLBuilder _sqlBuilder = null;
	private ISQLBuilderEditorInput _editorInput = null;

	ResultsViewControl _resultsViewControl;
	
	/**
	 * Constructor for SQLBuilderDialog
	 * 
	 * @param parentShell
	 * @param editorInput
	 */
	public SQLBuilderDialog(Shell parentShell,
			ISQLBuilderEditorInput editorInput) {
		super(parentShell);
		_editorInput = editorInput;
		setShellStyle(SWT.RESIZE | SWT.TITLE | SWT.CLOSE | SWT.BORDER
				| SWT.APPLICATION_MODAL);
	}

	/**
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell shell) {
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
			MessageDialog.openInformation(shell, "SQL Query Builder",
					"SQLBuilder Dialog was not passed an input to open.");
			return null;
		}

		/*
		 * Set dialog title
		 */
		getShell().setText(
				DIALOG_TITLE + " <" + _editorInput.getName() + ">");
		
		/*
		 * Create controls to host the SQLBuilder
		 */
		Composite topComposite = createMainComposite(parent);
		CTabFolder tabFolder = createTabFolder(topComposite, SWT.BOTTOM);
		CTabItem tabEditor = createTabItem(tabFolder, SWT.NONE, 0, TAB_EDITOR);
		Composite editComposite = createMainComposite(tabFolder);
		tabEditor.setControl(editComposite);

		try {
			/*
			 * Create the SQLBuilder part of the dialog.
			 */
			_sqlBuilder = new SQLBuilder();
			_sqlBuilder.addContentChangeListener(this);
			_sqlBuilder.setInput(_editorInput);
			_sqlBuilder.createClient(editComposite);

		} catch (PartInitException e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}

		/*
		 * Create controls to host the results view
		 */
		CTabItem tabResults = createTabItem(tabFolder, SWT.NONE, 1, TAB_RESULTS);
		Composite resultsComposite = createMainComposite(tabFolder);
		tabResults.setControl(resultsComposite);

		/*
		 * Add the results view
		 */
		_resultsViewControl = new ResultsViewControl(null);
		/*
		 * Tell the results view not to use preferences
		 */
		_resultsViewControl.setUsePreferences(false);
		try {
			_resultsViewControl.init(null, null);
		} catch (PartInitException e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
		_resultsViewControl.createPartControl(resultsComposite);	
		GridData gd = new GridData(GridData.FILL, GridData.FILL, true, true);
		_resultsViewControl.getControl().setLayoutData(gd);
		
		filterResultsView(_editorInput.getConnectionInfo().getConnectionProfile());
		
		return topComposite;
	}

	private void filterResultsView(IConnectionProfile connectionProfile) {
        _resultsViewControl.addResultHistoryFilter(
    			new ResultsHistoryFilter(connectionProfile.getName()));

	}

	/**
	 * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		Button btnSave = createButton(parent, I_SAVE_ID, I_SAVE_LABEL, false);
		btnSave.setEnabled(false);
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				false);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, true);
	}

	/**
	 * @see org.eclipse.jface.dialogs.Dialog#buttonPressed(int)
	 */
	protected void buttonPressed(int buttonId) {
		if (buttonId == I_SAVE_ID) {
			if (isDirty()) {
				/*
				 * If input is a file, save it
				 */
				if (_editorInput instanceof SQLBuilderFileEditorInput) {
					_sqlBuilder.doSave(null);
				}
				/*
				 * If input is Storage based, serialize it and display the XML.
				 * This demonstrates how to save the dialog's SQLStatement and associated information.
				 */
				else if (_editorInput instanceof SQLBuilderStorageEditorInput) {
					/*
					 *  Create an XMLMemento and save the SQLStatement, ConnectionInfo and OmitSchemaInfo to it
					 */
					XMLMemento memento = XMLMemento
							.createWriteRoot(SQLBuilderInputFactory.ID_XML_MEMENTO_ROOT_ELEMENT);
					// Create a SQLEditorStorage passing a name and the SQL Statement as parameters
					SQLEditorStorage storage = new SQLEditorStorage(
							_editorInput.getName(), _sqlBuilder.getSQL());
					// Create a SQLBuilderStorageEditorInput based on the SQLEditorStorage just created
					SQLBuilderStorageEditorInput storageEditorInput = new SQLBuilderStorageEditorInput(
							storage);
					// Set the SQLBuilderStorageEditorInput's connectionInfo
					storageEditorInput.setConnectionInfo(_sqlBuilder.getConnectionInfo());
					// Set the SQLBuilderStorageEditorInput's OmitSchemaInfo
					storageEditorInput.setOmitSchemaInfo(_sqlBuilder.getOmitSchemaInfo());

					// Save the state of the SQLBuilderStorageEditorInput to the XMLMemento
					SQLBuilderInputFactory.saveState(memento,
							storageEditorInput);

					// Write out memento to a string and show it in a Message Box.
					// This is to demonstrate what the memento contents look like
					StringWriter writer = new StringWriter();
					try {
						memento.save(writer);
					} catch (IOException e) {
						e.printStackTrace();
					}
					MessageBox mb = new MessageBox(Display.getCurrent()
							.getActiveShell(), SWT.ICON_INFORMATION);
					mb.setText("SQL Query Builder XMLMemento");
					mb.setMessage(writer.toString());
					mb.open();
					
					/*
					 * Set _sqlBuilder's dirty flag to false
					 */
					_sqlBuilder.setDirty(false);
				}
				else if (_editorInput instanceof SQLBuilderEditorInput) {
					String sEncodedConnectionInfo = _sqlBuilder.getConnectionInfo().encode();
					String sEncodedOmitSchemaInfo = _sqlBuilder.getOmitSchemaInfo().encode();
					String sSQL = _sqlBuilder.getSQL();
					
					String sMsg = "Encoded ConnectionInfo: <"+ sEncodedConnectionInfo + ">\n" +
								  "Encoded OmitSchemaInfo: <"+ sEncodedOmitSchemaInfo + ">\n" +
								  "SQL:\n" + sSQL.trim() + "";
					
					MessageBox mb = new MessageBox(Display.getCurrent()
							.getActiveShell(), SWT.ICON_INFORMATION);
					mb.setText("SQL Query Builder Data");
					mb.setMessage(sMsg);
					mb.open();
					
				}
				
				/*
				 * Update the dialog's dirty status after saving
				 */
				updateDirtyStatus();
			}
		} else if (buttonId == IDialogConstants.OK_ID) {
			/*
			 * If dialog was opened on a file, save the file. 
			 */
			if (_editorInput instanceof SQLBuilderFileEditorInput) {
				_sqlBuilder.doSave(null);
			}	
			setReturnCode(Dialog.OK);
			close();
		} else if (buttonId == IDialogConstants.CANCEL_ID) {
			setReturnCode(Dialog.CANCEL);
			close();
		}
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
	 * Updates the button states
	 */
	protected void updateButtons() {
		Button btnSave = this.getButton(I_SAVE_ID);
		if (btnSave != null) {
			btnSave.setEnabled(isDirty());
		}
	}

	/**
	 * Updates the dirty status
	 */
	protected void updateDirtyStatus() {
		if (getShell().getText() != null) {
			if (isDirty()) {
				if (!getShell().getText().startsWith("*")) {
					getShell().setText("*" + getShell().getText());
				}
			} else if (getShell().getText().startsWith("*")) {
				getShell().setText(getShell().getText().substring(1));
			}
		}
		updateButtons();
	}

	/**
	 * Tests whether the dialog is dirty by asking the SQLBuilder's whether it
	 * is dirty
	 * 
	 * @return boolean flag indicating the dirty state
	 */
	protected boolean isDirty() {
		return _sqlBuilder.isDirty();
	}

	public void notifyContentChange() {
		updateDirtyStatus();
	}
	
	/**
	 * @see org.eclipse.jface.window.Window#close()
	 */
	public boolean close() {
		_resultsViewControl.dispose();
		
		return super.close();
	}
	
}

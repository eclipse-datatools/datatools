/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: shongxum - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.wizards;

import java.io.File;
import java.util.Vector;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.ui.ProfileImageRegistry;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

/**
 * @author shongxum
 */
public class ExportProfilesDialog extends Dialog {

	private CheckboxTableViewer tvViewer;

	private Text txtFile;

	private Button btnEncryption;

	private IConnectionProfile[] mProfiles;

	private File mFile;

	private boolean mNeedEncryption;

	class TableLabelProvider extends LabelProvider implements
			ITableLabelProvider {

		public String getColumnText(Object element, int columnIndex) {
			IConnectionProfile profile = (IConnectionProfile) element;
			return profile.getName();
		}

		public Image getColumnImage(Object element, int columnIndex) {
			IConnectionProfile profile = (IConnectionProfile) element;
			return ProfileImageRegistry.getInstance().getProfileImage(
					profile.getProvider());
		}
	}

	class ContentProvider implements IStructuredContentProvider {

		public Object[] getElements(Object inputElement) {
			return ProfileManager.getInstance().getProfiles();
		}

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	public ExportProfilesDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.CLOSE | SWT.RESIZE);
	}

	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.marginHeight = 20;
		gridLayout.numColumns = 3;
		container.setLayout(gridLayout);
		{
			final Group group = new Group(container, SWT.NONE);
			group.setText(ConnectivityUIPlugin.getDefault().getResourceString(
					"ExportProfilesDialog.group.text")); //$NON-NLS-1$
			final GridData gridData = new GridData(GridData.FILL_BOTH);
			gridData.horizontalSpan = 3;
			group.setLayoutData(gridData);
			final GridLayout gridLayout_1 = new GridLayout();
			gridLayout_1.makeColumnsEqualWidth = true;
			gridLayout_1.numColumns = 3;
			group.setLayout(gridLayout_1);
			{
				final CheckboxTableViewer checkboxTableViewer = CheckboxTableViewer
						.newCheckList(group, SWT.V_SCROLL | SWT.BORDER
								| SWT.H_SCROLL);
				checkboxTableViewer.setSorter(new ViewerSorter());
				checkboxTableViewer.setLabelProvider(new TableLabelProvider());
				checkboxTableViewer.setContentProvider(new ContentProvider());
				final Table table = checkboxTableViewer.getTable();
				final GridData gridData_1 = new GridData(GridData.FILL_BOTH);
				gridData_1.horizontalSpan = 3;
				gridData_1.widthHint = 392;
				table.setLayoutData(gridData_1);
				checkboxTableViewer.setInput(new Object());
				tvViewer = checkboxTableViewer;
			}
			{
				final Button button = new Button(group, SWT.NONE);
				button.addSelectionListener(new SelectionAdapter() {

					public void widgetSelected(SelectionEvent e) {
						tvViewer.setAllChecked(true);
					}
				});
				button.setLayoutData(new GridData(
						GridData.HORIZONTAL_ALIGN_CENTER));
				button.setText(ConnectivityUIPlugin.getDefault()
						.getResourceString("ExportProfilesDialog.button.text")); //$NON-NLS-1$
			}
			{
				final Label label = new Label(group, SWT.NONE);
			}
			{
				final Button button = new Button(group, SWT.NONE);
				button.addSelectionListener(new SelectionAdapter() {

					public void widgetSelected(SelectionEvent e) {
						tvViewer.setAllChecked(false);
					}
				});
				button.setLayoutData(new GridData(
						GridData.HORIZONTAL_ALIGN_CENTER));
				button
						.setText(ConnectivityUIPlugin.getDefault()
								.getResourceString(
										"ExportProfilesDialog.button.text1")); //$NON-NLS-1$
			}
		}
		{
			final Label label = new Label(container, SWT.NONE);
			final GridData gridData = new GridData(
					GridData.HORIZONTAL_ALIGN_FILL);
			gridData.horizontalSpan = 3;
			gridData.widthHint = 495;
			label.setLayoutData(gridData);
		}
		{
			final Label label = new Label(container, SWT.NONE);
			final GridData gridData = new GridData();
			gridData.horizontalIndent = 5;
			label.setLayoutData(gridData);
			label.setText(ConnectivityUIPlugin.getDefault().getResourceString(
					"ExportProfilesDialog.label.text")); //$NON-NLS-1$
		}
		{
			final Text text = new Text(container, SWT.BORDER);
			final GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
			gridData.widthHint = 243;
			text.setLayoutData(gridData);
			txtFile = text;
		}
		{
			final Button button = new Button(container, SWT.NONE);
			button.addSelectionListener(new SelectionAdapter() {

				public void widgetSelected(SelectionEvent e) {
					txtFile.setText(new FileDialog(getShell()).open());
				}
			});
			button
					.setLayoutData(new GridData(
							GridData.HORIZONTAL_ALIGN_CENTER));
			button.setText(ConnectivityUIPlugin.getDefault().getResourceString(
					"ExportProfilesDialog.button.text2")); //$NON-NLS-1$
		}
		{
			final Button button = new Button(container, SWT.CHECK);
			final GridData gridData = new GridData(GridData.GRAB_HORIZONTAL);
			gridData.horizontalIndent = 10;
			gridData.horizontalSpan = 3;
			button.setLayoutData(gridData);
			button.setText(ConnectivityUIPlugin.getDefault().getResourceString(
					"ExportProfilesDialog.btnEncryption.text")); //$NON-NLS-1$
			button.setSelection(true);
			btnEncryption = button;
		}

		return container;
	}

	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(ConnectivityUIPlugin.getDefault().getResourceString(
				"ExportProfilesDialog.null.title")); //$NON-NLS-1$
	}

	public IConnectionProfile[] getSelectedProfiles() {
		return mProfiles;
	}

	public File getFile() {
		return mFile;
	}

	public boolean needEncryption() {
		return mNeedEncryption;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {
		Vector vec = new Vector();
		Object[] elements = tvViewer.getCheckedElements();

		if (elements.length == 0) {
			MessageDialog.openError(getShell(), ConnectivityUIPlugin
					.getDefault().getResourceString("dialog.title.error"), //$NON-NLS-1$
					ConnectivityUIPlugin.getDefault().getResourceString(
							"actions.export.noselection")); //$NON-NLS-1$
			return;
		}

		if (txtFile.getText().length() == 0) {
			MessageDialog.openError(getShell(), ConnectivityUIPlugin
					.getDefault().getResourceString("dialog.title.error"), //$NON-NLS-1$
					ConnectivityUIPlugin.getDefault().getResourceString(
							"actions.export.nofile")); //$NON-NLS-1$
			return;
		}
		
		for (int i = 0; i < elements.length; i++) {
			vec.add(elements[i]);
		}
		mProfiles = (IConnectionProfile[]) vec
				.toArray(new IConnectionProfile[0]);
		mFile = new File(txtFile.getText());
		
		/* validate that the file name has a valid directory as parent */
		String fileParent = mFile.getParent();
		boolean hasParent = !(fileParent == null || mFile.getParent().length() == 0);
		boolean pathEndsInFileSeparator = false;
		boolean hasValidParent = false;
		if (hasParent) {
			pathEndsInFileSeparator = fileParent.endsWith("" + File.separatorChar);
			File parentFile = new File(fileParent);
			hasValidParent = parentFile.exists();
		}
		if (!hasParent || !hasValidParent || !pathEndsInFileSeparator ) {
			MessageDialog.openError(getShell(), ConnectivityUIPlugin
					.getDefault().getResourceString("dialog.title.error"), //$NON-NLS-1$
					ConnectivityUIPlugin.getDefault().getResourceString(
							"actions.export.notvalidfile")); //$NON-NLS-1$
			return;
		}
		mNeedEncryption = btnEncryption.getSelection();
		super.okPressed();
	}
}
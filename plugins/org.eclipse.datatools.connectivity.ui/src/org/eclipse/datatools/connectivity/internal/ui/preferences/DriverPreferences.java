/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.preferences;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.connectivity.drivers.DriverMgmtMessages;
import org.eclipse.datatools.connectivity.drivers.DriverValidator;
import org.eclipse.datatools.connectivity.drivers.IDriverMgmtConstants;
import org.eclipse.datatools.connectivity.drivers.IPropertySet;
import org.eclipse.datatools.connectivity.drivers.PropertySetImpl;
import org.eclipse.datatools.connectivity.drivers.XMLFileManager;
import org.eclipse.datatools.connectivity.drivers.models.CategoryDescriptor;
import org.eclipse.datatools.connectivity.drivers.models.DriversProvider;
import org.eclipse.datatools.connectivity.drivers.models.TemplateDescriptor;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.DriverTreeContentProvider;
import org.eclipse.datatools.connectivity.internal.ui.DriverTreeLabelProvider;
import org.eclipse.datatools.connectivity.internal.ui.DriverTreeSorter;
import org.eclipse.datatools.connectivity.internal.ui.dialogs.EditDriverDialog;
import org.eclipse.datatools.connectivity.internal.ui.dialogs.NewDriverDialog;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceColors;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Driver management preference page.
 * 
 * @author brianf
 */
public class DriverPreferences extends PreferencePage implements
		IWorkbenchPreferencePage {

	// ui elements
	private Button mAddButton;
	private Button mRemoveButton;
	private Button mEditButton;
	private Button mCopyButton;
	private Label mErrorLabel;
	private TreeViewer mTreeViewer;

	private Action mAddAction;
	private Action mRemoveAction;
	private Action mEditAction;
	private Action mCopyAction;

	// dirty flag for save/no save
	private boolean mDirty = false;

	/**
	 * Default Constructor
	 */
	public DriverPreferences() {
		super();
	}

	/**
	 * Constructor with a page title
	 * 
	 * @param title
	 */
	public DriverPreferences(String title) {
		super(title);
	}

	/**
	 * Constructor with a title and icon
	 * 
	 * @param title
	 * @param image
	 */
	public DriverPreferences(String title, ImageDescriptor image) {
		super(title, image);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents(Composite parent) {
		Font font = parent.getFont();

		Composite content = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		content.setLayout(layout);
		GridData data = new GridData();
		data.verticalAlignment = GridData.FILL;
		data.horizontalAlignment = GridData.FILL;
		content.setLayoutData(data);
		content.setFont(font);

		Label label = new Label(content, SWT.LEFT);
		label.setText(DriverMgmtMessages
				.getString("DriverPreferences.label.availableDrivers")); //$NON-NLS-1$
		data = new GridData();
		data.horizontalAlignment = GridData.FILL;
		data.horizontalSpan = 2;
		label.setLayoutData(data);
		label.setFont(font);

		this.mErrorLabel = new Label(content, SWT.LEFT | SWT.WRAP);
		data = new GridData(GridData.VERTICAL_ALIGN_FILL
				| GridData.HORIZONTAL_ALIGN_FILL);
		data.horizontalSpan = 2;
		data.heightHint = 35;
		this.mErrorLabel.setLayoutData(data);
		this.mErrorLabel.setForeground(JFaceColors.getErrorText(getControl()
				.getDisplay()));
		this.mErrorLabel.setFont(font);

		this.mTreeViewer = new TreeViewer(content, SWT.BORDER | SWT.H_SCROLL
				| SWT.V_SCROLL);
		data = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL);
		data.verticalSpan = 10;
		this.mTreeViewer.getTree().setLayoutData(data);
		this.mTreeViewer.getTree().setFont(font);

		makeActions();
		hookContextMenu();

		this.mTreeViewer.setContentProvider(new DriverTreeContentProvider());
		this.mTreeViewer.setLabelProvider(new DecoratingLabelProvider(
				new DriverTreeLabelProvider(), new DriverTreeLabelProvider()));
		this.mTreeViewer.setSorter(new DriverTreeSorter());
		this.mTreeViewer.setInput(DriversProvider.getInstance());
		this.mTreeViewer.expandToLevel(3);

		this.mTreeViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {

					public void selectionChanged(SelectionChangedEvent event) {
						StructuredSelection selection = (StructuredSelection) DriverPreferences.this.mTreeViewer
								.getSelection();

						if (selection != null && selection.size() > 0 && selection.getFirstElement() != null)
							updateButtons (selection.getFirstElement());
						else 
							updateButtons ( null );
					}

				});

		this.mTreeViewer.addDoubleClickListener( new IDoubleClickListener() {

			public void doubleClick(DoubleClickEvent event) {
				StructuredSelection selection = (StructuredSelection) DriverPreferences.this.mTreeViewer
						.getSelection();
				editDriver(selection);
			}
		});
		Composite groupComponent = new Composite(content, SWT.NULL);
		GridLayout groupLayout = new GridLayout();
		groupLayout.marginWidth = 0;
		groupLayout.marginHeight = 0;
		groupComponent.setLayout(groupLayout);
		data = new GridData();
		data.verticalAlignment = GridData.FILL;
		data.horizontalAlignment = GridData.FILL;
		groupComponent.setLayoutData(data);
		groupComponent.setFont(font);

		this.mAddButton = new Button(groupComponent, SWT.PUSH);
		this.mAddButton.setText(DriverMgmtMessages
				.getString("DriverPreferences.button.addDriver")); //$NON-NLS-1$
		this.mAddButton.setLayoutData(data);
		this.mAddButton.setFont(font);
		setButtonLayoutData(this.mAddButton);
		this.mAddButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				addDriver(DriverPreferences.this.mTreeViewer.getSelection());
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

		});

		this.mEditButton = new Button(content, SWT.PUSH);
		this.mEditButton.setText(DriverMgmtMessages
				.getString("DriverPreferences.button.editDriver")); //$NON-NLS-1$
		this.mEditButton.setLayoutData(data);
		this.mEditButton.setFont(font);
		setButtonLayoutData(this.mEditButton);
		this.mEditButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				editDriver(DriverPreferences.this.mTreeViewer.getSelection());
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

		});

		this.mRemoveButton = new Button(content, SWT.PUSH);
		this.mRemoveButton.setText(DriverMgmtMessages
				.getString("DriverPreferences.button.removeDriver")); //$NON-NLS-1$
		this.mRemoveButton.setLayoutData(data);
		this.mRemoveButton.setFont(font);
		setButtonLayoutData(this.mRemoveButton);
		this.mRemoveButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				removeDriver(DriverPreferences.this.mTreeViewer.getSelection());
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

		});

		this.mCopyButton = new Button(content, SWT.PUSH);
		this.mCopyButton.setText(DriverMgmtMessages
				.getString("DriverPreferences.button.copyDriver")); //$NON-NLS-1$
		this.mCopyButton.setLayoutData(data);
		this.mCopyButton.setFont(font);
		setButtonLayoutData(this.mCopyButton);
		this.mCopyButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				copyDriver(DriverPreferences.this.mTreeViewer.getSelection());
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

		});

		if (CategoryDescriptor.getRootCategories() != null && CategoryDescriptor.getRootCategories().length > 0)
			this.mTreeViewer.setSelection(new StructuredSelection(
					CategoryDescriptor.getRootCategories()[0]));
		
		updateButtons(null);

		return content;
	}

	private void updateButtons ( Object selection ) {
		// if they selected a category...
		if (selection instanceof CategoryDescriptor) {
			DriverPreferences.this.mAddButton.setEnabled(true);
			DriverPreferences.this.mAddAction.setEnabled(true);
			DriverPreferences.this.mRemoveButton
					.setEnabled(false);
			DriverPreferences.this.mRemoveAction
					.setEnabled(false);
			DriverPreferences.this.mEditButton
					.setEnabled(false);
			DriverPreferences.this.mEditAction
					.setEnabled(false);
			DriverPreferences.this.mCopyButton
					.setEnabled(false);
			DriverPreferences.this.mCopyAction
					.setEnabled(false);
			DriverPreferences.this.mErrorLabel.setText(""); //$NON-NLS-1$
			DriverPreferences.this.mErrorLabel.computeSize(
					SWT.DEFAULT, SWT.DEFAULT);
		}
		// if they selected a driver instance
		else if (selection instanceof IPropertySet) {
			DriverPreferences.this.mAddButton.setEnabled(false);
			DriverPreferences.this.mAddAction.setEnabled(false);
			DriverPreferences.this.mRemoveButton
					.setEnabled(true);
			DriverPreferences.this.mRemoveAction
					.setEnabled(true);
			DriverPreferences.this.mEditButton.setEnabled(true);
			DriverPreferences.this.mEditAction.setEnabled(true);
			DriverPreferences.this.mCopyButton.setEnabled(true);
			DriverPreferences.this.mCopyAction.setEnabled(true);

			IPropertySet ps = (IPropertySet) selection;
			if (ps != null) {
				validate(ps);
			}
		}
		else {
			DriverPreferences.this.mAddButton.setEnabled(false);
			DriverPreferences.this.mAddAction.setEnabled(false);
			DriverPreferences.this.mRemoveButton.setEnabled(false);
			DriverPreferences.this.mRemoveAction.setEnabled(false);
			DriverPreferences.this.mEditButton.setEnabled(false);
			DriverPreferences.this.mEditAction.setEnabled(false);
			DriverPreferences.this.mCopyButton.setEnabled(false);
			DriverPreferences.this.mCopyAction.setEnabled(false);
		}
	}
	
	private void addDriver(ISelection selection) {
		StructuredSelection sselection = (StructuredSelection) selection;

		// Add a new driver instance from one of the templates for the category
		if (sselection.getFirstElement() instanceof CategoryDescriptor) {

			CategoryDescriptor descriptor = (CategoryDescriptor) sselection
					.getFirstElement();
			NewDriverDialog dlg = new NewDriverDialog(getShell(), descriptor
					.getId());
			if (dlg.open() == Window.OK) {
				IPropertySet instance = dlg.getNewDriverInstance();

				DriverPreferences.this.mDirty = true;

				// Should we edit immediately? if yes, pop up the edit dialog
				if (dlg.getEditImmediately()) {
					EditDriverDialog editdlg = new EditDriverDialog(getShell(),
							instance);
					editdlg.open();
				}

				// stash the new instance
				List psetsList = ((DriverTreeContentProvider) DriverPreferences.this.mTreeViewer
						.getContentProvider()).getDriverInstances();
				psetsList.add(instance);
				saveChanges();

				// refresh
				DriverPreferences.this.mTreeViewer.refresh();

			}
		}
	}

	private void editDriver(ISelection selection) {
		StructuredSelection sselection = (StructuredSelection) selection;
		if (sselection.getFirstElement() instanceof IPropertySet) {
			IPropertySet instance = (IPropertySet) sselection.getFirstElement();
			EditDriverDialog dlg = new EditDriverDialog(getShell(), instance);
			if (dlg.open() == Window.OK) {
				saveChanges();
				DriverPreferences.this.mDirty = true;
				DriverPreferences.this.mTreeViewer.refresh();
				validate(instance);
			}
		}
	}

	private void removeDriver(ISelection selection) {
		StructuredSelection sselection = (StructuredSelection) selection;
		if (sselection.getFirstElement() instanceof IPropertySet) {
			IPropertySet instance = (IPropertySet) sselection.getFirstElement();
			if (MessageDialog.openQuestion(getShell(), DriverMgmtMessages
					.getString("DriverPreferences.title.removeMessage"), //$NON-NLS-1$
					DriverMgmtMessages.format(
							"DriverPreferences.text.removeMessage", //$NON-NLS-1$ 
							new String[] { instance.getName()})) == true) {
				List psetsList = ((DriverTreeContentProvider) DriverPreferences.this.mTreeViewer
						.getContentProvider()).getDriverInstances();
				psetsList.remove(instance);
				saveChanges();
				CategoryDescriptor category = getCategoryFromPropertySet(instance);
				DriverPreferences.this.mTreeViewer.refresh(category);
				DriverPreferences.this.mDirty = true;
				DriverPreferences.this.mErrorLabel.setText(""); //$NON-NLS-1$
			}
		}
	}

	private void copyDriver(ISelection selection) {
		StructuredSelection sselection = (StructuredSelection) selection;
		if (sselection.getFirstElement() instanceof IPropertySet) {
			IPropertySet instance = (IPropertySet) sselection.getFirstElement();
			if (instance != null) {
				IPropertySet cloned = (IPropertySet) ((PropertySetImpl) instance)
						.clone();

				String copyPrefix = DriverMgmtMessages
						.getString("PropertySetImpl.copy_prefix"); //$NON-NLS-1$
				String copyIdSuffix = DriverMgmtMessages
						.getString("PropertySetImpl.copy_id_suffix"); //$NON-NLS-1$
				String name = copyPrefix + instance.getName();
				String id = instance.getID() + copyIdSuffix;
				cloned.setID(id);
				cloned.setName(name);

				List psetsList = ((DriverTreeContentProvider) DriverPreferences.this.mTreeViewer
						.getContentProvider()).getDriverInstances();
				psetsList.add(cloned);
				saveChanges();

				DriverPreferences.this.mTreeViewer.refresh();

				DriverPreferences.this.mTreeViewer
						.setSelection(new StructuredSelection(cloned));
			}
		}
	}

	private void makeActions() {
		this.mAddAction = new AddAction();
		this.mAddAction.setText(DriverMgmtMessages
				.getString("DriverPreferences.button.addDriver")); //$NON-NLS-1$
		this.mEditAction = new EditAction();
		this.mEditAction.setText(DriverMgmtMessages
				.getString("DriverPreferences.button.editDriver")); //$NON-NLS-1$
		this.mRemoveAction = new RemoveAction();
		this.mRemoveAction.setText(DriverMgmtMessages
				.getString("DriverPreferences.button.removeDriver")); //$NON-NLS-1$
		this.mCopyAction = new CopyAction();
		this.mCopyAction.setText(DriverMgmtMessages
				.getString("DriverPreferences.button.copyDriver")); //$NON-NLS-1$
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu"); //$NON-NLS-1$
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {

			public void menuAboutToShow(IMenuManager manager) {
				manager.add(DriverPreferences.this.mAddAction);
				manager.add(DriverPreferences.this.mEditAction);
				manager.add(DriverPreferences.this.mRemoveAction);
				manager.add(DriverPreferences.this.mCopyAction);
			}
		});
		Menu menu = menuMgr.createContextMenu(this.mTreeViewer.getControl());
		this.mTreeViewer.getControl().setMenu(menu);
	}

	/**
	 * Validate the property set and put an appropriate error message up
	 * 
	 * @param instance
	 */
	private void validate(IPropertySet instance) {
		String driverType = instance.getBaseProperties().getProperty(
				IDriverMgmtConstants.PROP_DEFN_TYPE); //$NON-NLS-1$
		if (driverType != null) {
			TemplateDescriptor template = TemplateDescriptor
					.getDriverTemplateDescriptor(driverType);
			if (template != null) {
				DriverValidator validator = new DriverValidator(template,
						instance);
				String mErrorMessage = ""; //$NON-NLS-1$
				if (!validator.isValid()) {
					mErrorMessage = validator.getMessage();
				}
				this.mErrorLabel.setText(mErrorMessage);
				this.mErrorLabel.computeSize(SWT.DEFAULT, SWT.DEFAULT);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
		// empty
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#performApply()
	 */
	protected void performApply() {
		// if there have been changes, save them
		if (this.mDirty)
			saveChanges();
		super.performApply();
	}

	/**
	 * Save the changes back to the instances file.
	 */
	private void saveChanges() {
		XMLFileManager.setFileName(IDriverMgmtConstants.DRIVER_FILE);
		List psetsList = ((DriverTreeContentProvider) this.mTreeViewer
				.getContentProvider()).getDriverInstances();
		Object[] objs = psetsList.toArray();
		IPropertySet[] propsets = new IPropertySet[objs.length];
		for (int i = 0; i < objs.length; i++) {
			propsets[i] = (IPropertySet) objs[i];
		}
		try {
			XMLFileManager.saveNamedPropertySet(propsets);
		}
		catch (CoreException e) {
			ConnectivityUIPlugin.getDefault().log(e);
		}
		this.mDirty = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.IPreferencePage#performOk()
	 */
	public boolean performOk() {
		// if there have been changes, save them
		if (this.mDirty)
			saveChanges();
		return super.performOk();
	}

	/**
	 * Return the category descriptor for the instance passed in.
	 * 
	 * @param instance
	 * @return
	 */
	private CategoryDescriptor getCategoryFromPropertySet(IPropertySet instance) {
		if (instance != null) {
			if (instance.getBaseProperties().getProperty(
					IDriverMgmtConstants.PROP_DEFN_TYPE) != null) {
				String driverType = instance.getBaseProperties().getProperty(
						IDriverMgmtConstants.PROP_DEFN_TYPE);
				TemplateDescriptor descriptor = TemplateDescriptor
						.getDriverTemplateDescriptor(driverType);
				if (descriptor != null) {
					CategoryDescriptor category = CategoryDescriptor
							.getCategoryDescriptor(descriptor
									.getParentCategory());
					return category;
				}
			}
		}
		return null;
	}

	private class AddAction extends Action {

		public void run() {
			addDriver(DriverPreferences.this.mTreeViewer.getSelection());
		}
	}

	private class RemoveAction extends Action {

		public void run() {
			removeDriver(DriverPreferences.this.mTreeViewer.getSelection());
		}
	}

	private class EditAction extends Action {

		public void run() {
			editDriver(DriverPreferences.this.mTreeViewer.getSelection());
		}
	}

	private class CopyAction extends Action {

		public void run() {
			copyDriver(DriverPreferences.this.mTreeViewer.getSelection());
		}
	}
}

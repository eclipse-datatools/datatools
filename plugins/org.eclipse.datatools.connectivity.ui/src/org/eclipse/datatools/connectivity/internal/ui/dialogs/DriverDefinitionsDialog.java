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
package org.eclipse.datatools.connectivity.internal.ui.dialogs;

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
import org.eclipse.datatools.connectivity.internal.ui.DriverTreeFilter;
import org.eclipse.datatools.connectivity.internal.ui.DriverTreeLabelProvider;
import org.eclipse.datatools.connectivity.internal.ui.DriverTreeSorter;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.JFaceColors;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * Driver Management dialog
 * 
 * @author brianf
 */
public class DriverDefinitionsDialog extends TitleAreaDialog {

	// memento keys
	public final static String MEMENTO_ROOT = "Drivers Definitions Driver_Dialog_Root";//$NON-NLS-1$
	public final static String MEMENTO_DIALOG_SIZE_HEIGHT = "Dialog_Size_Height";//$NON-NLS-1$
	public final static String MEMENTO_DIALOG_SIZE_WIDTH = "Dialog_Size_Width";//$NON-NLS-1$

	// ui components
	private Button mAddButton;
	private Button mRemoveButton;
	private Button mEditButton;
	private Button mCopyButton;
	private TreeViewer mTreeViewer;
	private Label mErrorLabel;

	// viewer filter
	private DriverTreeFilter mViewerFilter;

	// dirty flag
	private boolean mDirty = false;

	// Category id
	private String mCategoryId;

	// Initial driver name
	private String mInitialDriverName;

	// stashed selected propertyset
	private IPropertySet selectedPS = null;

	/**
	 * Constructor
	 * 
	 * @param parentShell
	 */
	public DriverDefinitionsDialog(Shell parentShell) {
		super(parentShell);
		this.setShellStyle(getShellStyle() | SWT.RESIZE | SWT.DIALOG_TRIM);
	}

	/**
	 * Constructor
	 * 
	 * @param parentShell
	 * @param category
	 */
	public DriverDefinitionsDialog(Shell parentShell, String category) {
		this(parentShell);
		if (CategoryDescriptor.getCategoryDescriptor(category) != null) {
			this.mViewerFilter = new DriverTreeFilter();
			this.mViewerFilter.setCategoryId(category);
			this.mCategoryId = category;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);

		Font font = parent.getFont();

		Composite content = new Composite(area, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		content.setLayout(layout);
		GridData data = new GridData(GridData.FILL_BOTH);
		content.setLayoutData(data);
		content.setFont(font);

		this.mErrorLabel = new Label(content, SWT.LEFT | SWT.WRAP);
		data = new GridData(GridData.VERTICAL_ALIGN_FILL
				| GridData.HORIZONTAL_ALIGN_FILL);
		data.horizontalSpan = 2;
		data.heightHint = 35;
		this.mErrorLabel.setLayoutData(data);
		this.mErrorLabel.setForeground(JFaceColors.getErrorText(content
				.getDisplay()));
		this.mErrorLabel.setFont(font);

		this.mTreeViewer = new TreeViewer(content, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL);
		this.mTreeViewer.getTree().setLayoutData(data);
		this.mTreeViewer.getTree().setFont(font);

		this.mTreeViewer.setContentProvider(new DriverTreeContentProvider());
		this.mTreeViewer.setLabelProvider(new DecoratingLabelProvider(
				new DriverTreeLabelProvider(), new DriverTreeLabelProvider()));
		this.mTreeViewer.setSorter(new DriverTreeSorter());
		if (this.mViewerFilter != null) {
			this.mTreeViewer.addFilter(this.mViewerFilter);
		}
		this.mTreeViewer.setInput(DriversProvider.getInstance());
		this.mTreeViewer.expandToLevel(3);

		this.mTreeViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {

					public void selectionChanged(SelectionChangedEvent event) {
						DriverDefinitionsDialog.this.selectedPS = null;
						StructuredSelection selection = (StructuredSelection) DriverDefinitionsDialog.this.mTreeViewer
								.getSelection();
						if (selection.getFirstElement() instanceof CategoryDescriptor) {
							DriverDefinitionsDialog.this.mAddButton
									.setEnabled(true);
							DriverDefinitionsDialog.this.mRemoveButton
									.setEnabled(false);
							DriverDefinitionsDialog.this.mEditButton
									.setEnabled(false);
							DriverDefinitionsDialog.this.mErrorLabel
									.setText(""); //$NON-NLS-1$
							DriverDefinitionsDialog.this.mErrorLabel
									.computeSize(SWT.DEFAULT, SWT.DEFAULT);
						}
						else if (selection.getFirstElement() instanceof IPropertySet) {
							DriverDefinitionsDialog.this.mAddButton
									.setEnabled(false);
							DriverDefinitionsDialog.this.mRemoveButton
									.setEnabled(true);
							DriverDefinitionsDialog.this.mEditButton
									.setEnabled(true);
							DriverDefinitionsDialog.this.selectedPS = (IPropertySet) selection
									.getFirstElement();
							if (DriverDefinitionsDialog.this.selectedPS != null) {
								validate(DriverDefinitionsDialog.this.selectedPS);
							}
						}
					}

				});

		Composite groupComponent = new Composite(content, SWT.NULL);
		GridLayout groupLayout = new GridLayout();
		groupLayout.marginWidth = 0;
		groupLayout.marginHeight = 0;
		groupComponent.setLayout(groupLayout);
		data = new GridData();
		data.verticalAlignment = GridData.BEGINNING;
		data.horizontalAlignment = GridData.FILL;
		groupComponent.setLayoutData(data);
		groupComponent.setFont(font);

		this.mAddButton = new Button(groupComponent, SWT.PUSH);
		this.mAddButton.setText(DriverMgmtMessages
				.getString("DriverPreferences.button.addDriver")); //$NON-NLS-1$
		this.mAddButton.setLayoutData(new GridData());
		this.mAddButton.setFont(font);
		setButtonLayoutData(this.mAddButton);
		this.mAddButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				StructuredSelection selection = (StructuredSelection) DriverDefinitionsDialog.this.mTreeViewer
						.getSelection();
				if (selection.getFirstElement() instanceof CategoryDescriptor) {
					CategoryDescriptor descriptor = (CategoryDescriptor) selection
							.getFirstElement();
					NewDriverDialog dlg;
					if (DriverDefinitionsDialog.this.mCategoryId != null) {
						dlg = new NewDriverDialog(getShell(), descriptor
								.getId());
					}
					else {
						dlg = new NewDriverDialog(getShell());
					}

					if (dlg.open() == Window.OK) {
						IPropertySet instance = dlg.getNewDriverInstance();

						DriverDefinitionsDialog.this.mDirty = true;

						if (dlg.getEditImmediately()) {
							EditDriverDialog editdlg = new EditDriverDialog(
									getShell(), instance);
							editdlg.open();
						}

						List psetsList = ((DriverTreeContentProvider) DriverDefinitionsDialog.this.mTreeViewer
								.getContentProvider()).getDriverInstances();
						psetsList.add(instance);

						DriverDefinitionsDialog.this.mTreeViewer.refresh();
					}
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

		});

		this.mEditButton = new Button(groupComponent, SWT.PUSH);
		this.mEditButton.setText(DriverMgmtMessages
				.getString("DriverPreferences.button.editDriver")); //$NON-NLS-1$
		this.mEditButton.setLayoutData(new GridData());
		this.mEditButton.setFont(font);
		setButtonLayoutData(this.mEditButton);
		this.mEditButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				StructuredSelection selection = (StructuredSelection) DriverDefinitionsDialog.this.mTreeViewer
						.getSelection();
				if (selection.getFirstElement() instanceof IPropertySet) {
					IPropertySet instance = (IPropertySet) selection
							.getFirstElement();
					EditDriverDialog dlg = new EditDriverDialog(getShell(),
							instance);
					if (dlg.open() == Window.OK) {
						DriverDefinitionsDialog.this.mDirty = true;
						DriverDefinitionsDialog.this.mTreeViewer.refresh();
						validate(instance);
					}
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

		});

		this.mRemoveButton = new Button(groupComponent, SWT.PUSH);
		this.mRemoveButton.setText(DriverMgmtMessages
				.getString("DriverPreferences.button.removeDriver")); //$NON-NLS-1$
		this.mRemoveButton.setLayoutData(new GridData());
		this.mRemoveButton.setFont(font);
		setButtonLayoutData(this.mRemoveButton);
		this.mRemoveButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				StructuredSelection selection = (StructuredSelection) DriverDefinitionsDialog.this.mTreeViewer
						.getSelection();
				if (selection.getFirstElement() instanceof IPropertySet) {
					IPropertySet instance = (IPropertySet) selection
							.getFirstElement();
					if (MessageDialog
							.openQuestion(
									getShell(),
									DriverMgmtMessages
											.getString("DriverPreferences.title.removeMessage"), //$NON-NLS-1$
									DriverMgmtMessages
											.format(
													"DriverPreferences.text.removeMessage", //$NON-NLS-1$ 
													new String[] { instance
															.getName()})) == true) {
						List psetsList = ((DriverTreeContentProvider) DriverDefinitionsDialog.this.mTreeViewer
								.getContentProvider()).getDriverInstances();
						psetsList.remove(instance);
						CategoryDescriptor category = getCategoryFromPropertySet(instance);
						DriverDefinitionsDialog.this.mTreeViewer
								.refresh(category);
						DriverDefinitionsDialog.this.mDirty = true;
					}
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

		});

		this.mCopyButton = new Button(groupComponent, SWT.PUSH);
		this.mCopyButton.setText(DriverMgmtMessages
				.getString("DriverPreferences.button.copyDriver")); //$NON-NLS-1$
		this.mCopyButton.setLayoutData(new GridData());
		this.mCopyButton.setFont(font);
		setButtonLayoutData(this.mCopyButton);
		this.mCopyButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				StructuredSelection selection = (StructuredSelection) DriverDefinitionsDialog.this.mTreeViewer
						.getSelection();
				if (selection.getFirstElement() instanceof IPropertySet) {
					IPropertySet instance = (IPropertySet) selection
							.getFirstElement();
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

						List psetsList = ((DriverTreeContentProvider) DriverDefinitionsDialog.this.mTreeViewer
								.getContentProvider()).getDriverInstances();
						psetsList.add(cloned);

						DriverDefinitionsDialog.this.mTreeViewer.refresh();

						DriverDefinitionsDialog.this.mTreeViewer
								.setSelection(new StructuredSelection(cloned));
					}
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

		});

		setTitle(DriverMgmtMessages.getString("DriverDefinitionsDialog.title")); //$NON-NLS-1$
		setMessage(DriverMgmtMessages
				.getString("DriverDefinitionsDialog.message")); //$NON-NLS-1$

		if (this.mInitialDriverName != null) {
			IPropertySet pset = getNamedInstance(this.mInitialDriverName);
			if (pset != null)
				this.mTreeViewer.setSelection(new StructuredSelection(pset),
						true);
			else {
				this.mTreeViewer.setSelection(new StructuredSelection(
						CategoryDescriptor.getRootCategories()[0]));
			}
		}
		else
			this.mTreeViewer.setSelection(new StructuredSelection(
					CategoryDescriptor.getRootCategories()[0]));

		return content;

	}

	/*
	 * Validate the current instance and reset the error label
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
	 * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(DriverMgmtMessages
				.getString("DriverDefinitionsDialog.windowTitle")); //$NON-NLS-1$

		IDialogSettings dset = ConnectivityUIPlugin.getDefault()
				.getDialogSettings();
		if (dset != null) {
			IDialogSettings dSection = dset.getSection(MEMENTO_ROOT);
			if (dSection != null) {
				if (dSection.get(MEMENTO_DIALOG_SIZE_HEIGHT) != null
						&& dSection.get(MEMENTO_DIALOG_SIZE_HEIGHT).trim()
								.length() > 0) {
					int height = dSection.getInt(MEMENTO_DIALOG_SIZE_HEIGHT);
					int width = dSection.getInt(MEMENTO_DIALOG_SIZE_WIDTH);
					Point newsize = new Point(height, width);
					newShell.setSize(newsize);
				}
			}
		}
	}

	/*
	 * Save the dialog settings
	 */
	private void saveState() {
		IDialogSettings dset = ConnectivityUIPlugin.getDefault()
				.getDialogSettings();
		if (dset != null) {
			IDialogSettings dSection = dset.getSection(MEMENTO_ROOT);
			if (dSection == null)
				dSection = dset.addNewSection(MEMENTO_ROOT);
			if (dSection != null) {
				Point size = getShell().getSize();
				dSection.put(MEMENTO_DIALOG_SIZE_HEIGHT, size.x);
				dSection.put(MEMENTO_DIALOG_SIZE_WIDTH, size.y);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {
		if (this.mDirty) {
			saveChanges();
		}
		saveState();
		super.okPressed();
	}

	/*
	 * Retrieve a named instance
	 */
	private IPropertySet getNamedInstance(String name) {
		List psetsList = ((DriverTreeContentProvider) this.mTreeViewer
				.getContentProvider()).getDriverInstances();
		Object[] objs = psetsList.toArray();
		for (int i = 0; i < objs.length; i++) {
			IPropertySet pset = (IPropertySet) objs[i];
			if (pset.getName().equals(name)) {
				return pset;
			}
		}
		return null;
	}

	/*
	 * Save the instances back to the file
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

	/**
	 * Retrieve the selected instance
	 * 
	 * @return
	 */
	public IPropertySet getSelectedDefinition() {
		return this.selectedPS;
	}

	/*
	 * Retrieve the category from the instance.
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

	/**
	 * Set the initial driver name
	 * 
	 * @param driverName
	 */
	public void setInitialDriverName(String driverName) {
		this.mInitialDriverName = driverName;
	}

	/**
	 * Return the initial driver name.
	 * 
	 * @return
	 */
	public String getInitialDriverName() {
		return this.mInitialDriverName;
	}

}

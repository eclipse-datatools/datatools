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

import java.io.File;
import java.util.Properties;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Path;
import org.eclipse.datatools.connectivity.drivers.DriverManager;
import org.eclipse.datatools.connectivity.drivers.DriverMgmtMessages;
import org.eclipse.datatools.connectivity.drivers.DriverValidator;
import org.eclipse.datatools.connectivity.drivers.IDriverMgmtConstants;
import org.eclipse.datatools.connectivity.drivers.IPropertySet;
import org.eclipse.datatools.connectivity.drivers.PropertySetImpl;
import org.eclipse.datatools.connectivity.drivers.models.CategoryDescriptor;
import org.eclipse.datatools.connectivity.drivers.models.TemplateDescriptor;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.DriverPropertySourceProvider;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.views.properties.PropertySheetPage;

import com.ibm.icu.util.StringTokenizer;

/**
 * Dialog used to edit a driver instance.
 * 
 * @author brianf
 */
public class EditDriverDialog extends TitleAreaDialog {

	// memento keys
	public final static String MEMENTO_ROOT = "Drivers Edit Driver_Dialog_Root";//$NON-NLS-1$
	public final static String MEMENTO_DIALOG_SIZE_HEIGHT = "Dialog_Size_Height";//$NON-NLS-1$
	public final static String MEMENTO_DIALOG_SIZE_WIDTH = "Dialog_Size_Width";//$NON-NLS-1$

	// previous directory browsed
	private static String previouslyBrowsedDirectory = ""; //$NON-NLS-1$
	private PageBook book = null;
	private IPropertySet mPropertySet = null;
	private TemplateDescriptor descriptor = null;

	// ui components
	private List list;
	private Text mDriverNameText;
	private Text mDriverTypeText;
	private Button mAddJar;
	private Button mEditJar;
	private Button mRemoveJar;
	private Button mClearAll;
	private Button mOKButton;

	// driver properties
	private String mDriverName;
	private String mJarList;
	private String mDriverTypeID;

	/**
	 * Constructor
	 * 
	 * @param parentShell
	 */
	public EditDriverDialog(Shell parentShell) {
		super(parentShell);
		this.setShellStyle(getShellStyle() | SWT.RESIZE | SWT.DIALOG_TRIM);
	}

	public EditDriverDialog(Shell parentShell, String driverTypeID) {
		this(parentShell);
		this.mDriverTypeID = driverTypeID;
		this.descriptor = TemplateDescriptor
				.getDriverTemplateDescriptor(this.mDriverTypeID);
	}

	public EditDriverDialog(Shell parentShell, IPropertySet pset) {
		this(parentShell);
		this.mPropertySet = pset;
		if (this.mPropertySet.getBaseProperties().getProperty(
				IDriverMgmtConstants.PROP_DEFN_TYPE) != null) {
			String driverType = this.mPropertySet.getBaseProperties()
					.getProperty(IDriverMgmtConstants.PROP_DEFN_TYPE);
			this.mDriverTypeID = driverType;
			this.descriptor = TemplateDescriptor
					.getDriverTemplateDescriptor(this.mDriverTypeID);
		}
	}

	protected IPage createDefaultPage(PageBook book) {
        PropertySheetPage page = new PropertySheetPage();
        page.createControl(book);
        return page;
    }
    
    /*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		area.setLayout(new GridLayout());

		Composite contents = new Composite(area, SWT.NONE);
		contents.setLayout(new GridLayout());
		contents.setLayoutData(new GridData(GridData.FILL_BOTH));
		{
			final Composite composite = new Composite(contents, SWT.NONE);
			composite.setLayoutData(new GridData(GridData.FILL_BOTH));
			final GridLayout gridLayout = new GridLayout();
			composite.setLayout(gridLayout);
			{
				final Label label = new Label(composite, SWT.NONE);
				label.setText(DriverMgmtMessages
						.getString("EditDriverDialog.label.driverNameText")); //$NON-NLS-1$
			}
			{
				this.mDriverNameText = new Text(composite, SWT.BORDER);
				this.mDriverNameText.setLayoutData(new GridData(
						GridData.FILL_HORIZONTAL));
				this.mDriverNameText.addModifyListener(new ModifyListener() {

					public void modifyText(ModifyEvent e) {
						EditDriverDialog.this.mDriverName = EditDriverDialog.this.mDriverNameText
								.getText();
						validateName();
					}
				});
			}
			{
				final Label label = new Label(composite, SWT.NONE);
				label.setText(DriverMgmtMessages
						.getString("EditDriverDialog.label.driverTypeText")); //$NON-NLS-1$
			}
			{
				this.mDriverTypeText = new Text(composite, SWT.BORDER);
				this.mDriverTypeText.setLayoutData(new GridData(
						GridData.FILL_HORIZONTAL));
				this.mDriverTypeText.setEnabled(false);
			}
			{
				final Label label = new Label(composite, SWT.NONE);
				label.setText(DriverMgmtMessages
						.getString("EditDriverDialog.label.driverFileList")); //$NON-NLS-1$
			}
			{
				final Composite composite_1 = new Composite(composite, SWT.NONE);
				final GridData gridData = new GridData(GridData.FILL_BOTH
						| GridData.GRAB_VERTICAL);
				gridData.horizontalIndent = -5;
				gridData.horizontalSpan = 2;
				composite_1.setLayoutData(gridData);
				final GridLayout gridLayout_1 = new GridLayout();
				gridLayout_1.numColumns = 3;
				composite_1.setLayout(gridLayout_1);
				{
					this.list = new List(composite_1, SWT.BORDER | SWT.H_SCROLL
							| SWT.V_SCROLL);
					final GridData gridData_1 = new GridData(GridData.FILL_BOTH);
					gridData_1.verticalSpan = 4;
					gridData_1.horizontalSpan = 2;
					this.list.setLayoutData(gridData_1);

					this.list.addSelectionListener(new SelectionListener() {

						public void widgetSelected(SelectionEvent e) {
							boolean enabled = (EditDriverDialog.this.list
									.getSelectionCount() > 0);
							EditDriverDialog.this.mEditJar.setEnabled(enabled);
							EditDriverDialog.this.mRemoveJar
									.setEnabled(enabled);
						}

						public void widgetDefaultSelected(SelectionEvent e) {
							widgetSelected(e);
						}

					});
				}
				{
					this.mAddJar = new Button(composite_1, SWT.NONE);
					this.mAddJar.addSelectionListener(new SelectionAdapter() {

						public void widgetSelected(SelectionEvent e) {
							handleLocationBrowseButtonPressed();
							updateJarList();
						}
					});
					this.mAddJar.setLayoutData(new GridData(
							GridData.HORIZONTAL_ALIGN_FILL));
					this.mAddJar.setText(DriverMgmtMessages
							.getString("EditDriverDialog.button.addJar")); //$NON-NLS-1$
				}
				{
					this.mEditJar = new Button(composite_1, SWT.NONE);
					this.mEditJar.addSelectionListener(new SelectionAdapter() {

						public void widgetSelected(SelectionEvent e) {
							if (EditDriverDialog.this.list.getSelectionCount() > 0) {
								String selectedItem = EditDriverDialog.this.list
										.getItem(EditDriverDialog.this.list
												.getSelectionIndex());
								handleLocationEditButtonPressed(selectedItem);
								updateJarList();
							}
						}
					});
					this.mEditJar.setLayoutData(new GridData(
							GridData.HORIZONTAL_ALIGN_FILL));
					this.mEditJar.setText(DriverMgmtMessages
							.getString("EditDriverDialog.button.editJar")); //$NON-NLS-1$
				}
				{
					this.mRemoveJar = new Button(composite_1, SWT.NONE);
					this.mRemoveJar
							.addSelectionListener(new SelectionAdapter() {

								public void widgetSelected(SelectionEvent e) {
									if (EditDriverDialog.this.list
											.getSelectionCount() > 0) {
										String[] selected = EditDriverDialog.this.list
												.getSelection();
										for (int i = 0; i < selected.length; i++) {
											EditDriverDialog.this.list
													.remove(selected[i]);
										}
										updateJarList();
									}
								}
							});
					this.mRemoveJar.setLayoutData(new GridData(
							GridData.HORIZONTAL_ALIGN_FILL));
					this.mRemoveJar.setText(DriverMgmtMessages
							.getString("EditDriverDialog.button.removeJar")); //$NON-NLS-1$
				}
				{
					this.mClearAll = new Button(composite_1, SWT.NONE);
					this.mClearAll.setLayoutData(new GridData(
							GridData.HORIZONTAL_ALIGN_FILL
									| GridData.VERTICAL_ALIGN_BEGINNING));
					this.mClearAll.setText(DriverMgmtMessages
							.getString("EditDriverDialog.button.clearAllJars")); //$NON-NLS-1$
					this.mClearAll.addSelectionListener(new SelectionAdapter() {

						public void widgetSelected(SelectionEvent e) {
							EditDriverDialog.this.list.removeAll();
							updateJarList();
						}
					});
				}
			}
			{
				if (hasVisibleProperties()) {
					final Label label = new Label(composite, SWT.NONE);
					label.setText(DriverMgmtMessages
							.getString("EditDriverDialog.label.properties")); //$NON-NLS-1$

					final Composite composite_2 = new Composite(composite,
							SWT.NONE);
					final GridData gridData = new GridData(GridData.FILL_BOTH);
					gridData.horizontalIndent = -5;
					gridData.horizontalSpan = 2;
					composite_2.setLayoutData(gridData);
					final GridLayout gridLayout_2 = new GridLayout();
					gridLayout_2.numColumns = 1;
					composite_2.setLayout(gridLayout_2);
					{
						book = new PageBook(composite_2, SWT.NONE);
						book.setLayoutData(new GridData(GridData.FILL_BOTH));
				        PropertySheetPage page = new PropertySheetPage();
				        page.createControl(book);
						DriverPropertySourceProvider mpsp = null;
						if (this.mPropertySet != null) {
							mpsp = new DriverPropertySourceProvider(this.mPropertySet, this.descriptor);
						}
						else {
							mpsp = new DriverPropertySourceProvider();
						}
						page.setPropertySourceProvider(mpsp);
				        book.showPage(page.getControl());
						page.selectionChanged(null, new StructuredSelection(this.mPropertySet));
					}
				}
			}
		}
		setTitle(DriverMgmtMessages.getString("EditDriverDialog.title")); //$NON-NLS-1$
		setMessage(DriverMgmtMessages.getString("EditDriverDialog.message")); //$NON-NLS-1$

		boolean enabled = (this.list.getSelectionCount() > 0);
		this.mEditJar.setEnabled(enabled);
		this.mRemoveJar.setEnabled(enabled);

		updateFromPropertySet();
		isValid(true);

		return area;
	}

	private void validateName() {
		boolean isOk = false;

		String testName = this.mDriverName;
		Object obj = DriverManager.getInstance().getDriverInstanceByName(
				testName);
		if ((obj == null && !testName.equals(this.mPropertySet.getName()))
				|| (obj != null && testName.equals(this.mPropertySet.getName()))) {
			isOk = true;
			this.setErrorMessage(null);
		}
		else {
			String errorMessage = DriverMgmtMessages
					.getString("NewDriverDialog.driverExistsWithName"); //$NON-NLS-1$
			this.setErrorMessage(errorMessage);
		}

		if (this.mOKButton != null)
			this.mOKButton.setEnabled(isOk);
	}

	/*
	 * Update the jar list
	 */
	private void updateJarList() {
		this.mJarList = createList(this.list.getItems());
		isValid();
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		this.mOKButton = createButton(parent, IDialogConstants.OK_ID,
				IDialogConstants.OK_LABEL, true);
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
				.getString("EditDriverDialog.windowTitle")); //$NON-NLS-1$

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
	 * Open an appropriate directory browser
	 */
	private void handleLocationBrowseButtonPressed() {
		FileDialog dialog = new FileDialog(getShell());
		dialog.setText(DriverMgmtMessages
				.getString("EditDriverDialog.title.filebrowse")); //$NON-NLS-1$

		String dirName = previouslyBrowsedDirectory;

		if (dirName.length() == 0) //$NON-NLS-1$
			dialog.setFilterPath(ResourcesPlugin.getWorkspace().getRoot()
					.getLocation().toOSString());
		else {
			File path = new File(dirName);
			if (path.exists())
				dialog.setFilterPath(new Path(dirName).toOSString());
		}

		String selectedDirectory = dialog.open();
		if (selectedDirectory != null) {
			previouslyBrowsedDirectory = selectedDirectory;
			this.list.add(selectedDirectory);
		}
	}

	/*
	 * Browse for a location, with the location passed in as the default
	 */
	private void handleLocationEditButtonPressed(String editLocation) {
		FileDialog dialog = new FileDialog(getShell());
		dialog.setText(DriverMgmtMessages
				.getString("EditDriverDialog.title.filebrowse")); //$NON-NLS-1$

		String dirName = editLocation;

		dialog.setFileName(dirName);
		if (dirName.length() == 0) //$NON-NLS-1$
			dialog.setFilterPath(ResourcesPlugin.getWorkspace().getRoot()
					.getLocation().toOSString());
		else {
			File path = new File(dirName);
			if (path.exists())
				dialog.setFilterPath(new Path(dirName).toOSString());
			else {
				dialog.setFilterPath(dirName);
			}
		}

		String selectedDirectory = dialog.open();
		if (selectedDirectory != null) {
			previouslyBrowsedDirectory = selectedDirectory;
			this.list.remove(editLocation);
			this.list.add(selectedDirectory);

			if (this.list.getItemCount() > 1) {
				if (MessageDialog
						.openQuestion(
								this.getShell(),
								DriverMgmtMessages
										.getString("EditDriverDialog.msg.updateAllJarsToSamePath.title"), //$NON-NLS-1$
								DriverMgmtMessages
										.getString("EditDriverDialog.msg.updateAllJarsToSamePath.message")) == true) { //$NON-NLS-1$
					String[] items = this.list.getItems();

					String separator = "\\";//$NON-NLS-1$
					if (selectedDirectory.indexOf("/") > -1)//$NON-NLS-1$
						separator = "/";//$NON-NLS-1$

					int location = selectedDirectory.lastIndexOf(separator);
					if (location > -1
							&& location < selectedDirectory.length() - 1) {
						String path = selectedDirectory.substring(0, location);
						for (int i = 0; i < items.length; i++) {
							String directory = items[i];
							if (!directory.equals(selectedDirectory)) {
								int location2 = directory
										.lastIndexOf(separator);
								String filename = directory;
								if (location2 > -1
										&& location2 < directory.length() - 1) {
									filename = directory.substring(
											location2 + 1, directory.length());
								}
								String newdirectory = path + separator
										+ filename;
								// System.out.println(directory + "=" +
								// newdirectory);
								this.list.remove(directory);
								this.list.add(newdirectory);
							}
						}
					}
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {
		String propIdPrefix = DriverMgmtMessages
			.getString("EditDriverDialog.text.id_prefix"); //$NON-NLS-1$
		String propId = propIdPrefix + this.mDriverName;
		if (this.mPropertySet == null) {
			this.mPropertySet = new PropertySetImpl(propId, this.mDriverName);
		}
		this.mPropertySet.setName(this.mDriverName);
		Properties props = new Properties();
		props
				.setProperty(IDriverMgmtConstants.PROP_DEFN_JARLIST,
						this.mJarList);
		
		IConfigurationElement[] templateprops = this.descriptor.getProperties();
		if (templateprops != null && templateprops.length > 0) {
			for (int i = 0; i < templateprops.length; i++) {
				IConfigurationElement prop = templateprops[i];
				String id = prop.getAttribute("id"); //$NON-NLS-1$
				String value = this.mPropertySet.getBaseProperties()
						.getProperty(id);
				if (value == null)
					value = prop.getAttribute("value"); //$NON-NLS-1$
				props.setProperty(id, value);
			}
		}
		props.setProperty(IDriverMgmtConstants.PROP_DEFN_TYPE, this.descriptor
				.getId());
		this.mPropertySet.setBaseProperties(props);
		
		saveState();
		
		super.okPressed();
	}

	/*
	 * Create a test set for validation
	 */
	private IPropertySet createTestPSet() {
		String propIdPrefix = DriverMgmtMessages
				.getString("EditDriverDialog.text.id_test_prefix"); //$NON-NLS-1$
		String propId = propIdPrefix + this.mDriverName;
		IPropertySet testPropertySet = new PropertySetImpl(propId,
				this.mDriverName);
		testPropertySet.setName(this.mDriverName);
		Properties props = new Properties();
		props
				.setProperty(IDriverMgmtConstants.PROP_DEFN_JARLIST,
						this.mJarList);

		IConfigurationElement[] templateprops = this.descriptor.getProperties();
		if (templateprops != null && templateprops.length > 0) {
			for (int i = 0; i < templateprops.length; i++) {
				IConfigurationElement prop = templateprops[i];
				String id = prop.getAttribute("id"); //$NON-NLS-1$

				String value = null;
				if (this.mPropertySet.getBaseProperties() != null) {
					value = this.mPropertySet.getBaseProperties().getProperty(
							id);
				}
				if (value == null)
					value = prop.getAttribute("value"); //$NON-NLS-1$

				props.setProperty(id, value);
			}
		}
		props.setProperty(IDriverMgmtConstants.PROP_DEFN_TYPE, this.descriptor
				.getId());
		testPropertySet.setBaseProperties(props);
		return testPropertySet;
	}

	/**
	 * Return the property set
	 */
	public IPropertySet getPropertySet() {
		return this.mPropertySet;
	}

	/**
	 * Return the category descriptor
	 * 
	 * @return
	 */
	public CategoryDescriptor getCategoryDescriptor() {
		if (this.descriptor != null) {
			CategoryDescriptor category = CategoryDescriptor
					.getCategoryDescriptor(this.descriptor.getParentCategory());
			return category;
		}
		return null;
	}

	/**
	 * Update the UI from the property set
	 */
	private void updateFromPropertySet() {
		if (this.descriptor != null) {
			this.mDriverTypeText.setText(this.descriptor.getName());
			if (this.descriptor.getJarList() != null
					&& this.descriptor.getJarList().length() > 0) {
				String[] jarListArray = parseString(this.descriptor
						.getJarList());
				this.list.removeAll();
				for (int i = 0; i < jarListArray.length; i++) {
					this.list.add(jarListArray[i]);
				}
			}
		}

		if (this.mPropertySet != null) {
			this.mDriverNameText.setText(this.mPropertySet.getName());
			this.mDriverName = this.mPropertySet.getName();

			String jarList = this.mPropertySet.getBaseProperties().getProperty(
					IDriverMgmtConstants.PROP_DEFN_JARLIST);
			this.mJarList = jarList;
			String[] jarListArray = parseString(jarList);
			this.list.removeAll();
			for (int i = 0; i < jarListArray.length; i++) {
				this.list.add(jarListArray[i]);
			}

		}

		isValid();
	}

	/**
	 * Set the instance
	 * 
	 * @param propset
	 */
	public void setPropertySet(IPropertySet propset) {
		this.mPropertySet = propset;
	}

	/**
	 * Create a list from an array of strings
	 * 
	 * @param items
	 * @return
	 */
	private String createList(String[] items) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < items.length; i++) {
			if (items[i] != null) {
				buffer.append(items[i]);
				if (i + 1 < items.length)
					buffer.append(IDriverMgmtConstants.PATH_DELIMITER);
			}
		}
		return buffer.toString();
	}

	/*
	 * Parse a string into an array
	 */
	private String[] parseString(String str_list) {
		StringTokenizer tk = new StringTokenizer(str_list,
				IDriverMgmtConstants.PATH_DELIMITER);
		String[] pieces = new String[tk.countTokens()];
		int index = 0;
		while (tk.hasMoreTokens())
			pieces[index++] = tk.nextToken();
		return pieces;
	}

	/*
	 * Determine if the template has visible properties
	 */
	private boolean hasVisibleProperties() {
		return this.descriptor == null ? false: this.descriptor.hasVisibleProperties();
	}

	/*
	 * Is the property set valid?
	 */
	private void isValid() {
		isValid(false);
	}

	/*
	 * Is the property set valid?
	 */
	private void isValid(boolean inflag) {
		IPropertySet testSet = null;
		if (inflag == true) {
			testSet = this.mPropertySet;
		}
		else {
			testSet = createTestPSet();
		}
		if (testSet != null) {
			DriverValidator testValidator = new DriverValidator(
					this.descriptor, testSet);
			boolean flag = testValidator.isValid();
			if (!flag) {
				setErrorMessage(testValidator.getMessage());
			}
			else
				setErrorMessage(null);
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
}

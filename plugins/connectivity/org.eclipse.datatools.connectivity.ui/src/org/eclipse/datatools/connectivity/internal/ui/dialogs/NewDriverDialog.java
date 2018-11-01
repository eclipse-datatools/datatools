/*******************************************************************************
 * Copyright (c) 2004-2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.dialogs;

import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.drivers.DriverManager;
import org.eclipse.datatools.connectivity.drivers.DriverMgmtMessages;
import org.eclipse.datatools.connectivity.drivers.DriverValidator;
import org.eclipse.datatools.connectivity.drivers.IDriverValuesProvider;
import org.eclipse.datatools.connectivity.drivers.IPropertySet;
import org.eclipse.datatools.connectivity.drivers.models.CategoryDescriptor;
import org.eclipse.datatools.connectivity.drivers.models.DriversProvider;
import org.eclipse.datatools.connectivity.drivers.models.OverrideTemplateDescriptor;
import org.eclipse.datatools.connectivity.drivers.models.TemplateDescriptor;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.DriverTreeContentProvider;
import org.eclipse.datatools.connectivity.internal.ui.DriverTreeFilter;
import org.eclipse.datatools.connectivity.internal.ui.DriverTreeLabelProvider;
import org.eclipse.datatools.connectivity.internal.ui.IHelpConstants;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.help.IContext;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Driver to add a new instance of a driver template.
 * 
 * @author brianf
 */
public class NewDriverDialog extends TitleAreaDialog {

	// Memento flags
	public final static String MEMENTO_ROOT = "Drivers New Driver_Dialog_Root";//$NON-NLS-1$
	public final static String MEMENTO_DIALOG_SIZE_HEIGHT = "Dialog_Size_Height";//$NON-NLS-1$
	public final static String MEMENTO_DIALOG_SIZE_WIDTH = "Dialog_Size_Width";//$NON-NLS-1$
	public final static String MEMENTO_DIALOG_EDIT_IMMEDIATELY = "Edit Immediately";//$NON-NLS-1$

	// local shell
	private Shell shell;

	// ui elements
	private TreeViewer mTreeViewer;
	private Text mDriverNameText;
	private Button mEditImmediatelyButton;
	private Button mOKButton;

	// tree filter
	private DriverTreeFilter mViewerFilter;

	// template descriptor
	private TemplateDescriptor mDriverTemplateDescriptor = null;

	// driver instance
	private IPropertySet mPropertySet = null;

	// new driver name
	private String mDriverName;

	// flag indicating whether we should edit the new
	// instance immediately or not.
	// NOTE: this flag only remembers user's choice, so when the driver don't support
	// editImmediately, we won't change this flag.
	private boolean mEditImmediately = true;
	
	private String mErrorMessage = null;

	private ContextProviderDelegate contextProviderDelegate =
		new ContextProviderDelegate(ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName());

	/**
	 * Constructor
	 * 
	 * @param parentShell
	 */
	public NewDriverDialog(Shell parentShell) {
		super(parentShell);
		this.setShellStyle(getShellStyle() | SWT.RESIZE | SWT.DIALOG_TRIM);
	}

	/**
	 * Constructor
	 * 
	 * @param parentShell
	 * @param category
	 */
	public NewDriverDialog(Shell parentShell, String category) {
		this(parentShell);

		if (CategoryDescriptor.getCategoryDescriptor(category) != null) {
			this.mViewerFilter = new DriverTreeFilter();
			this.mViewerFilter.setCategoryId(category);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createDialogArea(Composite parent) {
        getShell().setData( HelpUtil.CONTEXT_PROVIDER_KEY, this);
        HelpUtil.setHelp( getShell(), 
        		HelpUtil.getContextId(IHelpConstants.CONTEXT_ID_NEW_DRIVER_DIALOG, 
        				ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName()));

        Font font = parent.getFont();

		Composite area = (Composite) super.createDialogArea(parent);
		Composite contents = new Composite(area, SWT.NONE);
		contents.setLayout(new GridLayout());
		contents.setLayoutData(new GridData(GridData.FILL_BOTH));
		contents.addDisposeListener(new DisposeListener(){
			public void widgetDisposed(DisposeEvent e) {
				saveState();			
			}});

		Label label = new Label(contents, SWT.LEFT);
		label.setText(DriverMgmtMessages
				.getString("NewDriverDialog.label.availableTemplates")); //$NON-NLS-1$
		GridData data = new GridData();
		data.horizontalAlignment = GridData.FILL;
		data.horizontalSpan = 2;
		label.setLayoutData(data);
		label.setFont(font);

		this.mTreeViewer = new TreeViewer(contents, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL);
		this.mTreeViewer.getTree().setLayoutData(data);
		this.mTreeViewer.getTree().setFont(font);

		this.mTreeViewer
				.setContentProvider(new DriverTreeContentProvider(true));
		this.mTreeViewer.setLabelProvider(new DriverTreeLabelProvider());
		if (this.mViewerFilter != null) {
			this.mTreeViewer.addFilter(this.mViewerFilter);
		}
		this.mTreeViewer.setInput(DriversProvider.getInstance());
		this.mTreeViewer.expandToLevel(3);

		this.mTreeViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {

					public void selectionChanged(SelectionChangedEvent event) {
						StructuredSelection selection = (StructuredSelection) NewDriverDialog.this.mTreeViewer
								.getSelection();
						if (selection.getFirstElement() instanceof TemplateDescriptor) {
							TemplateDescriptor descriptor = (TemplateDescriptor) selection
									.getFirstElement();
									
							String name = descriptor.getDefaultDefinitionName();
							IDriverValuesProvider driverValsProvider = null;
							IDriverValuesProvider overrideDriverValsProvider = null;
							OverrideTemplateDescriptor[] otds = null; 
							if (descriptor != null) {
								
								otds = OverrideTemplateDescriptor.getByDriverTemplate(descriptor.getId());
								if (otds != null && otds.length > 0) {
									overrideDriverValsProvider =
										otds[0].getValuesProviderClass();
								}
								driverValsProvider =
									(IDriverValuesProvider) descriptor.getValuesProviderClass();
							}

							if (driverValsProvider != null) {
								String driverValsName = driverValsProvider.createDefaultValue(IDriverValuesProvider.VALUE_NAME);
								if (driverValsName != null) {
									name = driverValsName;
								}
								String driverValsDefaultDefinitionName = driverValsProvider.createDefaultValue(IDriverValuesProvider.VALUE_DEFAULT_DEFINITION_NAME);
								if (driverValsDefaultDefinitionName != null) {
									name = driverValsDefaultDefinitionName;
								}
							}
							if (overrideDriverValsProvider != null) {
								String overrideDriverValsName = overrideDriverValsProvider.createDefaultValue(IDriverValuesProvider.VALUE_NAME);
								if (overrideDriverValsName != null) {
									name = overrideDriverValsName;
								}
								String overrideDriverValsDefaultDefinitionName = overrideDriverValsProvider.createDefaultValue(IDriverValuesProvider.VALUE_DEFAULT_DEFINITION_NAME);
								if (overrideDriverValsDefaultDefinitionName != null) {
									name = overrideDriverValsDefaultDefinitionName;
								}
							}
							NewDriverDialog.this.mDriverNameText
									.setText(name);
							NewDriverDialog.this.mDriverTemplateDescriptor = descriptor;
							// NewDriverDialog.this.mOKButton.setEnabled(true);
							if (!needEditImmediately(descriptor)) {
								NewDriverDialog.this.mEditImmediatelyButton.setEnabled(false);
								NewDriverDialog.this.mEditImmediatelyButton.setSelection(false);
							}
							else {
								NewDriverDialog.this.mEditImmediatelyButton.setEnabled(true);
								NewDriverDialog.this.mEditImmediatelyButton.setSelection(mEditImmediately);
							}
						}
						// else {
						// NewDriverDialog.this.mOKButton.setEnabled(false);
						// }
						validateName();
					}

				});
		
		this.mTreeViewer.addDoubleClickListener( new IDoubleClickListener() {

			public void doubleClick(DoubleClickEvent event) {
				StructuredSelection selection = (StructuredSelection) NewDriverDialog.this.mTreeViewer
					.getSelection();
				if (selection.getFirstElement() instanceof TemplateDescriptor) {
					if (NewDriverDialog.this.mErrorMessage == null)
						okPressed();
				}
			}
		});

		label = new Label(contents, SWT.NONE);
		label.setText(DriverMgmtMessages
				.getString("NewDriverDialog.label.driverNameText")); //$NON-NLS-1$

		this.mDriverNameText = new Text(contents, SWT.BORDER);
		this.mDriverNameText.setLayoutData(new GridData(
				GridData.FILL_HORIZONTAL));
		this.mDriverNameText.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				NewDriverDialog.this.mDriverName = NewDriverDialog.this.mDriverNameText
						.getText();
				validateName();
			}
		});

		this.mEditImmediatelyButton = new Button(contents, SWT.CHECK);
		this.mEditImmediatelyButton.setText(DriverMgmtMessages
				.getString("NewDriverDialog.button.edit_immediately"));//$NON-NLS-1$
		this.mEditImmediatelyButton.setSelection(true);
		this.mEditImmediatelyButton
				.addSelectionListener(new SelectionListener() {

					public void widgetSelected(SelectionEvent e) {
						NewDriverDialog.this.mEditImmediately = NewDriverDialog.this.mEditImmediatelyButton
								.getSelection();
					}

					public void widgetDefaultSelected(SelectionEvent e) {
						widgetSelected(e);
					}
				});

		setTitle(DriverMgmtMessages.getString("NewDriverDialog.title")); //$NON-NLS-1$
		setMessage(DriverMgmtMessages.getString("NewDriverDialog.message")); //$NON-NLS-1$

		getEditImmediatelyState();
		this.mEditImmediatelyButton.setSelection(this.mEditImmediately);

		return area;
	}

	private void validateName() {
		boolean isOk = false;

		String testName = this.mDriverName;

		Object obj = DriverManager.getInstance().getDriverInstanceByName(
				testName);
		if (obj == null) {
			isOk = true;
			this.setErrorMessage(null);
			this.mErrorMessage = null;
		}
		else {
			isOk = false;
			String errorMessage = DriverMgmtMessages
					.getString("NewDriverDialog.driverExistsWithName"); //$NON-NLS-1$
			this.setErrorMessage(errorMessage);
			this.mErrorMessage = errorMessage;
		}

		if (testName != null
				&& (testName.startsWith(" ") || testName.endsWith(" "))) { //$NON-NLS-1$ //$NON-NLS-2$
			isOk = false;
			String errorMessage = DriverMgmtMessages
					.getString("NewDriverDialog.driverNameNoSpaces"); //$NON-NLS-1$
			this.setErrorMessage(errorMessage);
			this.mErrorMessage = errorMessage;
		}

		StructuredSelection selection = (StructuredSelection) NewDriverDialog.this.mTreeViewer
				.getSelection();
		boolean isOkSelection = false;
		if (selection.getFirstElement() instanceof TemplateDescriptor) {
			isOkSelection = true;
		}
		else {
			isOkSelection = false;
		}
		this.mOKButton.setEnabled(isOk && isOkSelection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		this.mOKButton = createButton(parent, IDialogConstants.OK_ID,
				IDialogConstants.OK_LABEL, true);
		this.mOKButton.setEnabled(false);

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
		this.shell = newShell;
		
		int width = 400;
		int height = 500;
			
		newShell.setText(DriverMgmtMessages
				.getString("NewDriverDialog.windowTitle")); //$NON-NLS-1$

		IDialogSettings dset = ConnectivityUIPlugin.getDefault()
				.getDialogSettings();
		if (dset != null) {
			IDialogSettings dSection = dset.getSection(MEMENTO_ROOT);
			if (dSection != null) {
				if (dSection.get(MEMENTO_DIALOG_SIZE_HEIGHT) != null
						&& dSection.get(MEMENTO_DIALOG_SIZE_HEIGHT).trim()
								.length() > 0) {
					height = dSection.getInt(MEMENTO_DIALOG_SIZE_HEIGHT);
					width = dSection.getInt(MEMENTO_DIALOG_SIZE_WIDTH);
				}
			}
		}
		this.shell.setSize(width, height);
		centerDialog(newShell);
	}
	
	/*
	 * Center the dialog relative to its parent
	 */
	private void centerDialog(Shell newShell){
		Composite parent = newShell.getParent();
		Rectangle parentSize = parent.getBounds();
		Rectangle newShellSize = newShell.getBounds();

		int x, y;
		x = (parentSize.width - newShellSize.width)/2 + parentSize.x;
		y = (parentSize.height - newShellSize.height)/2 + parentSize.y;

		newShell.setLocation(new Point(x, y));		
	}

	/**
	 * Return the state of the edit immediately.
	 * 
	 * @return
	 */
	public boolean getEditImmediately() {
		return this.mEditImmediately;
	}

	/**
	 * Return the template descriptor.
	 * 
	 * @return
	 */
	public TemplateDescriptor getDriverTypeDescriptor() {
		return this.mDriverTemplateDescriptor;
	}

	/**
	 * Return the driver instance.
	 * 
	 * @return
	 */
	public IPropertySet getNewDriverInstance() {
		return this.mPropertySet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {
		if (this.mDriverTemplateDescriptor != null) {
			String propId = DriverMgmtMessages
					.getString("NewDriverDialog.text.id_prefix") + this.mDriverName; //$NON-NLS-1$

			if (this.mPropertySet == null) {
				DriverInstance newDriver =
					DriverManager.getInstance().createNewDriverInstance(this.mDriverTemplateDescriptor.getId(),
							this.mDriverName.trim(), null);
				this.mPropertySet = newDriver.getPropertySet();
			}
			this.mPropertySet.setID(propId);
		}

		// after saveState, user's choice of "edit immediately" has been saved,
		// we reset this flag to the current selection's corresponding state, so
		// getEditImmediately() will return correct value.
		mEditImmediately = mEditImmediatelyButton.getSelection();
		
		super.okPressed();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#cancelPressed()
	 */
	protected void cancelPressed() {
		if (this.mPropertySet != null) {
			// just make sure that the marker is removed
			DriverValidator.removeOldProblemMarkers(this.mPropertySet.getName());
		}
		super.cancelPressed();
	}

	/**
	 * Return the category descriptor
	 * 
	 * @return
	 */
	public CategoryDescriptor getCategoryDescriptor() {
		if (this.mDriverTemplateDescriptor != null) {
			CategoryDescriptor category = CategoryDescriptor
					.getCategoryDescriptor(this.mDriverTemplateDescriptor
							.getParentCategory());
			return category;
		}
		return null;
	}

	/*
	 * Save the property sets
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
				dSection.put(MEMENTO_DIALOG_SIZE_HEIGHT, size.y);
				dSection.put(MEMENTO_DIALOG_SIZE_WIDTH, size.x);

				dSection.put(MEMENTO_DIALOG_EDIT_IMMEDIATELY,
						this.mEditImmediately);
			}
		}
	}

	/*
	 * Return the state of the edit immediately state from the dialog settings.
	 */
	private void getEditImmediatelyState() {
		IDialogSettings dset = ConnectivityUIPlugin.getDefault()
				.getDialogSettings();
		if (dset != null) {
			IDialogSettings dSection = dset.getSection(MEMENTO_ROOT);
			if (dSection != null) {
				if (dSection.get(MEMENTO_DIALOG_EDIT_IMMEDIATELY) != null) {
					this.mEditImmediately = dSection
							.getBoolean(MEMENTO_DIALOG_EDIT_IMMEDIATELY);
				}
			}
		}

	}
	
	protected boolean needEditImmediately(TemplateDescriptor descriptor) {
		return !descriptor.getEmptyJarListIsOKFlag() || descriptor.hasVisibleProperties();
	}

	public IContext getContext(Object target) {
		return contextProviderDelegate.getContext(target);
	}

	public int getContextChangeMask() {
		return contextProviderDelegate.getContextChangeMask();
	}

	public String getSearchExpression(Object target) {
		return contextProviderDelegate.getSearchExpression(target);
	}
}

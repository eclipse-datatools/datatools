/*******************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.dialogs;

import java.util.Properties;

import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.drivers.DriverManager;
import org.eclipse.datatools.connectivity.drivers.DriverMgmtMessages;
import org.eclipse.datatools.connectivity.drivers.DriverValidator;
import org.eclipse.datatools.connectivity.drivers.IDriverMgmtConstants;
import org.eclipse.datatools.connectivity.drivers.IPropertySet;
import org.eclipse.datatools.connectivity.drivers.PropertySetImpl;
import org.eclipse.datatools.connectivity.drivers.models.CategoryDescriptor;
import org.eclipse.datatools.connectivity.drivers.models.DriversProvider;
import org.eclipse.datatools.connectivity.drivers.models.TemplateDescriptor;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.DriverTreeContentProvider;
import org.eclipse.datatools.connectivity.internal.ui.DriverTreeFilter;
import org.eclipse.datatools.connectivity.internal.ui.DriverTreeLabelProvider;
import org.eclipse.datatools.connectivity.internal.ui.DriverTreeSorter;
import org.eclipse.datatools.connectivity.internal.ui.IHelpConstants;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.JFaceColors;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
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

/**
 * Driver Management dialog
 * 
 * @author brianf
 */
public class DriverDefinitionsDialog extends TitleAreaDialog 
	implements IContextProvider {

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
	
	// OK Button
	private Button mOKButton = null;

	private ContextProviderDelegate contextProviderDelegate =
		new ContextProviderDelegate(ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName());

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
        getShell().setData( HelpUtil.CONTEXT_PROVIDER_KEY, this);
        HelpUtil.setHelp( getShell(), 
        		HelpUtil.getContextId(IHelpConstants.CONTEXT_ID_DRIVER_DEFINITION_DIALOG, 
        				ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName()));
		Composite area = (Composite) super.createDialogArea(parent);

		Font font = parent.getFont();

		Composite content = new Composite(area, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		content.setLayout(layout);
		GridData data = new GridData(GridData.FILL_BOTH);
		content.setLayoutData(data);
		content.setFont(font);	
		content.addDisposeListener(new DisposeListener(){
			public void widgetDisposed(DisposeEvent e) {
				saveState();			
			}});

		this.mErrorLabel = new Label(content, SWT.LEFT | SWT.WRAP);
		data = new GridData(GridData.VERTICAL_ALIGN_FILL
				| GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		data.heightHint = 35;
		this.mErrorLabel.setLayoutData(data);
		this.mErrorLabel.setForeground(JFaceColors.getErrorText(content
				.getDisplay()));
		this.mErrorLabel.setFont(font);

		Label label = new Label(content, SWT.NONE);
		label.setText(DriverMgmtMessages
				.getString("DriverDefinitionsDialog.label.properties")); //$NON-NLS-1$
		GridData gridData = new GridData();
		gridData.horizontalSpan = 2;
		label.setLayoutData(gridData);
		
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
							if (DriverDefinitionsDialog.this.mOKButton != null)
								DriverDefinitionsDialog.this.mOKButton
									.setEnabled(false);
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
							if (DriverDefinitionsDialog.this.mOKButton != null)
								DriverDefinitionsDialog.this.mOKButton
									.setEnabled(true);
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
				.getString("DriverDefinitionsDialog.button.addDriver")); //$NON-NLS-1$
		this.mAddButton.setLayoutData(new GridData());
		this.mAddButton.setFont(font);
		setButtonLayoutData(this.mAddButton);
		this.mAddButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				StructuredSelection selection = (StructuredSelection) DriverDefinitionsDialog.this.mTreeViewer
						.getSelection();
				if (selection.getFirstElement() instanceof CategoryDescriptor) {
					addDriver(selection);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

		});

		this.mEditButton = new Button(groupComponent, SWT.PUSH);
		this.mEditButton.setText(DriverMgmtMessages
				.getString("DriverDefinitionsDialog.button.editDriver")); //$NON-NLS-1$
		this.mEditButton.setLayoutData(new GridData());
		this.mEditButton.setFont(font);
		setButtonLayoutData(this.mEditButton);
		this.mEditButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				StructuredSelection selection = (StructuredSelection) DriverDefinitionsDialog.this.mTreeViewer
						.getSelection();
				if (selection.getFirstElement() instanceof IPropertySet) {
					editDriver(selection);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

		});

		this.mRemoveButton = new Button(groupComponent, SWT.PUSH);
		this.mRemoveButton.setText(DriverMgmtMessages
				.getString("DriverDefinitionsDialog.button.removeDriver")); //$NON-NLS-1$
		this.mRemoveButton.setLayoutData(new GridData());
		this.mRemoveButton.setFont(font);
		setButtonLayoutData(this.mRemoveButton);
		this.mRemoveButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				StructuredSelection selection = (StructuredSelection) DriverDefinitionsDialog.this.mTreeViewer
						.getSelection();
				if (selection.getFirstElement() instanceof IPropertySet) {
					removeDriver(selection);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

		});

		this.mCopyButton = new Button(groupComponent, SWT.PUSH);
		this.mCopyButton.setText(DriverMgmtMessages
				.getString("DriverDefinitionsDialog.button.copyDriver")); //$NON-NLS-1$
		this.mCopyButton.setLayoutData(new GridData());
		this.mCopyButton.setFont(font);
		setButtonLayoutData(this.mCopyButton);
		this.mCopyButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				StructuredSelection selection = (StructuredSelection) DriverDefinitionsDialog.this.mTreeViewer
						.getSelection();
				if (selection.getFirstElement() instanceof IPropertySet) {
					copyDriver(selection);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

		});

		setTitle(DriverMgmtMessages.getString("DriverDefinitionsDialog.title")); //$NON-NLS-1$
		setMessage(DriverMgmtMessages
				.getString("DriverDefinitionsDialog.message")); //$NON-NLS-1$

		IPropertySet pset = null;
		IStructuredSelection selection = null;
		if (this.mInitialDriverName != null) {
			DriverInstance di = 
				DriverManager.getInstance().getDriverInstanceByName(this.mInitialDriverName);
			pset = di.getPropertySet();
			
		}

		if (pset == null) {
			if (this.mCategoryId == null) {
				if (CategoryDescriptor.getRootCategories() == null || 
						CategoryDescriptor.getRootCategories().length == 0) {
					selection = null;
				}
				else {
					selection = new StructuredSelection(CategoryDescriptor.getRootCategories()[0]);
				}
			} else {
				selection = new StructuredSelection(CategoryDescriptor.getCategoryDescriptor(this.mCategoryId));
			}
		} else {
			selection = new StructuredSelection(pset);
		}
		if (selection != null) {
			mTreeViewer.expandToLevel(selection.getFirstElement(),1);
			mTreeViewer.setSelection(selection,true);
		}
		else {
			mTreeViewer.expandToLevel(3);
		}
		
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
		this.mOKButton = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
		if (this.selectedPS == null ) {
			this.mOKButton.setEnabled(false);
		}
		else {
			this.mOKButton.setEnabled(true);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		int width = 500;
		int height = 400;
		
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
					height = dSection.getInt(MEMENTO_DIALOG_SIZE_HEIGHT);
					width = dSection.getInt(MEMENTO_DIALOG_SIZE_WIDTH);
				}
			}
		}
		newShell.setSize(width, height);
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
				dSection.put(MEMENTO_DIALOG_SIZE_HEIGHT, size.y);
				dSection.put(MEMENTO_DIALOG_SIZE_WIDTH, size.x);
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
		super.okPressed();
	}

	/*
	 * Save the instances back to the file
	 */
	private void saveChanges() {
		// no longer necessary, as this is handled by the DriverManager
		// BZ 166637 - BTF 12192006
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

	private IPropertySet duplicatePropertySet ( IPropertySet pset ) {
		IPropertySet newPset = new PropertySetImpl(pset.getName(), pset.getID());
		if (pset.getBaseProperties().size() > 0) {
			Properties newProps = new Properties();
			newPset.setBaseProperties(newProps);
			newPset.getBaseProperties().putAll(pset.getBaseProperties());
		}
		return newPset;
	}

	private void copyPropertySet ( IPropertySet fromPset, IPropertySet topset ) {
		topset.setID(fromPset.getID());
		topset.setName(fromPset.getName());
		if (topset.getBaseProperties().size() > 0) {
			topset.getBaseProperties().putAll(fromPset.getBaseProperties());
		}
	}

	private void addDriver(ISelection selection) {
		StructuredSelection sselection = (StructuredSelection) selection;

		// Add a new driver instance from one of the templates for the category
		if (sselection.getFirstElement() instanceof CategoryDescriptor) {

			CategoryDescriptor descriptor = (CategoryDescriptor) sselection
					.getFirstElement();
			NewDriverDialog dlg = null;
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

				// Should we edit immediately? if yes, pop up the edit dialog
				if (dlg.getEditImmediately()) {
					EditDriverDialog editdlg = new EditDriverDialog(getShell(),
							instance);
					int rtn_code = editdlg.open();
					if (rtn_code != EditDriverDialog.OK) {
						instance = editdlg.getInitialPropertySet();
					}
				}

				// stash the new instance
				DriverManager.getInstance().addDriverInstance(instance);

				// refresh
				this.mTreeViewer.setInput(DriversProvider.getInstance());
				this.mTreeViewer.refresh();
				this.mTreeViewer.expandToLevel(descriptor, 1);
				this.mTreeViewer.reveal(descriptor);

			}
		}
	}

	private void editDriver(ISelection selection) {
		StructuredSelection sselection = (StructuredSelection) selection;
		if (sselection.getFirstElement() instanceof IPropertySet) {
			IPropertySet instance = (IPropertySet) sselection.getFirstElement();
			IPropertySet copy = duplicatePropertySet(instance);
			EditDriverDialog dlg = new EditDriverDialog(getShell(), copy);
			if (dlg.open() == Window.OK) {
				
				copyPropertySet(copy, instance);
				DriverManager.getInstance().removeDriverInstance(instance.getID());
				
				/*
				 * This call to garbage collect is to try and reclaim
				 * the classloader held by the last instance of the 
				 * DriverInstance that is being dropped and re-added.
				 * Note that if the class is in use (i.e. any profile
				 * is connected that uses the referenced driver), it 
				 * won't be unloaded and subsequent connections will 
				 * fail.
				 */
				System.gc();
				
				DriverManager.getInstance().addDriverInstance(instance);
				DriverDefinitionsDialog.this.mDirty = true;
				DriverDefinitionsDialog.this.mTreeViewer.refresh();
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
				DriverManager.getInstance().removeDriverInstance(instance.getID());

				CategoryDescriptor category = getCategoryFromPropertySet(instance);
				DriverDefinitionsDialog.this.mTreeViewer.refresh(category);
				DriverDefinitionsDialog.this.mDirty = true;
				DriverDefinitionsDialog.this.mErrorLabel.setText(""); //$NON-NLS-1$
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
				
				DriverManager.getInstance().addDriverInstance(cloned);

				DriverDefinitionsDialog.this.mTreeViewer.refresh();

				DriverDefinitionsDialog.this.mTreeViewer
						.setSelection(new StructuredSelection(cloned));
			}
		}
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

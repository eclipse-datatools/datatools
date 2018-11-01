/*******************************************************************************
 * Copyright (c) 2004-2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 * 				brianf - update for usability work
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.preferences;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.drivers.DriverManager;
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
import org.eclipse.datatools.connectivity.internal.ui.DriverTableFilter;
import org.eclipse.datatools.connectivity.internal.ui.DriverTreeFilter;
import org.eclipse.datatools.connectivity.internal.ui.DriverTreeSorter;
import org.eclipse.datatools.connectivity.internal.ui.IHelpConstants;
import org.eclipse.datatools.connectivity.internal.ui.dialogs.AbstractInvertableTableSorter;
import org.eclipse.datatools.connectivity.internal.ui.dialogs.CategoryDescriptorNameComparator;
import org.eclipse.datatools.connectivity.internal.ui.dialogs.CategoryUtils;
import org.eclipse.datatools.connectivity.internal.ui.dialogs.DriverDialog;
import org.eclipse.datatools.connectivity.internal.ui.dialogs.DriverListContentProvider;
import org.eclipse.datatools.connectivity.internal.ui.dialogs.DriverListLabelProvider;
import org.eclipse.datatools.connectivity.internal.ui.dialogs.EditDriverDialog;
import org.eclipse.datatools.connectivity.internal.ui.dialogs.TableSortSelectionListener;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.ibm.icu.text.Collator;

/**
 * Driver management preference page.
 * 
 * @author brianf
 */
public class DriverPreferences extends PreferencePage implements
		IWorkbenchPreferencePage, IContextProvider {

	private static String ALL_STRING = DriverMgmtMessages.getString("DriverDialog.AllFilter"); //$NON-NLS-1$

	// ui elements
	private Button mAddButton;
	private Button mRemoveButton;
	private Button mEditButton;
	private Button mCopyButton;
	private TableViewer mTableViewer;
	private Combo mTypeCombo;
	private Combo mVendorCombo;

	private Action mAddAction;
	private Action mRemoveAction;
	private Action mEditAction;
	private Action mCopyAction;

	// dirty flag for save/no save
	private boolean mDirty = false;

	// viewer filter
	private DriverTableFilter mViewerFilter;
	private DriverTableFilter mTypeFilter;
	private DriverTableFilter mVendorFilter;

	// stashed selected propertyset
	private IPropertySet selectedPS = null;
	
	private String mMessage = null;

	private ContextProviderDelegate contextProviderDelegate =
		new ContextProviderDelegate(ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName());

	/**
	 * Default Constructor
	 */
	public DriverPreferences() {
		super();
		this.noDefaultAndApplyButton();
	}

	/**
	 * Constructor with a page title
	 * 
	 * @param title
	 */
	public DriverPreferences(String title) {
		super(title);
		this.noDefaultAndApplyButton();
	}

	/**
	 * Constructor with a title and icon
	 * 
	 * @param title
	 * @param image
	 */
	public DriverPreferences(String title, ImageDescriptor image) {
		super(title, image);
		this.noDefaultAndApplyButton();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents(Composite parent) {
		mMessage = getMessage();
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

		Composite comboComposite = new Composite ( content, SWT.NONE);
		GridLayout combolayout = new GridLayout(2, false);
		combolayout.marginWidth = 0;
		combolayout.marginHeight = 0;
		comboComposite.setLayout(combolayout);
		GridData CCdata = new GridData();
		CCdata.horizontalAlignment = GridData.FILL;
		CCdata.horizontalSpan = 2;
		comboComposite.setLayoutData(CCdata);
		
		CategoryDescriptor[] roots = CategoryUtils.getOrderedRootCategories();
		if (mViewerFilter != null) {
			roots = new CategoryDescriptor[] {((DriverTreeFilter) mViewerFilter).getCategoryDescriptor()};
		}
		if (roots != null && roots.length > 1) {
			Label tlabel = new Label(comboComposite, SWT.LEFT);
			tlabel.setText(DriverMgmtMessages.getString("DriverPreferences.DriverTypeFilter")); //$NON-NLS-1$
			tlabel.setLayoutData(new GridData());
			
			mTypeCombo = new Combo (comboComposite, SWT.BORDER | SWT.READ_ONLY );
			mTypeCombo.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
			
			mTypeCombo.add(ALL_STRING);
			mTypeCombo.setData(ALL_STRING, roots);
			
			for (int i = 0; i < roots.length; i++) {
				mTypeCombo.add(roots[i].getName());
				mTypeCombo.setData(roots[i].getName(), roots[i]);
			}
			
			mTypeCombo.addSelectionListener(new SelectionListener() {

				public void widgetDefaultSelected(SelectionEvent e) {
					handleTypeSelection();
				}

				public void widgetSelected(SelectionEvent e) {
					widgetDefaultSelected(e);
				}
			});
		}

		if (roots[0].getParent() != null && roots[0].getParent().getParent() == null) {
			// too deep
		}
		else {
			Label vLabel = new Label(comboComposite, SWT.LEFT);
			vLabel.setText(DriverMgmtMessages.getString("DriverPreferences.VendorFilter")); //$NON-NLS-1$
			vLabel.setLayoutData(new GridData());
			
			mVendorCombo = new Combo (comboComposite, SWT.BORDER | SWT.READ_ONLY );
			mVendorCombo.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
			
			mVendorCombo.addSelectionListener( new SelectionListener() {

				public void widgetDefaultSelected(SelectionEvent e) {
					handleVendorSelection();
				}

				public void widgetSelected(SelectionEvent e) {
					widgetDefaultSelected(e);
				}
			});
			
			if (roots != null && roots.length == 1) {
				CategoryDescriptor[] children = 
					CategoryUtils.getOrderedChildCategories(roots[0].getId());
				if (children != null && children.length > 0) {
					mVendorCombo.add(ALL_STRING);
					for (int i = 0; i < children.length; i++) {
						mVendorCombo.add(children[i].getName());
						mVendorCombo.setData(children[i].getName(), children[i]);
					}
				}
				else {
					mVendorCombo.setEnabled(false);
				}
			}
		}

		this.mTableViewer = new TableViewer(content, SWT.BORDER | SWT.FULL_SELECTION);
		data = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL);
		this.mTableViewer.getTable().setLayoutData(data);
		this.mTableViewer.getTable().setFont(font);
		this.mTableViewer.getTable().setHeaderVisible( true );

		makeActions();
		hookContextMenu();


		TableSortSelectionListener tssl1 = createTableColumn(mTableViewer, DriverMgmtMessages.getString("DriverPreferences.NameColumn"), DriverMgmtMessages.getString("DriverPreferences.DriverNameColumn"), //$NON-NLS-1$ //$NON-NLS-2$
				new TextSorter(0), SWT.UP, false);
		tssl1.getColumn().setWidth(200);
		tssl1.getColumn().setResizable(true);
		tssl1.chooseColumnForSorting();
		
		TableSortSelectionListener tssl2 = createTableColumn(mTableViewer, DriverMgmtMessages.getString("DriverPreferences.VendorColumn"), DriverMgmtMessages.getString("DriverPreferences.DriverVendorColumn"), //$NON-NLS-1$ //$NON-NLS-2$
				new TextSorter(1), SWT.UP, true);
		tssl2.getColumn().setWidth(200);
		tssl2.getColumn().setResizable(true);
		
		TableSortSelectionListener tssl3 = createTableColumn(mTableViewer, DriverMgmtMessages.getString("DriverPreferences.VersionColumn"), DriverMgmtMessages.getString("DriverPreferences.DriverVersionColumn"), //$NON-NLS-1$ //$NON-NLS-2$
				new TextSorter(2), SWT.UP, false);
		tssl3.getColumn().setWidth(100);
		tssl3.getColumn().setResizable(true);

		this.mTableViewer.setContentProvider(new DriverListContentProvider());
		this.mTableViewer.setLabelProvider(new DriverListLabelProvider()); //DecoratingLabelProvider(
//				new DriverTreeLabelProvider(), new DriverTreeLabelProvider()));
		this.mTableViewer.setSorter(new DriverTreeSorter());
		if (this.mViewerFilter != null) {
			this.mTableViewer.addFilter(this.mViewerFilter);
		}
		this.mTableViewer.setInput(DriversProvider.getInstance());
//		this.mTableViewer.expandToLevel(3);

		this.mTableViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {

					public void selectionChanged(SelectionChangedEvent event) {
						DriverPreferences.this.setMessage(DriverPreferences.this.mMessage);
						DriverPreferences.this.setErrorMessage(null);
						
						DriverPreferences.this.selectedPS = null;
						StructuredSelection selection = (StructuredSelection) DriverPreferences.this.mTableViewer
								.getSelection();
						updateButtons(selection.getFirstElement());
					}

				}
			);
		
		mTableViewer.addDoubleClickListener(new IDoubleClickListener() {

			public void doubleClick(DoubleClickEvent event) {
				StructuredSelection selection = (StructuredSelection) DriverPreferences.this.mTableViewer
					.getSelection();
				DriverPreferences.this.editDriver(selection);
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
				addDriver(DriverPreferences.this.mTableViewer.getSelection());
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

		});

		this.mEditButton = new Button(groupComponent, SWT.PUSH);
		this.mEditButton.setText(DriverMgmtMessages
				.getString("DriverPreferences.button.editDriver")); //$NON-NLS-1$
		this.mEditButton.setLayoutData(data);
		this.mEditButton.setFont(font);
		setButtonLayoutData(this.mEditButton);
		this.mEditButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				editDriver(DriverPreferences.this.mTableViewer.getSelection());
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

		});

		this.mRemoveButton = new Button(groupComponent, SWT.PUSH);
		this.mRemoveButton.setText(DriverMgmtMessages
				.getString("DriverPreferences.button.removeDriver")); //$NON-NLS-1$
		this.mRemoveButton.setLayoutData(data);
		this.mRemoveButton.setFont(font);
		setButtonLayoutData(this.mRemoveButton);
		this.mRemoveButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				removeDriver(DriverPreferences.this.mTableViewer.getSelection());
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

		});

		this.mCopyButton = new Button(groupComponent, SWT.PUSH);
		this.mCopyButton.setText(DriverMgmtMessages
				.getString("DriverPreferences.button.copyDriver")); //$NON-NLS-1$
		this.mCopyButton.setLayoutData(data);
		this.mCopyButton.setFont(font);
		setButtonLayoutData(this.mCopyButton);
		this.mCopyButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				copyDriver(DriverPreferences.this.mTableViewer.getSelection());
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

		});

		if (CategoryDescriptor.getRootCategories() != null && CategoryDescriptor.getRootCategories().length > 0)
			this.mTableViewer.setSelection(new StructuredSelection(
					CategoryDescriptor.getRootCategories()[0]));
		
		if (mTypeCombo != null) {
			mTypeCombo.select(0);
			handleTypeSelection();
		}
		if (mVendorCombo != null && mVendorCombo.getItemCount() > 0) {
			mVendorCombo.select(0);
			handleVendorSelection();
		}

		updateButtons(null);

		return content;
	}

	private void updateButtons ( Object selection ) {
		DriverPreferences.this.mAddButton
			.setEnabled(true);
		if (selection instanceof IPropertySet) {
			DriverPreferences.this.mRemoveAction
					.setEnabled(true);
			DriverPreferences.this.mEditAction
					.setEnabled(true);
			DriverPreferences.this.mCopyAction
				.setEnabled(true);
			DriverPreferences.this.mRemoveButton
				.setEnabled(true);
			DriverPreferences.this.mEditButton
				.setEnabled(true);
			DriverPreferences.this.mCopyButton
				.setEnabled(true);
			DriverPreferences.this.selectedPS = (IPropertySet) selection;
			if (DriverPreferences.this.selectedPS != null) {
				validate(DriverPreferences.this.selectedPS);
			}
//			updateButtons(selection);
		}
		else {
			DriverPreferences.this.mRemoveAction
				.setEnabled(false);
			DriverPreferences.this.mEditAction
					.setEnabled(false);
			DriverPreferences.this.mCopyAction
				.setEnabled(false);
			DriverPreferences.this.mRemoveButton
				.setEnabled(false);
			DriverPreferences.this.mEditButton
				.setEnabled(false);
			DriverPreferences.this.mCopyButton
				.setEnabled(false);
		}
//		DriverPreferences.this.mAddButton.setEnabled(true);
//		// if they selected a category...
//		if (selection instanceof CategoryDescriptor) {
//			DriverPreferences.this.mRemoveAction
//					.setEnabled(false);
//			DriverPreferences.this.mEditAction
//					.setEnabled(false);
//			DriverPreferences.this.mCopyAction
//					.setEnabled(false);
//		}
//		// if they selected a driver instance
//		else if (selection instanceof IPropertySet) {
//			DriverPreferences.this.mRemoveAction
//					.setEnabled(true);
//			DriverPreferences.this.mEditAction.setEnabled(true);
//			DriverPreferences.this.mCopyAction.setEnabled(true);
//
//			IPropertySet ps = (IPropertySet) selection;
//			if (ps != null) {
//				validate(ps);
//			}
//		}
//		else {
//			DriverPreferences.this.mRemoveAction.setEnabled(false);
//			DriverPreferences.this.mEditAction.setEnabled(false);
//			DriverPreferences.this.mCopyAction.setEnabled(false);
//		}
	}
	
	private void addDriver(ISelection selection) {
		DriverDialog ddtt = new DriverDialog(this.getShell());
		ddtt.setEditMode(false); // NEW Driver Dialog
		ddtt.setVendorFilter(this.mVendorFilter);
		ddtt.setTypeFilter(this.mTypeFilter);
		int rtn_code = ddtt.open();
		if (rtn_code != EditDriverDialog.OK) {
			// do nothing
			return;
		}

		// stash the new instance
		DriverManager.getInstance().addDriverInstance(ddtt.getPropertySet());

		// refresh
		this.mTableViewer.getControl().setRedraw(false);
		this.mTableViewer.setInput(DriversProvider.getInstance());
		this.mTableViewer.refresh();
		this.mDirty = true;
		
		DriverInstance instance =
			DriverManager.getInstance().getDriverInstanceByName(ddtt.getPropertySet().getName());
		this.mTableViewer.setSelection(new StructuredSelection(instance.getPropertySet()), true);
		this.mTableViewer.getControl().setRedraw(true);

	}

	private void editDriver(ISelection selection) {
		StructuredSelection sselection = (StructuredSelection) selection;
		if (sselection.getFirstElement() instanceof IPropertySet) {
			IPropertySet instance = (IPropertySet) sselection.getFirstElement();
			IPropertySet copy = duplicatePropertySet(instance);
			DriverDialog dlg = new DriverDialog(this.getShell());
			dlg.setEditMode(true); // EDIT Driver Dialog
			dlg.setPropertySet(copy);
			if (dlg.open() == Window.OK) {
				
				copy = dlg.getPropertySet();
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
				
				DriverManager.getInstance().addDriverInstance(copy);
				this.mDirty = true;
				this.mTableViewer.refresh();
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
				int selIndex = this.mTableViewer.getTable().getSelectionIndex();
				DriverManager.getInstance().removeDriverInstance(instance.getID());

				this.mTableViewer.refresh();//category);
				if (selIndex < this.mTableViewer.getTable().getItemCount() &&
						selIndex > -1) {
					Object obj = this.mTableViewer.getTable().getItem(selIndex).getData();
					StructuredSelection ssel = new StructuredSelection(obj);
					this.mTableViewer.setSelection(ssel, true);
				}
				else if ((selIndex - 1) > -1) {
					selIndex = selIndex - 1;
					if (this.mTableViewer.getTable().getItem(selIndex) != null) {
						Object obj = this.mTableViewer.getTable().getItem(selIndex).getData();
						StructuredSelection ssel = new StructuredSelection(obj);
						this.mTableViewer.setSelection(ssel, true);
					}
				}
				this.mDirty = true;
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
				String defnType = null;
				Properties props = instance.getBaseProperties();
				Iterator iter = props.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String key = (String)entry.getKey();
					if(key.equals(IDriverMgmtConstants.PROP_DEFN_TYPE))
						defnType = (String) entry.getValue();
				}
				
				String propIdPrefix = DriverMgmtMessages
					.getString("EditDriverDialog.text.id_prefix"); //$NON-NLS-1$
				String id = propIdPrefix + defnType + "." + copyPrefix + instance.getName(); //$NON-NLS-1$
				cloned.setID(id);
				cloned.setName(name);
				
				DriverManager.getInstance().addDriverInstance(cloned);

				this.mTableViewer.refresh();
				this.mDirty = true;
				this.mTableViewer
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
		Menu menu = menuMgr.createContextMenu(this.mTableViewer.getControl());
		this.mTableViewer.getControl().setMenu(menu);
	}

	/**
	 * Validate the property set and put an appropriate error message up
	 * 
	 * @param instance
	 */
	private void validate(IPropertySet instance) {
		DriverPreferences.this.setMessage(DriverPreferences.this.mMessage);
		this.setErrorMessage(null);
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
					this.setErrorMessage(mErrorMessage);
				}
//				this.mErrorLabel.setText(mErrorMessage);
//				this.mErrorLabel.computeSize(SWT.DEFAULT, SWT.DEFAULT);
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
		// this code isn't needed any more because 
		XMLFileManager.setFileName(IDriverMgmtConstants.DRIVER_FILE);
		List psetsList = ((DriverListContentProvider) this.mTableViewer
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

	private class AddAction extends Action {

		public void run() {
			addDriver(DriverPreferences.this.mTableViewer.getSelection());
		}
	}

	private class RemoveAction extends Action {

		public void run() {
			removeDriver(DriverPreferences.this.mTableViewer.getSelection());
		}
	}

	private class EditAction extends Action {

		public void run() {
			editDriver(DriverPreferences.this.mTableViewer.getSelection());
		}
	}

	private class CopyAction extends Action {

		public void run() {
			copyDriver(DriverPreferences.this.mTableViewer.getSelection());
		}
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

	public IContext getContext(Object target) {
		return contextProviderDelegate.getContext(target);
	}

	public int getContextChangeMask() {
		return contextProviderDelegate.getContextChangeMask();
	}

	public String getSearchExpression(Object target) {
		return contextProviderDelegate.getSearchExpression(target);
	}

	public void createControl(Composite parent) {
		super.createControl(parent);
		getShell().setData(HelpUtil.CONTEXT_PROVIDER_KEY, this);
		HelpUtil.setHelp(getControl(), HelpUtil.getContextId(IHelpConstants.CONTEXT_ID_DRIVER_PREFERENCES, ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName()));

	}

	private class TextSorter extends AbstractInvertableTableSorter {
		 
		private int mColumn = -1;
		
		public TextSorter ( int column ) {
			super();
			this.mColumn = column;
		}
		
		public int compare(Viewer viewer, Object e1, Object e2) {
			if (viewer instanceof TableViewer) {
				TableViewer tv = (TableViewer) viewer;
				if (tv.getLabelProvider() instanceof DriverListLabelProvider) {
					DriverListLabelProvider dtlp = 
						(DriverListLabelProvider) tv.getLabelProvider();
					String text1 = dtlp.getColumnText(e1, this.mColumn);
					String text2 = dtlp.getColumnText(e2, this.mColumn);
					if (text1 == null) text1 = ""; //$NON-NLS-1$
					if (text2 == null) text2 = ""; //$NON-NLS-1$
					return Collator.getInstance().compare(text1, text2);
				}
			}
			return Collator.getInstance().compare((String) e1, (String) e2);
		}
	}

	public static TableSortSelectionListener createTableColumn(
			TableViewer viewer, String text, String tooltip,
			AbstractInvertableTableSorter sorter, int initialDirection,
			boolean keepDirection) {
		TableColumn column = new TableColumn(viewer.getTable(), SWT.LEFT);
		column.setText(text);
		column.setToolTipText(tooltip);
		return new TableSortSelectionListener(viewer, column, sorter,
				initialDirection, keepDirection);
	}

	private void handleTypeSelection() {
		if (this.mTableViewer != null && !this.mTableViewer.getTable().isDisposed())
			this.mTableViewer.getTable().setRedraw(false);
		if (this.mTypeCombo == null)
			return;
		if (mTypeFilter != null && mTableViewer != null) {
			mTableViewer.removeFilter(mTypeFilter);
		}
		mVendorCombo.removeAll();
		if (mTypeCombo.getSelectionIndex() > -1) {
			String selectedText = mTypeCombo.getText().trim();
			if (selectedText.length() > 0) {
				Object data = mTypeCombo.getData(selectedText);
				CategoryDescriptor[] kids = null;
				if (data instanceof CategoryDescriptor) {
					CategoryDescriptor cd = (CategoryDescriptor) data;
					mTypeFilter = new DriverTableFilter();
					mTypeFilter.setCategoryId(cd.getId());
					mTableViewer.addFilter(mTypeFilter);
					mTableViewer.refresh();
					kids = CategoryUtils.getOrderedChildCategories(cd.getId());
				}
				else if (data instanceof CategoryDescriptor[]) {
					kids = (CategoryDescriptor[]) data;
					Arrays.sort(kids, new CategoryDescriptorNameComparator());
				}
				if (kids != null && !selectedText.equals(ALL_STRING)) {
					mVendorCombo.add(ALL_STRING);
					for (int j = 0; j < kids.length; j++) {
						mVendorCombo.add(kids[j].getName());
						mVendorCombo.setData(kids[j].getName(), kids[j]);
					}
					if (mVendorCombo.getItemCount() == 0) {
						mVendorCombo.setEnabled(false);
					}
				}
				else {
					mVendorCombo.add(ALL_STRING);
					for (int j = 0; j < kids.length; j++) {
						CategoryDescriptor[] details =
							CategoryUtils.getOrderedChildCategories(kids[j].getId());
						if (details != null && details.length > 0) {
							for (int i = 0; i < details.length; i++) {
								mVendorCombo.add(details[i].getName());
								mVendorCombo.setData(details[i].getName(), details[i]);
							}
						}
					}
					if (mVendorCombo.getItemCount() == 0) {
						mVendorCombo.setEnabled(false);
					}
				}
				mVendorCombo.select(0);
			}
		}
		else {
			mVendorCombo.setEnabled(false);
		}
		if (this.mTableViewer != null && !this.mTableViewer.getTable().isDisposed())
			this.mTableViewer.getTable().setRedraw(true);
	}
	
	private void handleVendorSelection() {
		if (this.mTableViewer != null && !this.mTableViewer.getTable().isDisposed())
			this.mTableViewer.getTable().setRedraw(false);
		if (this.mVendorCombo == null)
			return;
		if (mTypeFilter != null && mTableViewer != null) {
			mTableViewer.removeFilter(mTypeFilter);
		}
		if (mTableViewer != null && mVendorCombo != null && mVendorCombo.getText().trim().length() > 0) {
			String selText = mVendorCombo.getText().trim();
			if (!selText.equalsIgnoreCase(ALL_STRING)) {
				CategoryDescriptor cd = (CategoryDescriptor) mVendorCombo.getData(selText);
				if (cd != null) {
					mTypeFilter = new DriverTableFilter(false);
					mTypeFilter.setCategoryId(cd.getId());
					mTableViewer.addFilter(mTypeFilter);
					mTableViewer.refresh();
				}
			}
			else {
				if (mTypeCombo != null) {
					String selectedText = mTypeCombo.getText().trim();
					if (selectedText.length() > 0) {
						Object data = mTypeCombo.getData(selectedText);
						if (data instanceof CategoryDescriptor) {
							CategoryDescriptor cd = (CategoryDescriptor) data;
							mTypeFilter = new DriverTableFilter();
							mTypeFilter.setCategoryId(cd.getId());
							mTableViewer.addFilter(mTypeFilter);
							mTableViewer.refresh();
						}
					}
				}
			}
		}
		if (this.mTableViewer != null && !this.mTableViewer.getTable().isDisposed())
			this.mTableViewer.getTable().setRedraw(true);
	}

}


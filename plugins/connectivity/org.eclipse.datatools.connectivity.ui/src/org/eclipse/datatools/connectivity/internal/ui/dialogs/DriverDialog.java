/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 *               IBM Corporation - fix for 243829
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.dialogs;

import java.io.File;
import java.util.Arrays;
import java.util.Properties;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Path;
import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.drivers.DriverManager;
import org.eclipse.datatools.connectivity.drivers.DriverMgmtMessages;
import org.eclipse.datatools.connectivity.drivers.DriverValidator;
import org.eclipse.datatools.connectivity.drivers.IDriverMgmtConstants;
import org.eclipse.datatools.connectivity.drivers.IDriverValuesProvider;
import org.eclipse.datatools.connectivity.drivers.IPropertySet;
import org.eclipse.datatools.connectivity.drivers.PropertySetImpl;
import org.eclipse.datatools.connectivity.drivers.models.CategoryDescriptor;
import org.eclipse.datatools.connectivity.drivers.models.OverrideTemplateDescriptor;
import org.eclipse.datatools.connectivity.drivers.models.TemplateDescriptor;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.DriverPropertySourceProvider;
import org.eclipse.datatools.connectivity.internal.ui.DriverTreeFilter;
import org.eclipse.datatools.connectivity.internal.ui.IHelpConstants;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.help.IContext;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.views.properties.PropertySheetPage;

import com.ibm.icu.text.Collator;
import com.ibm.icu.util.StringTokenizer;

public class DriverDialog extends TitleAreaDialog {

	// memento keys
	public final static String MEMENTO_ROOT = "Alt Drivers Definitions Driver_Dialog_Root";//$NON-NLS-1$
	public final static String MEMENTO_DIALOG_SIZE_HEIGHT = "Dialog_Size_Height";//$NON-NLS-1$
	public final static String MEMENTO_DIALOG_SIZE_WIDTH = "Dialog_Size_Width";//$NON-NLS-1$

	private Text mDriverNameText;
	private Combo mTypeCombo;
	private Combo mVendorCombo;
	private TreeViewer mTreeViewer;
	private PageBook book = null;
	private List list;
	private Text mDriverTypeText;
	private Button mAddJar;
	private Button mEditJar;
	private Button mRemoveJar;
	private Button mClearAll;
	private Button mOKButton;
	private TemplateDescriptor descriptor = null;
	private IPropertySet mPropertySet = null;
	private IPropertySet mInitialPropertySet = null;
	private boolean hideDriverList = false;
	private DriverPropertySourceProvider mpsp = null;
	
	private static String ALL_STRING = DriverMgmtMessages.getString("DriverDialog.AllFilter"); //$NON-NLS-1$

	// driver properties
	private String mDriverName;
	private String mJarList;
	private boolean mInEdit = false;
	private boolean mIsEditable = true;
	private static String previouslyBrowsedDirectory = ""; //$NON-NLS-1$

	// tree filter
	private DriverTreeFilter mViewerFilter;
	private DriverTreeFilter mTypeFilter;
	private DriverTreeFilter mVendorFilter;
	
	//tabs
	private TabItem generalTab;
	private TabItem jarListTab;
	private TabItem propertiesTab;
	private Composite generalComposite;
	private Composite jarListComposite;
	private Composite propertiesComposite;
	
	private ContextProviderDelegate contextProviderDelegate =
		new ContextProviderDelegate(ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName());

	// listener for property changes to re-validate 
	private ChangeListener psetChangedListener = new ChangeListener(){

		public void stateChanged(ChangeEvent arg0) {
			boolean flag = isValid(true);
			validateName();
			if (DriverDialog.this.mOKButton != null && !DriverDialog.this.mOKButton.isDisposed())
				DriverDialog.this.mOKButton.setEnabled(flag);			
		}
	};
	
	public void setIsEditable (boolean flag) {
		this.mIsEditable = flag;
	}
	
	public void setEditMode ( boolean flag ) {
		this.mInEdit = flag;
	}

	public DriverDialog(Shell parentShell) {
		super(parentShell);
		this.setShellStyle(getShellStyle() | SWT.RESIZE | SWT.DIALOG_TRIM);
	}

	/**
	 * Constructor
	 * 
	 * @param parentShell
	 * @param category
	 */
	public DriverDialog(Shell parentShell, String category) {
		this(parentShell);
		
		if (CategoryDescriptor.getCategoryDescriptor(category) != null) {
			this.mViewerFilter = new DriverTreeFilter();
			this.mViewerFilter.setCategoryId(category);
		}
	}
	
	private void handleSelection() {
		if (this.mTreeViewer != null && !this.mTreeViewer.getTree().isDisposed())
			this.mTreeViewer.getTree().setRedraw(false);
		if (mTypeFilter != null && mTreeViewer != null) {
			mTreeViewer.removeFilter(mTypeFilter);
		}
		mVendorCombo.removeAll();
		if (mTypeCombo.getSelectionIndex() > -1) {
			mVendorCombo.setEnabled(true);
			String selectedText = mTypeCombo.getText();
			if (selectedText.trim().length() > 0) {
				CategoryDescriptor[] kids = null;
				if (mTypeCombo.getData(selectedText) instanceof CategoryDescriptor) {
					CategoryDescriptor cd = 
						(CategoryDescriptor) mTypeCombo.getData(selectedText);
					kids = CategoryUtils.getOrderedChildCategories(cd.getId());
					if (cd != null && mTreeViewer != null) {
						mTypeFilter = new DriverTreeFilter();
						mTypeFilter.setCategoryId(cd.getId());
						mTreeViewer.addFilter(mTypeFilter);
						mTreeViewer.refresh();
						mTreeViewer.expandToLevel(2);
					}
				}
				else if (mTypeCombo.getData(selectedText) instanceof CategoryDescriptor[]) {
					kids = (CategoryDescriptor[]) mTypeCombo.getData(selectedText);
					Arrays.sort(kids, new CategoryDescriptorNameComparator());
					mTreeViewer.expandToLevel(2);
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
		if (this.mTreeViewer != null && !this.mTreeViewer.getTree().isDisposed())
			this.mTreeViewer.getTree().setRedraw(true);
	}
	
	private void handleVendorSelected() {
		if (this.mTreeViewer != null && !this.mTreeViewer.getTree().isDisposed())
			this.mTreeViewer.getTree().setRedraw(false);
		if (mVendorFilter != null && mTreeViewer != null) {
			mTreeViewer.removeFilter(mVendorFilter);
		}
		if (mTreeViewer != null && mVendorCombo != null && mVendorCombo.getText().trim().length() > 0) {
			String selText = mVendorCombo.getText().trim();
			CategoryDescriptor cd = (CategoryDescriptor) mVendorCombo.getData(selText);
			if (cd != null) {
				mVendorFilter = new DriverTreeFilter();
				mVendorFilter.setCategoryId(cd.getId());
				mTreeViewer.addFilter(mVendorFilter);
				mTreeViewer.refresh();
				mTreeViewer.expandToLevel(2);
			}
		}
		if (this.mTreeViewer != null && !this.mTreeViewer.getTree().isDisposed())
			this.mTreeViewer.getTree().setRedraw(true);
	}
	
	private void processEnabledStateForComposite(Composite parent, boolean enabled) {
		if (parent != null && parent.equals(jarListComposite)) {
			parent.setEnabled(enabled);
			mAddJar.setEnabled(enabled);
			mEditJar.setEnabled(enabled);
			mClearAll.setEnabled(enabled);
			mRemoveJar.setEnabled(enabled);
		}
		else if (parent != null && parent.equals(propertiesComposite)) {
			parent.setEnabled(enabled);
			book.setEnabled(enabled);
		}
	}

	protected Control createDialogArea(Composite parent) {
        getShell().setData( HelpUtil.CONTEXT_PROVIDER_KEY, this);
        if (!this.mInEdit)
        	HelpUtil.setHelp( getShell(), 
        		HelpUtil.getContextId(IHelpConstants.CONTEXT_ID_NEW_DRIVER_DIALOG, 
        				ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName()));
        else
        	HelpUtil.setHelp( getShell(), 
            		HelpUtil.getContextId(IHelpConstants.CONTEXT_ID_EDIT_DRIVER_DIALOG, 
            				ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName()));

        Composite area = (Composite) super.createDialogArea(parent);

		Composite contents = new Composite(area, SWT.NONE);
		contents.setLayout(new GridLayout());
		contents.setLayoutData(new GridData(GridData.FILL_BOTH));
		contents.addDisposeListener(new DisposeListener(){
			public void widgetDisposed(DisposeEvent e) {
			}
		});
		
		TabFolder baseComposite = new TabFolder(contents, SWT.TOP);
		baseComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		generalTab = new TabItem(baseComposite, SWT.None);
		generalTab.setText(DriverMgmtMessages.getString("DriverDialog.Name.Type.Tab")); //$NON-NLS-1$

		jarListTab = new TabItem(baseComposite, SWT.None);
		jarListTab.setText(DriverMgmtMessages.getString("DriverDialog.JarList.Tab")); //$NON-NLS-1$
		
		propertiesTab = new TabItem(baseComposite, SWT.None);
		propertiesTab.setText(DriverMgmtMessages.getString("DriverDialog.Properties.Tab")); //$NON-NLS-1$
		
		generalComposite = new Composite(baseComposite, SWT.NULL);
		GridLayout layout = new GridLayout();
		generalComposite.setLayout(layout);
		generalTab.setControl(generalComposite);

		jarListComposite = new Composite(baseComposite, SWT.NULL);
		GridLayout jl_layout = new GridLayout();
		jarListComposite.setLayout(jl_layout);
		jarListTab.setControl(jarListComposite);

		propertiesComposite = new Composite(baseComposite, SWT.NULL);
		GridLayout p_layout = new GridLayout();
		propertiesComposite.setLayout(p_layout);
		propertiesTab.setControl(propertiesComposite);

		if (!mInEdit && !hideDriverList ) {
			Composite comboComposite = null; 
			
			CategoryDescriptor[] roots = CategoryUtils.getOrderedRootCategories();
			if (mViewerFilter != null) {
				roots = new CategoryDescriptor[] {((DriverTreeFilter) mViewerFilter).getCategoryDescriptor()};
			}
			if (roots != null && roots.length > 1) {
				if (comboComposite == null)
					comboComposite = createComboComposite(generalComposite);
				Label tlabel = new Label(comboComposite, SWT.LEFT);
				tlabel.setText(DriverMgmtMessages.getString("DriverDialog.DriverTypeFilter")); //$NON-NLS-1$
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
						handleSelection();
					}

					public void widgetSelected(SelectionEvent e) {
						widgetDefaultSelected(e);
					}
				});
			}

			if (roots[0] != null && roots[0].getParent() != null && roots[0].getParent().getParent() == null) {
				// too deep
			}
			else {
				if (comboComposite == null)
					comboComposite = createComboComposite(generalComposite);

				Label vLabel = new Label(comboComposite, SWT.LEFT);
				vLabel.setText(DriverMgmtMessages.getString("DriverDialog.VendorFilter")); //$NON-NLS-1$
				vLabel.setLayoutData(new GridData());
				
				mVendorCombo = new Combo (comboComposite, SWT.BORDER | SWT.READ_ONLY );
				mVendorCombo.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
				
				mVendorCombo.addSelectionListener( new SelectionListener() {
	
					public void widgetDefaultSelected(SelectionEvent e) {
						handleVendorSelected();
					}
	
					public void widgetSelected(SelectionEvent e) {
						widgetDefaultSelected(e);
					}
				});
				mVendorCombo.setEnabled(mIsEditable);
				
				if (roots != null && roots.length == 1 && roots[0] != null) {
					CategoryDescriptor[] children = 
						CategoryUtils.getOrderedChildCategories(roots[0].getId());
					if (children != null && children.length > 0) {
						mVendorCombo.add(ALL_STRING);
						for (int i = 0; i < children.length; i++) {
							mVendorCombo.add(children[i].getName());
							mVendorCombo.setData(children[i].getName(), children[i]);
						}
					}
					else if (mIsEditable) {
						mVendorCombo.setEnabled(false);
					}
				}
			}
			
			Label label = new Label(generalComposite, SWT.LEFT);
			label.setText(DriverMgmtMessages
					.getString("NewDriverDialog.label.availableTemplates")); //$NON-NLS-1$
			GridData data = new GridData();
			data.horizontalAlignment = GridData.FILL;
			data.horizontalSpan = 2;
			label.setLayoutData(data);
			
			mTreeViewer = new TreeViewer(generalComposite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
			mTreeViewer.setContentProvider(new DriverTreeTableContentProvider());
			mTreeViewer.setLabelProvider(new DriverTreeTableLabelProvider());
			mTreeViewer.setSorter(new ViewerSorter());
			GridData tvGD = new GridData(SWT.FILL, SWT.FILL, true, true);
			mTreeViewer.getTree().setLayoutData(tvGD);
			mTreeViewer.getTree().setHeaderVisible(true);
			if (this.mViewerFilter != null) {
				mTreeViewer.addFilter(this.mViewerFilter);
			}

			TreeSortSelectionListener tssl1 = createTableColumn(mTreeViewer, DriverMgmtMessages.getString("DriverDialog.NameColumn"), DriverMgmtMessages.getString("DriverDialog.DriverNameColumn"), //$NON-NLS-1$ //$NON-NLS-2$
					new TextSorter(0), SWT.UP, false);
			tssl1.getColumn().setWidth(200);
			tssl1.getColumn().setResizable(true);
			tssl1.chooseColumnForSorting();
			
			TreeSortSelectionListener tssl2 = createTableColumn(mTreeViewer, DriverMgmtMessages.getString("DriverDialog.VendorColumn"), DriverMgmtMessages.getString("DriverDialog.DriverVendorColumn"), //$NON-NLS-1$ //$NON-NLS-2$
					new TextSorter(1), SWT.UP, true);
			tssl2.getColumn().setWidth(200);
			tssl2.getColumn().setResizable(true);
			
			TreeSortSelectionListener tssl3 = createTableColumn(mTreeViewer, DriverMgmtMessages.getString("DriverDialog.Version"), DriverMgmtMessages.getString("DriverDialog.DriverVersionColumn"), //$NON-NLS-1$ //$NON-NLS-2$
					new TextSorter(2), SWT.UP, false);
			tssl3.getColumn().setWidth(100);
			tssl3.getColumn().setResizable(true);
	
			mTreeViewer.setInput(new CategoryRoot());
			mTreeViewer.expandToLevel(2);
	
			mTreeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
	
				public void selectionChanged(SelectionChangedEvent event) {
					DriverDialog.this.setErrorMessage(null);
					StructuredSelection selection = (StructuredSelection) DriverDialog.this.mTreeViewer
							.getSelection();
					if (selection.getFirstElement() instanceof TemplateDescriptor) {
						TemplateDescriptor descriptor = (TemplateDescriptor) selection
								.getFirstElement();
						// added for bug 249424 - BTF
						if (DriverDialog.this.descriptor != null && DriverDialog.this.descriptor.equals(descriptor)) {
							return;
						}
						DriverDialog.this.updateFromDescriptor(descriptor);
						DriverDialog.this.mDriverNameText.setEnabled(true);
					}
					else if (selection.getFirstElement() instanceof CategoryDescriptor) {
						DriverDialog.this.updateFromDescriptor(null);
						DriverDialog.this.mDriverNameText.setEnabled(false);
					}
				}
			});
			
			mTreeViewer.getTree().setEnabled(mIsEditable);
		}
		
		Label label = new Label(generalComposite, SWT.NONE);
		label.setText(DriverMgmtMessages
				.getString("NewDriverDialog.label.driverNameText")); //$NON-NLS-1$

		this.mDriverNameText = new Text(generalComposite, SWT.BORDER);
		this.mDriverNameText.setLayoutData(new GridData(
				GridData.FILL_HORIZONTAL));
		this.mDriverNameText.setEnabled(mIsEditable);
		this.mDriverNameText.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				DriverDialog.this.mDriverName = DriverDialog.this.mDriverNameText
						.getText();
				validateName();
			}
		});
		
		{
			final Label label2 = new Label(generalComposite, SWT.NONE);
			label2.setText(DriverMgmtMessages
					.getString("EditDriverDialog.label.driverTypeText")); //$NON-NLS-1$
		}
		{
			this.mDriverTypeText = new Text(generalComposite, SWT.BORDER | SWT.READ_ONLY);
			this.mDriverTypeText.setLayoutData(new GridData(
					GridData.FILL_HORIZONTAL));
		}
		{
			final Composite composite_1 = new Composite(jarListComposite, SWT.NONE);
			GridData gridData = new GridData(GridData.FILL_BOTH
					| GridData.GRAB_VERTICAL);
			gridData.horizontalIndent = -5;
			gridData.horizontalSpan = 2;
			composite_1.setLayoutData(gridData);
			final GridLayout gridLayout_1 = new GridLayout();
			gridLayout_1.numColumns = 3;
			composite_1.setLayout(gridLayout_1);
			
			{
				final Label label1 = new Label(composite_1, SWT.NONE);
				label1.setText(DriverMgmtMessages
						.getString("EditDriverDialog.label.driverFileList")); //$NON-NLS-1$
				gridData = new GridData();
				gridData.horizontalSpan = 2;
				label1.setLayoutData(gridData);
			}
			
			{
				this.list = new List(composite_1, SWT.BORDER | SWT.H_SCROLL
						| SWT.V_SCROLL);
				final GridData gridData_1 = new GridData(GridData.FILL_BOTH);
				gridData_1.verticalSpan = 4;
				gridData_1.horizontalSpan = 2;
				this.list.setLayoutData(gridData_1);

				this.list.addSelectionListener(new SelectionListener() {

					public void widgetSelected(SelectionEvent e) {
						boolean enabled = (DriverDialog.this.list
								.getSelectionCount() > 0);
						DriverDialog.this.mEditJar.setEnabled(enabled);
						DriverDialog.this.mRemoveJar
								.setEnabled(enabled);
					}

					public void widgetDefaultSelected(SelectionEvent e) {
						widgetSelected(e);
					}

				});
				this.list.setEnabled(mIsEditable);
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
				this.mAddJar.setEnabled(mIsEditable);
			}
			{
				this.mEditJar = new Button(composite_1, SWT.NONE);
				this.mEditJar.addSelectionListener(new SelectionAdapter() {

					public void widgetSelected(SelectionEvent e) {
						if (DriverDialog.this.list.getSelectionCount() > 0) {
							String selectedItem = DriverDialog.this.list
									.getItem(DriverDialog.this.list
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
				this.mEditJar.setEnabled(mIsEditable);
			}
			{
				this.mRemoveJar = new Button(composite_1, SWT.NONE);
				this.mRemoveJar
						.addSelectionListener(new SelectionAdapter() {

							public void widgetSelected(SelectionEvent e) {
								if (DriverDialog.this.list
										.getSelectionCount() > 0) {
									String[] selected = DriverDialog.this.list
											.getSelection();
									int selIndex = DriverDialog.this.list.getSelectionIndex();
									for (int i = 0; i < selected.length; i++) {
										DriverDialog.this.list
												.remove(selected[i]);
									}
									if (selIndex < DriverDialog.this.list.getItemCount() &&
											selIndex > -1) {
										DriverDialog.this.list.setSelection(selIndex);
									}
									else if ((selIndex - 1) > -1) {
										selIndex = selIndex - 1;
										if (DriverDialog.this.list.getItem(selIndex) != null) {
											DriverDialog.this.list.setSelection(selIndex);
										}
									}
									else {
										DriverDialog.this.mEditJar.setEnabled(false);
										DriverDialog.this.mRemoveJar.setEnabled(false);
									}
									updateJarList();
								}
							}
						});
				this.mRemoveJar.setLayoutData(new GridData(
						GridData.HORIZONTAL_ALIGN_FILL));
				this.mRemoveJar.setText(DriverMgmtMessages
						.getString("EditDriverDialog.button.removeJar")); //$NON-NLS-1$
				this.mRemoveJar.setEnabled(mIsEditable);
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
						DriverDialog.this.list.removeAll();
						updateJarList();
					}
				});
				this.mClearAll.setEnabled(mIsEditable);
			}
		}

		final Composite composite_2 = new Composite(propertiesComposite,
				SWT.NONE);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalIndent = -5;
		gridData.horizontalSpan = 2;
		composite_2.setLayoutData(gridData);
		final GridLayout gridLayout_2 = new GridLayout();
		gridLayout_2.numColumns = 1;
		composite_2.setLayout(gridLayout_2);
		
		final Label label3 = new Label(composite_2, SWT.NONE);
		label3.setText(DriverMgmtMessages
				.getString("EditDriverDialog.label.properties")); //$NON-NLS-1$
		gridData = new GridData();
		gridData.horizontalSpan = 2;
		label3.setLayoutData(gridData);
		
		{
			book = new PageBook(composite_2, SWT.BORDER);
			book.setLayoutData(new GridData(GridData.FILL_BOTH));
	        PropertySheetPage page = new PropertySheetPage();
	        page.createControl(book);
	        if (mpsp != null) {
	        	mpsp.removeChangeListener(psetChangedListener);
	        }
			mpsp = null;
			if (this.mPropertySet != null) {
				mpsp = new DriverPropertySourceProvider(this.mPropertySet, this.descriptor);
			}
			else {
				mpsp = new DriverPropertySourceProvider();
			}
			mpsp.addChangeListener(psetChangedListener);
			page.setPropertySourceProvider(mpsp);
	        book.showPage(page.getControl());
	        if (this.mPropertySet != null)
	        	page.selectionChanged(null, new StructuredSelection(this.mPropertySet));
	        book.setEnabled(mIsEditable);
		}

		this.mDriverNameText.setEnabled(false);
		if (mTypeCombo != null) {
			if (mTypeFilter != null && mTreeViewer != null) {
				mTreeViewer.addFilter(mTypeFilter);
				mTypeCombo.setText(mTypeFilter.getCategoryDescriptor().getName());
			}
			else {
				mTypeCombo.select(0);
			}
			handleSelection();
		}
		if (mVendorCombo != null && mVendorCombo.getItemCount() > 0) {
			if (mVendorFilter != null && mTreeViewer != null) {
				mTreeViewer.addFilter(mVendorFilter);
				mVendorCombo.setText(mVendorFilter.getCategoryDescriptor().getName());
			}
			else {
				mVendorCombo.select(0);
			}
			handleVendorSelected();
		}
		
		if (!mInEdit) {
			setTitle(DriverMgmtMessages.getString("NewDriverDialog.title")); //$NON-NLS-1$
			setMessage(DriverMgmtMessages.getString("DriverDialog.DialogMessage")); //$NON-NLS-1$

			if (this.descriptor != null) {
				this.mDriverName = this.descriptor.getName();
			}
			String quickName =
				DriverMgmtMessages
					.format("DriverDialog.quickdriver.name",  //$NON-NLS-1$
						new String[]{this.mDriverName});
			if (this.descriptor != null && this.mTreeViewer != null) {
				mTreeViewer.setSelection(new StructuredSelection(this.descriptor), true);
				this.mDriverName = quickName;
				this.mDriverNameText.setText(this.mDriverName);
			}
			else if (this.descriptor != null){
				updateFromDescriptor(descriptor);
				this.mDriverName = quickName;
				this.mDriverNameText.setText(this.mDriverName);
			}
			processEnabledStateForComposite(jarListComposite, false);
			processEnabledStateForComposite(propertiesComposite, false);

		}
		else {
			DriverInstance di = new DriverInstance(mInitialPropertySet);
			if (di != null) {
				this.mPropertySet = di.getPropertySet();
				this.descriptor = di.getTemplate();
				updateFromPropertySet();
				updatePropertyDescriptors();
			}
			setTitle(DriverMgmtMessages.getString("EditDriverDialog.title")); //$NON-NLS-1$
			setMessage(DriverMgmtMessages.getString("DriverDialog.EditDriver.message")); //$NON-NLS-1$
		}
		return area;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents(Composite parent) {
		Control dialogComposite = super.createContents(parent);
		
		// to fix NPE reported in bug 233518, this code was moved here so OK button has been created - BTF
		if (this.mOKButton != null || (this.mOKButton != null && !mIsEditable))
			this.mOKButton.setEnabled(false);
		return dialogComposite;
	}

	private Composite createComboComposite( Composite parent ) {
		Composite comboComposite = new Composite ( parent, SWT.NONE);
		comboComposite.setLayout(new GridLayout(2, false));
		GridData CCdata = new GridData();
		CCdata.horizontalAlignment = GridData.FILL;
		CCdata.horizontalSpan = 2;
		comboComposite.setLayoutData(CCdata);
		return comboComposite;
	}

	private class TextSorter extends AbstractInvertableTableSorter {
 
		private int mColumn = -1;
		
		public TextSorter ( int column ) {
			super();
			this.mColumn = column;
		}
		
		public int compare(Viewer viewer, Object e1, Object e2) {
			if (viewer instanceof TreeViewer) {
				TreeViewer tv = (TreeViewer) viewer;
				if (tv.getLabelProvider() instanceof DriverTreeTableLabelProvider) {
					DriverTreeTableLabelProvider dtlp = 
						(DriverTreeTableLabelProvider) tv.getLabelProvider();
					String text1 = dtlp.getColumnText(e1, this.mColumn);
					String text2 = dtlp.getColumnText(e2, this.mColumn);
					return Collator.getInstance().compare(text1, text2);
				}
			}
			if (e1 instanceof CategoryDescriptor && e2 instanceof CategoryDescriptor) {
				String name1 = ((CategoryDescriptor)e1).getName();
				String name2 = ((CategoryDescriptor)e2).getName();
				return Collator.getInstance().compare(name1, name2);
			}
			else if (e1 instanceof TemplateDescriptor && e2 instanceof TemplateDescriptor) {
				String name1 = ((TemplateDescriptor)e1).getName();
				String name2 = ((TemplateDescriptor)e2).getName();
				return Collator.getInstance().compare(name1, name2);
			}
			return Collator.getInstance().compare((String) e1, (String) e2);
		}
	}

	public static TreeSortSelectionListener createTableColumn(
			TreeViewer viewer, String text, String tooltip,
			AbstractInvertableTableSorter sorter, int initialDirection,
			boolean keepDirection) {
		TreeColumn column = new TreeColumn(viewer.getTree(), SWT.LEFT);
		column.setText(text);
		column.setToolTipText(tooltip);
		return new TreeSortSelectionListener(viewer, column, sorter,
				initialDirection, keepDirection);
	}
 
	/*
	 * Create the property sheet page
	 * @param book
	 * @return
	 */
	protected IPage createDefaultPage(PageBook book) {
        PropertySheetPage page = new PropertySheetPage();
        page.createControl(book);
        return page;
    }

	/*
	 * Is the property set valid?
	 */
	private boolean isValid() {
		return isValid(false);
	}

	/*
	 * Is the property set valid?
	 */
	private boolean isValid(boolean inflag) {
		IPropertySet testSet = null;
		if (inflag == true && this.mPropertySet != null) {
			testSet = this.mPropertySet;
		}
		else if (this.descriptor != null){
			testSet = createTestPSet();
		}
		if (testSet != null) {
			DriverValidator testValidator = new DriverValidator(
					this.descriptor, testSet);
			boolean flag = testValidator.isValid(false);
			if (!flag) {
				try {
					setErrorMessage(testValidator.getMessage());
					return flag;
				} catch (SWTException e) {
					if (e.code == SWT.ERROR_WIDGET_DISPOSED) {
						// move on... this is to get around a weird
						// bug in the platform having to do with
						// custom property descriptors 
					}
					else
						throw e;
				}
			}
			else
				try {
					setErrorMessage(null);
					return flag;
				} catch (SWTException e) {
					if (e.code == SWT.ERROR_WIDGET_DISPOSED) {
						// move on... this is to get around a weird
						// bug in the platform having to do with
						// custom property descriptors 
					}
					else
						throw e;
				}

			if (this.mOKButton != null && !this.mOKButton.isDisposed())
				this.mOKButton.setEnabled(flag);
		}
		else {
			if (this.mOKButton != null && !this.mOKButton.isDisposed())
				this.mOKButton.setEnabled(false);
			return false;
		}
		return true;
	}

	/*
	 * Create a test set for validation
	 */
	private IPropertySet createTestPSet() {
		String propIdPrefix = DriverMgmtMessages
				.getString("EditDriverDialog.text.id_test_prefix"); //$NON-NLS-1$
		String propId = propIdPrefix + this.mDriverName;
		IPropertySet testPropertySet =
			DriverManager.getInstance().createDefaultInstance(this.descriptor.getId());
		testPropertySet.setID(propId);
		

		Properties props = testPropertySet.getBaseProperties();
		if (props
				.getProperty(IDriverMgmtConstants.PROP_DEFN_JARLIST) != null) {
			String jarlist = props
					.getProperty(IDriverMgmtConstants.PROP_DEFN_JARLIST);
			this.mJarList = jarlist.trim();
		}
		
		testPropertySet.setName(this.mDriverName);
		props = new Properties();
		if (this.mJarList == null) {
			String jarList = DriverManager.getInstance().updatePluginJarList(this.descriptor);
			this.mJarList = jarList;
		}
		props
				.setProperty(IDriverMgmtConstants.PROP_DEFN_JARLIST,
						this.mJarList);

		IConfigurationElement[] templateprops = this.descriptor.getProperties();
		if (templateprops != null && templateprops.length > 0) {
			for (int i = 0; i < templateprops.length; i++) {
				IConfigurationElement prop = templateprops[i];

				String propid = prop.getAttribute("id"); //$NON-NLS-1$
				String propvalue = prop.getAttribute("value"); //$NON-NLS-1$

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

				boolean removeIt = false;
				if (driverValsProvider != null) {
					String valsPropValue = driverValsProvider.createDefaultValue(propid);
					if (valsPropValue != null) {
						propvalue = valsPropValue;
					}
				}
				if (overrideDriverValsProvider != null) {
					String overrideValsPropValue = overrideDriverValsProvider.createDefaultValue(propid);
					if (overrideValsPropValue != null) {
						propvalue = overrideValsPropValue;
					}
				}
				if (otds != null && otds.length > 0) {
					String temp =
						otds[0].getPropertyValueFromId(propid);
					if (temp != null && temp.length() > 0) {
						propvalue = temp;
					}
					if (otds[0].getPropertyRemoveFlagFromID(propid)) {
						removeIt = true;
					}
				}
				if (this.mPropertySet != null && this.mPropertySet.getBaseProperties() != null) {
					propvalue = this.mPropertySet.getBaseProperties().getProperty(
							propid);
				}
				if (propvalue == null) {
					if (!removeIt) 
						props.setProperty(propid, propvalue == null ? new String()
							: propvalue);
					else
						props.remove(propid);
				}
				if (propvalue == null) propvalue = ""; //$NON-NLS-1$
				props.setProperty(propid, propvalue);
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
	 * Return the initial property set (used to cancel pending changes)
	 */
	public IPropertySet getInitialPropertySet() {
		return this.mInitialPropertySet;
	}

	/*
	 * Make sure the name isn't used already
	 */
	private void validateName() {
		/*
		 * Name is valid if:
		 * 1) It's not null or an empty string
		 * 2) If we're creating a new driver (i.e. !mInEdit),
		 * 		check name. If name brings back a driver, 
		 * 		check the IDs.
		 * 3) If we're editing a new driver (i.e. mInEdit),
		 * 		if name stays same, we're ok. If name brings back
		 * 		a different driver, it's not ok.
		 */
		
		boolean isOk = false;
		String testName = "";//$NON-NLS-1$
		if (this.mDriverName != null)
			testName = this.mDriverName.trim();
		else if (this.mDriverName == null) {
			return;
		}

		// changed the if slightly to fix BZ 176781 - BTF
		if (testName.trim().length() == 0 && this.mPropertySet != null) {
			String errorMessage = DriverMgmtMessages
				.getString("DriverValidator.msg.empty_name"); //$NON-NLS-1$
			this.setErrorMessage(errorMessage);
			return;
		}

		String propId = null;
		if (this.mPropertySet != null) {
			propId = this.mPropertySet.getID();
		}
		
		// Check to see if we got back something by name
		Object obj = DriverManager.getInstance().getDriverInstanceByName(
				testName);
		if (obj != null) {
			if (!mInEdit) {
				// creating new driver, name matches existing driver
				String errorMessage = DriverMgmtMessages
					.getString("NewDriverDialog.driverExistsWithName"); //$NON-NLS-1$
				try {
					this.setErrorMessage(errorMessage);
				} catch (SWTException swt_e) {
					// just in case the message widget is disposed, since we can't grab
					// it directly
				}
				return;
			}
			else {
				// We're in edit mode and user cannot change the name so do nothing
				isOk = true;
			}
		}
		else if (obj == null) {
			isOk = true;
		}

		if (isOk && this.mTreeViewer != null && this.descriptor == null) {
			StructuredSelection selection = (StructuredSelection) DriverDialog.this.mTreeViewer
					.getSelection();
			if (selection != null && selection.getFirstElement() instanceof TemplateDescriptor) {
				TemplateDescriptor descriptor = (TemplateDescriptor) selection
						.getFirstElement();
				this.descriptor = descriptor;
			}
			isOk = isValid(true);
		}
		else if (this.descriptor != null) {
			isOk = isValid(true);
		}

		if (this.mOKButton != null && !this.mOKButton.isDisposed())
			this.mOKButton.setEnabled(isOk);
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

	/**
	 * Set the instance
	 * 
	 * @param propset
	 */
	public void setPropertySet(IPropertySet propset) {
		this.mPropertySet = propset;
		this.mInitialPropertySet = DuplicatePropertySet(propset);
	}

	public void setInitialTemplate(String templateID) {
		TemplateDescriptor td = TemplateDescriptor.getDriverTemplateDescriptor(templateID);
		if (td != null) {
			this.descriptor = td;
		}
	}

	/*
	 * Duplicates the passed in property set
	 * @param pset
	 * @return
	 */
	private IPropertySet DuplicatePropertySet ( IPropertySet pset ) {
		IPropertySet newPset = new PropertySetImpl(pset.getName(), pset.getID());
		if (pset.getBaseProperties().size() > 0) {
			Properties newProps = new Properties();
			newPset.setBaseProperties(newProps);
			newPset.getBaseProperties().putAll(pset.getBaseProperties());
		}
		return newPset;
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
			if (path.exists()) {
				dialog.setFilterPath(new Path(dirName).toOSString());
			} else {
				dialog.setFilterPath(new Path(System.getProperty("user.home")).toOSString());
			}
		}

		String selectedDirectory = dialog.open();
		if (selectedDirectory != null) {
			previouslyBrowsedDirectory = new Path(selectedDirectory).removeLastSegments(1).toOSString();
			this.list.add(selectedDirectory);
		}
	}

	/*
	 * Update the jar list
	 */
	private void updateJarList() {
		this.mJarList = createList(this.list.getItems());
		if (this.mPropertySet != null)
			this.mPropertySet.getBaseProperties().setProperty(
				IDriverMgmtConstants.PROP_DEFN_JARLIST, this.mJarList);
		validateName();
		updatePropertyDescriptors();
		if (this.mJarList.trim().length() > 0) {
			this.mClearAll.setEnabled(true);
		}
		else {
			this.mClearAll.setEnabled(false);
		}
	}

	/*
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
	 * Update property descriptors for the selected instance
	 */
	private void updatePropertyDescriptors() {
        PropertySheetPage page = new PropertySheetPage();
        page.createControl(book);
		DriverPropertySourceProvider mpsp = null;
		if (this.mPropertySet != null && this.descriptor != null) {
			mpsp = new DriverPropertySourceProvider(this.mPropertySet, this.descriptor);
		}
		else {
			mpsp = new DriverPropertySourceProvider();
		}
		page.setPropertySourceProvider(mpsp);
        book.showPage(page.getControl());
        if (this.mPropertySet != null) {
        	page.selectionChanged(null, new StructuredSelection(this.mPropertySet));
        }
	}

	private void updateFromDescriptor (TemplateDescriptor descriptor) {
		
		if (descriptor != null) {
			this.descriptor = descriptor;
			processEnabledStateForComposite(jarListComposite, true);
			processEnabledStateForComposite(propertiesComposite, true);
			this.mDriverTypeText.setText(DriverDialog.this.descriptor.getName());
			this.mPropertySet = null;
			this.mJarList = null;
			this.list.removeAll();
			if (!mInEdit) {
				this.mPropertySet = createTestPSet();
				this.mPropertySet.setName(descriptor.getName());
			}
			updateFromPropertySet();
			updatePropertyDescriptors();
			validateName();
			boolean isOk = isValid();
			if (this.mOKButton != null)
				this.mOKButton.setEnabled(isOk);
		}
		else {
			this.descriptor = null;
			this.mPropertySet = null;
			this.mJarList = null;
			this.mDriverName = "";
			this.mDriverTypeText.setText("");
			this.mDriverNameText.setText("");
			this.list.removeAll();
			updateJarList();
			updateFromPropertySet();
			updatePropertyDescriptors();
			validateName();
			boolean isOk = isValid();
			if (this.mOKButton != null)
				this.mOKButton.setEnabled(isOk);
		}
	}
	
	/*
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

		if (mIsEditable) {
			mAddJar.setEnabled(true);
			mEditJar.setEnabled(false);
			mRemoveJar.setEnabled(false);
			mClearAll.setEnabled(false);
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
			if (jarList != null && jarList.trim().length() > 0 && mIsEditable) {
				mClearAll.setEnabled(true);
			}
		}
		else {
			mAddJar.setEnabled(false);
		}

		isValid(true);
		if (this.mPropertySet != null)
			validateName();
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
			previouslyBrowsedDirectory = new Path(selectedDirectory).removeLastSegments(1).toOSString();
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
								this.list.remove(directory);
								this.list.add(newdirectory);
							}
						}
					}
				}
			}
		}
	}

	protected void okPressed() {
		
		if (!this.mInEdit) {
			if (this.descriptor != null) {
				String propId = DriverMgmtMessages
						.getString("NewDriverDialog.text.id_prefix") + this.mDriverName; //$NON-NLS-1$

				if (this.mPropertySet == null) {
					DriverInstance newDriver =
						DriverManager.getInstance().createNewDriverInstance(this.descriptor.getId(),
								this.mDriverName.trim(), null);
					this.mPropertySet = newDriver.getPropertySet();
				}
				this.mPropertySet.setID(propId);
			}
		}
		
		String propIdPrefix = DriverMgmtMessages
			.getString("EditDriverDialog.text.id_prefix"); //$NON-NLS-1$
		String propId = propIdPrefix + this.descriptor.getId() + "." + this.mDriverName; //$NON-NLS-1$
//		String propId = propIdPrefix + this.mDriverName;
		if (this.mPropertySet == null) {
			this.mPropertySet = new PropertySetImpl(propId, this.mDriverName);
		}
		else {
			this.mPropertySet.setID(propId);
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
				if (value == null) {
					value = prop.getAttribute("value"); //$NON-NLS-1$
					OverrideTemplateDescriptor[] otds = 
						OverrideTemplateDescriptor.getByDriverTemplate(this.descriptor.getId());
					if (otds != null && otds.length > 0) {
						boolean removetemp =
							otds[0].getPropertyRemoveFlagFromID(id);
						if (removetemp) continue;
						String valuetemp =
							otds[0].getPropertyValueFromId(id);
						if (valuetemp != null && valuetemp.length() > 0)
							value = valuetemp;
					}
				}
				props.setProperty(id, value);
			}
		}
		props.setProperty(IDriverMgmtConstants.PROP_DEFN_TYPE, this.descriptor
				.getId());
		this.mPropertySet.setBaseProperties(props);
		
		this.psetChangedListener = null;
	
		saveState();

		super.okPressed();
	}

	/**
	 * Retrieve the selected instance
	 * 
	 * @return
	 */
	public IPropertySet getSelectedDefinition() {
		return this.mPropertySet;
	}

	public void setHideDriverList (boolean flag ) {
		this.hideDriverList = flag;
	}
	
	public boolean getHideDriverList() {
		return this.hideDriverList;
	}

	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);

		int width = 600;
		int height = 500;

		if (!this.mInEdit)
			newShell.setText(DriverMgmtMessages
				.getString("NewDriverDialog.windowTitle")); //$NON-NLS-1$
		else
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
	
	public void setVendorFilter ( DriverTreeFilter filter ) {
		this.mViewerFilter = filter;
	}
	public void setTypeFilter ( DriverTreeFilter filter ) {
		this.mTypeFilter = filter;
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
	
   /**
     * Initializes the bounds (location, size) of the dialog.  This implementation adjusts the width of
     * the dialog so that the complete current message can be displayed.
     * </p>
     */
    protected void initializeBounds() {
        super.initializeBounds();
        
        Shell shell = getShell();
        Rectangle boundsRect = shell.getBounds();
        int dialogWidth = boundsRect.width;
        
        int preferredMsgAreaWidth = getPreferredMessageAreaWidth();
        int preferredDialogWidth = preferredMsgAreaWidth + 20; // add some dialog border width
        if (dialogWidth < preferredDialogWidth) {
            Rectangle newBoundsRect = new Rectangle(boundsRect.x, boundsRect.y, preferredDialogWidth, boundsRect.height);
            Rectangle constrainedBoundsRect = getConstrainedShellBounds(newBoundsRect);
            shell.setBounds(constrainedBoundsRect);
        }
    }

    /**
     * Gets the preferred width for the message area.  The message area for a TitleAreaDialog is hard-coded 
     * to show only two lines for the message, so our preferred width is the width of the current message 
     * divided by two plus a "fudge factor" to account for the message image area and word-wrapping variances.
     *  
     * @return the preferred message area width
     */
    private int getPreferredMessageAreaWidth() {
        int msgWidth = 0;
        
        Control dialogArea = getDialogArea();
        if (dialogArea != null) {
            GC gc = new GC(dialogArea);
            String dialogMessage = DriverMgmtMessages.getString("DriverDialog.DialogMessage");
            Point strExtent = gc.stringExtent(dialogMessage);
            int strWidth = strExtent.x;
            gc.dispose();
            msgWidth = (strWidth / 2) + 100; // add fudge factor
        }
        
        return msgWidth;
    }
}

/*******************************************************************************
 * Copyright (c) 2005, 2009 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: shongxum - initial API and implementation
 *  Actuate Corporation - refactored to improve extensibility
 *  brianf - fixing bug 273663
 ******************************************************************************/
package org.eclipse.datatools.connectivity.internal.ui.wizards;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.internal.ui.ConnectionProfileManagerUI;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.IHelpConstants;
import org.eclipse.datatools.connectivity.ui.Messages;
import org.eclipse.datatools.connectivity.ui.wizards.ICPWizard;
import org.eclipse.datatools.connectivity.ui.wizards.IProfileWizardProvider;
import org.eclipse.datatools.connectivity.ui.wizards.IWizardCategoryProvider;
import org.eclipse.datatools.connectivity.ui.wizards.NewConnectionProfileWizard;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardNode;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.osgi.util.TextProcessor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

/**
 * @author shongxum
 * 
 */
public class CPWizardSelectionPage 
	extends WizardSelectionPage
	implements IContextProvider {

    private static final String EMPTY_STRING = "";      //$NON-NLS-1$

    private Text filterText = null;
	
	private WizardFilter mWizFilter = null;
	
	private Text mCPName;
	private Text mCPDesc;
    private Label mCPNameLabel;
    private Label mCPDescLabel;
	
	private String mCPNameStr;
	private String mCPDescStr;
	
	private boolean mPageIsVisible = false;

	private String category = ""; //$NON-NLS-1$
	private TableViewer tableViewer;

	private ContextProviderDelegate contextProviderDelegate =
		new ContextProviderDelegate(ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName());

    private ViewerFilter[] viewerFilters = new ViewerFilter[] { 
        new ViewerFilter() {

		public boolean select(Viewer viewer, Object parentElement,
				Object element) {
			return true;
		}
	}};

	class Sorter extends ViewerSorter {

		public int compare(Viewer viewer, Object e1, Object e2) {
			CPWizardNode item1 = (CPWizardNode) e1;
			CPWizardNode item2 = (CPWizardNode) e2;
			return item1.getProvider().getName().compareTo(
					item2.getProvider().getName());
		}
	}

	class TableLabelProvider extends LabelProvider implements
			ITableLabelProvider {

		private Map<CPWizardNode, Image> cachedImages = new HashMap<CPWizardNode, Image>();

		public String getColumnText(Object element, int columnIndex) {
			return ((CPWizardNode) element).getProvider().getName();
		}

		public Image getColumnImage(Object element, int columnIndex) {
			Image image = this.cachedImages.get(element);
			if (image == null) {
				ImageDescriptor descriptor = ((CPWizardNode) element).getProvider().getIcon();
				image = descriptor.createImage();
				this.cachedImages.put((CPWizardNode) element, image);
			}
			return image;
		}

		@Override
		public void dispose() {
			for (Image image: this.cachedImages.values()) {
				image.dispose();
			}
			super.dispose();
		}
	}

	class TableContentProvider implements IStructuredContentProvider {

		private String wizardCategory;

		public void dispose() {
			// do nothing
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			wizardCategory = (String) newInput;
		}

		public Object[] getElements(Object inputElement) {
			Collection wizardNodes = getCategoryItems(wizardCategory);
			return (CPWizardNode[]) wizardNodes
					.toArray(new CPWizardNode[wizardNodes.size()]);
		}
	}

    /**
     * Get wizard for specified category
     * @param wizardCategory
     * @return
     * @deprecated  As of DTP 1.6, replaced by {@link #getCategoryItems(String)}
     */
	public List getCatagoryItems(String wizardCategory) {
	    return getCategoryItems( wizardCategory );
	}
	
	/**
	 * Get wizard for the specified category
	 * @param wizardCategory
	 * @return
	 */
	public List getCategoryItems(String wizardCategory) {
		ConnectionProfileManagerUI manager = ConnectionProfileManagerUI
				.getInstance();
		Collection wizards = manager.getNewWizards().values();
		Collection wizardCats = manager.getWizardCategories().values();
		List wizardNodes = new ArrayList();
		IProfileWizardProvider wizardProvider;
		if (wizards != null) {
			for (Iterator itr = wizards.iterator(); itr.hasNext();) {
				wizardProvider = (IProfileWizardProvider) itr.next();
				if (wizardProvider.getCategory().equals(wizardCategory)) {
					wizardNodes.add(new CPWizardNode(wizardProvider));
				}
			}
		}
		if (wizardCats != null) {
			for (Iterator itr = wizardCats.iterator(); itr.hasNext();) {
				wizardProvider = (IProfileWizardProvider) itr.next();
				if (wizardProvider.getCategory().equals(wizardCategory)) {
					wizardNodes.add(new CPCategoryWizardNode(wizardProvider));
				}
			}
		}

		return wizardNodes;
	}
	
	public CPWizardSelectionPage(String id) {
		super(id);
		setTitle(ConnectivityUIPlugin.getDefault().getResourceString(
				"CPWizardSelectionPage.title")); //$NON-NLS-1$
		setDescription(ConnectivityUIPlugin.getDefault().getResourceString(
				"CPWizardSelectionPage.desc")); //$NON-NLS-1$
	}

	protected CPWizardSelectionPage(String id, ViewerFilter filter) {
		this(id);
		if (filter != null)
			viewerFilters = new ViewerFilter[]{ filter };
	}
    
    /**
     * Constructor with an array of ViewerFilter.
     * @param id   page id or name
     * @param filters  an array of ViewerFilter; may be an empty array, in which case
     *             the default NewCPWizardCategoryFilter will be used
     * @since DTP 1.6
     */
    protected CPWizardSelectionPage(String id, ViewerFilter[] filters ) {
        this(id);
        if ( filters != null )
            viewerFilters = filters;
    }

	protected CPWizardSelectionPage(String id, ViewerFilter filter, String cat) {
		this(id, filter);
		setCategory( cat );
	}

	/**
	 * Specifies the category of connection profiles to include in this wizard selection page. 
     * @param categoryId   category id
     * @since DTP 1.6
	 */
	protected void setCategory( String categoryId )
	{
        category = categoryId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
        // <!-- Created by SWT-Designer
		Composite container = new Composite(parent, SWT.NULL);
		container.setLayout(new GridLayout());
		setControl(container);
		{
			final Group group = new Group(container, SWT.NONE);
			group.setLayout(new GridLayout());
			group.setText(ConnectivityUIPlugin.getDefault().getResourceString(
					"CPWizardSelectionPage.group")); //$NON-NLS-1$
			group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 8 ));//GridData.FILL_BOTH));

			filterText = new Text (group, SWT.BORDER);
			filterText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			filterText.setText(Messages.CPWizardSelectionPage_default_filter_text);
			filterText.selectAll();
			filterText.addModifyListener(new ModifyListener(){
				public void modifyText(ModifyEvent e) {
					if (tableViewer != null && !tableViewer.getControl().isDisposed()) {
						String filter = filterText.getText();
						if (mWizFilter != null) {
							tableViewer.removeFilter(mWizFilter);
						}
						if (filter != null && filter.trim().length() > 0) {
							mWizFilter = new WizardFilter(filter);
							tableViewer.addFilter(mWizFilter);
						}
						setProfileName(new String());
						setSelectedNode(null);
						validate();
					}
				}
			});
			
			{
				tableViewer = new TableViewer(group, SWT.BORDER
						| SWT.FULL_SELECTION);
				tableViewer.addDoubleClickListener(new IDoubleClickListener() {

					public void doubleClick(DoubleClickEvent e) {
						IStructuredSelection iss = (IStructuredSelection) tableViewer
								.getSelection();
						if (iss != null && !iss.isEmpty()) {
							CPWizardNode node = (CPWizardNode) iss
									.getFirstElement();
							
							String newName = ConnectivityUIPlugin.getDefault().getResourceString(
									"CPWizardSelectionPage.defaultName", new String[] {node.getProvider().getName()});//$NON-NLS-1$
							int i = 0;
							while (ProfileManager.getInstance().getProfileByName(newName) != null && i < Integer.MAX_VALUE) {
								newName = ConnectivityUIPlugin.getDefault().getResourceString(
										"CPWizardSelectionPage.defaultNameExtended",  //$NON-NLS-1$
										new String[] {node.getProvider().getName(), 
										Integer.toString(i)});
								newName = TextProcessor.process(newName);
								i++;
							}
							setProfileName(newName);
							setSelectedNode(node);
							validate();
							if (getErrorMessage() == null)
								CPWizardSelectionPage.this.getWizard()
									.getContainer().showPage(
											CPWizardSelectionPage.this
													.getNextPage());
						}
					}
				});
				tableViewer.setSorter(new Sorter());
				tableViewer.setLabelProvider(new TableLabelProvider());
				tableViewer.setContentProvider(new TableContentProvider());
				final Table table = tableViewer.getTable();
				
				// for bug 273663, limiting height of table to avoid huge dialog
				GridData gdTable = new GridData(GridData.FILL_BOTH);
				gdTable.heightHint = 240;
				table.setLayoutData(gdTable);
				
				TableLayout tl = new TableLayout();
				{
					tl.addColumnData(new ColumnWeightData(100));
					{
						final TableColumn tableColumn = new TableColumn(table,
								SWT.NONE);
						tableColumn.setWidth(400);
					}
				}

				tableViewer.setFilters( viewerFilters );
				tableViewer.setInput(category);
			}
		}
		// -->

		tableViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {

					public void selectionChanged(SelectionChangedEvent event) {
						IStructuredSelection iss = (IStructuredSelection) event
								.getSelection();
						if (iss != null && !iss.isEmpty()) {
							CPWizardNode node = (CPWizardNode) iss
									.getFirstElement();
							if (getSelectedNode() != null && getSelectedNode().equals(node))
								return;
							int i = 0;
							String newName = ConnectivityUIPlugin.getDefault().getResourceString(
								"CPWizardSelectionPage.defaultName", new String[] {node.getProvider().getName()}); //$NON-NLS-1$
							while (ProfileManager.getInstance().getProfileByName(newName) != null && i < Integer.MAX_VALUE) {
								newName = ConnectivityUIPlugin.getDefault().getResourceString(
										"CPWizardSelectionPage.defaultNameExtended",  //$NON-NLS-1$
										new String[] {node.getProvider().getName(), 
										Integer.toString(i)});
								newName = TextProcessor.process(newName);
								i++;
							}
							setProfileName(newName);
							setDescription(node.getProvider().getDescription());
							setSelectedNode(node);
						}
					}
				});
		
		mCPNameLabel = new Label(container, SWT.NONE);
		mCPNameLabel.setText(ConnectivityUIPlugin
				.getDefault().getResourceString(
						"NewConnectionProfileWizardPage.cp_name")); //$NON-NLS-1$
		mCPName = new Text(container, SWT.BORDER);
		if( mCPNameStr != null )
		    mCPName.setText( mCPNameStr );
		mCPName.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				mCPNameStr = mCPName.getText();
				handleModify();
			}
		});

		mCPName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// Connection profile description
		mCPDescLabel = new Label(container, SWT.NONE);
		mCPDescLabel.setText(ConnectivityUIPlugin
				.getDefault().getResourceString(
						"NewConnectionProfileWizardPage.cp_desc")); //$NON-NLS-1$
		mCPDesc = new Text(container, SWT.BORDER);
        if( mCPDescStr != null )
            mCPDesc.setText( mCPDescStr );
		mCPDesc.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		mCPDesc.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				mCPDescStr = mCPDesc.getText();
				handleModify();
			}
		});
		initializeDialogUnits(container);
		setPageComplete(false);

        getShell().setData( HelpUtil.CONTEXT_PROVIDER_KEY, this);
		HelpUtil.setHelp( tableViewer.getTable(), HelpUtil.getContextId(IHelpConstants.CONTEXT_ID_CP_WIZARD_PAGE, ConnectivityUIPlugin.getDefault().getBundle().getSymbolicName()));

	}

	public void onSetActive() {
		if (tableViewer == null)
			return;
		Object obj = tableViewer.getElementAt(0);
		if (obj != null)
			tableViewer.setSelection(new StructuredSelection(obj));
	}

	protected void initWizard(IWizard wizard) {
		IProfileWizardProvider wizardProvider = ((CPWizardNode) getSelectedNode())
				.getProvider();
		initWizard(wizard, wizardProvider);
	}
	
	private void initWizard(IWizard wizard, IProfileWizardProvider wizardProvider) {    
		if (wizard instanceof ICPWizard) {
			((ICPWizard) wizard)
					.initProviderID(((ProfileWizardProvider) wizardProvider)
							.getProfile());
			if (getWizard() instanceof NewCPWizard)
				((ICPWizard) wizard).setParentProfile(((NewCPWizard)getWizard()).getParentProfile());
		}
		else if (wizard instanceof NewCategoryWizard) {
			NewCategoryWizard catWizard = (NewCategoryWizard) wizard;
			catWizard
					.initWizardCategory((IWizardCategoryProvider) wizardProvider);
			catWizard.setWindowTitle(getWizard().getWindowTitle());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.sybase.stf.common.ui.wizards.WizardSelectionPage#getNextPage()
	 */
	public IWizardPage getNextPage() {
		IWizardNode selectedNode = this.getSelectedNode();
		if (selectedNode == null)
			return null;
		
		IProfileWizardProvider wizardProvider = ((CPWizardNode) getSelectedNode())
				.getProvider();
		boolean isCreated = selectedNode.isContentCreated();
		IWizard wizard = selectedNode.getWizard();

		if (wizard == null) {
			setSelectedNode(null);	
			return null;
		}

		if (wizard instanceof NewCategoryWizard) {
			List categoryItems = getCategoryItems(wizardProvider.getId());
			if (categoryItems.size() == 1) {
				// Get next wizard and the wizard provider for next page.
				IWizardNode wizardNode = (IWizardNode) categoryItems.get(0);
				isCreated = wizardNode.isContentCreated();
				wizard = wizardNode.getWizard();
				wizardProvider = ((CPWizardNode) wizardNode).getProvider();
			}
		}
		
		if (!isCreated) {
			initWizard(wizard, wizardProvider);

			if (wizard instanceof NewConnectionProfileWizard) {
				NewConnectionProfileWizard ancpw = (NewConnectionProfileWizard) wizard;
				ancpw.setProfileName(getProfileName());
				ancpw.setProfileDescription(getProfileDescription());
			}

			// Allow the wizard to create its pages
			wizard.addPages();
		}
		else {
			if (wizard instanceof NewConnectionProfileWizard) {
				NewConnectionProfileWizard ancpw = (NewConnectionProfileWizard) wizard;
				ancpw.setProfileName(getProfileName());
				ancpw.setProfileDescription(getProfileDescription());
			}
		}
		
		if (this.mPageIsVisible) {
			if (wizard instanceof NewConnectionProfileWizard) {
				NewConnectionProfileWizard ancpw = (NewConnectionProfileWizard) wizard;
				ancpw.setSkipProfileNamePage(true);
			}
		}

		return wizard.getStartingPage();
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

	public List getSummaryData() {
		List data = new ArrayList();

		data.add(new String[] {
				ConnectivityUIPlugin.getDefault().getResourceString(
						"NewConnectionProfileWizardPage.summary_cp_name"), //$NON-NLS-1$
				getProfileName()});

		data.add(new String[] {
				ConnectivityUIPlugin.getDefault().getResourceString(
						"NewConnectionProfileWizardPage.summary_cp_desc"), //$NON-NLS-1$
				getProfileDescription()});

		return data;
	}

	private void handleModify() {
		validate();
	}

	private void validate() {
		String errorMessage = null;
		String cpName = mCPName.getText();//.trim(); //removed for bug 290785
		IConnectionProfile foundProfile = null;
		
		if (cpName == null || cpName.trim().length() == 0) {
			errorMessage = ConnectivityUIPlugin.getDefault().getResourceString(
					"NewConnectionProfileWizardPage.Status.NoName"); //$NON-NLS-1$
		}
		else if (cpName.trim().length() < cpName.length() ) {
			errorMessage = ConnectivityUIPlugin.getDefault().getResourceString(
					"NewConnectionProfileWizardPage.Status.NoSpacesInName"); //$NON-NLS-1$
		}
		else if(cpName.indexOf(":") != -1) { //$NON-NLS-1$
			errorMessage = ConnectivityUIPlugin.getDefault().getResourceString(
				"NewConnectionProfileWizardPage.Status.ContainsColon");//$NON-NLS-1$
		}		
		else {
			foundProfile = ProfileManager.getInstance().getProfileByName(cpName.trim());
			if (foundProfile != null)
				errorMessage = ConnectivityUIPlugin.getDefault().getResourceString(
					"NewConnectionProfileWizardPage.Status.DuplicateName"); //$NON-NLS-1$
		}

		setErrorMessage(errorMessage);
		setPageComplete(errorMessage == null);
	}

	public String getProfileName() {
        return ( mCPName == null || mCPName.isDisposed() ) ? 
                mCPNameStr : mCPName.getText();
	}

	public void setProfileName(String name) {
		this.mCPNameStr = name;
		if ( mCPName != null && ! mCPName.isDisposed() ) {
            if ( name == null )
                name = EMPTY_STRING;
			mCPName.setText(name);
		}
	}

    /**
     * Set the visibility of the profile name controls.
     * @param  visible the new visibility state
     * @since DTP 1.7.2
     */
    protected void setProfileNameVisible( boolean visible )
    {
        mCPNameLabel.setVisible( visible );
        mCPName.setVisible( visible );
    }

	public String getProfileDescription() {
        return ( mCPDesc == null || mCPDesc.isDisposed() ) ? 
                mCPDescStr : mCPDesc.getText();
	}

	public void setProfileDescription(String desc) {
		this.mCPDescStr = desc;
		if ( mCPDesc != null && ! mCPDesc.isDisposed() ) {
            if ( desc == null )
                desc = EMPTY_STRING;
			mCPDesc.setText(desc);
		}
	}

	/**
	 * Set the visibility of the profile description controls.
	 * @param  visible the new visibility state
     * @since DTP 1.7.2
	 */
    protected void setProfileDescriptionVisible( boolean visible )
    {
        mCPDescLabel.setVisible( visible );
        mCPDesc.setVisible( visible );
    }

	class WizardFilter extends ViewerFilter {
		
		private String mFilter;
		
		public WizardFilter ( String filter ) {
			mFilter = filter;
		}

		public boolean select(Viewer viewer, Object parentElement,
				Object element) {
			if (element instanceof CPWizardNode) {
				CPWizardNode node = (CPWizardNode) element;
				String name = node.getProvider().getName();
				if (mFilter != null) {
					if (name.toUpperCase().startsWith(mFilter.toUpperCase())) {
						return true;
					}
					else if (name.toUpperCase().indexOf(mFilter.toUpperCase()) > 0) {
						return true;
					}
					else
						return false;
				}
			}
			return true;
		}
	}

	public boolean isPageComplete() {
		if (this.getErrorMessage() != null)
			return false;
		if (this.getNextPage() == null)
			return false;
		else if (!this.getNextPage().isPageComplete())
			return false;
		return super.isPageComplete();
	}

	public void setVisible(boolean visible) {
		super.setVisible(visible);
		this.mPageIsVisible = visible;
	}

	public boolean canFlipToNextPage() {
		if (this.getErrorMessage() != null)
			return false;
		return super.canFlipToNextPage();
	}
}
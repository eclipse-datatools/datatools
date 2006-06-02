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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.internal.ui.ConnectionProfileManagerUI;
import org.eclipse.datatools.connectivity.internal.ui.ConnectivityUIPlugin;
import org.eclipse.datatools.connectivity.internal.ui.IHelpConstants;
import org.eclipse.datatools.connectivity.ui.wizards.ICPWizard;
import org.eclipse.datatools.connectivity.ui.wizards.IProfileWizardProvider;
import org.eclipse.datatools.connectivity.ui.wizards.IWizardCategoryProvider;
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
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.PlatformUI;

/**
 * @author shongxum
 * 
 */
public class CPWizardSelectionPage extends WizardSelectionPage {

	private String category = ""; //$NON-NLS-1$
	private TableViewer tableViewer;

	private ViewerFilter viewerFilter = new ViewerFilter() {

		public boolean select(Viewer viewer, Object parentElement,
				Object element) {
			return true;
		}
	};

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

		public String getColumnText(Object element, int columnIndex) {
			return ((CPWizardNode) element).getProvider().getName();
		}

		public Image getColumnImage(Object element, int columnIndex) {
			return ((CPWizardNode) element).getProvider().getCachedIcon();
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
			Collection wizardNodes = getCatagoryItems(wizardCategory);
			return (CPWizardNode[]) wizardNodes
					.toArray(new CPWizardNode[wizardNodes.size()]);
		}
	}

	/**
	 * Get wizard for specified catagory
	 * @param wizardCategory
	 * @return
	 */
	public List getCatagoryItems(String wizardCategory) {
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
	
	protected CPWizardSelectionPage(String id) {
		super(id);
		setTitle(ConnectivityUIPlugin.getDefault().getResourceString(
				"CPWizardSelectionPage.title")); //$NON-NLS-1$
		setDescription(ConnectivityUIPlugin.getDefault().getResourceString(
				"CPWizardSelectionPage.desc")); //$NON-NLS-1$
	}

	protected CPWizardSelectionPage(String id, ViewerFilter filter) {
		this(id);
		if (filter != null)
			viewerFilter = filter;
	}

	protected CPWizardSelectionPage(String id, ViewerFilter filter, String cat) {
		this(id, filter);
		category = cat;
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
			group.setLayoutData(new GridData(GridData.FILL_BOTH));
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
							setSelectedNode(node);
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
				table.setLayoutData(new GridData(GridData.FILL_BOTH));
				TableLayout tl = new TableLayout();
				{
					tl.addColumnData(new ColumnWeightData(100));
					{
						final TableColumn tableColumn = new TableColumn(table,
								SWT.NONE);
						tableColumn.setWidth(400);
					}
				}

				tableViewer.setInput(category);
				tableViewer.addFilter(viewerFilter);
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
							setDescription(node.getProvider().getDescription());
							setSelectedNode(node);
						}
					}
				});

		setPageComplete(false);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(parent,
				IHelpConstants.CONTEXT_ID_CP_WIZARD_PAGE);

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
			List categoryItems = getCatagoryItems(wizardProvider.getId());
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
			// Allow the wizard to create its pages
			wizard.addPages();
		}

		return wizard.getStartingPage();
	}	
}
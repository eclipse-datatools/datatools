/*******************************************************************************
 * Copyright (c) 2007, 2010 SolutionsIQ, Inc. and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *   SolutionsIQ, Inc. - Initial API and implementation
 *   julien.repond - support for multiple EPackages (BZ 132958#c24)
 *
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.ecore.ui.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.ColumnDefinition;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.ResultSetColumns;
import org.eclipse.datatools.connectivity.oda.design.ResultSetDefinition;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage;
import org.eclipse.datatools.connectivity.oda.design.util.DesignUtil;
import org.eclipse.datatools.enablement.oda.ecore.Constants;
import org.eclipse.datatools.enablement.oda.ecore.impl.ColumnDefinitionUtil;
import org.eclipse.datatools.enablement.oda.ecore.ui.i18n.Messages;
import org.eclipse.datatools.enablement.oda.ecore.ui.util.PropertiesUtil;
import org.eclipse.datatools.enablement.oda.ecore.util.EPackageUtil;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.tree.TreeFactory;
import org.eclipse.emf.edit.tree.TreeNode;
import org.eclipse.emf.edit.tree.impl.TreeNodeImpl;
import org.eclipse.emf.edit.tree.provider.TreeItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class DataSetColumnsWizardPage extends DataSetWizardPage {

	private Set<EPackage> ePackages;
	private CheckboxTreeViewer viewer;
	private Properties dataSourceProperties;

	public DataSetColumnsWizardPage(final String pageName) {
		super(pageName);
		setTitle(pageName);
		setMessage(Messages.getString("DataSetColumnsWizardPage.message.default")); //$NON-NLS-1$
		setPageComplete(false);
	}

	public DataSetColumnsWizardPage(final String pageName, final String title, final ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
		setTitle(pageName);
		setMessage(Messages.getString("DataSetColumnsWizardPage.message.default")); //$NON-NLS-1$
		setPageComplete(false);
	}

	@Override
	public void createPageCustomControl(final Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.fillDefaults().hint(SWT.DEFAULT, 100).align(SWT.FILL, SWT.FILL).applyTo(composite);

		viewer = new ContainerCheckedGraphViewer(composite, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

		final ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory();
		adapterFactory.addAdapterFactory(new EcoreAdapterFactory());
		adapterFactory.addAdapterFactory(new TreeItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		viewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
		viewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		viewer.addCheckStateListener(new ICheckStateListener() {

			public void checkStateChanged(final CheckStateChangedEvent event) {
				validateData();
				savePage(getEditingDesign());
			}

		});
		initializeControls();
		setControl(composite);
		GridLayoutFactory.fillDefaults().margins(4, 4).generateLayout(composite);
	}

	@Override
	protected void refresh(final DataSetDesign dataSetDesign) {
		super.refresh(dataSetDesign);
		setViewerInputBasedOnInvariant(dataSetDesign);
	}

	private void initializeControls() {
		final DataSetDesign dataSetDesign = getInitializationDesign();
		dataSourceProperties = DesignUtil.convertDataSourceProperties(dataSetDesign.getDataSourceDesign());

		try {
			ePackages = EPackageUtil.getPackagesForModel(dataSourceProperties);
			setViewerInputBasedOnInvariant(dataSetDesign);
		} catch (final WrappedException e) {
			final Throwable cause = e.getCause();
			if (cause == null) {
				setMessage(e.getMessage(), ERROR);
			} else {
				setMessage(Messages.getString("DataSetColumnsWizardPage.message.loadError") + cause.getMessage(), ERROR); //$NON-NLS-1$
			}
		} catch (final OdaException e) {
			setMessage(Messages.getString("DataSetColumnsWizardPage.message.noEPackage"), ERROR); //$NON-NLS-1$
		}
	}

	private void setViewerInputBasedOnInvariant(final DataSetDesign dataSetDesign) {
		if (dataSetDesign.getPrivateProperties() != null) {
			final String invariant = dataSetDesign.getPrivateProperties().getProperty(Constants.OCL_ECORE_INVARIANT);
			if (invariant == null || invariant.length() == 0) {
				showAllEstructuralFeatures();
			} else {
				final Collection<EClass> eClassHierarchy = new ArrayList<EClass>();
				for (EPackage ePackage : ePackages) {
					if ((ePackage.getEClassifier(invariant) instanceof EClass)) {
						
						final EClass invariantEClass = (EClass) ePackage.getEClassifier(invariant);
						eClassHierarchy.add(invariantEClass);
						eClassHierarchy.addAll(invariantEClass.getEAllSuperTypes());
					}
				}
				if (eClassHierarchy.isEmpty()) {
					showAllEstructuralFeatures();
				} else {
					viewer.setInput(new TreeViewerBuilder(eClassHierarchy).buildTree());
				}
			}
		} else {
			showAllEstructuralFeatures();
		}
		initializeCheckedElements(dataSetDesign);
	}

	private void initializeCheckedElements(final DataSetDesign dataSetDesign) {
		final TreePath[] treePaths = findTreePaths(dataSetDesign.getPrimaryResultSet());
		for (final TreePath path : treePaths) {
			viewer.reveal(path);
			if (path.getLastSegment() != null) {
				viewer.setChecked(path.getLastSegment(), true);
			}
			validateData();
		}
	}

	private TreePath[] findTreePaths(final ResultSetDefinition primaryResultSet) {
		if (primaryResultSet == null) {
			return new TreePath[] {};
		}
		final List<TreePath> treePaths = new ArrayList<TreePath>();
		final ResultSetColumns resultSetColumns = primaryResultSet.getResultSetColumns();
		final List<ColumnDefinition> columnDefinitions = resultSetColumns.getResultColumnDefinitions();
		for (final ColumnDefinition columnDefinition : columnDefinitions) {
			final ENamedElement[] featurePath = ColumnDefinitionUtil.getFeaturePath(columnDefinition, ePackages);
			final List<TreeNode> nodePath = new ArrayList<TreeNode>();
			TreeNode currentNode = (TreeNode) viewer.getInput();
			for (int i = 0; i < featurePath.length; i++) {
				final TreeNode newParent = findNodeFor(featurePath[i], currentNode);
				nodePath.add(newParent);
				currentNode = newParent;
			}
			treePaths.add(new TreePath(nodePath.toArray()));
		}
		return treePaths.toArray(new TreePath[treePaths.size()]);
	}

	private TreeNode findNodeFor(final ENamedElement data, final TreeNode parent) {
		final Collection<TreeNode> children = parent.getChildren();
		for (final TreeNode treeNode : children) {
			if (data.getName().equals(((ENamedElement) treeNode.getData()).getName())) {
				return treeNode;
			}
		}
		throw new IllegalArgumentException("Unable to locate [" + data + "] in [" + parent.getData() + "].");
	}

	private void showAllEstructuralFeatures() {
		Set<EClassifier> eClassifiers = new HashSet<EClassifier>();
		for (EPackage ePackage : ePackages) {
			eClassifiers.addAll(ePackage.getEClassifiers());
		}
		viewer.setInput(new TreeViewerBuilder(org.eclipse.emf.ecore.util.EcoreUtil.<EClass> getObjectsByType(eClassifiers, 
				EcorePackage.Literals.ECLASS)).buildTree());
	}

	/**
	 * Validates the selected value is valid; sets page message accordingly.
	 */
	private void validateData() {
		final boolean aValidColumnIsSelected = aValidColumnIsSelected();
		if (aValidColumnIsSelected) {
			setMessage(Messages.getString("DataSetColumnsWizardPage.message.default")); //$NON-NLS-1$
		} else {
			setMessage(Messages.getString("DataSetColumnsWizardPage.message.noColumnSelected"), ERROR); //$NON-NLS-1$
		}
		setPageComplete(aValidColumnIsSelected);
	}

	private boolean aValidColumnIsSelected() {
		for (final Object checkedElement : viewer.getCheckedElements()) {
			final TreeNode node = (TreeNode) checkedElement;
			if (node.getData() instanceof EAttribute || node.getData() instanceof EReference) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void setVisible(final boolean visible) {
		if (visible) {
			setViewerInputBasedOnInvariant(getEditingDesign());
		}
		super.setVisible(visible);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage
	 *      #collectDataSetDesign(org.eclipse.datatools.connectivity.oda.design. DataSetDesign)
	 */
	@Override
	protected DataSetDesign collectDataSetDesign(final DataSetDesign design) {
		if (dataSourceProperties == null) {
			return design; // controls have not been initialized
		}
		savePage(design);
		return design;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage #canLeave()
	 */
	@Override
	protected boolean canLeave() {
		return isPageComplete();
	}

	private void savePage(final DataSetDesign dataSetDesign) {
		if (!aValidColumnIsSelected()) {
			dataSetDesign.setResultSets(null);
			return;
		}
		final ResultSetColumns newResultSetColumns = DesignFactory.eINSTANCE.createResultSetColumns();
		final StringBuilder columns = new StringBuilder();
		for (final Object checkedElement : viewer.getCheckedElements()) {
			final TreeNode node = (TreeNode) checkedElement;
			if (node.getData() instanceof EAttribute || node.getData() instanceof EReference && noChildrenAreChecked(node)) {
				final ColumnDefinition newColumn = ColumnDefinitionUtil.createFor(getPathTo(node));
				newResultSetColumns.getResultColumnDefinitions().add(newColumn);
				columns.append(newColumn.getAttributes().getName());
				columns.append(',');
			}
		}
		PropertiesUtil.persistProperty(dataSetDesign, Constants.CONNECTION_COLUMN_DEFINITIONS, columns.toString());
		final ResultSetDefinition resultSetDefinition = DesignFactory.eINSTANCE.createResultSetDefinition();
		resultSetDefinition.setResultSetColumns(newResultSetColumns);

		dataSetDesign.setPrimaryResultSet(resultSetDefinition);
		dataSetDesign.getResultSets().setDerivedMetaData(true);
	}

	private boolean noChildrenAreChecked(final TreeNode node) {
		final Collection<TreeNode> children = node.getChildren();
		for (final TreeNode child : children) {
			if (viewer.getChecked(child)) {
				return false;
			}
		}
		return true;
	}

	private ENamedElement[] getPathTo(final TreeNode treeNode) {
		final List<ENamedElement> namedElements = new ArrayList<ENamedElement>();
		TreeNode visiting = treeNode;
		while (visiting.getData() instanceof ENamedElement) {
			namedElements.add((ENamedElement) visiting.getData());
			visiting = visiting.getParent();
		}
		Collections.reverse(namedElements);
		return namedElements.toArray(new ENamedElement[namedElements.size()]);
	}

	private static final class TreeViewerBuilder {

		private final Collection<EClass> eClasses;
		private TreeNode root;

		TreeViewerBuilder(final Collection<EClass> eClasses) {
			this.eClasses = eClasses;
		}

		TreeNode buildTree() {
			for (final EClass eClass : eClasses) {
				getOrCreateRoot().getChildren().add(createNodeFor(eClass));
			}
			return getOrCreateRoot();
		}

		TreeNode getOrCreateRoot() {
			if (root == null) {
				root = TreeFactory.eINSTANCE.createTreeNode();
				root.setData(EcoreFactory.eINSTANCE.createEObject());
			}
			return root;
		}

		private void attach(final TreeNode parent, final EClass eClass, final boolean showAllStructuralFeatures) {
			final EList<EAttribute> attributes = showAllStructuralFeatures ? eClass.getEAllAttributes() : eClass
					.getEAttributes();
			for (final EAttribute attribute : attributes) {
				createNodeFor(attribute).setParent(parent);
			}
			final EList<EReference> references = showAllStructuralFeatures ? eClass.getEAllReferences() : eClass
					.getEReferences();
			for (final EReference reference : references) {
				createNodeFor(reference).setParent(parent);
			}
		}

		TreeNode createNodeFor(final EClass eClass) {
			final TreeNode referenceNode = new LazyNonDispatchingClassNode();
			referenceNode.setData(eClass);
			return referenceNode;
		}

		TreeNode createNodeFor(final EAttribute attribute) {
			final TreeNode attributeNode = new NonDispatchingTreeNode();
			attributeNode.setData(attribute);
			return attributeNode;
		}

		TreeNode createNodeFor(final EReference reference) {
			final TreeNode referenceNode = new LazyNonDispatchingTreeNode(reference.getEReferenceType());
			referenceNode.setData(reference);
			return referenceNode;
		}

		private static class NonDispatchingTreeNode extends TreeNodeImpl {

			@Override
			public NotificationChain basicSetParent(final TreeNode newParent, final NotificationChain msgs) {
				super.basicSetParent(newParent, msgs);
				return null;
			}
		}

		private final class LazyNonDispatchingClassNode extends NonDispatchingTreeNode {

			@Override
			public EList<TreeNode> getChildren() {
				if (children == null) {
					super.getChildren(); // initialize the children list
					attach(this, (EClass) getData(), false);
				}
				return super.getChildren();
			}
		}

		private final class LazyNonDispatchingTreeNode extends NonDispatchingTreeNode {

			private final EClass eClass;

			LazyNonDispatchingTreeNode(final EClass eClass) {
				this.eClass = eClass;
			}

			@Override
			public EList<TreeNode> getChildren() {
				if (children == null) {
					super.getChildren(); // initialize the children list
					attach(this, eClass, true);
				}
				return super.getChildren();
			}
		}
	}
}

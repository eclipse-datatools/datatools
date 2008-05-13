/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2007-2008 SolutionsIQ, Inc.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   SolutionsIQ, Inc. - Initial API and implementation
 *
 * </copyright>
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.ecore.ui.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.tree.TreeFactory;
import org.eclipse.emf.edit.tree.TreeNode;
import org.eclipse.emf.edit.tree.provider.TreeItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.ContainerCheckedTreeViewer;

public class DataSetColumnsWizardPage extends DataSetWizardPage {

	private static final String DEFAULT_MESSAGE = "Define the columns for the data set";
	private EPackage ePackage;
	private CheckboxTreeViewer viewer;
	private Properties dataSourceProperties;

	public DataSetColumnsWizardPage(final String pageName) {
		super(pageName);
		setTitle(pageName);
		setMessage(DEFAULT_MESSAGE);
		setPageComplete(false);
	}

	public DataSetColumnsWizardPage(final String pageName, final String title, final ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
		setTitle(pageName);
		setMessage(DEFAULT_MESSAGE);
		setPageComplete(false);
	}

	@Override
	public void createPageCustomControl(final Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());
		GridDataFactory.fillDefaults().grab(true, true).applyTo(composite);

		viewer = new ContainerCheckedTreeViewer(composite, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(viewer.getControl());

		final ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory();
		adapterFactory.addAdapterFactory(new EcoreAdapterFactory());
		adapterFactory.addAdapterFactory(new TreeItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		viewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
		viewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		viewer.addCheckStateListener(new ICheckStateListener() {

			public void checkStateChanged(final CheckStateChangedEvent event) {
				validateData();
				updateResultSetDesign(getEditingDesign());
			}

		});
		initializeControls();
		setControl(composite);
	}

	@Override
	protected void refresh(final DataSetDesign dataSetDesign) {
		super.refresh(dataSetDesign);
		setViewerInputBasedOnInvariant(dataSetDesign);
	}

	private TreePath pathTo(final EStructuralFeature feature) {
		for (final TreeNode eClassNode : ((TreeNode) viewer.getInput()).getChildren()) {
			if (eClassNode.getData() == feature.getEContainingClass()) {
				for (final TreeNode featureNode : eClassNode.getChildren()) {
					if (featureNode.getData() == feature) {
						return new TreePath(new Object[] { eClassNode, featureNode });
					}
				}
			}
		}
		return new TreePath(new Object[] {});
	}

	private void initializeControls() {
		final DataSetDesign dataSetDesign = getInitializationDesign();
		dataSourceProperties = DesignUtil.convertDataSourceProperties(dataSetDesign.getDataSourceDesign());

		try {
			ePackage = EcoreUtil.getPackageForModel(dataSourceProperties);
			setViewerInputBasedOnInvariant(dataSetDesign);
		} catch (final WrappedException e) {
			final Throwable cause = e.getCause();
			if (cause == null) {
				setMessage(e.getMessage(), ERROR);
			} else {
				setMessage("Got an error trying to load: " + cause.getMessage(), ERROR);
			}
		} catch (final OdaException e) {
			setMessage("Couldn't get EPackage for model");
		}
	}

	private void setViewerInputBasedOnInvariant(final DataSetDesign dataSetDesign) {
		if (dataSetDesign.getPrivateProperties() != null) {
			final String invariant = dataSetDesign.getPrivateProperties().getProperty(Constants.OCL_ECORE_INVARIANT);
			if (invariant == null || invariant.length() == 0 || !(ePackage.getEClassifier(invariant) instanceof EClass)) {
				showAllEstructuralFeatures();
			} else {
				final Collection<EClass> eClassHierarchy = new ArrayList<EClass>();
				final EClass invariantEClass = (EClass) ePackage.getEClassifier(invariant);
				eClassHierarchy.add(invariantEClass);
				eClassHierarchy.addAll(invariantEClass.getEAllSuperTypes());
				viewer.setInput(new TreeViewerBuilder(eClassHierarchy).buildTree());
			}
		} else {
			showAllEstructuralFeatures();
		}
		initializeCheckedElements(dataSetDesign);
	}

	private void initializeCheckedElements(final DataSetDesign dataSetDesign) {
		final EStructuralFeature[] initialCheckedElements = findElementsToCheck(dataSetDesign.getPrimaryResultSet());
		if (initialCheckedElements != null) {
			for (final EStructuralFeature checkedElement : initialCheckedElements) {
				final TreePath path = pathTo(checkedElement);
				viewer.reveal(path);
				if (path.getLastSegment() != null) {
					viewer.setChecked(path.getLastSegment(), true);
				}
			}
			validateData();
		}
	}

	private void showAllEstructuralFeatures() {
		viewer.setInput(new TreeViewerBuilder(org.eclipse.emf.ecore.util.EcoreUtil.<EClass> getObjectsByType(ePackage
				.getEClassifiers(), EcorePackage.Literals.ECLASS)).buildTree());
	}

	private Map<String, EStructuralFeature> mapFeaturesToColumnNames() {
		final Map<String, EStructuralFeature> referencesByName = new HashMap<String, EStructuralFeature>();
		final List<EStructuralFeature> features = ColumnDefinitionUtil.getAllEStructuralFeatures(ePackage);
		for (final EStructuralFeature feature : features) {
			referencesByName.put(ColumnDefinitionUtil.getColumnNameFor(feature), feature);
		}
		return referencesByName;
	}

	private EStructuralFeature[] findElementsToCheck(final ResultSetDefinition primaryResultSet) {
		if (primaryResultSet == null) {
			return new EStructuralFeature[] {};
		}

		final List<EStructuralFeature> checkboxesToSelect = new ArrayList<EStructuralFeature>();
		final ResultSetColumns resultSetColumns = primaryResultSet.getResultSetColumns();
		@SuppressWarnings("unchecked")
		final List<ColumnDefinition> columnDefinitions = resultSetColumns.getResultColumnDefinitions();
		final Map<String, EStructuralFeature> featuresByColumnName = mapFeaturesToColumnNames();
		for (final ColumnDefinition columnDefinition : columnDefinitions) {
			checkboxesToSelect.add(featuresByColumnName.get(columnDefinition.getAttributes().getName()));
		}
		return checkboxesToSelect.toArray(new EStructuralFeature[checkboxesToSelect.size()]);
	}

	/**
	 * Validates the user-defined value in the page control exists and not a
	 * blank text. Set page message accordingly.
	 */
	private void validateData() {
		final boolean isValid = viewer.getCheckedElements().length > 0;

		if (isValid) {
			setMessage(DEFAULT_MESSAGE);
		} else {
			setMessage("At least one column must be selected", ERROR);
		}

		setPageComplete(isValid);
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
	 * @see
	 * org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage
	 * #collectDataSetDesign(org.eclipse.datatools.connectivity.oda.design.
	 * DataSetDesign)
	 */
	@Override
	protected DataSetDesign collectDataSetDesign(final DataSetDesign design) {
		if (dataSourceProperties == null) {
			return design; // controls have not been initialized
		}
		updateResultSetDesign(design);
		return design;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage
	 * #canLeave()
	 */
	@Override
	protected boolean canLeave() {
		return isPageComplete();
	}

	/**
	 * Updates the specified data set design's result set definition based on
	 * the specified runtime metadata.
	 * 
	 * @param resultSetMetaData
	 * 		runtime result set metadata instance
	 * @param dataSetDesign
	 * 		data set design instance to update
	 * @throws OdaException
	 */
	@SuppressWarnings("unchecked")
	private void updateResultSetDesign(final DataSetDesign dataSetDesign) {
		final List<Object> checkedElements = Arrays.asList(viewer.getCheckedElements());
		if (checkedElements.size() < 1) {
			dataSetDesign.setResultSets(null);
			return;
		}
		final ResultSetColumns newResultSetColumns = DesignFactory.eINSTANCE.createResultSetColumns();
		for (final Object checkedElement : checkedElements) {
			final TreeNode node = (TreeNode) checkedElement;
			if (node.getData() instanceof EStructuralFeature) {
				final EStructuralFeature feature = (EStructuralFeature) node.getData();
				newResultSetColumns.getResultColumnDefinitions().add(ColumnDefinitionUtil.createFor(feature));
			}
		}
		final ResultSetDefinition resultSetDefinition = DesignFactory.eINSTANCE.createResultSetDefinition();
		resultSetDefinition.setResultSetColumns(newResultSetColumns);

		dataSetDesign.setPrimaryResultSet(resultSetDefinition);
		dataSetDesign.getResultSets().setDerivedMetaData(true);
	}

	private static final class TreeViewerBuilder {

		private final Collection<EClass> eClasses;
		private TreeNode root;

		TreeViewerBuilder(final Collection<EClass> eClasses) {
			this.eClasses = eClasses;
		}

		TreeNode buildTree() {
			for (final EClass eClass : eClasses) {
				createNodeFor(eClass);
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

		TreeNode createNodeFor(final EClass eClass) {
			final TreeNode classNode = TreeFactory.eINSTANCE.createTreeNode();
			classNode.setData(eClass);
			for (final EStructuralFeature structuralFeature : eClass.getEStructuralFeatures()) {
				classNode.getChildren().add(createNodeFor(structuralFeature));
			}
			classNode.setParent(getOrCreateRoot());
			return classNode;
		}

		TreeNode createNodeFor(final EStructuralFeature feature) {
			final TreeNode featureNode = TreeFactory.eINSTANCE.createTreeNode();
			featureNode.setData(feature);
			return featureNode;
		}
	}
}

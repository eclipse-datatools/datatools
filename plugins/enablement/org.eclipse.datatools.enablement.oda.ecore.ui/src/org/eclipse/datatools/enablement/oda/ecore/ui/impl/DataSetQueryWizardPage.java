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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDriver;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.DesignFactory;
import org.eclipse.datatools.connectivity.oda.design.ResultSetColumns;
import org.eclipse.datatools.connectivity.oda.design.ResultSetDefinition;
import org.eclipse.datatools.connectivity.oda.design.ui.designsession.DesignSessionUtil;
import org.eclipse.datatools.connectivity.oda.design.util.DesignUtil;
import org.eclipse.datatools.enablement.oda.ecore.Constants;
import org.eclipse.datatools.enablement.oda.ecore.impl.Driver;
import org.eclipse.datatools.enablement.oda.ecore.ui.Activator;
import org.eclipse.datatools.enablement.oda.ecore.ui.i18n.Messages;
import org.eclipse.datatools.enablement.oda.ecore.ui.sourceviewer.DefaultSourceViewer;
import org.eclipse.datatools.enablement.oda.ecore.ui.sourceviewer.IOCLSourceViewer;
import org.eclipse.datatools.enablement.oda.ecore.ui.util.PropertiesUtil;
import org.eclipse.datatools.enablement.oda.ecore.util.EPackageUtil;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class DataSetQueryWizardPage extends org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage {

	private transient ComboViewer contextCombo;

	private transient IOCLSourceViewer syntaxViewer;

	private Properties dataSourceProperties;

	private StyledText queryText;

	public DataSetQueryWizardPage(final String pageName) {
		super(pageName);
		setTitle(pageName);
		setMessage(Messages.getString("DataSetQueryWizardPage.message.default")); //$NON-NLS-1$
		setPageComplete(false);
	}

	public DataSetQueryWizardPage(final String pageName, final String title, final ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
		setTitle(pageName);
		setMessage(Messages.getString("DataSetQueryWizardPage.message.default")); //$NON-NLS-1$
		setPageComplete(false);
	}

	@Override
	public void createPageCustomControl(final Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.fillDefaults().hint(SWT.DEFAULT, 100).align(SWT.FILL, SWT.FILL).applyTo(composite);

		final Label fieldLabel = new Label(composite, SWT.NONE);
		fieldLabel.setText(Messages.getString("DataSetQueryWizardPage.label.invariant")); //$NON-NLS-1$

		contextCombo = new ComboViewer(composite, SWT.NONE);
		contextCombo.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(final SelectionChangedEvent event) {
				validateData();
				final IStructuredSelection eventSelection = (IStructuredSelection) event.getSelection();
				setInvariantToSelection(getEditingDesign(), eventSelection);
			}
		});

		final Label queryLabel = new Label(composite, SWT.NONE);
		queryLabel.setText(Messages.getString("DataSetQueryWizardPage.label.query")); //$NON-NLS-1$

		syntaxViewer = createSyntaxViewer(composite);
		final SourceViewer viewer = syntaxViewer.getSourceViewer();
		GridDataFactory.fillDefaults().grab(true, true).applyTo(viewer.getControl());
		queryText = viewer.getTextWidget();
		queryText.addModifyListener(new ModifyListener() {

			public void modifyText(final ModifyEvent e) {
				validateData();
			}
		});
		queryText.addKeyListener(new InputKeyListener(viewer));

		setControl(composite);
		initializeControl();
		GridLayoutFactory.fillDefaults().margins(4, 4).generateLayout(composite);
	}

	private IOCLSourceViewer createSyntaxViewer(final Composite composite) {
		IOCLSourceViewer sourceViewer = null;
		final IConfigurationElement[] configurationElements = Platform.getExtensionRegistry().getConfigurationElementsFor(
				"org.eclipse.datatools.enablement.oda.ecore.ui.OCLSyntaxViewer"); //$NON-NLS-1$
		for (int i = 0; i < configurationElements.length; i++) {
			final IConfigurationElement element = configurationElements[i];
			if (element.getName().equalsIgnoreCase("oclSourceViewer")) { //$NON-NLS-1$
				try {
					sourceViewer = (IOCLSourceViewer) element.createExecutableExtension("class"); //$NON-NLS-1$
				} catch (final CoreException e) {
					throw new RuntimeException("Unable to create the extension", e);
				}
				if (!(sourceViewer instanceof DefaultSourceViewer)) {
					break;
				}
			}
		}
		sourceViewer.createExpressionControl(composite);
		return sourceViewer;
	}

	private void initializeControl() {
		final DataSetDesign dataSetDesign = getInitializationDesign();
		dataSourceProperties = DesignUtil.convertDataSourceProperties(dataSetDesign.getDataSourceDesign());
		try {
			Set<EPackage> ePackages = EPackageUtil.getPackagesForModel(dataSourceProperties);
			if (!ePackages.isEmpty()) {
				fillContextCombo(dataSetDesign, ePackages);
				syntaxViewer.setExpression(dataSetDesign.getQueryText());
			}
			validateData();
		} catch (final WrappedException e) {
			final Throwable cause = e.getCause();
			if (cause == null) {
				setMessage(e.getMessage(), ERROR);
			} else {
				setMessage(Messages.getString("DataSetQueryWizardPage.message.loadError") + cause.getMessage(), ERROR); //$NON-NLS-1$
			}
		} catch (final OdaException e) {
			setMessage(Messages.getString("DataSetQueryWizardPage.message.noEPackage"), ERROR); //$NON-NLS-1$
		}
	}

	private void fillContextCombo(final DataSetDesign dataSetDesign, final Set<EPackage> ePackages) {
		contextCombo.setContentProvider(new ArrayContentProvider());
		contextCombo.setLabelProvider(new LabelProvider() {

			@Override
			public String getText(final Object element) {
				return ((EClassifier) element).getName();
			}
		});
		contextCombo.setSorter(new ViewerSorter() {

			@Override
			public int compare(final Viewer viewer, final Object e1, final Object e2) {
				return ((EClassifier) e1).getName().compareTo(((EClassifier) e2).getName());
			}
		});

		final List<EClassifier> classes = new LinkedList<EClassifier>();
		for (EPackage ePackage : ePackages) {
			classes.addAll(ePackage.getEClassifiers());
		}
		for (final Iterator<EClassifier> iter = classes.iterator(); iter.hasNext();) {
			if (!(iter.next() instanceof EClass)) {
				iter.remove();
			}
		}
		final EClass dummy = EcoreFactory.eINSTANCE.createEClass();
		dummy.setName(""); //$NON-NLS-1$
		classes.add(dummy);
		contextCombo.setInput(classes);
		initializeContextCombo(getEditingDesign(), ePackages);
	}

	private void initializeContextCombo(final DataSetDesign dataSetDesign, final Set<EPackage> ePackages) {
		if (dataSetDesign.getPrivateProperties() != null) {
			final String invariant = dataSetDesign.getPrivateProperties().getProperty(Constants.OCL_ECORE_INVARIANT);
			if (invariant != null && invariant.length() > 0) {
				for (EPackage ePackage : ePackages) {
					if (ePackage.getEClassifier(invariant) != null) {
						final EClassifier invariantEClass = ePackage.getEClassifier(invariant);
						contextCombo.setSelection(new StructuredSelection(invariantEClass));
					}
				}
			}
		}
	}

	@Override
	protected DataSetDesign collectDataSetDesign(final DataSetDesign design) {
		// if this page in DataSetEditor hasn't been activated
		if (syntaxViewer == null || queryText == null) {
			return design;
		}

		savePage(design);
		return design;
	}

	@Override
	protected boolean canLeave() {
		return isPageComplete();
	}

	private void setInvariantToSelection(final DataSetDesign dataSetDesign, final IStructuredSelection selection) {
		final Object selectedElement = selection.getFirstElement();
		if (selectedElement == null) {
			return;
		}
		final String invariant;
		if (selectedElement instanceof EClassifier) {
			invariant = ((EClassifier) selectedElement).getName();
		} else {
			invariant = selectedElement.toString();
		}
		PropertiesUtil.persistProperty(dataSetDesign, Constants.OCL_ECORE_INVARIANT, invariant);
	}

	/**
	 * Updates the given dataSetDesign with the query and its metadata defined in this page.
	 * 
	 * @param dataSetDesign
	 */
	private void savePage(final DataSetDesign dataSetDesign) {
		dataSetDesign.setQueryText(queryText.getText());
		setInvariantToSelection(dataSetDesign, (IStructuredSelection) contextCombo.getSelection());
		if (dataSetDesign.getPrimaryResultSet() != null) {
			return;
		}
		IConnection connection = null;
		try {
			// instantiate your custom ODA runtime driver class
			/*
			 * Note: You may need to manually update your ODA runtime extension's plug-in manifest to export its package for
			 * visibility here.
			 */
			final IDriver driver = new Driver();

			// obtain and open a live connection
			connection = driver.getConnection(null);
			connection.open(dataSourceProperties);

			// update the data set design with the
			// query's current runtime metadata
			updateDesign(connection, dataSetDesign);
		} catch (final OdaException e) {
			// not able to get current metadata, reset previous derived metadata
			dataSetDesign.setResultSets(null);
			dataSetDesign.setParameters(null);
			final String message = Messages.getString("DataSetQueryWizardPage.message.designUpdateError") + e.getMessage(); //$NON-NLS-1$
			Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, message, e));
		} finally {
			try {
				connection.close();
			} catch (final OdaException e) {
				// Ignore--nothing can be done
			}
		}
	}

	/**
	 * Updates the given dataSetDesign with the queryText and its derived metadata obtained from the ODA runtime connection.
	 */
	private void updateDesign(final IConnection conn, final DataSetDesign dataSetDesign) throws OdaException {
		final IQuery query = conn.newQuery(null);
		query.prepare(dataSetDesign.getQueryText());
		try {
			final IResultSetMetaData resultSetMetaData = query.getMetaData();
			updateResultSetDesign(resultSetMetaData, dataSetDesign);
		} catch (final OdaException e) {
			dataSetDesign.setResultSets(null);
			throw e;
		}
	}

	/**
	 * Updates the specified data set design's result set definition based on the specified runtime metadata.
	 * 
	 * @param resultSetMetaData
	 *            runtime result set metadata instance
	 * @param dataSetDesign
	 *            data set design instance to update
	 * @throws OdaException
	 */
	private void updateResultSetDesign(final IResultSetMetaData resultSetMetaData, final DataSetDesign dataSetDesign)
			throws OdaException {
		final ResultSetColumns columns = DesignSessionUtil.toResultSetColumnsDesign(resultSetMetaData);

		final ResultSetDefinition resultSetDefinition = DesignFactory.eINSTANCE.createResultSetDefinition();
		// resultSetDefn.setName( value ); // result set name
		resultSetDefinition.setResultSetColumns(columns);

		// no exception in conversion; go ahead and assign to specified
		// dataSetDesign
		dataSetDesign.setPrimaryResultSet(resultSetDefinition);
		dataSetDesign.getResultSets().setDerivedMetaData(true);
	}

	private void validateData() {
		final boolean isValid = syntaxViewer.getExpression() != null && syntaxViewer.getExpression().trim().length() > 0;
		if (isValid) {
			setMessage(Messages.getString("DataSetQueryWizardPage.message.default")); //$NON-NLS-1$
		} else {
			setMessage(Messages.getString("DataSetQueryWizardPage.message.expressionRequired"), ERROR); //$NON-NLS-1$
		}

		setPageComplete(isValid);
	}

	private class InputKeyListener implements KeyListener {

		private final SourceViewer viewer;

		public InputKeyListener(final SourceViewer viewer) {
			this.viewer = viewer;
		}

		public void keyPressed(final KeyEvent e) {
			if (e.keyCode == SWT.TAB) {
				// Move out of expression
				viewer.getTextWidget().traverse(SWT.TRAVERSE_TAB_NEXT);
			}
		}

		public void keyReleased(final KeyEvent e) {
			// no-op
		}
	}
}

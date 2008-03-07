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

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.util.DesignUtil;
import org.eclipse.datatools.enablement.oda.ecore.Constants;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.search.ocl.ui.viewer.OCLSourceViewer;
import org.eclipse.emf.search.ocl.ui.widget.OCLExpressionWidget;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class DataSetQueryWizardPage extends org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage {

	private static String DEFAULT_MESSAGE = "Define the query text for the data set";

	private transient ComboViewer contextCombo;

	private transient OCLExpressionWidget expressionWidget;

	private DataSetDesign dataSetDesign;

	public DataSetQueryWizardPage(final String pageName) {
		super(pageName);
		setTitle(pageName);
		setMessage(DEFAULT_MESSAGE);
		setPageComplete(false);
	}

	public DataSetQueryWizardPage(final String pageName, final String title, final ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
		setTitle(pageName);
		setMessage(DEFAULT_MESSAGE);
		setPageComplete(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#createPageCustomControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPageCustomControl(final Composite parent) {
		final Composite composite = new Composite(parent, SWT.BORDER);
		composite.setLayout(new GridLayout());
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).applyTo(composite);

		final Label fieldLabel = new Label(composite, SWT.NONE);
		fieldLabel.setText("OCL Invariant &Context (optional):");
		GridDataFactory.fillDefaults().applyTo(fieldLabel);

		contextCombo = new ComboViewer(composite, SWT.NONE);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(contextCombo.getControl());
		contextCombo.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(final SelectionChangedEvent event) {
				validateData();
			}
		});

		final Label queryLabel = new Label(composite, SWT.NONE);
		queryLabel.setText("OCL Invariant &Expression:");
		GridDataFactory.fillDefaults().applyTo(queryLabel);

		expressionWidget = new OCLExpressionWidget(composite);
		expressionWidget.setLayout(new GridLayout());
		GridDataFactory.fillDefaults().grab(true, true).applyTo(expressionWidget);
		final SourceViewer viewer = expressionWidget.getViewer();
		if (viewer instanceof OCLSourceViewer) {
			final StyledText textWidget = ((OCLSourceViewer) viewer).getTextWidget();
			GridDataFactory.fillDefaults().grab(true, true).applyTo(textWidget);
			textWidget.addModifyListener(new ModifyListener() {
				public void modifyText(final ModifyEvent e) {
					validateData();
				}
			});
			textWidget.addKeyListener(new InputKeyListener(viewer));
		}

		setControl(composite);
		initializeControl();
	}

	/**
	 * Initializes the page control with the last edited data set design.
	 */
	private void initializeControl() {
		dataSetDesign = getInitializationDesign();
		final Properties dataSourceProperties = DesignUtil.convertDataSourceProperties(dataSetDesign
				.getDataSourceDesign());
		final URI uri = URI.createURI((String) dataSourceProperties.get(Constants.CONNECTION_ECORE_MODEL_URI_STRING));

		try {
			fillContextCombo(EcoreUtil.getPackageForDataSource(uri));
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		expressionWidget.setExpression(dataSetDesign.getQueryText());
		if (!contextCombo.getSelection().isEmpty()) {
			expressionWidget.setContext((EObject) contextCombo.getSelection());
		}

		validateData();
	}

	/*
	 * Fills the combo box with the available context metaclasses.
	 * 
	 * @see org.eclipse.emf.query.examples.ocl.wizards.QueryWithContextWizardPage
	 */
	private void fillContextCombo(final EPackage ePackage) {
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

		// show only EClasses (cannot query for EDataType values)
		final List<EClassifier> classes = new LinkedList<EClassifier>(ePackage.getEClassifiers());
		for (final Iterator<EClassifier> iter = classes.iterator(); iter.hasNext();) {
			if (!(iter.next() instanceof EClass)) {
				iter.remove();
			}
		}
		final EClass dummy = EcoreFactory.eINSTANCE.createEClass();
		dummy.setName("");
		classes.add(dummy);
		contextCombo.setInput(classes);

		// // apply the default selection, if possible
		// final EClassifier defaultSelection =
		// ePackage.getEClassifier(metaClassDefault);
		// if (defaultSelection != null) {
		// contextCombo.setSelection(new StructuredSelection(defaultSelection),
		// true);
		// }
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#collectDataSetDesign(org.eclipse.datatools.connectivity.oda.design.DataSetDesign)
	 */
	@Override
	protected DataSetDesign collectDataSetDesign(final DataSetDesign design) {
		// FIXME: How to preserve page contributions in design?
		// if (!hasValidData()) {
		// return design;
		// }
		// design.setQueryText(expressionWidget.getExpression());
		return design;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#collectResponseState()
	 */
	@Override
	protected void collectResponseState() {
		super.collectResponseState();
		/*
		 * To optionally assign a custom response state, for inclusion in the
		 * ODA design session response, use setResponseSessionStatus(
		 * SessionStatus status ); setResponseDesignerState( DesignerState
		 * customState );
		 */
		// TODO: Implement something for additional fields?
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#canLeave()
	 */
	@Override
	protected boolean canLeave() {
		return isPageComplete();
	}

	/**
	 * Validates the user-defined value in the page control exists and not a
	 * blank text. Set page message accordingly.
	 */
	private void validateData() {
		final boolean isValid = expressionWidget.getExpression() != null
				&& expressionWidget.getExpression().trim().length() > 0;

		if (isValid) {
			setMessage(DEFAULT_MESSAGE);
		} else {
			setMessage("An expression is required.", ERROR);
		}

		setPageComplete(isValid);
	}

	/**
	 * Indicates whether the custom page has valid data to proceed with defining
	 * a data set.
	 */
	private boolean hasValidData() {
		validateData();

		return canLeave();
	}

	/**
	 * A key listener that listens for the Tab key to exit the OCL expression
	 * widget.
	 */
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
			// Nada
		}
	}

}

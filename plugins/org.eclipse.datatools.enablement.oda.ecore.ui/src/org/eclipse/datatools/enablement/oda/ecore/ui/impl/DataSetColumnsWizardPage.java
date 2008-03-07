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
import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.util.DesignUtil;
import org.eclipse.datatools.enablement.oda.ecore.Constants;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class DataSetColumnsWizardPage extends
		org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage {

	private static final String DEFAULT_MESSAGE = "Define the columns for the data set";
	private DataSetDesign dataSetDesign;
	private EPackage ePackage;
	private CheckboxTableViewer viewer;

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

		viewer = CheckboxTableViewer.newCheckList(composite, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(viewer.getControl());

		final ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory();
		adapterFactory.addAdapterFactory(new EcoreAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		viewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory) {

			@Override
			public Object[] getElements(final Object object) {
				return ((EPackage) object).getEClassifiers().toArray();
			}

		});
		viewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

		initializeControls();
		viewer.setInput(ePackage);
		viewer.addCheckStateListener(new ICheckStateListener() {

			public void checkStateChanged(final CheckStateChangedEvent event) {
				validateData();
			}

		});
		setControl(composite);
	}

	private void initializeControls() {
		dataSetDesign = getInitializationDesign();
		final Properties dataSourceProperties = DesignUtil.convertDataSourceProperties(dataSetDesign
				.getDataSourceDesign());
		final URI uri = URI.createURI((String) dataSourceProperties.get(Constants.CONNECTION_ECORE_MODEL_URI_STRING));

		ePackage = null;
		try {
			ePackage = EcoreUtil.getPackageForDataSource(uri);
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSetWizardPage#collectDataSetDesign(org.eclipse.datatools.connectivity.oda.design.DataSetDesign)
	 */
	@Override
	protected DataSetDesign collectDataSetDesign(final DataSetDesign design) {
		// if (!hasValidData()) {
		// return design;
		// }
		// design.??????
		// FIXME: How should we preserve the selected columns?
		return design;
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

}

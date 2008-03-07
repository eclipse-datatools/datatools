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
package org.eclipse.datatools.enablement.oda.ecore.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDataSetMetaData;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.ecore.Constants;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public class Connection implements IConnection {

	private boolean isOpen = false;
	private Properties connProperties;
	private EObject model;
	private EClassifier classifier;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#open(java.util.Properties)
	 */
	public void open(final Properties connProperties) throws OdaException {
		if (isOpen) {
			return;
		}
		if (connProperties == null) {
			throw new OdaException(new IllegalArgumentException("NULL connection properties not allowed"));
		}
		this.connProperties = connProperties;

		model = getModel(connProperties);
		isOpen = true;
	}

	protected EObject getModel(final Properties properties) throws OdaException {
		EObject model = null;
		final Object object = properties.get(Constants.CONNECTION_ECORE_MODEL_INSTANCE);
		if (object instanceof EObject) {
			model = (EObject) object;
		} else {
			final String uriString = (String) properties.get(Constants.CONNECTION_ECORE_MODEL_URI_STRING);
			final ResourceSet resourceSet = new ResourceSetImpl();
			final URI uri = URI.createURI(uriString);
			final Resource resource = resourceSet.getResource(uri, true);
			model = resource.getContents().get(0);
		}
		return model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#setAppContext(java.lang.Object)
	 */
	public void setAppContext(final Object context) throws OdaException {
		// do nothing; assumes no support for pass-through context
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#close()
	 */
	public void close() throws OdaException {
		isOpen = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#isOpen()
	 */
	public boolean isOpen() throws OdaException {
		return isOpen;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#getMetaData(java.lang.String)
	 */
	public IDataSetMetaData getMetaData(final String dataSetType) throws OdaException {
		// assumes that this driver supports only one type of data set,
		// ignores the specified dataSetType
		return new DataSetMetaData(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#newQuery(java.lang.String)
	 */
	public IQuery newQuery(final String dataSetType) throws OdaException {
		if (!isOpen) {
			throw new OdaException("Query cannot be created for closed connection");
		}
		return new Query(model, classifier, getColumnDefinitions());
	}

	private ColumnDefinition[] getColumnDefinitions() {
		final List<ColumnDefinition> columnDefinitions = new ArrayList<ColumnDefinition>();
		if (classifier != null) {
			for (final EStructuralFeature structuralFeature : classifier.eClass().getEAllStructuralFeatures()) {
				final ColumnDefinition columnDefinition = new ColumnDefinition(structuralFeature.getName(),
						structuralFeature);
				columnDefinitions.add(columnDefinition);
			}
		} else {
			for (final EStructuralFeature structuralFeature : model.eClass().getEAllStructuralFeatures()) {
				final ColumnDefinition columnDefinition = new ColumnDefinition(structuralFeature.getName(),
						structuralFeature);
				columnDefinitions.add(columnDefinition);
			}
		}
		return columnDefinitions.toArray(new ColumnDefinition[columnDefinitions.size()]);
	}

	private ColumnDefinition[] getColumnDefinitions(final Properties properties) throws OdaException {
		ColumnDefinition[] columnDefinitions = (ColumnDefinition[]) properties
				.get(Constants.CONNECTION_COLUMN_DEFINITIONS);
		if (columnDefinitions == null) {
			final String[] columnNames = (String[]) properties.get(Constants.CONNECTION_COLUMN_DEFINITION_NAMES);
			if (columnNames == null) {
				throw new OdaException("No column names were defined in property '"
						+ Constants.CONNECTION_COLUMN_DEFINITION_NAMES + "'");
			}
			final String[][] columnReferences = (String[][]) properties
					.get(Constants.CONNECTION_COLUMN_DEFINITION_REFERENCES);
			if (columnReferences == null) {
				throw new OdaException("No column references were defined in property '"
						+ Constants.CONNECTION_COLUMN_DEFINITION_REFERENCES + "'");
			}
			final int[] columnFeatures = (int[]) properties.get(Constants.CONNECTION_COLUMN_DEFINITION_FEATURES);
			if (columnReferences == null) {
				throw new OdaException("No column structural features were defined in property '"
						+ Constants.CONNECTION_COLUMN_DEFINITION_FEATURES + "'");
			}
			columnDefinitions = new ColumnDefinition[columnReferences.length];
			for (int i = 0; i < columnReferences.length; i++) {
				columnDefinitions[i] = new ColumnDefinition(columnNames[i], columnReferences[i], columnFeatures[i]);
			}
		}
		return columnDefinitions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#getMaxQueries()
	 */
	public int getMaxQueries() throws OdaException {
		return 0; // no limit
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#commit()
	 */
	public void commit() throws OdaException {
		// do nothing; assumes no transaction support needed
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#rollback()
	 */
	public void rollback() throws OdaException {
		// do nothing; assumes no transaction support needed
	}

}

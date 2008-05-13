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

import static org.eclipse.datatools.enablement.oda.ecore.impl.ColumnDefinitionUtil.createFor;
import static org.eclipse.datatools.enablement.oda.ecore.impl.ColumnDefinitionUtil.getAllEStructuralFeatures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDataSetMetaData;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.ColumnDefinition;
import org.eclipse.datatools.enablement.oda.ecore.Constants;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public class Connection implements IConnection {

	private boolean isOpen = false;
	private Collection<EObject> eObjects;
	private EClassifier classifier;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.datatools.connectivity.oda.IConnection#open(java.util.Properties
	 * )
	 */
	public void open(final Properties connProperties) throws OdaException {
		if (isOpen) {
			return;
		}
		if (connProperties == null) {
			throw new OdaException(new IllegalArgumentException("NULL connection properties not allowed"));
		}

		eObjects = getModel(connProperties);
		isOpen = true;
	}

	public static Collection<EObject> getModel(final Properties properties) throws OdaException {
		final Object object = properties.get(Constants.CONNECTION_EOBJECT_INSTANCES);
		if (object instanceof Collection) {
			@SuppressWarnings("unchecked")
			final Collection<EObject> returnValue = (Collection<EObject>) object;
			return returnValue;
		}
		final boolean isWorkspaceRelative = Boolean.parseBoolean(properties.getProperty(
				Constants.CONNECTION_DIRECTORY_ISWORKSPACE, "false"));
		final URI uri;
		final String pathString = properties.getProperty(Constants.CONNECTION_DIRECTORY_PATH, null);
		if (isWorkspaceRelative && pathString != null) {
			uri = URI.createURI(ResourcesPlugin.getWorkspace().getRoot().findMember(pathString).getLocationURI()
					.toString());
		} else {
			uri = URI.createFileURI(pathString);
		}
		try {
			final Resource resource = new ResourceSetImpl().getResource(uri, true);
			return resource.getContents();
		} catch (final WrappedException e) {
			throw new OdaException(e.getCause());
		} catch (final RuntimeException e) {
			throw new OdaException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.datatools.connectivity.oda.IConnection#setAppContext(java
	 * .lang.Object)
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
	 * @see
	 * org.eclipse.datatools.connectivity.oda.IConnection#getMetaData(java.lang
	 * .String)
	 */
	public IDataSetMetaData getMetaData(final String dataSetType) throws OdaException {
		// assumes that this driver supports only one type of data set,
		// ignores the specified dataSetType
		return new DataSetMetaData(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.datatools.connectivity.oda.IConnection#newQuery(java.lang
	 * .String)
	 */
	public IQuery newQuery(final String dataSetType) throws OdaException {
		if (!isOpen) {
			throw new OdaException("Query cannot be created for closed connection");
		}
		return new Query(eObjects, classifier, getColumnDefinitions());
	}

	private ColumnDefinition[] getColumnDefinitions() {
		final List<ColumnDefinition> columnDefinitions = new ArrayList<ColumnDefinition>();
		if (classifier != null) {
			for (final EStructuralFeature structuralFeature : classifier.eClass().getEAllStructuralFeatures()) {
				final ColumnDefinition columnDefinition = createFor(structuralFeature);
				columnDefinitions.add(columnDefinition);
			}
		} else {
			for (final EStructuralFeature structuralFeature : getAllEStructuralFeatures(eObjects)) {
				final ColumnDefinition columnDefinition = createFor(structuralFeature);
				columnDefinitions.add(columnDefinition);
			}
		}
		return columnDefinitions.toArray(new ColumnDefinition[columnDefinitions.size()]);
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

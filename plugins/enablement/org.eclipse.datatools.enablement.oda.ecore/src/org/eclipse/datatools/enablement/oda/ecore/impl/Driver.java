/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2007-2008 SolutionsIQ, Inc.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *   SolutionsIQ, Inc. - Initial API and implementation
 *
 * </copyright>
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.ecore.impl;

import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDriver;
import org.eclipse.datatools.connectivity.oda.LogConfiguration;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.util.manifest.DataTypeMapping;
import org.eclipse.datatools.connectivity.oda.util.manifest.ExtensionManifest;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestExplorer;

public class Driver implements IDriver {
	static String ODA_DATA_SOURCE_ID = "org.eclipse.datatools.enablement.oda.ecore"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IDriver#getConnection(java.lang.String)
	 */
	public IConnection getConnection(final String dataSourceType) throws OdaException {
		// assumes that this driver supports only one type of data source,
		// ignores the specified dataSourceType
		return new Connection();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IDriver#setLogConfiguration(org.eclipse.datatools.connectivity.oda.LogConfiguration)
	 */
	public void setLogConfiguration(final LogConfiguration logConfig) throws OdaException {
		// do nothing; assumes simple driver has no logging
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IDriver#getMaxConnections()
	 */
	public int getMaxConnections() throws OdaException {
		return 0; // no limit
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.datatools.connectivity.oda.IDriver#setAppContext(java.lang.Object)
	 */
	public void setAppContext(final Object context) throws OdaException {
		// do nothing; assumes no support for pass-through context
	}

	/**
	 * (non-Javadoc) Returns the object that represents this extension's
	 * manifest.
	 * 
	 * @throws OdaException
	 */
	static ExtensionManifest getManifest() throws OdaException {
		return ManifestExplorer.getInstance().getExtensionManifest(ODA_DATA_SOURCE_ID);
	}

	/**
	 * Returns the native data type name of the specified code, as defined in
	 * this data source extension's manifest.
	 * 
	 * @param nativeTypeCode
	 *            the native data type code
	 * @return corresponding native data type name
	 * @throws OdaException
	 *             if lookup fails
	 */
	static String getNativeDataTypeName(final int nativeDataTypeCode) throws OdaException {
		final DataTypeMapping typeMapping = getManifest().getDataSetType(null).getDataTypeMapping(nativeDataTypeCode);
		if (typeMapping != null) {
			return typeMapping.getNativeType();
		}
		return "Non-defined";
	}

}

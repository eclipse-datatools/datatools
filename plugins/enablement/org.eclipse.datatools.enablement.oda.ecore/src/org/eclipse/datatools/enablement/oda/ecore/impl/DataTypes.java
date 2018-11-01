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

import java.util.Locale;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.util.manifest.DataTypeMapping;
import org.eclipse.datatools.connectivity.oda.util.manifest.ExtensionManifest;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestExplorer;
import org.eclipse.datatools.enablement.oda.ecore.i18n.Messages;

/**
 * This class hosts the information of data types that are supported by the Ecore ODA driver
 */
final class DataTypes {

	static final String DEFAULT_TYPE = "STRING";
	static final int DEFAULT_TYPE_CODE = 1;
	private static final String ECORE_DATA_SOURCE_ID = "org.eclipse.datatools.enablement.oda.ecore";

	private DataTypes() {
		// singleton
	}

	/**
	 * Return the int which stands for the type specified by input argument
	 * 
	 * @param typeName
	 *            the String value of a Type
	 * @return the int which stands for the type specified by input typeName
	 * @throws OdaException
	 *             Once the input argument is not a valid type name
	 */
	static int getNativeTypeCode(final String typeName) throws OdaException {
		if (typeName == null) {
			throw new OdaException(Messages.bind(Messages.dataTypes_typeNameInvalid, typeName));
		}
		final DataTypeMapping typeMapping = getManifest().getDataSetType(null).getDataTypeMapping(
				typeName.trim().toUpperCase(Locale.getDefault()));
		if (typeMapping == null) {
			throw new OdaException(Messages.bind(Messages.dataTypes_typeNameInvalid, typeName));
		}
		return typeMapping.getNativeTypeCode();
	}

	/**
	 * Return the String which stands for the type specified by input argument
	 * 
	 * @param typeName
	 *            the int value of a Type
	 * @return the String which stands for the type specified by input typeName
	 * @throws OdaException
	 *             Once the input argument is not a valid type name
	 */
	static String getNativeType(final int type) throws OdaException {
		final DataTypeMapping typeMapping = getManifest().getDataSetType(null).getDataTypeMapping(type);
		if (typeMapping == null) {
			throw new OdaException(Messages.bind(Messages.dataTypes_typeNameInvalid, type));
		}
		return typeMapping.getNativeType();
	}

	/**
	 * Returns the object that represents this extension's manifest.
	 * 
	 * @throws OdaException
	 */
	private static ExtensionManifest getManifest() throws OdaException {
		return ManifestExplorer.getInstance().getExtensionManifest(ECORE_DATA_SOURCE_ID);
	}
}

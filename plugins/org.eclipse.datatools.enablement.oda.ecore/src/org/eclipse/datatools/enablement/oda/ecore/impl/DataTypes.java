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

import java.sql.Types;
import java.util.Locale;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.util.manifest.DataTypeMapping;
import org.eclipse.datatools.connectivity.oda.util.manifest.ExtensionManifest;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestExplorer;
import org.eclipse.datatools.enablement.oda.ecore.i18n.Messages;

/**
 * This class hosts the information of data types that are supported by the
 * Ecore ODA driver
 */
public final class DataTypes {

	public static final int EINT = Types.INTEGER;
	public static final int EDOUBLE = Types.DOUBLE;
	public static final int ESTRING = Types.VARCHAR;
	public static final int EDATE = Types.DATE;
	public static final int EBOOLEAN = Types.BOOLEAN;

	private static final String ECORE_DATA_SOURCE_ID = "org.eclipse.datatools.enablement.oda.ecore";

	private DataTypes() {
		// not instantiable
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
	public static int getType(final String typeName) throws OdaException {
		if (typeName == null || typeName.trim().length() == 0) {
			return ESTRING;
		}

		final String preparedTypeName = typeName.trim().toUpperCase(Locale.ENGLISH);

		// get the data type definition from my plugin manifest for all other
		// types
		final DataTypeMapping typeMapping = getManifest().getDataSetType(null).getDataTypeMapping(preparedTypeName);
		if (typeMapping != null) {
			return typeMapping.getNativeTypeCode();
		}
		throw new OdaException(Messages.bind(Messages.dataTypes_typeNameInvalid, typeName));
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
	public static String getTypeString(final int type) throws OdaException {
		// get the data type definition from my plugin manifest for all other
		// types
		final DataTypeMapping typeMapping = getManifest().getDataSetType(null).getDataTypeMapping(type);
		if (typeMapping != null) {
			return typeMapping.getNativeType();
		}

		throw new OdaException(Messages.bind(Messages.dataTypes_typeNameInvalid, type));
	}

	/**
	 * Evaluate whether an input String is a valid type that is supported by
	 * Ecore driver
	 * 
	 * @param typeName
	 * @return
	 */
	public static boolean isValidType(final String typeName) {
		final String preparedTypeName = typeName.trim().toUpperCase(Locale.ENGLISH);

		// check the data type definition in my plugin manifest for all other
		// types
		DataTypeMapping typeMapping = null;
		try {
			typeMapping = getManifest().getDataSetType(null).getDataTypeMapping(preparedTypeName);
		} catch (final OdaException e) {
			// ignore
		}

		return typeMapping != null;
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

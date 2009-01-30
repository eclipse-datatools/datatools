/*
 *************************************************************************
 * Copyright (c) 2004, 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.nls;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
    private static final String BUNDLE_NAME = "org.eclipse.datatools.connectivity.oda.nls.messages"; //$NON-NLS-1$

    private Messages()
    {
    }

    static
    {
        // initialize resource bundle
        NLS.initializeMessages( BUNDLE_NAME, Messages.class );
    }

    public static String logManager_duplicateName;    

    public static String manifest_nullArgument;
	public static String manifest_NO_DATA_SOURCE_EXTN_ID_DEFINED;
	public static String manifest_INVALID_VERSION_VALUE;
	public static String manifest_NO_DRIVER_CLASS_DEFINED;
	public static String manifest_INVALID_SET_THREAD_CONTEXT_CLASSLOADER_VALUE;
	public static String manifest_NO_DATA_SET_TYPES_DEFINED;
	public static String manifest_NO_ATTRIBUTE_ID_DEFINED;
	public static String manifest_NO_DATA_TYPE_MAPPINGS_DEFINED;
	public static String manifest_NO_NATIVE_TYPE_CODE_DEFINED;
	public static String manifest_INVALID_NATIVE_TYPE_CODE_VALUE;
	public static String manifest_NO_NATIVE_TYPE_NAME_DEFINED;
	public static String manifest_NO_ODA_SCALAR_DATA_TYPE_DEFINED_1;
	public static String manifest_NO_ODA_SCALAR_DATA_TYPE_DEFINED_2;
	public static String manifest_INVALID_ODA_SCALAR_DATA_TYPE_VALUE;
	public static String manifest_INVALID_LOG_LEVEL_VALUE;

    public static String manifest_MISSING_ELEMENT_IN_EXTENSION_MANIFEST;
	public static String manifest_NO_LOG_FILENAME_PREFIX_DEFINED;

    public static String profileProperty_GROUP_PROPERTIES;
    public static String profileProperty_PROFILE_NAME;
    public static String profileProperty_PROFILE_STORE_PATH;

    public static String sortSpec_INDEX_OUT_OF_BOUND;    
	public static String sortSpec_INVALID_SORT_MODE_SPECIFIED;
	public static String sortSpec_NULL_COLUMN_NAME_SPECIFIED;
	public static String sortSpec_INVALID_COLUMN_NAME_SPECIFIED;
	public static String sortSpec_INVALID_SORT_ORDER_SPECIFIED;
	public static String sortSpec_NO_DYNAMIC_SORT_KEY_FOR_SORTMODENONE;
	public static String sortSpec_ONE_SORTCOLUMN_FOR_SINGLE_COLUMN_MODE;
	public static String sortSpec_ONE_SORTORDER_FOR_SINGLE_ORDER_MODE;
	public static String sortSpec_ONLY_IN_SINGLE_ORDER_MODE;

	public static String stringSubUtil_NO_STRING_VALUE_TO_REPLACE;
	public static String stringSubUtil_DELIMITER_CANNOT_BE_EMPTY;
	public static String stringSubUtil_DELIMITER_CANNOT_BE_NULL;
	public static String stringSubUtil_SUBSTITUTION_LIST_CANNOT_BE_NULL;
	public static String stringSubUtil_SUBSTITUTION_VALUE_CANNOT_BE_NULL;
	public static String stringSubUtil_TEXT_STRING_CANNOT_BE_NULL;
	public static String stringSubUtil_NAME_VALUE_MAP_CANNOT_BE_NULL;

}

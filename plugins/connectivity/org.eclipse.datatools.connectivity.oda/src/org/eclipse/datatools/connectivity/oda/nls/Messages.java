/*
 *************************************************************************
 * Copyright (c) 2004, 2013 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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

    public static String manifest_CANNOT_FIND_EXTENSION;
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
    public static String manifest_UNKNOWN_BUNDLE_LOCATION;

    public static String profileProperty_GROUP_PROPERTIES;
    public static String profileProperty_PROFILE_NAME;
    public static String profileProperty_PROFILE_STORE_PATH;

    public static String querySpec_CUSTOM_AGGR_EXCEED_MAX_ARGS;
    public static String querySpec_CUSTOM_AGGR_INCOMPATIBLE_DUPL_CHECK;
    public static String querySpec_CUSTOM_AGGR_INCOMPATIBLE_NULL_CHECK;
    public static String querySpec_CUSTOM_AGGR_LESS_THAN_MIN_ARGS;
    public static String querySpec_CUSTOM_FILTER_EXCEED_MAX_ARGS;
    public static String querySpec_CUSTOM_FILTER_LESS_THAN_MIN_ARGS;
    public static String querySpec_CUSTOM_FUNC_EXCEED_MAX_ARGS;
    public static String querySpec_CUSTOM_FUNC_INCOMPATIBLE_DUPL_CHECK;
    public static String querySpec_CUSTOM_FUNC_LESS_THAN_MIN_ARGS;
    public static String querySpec_EXTENSION_ID_NOT_FOUND;
    public static String querySpec_ILLEGAL_CONSTRUCTOR_ARG;
    public static String querySpec_INCOMPLETE_COMBINED_QUERY;
    public static String querySpec_INVALID_AGGR_EXPR;
    public static String querySpec_INVALID_AGGR_HIDE_COLUMN;
    public static String querySpec_INVALID_ARG;
    public static String querySpec_INVALID_BASE_QUERY;
    public static String querySpec_INVALID_CLASS_TYPE_ATTRIBUTE;
    public static String querySpec_INVALID_COLUMN_IDENTIFIER;
    public static String querySpec_INVALID_EXT_POINT_ATTR_VALUE;
    public static String querySpec_INVALID_EXT_POINT_ELEMENT;
    public static String querySpec_INVALID_FILTER_EXPR;
    public static String querySpec_INVALID_RESULT_PROJ;
    public static String querySpec_INVALID_SORT_KEY;
    public static String querySpec_INVALID_SORT_SPEC;
    public static String querySpec_INVALID_VALUE_EXPR;
    public static String querySpec_MAX_ONE_NEGATING_EXPR;
    public static String querySpec_MISSING_ATOMIC_QUERY_TEXT;
    public static String querySpec_MISSING_COMBINED_MATCHING_EXPR;
    public static String querySpec_MISSING_COMPOSITE_MIN_CHILDREN;
    public static String querySpec_MISSING_EXPR_VARIABLE;
    public static String querySpec_MISSING_EXT_POINT_ATTR_VALUE;
    public static String querySpec_MISSING_EXT_POINT_ELEMENT;
    public static String querySpec_MISSING_EXT_POINT_MIN_ELEMENT;
    public static String querySpec_NON_DEFINED_COMBINED_OP;
    public static String querySpec_NON_DEFINED_CUSTOM_AGGR;
    public static String querySpec_NON_DEFINED_CUSTOM_FILTER;
    public static String querySpec_NON_DEFINED_CUSTOM_FUNC;
    public static String querySpec_NON_RELATED_QUERYSPEC;
    public static String querySpec_NONSUPPORTED_NULL_ORDERING;
    public static String querySpec_NONSUPPORTED_VAR_DATA_TYPE;
    public static String querySpec_NOT_EXPR_INCOMPATIBLE;
    public static String querySpec_NOT_EXPR_MISSING_CHILD;
    public static String querySpec_NULL_CONSTRUCTOR_3ARGS;
    public static String querySpec_NULL_CONSTRUCTOR_ARG;
    public static String querySpec_UNABLE_ADD_TO_NON_LIST_COLLECTION;
    public static String querySpec_UNEXPECTED_AGGR_EXPR_EXTENSION;
    public static String querySpec_UNEXPECTED_AGGR_EXPR_TYPE;
    public static String querySpec_UNEXPECTED_CUSTOM_EXPR_EXTENSION;
    public static String querySpec_UNEXPECTED_CUSTOM_EXPR_TYPE;
    public static String querySpec_UNEXPECTED_EXPR_VARIABLE_TYPE;
    public static String querySpec_UNEXPECTED_FUNC_EXPR_EXTENSION;
    public static String querySpec_UNEXPECTED_FUNC_EXPR_TYPE;

    public static String sortSpec_INDEX_OUT_OF_BOUND;    
	public static String sortSpec_INVALID_SORT_MODE_SPECIFIED;
	public static String sortSpec_NULL_COLUMN_NAME_SPECIFIED;
	public static String sortSpec_INVALID_COLUMN_NAME_SPECIFIED;
    public static String sortSpec_INVALID_NULL_ORDERING;
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

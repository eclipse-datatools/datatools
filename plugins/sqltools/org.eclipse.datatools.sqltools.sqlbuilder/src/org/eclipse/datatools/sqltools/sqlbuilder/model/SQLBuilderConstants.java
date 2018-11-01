/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.model;

import org.eclipse.datatools.sqltools.sqlbuilder.Messages;

public interface SQLBuilderConstants {

    public final String P_STAR = "*"; //$NON-NLS-1$
    public final String P_BUILD_EXPRESSION = Messages._UI_BUILD_EXPRESSION; //$NON-NLS-1$
    public final String P_EDIT_EXPRESSION = Messages._UI_EDIT_EXPRESSION; //$NON-NLS-1$
    public final String P_REPLACE_EXPRESSION = Messages._UI_REPLACE_EXPRESSION; //$NON-NLS-1$
    public final String P_ADD_SELECT = Messages._UI_ADD_SELECT; //$NON-NLS-1$
    public final String P_ADD_FULLSELECT = Messages._UI_ADD_FULLSELECT; //$NON-NLS-1$
    public final String P_ADD_WITH = Messages._UI_ADD_WITH; //$NON-NLS-1$
    public final String P_ADD_VALUES = Messages._UI_ADD_VALUES; //$NON-NLS-1$
    public final String P_EDIT_INPUT_VALUE = Messages._UI_SPECIFY_VALUE; //$NON-NLS-1$

    public final String P_ACTION_EXECUTE = "executeAction"; //$NON-NLS-1$

    public final String P_OPERATOR_UNION = "UNION"; //$NON-NLS-1$
    public final String P_OPERATOR_INTERSECT = "INTERSECT"; //$NON-NLS-1$
    public final String P_OPERATOR_EXCEPT = "EXCEPT"; //$NON-NLS-1$
    public final String P_OPERATOR_UNION_ALL = "UNION ALL"; //$NON-NLS-1$
    public final String P_OPERATOR_INTERSECT_ALL = "INTERSECT ALL"; //$NON-NLS-1$
    public final String P_OPERATOR_EXCEPT_ALL = "EXCEPT ALL"; //$NON-NLS-1$
    public final String P_OPERATOR_MINUS = "MINUS"; //$NON-NLS-1$
    public final String P_OPERATOR_EXISTS = "EXISTS"; //$NON-NLS-1$
    public final String P_OPERATOR_XMLEXISTS = "XMLEXISTS"; //$NON-NLS-1$

    public final String P_VALUE_DEFAULT = "DEFAULT"; //$NON-NLS-1$
    public final String P_VALUE_NULL = "NULL"; //$NON-NLS-1$

    public final String P_SOURCE = "org.eclipse.datatools.sqltools.sqlbuilder.source"; //$NON-NLS-1$
    public final String P_STATEMENT_GRAPH = "org.eclipse.datatools.sqltools.sqlbuilder.SQLStatement.graph"; //$NON-NLS-1$

    public final Object P_STATEMENT_STRING = "org.eclipse.datatools.sqltools.sqlbuilder.SQLStatement.P_STATEMENT_STRING"; //$NON-NLS-1$
    public final Object P_STATEMENT_COLUMN = "org.eclipse.datatools.sqltools.sqlbuilder.SQLStatement.P_STATEMENT_COLUMN"; //$NON-NLS-1$
    public final Object P_STATEMENT_ALIAS = "org.eclipse.datatools.sqltools.sqlbuilder.SQLStatement.P_STATEMENT_ALIAS"; //$NON-NLS-1$
    public final Object P_STATEMENT_FUNCTION = "org.eclipse.datatools.sqltools.sqlbuilder.SQLStatement.P_STATEMENT_FUNCTION"; //$NON-NLS-1$
    public final Object P_STATEMENT_TABLE = "org.eclipse.datatools.sqltools.sqlbuilder.SQLStatement.P_STATEMENT_TABLE"; //$NON-NLS-1$
    public final Object P_STATEMENT_SORTTYPE = "org.eclipse.datatools.sqltools.sqlbuilder.SQLStatement.P_STATEMENT_SORTTYPE"; //$NON-NLS-1$
    public final Object P_STATEMENT_SORTORDER = "org.eclipse.datatools.sqltools.sqlbuilder.SQLStatement.P_STATEMENT_SORTORDER"; //$NON-NLS-1$
    public final Object P_STATEMENT_CRITERIA = "org.eclipse.datatools.sqltools.sqlbuilder.SQLStatement.P_STATEMENT_CRITERIA"; //$NON-NLS-1$
    public final Object P_STATEMENT_OPERATOR = "org.eclipse.datatools.sqltools.sqlbuilder.SQLStatement.P_STATEMENT_OPERATOR"; //$NON-NLS-1$
    public final Object P_STATEMENT_ORCRITERIA = "org.eclipse.datatools.sqltools.sqlbuilder.SQLStatement.P_STATEMENT_ORCRITERIA"; //$NON-NLS-1$
    public final Object P_STATEMENT_OUTPUT = "org.eclipse.datatools.sqltools.sqlbuilder.SQLStatement.P_STATEMENT_OUTPUT"; //$NON-NLS-1$
    public final Object P_STATEMENT_VALUE = "org.eclipse.datatools.sqltools.sqlbuilder.SQLStatement.P_STATEMENT_VALUE"; //$NON-NLS-1$
    public final Object P_STATEMENT_PARAMETER = "org.eclipse.datatools.sqltools.sqlbuilder.SQLStatement.P_STATEMENT_PARAMETER"; //$NON-NLS-1$
    public final Object P_STATEMENT_AS_TABLE_ROWS = "org.eclipse.datatools.sqltools.sqlbuilder.SQLStatement.P_STATEMENT_AS_TABLE_ROWS"; //$NON-NLS-1$
    public final Object P_STATEMENT_GET_TABLE_ROWS = "org.eclipse.datatools.sqltools.sqlbuilder.SQLStatement.P_STATEMENT_GET_TABLE_ROWS"; //$NON-NLS-1$
    public final Object P_STATEMENT_STOPEDITING = "org.eclipse.datatools.sqltools.sqlbuilder.SQLStatement.P_STATEMENT_STOPEDITING"; //$NON-NLS-1$
    public final Object P_TABLE_ADDCRITERIA_COLUMN = "org.eclipse.datatools.sqltools.sqlbuilder.SQLStatement.P_TABLE_ADDCRITERIA_COLUMN"; //$NON-NLS-1$
    public final Object P_TABLE_ADD_ROW = "org.eclipse.datatools.sqltools.sqlbuilder.SQLStatement.P_TABLE_ADD_ROW"; //$NON-NLS-1$
    public final Object P_EXPRESSION = "org.eclipse.datatools.sqltools.sqlbuilder.SQLStatement.P_EXPRESSION"; //$NON-NLS-1$

    /** Omit schema preference */
    public final static String P_VALIDATE_BEFORE_SAVE = "org.eclipse.datatools.sqltools.sqlbuilder.P_VALIDATE_BEFORE_SAVE"; //$NON-NLS-1$
    public final static String P_OMIT_SCHEMA = "org.eclipse.datatools.sqltools.sqlbuilder.P_OMIT_SCHEMA"; //$NON-NLS-1$
    public static final String MAX_ROWS_IN_QUERY_OUTPUT_PREFERENCE = "Maximum rows returned from SQL statement execution"; //$NON-NLS-1$
    public static final String SET_QUERY_OUTPUT_LIMIT_PREFERENCE = "Limit rows returned from SQL statement execution"; //$NON-NLS-1$

   
    //file extension that the editor works with  
    public static final String SQL_FILE_EXTENSION = ".sql"; //$NON-NLS-1$
 
    public static final String EMPTY_STRING = ""; //$NON-NLS-1$

}


/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sql.parser;

/**
 * This interface defines the constants used by SQLParser, including: scopes indicating the current context for the
 * SQLParser; and sql statement type constansts.
 * 
 * @author Hui Cao
 * 
 */
public interface SQLParserConstants
{
    // constanst starts with "SCOPE" are used to determine the context information when providing db information
    /**
     * Scope constant indicating the default scope.
     */
    public final static int SCOPE_DEFAULT              = 0x0001;
    /**
     * Scope constant indicating catalog list information is required.
     */
    public final static int SCOPE_CATALOGS             = 0x0002;
    /**
     * Scope constant indicating schema list information is required.
     */
    public final static int SCOPE_SCHEMAS              = 0x0004;
    /**
     * Scope constant indicating table list information is required.
     */
    public final static int SCOPE_TABLES               = 0x0008;
    /**
     * Scope constant indicating table list information is required.
     */
    public final static int SCOPE_VIEWS                = 0x0010;
    /**
     * Scope constant indicating column list information is required.
     */
    public final static int SCOPE_COLUMNS              = 0x0020;
    /**
     * Scope constant indicating index list information is required.
     */
    public final static int SCOPE_INDEXES              = 0x0040;
    /**
     * Scope constant indicating data type list information is required.
     */
    public final static int SCOPE_DATA_TYPES           = 0x0080;
    /**
     * Scope constant indicating procedure list information is required.
     */
    public final static int SCOPE_STORED_PROCEDURES    = 0x0100;
    /**
     * Scope constant indicating function list information is required.
     */
    public final static int SCOPE_FUNCTIONS            = 0x0200;
    /**
     * Scope constant indicating trigger list information is required.
     */
    public final static int SCOPE_TRIGGERS             = 0x0400;
    /**
     * Scope constant indicating event list information is required.
     */
    public final static int SCOPE_EVENTS               = 0x0800;
    /**
     * Scope constant indicating variable list information is NOT required because we are defining a new one.
     */
    public final static int SCOPE_DEFINE_VARIABLES     = 0x1000;
    /**
     * Scope constant indicating variable list information is required.
     */
    public final static int SCOPE_REFERENCE_VARIABLES  = 0x2000;
    /**
     * Scope constant indicating table name should be removed for columns. Combined with SCOPE_COLUMNS.
     */
    public final static int    SCOPE_WITHOUT_TABLE     = 0x4000;
    /**
     * Scope constant indicating the segment scope.
     */
    public final static int    SCOPE_SEGMENT           = 0x8000;

    // SQL statement types

    public final static int TYPE_SQL_ROOT              = 100;
    public final static int TYPE_SQL_OTHERS            = 50;
    public final static int TYPE_SQL_SELECT            = 51;
    public final static int TYPE_SQL_INSERT            = 52;
    public final static int TYPE_SQL_DELETE            = 53;
    public final static int TYPE_SQL_UPDATE            = 54;
    public final static int TYPE_SQL_CREATE_DATABASE   = 55;
    public final static int TYPE_SQL_CREATE_TABLE      = 56;
    public final static int TYPE_SQL_CREATE_VIEW       = 57;
    public final static int TYPE_SQL_CREATE_PROCEDURE  = 58;
    public final static int TYPE_SQL_CREATE_FUNCTION   = 59;
    public final static int TYPE_SQL_CREATE_EVENT      = 60;
    public final static int TYPE_SQL_CREATE_TRIGGER    = 61;
    public final static int TYPE_SQL_CREATE_DEFAULT    = 62;
    public final static int TYPE_SQL_DECLARE           = 63;
    public final static int TYPE_SQL_BEGIN             = 64;
    public final static int TYPE_SQL_CREATE_INDEX      = 65;
    public final static int TYPE_SQL_ALTER_DATABASE    = 155;
    public final static int TYPE_SQL_ALTER_TABLE       = 156;
    public final static int TYPE_SQL_ALTER_VIEW        = 157;
    public final static int TYPE_SQL_ALTER_PROCEDURE   = 158;
    public final static int TYPE_SQL_ALTER_FUNCTION    = 159;
    public final static int TYPE_SQL_ALTER_EVENT       = 160;
    public final static int TYPE_SQL_ALTER_TRIGGER     = 161;
    public final static int TYPE_SQL_CALL              = 162;
    public final static int TYPE_SQL_BEGIN_TRANSACTION = 163;
    public final static int TYPE_SQL_ALTER_INDEX       = 165;
    public final static int TYPE_SQL_DROP_VIEW         = 257;
    public final static int TYPE_SQL_SELECT_INTO       = 300;
    public final static int TYPE_SQL_EVENT_CONDITION   = 301;
    /**
     * Indicates that only the routine header is interesting to the consumer. Can be combined with other routine types.
     */
    public final static int TYPE_SQL_ROUTINE_HEADER    = 0x80000000;

    /*
     * Entry type constants
     */
    public static final String DATABASES                  = "__databases";
    public static final String TABLES                     = "__tables";
    public static final String COLUMNS                    = "__columns";
    /* key for variable names referenced in sql statements */
    public static final String VARIABLE_REFERENCES        = "__variable_references";
    public static final String VARIABLE_DECLARATIONS      = "__variable_declarations";
    public static final String VARIABLE_ASSIGNMENTS       = "__variable_assignments";
    public static final String GLOBAL_VARIABLES           = "__globalvariables";
    public static final String PARAMETERS                 = "__parameters";
    public static final String EVENT_PARAMETERS           = "__eventparameters";
    public static final String DML_STATEMENTS             = "__dml";
    public static final String DDL_STATEMENTS             = "__ddl";

}

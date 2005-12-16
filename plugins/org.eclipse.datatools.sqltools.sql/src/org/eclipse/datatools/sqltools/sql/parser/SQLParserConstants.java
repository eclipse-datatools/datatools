/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
     * Scope constant indicating catalog .
     */
    public final static int SCOPE_CATALOGS             = 0x0002;
    /**
     * Scope constant indicating the default scope.
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
     * Scope constant indicating the default scope.
     */
    public final static int SCOPE_COLUMNS              = 0x0020;
    /**
     * Scope constant indicating the default scope.
     */
    public final static int SCOPE_INDEXES              = 0x0040;
    /**
     * Scope constant indicating the default scope.
     */
    public final static int SCOPE_DATA_TYPES           = 0x0080;
    /**
     * Scope constant indicating the default scope.
     */
    public final static int SCOPE_STORED_PROCEDURES    = 0x0100;
    /**
     * Scope constant indicating the default scope.
     */
    public final static int SCOPE_FUNCTIONS            = 0x0200;
    /**
     * Scope constant indicating the default scope.
     */
    public final static int SCOPE_TRIGGERS             = 0x0400;
    /**
     * Scope constant indicating the default scope.
     */
    public final static int SCOPE_EVENTS               = 0x0800;

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

}

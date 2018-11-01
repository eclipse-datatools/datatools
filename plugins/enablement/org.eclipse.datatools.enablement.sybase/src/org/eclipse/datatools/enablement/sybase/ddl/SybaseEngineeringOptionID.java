/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.sybase.ddl;

import org.eclipse.datatools.connectivity.sqm.core.definition.EngineeringOptionID;

/**
 * TODO: 1. add more options here for IQ 2. add temp table or other object type
 */
public class SybaseEngineeringOptionID extends EngineeringOptionID
{
    // generation options
    /**
     * Specifies whether to generate the optional ddl statements when the model's value is set to default.
     */
    public final static String GENERATE_FULL_SYNTAX        = "GENERATE_FULL_SYNTAX";       //$NON-NLS-1$
    public final static String GENERATE_EXTRA_CODE         = "GENERATE_EXTRA_CODE";        //$NON-NLS-1$
    public final static String GENERATE_COMMENTS           = "GENERATE_COMMENTS";          //$NON-NLS-1$
    public final static String GENERATE_PRIVILEGE          = "GENERATE_PRIVILEGE";         //$NON-NLS-1$
    public final static String GENERATE_SETUSER            = "GENERATE_SETUSER";           //$NON-NLS-1$
    public final static String GENERATE_USEDATABASE        = "GENERATE_USEDATABASE";       //$NON-NLS-1$

    // ASE & ASA
    public final static String GENERATE_USERS_GROUPS       = "GENERATE_USERS_GROUPS";      //$NON-NLS-1$
    public final static String GENERATE_PROXY_TABLES       = "GENERATE_PROXY_TABLES";      //$NON-NLS-1$
    public final static String GENERATE_UNIQUE_CONSTRAINTS = "GENERATE_UNIQUE_CONSTRAINTS"; //$NON-NLS-1$
    public final static String GENERATE_DATABASE           = "GENERATE_DATABASE";          //$NON-NLS-1$
    public final static String GENERATE_CATALOGS           = "GENERATE_CATALOG";           //$NON-NLS-1$

    // ASE
    public final static String GENERATE_DEFAULTS           = "GENERATE_DEFAULTS";          //$NON-NLS-1$
    public final static String GENERATE_RULES              = "GENERATE_RULES";             //$NON-NLS-1$
    public final static String GENERATE_SEGMENTS           = "GENERATE_SEGMENTS";          //$NON-NLS-1$

    // ASA/IQ
    public final static String GENERATE_DBSPACES           = "GENERATE_DBSPACES";          //$NON-NLS-1$
    public final static String GENERATE_EVENTS             = "GENERATE_EVENTS";            //$NON-NLS-1$
    public final static String GENERATE_ROUTINES           = "GENERATE_ROUTINES";          //$NON-NLS-1$
    public final static String GENERATE_JOIN_INDICES       = "GENERATE_JOIN_INDICES";      //$NON-NLS-1$
    
    // masks
    // for Sybase
    public final static int    USERS_GROUPS                = 1 << 13;
    public final static int    PROXY_TABLES                = 1 << 14;
    public final static int    PRIMARY_KEY                 = 1 << 15;
    public final static int    PRIVILEGE                   = 1 << 16;

    // for ASE
    public final static int    RULES                       = 1 << 17;
    public final static int    DEFAULTS                    = 1 << 18;
    public final static int    SEGMENTS                    = 1 << 19;

    // for ASA/IQ
    public final static int    DBSPACE                     = 1 << 20;

    // extra code includes extra properties setting etc.
    public final static int    EXTRACODE                   = 1 << 21;
    public final static int    FULLY_SYNTAX                = 1 << 22;

}

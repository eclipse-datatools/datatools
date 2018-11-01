/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege;

import org.eclipse.datatools.enablement.sybase.ddl.ISybaseDdlConstants;

/**
 * Constants for privileges page
 * 
 * @author Idull
 */
public class PrivilegesConstants
{
    /**
     * Stand for the authorization identifiers
     */
    public static final String AUTH_ID_ITEMS       = "authids";

    public static final String SELECT_PRIVILEGE    = ISybaseDdlConstants.SELECT_PRIVILEGE;
    public static final String INSERT_PRIVILEGE    = ISybaseDdlConstants.INSERT_PRIVILEGE;
    public static final String UPDATE_PRIVILEGE    = ISybaseDdlConstants.UPDATE_PRIVILEGE;
    public static final String DELETE_PRIVILEGE    = ISybaseDdlConstants.DELETE_PRIVILEGE;
    public static final String ALTER_PRIVILEGE     = ISybaseDdlConstants.ALTER_PRIVILEGE;
    public static final String REFERENCE_PRIVILEGE = ISybaseDdlConstants.REFERENCE_PRIVILEGE;
    public static final String REFERENCES_PRIVILEGE = ISybaseDdlConstants.REFERENCES_PRIVILEGE;
}

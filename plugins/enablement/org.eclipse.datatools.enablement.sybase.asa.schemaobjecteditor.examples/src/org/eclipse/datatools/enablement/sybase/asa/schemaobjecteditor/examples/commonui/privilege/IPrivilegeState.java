/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege;

import org.eclipse.swt.graphics.Image;

/**
 * The state of the privilege
 * 
 * @author Idull
 */
public interface IPrivilegeState
{
    int USER = 0;
    int GROUP = 1;
    int ROLE = 2;

    public static final int             EMPTY_PRIVILEGE                          = 0;
    public static final int             GRANTED_PRIVILEGE                        = 1;
    public static final int             GRANTED_WITH_GRANTOPTION_PRIVILEGE       = 2;
    public static final int             INHERITED_PRIVILEGE                      = 3;
    public static final int             REVOKED_INHERITED_PRIVILEGE              = 4;
    public static final String[]        PRIVILEGES_DISPLAY_NAME                  = new String[]
                                                                                 {
        "", "G", "GO", "I", "RI"
                                                                                 };
    public static final IPrivilegeState EMPTY_PRIVILEGE_STATE                    = new PrivilegeState(
                                                                                         EMPTY_PRIVILEGE,
                                                                                         PRIVILEGES_DISPLAY_NAME[EMPTY_PRIVILEGE]);
    public static final IPrivilegeState GRANTED_PRIVILEGE_STATE                  = new PrivilegeState(
                                                                                         GRANTED_PRIVILEGE,
                                                                                         PRIVILEGES_DISPLAY_NAME[GRANTED_PRIVILEGE]);
    public static final IPrivilegeState GRANTED_WITH_GRANTOPTION_PRIVILEGE_STATE = new PrivilegeState(
                                                                                         GRANTED_WITH_GRANTOPTION_PRIVILEGE,
                                                                                         PRIVILEGES_DISPLAY_NAME[GRANTED_WITH_GRANTOPTION_PRIVILEGE]);
    public static final IPrivilegeState INHERITED_PRIVILEGE_STATE                = new PrivilegeState(
                                                                                         INHERITED_PRIVILEGE,
                                                                                         PRIVILEGES_DISPLAY_NAME[INHERITED_PRIVILEGE]);
    public static final IPrivilegeState REVOKED_INHERITED_PRIVILEGE_STATE        = new PrivilegeState(
                                                                                         REVOKED_INHERITED_PRIVILEGE,
                                                                                         PRIVILEGES_DISPLAY_NAME[REVOKED_INHERITED_PRIVILEGE]);

    public static final IPrivilegeState GROUP_EMPTY_PRIVILEGE_STATE                    = new PrivilegeState(
            EMPTY_PRIVILEGE,
            PRIVILEGES_DISPLAY_NAME[EMPTY_PRIVILEGE], GROUP);
    public static final IPrivilegeState GROUP_GRANTED_PRIVILEGE_STATE                  = new PrivilegeState(
            GRANTED_PRIVILEGE,
            PRIVILEGES_DISPLAY_NAME[GRANTED_PRIVILEGE], GROUP);
    public static final IPrivilegeState GROUP_GRANTED_WITH_GRANTOPTION_PRIVILEGE_STATE = new PrivilegeState(
            GRANTED_WITH_GRANTOPTION_PRIVILEGE,
            PRIVILEGES_DISPLAY_NAME[GRANTED_WITH_GRANTOPTION_PRIVILEGE], GROUP);
    public static final IPrivilegeState GROUP_INHERITED_PRIVILEGE_STATE                = new PrivilegeState(
            INHERITED_PRIVILEGE,
            PRIVILEGES_DISPLAY_NAME[INHERITED_PRIVILEGE], GROUP);
    public static final IPrivilegeState GROUP_REVOKED_INHERITED_PRIVILEGE_STATE        = new PrivilegeState(
            REVOKED_INHERITED_PRIVILEGE,
            PRIVILEGES_DISPLAY_NAME[REVOKED_INHERITED_PRIVILEGE], GROUP);
    
    public static final IPrivilegeState ROLE_EMPTY_PRIVILEGE_STATE                    = new PrivilegeState(
            EMPTY_PRIVILEGE,
            PRIVILEGES_DISPLAY_NAME[EMPTY_PRIVILEGE], ROLE);
    public static final IPrivilegeState ROLE_GRANTED_PRIVILEGE_STATE                  = new PrivilegeState(
            GRANTED_PRIVILEGE,
            PRIVILEGES_DISPLAY_NAME[GRANTED_PRIVILEGE], ROLE);
    public static final IPrivilegeState ROLE_GRANTED_WITH_GRANTOPTION_PRIVILEGE_STATE = new PrivilegeState(
            GRANTED_WITH_GRANTOPTION_PRIVILEGE,
            PRIVILEGES_DISPLAY_NAME[GRANTED_WITH_GRANTOPTION_PRIVILEGE], ROLE);
    public static final IPrivilegeState ROLE_INHERITED_PRIVILEGE_STATE                = new PrivilegeState(
            INHERITED_PRIVILEGE,
            PRIVILEGES_DISPLAY_NAME[INHERITED_PRIVILEGE], ROLE);
    public static final IPrivilegeState ROLE_REVOKED_INHERITED_PRIVILEGE_STATE        = new PrivilegeState(
            REVOKED_INHERITED_PRIVILEGE,
            PRIVILEGES_DISPLAY_NAME[REVOKED_INHERITED_PRIVILEGE], ROLE);
    
    /**
     * Returns the display string of this state
     * 
     * @return
     */
    public String getDisplayString();

    /**
     * Returns the image of this state
     * 
     * @return
     */
    public Image getImage();

    /**
     * Returns the code of this state
     * 
     * @see IPrivilegeState#EMPTY_PRIVILEGE
     * @see IPrivilegeState#GRANTED_PRIVILEGE
     * @see IPrivilegeState#GRANTED_WITH_GRANTOPTION_PRIVILEGE
     * @see IPrivilegeState#INHERITED_PRIVILEGE
     * @see IPrivilegeState#REVOKED_INHERITED_PRIVILEGE
     * @return
     */
    public int getCode();

    /**
     * Returns the authorization identifier type
     * @see USER, ROLE, GROUP
     * 
     * @return
     */
    public int getAuthType();

}

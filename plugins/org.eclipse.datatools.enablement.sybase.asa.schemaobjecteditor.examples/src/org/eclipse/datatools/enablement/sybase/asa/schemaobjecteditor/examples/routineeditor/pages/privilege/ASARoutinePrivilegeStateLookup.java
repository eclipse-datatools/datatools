/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.pages.privilege;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.IPrivilegeState;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.IPrivilegeStateLookup;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.PrivilegeState;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils.PrivilegesUtil;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;


/**
 * 
 * @author Hui Cao
 * 
 */
public class ASARoutinePrivilegeStateLookup implements IPrivilegeStateLookup
{

    public static final int[][] USER_STATE_TRANSITION_ARRAY  = new int[][]
                                                             {
        new int[]
        {
        IPrivilegeState.EMPTY_PRIVILEGE, IPrivilegeState.GRANTED_PRIVILEGE,
        },/* IPrivilegeState.EMPTY_PRIVILEGE */
        new int[]
        {
        IPrivilegeState.EMPTY_PRIVILEGE, IPrivilegeState.GRANTED_PRIVILEGE,
        },/* IPrivilegeState.GRANTED_PRIVILEGE */
        new int[]
        {
            //impossible
        },/* IPrivilegeState.GRANTED_WITH_GRANTOPTION_PRIVILEGE */
        new int[]
        {
            IPrivilegeState.INHERITED_PRIVILEGE
        },/* INHERITED_PRIVILEGE, no action*/
        new int[]
        {
            //impossible
        }
                                                             /* REVOKED_INHERITED_PRIVILEGE */
                                                             };

    public static final int[][] GROUP_STATE_TRANSITION_ARRAY = new int[][]
                                                             {
        new int[]
        {
        IPrivilegeState.EMPTY_PRIVILEGE, IPrivilegeState.GRANTED_PRIVILEGE
        },/* IPrivilegeState.EMPTY_PRIVILEGE */
        new int[]
        {
        IPrivilegeState.EMPTY_PRIVILEGE, IPrivilegeState.GRANTED_PRIVILEGE
        },/* IPrivilegeState.GRANTED_PRIVILEGE */
        new int[]
        {
            //impossible
        },/* IPrivilegeState.GRANTED_WITH_GRANTOPTION_PRIVILEGE */
        new int[]
        {
            IPrivilegeState.INHERITED_PRIVILEGE
        },/* INHERITED_PRIVILEGE, no action*/
        new int[]
        {
            //impossible
        }
                                                             /* REVOKED_INHERITED_PRIVILEGE */
                                                             };


    public IPrivilegeState[] getReachableStates(IPrivilegeState initialState, AuthorizationIdentifier authId)
    {
        return getReachableStates(initialState, initialState.getAuthType());
    }

    /**
     * 
     * @param initialState
     * @param authType
     * @see IPrivilege.USER, IPrivilege.GROUP, IPrivilege.ROLE
     * @return
     */
    public IPrivilegeState[] getReachableStates(IPrivilegeState initialState, int receiverType)
    {
        int[][] states = null;
        if (receiverType == IPrivilegeState.GROUP)
        {
            states = GROUP_STATE_TRANSITION_ARRAY;
        }
        else
        {
            states = USER_STATE_TRANSITION_ARRAY;
        }

        List reachableStates = new ArrayList();

        int initialCode = initialState.getCode();
        if (initialCode < IPrivilegeState.EMPTY_PRIVILEGE || initialCode > IPrivilegeState.REVOKED_INHERITED_PRIVILEGE)
        {
            return new IPrivilegeState[]
            {};
        }
        int[] statesCanReach = states[initialCode];

        for (int i = 0; i < statesCanReach.length; i++)
        {
            reachableStates.add(PrivilegeState.get(statesCanReach[i], receiverType));
        }
        return (IPrivilegeState[]) reachableStates.toArray(new IPrivilegeState[reachableStates.size()]);
    }

    public IPrivilegeState getPrivilegeState(List authids, AuthorizationIdentifier authid, SQLObject sqlObj,
            String action)
    {
        return PrivilegesUtil.getPrivilegeState(authids, authid, sqlObj, action);
    }

    public boolean hasInheritedPrivilege(List authids, AuthorizationIdentifier authid, SQLObject sqlObj, String action)
    {
        return PrivilegesUtil.hasInheritedPrivilege(authids, authid, sqlObj, action);
    }

    public IPrivilegeState[] getReachableStates(IPrivilegeState initialState)
    {
        return getReachableStates(initialState, null);
    }
    
    public Privilege[] getInheritedPrivileges(List authids, AuthorizationIdentifier authid, SQLObject sqlObj,
            String action)
    {
        return PrivilegesUtil.getInheritedPrivilege(authids, authid, sqlObj, action);
    } 
}

/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.pages.privileges;

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
 * Privilege state look up for ASA privielges page
 * 
 * @author Idull
 * 
 */
public class ASAPrivilegeStateLookup implements IPrivilegeStateLookup
{
    public static final int[][] STATIC_STATE_TRANSITION_ARRAY = new int[][]
                                                              {
        new int[]
        {
        0, 1, 2
        }, new int[]
        {
        1, 0, 2
        }, new int[]
        {
        2, 0
        }, new int[]
        {
        0, 1, 2
        }
                                                              };

    public IPrivilegeState[] getReachableStates(IPrivilegeState initialState,AuthorizationIdentifier authid)
    {
        List reachableStates = new ArrayList();

        int initialCode = initialState.getCode();
        if (initialCode < IPrivilegeState.EMPTY_PRIVILEGE || initialCode > IPrivilegeState.INHERITED_PRIVILEGE)
        {
            return new IPrivilegeState[]
            {};
        }
        int[] statesCanReach = STATIC_STATE_TRANSITION_ARRAY[initialCode];

        for (int i = 0; i < statesCanReach.length; i++)
        {
            String displayString = IPrivilegeState.PRIVILEGES_DISPLAY_NAME[statesCanReach[i]];
            reachableStates.add(new PrivilegeState(statesCanReach[i], displayString));
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

    public Privilege[] getInheritedPrivileges(List authids, AuthorizationIdentifier authid, SQLObject sqlObj,
            String action)
    {
        return PrivilegesUtil.getInheritedPrivilege(authids, authid, sqlObj, action);
    } 
}

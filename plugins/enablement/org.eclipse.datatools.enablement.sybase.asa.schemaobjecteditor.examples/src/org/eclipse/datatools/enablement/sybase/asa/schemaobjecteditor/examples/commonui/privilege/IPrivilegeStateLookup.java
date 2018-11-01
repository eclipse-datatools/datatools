/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege;

import java.util.List;

import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * Lookup of privilege state.
 * 
 * @author Idull
 */
public interface IPrivilegeStateLookup
{
    /**
     * Returns the reachable states of the given state
     * 
     * @param initialState
     * @return
     */
    public IPrivilegeState[] getReachableStates(IPrivilegeState initialState, AuthorizationIdentifier authId);

    /**
     * Returns the privileges state of the given authorization identifier (user, group or role) for the given SQL object
     * with action type specified
     * 
     * @param authids all the authorization identifiers
     * @param authid the authorization identifier
     * @param sqlObj SQL object
     * @param action action type
     * @return
     */
    public IPrivilegeState getPrivilegeState(List authids, AuthorizationIdentifier authid, SQLObject sqlObj,
            String action);

    /**
     * Checks if the given authorization identifier (user, group or role) has inherited privilge for the given SQL
     * object with action type specified
     * 
     * @param authids
     * @param authid
     * @param sqlObj
     * @param action
     * @return
     */
    public boolean hasInheritedPrivilege(List authids, AuthorizationIdentifier authid, SQLObject sqlObj, String action);
    
    /**
     * Returns the inherited privileges
     * 
     * @param authids
     * @param authid
     * @param sqlObj
     * @param action
     * @return
     */
    public Privilege[] getInheritedPrivileges(List authids, AuthorizationIdentifier authid, SQLObject sqlObj, String action);
}

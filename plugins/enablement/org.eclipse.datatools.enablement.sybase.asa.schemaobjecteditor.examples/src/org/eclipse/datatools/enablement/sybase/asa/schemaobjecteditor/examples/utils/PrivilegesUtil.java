/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.IPrivilegeState;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.PrivilegeState;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizedObject;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasePrivilege;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Group;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Role;
import org.eclipse.datatools.modelbase.sql.accesscontrol.RoleAuthorization;
import org.eclipse.datatools.modelbase.sql.accesscontrol.User;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Privilege related utilities
 * 
 * @author Idull
 */
public class PrivilegesUtil
{
    /**
     * Returns all the authorization identifiers
     * 
     * @param authid
     * @param obj used to locate the catalog or database
     * @return
     */
    public static List getAuthorizationIdentifiers(SQLObject obj )
    {
        Catalog catalog = ModelUtil.getCatalog(obj);
        List authIds = null;
        //TODO shall we request the base sql model change?
        if (catalog != null)
        {
            EStructuralFeature feature = catalog.eClass().getEStructuralFeature("authorizationIds"); //$NON-NLS-1$
            if (feature != null)
            {
                authIds = ((List) catalog.eGet(feature));
            }
        }
        if (authIds == null || authIds.isEmpty())
        {
            EObject db = ContainmentServiceImpl.INSTANCE.getRootElement(obj);
            if (db instanceof Database)
            {
                authIds = ((Database)db).getAuthorizationIds();
            }
        }
        return authIds;
    }

    /**
     * Returns all privileges of the given SQL object owned by the given authid
     * 
     * @param authid
     * @param obj
     * @return
     */
    public static List getReceivedSQLObjectPrivileges(AuthorizationIdentifier authid, SQLObject obj)
    {
        List matchedPrivileges = new ArrayList();
        List privs = new ArrayList();
        if (obj instanceof SybaseAuthorizedObject)
        {
            privs = ((SybaseAuthorizedObject)obj).getPrivileges();
        }
        else
        {
            privs = authid.getReceivedPrivilege();
        }
        Iterator iter = privs.iterator();
        while (iter.hasNext())
        {
            SybasePrivilege p = (SybasePrivilege) iter.next();
            if (p.getObject() == obj && p.getGrantee() == authid)
            {
                matchedPrivileges.add(p);
            }
        }
        return matchedPrivileges;
    }

    /**
     * Returns the privilege of the given SQL object owned by the given authid with action specified
     * 
     * @param authid
     * @param obj
     * @param action
     * @return a <code>Privilege</code> instance if the user/group/role has, otherwise return <code>null</code>
     */
    public static Privilege getPrivilege(AuthorizationIdentifier authid, SQLObject obj, String action)
    {
        List matchedPrivileges = getReceivedSQLObjectPrivileges(authid, obj);
        Privilege matchedP = null;
        Iterator iter = matchedPrivileges.iterator();
        while (iter.hasNext())
        {
            Privilege p = (Privilege) iter.next();
            if (p.getAction().toLowerCase().equals(action.toLowerCase()))
            {
                matchedP = p;
                break;
            }
        }
        return matchedP;
    }

    public static List getBelongedGroups(User user, List authids)
    {
        List belongedGroups = new ArrayList();
        getBelongedGroups(user, authids, belongedGroups);
        return belongedGroups;
    }

    public static void getBelongedGroups(User user, List authids, List belongedGroups)
    {
        Iterator iter = authids.iterator();
        while (iter.hasNext())
        {
            AuthorizationIdentifier authid = (AuthorizationIdentifier) iter.next();
            if ( authid instanceof Group && !(belongedGroups.contains(authid)))
            {
                Group g = (Group) authid;
                if (g.getUser().contains(user))
                {
                    belongedGroups.add(g);
                    if (g instanceof User)
                    {
                        getBelongedGroups((User)g, authids, belongedGroups);
                    }
                }
            }
        }
    }

    /**
     * Returns the inherited privilege, if no privilege inherited, return null
     * 
     * @param authids
     * @param user
     * @param obj
     * @param action
     * @return
     */
    public static Privilege[] getUserInheritedPrivilege(List authids, User user, SQLObject obj, String action)
    {
        Privilege[] p = getInheritedGroupPrivilege(authids, user, obj, action);
        if (p == null)
        {
            p = getReceivedRolePrivilege(user, obj, action);
        }
        
        return p;
    }

    public static Privilege[] getReceivedRolePrivilege(AuthorizationIdentifier user, SQLObject obj, String action)
    {
        List ps = new ArrayList();
        Privilege p = null;
        List roles = user.getReceivedRoleAuthorization();
        Iterator iter = roles.iterator();
        while (iter.hasNext())
        {
            RoleAuthorization authid = (RoleAuthorization) iter.next();
            p = getPrivilege(authid.getRole(), obj, action);
            if (p != null)
            {
                ps.add(p);
            }
        }
    
        return (Privilege[]) ps.toArray(new Privilege[ps.size()]);
    }

    public static Privilege[] getInheritedGroupPrivilege(List authids, User user, SQLObject obj, String action)
    {
        List ps = new ArrayList();
        Privilege p = null;
        List belongedGroups = getBelongedGroups(user, authids);
        Iterator iter = belongedGroups.iterator();
        while (iter.hasNext())
        {
            AuthorizationIdentifier authid = (AuthorizationIdentifier) iter.next();
            p = getPrivilege(authid, obj, action);
            if (p != null)
            {
                ps.add(p);
            }
        }
        return (Privilege[]) ps.toArray(new Privilege[ps.size()]);
    }

    /**
     * Returns the privilege state. Not consider inherited privilege
     * 
     * @param p
     * @return
     */
    private static int getPrivilegeState(Privilege p)
    {

        if (p == null)
        {
            return IPrivilegeState.EMPTY_PRIVILEGE;
        }

        boolean revoked = false;
        int pState = IPrivilegeState.EMPTY_PRIVILEGE;
        EStructuralFeature feature = p.eClass().getEStructuralFeature("revoked"); //$NON-NLS-1$
        if (feature != null)
        {
            revoked = ((Boolean) p.eGet(feature)).booleanValue();
        }

        if (revoked)
        {
            pState = IPrivilegeState.REVOKED_INHERITED_PRIVILEGE;
        }
        else if (p.isGrantable())
        {
            pState = IPrivilegeState.GRANTED_WITH_GRANTOPTION_PRIVILEGE;
        }
        else
        {
            pState = IPrivilegeState.GRANTED_PRIVILEGE;
        }
        return pState;
    }

    public static boolean hasInheritedPrivilege(List authids, AuthorizationIdentifier authid, SQLObject sqlObj,
            String action)
    {
        Privilege[] ps = getInheritedPrivilege(authids, authid, sqlObj, action);
        return (ps != null && ps.length > 0);
    }

    public static Privilege[] getInheritedPrivilege(List authids, AuthorizationIdentifier authid, SQLObject sqlObj, String action)
    {
        if (!(authid instanceof User))
        {
            Privilege[] ps = getReceivedRolePrivilege(authid, sqlObj, action);
            return ps;
        }
        else
        {
            User user = (User) authid;
            Privilege[] ps = getUserInheritedPrivilege(authids, user, sqlObj, action);
            return ps;
        }
    }
    public static IPrivilegeState getPrivilegeState(List authids, AuthorizationIdentifier authid, SQLObject sqlObj,
            String action)
    {
        Privilege p = getPrivilege(authid, sqlObj, action);
    
        // Calculate the initial state of the privilege
        int pState = IPrivilegeState.EMPTY_PRIVILEGE;

        if (p == null)
        {
            Privilege[] ps = getInheritedPrivilege(authids, authid, sqlObj, action);
            if (ps != null && ps.length > 0)
            {
                pState = IPrivilegeState.INHERITED_PRIVILEGE;
            }
        }
    
        if (pState == IPrivilegeState.EMPTY_PRIVILEGE)
        {
            pState = getPrivilegeState(p);
        }
        
        if(pState == IPrivilegeState.REVOKED_INHERITED_PRIVILEGE)
        {
            if(authid instanceof User)
            {
                User user = (User) authid;
                Privilege[] ps = getInheritedPrivilege(authids, user, sqlObj, action);
                
                if (ps == null || ps.length == 0) 
                {
                    pState = IPrivilegeState.EMPTY_PRIVILEGE;
                }
            }
        }
        int type = IPrivilegeState.USER;
        if (authid instanceof Group)
        {
            type = IPrivilegeState.GROUP;
        }
        else if (authid instanceof Role)
        {
            type = IPrivilegeState.ROLE;
        }
            
        return PrivilegeState.get(pState, type);
    }

    /**
     * Calculates the display string for inherited privileges
     * @param ps
     * @return
     */
    public static String getInheritedPrivilegeDspString(Privilege[] ps)
    {
        if (ps != null && ps.length > 0)
        {
            boolean isGrantable = false;
            for (int i = 0; i < ps.length; i++)
            {
                if (ps[i].isGrantable())
                {
                    isGrantable = true;
                    break;
                }
            }
            String displayStr = "";
            if (isGrantable)
            {
                displayStr = IPrivilegeState.PRIVILEGES_DISPLAY_NAME[IPrivilegeState.GRANTED_WITH_GRANTOPTION_PRIVILEGE];
            }
            else
            {
                displayStr = IPrivilegeState.PRIVILEGES_DISPLAY_NAME[IPrivilegeState.GRANTED_PRIVILEGE];
            }
            displayStr += "(" + IPrivilegeState.INHERITED_PRIVILEGE_STATE.getDisplayString() + ")";
            return displayStr;
        }
        return IPrivilegeState.INHERITED_PRIVILEGE_STATE.getDisplayString();
    }
    
    /**
     * Get the original authorization id from the cloned authorization id
     * @param authid cloned authorization id
     * @param authids original authorization id list
     * @return
     */
    public static AuthorizationIdentifier getOriginalAuthid(AuthorizationIdentifier authid, List authids)
    {
        Iterator iter = authids.iterator();
        while (iter.hasNext())
        {
            AuthorizationIdentifier item = (AuthorizationIdentifier) iter.next();
            if ((item.getName().equals(authid.getName())) && (item.eClass() == authid.eClass()))
            {
                return item;
            }
        }
        return null;
    }
    
    
    /**
     * Get the display string for the given privilege
     * 
     */
    public static String getDisplayString(List authids, AuthorizationIdentifier authid, SQLObject sqlObj, String action, Privilege p)
    {
    	String initialDisplayString = null;
    	IPrivilegeState pState = PrivilegesUtil.getPrivilegeState(authids, authid, sqlObj, action);
    	if(pState.getCode() == IPrivilegeState.INHERITED_PRIVILEGE)
    	{
    		Privilege[] ps = PrivilegesUtil.getInheritedPrivilege(authids, authid, sqlObj, action);
    		initialDisplayString = PrivilegesUtil.getInheritedPrivilegeDspString(ps);
    	}
    	else
    	{
    		initialDisplayString = pState.getDisplayString();
    	}
    	return initialDisplayString;
    }
}

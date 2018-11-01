/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.utils;

import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.PrivilegesConstants;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils.PrivilegesUtil;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizedObject;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasePrivilege;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelFactory;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Group;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.accesscontrol.User;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ddl.IDDLGeneratorWrapper;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.SchemaObjectEditorInput;


/**
 * This util is only to handle some special cases in privilege related execution
 * 
 * @author Hao-yue
 */
public class HandlePrivilegeUtil
{
    /**
     *some group grant/revoke operation may impact the privilege of the members in this group, 
     *so sometimes the UI may be not consistent with the result after execution,
     *in this case we have to add some extra scripts to make the result as expectation
     *
     *@param sb deltaddl to be handled
     *@param clonedObj cloned sql object
     *@param oriObj original sql object
     *@param authids cloned authorized ids
     *@param oriAuthids original authorized ids
     *@param dGeneratorWrapper DDLGeneratorWrapper to get the extra scripts
     *@return
     *
     */
    public static void handlePrivilegeDeltaDdl(StringBuffer sb, SQLObject clonedObj, SQLObject oriObj, List authids, List oriAuthids, IDDLGeneratorWrapper dGeneratorWrapper)
    {        
        Iterator iter = authids.iterator();
        while (iter.hasNext())
        {
            AuthorizationIdentifier authid = (AuthorizationIdentifier) iter.next();
            if (authid instanceof Group)
            {
                List privs = PrivilegesUtil.getReceivedSQLObjectPrivileges(authid, clonedObj);
                Iterator piter = privs.iterator();
                while (piter.hasNext())
                {
                    //after execution, the group has privilege
                    SybasePrivilege p = (SybasePrivilege) piter.next();
                    Privilege oriPriv = PrivilegesUtil.getPrivilege(PrivilegesUtil.getOriginalAuthid(authid, oriAuthids),
                            oriObj, p.getAction());
                    //before execution, the group has no privilege
                    if (oriPriv == null)
                    {
                        Iterator uiter = ((Group) authid).getUser().iterator();
                        while (uiter.hasNext())
                        {
                            User u = (User) uiter.next();
                            Privilege userPriv = PrivilegesUtil.getPrivilege(u, clonedObj, p.getAction());
                            //after execution, the user has no privilege
                            if (userPriv == null)
                            {
                                Privilege oriUserPriv = PrivilegesUtil.getPrivilege(
                                        PrivilegesUtil.getOriginalAuthid(u, oriAuthids), oriObj , p.getAction());
                                //before execution, the user has 'G' privilege
                                if (oriUserPriv != null && !oriUserPriv.isGrantable())
                                {
                                    //add create privilege script to delta ddl
                                    //for CR:471904
                                    String[] ddl = dGeneratorWrapper.getCreateStatementsDDL(new SQLObject[]
                                    {
                                        oriUserPriv
                                    });
                                    sb.append(ddl[1]);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     *when revoking the privilege of a group, we will check if any user in this group have been revoked 'RI' privilege
     *if so, there is no need to remove that 'RI' privilege from model, we will add it to the edit model,
     *otherwise, the result after execution may be out of expectation
     *
     *@param authid the group to be checked
     *@param editor the editor for the sql object
     *@param sqlObj cloned sql object
     *@param action the action of the privilege     
     *@return
     *
     */
    public static void handleRevokedPrivilege(Group authid, ISchemaObjectEditor editor, SQLObject sqlObj, String action)
    {
        SQLObject originalObj = ((SchemaObjectEditorInput)editor.getEditorInput()).getEditModelObject().getSchemaObjectImmutableModel().getMainSQLObject();
        if(sqlObj instanceof Column)
        {
            Iterator cIter = ((Table) originalObj).getColumns().iterator();
            while (cIter.hasNext())
            {
                Column col = (Column) cIter.next();
                if ((col.getName()).equals(sqlObj.getName()))
                {
                    originalObj = col;
                }
            }
        }
        AuthorizationIdentifier orginalAuthid = PrivilegesUtil.getOriginalAuthid(authid, (List) ((SchemaObjectEditorInput)editor.getEditorInput()).getEditModelObject().getSchemaObjectImmutableModel()
                .getAdditionalSQLObjects().get(PrivilegesConstants.AUTH_ID_ITEMS));
        
        List user = ((Group)orginalAuthid).getUser();
        Iterator iter = user.iterator();
        Privilege priv = null;
        Privilege clonedPriv = null;
        while (iter.hasNext())
        {
            User u = (User) iter.next();
            priv = PrivilegesUtil.getPrivilege(u, originalObj, action);
            User clonedUser = (User)((SchemaObjectEditorInput)editor.getEditorInput()).getEditModelObject().getCopier().get(u);
            clonedPriv = PrivilegesUtil.getPrivilege(clonedUser, sqlObj, action);
            if (clonedPriv == null && (priv instanceof SybasePrivilege) && ((SybasePrivilege) priv).isRevoked())
            {
                SybasePrivilege privilege = SybasesqlmodelFactory.eINSTANCE.createSybasePrivilege();
                
                privilege.setObject(sqlObj);
                privilege.setGrantee(clonedUser);
                ((SybasePrivilege) privilege).setRevoked(true);
                privilege.setAction(action);
                clonedUser.getReceivedPrivilege().add(privilege);
                if (sqlObj instanceof SybaseAuthorizedObject)
                {
                    ((SybaseAuthorizedObject) sqlObj).getPrivileges().add(privilege);
                }
            }
        }
    }
}

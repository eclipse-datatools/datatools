/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.pages.privilege;

import java.util.List;

import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.IPrivilegeState;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.IPrivilegeStateLookup;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.PrivilegesConstants;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.PrivilegesDetailPage;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.PrivilegesDetailTableMetaData;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.PrivilegesTreeViewerInput;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.utils.HandlePrivilegeUtil;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils.PrivilegesUtil;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizedObject;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasePrivilege;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelFactory;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Group;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectEditModel;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;


/**
 * 
 * @author Hui Cao
 * 
 */
public class RoutinePrivilegesDetailPage extends PrivilegesDetailPage
{
    private ISchemaObjectEditor _editor;

    public RoutinePrivilegesDetailPage(ISchemaObjectEditor editor, FormToolkit toolkit, ISchemaObjectEditModel model,
            SQLObject sqlObj, PrivilegesDetailTableMetaData meta, PrivilegesTreeViewerInput input,
            IPrivilegeStateLookup stateProvider)
    {
        super(toolkit, model, sqlObj, meta, input, stateProvider);
        _editor = editor;
    }

    protected boolean supportGO(AuthorizationIdentifier authid)
    {
        return false;
    }

    protected boolean supportRevokeGrantOption(AuthorizationIdentifier authid)
    {
        return false;
    }

    public void modify(AuthorizationIdentifier authid, SQLObject sqlObj, String action, Privilege p,
            String selectString, CellEditor currentEditor, Object value)
    {    	
    	String initialDisplayString = PrivilegesUtil.getDisplayString((List) _model.getAdditionalSQLObjects().get(PrivilegesConstants.AUTH_ID_ITEMS), authid, sqlObj, action, p);

    	if (p != null)
        {
            if (selectString.equals(IPrivilegeState.PRIVILEGES_DISPLAY_NAME[0]))// ""
            {
                // if revoke the privilege of a group, we will check if any user in this group have been revoked 'RI'
                // privilege
                // if so, there is no need to remove that 'RI' privilege from model, we will add it to the edit model
                if (authid instanceof Group)
                {
                    HandlePrivilegeUtil.handleRevokedPrivilege((Group) authid, _editor, sqlObj, action);
                }
                authid.getReceivedPrivilege().remove(p);
                if (sqlObj instanceof SybaseAuthorizedObject)
                {
                    ((SybaseAuthorizedObject) sqlObj).getPrivileges().remove(p);
                }
            }
            else if (selectString.equals(IPrivilegeState.PRIVILEGES_DISPLAY_NAME[1]))// G
            {
                p.setGrantable(false);
            }
            else if (selectString.equals(IPrivilegeState.PRIVILEGES_DISPLAY_NAME[2]))// GO
            {
                p.setGrantable(true);
            }
            else if (selectString.indexOf(IPrivilegeState.PRIVILEGES_DISPLAY_NAME[3]) >= 0)// I
            {
                authid.getReceivedPrivilege().remove(p);
                if (sqlObj instanceof SybaseAuthorizedObject)
                {
                    ((SybaseAuthorizedObject) sqlObj).getPrivileges().remove(p);
                }
            }
        }
        else
        {
            if (selectString.equals(IPrivilegeState.PRIVILEGES_DISPLAY_NAME[1]) // G
                    || selectString.equals(IPrivilegeState.PRIVILEGES_DISPLAY_NAME[2]))// GO
            {
                SybasePrivilege privilege = SybasesqlmodelFactory.eINSTANCE.createSybasePrivilege();
                privilege.setObject(sqlObj);
                privilege.setGrantee(authid);
                privilege.setGrantable(selectString.equals(IPrivilegeState.PRIVILEGES_DISPLAY_NAME[2]));
                privilege.setAction(action);
                authid.getReceivedPrivilege().add(privilege);
                if (sqlObj instanceof SybaseAuthorizedObject)
                {
                    ((SybaseAuthorizedObject) sqlObj).getPrivileges().add(privilege);
                }

            }
        }
        if(!initialDisplayString.equals(selectString))
        {
        	markDirty();
        }
    }

    /**
     * Overrides super implementation to notifies the editor about the dirty state
     */
    public void markDirty()
    {
        _editor.markDirty();
        super.markDirty();
    }

}

/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.pages.privileges;

import java.util.List;

import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.ExamplePlugin;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.IPrivilegeState;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.IPrivilegeStateLookup;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.PrivilegesConstants;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.PrivilegesDetailPage;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.PrivilegesDetailTableMetaData;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.PrivilegesTreeViewerInput;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils.PrivilegesUtil;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizedObject;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasePrivilege;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelFactory;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectEditModel;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.forms.widgets.FormToolkit;


public class ASATablePrivilegesDetailPage extends PrivilegesDetailPage
{
    public ASATablePrivilegesDetailPage(FormToolkit toolkit, ISchemaObjectEditModel model, SQLObject sqlObj,
            PrivilegesDetailTableMetaData meta, PrivilegesTreeViewerInput input, IPrivilegeStateLookup stateProvider)
    {
        super(toolkit, model, sqlObj, meta, input, stateProvider);
    }

    private void markDirtyFlag()
    {
        IWorkbenchPage page = ExamplePlugin.getActiveWorkbenchPage();
        IEditorPart part = page.getActiveEditor();
        if(part instanceof ISchemaObjectEditor)
        {
            ((ISchemaObjectEditor)part).markDirty();
        }
    }
    
    public void modify(AuthorizationIdentifier authid, SQLObject sqlObj, String action, Privilege p,
            String selectString, CellEditor currentEditor, Object value)
    {
    	String initialDisplayString = PrivilegesUtil.getDisplayString((List) _model.getAdditionalSQLObjects().get(PrivilegesConstants.AUTH_ID_ITEMS), authid, sqlObj, action, p);
    	
        if (p != null)
        {
            if (selectString.equals(IPrivilegeState.PRIVILEGES_DISPLAY_NAME[IPrivilegeState.EMPTY_PRIVILEGE]))
            {
                authid.getReceivedPrivilege().remove(p);
                if (sqlObj instanceof SybaseAuthorizedObject)
                {
                    ((SybaseAuthorizedObject)sqlObj).getPrivileges().remove(p);
                }
            }
            if (selectString.equals(IPrivilegeState.PRIVILEGES_DISPLAY_NAME[IPrivilegeState.GRANTED_PRIVILEGE]))
            {
                p.setGrantable(false);
            }
            if (selectString
                    .equals(IPrivilegeState.PRIVILEGES_DISPLAY_NAME[IPrivilegeState.GRANTED_WITH_GRANTOPTION_PRIVILEGE]))
            {
                p.setGrantable(true);
            }
            if (selectString.indexOf(IPrivilegeState.PRIVILEGES_DISPLAY_NAME[IPrivilegeState.INHERITED_PRIVILEGE]) >= 0)
            {
                authid.getReceivedPrivilege().remove(p);
                if (sqlObj instanceof SybaseAuthorizedObject)
                {
                    ((SybaseAuthorizedObject)sqlObj).getPrivileges().remove(p);
                }
            }

        }
        else
        {
            if (selectString.equals(IPrivilegeState.PRIVILEGES_DISPLAY_NAME[IPrivilegeState.GRANTED_PRIVILEGE]))
            {
                SybasePrivilege privilege = SybasesqlmodelFactory.eINSTANCE.createSybasePrivilege();
                privilege.setObject(sqlObj);
                privilege.setGrantee(authid);
                privilege.setGrantable(false);
                privilege.setAction(action);
                authid.getReceivedPrivilege().add(privilege);
                if (sqlObj instanceof SybaseAuthorizedObject)
                {
                    ((SybaseAuthorizedObject)sqlObj).getPrivileges().add(privilege);
                }
            }
            if (selectString
                    .equals(IPrivilegeState.PRIVILEGES_DISPLAY_NAME[IPrivilegeState.GRANTED_WITH_GRANTOPTION_PRIVILEGE]))
            {
                SybasePrivilege privilege = SybasesqlmodelFactory.eINSTANCE.createSybasePrivilege();
                privilege.setObject(sqlObj);
                privilege.setGrantee(authid);
                privilege.setGrantable(true);
                privilege.setAction(action);
                authid.getReceivedPrivilege().add(privilege);
                if (sqlObj instanceof SybaseAuthorizedObject)
                {
                    ((SybaseAuthorizedObject)sqlObj).getPrivileges().add(privilege);
                }
            }
        }
        if(!initialDisplayString.equals(selectString))
        {
        	markDirtyFlag();
        }
    }

}

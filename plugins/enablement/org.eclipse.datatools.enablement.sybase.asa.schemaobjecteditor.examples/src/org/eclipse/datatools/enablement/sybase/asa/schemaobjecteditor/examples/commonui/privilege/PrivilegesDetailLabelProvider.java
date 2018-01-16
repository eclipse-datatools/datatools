/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege;

import java.util.List;

import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils.PrivilegesUtil;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils.SybaseImages;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Group;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Role;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectEditModel;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;


/**
 * Label provider for privileges detail table.
 * <p>
 * 
 * @author Idull
 */
public class PrivilegesDetailLabelProvider implements ITableLabelProvider
{
    protected SQLObject                     _sqlObj;
    protected PrivilegesDetailTableMetaData _meta;
    protected ISchemaObjectEditModel        _model;
    protected IPrivilegeStateLookup         _stateLookup;
    
    public PrivilegesDetailLabelProvider(ISchemaObjectEditModel model, SQLObject sqlObj, PrivilegesDetailTableMetaData meta,
            IPrivilegeStateLookup stateLookup)
    {
        super();
        _sqlObj = sqlObj;
        _meta = meta;
        _model = model;
        _stateLookup = stateLookup;
    }

    public Image getColumnImage(Object element, int columnIndex)
    {
        if (columnIndex == 0)
        {
            if (!(element instanceof LeafNode))
            {
                return SybaseImages.get(SybaseImages.IMG_FOLDER);
            }
            else
            {
                LeafNode node = (LeafNode) element;
                if (!(node.getData() instanceof Group) && !(node.getData() instanceof Role))
                {
                    return SybaseImages.get(SybaseImages.IMG_USER);
                }
                else if (node.getData() instanceof Group)
                {
                    return SybaseImages.get(SybaseImages.IMG_GROUP);
                }
                else if (node.getData() instanceof Role)
                {
                    return SybaseImages.get(SybaseImages.IMG_ROLE);
                }
            }
        }
        return null;
    }

    public String getColumnText(Object element, int columnIndex)
    {
        if ((columnIndex != PrivilegesDetailTableMetaData.GRANTEE_COLUMN) && !(element instanceof LeafNode))
        {
            return "";
        }
        AuthorizationIdentifier authid = null;
        if (columnIndex != PrivilegesDetailTableMetaData.GRANTEE_COLUMN)
        {
            authid = (AuthorizationIdentifier) (((LeafNode) element).getData());
            if (authid == null)
            {
                return "";
            }
        }

        if (columnIndex == PrivilegesDetailTableMetaData.GRANTEE_COLUMN)
        {
            if (element instanceof FolderNode)
            {
                FolderNode folder = (FolderNode)element;
                try
                {
                    if (_sqlObj instanceof Column)
                    {
                        Column col = (Column) _sqlObj;
                        if (col.getTable().getSchema().getName().equals(folder.getName()))
                        {
                            return folder.getName() + " (owner)";
                        }
                    }
                    else if (_sqlObj instanceof Table)
                    {
                        Table t = (Table) _sqlObj;
                        if (t.getSchema().getName().equals(folder.getName()))
                        {
                            return folder.getName() + " (owner)";
                        }
                    }
                    else if (_sqlObj instanceof Routine)
                    {
                        Routine r = (Routine) _sqlObj;
                        if (r.getSchema().getName().equals(folder.getName()))
                        {
                            return folder.getName() + " (owner)";
                        }
                    }
                }
                catch (Exception e)
                {
                    
                }
                    
                return ((FolderNode) element).getName();
            }
            return "";
        }
        else
        {
            /**
             * For the current user/group/role, find the privilege state of the current action (select, update, etc.)
             */
            IPrivilegeState state = _stateLookup.getPrivilegeState((List) _model.getAdditionalSQLObjects().get(
                    PrivilegesConstants.AUTH_ID_ITEMS), authid, _sqlObj, _meta.getAction(_meta
                    .getColumnName(columnIndex)));
            if(state.getCode()==IPrivilegeState.INHERITED_PRIVILEGE)
            {
                Privilege[] ps = _stateLookup.getInheritedPrivileges((List) _model.getAdditionalSQLObjects().get(
                    PrivilegesConstants.AUTH_ID_ITEMS), authid, _sqlObj, _meta.getAction(_meta
                    .getColumnName(columnIndex)));
                return PrivilegesUtil.getInheritedPrivilegeDspString(ps);
            }
            return state.getDisplayString();
        }
    }

    public void addListener(ILabelProviderListener listener)
    {

    }

    public void dispose()
    {

    }

    public boolean isLabelProperty(Object element, String property)
    {
        return false;
    }

    public void removeListener(ILabelProviderListener listener)
    {

    }
}

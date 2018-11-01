/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege;

import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Group;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Role;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectEditModel;


/**
 * A default implementation for privileges tree viewer's input.
 * <p>
 * Three folder nodes are defined:
 * 
 * <pre>
 *    Grantee
 *     |
 *     |---Users
 *     |
 *     |---Groups
 *     |
 *     |---Roles (optional)
 * </pre>
 * 
 * 
 * @author Idull
 */
public class PrivilegesTreeViewerInput implements IPrivilegesTreeViewerInput
{
    protected FolderNode             _root;
    protected FolderNode             _granteeFolder;
    protected FolderNode             _usersFolder;
    protected FolderNode             _groupsFolder;
    protected FolderNode             _rolesFolder;
    protected ISchemaObjectEditModel _model;
    protected boolean                _supportRole = false;

    /**
     * All the authorization identifiers
     * 
     * @param authids
     */
    public PrivilegesTreeViewerInput(ISchemaObjectEditModel model)
    {
        super();
        _model = model;
        generateInput();
    }

    /**
     * All the authorization identifiers
     * 
     * @param authids
     */
    public PrivilegesTreeViewerInput(ISchemaObjectEditModel model, boolean supportRole)
    {
        super();
        _model = model;
        _supportRole = supportRole;
        generateInput();
    }

    /**
     * Subclass may extend this method or overide it.
     * 
     */
    protected void generateInput()
    {
        _root = new FolderNode("root"); //$NON-NLS-1$
        _granteeFolder = new FolderNode(Messages.PrivilegesTreeViewerInput_grantee); 
        _root.addChild(_granteeFolder);

        _usersFolder = new FolderNode(Messages.PrivilegesTreeViewerInput_users); 
        _granteeFolder.addChild(_usersFolder);

        _groupsFolder = new FolderNode(Messages.PrivilegesTreeViewerInput_groups); 
        _granteeFolder.addChild(_groupsFolder);

        if (_supportRole)
        {
            _rolesFolder = new FolderNode(Messages.PrivilegesTreeViewerInput_roles); 
            _granteeFolder.addChild(_rolesFolder);
        }
        
        refresh();
    }

    public FolderNode getRoot()
    {
        return _root;
    }

    public FolderNode getGranteeFolder()
    {
        return _granteeFolder;
    }

    public FolderNode getGroupsFolder()
    {
        return _groupsFolder;
    }

    public FolderNode getRolesFolder()
    {
        return _rolesFolder;
    }
    
    public FolderNode getUsersFolder()
    {
        return _usersFolder;
    }

    public void refresh()
    {
    	_usersFolder.getChildren().clear();
        _groupsFolder.getChildren().clear();
        if (_supportRole)
        {
            _rolesFolder.getChildren().clear();
        }

        List authids = (List) _model.getAdditionalSQLObjects().get(PrivilegesConstants.AUTH_ID_ITEMS);
        Iterator iter = authids.iterator();
        while (iter.hasNext())
        {
            AuthorizationIdentifier authid = (AuthorizationIdentifier) iter.next();

            // Since the "group" may be a user, we can not use "(authid instanceof User)"
            if (!(authid instanceof Group) && !(authid instanceof Role))
            {
                _usersFolder.addChild(new LeafNode(authid.getName(), authid));
            }
        }

        iter = authids.iterator();
        while (iter.hasNext())
        {
            AuthorizationIdentifier authid = (AuthorizationIdentifier) iter.next();
            if (authid instanceof Group)
            {
                _groupsFolder.addChild(new LeafNode(authid.getName(), authid));
            }
        }
        
        if(_supportRole)
        {
            iter = authids.iterator();
            while (iter.hasNext())
            {
                AuthorizationIdentifier authid = (AuthorizationIdentifier) iter.next();
                if (authid instanceof Role)
                {
                    _rolesFolder.addChild(new LeafNode(authid.getName(), authid));
                }
            }
        }
    }
}

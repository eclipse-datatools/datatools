/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.profile.NoSuchProfileException;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectEditModel;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectImmutableModel;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorInput;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.extensions.IEditorDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPersistableElement;

/**
 * The editor input for schema object editor.
 * 
 * @author Idull
 */
public class SchemaObjectEditorInput implements ISchemaObjectEditorInput
{
    IEditorDescriptor      _editor;
    ISchemaObjectEditModel _modelObject;
    DatabaseIdentifier     _databaseIdentifier;

    public SchemaObjectEditorInput(IEditorDescriptor editor, ISchemaObjectEditModel modelObject,
            DatabaseIdentifier identifier)
    {
        this._editor = editor;
        this._modelObject = modelObject;
        this._databaseIdentifier = identifier;
    }

    public IEditorDescriptor getEditorDescriptor()
    {
        return _editor;
    }

    public boolean exists()
    {
        return false;
    }

    public ImageDescriptor getImageDescriptor()
    {
        return null;
    }

    public String getName()
    {
        if (_modelObject.getMainSQLObject() != null && _modelObject.getMainSQLObject().getName() != null)
        {
            return _modelObject.getMainSQLObject().getName();
        }
        return Messages.SchemaObjectEditorInput_name;
    }

    public IPersistableElement getPersistable()
    {
        return null;
    }

    public String getToolTipText()
    {
        if (_modelObject != null && _modelObject.getEditorTooltipText() != null)
        {
            return _modelObject.getEditorTooltipText();
        }
        return Messages.SchemaObjectEditorInput_name;
    }

    public Object getAdapter(Class adapter)
    {
        if (adapter == IConnectionProfile.class)
        {
            try
            {
                return ProfileUtil.getProfile(_databaseIdentifier.getProfileName());
            }
            catch (NoSuchProfileException e)
            {
                return null;
            }
        }

        return null;
    }

    public ISchemaObjectEditModel getEditModelObject()
    {
        return _modelObject;
    }

    public DatabaseIdentifier getDatabaseIdentifier()
    {
        return _databaseIdentifier;
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof ISchemaObjectEditorInput))
        {
            return false;
        }
        ISchemaObjectEditorInput input = (ISchemaObjectEditorInput) obj;
        ISchemaObjectImmutableModel originalModel = input.getEditModelObject().getSchemaObjectImmutableModel();
        return originalModel.equals(this.getEditModelObject().getSchemaObjectImmutableModel());
    }
}

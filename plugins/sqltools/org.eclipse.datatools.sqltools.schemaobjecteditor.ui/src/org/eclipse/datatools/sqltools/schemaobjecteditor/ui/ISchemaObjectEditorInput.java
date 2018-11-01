/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui;

import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectEditModel;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.extensions.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;

/**
 * The editor input for scheme object eidtor.
 * 
 * @author Idull
 */
public interface ISchemaObjectEditorInput extends IEditorInput
{
    /**
     * Returns the <code>IEditorDescriptor</code> instance which is used to describe some static information of the
     * schema object editor
     * 
     * @return
     */
    public IEditorDescriptor getEditorDescriptor();

    /**
     * Returns the model of the object which is been editing
     * 
     * @return
     */
    public ISchemaObjectEditModel getEditModelObject();

    /**
     * Returns the database identifier
     * 
     * @return
     */
    public DatabaseIdentifier getDatabaseIdentifier();
}

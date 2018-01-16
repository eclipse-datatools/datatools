/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ddl;

/**
 * Consumers of DDL page need to implement this interface in their model.
 * 
 * @see ISchemaObjectEditorInput.getModelObject()
 * @author Idull
 */
public interface IDDLProvider
{
    /**
     * Returns the DDL
     * 
     * @return
     */
    public String getDDL();
}

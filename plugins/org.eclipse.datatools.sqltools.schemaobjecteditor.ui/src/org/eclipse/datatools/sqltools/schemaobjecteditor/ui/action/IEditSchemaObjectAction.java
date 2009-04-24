/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.action;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * Interface for edit schema object action
 * 
 * @author Hui Wan
 */
public interface IEditSchemaObjectAction
{
    /**
     * Set the object to be opened
     * 
     * @param object Object
     */
    public void setSQLObject(SQLObject object);

    /**
     * Set the default page ID
     * 
     * @param defaultPageId Default page Id
     */
    public void setDefaultPageId(String defaultPageId);
}

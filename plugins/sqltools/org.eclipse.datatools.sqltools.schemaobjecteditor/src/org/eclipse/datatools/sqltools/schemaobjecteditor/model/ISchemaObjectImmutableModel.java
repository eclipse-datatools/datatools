/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.model;

import java.util.Map;

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * The immutable model is used to keep the original status of the schema object which is being edited. <br>
 * Since the model may consist of more than one object, the consumer can divide their model into main object and
 * additional objects. <br>
 * After the <code>refreshFromDB()</code> is called, the main object and additional objects should all be refreshed.
 * 
 * @author Idull
 */
public interface ISchemaObjectImmutableModel
{
    /**
     * Returns the main SQL object referenced by this model, for example, the table
     * 
     * @return
     */
    public SQLObject getMainSQLObject();

    /**
     * Returns the additional SQL objects, for example, the privileges of the table
     * 
     * @return
     */
    public Map getAdditionalSQLObjects();

    /**
     * Refreshes the referenced SQL objects to make them synchronized with DB
     * 
     * @param newName the name of the main object
     */
    public void refreshFromDB(String newName);

    /**
     * Check immutable model existence
     * 
     * @return
     */
    public boolean isModelExist();
}

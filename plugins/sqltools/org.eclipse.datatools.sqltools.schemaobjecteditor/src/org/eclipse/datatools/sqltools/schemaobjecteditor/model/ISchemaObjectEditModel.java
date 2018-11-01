/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.model;

import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

/**
 * The schema object edit model is defined for schema object editor, as part of editor input, this model provides data
 * to the schema object editor, modifications are also stored in this model as well. <br>
 * The underlying SQL model object in this model is a cloned one from <code>SchemaObjectImmutableModel</code>
 * <p>
 * The initial status of the underlying SQL model object is kept in the <code>ISchemaObjectImmutableModel</code>, which
 * is the real SQL model object in the big SQL model instances map.
 * 
 * @author Idull
 */
public interface ISchemaObjectEditModel extends IAdaptable
{
    public static final int REFRESH_SUCCESSFUL           = 0;
    public static final int FATAL_ERROR_MAIN_OBJ_LOST    = 1;
    public static final int ERROR_REFRESH_ADDITIONAL_OBJ = 2;

    /**
     * Reverts the edit model using the immutable model
     * 
     */
    public void revert();

    /**
     * Returns the immutable model of this edit model
     * 
     * @return
     */
    public ISchemaObjectImmutableModel getSchemaObjectImmutableModel();

    /**
     * Refreshes the edit model to make it sychronize with DB, this will be delegated to the immutable model to refresh
     * 
     * @return <code>0</code> if successfully refresh, returns error code otherwise
     * @see #FATAL_ERROR_MAIN_OBJ_LOST
     * @see #ERROR_REFRESH_ADDITIONAL_OBJ
     */
    public int refreshFromDB();

    /**
     * Returns the underlying SQL object
     * 
     * @return
     */
    public SQLObject getMainSQLObject();

    /**
     * Returns the additional SQL objects, the items defined here should has an onte-to-one relationship with those
     * additional SQL objects defined in <code>ISchemaObjectImmutableModel</code>
     * 
     * @return
     */
    public Map getAdditionalSQLObjects();

    /**
     * Returns the copier
     * 
     * @return
     */
    public Copier getCopier();

    /**
     * Returns the delta ddl which will be used to execute
     * 
     * @return
     */
    public String getDeltaDDL();

    /**
     * Starts logging, this can be manually called when the editing is started. And it is called after the model is
     * cloned.
     * 
     */
    public void startLogging();

    /**
     * Stops logging, this should be manually called when the editing is finished
     * 
     */
    public void stopLogging();

    /**
     * Returns the tooltip text for the editor in which this edit model is being edited
     * 
     * @return
     */
    public String getEditorTooltipText();

    /**
     * Check model existence
     */
    public boolean checkModelExistence();
}

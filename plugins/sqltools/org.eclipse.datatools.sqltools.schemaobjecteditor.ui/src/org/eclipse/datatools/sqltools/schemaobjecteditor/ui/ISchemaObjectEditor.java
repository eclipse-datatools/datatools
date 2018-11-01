/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui;

import java.util.Map;

import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.extensions.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;

/**
 * Thru this interface, the consumer can get some run-time object of the editor.
 * 
 * @author Idull
 */
public interface ISchemaObjectEditor extends IResourceChangeHandler, IEditorPart
{

    /**
     * Clear the dirty status of the editor
     * 
     */
    public void clearDirty();

    /**
     * Fires the editor dirty property change event
     * 
     */
    public void fireDirtyPropertyChange();

    /**
     * Returns the active page
     * 
     * @return
     */
    public ISchemaObjectEditorPage getActiveEditorPage();

    /**
     * Returns all the pages currently initialized in this editor
     * 
     * @return
     */
    public ISchemaObjectEditorPage[] getAllPages();

    /**
     * Returns the current page's index
     * 
     * @return
     */
    public int getCurrentPageIndex();

    /**
     * Returns the application data
     * 
     * @return
     */
    public Object getData();

    /**
     * Returns the edit model
     * 
     * @return
     */
    public Object getEditModel();

    /**
     * Returns the handler
     * 
     * @return
     */
    public ISchemaObjectEditorHandler getEditorHandler();

    /**
     * Returns the page with the given id
     * 
     * @param id the id of a page, should be unique for an editor
     * @return
     */
    public ISchemaObjectEditorPage getPageById(String id);

    /**
     * Returns the page with the given name
     * 
     * @param name name of editor pages, we suggest that the name is also unique for an editor, otherwise this method
     *            will only return the first one
     * @return
     */
    public ISchemaObjectEditorPage getPageByName(String name);

    /**
     * Returns the <code>IEditorDescriptor</code> instance containing some static information of this editor
     * 
     * @return
     */
    public IEditorDescriptor getEditorDescriptor();

    /**
     * Mark the editor as dirty status
     * 
     */
    public void markDirty();

    /**
     * Sets the application data
     * 
     * @param _data
     */
    public void setData(Object _data);

    /**
     * Sets the edit model
     * 
     * @param model
     */
    public void setEditModel(Object model);

    /**
     * Sets the part name for the schema object editor
     * 
     * @param name
     */
    public void setEditorPartName(String name);

    /**
     * Validates the editor to see if there are errors
     * 
     * @return
     */
    public Map validate();

    /**
     * Returns the display name of the current edited object (a meaningful name), this will be used for saving as
     * purpose.
     * 
     * @return
     */
    public String getDisplayName();

    /**
     * Sets sync save mode for the next save process
     * 
     */
    public void setSyncSaveMode();

    /**
     * Checks if current save mode is sync save
     */
    public boolean isSyncSave();

    /**
     * Sets if the editor needs to be refreshed after it is saved
     * 
     * @param needRefresh
     */
    public void setNeedRefreshAfterSave(boolean needRefresh);

    /**
     * 
     * @return
     */
    public boolean needRefreshAfterSave();
}

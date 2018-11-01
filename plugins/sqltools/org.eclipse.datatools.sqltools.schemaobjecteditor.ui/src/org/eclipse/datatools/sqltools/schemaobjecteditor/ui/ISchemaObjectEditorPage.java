/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.extensions.IEditorDescriptor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.extensions.IEditorPageDescriptor;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.events.TypedEvent;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.IFormPage;

/**
 * An instance of this class stands for a run-time editor page of a schema object editor
 * 
 * @author Idull
 */
public interface ISchemaObjectEditorPage extends IFormPage, IDisablementPart
{
    public static final int UNKNOWN_ITEM_TYPE = -1;

    /**
     * Returns the error message in this page which prevent to flip to other pages
     * 
     * @return
     */
    public String getErrorMsg();

    /**
     * Returns the database identifier
     * 
     * @return
     */
    public DatabaseIdentifier getDatabaseIdentifier();

    /**
     * Returns an instance of <code>IEditorDescriptor</code> which is used to describe some static information of the
     * scheme object editor in which this page is contained
     * 
     * @return
     */
    public IEditorDescriptor getEditorDescriptor();

    /**
     * Returns an instance of <code>IEditorPageDescriptor</code> which is used to describe some static information of
     * this page
     * 
     * @return
     */
    public IEditorPageDescriptor getPageDescriptor();

    /**
     * Notifies this page that the editor is about to save, subclass should update the model if necessary
     * 
     */
    public boolean aboutToSave(IProgressMonitor monitor);

    /**
     * Refresh the content of this page to the edit model to synchronize this page with the immutable model. Note: This
     * is called after save or revert. @see #setActive
     * 
     */
    public void refresh();

    /**
     * Do something when the model is re-generated, for example, after successfully saving the dirty editor, the edit
     * model will be re-cloned from the refreshed database model, in this case, page should be refreshed to keep it
     * synchronized with database
     * 
     */
    public void modelRegenerated();

    /**
     * Revert this page to the original model
     * 
     */
    public void revert();

    /**
     * Sets the editor in which this paged is contained, this will be called after the schema object is opened. Subclass
     * of <code>SchemaObjectPage</code> should not override this method
     * 
     * @param editor
     */
    public void setEditor(FormEditor editor);

    /**
     * Sets the id for this page
     * 
     * @param id
     */
    public void setId(String id);

    /**
     * Checks if this page is valid
     * 
     * @return the error items if this page is not valid, otherwise return <code>null</code>
     */
    public IErrorItem[] validate(TypedEvent event);

    /**
     * Online check if the page is valid
     * 
     * @param event
     * @return the error items if this page is not valid, otherwise return <code>null</code>
     */
    public IErrorItem[] validateOnline(TypedEvent event);

    /**
     * Populates the SQL object using the user's input
     * 
     * @param event the edit event which changes the input
     */
    public void populateSQLObjects(TypedEvent event);

    /**
     * Validates the page and show error in problems view accordingly
     * 
     * @param event
     */
    public void validateAndShowErrors(TypedEvent event);

    /**
     * Checks if the page is opened (Controls are created)
     * 
     * @return
     */
    public boolean isPageOpened();

    /**
     * Sets the title for this page
     * 
     * @param title
     */
    public void setPartName(String title);

    /**
     * Sets the editor descriptor for this page
     * 
     * @param editor
     */
    public void setEditorDescriptor(IEditorDescriptor editor);

    /**
     * Sets the page descriptor for this page
     * 
     * @param page
     */
    public void setPageDescriptor(IEditorPageDescriptor page);

    /**
     * Marks the parent editor as dirty status
     */
    public void markDirty();

    /**
     * Adds menu items for this page
     * 
     * @param manager
     */
    public void menuAboutToShow(IMenuManager manager);

    /**
     * Returns the preference pages ids related to this editor page.<br>
     * The returns preference pages will be displayed if user click "Preferences..." in the editor page's context menu
     * 
     * @return
     */
    public String[] getPreferencePageIds();

    /**
     * Sets focus to the pre-defined item
     * 
     * @param itemType the type of the item, each page can define its own types
     * @param item the object of the type, it can be <code>null</code> if the item type provides enough information
     */
    public void setFocus(int itemType, Object item);
}

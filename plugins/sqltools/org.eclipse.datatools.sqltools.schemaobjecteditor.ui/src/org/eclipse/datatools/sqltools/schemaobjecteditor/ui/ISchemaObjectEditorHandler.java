/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;

/**
 * Defines the behaviour of the schema object editor, notice that the name of most of the methods defined in this class
 * are the same as those in <code>SchemaObjectEditor</code>, the method in <code>SchemaObjectEditor</code> will delegate
 * the call to the corresponding method in this class.
 * 
 * @author Idull
 */
public interface ISchemaObjectEditorHandler extends IResourceChangeHandler
{

    /**
     * Does something when the editor part is disposing
     * 
     */
    public void dispose();

    /**
     * Saves the content of the editor part.
     * 
     * @param monitor
     */
    public void doSave(IProgressMonitor monitor);

    /**
     * Saves the content of the editor part to another file
     */
    public void doSaveAs();

    /**
     * Generates script for the current editor. This method will be invoked by doSave() to get the script
     * 
     * @return
     */
    public String generateScript();

    /**
     * Returns the adapter. This will be called when the <code>super.getAdapter</code> in
     * <code>SchemaObjectEditor</code> returns null.
     * 
     * @param adapter
     * @return
     */
    public Object getAdapter(Class adapter);

    /**
     * Tests if savesa is allowed for this editor part
     * 
     * @return
     */
    public boolean isSaveAsAllowed();

    /**
     * Does something with the editor when the page is changed. This method will be called before all the obsevers of
     * page change event get notified.
     * 
     * @param newPageIndex
     */
    public void pageChanged(int newPageIndex);

    /**
     * Refreshes the model and then refresh the editor
     * 
     */
    public void refreshFromDB(IProgressMonitor monitor);

    /**
     * Reverts the editor to the original model (no need to refresh the model)
     * 
     */
    public void revert();

    /**
     * Since the instance of this class will be created using the Eclipse extension API, so that we need to set the
     * editor to the hanlder for it to use
     * 
     * @param editor
     */
    public void setEditor(ISchemaObjectEditor editor);

    /**
     * Returns the display name of the current edited object (a meaningful name), this will be used for saving purpose.
     * If nothing is returned, the editor part name will be used for saving purpose
     * 
     * @return
     */
    public String getDisplayName();

    /**
     * Does some initialization jobs, this will be invoked by the editor after all pages are loaded
     * 
     */
    public void hookInitialization();

    /**
     * Returns the editor model listeners notifier. The notifier is registered as the listener of the schema object
     * editor model.
     * 
     * @return
     */
    public SchemaObjectEditorModelListenersNotifier getNotifier();

    /**
     * Checks if the editor is in saving process, since the saving process may take time
     * 
     * @return <code>true</code> if the editor is in saving process
     */
    public boolean inSavingProcess();

    /**
     * Performs a set focus action in schema editor to the given SQL object.<br>
     * Generally there're two steps: 1.Focus the page;2.Focus the UI component of the given object in that page.
     * 
     * @param object
     */
    public void forceFocusObject(SQLObject object);

    /**
     * existence check for schema object.
     * 
     * @param doCheck
     * @return <code>true</code> if object exists or the doCheck is passed with value false.
     */
    public boolean checkSchemaObjectExistence(boolean doCheck);
}

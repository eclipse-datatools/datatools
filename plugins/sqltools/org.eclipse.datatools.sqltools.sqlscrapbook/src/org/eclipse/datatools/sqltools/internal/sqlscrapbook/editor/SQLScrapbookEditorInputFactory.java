/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.SQLEditorFileEditorInput;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorInputFactory;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.FileEditorInputFactory;


/** 
 * The class implements a factory which is capable of recreating SQL Editor file editor
 * inputs stored in a memento.
 *
 * @author bgp
 */
public class SQLScrapbookEditorInputFactory extends SQLEditorInputFactory implements IElementFactory {

    public final static String ID_FACTORY =  "org.eclipse.datatools.sqltools.internal.sqlscrapbook.editor.SQLScrapbookEditorInputFactory"; //$NON-NLS-1$
    public final static String ID_FILE_EDITOR_INPUT = "SQLScrapbookEditorInput"; //$NON-NLS-1$
    
    /**
     * Re-creates and returns an object from the state captured within the given 
     * memento.
     *  
     * @see org.eclipse.ui.IElementFactory#createElement(org.eclipse.ui.IMemento)
     */
    public IAdaptable createElement( IMemento memento ) {
        IAdaptable input = null;
        
        // Get the editor input type from the memento.
        String editorInputType = memento.getString( KEY_EDITOR_INPUT_TYPE );
        
        // Process a file editor input.
        if (editorInputType.equals (ID_FILE_EDITOR_INPUT)) {
            // Create a FileEditorInput from the memento.  We will use the file
            // from it to create the SQLEditorFileEditorInput that we want to return.
            FileEditorInputFactory fileInputFactory = new FileEditorInputFactory();
            IAdaptable adaptable = fileInputFactory.createElement( memento );
            if (adaptable instanceof FileEditorInput) {
                FileEditorInput fileInput = (FileEditorInput) adaptable;
                
                // Get the file resource from the FileEditorInput.
                IFile fileResource = fileInput.getFile();
                if (fileResource != null) {
                    // Create the SQLEditorFileEditorInput.
                    SQLScrapbookEditorInput sqlFileInput = new SQLScrapbookEditorInput( fileResource );
                    
                    // Get the connection name from the memento, and use that to
                    // get the ISQLEditorConnectionInfo using the ConnectionManager.  Put the
                    // ISQLEditorConnectionInfo into the editor input object.
                    ISQLEditorConnectionInfo connInfo = null;
                    String connInfoCode = memento.getString(KEY_CONN_INFO_CODE);
                    if (connInfoCode != null && sqlFileInput.getConnectionInfo() == null) {
                        connInfo = SQLEditorConnectionInfo.decode(connInfoCode);
                        sqlFileInput.setConnectionInfo( connInfo );
                    }
                    
                    input = sqlFileInput;
                }
            }
        }
        else
        {
            input = super.createElement(memento);
        }

        return input; 
    }

    /**
     * Saves the state of the given file editor input object in the given memento.
     * 
     * @param memento the storage area for object's state
     * @param input the file editor input object that needs to be saved
     */
    public static void saveState(IMemento memento, SQLEditorFileEditorInput input) {
        // Save the editor input type.
        memento.putString( KEY_EDITOR_INPUT_TYPE, ID_FILE_EDITOR_INPUT );
        
        // Get the factory of the superclass to save the File part of the input.
        FileEditorInputFactory.saveState( memento, input );
        
        // Save the connection name in the memento.
        ISQLEditorConnectionInfo connInfo = input.getConnectionInfo();
        if (connInfo != null) {
            String connInfoCode = connInfo.encode(); 
            memento.putString(KEY_CONN_INFO_CODE, connInfoCode );
        }
        
    }
    
}

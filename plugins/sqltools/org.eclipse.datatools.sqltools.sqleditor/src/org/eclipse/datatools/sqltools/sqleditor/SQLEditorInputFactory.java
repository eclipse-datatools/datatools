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
package org.eclipse.datatools.sqltools.sqleditor;

import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;


/** 
 * The class implements a factory which is capable of recreating SQL Editor file editor
 * inputs stored in a memento.
 *
 * @author bgp
 */
public class SQLEditorInputFactory implements IElementFactory {

    public final static String ID_FACTORY =  "org.eclipse.datatools.sqltools.sqleditor.SQLEditorInputFactory"; //$NON-NLS-1$
    public final static String ID_STORAGE_EDITOR_INPUT = "SQLEditorStorageEditorInput"; //$NON-NLS-1$
    
    public final static String KEY_CONN_INFO_CODE = "connInfoName"; //$NON-NLS-1$
    public final static String KEY_DEFAULT_SCHEMA_NAME = "defaultSchemaName"; //$NON-NLS-1$
    public final static String KEY_EDITOR_INPUT_TYPE = "editorInputType"; //$NON-NLS-1$ 
    public final static String KEY_STORAGE_CONTENT = "storageContent"; //$NON-NLS-1$
    public final static String KEY_STORAGE_NAME = "storageName"; //$NON-NLS-1$

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
        
        // Process a storage editor input
        if (editorInputType.equals (ID_STORAGE_EDITOR_INPUT)) {
            // Create a Storage object from the memento.
            String contentName = memento.getString( KEY_STORAGE_NAME );
            String contentString = memento.getString( KEY_STORAGE_CONTENT );
            SQLEditorStorage storage = new SQLEditorStorage( contentName, contentString );
            
            // Create a SQLEditorStorageEditorInput from the storage we just created.
            SQLEditorStorageEditorInput sqlStorageInput = new SQLEditorStorageEditorInput( storage );
            
            // Get the connection name from the memento, and use that to
            // get the ISQLEditorConnectionInfo using the ConnectionManager.  Put the
            // ISQLEditorConnectionInfo into the editor input object.
            ISQLEditorConnectionInfo connInfo = null;
            String connInfoCode = memento.getString(KEY_CONN_INFO_CODE);
            if (connInfoCode != null) {
            	connInfo = SQLEditorConnectionInfo.decode(connInfoCode);
                sqlStorageInput.setConnectionInfo( connInfo );
            }

            input = sqlStorageInput;
        }

        return input; 
    }

    /**
     * Saves the state of the given storage editor input object in the given memento.
     * 
     * @param memento the storage area for object's state
     * @param input the storage editor input object that needs to be saved
     */
    public static void saveState(IMemento memento, SQLEditorStorageEditorInput input) {
        // Save the editor input type.
        memento.putString( KEY_EDITOR_INPUT_TYPE, ID_STORAGE_EDITOR_INPUT );
        
        String storageName = null;
        String storageContent = ""; //$NON-NLS-1$
        
        IStorage storage = input.getStorage();
        if (storage != null) {
            storageName = storage.getName();
            if (storage instanceof SQLEditorStorage) {
                SQLEditorStorage sqlEditorStorage = (SQLEditorStorage) storage;
                storageContent = sqlEditorStorage.getContentsString();
            }
        }
     
        // Save the storage content name in the memento.
        memento.putString( KEY_STORAGE_NAME, storageName );
        
        // Save the storage content string in the memento.
        memento.putString( KEY_STORAGE_CONTENT, storageContent );
        
        // Save the connection name in the memento.
        ISQLEditorConnectionInfo connInfo = input.getConnectionInfo();
        if (connInfo != null) {
            String connInfoCode = connInfo.encode(); 
            memento.putString( KEY_CONN_INFO_CODE, connInfoCode );
        }
        
    }
}

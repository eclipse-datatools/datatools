/*******************************************************************************
 * Copyright © 2000, 2007 Sybase, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.input;

import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.IOmitSchemaInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.OmitSchemaInfo;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorStorage;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;


/** 
 * The class implements a factory which is capable of saving and recreating SQLBuilderStorageEditorInputs
 * stored in a memento. 
 *
 * @author Jeremy Lindop
 */
public class SQLBuilderInputFactory implements IElementFactory {

	/**
	 * The Factory ID.
	 */
    public final static String ID_SQL_BUILDER_STORAGE_EDITOR_INPUT_FACTORY =  "org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderInputFactory"; //$NON-NLS-1$
    /**
     * The InputType
     */
    public final static String ID_SQL_BUILDER_STORAGE_EDITOR_INPUT_TYPE = "SQLBuilderStorageEditorInput"; //$NON-NLS-1$
    /**
     * The root element name used in <code>XMLMemento</code> objects representing
     * <code>SQLBuilderStorageEditorInput</code> objects. 
     */
    public final static String ID_XML_MEMENTO_ROOT_ELEMENT = "SQLQueryBuilder";
    /**
     * Key used for EditorInputType property.
     */
    public final static String KEY_EDITOR_INPUT_TYPE = "editorInputType"; //$NON-NLS-1$ 
    /**
     * Key used for StorageName property.
     */
    public final static String KEY_STORAGE_NAME = "name"; //$NON-NLS-1$
    /**
     * Key used for Version property.
     */
    public final static String KEY_STORAGE_VERSION = "version"; //$NON-NLS-1$

   /**
     * Key used for SQLStatement element.
     */
    public final static String KEY_STORAGE_SQLCONTENT = "SQLStatement"; //$NON-NLS-1$
    /**
     * Key used for ConnectionInfo element.
     */
    public final static String KEY_CONN_INFO_CODE = "connInfo"; //$NON-NLS-1$
    /**
     * Key used for OmitSchemaInfo element.
     */
    public final static String KEY_OMIT_SCHEMA_INFO_CODE = "omitSchemaInfo"; //$NON-NLS-1$

    /**
     * Re-creates and returns an object from the state captured within the given 
     * memento. Returns a SQLBuilderStorageEditorInput.
     *  
     * @see org.eclipse.ui.IElementFactory#createElement(org.eclipse.ui.IMemento)
     */
    public IAdaptable createElement( IMemento memento ) {
        IAdaptable input = null;
        
        // Get the editor input type from the memento.
        String editorInputType = memento.getString( KEY_EDITOR_INPUT_TYPE );
        
        // Process a storage editor input
        if (editorInputType.equals (ID_SQL_BUILDER_STORAGE_EDITOR_INPUT_TYPE)) {
            // Create a Storage object from the memento.
            String contentName = memento.getString( KEY_STORAGE_NAME );
            IMemento sqlStmtChild = memento.getChild(KEY_STORAGE_SQLCONTENT);
            String contentSQLStatement = "";
            if (sqlStmtChild != null){
            	contentSQLStatement = sqlStmtChild.getTextData();
            }
            SQLEditorStorage storage = new SQLEditorStorage( contentName, contentSQLStatement );
            
            // Create a SQLBuilderStorageEditorInput from the storage we just created.
            SQLBuilderStorageEditorInput sqlStorageInput = new SQLBuilderStorageEditorInput( storage );
            
            // Get the connectionInfo from the memento and put the
            // ISQLEditorConnectionInfo into the editor input object.
            IMemento connInfoChild = memento.getChild(KEY_CONN_INFO_CODE);
            if (connInfoChild != null){
	            String connInfoCode = connInfoChild.getTextData();
	            if (connInfoCode != null) {
	                ISQLEditorConnectionInfo connInfo = null;
	            	connInfo = SQLEditorConnectionInfo.decode(connInfoCode);
	                sqlStorageInput.setConnectionInfo( connInfo );
	            }
            }

            // Get the OmitSchemaInfo name from the memento and put the
            // OmitSchemaInfo into the editor input object.
            IOmitSchemaInfo omitSchemaInfo = null;
            IMemento omitSchemaInfoChild = memento.getChild(KEY_OMIT_SCHEMA_INFO_CODE);
            if (omitSchemaInfoChild != null){
            	String omitSchemaInfoCode = omitSchemaInfoChild.getTextData();
            	if (omitSchemaInfoCode != null) {
            		omitSchemaInfo = OmitSchemaInfo.decode(omitSchemaInfoCode);
            		sqlStorageInput.setOmitSchemaInfo(omitSchemaInfo);
            	}
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
    public static void saveState(IMemento memento, SQLBuilderStorageEditorInput input) {
        // Save the editor input type.
        memento.putString( KEY_EDITOR_INPUT_TYPE, ID_SQL_BUILDER_STORAGE_EDITOR_INPUT_TYPE );
        
        // Get the name from the storage object
        String storageName = null;
        IStorage storage = input.getStorage();
        if (storage != null) {
            storageName = storage.getName();
        }
     
        // Save the storage content name in the memento
        memento.putString( KEY_STORAGE_NAME, storageName );
        
        // Save the version in the memento
        memento.putString(KEY_STORAGE_VERSION, SQLBuilderStorageEditorInput.CURRENT_VERSION);
        
        // Get the SQL Statement from the input
        String storageContent = input.getSQL();

        // Save the storage content string, i.e. the SQL statement
        // in the memento as a <KEY_STORAGE_CONTENT> element
        IMemento sqlStmtChild = memento.createChild(KEY_STORAGE_SQLCONTENT);
        sqlStmtChild.putTextData(storageContent);
               
        // Save the connection into in the memento as a <KEY_CONN_INFO_CODE> element.
        ISQLEditorConnectionInfo connInfo = input.getConnectionInfo();
        if (connInfo != null) {
            String connInfoCode = connInfo.encode(); 
            IMemento connInfoChild = memento.createChild(KEY_CONN_INFO_CODE);
            connInfoChild.putTextData(connInfoCode);
        }
        
        // Save the omit schema info in the memento as a <KEY_CONN_INFO_CODE> element
        IOmitSchemaInfo omitSchemaInfo = input.getOmitSchemaInfo();
        if (omitSchemaInfo != null) {
            String omitSchemaInfoCode = omitSchemaInfo.encode(); 
            IMemento omitSchemaInfoChild = memento.createChild(KEY_OMIT_SCHEMA_INFO_CODE);
            omitSchemaInfoChild.putTextData(omitSchemaInfoCode);
        }
        
    }
}

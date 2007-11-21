/*******************************************************************************
 * Copyright © 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.input;

import org.eclipse.core.resources.IStorage;
import org.eclipse.datatools.sqltools.sqlbuilder.model.IOmitSchemaInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.model.OmitSchemaInfo;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorStorage;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorStorageEditorInput;
import org.eclipse.ui.IMemento;

/**
 * This class implements the <code>ISQLBuilderEditorInput</code> interface on a
 * <code>IStorageEditorInput</code> base.  In addition, this class contains 
 * fields and methods that are specifically for launching the SQL Builder.  This class  
 * is provided as a convenience for callers of the SQL Builder who want to open the 
 * SQL Builder on an input that isn't a file.  
 */
public class SQLBuilderStorageEditorInput extends SQLEditorStorageEditorInput 
        implements ISQLBuilderEditorInput {

	/** Current version identifier */
	public static final String CURRENT_VERSION = "1.0";
	
	/** Contains OmitSchemaInfo associated with this object. */
    private IOmitSchemaInfo fOmitSchemaInfo = null;
    
    /**
     * Constructs an instance of this class with the given string as the editor
     * input source.
     * 
     * @param storageSource the editor input source string, i.e. the SQL statement
     */
    public SQLBuilderStorageEditorInput(String name, String storageSource) {
        super(name, storageSource);
    }

    /**
     * Constructs an instance of this class with the given <code>IStorage</code>
     * object as the editor input source.
     * 
     * @param storage the storage object for this editor input
     */
    public SQLBuilderStorageEditorInput( IStorage storage ) {
        super( storage );
    }
   
	/**
	 * Gets the <code>SQLStatement</code> associated with this input.
	 * 
	 * @return the current <code>SQLStatement</code>
	 */
	public String getSQL() {
		SQLEditorStorage storage = (SQLEditorStorage)getStorage();
		return storage.getContentsString();
	}
   
	/**
	 * Sets the <code>IOmitSchemaInfo</code> associated with this input to the given 
	 * object.
	 * 
	 * @param omitSchemaInfo the <code>IOmitSchemaInfo</code> object to set
	 */
	public void setOmitSchemaInfo( IOmitSchemaInfo omitSchemaInfo ) {
		if (omitSchemaInfo == null)
    	{
			fOmitSchemaInfo = new OmitSchemaInfo();
			fOmitSchemaInfo.initFromPreferences();
    	}
		else
		{
			fOmitSchemaInfo = omitSchemaInfo;
		}
	}

	
	/**
	 * Gets the <code>IOmitSchemaInfo</code> associated with this input.
	 * 
	 * @return the current <code>IOmitSchemaInfo</code> object
	 */
	public IOmitSchemaInfo getOmitSchemaInfo() {
		return fOmitSchemaInfo;
	}

    /**
     * Returns the id of the element factory which should be used to re-create this
     * object.
     * 
     * @see org.eclipse.ui.IPersistableElement#getFactoryId()
     */
    public String getFactoryId() {
        return SQLBuilderInputFactory.ID_SQL_BUILDER_STORAGE_EDITOR_INPUT_FACTORY;
    }
    
    /**
     * Saves the state of the object in the given memento.
     * 
     * @param memento the storage area for object's state
     * @see org.eclipse.ui.IPersistableElement#saveState(org.eclipse.ui.IMemento)
     */
    public void saveState(IMemento memento) {
    	SQLBuilderInputFactory.saveState( memento, this );
    }

	
}

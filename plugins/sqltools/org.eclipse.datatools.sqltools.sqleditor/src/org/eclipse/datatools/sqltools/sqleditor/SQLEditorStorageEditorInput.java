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
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IStorageEditorInput;

/**
 * This class implements the <code>ISQLEditorInput</code> interface on a
 * <code>IStorageEditorInput</code> base.  It is provided as a convenience to 
 * callers of the SQL Editor who want to open the editor on an input that 
 * isn't a file.
 */
public class SQLEditorStorageEditorInput implements IStorageEditorInput, ISQLEditorInput, IPersistableElement {

    /** The connection information for this SQL editor input. */
    private ISQLEditorConnectionInfo fConnInfo;
    /** The name of ths editor input. */
    private String fName;
    /** The storage object used by this editor input. */
    private IStorage fStorage;

    /**
     * Constructs an instance of this class with the given string as the editor
     * input source.
     * 
     * @param storageSource the editor input source string
     */
    public SQLEditorStorageEditorInput(String name, String storageSource ) {
        this( new SQLEditorStringStorage( name, storageSource ) );
    }

    /**
     * Constructs an instance of this class with the given <code>IStorage</code>
     * object as the editor input source.
     * 
     * @param storage the storage object for this editor input
     */
    public SQLEditorStorageEditorInput( IStorage storage ) {
		if (storage == null) {
			throw new IllegalArgumentException();
        }
        setStorage( storage );
        setName( storage.getName() );
        setConnectionInfo( null );
    }    

    /**
     * Gets whether the editor input exists.
     * 
     * @see org.eclipse.ui.IEditorInput#exists()
     */
    public boolean exists() {
        if (fStorage != null)
            return true;
        
        return false;
    }

    /**
     * Gets an object which is an instance of the given class associated with
     * this object. Returns <code>null</code> if no such object can be found.
     * This default implementation returns null.
     * 
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     */
    public Object getAdapter( Class adapter ) {
        return null;
    }

    /**
     * Gets the <code>ISQLEditorConnectionInfo</code> object for this editor input.
     * 
     * @return the current <code>ISQLEditorConnectionInfo</code> object
     */
    public ISQLEditorConnectionInfo getConnectionInfo() {
        return fConnInfo;
    }

    /**
     * Gets the image descriptor for this input.
     * 
     * @see org.eclipse.ui.IEditorInput#getImageDescriptor()
     */
    public ImageDescriptor getImageDescriptor() {
        return null;
    }

    /**
     * Gets the name of this editor input for display purposes.
     * 
     * @see org.eclipse.ui.IEditorInput#getName()
     */
    public String getName() {
        return fName;
    }

    /**
     * Gets an object that can be used to save the state of this editor input.
     * 
     * @see org.eclipse.ui.IEditorInput#getPersistable()
     */
    public IPersistableElement getPersistable() {
        //return this;
        return null;
    }

    /**
     * Gets the underlying <code>IStorage</code> object. The default storage
     * object is implemented as a <code>InputStream</code>.)
     * 
     * @see org.eclipse.ui.IStorageEditorInput#getStorage()
     */
    public IStorage getStorage() {
        return fStorage;
    }

    /**
     * Gets the tool tip text for this editor input.
     * 
     * @see org.eclipse.ui.IEditorInput#getToolTipText()
     */
    public String getToolTipText() {        
        StringBuffer sb = new StringBuffer(30);
        if (fConnInfo != null && fConnInfo.getConnectionProfile() != null) {
        	sb.append(fConnInfo.getConnectionProfile().getName());
            sb.append("/");
        }
        sb.append(fName);
        return sb.toString();        
    }

    /**
     * Returns the id of the element factory which should be used to re-create this
     * object.
     * 
     * @see org.eclipse.ui.IPersistableElement#getFactoryId()
     */
    public String getFactoryId() {
        return SQLEditorInputFactory.ID_FACTORY;
    }

    /**
     * Saves the state of the object in the given memento.
     * 
     * @param memento the storage area for object's state
     * @see org.eclipse.ui.IPersistableElement#saveState(org.eclipse.ui.IMemento)
     */
    public void saveState(IMemento memento) {
        SQLEditorInputFactory.saveState( memento, this );
    }

    /**
     * Sets the <code>ISQLEditorConnectionInfo</code> object for this editor input to
     * the given object.
     * 
     * @param connInfo the <code>ISQLEditorConnectionInfo</code> object to set
     */
    public void setConnectionInfo( ISQLEditorConnectionInfo connInfo ) {
        fConnInfo = connInfo;
    }

    /**
     * Sets the name of this editor input to the given name.
     * 
     * @param name the name to set
     */
    public void setName( String name ) {
        fName = name;
    }

    /**
     * Sets the underlying <code>IStorage</code> object.
     * 
     * @param storage the storage object to use
     */
    public void setStorage( IStorage storage ) {
        fStorage = storage;
    }

    public boolean isConnectionRequired()
    {
        return false;
    }

    public String getId()
    {
        return getClass().getName() + "(" + fName + ")";
    }

} // end class

/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.externalfile;

import java.io.InputStream;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorInput;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorInputFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IStorageEditorInput;
import org.eclipse.ui.editors.text.ILocationProvider;
import org.eclipse.ui.model.IWorkbenchAdapter;

/**
 * 
 * @author Hui Cao
 * 
 */
public class ExternalSQLFileEditorInput implements IPathEditorInput, IStorageEditorInput, 
                                                   ILocationProvider, ISQLEditorInput {
    /**
     * The workbench adapter which simply provides the label.
     *
     * @since 3.1
     */
    private class WorkbenchAdapter implements IWorkbenchAdapter {
        /*
         * @see org.eclipse.ui.model.IWorkbenchAdapter#getChildren(java.lang.Object)
         */
        public Object[] getChildren(Object o) {
            return null;
        }

        /*
         * @see org.eclipse.ui.model.IWorkbenchAdapter#getImageDescriptor(java.lang.Object)
         */
        public ImageDescriptor getImageDescriptor(Object object) {
            return null;
        }

        /*
         * @see org.eclipse.ui.model.IWorkbenchAdapter#getLabel(java.lang.Object)
         */
        public String getLabel(Object o) {
            return ((ExternalSQLFileEditorInput)o).getName();
        }

        /*
         * @see org.eclipse.ui.model.IWorkbenchAdapter#getParent(java.lang.Object)
         */
        public Object getParent(Object o) {
            return null;
        }
    }
    
    private class SQLEditorStorage implements IStorage {

        private IFileStore fFileStore;
        private IPath fFullPath;
        
        public SQLEditorStorage(IFileStore fileStore) {
            Assert.isNotNull(fileStore);
            Assert.isTrue(EFS.SCHEME_FILE.equals(fileStore.getFileSystem().getScheme()));
            fFileStore= fileStore;
        }
        
        /*
         * @see org.eclipse.core.resources.IStorage#getContents()
         */
        public InputStream getContents() throws CoreException {
            return fFileStore.openInputStream(EFS.NONE, null);
        }

        /*
         * @see org.eclipse.core.resources.IStorage#getFullPath()
         */
        public IPath getFullPath() {
            if (fFullPath == null)
                fFullPath= new Path(fFileStore.toURI().getPath());
            return fFullPath;
        }

        /*
         * @see org.eclipse.core.resources.IStorage#getName()
         */
        public String getName() {
            return fFileStore.getName();
        }

        /*
         * @see org.eclipse.core.resources.IStorage#isReadOnly()
         */
        public boolean isReadOnly() {
            return fFileStore.fetchInfo().getAttribute(EFS.ATTRIBUTE_READ_ONLY);
        }

        /*
         * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
         */
        public Object getAdapter(Class adapter) {
            return null;
        }
    }
    
    private IFileStore fFileStore;
    private WorkbenchAdapter fWorkbenchAdapter= new WorkbenchAdapter();
    private IStorage fStorage;
    private IPath fPath;
    
    /** Contains connection information associated with this object. */
    private ISQLEditorConnectionInfo fConnInfo;
    
    public ExternalSQLFileEditorInput(IFileStore fileStore) {
        Assert.isNotNull(fileStore);
        Assert.isTrue(EFS.SCHEME_FILE.equals(fileStore.getFileSystem().getScheme()));
        fFileStore= fileStore;
        fWorkbenchAdapter= new WorkbenchAdapter();
        setConnectionInfo(SQLEditorConnectionInfo.DEFAULT_SQLEDITOR_CONNECTION_INFO);
    }

    /*
     * @see org.eclipse.ui.IEditorInput#exists()
     */
    public boolean exists() {
        return fFileStore.fetchInfo().exists();
    }

    /*
     * @see org.eclipse.ui.IEditorInput#getImageDescriptor()
     */
    public ImageDescriptor getImageDescriptor() {
        return null;
    }

    /*
     * @see org.eclipse.ui.IEditorInput#getName()
     */
    public String getName() {
        return fFileStore.getName();
    }

    /*
     * @see org.eclipse.ui.IEditorInput#getPersistable()
     */
    public IPersistableElement getPersistable() {
        return null;
    }

    /*
     * @see org.eclipse.ui.IEditorInput#getToolTipText()
     */
    public String getToolTipText() {
        return fFileStore.toString();
    }

    /*
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     */
    public Object getAdapter(Class adapter) {
        if (ILocationProvider.class.equals(adapter))
            return this;
        if (IWorkbenchAdapter.class.equals(adapter))
            return fWorkbenchAdapter;
        return Platform.getAdapterManager().getAdapter(this, adapter);
    }

    /*
     * @see org.eclipse.ui.editors.text.ILocationProvider#getPath(java.lang.Object)
     */
    public IPath getPath(Object element) {
        if (element instanceof ExternalSQLFileEditorInput)
            return ((ExternalSQLFileEditorInput)element).getPath();
        
        return null;
    }

    /*
     * @see org.eclipse.ui.IPathEditorInput#getPath()
     * @since 3.1
     */
    public IPath getPath() {
        if (fPath == null)
            fPath= new Path(fFileStore.toURI().getPath());
        return fPath;
    }

    /*
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (o instanceof ExternalSQLFileEditorInput) {
            ExternalSQLFileEditorInput input= (ExternalSQLFileEditorInput) o;
            return fFileStore.equals(input.fFileStore);
        }

        if (o instanceof IPathEditorInput) {
            IPathEditorInput input= (IPathEditorInput)o;
            return getPath().equals(input.getPath());
        }

        return false;
    }

    /*
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return fFileStore.hashCode();
    }

    /*
     * @see org.eclipse.ui.IStorageEditorInput#getStorage()
     * @since 3.2
     */
    public IStorage getStorage() throws CoreException {
        if (fStorage == null)
            fStorage= new SQLEditorStorage(fFileStore);
        return fStorage;
    }
    
    /**
     * Gets the <code>ISQLEditorConnectionInfo</code> associated with this input.
     * 
     * @return the current <code>ISQLEditorConnectionInfo</code> object
     */
    public ISQLEditorConnectionInfo getConnectionInfo() {
        return fConnInfo;
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
     * Sets the <code>ISQLEditorConnectionInfo</code> associated with this input to the given 
     * object.
     * 
     * @param connInfo the <code>ISQLEditorConnectionInfo</code> object to set
     */
    public void setConnectionInfo( ISQLEditorConnectionInfo connInfo ) {
        //the connection info must not be null
        if (connInfo == null)
        {
            fConnInfo = SQLEditorConnectionInfo.DEFAULT_SQLEDITOR_CONNECTION_INFO;
        }
        else
        {
            fConnInfo = connInfo;
        }
    }

    public boolean isConnectionRequired()
    {
        return false;
    }

    public String getId()
    {
        return getClass().getName() + "(" + getPath().toString() + ")";
    }
}

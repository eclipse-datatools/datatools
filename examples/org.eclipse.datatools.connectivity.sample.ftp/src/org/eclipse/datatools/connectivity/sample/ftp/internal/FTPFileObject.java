/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf & mdow - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.connectivity.sample.ftp.internal;

import org.apache.commons.net.ftp.FTPFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IActionFilter;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sample.ftp.IFtpProfileConstants;


/**
 * Wrapper class for FTPFile which stores the connection
 * profile
 */
public class FTPFileObject implements IAdaptable {
    private Object parent;

    private FTPFile file;

    private IConnectionProfile profile;

    public FTPFileObject(Object parent, IConnectionProfile profile, FTPFile file) {
        this.parent = parent;
        this.profile = profile;
        this.file = file;
    }

    /**
     * @return Returns the parent.
     */
    public Object getParent() {
        return parent;
    }

    /**
     * @return Returns the file.
     */
    public FTPFile getFTPFile() {
        return file;
    }

    public String getName() {
        StringBuffer sb = new StringBuffer();        
        FTPFileObject parent = this;
        while(parent != null) {
            sb.insert(0, "/" + parent.getFTPFile().getName());
            if (parent.getParent() instanceof FTPFileObject) {
                parent = (FTPFileObject) parent.getParent();
            } else {
                parent = null;
            }
        }
        while (sb.charAt(0) == '/') {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    /**
     * @return Returns the profile.
     */
    public IConnectionProfile getProfile() {
        return profile;
    }

    /**
     * Utility method
     */
    public static FTPFileObject[] convert(Object parent,
            IConnectionProfile profile, FTPFile[] files) {
        if (files == null) return new FTPFileObject[0];
        FTPFileObject[] fileobjs = new FTPFileObject[files.length];
        for (int i = 0; i < files.length; i++) {
            fileobjs[i] = new FTPFileObject(parent, profile, files[i]);
        }
        return fileobjs;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     */
    public Object getAdapter(Class adapter) {
        if (adapter == IActionFilter.class)
            return new IActionFilter() {
                public boolean testAttribute(Object target, String name,
                        String value) {
                    if (target == null || !(target instanceof FTPFileObject))
                        return false;
                    FTPFileObject fo = (FTPFileObject) target;
                    if (name
                            .equals(IFtpProfileConstants.FTP_PHANTOM_PROPERTY_IS_DIRECTORY)
                            && fo.getFTPFile().isDirectory())
                        return true;
                    else
                        return false;
                }
            };
        else
            return null;
    }
}
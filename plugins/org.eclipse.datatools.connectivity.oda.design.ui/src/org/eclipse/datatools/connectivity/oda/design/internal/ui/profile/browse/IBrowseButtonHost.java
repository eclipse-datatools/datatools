/*
 *************************************************************************
 * Copyright (c) 2011 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */
package org.eclipse.datatools.connectivity.oda.design.internal.ui.profile.browse;

/**
 * Internal interface that must be implemented by all classes
 * that consumes the {@link ProfileStoreBrowseButton}.
 * 
 * @since 3.2.6 (DTP 1.9.2)
 */
public abstract interface IBrowseButtonHost 
{
    public static final String RESOURCE_FILE_DIR = "ResourceFileDIR"; //$NON-NLS-1$
    public static final String IS_CREATE_PROFILE = "IsCreateProfile"; //$NON-NLS-1$
    public static final String STORED_PATH = "StoredPath"; //$NON-NLS-1$
    
    /**
     * This function should be used by the Browse Button host to get
     * back the path to the file 
     * @param path - absolute or relative path to the file
     * @param isRelative - true if path is relative; false otherwise
     */
    public void setProfileStorePath( String path, boolean isRelative ); 
    
    /**
     * This function is called whenever a menu in the browse button is selected.
     * This serves as a notification to the host, so that the host can perform 
     * some operations before the dialog is launched.
     */
    public void browseSelected();

}

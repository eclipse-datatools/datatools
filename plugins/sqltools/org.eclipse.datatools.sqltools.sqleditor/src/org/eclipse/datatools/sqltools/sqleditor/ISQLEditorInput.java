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

import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.ui.IEditorInput;

/**
 * This interface is a mix-in interface to be used with an <code>IEditorInput</code> 
 * to add additional information to the editor input for the SQL editor.
 */
public interface ISQLEditorInput extends IEditorInput {

    public void setConnectionInfo(ISQLEditorConnectionInfo connInfo);
    
    public ISQLEditorConnectionInfo getConnectionInfo();
    
    /**
     * Returns whether database connection is required for this editor input to exist. 
     * @return
     */
    public boolean isConnectionRequired();
    
    /**
     * Returns the unique identifier that distinguishes this editor input
     * @return A string id
     */
    public String getId();
    
}

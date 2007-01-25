/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor;
/**
 * Actions that are aware of the multi form editor should implement this interface.
 * @author Hui Cao
 * 
 */
public interface IPageUpdate
{
    /**
     * Requests that this object update itself when the page is switched.
     * @param isSQLEditorPage whether the active page is an embeded instance of SQLEditor. 
     */
    void update(boolean isSQLEditorPage);
    
}

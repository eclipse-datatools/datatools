/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege;


/**
 * Input for privileges (table) tree viewer.
 * 
 * @author Idull
 */
public interface IPrivilegesTreeViewerInput
{
    /**
     * Returns the root node of the tree
     * @return
     */
    public FolderNode getRoot();
    
    /**
     * Refreshes the input due to model change
     */
    public void refresh();
}

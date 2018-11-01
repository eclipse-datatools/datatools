/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.util;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * @author ljulien
 */
public class EclipseUtilities
{
    /**
     * @return the active workbench window
     */
    public static IWorkbenchWindow getActiveWorkbenchWindow()
    {
    	IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    	return window != null ? window : PlatformUI.getWorkbench().getWorkbenchWindows()[0];
    }
    /**
     * @return All the workbench windows
     */
	public static IWorkbenchWindow[] getWorkbenchWindows ()
	{
		return PlatformUI.getWorkbench().getWorkbenchWindows();
	}
    /**
     * @return the active page
     */
    public static IWorkbenchPage getActivePage()
    {
        return getActiveWorkbenchWindow().getActivePage();
    }
    /**
     * @return the active editor
     */
    public static IEditorPart getActiveEditor()
    {
        return getActivePage().getActiveEditor();
    }
    /**
     * @return the Workspace root
     */
    public static IWorkspaceRoot getWorkspaceRoot ()
    {
    	return ResourcesPlugin.getWorkspace().getRoot();
    }
}

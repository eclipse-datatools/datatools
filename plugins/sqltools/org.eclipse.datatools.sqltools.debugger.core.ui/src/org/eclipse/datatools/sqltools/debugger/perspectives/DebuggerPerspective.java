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
package org.eclipse.datatools.sqltools.debugger.perspectives;


import org.eclipse.datatools.sqltools.debugger.core.internal.DebuggerCorePlugin;
import org.eclipse.datatools.sqltools.debugger.core.internal.DebuggerMessages;
import org.eclipse.datatools.sqltools.sqleditor.EditorConstants;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.IConsoleConstants;

/**
 * The Debugger persective implementation 
 *
 * @author Samir Nigam
 * @author Arathi Sarvesh - Added code for "change perspective."
 *
 */
public class DebuggerPerspective implements IPerspectiveFactory
{
	public static final String DEBUGGER_PERSPECTIVE_ID = "org.eclipse.datatools.sqltools.debugger.perspectives.DebuggerPerspective"; // "org.eclipse.debug.ui.DebugPerspective";
	
    //Folders
    public static final String ID_NAVIGATOR_FOLDER_VIEW= "org.eclipse.debug.internal.ui.NavigatorFolderView"; //$NON-NLS-1$
    public static final String ID_TOOLS_FOLDER_VIEW= "org.eclipse.debug.internal.ui.ToolsFolderView"; //$NON-NLS-1$
    public static final String ID_CONSOLE_FOLDER_VIEW= "org.eclipse.debug.internal.ui.ConsoleFolderView"; //$NON-NLS-1$
    public static final String ID_OUTLINE_FOLDER_VIEW= "org.eclipse.debug.internal.ui.OutlineFolderView"; //$NON-NLS-1$

    public DebuggerPerspective()
    {
    }

    public void createInitialLayout(IPageLayout layout)
    {	
		layout.addActionSet(IDebugUIConstants.LAUNCH_ACTION_SET);
		layout.addActionSet(IDebugUIConstants.DEBUG_ACTION_SET);
		layout.addActionSet(IDebugUIConstants.LAUNCH_ACTION_SET);
		layout.addActionSet(IPageLayout.ID_NAVIGATE_ACTION_SET);
		
		setContentsOfShowViewMenu(layout);
	}
	
	/** 
	 * Sets the initial contents of the "Show View" menu.
	 */
	protected void setContentsOfShowViewMenu(IPageLayout layout) {
		layout.addShowViewShortcut(IDebugUIConstants.ID_DEBUG_VIEW);
		layout.addShowViewShortcut(IDebugUIConstants.ID_VARIABLE_VIEW);
		layout.addShowViewShortcut(IDebugUIConstants.ID_BREAKPOINT_VIEW);
		layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
	}


    public static void changePerspective()
    {
        try
        {
            // Map perspective id to descriptor.
            IPerspectiveRegistry reg = PlatformUI.getWorkbench().getPerspectiveRegistry();
            IPerspectiveDescriptor desc = reg.findPerspectiveWithId(DEBUGGER_PERSPECTIVE_ID);
            //Get the active page.
            IWorkbench i = PlatformUI.getWorkbench();
            IWorkbenchWindow[] j = i.getWorkbenchWindows();
            IWorkbenchWindow window = i.getActiveWorkbenchWindow();

            if (window == null)
            {
            	DebuggerCorePlugin.getDefault().log(DebuggerMessages.perspective_DebuggerPerspective_activeWinNull1);
                window = j[0];
            }

            if (window == null)
            {
            	DebuggerCorePlugin.getDefault().log(DebuggerMessages.perspective_DebuggerPerspective_activeWinNull2);
            }

            IWorkbenchPage page = window.getActivePage();
            // Set the perspective.
            page.setPerspective(desc);
        }
        catch (Exception e)
        {
        	DebuggerCorePlugin.getDefault().log(DebuggerMessages.perspective_DebuggerPerspective_changePerspectiveException);
        }
    }
}

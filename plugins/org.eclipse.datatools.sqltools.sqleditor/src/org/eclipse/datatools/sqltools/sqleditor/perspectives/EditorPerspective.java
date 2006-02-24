/*
 * Created on May 28, 2004
 *
 * Copyright (c) Sybase, Inc. 2004   
 * All rights reserved.                                    
 */
package org.eclipse.datatools.sqltools.sqleditor.perspectives;

import org.eclipse.datatools.sqltools.sqleditor.EditorConstants;
import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;


/**
 * @author asarvesh
 */
public class EditorPerspective implements IPerspectiveFactory, EditorConstants
{

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
     */
    public void createInitialLayout(IPageLayout layout)
    {
        addActionSets(layout);
        // Get the editor area.
        String editorArea = layout.getEditorArea();

        //Left
        IFolderLayout left = layout.createFolder("left", IPageLayout.LEFT, 0.25f, editorArea);
        left.addView(EditorConstants.DATA_SOURCE_EXPLORER);

        // Bottom 
        IFolderLayout bottom = layout.createFolder("bottom", IPageLayout.BOTTOM, 0.75f, editorArea);
        bottom.addView(EditorConstants.RESULTS_VIEW);
        bottom.addView(IPageLayout.ID_TASK_LIST);
        bottom.addView(IPageLayout.ID_PROBLEM_VIEW);
        bottom.addView(EditorConstants.LOG_VIEW);

        // right 
        IFolderLayout right = layout.createFolder("right", IPageLayout.RIGHT, 0.75f, editorArea);
        right.addView(IPageLayout.ID_OUTLINE);
        

    }



    private void addActionSets(IPageLayout layout)
    {
        layout.addActionSet(IDebugUIConstants.LAUNCH_ACTION_SET);
        layout.addActionSet(IPageLayout.ID_NAVIGATE_ACTION_SET);
    }

}

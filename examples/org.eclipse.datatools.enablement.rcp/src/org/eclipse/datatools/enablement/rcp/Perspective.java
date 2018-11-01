/*******************************************************************************
 * Copyright (c) 2009 Sybase, Inc. and others
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Ingres - initial API and implementation
 * 				brianf - a few tweaks
 ******************************************************************************/
package org.eclipse.datatools.enablement.rcp;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {
	
	private static String DSE_VIEW_ID = "org.eclipse.datatools.connectivity.DataSourceExplorerNavigator"; //$NON-NLS-1$
	private static String PROJECT_EXPLORER_VIEW_ID = "org.eclipse.ui.navigator.ProjectExplorer"; //$NON-NLS-1$
	private static String RESULT_VIEW_ID = "org.eclipse.datatools.sqltools.result.resultView"; //$NON-NLS-1$
	private static String PLAN_VIEW_ID = "org.eclipse.datatools.sqltools.plan.planView"; //$NON-NLS-1$

	public void createInitialLayout(IPageLayout layout) {

		// Get the editor area.
        String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(true);

        //Left
        IFolderLayout left = layout.createFolder("topleft", IPageLayout.LEFT, 0.25f, editorArea); //$NON-NLS-1$
        left.addView(DSE_VIEW_ID);

		left = layout.createFolder("bottomleft", IPageLayout.BOTTOM, 0.5f, //$NON-NLS-1$
			"topleft"); //$NON-NLS-1$
        left.addView(PROJECT_EXPLORER_VIEW_ID);

        // Bottom 
        IFolderLayout bottom = layout.createFolder("bottom", IPageLayout.BOTTOM, 0.75f, editorArea); //$NON-NLS-1$
        bottom.addView(RESULT_VIEW_ID);
		bottom.addView(PLAN_VIEW_ID);
	}
}

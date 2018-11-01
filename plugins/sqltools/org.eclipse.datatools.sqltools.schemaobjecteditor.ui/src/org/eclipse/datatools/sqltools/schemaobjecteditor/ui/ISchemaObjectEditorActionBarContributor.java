/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;

/**
 * A schema object editor which need to contribute new actions need to implement this interface
 * 
 * @author Idull
 */
public interface ISchemaObjectEditorActionBarContributor
{
    /**
     * Sets the editor
     * 
     * @param editor
     */
    public void setEditor(ISchemaObjectEditor editor);

    /**
     * Contributes actions to cool bar.
     * 
     * @param coolBarManager
     */
    public void contributeToCoolBar(ICoolBarManager coolBarManager);

    /**
     * Contributes actions to menu
     * 
     * @param menuManager
     */
    public void contributeToMenu(IMenuManager menuManager);

    /**
     * Sets/clears message on status line
     * 
     * @param statusLineManager
     */
    public void contributeToStatusLine(IStatusLineManager statusLineManager);

    /**
     * Contribute actions to tool bar
     * 
     * @param toolBarManager
     */
    public void contributeToToolBar(IToolBarManager toolBarManager);

    /**
     * This method is called whenever the page changes. Subclasses must implement this method to redirect actions to the
     * given editor (if not already directed to it).
     * 
     * @param activeEditor
     */
    public void setActivePage(IEditorPart activeEditor);

    /**
     * Initializes this contributor, which is expected to add contributions as required to the given action bars and
     * global action handlers.
     * <p>
     * The page is passed to support the use of <code>RetargetAction</code> by the contributor. In this case the init
     * method implementors should:
     * </p>
     * <p>
     * <ul>
     * <li>1) set retarget actions as global action handlers</li>
     * <li>2) add the retarget actions as part listeners</li>
     * <li>3) get the active part and if not <code>null</code> call partActivated on the retarget actions</li>
     * </ul>
     * </p>
     * <p>
     * And in the dispose method the retarget actions should be removed as part listeners.
     * </p>
     * 
     * @param bars the action bars
     * @param page the workbench page for this contributor
     */
    public void init(IActionBars bars, IWorkbenchPage page);

    /**
     * Returns this contributor's action bars.
     * 
     * @return the action bars
     */
    public IActionBars getActionBars();

    /**
     * Returns this contributor's workbench page.
     * 
     * @return the workbench page
     */
    public IWorkbenchPage getPage();
}

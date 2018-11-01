/***********************************************************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.editors.text.TextEditorActionContributor;

/**
 * 
 * @author Hui Cao
 * 
 */
public interface ISQLEditorActionContributorExtension
{

    String EDITOR_ID_ALL = "all";

    /**
     * Receives notification that the workbench menu is about to show.
     */
    public abstract void menuAboutToShow(IMenuManager manager);

    /**
     * Contributes to the given SQLEditor context menu.
     * 
     * @param menuManager the manager that controls the menu
     */
    public abstract void contributeToContextMenu(IMenuManager mm);

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
     * Sets the active editor for the contributor. Implementors should disconnect from the old editor, connect to the
     * new editor, and update the actions to reflect the new editor.
     * 
     * @param targetEditor the new editor target
     */
    public void setActiveEditor(SQLEditor targetEditor);

    /**
     * Disposes this contributor.
     * 
     */
    public void dispose();

    /**
     * Contributes to the given menu.
     * 
     * @param menuManager the manager that controls the menu
     */
    public void contributeToMenu(IMenuManager menuManager);

    /**
     * Contributes to the given status line.
     * 
     * @param statusLineManager the manager of the status line
     */
    public void contributeToStatusLine(IStatusLineManager statusLineManager);

    /**
     * Contributes to the given tool bar.
     * 
     * @param toolBarManager the manager that controls the workbench tool bar
     */
    public void contributeToToolBar(IToolBarManager toolBarManager);

    /**
     * Contributes to the given cool bar.
     * 
     * @param coolBarManager the manager that controls the workbench cool bar.
     * 
     */
    public void contributeToCoolBar(ICoolBarManager coolBarManager);

    public void setParent(TextEditorActionContributor parent);

    /**
     * Requests that this object update its actions. This is equivalent to udpateAction(true).
     */
    public void updateAction();

    /**
     * Requests that this object update its actions when the page is switched.
     * 
     * @param isSQLEditorPage whether the active page is an embeded instance of SQLEditor.
     */
    public void updateAction(boolean isSQLEditorPage);
}
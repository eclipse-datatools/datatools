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
package org.eclipse.datatools.sqltools.sqleditor;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.editors.text.TextEditorActionContributor;

/**
 * This class is for tooling vendor who wish to extend the sql dev tools to
 * contribute workbench menu, toolbar item and context menus.
 * 
 * @see extension point:
 *      org.eclipse.datatools.sqltools.sqleditor.actionextension
 * 
 * @author Hui Cao
 * 
 */
public class SQLEditorActionContributorExtension implements IMenuListener, ISQLEditorActionContributorExtension, IExecutableExtension {

	private String editorId = EDITOR_ID_ALL;
 
    private SQLEditor                                         sqlEditor;

	TextEditorActionContributor parent;
	
	public SQLEditorActionContributorExtension()
	{
		
	}
	
	public TextEditorActionContributor getParent() {
		return parent;
	}



	public void setParent(TextEditorActionContributor parent) {
		this.parent = parent;
	}


	/**
	 * Returns the editor id that this contributor is interested in
	 * @return
	 */
	public String getEditorId() {
		return editorId;
	}


	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		if (propertyName.equals("editorId"))
		{
			this.editorId = config.getAttribute(propertyName); //$NON-NLS-1$
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.datatools.sqltools.sqleditor.ISQLEditorActionContributorExtension#setActiveEditor(org.eclipse.ui.IEditorPart)
	 */
	public void setActiveEditor(SQLEditor targetEditor)
	{
		sqlEditor = targetEditor;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.datatools.sqltools.sqleditor.ISQLEditorActionContributorExtension#init(org.eclipse.ui.IActionBars, org.eclipse.ui.IWorkbenchPage)
	 */
	public void init(IActionBars bars, IWorkbenchPage page)
	{

	}
	
    /**
     * Contributes to the given menu.
     * <p>
     * The <code>SQLEditorActionContributorExtension</code> implementation of this method
     * does nothing. Subclasses may reimplement to add to the menu portion of this
     * contribution.
     * </p>
     *
     * @param menuManager the manager that controls the menu
     */
    public void contributeToMenu(IMenuManager menuManager) {
    }


    /**
     * Receives notification that the workbench menu is about to show. 
     */
	public void menuAboutToShow(IMenuManager manager) {
		// TODO Auto-generated method stub
		
	}
	
    /**
     * Contributes to the given status line.
     * <p>
     * The <code>SQLEditorActionContributorExtension</code> implementation of this method
     * does nothing. Subclasses may reimplement to add to the status line portion of
     * this contribution.
     * </p>
     *
     * @param statusLineManager the manager of the status line
     */
    public void contributeToStatusLine(IStatusLineManager statusLineManager) {
    }

    /**
     * Contributes to the given tool bar.
     * <p>
     * The <code>SQLEditorActionContributorExtension</code> implementation of this method
     * does nothing. Subclasses may reimplement to add to the tool bar portion of
     * this contribution.
     * </p>
     *
     * @param toolBarManager the manager that controls the workbench tool bar
     */
    public void contributeToToolBar(IToolBarManager toolBarManager) {
    }

    /**
     * Contributes to the given cool bar.
     * <p>
     * The <code>SQLEditorActionContributorExtension</code> implementation of this method
     * does nothing. Subclasses may reimplement to add to the cool bar portion of
     * this contribution. There can only be conributions from a cool bar or a tool bar.
     * </p>
     *
     * @param coolBarManager the manager that controls the workbench cool bar.
     * 
     */
    public void contributeToCoolBar(ICoolBarManager coolBarManager) {
    }



	public void contributeToContextMenu(IMenuManager mm) {
		// TODO Auto-generated method stub
		
	}


    /* (non-Javadoc)
	 * @see org.eclipse.datatools.sqltools.sqleditor.ISQLEditorActionContributorExtension#dispose()
	 */
	public void dispose()
	{
		
	}


	/**
	 * Tells whether this contributor should be called to contribute action items. Suclasses
	 * should call this method before their contribution.
	 * @return
	 */
	protected boolean isRelevant()
	{
		if (sqlEditor != null)
		{
			String realId = sqlEditor.getSite().getId();
			return (editorId.equals(EDITOR_ID_ALL) || editorId.equals(realId));
		}
		return true;
	}

    public void updateAction()
    {
                
    }

    public void updateAction(boolean isSQLEditorPage)
    {
    }
}

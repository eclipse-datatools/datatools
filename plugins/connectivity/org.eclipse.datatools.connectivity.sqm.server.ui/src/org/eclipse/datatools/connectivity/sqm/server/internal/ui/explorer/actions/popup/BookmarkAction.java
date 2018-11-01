/*******************************************************************************
 * Copyright (c) 2001, 2004, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.actions.popup;

import java.util.Iterator;

import org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.bookmark.BookmarkProvider;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.providers.bookmark.BookmarkSelectionProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.ide.IDEActionFactory;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;

/**
 * @author ljulien
 */
public class BookmarkAction extends CommonActionProvider
{
    private BookmarkProvider bookmarkProvider = null;
    private BookmarkSelectionProvider bookmarkSelectionProvider = null;
    private ICommonViewerWorkbenchSite viewSite;

    public BookmarkAction() {
		super();
	}
    
    private boolean isSelectionValid(IStructuredSelection selection)
    {
        for (Iterator iterator = selection.iterator(); iterator.hasNext();)
        {
            Object selected = iterator.next();
            if (!(selected instanceof EObject))
            {
                return false;
            }
        }
        return true;
    }

    public void selectionChanged(SelectionChangedEvent event)
    {
    }

    public void dispose()
    {
        this.viewSite.getActionBars().setGlobalActionHandler(IDEActionFactory.BOOKMARK.getId(), null);
        if (bookmarkProvider != null)
        {
            bookmarkProvider.removeListener();
            bookmarkSelectionProvider.removeListener();
        }
    }

    public void init(ICommonActionExtensionSite aConfig)
    {
        this.viewSite = (ICommonViewerWorkbenchSite) aConfig.getViewSite();
        bookmarkProvider = new BookmarkProvider(this.viewSite);
        bookmarkSelectionProvider = new BookmarkSelectionProvider();
    }

    public void fillActionBars(IActionBars theActionBars)
    {
        if (isSelectionValid((IStructuredSelection) this.getContext().getSelection()))
        {
            theActionBars.setGlobalActionHandler(IDEActionFactory.BOOKMARK.getId(), bookmarkProvider);
        }

        theActionBars.updateActionBars();
        theActionBars.getMenuManager().update();
    }

    public void fillContextMenu(IMenuManager aMenu)
    {
    }

    public void restoreState(IMemento aMemento)
    {
    }

    public void saveState(IMemento aMemento)
    {
    }
}

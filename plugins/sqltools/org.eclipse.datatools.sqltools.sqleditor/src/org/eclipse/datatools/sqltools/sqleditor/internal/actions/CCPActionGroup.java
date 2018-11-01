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
package org.eclipse.datatools.sqltools.sqleditor.internal.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionGroup;
import org.eclipse.ui.part.Page;
import org.eclipse.ui.texteditor.IWorkbenchActionDefinitionIds;

/**
 * Action group that adds the copy, cut, paste actions to a view part's context menu and installs handlers for the
 * corresponding global menu actions.
 * 
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 * 
 * @author Hui Cao
 *  
 */
public class CCPActionGroup extends ActionGroup
{
    private IWorkbenchSite            _fSite;
    private Clipboard                 _fClipboard;

    private SelectionDispatchAction[] _fActions;

    private SelectionDispatchAction   _fDeleteAction;
    private SelectionDispatchAction   _fCopyAction;
    private SelectionDispatchAction   _fPasteAction;
    private SelectionDispatchAction   _fCutAction;

    /**
     * Creates a new <code>CCPActionGroup</code>. The group requires that the selection provided by the view part's
     * selection provider is of type <code>org.eclipse.jface.viewers.IStructuredSelection</code>.
     * 
     * @param part the view part that owns this action group
     */
    public CCPActionGroup(IViewPart part)
    {
        this(part.getSite());
    }

    /**
     * Creates a new <code>CCPActionGroup</code>. The group requires that the selection provided by the page's
     * selection provider is of type <code>org.eclipse.jface.viewers.IStructuredSelection</code>.
     * 
     * @param page the page that owns this action group
     */
    public CCPActionGroup(Page page)
    {
        this(page.getSite());
    }

    private CCPActionGroup(IWorkbenchSite site)
    {
        _fSite = site;
        _fClipboard = new Clipboard(site.getShell().getDisplay());

        _fPasteAction = new PasteAction(_fSite, _fClipboard);
        _fPasteAction.setActionDefinitionId(IWorkbenchActionDefinitionIds.PASTE);

        _fCopyAction = new CopyToClipboardAction(_fSite, _fClipboard, _fPasteAction);
        _fCopyAction.setActionDefinitionId(IWorkbenchActionDefinitionIds.COPY);

        _fCutAction = new CutAction(_fSite, _fClipboard, _fPasteAction);
        _fCutAction.setActionDefinitionId(IWorkbenchActionDefinitionIds.CUT);

        _fDeleteAction = new DeleteAction(_fSite);
        _fDeleteAction.setActionDefinitionId(IWorkbenchActionDefinitionIds.DELETE);

        _fActions = new SelectionDispatchAction[] 
        {
            _fCutAction, _fCopyAction, _fPasteAction, _fDeleteAction
        }
        ;
        registerActionsAsSelectionChangeListeners();
    }

    private void registerActionsAsSelectionChangeListeners()
    {
        ISelectionProvider provider = _fSite.getSelectionProvider();
        for (int i = 0; i < _fActions.length; i++)
        {
            provider.addSelectionChangedListener(_fActions[i]);
        }
    }

    private void deregisterActionsAsSelectionChangeListeners()
    {
        ISelectionProvider provider = _fSite.getSelectionProvider();
        for (int i = 0; i < _fActions.length; i++)
        {
            provider.removeSelectionChangedListener(_fActions[i]);
        }
    }

    /**
     * Returns the delete action managed by this action group.
     * 
     * @return the delete action. Returns <code>null</code> if the group doesn't provide any delete action
     */
    public IAction getDeleteAction()
    {
        return _fDeleteAction;
    }

    /*
     * (non-Javadoc) Method declared in ActionGroup
     */
    public void fillActionBars(IActionBars actionBars)
    {
        super.fillActionBars(actionBars);
        actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), _fDeleteAction);
        actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(), _fCopyAction);
        actionBars.setGlobalActionHandler(ActionFactory.CUT.getId(), _fCutAction);
        actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(), _fPasteAction);
    }

    /*
     * (non-Javadoc) Method declared in ActionGroup
     */
    public void fillContextMenu(IMenuManager menu)
    {
        super.fillContextMenu(menu);
        for (int i = 0; i < _fActions.length; i++)
        {
            SelectionDispatchAction action = _fActions[i];
            //			if (action == fCutAction && !fCutAction.isEnabled())
            //				continue;
            //menu.appendToGroup(IContextMenuConstants.GROUP_REORGANIZE, action);
            menu.add(action);
        }
    }

    /*
     * @see ActionGroup#dispose()
     */
    public void dispose()
    {
        super.dispose();
        if (_fClipboard != null)
        {
            _fClipboard.dispose();
            _fClipboard = null;
        }
        deregisterActionsAsSelectionChangeListeners();
    }

}


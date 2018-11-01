/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.core;

import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorActionBarContributor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.action.RefreshSchemaEditorAction;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.action.RevertSchemaEditorAction;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.Constants;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.extensions.IEditorDescriptor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util.Images;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.actions.RetargetAction;
import org.eclipse.ui.part.MultiPageEditorActionBarContributor;

/**
 * The action bar contributor
 * 
 * @author Idull
 */
public class SchemaObjectEditorActionBarContributor extends MultiPageEditorActionBarContributor
{
    ISchemaObjectEditor                     _editor;
    IEditorDescriptor                       _exEditor;
    ISchemaObjectEditorActionBarContributor _contributor;

    IToolBarManager                         _toolManager;
    ICoolBarManager                         _coolManager;
    IMenuManager                            _menuManager;
    IStatusLineManager                      _statusManager;
    RetargetAction                          _refreshEditorRetargetAction;
    RetargetAction                          _revertEditorRetargetAction;

    RefreshSchemaEditorAction               _refreshEditorAction;
    RevertSchemaEditorAction                _revertEditorAction;

    public SchemaObjectEditorActionBarContributor()
    {
        _refreshEditorRetargetAction = new RetargetAction(Constants.REFRESH_EDITOR_ACTION_ID,
                Messages.SchemaObjectEditorActionBarContributor_refresh_from_server);
        _refreshEditorRetargetAction.setActionDefinitionId(Constants.REFRESH_EDITOR_ACTION_ID);
        _refreshEditorRetargetAction.setImageDescriptor(Images.DESC_REFRESH);

        _revertEditorRetargetAction = new RetargetAction(Constants.REVERT_EDITOR_ACTION_ID,
                Messages.SchemaObjectEditorActionBarContributor_revert);
        _revertEditorRetargetAction.setActionDefinitionId(Constants.REVERT_EDITOR_ACTION_ID);
        _revertEditorRetargetAction.setImageDescriptor(Images.DESC_REVERT);
    }

    protected void createRetargetActions()
    {

    }

    public void contributeToCoolBar(ICoolBarManager coolBarManager)
    {
        super.contributeToCoolBar(coolBarManager);
        _coolManager = coolBarManager;
    }

    public void contributeToMenu(IMenuManager menuManager)
    {
        super.contributeToMenu(menuManager);
        _menuManager = menuManager;
    }

    public void contributeToStatusLine(IStatusLineManager statusLineManager)
    {
        super.contributeToStatusLine(statusLineManager);
        _statusManager = statusLineManager;
    }

    public void contributeToToolBar(IToolBarManager toolBarManager)
    {
        super.contributeToToolBar(toolBarManager);
        _toolManager = toolBarManager;
    }

    public void setActiveEditor(IEditorPart targetEditor)
    {
        _toolManager.removeAll();
        _coolManager.removeAll();
        _menuManager.removeAll();
        _statusManager.removeAll();
        if (targetEditor instanceof ISchemaObjectEditor)
        {
            ISchemaObjectEditor editor = (ISchemaObjectEditor) targetEditor;
            setEditor(editor);

            _toolManager.add(_refreshEditorRetargetAction);
            _toolManager.add(_revertEditorRetargetAction);

            // Add the default actions for all editors
            _refreshEditorAction = new RefreshSchemaEditorAction();
            _refreshEditorAction.setEditor(editor);
            targetEditor.getEditorSite().getActionBars().setGlobalActionHandler(Constants.REFRESH_EDITOR_ACTION_ID,
                    _refreshEditorAction);
            _refreshEditorRetargetAction.partActivated(targetEditor);

            _revertEditorAction = new RevertSchemaEditorAction();
            _revertEditorAction.setEnabled(editor.isDirty());
            editor.addPropertyListener(_revertEditorAction);
            _revertEditorAction.setEditor(editor);
            targetEditor.getEditorSite().getActionBars().setGlobalActionHandler(Constants.REVERT_EDITOR_ACTION_ID,
                    _revertEditorAction);
            _revertEditorRetargetAction.partActivated(targetEditor);

            targetEditor.getEditorSite().getActionBars().updateActionBars();
            _exEditor = editor.getEditorDescriptor();
            if (_exEditor != null)
            {
                _contributor = _exEditor.getActionContributor();
            }

            if (_exEditor != null && _contributor != null)
            {
                // can do _contributor.init in this.init because _exEditor was null
                if (_contributor.getActionBars() == null)
                {
                    _contributor.init(this.getActionBars(), this.getPage());
                    _contributor.setEditor(_editor);
                }
                else
                {
                    _contributor.setEditor(_editor);
                    _contributor.contributeToMenu(_menuManager);
                    _contributor.contributeToCoolBar(_coolManager);
                    _contributor.contributeToToolBar(_toolManager);
                    _contributor.contributeToStatusLine(_statusManager);
                }
            }
        }
        _toolManager.update(true);
        _menuManager.update(true);
        // Set wrapped EditorMenuManagers contained in _menuManager visible
        _menuManager.setVisible(true);
        _coolManager.update(true);
        _statusManager.update(true);
        super.setActiveEditor(targetEditor);
    }

    public ISchemaObjectEditor getEditor()
    {
        return _editor;
    }

    public void setEditor(ISchemaObjectEditor editor)
    {
        _editor = editor;
    }

    public void setActivePage(IEditorPart activeEditor)
    {
        if (_exEditor != null && _exEditor.getActionContributor() != null)
        {
            _exEditor.getActionContributor().setActivePage(activeEditor);
        }
    }

}

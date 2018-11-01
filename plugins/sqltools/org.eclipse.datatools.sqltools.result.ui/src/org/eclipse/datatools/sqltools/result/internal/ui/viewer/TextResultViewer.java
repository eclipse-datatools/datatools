/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.ui.viewer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import org.eclipse.datatools.sqltools.result.ResultsConstants;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.text.DefaultUndoManager;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.IUndoManager;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.FindReplaceAction;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.ui.texteditor.IUpdate;
import org.eclipse.ui.texteditor.IWorkbenchActionDefinitionIds;

/**
 * This is a text widget with some common context menu items:CUT,COPY, PASTE,
 * etc. that is used to display SQL results in text mode. User can also hook
 * user-defined items.
 * 
 * @author Dafan Yang
 */
public class TextResultViewer
{
    private SourceViewer        _viewer;
    private IUndoManager        _undoMgr;
    private static final int    UNDO_LEVEL         = 15;
    private Map                 _actions           = new HashMap();
    private static final String GROUP_SELECT       = "select_group";
    private static final String GROUP_HOOKED       = "hook_group";
    private static final String FONT_STYLE         = "Courier New";
    private static final String FIND_REPLACE_ACTION_PREFIX = "find_replace_action_";
    private static final int    FONT_SIZE          = 10;
    private MenuManager         _contextMenuMgr;
    private ArrayList           _hookedItems;
    private Action              _undo;
    private Action              _redo;
    
    // This flag is used to determine whether find/replace action has been already added to the context menu or not.  
    private boolean             _isFindReplaceActionAdded = false;

    /**
     * 
     * @param parent
     * @param style
     * @param hookedActions
     */
    public TextResultViewer(Composite parent, int style, ArrayList hookedActions)
    {
        super();
        _hookedItems = hookedActions;
        _undoMgr = new DefaultUndoManager(UNDO_LEVEL);
        IVerticalRuler ruler = null;
        _viewer = new SourceViewer(parent, ruler, style);
        this.configureViewer();
    }

    /**
     * get all global actions
     *  
     */
    public void initializeCommonActions()
    {
        IAction action = new CommonAction(_viewer.getTextOperationTarget(), ITextOperationTarget.CUT, Messages.CommonTextViewer_action_cut); //$NON-NLS-1$
        action.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_CUT));
        _actions.put(ITextEditorActionConstants.CUT, action);
        
        action = new CommonAction(_viewer.getTextOperationTarget(), ITextOperationTarget.COPY, Messages.CommonTextViewer_action_copy); //$NON-NLS-1$
        action.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_COPY));
        _actions.put(ITextEditorActionConstants.COPY, action);

        action = new CommonAction(_viewer.getTextOperationTarget(), ITextOperationTarget.PASTE, Messages.CommonTextViewer_action_paste); //$NON-NLS-1$
        action.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_PASTE));
        _actions.put(ITextEditorActionConstants.PASTE, action);

        action = new CommonAction(_viewer.getTextOperationTarget(), ITextOperationTarget.DELETE, Messages.CommonTextViewer_action_delete); //$NON-NLS-1$
        action.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
        _actions.put(ITextEditorActionConstants.DELETE, action);

        action = new CommonAction(_viewer.getTextOperationTarget(), ITextOperationTarget.SELECT_ALL, Messages.CommonTextViewer_action_selectall); //$NON-NLS-1$
        _actions.put(ITextEditorActionConstants.SELECT_ALL, action);

        _undoMgr.connect(_viewer);
        _undo = new UndoAction(_undoMgr);
        _redo = new RedoAction(_undoMgr);
        _actions.put(ITextEditorActionConstants.UNDO, _undo);
        _actions.put(ITextEditorActionConstants.REDO, _redo);
    }

    /**
     * configure the source viewer
     * 
     */
    public void configureViewer()
    {
        _viewer.getTextWidget().setWordWrap(false);

        FontData fd = new FontData(FONT_STYLE, FONT_SIZE, SWT.NORMAL);
        final Font font = new Font(_viewer.getTextWidget().getDisplay(), fd);
        _viewer.getTextWidget().setFont(font);
        _viewer.getTextWidget().addDisposeListener(new DisposeListener()
        {
            public void widgetDisposed(DisposeEvent e)
            {
                if ( font != null & !font.isDisposed() )
                {
                    font.dispose();
                }
            }
        });
        
        _viewer.getTextWidget().addFocusListener(new FocusAdapter()
        {
            public void focusGained(FocusEvent e)
            {
                // Add in find/replace action if and only if the menu is about to show for the first time.
                if (!_isFindReplaceActionAdded)
                {
                    IViewPart view = ResultsViewUIPlugin.getDefault().getWorkbench().getActiveWorkbenchWindow()
                            .getActivePage().findView(ResultsConstants.SQL_RESULTS_VIEW_ID);
                    IAction action = new FindReplaceAction(ResultsViewUIPlugin.getDefault().getResourceBundle(), FIND_REPLACE_ACTION_PREFIX, view);
                    action.setActionDefinitionId(IWorkbenchActionDefinitionIds.FIND_REPLACE);
                    
                    _actions.put(ITextEditorActionConstants.FIND, action);
                    _contextMenuMgr.appendToGroup(ITextEditorActionConstants.GROUP_FIND, (IAction) _actions
                            .get(ITextEditorActionConstants.FIND));

                    _isFindReplaceActionAdded = true;
                }
            }
        });

        _viewer.configure(new SourceViewerConfiguration()
        {
        });
        
        // add context menu
        addContextMenu();
    }

    /**
     * create context menu for this viewer
     *  
     */
    private void addContextMenu()
    {
        initializeCommonActions();
        
        //Create context menu
        _contextMenuMgr = new MenuManager("#contextMenu");
        Menu contextMenu = _contextMenuMgr.createContextMenu(_viewer.getTextWidget());

        _contextMenuMgr.add(_undo);
        _contextMenuMgr.add(_redo);
        _contextMenuMgr.addMenuListener(new IMenuListener()
        {
            public void menuAboutToShow(IMenuManager mgr)
            {
                //update items' status
                Iterator iter = _actions.values().iterator();
                while (iter.hasNext())
                {
                    Object obj = iter.next();
                    if (obj instanceof IUpdate)
                    {
                        IUpdate action = (IUpdate) obj;
                        action.update();
                    }
                }
            }
        }
        );
        _contextMenuMgr.add(new Separator(ITextEditorActionConstants.GROUP_EDIT));
        _contextMenuMgr.appendToGroup(ITextEditorActionConstants.GROUP_EDIT, (IAction) _actions
            .get(ITextEditorActionConstants.CUT));
        _contextMenuMgr.appendToGroup(ITextEditorActionConstants.GROUP_EDIT, (IAction) _actions
            .get(ITextEditorActionConstants.COPY));
        _contextMenuMgr.appendToGroup(ITextEditorActionConstants.GROUP_EDIT, (IAction) _actions
            .get(ITextEditorActionConstants.PASTE));
        _contextMenuMgr.appendToGroup(ITextEditorActionConstants.GROUP_EDIT, (IAction) _actions
            .get(ITextEditorActionConstants.DELETE));
        _contextMenuMgr.add(new Separator(GROUP_SELECT));
        _contextMenuMgr.appendToGroup(GROUP_SELECT, (IAction) _actions.get(ITextEditorActionConstants.SELECT_ALL));
        _contextMenuMgr.add(new Separator(ITextEditorActionConstants.GROUP_FIND));
        
        //hook user-defined menu items
        addHookedContextMenuItems();

        _viewer.getTextWidget().setMenu(contextMenu);
        
        // shortcut key
        _viewer.getTextWidget().addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent event)
            {
                if (event.keyCode == 'z' && (event.stateMask & SWT.CTRL) != 0)
                {
                    _undo.run();
                }
                if (event.keyCode == 'y' && (event.stateMask & SWT.CTRL) != 0)
                {
                    _redo.run();
                }
                if (event.keyCode == 'a' && (event.stateMask & SWT.CTRL) != 0)
                {
                    CommonAction action = (CommonAction) _actions.get(ITextEditorActionConstants.SELECT_ALL);
                    if (action != null)
                    {
                        action.run();
                    }
                }
                if (event.keyCode == 'f' && (event.stateMask & SWT.CTRL) != 0)
                {
                    IAction action = (IAction) _actions.get(ITextEditorActionConstants.FIND);
                    if (action != null)
                    {
                        if (action instanceof IUpdate)
                        {
                            ((IUpdate) action).update();
                        }
                        action.run();
                    }
                }
            }
        }
        );
    }

    public SourceViewer getViewer()
    {
        return this._viewer;
    }

    public void resetUndoMgr()
    {
        _undoMgr.reset();
    }

    /**
     * hook some menu item to the context menu
     *  
     */
    public void addHookedContextMenuItems()
    {
        if (_hookedItems != null)
        {
            if (_hookedItems.size() > 0)
            {
                _contextMenuMgr.add(new Separator(GROUP_HOOKED));
            }
            Iterator iter = _hookedItems.iterator();
            while (iter.hasNext())
            {
                Object obj = iter.next();
                if(obj instanceof IAction)
                {
                    IAction action = (IAction) obj;
                    _contextMenuMgr.appendToGroup(GROUP_HOOKED, action);
                }
                else if(obj instanceof IContributionItem)
                {
                    IContributionItem item = (IContributionItem)obj;
                    _contextMenuMgr.appendToGroup(GROUP_HOOKED, item);
                }
                else
                {
                    //should not happen
                }
            }
        }
    }
    
    public class RedoAction extends Action implements IUpdate
    {
        private IUndoManager _manager;
        /**
         * 
         */
        public RedoAction(IUndoManager manager)
        {
            super();
            setText(Messages.RedoAction_title); 
            setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_REDO));
            _manager = manager;
        }


        public void run()
        {
            _manager.redo();
        }

        public void update()
        {
            if (_manager.redoable())
            {
                setEnabled(true);
            }
            else
            {
                setEnabled(false);
            }
        }
    }
    
    public class UndoAction extends Action implements IUpdate
    {
        private IUndoManager _manager;
        /**
         * 
         */
        public UndoAction(IUndoManager manager)
        {
            super();
            setText(Messages.UndoAction_title); 
            setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_UNDO));
            _manager = manager;
        }

        public void run()
        {
            _manager.undo();
        }

        public void update()
        {
            if (_manager.undoable())
            {
                setEnabled(true);
            }
            else
            {
                setEnabled(false);
            }
        }
    }
}

class CommonAction extends Action implements IUpdate
{
    private int                  _operationCode;
    private ITextOperationTarget _target;

    /**
     *  
     */
    public CommonAction(ITextOperationTarget target, int operationCode, String itemName)
    {
        super();
        _operationCode = operationCode;
        _target = target;
        this.setText(itemName);
    }

    /**
     * delegate the call to ITextOperationTarget instance
     */
    public void run()
    {
        _target.doOperation(_operationCode);
    }

    public void update()
    {
        boolean wasEnabled = isEnabled();
        boolean isEnabled = (_target != null && _target.canDoOperation(_operationCode));
        setEnabled(isEnabled);

        if (wasEnabled != isEnabled)
        {
            firePropertyChange(ENABLED, wasEnabled ? Boolean.TRUE : Boolean.FALSE, isEnabled ? Boolean.TRUE
                : Boolean.FALSE);
        }
    }
}

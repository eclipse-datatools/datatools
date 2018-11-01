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
package org.eclipse.datatools.sqltools.sqleditor.internal.editor;

import java.util.ArrayList;

import org.eclipse.datatools.sqltools.sql.parser.SQLParserConstants;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTDeployable;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLParam;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLParamDefList;
import org.eclipse.datatools.sqltools.sql.parser.ast.IASTSQLStatement;
import org.eclipse.datatools.sqltools.sql.parser.ast.Node;
import org.eclipse.datatools.sqltools.sql.parser.ast.SimpleNode;
import org.eclipse.datatools.sqltools.sql.util.SQLUtil;
import org.eclipse.datatools.sqltools.sqleditor.EditorConstants;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorActionConstants;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorResources;
import org.eclipse.datatools.sqltools.sqleditor.internal.actions.CCPActionGroup;
import org.eclipse.datatools.sqltools.sqleditor.internal.actions.DeployAction;
import org.eclipse.datatools.sqltools.sqleditor.internal.actions.ExecuteSQLAction;
import org.eclipse.datatools.sqltools.sqleditor.internal.actions.ExecuteSelectionSQLAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.texteditor.ITextEditorActionConstants;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

/**
 * @author Hui Cao
 *  
 */
public class SQLOutlinePage extends ContentOutlinePage implements ISelectionProvider
{
    //TODO: customize the node structure to show
    private class SQLContentProvider implements ITreeContentProvider
    {
        public void dispose()
        {
        }

        public Object[] getChildren(Object parentElement)
        {
            //Even if we have told eclipse that parentElement doesn't have children, this method will still be called,
            //so we check it again
            if (!hasChildren(parentElement))
            {
                return null;
            }

            if (parentElement instanceof SimpleNode)
            {
                SimpleNode parent = ((SimpleNode) parentElement);
                ArrayList children = new ArrayList();
                for (int i = 0; i < parent.jjtGetNumChildren(); i++)
                {
                    if (!dontShowMe(parent.jjtGetChild(i)))
                    {
                        children.add(parent.jjtGetChild(i));
                    }
                }
                return children.toArray();
            }
            return null;
        }

        public Object[] getElements(Object inputElement)
        {
            if (inputElement instanceof SimpleNode)
            {
                SimpleNode parent = ((SimpleNode) inputElement);
                ArrayList children = new ArrayList();
                for (int i = 0; i < parent.jjtGetNumChildren(); i++)
                {
                    if (!dontShowMe(parent.jjtGetChild(i)))
                    {
                        children.add(parent.jjtGetChild(i));
                    }
                }
                return children.toArray();
            }
            return null;
        }

        public Object getParent(Object element)
        {
            if (element instanceof SimpleNode)
            {
                SimpleNode e = ((SimpleNode) element);
                return e.jjtGetParent();
            }
            return null;
        }

        public boolean hasChildren(Object element)
        {
            if (dontGoDown(element))
            {
                return false;
            }

            if (element instanceof SimpleNode)
            {
                SimpleNode parent = ((SimpleNode) element);
                for (int i = 0; i < parent.jjtGetNumChildren(); i++)
                {
                    if (dontShowMe(parent.jjtGetChild(i)))
                    {
                        continue;
                    }
                    return true;
                }
            }
            return false;
        }

        public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
        {
        }

        private boolean dontGoDown(Object element)
        {
            return (element instanceof IASTSQLParam);
        }

        private boolean dontShowMe(Object element)
        {
            return !(element instanceof IASTDeployable || element instanceof IASTSQLStatement
                || element instanceof IASTSQLParamDefList || element instanceof IASTSQLParam);
        }
    }
    ;

    private class OutlineViewer extends TreeViewer
    {
        public OutlineViewer(Composite parent, int style)
        {
            super(parent, style);
        }

        protected void createTreeItem(Widget parent, Object element, int index)
        {
            super.createTreeItem(parent, element, index);
        }
    }
    ;

    private class TooltipPresenter extends MouseTrackAdapter
    {
        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.swt.events.MouseTrackAdapter#mouseHover(org.eclipse.swt.events.MouseEvent)
         */
        public void mouseHover(org.eclipse.swt.events.MouseEvent e)
        {
            if (_viewer != null && !_viewer.getControl().isDisposed())
            {
                Tree tree = ((Tree) e.widget);
                TreeItem item = tree.getItem(new Point(e.x, e.y));
                if (item != null && item.getData() != null)
                {
                    String tooltip = ((Node) item.getData()).getSQLText();
                    tooltip = SQLUtil.describeSQL(tooltip, EditorConstants.SQL_SHORT_DESC_LENGTH);
                    tree.setToolTipText(tooltip);
                }
                else
                {
                    tree.setToolTipText(null);
                }
            }
        }

    }

    private SQLEditor      _editor;
    private Node           _input;
    private OutlineViewer  _viewer;
    private CCPActionGroup _actionGroups;
    private DeployAction   _deployAction;
    private IAction        _fUndo;
    private IAction        _fRedo;

    public SQLOutlinePage(SQLEditor editor)
    {
        _editor = editor;
        _fUndo = (IAction) _editor.getAction(ITextEditorActionConstants.UNDO);
        _fRedo = (IAction) _editor.getAction(ITextEditorActionConstants.REDO);
    }

    public void createControl(Composite parent)
    {
    	//since tooltip will show sql statements, we must use left to right orientation
        _viewer = new OutlineViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.LEFT_TO_RIGHT); 
        _viewer.addSelectionChangedListener(this);
        _viewer.getControl().addMouseTrackListener(new TooltipPresenter());
        _viewer.setContentProvider(new SQLContentProvider());
        SQLLabelProvider labelProvider = new SQLLabelProvider();
        labelProvider.addLabelDecorator(new SQLMarkerLabelDecorator(_editor));
        _viewer.setLabelProvider(labelProvider);

        MenuManager menuManager = new MenuManager();
        menuManager.setRemoveAllWhenShown(true);
        menuManager.addMenuListener(new IMenuListener()
        {
            public void menuAboutToShow(IMenuManager manager)
            {
                fillMenu(manager);
            }
        }
        );
        Menu contextMenu = menuManager.createContextMenu(_viewer.getControl());
        _viewer.getControl().setMenu(contextMenu);

        getSite().setSelectionProvider(this);

        //we must create the groups after we have set the selection provider to the site
        _actionGroups = new CCPActionGroup(this);
        _deployAction = new DeployAction(this.getSite());
        _deployAction.setImageDescriptor(SQLEditorResources.getImageDescriptor("save_to_database"));
        addSelectionChangedListener(_deployAction);


        // register global actions
        IActionBars bars = getSite().getActionBars();

        bars.setGlobalActionHandler(ITextEditorActionConstants.UNDO, _fUndo);
        bars.setGlobalActionHandler(ITextEditorActionConstants.REDO, _fRedo);
        bars
            .setGlobalActionHandler(ISQLEditorActionConstants.EXPLAIN_SQL_ACTION_ID, _editor
            .getAction(ISQLEditorActionConstants.EXPLAIN_SQL_ACTION_ID));
        bars
            .setGlobalActionHandler(ISQLEditorActionConstants.EXECUTE_SQL_ACTION_ID, _editor
            .getAction(ISQLEditorActionConstants.EXECUTE_SQL_ACTION_ID));
        bars.setGlobalActionHandler(ISQLEditorActionConstants.EXECUTE_SELECTION_SQL_ACTION_ID, _editor
                .getAction(ISQLEditorActionConstants.EXECUTE_SELECTION_SQL_ACTION_ID));
        bars.setGlobalActionHandler(ISQLEditorActionConstants.EXECUTE_SQL_AS_ONE_STATEMENT_ACTION_ID, _editor
                .getAction(ISQLEditorActionConstants.EXECUTE_SQL_AS_ONE_STATEMENT_ACTION_ID));
        bars.setGlobalActionHandler(ISQLEditorActionConstants.EXECUTE_CURRENT_SQL_ACTION_ID, _editor
                .getAction(ISQLEditorActionConstants.EXECUTE_CURRENT_SQL_ACTION_ID));
        bars.setGlobalActionHandler(ISQLEditorActionConstants.DMLDIALOG_SELECTION_SQL_ACTION_ID, _editor
                .getAction(ISQLEditorActionConstants.DMLDIALOG_SELECTION_SQL_ACTION_ID));


        _actionGroups.fillActionBars(bars);

        if (_input != null)
        {
            _viewer.setInput(_input);
            _viewer.expandToLevel(2);
            _viewer.setAutoExpandLevel(2);
        }
    }

    public void setInput(Node node)
    {
        update(node);
    }

    public void update(Node node)
    {
        _input = node;
        if (_viewer == null || _viewer.getControl().isDisposed())
        {
            return;
        }
        Object[] exp = _viewer.getExpandedElements();
        _viewer.setInput(_input);
        if (exp.length > 1)
        {
            ArrayList nexp = new ArrayList();
            for (int i = 0; i < exp.length; i++)
            {
                Node nnode = null;
                if (node.equals(exp[i]))
                {
                    nnode = node;
                }
                else
                {
                    nnode = findNode(node, (Node) exp[i]);
                }
                if (nnode != null)
                {
                    nexp.add(nnode);
                }
            }
            if (nexp.size() > 1)
            {
                _viewer.setExpandedElements(nexp.toArray());
                return;
            }
        }
        _viewer.expandToLevel(2);
        //        if ( exp == null || exp.length == 0){
        //            fViewer.expandAll();
        //        }else{
        //            fViewer.setExpandedElements(exp);
        //        }
    }

    public void setFocus()
    {
        if (_viewer != null)
        {
            _viewer.getControl().setFocus();
        }
    }

    public void dispose()
    {
        super.dispose();
        if (_actionGroups != null)
        {
            _actionGroups.dispose();
        }
        removeSelectionChangedListener(_deployAction);
        _editor.outlinePageClosed();
    }

    public Control getControl()
    {
        return _viewer != null ? _viewer.getControl() : null;
    }

    protected void fillMenu(IMenuManager manager)
    {
        //		IStructuredSelection selection= (IStructuredSelection)getSelection();
        //		_actionGroups.setContext(new ActionContext(selection));
        _actionGroups.fillContextMenu(manager);
        manager.add(new Separator(ISQLEditorActionConstants.GROUP_SQLEDITOR_SOURCE));
        manager.appendToGroup(ISQLEditorActionConstants.GROUP_SQLEDITOR_SOURCE, _editor
            .getAction(ISQLEditorActionConstants.TOGGLE_COMMENT));
        manager.add(new Separator());

        if (_editor.getSQLType() != SQLParserConstants.TYPE_SQL_ROOT)
        {
            manager.add(_deployAction);
        }
        manager.add(_editor
                .getAction(ISQLEditorActionConstants.EXECUTE_SELECTION_SQL_ACTION_ID));
        manager.add(_editor
                .getAction(ISQLEditorActionConstants.EXECUTE_SQL_AS_ONE_STATEMENT_ACTION_ID));
        manager.add(_editor
                .getAction(ISQLEditorActionConstants.EXECUTE_SQL_ACTION_ID));
        manager.add(_editor
                .getAction(ISQLEditorActionConstants.DMLDIALOG_SELECTION_SQL_ACTION_ID));
    }

    public void setSelection(ISelection selection)
    {
        if (_viewer != null)
        {
            _viewer.setSelection(selection);
        }
    }

    /*
     * (non-Javadoc) Method declared on ISelectionProvider.
     */
    public ISelection getSelection()
    {
        if (_viewer == null)
        {
            return StructuredSelection.EMPTY;
        }
        return _viewer.getSelection();
    }

    /**
     * Returns this page's tree viewer.
     * 
     * @return this page's tree viewer, or <code>null</code> if <code>createControl</code> has not been called yet
     */
    protected TreeViewer getTreeViewer()
    {
        return _viewer;
    }

    private Node findNode(Node pnode, Node cnode)
    {
        for (int i = 0; i < pnode.jjtGetNumChildren(); i++)
        {
            if (pnode.jjtGetChild(i).equals(cnode))
            {
                return pnode.jjtGetChild(i);
            }
            else
            {
                Node nnode = findNode(pnode.jjtGetChild(i), cnode);
                if (nnode != null)
                {
                    return nnode;
                }
            }
        }
        return null;
    }
}

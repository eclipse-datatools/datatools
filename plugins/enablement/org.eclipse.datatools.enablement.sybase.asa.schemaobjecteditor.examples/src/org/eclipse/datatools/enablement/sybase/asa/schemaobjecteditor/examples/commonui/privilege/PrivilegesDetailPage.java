/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege;

import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.ExamplePlugin;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils.FilterUtil;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils.PrivilegesUtil;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils.SWTUtilsExt;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.common.ui.tableviewer.Util;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectEditModel;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.forms.AbstractFormPart;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.widgets.FormToolkit;


/**
 * The privileges detail page on privileges block.<br>
 * The user can grant/revoke privileges by change the state of the privilege, that is, select a new state from the combo
 * box.
 * <p>
 * Subclass should extend this class to modify the model based on the user's input.<br>
 * WARN: DONT cache any database object here unless it's a must
 * 
 * @author Idull
 */
public abstract class PrivilegesDetailPage extends AbstractFormPart implements IDetailsPage
{
    private Color _bgColor = null;
    private Color _fgColor = null;

    /**
     * Decorates the owner item
     * 
     * @author Idull
     */
    class StyledTreeViewer extends TreeViewer
    {
        public StyledTreeViewer(Tree tree)
        {
            super(tree);
        }

        protected void doUpdateItem(Item item, Object element)
        {
            super.doUpdateItem(item, element);
            if (!(item instanceof TreeItem))
            {
                return;
            }

            TreeItem treeItem = (TreeItem) item;
            if (treeItem.isDisposed())
            {
                return;
            }

            if (!(treeItem.getData() instanceof FolderNode))
            {
                return;
            }
            FolderNode node = (FolderNode) treeItem.getData();
            if (!(isOwner(node)))
            {
                return;
            }
            FontData[] fds = treeItem.getFont().getFontData();
            if (fds.length > 0)
            {
                FontData fd = new FontData(fds[0].getName(), fds[0].getHeight(), SWT.BOLD);
                Font font = new Font(treeItem.getDisplay(), fd);
                treeItem.setFont(font);
                if(shouldDisableOwner())
                {
                    _bgColor = Util.getBackGroundColor(this.getTree().getDisplay());
                    _fgColor = Util.getForeGroundColor(this.getTree().getDisplay());
                    treeItem.setBackground(_bgColor);
                    treeItem.setForeground(_fgColor);
                }
            }
        }
    }

    FormToolkit                   _toolkit;
    Tree                          _pTableTree;
    TreeViewer                    _pTableTreeViewer;

    /**
     * The SQL object for which the privileges are displayed, should be refreshed after saving the editor
     */
    SQLObject                     _sqlObj;

    /**
     * The edit model, we may get some object from it
     */
    protected ISchemaObjectEditModel        _model;

    PrivilegesDetailTableMetaData _meta;

    IPrivilegesTreeViewerInput    _input;

    IPrivilegeStateLookup         _stateLookup;

    PrivilegesDetailLabelProvider _labelProvider;
    Button                        _grantAllB;
    Button                        _revokeAllB;
    
    Text                          _pattern;

    private static final int      GRANT_OPERATION  = 0;
    private static final int      REVOKE_OPERATION = 1;

    /**
     * Constructs a permission detail page without create the controls
     * 
     * @param toolkit the form toolkit, will be used to create the controls
     * @param model the edit model, some objects will be obtained from it
     * @param sqlObj the SQL object for which the permissions are displayed
     * @param meta the meta data for the detail page
     * @param input the input for the detail page
     * @param stateLookup the lookup for privilege state
     */
    public PrivilegesDetailPage(FormToolkit toolkit, ISchemaObjectEditModel model, SQLObject sqlObj,
            PrivilegesDetailTableMetaData meta, IPrivilegesTreeViewerInput input, IPrivilegeStateLookup stateLookup)
    {
        super();
        _toolkit = toolkit;
        _model = model;
        _sqlObj = sqlObj;
        _meta = meta;
        _input = input;
        _stateLookup = stateLookup;
    }

    public PrivilegesDetailPage(FormToolkit toolkit, ISchemaObjectEditModel model, SQLObject sqlObj,
            PrivilegesDetailTableMetaData meta, IPrivilegesTreeViewerInput input, IPrivilegeStateLookup stateLookup,
            PrivilegesDetailLabelProvider labelProvider)
    {
        this(toolkit, model, sqlObj, meta, input, stateLookup);
        this._labelProvider = labelProvider;
    }

    protected boolean isOwner(FolderNode node)
    {
        if (_sqlObj instanceof Column)
        {
            Column col = (Column) _sqlObj;
            if (col.getTable().getSchema().getName().equals(node.getName()))
            {
                return true;
            }
        }
        else if (_sqlObj instanceof Table)
        {
            Table t = (Table) _sqlObj;
            if (t.getSchema().getName().equals(node.getName()))
            {
                return true;
            }
        }
        else if (_sqlObj instanceof Routine)
        {
            Routine r = (Routine) _sqlObj;
            if (r.getSchema().getName().equals(node.getName()))
            {
                return true;
            }
        }
        return false;
    }

    private void initializeEditorItems(LeafNode node)
    {
        AuthorizationIdentifier authid = (AuthorizationIdentifier) node.getData();
        AuthorizationIdentifier orginalAuthid = getOriginalAuthid(authid, (List) _model.getSchemaObjectImmutableModel()
                .getAdditionalSQLObjects().get(PrivilegesConstants.AUTH_ID_ITEMS));
        if (orginalAuthid == null)
        {
            return;
        }
        CellEditor[] editors = _pTableTreeViewer.getCellEditors();
        if (editors == null)
        {
            return;
        }
        CellEditor currentEditor = null;
        for (int i = 0; i < editors.length; i++)
        {
            currentEditor = editors[i];
            if (i == PrivilegesDetailTableMetaData.GRANTEE_COLUMN)
            {
                continue;
            }
            else
            {
                if (currentEditor != null && (currentEditor instanceof ComboBoxCellEditor))
                {
                    String action = _meta.getAction(_meta.getColumnName(i));
                    IPrivilegeState[] states = getReachableState(authid, action);
                    String[] items = calculateComboItems(authid, action, states);
                    ((ComboBoxCellEditor) currentEditor).setItems(items);
                }
            }
        }
    }

    protected String[] calculateComboItems(AuthorizationIdentifier authid, String action, IPrivilegeState[] states)
    {
        String[] items = new String[states.length];
        for (int j = 0; j < states.length; j++)
        {
            if (states[j].getCode() == IPrivilegeState.EMPTY_PRIVILEGE
                    && _stateLookup.hasInheritedPrivilege((List) _model.getAdditionalSQLObjects().get(
                            PrivilegesConstants.AUTH_ID_ITEMS), authid, _sqlObj, action))
            {
                Privilege[] ps = _stateLookup.getInheritedPrivileges((List) _model.getAdditionalSQLObjects().get(
                        PrivilegesConstants.AUTH_ID_ITEMS), authid, _sqlObj, action);
                items[j] = PrivilegesUtil.getInheritedPrivilegeDspString(ps);
            }
            else if (states[j].getCode() == IPrivilegeState.INHERITED_PRIVILEGE)
            {
                Privilege[] ps = _stateLookup.getInheritedPrivileges((List) _model.getAdditionalSQLObjects().get(
                        PrivilegesConstants.AUTH_ID_ITEMS), authid, _sqlObj, action);
                items[j] = PrivilegesUtil.getInheritedPrivilegeDspString(ps);
            }
            else
            {
                items[j] = states[j].getDisplayString();
            }
        }
        return items;
    }

    protected IPrivilegeState getCurrentState(AuthorizationIdentifier authid, String action)
    {
        SQLObject originalSqlObj = _model.getSchemaObjectImmutableModel().getMainSQLObject();
        if (_sqlObj instanceof Column)
        {
            Iterator iter = ((Table) originalSqlObj).getColumns().iterator();
            while (iter.hasNext())
            {
                Column col = (Column) iter.next();
                if ((col.getName()).equals(_sqlObj.getName()))
                {
                    originalSqlObj = col;
                }
            }
        }
        AuthorizationIdentifier orginalAuthid = getOriginalAuthid(authid, (List) _model.getSchemaObjectImmutableModel()
                .getAdditionalSQLObjects().get(PrivilegesConstants.AUTH_ID_ITEMS));
        if (orginalAuthid == null)
        {
            return null;
        }
        IPrivilegeState currentState = null;

        // For current state, we should get it from the dirty model
        currentState = _stateLookup.getPrivilegeState((List) _model.getAdditionalSQLObjects().get(
                PrivilegesConstants.AUTH_ID_ITEMS), authid, _sqlObj, action);
        return currentState;
    }

    protected IPrivilegeState getInitialState(AuthorizationIdentifier authid, String action)
    {
        SQLObject originalSqlObj = _model.getSchemaObjectImmutableModel().getMainSQLObject();
        if (_sqlObj instanceof Column)
        {
            Iterator iter = ((Table) originalSqlObj).getColumns().iterator();
            while (iter.hasNext())
            {
                Column col = (Column) iter.next();
                if ((col.getName()).equals(_sqlObj.getName()))
                {
                    originalSqlObj = col;
                }

            }
        }
        AuthorizationIdentifier orginalAuthid = getOriginalAuthid(authid, (List) _model.getSchemaObjectImmutableModel()
                .getAdditionalSQLObjects().get(PrivilegesConstants.AUTH_ID_ITEMS));
        if (orginalAuthid == null)
        {
            return null;
        }
        IPrivilegeState initialState = null;

        // For initial state, we should get it from the immutable model
        initialState = _stateLookup.getPrivilegeState((List) _model.getSchemaObjectImmutableModel()
                .getAdditionalSQLObjects().get(PrivilegesConstants.AUTH_ID_ITEMS), orginalAuthid, originalSqlObj,
                action);
        // But for the inherited or revoked inherited state, we should get it from the dirty model
        if (_stateLookup.hasInheritedPrivilege((List) _model.getSchemaObjectImmutableModel().getAdditionalSQLObjects()
                .get(PrivilegesConstants.AUTH_ID_ITEMS), orginalAuthid, originalSqlObj, action)
                && !(_stateLookup.hasInheritedPrivilege((List) _model.getAdditionalSQLObjects().get(
                        PrivilegesConstants.AUTH_ID_ITEMS), authid, _sqlObj, action)))
        {
            initialState = IPrivilegeState.EMPTY_PRIVILEGE_STATE;
        }
        return initialState;
    }

    protected IPrivilegeState[] getReachableState(AuthorizationIdentifier authid, String action)
    {
        AuthorizationIdentifier orginalAuthid = getOriginalAuthid(authid, (List) _model.getSchemaObjectImmutableModel()
                .getAdditionalSQLObjects().get(PrivilegesConstants.AUTH_ID_ITEMS));
        if (orginalAuthid == null)
        {
            return new IPrivilegeState[]
            {};
        }

        return _stateLookup.getReachableStates(getInitialState(authid, action), authid);
    }

    private int getItemIndex(String[] items, String item, boolean isContained)
    {
        if (items == null || items.length == 0)
        {
            return -1;
        }
        if (item == null)
        {
            return -1;
        }
        for (int i = 0; i < items.length; i++)
        {
            if (items[i] == null)
            {
                continue;
            }
            if (isContained)
            {
                if (items[i].indexOf(item) >= 0)
                {
                    return i;
                }
            }
            else
            {
                if (items[i].equals(item))
                {
                    return i;
                }
            }
        }
        return -1;
    }

    private int getReachedIndex(String[] items, int privilegeCode)
    {
        // G or GO or revoke
        int index = getItemIndex(items, IPrivilegeState.PRIVILEGES_DISPLAY_NAME[privilegeCode], false);
        if (index != -1)
        {
            return index;
        }
        // Revoke but no empty state
        else if (privilegeCode == IPrivilegeState.EMPTY_PRIVILEGE)
        {
            // For ASE, the following state transition is allowed: G(I)->RI
            int i = getItemIndex(items,
                    IPrivilegeState.PRIVILEGES_DISPLAY_NAME[IPrivilegeState.REVOKED_INHERITED_PRIVILEGE], false);
            if (i >= 0)
            {
                return i;
            }
            i = getItemIndex(items, IPrivilegeState.PRIVILEGES_DISPLAY_NAME[IPrivilegeState.INHERITED_PRIVILEGE], true);
            return i;
        }
        return -1;
    }

    private boolean canReach(IPrivilegeState[] states, int privilegeCode)
    {
        if (states == null || states.length == 0)
        {
            return false;
        }
        for (int i = 0; i < states.length; i++)
        {
            if (states[i].getCode() == privilegeCode)
            {
                return true;
            }
            // If the destination privilege is empty, then if current state is inherited, it's also reachable
            if (privilegeCode == IPrivilegeState.EMPTY_PRIVILEGE)
            {
                if (states[i].getCode() == IPrivilegeState.INHERITED_PRIVILEGE)
                {
                    return true;
                }
            }
        }
        return false;
    }

    protected void operateAll(final int privilegeCode)
    {
        BusyIndicator.showWhile(ExamplePlugin.getDisplay(), new Runnable()
        {
            public void run()
            {
                doOperateAll(privilegeCode, GRANT_OPERATION);
            }
        });
    }

    protected void operateAll(final int privilegeCode, final int operType)
    {
        BusyIndicator.showWhile(ExamplePlugin.getDisplay(), new Runnable()
        {
            public void run()
            {
                doOperateAll(privilegeCode, operType);
            }
        });
    }

    protected void doOperateAll(int privilegeCode, int operType)
    {
        ISelection sel = _pTableTreeViewer.getSelection();
        if (sel instanceof IStructuredSelection)
        {
            IStructuredSelection ss = (IStructuredSelection) sel;
            Iterator iter = ss.toList().iterator();
            while (iter.hasNext())
            {
                Object obj = iter.next();
                if (!(obj instanceof LeafNode))
                {
                    continue;
                }
                initializeEditorItems((LeafNode) obj);
                for (int i = 0; i < _meta.getColumnsCount(); i++)
                {
                    if (i == PrivilegesDetailTableMetaData.GRANTEE_COLUMN)
                    {
                        continue;
                    }
                    String property = _meta.getColumnName(i);
                    AuthorizationIdentifier authid = (AuthorizationIdentifier) ((LeafNode) obj).getData();
                    String action = _meta.getAction(i);
                    IPrivilegeState[] states = getReachableState(authid, action);
                    String[] items = calculateComboItems(authid, action, states);

                    IPrivilegeState state = getCurrentState(authid, action);

                    // If current state is not GO, can not revoke grant option
                    if (operType == REVOKE_OPERATION && privilegeCode == IPrivilegeState.GRANTED_PRIVILEGE
                            && state.getCode() != IPrivilegeState.GRANTED_WITH_GRANTOPTION_PRIVILEGE)
                    {
                        continue;
                    }

                    if (canReach(states, privilegeCode))
                    {
                        int reachedIndex = getReachedIndex(items, privilegeCode);
                        if (reachedIndex != -1)
                        {
                            if (_pTableTreeViewer.getCellModifier().canModify(obj, property))
                            {
                                _pTableTreeViewer.getCellModifier().modify(obj, property, new Integer(reachedIndex));
                            }
                        }
                    }
                }
            }
        }
    }

    protected void enableButtons(boolean enabled)
    {
        _grantAllB.setEnabled(enabled);
        _revokeAllB.setEnabled(enabled);
    }

    /**
     * Creates controls
     * 
     * @param parent
     */
    public void createContents(Composite parent)
    {
        parent.setLayout(new GridLayout());

        createFilter(parent);
        
        Composite outter = _toolkit.createComposite(parent);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        outter.setLayout(layout);
        outter.setLayoutData(new GridData(GridData.FILL_BOTH));

        _pTableTree = _toolkit.createTree(outter, SWT.FULL_SELECTION | SWT.MULTI | SWT.VIRTUAL);
        _pTableTree.setHeaderVisible(true);
        _pTableTree.setLinesVisible(true);
        _pTableTree
                .setToolTipText(org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.Messages.PrivilegesDetailPage_grant_all_tip);

        Composite buttonComp = _toolkit.createComposite(outter);
        GridData gd = new GridData(GridData.FILL_VERTICAL);
        gd.verticalAlignment = SWT.TOP;
        buttonComp.setLayoutData(gd);
        layout = new GridLayout();
        layout.marginHeight = 0;
        buttonComp.setLayout(layout);

        _grantAllB = _toolkit.createButton(buttonComp,
                org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.Messages.PrivilegesDetailPage_grant_all, SWT.NONE);
        _grantAllB
                .setToolTipText(org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.Messages.PrivilegesDetailPage_grant_all_button_tip);
        _revokeAllB = _toolkit.createButton(buttonComp,
                org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.Messages.PrivilegesDetailPage_revoke_all, SWT.NONE);
        _revokeAllB
                .setToolTipText(org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.Messages.PrivilegesDetailPage_revoke_all_button_tip);
        gd = new GridData();
        gd.widthHint = 70;
        _grantAllB.setLayoutData(gd);
        gd = new GridData();
        gd.widthHint = 70;
        _revokeAllB.setLayoutData(gd);

        _grantAllB.addSelectionListener(new SelectionListener()
        {
            public void widgetDefaultSelected(SelectionEvent e)
            {

            }

            public void widgetSelected(SelectionEvent e)
            {
                if (supportGOAll())
                {
                    MenuManager mgr = new MenuManager();
                    mgr
                            .add(new Action(
                                    org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.Messages.PrivilegesDetailPage_without_grant_option)
                            {
                                public void run()
                                {
                                    operateAll(IPrivilegeState.GRANTED_PRIVILEGE);
                                }
                            });
                    mgr.add(new Action(
                            org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.Messages.PrivilegesDetailPage_with_grant_option)
                    {
                        public void run()
                        {
                            operateAll(IPrivilegeState.GRANTED_WITH_GRANTOPTION_PRIVILEGE);
                        }
                    });
                    Menu menu = mgr.createContextMenu(_grantAllB);
                    menu.setVisible(true);
                }
                else
                {
                    operateAll(IPrivilegeState.GRANTED_PRIVILEGE);
                }
            }
        });

        _revokeAllB.addSelectionListener(new SelectionListener()
        {
            public void widgetDefaultSelected(SelectionEvent e)
            {

            }

            public void widgetSelected(SelectionEvent e)
            {
                if (supportRevokeAllGrantOption())
                {
                    MenuManager mgr = new MenuManager();
                    mgr.add(new Action(
                            org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.Messages.PrivilegesDetailPage_permissions)
                    {
                        public void run()
                        {
                            operateAll(IPrivilegeState.EMPTY_PRIVILEGE);
                        }
                    });
                    mgr.add(new Action(
                            org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.Messages.PrivilegesDetailPage_grant_options)
                    {
                        public void run()
                        {
                            operateAll(IPrivilegeState.GRANTED_PRIVILEGE, REVOKE_OPERATION);
                        }
                    });
                    Menu menu = mgr.createContextMenu(_grantAllB);
                    menu.setVisible(true);
                }
                else
                {
                    operateAll(IPrivilegeState.EMPTY_PRIVILEGE);
                }
            }
        });
        enableButtons(false);
        _pTableTree.addSelectionListener(new SelectionListener()
        {
            public void widgetDefaultSelected(SelectionEvent e)
            {

            }

            public void widgetSelected(SelectionEvent e)
            {
                if (!enableContextMenu())
                {
                    enableButtons(false);
                    return;
                }
                enableButtons(true);
            }
        });

        _toolkit.paintBordersFor(buttonComp);
        createColumns(_pTableTree);

        gd = new GridData(GridData.FILL_BOTH);
        _pTableTree.setLayoutData(gd);

        _pTableTree.addMouseListener(new MouseListener()
        {
            public void mouseDoubleClick(MouseEvent e)
            {

            }

            public void mouseDown(MouseEvent e)
            {
                TreeColumn granteeColumn = _pTableTree.getColumn(0);
                Point p = new Point(granteeColumn.getWidth() / 2, e.y);
                TreeItem item = _pTableTree.getItem(p);
                if (e.x < granteeColumn.getWidth())
                {
                    return;
                }
                if (item == null)
                {
                    // Search to find the right point
                    for (int i = 1; i < granteeColumn.getWidth(); i += 4)
                    {
                        p = new Point(i, e.y);
                        item = _pTableTree.getItem(p);
                        if (item != null)
                        {
                            break;
                        }
                    }
                    if (item == null)
                    {
                        return;
                        // The grantee column is hidden, what should we do?
                    }
                }

                _pTableTree.setSelection(item);
                TreeItem sel = _pTableTree.getSelection()[0];
                if (sel == null)
                {
                    return;
                }
                if (!(sel.getData() instanceof LeafNode))
                {
                    return;
                }
                LeafNode node = (LeafNode) sel.getData();
                initializeEditorItems(node);
            }

            public void mouseUp(MouseEvent e)
            {

            }
        });

        // Add context menu to grant/revoke all privilege for a user/group/role
        MenuManager menuMgr = new MenuManager();
        menuMgr.setRemoveAllWhenShown(true);
        menuMgr.addMenuListener(new IMenuListener()
        {
            public void menuAboutToShow(IMenuManager manager)
            {
                if (!enableContextMenu())
                {
                    return;
                }
                String grantGrp = "grantgroup"; //$NON-NLS-1$
                String revokeGrp = "revokegroup"; //$NON-NLS-1$
                manager.add(new Separator(grantGrp));

                Action grantAllGAction = new Action()
                {
                    public void run()
                    {
                        operateAll(IPrivilegeState.GRANTED_PRIVILEGE);
                    }
                };
                grantAllGAction
                        .setText(org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.Messages.PrivilegesDetailPage_grant_all_context);
                manager.appendToGroup(grantGrp, grantAllGAction);

                Action grantAllGOAction = new Action()
                {
                    public void run()
                    {
                        operateAll(IPrivilegeState.GRANTED_WITH_GRANTOPTION_PRIVILEGE);
                    }
                };
                grantAllGOAction
                        .setText(org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.Messages.PrivilegesDetailPage_grant_all_go_context);
                if (supportGOAll())
                {
                    manager.appendToGroup(grantGrp, grantAllGOAction);
                }

                manager.add(new Separator(revokeGrp));
                Action revokeAllAction = new Action()
                {
                    public void run()
                    {
                        operateAll(IPrivilegeState.EMPTY_PRIVILEGE);
                    }
                };
                revokeAllAction
                        .setText(org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.Messages.PrivilegesDetailPage_revoke_all_context);
                manager.appendToGroup(revokeGrp, revokeAllAction);
                Action revokeAllGOAction = new Action()
                {
                    public void run()
                    {
                        operateAll(IPrivilegeState.GRANTED_PRIVILEGE, REVOKE_OPERATION);
                    }
                };
                revokeAllGOAction
                        .setText(org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.Messages.PrivilegesDetailPage_revoke_grant_options);

                if (supportRevokeAllGrantOption())
                {
                    manager.appendToGroup(revokeGrp, revokeAllGOAction);
                }
            }
        });
        Menu menu = menuMgr.createContextMenu(_pTableTree);
        _pTableTree.setMenu(menu);

        _pTableTreeViewer = new StyledTreeViewer(_pTableTree);
        if (_labelProvider == null)
        {
            _labelProvider = new PrivilegesDetailLabelProvider(_model, _sqlObj, _meta, _stateLookup);
        }
        _pTableTreeViewer.setContentProvider(new LazyTreeViewerContentProvider());
        _pTableTreeViewer.setLabelProvider(_labelProvider);
        _pTableTreeViewer.setUseHashlookup(true);
        _toolkit.paintBordersFor(parent);

        configCellEditors();
        _pTableTreeViewer.setInput(_input.getRoot());
        _pTableTreeViewer.getTree().setItemCount(1);
        _pTableTreeViewer.expandToLevel(2);
    }

    private void createFilter(Composite parent)
    {
        Composite comp = _toolkit.createComposite(parent);
        GridLayout layout = new GridLayout();
        layout.numColumns = 4;
        comp.setLayout(layout);
        comp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        Label label = SWTUtilsExt.createLabel(comp, Messages.PrivilegesDetailPage_filter_grantee, SWT.NONE, _toolkit);
        _pattern = SWTUtilsExt.createText(comp, "", SWT.NONE, _toolkit);
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        _pattern.setLayoutData(gd);
        _pattern.setToolTipText(Messages.PrivilegesDetailPage_filter_tip);
        
        Button filterButton = SWTUtilsExt.createButton(comp, SWT.NONE, _toolkit);
        gd = new GridData();
        gd.widthHint = 70;
        filterButton.setText(Messages.PrivilegesDetailPage_filter);
        
        filterButton.addSelectionListener(new SelectionListener()
        {
            public void widgetDefaultSelected(SelectionEvent e)
            {

            }

            public void widgetSelected(SelectionEvent e)
            {
                _pTableTreeViewer.setInput(getFilteredGrantees(_input, _pattern.getText()));
                _pTableTreeViewer.refresh();
                _pTableTreeViewer.expandToLevel(2);
            }
        });

        Label blankLabel = SWTUtilsExt.createLabel(comp, "", SWT.NONE, _toolkit);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        blankLabel.setLayoutData(gd);
    }
    
    private FolderNode getFilteredGrantees(IPrivilegesTreeViewerInput input, String pattern)
    {
        FolderNode oRoot = ((PrivilegesTreeViewerInput) input).getRoot();
        FolderNode oGrantee = ((PrivilegesTreeViewerInput) input).getGranteeFolder();

        FolderNode nRoot = new FolderNode(oRoot.getName());
        FolderNode nGrantee = new FolderNode(oGrantee.getName());
        nRoot.addChild(nGrantee);
        nGrantee.setParent(nRoot);

        for (int granteeChildCount = 0; granteeChildCount < oGrantee.getChildren().size(); granteeChildCount++)
        {
            FolderNode nGranteeChild = new FolderNode(((FolderNode) oGrantee.getChildren().get(granteeChildCount)).getName());
            nGranteeChild.setParent(nGrantee);
            nGrantee.addChild(nGranteeChild);
        }

        for (int granteeChildCount = 0; granteeChildCount < oGrantee.getChildren().size(); granteeChildCount++)
        {
            FolderNode oGranteeChild = (FolderNode) oGrantee.getChildren().get(granteeChildCount);
            for (int i = 0; i < oGranteeChild.getChildren().size(); i++)
            {
                if (FilterUtil.isMatch(pattern, ((FolderNode) oGranteeChild.getChildren().get(i)).getName()))
                {
                    ((FolderNode) nGrantee.getChildren().get(granteeChildCount)).getChildren().add(
                            oGranteeChild.getChildren().get(i));
                }
            }
        }

        return nRoot;
    }
    
    protected boolean supportGOAll()
    {
        ISelection sel = _pTableTreeViewer.getSelection();
        if (sel instanceof IStructuredSelection)
        {
            IStructuredSelection ss = (IStructuredSelection) sel;
            Iterator iter = ss.toList().iterator();
            while (iter.hasNext())
            {
                Object obj = iter.next();
                if (!(obj instanceof LeafNode))
                {
                    continue;
                }
                LeafNode node = (LeafNode) obj;
                AuthorizationIdentifier authid = (AuthorizationIdentifier) node.getData();
                if (!supportGO(authid))
                {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns if the given user/group/role supports grant option
     * 
     * @param authid
     * @return
     */
    protected boolean supportGO(AuthorizationIdentifier authid)
    {
        return true;
    }

    protected boolean supportRevokeAllGrantOption()
    {
        ISelection sel = _pTableTreeViewer.getSelection();
        if (sel instanceof IStructuredSelection)
        {
            IStructuredSelection ss = (IStructuredSelection) sel;
            Iterator iter = ss.toList().iterator();
            while (iter.hasNext())
            {
                Object obj = iter.next();
                if (!(obj instanceof LeafNode))
                {
                    continue;
                }
                LeafNode node = (LeafNode) obj;
                AuthorizationIdentifier authid = (AuthorizationIdentifier) node.getData();
                if (!supportRevokeGrantOption(authid))
                {
                    return false;
                }
            }
        }
        return true;
    }

    protected boolean enableContextMenu()
    {
        ISelection sel = _pTableTreeViewer.getSelection();
        if (sel instanceof IStructuredSelection)
        {
            IStructuredSelection ss = (IStructuredSelection) sel;
            Iterator iter = ss.toList().iterator();
            while (iter.hasNext())
            {
                Object obj = iter.next();
                if (obj instanceof LeafNode && !(isOwner((LeafNode) obj) && shouldDisableOwner()))
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns if the given user/group/role supports revoking grant option
     * 
     * @param authid
     * @return
     */
    protected boolean supportRevokeGrantOption(AuthorizationIdentifier authid)
    {
        return false;
    }

    public void selectionChanged(IFormPart part, ISelection selection)
    {

    }

    private void createColumns(Tree pTableTree)
    {
        for (int i = 0; i < _meta.getColumnsCount(); i++)
        {
            TreeColumn col = new TreeColumn(pTableTree, SWT.NONE);
            col.setText(_meta.getColumnName(i));
            col.pack();
            col.setWidth(_meta.getColumnLength(i));
        }
    }

    /**
     * Modify the model based on the user's selection
     * 
     * @param authid the authorization identifier
     * @param sqlObj the SQL object
     * @param action the action
     * @param p the privilge which is edited
     * @param selectString the user's selection
     */
    public abstract void modify(AuthorizationIdentifier authid, SQLObject sqlObj, String action, Privilege p,
            String selectString, CellEditor currentEditor, Object value);

    protected AuthorizationIdentifier getOriginalAuthid(AuthorizationIdentifier authid, List authids)
    {
        Iterator iter = authids.iterator();
        while (iter.hasNext())
        {
            AuthorizationIdentifier item = (AuthorizationIdentifier) iter.next();
            if ((item.getName().equals(authid.getName())) && (item.eClass() == authid.eClass()))
            {
                return item;
            }
        }
        return null;
    }

    private void configCellEditors()
    {
        CellEditor editors[] = new CellEditor[_meta.getColumnsCount()];
        String[] properties = new String[_meta.getColumnsCount()];
        CellEditor currentEditor = null;
        for (int i = 0; i < _meta.getColumnsCount(); i++)
        {
            currentEditor = null;
            if (i != PrivilegesDetailTableMetaData.GRANTEE_COLUMN)
            {
                currentEditor = new ComboBoxCellEditor(_pTableTree, new String[]
                {}, SWT.READ_ONLY);
            }
            editors[i] = currentEditor;
            properties[i] = _meta.getColumnName(i);
        }
        _pTableTreeViewer.setCellEditors(editors);
        _pTableTreeViewer.setColumnProperties(properties);
        _pTableTreeViewer.setCellModifier(new ICellModifier()
        {
            public boolean canModify(Object element, String property)
            {
                if (!(element instanceof LeafNode))
                {
                    return false;
                }
                if ((element instanceof LeafNode) && isOwner((FolderNode) element)
                        && shouldDisableOwner())
                {
                    return false;
                }
                if (!property.equals(_meta.getColumnName(PrivilegesDetailTableMetaData.GRANTEE_COLUMN)))
                {
                    return true;
                }
                return false;
            }

            public Object getValue(Object element, String property)
            {
                String action = _meta.getAction(property);

                // ignore the Folder node
                if (!(element instanceof LeafNode))
                {
                    return null;
                }

                // ignore the "Grantee" column
                int index = _meta.getColumnIndex(property);
                if (index <= 0)
                {
                    return null;
                }

                // Calculate the current value
                LeafNode node = (LeafNode) element;
                AuthorizationIdentifier clonedAuthid = (AuthorizationIdentifier) node.getData();
                AuthorizationIdentifier originalAuthid = getOriginalAuthid(clonedAuthid, (List) _model
                        .getSchemaObjectImmutableModel().getAdditionalSQLObjects().get(
                                PrivilegesConstants.AUTH_ID_ITEMS));
                IPrivilegeState state = _stateLookup.getPrivilegeState((List) _model.getAdditionalSQLObjects().get(
                        PrivilegesConstants.AUTH_ID_ITEMS), clonedAuthid, _sqlObj, action);
                String dspString = state.getDisplayString();
                if (state.getCode() == IPrivilegeState.INHERITED_PRIVILEGE)
                {
                    dspString = PrivilegesUtil.getInheritedPrivilegeDspString(PrivilegesUtil.getInheritedPrivilege(
                            (List) _model.getAdditionalSQLObjects().get(PrivilegesConstants.AUTH_ID_ITEMS),
                            clonedAuthid, _sqlObj, action));
                }

                CellEditor[] editors = _pTableTreeViewer.getCellEditors();
                CellEditor currentEditor = editors[index];
                if (currentEditor != null && (currentEditor instanceof ComboBoxCellEditor))
                {
                    String[] items = ((ComboBoxCellEditor) currentEditor).getItems();
                    int selectionIndex = getIndex(items, dspString);
                    if (selectionIndex == -1)
                    {
                        return new Integer(0);
                    }
                    return new Integer(selectionIndex);
                }
                return new Integer(1);
            }

            private int getIndex(String[] items, String item)
            {
                for (int i = 0; i < items.length; i++)
                {
                    if (item.equals(items[i]))
                    {
                        return i;
                    }
                }
                return -1;
            }

            public void modify(Object element, String property, Object value)
            {
                LeafNode node = null;
                if (element instanceof TreeItem)
                {
                    node = (LeafNode) (((TreeItem) element).getData());
                }
                else if (element instanceof LeafNode)
                {
                    node = (LeafNode) element;
                }
                else
                {
                    return;
                }
                AuthorizationIdentifier authid = (AuthorizationIdentifier) node.getData();
                int columnIndex = _meta.getColumnIndex(property);

                if (columnIndex > PrivilegesDetailTableMetaData.GRANTEE_COLUMN && columnIndex < _meta.getColumnsCount())
                {
                    String action = _meta.getAction(property);
                    Privilege p = PrivilegesUtil.getPrivilege(authid, _sqlObj, action);

                    CellEditor[] editors = _pTableTreeViewer.getCellEditors();
                    CellEditor currentEditor = editors[columnIndex];
                    if (currentEditor != null && (currentEditor instanceof ComboBoxCellEditor))
                    {
                        String[] items = ((ComboBoxCellEditor) currentEditor).getItems();
                        int selectedIndex = ((Integer) value).intValue();
                        if (selectedIndex >= 0 && selectedIndex < items.length)
                        {
                            String selectedString = items[selectedIndex];
                            PrivilegesDetailPage.this.modify(authid, _sqlObj, action, p, selectedString, currentEditor,
                                    value);
                        }
                    }
                    _pTableTreeViewer.refresh();
                }
            }
        });
    }

    public TreeViewer getTableTreeViewer()
    {
        return _pTableTreeViewer;
    }

    public IPrivilegesTreeViewerInput getInput()
    {
        return _input;
    }

    protected boolean shouldDisableOwner()
    {
        return false;
    }
    
    public void setSqlObj(SQLObject obj)
    {
        _sqlObj = obj;
        _labelProvider = new PrivilegesDetailLabelProvider(_model, _sqlObj, _meta, _stateLookup);
        _pTableTreeViewer.setLabelProvider(_labelProvider);
    }

    public SQLObject getOriginalSqlObj()
    {
        return _sqlObj;
    }

    public void reInit(ISchemaObjectEditModel model, SQLObject sqlObj, IPrivilegesTreeViewerInput input)
    {
        _model = model;
        _sqlObj = sqlObj;
        _input = input;
        _labelProvider = new PrivilegesDetailLabelProvider(_model, _sqlObj, _meta, _stateLookup);
        _pattern.setText("");
        _pTableTreeViewer.setLabelProvider(_labelProvider);
        _pTableTreeViewer.setInput(_input.getRoot());
        _pTableTreeViewer.refresh();
        _pTableTreeViewer.expandToLevel(2);
    }

    public void dispose()
    {
        super.dispose();
        if (_bgColor != null)
        {
            _bgColor.dispose();
        }
        if (_fgColor != null)
        {
            _fgColor.dispose();
        }
    }

    public void refresh()
    {
        super.refresh();
        
        //Workaround to FIX469806-1, the tree can not refresh itself after resizing
        //Since it's a Eclipse's defect, this should be removed in the future
        _pTableTreeViewer.getTree().getDisplay().timerExec(10, new Runnable()
        {
            public void run()
            {
                TreeColumn col = new TreeColumn(_pTableTree, SWT.NONE);//
                col.setText("");
                col.setResizable(false);
                col.setWidth(0);
                col.dispose();
            }
        });
    }
}

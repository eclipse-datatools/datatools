/*******************************************************************************
 * Copyright © 2000, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.views;

import java.util.Collections;

import org.eclipse.datatools.modelbase.sql.query.QueryCombined;
import org.eclipse.datatools.modelbase.sql.query.QueryDeleteStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValues;
import org.eclipse.datatools.modelbase.sql.query.ValuesRow;
import org.eclipse.datatools.modelbase.sql.query.WithTableSpecification;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilder;
import org.eclipse.datatools.sqltools.sqlbuilder.actions.AddSubFullSelectAction;
import org.eclipse.datatools.sqltools.sqlbuilder.actions.AddSubSelectAction;
import org.eclipse.datatools.sqltools.sqlbuilder.actions.AddTableAction;
import org.eclipse.datatools.sqltools.sqlbuilder.actions.AddValueRowAction;
import org.eclipse.datatools.sqltools.sqlbuilder.actions.AddValuesAction;
import org.eclipse.datatools.sqltools.sqlbuilder.actions.ChangeSetOperatorAction;
import org.eclipse.datatools.sqltools.sqlbuilder.actions.ConvertToFullSelectAction;
import org.eclipse.datatools.sqltools.sqlbuilder.actions.ChangeStatementTypeAction;
import org.eclipse.datatools.sqltools.sqlbuilder.actions.CreateJoinAction;
import org.eclipse.datatools.sqltools.sqlbuilder.actions.CreateWithTableAction;
import org.eclipse.datatools.sqltools.sqlbuilder.actions.DeleteStatementAction;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SelectHelper;
import org.eclipse.emf.edit.provider.ItemProvider;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

/**
 * The Statements tree view
 */
public class SQLTreeViewer extends ContentOutlinePage {

    // Add remove table
    AddTableAction addTableAction;
    //  RemoveTableAction removeTableAction;

    //Create join action - for select
    CreateJoinAction createJoinAction;

    // With table actions
    CreateWithTableAction createWithTableAction;
    ConvertToFullSelectAction convertToFullSelectAction;
    
    // Full select actions
    AddValuesAction addValuesAction;
    AddValueRowAction addValueRowAction;
    AddSubSelectAction addSubSelectAction;
    AddSubFullSelectAction addSubFullSelectAction;
    ChangeSetOperatorAction changeSetOperatorAction;

    // General actions
    DeleteStatementAction deleteStatementAction;
    //ExecuteAction executeStatementAction;
    ChangeStatementTypeAction changeStatementTypeAction;

    MenuManager menu;
    SQLBuilder sqlBuilder;
    SQLDomainModel domainModel;

    IContentProvider contentProvider;
    ILabelProvider labelProvider;
    Object input;

    public SQLTreeViewer(SQLBuilder sqlBuilder, IContentProvider contentProvider, ILabelProvider labelProvider, Object input)

    {
        this.sqlBuilder = sqlBuilder;
        this.domainModel = sqlBuilder.getDomainModel();
        this.contentProvider = contentProvider;
        this.labelProvider = labelProvider;
        this.input = input;

        createActions(this.domainModel);
    }

    public void createControl(Composite parent) {
        super.createControl(parent);

        getTreeViewer().setContentProvider(contentProvider);
        getTreeViewer().setLabelProvider(labelProvider);
        getTreeViewer().setInput(new ItemProvider(Collections.singleton(input)));

        if (sqlBuilder != null) {
            menu = sqlBuilder.createContextMenuFor(getTreeViewer());
        }

        if (input != null) {
            Runnable runnable = new Runnable() {

                public void run() {
                    setSelection(new StructuredSelection(input), true);
                }
            };
            Display.getCurrent().asyncExec(runnable);
        }
    }

    protected void defaultSelect(final Object selInput) {
        if (selInput != null) {
            Runnable runnable = new Runnable() {

                public void run() {
                    setSelection(new StructuredSelection(selInput), true);
                }
            };
            Display.getCurrent().asyncExec(runnable);
        }
    }

    public void resetInput(Object resetInput) {

        if (!isDisposed()) {
            getTreeViewer().setInput(new ItemProvider(Collections.singleton(resetInput)));
            defaultSelect(resetInput);
         }
    }

    public boolean isDisposed() {
        return (getTreeViewer() == null) || getTreeViewer().getControl().isDisposed();
    }

    public void setSelection(ISelection selection, boolean reveal) {
        getTreeViewer().setSelection(selection, reveal);
    }

    /**
     * Return the selection.
     */
    public ISelection getSelection() {
        if (getTreeViewer() != null) {
            return super.getSelection();
        }
        return null;
    }

    private void createActions(SQLDomainModel createActionDomainModel) {
        // SQL Builder specific actions
        addTableAction = new AddTableAction(createActionDomainModel);
        //    removeTableAction = new RemoveTableAction();
        createJoinAction = new CreateJoinAction(createActionDomainModel);
        //executeStatementAction = new ExecuteAction();
        //executeStatementAction.setDomainModel(createActionDomainModel);
        createWithTableAction = new CreateWithTableAction(createActionDomainModel);
        convertToFullSelectAction = new ConvertToFullSelectAction(createActionDomainModel);
        deleteStatementAction = new DeleteStatementAction(createActionDomainModel, this);
        // Change statement type
        changeStatementTypeAction = new ChangeStatementTypeAction();
        changeStatementTypeAction.setSQLBuilder(sqlBuilder);
        changeStatementTypeAction.setShell( Display.getCurrent().getActiveShell() );
        // Fullselect specific actions
        addValuesAction = new AddValuesAction(createActionDomainModel);
        addValueRowAction = new AddValueRowAction(createActionDomainModel);
        addSubSelectAction = new AddSubSelectAction(createActionDomainModel);
        addSubFullSelectAction = new AddSubFullSelectAction(createActionDomainModel);
        // Change set operator type
        changeSetOperatorAction = new ChangeSetOperatorAction();
        changeSetOperatorAction.setSQLBuilder(sqlBuilder);
        changeSetOperatorAction.setShell( Display.getCurrent().getActiveShell() );

    }

    /**
     * Override this method to fill in the popup context menu for each MOF object
     */
    public void fillContextMenu() {
        ISelection selection = this.getSelection();

        if (selection instanceof StructuredSelection) {
            StructuredSelection structuredSelection = (StructuredSelection) selection;
            Object element =  null;
            if(structuredSelection.iterator().hasNext()){
               element = structuredSelection.iterator().next();
            }
            if (element instanceof QuerySelect) {
                //createWithTableAction.setElement(element);  //commented bcz it was causing NPE
                //menu.add(createWithTableAction);

                QuerySelect select = (QuerySelect) element;
                //add Delete menu if the QuerySelect is part of a Full Select
                if (select.getCombinedLeft() != null || select.getCombinedRight() != null) {
                    deleteStatementAction.setElement(element);
                    menu.add(deleteStatementAction);
                    convertToFullSelectAction.setElement(element);
                    menu.add(convertToFullSelectAction);
                }
                if (select.eContainer() instanceof WithTableSpecification) {
                    convertToFullSelectAction.setElement(element);
                    menu.add(convertToFullSelectAction);
                }
            }
            else if (element instanceof QueryCombined) {
                QueryCombined queryCombined = (QueryCombined) element;
                if (!SelectHelper.isNodeFull(queryCombined)) {
                    addSubSelectAction.setElement(element);
                    menu.add(addSubSelectAction);

                    addValuesAction.setElement(element);
                    menu.add(addValuesAction);

                    addSubFullSelectAction.setElement(element);
                    menu.add(addSubFullSelectAction);
                 }
                deleteStatementAction.setElement(element);
                menu.add(deleteStatementAction);
                
                changeSetOperatorAction.setElement(queryCombined);
                menu.add(changeSetOperatorAction);
         
                //        menu.add(new Separator());
                //executeStatementAction.setElement(element);
                //menu.add(executeStatementAction);
                //
                // Only enable the Add With and Add Value action for DB2 family
                //
                if (domainModel.getVendor().isDB2() && !domainModel.getVendor().isDB2UDBOS390_V6()) {
                    addValuesAction.setEnabled(true);
                }
                else {
                    addValuesAction.setEnabled(false);
                }

                // Enable the Add Values action for Cloudscape
                if (domainModel.getVendor().isCloudscape()) {
                    addValuesAction.setEnabled(true);
                }

            }
            else if (element instanceof QueryValues) {
                addValueRowAction.setElement(element);
                menu.add(addValueRowAction);

                menu.add(new Separator());

                deleteStatementAction.setElement(element);
                menu.add(deleteStatementAction);
            }
            else if (element instanceof ValuesRow) {
                deleteStatementAction.setElement(element);
                menu.add(deleteStatementAction);
            }
            else if (element instanceof WithTableSpecification) {
                deleteStatementAction.setElement(element);
                menu.add(deleteStatementAction);
                //executeStatementAction.setElement(element);
                //menu.add(executeStatementAction);
            }
            else if (element instanceof QueryExpressionRoot || element instanceof QuerySelectStatement) {
                createWithTableAction.setElement(element);
                menu.add(createWithTableAction);
                if (element instanceof QuerySelectStatement && SelectHelper.getQuerySelect((QuerySelectStatement) element) != null) {
                    convertToFullSelectAction.setElement(element);
                    menu.add(convertToFullSelectAction);
                }
                menu.add(new Separator());
                menu.add(changeStatementTypeAction);
            }
            else if (element instanceof QueryInsertStatement) {
                addTableAction.setElement(element);
                menu.add(addTableAction);
                menu.add(new Separator());
                menu.add(changeStatementTypeAction);
                //executeStatementAction.setElement(element);
                //menu.add(executeStatementAction);
            }
            else if (element instanceof QueryUpdateStatement) {
                addTableAction.setElement(element);
                menu.add(addTableAction);
                menu.add(new Separator());
                menu.add(changeStatementTypeAction);
                //executeStatementAction.setElement(element);
                //menu.add(executeStatementAction);
            }
            else if (element instanceof QueryDeleteStatement) {
                addTableAction.setElement(element);
                menu.add(addTableAction);
                menu.add(new Separator());
                menu.add(changeStatementTypeAction);
                //executeStatementAction.setElement(element);
                //menu.add(executeStatementAction);
            }
        }
        
        enableMenus();
    }

    private void enableMenus() {
        boolean proper = SQLBuilder.isStatementProper(domainModel);

        addSubFullSelectAction.setEnabled(proper);
        addSubSelectAction.setEnabled(proper);
        addTableAction.setEnabled(proper);
        addValueRowAction.setEnabled(proper);
        addValuesAction.setEnabled(proper);
        createJoinAction.setEnabled(proper);
        createWithTableAction.setEnabled(proper);        
    }

    public void refreshTree() {
        if (getTreeViewer() != null) {
            getTreeViewer().refresh();
        }
    }

    public void selectRootElement() {
        if (getTreeViewer() != null) {
            Tree tree = getTreeViewer().getTree();
            TreeItem items[] = tree.getItems();
            TreeItem item[] = { items[0] };
            tree.setSelection(item);
            setSelection(this.getSelection(), true);
        }
    }

    public boolean isOnlyRootSelected() {
        boolean rootSelected = false;
        
        TreeViewer treeViewer = getTreeViewer();
        if (treeViewer != null) {
            Tree tree = treeViewer.getTree();
            if (tree != null) {
                TreeItem topItem = tree.getTopItem();
                TreeItem selItem = null;
                if (tree.getSelectionCount() == 1) {
                    selItem = tree.getSelection()[0];
                }
                
                if (selItem != null && selItem == topItem) {
                    rootSelected = true;
                }
            }
        }

        return rootSelected;
    }

    public void disableContentOutline() {
        if (getTreeViewer() != null) {
            Tree tree = getTreeViewer().getTree();
            tree.setEnabled(false);
        }
    }

    public void enableContentOutline() {
        if (getTreeViewer() != null && !getTreeViewer().getTree().isEnabled()) {
            getTreeViewer().getTree().setEnabled(true);
        }
    }
}

/*******************************************************************************
 * Copyright 2000, 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.sqlbuilder.views.update;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.XMLDataType;
import org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody;
import org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression;
import org.eclipse.datatools.modelbase.sql.query.UpdateSource;
import org.eclipse.datatools.modelbase.sql.query.UpdateSourceExprList;
import org.eclipse.datatools.modelbase.sql.query.UpdateSourceQuery;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLBuilderConstants;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.model.UpdateHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.VendorHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.util.LabelValuePair;
import org.eclipse.datatools.sqltools.sqlbuilder.util.StringUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.util.WindowUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.util.WorkbenchUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.ComboBoxCellEditor;
import org.eclipse.datatools.sqltools.sqlbuilder.views.DynamicComboBoxCellEditor;
import org.eclipse.datatools.sqltools.sqlbuilder.views.EditComboBoxCellEditor;
import org.eclipse.datatools.sqltools.sqlbuilder.views.GridContentProvider;
import org.eclipse.datatools.sqltools.sqlbuilder.views.Modifier;
import org.eclipse.datatools.sqltools.sqlbuilder.views.TableNavigator;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableTreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.PlatformUI;


/**
 * The Update tree view
 */
public class UpdateTreeViewer extends TableTreeViewer {

    public static final String copyright = "(c) Copyright IBM Corporation 2000, 2002.";
    protected SQLDomainModel domainModel;
    protected UpdateTreeLabelProvider updateTreeLabelProvider;
    protected UpdateTreeContentProvider updateTreeContentProvider;

    final Table table;

    protected TableColumn c1, c2;

    protected DynamicComboBoxCellEditor expressionCellEditor;
    protected ComboBoxCellEditor queryCellEditor;

    protected TableNavigator navigator;
    protected Vector existingSelects;

    public UpdateTreeViewer(SQLDomainModel domainModel, Composite parent) {
        super(parent, SWT.MULTI | SWT.FULL_SELECTION);

        this.domainModel = domainModel;

        table = getTableTree().getTable();

        final Table tbl = getTableTree().getTable();
        PlatformUI.getWorkbench().getHelpSystem().setHelp(tbl, SQLBuilderContextIds.SQDU_SET_TAB);

        navigator = new TableNavigator(tbl, this);
        for (int i = 0; i < 2; i++) {
            TableColumn column = new TableColumn(tbl, SWT.NONE, i);
            column.setText(i == 0 ? Messages._UI_COLUMN_UPDATE_COLUMN : Messages._UI_COLUMN_UPDATE_EXPRESSION);
        }

        String columnProperties[] = { (String) SQLBuilderConstants.P_STATEMENT_COLUMN, (String) SQLBuilderConstants.P_EXPRESSION };

        setColumnProperties(columnProperties);

        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnWeightData(50, true)); // column
        // Ensure column is as big as dropdown combo box, since
        // Expression column is last column in display.
        layout.addColumnData(new ColumnWeightData(50, getMinExpressionWidth(), true)); //expression);   
        tbl.setLayout(layout);

        tbl.setLinesVisible(true);
        tbl.setHeaderVisible(true);
        tbl.setLayoutData(ViewUtility.createFill());

        setCellModifier(new Modifier());

        CellEditor[] editors = { null, null };
        setCellEditors(editors);

        updateTreeContentProvider = new UpdateTreeContentProvider(domainModel);
        this.setContentProvider(updateTreeContentProvider);

        updateTreeLabelProvider = new UpdateTreeLabelProvider();
        this.setLabelProvider(updateTreeLabelProvider);

        addSelectionChangedListener(new SelectionChangedListener());
    }

    // Length in pixel of drop down combo for expression
    protected int getMinExpressionWidth() {
        GC gc = new GC(table);
        // To be completely accurate, really should get max of all strings that could be in
        // combo box. But for performance, we will only do one.
        // This string is the longest - at least in English.  
        int len = gc.textExtent(SQLBuilderConstants.P_ADD_FULLSELECT).x;
        // account for dropdown arrow.
        len += gc.textExtent("M").x;
        gc.dispose();
        return len;
    }

    public void setCellEditors(CellEditor[] editors) {
        super.setCellEditors(editors);
        navigator.moveCellEditorsAbove(editors);
    }

    public void refresh() {
        // setVisible for TableNavigator to false if there are no items 
        //  in the table tree. Necessary to avoid repaint of cursor.
        if (getTableTree().getTable().getItemCount() == 0) {
            navigator.setVisible(false);
        }
        else {
            navigator.setVisible(true);
            navigator.refresh();
            navigator.setSelection(0, 0);
        }
        super.refresh();
    }

    //override setExpanded so that the tree doesn't get collapsed by the framework
    protected void setExpanded(Item node, boolean expand) {
        super.setExpanded(node, true);
    }

    // override getExpanded so that the tree doesn't get collapsed by the framework
    protected boolean getExpanded(Item item) {
        return true;
    }

    class SelectionChangedListener implements ISelectionChangedListener {

        public void selectionChanged(SelectionChangedEvent event) {
            Object selection = WindowUtility.getSelection(event.getSelection());
            if (selection != null) {
                CellEditor[] cellEditors = { null, getCellEditor(selection, 1) };
                setCellEditors(cellEditors);
            }
            // setVisible for TableNavigator to false if there are no items 
            //  in the table tree. Necessary to avoid repaint of cursor.
            if (getTableTree().getTable().getItemCount() <= 0) {
                navigator.setVisible(false);
            }
            else {
                navigator.setVisible(true);
            }
        }

        public ComboBoxCellEditor getQueryComboBoxCellEditor() {
            Vector contentsHolder = new Vector();
            
            contentsHolder.addAll(getExistingQueries());

            contentsHolder.add(new LabelValuePair(SQLBuilderConstants.P_ADD_SELECT, SQLBuilderConstants.P_ADD_SELECT));
            if (VendorHelper.isFullSelectSupported(domainModel.getDatabase())) {
                contentsHolder.add(new LabelValuePair(SQLBuilderConstants.P_ADD_FULLSELECT, SQLBuilderConstants.P_ADD_FULLSELECT));
            }

            LabelValuePair[] comboContents = new LabelValuePair[contentsHolder.size()];
            Iterator contentsIterator = contentsHolder.iterator();
            int counter = 0;
            while (contentsIterator.hasNext()) {
                comboContents[counter] = (LabelValuePair) contentsIterator.next();
                counter++;
            }

            queryCellEditor = new ComboBoxCellEditor(getTableTree().getTable(), comboContents);
            queryCellEditor.getControl().moveAbove(null);
            return queryCellEditor;
        }

        public DynamicComboBoxCellEditor getExprComboBoxCellEditor(boolean isExpr) {
            if (!isExpr) {
                LabelValuePair comboItems[] = { new LabelValuePair(SQLBuilderConstants.P_BUILD_EXPRESSION, SQLBuilderConstants.P_BUILD_EXPRESSION) };
                expressionCellEditor = new DynamicComboBoxCellEditor(getTableTree().getTable(), comboItems, this);
            }
            else {
                LabelValuePair comboItems[] = { new LabelValuePair(SQLBuilderConstants.P_EDIT_EXPRESSION, SQLBuilderConstants.P_EDIT_EXPRESSION),
                        new LabelValuePair(SQLBuilderConstants.P_REPLACE_EXPRESSION, SQLBuilderConstants.P_REPLACE_EXPRESSION) };
                expressionCellEditor = new DynamicComboBoxCellEditor(getTableTree().getTable(), comboItems, this);
            }
            expressionCellEditor.getControl().moveAbove(null);
            return expressionCellEditor;
        }
        
        public DynamicComboBoxCellEditor getExprComboBoxCellEditor(QueryValueExpression expr) {
        	if (expr == null) {
        		LabelValuePair comboItems[] = { new LabelValuePair(SQLBuilderConstants.P_BUILD_EXPRESSION, SQLBuilderConstants.P_BUILD_EXPRESSION) };
                expressionCellEditor = new DynamicComboBoxCellEditor(getTableTree().getTable(), comboItems, this);
            }
            else {            	
                if (expr.getDataType() instanceof XMLDataType ||
                        expr.getDataType() instanceof CharacterStringDataType) {
                    LabelValuePair comboItems[] = { new LabelValuePair(SQLBuilderConstants.P_EDIT_EXPRESSION, SQLBuilderConstants.P_EDIT_EXPRESSION),
                            new LabelValuePair(SQLBuilderConstants.P_REPLACE_EXPRESSION, SQLBuilderConstants.P_REPLACE_EXPRESSION),
                            new LabelValuePair(SQLBuilderConstants.P_EDIT_INPUT_VALUE, SQLBuilderConstants.P_EDIT_INPUT_VALUE)};
                    expressionCellEditor = new DynamicComboBoxCellEditor(getTableTree().getTable(), comboItems, this);
                }
                else{
                    LabelValuePair comboItems[] = { new LabelValuePair(SQLBuilderConstants.P_EDIT_EXPRESSION, SQLBuilderConstants.P_EDIT_EXPRESSION),
                            new LabelValuePair(SQLBuilderConstants.P_REPLACE_EXPRESSION, SQLBuilderConstants.P_REPLACE_EXPRESSION) };
                    expressionCellEditor = new DynamicComboBoxCellEditor(getTableTree().getTable(), comboItems, this);
                }
            }
            expressionCellEditor.getControl().moveAbove(null);
            return expressionCellEditor;
        }

        public CellEditor getCellEditor(Object o, int col) {
            CellEditor result = null;

            if (col == 1) {
                UpdateTreeElement treeElement = (UpdateTreeElement) o;
               
                // Only allow subselect for group entry -
                // This is the way it is in RAD V6, even though SQL supports
                // subselect for an individual column entry
                if (treeElement.getRDBColumn() == null) {
                    result = getQueryComboBoxCellEditor();
                }
                //TODO: Figure out what is trying to be prevented for Oracle
                else if (treeElement.getUpdateSource() instanceof UpdateSourceQuery && treeElement.getRDBColumn() != null && domainModel.getVendor().isOracle()) {
                    result = null;
                }
                else {
                    if (treeElement.getUpdateSource() != null) {

                        QueryUpdateStatement statement = treeElement.getUpdateStatement();
                        ValueExpressionColumn column = treeElement.getRDBColumn();
                        QueryValueExpression expr = UpdateHelper.getValueForColumn(statement, column);

                        if (expr != null && !(StringUtility.stripTrailingBlanks(expr.toString())).equals("")) {
                            //result = getExprComboBoxCellEditor(true);
                            result = getExprComboBoxCellEditor(column);
                        }
                        else {
                            result = getExprComboBoxCellEditor(false);
                        }
                    }
                    else {
                        result = getExprComboBoxCellEditor(false);
                    }
                    //***  Added to append single quotes
                    //b370 nb 20040729 - begin
                    UpdateTreeElement updTE = (UpdateTreeElement) o;
                    ValueExpressionColumn colExpr = updTE.getRDBColumn();
                    ((EditComboBoxCellEditor) result).setNeedQuotes(true);
                    ((EditComboBoxCellEditor) result).setQuotesContext("update");
                    if (colExpr != null) {
                        ((EditComboBoxCellEditor) result).setPairDataType(colExpr.getDataType());
                    }
                    //b370 nb 20040729 - end
                    //***  End - Added to append single quotes
                }
            }
            return result;
        }
    }

    /**
     * UpdateTree content provider
     */
    public class UpdateTreeContentProvider extends GridContentProvider {

        public UpdateTreeContentProvider(SQLDomainModel domainModel) {
            super(domainModel.getAdapterFactory());
        }

        //QMP-RK
        /**
         * This method is repeatedly called to get instances of UpdateTreeElement representing all the atomic predicates
         * in the assignment clause of the given update statement.UpdateTreeElement can represent either a single predicate
         * or a group of predicates.
         */
        public Object[] getElements(Object object) {
        	
        	// This is done is super class AdapterFactoryContentProvider.getElements(), but
        	// since this method does not call the superclass method, it must be done here
        	// as well.  Without this call, notifications will not be enabled for the SQL object
            adapterFactory.adapt(object, IStructuredItemContentProvider.class);
            
            tableElements = new Vector();
            if (object instanceof QueryUpdateStatement) {
                QueryUpdateStatement update = (QueryUpdateStatement) object;
                List assignExprList = update.getAssignmentClause();
                if (assignExprList != null) {
                    Iterator assignExprListItr = assignExprList.iterator();
                    UpdateAssignmentExpression assignExpr;
                    UpdateSource updateSource;
                    while (assignExprListItr.hasNext()) {
                        assignExpr = (UpdateAssignmentExpression) assignExprListItr.next();
                        if (assignExpr != null) {
                        	adapterFactory.adapt(assignExpr, IStructuredItemContentProvider.class);
                            updateSource = assignExpr.getUpdateSource();
                            List colList = assignExpr.getTargetColumnList();
                            // if the assingment expression updates only one column
                            // create the UpdateTreeElement instnace representing a single predicate (by setting its
                            // hasChildren to false)  
                            if (colList != null && colList.size() == 1) {
                                ValueExpressionColumn column = (ValueExpressionColumn) colList.get(0);
                                if (updateSource instanceof UpdateSourceQuery) {
                                    QueryExpressionBody queryExpr = ((UpdateSourceQuery) updateSource).getQueryExpr();
                                    createNewUpdateListElement(update, updateSource, column, queryExpr, false);
                                }
                                else if (updateSource instanceof UpdateSourceExprList) {
                                    List exprList = ((UpdateSourceExprList) updateSource).getValueExprList();
                                    QueryValueExpression value = (QueryValueExpression) exprList.get(0);
                                    createNewUpdateListElement(update, updateSource, column, value, false);
                                }
                            }
                            // create UpdateTreeElement representing a group of predicates
                            else {
                                createNewUpdateListElement(update, updateSource, null, null, true);
                            }
                        }

                    }
                }
            }
            else if (object instanceof UpdateTreeElement) {
                UpdateTreeElement treeElement = (UpdateTreeElement) object;
                QueryUpdateStatement update = treeElement.getUpdateStatement();
                UpdateSource updateSource = treeElement.getUpdateSource();
                // if this UpdateTreeElement instance represens a group of predicates, 
                // create UpdateTreeElement instances for each of the predicates in the group
                if (treeElement.hasChildren() && updateSource != null) {
                    UpdateAssignmentExpression assignExpr = updateSource.getUpdateAssignmentExpr();
                    List colList = assignExpr.getTargetColumnList();
                    if (colList.size() > 1) {
                        Iterator columnListItr = colList.iterator();

                        if (updateSource instanceof UpdateSourceExprList) {
                            List values = ((UpdateSourceExprList) updateSource).getValueExprList();
                            Iterator valuesItr = values.iterator();
                            while (columnListItr.hasNext()) {
                                ValueExpressionColumn col = (ValueExpressionColumn) columnListItr.next();
                                QueryValueExpression value = (QueryValueExpression) valuesItr.next();
                                createNewUpdateListElement(update, updateSource, col, value, false);
                            }
                        }
                        else if (updateSource instanceof UpdateSourceQuery) {
                            while (columnListItr.hasNext()) {
                                ValueExpressionColumn col = (ValueExpressionColumn) columnListItr.next();
                                createNewUpdateListElement(update, updateSource, col, null, false);
                            }
                        }
                    }
                }
            }
            return tableElements.toArray();
        }

        /**
         * Make a new UpdateTree Element
         */
        private UpdateTreeElement createNewUpdateListElement(QueryUpdateStatement update, UpdateSource updateSource, ValueExpressionColumn col,
                SQLQueryObject source, boolean hasChildren) {
            UpdateTreeElement newTreeElement = new UpdateTreeElement(update, updateSource, col, source, domainModel, hasChildren);
            tableElements.add(newTreeElement);
            return newTreeElement;
        }

        public boolean hasChildren(Object element) {
            if (element instanceof UpdateTreeElement) {
                return ((UpdateTreeElement) element).hasChildren();
            }
            return false;
        }

        public Object[] getChildren(Object element) {
            return getElements(element);
        }
    }

    /**
     * Update grid label provider
     */

    public class UpdateTreeLabelProvider extends LabelProvider implements ITableLabelProvider {

        public String getColumnText(Object object, int columnIndex) {
            if (object instanceof UpdateTreeElement) {
                UpdateTreeElement element = (UpdateTreeElement) object;
                return element.getColumnText(columnIndex);
            }
            return "";
        }

        public Image getColumnImage(Object object, int columnIndex) {
            //TODO need image for column
            /*      if (object instanceof UpdateTreeElement)
             {
             UpdateTreeElement treeElement = (UpdateTreeElement)object;
             //         if (treeElement.getSQLUpdateList() instanceof SQLUpdateQuery &&
             if (treeElement.getUpdateSource() instanceof QueryExpressionBody &&
             treeElement.getRDBColumn() == null)
             {
             return SQLBuilderPlugin.getPlugin().getImage(SQLResource.SQL_COLUMN_GROUP);
             }
             else
             {
             return SQLBuilderPlugin.getPlugin().getImage(SQLResource.RDB_COLUMN);
             }
             }
             */return null;
        }
    }

    public void setEnabled(boolean enable) {
        if (enable) {
            table.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
        }
        else {
            Control control = table.getParent().getParent();
            table.setBackground(control.getBackground());
        }
    }
    
    private Vector getExistingQueries() {

		if (existingSelects == null) {
			existingSelects = new Vector();
			IProject project = domainModel.getProject();
			List stmtsList = WorkbenchUtility.getSelectStatementsFromProject(
					project, domainModel);
			Iterator stmtsItr = stmtsList.iterator();
			String name;
			IFile file;
			String extension;
			while (stmtsItr.hasNext()) {
				file = (IFile) stmtsItr.next();
				name = file.getName();
				extension = file.getFileExtension();
				name = name.substring(0, name
						.indexOf(extension)-1); //strip the .sql  or .dll part
				existingSelects.add(new LabelValuePair(name, file));
			}
		}
		return existingSelects;

	}

}
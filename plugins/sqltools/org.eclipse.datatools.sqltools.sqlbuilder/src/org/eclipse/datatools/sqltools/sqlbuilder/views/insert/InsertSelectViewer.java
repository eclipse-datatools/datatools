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
package org.eclipse.datatools.sqltools.sqlbuilder.views.insert;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot;
import org.eclipse.datatools.modelbase.sql.query.QueryInsertStatement;
import org.eclipse.datatools.modelbase.sql.query.TableExpression;
import org.eclipse.datatools.modelbase.sql.query.TableInDatabase;
import org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn;
import org.eclipse.datatools.modelbase.sql.query.helper.TableHelper;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.model.InsertHelper;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLBuilderConstants;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.util.ViewUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.GridContentProvider;
import org.eclipse.datatools.sqltools.sqlbuilder.views.GridViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.ObjectListHelper;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.PlatformUI;


/**
 * Insert Statement Contents - Subquery Pane
 */

public class InsertSelectViewer extends ContentViewer {

    QueryInsertStatement insert;
    SQLDomainModel domainModel;

    Composite canvas;
    org.eclipse.swt.widgets.List columnList;
    ObjectListHelper columnListHelper;
    int selectionNumber = 0;
    String[] listSelections;

    Button addButton;
    Button addAllButton;
    Button removeButton;
    Button removeAllButton;

    ColumnListGridViewer columnGrid;

    public InsertSelectViewer(SQLDomainModel domain) {
        domainModel = domain;
        insert = null;
        setContentProvider(domainModel.createContentProvider());
    }

    public Control createControl(Composite parent) {
        SashForm sashForm = new SashForm(parent, SWT.HORIZONTAL);
        canvas = sashForm;

        Composite listPanel = ViewUtility.createComposite(canvas, 2, true);
        // ratio 0.45
        listPanel.setData("layout ratio", new Long((((long) 45 << 16) + 99) / 100)); //$NON-NLS-1$
        columnList = new org.eclipse.swt.widgets.List(listPanel, SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI | SWT.BORDER);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(columnList, SQLBuilderContextIds.SQLB_INSERT_VIEW);
        GridData gridData = ViewUtility.createFill();
        gridData.horizontalSpan = 2;
        gridData.widthHint = 10;
        columnList.setLayoutData(gridData);
        columnListHelper = new ObjectListHelper(columnList);
        ColumnListListener columnListListener = new ColumnListListener();
        columnList.addListener(SWT.Selection, columnListListener);

        Composite buttonPanel = ViewUtility.createComposite(canvas, 1, true);
        // ratio 0.10
        buttonPanel.setData("layout ratio", new Long((((long) 10 << 16) + 99) / 100)); //$NON-NLS-1$

        addButton = ViewUtility.createPushButton(buttonPanel, ">"); //$NON-NLS-1$
        SelectListener addButtonListener = new SelectListener();
        addButton.addSelectionListener(addButtonListener);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(addButton, SQLBuilderContextIds.SQLB_INSERT_VIEW);

        removeButton = ViewUtility.createPushButton(buttonPanel, "<"); //$NON-NLS-1$
        SelectListener removeButtonListener = new SelectListener();
        removeButton.addSelectionListener(removeButtonListener);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(removeButton, SQLBuilderContextIds.SQLB_INSERT_VIEW);

        addAllButton = ViewUtility.createPushButton(buttonPanel, ">>"); //$NON-NLS-1$
        SelectListener addAllButtonListener = new SelectListener();
        addAllButton.addSelectionListener(addAllButtonListener);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(addAllButton, SQLBuilderContextIds.SQLB_INSERT_VIEW);

        removeAllButton = ViewUtility.createPushButton(buttonPanel, "<<"); //$NON-NLS-1$
        SelectListener removeAllButtonListener = new SelectListener();
        removeAllButton.addSelectionListener(removeAllButtonListener);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(removeAllButton, SQLBuilderContextIds.SQLB_INSERT_VIEW);

        Composite colGroup = ViewUtility.createComposite(canvas, 2, true);
        // ratio 0.45
        colGroup.setData("layout ratio", new Long((((long) 45 << 16) + 99) / 100)); //$NON-NLS-1$
        PlatformUI.getWorkbench().getHelpSystem().setHelp(colGroup, SQLBuilderContextIds.SQLB_INSERT_VIEW);

        columnGrid = new ColumnListGridViewer(domainModel, colGroup);
        gridData = ViewUtility.createFill();
        gridData.horizontalSpan = 2;
        columnGrid.getTable().setLayoutData(gridData);
        SelectionChangedListener gridListener = new SelectionChangedListener();
        columnGrid.addSelectionChangedListener(gridListener);
        
        int[] weigths = {45, 10,45};
        sashForm.setWeights(weigths);
        
        hookControl(canvas);
        return getControl();
    }

    public Control getControl() {
        return canvas;
    }

    public void setInput(Object input) {
        // SQLBuilderPlugin.getPlugin().getMsgLogger().write("InsertSelectViewer setInput()");
        insert = (QueryInsertStatement) input;
        populateColumnList();
        columnGrid.setInput(input);
        super.setInput(input);
    }

    public Object getInput() {
        return insert;
    }

    public void refresh() {
        populateColumnList();
    }

    public void inputChanged(Object element, Object oldElement) {
        super.inputChanged(element, oldElement);
    }

    protected void populateColumnList() {
        TableExpression tableExpr = insert.getTargetTable();
        if (tableExpr != null) {
            Vector availCol = new Vector();
            Iterator tableColumns = tableExpr.getColumnList().iterator();
            while (tableColumns.hasNext()) {
                ValueExpressionColumn colExpr = (ValueExpressionColumn) tableColumns.next();
                String name = colExpr.getName();
                ValueExpressionColumn tempCol = InsertHelper.getColumnExpressionForName(insert, name);
                if (tempCol == null) {
                    availCol.add(colExpr.getName());
                }
            }
            String[] listColumns = new String[availCol.size()];
            Iterator addColumns = availCol.iterator();
            int count = 0;
            while (addColumns.hasNext()) {
                String nextCol = (String) addColumns.next();
                listColumns[count] = nextCol;
                count++;
            }
            columnListHelper.removeAll();
            columnList.setItems(listColumns);

        }
        else {
            columnListHelper.removeAll();
        }
        columnListHelper.deselectAll();
        addButton.setEnabled(false);
        removeButton.setEnabled(false);
        if (columnList.getItemCount() == 0) {
            addAllButton.setEnabled(false);
        }
        else {
            addAllButton.setEnabled(true);
        }

        removeAllButton.setEnabled(false);
        QueryExpressionRoot expr = insert.getSourceQuery();
        if (expr != null) //is furhter cheking of columns needed ?
        {
            removeAllButton.setEnabled(true);
        }
    }

//    public void selectionChanged(SelectionChangedEvent event) {
//    }
//
//    public void notifySelectionChanged(SelectionChangedEvent event) {
//    }

    public ISelection getSelection() {
        return null;
    }

    public void setSelection(ISelection selection, boolean reveal) {
    }

    class SelectListener implements SelectionListener {

        public void widgetDefaultSelected(SelectionEvent e) {

        }

        public void widgetSelected(SelectionEvent e) {
            TableExpression tableExpr = insert.getTargetTable();
            Column selectedColumn = null;
            if (e.widget == addButton) {
                if (tableExpr instanceof TableInDatabase) {
                	TableInDatabase tableInDB = (TableInDatabase) tableExpr;
                	for (int i=0;i<listSelections.length;i++){
                		selectedColumn = TableHelper.getColumnForName(tableInDB, listSelections[i]);
                		EditingDomain editDomain = domainModel.getEditingDomain();
                        InsertHelper.addDefaultInsertValue(editDomain, insert, selectedColumn);
                	}
                }
            }
            else if (e.widget == addAllButton) {
                String[] columnListNames = columnList.getItems();
                int itemCount = columnListNames.length;
                for (int i = 0; i < itemCount; i++) {
                    if (tableExpr instanceof TableInDatabase) {
                        TableInDatabase tableInDB = (TableInDatabase) tableExpr;
                        selectedColumn = TableHelper.getColumnForName(tableInDB, columnListNames[i]);
                    }
                    if (selectedColumn != null) {
                        EditingDomain editDomain = domainModel.getEditingDomain();
                        InsertHelper.addDefaultInsertValue(editDomain, insert, selectedColumn);
                    }
                }

            }
            else if (e.widget == removeButton) {
                Object gridSelection = ((StructuredSelection) columnGrid.getSelection()).getFirstElement();
                if (gridSelection instanceof ValueExpressionColumn) {
                    ValueExpressionColumn column = (ValueExpressionColumn) gridSelection;
                    String columnName = column.getName();
                    Iterator columnListItr = insert.getTargetColumnList().iterator();
                    ValueExpressionColumn valueExprCol;
                    while (columnListItr.hasNext()) {
                        valueExprCol = (ValueExpressionColumn) columnListItr.next();
                        if (valueExprCol.getName() == columnName) {
                            InsertHelper.removeColumn(insert, valueExprCol);
                            break;
                        }
                    }
                }
                InsertHelper.refresh(insert);
            }
            else if (e.widget == removeAllButton) {
                insert.getTargetColumnList().clear();
                InsertHelper.refresh(insert);
            }

        }
    }

    class ColumnListListener implements Listener {

        public void handleEvent(Event e) {
            selectionNumber = columnListHelper.getSelectionCount();
            listSelections = columnListHelper.getSelection();
            if (selectionNumber > 0) {
                addButton.setEnabled(true);
            }
            else if (selectionNumber <= 0) {
                addButton.setEnabled(false);
            }
        }
    }

    class SelectionChangedListener implements ISelectionChangedListener {

        public void selectionChanged(SelectionChangedEvent event) {
            Object selection = columnGrid.getSelection();
            if (selection instanceof Column) {
            }
            removeButton.setEnabled(true);
        }
    }

    public class ColumnListGridViewer extends GridViewer implements IMenuListener {

        public ColumnListGridViewer(SQLDomainModel domainModel, Composite parent) {
            super(domainModel, parent);

            TableLayout layout = new TableLayout();
            layout.addColumnData(new ColumnWeightData(100, true)); // column name

            table.setLayout(layout);

            // Create the column properties
            String columnProperties[] = { (String) SQLBuilderConstants.P_STATEMENT_COLUMN, };
            setColumnProperties(columnProperties);

            this.setContentProvider(new ColumnContentProvider(domainModel));
            this.setLabelProvider(new ColumnLabelProvider());
        }

        public void menuAboutToShow(IMenuManager menu) {
        }

        public void setEnabled(boolean enable) {
            table.setEnabled(enable);
        }
    }

    /*
     public class ColumnModifier implements ICellModifier
     {
     public boolean canModify(Object element, String property)
     {
     // grid is readonly
     return false;
     }

     public Object getValue(Object element, String property)
     {
     // This implicitly uses the element's toString method
     return element;
     }

     Object currentData, currentProperty, currentValue;
     public void modify(Object element,
     String property,
     Object value)
     {
     // not sure that need anything in here since user can't edit grid
     //  removal of items will be done via model and reflected in the grid
     }

     protected void updateValue(Object data, SQLExpression newExpression)
     {

     }
     }
     */
    class ColumnLabelProvider extends LabelProvider implements ITableLabelProvider {

        public String getColumnText(Object object, int columnIndex) {
            if (columnIndex == 0) {
                return ((ValueExpressionColumn) object).getName();
            }
            return ""; //$NON-NLS-1$
        }

        public Image getColumnImage(Object object, int column) {
            return null;
        }

    }

    public class ColumnContentProvider extends GridContentProvider {

        public ColumnContentProvider(SQLDomainModel domainModel) {
            super(domainModel.getAdapterFactory());
        }

        public Object[] getElements(Object object) {
        	
        	// This is done is super class AdapterFactoryContentProvider.getElements(), but
        	// since this method does not call the superclass method, it must be done here
        	// as well.  Without this call, notifications will not be enabled for the SQL object
            adapterFactory.adapt(object, IStructuredItemContentProvider.class);
            
            tableElements = new Vector();
            if (object instanceof QueryInsertStatement) {
                QueryInsertStatement insertStmt = (QueryInsertStatement) object;
                List colList = insertStmt.getTargetColumnList();
                tableElements.addAll(colList);
                if (tableElements.size() > 0) {
                    removeAllButton.setEnabled(true);
                }
            }
            return tableElements.toArray();
        }
    }

    public void setEnabled(boolean enable) {
        columnGrid.setEnabled(enable);
    }
}

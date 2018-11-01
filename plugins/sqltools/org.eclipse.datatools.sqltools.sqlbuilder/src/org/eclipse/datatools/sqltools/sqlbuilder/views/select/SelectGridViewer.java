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

package org.eclipse.datatools.sqltools.sqlbuilder.views.select;

import java.util.List;

import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryValueExpression;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderContextIds;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLResource;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLBuilderConstants;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.datatools.sqltools.sqlbuilder.util.LabelValuePair;
import org.eclipse.datatools.sqltools.sqlbuilder.views.BuilderUtility;
import org.eclipse.datatools.sqltools.sqlbuilder.views.DynamicComboBoxCellEditor;
import org.eclipse.datatools.sqltools.sqlbuilder.views.EditComboBoxCellEditor;
import org.eclipse.datatools.sqltools.sqlbuilder.views.GridViewer;
import org.eclipse.datatools.sqltools.sqlbuilder.views.Modifier;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerRow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.PlatformUI;

/**
 * Grid viewer for select statement
 */
public class SelectGridViewer extends GridViewer implements IMenuListener {

    TableColumn c2, c3, c4, c5;
    SelectGridLabelProvider selectGridLabelProvider;
    SortOrderComboBoxCellEditor sortOrderCellEditor;
    DynamicComboBoxCellEditor columnComboBoxCellEditor; // we don't need to extend GridViewer

    public static final String P_UNSORTED = Messages._UI_COMBO_SORT_UNSORTED;
    public static final String P_ASCENDING = Messages._UI_COMBO_SORT_ASCENDING;
    public static final String P_DESCENDING = Messages._UI_COMBO_SORT_DESCENDING;
    public static final String P_DEFAULT = Messages._UI_COMBO_SORT_DEFAULT;

    public SelectGridViewer(SQLDomainModel domainModel, Composite parent) {
        super(domainModel, parent);

        PlatformUI.getWorkbench().getHelpSystem().setHelp(table, SQLBuilderContextIds.SQDS_COLUMNS_TAB_GRID);

        c2 = new TableColumn(table, SWT.NULL);
        c2.setText(Messages._UI_COLUMN_SELECT_ALIAS);
        columnComboBoxCellEditor = new DynamicComboBoxCellEditor(table, null, this);

        c3 = new TableColumn(table, SWT.NULL);
        c3.setText(Messages._UI_COLUMN_SELECT_OUTPUT);

        c4 = new TableColumn(table, SWT.NULL);
        c4.setText(Messages._UI_COLUMN_SELECT_SORT_TYPE);

        c5 = new TableColumn(table, SWT.NULL);
        c5.setText(Messages._UI_COLUMN_SELECT_SORT_ORDER);

        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnPixelData(200)); // column name
        layout.addColumnData(new ColumnPixelData(80)); // alias
        layout.addColumnData(new ColumnPixelData(60)); // Output
        layout.addColumnData(new ColumnPixelData(90)); // Sort Type
        layout.addColumnData(new ColumnPixelData(90)); // Sort Order

        table.setLayout(layout);

        // Create the column properties
        String columnProperties[] = { (String) SQLBuilderConstants.P_STATEMENT_COLUMN, (String) SQLBuilderConstants.P_STATEMENT_ALIAS,
                (String) SQLBuilderConstants.P_STATEMENT_OUTPUT, (String) SQLBuilderConstants.P_STATEMENT_SORTTYPE,
                (String) SQLBuilderConstants.P_STATEMENT_SORTORDER };
        setColumnProperties(columnProperties);

        sortOrderCellEditor = new SortOrderComboBoxCellEditor(table);

        // Create the cell editors
        CellEditor editors[] = { columnComboBoxCellEditor, new AliasCellEditor(table), new OutputCheckboxCellEditor(table),
                new SortTypeComboBoxCellEditor(table), sortOrderCellEditor };
        setCellEditors(editors);

        setCellModifier(new Modifier());

        SelectGridContentProvider gridContentProvider = new SelectGridContentProvider(domainModel);

        setContentProvider(gridContentProvider);
        selectGridLabelProvider = new SelectGridLabelProvider();
        setLabelProvider(selectGridLabelProvider);

        // BZ 202596 remove call to hookControl as it's called from
        // the base class constructor TableViewer(Table table)
        //hookControl(table);
    }
    
    public QueryStatement getCurrentStatement() {
        return currentStatement;
    }
    
    public void refreshCellEditor(int row) {
        boolean resultExprExistsExpression = false;

        Object obj = getElementAt(row);
        if (obj instanceof SelectTableElement) {
            QueryValueExpression sqlExpr = ((SelectTableElement) obj).getSQLExpression();
            if (sqlExpr != null) {
                resultExprExistsExpression = true;
            }
        }
        if (getInput() instanceof QueryStatement || getInput() instanceof QuerySelect) {
            BuilderUtility.fillColumnComboBox((EditComboBoxCellEditor) columnComboBoxCellEditor, (SQLQueryObject) getInput(), true, resultExprExistsExpression);
        }

        CellEditor editors[] = { columnComboBoxCellEditor, new AliasCellEditor(table), new OutputCheckboxCellEditor(table),
                new SortTypeComboBoxCellEditor(table), sortOrderCellEditor };

        setCellEditors(editors);
    }

    /**
     * The context menu is about to appear.  Populate it.
     */
    public void menuAboutToShow(IMenuManager menu) {
        RemoveSelectColumnAction removeColumnAction = new RemoveSelectColumnAction(this);
        RemoveOrderByExpressionAction removeOrderByExpressionAction = new RemoveOrderByExpressionAction(this);
        menu.add(removeColumnAction);
        menu.add(removeOrderByExpressionAction);
    }

    protected void inputChanged(java.lang.Object input, java.lang.Object oldInput) {
        super.inputChanged(input, oldInput);
        setGridTitle();
    }

    public void refresh() {
        super.refresh();
    }

    private void setGridTitle() {

    }

	/**
	 * Hook up the editing support. This overrides base class ColumnViewer's implementation
	 * which responds to mouseDown events. This implementation responds to mouseUp events
	 * so that there is no duplication of responses with TableNavigator, which also
	 * responds to mouseUp.
	 *
	 * @param control
	 *            the control you want to hook on
	 */
	protected void hookEditingSupport(Control control) {

		if (getColumnViewerEditor() != null) {
			control.addMouseListener(new MouseAdapter() {
				public void mouseUp(MouseEvent e) {
					// Workaround for bug 185817
					if( e.count != 2 ) {
						handleMouseUp(e);
					}
				}

				public void mouseDoubleClick(MouseEvent e) {
					handleMouseUp(e);
				}
			});
		}
	}
    
    /*
     * Copied from ColumnViewer.handleMouseDown - required so it can be called
     * from hookEditingSupport's mouseListener
     * @param e
     */
	private void handleMouseUp(MouseEvent e) {
		ViewerCell cell = getCell(new Point(e.x, e.y));

		if (cell != null) {
			triggerEditorActivationEvent(new ColumnViewerEditorActivationEvent(
					cell, e));
		}
	}

    /*
     * Copied from ColumnViewer.getCell - required so it can be called
     * from handleMouseDown
     * @param point
     */
	public ViewerCell getCell(Point point) {
		ViewerRow row = getViewerRow(point);
		if (row != null) {
			return row.getCell(point);
		}

		return null;
	}
	
	
    class SelectGridLabelProvider extends LabelProvider implements ITableLabelProvider {

        public String getColumnText(Object object, int columnIndex) {
            if (object instanceof SelectTableElement && columnIndex != 2) // not Output column
            {
                SelectTableElement selectElement = (SelectTableElement) object;
                return selectElement.getColumnText(columnIndex);
            }
            return ""; //$NON-NLS-1$
        }

        public Image getColumnImage(Object object, int columnIndex) {
            if (columnIndex == 2) // Output Column
            {
                if (object instanceof SelectTableElement) {
                    SelectTableElement selectElement = (SelectTableElement) object;
                    // BZ 202596 - don't show image if it's the empty row at the end
                    if (selectElement.hasColumn()){
                    	String result = selectElement.getColumnText(columnIndex);
                    	if (result.equals("true")) { //$NON-NLS-1$
                    		return SQLBuilderPlugin.getPlugin().getImage(SQLResource.SQL_OUTPUT_YES);
                    	}
                    	return SQLBuilderPlugin.getPlugin().getImage(SQLResource.SQL_OUTPUT_NO);
                    }
                }
            }
            return null;
        }
    }

    class SortTypeComboBoxCellEditor extends org.eclipse.datatools.sqltools.sqlbuilder.views.ComboBoxCellEditor {

        public SortTypeComboBoxCellEditor(Composite parent) {
            super(parent, new LabelValuePair[3]);

            LabelValuePair[] items = getLabelValuePairs();
            items[0] = new LabelValuePair(P_ASCENDING, P_ASCENDING);
            items[1] = new LabelValuePair(P_DESCENDING, P_DESCENDING);
            items[2] = new LabelValuePair("", ""); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    class SortOrderComboBoxCellEditor extends org.eclipse.datatools.sqltools.sqlbuilder.views.ComboBoxCellEditor {

        public SortOrderComboBoxCellEditor(Composite parent) {
            super(parent, null);
        }

        protected void doSetValue(Object value) {
            super.doSetValue(value);
        }

        protected void refreshComboItems() {
            boolean orderByExpressionsExist = false;
            int row = getTable().getSelectionIndex();
            boolean hasOrder = false;
            if (row >= 0) {
                Object obj = getElementAt(row);
                if (obj instanceof SelectTableElement) {
                    String sortOrder = ((SelectTableElement) obj).getSortOrder();
                    if (sortOrder != null && sortOrder != "") { //$NON-NLS-1$
                        hasOrder = true;
                    }
                }
            }

            if (getCurrentStatement() instanceof QuerySelectStatement) {
                List sqlOrderByClause = ((QuerySelectStatement) getCurrentStatement()).getOrderByClause();
                if (sqlOrderByClause != null && !sqlOrderByClause.isEmpty()) {
                    int orderNumbers = sqlOrderByClause.size();
                    if (orderNumbers >= 0) {
                        orderByExpressionsExist = true;
                        if (!hasOrder) // if the item has no order, then we want to add another numeric for the next order
                        {
                            orderNumbers++; // increment by one for the next order expression to be added
                        }
                        LabelValuePair[] sortOrderItems = new LabelValuePair[orderNumbers];
                        for (int i = 0; i < orderNumbers; i++) {
                            String item = (new Integer(i + 1)).toString();
                            sortOrderItems[i] = new LabelValuePair(item, item);
                        }

                        sortOrderCellEditor.createItems(sortOrderItems);
                    }
                }
            }
            if (!orderByExpressionsExist) {
                LabelValuePair[] sortOrderItems = new LabelValuePair[1];
                String item = (new Integer(1)).toString();
                sortOrderItems[0] = new LabelValuePair(item, item);
                sortOrderCellEditor.createItems(sortOrderItems);
            }
        }
    }

    /**
     * Cell editor for column alias
     */
    class AliasCellEditor extends TextCellEditor implements org.eclipse.swt.events.FocusListener {

        public AliasCellEditor(Composite parent) {
            super(parent);
            text.addFocusListener(this);
        }

        public void focusLost(org.eclipse.swt.events.FocusEvent e) {
            fireApplyEditorValue();
        }

        public void focusGained(org.eclipse.swt.events.FocusEvent e) {

        }

        protected void doSetValue(Object value) {
            if (value instanceof SelectTableElement) {
                SelectTableElement ste = (SelectTableElement) value;
                String result = ste.getColumnText(1);
                super.doSetValue(result);
            }
        }
    }

    /**
     * Cell editor for check box. This is an image that toggles based on
     * model value
     */
    class OutputCheckboxCellEditor extends CheckboxCellEditor {

        public OutputCheckboxCellEditor(Composite parent) {
            super(parent);
        }
        
        protected void doSetValue(Object value) {
            if (value instanceof SelectTableElement) {
                SelectTableElement ste = (SelectTableElement) value;
                String result = ste.getColumnText(2);

                super.doSetValue(new Boolean(result));
            }
        }

        public void activate() {
        	super.activate();
            deactivate();
            Display.getCurrent().getFocusControl().redraw();
        }

    }

    public void setEnabled(boolean enable) {
        table.setEnabled(enable);
    }
}

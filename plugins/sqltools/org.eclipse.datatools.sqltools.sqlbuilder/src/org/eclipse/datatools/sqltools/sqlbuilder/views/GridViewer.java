/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.views;

import org.eclipse.datatools.modelbase.sql.query.QuerySelect;
import org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement;
import org.eclipse.datatools.modelbase.sql.query.QueryStatement;
import org.eclipse.datatools.modelbase.sql.query.SQLQueryObject;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.model.SQLDomainModel;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;


/**
 * Base class for the select/update/insert table
 */
public abstract class GridViewer extends NavigableTableViewer implements IMenuListener {

    protected QueryStatement currentStatement;
    protected TableColumn c1;
    protected int totalRdbColumn;

    // This is for the first column in table.  It is a non-editable combobox.
    protected ColumnNameComboBoxCellEditor columnComboBoxCellEditor;
    protected Table table;

    protected SQLDomainModel domainModel;

    //
    // Create the "Rdb Column name" column
    //
    public GridViewer(SQLDomainModel domainModel, Composite parent) {
        super(new Table(parent, SWT.FULL_SELECTION | SWT.MULTI | SWT.BORDER));

        this.domainModel = domainModel;

        table = getTable();
        table.setLinesVisible(true);
        table.setHeaderVisible(true);

        c1 = new TableColumn(table, SWT.NULL);
        c1.setText(Messages._UI_COLUMN_DEFAULT_COLUMN);

        columnComboBoxCellEditor = new ColumnNameComboBoxCellEditor(table);

        MenuManager contextMenu = new MenuManager("#PopUp");
        contextMenu.add(new Separator("additions"));
        contextMenu.setRemoveAllWhenShown(true);
        contextMenu.addMenuListener(this);
        Menu menu = contextMenu.createContextMenu(getControl());
        getControl().setMenu(menu);

    }

    public org.eclipse.datatools.sqltools.sqlbuilder.views.ComboBoxCellEditor getColumnComboBoxCellEditor() {
        return columnComboBoxCellEditor;
    }

    public int getTotalRdbColumn() {
        return totalRdbColumn;
    }

    protected void inputChanged(java.lang.Object input, java.lang.Object oldInput) {
        super.inputChanged(input, oldInput);
        refreshColumnCombo();
    }

    // this variable is used to signify that we are in the process of 
    // creating new combo box items.  We need it because fillColumnComboBox
    // will cause inputchanged to be re-entrant.
    boolean isFillingCombo = false;

    protected void refreshColumnCombo() {
        Object input = getInput();
        if (!isFillingCombo && (input instanceof QueryStatement || input instanceof QuerySelect)) {
            isFillingCombo = true;
            if (input instanceof QueryStatement)
                currentStatement = (QueryStatement) input;

            // Populate the combo-box with the rdb columns of current input
            boolean isSelect = (input instanceof QuerySelectStatement || input instanceof QuerySelect);
            totalRdbColumn = BuilderUtility.fillColumnComboBox((ComboBoxCellEditor) columnComboBoxCellEditor, (SQLQueryObject) input, isSelect, false);
            isFillingCombo = false;
        }
    }

    public void refresh() {
        if (!isCellEditorActive() && !isFillingCombo) {
            refreshColumnCombo();
            super.refresh();
        }
    }

    /**
     * Cell editor for column name
     */

    class ColumnNameComboBoxCellEditor extends org.eclipse.datatools.sqltools.sqlbuilder.views.ComboBoxCellEditor {

        public ColumnNameComboBoxCellEditor(Composite parent) {
            super(parent, null);
        }

        protected void doSetValue(Object value) {
            super.doSetValue(value);
        }
    }

}

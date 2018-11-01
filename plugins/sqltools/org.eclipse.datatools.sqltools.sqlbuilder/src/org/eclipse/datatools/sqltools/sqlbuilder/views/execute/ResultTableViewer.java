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
package org.eclipse.datatools.sqltools.sqlbuilder.views.execute;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;


public class ResultTableViewer extends TableViewer {

    String[] columnProperties;
    ResultSet resultSet;
    TextCellEditor stringEditor;

    public ResultTableViewer(int style, ResultSet resultSet, Composite parent) {
        super(new Table(parent, style));
        this.resultSet = resultSet;

        Table table = getTable();
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        if (resultSet != null) {
            try {
                ResultSetMetaData metaData = resultSet.getMetaData();

                int numberOfColumns = metaData.getColumnCount();
                columnProperties = new String[numberOfColumns];
                if (numberOfColumns > 0) {
                    for (int i = 1; i <= numberOfColumns; i++) {
                        String label = metaData.getColumnLabel(i);
//                        if (label.length() == 0) // string doesn't exist
//                            label = Messages._UI_COLUMN_RESULT_COLUMN + i;
                        	
                        TableColumn column = new TableColumn(table, SWT.NULL);
                        column.setText(label);
                        column.setWidth(80);

                        columnProperties[i - 1] = new String(label);
                    }
                }
                setContentProvider(ResultContentProvider.createMyTable(columnProperties, resultSet));
                setLabelProvider(new ResultLabelProvider());
            }
            catch (Exception exception) {
                ErrorDialog.openError(Display.getCurrent().getActiveShell(), Messages._UI_DIALOG_OP_FAILED_TITLE, exception.toString(),
                        null);
            }
        }
    }

    public int getRecordsDisplayedCount() {
        return ((ResultContentProvider) getContentProvider()).getRecordsDisplayedCount();
    }

    public void setLinesVisible(boolean visible) {
        getTable().setLinesVisible(visible);
    }

    class ResultLabelProvider extends LabelProvider implements ITableLabelProvider {

        public String getColumnText(Object object, int columnIndex) {
            if (object instanceof ResultTableElement) {
                return ((ResultTableElement) object).getColumnText(columnIndex);
            }
            return "";
        }

        public Image getColumnImage(Object object, int columnIndex) {
            return null;
        }
    }
}

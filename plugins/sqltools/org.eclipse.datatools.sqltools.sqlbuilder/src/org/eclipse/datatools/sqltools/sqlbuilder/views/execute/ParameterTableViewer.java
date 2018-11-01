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

import java.util.Vector;

import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.views.NavigableTableViewer;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;


public class ParameterTableViewer extends NavigableTableViewer {

    public static final String P_MARKER_NAME = "org.eclipse.datatools.sqltools.sqlbuilder.views.execute.name";
    public static final String P_MARKER_TYPE = "org.eclipse.datatools.sqltools.sqlbuilder.views.execute.type";
    public static final String P_MARKER_VALUE = "org.eclipse.datatools.sqltools.sqlbuilder.views.execute.value";

    private Table table;
    private TableColumn c1, c2, c3;
    private ParameterMarkerPage parameterMarkerPage;
    //ParameterValueCellEditor parameterValueCellEditor;
    InputValueCellEditor inputCellEditor;

    public ParameterTableViewer(ParameterMarkerPage pmp, int style, Composite parent, Vector parmMarkers) {
        super(new Table(parent, style));
        this.parameterMarkerPage = pmp;

        table = getTable();
        table.setLinesVisible(true);
        table.setHeaderVisible(true);

        c1 = new TableColumn(table, SWT.NULL);
        c1.setText(Messages._UI_COLUMN_MARKER_NAME);

        c2 = new TableColumn(table, SWT.NULL);
        c2.setText(Messages._UI_COLUMN_TYPE);

        c3 = new TableColumn(table, SWT.NULL);
        c3.setText(Messages._UI_COLUMN_PARAMETER_VALUE);
        //parameterValueCellEditor = new ParameterValueCellEditor(table);        

        // allow user to specify value by launcing editor        
        String[] items = {Messages._UI_SPECIFY_VALUE};
        inputCellEditor = new InputValueCellEditor(table, this, items);

        TableLayout layout = new TableLayout();
        layout.addColumnData(new ColumnPixelData(130, true));
        layout.addColumnData(new ColumnPixelData(130, true));
        layout.addColumnData(new ColumnPixelData(130, true));
        table.setLayout(layout);

        String properties[] = { P_MARKER_NAME, P_MARKER_TYPE, P_MARKER_VALUE };

        setColumnProperties(properties);

        //CellEditor editors[] = { null, null, parameterValueCellEditor };
        CellEditor editors[] = { null, null, inputCellEditor };

        setCellEditors(editors);

        // The cell modifier is no longer used like it was before
        // modification is handled by the new cell editor
        setCellModifier(new ParameterTableCellModifier(this));

        setContentProvider(new ParameterContentProvider(parmMarkers));
        setLabelProvider(new ParameterLabelProvider());
    }

    public ParameterMarkerPage getParameterMarkerPage() {
        return parameterMarkerPage;
    }
    
    public class ParameterValueCellEditor extends TextCellEditor implements org.eclipse.swt.events.FocusListener {

        public ParameterValueCellEditor(Composite parent) {
            super(parent);
            setValidator(null);
            text.addFocusListener(this);
        }

        public void focusLost(org.eclipse.swt.events.FocusEvent e) {
            fireApplyEditorValue();
        }

        public void focusGained(org.eclipse.swt.events.FocusEvent e) {

        }

        protected void doSetValue(Object value) {
            if (value instanceof ParameterElement) {
                ParameterElement pe = (ParameterElement) value;
                String result = pe.getColumnText(2);
                super.doSetValue(result);
            }
        }
    }

    public class ParameterTableCellModifier implements ICellModifier {

        ParameterTableViewer viewer;

        public ParameterTableCellModifier(ParameterTableViewer viewer) {
            this.viewer = viewer;
        }

        public boolean canModify(Object element, String property) {
            if (property.equals(ParameterTableViewer.P_MARKER_VALUE)) {
                return true;
            }

            return false;
        }

        public Object getValue(Object element, String property) {
            // This implicitly uses the element's toString method
            return element;
        }

        Object currentElement, currentProperty, currentValue;
        ParameterElement parameterElement;

        public void modify(Object element, String property, Object value) {
            currentElement = element;
            currentProperty = property;
            currentValue = value;

            if (property.equals(ParameterTableViewer.P_MARKER_VALUE)) {
                Object data = ((TableItem) currentElement).getData();

                if (currentValue == null) {
                    return;
                }

                if (data instanceof ParameterElement) {
                    parameterElement = (ParameterElement) data;
                    if (value instanceof String) {
                        parameterElement.setValue((String) value);
                        Display.getCurrent().asyncExec(new Runnable() {

                            public void run() {
                                viewer.refresh();
                            }
                        });
                        getParameterMarkerPage().updateFinishButton();
                    }
                }
            }
        }
    }

    class ParameterLabelProvider extends LabelProvider implements ITableLabelProvider {

        public String getColumnText(Object object, int columnIndex) {
            if (object instanceof ParameterElement) {
                return ((ParameterElement) object).getColumnText(columnIndex);
            }
            return "";
        }

        public Image getColumnImage(Object object, int columnIndex) {
            return null;
        }
    }
}

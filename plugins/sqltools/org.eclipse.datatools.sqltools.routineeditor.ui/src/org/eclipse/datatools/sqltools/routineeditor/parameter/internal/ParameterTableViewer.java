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
package org.eclipse.datatools.sqltools.routineeditor.parameter.internal;

import java.util.List;

import org.eclipse.datatools.sqltools.core.dbitem.ParameterWrapper;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;

/**
 * @author Yang Liu
 */
public class ParameterTableViewer extends TableViewer
{
    public static final String[] COLUMNPROPERTIES = new String[]
    {
        "name", "type", "null", "value", "inout" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
    }
    ;

    public static final int      NAMECOLUMN       = 0;
    public static final int      TYPECOLUMN       = 1;
    public static final int      NULLCOLUMN       = 2;
    public static final int      VALUECOLUMN      = 3;
    public static final int      INOUTCOLUMN      = 4;

    /**
     * @param parent
     */
    public ParameterTableViewer(Composite parent, ParameterWrapper[] wrappers, int style)
    {
        super(parent, style);
        configTable();
        setInput(wrappers);
    }

    protected void configTable()
    {
        this.setColumnProperties(COLUMNPROPERTIES);

        // currently, we only have 5 columns
        // 1. name.
        // 2. type
        // 3. null
        // 4. value
        // 5. in/out
        getTable().setHeaderVisible(true);
        getTable().setLinesVisible(true);

        TableLayout tablelayout = new TableLayout();

        TableColumn nameColumn = new TableColumn(getTable(), SWT.LEFT);
        nameColumn.setText(Messages.getString("ParameterTableViewer.name")); //$NON-NLS-1$
        nameColumn.setResizable(true);
        tablelayout.addColumnData(new ColumnWeightData(20));

        TableColumn typeColumn = new TableColumn(getTable(), SWT.LEFT);
        typeColumn.setText(Messages.getString("ParameterTableViewer.type")); //$NON-NLS-1$
        typeColumn.setResizable(true);
        tablelayout.addColumnData(new ColumnWeightData(20));

        TableColumn nullColumn = new TableColumn(getTable(), SWT.LEFT);
        nullColumn.setText(Messages.getString("ParameterTableViewer.null")); //$NON-NLS-1$
        nullColumn.setResizable(true);
        tablelayout.addColumnData(new ColumnWeightData(10));

        TableColumn valueColumn = new TableColumn(getTable(), SWT.LEFT);
        valueColumn.setText(Messages.getString("ParameterTableViewer.value")); //$NON-NLS-1$
        valueColumn.setResizable(true);
        tablelayout.addColumnData(new ColumnWeightData(35));

        TableColumn inoutColumn = new TableColumn(getTable(), SWT.LEFT);
        inoutColumn.setText(Messages.getString("ParameterTableViewer.inout")); //$NON-NLS-1$
        inoutColumn.setResizable(true);
        tablelayout.addColumnData(new ColumnWeightData(15));

        getTable().setLayout(tablelayout);

        CellEditor[] celleditors = new CellEditor[5];
        celleditors[NAMECOLUMN] = null;
        celleditors[TYPECOLUMN] = null;
        celleditors[NULLCOLUMN] = new CheckboxCellEditor(getTable());
        celleditors[VALUECOLUMN] = new TextCellEditor(getTable());
        celleditors[INOUTCOLUMN] = null;

        this.setCellEditors(celleditors);
        this.setLabelProvider(new ParameterTableLabelProvider());
        this.setCellModifier(new ParameterCellModifier(this));
        this.setContentProvider(new IStructuredContentProvider()
        {
            public Object[] getElements(Object inputElement)
            {
                return (Object[])inputElement;
            }

            public void dispose()
            {
            }

            public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
            {
            }
        }
        );
    }

    public List getResult()
    {
        return null;
    }
}

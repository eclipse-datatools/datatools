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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.core.dbitem.IEvent;
import org.eclipse.datatools.sqltools.routineeditor.parameter.EventParameter;
import org.eclipse.datatools.sqltools.sqleditor.internal.IHelpContextIds;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.PlatformUI;

/**
 * A dialog containing a table to edit event parameters.
 * @author Hui Cao
 *  
 */
public class EventParameterTableDialog extends Dialog
{

    private Table              _table;
    private TableViewer        _tableViewer;
    private Button             _addButton;
    private Button             _removeButton;
    private EventParameter[]   _parameters;
    private List               _paramList;
    private Map                _paramMap;
    private IEvent             _eventItem;

    // Set the table column property names
    public static final String NAME_COLUMN  = "name";                                    //$NON-NLS-1$
    public static final String VALUE_COLUMN = "value";                                   //$NON-NLS-1$

    // Set column names
    private String[]           _columnNames = new String[] 
    {
        NAME_COLUMN, VALUE_COLUMN,
    }
    ;

    /**
     * @param parentShell
     */
    public EventParameterTableDialog(Shell parentShell, EventParameter[] parameters, IEvent eventItem)
    {
        super(parentShell);
        _parameters = parameters;
        _paramList = new ArrayList();
        _paramMap = new HashMap();
        _eventItem = eventItem;
        for (int i = 0; i < parameters.length; i++)
        {
            if (!_paramMap.containsKey(parameters[i].getName()))
            {
                _paramList.add(parameters[i]);
            }
            _paramMap.put(parameters[i].getName(), null);
        }
        _parameters = new EventParameter[_paramList.size()];
        _paramList.toArray(_parameters);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    protected void configureShell(Shell newShell)
    {
        super.configureShell(newShell);
        newShell.setText(Messages.getString("EventParameterTableDialog.title")); //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
     */
    protected Control createDialogArea(Composite parent)
    {
        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent.getShell(), HelpUtil.getContextId(IHelpContextIds.EVENT_LAUNCH_CONFIGURATION_PARAMETERS, SQLEditorPlugin.getDefault().getBundle().getSymbolicName())); 

        Composite composite = (Composite) super.createDialogArea(parent);
        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        composite.setLayout(gridLayout);
        createTable(composite);
        createTableViewer();
        _tableViewer.setContentProvider(new IStructuredContentProvider()
        {
            public Object[] getElements(Object inputElement)
            {
                return (Object[]) inputElement;
            }

            public void dispose()
            {
            }

            public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
            {
            }
        }
        );
        _tableViewer.setLabelProvider(new ITableLabelProvider()
        {

            public Image getColumnImage(Object element, int columnIndex)
            {
                return null;
            }

            public String getColumnText(Object element, int columnIndex)
            {
                EventParameter wrapper = (EventParameter) element;
                if (columnIndex == 0)
                {
                    return wrapper.getName();
                }
                else
                {
                    return wrapper.getValue();
                }
            }

            public void addListener(ILabelProviderListener listener)
            {
                // TODO Auto-generated method stub

            }

            public void dispose()
            {
                // TODO Auto-generated method stub

            }

            public boolean isLabelProperty(Object element, String property)
            {
                // TODO Auto-generated method stub
                return false;
            }

            public void removeListener(ILabelProviderListener listener)
            {
                // TODO Auto-generated method stub

            }

        }
        );
        _tableViewer.setInput(_parameters);

        final Composite compositeForButton = new Composite(composite, SWT.NONE);

        final GridData gridDataForButton = new GridData(GridData.HORIZONTAL_ALIGN_END | GridData.FILL_VERTICAL);
        compositeForButton.setLayoutData(gridDataForButton);
        GridLayout gridlayout = new GridLayout();
        gridlayout.numColumns = 1;
        compositeForButton.setLayout(gridlayout);

        _addButton = new Button(compositeForButton, SWT.NONE);
        _addButton.setText(Messages.getString("EventParameterTableDialog.button.add"));
        setButtonLayoutData(_addButton);
        //		_addButton = createButton(compositeForButton, 3,
        // DmpMessages.getString("EventParameterTableDialog.button.add"), //$NON-NLS-1$
        //				false);
        _addButton.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                EventParameterAddDialog dlg = new EventParameterAddDialog(getShell(),_eventItem);
                if (dlg.open() == EventParameterTableDialog.OK)
                {
                    if (dlg.getEventParameter() != null)
                    {
                        if (!_paramMap.containsKey(dlg.getEventParameter().getName()))
                        {
                            _paramList.add(dlg.getEventParameter());
                            _parameters = new EventParameter[_paramList.size()];
                            _paramList.toArray(_parameters);
                            _tableViewer.setInput(_parameters);
                            _tableViewer.refresh();
                        }
                    }
                }
            }
        }
        );
        _removeButton = new Button(compositeForButton, SWT.NONE);
        _removeButton.setText(Messages.getString("EventParameterTableDialog.button.remove"));
        setButtonLayoutData(_removeButton);
        //		_removeButton = createButton(compositeForButton, 4,
        //				DmpMessages.getString("EventParameterTableDialog.button.remove"), false); //$NON-NLS-1$
        _removeButton.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                StructuredSelection sel = (StructuredSelection) _tableViewer.getSelection();
                _paramList.remove(sel.getFirstElement());
                _parameters = new EventParameter[_paramList.size()];
                _paramList.toArray(_parameters);
                _tableViewer.setInput(_parameters);
                _tableViewer.refresh();
            }
        }
        );

        return composite;
    }

    /**
     * Creates the Table
     */
    private void createTable(Composite parent)
    {
        int style = SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.HIDE_SELECTION;

        _table = new Table(parent, style);
        GridData gd = new GridData(GridData.FILL_BOTH);
        gd.widthHint = 250;
        gd.heightHint = 200;
        _table.setLayoutData(gd);

        TableLayout tablelayout = new TableLayout();
        tablelayout.addColumnData(new ColumnWeightData(50, 100));
        tablelayout.addColumnData(new ColumnWeightData(50, 100));
        _table.setLayout(tablelayout);

        _table.setLinesVisible(true);
        _table.setHeaderVisible(true);

        // 1st column with name
        TableColumn column = new TableColumn(_table, SWT.LEFT, 0);
        column.setText(Messages.getString("EventParameterTableDialog.column.name")); //$NON-NLS-1$

        // 2nd column with value
        column = new TableColumn(_table, SWT.LEFT, 1);
        column.setText(Messages.getString("EventParameterTableDialog.column.value")); //$NON-NLS-1$
    }

    /**
     * Create the TableViewer
     */
    private void createTableViewer()
    {

        _tableViewer = new TableViewer(_table);
        _tableViewer.setUseHashlookup(true);

        _tableViewer.setColumnProperties(_columnNames);

        // Create the cell editors
        CellEditor[] editors = new CellEditor[_columnNames.length];

        // Column 1 : Name
        editors[0] = null;

        // Column 2 : Value (Free text)
        editors[1] = new TextCellEditor(_table);

        // Assign the cell editors to the viewer 
        _tableViewer.setCellEditors(editors);
        // Set the cell modifier for the viewer
        _tableViewer.setCellModifier(new EventParameterCellModifier(_tableViewer));
    }

    protected void createButtonsForButtonBar(Composite parent)
    {
        super.createButtonsForButtonBar(parent);
    }

    public EventParameter[] getEventParameter()
    {
        return _parameters;
    }
}

/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.commonui;

import java.util.Iterator;

import org.eclipse.datatools.sqltools.common.ui.tableviewer.AccessibleTableViewer;
import org.eclipse.datatools.sqltools.common.ui.tableviewer.TableCellEditor;
import org.eclipse.datatools.sqltools.common.ui.tableviewer.TableComboBoxCellEditor;
import org.eclipse.datatools.sqltools.common.ui.tableviewer.TableDataContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * Parameter section for stored procedures
 * @author Hui Cao
 */
public class ParametersTableProvider implements ICompositeProvider
{
    private boolean                      _useGroup                      = true;
    private Shell                        _sShell                        = null;
    private Composite                    _groupParameter                = null;
    private Table                        _table                         = null;
    private Composite                    _composite                     = null;
    protected ContextAccessibleTableViewer _viewer;
    private ParametersData               _data;
    private FormToolkit formToolkit = null;   //  @jve:decl-index=0:visual-constraint=""
    private String[] inOutItems = new String[] {"IN", "OUT"};
    private String[] _dataTypes = new String[]{};
    protected boolean _enforcePrefix = false;
    protected boolean _supportsExp = false;
    private int border = NONE;
    private Listener _changeListener;
    /**
     * 
     */
    public ParametersTableProvider()
    {
        super();
    }

    /**
     * @param _useGroup whether to use group
     */
    public ParametersTableProvider(boolean useGroup)
    {
        super();
        this._useGroup = useGroup;
    }

    /**
     * @param _useGroup whether to use group
     * @param enforcePrefix Sets whether the parameter name must be prefixed by a "@" character.
     */
    public ParametersTableProvider(boolean useGroup, boolean enforcePrefix)
    {
    	this(useGroup, enforcePrefix, false);
    }
    

    /**
     * @param _useGroup whether to use group
     * @param enforcePrefix Sets whether the parameter name must be prefixed by a "@" character.
     */
    public ParametersTableProvider(boolean useGroup, boolean enforcePrefix, boolean supportsExp)
    {
        super();
        this._useGroup = useGroup;
        _enforcePrefix = enforcePrefix;
        _supportsExp = supportsExp;
    }
    
    /**
     * This method initializes formToolkit  
     *  
     * @return org.eclipse.ui.forms.widgets.FormToolkit 
     */
    private FormToolkit getFormToolkit()
    {
        if (formToolkit == null)
        {
            formToolkit = new PseudoFormToolkit(Display.getCurrent());
        }
        return formToolkit;
    }

    public Composite createTheTable(Composite parent)
    {
        if (_groupParameter == null)
        {
            createSShell();
        }
        _groupParameter.setParent(parent);
        _table.setSelection(0);
        return _groupParameter;
    }

    /**
     * This method initializes _sShell
     */
    private void createSShell()
    {
        _sShell = new Shell();
        _sShell.setLayout(new GridLayout());
        createComposite();
    }

    /**
     * This method initializes _composite
     */
    private void createComposite()
    {
        org.eclipse.swt.layout.GridData gridData1 = new org.eclipse.swt.layout.GridData();
        _composite = new Composite(_sShell, SWT.NONE);
        _composite.setLayout(new GridLayout());
        _composite.setLayoutData(gridData1);
        gridData1.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData1.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData1.grabExcessHorizontalSpace = true;
        gridData1.grabExcessVerticalSpace = true;
        createGroupParameters();
    }

    public Composite getComposite(Composite parent, FormToolkit formToolkit, int style)
    {
        if (_groupParameter == null)
        {
            this.formToolkit = formToolkit;
            border = style & BORDER;
            createSShell();
        }
        _groupParameter.setParent(parent);
        _table.setSelection(0);
        return _groupParameter;
    }

    /**
     * This method initializes _groupParameters
     */
    private void createGroupParameters()
    {
        GridData gridData = new GridData();

        GridLayout gridLayout = new GridLayout();
        if (_useGroup)
        {
            _groupParameter = new Group(_composite, SWT.NONE);
            {
                ((Group) _groupParameter).setText(Messages.wizard_createSP_page2_parameters_label);
            }
        }
        else
        {
            _groupParameter = getFormToolkit().createComposite(_composite, SWT.NONE);
            gridLayout.marginHeight = 1;
        }
        createTable();
        // set focus to cursor for the first time
        _viewer.getCursor().setSelection(0, 0);
        _groupParameter.setLayout(gridLayout);
        _groupParameter.setLayoutData(gridData);
        gridData.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        gridData.horizontalSpan = 5;
        gridData.heightHint = 80;
        
        getFormToolkit().paintBordersFor(_groupParameter);
        _table.pack();
    }

    /**
     * Configure the parameters table.
     * 
     */
    private void configureTable()
    {
        _viewer.getTable().setHeaderVisible(true);
        _viewer.getTable().setLinesVisible(true);
        _viewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));

        CellEditor editors[] = new CellEditor[_data.getColumnCount()];
        String[] properties = new String[_data.getColumnCount()];
        for (int i = 0; i < _data.getColumnCount(); i++)
        {
            TableColumn col = new TableColumn(_viewer.getTable(), SWT.NONE);
            col.setText(_data.getColumnName(i));
            col.pack();
            col.setWidth(ParametersData.COLUMN_LENGTH[i]);

            CellEditor currentEditor = null;
            switch (i)
            {
                case ParametersData.DIRTY_COLUMN:
                    currentEditor = new TableCellEditor(_viewer);
                    break;
                case ParametersData.NAME_COLUMN:
                    currentEditor = new TableCellEditor(_viewer);
                    break;
                case ParametersData.TYPE_COLUMN:
                    currentEditor = new TableComboBoxCellEditor(_viewer, _dataTypes);
                    break;
                case ParametersData.INOUT_COLUMN:
                    currentEditor = new TableComboBoxCellEditor(_viewer, inOutItems, SWT.READ_ONLY);
                    break;
                case ParametersData.DEFAULT_COLUMN:
                    currentEditor = new TableCellEditor(_viewer);
                    break;
                default:
                    break;
            }
            editors[i] = currentEditor;
            properties[i] = _data.getColumnName(i);
        }
        _viewer.setColumnProperties(properties);
        _viewer.setCellEditors(editors);
        _viewer.setCellModifier(createParameterDataCellModifier());
    }
    
    protected ParameterDataCellModifier createParameterDataCellModifier()
    {
        return new ParameterDataCellModifier(_viewer, _enforcePrefix, _supportsExp)
        {
            public boolean canChange(Object obj, int index)
            {
//                if (index == ParametersData.DIRTY_COLUMN)
//                {
//                    return false;
//                }
//                return true;
                return false;
            }
        };
    }

    /**
     * This method initializes _table
     */
    private void createTable()
    {
        _table = getFormToolkit().createTable(_groupParameter, border | SWT.MULTI | SWT.FULL_SELECTION);
        _viewer = new ContextAccessibleTableViewer(_table, ParametersData.NAME_COLUMN);
        _viewer.setLabelProvider(new ParameterVariableSectionLabelProvider());
        _viewer.setContentProvider(new TableDataContentProvider());

        _data = createParametersData();
        configureTable();
        _viewer.setInput(_data);
    }
    
    protected ParametersData createParametersData()
    {
        return new ParametersData();
    }

    /**
     * Returns the table widget. Caution: might be null
     * @return
     */
    public Table getTable()
    {
        return _table;
    }
    
    public boolean setFocus()
    {
        return _table.setFocus();
    }

    /**
     * Enable/disable the parameters table.
     * 
     * @param status
     */
    public void enableTable(boolean status)
    {
        _table.setEnabled(status);
        _table.setLinesVisible(true);
    }

    public void setInOutItems(String[] inOutItems)
    {
        this.inOutItems = inOutItems;
        updateInOutField();
    }

    public void setDataTypes(String[] types)
    {
        _dataTypes = types;
        if (_table == null)
        {
            return;
        }
        TableComboBoxCellEditor editor = (TableComboBoxCellEditor) _viewer.getCellEditors()[ParametersData.TYPE_COLUMN];
        editor.setItems(_dataTypes);
        ((CCombo) editor.getControl()).setVisibleItemCount(_dataTypes.length > 20 ? 20 : _dataTypes.length);
        
    }
    
    /**
     * When using different types of syntax, the INOUT field may have different options.
     * 
     */
    public void updateInOutField()
    {
        if (_table == null)
        {
            return;
        }
        TableComboBoxCellEditor editor = (TableComboBoxCellEditor) _viewer.getCellEditors()[ParametersData.INOUT_COLUMN];
        editor.setItems(inOutItems);
        ((CCombo) editor.getControl()).setVisibleItemCount(inOutItems.length);

        ParametersData data = (ParametersData) _viewer.getInput();
        Iterator iter = data.getRows().iterator();
        while (iter.hasNext())
        {
            ParametersRowData row = (ParametersRowData) iter.next();
            String inout = row.getValue(ParametersData.INOUT_COLUMN) == null ? "" : row.getValue(
                ParametersData.INOUT_COLUMN).toString();
            if (!inout.equals(""))
            {
                persistToDataModel(row, ParametersData.INOUT_COLUMN, inout.trim());
                _viewer.doRefresh(false);
                _table.redraw();
            }
        }
    }

    /**
     * Save data from UI back to Model. Notice that we use 'updateValueWithoutNotification' method here. This method
     * updates the value of given column, but will not notify the listeners. Thus, we can keep the 'WARNNING' status
     * message.
     * 
     * @param itemIndex
     * @param columnIndex
     * @param obj
     */
    public void persistToDataModel(ParametersRowData row, int columnIndex, Object obj)
    {
        row.updateValueWithoutNotification(columnIndex, obj);
    }

    /**
     * Set the focus onto table cursor for editing.
     *
     */
    public void focus()
    {
        _viewer.getCursor().setSelection(0, 0);
    }

    public ParametersData geData()
    {
        return _data;
    }

    public void setData(ParametersData data)
    {
        this._data = data;
        _viewer.setInput(_data);
    }

    public AccessibleTableViewer getViewer()
    {
        return _viewer;
    }
}

/**
 * Created on 2004-10-21
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.ui.preferences;

import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.util.Assert;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * @author Hui Cao
 *  
 */
/**
 * A field editor for a combo box that allows the drop-down selection of one of a list of items.
 */
public class ComboFieldEditor extends FieldEditor
{

    /**
     * The <code>Combo</code> widget.
     */
    private Combo      _fCombo;

    /**
     * The value (not the name) of the currently selected item in the Combo widget.
     */
    private String     _fValue;

    /**
     * The names (labels) and underlying values to populate the combo widget. These should be arranged as: { {name1,
     * value1}, {name2, value2}, ...}
     */
    private String[][] _fEntryNamesAndValues;

    public ComboFieldEditor(String name, String labelText, String[][] entryNamesAndValues, Composite parent)
    {
        init(name, labelText);
        Assert.isTrue(checkArray(entryNamesAndValues));
        _fEntryNamesAndValues = entryNamesAndValues;
        createControl(parent);
    }

    /**
     * Checks whether given <code>String[][]</code> is of "type" <code>String[][2]</code>.
     * 
     * @return <code>true</code> if it is ok, and <code>false</code> otherwise
     */
    private boolean checkArray(String[][] table)
    {
        if (table == null)
        {
            return false;
        }
        for (int i = 0; i < table.length; i++)
        {
            String[] array = table[i];
            if (array == null || array.length != 2)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * @see FieldEditor#adjustForNumColumns(int)
     */
    protected void adjustForNumColumns(int numColumns)
    {
        if (numColumns > 1)
        {
            Control control = getLabelControl();
            int left = numColumns;
            if (control != null)
            {
                ((GridData) control.getLayoutData()).horizontalSpan = 1;
                left = left - 1;
            }
            ((GridData) _fCombo.getLayoutData()).horizontalSpan = left;
        }
        else
        {
            Control control = getLabelControl();
            if (control != null)
            {
                ((GridData) control.getLayoutData()).horizontalSpan = 1;
            }
            ((GridData) _fCombo.getLayoutData()).horizontalSpan = 1;
        }
    }

    /**
     * @see FieldEditor#doFillIntoGrid(Composite, int)
     */
    protected void doFillIntoGrid(Composite parent, int numColumns)
    {
        int comboC = 1;
        if (numColumns > 1)
        {
            comboC = numColumns - 1;
        }
        Control control = getLabelControl(parent);
        GridData gd = new GridData();
        gd.horizontalSpan = 1;
        control.setLayoutData(gd);
        control = getComboBoxControl(parent);
        gd = new GridData();
        gd.horizontalSpan = comboC;
        gd.horizontalAlignment = GridData.FILL;
        control.setLayoutData(gd);
        control.setFont(parent.getFont());
    }

    /**
     * @see FieldEditor#doLoad()
     */
    protected void doLoad()
    {
        updateComboForValue(getPreferenceStore().getString(getPreferenceName()));
    }

    /**
     * @see FieldEditor#doLoadDefault()
     */
    protected void doLoadDefault()
    {
        updateComboForValue(getPreferenceStore().getDefaultString(getPreferenceName()));
    }

    /**
     * @see FieldEditor#doStore()
     */
    protected void doStore()
    {
        if (_fValue == null)
        {
            getPreferenceStore().setToDefault(getPreferenceName());
            return;
        }

        getPreferenceStore().setValue(getPreferenceName(), _fValue);
    }

    /**
     * @see FieldEditor#getNumberOfControls()
     */
    public int getNumberOfControls()
    {
        return 2;
    }

    /**
     * Lazily create and return the Combo control.
     */
    public Combo getComboBoxControl(Composite parent)
    {
        if (_fCombo == null)
        {
            _fCombo = new Combo(parent, SWT.READ_ONLY);
            _fCombo.setFont(parent.getFont());
            for (int i = 0; i < _fEntryNamesAndValues.length; i++)
            {
                _fCombo.add(_fEntryNamesAndValues[i][0], i);
            }

            _fCombo.addSelectionListener(new SelectionAdapter()
            {
                public void widgetSelected(SelectionEvent evt)
                {
                    String oldValue = _fValue;
                    String name = _fCombo.getText();
                    _fValue = getValueForName(name);
                    setPresentsDefaultValue(false);
                    fireValueChanged(VALUE, oldValue, _fValue);
                }
            }
            );
        }
        return _fCombo;
    }

    /**
     * Given the name (label) of an entry, return the corresponding value.
     */
    protected String getValueForName(String name)
    {
        for (int i = 0; i < _fEntryNamesAndValues.length; i++)
        {
            String[] entry = _fEntryNamesAndValues[i];
            if (name.equals(entry[0]))
            {
                return entry[1];
            }
        }
        return _fEntryNamesAndValues[0][0];
    }

    /**
     * Set the name in the combo widget to match the specified value.
     */
    protected void updateComboForValue(String value)
    {
        _fValue = value;
        for (int i = 0; i < _fEntryNamesAndValues.length; i++)
        {
            if (value.equals(_fEntryNamesAndValues[i][1]))
            {
                _fCombo.setText(_fEntryNamesAndValues[i][0]);
                return;
            }
        }
        if (_fEntryNamesAndValues.length > 0)
        {
            _fValue = _fEntryNamesAndValues[0][1];
        }
    }
}

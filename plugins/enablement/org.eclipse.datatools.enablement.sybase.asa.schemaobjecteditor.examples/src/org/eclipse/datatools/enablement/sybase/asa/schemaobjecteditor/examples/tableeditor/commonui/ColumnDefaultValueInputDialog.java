/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.commonui;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASADefaultWrapper;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.TypeOfDefault;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASADefaultWrapperImpl;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.model.validation.ASASQLDataOfflineValidator;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.utils.ASADatatypeUtil;
import org.eclipse.datatools.enablement.sybase.util.SQLUtil;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.sqltools.core.modelvalidity.ISQLDataOfflineValidator;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


/**
 * Default value input dialog shared by Adaptive Server Anywhere table creation wizard and table schema editor
 * 
 * @author Idull
 */
public class ColumnDefaultValueInputDialog extends TitleAreaDialog
{
    Button                  _noDefaultRadio;
    Button                  _defaultValueRadio;
    Button                  _computedValueRadio;
    Button                  _userDefinedDefaultRadio;
    Button                  _sysDefinedDefaultRadio;
    Text                    _defaultValueTxt;
    Button                  _isDefaultLiteralButton;
    Combo                   _sysDefinedValuesCombo;
    Text                    _partitionSizeTxt;
    Text                    _computedText;
    Label                   _partionSizeLabel;
    SybaseASABaseColumn     _column;
    SybaseASADefaultWrapper _defaultWrapper;
    String                  _message;
    boolean                 _disableComputed = false;
    public ColumnDefaultValueInputDialog(Shell parentShell, SybaseASABaseColumn column)
    {
        super(parentShell);

        _column = column;
        _defaultWrapper = new SybaseASADefaultWrapperImpl(_column.getDefaultValue(), _column.isIsComputedColumn());
        _message = Messages.ColumnDefaultValueInputDialog_default_value_for_ASA_table;
    }
    
    public ColumnDefaultValueInputDialog(Shell parentShell, SybaseASABaseColumn column, boolean disableComputed)
    {
        super(parentShell);

        _column = column;
        _defaultWrapper = new SybaseASADefaultWrapperImpl(_column.getDefaultValue(), _column.isIsComputedColumn());
        _message = Messages.ColumnDefaultValueInputDialog_default_value_for_ASA_table;
        _disableComputed = disableComputed;
    }

    protected void configureShell(Shell newShell)
    {
        super.configureShell(newShell);
        newShell.setText(Messages.ColumnDefaultValueInputDialog_default_value);
    }

    protected Control createDialogArea(Composite parent)
    {
        parent.setLayout(new GridLayout());

        Composite outter = new Composite(parent, SWT.NONE);
        outter.setLayoutData(new GridData(GridData.FILL_BOTH));
        GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.numColumns = 1;
        outter.setLayout(layout);

        _noDefaultRadio = new Button(outter, SWT.RADIO);
        _noDefaultRadio.setText(Messages.ColumnDefaultValueInputDialog_no_default);
        _noDefaultRadio.setToolTipText(Messages.ColumnDefaultValueInputDialog_no_default_tip);
        _noDefaultRadio.addSelectionListener(new SelectionListener()
        {

            public void widgetDefaultSelected(SelectionEvent e)
            {
            }

            public void widgetSelected(SelectionEvent e)
            {
                enableDefaultValue(_defaultValueRadio.getSelection());
                _computedText.setEnabled(_computedValueRadio.getSelection());
                validateDefaultAndUpdate();
            }
        }
        );

        _defaultValueRadio = new Button(outter, SWT.RADIO);
        _defaultValueRadio.setText(Messages.ColumnDefaultValueInputDialog_default_value_radio);
        _defaultValueRadio.setToolTipText(Messages.ColumnDefaultValueInputDialog_default_tip);
        _defaultValueRadio.addSelectionListener(new SelectionListener()
        {
            public void widgetDefaultSelected(SelectionEvent e)
            {
            }

            public void widgetSelected(SelectionEvent e)
            {
                enableDefaultValue(_defaultValueRadio.getSelection());
                _computedText.setEnabled(_computedValueRadio.getSelection());
                if (_defaultValueRadio.getSelection())
                {
                    _userDefinedDefaultRadio.notifyListeners(SWT.Selection, new Event());
                }
                validateDefaultAndUpdate();
            }
        }
        );

        Composite comp = new Composite(outter, SWT.NONE);
        layout = new GridLayout();
        layout.numColumns = 5;
        comp.setLayout(layout);
        comp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        Label blankLabel = new Label(comp, SWT.NONE);
        GridData gd = new GridData();
        gd.widthHint = Constants.BLANK_WIDTH;
        blankLabel.setLayoutData(gd);

        _userDefinedDefaultRadio = new Button(comp, SWT.RADIO);
        _userDefinedDefaultRadio.setSelection(true);
        _userDefinedDefaultRadio.setText(Messages.ColumnDefaultValueInputDialog_user_defined);
        _userDefinedDefaultRadio.setToolTipText(Messages.ColumnDefaultValueInputDialog_ud_default_tip);
        _userDefinedDefaultRadio.addSelectionListener(new SelectionListener()
        {

            public void widgetDefaultSelected(SelectionEvent e)
            {
            }

            public void widgetSelected(SelectionEvent e)
            {
                _defaultValueTxt.notifyListeners(SWT.Modify, new Event());
                enableUserDefinedPart(_userDefinedDefaultRadio.getSelection());
                validateDefaultAndUpdate();
            }
        }
        );

        _defaultValueTxt = new Text(comp, SWT.BORDER);
        gd = new GridData();
        gd.widthHint = Constants.WIDGET_WIDTH_TEXT_NARROW;
        _defaultValueTxt.setLayoutData(gd);
        _defaultValueTxt.addModifyListener(new ModifyListener()
        {
            public void modifyText(ModifyEvent e)
            {
                validateDefaultAndUpdate();
            }
        }
        );

        _isDefaultLiteralButton = new Button(comp, SWT.CHECK);
        _isDefaultLiteralButton.setText(Messages.ColumnDefaultValueInputDialog_literal_string);
        _isDefaultLiteralButton.setToolTipText(Messages.ColumnDefaultValueInputDialog_literal_tip);
        _isDefaultLiteralButton.addSelectionListener(new SelectionListener()
        {
            public void widgetDefaultSelected(SelectionEvent e)
            {
                validateDefaultAndUpdate();
            }

            public void widgetSelected(SelectionEvent e)
            {
                validateDefaultAndUpdate();
            }
        }
        );
        gd = new GridData();
        gd.horizontalSpan = 2;
        _isDefaultLiteralButton.setLayoutData(gd);

        blankLabel = new Label(comp, SWT.NONE);
        gd = new GridData();
        gd.widthHint = Constants.BLANK_WIDTH;
        blankLabel.setLayoutData(gd);

        _sysDefinedDefaultRadio = new Button(comp, SWT.RADIO);
        _sysDefinedDefaultRadio.setText(Messages.ColumnDefaultValueInputDialog_system_defined);
        _sysDefinedDefaultRadio.setToolTipText(Messages.ColumnDefaultValueInputDialog_system_defined_default_tip);
        _sysDefinedDefaultRadio.addSelectionListener(new SelectionListener()
        {
            public void widgetDefaultSelected(SelectionEvent e)
            {
                validateDefaultAndUpdate();
            }

            public void widgetSelected(SelectionEvent e)
            {
                enableUserDefinedPart(_userDefinedDefaultRadio.getSelection());
                validateDefaultAndUpdate();
            }
        }
        );

        _sysDefinedValuesCombo = new Combo(comp, SWT.READ_ONLY);
        gd = new GridData();
        gd.widthHint = Constants.WIDGET_WIDTH_NARROW;
        _sysDefinedValuesCombo.setLayoutData(gd);
        _sysDefinedValuesCombo.addSelectionListener(new SelectionListener()
        {
            public void widgetDefaultSelected(SelectionEvent e)
            {
                //
            }

            public void widgetSelected(SelectionEvent e)
            {
                if (_sysDefinedDefaultRadio.getSelection()
                && _sysDefinedValuesCombo.getText().equals(SybaseASADefaultWrapper.GLOBAL_AUTOINCREMENT))
                {
                    _partionSizeLabel.setEnabled(true);
                    _partitionSizeTxt.setEnabled(true);

                    // the value may be invalid
                    _partitionSizeTxt.notifyListeners(SWT.Modify, new Event());
                }
                else
                {
                    _partionSizeLabel.setEnabled(false);
                    _partitionSizeTxt.setEnabled(false);

                    // the OK button may be disabled
                    getButton(IDialogConstants.OK_ID).setEnabled(true);
                    setMessage(_message);
                }
            }
        }
        );

        _partionSizeLabel = new Label(comp, SWT.NONE);
        _partionSizeLabel.setText(Messages.ColumnDefaultValueInputDialog_partition_size);

        _partitionSizeTxt = new Text(comp, SWT.BORDER);
        gd = new GridData();
        gd.widthHint = Constants.WIDGET_WIDTH_TEXT_NARROW;
        _partitionSizeTxt.setLayoutData(gd);
        _partitionSizeTxt.setToolTipText(Messages.ColumnDefaultValueInputDialog_partition_size_tip);
        _partitionSizeTxt.addModifyListener(new ModifyListener()
        {
            public void modifyText(ModifyEvent e)
            {
                if (!_partitionSizeTxt.getText().trim().equals("")) //$NON-NLS-1$
                {
                    try
                    {
                        int partitionSize = Integer.parseInt(_partitionSizeTxt.getText().trim());
                        if (partitionSize > 0)
                        {
                            setMessage(_message);
                            getButton(IDialogConstants.OK_ID).setEnabled(true);
                        }
                        else
                        {
                            setMessage(Messages.ColumnDefaultValueInputDialog_should_be_positive, IMessageProvider.ERROR);
                            getButton(IDialogConstants.OK_ID).setEnabled(false);
                        }
                    }
                    catch (Exception ex)
                    {
                        setMessage(Messages.ColumnDefaultValueInputDialog_not_valid_number + _partitionSizeTxt.getText(), IMessageProvider.ERROR);
                        getButton(IDialogConstants.OK_ID).setEnabled(false);
                    }
                }
                else
                {
                    setMessage(_message);
                    getButton(IDialogConstants.OK_ID).setEnabled(true);
                }
            }
        }
        );

        _computedValueRadio = new Button(outter, SWT.RADIO);
        _computedValueRadio.setText(Messages.ColumnDefaultValueInputDialog_computed_value);
        _computedValueRadio.setToolTipText(Messages.ColumnDefaultValueInputDialog_computed_value_tip);
        _computedValueRadio.addSelectionListener(new SelectionListener()
        {
            public void widgetDefaultSelected(SelectionEvent e)
            {
            }

            public void widgetSelected(SelectionEvent e)
            {
                _computedText.setEnabled(_computedValueRadio.getSelection());
                enableDefaultValue(_defaultValueRadio.getSelection());
                validateDefaultAndUpdate();
            }
        }
        );

        Composite computedTextComp = new Composite(outter, SWT.NONE);
        layout = new GridLayout();
        layout.numColumns = 2;
        computedTextComp.setLayout(layout);
        computedTextComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        blankLabel = new Label(computedTextComp, SWT.NONE);
        gd = new GridData();
        gd.widthHint = Constants.BLANK_WIDTH;
        blankLabel.setLayoutData(gd);

        _computedText = new Text(computedTextComp, SWT.BORDER | SWT.MULTI);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.heightHint = 60;
        _computedText.setLayoutData(gd);
        _computedText.addModifyListener(new ModifyListener()
        {
            public void modifyText(ModifyEvent e)
            {
                validateDefaultAndUpdate();
            }
        });
        
        Label sep = new Label(outter, SWT.SEPARATOR | SWT.HORIZONTAL);
        sep.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        Composite buttonComp = new Composite(outter, SWT.NONE);
        layout = new GridLayout();
        layout.numColumns = 3;
        buttonComp.setLayout(layout);
        buttonComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        blankLabel = new Label(buttonComp, SWT.NONE);
        blankLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        return outter;
    }

    protected Control createContents(Composite parent)
    {
        Control c = super.createContents(parent);
        initialize();
        setTitle(Messages.ColumnDefaultValueInputDialog_default_value_2);
        setMessage(_message); //$NON-NLS-1$
        if(_disableComputed)
        {
            setMessage(Messages.ColumnDefaultValueInputDialog_can_not_set_computed_value, IMessageProvider.INFORMATION);
        }
        return c;
    }

    protected void cancelPressed()
    {
        super.cancelPressed();
    }

    protected void okPressed()
    {
        String stringValue = ""; //$NON-NLS-1$
        _column.setIsComputedColumn(false);
        if (_noDefaultRadio.getSelection())
        {
            stringValue = null;
        }

        if (_defaultValueRadio.getSelection())
        {
            if (_userDefinedDefaultRadio.getSelection())
            {
                stringValue = _defaultValueTxt.getText();
                if (_isDefaultLiteralButton.getSelection())
                {
                    stringValue = SQLUtil.quote(stringValue, "'"); //$NON-NLS-1$
                }
            }
            if (_sysDefinedDefaultRadio.getSelection())
            {
                stringValue = _sysDefinedValuesCombo.getText();
                if (stringValue.equals(SybaseASADefaultWrapper.GLOBAL_AUTOINCREMENT))
                {
                    if (!_partitionSizeTxt.getText().trim().equals("")) //$NON-NLS-1$
                    {
                        stringValue = stringValue + "(" + Integer.parseInt(_partitionSizeTxt.getText().trim()) + ")"; //$NON-NLS-1$ //$NON-NLS-2$
                    }
                }
            }
        }

        if (_computedValueRadio.getSelection())
        {
            _column.setIsComputedColumn(true);
            stringValue = _computedText.getText();
        }

        if (stringValue != null && stringValue.equals("")) //$NON-NLS-1$
        {
            stringValue = null;
        }
        _column.setDefaultValue(stringValue);
        if (_computedValueRadio.getSelection())
        {
            _column.setIsComputedColumn(true);
        }
        else
        {
            _column.setIsComputedColumn(false);
        }

        super.okPressed();
    }

    private void validateDefaultAndUpdate()
    {
        if (_noDefaultRadio.getSelection()
                || (_computedValueRadio.getSelection() && _computedText.getText().trim().length() != 0))
        {
            setMessage(_message);
            getButton(IDialogConstants.OK_ID).setEnabled(true);
            return;
        }
        else if (_defaultValueRadio.getSelection() && _sysDefinedDefaultRadio.getSelection())
        {
            if (_sysDefinedValuesCombo.getText().equals("")) //$NON-NLS-1$
            {
                setMessage(Messages.ColumnDefaultValueInputDialog_select_a_system_default, IMessageProvider.INFORMATION);
                getButton(IDialogConstants.OK_ID).setEnabled(false);
            }
            else
            {
                getButton(IDialogConstants.OK_ID).setEnabled(true);
            }
            return;
        }
        else if (_defaultValueRadio.getSelection() && _userDefinedDefaultRadio.getSelection())
        {
            if (_defaultValueTxt.getText().trim().length() == 0)
            {
                setMessage(Messages.ColumnDefaultValueInputDialog_input_default, IMessageProvider.INFORMATION);
                getButton(IDialogConstants.OK_ID).setEnabled(false);
                return;
            }
            else
            {
                setMessage(_message);
                getButton(IDialogConstants.OK_ID).setEnabled(true);

                ISQLDataOfflineValidator validator = new ASASQLDataOfflineValidator();
                String errorMsg = validator.validate(_column.getDataType(),
                        _isDefaultLiteralButton.getSelection() ? SQLUtil.quote(_defaultValueTxt.getText(), "'") //$NON-NLS-1$
                                : _defaultValueTxt.getText());
                if (errorMsg != null)
                {
                    setMessage(errorMsg, IMessageProvider.ERROR);
                    getButton(IDialogConstants.OK_ID).setEnabled(false);
                }
                else
                {
                    setMessage(_message);
                    getButton(IDialogConstants.OK_ID).setEnabled(true);
                }
            }
        }
        else
        {
            setMessage(Messages.ColumnDefaultValueInputDialog_input_compute, IMessageProvider.INFORMATION);
            getButton(IDialogConstants.OK_ID).setEnabled(false);
            return;
        }
    }

    private void enableDefaultValue(boolean enabled)
    {
        _userDefinedDefaultRadio.setEnabled(enabled);
        _defaultValueTxt.setEnabled(enabled);

        _isDefaultLiteralButton.setEnabled(enabled);

        _sysDefinedDefaultRadio.setEnabled(enabled && _column.getDataType() != null);
        _sysDefinedValuesCombo.setEnabled(enabled && _column.getDataType() != null);
        _sysDefinedValuesCombo.notifyListeners(SWT.Selection, new Event());

        if (enabled)
        {
            _userDefinedDefaultRadio.notifyListeners(SWT.Selection, new Event());
        }
    }

    private void enableUserDefinedPart(boolean enabled)
    {
        _defaultValueTxt.setEnabled(enabled);

        _isDefaultLiteralButton.setEnabled(enabled);

        _sysDefinedValuesCombo.setEnabled(!enabled);
        _sysDefinedValuesCombo.notifyListeners(SWT.Selection, new Event());
    }

    /**
     * 
     */
    private void initialize()
    {
        //Disable the computed radio
        if(_disableComputed)
        {
            _computedValueRadio.setEnabled(false);
            _computedText.setEnabled(false); 
        }
        if (_column != null && _column.getDataType() != null)
        {
            DataType dType = _column.getDataType();
            if (ASADatatypeUtil.isStringType(dType))
            {
                _sysDefinedValuesCombo.setItems(SybaseASADefaultWrapper.STRING_TYPE_SYSTEM_DEFAULTS);
                _isDefaultLiteralButton.setSelection(true);
            }
            else if (ASADatatypeUtil.isNumericType(dType))
            {
                _sysDefinedValuesCombo.setItems(SybaseASADefaultWrapper.NUMERIC_TYPE_SYSTEM_DEFAULTS);
            }
            else if (ASADatatypeUtil.isTimeType(dType))
            {
                _sysDefinedValuesCombo.setItems(SybaseASADefaultWrapper.TIME_TYPE_SYSTEM_DEFAULTS);
                _isDefaultLiteralButton.setSelection(true);
            }
            else if (ASADatatypeUtil.isDateType(dType))
            {
                _sysDefinedValuesCombo.setItems(SybaseASADefaultWrapper.DATE_TYPE_SYSTEM_DEFAULTS);
                _isDefaultLiteralButton.setSelection(true);
            }
            else if(ASADatatypeUtil.isTSType(dType))
            {
                _sysDefinedValuesCombo.setItems(SybaseASADefaultWrapper.TS_TYPE_SYSTEM_DEFAULTS);
                _isDefaultLiteralButton.setSelection(true);
            }
            else if (ASADatatypeUtil.isBinaryType(dType))
            {
                _sysDefinedValuesCombo.setItems(SybaseASADefaultWrapper.BINARY_TYPE_SYSTEM_DEFAULTS);
            }
        }

        if (_column.getDefaultValue() == null)
        {
            _noDefaultRadio.setSelection(true);
            _noDefaultRadio.notifyListeners(SWT.Selection, new Event());
            return;
        }

        if (_defaultWrapper.getType() == TypeOfDefault.SYSTEM_DEFINED_LITERAL)
        {
            _defaultValueRadio.setSelection(true);
            _sysDefinedDefaultRadio.setSelection(true);
            _userDefinedDefaultRadio.setSelection(false);
            int index = getIndex(_defaultWrapper.getRawValue(), _sysDefinedValuesCombo.getItems());
            if (index > -1)
            {
                _sysDefinedValuesCombo.select(index);
            }

            if ((_defaultWrapper.getRawValue().equals(SybaseASADefaultWrapper.GLOBAL_AUTOINCREMENT))
            && (_defaultWrapper.getPartitionSize() > 0))
            {
                _partitionSizeTxt.setText(Integer.toString(_defaultWrapper.getPartitionSize()));
            }
            _defaultValueRadio.notifyListeners(SWT.Selection, new Event());
            _sysDefinedDefaultRadio.notifyListeners(SWT.Selection, new Event());
            return;
        }

        if (_defaultWrapper.getType() == TypeOfDefault.USER_DEFINED_LITERAL)
        {
            _defaultValueRadio.setSelection(true);
            _sysDefinedDefaultRadio.setSelection(false);
            _userDefinedDefaultRadio.setSelection(true);
            _defaultValueRadio.notifyListeners(SWT.Selection, new Event());
            _userDefinedDefaultRadio.notifyListeners(SWT.Selection, new Event());
            _defaultValueTxt.setText(_defaultWrapper.getRawValue());
            _isDefaultLiteralButton.setSelection(_defaultWrapper.isIsLiteral());
            validateDefaultAndUpdate();
            return;
        }

        if (_defaultWrapper.getType() == TypeOfDefault.COMPUTED_VALUE_LITERAL)
        {
            _computedValueRadio.setSelection(true);
            _computedValueRadio.notifyListeners(SWT.Selection, new Event());
            _computedText.setText(_column.getDefaultValue());
            return;
        }
    }

    private int getIndex(String item, String[] items)
    {
        if (items == null || item == null)
        {
            return -1;
        }

        for (int i = 0; i < items.length; i++)
        {
            if (items[i] != null && items[i].equals(item))
            {
                return i;
            }
        }
        return -1;
    }
}

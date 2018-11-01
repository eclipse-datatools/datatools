/***********************************************************************************************************************
 * Copyright (c) 2005 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.preferences;

import java.util.Collection;

import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.common.ui.util.SWTUtils;
import org.eclipse.datatools.sqltools.common.ui.util.TableLayoutComposite;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.sql.parser.SQLParser;
import org.eclipse.datatools.sqltools.sqleditor.internal.IHelpContextIds;
import org.eclipse.datatools.sqltools.sqleditor.internal.PreferenceConstants;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;

/**
 * Configure SQL Editor preferences.
 * 
 * @author Li Huang
 */
public class SQLEditorPage extends PreferencePage implements IWorkbenchPreferencePage
{

    private IPreferenceStore _store                 = getPreferenceStore();

    private PreferencePage   _preferencePage;
    //General tab variables
    private Button           _syntaxValidation;
    private Label            _portabilityCheckLabel;
    private Combo            _portabilityCheckCombo;
    private Button           _maxLineButton;
    private Text             _maxLineText;
    private Button           _promptDisableButton;
    private Button           _showSyntaxErorrDetail;
    private Button           _executeBetweenDelimiter;
    private Combo            _terminatorsCombo;
    private Button           _executeCurrentLine;
    private Button           _executeBetweenBlankLine;


    //Typing tab variables
    private Table            _typingAidsTable;
    private Text             _description;
    private final String[]   _typingAidsName        = new String[]
    {
        PreferenceMessages.SQLEditor_closeSingleQuotes,
            PreferenceMessages.SQLEditor_closeDoubleQuotes,
            PreferenceMessages.SQLEditor_closeBrackets,
            PreferenceMessages.SQLEditor_closeComments,
            PreferenceMessages.SQLEditor_beginEndStatement
    }
    ;
    private final String[]   _typingAidsContext     = new String[]
    {
        PreferenceMessages.SQLEditor_typingAidsTable_context_all,
            PreferenceMessages.SQLEditor_typingAidsTable_context_all,
            PreferenceMessages.SQLEditor_typingAidsTable_context_all,
            PreferenceMessages.SQLEditor_typingAidsTable_context_all,
            PreferenceMessages.SQLEditor_typingAidsTable_context_all

    }
    ;
    private final String[]   _typingAidsDescription = new String[]
    {
        PreferenceMessages.SQLEditor_closeSingleQuotes_description,
            PreferenceMessages.SQLEditor_closeDoubleQuotes_description,
            PreferenceMessages.SQLEditor_closeBrackets_description,
            PreferenceMessages.SQLEditor_closeComments_description,
            PreferenceMessages.SQLEditor_beginEndStatement_description
    }
    ;
    private final String[]   _typingAidsPreview     = new String[]
    {
        PreferenceMessages.SQLEditor_closeSingleQuotes_preview,
            PreferenceMessages.SQLEditor_closeDoubleQuotes_preview,
            PreferenceMessages.SQLEditor_closeBrackets_preview,
            PreferenceMessages.SQLEditor_closeComments_preview,
            PreferenceMessages.SQLEditor_beginEndStatement_preview
    }
    ;
    private final String[]   _typingAidsPreferences = new String[]
    {
        PreferenceConstants.SQLEDITOR_CLOSE_SINGLE_QUOTES, PreferenceConstants.SQLEDITOR_CLOSE_DOUBLE_QUOTES,
            PreferenceConstants.SQLEDITOR_CLOSE_BRACKETS, PreferenceConstants.SQLEDITOR_CLOSE_COMMENTS,
            PreferenceConstants.SQLEDITOR_CLOSE_BEGIN_END
    }
    ;

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
     */
    protected Control createContents(Composite parent)
    {

        _preferencePage = this;

        PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), HelpUtil.getContextId(IHelpContextIds.PREFERENCES_SQL_EDITOR, SQLEditorPlugin.getDefault().getBundle().getSymbolicName()));

        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout compositeLayout = new GridLayout(1, true);
        compositeLayout.marginHeight = 0;
        compositeLayout.marginWidth = 0;
        composite.setLayout(compositeLayout);

        TabFolder folder = new TabFolder(composite, SWT.NONE);
        GridLayout gridLayout = new GridLayout();
        folder.setLayout(gridLayout);
        GridData gridData = new GridData(GridData.FILL_BOTH);
        folder.setLayoutData(gridData);

        TabItem item = new TabItem(folder, SWT.NONE);
        item.setText(PreferenceMessages.SQLEditor_general_title); 
        item.setControl(createAppearancePage(folder));

        item = new TabItem(folder, SWT.NONE);
        item.setText(PreferenceMessages.SQLEditor_typing_title); 
        item.setControl(createTypingPage(folder));

        initializeValues();
        update();
        updateMaxLineText();
        return composite;
    }

    private Control createAppearancePage(Composite parent)
    {
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout gridLayout = new GridLayout(1, true);
        composite.setLayout(gridLayout);

        // syntax validation group
        Group syntaxValidationGroup = SWTUtils.createGroup(composite, PreferenceMessages.SQLEditor_syntaxValidationGroup, 1);

        Composite composite1 = SWTUtils.createComposite(syntaxValidationGroup, 2);
        // Syntax validation
        _syntaxValidation = SWTUtils.createCheckBox(composite1, PreferenceMessages.SQLEditor_syntaxValidation, 2, 0);
        _syntaxValidation.setToolTipText(PreferenceMessages.SQLEditor_syntaxValidation_tooltip);
        _syntaxValidation.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                update();
            }
        }
        );
        createPortabilityCheckCombo(composite1);


        //syntax validation limitation group
        _maxLineButton = new Button(composite1, SWT.CHECK);
        _maxLineButton.setText(PreferenceMessages.SQLEditor_maxLineButton);
        _maxLineButton.setToolTipText(PreferenceMessages.SQLEditor_maxLineButton_tooltip);
        _maxLineButton.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                updateMaxLineText();
            }
        }
        );
        _maxLineText = SWTUtils.createTextBox(composite1, 1, 40);
        _maxLineText.addModifyListener(new ModifyListener()
        {

            public void modifyText(ModifyEvent e)
            {
                try
                {
                    int maxRow = 0;
                    maxRow = Integer.parseInt(((Text) e.widget).getText().trim());
                    if (maxRow <= 0)
                    {
                        _preferencePage.setMessage(PreferenceMessages.General_max_row_num, IMessageProvider.ERROR); 
                        _preferencePage.setValid(false);
                    }
                    else
                    {
                        _preferencePage.setMessage(null);
                        _preferencePage.setValid(true);
                    }
                }
                catch (NumberFormatException e1)
                {
                    _preferencePage.setMessage((((Text) e.widget).getText().trim() + PreferenceMessages.General_invalid_int), 
                        IMessageProvider.ERROR);
                    _preferencePage.setValid(false);
                }

            }
        }
        );

        _promptDisableButton = SWTUtils.createCheckBox(syntaxValidationGroup, PreferenceMessages.SQLEditor_showPromptDialog, 1, 20);
        
        _showSyntaxErorrDetail = SWTUtils.createCheckBox(syntaxValidationGroup, PreferenceMessages.SQLEditor_showSyntaxErorrDetail, 2, 5);
        _showSyntaxErorrDetail.setToolTipText(PreferenceMessages.SQLEditor_showSyntaxErorrDetail_tooltip);
        
        Group executeSelectedSQLGroup = SWTUtils.createGroup(composite,
                PreferenceMessages.SQLEditor_executeCurrentSQLGroup, 2);

        _executeBetweenDelimiter = new Button(executeSelectedSQLGroup, SWT.RADIO);
        _executeBetweenDelimiter.setText(PreferenceMessages.SQLEditor_executeSQLBetweenDelimiter);
        _executeBetweenDelimiter.setToolTipText(PreferenceMessages.SQLEditor_executeSQLBetweenDelimiter_tip);
        _executeBetweenDelimiter.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                _terminatorsCombo.setEnabled(_executeBetweenDelimiter.getSelection());
            }
        });
        _terminatorsCombo = SWTUtils.createCombo(executeSelectedSQLGroup, new String[]
        {
            "go", ";", "/"
        }, 1);

        _executeCurrentLine = new Button(executeSelectedSQLGroup, SWT.RADIO);
        _executeCurrentLine.setText(PreferenceMessages.SQLEditor_executeCurrentLine);
        _executeCurrentLine.setToolTipText(PreferenceMessages.SQLEditor_executeCurrentLine_tip);
        GridData data = new GridData(GridData.FILL_BOTH);
        data.horizontalSpan = 2;
        _executeCurrentLine.setLayoutData(data);

        _executeBetweenBlankLine = new Button(executeSelectedSQLGroup, SWT.RADIO);
        _executeBetweenBlankLine.setText(PreferenceMessages.SQLEditor_executeSQLBetweenBlankLine);
        _executeBetweenBlankLine.setToolTipText(PreferenceMessages.SQLEditor_executeSQLBetweenBlankLine_tip);
        data = new GridData(GridData.FILL_BOTH);
        data.horizontalSpan = 2;
        _executeBetweenBlankLine.setLayoutData(data);
        
        return composite;
    }

    private void createPortabilityCheckCombo(Composite parent)
    {

        Collection dbnames = SQLToolsFacade.getSupportedDBDefinitionNames();
        String[] names = (String[])dbnames.toArray(new String[dbnames.size()]);
        String[] nameValues = new String[names.length + 1];
        nameValues[0] = PreferenceMessages.GeneralPreferencePage_portable_target;
        // FOR CR:403675-1 remove the portability checks option that has no parser in preference page  
        int j = 0;
        for (int i = 0; i < names.length; i++)
        {
        	SQLDevToolsConfiguration config = SQLToolsFacade.getConfigurationByDBDefName(names[i]);
            SQLParser pp = config.getSQLService().getSQLParser();
            //For CR:list all supported database definitions
            if (pp != null && pp.isComplete())
            {
                nameValues[j + 1] = names[i];
                j++;
            }
        }
        String[] portNameValues = new String[j + 1];
        for (int i = 0; i < j + 1; i++)
        {
            portNameValues[i] = nameValues[i];
        }
        _portabilityCheckLabel = new Label(parent, SWT.NONE);
        _portabilityCheckLabel.setText(PreferenceMessages.GeneralPreferencePage_portable_check);
        _portabilityCheckCombo = new Combo(parent, SWT.READ_ONLY);
        _portabilityCheckCombo.setItems(portNameValues);


    }


    private void update()
    {
        boolean syntaxValidationEnabled = _syntaxValidation.getSelection();
        _portabilityCheckLabel.setEnabled(syntaxValidationEnabled);
        _portabilityCheckCombo.setEnabled(syntaxValidationEnabled);
        _maxLineButton.setEnabled(syntaxValidationEnabled);
        _maxLineText.setEnabled(syntaxValidationEnabled);
        _promptDisableButton.setEnabled(syntaxValidationEnabled);
        _showSyntaxErorrDetail.setEnabled(syntaxValidationEnabled);
        updateMaxLineText();
    }

    private void updateMaxLineText()
    {
        if (_syntaxValidation.getSelection())
        {
            _maxLineText.setEnabled(_maxLineButton.getSelection());
            _promptDisableButton.setEnabled(_maxLineButton.getSelection());
        }
    }
    
    private void updateTerminatorsCombo()
    {
        _terminatorsCombo.setEnabled(_executeBetweenDelimiter.getSelection());
    }
    
    private Control createTypingPage(Composite parent)
    {
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayout gridLayout = new GridLayout(1, true);
        composite.setLayout(gridLayout);

        //Auto completion group
        Group typingAidsGroup = SWTUtils.createGroup(composite, PreferenceMessages.SQLEditor_typingAidsGroup_title, 2);
        typingAidsGroup.setLayout(new GridLayout());
        typingAidsGroup.setLayoutData(new GridData(GridData.FILL_BOTH));

        TableLayoutComposite layouter = new TableLayoutComposite(typingAidsGroup, SWT.NONE);
        addColumnLayoutData(layouter);

        _typingAidsTable = new Table(layouter, SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE | SWT.BORDER
            | SWT.FULL_SELECTION | SWT.CHECK);
        _typingAidsTable.setHeaderVisible(true);
        _typingAidsTable.setLinesVisible(true);

        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.heightHint = SWTUtils.getTableHeightHint(_typingAidsTable, 8);
        layouter.setLayoutData(gd);

        _typingAidsTable.addSelectionListener(new SelectionListener()
        {
            public void widgetSelected(SelectionEvent e)
            {

                handleTypingAidsListSelection();
            }

            public void widgetDefaultSelected(SelectionEvent e)
            {
            }
        }
        );

        //Name
        TableColumn nameColumn = new TableColumn(_typingAidsTable, SWT.NONE);
        nameColumn.setText(PreferenceMessages.SQLEditor_typingAidsTable_columnName1); 
        nameColumn.setResizable(true);

        //Context
        nameColumn = new TableColumn(_typingAidsTable, SWT.NONE);
        nameColumn.setText(PreferenceMessages.SQLEditor_typingAidsTable_columnName2); 
        nameColumn.setResizable(true);
        nameColumn.pack();

        //Description
        nameColumn = new TableColumn(_typingAidsTable, SWT.NONE);
        nameColumn.setText(PreferenceMessages.SQLEditor_typingAidsTable_columnName3); 
        nameColumn.setResizable(true);

        for (int i = 0; i < _typingAidsName.length; i++)
        {
            TableItem nameItem = new TableItem(_typingAidsTable, SWT.NONE);
            nameItem.setText(0, _typingAidsName[i]);
            nameItem.setText(1, _typingAidsContext[i]);
            nameItem.setText(2, _typingAidsDescription[i]);
        }

        // Preview label
        Label previewLabel = new Label(typingAidsGroup, SWT.LEFT);
        previewLabel.setText(PreferenceMessages.SQLEditor_typingAids_preview); 
        // Preview text
        _description = new Text(typingAidsGroup, SWT.LEFT | SWT.WRAP | SWT.MULTI | SWT.READ_ONLY | SWT.BORDER);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.heightHint = 50;
        _description.setLayoutData(gd);

        return composite;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(IWorkbench workbench)
    {
    }

    /*
     * Initializes states of the controls from the preference store.
     */
    private void initializeValues()
    {
        _syntaxValidation.setSelection(_store.getBoolean(PreferenceConstants.SYNTAX_VALIDATION));

        _portabilityCheckCombo.setText(_store.getString(PreferenceConstants.EDITOR_PORTABILITY_CHECK_TARGET));

        _maxLineButton.setSelection(_store.getBoolean(PreferenceConstants.SYNTAX_VALIDATION_MAX_LINE));

        _maxLineText.setText(_store.getString(PreferenceConstants.SYNTAX_VALIDATION_MAX_LINE_NUMBER));

        _promptDisableButton.setSelection(_store.getBoolean(PreferenceConstants.SHOW_DAILOG_FOR_SYNTAX_VALIDATION));

        _showSyntaxErorrDetail.setSelection(_store.getBoolean(PreferenceConstants.SHOW_SYNTAX_ERROR_DETAIL));
        
        // execute sql
        String executePolicy = _store.getString(PreferenceConstants.EXECUTE_SELECTED_SQL);
        _executeBetweenDelimiter.setSelection(executePolicy.equalsIgnoreCase(PreferenceConstants.EXECUTE_SQL_BETWEEN_DELIMITER));
        _executeCurrentLine.setSelection(executePolicy.equalsIgnoreCase(PreferenceConstants.EXECUTE_SQL_CURRENT_LINE));
        _executeBetweenBlankLine.setSelection(executePolicy.equalsIgnoreCase(PreferenceConstants.EXECUTE_SQL_BETWEEN_BLANK_LINE));
        String term = _store.getString(PreferenceConstants.EXECUTE_SQL_DELIMITER_TYPE);
        setTerminatorsCombo(term);
        updateTerminatorsCombo();
        
        // typing
        for (int i = 0; i < _typingAidsName.length; i++)
        {
            _typingAidsTable.getItems()[i].setChecked(_store.getBoolean(_typingAidsPreferences[i]));
        }
    }

    /**
     * Stores the values of the controls back to the preference store. This is called when the user presses the OK or
     * Apply button.
     */
    public boolean performOk()
    {
        _store.setValue(PreferenceConstants.SYNTAX_VALIDATION, _syntaxValidation.getSelection());

        _store.setValue(PreferenceConstants.EDITOR_PORTABILITY_CHECK_TARGET, _portabilityCheckCombo.getText());

        _store.setValue(PreferenceConstants.SYNTAX_VALIDATION_MAX_LINE, _maxLineButton.getSelection());

        _store.setValue(PreferenceConstants.SYNTAX_VALIDATION_MAX_LINE_NUMBER, _maxLineText.getText());

        _store.setValue(PreferenceConstants.SHOW_DAILOG_FOR_SYNTAX_VALIDATION, _promptDisableButton.getSelection());

        _store.setValue(PreferenceConstants.SHOW_SYNTAX_ERROR_DETAIL, _showSyntaxErorrDetail.getSelection());
        
        if (_executeBetweenDelimiter.getSelection())
        {
            _store
                    .setValue(PreferenceConstants.EXECUTE_SELECTED_SQL,
                            PreferenceConstants.EXECUTE_SQL_BETWEEN_DELIMITER);
        }
        else if (_executeCurrentLine.getSelection())
        {
            _store.setValue(PreferenceConstants.EXECUTE_SELECTED_SQL, PreferenceConstants.EXECUTE_SQL_CURRENT_LINE);
        }
        else
        {
            _store.setValue(PreferenceConstants.EXECUTE_SELECTED_SQL,
                    PreferenceConstants.EXECUTE_SQL_BETWEEN_BLANK_LINE);
        }
        _store.setValue(PreferenceConstants.EXECUTE_SQL_DELIMITER_TYPE, _terminatorsCombo.getItem(_terminatorsCombo.getSelectionIndex()));

        // typing
        for (int i = 0; i < _typingAidsName.length; i++)
        {
            _store.setValue(_typingAidsPreferences[i], _typingAidsTable.getItems()[i].getChecked());
        }
        return super.performOk();
    }

    /*
     * This is called when the Defaults button is pressed.
     * 
     * @see PreferencePage.performDefaults()
     */
    protected void performDefaults()
    {
        super.performDefaults();

        _syntaxValidation.setSelection(_store.getDefaultBoolean(PreferenceConstants.SYNTAX_VALIDATION));

        _portabilityCheckCombo.setText(_store.getDefaultString(PreferenceConstants.EDITOR_PORTABILITY_CHECK_TARGET));

        _maxLineButton.setSelection(_store.getDefaultBoolean(PreferenceConstants.SYNTAX_VALIDATION_MAX_LINE));
        updateMaxLineText();

        _maxLineText.setText(_store.getDefaultString(PreferenceConstants.SYNTAX_VALIDATION_MAX_LINE_NUMBER));

        _promptDisableButton.setSelection(_store.getDefaultBoolean(PreferenceConstants.SHOW_DAILOG_FOR_SYNTAX_VALIDATION));

        _showSyntaxErorrDetail.setSelection(_store.getDefaultBoolean(PreferenceConstants.SHOW_SYNTAX_ERROR_DETAIL));
        
        // execute sql
        String executePolicy = _store.getDefaultString(PreferenceConstants.EXECUTE_SELECTED_SQL);

        _executeBetweenDelimiter.setSelection(executePolicy.equalsIgnoreCase(PreferenceConstants.EXECUTE_SQL_BETWEEN_DELIMITER));
        _executeCurrentLine.setSelection(executePolicy.equalsIgnoreCase(PreferenceConstants.EXECUTE_SQL_CURRENT_LINE));
        _executeBetweenBlankLine.setSelection(executePolicy.equalsIgnoreCase(PreferenceConstants.EXECUTE_SQL_BETWEEN_BLANK_LINE));
        String term = _store.getDefaultString(PreferenceConstants.EXECUTE_SQL_DELIMITER_TYPE);
        setTerminatorsCombo(term);
        updateTerminatorsCombo();
        
        // typing
        for (int i = 0; i < _typingAidsName.length; i++)
        {
            _typingAidsTable.getItems()[i].setChecked(_store.getDefaultBoolean(_typingAidsPreferences[i]));
        }
        update();
    }
    
    /**
     * @param term
     */
    private void setTerminatorsCombo(String term)
    {
        String[] items = _terminatorsCombo.getItems();
        int match = 0;
        for (match = 0; match < items.length; match ++)
        {
            if (term.equalsIgnoreCase(items[match]))
            {
                break;
            }
        }
        if (match >= items.length)
        {
            match = 0;
        }
        _terminatorsCombo.select(match);
    }

    /*
     * Returns preference store that belongs to the our plugin.
     */
    protected IPreferenceStore doGetPreferenceStore()
    {
        return SQLEditorPlugin.getDefault().getPreferenceStore();
    }

    private void handleTypingAidsListSelection()
    {
        int i = _typingAidsTable.getSelectionIndex();

        if (i == -1)
        {
            return;
        }

        _description.setText(_typingAidsPreview[i]);
    }

    private void addColumnLayoutData(TableLayoutComposite layouter)
    {
        layouter.addColumnData(new ColumnWeightData(35, true));
        layouter.addColumnData(new ColumnWeightData(15, true));
        layouter.addColumnData(new ColumnWeightData(40, true));
    }

}





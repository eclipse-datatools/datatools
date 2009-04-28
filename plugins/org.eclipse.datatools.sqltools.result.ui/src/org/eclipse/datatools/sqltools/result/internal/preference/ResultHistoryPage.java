/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.preference;

import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.ui.PreferenceConstants;
import org.eclipse.datatools.sqltools.result.ui.IHelpConstants;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.help.IContext;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Result history options preference page
 * 
 * @author Dafan Yang
 */
public class ResultHistoryPage extends PreferencePage implements IWorkbenchPreferencePage
{
    private Group     _columnsGroup;
    private Button    _statusColumn;
    private Button    _operColumn;
    private Button    _freqColumn;
    private Button    _dateColumn;
    private Button    _actionColumn;
    private Button    _consumerColumn;
    private Button    _profileColumn;
    private Composite _otherComp;
    private Button    _autoSaveHistory;
    private Button    _autoCleanHistory;
    
    private ContextProviderDelegate contextProviderDelegate = new ContextProviderDelegate(ResultsViewUIPlugin.getDefault().getBundle().getSymbolicName());
    
    public IContext getContext(Object target)
    {
        return contextProviderDelegate.getContext(target);
    }

    public int getContextChangeMask()
    {
        return contextProviderDelegate.getContextChangeMask();
    }

    public String getSearchExpression(Object target)
    {
        return contextProviderDelegate.getSearchExpression(target);
    }
    
    private SelectionListener _selectionListener = new SelectionListener()
    {
        public void widgetDefaultSelected(SelectionEvent e)
        {
        }

        public void widgetSelected(SelectionEvent e)
        {
            if (!_statusColumn.getSelection()
                && !_operColumn.getSelection()
                && !_freqColumn.getSelection()
                && !_dateColumn.getSelection()
                && !_actionColumn.getSelection()
                && !_consumerColumn.getSelection()
                && !_profileColumn.getSelection())
            {
                setErrorMessage(Messages.ResultHistoryPage_no_column_selected);
                setValid(false);
                updateApplyButton();
            }
            else
            {
                setErrorMessage(null);
                setValid(true);
                updateApplyButton();
            }
        }
        
    };
    
    protected Control createContents(Composite parent)
    {
        getShell().setData(HelpUtil.CONTEXT_PROVIDER_KEY, this);
        HelpUtil.setHelp(parent, HelpUtil.getContextId(IHelpConstants.PREFERENCE_PAGE_RESULT_HISTORY, ResultsViewUIPlugin.getDefault().getBundle().getSymbolicName()));
        
        Composite comp = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        comp.setLayout(layout);
        
        _columnsGroup = new Group(comp, SWT.NONE);
        _columnsGroup.setText(Messages.ResultHistoryPage_columns_group_name); 
        _columnsGroup.setLayout(new GridLayout());
        _columnsGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        _statusColumn = new Button(_columnsGroup, SWT.CHECK);
        _statusColumn.setText(Messages.ResultHistoryPage_status); 
        _statusColumn.setToolTipText(Messages.ResultHistoryPage_tooltip_status); 
        _statusColumn.addSelectionListener(_selectionListener);
        
        _operColumn = new Button(_columnsGroup, SWT.CHECK);
        _operColumn.setText(Messages.ResultHistoryPage_operation); 
        _operColumn.setToolTipText(Messages.ResultHistoryPage_tooltip_operation); 
        _operColumn.addSelectionListener(_selectionListener);
        
        _freqColumn = new Button(_columnsGroup, SWT.CHECK);
        _freqColumn.setText(Messages.ResultHistoryPage_frequency); 
        _freqColumn.setToolTipText(Messages.ResultHistoryPage_tooltip_frequency); 
        _freqColumn.addSelectionListener(_selectionListener);
        
        _dateColumn = new Button(_columnsGroup, SWT.CHECK);
        _dateColumn.setText(Messages.ResultHistoryPage_date); 
        _dateColumn.setToolTipText(Messages.ResultHistoryPage_tooltip_date); 
        _dateColumn.addSelectionListener(_selectionListener);
        
        _actionColumn = new Button(_columnsGroup, SWT.CHECK);
        _actionColumn.setText(Messages.ResultHistoryPage_action_type); 
        _actionColumn.setToolTipText(Messages.ResultHistoryPage_tooltip_actiontype); 
        _actionColumn.addSelectionListener(_selectionListener);
        
        _consumerColumn = new Button(_columnsGroup, SWT.CHECK);
        _consumerColumn.setText(Messages.ResultHistoryPage_consumer_name); 
        _consumerColumn.setToolTipText(Messages.ResultHistoryPage_tooltip_consume); 
        _consumerColumn.addSelectionListener(_selectionListener);
        
        _profileColumn = new Button(_columnsGroup, SWT.CHECK);
        _profileColumn.setText(Messages.ResultHistoryPage_connection_profile); 
        _profileColumn.setToolTipText(Messages.ResultHistoryPage_tooltip_profile); 
        _profileColumn.addSelectionListener(_selectionListener);
        
        _otherComp = new Composite(comp, SWT.NONE);
        layout = new GridLayout();
        layout.numColumns = 1;
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        
        _otherComp.setLayout(layout);
        _otherComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        _autoSaveHistory = new Button(_otherComp, SWT.CHECK);
        _autoSaveHistory.setText(Messages.ResultHistoryPage_auto_persist); 
        _autoSaveHistory.setToolTipText(Messages.ResultHistoryPage_tooltip_auto_persist); 

        _autoCleanHistory = new Button(_otherComp, SWT.CHECK);
        _autoCleanHistory.setText(Messages.ResultHistoryPage_auto_clean);
        _autoCleanHistory.setToolTipText(Messages.ResultHistoryPage_tooltip_auto_clean);
        
        initializeValues();
        return comp;
    }

    public void init(IWorkbench workbench)
    {
        setPreferenceStore(ResultsViewUIPlugin.getDefault().getPreferenceStore());        
    }

    private void initializeValues()
    {
        IPreferenceStore store = getPreferenceStore();
        _statusColumn.setSelection(store.getBoolean(PreferenceConstants.RESULT_HISTORY_STATUS_COLUMN));
        _operColumn.setSelection(store.getBoolean(PreferenceConstants.RESULT_HISTORY_OPER_COLUMN));
        _freqColumn.setSelection(store.getBoolean(PreferenceConstants.RESULT_HISTORY_FREQ_COLUMN));
        _dateColumn.setSelection(store.getBoolean(PreferenceConstants.RESULT_HISTORY_DATE_COLUMN));
        _actionColumn.setSelection(store.getBoolean(PreferenceConstants.RESULT_HISTORY_ACTION_COLUMN));
        _consumerColumn.setSelection(store.getBoolean(PreferenceConstants.RESULT_HISTORY_CONSUMER_COLUMN));
        _profileColumn.setSelection(store.getBoolean(PreferenceConstants.RESULT_HISTORY_PROFILE_COLUMN));
        _autoSaveHistory.setSelection(store.getBoolean(PreferenceConstants.RESULT_HISTORY_SAVE_HISTORY));
        _autoCleanHistory.setSelection(store.getBoolean(PreferenceConstants.RESULT_HISTORY_CLEAN_HISTORY));
    }
    
    protected void performDefaults()
    {
        IPreferenceStore store = getPreferenceStore();
        _statusColumn.setSelection(store.getDefaultBoolean(PreferenceConstants.RESULT_HISTORY_STATUS_COLUMN));
        _operColumn.setSelection(store.getDefaultBoolean(PreferenceConstants.RESULT_HISTORY_OPER_COLUMN));
        _freqColumn.setSelection(store.getDefaultBoolean(PreferenceConstants.RESULT_HISTORY_FREQ_COLUMN));
        _dateColumn.setSelection(store.getDefaultBoolean(PreferenceConstants.RESULT_HISTORY_DATE_COLUMN));
        _actionColumn.setSelection(store.getDefaultBoolean(PreferenceConstants.RESULT_HISTORY_ACTION_COLUMN));
        _consumerColumn.setSelection(store.getDefaultBoolean(PreferenceConstants.RESULT_HISTORY_CONSUMER_COLUMN));
        _profileColumn.setSelection(store.getDefaultBoolean(PreferenceConstants.RESULT_HISTORY_PROFILE_COLUMN));
        _autoSaveHistory.setSelection(store.getDefaultBoolean(PreferenceConstants.RESULT_HISTORY_SAVE_HISTORY));
        _autoCleanHistory.setSelection(store.getDefaultBoolean(PreferenceConstants.RESULT_HISTORY_CLEAN_HISTORY));
        
        super.performDefaults();
    }

    public boolean performOk()
    {
        IPreferenceStore store = getPreferenceStore();
        store.setValue(PreferenceConstants.RESULT_HISTORY_STATUS_COLUMN, _statusColumn.getSelection());
        store.setValue(PreferenceConstants.RESULT_HISTORY_OPER_COLUMN, _operColumn.getSelection());
        store.setValue(PreferenceConstants.RESULT_HISTORY_FREQ_COLUMN, _freqColumn.getSelection());
        store.setValue(PreferenceConstants.RESULT_HISTORY_DATE_COLUMN, _dateColumn.getSelection());
        store.setValue(PreferenceConstants.RESULT_HISTORY_ACTION_COLUMN, _actionColumn.getSelection());
        store.setValue(PreferenceConstants.RESULT_HISTORY_CONSUMER_COLUMN, _consumerColumn.getSelection());
        store.setValue(PreferenceConstants.RESULT_HISTORY_PROFILE_COLUMN, _profileColumn.getSelection());
        store.setValue(PreferenceConstants.RESULT_HISTORY_SAVE_HISTORY, _autoSaveHistory.getSelection());
        store.setValue(PreferenceConstants.RESULT_HISTORY_CLEAN_HISTORY, _autoCleanHistory.getSelection());
        
        return super.performOk();
    } 
}

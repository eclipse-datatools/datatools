/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.preference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.result.internal.ui.AbstractShiftedListSection;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.ui.PreferenceConstants;
import org.eclipse.datatools.sqltools.result.internal.ui.view.ResultHistoryHelper;
import org.eclipse.datatools.sqltools.result.ui.IHelpConstants;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.help.IContext;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Result history options preference page
 * 
 * @author Dafan Yang
 */
public class ResultHistoryPage extends PreferencePage implements IWorkbenchPreferencePage
{
    private String[] _initVisibleColumns, _initInvisibleColumns;
    private AbstractShiftedListSection _shiftedListSection;
    
    private Composite _otherComp;
    private Button    _autoSaveHistory;
    private Button    _autoCleanHistory;
    
    private ContextProviderDelegate contextProviderDelegate = new ContextProviderDelegate(ResultsViewUIPlugin.getDefault().getBundle().getSymbolicName());
    
    class ColumnsDisplaySection extends AbstractShiftedListSection
    {
        public ColumnsDisplaySection(String sectionTitle, String leftTitle, String rightTitle)
        {
            super(sectionTitle, leftTitle, rightTitle);
        }
        
        PaintListener paintListener = new PaintListener()
        {
            public void paintControl(PaintEvent e)
            {
                if (_leftList.getItems().length < 1)
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
        
        public Control createContents(Composite parent)
        {
            Control control = super.createContents(parent);
            _leftList.addPaintListener(paintListener);
            setInput(_initVisibleColumns, _initInvisibleColumns, true);
            return control;
        }
        
        Map getCloumnsDisplayInfo()
        {
            Map result = new HashMap();
            String name = "";
            
            String[] items = _leftList.getItems();
            for(int i = 0; i < items.length; i++)
            {
                name = items[i];
                result.put(name, _leftList.getData(name));
            }
            
            items = _rightList.getItems();
            for(int i = 0; i < items.length; i++)
            {
                name = items[i];
                result.put(name, _rightList.getData(name));
            }
            
            return result;
        }
    }
    
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
        
        initShiftedListValues(true);
        _shiftedListSection = new ColumnsDisplaySection(Messages.ResultHistoryPage_columns_group_name, 
                Messages.ResultHistoryPage_columns_selectedlist_name,
                Messages.ResultHistoryPage_columns_availablelist_name);
        _shiftedListSection.createContents(comp);
        
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
        
        _autoSaveHistory.setSelection(store.getBoolean(PreferenceConstants.RESULT_HISTORY_SAVE_HISTORY));
        _autoCleanHistory.setSelection(store.getBoolean(PreferenceConstants.RESULT_HISTORY_CLEAN_HISTORY));
    }

    private void initShiftedListValues(boolean usePreference)
    {
        ResultHistoryHelper.refreshOrderFromPreference(usePreference);
        
        int len = ResultHistoryHelper.getColumnNumber(usePreference);
        _initVisibleColumns = new String[len];
        _initInvisibleColumns = new String[ResultHistoryHelper.COLUMN_NAME.length - len];
        
        for(Iterator iter = ResultHistoryHelper.COLUMN_PREFERENCE_ORDER_MAP.keySet().iterator(); iter.hasNext(); )
        {
            Object prefName = iter.next();
            int index = ((Integer)ResultHistoryHelper.COLUMN_PREFERENCE_ORDER_MAP.get(prefName)).intValue();
            
            if(index < 0)
            {
                continue;
            }
            
            for(Iterator iter1 = ResultHistoryHelper.COLUMN_NAME_PREFERENCE_MAP.keySet().iterator(); iter1.hasNext();)
            {
                Object columnName = iter1.next();
                
                if(ResultHistoryHelper.COLUMN_NAME_PREFERENCE_MAP.get(columnName).equals(prefName))
                {
                    _initVisibleColumns[index] = (String)columnName;
                }
            }
        }
        
        List visibleList = Arrays.asList(_initVisibleColumns);
        List invisibleList = new ArrayList(Arrays.asList(ResultHistoryHelper.COLUMN_NAME));
        
        invisibleList.removeAll(visibleList);
        invisibleList.toArray(_initInvisibleColumns);
    }
    
    protected void performDefaults()
    {
        initShiftedListValues(false);
        _shiftedListSection.setInput(_initVisibleColumns, _initInvisibleColumns, true);
        
        IPreferenceStore store = getPreferenceStore();
        _autoSaveHistory.setSelection(store.getDefaultBoolean(PreferenceConstants.RESULT_HISTORY_SAVE_HISTORY));
        _autoCleanHistory.setSelection(store.getDefaultBoolean(PreferenceConstants.RESULT_HISTORY_CLEAN_HISTORY));
        
        super.performDefaults();
    }

    public boolean performOk()
    {
        IPreferenceStore store = getPreferenceStore();
        
        Map columnNameOrderMap = ((ColumnsDisplaySection)_shiftedListSection).getCloumnsDisplayInfo();
        
        for(Iterator iter = columnNameOrderMap.keySet().iterator(); iter.hasNext();)
        {
            Object columnName = iter.next();
            Object preference = ResultHistoryHelper.COLUMN_NAME_PREFERENCE_MAP.get(columnName);
            Object order = columnNameOrderMap.get(columnName);
            
            store.setValue((String)preference, ((Integer)order).intValue());
        }
        
        store.setValue(PreferenceConstants.RESULT_HISTORY_ALL_COLUMNS, !store.getBoolean(PreferenceConstants.RESULT_HISTORY_ALL_COLUMNS));
        store.setValue(PreferenceConstants.RESULT_HISTORY_SAVE_HISTORY, _autoSaveHistory.getSelection());
        store.setValue(PreferenceConstants.RESULT_HISTORY_CLEAN_HISTORY, _autoCleanHistory.getSelection());
        
        return super.performOk();
    } 
}

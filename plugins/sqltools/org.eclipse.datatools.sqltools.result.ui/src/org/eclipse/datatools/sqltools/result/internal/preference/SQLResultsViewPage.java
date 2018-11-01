/*******************************************************************************
 * Copyright (c) 2005, 2009 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.preference;

import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.common.ui.preferences.AbstractDBPreferenceFieldPage;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.ui.PreferenceConstants;
import org.eclipse.datatools.sqltools.result.ui.IHelpConstants;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;

/**
 * SQL Results View display options
 * 
 * @author Dafan Yang
 */
public class SQLResultsViewPage extends AbstractDBPreferenceFieldPage implements IContextProvider
{
    private Group            _displayWindow;
    private Button           _singleWindow;
    private Button           _multiWindows;
    private Group            _displayMode;
    private Button           _textMode;
    private Button           _gridMode;
    private Group            _resultSetOptions;
    private Button           _showHeadings;
    private Button           _showRowNumber;
    private Button           _showRowCountMessage;
    private Label            _maxRowCountLabel;
    private Text             _maxRowCount;
    private Label            _maxDisplayRowCountLabel;
    private Text             _maxDisplayRowCount;
    private Label            _nullDisplayStrLabel;
    private Text             _nullDisplayStr;

    private Group            _otherOptionsGrp;
    private Button           _splitMessages;
    private Label            _limitTabs;
    private Text             _limitTabsNum;

    private Label            _limitTables;
    private Text             _limitTablesNum;
    private Button 			 _showLabels;
    
    private IPreferenceStore _store;
    private ModifyListener   _modifyListener = new ModifyListener()
                                             {
                                                 public void modifyText(ModifyEvent event)
                                                 {
                                                     try
                                                     {
                                                         int max = Integer.parseInt(_maxRowCount.getText().trim());
                                                         int maxDisplay = Integer.parseInt(_maxDisplayRowCount
                                                                 .getText().trim());
                                                         int tabsNum = Integer.parseInt(_limitTabsNum.getText().trim());;
                                                         int tablesNum = Integer.parseInt(_limitTablesNum.getText().trim());
                                                         if (max < 0)
                                                         {
                                                             setMessage(
                                                                     Messages.SQLResultsViewPage_resultsetoptions_lessthanzero, //$NON-NLS-1$
                                                                     DialogPage.ERROR);
                                                             setValid(false);
                                                             updateApplyButton();
                                                         }
                                                         else if (maxDisplay < 0)
                                                         {
                                                             setMessage(
                                                                     Messages.SQLResultsViewPage_resultsetoptions_lessthanzero, //$NON-NLS-1$
                                                                     DialogPage.ERROR);
                                                             setValid(false);
                                                             updateApplyButton();
                                                         }
                                                         else if (tabsNum < 0)
                                                         {
                                                             setMessage(
                                                                     Messages.SQLResultsViewPage_resultsetoptions_lessthanzero, //$NON-NLS-1$
                                                                     DialogPage.ERROR);
                                                             setValid(false);
                                                             updateApplyButton();
                                                         }
                                                         else if(tablesNum<0)
                                                         {
                                                             setMessage(
                                                                     Messages.SQLResultsViewPage_resultsetoptions_lessthanzero, //$NON-NLS-1$
                                                                     DialogPage.ERROR);
                                                             setValid(false);
                                                             updateApplyButton();
                                                         }
                                                         else
                                                         {
                                                             if(maxDisplay > 500)
                                                             {
                                                                 setMessage(Messages.SQLResultsViewPage_too_many_rows, DialogPage.WARNING); 
                                                                 setValid(true);
                                                                 updateApplyButton();
                                                             }
                                                             else if(tabsNum > 100)
                                                             {
                                                                 setMessage(Messages.SQLResultsViewPage_too_many_tabs, DialogPage.WARNING); 
                                                                 setValid(true);
                                                                 updateApplyButton();
                                                             }
                                                             else if(tablesNum > 100)
                                                             {
                                                                 setMessage(Messages.SQLResultsViewPage_too_many_tables, DialogPage.WARNING);
                                                                 setValid(true);
                                                                 updateApplyButton();
                                                             }
                                                             else if (maxDisplay > max && max != 0)
                                                             {
                                                                 setMessage(
                                                                         Messages.SQLResultsViewPage_resultsetoptions_displaybiggerthanmax, //$NON-NLS-1$
                                                                         DialogPage.WARNING);
                                                                 setValid(true);
                                                                 updateApplyButton();
                                                             }
                                                             else
                                                             {
                                                                 setMessage(null);
                                                                 setValid(true);
                                                                 updateApplyButton();
                                                             }
                                                         }
                                                     }
                                                     catch (Exception e)
                                                     {
                                                         setMessage(
                                                                 Messages.SQLResultsViewPage_resultsetoptions_invalidnumberformat, //$NON-NLS-1$
                                                                 DialogPage.ERROR);
                                                         setValid(false);
                                                         updateApplyButton();
                                                     }

                                                 }
                                             };

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
                                             
    public void init(IWorkbench workbench)
    {
        setPreferenceStore(ResultsViewUIPlugin.getDefault().getPreferenceStore());
        _store = getPreferenceStore();
    }

    protected Control createContents(Composite parent)
    {
        getShell().setData(HelpUtil.CONTEXT_PROVIDER_KEY, this);
        HelpUtil.setHelp(parent, HelpUtil.getContextId(IHelpConstants.PREFERENCE_PAGE_SQL_RESULTS_VIEW, ResultsViewUIPlugin.getDefault().getBundle().getSymbolicName()));
        
        Composite comp = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        comp.setLayout(layout);

        Composite topComp = new Composite(comp, SWT.NONE);
        topComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        FillLayout filllayout = new FillLayout();
        filllayout.spacing = 7;
        topComp.setLayout(filllayout);

        _displayWindow = new Group(topComp, SWT.NONE);
        _displayWindow.setText(Messages.SQLResultsViewPage_displaywindow); 
        layout = new GridLayout();
        _displayWindow.setLayout(layout);

        _singleWindow = new Button(_displayWindow, SWT.RADIO);
        _singleWindow.setText(Messages.SQLResultsViewPage_displaywindow_singlewindow); 
        _singleWindow.setToolTipText(Messages.SQLResultsViewPage_singlewindow_tooltip); 
        _singleWindow.setLayoutData(new GridData());
        _singleWindow.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                if (_singleWindow.getSelection())
                {
                    _showRowCountMessage.setEnabled(true);
                    _splitMessages.setEnabled(false);
                    _limitTabs.setEnabled(false);
                    _limitTabsNum.setEnabled(false);
                    if (_gridMode.getSelection())
                    {
                        _limitTables.setEnabled(true);
                        _limitTablesNum.setEnabled(true);
                    }
                    else
                    {
                        _limitTables.setEnabled(false);
                        _limitTablesNum.setEnabled(false);
                    }
                }
                else
                {
                    _showRowCountMessage.setEnabled(false);
                    _splitMessages.setEnabled(true);
                    _limitTabs.setEnabled(true);
                    _limitTabsNum.setEnabled(true);
                }
            }
        });

        _multiWindows = new Button(_displayWindow, SWT.RADIO);
        _multiWindows.setText(Messages.SQLResultsViewPage_displaywindow_multiplewindows); 
        _multiWindows.setToolTipText(Messages.SQLResultsViewPage_multipletabs_tooltip); 
        _multiWindows.setLayoutData(new GridData());
        _multiWindows.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                if (_multiWindows.getSelection())
                {
                    _showRowCountMessage.setEnabled(false);
                    _splitMessages.setEnabled(true);
                    _limitTabs.setEnabled(true);
                    _limitTabsNum.setEnabled(true);
                    _limitTables.setEnabled(false);
                    _limitTablesNum.setEnabled(false);
                }
                else
                {
                    _showRowCountMessage.setEnabled(true);
                    _splitMessages.setEnabled(false);
                    _limitTabs.setEnabled(false);
                    _limitTabsNum.setEnabled(false);
                    _limitTables.setEnabled(true);
                    _limitTablesNum.setEnabled(true);
                }
            }
        });
        _displayMode = new Group(topComp, SWT.NONE);
        _displayMode.setText(Messages.SQLResultsViewPage_displaymode); 
        layout = new GridLayout();
        _displayMode.setLayout(layout);

        _textMode = new Button(_displayMode, SWT.RADIO);
        _textMode.setText(Messages.SQLResultsViewPage_displaymode_text); 
        _textMode.setToolTipText(Messages.SQLResultsViewPage_textmode_tooltip); 
        _textMode.setLayoutData(new GridData());
        _textMode.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent event)
            {
                if (_textMode.getSelection())
                {
                    _showHeadings.setEnabled(true);
                    _showRowNumber.setEnabled(false);
                    _singleWindow.notifyListeners(SWT.Selection, new Event());
                }
                else
                {
                    _showHeadings.setEnabled(false);
                    _showRowNumber.setEnabled(true);
                }
            }
        });
        _gridMode = new Button(_displayMode, SWT.RADIO);
        _gridMode.setText(Messages.SQLResultsViewPage_displaymode_grid); 
        _gridMode.setToolTipText(Messages.SQLResultsViewPage_girdmode_tooltip); 
        _gridMode.setLayoutData(new GridData());
        _gridMode.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent event)
            {
                if (_gridMode.getSelection())
                {
                    _showHeadings.setEnabled(false);
                    _showRowNumber.setEnabled(true);
                    _singleWindow.notifyListeners(SWT.Selection, new Event());
                }
                else
                {
                    _showHeadings.setEnabled(true);
                    _showRowNumber.setEnabled(false);
                }
            }
        });

        Composite midComp = new Composite(comp, SWT.NONE);
        layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        midComp.setLayout(layout);
        midComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        _resultSetOptions = new Group(midComp, SWT.NONE);
        _resultSetOptions.setLayoutData(new GridData(GridData.FILL_BOTH));
        _resultSetOptions.setText(Messages.SQLResultsViewPage_resultsetoptions); 
        _resultSetOptions.setLayout(new GridLayout());

        Composite buttonComp = new Composite(_resultSetOptions, SWT.NONE);
        layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        buttonComp.setLayout(layout);
        buttonComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        _showHeadings = new Button(buttonComp, SWT.CHECK);
        _showHeadings.setLayoutData(new GridData());
        _showHeadings.setText(Messages.SQLResultsViewPage_resultsetoptions_showheadings); 
        
        //This part of code is added to display the checkbox for displaying Column Label as Column Heading.
        _showLabels = new Button(buttonComp, SWT.CHECK);
        _showLabels.setLayoutData(new GridData());
        _showLabels.setText(Messages.SQLResultsViewPage_show_label);
        _showLabels.setToolTipText(Messages.SQLResultsViewPage_show_label_tooltip);   

        _showRowNumber = new Button(buttonComp, SWT.CHECK);
        _showRowNumber.setLayoutData(new GridData());
        _showRowNumber.setText(Messages.SQLResultsViewPage_resultsetoptions_showrownumber); 

        _showRowCountMessage = new Button(buttonComp, SWT.CHECK);
        _showRowCountMessage.setLayoutData(new GridData());
        _showRowCountMessage.setText(Messages.SQLResultsViewPage_resultsetoptions_showrowcount); 

        Composite textComp = new Composite(_resultSetOptions, SWT.NONE);
        layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.numColumns = 2;
        textComp.setLayout(layout);
        textComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        _maxRowCountLabel = new Label(textComp, SWT.NONE);
        _maxRowCountLabel.setText(Messages.SQLResultsViewPage_resultsetoptions_maxrowcount); 
        _maxRowCountLabel.setToolTipText(Messages.SQLResultsViewPage_resultsetoptions_maxrowcount_tooltip); 
        _maxRowCountLabel.setLayoutData(new GridData());

        _maxRowCount = new Text(textComp, SWT.SINGLE | SWT.BORDER);
        _maxRowCount.setToolTipText(Messages.SQLResultsViewPage_resultsetoptions_maxrowcount_tooltip); 
        _maxRowCount.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        _maxDisplayRowCountLabel = new Label(textComp, SWT.NONE);
        _maxDisplayRowCountLabel.setText(Messages.SQLResultsViewPage_resultsetoptions_maxdisplayrowcount); //$NON-NLS-1$
        _maxDisplayRowCountLabel.setToolTipText(Messages.SQLResultsViewPage_maxdisplayrows_tooltip); 
        _maxDisplayRowCountLabel.setLayoutData(new GridData());

        _maxDisplayRowCount = new Text(textComp, SWT.SINGLE | SWT.BORDER);
        _maxDisplayRowCount.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        _nullDisplayStrLabel = new Label(textComp, SWT.NONE);
        _nullDisplayStrLabel.setText(Messages.SQLResultsViewPage_resultsetoptions_nulldisplaystr); 
        _nullDisplayStrLabel.setLayoutData(new GridData());

        _nullDisplayStr = new Text(textComp, SWT.SINGLE | SWT.BORDER);
        // ten characters are allowed
        _nullDisplayStr.setTextLimit(10);
        _nullDisplayStr.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        Composite bottomComp = new Composite(comp, SWT.NONE);
        layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        bottomComp.setLayout(layout);
        bottomComp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        _otherOptionsGrp = new Group(bottomComp, SWT.NONE);
        _otherOptionsGrp.setText(Messages.SQLResultsViewPage_other_option); 
        _otherOptionsGrp.setLayoutData(new GridData(GridData.FILL_BOTH));
        layout = new GridLayout();
        layout.numColumns = 2;
        _otherOptionsGrp.setLayout(layout);
        
        _splitMessages = new Button(_otherOptionsGrp, SWT.CHECK);
        GridData gd = new GridData();
        gd.horizontalSpan = 2;
        _splitMessages.setLayoutData(gd);
        _splitMessages.setText(Messages.SQLResultsViewPage_split_message); 
        _splitMessages.setToolTipText(Messages.SQLResultsViewPage_splitmessages_tooltip); 
        
        _limitTabs = new Label(_otherOptionsGrp, SWT.NONE);
        _limitTabs.setText(Messages.SQLResultsViewPage_limit_tabs); 
        _limitTabs.setToolTipText(Messages.SQLResultsViewPage_limit_tabs_tooltip); 
        
        _limitTabsNum = new Text(_otherOptionsGrp, SWT.BORDER);
        _limitTabsNum.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));      
        
        _limitTables = new Label(_otherOptionsGrp, SWT.NONE);
        _limitTables.setText(Messages.SQLResultsViewPage_limit_tables_number); 
        _limitTables.setToolTipText(Messages.SQLResultsViewPage_limit_tables_tooltip); 
        
        _limitTablesNum = new Text(_otherOptionsGrp, SWT.BORDER);
        _limitTablesNum.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        
        initilizeValues();
        
        _maxRowCount.addModifyListener(_modifyListener);
        _maxDisplayRowCount.addModifyListener(_modifyListener);
        _limitTabsNum.addModifyListener(_modifyListener);
        _limitTablesNum.addModifyListener(_modifyListener);
        
        _limitTablesNum.notifyListeners(SWT.Modify, new Event());
        _singleWindow.notifyListeners(SWT.Selection, new Event());
        _multiWindows.notifyListeners(SWT.Selection, new Event());
        
        // composite for vendor specific tabs
        Composite tabs = new Composite(comp, SWT.NONE);
        tabs.setLayoutData(new GridData(GridData.FILL_BOTH));
        tabs.setLayout(layout);
        super.createContents(tabs);
        
        return comp;
    }

    protected void performDefaults()
    {
        if (_store.getDefaultInt(PreferenceConstants.SQL_RESULTS_VIEW_DISPLAY_WINDOW) == 1)
        {
            _singleWindow.setSelection(true);
            _multiWindows.setSelection(false);
            
            _showRowCountMessage.setEnabled(true);
            _splitMessages.setEnabled(false);
            _limitTabsNum.setEnabled(false);
        }
        else
        {
            _multiWindows.setSelection(true);
            _singleWindow.setSelection(false);

            _showRowCountMessage.setEnabled(false);
            _splitMessages.setEnabled(true);
            _limitTabsNum.setEnabled(true);
        }

        if (_store.getDefaultInt(PreferenceConstants.SQL_RESULTS_VIEW_DISPLAY_MODE) == 1)
        {
            _textMode.setSelection(true);
            _gridMode.setSelection(false);

            // trigger the listener
            _gridMode.notifyListeners(SWT.Selection, new Event());
        }
        else
        {
            _textMode.setSelection(false);
            _gridMode.setSelection(true);

            // trigger the listener
            _gridMode.notifyListeners(SWT.Selection, new Event());
        }

        _showHeadings.setSelection(_store.getDefaultBoolean(PreferenceConstants.SQL_RESULTS_VIEW_SHOW_HEADING));
        _showRowNumber.setSelection(_store.getDefaultBoolean(PreferenceConstants.SQL_RESULTS_VIEW_SHOW_ROW_NUMBER));
        _showRowCountMessage.setSelection(_store
                .getDefaultBoolean(PreferenceConstants.SQL_RESULTS_VIEW_SHOW_ROW_COUNT_MSG));
        _maxRowCount
                .setText(Integer.toString(_store.getDefaultInt(PreferenceConstants.SQL_RESULTS_VIEW_MAX_ROW_COUNT)));
        _maxDisplayRowCount.setText(Integer.toString(_store
                .getDefaultInt(PreferenceConstants.SQL_RESULTS_VIEW_MAX_DISPLAY_ROW_COUNT)));
        _nullDisplayStr.setText(_store.getDefaultString(PreferenceConstants.SQL_RESULTS_VIEW_NULL_STRING));
        _splitMessages.setSelection(_store.getDefaultBoolean(PreferenceConstants.SQL_RESULTS_VIEW_SPLIT_MESSAGES));
        _limitTabsNum.setText(Integer.toString(_store.getDefaultInt(PreferenceConstants.SQL_RESULTS_VIEW_TABS_NUMBER)));
        _limitTablesNum.setText(Integer.toString(_store
                .getDefaultInt(PreferenceConstants.SQL_RESULTS_VIEW_TABLES_LIMITATION)));
        _showLabels.setSelection(_store.getDefaultBoolean(PreferenceConstants.SQL_RESULT_VIEW_SHOW_LABELS));
        super.performDefaults();
    }

    private void initilizeValues()
    {
        if (_store.getInt(PreferenceConstants.SQL_RESULTS_VIEW_DISPLAY_WINDOW) == 1)
        {
            _singleWindow.setSelection(true);
            _multiWindows.setSelection(false);

            _showRowCountMessage.setEnabled(true);
            _splitMessages.setEnabled(false);
            _limitTabsNum.setEnabled(false);
        }
        else
        {
            _multiWindows.setSelection(true);
            _singleWindow.setSelection(false);

            _showRowCountMessage.setEnabled(false);
            _splitMessages.setEnabled(true);
            _limitTabsNum.setEnabled(true);
        }

        if (_store.getInt(PreferenceConstants.SQL_RESULTS_VIEW_DISPLAY_MODE) == 1)
        {
            _textMode.setSelection(true);
            _gridMode.setSelection(false);

            // trigger the listener
            _gridMode.notifyListeners(SWT.Selection, new Event());
        }
        else
        {
            _textMode.setSelection(false);
            _gridMode.setSelection(true);

            // trigger the listener
            _gridMode.notifyListeners(SWT.Selection, new Event());
        }

        _showHeadings.setSelection(_store.getBoolean(PreferenceConstants.SQL_RESULTS_VIEW_SHOW_HEADING));
        _showRowNumber.setSelection(_store.getBoolean(PreferenceConstants.SQL_RESULTS_VIEW_SHOW_ROW_NUMBER));
        _showRowCountMessage.setSelection(_store.getBoolean(PreferenceConstants.SQL_RESULTS_VIEW_SHOW_ROW_COUNT_MSG));
        _maxRowCount.setText(Integer.toString(_store.getInt(PreferenceConstants.SQL_RESULTS_VIEW_MAX_ROW_COUNT)));
        _maxDisplayRowCount.setText(Integer.toString(_store
                .getInt(PreferenceConstants.SQL_RESULTS_VIEW_MAX_DISPLAY_ROW_COUNT)));
        _nullDisplayStr.setText(_store.getString(PreferenceConstants.SQL_RESULTS_VIEW_NULL_STRING));
        _splitMessages.setSelection(_store.getBoolean(PreferenceConstants.SQL_RESULTS_VIEW_SPLIT_MESSAGES));
        _limitTabsNum.setText(Integer.toString(_store.getInt(PreferenceConstants.SQL_RESULTS_VIEW_TABS_NUMBER)));
        _limitTablesNum.setText(Integer.toString(_store.getInt(PreferenceConstants.SQL_RESULTS_VIEW_TABLES_LIMITATION)));
        
        _showLabels.setSelection(_store.getBoolean(PreferenceConstants.SQL_RESULT_VIEW_SHOW_LABELS));
    }

    public boolean performOk()
    {
        if (_singleWindow.getSelection())
        {
            _store.setValue(PreferenceConstants.SQL_RESULTS_VIEW_DISPLAY_WINDOW, 1);
        }
        else
        {
            _store.setValue(PreferenceConstants.SQL_RESULTS_VIEW_DISPLAY_WINDOW, 2);
        }

        if (_textMode.getSelection())
        {
            _store.setValue(PreferenceConstants.SQL_RESULTS_VIEW_DISPLAY_MODE, 1);
        }
        else
        {
            _store.setValue(PreferenceConstants.SQL_RESULTS_VIEW_DISPLAY_MODE, 2);
        }

        _store.setValue(PreferenceConstants.SQL_RESULTS_VIEW_SHOW_HEADING, _showHeadings.getSelection());
        _store.setValue(PreferenceConstants.SQL_RESULTS_VIEW_SHOW_ROW_COUNT_MSG, _showRowCountMessage.getSelection());
        _store.setValue(PreferenceConstants.SQL_RESULTS_VIEW_SHOW_ROW_NUMBER, _showRowNumber.getSelection());
        _store.setValue(PreferenceConstants.SQL_RESULTS_VIEW_MAX_ROW_COUNT, Integer.parseInt(_maxRowCount.getText().trim()));
        _store.setValue(PreferenceConstants.SQL_RESULTS_VIEW_MAX_DISPLAY_ROW_COUNT, Integer.parseInt(_maxDisplayRowCount.getText().trim()));
        _store.setValue(PreferenceConstants.SQL_RESULTS_VIEW_NULL_STRING, _nullDisplayStr.getText());
        _store.setValue(PreferenceConstants.SQL_RESULTS_VIEW_SPLIT_MESSAGES, _splitMessages.getSelection());
        _store.setValue(PreferenceConstants.SQL_RESULTS_VIEW_TABS_NUMBER, _limitTabsNum.getText().trim());
        _store.setValue(PreferenceConstants.SQL_RESULTS_VIEW_TABLES_LIMITATION, _limitTablesNum.getText().trim());
        //The checkbox for Column Heading will set the value true/false at the time of pressing "OK" button. 
        _store.setValue(PreferenceConstants.SQL_RESULT_VIEW_SHOW_LABELS,  _showLabels.getSelection());
        return super.performOk();
    }

    public String getPreferencePageId()
    {
        return PreferenceConstants.PAGE_RESULT;
    }
}

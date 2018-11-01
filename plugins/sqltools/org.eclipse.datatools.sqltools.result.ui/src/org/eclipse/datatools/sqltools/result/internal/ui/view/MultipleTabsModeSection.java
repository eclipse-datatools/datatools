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
package org.eclipse.datatools.sqltools.result.internal.ui.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.sqltools.result.IResultSetObject;
import org.eclipse.datatools.sqltools.result.Parameter;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.ui.PreferenceConstants;
import org.eclipse.datatools.sqltools.result.internal.ui.utils.PreferenceUtil;
import org.eclipse.datatools.sqltools.result.internal.utils.ILogger;
import org.eclipse.datatools.sqltools.result.internal.utils.StatusTextProvider;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.model.ResultItem;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIAccessor;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.datatools.sqltools.result.ui.view.ResultsViewControl;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

/**
 * Parent class of <code>MultipleTabsTextSection</code> and <code>MultipleTabsGridSection</code>.
 * 
 * @author Dafan Yang
 *  
 */
public abstract class MultipleTabsModeSection extends ResultSection
{
    private static ILogger _log             = ResultsViewUIPlugin.getLogger(null);
    /* Number of static tabs, for example, status tab */
    protected int          _numberStaticTab = 1;
    /* Use multiple tabs to display the execution result */
    protected CTabFolder   _tabFolder;
    /* Dtatus item is always there */
    protected CTabItem     _statusItem;
    /* Message item may be there (depend on the SQL_RESULTS_VIEW_SPLIT_MESSAGES option) */
    protected CTabItem     _messageItem;
    /* Parameter item will be displayed only when there are parameters */
    protected CTabItem     _paramsItem;
    protected int          _messageIndex    = 1;
    protected int          _resultsetIndex  = 1;
    protected ResultItem   _preItem;
    protected CTabItem     _preTab;
    /*
     * If it is false, all messages will be displayed on one message tab; otherwise they will be display on multiple
     * message tabs
     */
    protected boolean      _splitMessages;

    protected boolean      _endTabCreated            = false;
    /* Tabs number limitation */
    protected int          _tabsNum;
    protected int          _currentTabsNum;
    protected boolean      _isParamShown       = false;
    protected int          _paramTabNumber;
    public MultipleTabsModeSection(Composite composite, IResultInstance instance, ResultsViewControl resultsViewControl)
    {
        super(instance, resultsViewControl);
        _parent = composite;
        _tabFolder = new CTabFolder(composite, SWT.NONE);
        _splitMessages = PreferenceUtil.getBoolean(_store, PreferenceConstants.SQL_RESULTS_VIEW_SPLIT_MESSAGES, resultsViewControl.getUsePreferences());
        _tabsNum = PreferenceUtil.getInt(_store, PreferenceConstants.SQL_RESULTS_VIEW_TABS_NUMBER, resultsViewControl.getUsePreferences());
        if(!_splitMessages)
        {
            _numberStaticTab = 2;
        }
        _currentTabsNum = _numberStaticTab;
        createInitialControl(_tabFolder);
    }

    public MultipleTabsModeSection(Composite composite, ResultsViewControl resultsViewControl)
    {
        this(composite, null, resultsViewControl);
    }

    /**
     * Some static tabs are created by default
     * 
     * @param ctf the tab folder
     */
    protected void createInitialControl(CTabFolder ctf)
    {
        _statusItem = new CTabItem(ctf, SWT.NONE);
        _statusItem.setControl(createStatusItem(ctf));
        _statusItem.setText(Messages.ResultHistorySection_status); 

        if (!_splitMessages)
        {
            _messageItem = new CTabItem(ctf, SWT.NONE);
            _messageItem.setControl(createMessageItem(ctf));
            _messageItem.setText(Messages.MultipleTabsModeSection_message); 
        }
        _tabFolder.setSelection(_statusItem);
    }

    public void showDetail(IResultInstance instance)
    {     
        super.showDetail(instance);
        if (instance == _resultInstance)
        {
            return;
        }
        // clear the status line
        if (_resultsViewControl.getView() != null){
        	_resultsViewControl.getView().clearStatusLine();
        }
        
        _resultInstance = instance;
        //set visible to false to prevent flash
        _tabFolder.setVisible(false);
        
        showStatusDetail(instance);
        showMessageDetail(instance);
        showParameterDetail(instance);
        showResultsDetail(instance);
        
        onInstanceFinished();
        _tabFolder.setVisible(true);
        
        // show the status tab by default
        _tabFolder.setSelection(0);
    }

    /**
     * Shows the status messages on the status tab item
     * 
     * @param instance the result instance
     */
    abstract protected void showStatusDetail(IResultInstance instance);

    /**
     * Shows the plain messages on the message tab item
     * 
     * @param instance the result instance
     */
    abstract protected void showMessageDetail(IResultInstance instance);

    /**
     * Shows the parameters on parameter tab (if there are parameters)
     * 
     * @param instance the result instance
     */
    abstract protected void showParameterDetail(IResultInstance instance);
    
    /**
     * Shows the execution result (messages , result sets), update count message will be ignored in this method, because
     * update count messages are already included on the status tab item.
     * 
     * @param instance the result instance
     */
    protected void showResultsDetail(IResultInstance instance)
    {
        CTabItem[] items = _tabFolder.getItems();
        for (int i = _numberStaticTab; i < items.length; i++)
        {
            if (items[i] != null)
            {
                if (items[i].getControl() != null)
                {
                    items[i].getControl().dispose();
                }
                items[i].dispose();
            }
        }

        if (instance != null)
        {
            int count = instance.getItemCount();
            for (int i = 0; i < count; i++)
            {
                createTabForResultItem(_tabFolder, instance.getItem(i));
            }
        }
    }

    /**
     * Creates a tab item for a result item, update count messages are ignored
     * 
     * @param tabFolder the tab folder
     * @param item the result item
     */
    private void createTabForResultItem(CTabFolder tabFolder, ResultItem item)
    {
        /**
         * Since this function is responsible for creating tabs when the user chicks the history result item or
         * resultInstanceUpdate event occurs, so in newResultItem method we append the status view, but for
         * showResultDetail function, we just return (update count messages and status messages are already included on
         * the status item).
         */
        if (item.getResultType() == ResultItem.UPDATE_COUNT || item.getResultType() == ResultItem.STATUS_TEXT)
        {
            return;
        }
        
        /**
         * If it is a plain message and if the SQL_RESULTS_VIEW_SPLIT_MESSAGES option is off, all plain messages are
         * displayed on a single message tab.
         */
        if(!_splitMessages && item.getResultType() == ResultItem.PLAIN_TEXT)
        {
            return;
        }

        //we try to show continues messages on one tab item
        if (_preItem != null && _preItem.getResultType() == ResultItem.PLAIN_TEXT
        && _preItem.getResultType() == item.getResultType())
        {
            Text text = null;
            StyledText styledText = null;
            
            // show this tab
            tabFolder.setSelection(_preTab);
            
            if (_preTab != null)
            {
                /**
                 * we are trying to get the text widget on the tab item. Make sure that there is one composite on the
                 * tab item, and there is one and only one text widget in this composite.
                 */
                try
                {
                    Composite tabControl = (Composite) _preTab.getControl();
                    if (tabControl.getChildren()[0] instanceof Text)
                    {
                        text = (Text) tabControl.getChildren()[0];
                    }
                    else if (tabControl.getChildren()[0] instanceof StyledText)
                    {
                        styledText = (StyledText) tabControl.getChildren()[0];
                    }
                }
                catch (Exception ex)
                {
                    _log.error("MultipleTabsModeSection_unexpectederror", ex); //$NON-NLS-1$
                    //this will not happen unless the code is changed
                }
            }
            if (text != null)
            {
                _preItem = item;
                text.append((String) item.getResultObject());
                return;
            }
            else if (styledText != null)
            {
                _preItem = item;
                StringBuffer orginalStr = new StringBuffer(styledText.getText());
                orginalStr.append((String) item.getResultObject());
                styledText.setText(orginalStr.toString());
                return;
            }
        }
        
        /**
         * If current tabs number exceeds the max number, stop displaying.
         */
        if(_currentTabsNum >= _tabsNum)
        {
            if (!_endTabCreated)
            {
                _endTabCreated = true;
                CTabItem tabitem = new CTabItem(tabFolder, SWT.NONE);
                tabitem.setText(Messages.MultipleTabsModeSection_warning);

                Composite composite = new Composite(tabFolder, SWT.NONE);

                GridLayout layout = new GridLayout();
                layout.marginHeight = 0;
                layout.marginWidth = 0;
                layout.numColumns = 1;
                composite.setLayout(layout);

                Text warningText = new Text(composite, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.READ_ONLY);
                warningText.setLayoutData(new GridData(GridData.FILL_BOTH));
                warningText
                    .setText(Messages.MultipleTabsModeSection_warning_info);
                tabitem.setControl(composite);
                tabFolder.setSelection(tabitem);
            }
            return;
        }

        CTabItem tabitem = new CTabItem(tabFolder, SWT.NONE);

        //show the current tab item
        tabFolder.setSelection(tabitem);
        
        //save the previous tab item and previous result item
        _preTab = tabitem;
        _preItem = item;
        Composite composite = new Composite(tabFolder, SWT.NONE);

        GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.numColumns = 1;
        composite.setLayout(layout);

        createViewerForItem(composite, item);
        tabitem.setControl(composite);
        switch (item.getResultType())
        {
            case ResultItem.PLAIN_TEXT:
                tabitem.setText(Messages.ResultSection_message + _messageIndex++); 
                break;
            case ResultItem.RESULT_SET:
                tabitem.setData(item);
                tabitem.setText(Messages.ResultSection_result + _resultsetIndex++); 
                break;
            case ResultItem.UPDATE_COUNT:
                //ignore
                break;
            default:
                break;
        }
        _currentTabsNum++;
    }

    public Composite getControl()
    {
        return _tabFolder;
    }

    public void onNewItemAppended(ResultItem item, int index)
    {
        if(_resultInstance == null || _displayedItems.contains(item))
        {
            return;
        }
        _displayedItems.add(item);
        
        _messageCache.add(item);
        
        super.onNewItemAppended(item, index);
        
    }
    
    public void onParametersShown(List params)
    {
        if(_resultInstance == null || params == null)
        {
            return;
        }
        Iterator iter = params.iterator();
        ArrayList newList = new ArrayList();
        while(iter.hasNext())
        {
            // only Parameter type is accepted
            Object obj = iter.next();
            if(obj != null && obj instanceof Parameter)
            {
                newList.add(obj);
            }
        }
        appendAndShowParameters(newList);
    }

    public void onInstanceReseted()
    {
        if(_resultInstance == null)
        {
            return;
        }
        //prevent from flashing
        _tabFolder.setVisible(false);
        _messageIndex = 1;
        _resultsetIndex = 1;
        CTabItem[] items = _tabFolder.getItems();
        for (int i = _numberStaticTab; i < items.length; i++)
        {
            items[i].dispose();
        }
        if(_isParamShown)
        {
            _numberStaticTab--;
            _isParamShown = false;
            items[_paramTabNumber].dispose();
        }
        _tabFolder.setVisible(true);
    }
    
    public void onInstanceFinished()
    {
        // refresh the result history to generate the status message
        _resultsViewControl.refreshResults();
//        if(_isResultItemHidden)
//        {
//            IActionBars actionBars = _view.getViewSite().getActionBars();
//            IStatusLineManager statusMgr = actionBars.getStatusLineManager();
//            statusMgr.setMessage(Images.DESC_RESULT_VIEW_WARN.createImage(), Messages.MultipleTabsModeSection_tabs_hidden); 
//        }
//        else
//        {
//            // refresh the result history to generate the status message
//            _view.refreshResults();
//        }
    }

    /**
     * Creates an viewer for given result item on the tab item (composite)
     * 
     * @param composite the parent composite
     * @param item the result item
     */
    abstract protected void createViewerForItem(Composite composite, ResultItem item);

    /**
     * Creates the status tab
     * 
     * @param ctf the tab folder
     * @return the control on status item
     */
    abstract protected Control createStatusItem(CTabFolder ctf);
    
    /**
     * Creates the message tab
     * 
     * @param ctf the tab folder
     * @return the control on message item
     */
    abstract protected Control createMessageItem(CTabFolder ctf);

    /**
     * Aappends the status message to the status tab
     * 
     * @param text the message
     */
    abstract protected void appendStatusView(String text);
    
    /**
     * Appends the plain message to the message tab
     * 
     * @param text the message
     */
    abstract protected void appendMessageView(String text);
    
    /**
     * Appends a parameter tab and show parameters in it
     * 
     * @param params the <code>Parameter</code> instances list
     */
    abstract protected void appendAndShowParameters(List params);

    public void showTab(int tabType)
    {
        switch (tabType)
        {
            case ResultsViewUIAccessor.STATUS_TAB:
                if(_statusItem != null)
                {
                    _tabFolder.setSelection(_statusItem);
                }
                break;
            case ResultsViewUIAccessor.PARAM_TAB:
                if(_paramsItem != null)
                {
                    _tabFolder.setSelection(_paramsItem);
                }
                break;
            case ResultsViewUIAccessor.MESSAGE_TAB:
                if (!_splitMessages)
                {
                    if (_messageItem != null)
                    {
                        _tabFolder.setSelection(_messageItem);
                    }
                }
                else
                {
                    CTabItem firstMessageItem = findTabItem(Messages.ResultSection_message + "1"); //$NON-NLS-1$
                    if(firstMessageItem != null)
                    {
                        _tabFolder.setSelection(firstMessageItem);
                    }
                }
                break;
            case ResultsViewUIAccessor.RESULT_TAB:
                CTabItem fisrtResultItem = findTabItem(Messages.ResultSection_result + "1"); //$NON-NLS-1$
                if(fisrtResultItem != null)
                {
                    _tabFolder.setSelection(fisrtResultItem);
                }
                break;
            default:
                break;
        }
    }

    public void showResultSet(IResultSetObject result)
    {
        if (result == null)
        {
            return;
        }
        if (_resultInstance == null)
        {
            return;
        }
        int resultSerialNumber = 0;
        boolean found = false;
        for (int i = 0; i < _resultInstance.getItemCount(); i++)
        {
            if (_resultInstance.getItem(i).getResultType() == ResultItem.RESULT_SET)
            {
                resultSerialNumber++;
            }
            if (_resultInstance.getItem(i) == result)
            {
                found = true;
                break;
            }
        }
        if (resultSerialNumber == 0 || !found)
        {
            return;
        }
        CTabItem resultItem = findTabItem(Messages.ResultSection_result + resultSerialNumber);
        if (resultItem != null)
        {
            _tabFolder.setSelection(resultItem);
        }
    }

    
    
    public void showTab(int tabType, int tabNumber)
    {
        if (_resultInstance == null)
        {
            return;
        }
        String namePrefix = ""; //$NON-NLS-1$
        switch (tabType)
        {
            case ResultsViewUIAccessor.RESULT_TAB:
                namePrefix = Messages.ResultSection_result;
                break;
            case ResultsViewUIAccessor.MESSAGE_TAB:
                namePrefix = Messages.ResultSection_message;
                break;
            default:
                break;
        }
        CTabItem item = findTabItem(namePrefix + tabNumber);
        if (item != null)
        {
            _tabFolder.setSelection(item);
        }
    }

    private CTabItem findTabItem(String tabName)
    {
        CTabItem[] items = _tabFolder.getItems();
        for (int i = 0; i < items.length; i++)
        {
            if (items[i].getText().equals(tabName))
            {
                return items[i];
            }
        }
        return null;
    }

    public int getRowCount()
    {
        CTabItem item = _tabFolder.getSelection();
        Object data = item.getData();
        if (data == null || !(data instanceof ResultItem))
        {
            return super.getRowCount();
        }
        ResultItem result = (ResultItem) data;
        if (result.getResultType() == ResultItem.RESULT_SET)
        {
            return ((IResultSetObject) result.getResultObject()).getRowCount();
        }
        return super.getRowCount();
    } 
    
	protected void outputToViewer(List outputList) 
	{
		List mergedList = super.getMergedOutputList(outputList);
		for (Iterator iterator = mergedList.iterator(); iterator.hasNext();) {
			ResultItem item = (ResultItem) iterator.next();
			
			if (item.getResultType() == ResultItem.UPDATE_COUNT)
	        {
	            appendStatusView(StatusTextProvider.getUpdateCountText(((Integer) item.getResultObject()).intValue()));
	            continue;
	        }
	        else if(item.getResultType() == ResultItem.STATUS_TEXT)
	        {
	            // show the status tab
	            _tabFolder.setSelection(_statusItem);
	            
	            appendStatusView((String) item.getResultObject());
	            continue;
	        }
	        else if(!_splitMessages && item.getResultType() == ResultItem.PLAIN_TEXT)
	        {
	            // show the message tab
	            _tabFolder.setSelection(_messageItem);
	            
	            appendMessageView((String)item.getResultObject());
	            continue;
	        }
	        createTabForResultItem(_tabFolder, item);
		}
	}
}

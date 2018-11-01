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
import java.util.Vector;

import org.eclipse.datatools.sqltools.result.IResultSetObject;
import org.eclipse.datatools.sqltools.result.internal.ui.PreferenceConstants;
import org.eclipse.datatools.sqltools.result.internal.ui.utils.PreferenceUtil;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.model.ResultItem;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIAccessor;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.datatools.sqltools.result.ui.view.ResultsViewControl;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

/**
 * <code>ResultSection</code> is an UI class, which is responsible for displaying the right part of the SQL Results
 * View. Four kinds of display modes are supported:
 * <ul>
 * <li>Grid mode in multiple windows(default)
 * <li>Grid mode in single window
 * <li>Text mode in multiple windows
 * <li>Text mode in single window
 * </ul>
 * <p>
 * So four classes are defined for each mode --- <code>MultipleTabsGridSection</code>,
 * <code>MultipleTabsTextSection</code>, <code>SingleWindowGridSection</code>, and
 * <code>SingleWindowTextSection</code>.
 * 
 * @author Dafan Yang
 */
abstract public class ResultSection
{
    /* The result instance displaying */
    protected IResultInstance  _resultInstance;
    protected IPreferenceStore _store;

    /* Preferences */
    protected boolean          _displayRowNumber;
    protected boolean          _showRowCountMsg;
    protected boolean          _showHeadings;
    protected String           _nullValue;

    /* Parent of this UI section */
    protected Composite        _parent;
    /* The results view */
    protected ResultsViewControl      _resultsViewControl;
    /* The cache to improve large mount of message output performance*/
    protected Vector 		_messageCache;
    /*
     * thread responsible for output appended resultset item to UI
     */
    protected OutputThread		_outputThread;
    /*
     * milliseconds delay for output appended messages
     */
    private static int OUTPUT_INTERVAL = 500;
    
    /* maximum waiting times for incoming append message*/
    private static int END_OUTPUT_THEAD_TIMES = 20;
    
    /*
     * In some senarios, some items may be displayed for two times: showDetail and onNewItemAppended will both display
     * one item (They are invoked in two threads). So we keep the displayed item in a list.
     */
    protected List             _displayedItems;
    
    public ResultSection(IResultInstance instance, ResultsViewControl resultsViewControl)
    {
        super();
        _resultsViewControl = resultsViewControl;
        _resultInstance = instance;
        _store = ResultsViewUIPlugin.getDefault().getPreferenceStore();
        _displayRowNumber = PreferenceUtil.getBoolean(_store, PreferenceConstants.SQL_RESULTS_VIEW_SHOW_ROW_NUMBER, _resultsViewControl.getUsePreferences());
        _nullValue = PreferenceUtil.getString(_store, PreferenceConstants.SQL_RESULTS_VIEW_NULL_STRING, _resultsViewControl.getUsePreferences());
        _showRowCountMsg = PreferenceUtil.getBoolean(_store, PreferenceConstants.SQL_RESULTS_VIEW_SHOW_ROW_COUNT_MSG, _resultsViewControl.getUsePreferences());
        _showHeadings = PreferenceUtil.getBoolean(_store, PreferenceConstants.SQL_RESULTS_VIEW_SHOW_HEADING, _resultsViewControl.getUsePreferences());
        _displayedItems = new ArrayList();
        _messageCache = new Vector();
    }

    /**
     * Returns the outer control of this UI section
     * 
     * @return the outer composite
     */
    abstract public Composite getControl();

    /**
     * Shows the selected instance (when user clicks the history results item).
     * 
     * @param instance the selected result instance
     */
    public void showDetail(IResultInstance instance)
    {
        if (instance == _resultInstance)
        {
            return;
        }
        int count = instance.getItemCount();
        for (int i = 0; i < count; i++)
        {
            _displayedItems.add(instance.getItem(i));
        }
    }

    /**
     * Shows the newly-appended result item (when resultInstanceAppended event occurs) for the current result instance
     * 
     * @param item the newly-appended result item
     * @param index the index of this item
     */
    public void onNewItemAppended(ResultItem item, int index)
    {
    	//after populate _messageCache, start output thread
    	if(_outputThread == null || !_outputThread.isAlive())
    	{
    		_outputThread = getOutputThread();
   			_outputThread.start();
    	}
    }

    /**
     * Shows the parameters for the current result instance, do nothing in single window display mode, because we need
     * to display the parameters at the end in this mode
     * 
     * @param params the parameters
     */
    abstract public void onParametersShown(List params);
    
    /**
     * Does some jobs when resultInstanceFinish event occurs for the current result instance, for example, when using
     * single window display mode, we need to display the parameters at the end
     * 
     */
    abstract public void onInstanceFinished();
    
    /**
     * Refreshes UI when resultInstanceReset event occurs for the current result instance
     *
     */
    abstract public void onInstanceReseted();
    
    /**
     * Sets focus to the given tab
     * @param tabType the type of the tab to be set focus
     * @see ResultsViewUIAccessor#STATUS_TAB
     * @see ResultsViewUIAccessor#PARAM_TAB
     * @see ResultsViewUIAccessor#MESSAGE_TAB
     * @see ResultsViewUIAccessor#RESULT_TAB
     */
    public void showTab(int tabType)
    {
        // do nothing
    }
    
    /**
     * Sets focus to the given result set
     * @param result the result set
     */
    public void showResultSet(IResultSetObject result)
    {
        // do nothing
    }
    
    /**
     * Sets focus to the given message/result tab with the given number
     * 
     * @param tabType the type of the tab to be set focus
     * @see ResultsViewUIAccessor#MESSAGE_TAB
     * @see ResultsViewUIAccessor#RESULT_TAB
     * @param tabNumber the number of the tab for the given type
     */
    public void showTab(int tabType, int tabNumber)
    {
        // do nothing
    }
    
    /**
     * Returns the row count of the current displaying result set.
     * 
     * @return the row count of the current displaying result set, returns -1 if the current tab is not a result set tab
     *         or current display mode is single tab display mode
     */
    public int getRowCount()
    {
        return -1;
    }
    
    /**
     * Output list of ResultItem to UI
     * @param outputList
     */
    abstract protected void outputToViewer(List outputList);
    
    /**
     * 
     * @author linsong
     *
     */
    public class OutputThread extends Thread
    {
    	private Display display;
    	private int counter;
    	
    	public OutputThread(Display display)
    	{
    		this.display = display;
    	}
    	
    	public void run() {
    		while(true)
    		{
    			try {
					sleep(OUTPUT_INTERVAL);
				} catch (InterruptedException e) {
					return;
				}
				
				final List outputList;
				synchronized (_messageCache) 
				{
					outputList = new ArrayList(_messageCache);
		    		_messageCache.clear();
				}
				
    			if(outputList.size() != 0)
    			{
    				counter = 0;
    				display.asyncExec(new Runnable() {
						public void run() {
							ResultSection.this.outputToViewer(outputList);
						}
					});
    			}
    			else 
    			{
    				counter++;
    			}
    			
    			//thread exit when after no message incoming for END_OUTPUT_THEAD_TIMES times 
    			if(counter >= END_OUTPUT_THEAD_TIMES)
    			{
    				return;
    			}
    		}
    	}
    }
    
    /**
     * Merge continuous plain text ResultItems into one item to improve output performance
     * @param outputList
     * @return
     */
    protected List getMergedOutputList(List outputList)
	{
		List result = new ArrayList();
		ResultItem preItem = null;
		for (Iterator iterator = outputList.iterator(); iterator.hasNext();)
		{
			if(result.size() > 0)
			{
				preItem = (ResultItem)result.get(result.size() - 1);
			}
			ResultItem item = (ResultItem) iterator.next();
			if(isTextResultItem(item) && isTextResultItem(preItem))
			{
					preItem = mergeTextItems(preItem, item);
					result.set(result.size() - 1, preItem);
			}
			else
			{
				result.add(item);
			}
		}
		return result;
	}
	
	private ResultItem mergeTextItems(ResultItem preItem, ResultItem item) 
	{
		String message = (String)preItem.getResultObject();
		message = message + item.getResultObject();
		ResultItem result = new ResultItem(message, true);
		return result;
	}

	protected boolean isTextResultItem(ResultItem item)
	{
		if(item == null)
		{
			return false;
		}
		
		int type = item.getResultType();
		return type == ResultItem.PLAIN_TEXT;
	}
	
	private OutputThread getOutputThread()
	{
		return new OutputThread(_resultsViewControl.getControl().getDisplay());
	}
}

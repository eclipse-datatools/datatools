/*******************************************************************************
 * Copyright (c) 2007 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.ui.view;

import java.util.List;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.IProfileListener1;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.result.ResultsConstants;
import org.eclipse.datatools.sqltools.result.core.IResultManagerListener;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.ui.PreferenceConstants;
import org.eclipse.datatools.sqltools.result.internal.ui.actions.RemoveAllVisibleFinishedResultAction;
import org.eclipse.datatools.sqltools.result.internal.ui.actions.RemoveResultAction;
import org.eclipse.datatools.sqltools.result.internal.ui.actions.SaveResultInstanceAction;
import org.eclipse.datatools.sqltools.result.internal.ui.actions.TerminateInstanceAction;
import org.eclipse.datatools.sqltools.result.internal.ui.filters.OpenFilterDialogAction;
import org.eclipse.datatools.sqltools.result.internal.ui.utils.Images;
import org.eclipse.datatools.sqltools.result.internal.ui.utils.PreferenceUtil;
import org.eclipse.datatools.sqltools.result.internal.ui.view.ResultHistorySection;
import org.eclipse.datatools.sqltools.result.internal.ui.view.ResultSection;
import org.eclipse.datatools.sqltools.result.internal.ui.view.ResultSectionFactory;
import org.eclipse.datatools.sqltools.result.internal.ui.view.ResultsView;
import org.eclipse.datatools.sqltools.result.internal.ui.view.SingleTabDisplayAction;
import org.eclipse.datatools.sqltools.result.internal.ui.view.TextModeDisplayAction;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.model.ResultItem;
import org.eclipse.datatools.sqltools.result.ui.IHelpConstants;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.help.IContext;
import org.eclipse.help.IContextProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PreferencesUtil;

/**
 * This is the control for the view showing the result.
 * 
 * @author Jeremy Lindop
 */
public class ResultsViewControl  implements IPropertyChangeListener, IProfileListener1, IContextProvider, IResultManagerListener {
	
	private ResultsView							_resultsView;
	
    private SashForm                             _sashform;
    /* Use to display history results */
    private Composite                            _leftArea;
    /* Result detail area */
    private Composite                            _detailArea;
    private ResultSection                        _resultSection;

    /**
     * Current selected result instance. The detail part will display information of this result instance.
     */
    private IResultInstance                      _currentInstance  = null;
    private Action                               _preferenceAction = null;

    private IMemento                             _memento;
    private IPreferenceStore                     _store;
    private Display                              _display;
    private ResultHistorySection                 _resultHistory;

    private RemoveResultAction                   _removeResultAction;
    private RemoveAllVisibleFinishedResultAction _removeAllVisibleFinishedResultAction;
    private TerminateInstanceAction              _terminateAction;
    private SaveResultInstanceAction             _saveResultInstanceAction;
    private VerticalLayoutAction                 _vLayoutAction;
    private HorizontalLayoutAction               _hLayoutAction;
    private OpenFilterDialogAction               _openFilterDialogAction;
    
    private boolean								_usePreferences = true;
    
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

    SingleTabDisplayAction     _singleTabDisplayAction   = null;
    TextModeDisplayAction      _textModeDisplayAction    = null;
    
    public ResultsViewControl()
    {
        this(null);
    }
    
    public ResultsViewControl(ResultsView resultsView)
    {
        _resultsView = resultsView;
    }

    public void init() throws PartInitException
    {
        init(null, null);
    }
    
    public void init(IViewSite site, IMemento memento) throws PartInitException
    {
        this._memento = memento;

        _store = ResultsViewUIPlugin.getDefault().getPreferenceStore();
        _store.addPropertyChangeListener(this);
    }

    public void saveState(IMemento memento)
    {
        _resultHistory.saveState(memento);
    }
    
    public Composite getControl(){
    	return _sashform;
    }

    public void createPartControl(Composite parent)
    {
        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, HelpUtil.getContextId(IHelpConstants.VIEW_SQL_RESULT, ResultsViewUIPlugin.getDefault().getBundle().getSymbolicName()));
        
        _sashform = new SashForm(parent, PreferenceUtil.getBoolean(
        		ResultsViewUIPlugin.getDefault().getPreferenceStore(),
                PreferenceConstants.VERTICAL_LAYOUT_RESULTS_VIEW,
                getUsePreferences()) ? SWT.VERTICAL : SWT.HORIZONTAL | SWT.SMOOTH);
        _display = _sashform.getDisplay();

        _leftArea = new Composite(_sashform, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.numColumns = 1;
        _leftArea.setLayout(layout);
        _leftArea.layout(true);
        
        _detailArea = new Composite(_sashform, SWT.NONE);
        _detailArea.setLayout(new FillLayout());
        _resultSection = ResultSectionFactory.createResultSection(_detailArea, this);

        _resultHistory = new ResultHistorySection(_leftArea, _detailArea, this);
        _resultHistory.applyState(_memento);
        
        createActions();

        ProfileManager pMgr = ProfileManager.getInstance();
        pMgr.addProfileListener(this);
        
    }

    public void createActions()
    {
        _textModeDisplayAction = new TextModeDisplayAction();
        _textModeDisplayAction.update();
        _singleTabDisplayAction = new SingleTabDisplayAction();
        _singleTabDisplayAction.update();
        
        
        _terminateAction = new TerminateInstanceAction(_resultHistory.getResultTable());
        _terminateAction.setActionDefinitionId("org.eclipse.datatools.sqltools.result.terminate");
        
        _saveResultInstanceAction = new SaveResultInstanceAction(_resultHistory.getResultTable().getTree().getShell(),
                _resultHistory.getResultTable());
        
        _removeResultAction = new RemoveResultAction(_resultHistory.getResultTable());
        _removeResultAction.setActionDefinitionId("org.eclipse.datatools.sqltools.result.removeInstance");

        _removeAllVisibleFinishedResultAction = new RemoveAllVisibleFinishedResultAction(_resultHistory.getResultTable(), (IResultInstance[])null);
        _removeAllVisibleFinishedResultAction.setActionDefinitionId("org.eclipse.datatools.sqltools.result.removeAllInstances");

        _preferenceAction = new Action(Messages.ResultsView_preference) 
        {
            public void run()
            {
                String[] preferencePages =
                {
                    PreferenceConstants.PAGE_RESULT, PreferenceConstants.PAGE_HISTORY, PreferenceConstants.PAGE_EXPORT,
                    PreferenceConstants.PAGE_RESULTSETVIEWER
                };
                PreferencesUtil.createPreferenceDialogOn(null, preferencePages[0], preferencePages, null).open();
            }
        };

        _vLayoutAction = new VerticalLayoutAction(_sashform);
        _hLayoutAction = new HorizontalLayoutAction(_sashform);
        _openFilterDialogAction = new OpenFilterDialogAction(_resultHistory.getResultTable().getTree().getShell());
        
    }

    public void setFocus()
    {
        if (_sashform != null && !_sashform.isDisposed())
        {
            _sashform.setFocus();
        }
    }

    public void dispose()
    {
       	_terminateAction.dispose();
       	_removeResultAction.dispose();

        _store.removePropertyChangeListener(this);
        ProfileManager pMgr = ProfileManager.getInstance();
        pMgr.removeProfileListener(this);
        _resultHistory.dispose();
    }

    public void propertyChange(PropertyChangeEvent event)
    {
    	/**
    	 * Respond to preference setting change only if the usePreferences flag is set to true.
    	 */
    	if (getUsePreferences()){
	        /**
	         * Re-display the selected instance when any one of the options changed
	         */
	        if (event.getProperty().equals(PreferenceConstants.SQL_RESULTS_VIEW_DISPLAY_MODE)
	                || event.getProperty().equals(PreferenceConstants.SQL_RESULTS_VIEW_DISPLAY_WINDOW)
	                || event.getProperty().equals(PreferenceConstants.SQL_RESULTS_VIEW_NULL_STRING)
	                || event.getProperty().equals(PreferenceConstants.SQL_RESULTS_VIEW_SHOW_ROW_NUMBER)
	                || event.getProperty().equals(PreferenceConstants.SQL_RESULTS_VIEW_SHOW_HEADING)
	                || event.getProperty().equals(PreferenceConstants.SQL_RESULTS_VIEW_SHOW_ROW_COUNT_MSG)
	                || event.getProperty().equals(PreferenceConstants.SQL_RESULTS_VIEW_SPLIT_MESSAGES)
	                || event.getProperty().equals(PreferenceConstants.SQL_RESULTS_VIEW_TABS_NUMBER)
	                || event.getProperty().equals(PreferenceConstants.SQL_RESULTS_VIEW_TABLES_LIMITATION))
	        {
	            _singleTabDisplayAction.update();
	            _textModeDisplayAction.update();
	            
	            ISelection selection = _resultHistory.getSelection();
	            IStructuredSelection sSelection = null;
	            if (selection instanceof IStructuredSelection)
	            {
	                sSelection = (IStructuredSelection) selection;
	            }
	            else
	            {
	                return;
	            }
	            IResultInstance instance = (IResultInstance) sSelection.getFirstElement();
	            reDisplay(instance);
	        }
	        
	        /**
	         * Refresh the result history when the filters are changed
	         */
	        if (event.getProperty().equals(PreferenceConstants.PROFILE_FILTERS_LIMIT_CHECK)
	                || event.getProperty().equals(PreferenceConstants.PROFILE_FILTERS_LIMIT_NUM)
	                || event.getProperty().equals(PreferenceConstants.PROFILE_FILTERS_STATUS_CRITICAL)
	                || event.getProperty().equals(PreferenceConstants.PROFILE_FILTERS_STATUS_WARNING)
	                || event.getProperty().equals(PreferenceConstants.PROFILE_FILTERS_STATUS_FAILED)
	                || event.getProperty().equals(PreferenceConstants.PROFILE_FILTERS_STATUS_SUCCESS)
	                || event.getProperty().equals(PreferenceConstants.PROFILE_FILTERS_STATUS_TERMINATED)
	                || event.getProperty().equals(PreferenceConstants.PROFILE_FILTERS_UNKNOWNPROFILE)
	                || event.getProperty().equals(PreferenceConstants.PROFILE_FILTERS_PROFILE_MAY_CHANGED))
	        {
	            _resultHistory.getResultTable().refresh();
	        }
	        
	        /**
	         * Re-display the result history according to the columns setting 
	         */
	        if (event.getProperty().equals(PreferenceConstants.RESULT_HISTORY_ALL_COLUMNS))
	        {
	            _resultHistory.reDisplayResults();
	        }
    	}
    }

    /**
     * Re-displays the selected instance since the options is modified
     * 
     * @param instance the result instance
     */
    public void reDisplay(final IResultInstance instance)
    {
        if (instance != null)
        {
            BusyIndicator.showWhile(_display, new Runnable()
            {
                public void run()
                {
                    _display.syncExec(new Runnable()
                    {
                        public void run()
                        {
                            if (_resultSection != null)
                            {
                                Composite comp = _resultSection.getControl();
                                if (comp != null && !comp.isDisposed())
                                {
                                    comp.dispose();
                                }
                            }
                            _resultSection = ResultSectionFactory.createResultSection(_detailArea, ResultsViewControl.this);

                            // must call layout method on _resultArea to force it layout
                            _detailArea.layout(true);
                            _resultSection.showDetail(instance);
                            _resultHistory.refresh();
                        }
                    });
                }
            });
        }
        else
        {
            _display.syncExec(new Runnable()
            {
                public void run()
                {
                    if (_resultSection != null)
                    {
                        Composite comp = _resultSection.getControl();
                        if (comp != null && !comp.isDisposed())
                        {
                            comp.dispose();
                        }
                    }
                    _resultSection = ResultSectionFactory.createResultSection(_detailArea, ResultsViewControl.this);
                    _detailArea.layout(true);
                }
            });
        }
    }

    public ResultSection getResultSection()
    {
        return _resultSection;
    }

    public void setResultSection(ResultSection section)
    {
        _resultSection = section;
    }

    public ResultHistorySection getResultHistorySection(){
    	return _resultHistory;
    }
    
    public IResultInstance getCurrentInstance()
    {
        return _currentInstance;
    }

    public IAction[] getRegisteredActions()
    {
        return new IAction[]
        {
            _removeResultAction, _removeAllVisibleFinishedResultAction, _terminateAction, _saveResultInstanceAction
        };
    }
    
    public void refreshResults()
    {
        _resultHistory.getResultTable().refresh();
    }
    
    class VerticalLayoutAction extends Action
    {
        SashForm _sash;

        /**
         *  
         */
        public VerticalLayoutAction(SashForm sash)
        {
            super(Messages.ResultsView_layout_vertical, IAction.AS_RADIO_BUTTON); 
            _sash = sash;
        }

        public void run()
        {
            _sash.setOrientation(SWT.VERTICAL);
            ResultsViewUIPlugin.getDefault().getPreferenceStore().setValue(
                    PreferenceConstants.VERTICAL_LAYOUT_RESULTS_VIEW, true);
        }

        public ImageDescriptor getImageDescriptor()
        {
            return Images.DESC_VERTICAL_RESULTS_VIEW;
        }
    }
    
    class HorizontalLayoutAction extends Action
    {
        SashForm _sash;

        /**
         *  
         */
        public HorizontalLayoutAction(SashForm sash)
        {
            super(Messages.ResultsView_layout_horizontal, IAction.AS_RADIO_BUTTON); 
            _sash = sash;
        }

        public void run()
        {
            _sash.setOrientation(SWT.HORIZONTAL);
            ResultsViewUIPlugin.getDefault().getPreferenceStore().setValue(
                    PreferenceConstants.VERTICAL_LAYOUT_RESULTS_VIEW, false);
        }

        public ImageDescriptor getImageDescriptor()
        {
            return Images.DESC_HORIZONTAL_RESULTS_VIEW;
        }
    }

    public void setCurrentInstance(IResultInstance instance)
    {
        _currentInstance = instance;
    }

    /**
     * Refresh the result history when profile is changed
     */
    public void profileChanged(IConnectionProfile profile, String oldName, String oldDesc, Boolean oldAutoConnect)
    {
        _sashform.getDisplay().syncExec(new Runnable()
        {
            public void run()
            {
                _resultHistory.getResultTable().refresh();
            }
        });    
    }

    /**
     * Refresh the result history when profile is created
     */
    public void profileAdded(IConnectionProfile profile)
    {
        _sashform.getDisplay().syncExec(new Runnable()
        {
            public void run()
            {
                _resultHistory.getResultTable().refresh();
            }
        });       
    }

    /**
     * Refresh the result history when profile is deleted
     */
    public void profileDeleted(IConnectionProfile profile)
    {
        _sashform.getDisplay().syncExec(new Runnable()
        {
            public void run()
            {
                _resultHistory.getResultTable().refresh();
            }
        });        
    }

    /**
     * Refresh the result history when profile is changed
     */
    public void profileChanged(IConnectionProfile profile)
    {
        _sashform.getDisplay().syncExec(new Runnable()
        {
            public void run()
            {
                _resultHistory.getResultTable().refresh();
            }
        });      
    }

    public RemoveAllVisibleFinishedResultAction getRemoveAllVisibleFinishedResultAction()
    {
        return _removeAllVisibleFinishedResultAction;
    }

    public void clearHistory()
    {
    	_removeAllVisibleFinishedResultAction.setInstances(ResultsViewUIPlugin.getResultManager().getAllResults());
    	_removeAllVisibleFinishedResultAction.run();

    }
    
	public ResultsView getView() {
		return _resultsView;
	}

	public void addResultHistoryFilter(ViewerFilter filter) {
		getResultHistorySection().getResultTable().addFilter(filter);
	}

	/**
	 * Gets the ResultsViewControl's setting about whether to use preference
	 * settings or defaults.
	 */
	public boolean getUsePreferences(){
		return _usePreferences;
	}
	
	/**
	 * Tells the ResultsViewControl whether to use preference settings or
	 * preference defaults.
	 * @param usePreferences
	 */
	public void setUsePreferences(boolean usePreferences){
		_usePreferences = usePreferences;
	}
	
	public IAction getTerminateAction() {
		return _terminateAction;
	}

	public IAction getRemoveResultAction() {
		return _removeResultAction;
	}

	public IAction getSingleTabDisplayAction() {
		return _singleTabDisplayAction;
	}

	public IAction getTextModeDisplayAction() {
		return _textModeDisplayAction;
	}

	public Action getVLayoutAction() {
		return _vLayoutAction;
	}

	public Action getHLayoutAction() {
		return _hLayoutAction;
	}

	public IAction getOpenFilterDialogAction() {
		return _openFilterDialogAction;
	}

	public IAction getPreferenceAction() {
		return _preferenceAction;
	}

    /**
     * When a new result instance is created, we perform the following steps:
     * <ul>
     * <li>Clears the status for the SQL Results View
     * <li>Refreshes the history results table to display the newly-created result
     * <li>Selects this newly-created result (make it the current result, this will invoke the
     * <code>showDetail</code> method to show this result)
     * </ul>
     */
    public void resultInstanceCreated(final IResultInstance result)
    {
        _display.syncExec(new Runnable()
        {
            public void run()
            {
            	if (_resultsView != null){
            		_resultsView.clearStatusLine();
            	}
                _resultHistory.search();
                _resultHistory.refresh();
                _resultHistory.getResultTable().setSelection(new StructuredSelection(result));
                _currentInstance = result;
            }
        });
    }

    /**
     * When an instance is removed, we perform the following steps:
     * <ul>
     * <li>Clears the status for the SQL Results View
     * <li>Re-search the index according to current query expression
     * <li>Refreshes the history results table
     * </ul>
     */
    public void resultInstanceRemoved(final IResultInstance result)
    {
        _display.syncExec(new Runnable()
        {
            public void run()
            {
            	if (_resultsView != null){
            		_resultsView.clearStatusLine();
            	}
                _resultHistory.search();
                _resultHistory.refresh();
            }
        });
    }
    
    /**
     * When an array of instances are removed, we perform the following steps:
     * <ul>
     * <li>Clears the status for the SQL Results View
     * <li>Re-search the index according to current query expression
     * <li>Refreshes the history results table
     * </ul>
     */
    public void resultInstancesRemoved(final IResultInstance[] results)
    {
        _display.syncExec(new Runnable()
        {
            public void run()
            {
            	if (_resultsView != null){
            		_resultsView.clearStatusLine();
            	}
                _resultHistory.search();
                _resultHistory.refresh();
            }
        });
    }

    /**
     * When all instances are removed, we perform the following steps:
     * <ul>
     * <li>Clears the status for the SQL Results View
     * <li>Re-search the index according to current query expression
     * <li>Refreshes the history results table
     * </ul>
     */
    public void allResultInstancesRemoved()
    {
        _display.syncExec(new Runnable()
        {
            public void run()
            {
            	if (_resultsView != null){
            		_resultsView.clearStatusLine();
            	}
                _resultHistory.search();
                _resultHistory.refresh();
            }
        });
    }

    /**
     * When an item is appended to the CURRENT result instance, we perform the following steps:
     * <ul>
     * <li>Invokes the <code>onNewItemAppended</code> method to show the new item
     * </ul>
     */
    public void resultInstanceAppended(IResultInstance instance, final ResultItem result, final int index)
    {
        if (instance != _currentInstance)
        {
            return;
        }
        /**
         * When the onNewItemAppended method is invoked, maybe the item is already displayed (in showDetail method)
         */
        _sashform.getDisplay().syncExec(new Runnable()
        {
            public void run()
            {
                _resultSection.onNewItemAppended(result, index);
            }
        });
    }

    /**
     * When a result instance's status is changed, we perform the following steps:
     * <ul>
     * <li>If the status is changed to FINISH status, we update the actions accordingly
     * <li>Refreshes the history results table to display new status for this result
     * <li>If it is the CURRENT instance, invokes <code>onInstanceFinished</code>
     * </ul>
     */
    public void resultInstanceStatusUpdated(final IResultInstance instance)
    {
        if (instance.isFinished())
        {
       		_terminateAction.update();
       		_removeResultAction.update();
        }
        _display.syncExec(new Runnable()
        {
            public void run()
            {
                _resultHistory.refresh();
                if (instance == _currentInstance && instance.isFinished())
                {
                    _resultHistory.search();
                    _resultHistory.refresh();
                    _resultSection.onInstanceFinished();
                }
            }
        });
    }

    /**
     * When an instance is reseted, we perform the following steps:
     * <ul>
     * <li>Re-search the index according to current query expression
     * <li>Refreshes the history results table to display new status
     * <li>If it is the CURRENT instance, invokes <code>onInstanceReseted</code> method
     * </ul>
     */
    public void resultInstanceReset(final IResultInstance instance)
    {
        _display.syncExec(new Runnable()
        {
            public void run()
            {
                _resultHistory.search();
                _resultHistory.refresh();
                if (instance == _currentInstance)
                {
                    _resultSection.onInstanceReseted();
                }
            }
        });
    }

    /**
     * When CURRENT result instance need to show parameters, we perform the following steps:
     * <ul>
     * <li>Invokes the <code>onParametersShown</code> method to show the parameters
     * </ul>
     */
    public void parametersShow(IResultInstance instance, final List params)
    {
        if (instance != _currentInstance)
        {
            return;
        }
        _display.syncExec(new Runnable()
        {
            public void run()
            {
                _resultSection.onParametersShown(params);
            }
        });
    }
}

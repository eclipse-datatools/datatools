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

import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.result.internal.filters.ConnectionProfileFilter;
import org.eclipse.datatools.sqltools.result.internal.filters.ResultStatusFilter;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.ui.PreferenceConstants;
import org.eclipse.datatools.sqltools.result.internal.ui.actions.RemoveAllVisibleFinishedResultAction;
import org.eclipse.datatools.sqltools.result.internal.ui.viewer.TextResultViewer;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.ui.IHelpConstants;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.datatools.sqltools.result.ui.view.ResultsViewControl;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.text.IFindReplaceTarget;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IKeyBindingService;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

/**
 * This is the view showing the result.
 * 
 * @author Dafan Yang
 */
public class ResultsView extends ViewPart
{
	ResultsViewControl _resultsViewControl;

    static final String                          ORIENTATION_GROUP = "orientation";

    public ResultsView()
    {
        super();
        _resultsViewControl = new ResultsViewControl(this);
        _resultsViewControl.setUsePreferences(true);
    }

    public void init(IViewSite site, IMemento memento) throws PartInitException
    {
        super.init(site, memento);
        _resultsViewControl.init(site, memento);
    }

    public void saveState(IMemento memento)
    {
        super.saveState(memento);
        _resultsViewControl.saveState(memento);
    }

    public void createPartControl(Composite parent)
    {
    	PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, HelpUtil.getContextId(IHelpConstants.VIEW_SQL_RESULT, ResultsViewUIPlugin.getDefault().getBundle().getSymbolicName()));
    	
    	this.setTitleToolTip(Messages.ResultsView_tooltip); 

        _resultsViewControl.createPartControl(parent);
        
        // set the selection provider as the result table.
        getSite().setSelectionProvider(_resultsViewControl.getResultHistorySection().getResultTable());
        
        initActionBars();

        MenuManager menuBar = (MenuManager) getViewSite().getActionBars().getMenuManager();
        menuBar.add(new Separator());
        menuBar.add(_resultsViewControl.getOpenFilterDialogAction());
        menuBar.add(_resultsViewControl.getPreferenceAction());
	        
        menuBar.add(new Separator(ORIENTATION_GROUP));

        menuBar.appendToGroup(ORIENTATION_GROUP, _resultsViewControl.getVLayoutAction());
        menuBar.appendToGroup(ORIENTATION_GROUP, _resultsViewControl.getHLayoutAction());
    
    	IKeyBindingService keyBinding = getViewSite().getKeyBindingService();
    	IActionBars actionBars = getViewSite().getActionBars();
    	
        actionBars.setGlobalActionHandler("org.eclipse.datatools.sqltools.result.terminate", _resultsViewControl.getTerminateAction());        
	        
        actionBars.setGlobalActionHandler("org.eclipse.datatools.sqltools.result.removeInstance", _resultsViewControl.getRemoveResultAction());
        
        actionBars.setGlobalActionHandler("org.eclipse.datatools.sqltools.result.removeAllInstances", _resultsViewControl.getRemoveAllVisibleFinishedResultAction());
	        
        keyBinding.registerAction(_resultsViewControl.getRemoveResultAction());
        keyBinding.registerAction(_resultsViewControl.getRemoveAllVisibleFinishedResultAction());
        keyBinding.registerAction(_resultsViewControl.getTerminateAction());
        actionBars.updateActionBars();
        
        _resultsViewControl.getResultHistorySection().getResultTable().addFilter(new ConnectionProfileFilter(ResultsViewUIPlugin.getDefault().getPreferenceStore()));
        _resultsViewControl.getResultHistorySection().getResultTable().addFilter(new ResultStatusFilter(ResultsViewUIPlugin.getDefault().getPreferenceStore()));
    }

    protected void initActionBars()
    {
        IActionBars actionbars = getViewSite().getActionBars();
        actionbars.getToolBarManager().add(_resultsViewControl.getTerminateAction());
        actionbars.getToolBarManager().add(_resultsViewControl.getRemoveResultAction());
        actionbars.getToolBarManager().add(_resultsViewControl.getRemoveAllVisibleFinishedResultAction());
        actionbars.getToolBarManager().add(new Separator());
        actionbars.getToolBarManager().add(_resultsViewControl.getSingleTabDisplayAction());
        actionbars.getToolBarManager().add(_resultsViewControl.getTextModeDisplayAction());        
        if (ResultsViewUIPlugin.getDefault().getPreferenceStore().getBoolean(PreferenceConstants.VERTICAL_LAYOUT_RESULTS_VIEW))
        {
        	_resultsViewControl.getVLayoutAction().setChecked(true);
        	_resultsViewControl.getHLayoutAction().setChecked(false);
        }
        else
        {
        	_resultsViewControl.getHLayoutAction().setChecked(true);
        	_resultsViewControl.getVLayoutAction().setChecked(false);
        }
        actionbars.getToolBarManager().add(new Separator());
        actionbars.getToolBarManager().add(_resultsViewControl.getOpenFilterDialogAction());
    }

    public void setFocus()
    {
    	_resultsViewControl.setFocus();
    }

    public void dispose()
    {
        super.dispose();
        
        _resultsViewControl.dispose();
    }


    /**
     * Re-displays the selected instance since the options is modified
     * 
     * @param instance the result instance
     */
    private void reDisplay(final IResultInstance instance)
    {
    	_resultsViewControl.reDisplay(instance);
    }
    
    public ResultsViewControl getResultsViewControl()
    {
    	return _resultsViewControl;
    }

    public ResultSection getResultSection()
    {
        return _resultsViewControl.getResultSection();
    }

    public void setResultSection(ResultSection section)
    {
    	_resultsViewControl.setResultSection(section);
    }

    public IResultInstance getCurrentInstance()
    {
    	return _resultsViewControl.getCurrentInstance();
    }

    public IAction[] getRegisteredActions()
    {
    	return _resultsViewControl.getRegisteredActions();
    }
    
    public void clearStatusLine()
    {
        getViewSite().getActionBars().getStatusLineManager().setMessage(null);
    }
    
    public void refreshResults()
    {
    	_resultsViewControl.getResultHistorySection().getResultTable().refresh();
    }
       
    public void setCurrentInstance(IResultInstance instance)
    {
    	_resultsViewControl.setCurrentInstance(instance);
    }


    public RemoveAllVisibleFinishedResultAction getRemoveAllVisibleFinishedResultAction()
    {
        return _resultsViewControl.getRemoveAllVisibleFinishedResultAction();
    }

    public Object getAdapter(Class adapter)
    {
        if (IFindReplaceTarget.class.equals(adapter))
        {
            IFindReplaceTarget target = null;
            TextResultViewer viewer = null;
            if (_resultsViewControl != null){
	            if (_resultsViewControl.getResultSection() instanceof MultipleTabsTextSection)
	            {
	                viewer = ((MultipleTabsTextSection) _resultsViewControl.getResultSection()).getTextViewer();
	            }
	            if (_resultsViewControl.getResultSection() instanceof SingleWindowTextSection)
	            {
	                viewer = ((SingleWindowTextSection) _resultsViewControl.getResultSection()).getTextViewer();
	            }
	            if (viewer != null)
	            {
	                target = viewer.getViewer().getFindReplaceTarget();
	            }
            }
            return target;
        }
        
        return super.getAdapter(adapter);
    }

}
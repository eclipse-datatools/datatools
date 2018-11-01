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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.datatools.sqltools.result.IReExecutionRunnable;
import org.eclipse.datatools.sqltools.result.OperationCommand;
import org.eclipse.datatools.sqltools.result.ResultsConstants;
import org.eclipse.datatools.sqltools.result.ResultsViewPlugin;
import org.eclipse.datatools.sqltools.result.internal.core.ReExecutionRegistryReader;
import org.eclipse.datatools.sqltools.result.internal.index.IResultHistoryIndex;
import org.eclipse.datatools.sqltools.result.internal.model.ResultInstance;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.ui.PreferenceConstants;
import org.eclipse.datatools.sqltools.result.internal.ui.actions.ReExecuteAction;
import org.eclipse.datatools.sqltools.result.internal.ui.utils.PreferenceUtil;
import org.eclipse.datatools.sqltools.result.internal.utils.SerializationHelper;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.datatools.sqltools.result.ui.view.ResultsViewControl;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.texteditor.IUpdate;

/**
 * Table viewer that supports limit the maximum number of items
 * 
 * @author Dafan Yang
 */
class ItemsLimitationTreeViewer extends TreeViewer
{
    private org.eclipse.core.runtime.ListenerList _listeners;
    private ResultsViewControl _resultsViewControl;
    
    public void addRefreshListener(Listener listener)
    {
        _listeners.add(listener);
    }
    
    public void removeRefreshListener(Listener listener)
    {
        _listeners.remove(listener);
    }
    
    public ItemsLimitationTreeViewer(Composite parent, int style, ResultsViewControl resultsViewControl)
    {
        super(parent, style);
        _listeners = new ListenerList();
        _resultsViewControl = resultsViewControl;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.viewers.StructuredViewer#getFilteredChildren(java.lang.Object)
     */
    public Object[] getFilteredChildren(Object parent)
    {
        IPreferenceStore store = ResultsViewUIPlugin.getDefault().getPreferenceStore();

        ViewerFilter[] filters = getFilters();
        Object[] result = getRawChildren(parent);
        boolean isSubResults = false;
        if(parent instanceof IResultInstance)
        {
            isSubResults = true;
        }
        if (filters != null) 
        {
            for (int i=0;i<filters.length;i++) 
            {
                ViewerFilter f = (ViewerFilter) filters[i];
                result = f.filter(this, parent, result);
            }
        }

        // don't limit the sub results
        if(PreferenceUtil.getBoolean(store, PreferenceConstants.PROFILE_FILTERS_LIMIT_CHECK, _resultsViewControl.getUsePreferences()) && !isSubResults)
        {
            int limit = PreferenceUtil.getInt(store, PreferenceConstants.PROFILE_FILTERS_LIMIT_NUM,  _resultsViewControl.getUsePreferences());
            if(result.length > limit)
            {
                ArrayList list = new ArrayList();

                //Get the latest array. ignore the running items
                for(int i=0;i<limit;i++)
                {
                    list.add(result[result.length - i - 1]);
                }
               
                return list.toArray();
            }
        }
        return result;
    }

    public void refresh()
    {
        super.refresh();
        Object[] listeners = this._listeners.getListeners();
        for (int i = 0; i < listeners.length; i++)
        {
            ((Listener)listeners[i]).handleEvent(new Event());
        }
    }
}

/**
 * This UI component is in charge of displaying the left panel (result history) of SQL Results View.
 * 
 * @author Dafan Yang
 */
public class ResultHistorySection
{
    public static final String       TAG_COLUMN_WIDTH = "columnWidth"; //$NON-NLS-1$
    private int[]                    _columnsWidth    = new int[ResultHistoryHelper.COLUMN_NUMBER];
    private Composite                _detailParent;
    private Font                     _filterFont;
    private Text                     _queryExpressionText;
    private boolean                  _firstClick      = true;
    private TreeViewer               _resultsTreeTable;
    /* A new ResultSection may be created and should write back to ResultsView */
    private ResultsViewControl        _resultsViewControl;
    private ResultsViewLabelProvider _resultViewLabelProvider;
    private String                   _filterInfo;
    private static final int         INITIAL_WIDTH    = 80;
    private Runnable                 _searchHistory   = new Runnable()
                                                      {
                                                          public void run()
                                                          {
                                                              String query = _queryExpressionText.getText();
                                                              if (query == null || query.length() == 0)
                                                              {
                                                                  _resultsTreeTable.getTree().setFont(_sysFont);
                                                                  _resultsTreeTable.setInput(ResultsViewUIPlugin
                                                                          .getResultManager().getAllResults());
                                                                  _resultsTreeTable.refresh();
                                                              }
                                                              else
                                                              {
                                                                  _resultsTreeTable.getTree().setFont(_filterFont);
                                                                  IResultHistoryIndex index = ResultsViewPlugin
                                                                          .getDefault().getResultHistoryIndex();
                                                                  IResultInstance[] instances = index.search(query);
                                                                  _resultsTreeTable.setInput(instances);
                                                                  _resultsTreeTable.refresh();
                                                              }
                                                          }
                                                      };
    private Font                     _sysFont;
    private int                      OPER_ORDER       = -1;
    private int                      DATE_ORDER       = -1;
    private int                      STATUS_ORDER     = -1;
    private int                      FREQ_ORDER       = -1;
    private int                      ACTION_ORDER     = -1;
    private int                      CONSUMER_ORDER   = -1;
    private int                      PROFILE_ORDER    = -1;
    private static int               ASCENDING        = 1;

    public ResultHistorySection(Composite parent, Composite detailArea, ResultsViewControl resultsViewControl)
    {
        GridLayout layout = new GridLayout();
        layout.verticalSpacing = 1;
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.numColumns = 1;
        parent.setLayout(layout);
        _resultsViewControl = resultsViewControl;

        _queryExpressionText = new Text(parent, SWT.BORDER);
        _queryExpressionText.setToolTipText(Messages.ResultHistorySection_query_expression_tooltip);
        _queryExpressionText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        _queryExpressionText.setText(Messages.ResultHistorySection_query_expression); 
        _queryExpressionText.addMouseListener(new MouseListener()
        {

            public void mouseDoubleClick(MouseEvent e)
            {
                // Do nothing
            }

            public void mouseDown(MouseEvent e)
            {
                if(_firstClick)
                {
                    _queryExpressionText.setText(""); //$NON-NLS-1$
                    _firstClick = false;
                }
            }

            public void mouseUp(MouseEvent e)
            {
                // Do nothing
            }
        });
        _queryExpressionText.addMouseTrackListener(new MouseTrackListener()
        {

            public void mouseEnter(MouseEvent e)
            {
                //Do nothing
            }

            public void mouseExit(MouseEvent e)
            {
                _queryExpressionText.clearSelection();
            }

            public void mouseHover(MouseEvent e)
            {
                //FIX BZ185443  
//                _queryExpressionText.setFocus();
//                _queryExpressionText.selectAll();
            }
        });
        _queryExpressionText.addModifyListener(new ModifyListener()
        {
            public void modifyText(ModifyEvent e)
            {
                if(!_firstClick)
                {
                    prepareToQuery();
                }
                else
                {
                    _firstClick = false;
                }
            }
        });
        
        _resultsTreeTable = new ItemsLimitationTreeViewer(parent, SWT.SINGLE | SWT.V_SCROLL | SWT.H_SCROLL
                | SWT.FULL_SELECTION, resultsViewControl);
        GridData gd = new GridData(GridData.FILL_BOTH);
        _resultsTreeTable.getTree().setLayoutData(gd);
        _detailParent = detailArea;
        configureResultsTable(_resultsTreeTable);
        createContextMenu();
        
        _sysFont = _resultsTreeTable.getTree().getFont();
        FontData[] fontData = _sysFont.getFontData();

        for (int i = 0; i < fontData.length; i++)
        {
            fontData[i].setStyle(fontData[i].getStyle() | SWT.ITALIC);
        }
        _filterFont = new Font(_resultsTreeTable.getTree().getDisplay(), fontData);
    }

    /**
     * Applies the memento to this section
     * 
     * @param memento the memento
     */
    public void applyState(IMemento memento)
    {
        // Restore column width
        if (memento != null)
        {
            TableLayout layout = new TableLayout();
            boolean suc = true;
            for (int i = 0; i < ResultHistoryHelper.COLUMN_NUMBER; i++)
            {
                Integer width = memento.getInteger(TAG_COLUMN_WIDTH + i);
                if (width == null)
                {
                    suc = false;
                    break;
                }
                else
                {
                    _columnsWidth[i] = width.intValue();
                }
            }

            if (suc)
            {
                for (int i = 0; i < ResultHistoryHelper.COLUMN_NUMBER; i++)
                {
                    if(_columnsWidth[i]!=0)
                    {
                        layout.addColumnData(new ColumnPixelData(_columnsWidth[i], true));
                    }
                }
                _resultsTreeTable.getTree().setLayout(layout);
            }
        }
    }

    /**
     * Configures the result history table
     * 
     * @param resultsTable the table viewer
     */
    private void configureResultsTable(final TreeViewer resultsTable)
    {
        resultsTable.getTree().setHeaderVisible(true);
        
        createColumns();
        
        _resultViewLabelProvider = new ResultsViewLabelProvider(_resultsViewControl);
        resultsTable.setLabelProvider(_resultViewLabelProvider);
        resultsTable.setContentProvider(new ResultsHistoryContentProvider());
        resultsTable.setInput(ResultsViewUIPlugin.getResultManager().getAllResults());
        ((ItemsLimitationTreeViewer) resultsTable).addRefreshListener(new Listener()
        {
            public void handleEvent(Event event)
            {
                IResultInstance[] visibleItems = getVisibleInstances();
                List visibleFinishedItems = new ArrayList();
                for(int i=0;i<visibleItems.length;i++)
                {
                    if(visibleItems[i].isFinished())
                    {
                        visibleFinishedItems.add(visibleItems[i]);
                    }
                }
                _resultsViewControl.getRemoveAllVisibleFinishedResultAction().setInstances(
                        (IResultInstance[]) visibleFinishedItems.toArray(new IResultInstance[0]));
                
                int succeededItems = 0;
                int failedItems = 0;
                int terminatedItems = 0;
                int warningItems = 0;
                int criticalItems = 0;
                for (int i = 0; i < visibleItems.length; i++)
                {
                    switch (visibleItems[i].getStatus())
                    {
                        case OperationCommand.STATUS_SUCCEEDED:
                            succeededItems++;
                            break;
                        case OperationCommand.STATUS_FAILED:
                            failedItems++;
                            break;
                        case OperationCommand.STATUS_TERMINATED:
                            terminatedItems++;
                            break;
                        case OperationCommand.STATUS_WARNING:
                            warningItems++;
                            break;
                        case OperationCommand.STATUS_CRITICAL_ERROR:
                            criticalItems++;
                            break;
                        default:
                            break;
                    }
                }
                
                Object[] args = new Object[]
                {
                    new Integer(visibleItems.length),
                    new Integer(ResultsViewUIPlugin.getResultManager().getAllResults().length),
                    new Integer(succeededItems),
                    new Integer(failedItems),
                    new Integer(terminatedItems),
                    new Integer(warningItems),
                    new Integer(criticalItems)
                };
                
                _filterInfo = NLS.bind(Messages.ResultView_filter_info, args); 
                if (_resultsViewControl.getView() != null){
                	_resultsViewControl.getView().getViewSite().getActionBars().getStatusLineManager().setMessage(_filterInfo);
                }
            }
        });
        resultsTable.addSelectionChangedListener(new ISelectionChangedListener()
        {
            public void selectionChanged(SelectionChangedEvent event)
            {               
                IStructuredSelection sel = (IStructuredSelection) event.getSelection();
                final Object obj = sel.getFirstElement();
                if (_resultsViewControl.getCurrentInstance() == (IResultInstance) obj)
                {
                    return;
                }
                final IResultInstance lastResultInstance = _resultsViewControl.getCurrentInstance();
                
                _resultsViewControl.setCurrentInstance((IResultInstance) obj);
                BusyIndicator.showWhile(_detailParent.getDisplay(), new Runnable()
                {
                    public void run()
                    {
                        _detailParent.getDisplay().syncExec(new Runnable()
                        {
                            public void run()
                            {
                                if (_resultsViewControl.getResultSection() != null)
                                {
                                    Composite comp = _resultsViewControl.getResultSection().getControl();
                                    if (comp != null && !comp.isDisposed())
                                    {
                                        comp.dispose();
                                    }
                                }
                                _resultsViewControl.setResultSection(ResultSectionFactory.createResultSection(_detailParent,
                                		_resultsViewControl));

                                // must call layout method on _resultArea to force it layout
                                _detailParent.layout(true);
                                _resultsViewControl.getResultSection().showDetail((IResultInstance) obj);
                                
                                // set the result and parameter list of the last display to be null for reclaiming them, when selection is changed.
                                if(lastResultInstance instanceof ResultInstance)
                                {
                                	Object[] objs ={lastResultInstance.getParameters(),((ResultInstance) lastResultInstance).getResults()};
                                	SerializationHelper.SaveObjects(objs, ((ResultInstance) lastResultInstance).getFileName());
                                    ((ResultInstance) lastResultInstance).reclaimedTransientThings();
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    /**
     * Creates all the configured-to-display columns
     *
     */
    private void createColumns()
    {
        int columnNum = ResultHistoryHelper.getColumnNumber(_resultsViewControl.getUsePreferences());
        boolean isFirstTimeToCreate = true;
        for (int i = 0; i < columnNum; i++)
        {
            int configurableColumnIndex = ResultHistoryHelper.getConfigurableColumnIndex(i, _resultsViewControl.getUsePreferences());
            int style = configurableColumnIndex == ResultHistoryHelper.OPER_INDEX ? SWT.LEFT_TO_RIGHT: SWT.NONE;
            final TreeColumn column = new TreeColumn(_resultsTreeTable.getTree(), style);
            switch (configurableColumnIndex)
            {
                case ResultHistoryHelper.STATUS_INDEX:
                    column.setText(Messages.ResultHistorySection_status); 
                    column.addSelectionListener(new SelectionAdapter()
                    {
                        public void widgetSelected(SelectionEvent e)
                        {
                            STATUS_ORDER *= -1;
                            ViewerSorter sorter = getViewerSorter(ResultHistoryHelper.STATUS_INDEX);
                            _resultsTreeTable.setSorter(sorter);
                            _resultsTreeTable.getTree().setSortColumn(column);
                            _resultsTreeTable.getTree().setSortDirection(STATUS_ORDER == -1 ? SWT.DOWN : SWT.UP);
                        }
                    });
                    break;
                case ResultHistoryHelper.OPER_INDEX:
                    column.setText(Messages.ResultHistorySection_operation); 
                    column.addSelectionListener(new SelectionAdapter()
                    {
                        public void widgetSelected(SelectionEvent e)
                        {
                            OPER_ORDER *= -1;
                            ViewerSorter sorter = getViewerSorter(ResultHistoryHelper.OPER_INDEX);
                            _resultsTreeTable.setSorter(sorter);
                            _resultsTreeTable.getTree().setSortColumn(column);
                            _resultsTreeTable.getTree().setSortDirection(OPER_ORDER == -1 ? SWT.DOWN : SWT.UP);
                        }
                    });
                    break;
                case ResultHistoryHelper.FREQ_INDEX:
                    column.setText(Messages.ResultHistorySection_frequency); 
                    column.addSelectionListener(new SelectionAdapter()
                            {
                                public void widgetSelected(SelectionEvent e)
                                {
                                    FREQ_ORDER *= -1;
                                    ViewerSorter sorter = getViewerSorter(ResultHistoryHelper.FREQ_INDEX);
                                    _resultsTreeTable.setSorter(sorter);
                                    _resultsTreeTable.getTree().setSortColumn(column);
                                    _resultsTreeTable.getTree().setSortDirection(FREQ_ORDER == -1 ? SWT.DOWN : SWT.UP);
                                }
                            });
                    break;
                case ResultHistoryHelper.DATE_INDEX:
                    column.setText(Messages.ResultHistorySection_date); 
                    column.addSelectionListener(new SelectionAdapter()
                    {
                        public void widgetSelected(SelectionEvent e)
                        {
                            DATE_ORDER *= -1;
                            ViewerSorter sorter = getViewerSorter(ResultHistoryHelper.DATE_INDEX);
                            _resultsTreeTable.setSorter(sorter);
                            _resultsTreeTable.getTree().setSortColumn(column);
                            _resultsTreeTable.getTree().setSortDirection(DATE_ORDER == -1 ? SWT.DOWN : SWT.UP);
                        }
                    });
                    break;
                case ResultHistoryHelper.ACTION_INDEX:
                    column.setText(Messages.ResultHistorySection_action_type); 
                    column.addSelectionListener(new SelectionAdapter()
                            {
                                public void widgetSelected(SelectionEvent e)
                                {
                                    ACTION_ORDER *= -1;
                                    ViewerSorter sorter = getViewerSorter(ResultHistoryHelper.ACTION_INDEX);
                                    _resultsTreeTable.setSorter(sorter);
                                    _resultsTreeTable.getTree().setSortColumn(column);
                                    _resultsTreeTable.getTree().setSortDirection(ACTION_ORDER == -1 ? SWT.DOWN : SWT.UP);
                                }
                            });
                    break;
                case ResultHistoryHelper.CONSUMER_INDEX:
                    column.setText(Messages.ResultHistorySection_consumer_name); 
                    column.addSelectionListener(new SelectionAdapter()
                            {
                                public void widgetSelected(SelectionEvent e)
                                {
                                    CONSUMER_ORDER *= -1;
                                    ViewerSorter sorter = getViewerSorter(ResultHistoryHelper.CONSUMER_INDEX);
                                    _resultsTreeTable.setSorter(sorter);
                                    _resultsTreeTable.getTree().setSortColumn(column);
                                    _resultsTreeTable.getTree().setSortDirection(CONSUMER_ORDER == -1 ? SWT.DOWN : SWT.UP);
                                }
                            });
                    break;
                case ResultHistoryHelper.PROFILE_INDEX:
                    column.setText(Messages.ResultHistorySection_connection_profile); 
                    column.addSelectionListener(new SelectionAdapter()
                            {
                                public void widgetSelected(SelectionEvent e)
                                {
                                    PROFILE_ORDER *= -1;
                                    ViewerSorter sorter = getViewerSorter(ResultHistoryHelper.PROFILE_INDEX);
                                    _resultsTreeTable.setSorter(sorter);
                                    _resultsTreeTable.getTree().setSortColumn(column);
                                    _resultsTreeTable.getTree().setSortDirection(PROFILE_ORDER == -1 ? SWT.DOWN : SWT.UP);
                                }
                            });
                    break;
                default:
                    break;
            }
            
            if(_columnsWidth[configurableColumnIndex] != 0)
            {
                column.setWidth(_columnsWidth[configurableColumnIndex]);
                isFirstTimeToCreate = false;
            }
            else
            {
                column.pack();
            }
        }
        
        if (isFirstTimeToCreate)
        {
            TreeColumn[] columns = _resultsTreeTable.getTree().getColumns();
            for (int i = 0; i < columns.length; i++)
            {
                columns[i].setWidth(INITIAL_WIDTH);
            }
        }
    }

    private void createContextMenu()
    {
        MenuManager mgr = new MenuManager();
        mgr.setRemoveAllWhenShown(true);
        mgr.addMenuListener(new IMenuListener()
        {
            public void menuAboutToShow(IMenuManager m)
            {
                fillResultViewContextMenu(m);
            }
        }
        );
        Menu menu = mgr.createContextMenu(_resultsTreeTable.getControl());
        _resultsTreeTable.getControl().setMenu(menu);
        if (_resultsViewControl.getView() != null){
        	_resultsViewControl.getView().getSite().registerContextMenu(mgr, _resultsTreeTable);
        }
    }
    
    private void fillResultViewContextMenu(IMenuManager mgr)
    {
        IAction[] actions = _resultsViewControl.getRegisteredActions();
        for (int i = 0; i < actions.length; i++)
        {
            mgr.add(actions[i]);
            if(actions[i] instanceof IUpdate)
            {
                ((IUpdate) actions[i]).update();
            }
        }
        List visibleFinishedItems = new ArrayList();
        IResultInstance[] visibleItems = getVisibleInstances();
        for(int i=0;i<visibleItems.length;i++)
        {
            if(visibleItems[i].isFinished())
            {
                visibleFinishedItems.add(visibleItems[i]);
            }
        }
        _resultsViewControl.getRemoveAllVisibleFinishedResultAction().setInstances(
                (IResultInstance[]) visibleFinishedItems.toArray(new IResultInstance[0]));
        
      IStructuredSelection sel = (IStructuredSelection) _resultsTreeTable.getSelection();
      if (!sel.isEmpty())
      {
          IResultInstance ins = (IResultInstance) sel.getFirstElement();
          OperationCommand cmd = ins.getOperationCommand();
          if(cmd != null)
          {
              IReExecutionRunnable runnable = ReExecutionRegistryReader.readProperReExecutionHandler(cmd);
              if(runnable != null)
              {
                  ReExecuteAction reExecuteAction = new ReExecuteAction(cmd, runnable);
                  if(!ins.isFinished())
                  {
                      reExecuteAction.setEnabled(false);
                  }
                  mgr.add(reExecuteAction);
              }
          }
      }
        mgr.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
    }
    
    public TreeViewer getResultTable()
    {
        return _resultsTreeTable;
    }

    public ISelection getSelection()
    {
        return _resultsTreeTable.getSelection();
    }

    private boolean hasSubResult(Object e1, Object e2)
    {
        if (e1 instanceof IResultInstance && e2 instanceof IResultInstance)
        {
            IResultInstance ins1 = (IResultInstance) e1;
            IResultInstance ins2 = (IResultInstance) e2;
            if (!ins1.isParentResult() || !ins2.isParentResult())
            {
                return true;
            }
        }
        return false;
    }
    
    private ViewerSorter getViewerSorter(int columnIndex)
    {
        switch (columnIndex)
        {
            case ResultHistoryHelper.DATE_INDEX:
                return new ViewerSorter()
                {
                    public int compare(Viewer viewer, Object e1, Object e2)
                    {
                        if (hasSubResult(e1, e2))
                        {
                            return 0;
                        }
                        if (e1 instanceof IResultInstance && e2 instanceof IResultInstance) 
                        {
                            Date date1 = ((IResultInstance) e1).getExecuteDate();
                            Date date2 = ((IResultInstance) e2).getExecuteDate();
                            if (DATE_ORDER == ASCENDING)
                            {
                                return date1.before(date2) ? -1 : 1;
                            }
                            return date1.after(date2) ? -1 : 1;
                        }
                        return 0;
                    }
                };
            case ResultHistoryHelper.STATUS_INDEX:
                return new ViewerSorter()
                {
                    public int compare(Viewer viewer, Object e1, Object e2)
                    {
                        if (hasSubResult(e1, e2))
                        {
                            return 0;
                        }
                        return super.compare(viewer, _resultViewLabelProvider.getColumnText(e1,
                                ResultHistoryHelper.STATUS_INDEX), _resultViewLabelProvider.getConfigurableColumnText(
                                e2, ResultHistoryHelper.STATUS_INDEX))
                                * STATUS_ORDER;
                    }
                };
            case ResultHistoryHelper.OPER_INDEX:
                return new ViewerSorter()
                {
                    public int compare(Viewer viewer, Object e1, Object e2)
                    {
                        if (hasSubResult(e1, e2))
                        {
                            return 0;
                        }
                        return super.compare(viewer, _resultViewLabelProvider.getColumnText(e1,
                                ResultHistoryHelper.OPER_INDEX), _resultViewLabelProvider.getConfigurableColumnText(e2,
                                ResultHistoryHelper.OPER_INDEX))
                                * OPER_ORDER;
                    }
                };
            case ResultHistoryHelper.FREQ_INDEX:
                return new ViewerSorter()
                {
                    public int compare(Viewer viewer, Object e1, Object e2)
                    {
                        if (hasSubResult(e1, e2))
                        {
                            return 0;
                        }
                        try
                        {
                            int freq1 = Integer.parseInt(_resultViewLabelProvider.getColumnText(e1,
                                    ResultHistoryHelper.FREQ_INDEX));
                            int freq2 = Integer.parseInt(_resultViewLabelProvider.getColumnText(e2,
                                    ResultHistoryHelper.FREQ_INDEX));
                            return (freq1 > freq2 ? 1 : -1) * FREQ_ORDER;
                        }
                        catch (Exception e)
                        {
                            return 0;
                        }
                    }
                };
            case ResultHistoryHelper.ACTION_INDEX:
                return new ViewerSorter()
                {
                    public int compare(Viewer viewer, Object e1, Object e2)
                    {
                        if (hasSubResult(e1, e2))
                        {
                            return 0;
                        }
                        return super.compare(viewer, _resultViewLabelProvider.getColumnText(e1,
                                ResultHistoryHelper.ACTION_INDEX), _resultViewLabelProvider.getConfigurableColumnText(
                                e2, ResultHistoryHelper.ACTION_INDEX))
                                * ACTION_ORDER;
                    }
                };
            case ResultHistoryHelper.CONSUMER_INDEX:
                return new ViewerSorter()
                {
                    public int compare(Viewer viewer, Object e1, Object e2)
                    {
                        if (hasSubResult(e1, e2))
                        {
                            return 0;
                        }
                        return super.compare(viewer, _resultViewLabelProvider.getColumnText(e1,
                                ResultHistoryHelper.CONSUMER_INDEX), _resultViewLabelProvider
                                .getConfigurableColumnText(e2, ResultHistoryHelper.CONSUMER_INDEX))
                                * CONSUMER_ORDER;
                    }
                };
            case ResultHistoryHelper.PROFILE_INDEX:
                return new ViewerSorter()
                {
                    public int compare(Viewer viewer, Object e1, Object e2)
                    {
                        if (hasSubResult(e1, e2))
                        {
                            return 0;
                        }
                        return super.compare(viewer, _resultViewLabelProvider.getColumnText(e1,
                                ResultHistoryHelper.PROFILE_INDEX), _resultViewLabelProvider.getConfigurableColumnText(
                                e2, ResultHistoryHelper.PROFILE_INDEX))
                                * PROFILE_ORDER;
                    }
                };
            default:
                return null;
        }
    }
    
    /**
     * Returns all visible result instances
     * @return all visible result instances
     */
    public IResultInstance[] getVisibleInstances()
    {
        TreeItem[] items = _resultsTreeTable.getTree().getItems();
        IResultInstance[] visibleItems = new IResultInstance[items.length];
        for (int i = 0; i < items.length; i++)
        {
            visibleItems[i] = (IResultInstance) items[i].getData();
        }
        return visibleItems;
    }
    
    /**
     * Checks if the given result instance is filtered out or not
     * @param instance a result instance
     * @return <code>true</code> if the given instance is filtered out, <code>false</code> otherwise
     */
    public boolean isFilteredOut(IResultInstance instance)
    {
        // Get the filtered results first
        ItemsLimitationTreeViewer viewer = (ItemsLimitationTreeViewer)_resultsTreeTable;
        Object[] instances = viewer.getFilteredChildren(ResultsViewUIPlugin.getResultManager().getAllResults());
        int count = instances.length;
        for(int i=count - 1;i>-1;i--)
        {
            if(instances[i] == instance)
            {
                return false;
            }
        }
        return true;
    }
    
    private void prepareToQuery()
    {
        Display.getCurrent().timerExec(400, _searchHistory);
    }
    
    /**
     * Re-displays the result history table (Configured columns are changed)
     *
     */
    public void reDisplayResults()
    {
        for(int i=0;i<_columnsWidth.length;i++)
        {
            _columnsWidth[i] = 0;
        }
        
        TreeColumn[] columns = _resultsTreeTable.getTree().getColumns();
        for (int i = 0; i < columns.length; i++)
        {
            // Store the column width
            int configurableColumnIndex = ResultHistoryHelper.getConfigurableColumnIndex(columns[i].getText());
            if(configurableColumnIndex > -1)
            {
                _columnsWidth[configurableColumnIndex] = columns[i].getWidth();
            }
            columns[i].dispose();
        }
        
        createColumns();
        _resultsTreeTable.refresh();
    }
    
    public void refresh()
    {
        _resultsTreeTable.refresh();
    }
    
    public void dispose()
    {
        if ( _filterFont != null && !_filterFont.isDisposed() )
        {
            _filterFont.dispose();
        }
    }
    
    /**
     * Saves the current column width status
     * 
     * @param memento the memento
     */
    public void saveState(IMemento memento)
    {
        TreeColumn[] columns = _resultsTreeTable.getTree().getColumns();
        
        // Clear the width first
        for(int i=0;i<_columnsWidth.length;i++)
        {
            _columnsWidth[i] = 0;
        }
        
        // Store the current width
        for (int i = 0; i < columns.length; i++)
        {
            int configurableColumnIndex = ResultHistoryHelper.getConfigurableColumnIndex(i, _resultsViewControl.getUsePreferences());
            if(configurableColumnIndex == -1)
            {
                // Should not happen
                continue;
            }
            _columnsWidth[configurableColumnIndex] = columns[i].getWidth();
        }
        
        for (int i = 0; i < ResultHistoryHelper.COLUMN_NUMBER; i++)
        {
            memento.putInteger(TAG_COLUMN_WIDTH + i, _columnsWidth[i]);
        }
    }
    
    /**
     * Searches the result instances using current input expression
     *
     */
    public void search()
    {
        BusyIndicator.showWhile(_resultsTreeTable.getTree().getDisplay(), new Runnable()
        {
            public void run()
            {
                if (!_firstClick)
                {
                    Display.getCurrent().syncExec(_searchHistory);
                }
                else
                {
                    _resultsTreeTable.setInput(ResultsViewUIPlugin.getResultManager().getAllResults());
                }
            }
        });
    }
}

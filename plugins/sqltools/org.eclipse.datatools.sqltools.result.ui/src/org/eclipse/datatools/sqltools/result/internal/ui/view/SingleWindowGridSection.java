/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.ui.view;

import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.sqltools.result.IResultSetObject;
import org.eclipse.datatools.sqltools.result.IResultSetRow;
import org.eclipse.datatools.sqltools.result.ResultSetObject;
import org.eclipse.datatools.sqltools.result.XMLResultSetObject;
import org.eclipse.datatools.sqltools.result.internal.preference.ResultSetViewerPreferencePage;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.ui.PreferenceConstants;
import org.eclipse.datatools.sqltools.result.internal.ui.export.actions.ExportAllResultSetsAction;
import org.eclipse.datatools.sqltools.result.internal.ui.export.actions.ExportResultSetAction;
import org.eclipse.datatools.sqltools.result.internal.ui.export.actions.PrintResultSetAction;
import org.eclipse.datatools.sqltools.result.internal.ui.export.actions.SaveAllResultSetsAction;
import org.eclipse.datatools.sqltools.result.internal.ui.export.actions.SaveResultSetAction;
import org.eclipse.datatools.sqltools.result.internal.ui.utils.PreferenceUtil;
import org.eclipse.datatools.sqltools.result.internal.ui.viewer.ResultSetViewer;
import org.eclipse.datatools.sqltools.result.internal.utils.StatusTextProvider;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.model.ResultItem;
import org.eclipse.datatools.sqltools.result.ui.ExternalResultSetViewerProvider;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.datatools.sqltools.result.ui.view.ResultsViewControl;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

/**
 * @author Dafan Yang
 */
public class SingleWindowGridSection extends SingleWindowModeSection
{
    /* the scrolled composite is used to scroll the content of the composite */
    private ScrolledComposite _scrolledComp;
    private Composite         _comp;
    private Text              _preText;
    private ResultItem        _preItem;
    private Table             _paramTable;

    /**
     * The number of tables in single window mode is limited to prevent from crashing
     */
    private int               _tableNums;
    private int               _currentTableNum;
    private boolean           _endWarnTextCreated = false;

    private MouseListener _mListener = new MouseListener(){

        public void mouseDoubleClick(MouseEvent e)
        {
        }

        public void mouseDown(MouseEvent e)
        {
            if(_scrolledComp != null)
                _scrolledComp.forceFocus();
        }

        public void mouseUp(MouseEvent e)
        {
        }

    };
    
    public SingleWindowGridSection(Composite composite, ResultsViewControl resultsViewControl)
    {
        super(composite, resultsViewControl);
        _parent = composite;
        IPreferenceStore store = ResultsViewUIPlugin.getDefault().getPreferenceStore();
        _tableNums = PreferenceUtil.getInt(store, PreferenceConstants.SQL_RESULTS_VIEW_TABLES_LIMITATION,
                resultsViewControl.getUsePreferences());
        _currentTableNum = 0;
    }

    public SingleWindowGridSection(Composite composite, IResultInstance instance, ResultsViewControl resultsViewControl)
    {
        super(composite, instance, resultsViewControl);
        _parent = composite;
        IPreferenceStore store = ResultsViewUIPlugin.getDefault().getPreferenceStore();
        _tableNums = PreferenceUtil.getInt(store, PreferenceConstants.SQL_RESULTS_VIEW_TABLES_LIMITATION,
                resultsViewControl.getUsePreferences());
        _currentTableNum = 0;
    }

    public void createInitialControl(Composite composite)
    {
        super.createInitialControl(composite);
        // create a scrolled composite in _composite
        _scrolledComp = new ScrolledComposite(_composite, SWT.V_SCROLL | SWT.H_SCROLL);
        _scrolledComp.getVerticalBar().setIncrement(17);
        _scrolledComp.setLayout(new FillLayout());
        _scrolledComp.setExpandHorizontal(true);
        _scrolledComp.setExpandVertical(true);

        GridData gd = new GridData(GridData.FILL_BOTH);
        _scrolledComp.setLayoutData(gd);

        _comp = new Composite(_scrolledComp, SWT.NONE);

        // set the content for this scrolled composite
        _scrolledComp.setContent(_comp);

        GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.numColumns = 1;
        _comp.setLayout(layout);

        composite.layout(true);
    }

    protected void createViewerForResultInstance(IResultInstance instance)
    {
        int count = instance.getItemCount();
        for (int i = 0; i < count; i++)
        {
            ResultItem item = instance.getItem(i);
            createWidgetForItem(_comp, item);
        }
        onInstanceFinished();
        _comp.layout(true);
        _scrolledComp.setMinSize(_comp.computeSize(SWT.DEFAULT, SWT.DEFAULT));
    }

    public void onNewItemAppended(ResultItem item, int index)
    {
        if (_resultInstance == null || _displayedItems.contains(item))
        {
            return;
        }
        _displayedItems.add(item);

        _messageCache.add(item);

        super.onNewItemAppended(item, index);
    }

    private void createWidgetForItem(Composite composite, ResultItem item)
    {
        IPreferenceStore store = ResultsViewUIPlugin.getDefault().getPreferenceStore();
        if (_currentTableNum >= _tableNums)
        {
            if (!_endWarnTextCreated)
            {
                _endWarnTextCreated = true;
                Text warnText = new Text(composite, SWT.READ_ONLY | SWT.MULTI | SWT.WRAP);
                warnText.setText(Messages.SingleWindowGridSection_warning_info);
                _comp.layout(true);
                _scrolledComp.setMinSize(_comp.computeSize(SWT.DEFAULT, SWT.DEFAULT));
            }
            return;
        }

        /**
         * try to display the continous messages in the same text widget
         */
        if (_preItem != null && _preItem.getResultType() != ResultItem.RESULT_SET
                && item.getResultType() != ResultItem.RESULT_SET)
        {
            if (_preText != null)
            {
                if (item.getResultType() == ResultItem.PLAIN_TEXT || item.getResultType() == ResultItem.STATUS_TEXT)
                {
                    _preText.append((String) item.getResultObject());
                }
                else if (item.getResultType() == ResultItem.UPDATE_COUNT)
                {
                    _preText.append(StatusTextProvider
                            .getUpdateCountText(((Integer) item.getResultObject()).intValue()));
                }
                _preItem = item;
                return;
            }
        }

        _preItem = item;
        switch (item.getResultType())
        {
            case ResultItem.PLAIN_TEXT:
            case ResultItem.STATUS_TEXT:
                Text messageText = new Text(composite, SWT.MULTI);

                // save the previous text widget
                _preText = messageText;
                GridData gd = new GridData(GridData.FILL_HORIZONTAL);
                messageText.setLayoutData(gd);
                messageText.setText((String) item.getResultObject());
                messageText.setEditable(false);
                messageText.addMouseListener(_mListener);
                break;
            case ResultItem.UPDATE_COUNT:
                if (_showRowCountMsg)
                {
                    Text updateCountText = new Text(composite, SWT.MULTI);

                    // save the previous text widget
                    _preText = updateCountText;
                    gd = new GridData(GridData.FILL_HORIZONTAL);
                    updateCountText.setLayoutData(gd);
                    updateCountText.setText(StatusTextProvider.getUpdateCountText(((Integer) item.getResultObject())
                            .intValue()));
                    updateCountText.setEditable(false);
                    updateCountText.addMouseListener(_mListener);
                }
                break;
            case ResultItem.RESULT_SET:
                _currentTableNum++;
                IResultSetObject result = (IResultSetObject) item.getResultObject();
                if (!result.isAllResultLoaded())
                {
                    _isResultHid = true;
                }
                // treat result set object and xml result
                if (result instanceof XMLResultSetObject)
                {
                    createTextforResultSet(composite, result);
                }
                if (result instanceof ResultSetObject)
                {
                    // Check for external viewer extensions
                    String viewerName = store.getString(PreferenceConstants.RESULT_SET_VIEWER_VIEWERNAME);
                    if (ResultSetViewerPreferencePage.DEFAULT_VIEWER.equalsIgnoreCase(viewerName))
                    {
                        createTableViewerForResultSet(composite, result);
                    }
                    else
                    {
                        createExternalTableViewerForResultSet(composite, result, viewerName);
                    }
                }
                else
                {
                    // to be extended
                }
                break;
            default:
                break;
        }
    }

    public Composite getControl()
    {
        return _composite;
    }

    /**
     * Create result set viewer to display result set object.
     * 
     * @param composite the parent composite
     * @param result the reusult set object
     */
    private void createTableViewerForResultSet(Composite composite, IResultSetObject result)
    {
        ResultSetViewer viewer = new ResultSetViewer(composite, SWT.FULL_SELECTION | SWT.MULTI, _resultInstance,
                result, _displayRowNumber, _resultsViewControl);
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        int height = viewer.getTable().getHeaderHeight();
        int itemHeight = viewer.getTable().getItemHeight();
        int itemCount = viewer.getTable().getItemCount() == 1 ? 2 : viewer.getTable().getItemCount();
        height += (itemCount - 1) * itemHeight;

        gd.heightHint = height;
        viewer.getTable().setLayoutData(gd);
        viewer.getTable().addMouseListener(_mListener);
    }

    /**
     * Creates the external viewer to display the result set object
     * 
     * @param composite the parent composite
     * @param result the result set object
     * @param viewerName the external viewer name
     */
    private void createExternalTableViewerForResultSet(Composite composite, IResultSetObject result, String viewerName)
    {
        ResultSetViewerRegistryReader reader = ResultSetViewerRegistryReader.getInstance();
        ExternalResultSetViewerProvider provider = reader.getResultSetViewerExecutable(viewerName);
        provider.configureViewer(composite, _resultInstance, result, _displayRowNumber, _resultsViewControl);

        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        int height = provider.getViewer().getTable().getHeaderHeight();
        int itemHeight = provider.getViewer().getTable().getItemHeight();
        int itemCount = provider.getViewer().getTable().getItemCount() == 1 ? 2 : provider.getViewer().getTable()
                .getItemCount();
        height += (itemCount - 1) * itemHeight;

        gd.heightHint = height;
        provider.getViewer().getTable().setLayoutData(gd);
        provider.getViewer().getTable().addMouseListener(_mListener);
    }

    /**
     * Creates text widget to display xml result set object
     * 
     * @param composite the parent composite
     * @param result the xml result set object
     */
    private void createTextforResultSet(Composite composite, IResultSetObject result)
    {
        Text txt = new Text(composite, SWT.MULTI | SWT.READ_ONLY);
        txt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        StringBuffer buf = new StringBuffer();

        Iterator iter = result.getDisplayRecords();
        while (iter != null && iter.hasNext())
        {
            IResultSetRow row = (IResultSetRow) iter.next();
            for (int i = 0; row.getData() != null && i < row.getData().length; i++)
            {
                buf.append(row.getData(i));
            }
        }
        String s = buf.toString();
        txt.setText(s);

        // Register context menu for save or export result set
        MenuManager mgr = new MenuManager();
        MenuManager saveMgr = new MenuManager(Messages.Save_name);
        saveMgr.add(new SaveResultSetAction(txt.getShell(), result));
        saveMgr.add(new SaveAllResultSetsAction(txt.getShell(), _resultInstance));

        MenuManager exportMgr = new MenuManager(Messages.Export_name);
        exportMgr.add(new ExportResultSetAction(txt.getShell(), result));
        exportMgr.add(new ExportAllResultSetsAction(txt.getShell(), _resultInstance));

        MenuManager printMgr = new MenuManager(Messages.Print_name);
        printMgr.add(new PrintResultSetAction(result, _parent));
        printMgr.add(new PrintResultSetAction(_resultInstance, _parent));

        mgr.add(saveMgr);
        mgr.add(exportMgr);
        mgr.add(printMgr);

        Menu menu = mgr.createContextMenu(txt);
        txt.setMenu(menu);
        txt.addMouseListener(_mListener);
    }

    public void onInstanceFinished()
    {
        super.onInstanceFinished();
        // always display the parameters at the end

        if (_resultInstance.getParameters() == null)
        {
            return;
        }
        // Don't show parameter in single window mode [2007-1-4]
        // Text inout = new Text(_comp, SWT.MULTI | SWT.READ_ONLY);
        // inout.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        // inout.setText(Messages.SingleWindowGridSection_inout_params);
        // List params = getValidParamList(_resultInstance.getParameters());
        // createTableForParameters(_comp);
        // Iterator iter = params.iterator();
        // while(iter.hasNext())
        // {
        // Parameter param = (Parameter)iter.next();
        // TableItem item = new TableItem(_paramTable, SWT.NONE);
        // item.setText(ColumnAlignedResultItem.PARAM_NAME, param.getParamName());
        // item.setText(ColumnAlignedResultItem.PARAM_TYPE, param.getParamType());
        // item.setText(ColumnAlignedResultItem.PARAM_DATA_TYPE, param.getParamDataType());
        // item.setText(ColumnAlignedResultItem.PARAM_VALUE, param.getParamValue());
        // item.setText(ColumnAlignedResultItem.PARAM_VALUE_OUT, param.getParamOutValue());
        // }
        _comp.layout(true);
        _scrolledComp.setMinSize(_comp.computeSize(SWT.DEFAULT, SWT.DEFAULT));
    }

    private void createTableForParameters(Composite comp)
    {
        TableViewer paramViewer = new TableViewer(comp, SWT.V_SCROLL | SWT.FULL_SELECTION);
        _paramTable = paramViewer.getTable();

        _paramTable.setLinesVisible(true);
        _paramTable.setHeaderVisible(true);
        _paramTable.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        TableColumn nameColumn = new TableColumn(_paramTable, SWT.NONE);
        nameColumn.setText(Messages.MultipleTabsGridSection_parameter_name);

        TableColumn typeColumn = new TableColumn(_paramTable, SWT.NONE);
        typeColumn.setText(Messages.MultipleTabsGridSection_parameter_type);

        TableColumn dataTypeColumn = new TableColumn(_paramTable, SWT.NONE);
        dataTypeColumn.setText(Messages.MultipleTabsGridSection_parameter_datatype);

        TableColumn valueColumn = new TableColumn(_paramTable, SWT.NONE);
        valueColumn.setText(Messages.MultipleTabsGridSection_value);

        TableColumn outValueColumn = new TableColumn(_paramTable, SWT.NONE);
        outValueColumn.setText(Messages.MultipleTabsGridSection_value_out);

        int defaultWidth = 0;

        int columnCount = _paramTable.getColumnCount();
        for (int i = 0; i < columnCount; i++)
        {
            TableColumn column = _paramTable.getColumn(i);
            column.pack();
            defaultWidth = defaultWidth + column.getWidth() + _paramTable.getGridLineWidth();
        }

        int moreWidth = comp.getParent().getBounds().width - 2 - defaultWidth;
        if (moreWidth > 0)
        {
            for (int i = 0; i < columnCount; i++)
            {
                TableColumn col = _paramTable.getColumn(i);
                col.setWidth(col.getWidth() + moreWidth / columnCount);
            }
        }
        _paramTable.pack();
    }

    public void onInstanceReseted()
    {
        Control[] controls = _comp.getChildren();
        for (int i = 0; i < controls.length; i++)
        {
            controls[i].dispose();
        }
    }

    protected void outputToViewer(List outputList)
    {
        ResultItem preItem = null;
        List mergedList = getMergedOutputList(outputList);
        for (Iterator iterator = mergedList.iterator(); iterator.hasNext();)
        {
            ResultItem item = (ResultItem) iterator.next();
            createWidgetForItem(_comp, item);
        }

        if (!_endWarnTextCreated)
        {
            _comp.layout(true);
            _scrolledComp.setMinSize(_comp.computeSize(SWT.DEFAULT, SWT.DEFAULT));
        }
    }
}

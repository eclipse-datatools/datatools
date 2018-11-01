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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.datatools.sqltools.result.IResultSetObject;
import org.eclipse.datatools.sqltools.result.Parameter;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.ui.export.actions.ExportAllResultSetsAction;
import org.eclipse.datatools.sqltools.result.internal.ui.export.actions.ExportResultSetAction;
import org.eclipse.datatools.sqltools.result.internal.ui.export.actions.PrintResultSetAction;
import org.eclipse.datatools.sqltools.result.internal.ui.export.actions.SaveAllResultSetsAction;
import org.eclipse.datatools.sqltools.result.internal.ui.export.actions.SaveResultSetAction;
import org.eclipse.datatools.sqltools.result.internal.ui.utils.UIUtil;
import org.eclipse.datatools.sqltools.result.internal.ui.viewer.TextResultViewer;
import org.eclipse.datatools.sqltools.result.internal.utils.StatusTextProvider;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.model.ResultItem;
import org.eclipse.datatools.sqltools.result.ui.view.ResultsViewControl;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * Displays the SQL result in multiple tabs in text mode
 * 
 * @author Dafan Yang 
 */
public class MultipleTabsTextSection extends MultipleTabsModeSection
{
    private Text                  _statusView;
    private Text                  _messageView;
    private TextResultViewer      _textViewer;
    private TextResultViewer      _paramsViewer;

    private Map                   _viewerMap = new HashMap();
    
    public MultipleTabsTextSection(Composite composite, ResultsViewControl resultsViewControl)
    {
        super(composite, resultsViewControl);
        addTabSelectionListener();
    }

    public MultipleTabsTextSection(Composite composite, IResultInstance instance, ResultsViewControl resultsViewControl)
    {
        super(composite, instance, resultsViewControl);
        addTabSelectionListener();
    }
    
    private void addTabSelectionListener()
    {
        _tabFolder.addSelectionListener(new SelectionAdapter(){
            
            public void widgetSelected(SelectionEvent e)
            {
                _textViewer = (TextResultViewer)_viewerMap.get(e.item);
            }
        });
    }

    protected Control createStatusItem(CTabFolder ctf)
    {
        _statusView = new Text(ctf, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
        _statusView.setEditable(false);
        return _statusView;
    }

    protected Control createMessageItem(CTabFolder ctf)
    {
        _messageView = new Text(ctf, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
        _messageView.setEditable(false);
        return _messageView;
    }

    protected void showStatusDetail(IResultInstance instance)
    {
        if (instance == null)
        {
            _statusView.setText(""); //$NON-NLS-1$
        }
        else
        {
            String text = StatusTextProvider.getStatusText(instance);
            _statusView.setText(text);
            _statusView.setEditable(false);
        }
    }
    
    protected void showMessageDetail(IResultInstance instance)
    {
        if(_splitMessages)
        {
            // will display messages in multiple tabs
            return; 
        }
        if (instance == null)
        {
            _messageView.setText(""); //$NON-NLS-1$
        }
        else
        {
            int count = instance.getItemCount();
            StringBuffer sb = new StringBuffer(""); //$NON-NLS-1$
            for (int i = 0; i < count; i++)
            {
                ResultItem item = instance.getItem(i);
                if(item.getResultType() == ResultItem.PLAIN_TEXT)
                {
                    sb.append(item.getResultObject());
                }
            }
            _messageView.setText(sb.toString());
        }
    }

    protected void showParameterDetail(IResultInstance instance)
    {
        if(instance.getParameters() != null)
        {
            Iterator iter = instance.getParameters().iterator();
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
    }
    
    protected void createViewerForItem(Composite composite, ResultItem item)
    {
        if (_textViewer != null)
        {
            // reset the previous text viewer's undo manager such that user can not redo our output
            _textViewer.resetUndoMgr();
        }

        ArrayList items = new ArrayList();
        if (item.getResultType() == ResultItem.RESULT_SET)
        {
            MenuManager saveMgr = new MenuManager(Messages.Save_name); 
            saveMgr.add(new SaveResultSetAction(_statusView.getShell(), (IResultSetObject) item.getResultObject()));
            saveMgr.add(new SaveAllResultSetsAction(_statusView.getShell(), _resultInstance));
            items.add(saveMgr);
            
            MenuManager exportMgr = new MenuManager(Messages.Export_name); 
            exportMgr.add(new ExportResultSetAction(_statusView.getShell(), (IResultSetObject) item.getResultObject()));
            exportMgr.add(new ExportAllResultSetsAction(_statusView.getShell(), _resultInstance));
            items.add(exportMgr);
            
            MenuManager printMgr = new MenuManager(Messages.Print_name); 
            printMgr.add(new PrintResultSetAction((IResultSetObject) item.getResultObject(), _tabFolder));
            printMgr.add(new PrintResultSetAction(_resultInstance, _tabFolder));
            items.add(printMgr);
        }
       
        _textViewer = new TextResultViewer(composite, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL, items);

        GridData gd = new GridData(GridData.FILL_BOTH);
        _textViewer.getViewer().getTextWidget().setLayoutData(gd);

        // if current item is result set, we should display row count, displayed row count message for user
        if (item.getResultType() == ResultItem.RESULT_SET)
        {
            IResultSetObject result = (IResultSetObject) item.getResultObject();
            Label label = new Label(composite, SWT.NONE);

            GridData labelGridData = new GridData();
            labelGridData.grabExcessHorizontalSpace = true;
            labelGridData.grabExcessVerticalSpace = false;
            labelGridData.heightHint =  UIUtil.convertHeightInCharsToPixels(1, _parent);
            label.setLayoutData(labelGridData);

            int totalRowCount = result.getTotalRowCount();
            int rowCount = result.getRowCount();
            if (totalRowCount == rowCount)
            {
                label.setText(NLS.bind(Messages.ResultSection_resultset_tooltip1, new Object[]
				{
				    String.valueOf(rowCount)
				})); //$NON-NLS-1$
            }
            else
            {
                label.setText(NLS.bind(Messages.ResultSection_resultset_tooltip, new Object[]
				{
				    String.valueOf(totalRowCount), String.valueOf(rowCount)
				}));
            }
        }
        IDocument document = new Document();
        document.set(ColumnAlignedResultItem.getResultItemDisplayString(item, _nullValue, _showHeadings, false));
        _textViewer.getViewer().setDocument(document);
        
        _viewerMap.put(_tabFolder.getSelection(), _textViewer);
    }

    protected void appendStatusView(String text)
    {
        _statusView.append(text);
    }

    protected void appendMessageView(String text)
    {
        _messageView.append(text);
    }
    
    public void onInstanceReseted()
    {
        super.onInstanceReseted();
        _statusView.setText(""); //$NON-NLS-1$
        _viewerMap.clear();
    }
    
    /**
     * Create the tab/controls and display the parameters on it
     * 
     * @param params the list of <code>Parameter</code>
     */
    protected void appendAndShowParameters(List params)
    {
        if(_paramsItem == null)
        {
            _paramsItem = new CTabItem(_tabFolder, SWT.NONE, _numberStaticTab);
            _paramsItem.setText(Messages.MultipleTabsTextSection_parameter); 
            _numberStaticTab ++;
            _isParamShown = true;
            _paramTabNumber = _numberStaticTab - 1;
            _paramsViewer = new TextResultViewer(_tabFolder, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL, null);
            GridData gd = new GridData(GridData.FILL_BOTH);
            _paramsViewer.getViewer().getTextWidget().setLayoutData(gd);
            _paramsItem.setControl(_paramsViewer.getViewer().getControl());
            
            _viewerMap.put(_paramsItem, _paramsViewer);
        }
        // will overwrite the orginal parameters
        _paramsViewer.getViewer().setDocument(
                new Document(ColumnAlignedResultItem.getParametersDsipalyStr(params, _nullValue)));
        ;
    }

    public TextResultViewer getTextViewer()
    {
        return _textViewer;
    }
}

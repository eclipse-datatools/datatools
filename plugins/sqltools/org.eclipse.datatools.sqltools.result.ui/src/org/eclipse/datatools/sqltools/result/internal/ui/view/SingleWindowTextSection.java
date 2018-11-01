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
import java.util.StringTokenizer;

import org.eclipse.datatools.sqltools.result.IResultSetObject;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.ui.PreferenceConstants;
import org.eclipse.datatools.sqltools.result.internal.ui.export.actions.ExportAllResultSetsAction;
import org.eclipse.datatools.sqltools.result.internal.ui.export.actions.PrintResultSetAction;
import org.eclipse.datatools.sqltools.result.internal.ui.export.actions.SaveAllResultSetsAction;
import org.eclipse.datatools.sqltools.result.internal.ui.utils.PreferenceUtil;
import org.eclipse.datatools.sqltools.result.internal.ui.viewer.TextResultViewer;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.model.ResultItem;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.datatools.sqltools.result.ui.view.ResultsViewControl;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ST;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

/**
 * This UI component displays the execution result on one tab in text mode(using text widgets to display result sets).
 * 
 * @author Dafan Yang
 */
public class SingleWindowTextSection extends SingleWindowModeSection
{
    public static String _LINESEPARATOR = System.getProperty("line.separator");
    
    private TextResultViewer _textViewer;
    // We use this flag to decide whether to append the last sub ResultIntance's display string to _textViewer in the case of group execution.
    private boolean _isLastSubResultAppended = true;

    public SingleWindowTextSection(Composite composite, ResultsViewControl resultsViewControl)
    {
        super(composite, resultsViewControl);
    }

    public SingleWindowTextSection(Composite composite, IResultInstance instance, ResultsViewControl resultsViewControl)
    {
        super(composite, instance, resultsViewControl);
    }

    /**
     * create viewer for the result instance, and set the text
     */
    protected void createViewerForResultInstance(IResultInstance instance)
    {
        _textViewer.getViewer().getTextWidget().dispose();

        //add actions to the context menu of text widget
        ArrayList actions = new ArrayList();
        if (_resultInstance != null)
        {
            Action saveAll = new SaveAllResultSetsAction(_composite.getShell(), _resultInstance);
            saveAll.setText(Messages.SaveAllResultSetAction_Title); 
            actions.add(saveAll);
            
            Action exportAll = new ExportAllResultSetsAction(_composite.getShell(), _resultInstance);
            exportAll.setText(Messages.ExportAllResultSetAction_Title); 
            actions.add(exportAll);
            
            actions.add(new Separator());
            
            Action printAll = new PrintResultSetAction(_resultInstance, _composite);
            printAll.setText(Messages.PrintAllResultSetAction_Title); 
            actions.add(printAll);
        }
        
        _textViewer = new TextResultViewer(_composite, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL, actions);
        GridData gd = new GridData(GridData.FILL_BOTH);
        _textViewer.getViewer().getTextWidget().setLayoutData(gd);
        _textViewer.getViewer().getTextWidget().setEditable(true);
        _textViewer.getViewer().getTextWidget().setEnabled(true);
        _textViewer.getViewer().setDocument(createDocumentForResultInstance(instance));
        
        onInstanceFinished();
        _composite.layout(true);
    }

    private IDocument createDocumentForResultInstance(IResultInstance instance)
    {
        for (int i = 0; i < instance.getItemCount(); i++)
        {
            ResultItem item = instance.getItem(i);

            // check if some rows are cached
            if (item.getResultType() == ResultItem.RESULT_SET)
            {
                IResultSetObject result = (IResultSetObject) item.getResultObject();
                if (!result.isAllResultLoaded())
                {
                    _isResultHid = true;
                }
            }
        }
        
        StringBuffer sb = new StringBuffer(""); //$NON-NLS-1$
        if (instance.getSubResults().size() > 0)
        {
            // group execution
            Iterator it = instance.getSubResults().iterator();
            while (it.hasNext())
            {
                IResultInstance subInstance = (IResultInstance)it.next();
                if (subInstance != null)
                {
                    sb.append(getSubResultInstanceText(subInstance));
                }
            }
        }
        else
        {
            // single execution
            for (int i = 0; i < instance.getItemCount(); i++)
            {
                ResultItem item = instance.getItem(i);
                sb.append(ColumnAlignedResultItem.getResultItemDisplayString(item, _nullValue, _showHeadings,
                        _showRowCountMsg));
            }
        }
        
        IDocument document = new Document();
        document.set(sb.toString());
        return document;
    }
    
    /**
     * Get the output text for the input ResultIntance object
     * @param instance  - the input ResultIntance object
     * @return  the output text, e.g.
     *          1> select * from tbl
     *          2> go
     *           id          name
     *           ----------- --------------------------------
     *           1           anonymous           
     *          (1 row affected)
     */
    private String getSubResultInstanceText(IResultInstance instance)
    {
        StringBuffer sb = new StringBuffer(""); //$NON-NLS-1$
        sb.append(addLinePrefix(instance.getOperationCommand().getDisplayString()));
        sb.append(ColumnAlignedResultItem.getResultInstanceDispString(instance, 
        		PreferenceUtil.getString(
        				ResultsViewUIPlugin.getDefault().getPreferenceStore(), 
        				PreferenceConstants.SQL_RESULTS_VIEW_NULL_STRING,
        				_resultsViewControl.getUsePreferences())));
        return sb.toString();
    }

    private String addLinePrefix(String sql)
    {
        StringBuffer sb = new StringBuffer(""); //$NON-NLS-1$
        StringTokenizer st = new StringTokenizer(sql, _LINESEPARATOR);
        int lineNumber = 1;
        while (st.hasMoreTokens())
        {
            sb.append((lineNumber++) + "> "); //$NON-NLS-1$
            sb.append(st.nextToken()).append(_LINESEPARATOR);
        }
        sb.append(lineNumber + "> go").append(_LINESEPARATOR); //$NON-NLS-1$
        return sb.toString();
    }

    public void onNewItemAppended(ResultItem item, int index)
    {
        if (_resultInstance == null || _displayedItems.contains(item))
        {
            return;
        }
        _displayedItems.add(item);

        if (item.getResultType() == ResultItem.RESULT_SET)
        {
            IResultSetObject result = (IResultSetObject) item.getResultObject();
            if (!result.isAllResultLoaded())
            {
                _isResultHid = true;
            }
        }
        
        StringBuffer sb = new StringBuffer(""); //$NON-NLS-1$
        if (_resultInstance.isMayHaveSubResults())
        {
            // append the preceding sub ResultIntance's display string to _textViewer 
            if(index > 0)
            {
                IResultInstance preSubInstance = (IResultInstance) _resultInstance.getSubResults().get(index - 1);
                if(preSubInstance != null)
                {
                    sb.append(ColumnAlignedResultItem.getResultInstanceDispString(preSubInstance,
                    		PreferenceUtil.getString(
                    				ResultsViewUIPlugin.getDefault().getPreferenceStore(),
                    				PreferenceConstants.SQL_RESULTS_VIEW_NULL_STRING,
                    				_resultsViewControl.getUsePreferences())));
                }
            }
            sb.append(addLinePrefix(ColumnAlignedResultItem.getResultItemDisplayString(item, _nullValue, _showHeadings,
                    _showRowCountMsg)));
            
            _isLastSubResultAppended = false;
        }
        else
        {
            sb.append(ColumnAlignedResultItem.getResultItemDisplayString(item, _nullValue, _showHeadings,
                    _showRowCountMsg));
        }
        
        _messageCache.add(sb.toString());
        
    	super.onNewItemAppended(item, index);
    }
    
    protected void outputToViewer(List outputList)
    {
    	StringBuffer sb = new StringBuffer();
    	sb.append(_textViewer.getViewer().getTextWidget().getText());
    	for (Iterator iterator = outputList.iterator(); iterator.hasNext();) {
			String message = (String) iterator.next();
			sb.append(message);
		}
    	
    	_textViewer.getViewer().getTextWidget().setVisible(false);
        _textViewer.getViewer().getTextWidget().setText(sb.toString());
        _textViewer.getViewer().getTextWidget().invokeAction(ST.TEXT_END);
        _textViewer.getViewer().getTextWidget().setVisible(true);
        
        //should reset the undo manager when writting result in the text widget
        _textViewer.resetUndoMgr();
    }

    public void createInitialControl(Composite composite)
    {
        super.createInitialControl(composite);

        _textViewer = new TextResultViewer(_composite, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL, null);

        GridData gd = new GridData(GridData.FILL_BOTH);
        _textViewer.getViewer().getTextWidget().setLayoutData(gd);
        _textViewer.getViewer().setDocument(new Document("")); //$NON-NLS-1$

        
        //set the result widget to be enabled when it is initialized
        _textViewer.getViewer().getTextWidget().setEditable(false);
        _textViewer.getViewer().getTextWidget().setEnabled(false);
    }

    public void onInstanceFinished()
    {
		super.onInstanceFinished();
		
        if (_resultInstance.getSubResults().size() > 0 && !_isLastSubResultAppended)
        {
            // append the last sub ResultIntance's display string to _textViewer
            StringBuffer sb = new StringBuffer(""); //$NON-NLS-1$
            sb.append(_textViewer.getViewer().getTextWidget().getText());
            int indexOfLastSubResult = _resultInstance.getSubResults().size() - 1;
            IResultInstance lastSubInstance = (IResultInstance) _resultInstance.getSubResults()
                    .get(indexOfLastSubResult);
            if (lastSubInstance != null)
            {
                sb.append(ColumnAlignedResultItem.getResultInstanceDispString(lastSubInstance, 
                		PreferenceUtil.getString(
                				ResultsViewUIPlugin.getDefault().getPreferenceStore(),
                				PreferenceConstants.SQL_RESULTS_VIEW_NULL_STRING,
                				_resultsViewControl.getUsePreferences())));
            }

            _textViewer.getViewer().getTextWidget().setVisible(false);
            _textViewer.getViewer().getTextWidget().setText(sb.toString());
            _textViewer.getViewer().getTextWidget().invokeAction(ST.TEXT_END);
            _textViewer.getViewer().getTextWidget().setVisible(true);

            // should reset the undo manager when writting result in the text widget
            _textViewer.resetUndoMgr();
            
            _isLastSubResultAppended = true;
        }
        
        if(_resultInstance.getParameters() == null)
        {
            return;
        }
//      Don't show parameter in single window mode [2007-1-4]         
//        StringBuffer sb = new StringBuffer(_textViewer.getViewer().getTextWidget().getText());             
//        sb.append(Messages.SingleWindowTextSection_inout_params).append(ColumnAlignedResultItem.getLineSeparator());       
//        sb.append(ColumnAlignedResultItem.getParametersDsipalyStr(getValidParamList(_resultInstance.getParameters()),
//                _nullValue));
//        _textViewer.getViewer().setDocument(new Document(sb.toString()));
    }

    public void onInstanceReseted()
    {
        _textViewer.getViewer().getTextWidget().setText(""); //$NON-NLS-1$
    }

    public TextResultViewer getTextViewer()
    {
        return _textViewer;
    }
}

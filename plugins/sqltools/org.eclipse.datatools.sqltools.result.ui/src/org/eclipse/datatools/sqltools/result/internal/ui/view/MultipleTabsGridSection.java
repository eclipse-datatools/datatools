/*******************************************************************************
 * Copyright (c) 2005, 2010 Sybase, Inc. and others
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
import org.eclipse.datatools.sqltools.result.IResultSetRow;
import org.eclipse.datatools.sqltools.result.Parameter;
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
import org.eclipse.datatools.sqltools.result.internal.ui.utils.UIUtil;
import org.eclipse.datatools.sqltools.result.internal.ui.viewer.ResultSetViewer;
import org.eclipse.datatools.sqltools.result.internal.utils.StatusTextProvider;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.model.ResultItem;
import org.eclipse.datatools.sqltools.result.ui.ExternalResultSetViewerProvider;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.datatools.sqltools.result.ui.view.ParameterViewerProvider;
import org.eclipse.datatools.sqltools.result.ui.view.ResultsViewControl;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;

/**
 * This UI component displays the execution result on multiple tabs in grid mode(using tables to display result sets).
 * 
 * @author Dafan Yang
 *  
 */
public class MultipleTabsGridSection extends MultipleTabsModeSection
{
    private Text             _statusView;
    private Text             _messageView;
    
    private ParameterViewerProvider provider;
    
    public MultipleTabsGridSection(Composite composite, ResultsViewControl resultsViewControl)
    {
        super(composite, resultsViewControl);
    }

    public MultipleTabsGridSection(Composite composite, IResultInstance instance, ResultsViewControl resultsViewControl)
    {
        super(composite, instance, resultsViewControl);
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
    
    public void showStatusDetail(IResultInstance instance)
    {
        if (instance == null)
        {
            _statusView.setText(""); //$NON-NLS-1$
        }
        else
        {
            String text = StatusTextProvider.getStatusText(instance);
            _statusView.setText(text);
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
	List paramList = instance.getParameters();
        if(paramList == null || paramList.size() == 0)
        {
            return;
        }
        Iterator iter = paramList.iterator();
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

    protected void createViewerForItem(Composite composite, ResultItem item)
    {
        int resultType = item.getResultType();
        IPreferenceStore store = ResultsViewUIPlugin.getDefault().getPreferenceStore();
        switch (resultType)
        {
            case ResultItem.PLAIN_TEXT:
                Text message = new Text(composite, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
                message.setLayoutData(new GridData(GridData.FILL_BOTH));
                message.setEditable(false);
                message.setText((String) item.getResultObject());
                break;
            case ResultItem.UPDATE_COUNT:

                break;
            case ResultItem.RESULT_SET:
                IResultSetObject result = (IResultSetObject) item.getResultObject();

                //treat result set object and xml result
                if(result instanceof XMLResultSetObject)
                {
                    createTextforResultSet(composite, result);
                }
                if(result instanceof ResultSetObject)
                {
                	// Check for external viewer extensions
                	String viewerName = 
                		store.getString(PreferenceConstants.RESULT_SET_VIEWER_VIEWERNAME);
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

    protected void appendStatusView(String text)
    {
        _statusView.append(text);
    }

    protected void appendMessageView(String text)
    {
        _messageView.append(text);
    }
    
    /**
     * Creates table viewer for result set object
     * 
     * @param composite the parent composite
     * @param result the result set object
     */
    protected void createTableViewerForResultSet(Composite composite, IResultSetObject result)
    {
        new ResultSetViewer(composite, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL
            | SWT.FULL_SELECTION, _resultInstance, result, _displayRowNumber, _resultsViewControl);

        Label label = new Label(composite, SWT.NONE);

        GridData labelGridData = new GridData();
        labelGridData.grabExcessHorizontalSpace = true;
        labelGridData.grabExcessVerticalSpace = false;
        labelGridData.heightHint = UIUtil.convertHeightInCharsToPixels(1, _parent);
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
			    String.valueOf(totalRowCount), String //$NON-NLS-1$
				                .valueOf(rowCount)
			}));
        }
    }    
    
    /**
     * Creates the external viewer for result set
     * @param composite the parent Composite
     * @param result the implementation of IResultSetObject containing the result
     * @param viewerName the external viewer name
     */
    protected void createExternalTableViewerForResultSet(Composite composite, 
    		IResultSetObject result, String viewerName)
    {
    	ResultSetViewerRegistryReader reader = ResultSetViewerRegistryReader.getInstance();
    	ExternalResultSetViewerProvider provider = 
    		reader.getResultSetViewerExecutable(viewerName);    	
    	provider.configureViewer(composite, _resultInstance, result, _displayRowNumber, _resultsViewControl);
    		
    	Label label = new Label(composite, SWT.NONE);
        GridData labelGridData = new GridData();
        labelGridData.grabExcessHorizontalSpace = true;
        labelGridData.grabExcessVerticalSpace = false;
        labelGridData.heightHint = UIUtil.convertHeightInCharsToPixels(1, _parent);
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
    		    String.valueOf(totalRowCount), String //$NON-NLS-1$
    	              .valueOf(rowCount)
    		}));
        }
    }   

    protected void createTextforResultSet(Composite composite, IResultSetObject result)
    {
        Text txt = new Text(composite, SWT.MULTI | SWT.READ_ONLY | SWT.V_SCROLL | SWT.H_SCROLL);
        txt.setLayoutData(new GridData(GridData.FILL_BOTH));
        StringBuffer buf = new StringBuffer();

        Iterator iter = result.getDisplayRecords();
        while (iter != null && iter.hasNext())
        {
            IResultSetRow row = (IResultSetRow) iter.next();
            // in fact, there is only one column
            for (int i = 0; i < row.getData().length; i++)
            {
                buf.append(row.getData(i));
            }
        }
        String s = buf.toString();
        txt.setText(s);        

        //Register context menu for save or export result set
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
    }

    public void onInstanceReseted()
    {
        super.onInstanceReseted();
        _statusView.setText(""); //$NON-NLS-1$
    }
    
    /**
     * This method creates Viewer for Parameters depends on the preferences set.
     * @params params 
     */
     protected void appendAndShowParameters(List params) {
         if (_paramsItem == null) {
             _paramsItem = new CTabItem(_tabFolder, SWT.NONE, _numberStaticTab);
             _paramsItem.setText(Messages.MultipleTabsGridSection_parameter);
             _numberStaticTab++;
             _isParamShown = true;
             _paramTabNumber = _numberStaticTab - 1;
             IPreferenceStore store = ResultsViewUIPlugin.getDefault()
             .getPreferenceStore();
             String viewerName = store
             .getString(PreferenceConstants.PARAMETER_VIEWER_VIEWERNAME);
             if (ResultSetViewerPreferencePage.PARAM_DEFAULT_VIEWER.equalsIgnoreCase(viewerName))
             {
                 createTableForParameters(_tabFolder);
             }
             else
             {
                 createExternalViewerForParameter(_tabFolder, viewerName);
             }
             _paramsItem.setControl(provider.getTable());
         }
         // overwrite the original parameters
         provider.fillDataIntoParamsTable(params);
     }

     /**
      * Creates table viewer for parameter
      * 
      * @param composite the parent composite
      */
     private void createTableForParameters(Composite comp) {
         provider = new ParameterViewerProvider();
         provider.configureViewer(comp, SWT.V_SCROLL | SWT.FULL_SELECTION);
     }
         
     /**
      * Creates the external viewer for parameter table viewer
      * @param composite the parent Composite
      * @param viewerName the external viewer name
      */
     private void createExternalViewerForParameter(Composite comp, String viewerName) {
         ParameterViewerRegistryReader reader = ParameterViewerRegistryReader
         .getInstance();
         provider = reader
         .getParameterViewerExecutable(viewerName);
         provider.configureViewer(comp, SWT.V_SCROLL | SWT.FULL_SELECTION);
     }
}

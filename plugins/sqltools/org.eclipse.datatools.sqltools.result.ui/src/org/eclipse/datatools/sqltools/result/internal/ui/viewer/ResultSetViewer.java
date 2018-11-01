/*******************************************************************************
 * Copyright (c) 2005, 2013 Sybase, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *    IBM Corporation - Bug 376356
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.ui.viewer;

import java.sql.Types;
import java.util.Date;

import org.eclipse.datatools.sqltools.result.IResultSetObject;
import org.eclipse.datatools.sqltools.result.IResultSetRow;
import org.eclipse.datatools.sqltools.result.internal.ui.LongDataDialog;
import org.eclipse.datatools.sqltools.result.internal.ui.Messages;
import org.eclipse.datatools.sqltools.result.internal.ui.export.actions.CopyRowsAction;
import org.eclipse.datatools.sqltools.result.internal.ui.export.actions.ExportAllResultSetsAction;
import org.eclipse.datatools.sqltools.result.internal.ui.export.actions.ExportResultSetAction;
import org.eclipse.datatools.sqltools.result.internal.ui.export.actions.PrintResultSetAction;
import org.eclipse.datatools.sqltools.result.internal.ui.export.actions.SaveAllResultSetsAction;
import org.eclipse.datatools.sqltools.result.internal.ui.export.actions.SaveResultSetAction;
import org.eclipse.datatools.sqltools.result.internal.ui.utils.UIUtil;
import org.eclipse.datatools.sqltools.result.internal.utils.HexHelper;
import org.eclipse.datatools.sqltools.result.internal.utils.SQLUtil;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.datatools.sqltools.result.ui.view.ResultsViewControl;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Widget;

import com.ibm.icu.text.Collator;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;

/**
 * This is a configurable viewer used to display result set object.
 * 
 * @author Dafan Yang
 *  
 */
public class ResultSetViewer extends TableViewer
{
    private IResultInstance     _instance;
    private IResultSetObject    _result;
    private boolean             _showRowCount;
    private String[]            _columnProperties;
    private Composite           _parent;
    private ITableLabelProvider _labelProvider;
    private ViewerSorter        _currentSorter;
    private ResultsViewControl	_resultsViewControl;
    private MenuManager mgr;
    /* to compute the average widht of each character (including average distance between characters) */
    private static final double FONT_AVER_WIDTH_FACTOR = 1.6;
    /* the minimum width of the row count column */
    private static final int    MIN_WIDTH              = 30;
    
    /* if the lenght of data is bigger than LONG_BOUNDARY, we will use a pop-up dialog to display it */
    public static final int     LONG_BOUNDARY          = 130; 
    
    /**
     * Creates a result set viewer.
     * 
     * @param parent the parent
     * @param style the style
     * @param instance the result instance
     * @param result the result set object
     * @param showRowCount the show row count option
     */
    public ResultSetViewer(Composite parent, int style, IResultInstance instance, IResultSetObject result,
        boolean showRowCount, ResultsViewControl	resultsViewControl)
    {
        super(parent, style);
        _showRowCount = showRowCount;
        _instance = instance;
        _result = result;
        _parent = parent;
        _resultsViewControl = resultsViewControl;
        configTableViewer();
        setInput(_result);
        // set focus to the first row
        getTable().setSelection(0);
    }

    protected void configTableViewer()
    {
        getTable().setHeaderVisible(true);
        getTable().setLinesVisible(true);
        
        getTable().addMouseListener(new MouseAdapter()
        {
            public void mouseDoubleClick(MouseEvent event)
            {
                
                // compute the row id and column id
                Table table = ResultSetViewer.this.getTable();
                Point pt = new Point(event.x, event.y);
                Rectangle clientRect = table.getClientArea();
                int columnCount = table.getColumnCount();
                int maxColumnIndex = columnCount == 0 ? 0 : columnCount - 1;
                int start = table.getTopIndex();
                int end = table.getItemCount();
                int i = 0, j = 0;
                outer:
                for (i = start; i < end; i++)
                {
                    TableItem item = table.getItem(i);
                    for (j = 0; j <= maxColumnIndex; j++)
                    {
                        Rectangle rect = item.getBounds(j);
                        if (rect.y > clientRect.y + clientRect.height)
                        {
                            return;
                        }
                        if (rect.contains(pt))
                        {
                            break outer;
                        }
                    }
                }
                
                // must get data from the sorted result
                ResultSetViewer.this.setSorter(_currentSorter);
                Integer[] rows = (Integer[])ResultSetViewer.this.getSortedChildren(_result);
                IResultSetRow row = _result.getRowData(rows[i].intValue());
                if(row == null)
                {
                    return;
                }
                if(_showRowCount)
                {
                    j--;
                }
                Object data = row.getData(j);
                if(data == null)
                {
                    return;
                }
                String strValue = ""; //$NON-NLS-1$
                
                //consider the binay type
                if (data instanceof byte[])
                {
                    byte[] os = (byte[]) data;
                    strValue = HexHelper.toHexString(os);
                }
                else
                {
                    strValue = data.toString();
                }
                if(strValue.length() > LONG_BOUNDARY)
                {
                    LongDataDialog dlg = new LongDataDialog(ResultSetViewer.this.getTable().getShell(), _result
                            .getColumnName(j + 1), SQLUtil.convertToString(_result.getColumnSQLType(j + 1)), strValue);
                    dlg.open();
                }
            }
        });
        
        // set the tooltip of this table viewer
        int totalRowCount = _result.getTotalRowCount();
        int rowCount = _result.getRowCount();
        if (totalRowCount == rowCount)
        {
            getTable().setToolTipText(NLS.bind(Messages.ResultSection_resultset_tooltip1, new Object[]
			{
			    String.valueOf(rowCount)
			})); //$NON-NLS-1$
        }
        else
        {
            getTable().setToolTipText(
            		NLS.bind(Messages.ResultSection_resultset_tooltip, new Object[]
				{
				    String.valueOf(totalRowCount), String //$NON-NLS-1$
					                .valueOf(rowCount)
				}));
        }

        GridData tableGridData = new GridData();
        tableGridData.grabExcessHorizontalSpace = true;
        tableGridData.grabExcessVerticalSpace = true;
        tableGridData.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
        tableGridData.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;

        getTable().setLayoutData(tableGridData);

        int columnNum = 0;
        if (_showRowCount)
        {
            columnNum++;
            TableColumn rowCountColumn = new TableColumn(getTable(), SWT.NONE);
            rowCountColumn.setResizable(true);
        }

        columnNum += _result.getColumnCount();
        _columnProperties = new String[columnNum];

        if (_showRowCount)
        {
            _columnProperties[0] = "RowCount"; //$NON-NLS-1$
        }

        // create the table columns for the result set (according to the metadata)
        for (int i = 0; i < _result.getColumnCount(); i++)
        {
            final TableColumn column = new TableColumn(getTable(), SWT.NONE);
            column.setText(_result.getColumnName(i + 1));
            column.setResizable(true);

            final int finalIndex = i + 1;
            column.addSelectionListener(new SelectionAdapter()
            {
                private int _index = finalIndex;
                private int _order = 1;

                public void widgetSelected(SelectionEvent e)
                {
                    ViewerSorterByColumn sorter = new ViewerSorterByColumn(_index, _result, _order, _labelProvider, _showRowCount);
                    _currentSorter = sorter;
                    ResultSetViewer.this.setSorter(sorter);
                    _order *= -1;
                    getTable().setSortColumn(column);
                    getTable().setSortDirection(_order == -1 ? SWT.DOWN : SWT.UP);

                    if(_showRowCount)
                    {
                        Table table = ResultSetViewer.this.getTable();
                        int itemCount = table.getItemCount();
                        for (int j = 0; j < itemCount; j++)
                        {
                            TableItem item = table.getItem(j);
                            item.setText(0, Integer.toString(j + 1));
                        }
                    }
                }
            }
            );

            int index = i;
            if (_showRowCount)
            {
                index = i + 1;
            }
            _columnProperties[index] = _result.getColumnName(i + 1);
        }

        setColumnProperties(_columnProperties);
        setContentProvider(new ResultSetContentProvider());
        _labelProvider = new ResultSetLabelProvider(_result, _showRowCount, _resultsViewControl);
        setLabelProvider(_labelProvider);

        int columnCount = _columnProperties.length;
        int defaultWidth = 0;

        int i = _showRowCount ? 1 : 0;

        if (_showRowCount)
        {
            int chars = Integer.toString(rowCount).length();
            chars = chars < 2 ? 2 : chars;
            int computedWidth = Math.round(Math.round(UIUtil.convertWidthInCharsToPixels(chars, _parent)
                    * FONT_AVER_WIDTH_FACTOR));
            if (computedWidth < MIN_WIDTH)
            {
                computedWidth = MIN_WIDTH;
            }
            getTable().getColumn(0).setWidth(computedWidth);
        }
        for (; i < columnCount; i++)
        {
            TableColumn column = getTable().getColumn(i);
            column.pack();
            defaultWidth = defaultWidth + column.getWidth() + getTable().getGridLineWidth();
        }

        //don't adjust the rowcount column
        i = _showRowCount ? 1 : 0;

        int moreWidth = _parent.getParent().getBounds().width - 2 - defaultWidth;
        if (moreWidth > 0)
        {
            for (; i < columnCount; i++)
            {
                TableColumn col = getTable().getColumn(i);
                int adjustColumnCount = _showRowCount ? (columnCount - 1) : columnCount;
                col.setWidth(col.getWidth() + moreWidth / adjustColumnCount);
            }
        }
        
        //create the context menu on this viewer
        mgr = new MenuManager();
        
        MenuManager saveMgr = new MenuManager(Messages.Save_name); 
        saveMgr.add(new SaveResultSetAction(getTable().getShell(), _result));
        saveMgr.add(new SaveAllResultSetsAction(getTable().getShell(), _instance));
        
        MenuManager exportMgr = new MenuManager(Messages.Export_name); 
        exportMgr.add(new ExportResultSetAction(getTable().getShell(), _result));
        exportMgr.add(new ExportAllResultSetsAction(getTable().getShell(), _instance));
        
        MenuManager printMgr = new MenuManager(Messages.Print_name); 
        printMgr.add(new PrintResultSetAction(_result, _parent));
        printMgr.add(new PrintResultSetAction(_instance, _parent));
        
        final CopyRowsAction copyAction = new CopyRowsAction(Messages.ResultSetViewer_copy_rows, this, _result);
        
        mgr.add(copyAction);
        mgr.add(saveMgr);
        mgr.add(exportMgr);
        mgr.add(printMgr);
        
        mgr.addMenuListener(new IMenuListener()
        {
            public void menuAboutToShow(IMenuManager manager)
            {
                IStructuredSelection ss = (IStructuredSelection) ResultSetViewer.this.getSelection();
                if ( ss == null || ss.toList().size() == 0 )
                {
                    copyAction.setEnabled(false);
                }
                else
                {
                    copyAction.setEnabled(true);
                }
            }
        });
        Menu menu = mgr.createContextMenu(getControl());
        getControl().setMenu(menu);
    }

    protected void doUpdateItem(Widget widget, Object element, boolean fullMap)
    {
        super.doUpdateItem(widget, element, fullMap);
        if (element instanceof Integer)
        {
            if (_showRowCount)
            {
                /**
                 * set the row count column's backgroud color to make it different from other columns
                 */
                TableItem item = getTable().getItem(((Integer) element).intValue());
                item.setBackground(0, ResultsViewUIPlugin.getDefault().getDisabledBakColor());
            }
        }
    }
    
    /**
     * Gets the menuManager for this viewers control
     * @return the menuManger
     */
    public MenuManager getMenuManager()
    {
    	return mgr;
    }
}

/**
 * this class is used to sort the table viewer according to the given column's
 * SQL type and order (ASCEND or DESCEND)
 * 
 */
class ViewerSorterByColumn extends ViewerSorter
{
    private int                 _columnIndex;
    private IResultSetObject     _result;
    private int                 _order;
    private ITableLabelProvider _labelProvider;
    private boolean             _showRowCount;

    public ViewerSorterByColumn(int index, IResultSetObject result, int order, ITableLabelProvider labelProvider, boolean showRowCount)
    {
        super();
        _columnIndex = index;
        _result = result;
        _order = order;
        _labelProvider = labelProvider;
        _showRowCount = showRowCount;
    }

    public int compare(Viewer viewer, Object e1, Object e2)
    {
        int type = _result.getColumnSQLType(_columnIndex);

        int index = _columnIndex;

        //if there is a row count column, we must decrease column index by one to get the right value
        if (!_showRowCount)
        {
            index = _columnIndex - 1;
        }
        if (type == Types.INTEGER || type == Types.DECIMAL || type == Types.DOUBLE || type == Types.FLOAT
                || type == Types.NUMERIC || type == Types.REAL || type == Types.TINYINT || type == Types.SMALLINT
                || type == Types.BIGINT)
        {
            try
            {
                double value1 = Double.parseDouble(_labelProvider.getColumnText(e1, index));
                double value2 = Double.parseDouble(_labelProvider.getColumnText(e2, index));
                if (value1 > value2)
                {
                    return -1 * _order;
                }
                else if (value1 == value2)
                {
                    return 0;
                }
                else
                {
                    return 1 * _order;
                }
            }
            catch (Exception e)
            {
                //should not happen
                return 0;
            }
        }
        else if (type == Types.CHAR || type == Types.VARCHAR || type == Types.LONGVARCHAR)
        {
                  	
        	String str1= _labelProvider.getColumnText(e1, index);
        	String str2= _labelProvider.getColumnText(e2, index);
        	Collator collator = Collator.getInstance();
        	collator.setStrength(Collator.PRIMARY);
        	if(collator.compare(str1, str2)>0){
        		 return -1 * _order;
        	}else if (collator.compare(str1, str2)==0){
        		return 0;
        	}else{
        		 return 1 * _order;
        	}
        }
        else if (type == Types.DATE || type == Types.TIMESTAMP || type == Types.TIME)
        {
            try
            {
                //WARN: if the date output format is configurable, should also change the format here.
                DateFormat dFormatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss"); //$NON-NLS-1$
                Date date1 = dFormatter.parse(_labelProvider.getColumnText(e1, index));
                Date date2 = dFormatter.parse(_labelProvider.getColumnText(e2, index));
                return date1.before(date2) ? 1 * _order : -1 * _order;
            }
            catch (Exception e)
            {
                //should not happen
            }
            return 0;
        }
        else if(type == Types.BIT || type == Types.BOOLEAN)
        {
            try
            {
                Boolean value1 = Boolean.valueOf(_labelProvider.getColumnText(e1, index));
                Boolean value2 = Boolean.valueOf(_labelProvider.getColumnText(e2, index));
                if (value1.booleanValue() && !value2.booleanValue())
                {
                    return 1 * _order;
                }
                else if (!value1.booleanValue() && value2.booleanValue())
                {
                    return -1 * _order;
                }
                else
                {
                    return 0;
                }
            }
            catch (Exception e)
            {
                //should not happen
            }
            return 0;
        }
        //for now, we don't consider the sorting for BINARY, VARBINARY, LONGVARBINARY, CLOB, BLOB
        else
        {
            return 0;
        }
    }
}

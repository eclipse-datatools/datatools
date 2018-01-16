/**
 * Created on 2005-11-3
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.ui.tableviewer;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.sqltools.common.core.tableviewer.IRowData;
import org.eclipse.datatools.sqltools.common.core.tableviewer.ITableData;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;


/**
 * 
 * A TableDataTableCursor provides a way for the user to navigate around a <code>AccessibleTableViewer</code> using
 * the keyboard. It also provides a mechanism for selecting an individual cell in a table.
 * 
 * 
 * 
 * @author lihuang
 * 
 */
public class TableDataTableCursor extends TableCursor implements ISelectionProvider
{

    protected AccessibleTableViewer _tableViewer;

    protected ListenerList<ISelectionChangedListener>  _selectionChangedListeners = new ListenerList<>();

    public TableDataTableCursor(AccessibleTableViewer tableViewer)
    {
        super(tableViewer.getTable(), SWT.NONE);
        this._tableViewer = tableViewer;

        tableViewer.setCursor(this);

        setBackground(Display.getDefault().getSystemColor(SWT.COLOR_LIST_SELECTION));
        setForeground(Display.getDefault().getSystemColor(SWT.COLOR_LIST_SELECTION_TEXT));
    }

    public void registerListeners()
    {
        registerCellEditorsListener();
        registerSelectionListener();
        registerKeyListener();
        registerMouseListener();
        registerTraverseListener();
    }

    protected void registerTraverseListener()
    {
        addTraverseListener(new TraverseListener()
        {
            public void keyTraversed(TraverseEvent e)
            {
                handleTraverse(e);

            }
        }
        );
    }

    protected void registerMouseListener()
    {
        addMouseListener(new MouseListener()
        {
            public void mouseDoubleClick(MouseEvent e)
            {

            }

            public void mouseDown(MouseEvent e)
            {
                if (e.button == 1)
                {
                    edit();

                    CellEditor editor = _tableViewer.getCellEditors()[getColumn()];
                    if (editor instanceof TableCheckBoxCellEditor)
                    {
                        Boolean value = (Boolean) ((TableCheckBoxCellEditor) editor).doGetValue();
                        ((TableCheckBoxCellEditor) editor).doSetValue(new Boolean(!value.booleanValue()));
                    }
                }
            }

            public void mouseUp(MouseEvent e)
            {
            }
        });
    }

    protected void registerKeyListener()
    {
        addKeyListener(new KeyListener()
        {
            public void keyPressed(KeyEvent e)
            {
                if (e.character != '\0' && e.character != SWT.CR && e.character != SWT.LF && e.character != SWT.BS
                && e.character != SWT.DEL && e.character != SWT.TAB && e.character != SWT.ESC
                    && (e.stateMask == 0 || e.stateMask == SWT.SHIFT))
                {

                    edit();

                    CellEditor editor = _tableViewer.getCellEditors()[getColumn()];
                    if (editor instanceof TextCellEditor)
                    {
                        editor.setValue(String.valueOf(e.character));
                        ((Text) editor.getControl()).setSelection(1);
                    }

                }
            }

            public void keyReleased(KeyEvent e)
            {
            }
        }
        );
    }

    protected void registerSelectionListener()
    {
        addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                selectionChanged();

            }

            public void widgetDefaultSelected(SelectionEvent e)
            {
                edit();
            }
        }
        );
    }

    protected void registerCellEditorsListener()
    {
        ICellEditorListener editorListener = new ICellEditorListener()
        {
            public void applyEditorValue()
            {
                setVisible(true);
                redraw();
            }

            public void cancelEditor()
            {
                setVisible(true);
            }

            public void editorValueChanged(boolean oldValidState, boolean newValidState)
            {
            }
        }
        ;

        CellEditor editors[] = _tableViewer.getCellEditors();
        for (int i = 0; i < editors.length; ++i)
        {
            if (editors[i] != null)
            {
                editors[i].addListener(editorListener);
            }
        }
    }

    public void edit()
    {

        ((TableDataCellModifier) _tableViewer.getCellModifier()).setCanModify(true);
        if (((ITableData) _tableViewer.getInput()).isReadonly())
        {
            return;
        }
        //if cell editor is null, that means that column is uneditable.
        if(_tableViewer.getCellEditors()[getColumn()]==null)
        {
            return;
        }

        Object object = null;
        try
        {
            object = getRow().getData();
        }
        catch (NullPointerException e)
        {
            //When table is empty, click table will cause this exception.
            //Just ingore and return.
            return;
        }

        ICellModifier cellModifier = _tableViewer.getCellModifier();
        if( cellModifier == null)
        {
            setVisible(false);//FIXME: need to set it un-visible?
            return;
        }

        if(!((TableDataCellModifier)_tableViewer.getCellModifier()).canChange(object, getColumn()))
        {
            return;
        }
        _tableViewer.editElement(object, getColumn()+1);
        ((TableDataCellModifier) _tableViewer.getCellModifier()).setCanModify(false);
        setVisible(false);



    }

    protected void handleTraverse(TraverseEvent event)
    {
        int row = (getRow() == null) ? 0 : _tableViewer.getTable().indexOf(getRow());
        int col = getColumn() + 1;
        int columnCount = _tableViewer.getTable().getColumnCount() - 1;
        int itemCount = _tableViewer.getTable().getItemCount() - 1;
        TableDataCellModifier cellModifier = (TableDataCellModifier) _tableViewer.getCellModifier();
        boolean canChange = false;
        IRowData rowData;
        ITableData tableData = (ITableData) _tableViewer.getInput();

        switch (event.detail)
        {
            case SWT.TRAVERSE_TAB_PREVIOUS:
                if (col - 1 != 0)
                {
                    col--;

                    //If previous cell is not modified, skip it.
                    while (!canChange)
                    {
                        if (row > tableData.getRows().size() - 1)
                        {
                            break;
                        }
                        rowData = (IRowData) tableData.getRows().get(row);
                        if (col > 0)
                        {
                            canChange = cellModifier.canChange(rowData, col-1);
                        }
                        if (canChange)
                        {
                            break;
                        }
                        if (col > 0)
                        {
                            col--;
                        }
                        else if (row > 0)
                        {
                            row--;
                            col = columnCount;
                        }
                        else
                        {
                            return;
                        }
                    }

                }
                else
                {
                    if (row != 0)
                    {
                        col = columnCount;
                        row--;
                        rowData = (IRowData) tableData.getRows().get(row);
                        while (!canChange)
                        {
                            canChange = cellModifier.canChange(rowData, col-1);
                            if (canChange)
                            {
                                break;
                            }
                            if (col > 0)
                            {
                                col--;
                            }
                            else if (row > 0)
                            {
                                row--;
                                col = columnCount;
                            }
                            else
                            {
                                return;
                            }
                        }
                    }
                    else
                    {
                        return;
                    }
                }
                setSelection(row, col);
                event.doit = false;
                return;
            case SWT.TRAVERSE_TAB_NEXT:
                if (col != columnCount)
                {
                    col++;
                    
                    while(_tableViewer.getTable().getColumns()[col].getWidth() == 0)
                    {
                        col++;
                    }
                    
                    //If next cell is not modified, skip it.
                    while (!canChange)
                    {
                        if (row > tableData.getRows().size() - 1)
                        {
                            break;
                        }
                        rowData = (IRowData) tableData.getRows().get(row);
                        canChange = cellModifier.canChange(rowData, col-1);
                        if (canChange)
                        {
                            break;
                        }
                        if (col < columnCount)
                        {
                            col++;
                        }
                        else if (row < itemCount)
                        {
                            row++;
                            col = 0;
                        }
                        else
                        {
                            return;
                        }
                    }

                }
                else
                {
                    if (row != itemCount)
                    {
                        col = 1;
                        row++;
                        while (!canChange)
                        {
                            if (row > tableData.getRows().size() )
                            {
                                break;
                            }
                            if (row < tableData.getRows().size())
                            {
                                rowData = (IRowData) tableData.getRows().get(row);
                                canChange = cellModifier.canChange(rowData, col-1);
                            }
                            else
                            {
                                canChange = cellModifier.canChange(new Object(), col-1);
                            }
                            if (canChange)
                            {
                                break;
                            }
                            if (col < columnCount)
                            {
                                col++;
                            }
                            else if (row < itemCount)
                            {
                                row++;
                                col = 0;
                            }
                            else
                            {
                                return;
                            }
                        }
                    }
                    else
                    {
                        return;
                    }
                }
                setSelection(row, col);
                event.doit = false;
                return;
        }

    }



    public void addSelectionChangedListener(ISelectionChangedListener listener)
    {
        _selectionChangedListeners.add(listener);
    }

    public ISelection getSelection()
    {
        if (getRow() == null)
        {
            return null;
        }
        return new CellSelection(getRow().getData(), getColumn());
    }

    public void removeSelectionChangedListener(ISelectionChangedListener listener)
    {
        _selectionChangedListeners.remove(listener);
    }

    public void setSelection(ISelection selection)
    {
    }

    public void selectionChanged()
    {
        for (final ISelectionChangedListener l : _selectionChangedListeners)
        {
            Platform.run(new SafeRunnable()
            {
                public void run()
                {
                    l.selectionChanged(new SelectionChangedEvent(TableDataTableCursor.this, getSelection()));
                }
            }
            );
        }
    }


    public void setSelection(int row, int column)
    {
        //Skip to next column when column is 0 for an additional tableColumn is created default.
        if (column == 0)
        {
            column++;
        }
        super.setSelection(row, column);
    }

    public void setSelection(TableItem row, int column)
    {
        //Skip to next column when column is 0 for an additional tableColumn is created default.
        if (column == 0)
        {
            column++;
        }
        super.setSelection(row, column);
    }

    public int getColumn()
    {
        if (super.getColumn() == 0)
        {
            return super.getColumn();
        }
        else
        {
            return super.getColumn()-1;
        }
    }


    public int getTableRow()
    {
        return (getRow() == null) ? 0 : _tableViewer.getTable().indexOf(getRow());
    }
}

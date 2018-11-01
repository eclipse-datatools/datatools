/*******************************************************************************
 * Copyright (c) 2005, 2008 IBM Corporation and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which accompanies this distribution,
 * and is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: IBM Corporation - initial API and implementation
 ******************************************************************************/

package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.filter;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class CommonTableCursor extends TableCursor {
    
	protected TableViewer tableViewer;
	
	public CommonTableCursor(TableViewer tableViewer) {
		super(tableViewer.getTable(), SWT.NONE);
		this.tableViewer = tableViewer;
		setBackground(Display.getDefault().getSystemColor(SWT.COLOR_LIST_SELECTION));
		setForeground(Display.getDefault().getSystemColor(SWT.COLOR_LIST_SELECTION_TEXT));
		
		registerCellEditorsListener();
		registerKeyListener();
		registerMouseListener();
		registerTraverseListener();
		
		addListeners();
	}

    protected void registerTraverseListener() {
		addTraverseListener(new TraverseListener() {
            public void keyTraversed(TraverseEvent e) {
                handleTraverse(e);
                
            }
		});     
    }
	
    protected void registerMouseListener() {
		addMouseListener(new MouseListener() {
            public void mouseDoubleClick(MouseEvent e) {
            }
            public void mouseDown(MouseEvent e) {
                if (e.button==1)
                    edit();
            }
            public void mouseUp(MouseEvent e) {
            }
		});
    }

    protected void registerKeyListener() {
		addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if(e.character!='\0' && e.character!=SWT.CR && e.character!=SWT.LF &&
				        e.character!=SWT.BS && e.character!=SWT.DEL && e.character!=SWT.TAB &&
				        e.character!=SWT.ESC
				    && (e.stateMask==0 || e.stateMask==SWT.SHIFT)){
					
					edit();
					
					CellEditor editor = tableViewer.getCellEditors()[getColumn()];
					if (editor instanceof TextCellEditor) {
						editor.setValue(String.valueOf(e.character));
						((Text)editor.getControl()).setSelection(1);
					}
//					else {
//					    int temp;
//					    temp = 0;
//					}
				}
                else if (e.keyCode == SWT.F2) {
                    edit();
                }
			}
			public void keyReleased(KeyEvent e) {
			}
		});	
    }



    protected void registerCellEditorsListener()
	{
		ICellEditorListener editorListener = new ICellEditorListener() {
            public void applyEditorValue() {
        		setVisible(true);
           		redraw();
            }
            public void cancelEditor() {
                setVisible(true);
            }
            public void editorValueChanged(boolean oldValidState, boolean newValidState) { 
            }
		};
		
		
		CellEditor editors[] = tableViewer.getCellEditors();
		if(editors == null) return;
		for (int i=0; i<editors.length; ++i)
		    if (editors[i]!=null)
		        editors[i].addListener(editorListener);
	}
	
	public void edit() {
	    Object o = getRow().getData();
		tableViewer.editElement(o, getColumn());
	}
	
	protected void handleTraverse(TraverseEvent event)
	{
	    if (event.widget == this) {
            return;
        }
	    int row = (getRow()==null) ? 0 : tableViewer.getTable().indexOf(getRow());
		int col = getColumn();
		
		switch (event.detail) {
		case SWT.TRAVERSE_TAB_PREVIOUS:		    
		    if (col!=0)
		        col--;
		    else { 
		        if (row!=0 ) {
			        col=tableViewer.getTable().getColumnCount()-1;
			        row--;
		        } else {
		            return;
		        }
		    }
		    setSelection(row, col);
			event.doit = false;
			return;
		case SWT.TRAVERSE_TAB_NEXT:
		    if (col!=tableViewer.getTable().getColumnCount()-1)
		        col++;
		    else {
		        if (row!=tableViewer.getTable().getItemCount()-1) {				            
		            col=0;
		            row++;
		        } else {
		            return;
		        }
		    }
		    setSelection(row, col);
		    event.doit = false;
		    return;
		}
		    
	}
	
	protected void addListeners() {
		this.addSelectionListener(new SelectionAdapter() {
            // when the TableEditor is over a cell, select the corresponding row in 
            // the table
            public void widgetSelected(SelectionEvent e) {
                tableViewer.setSelection(new StructuredSelection(tableViewer.getElementAt(tableViewer.getTable().indexOf(getRow()))));
            }
            // when the user hits "ENTER" in the TableCursor, pop up a text editor so that 
            // they can change the text of the cell
            public void widgetDefaultSelected(SelectionEvent e) {
                //TableItem row = cursor.getRow();
                int column = getColumn();
                IStructuredSelection selection= (IStructuredSelection) tableViewer.getSelection();
                if (!selection.isEmpty()) {
                    tableViewer.editElement(selection.getFirstElement(), column);
                }
            }
        });

        // Hide the TableCursor when the user hits the "CTRL" or "SHIFT" key.
        // This alows the user to select multiple items in the table.
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.keyCode == SWT.CTRL
                    || e.keyCode == SWT.SHIFT
                    || (e.stateMask & SWT.CONTROL) != 0
                    || (e.stateMask & SWT.SHIFT) != 0) {
                    setVisible(false);
                }
            }
        });
        // Show the TableCursor when the user releases the "SHIFT" or "CTRL" key.
        // This signals the end of the multiple selection task.
        tableViewer.getTable().addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (e.keyCode == SWT.CONTROL && (e.stateMask & SWT.SHIFT) != 0)
                    return;
                if (e.keyCode == SWT.SHIFT && (e.stateMask & SWT.CONTROL) != 0)
                    return;
                if (e.keyCode != SWT.CONTROL
                    && (e.stateMask & SWT.CONTROL) != 0)
                    return;
                if (e.keyCode != SWT.SHIFT && (e.stateMask & SWT.SHIFT) != 0)
                    return;

                Table table = tableViewer.getTable();
                TableItem[] selection = table.getSelection();
                
                if (table.getItemCount() > 0) {
                    TableItem row = (selection.length == 0) ? table.getItem(table.getTopIndex()) : selection[0];
                    table.showItem(row);
                    setSelection(row, getColumn());
                    setVisible(true);
                    setFocus();
                }
            }
        });
	}

}

/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.data.internal.ui.editor;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class TableDataTableCursor extends TableCursor {
    
	protected TableViewer tableViewer;
	
	public TableDataTableCursor(TableViewer tableViewer) {
		super(tableViewer.getTable(), SWT.NONE);
		this.tableViewer = tableViewer;
		setBackground(Display.getDefault().getSystemColor(SWT.COLOR_LIST_SELECTION));
		setForeground(Display.getDefault().getSystemColor(SWT.COLOR_LIST_SELECTION_TEXT));
		
		registerCellEditorsListener();
		registerKeyListener();
		registerMouseListener();
		registerTraverseListener();
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
		for (int i=0; i<editors.length; ++i)
		    if (editors[i]!=null)
		        editors[i].addListener(editorListener);
	}
	
	public void edit() {
	    TableItem row = getRow();
	    if (row != null) {
	        Object obj = row.getData();
	        ICellModifier cellModifier = tableViewer.getCellModifier();
	        if (cellModifier instanceof TableDataCellModifier) {
	            TableDataCellModifier tableCellModifier = (TableDataCellModifier) cellModifier;
	            tableCellModifier.setCanModify(true);
	            tableViewer.editElement(obj, getColumn());
	            tableCellModifier.setCanModify(false);
	        }
	    }
	}
	
	protected void handleTraverse(TraverseEvent event)
	{
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
		    notifyListeners(SWT.Selection, new Event());
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
		    notifyListeners(SWT.Selection, new Event());
		    event.doit = false;
		    return;
		case SWT.TRAVERSE_RETURN:
			edit();
			event.doit = false;
			return;
		}
		
		    
	}

}
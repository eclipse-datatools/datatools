package org.eclipse.datatools.sqltools.data.internal.ui.editor;

import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class TableDataCellEditor extends TextCellEditor {

    protected ITableDataEditor editor;
    
    public TableDataCellEditor(ITableDataEditor editor, Composite parent)
    {
        super(parent);
        
        this.editor = editor;
        
    }
    
    protected Control createControl(Composite parent) {
        Control c = super.createControl(parent);
		c.addTraverseListener(new TraverseListener() {
            public void keyTraversed(TraverseEvent e) {
                handleTraverse(e);
                
            }
		});
        return c;
    }
        
    protected void handleTraverse(TraverseEvent e)
    {
        if (e.detail == SWT.TRAVERSE_TAB_PREVIOUS || e.detail == SWT.TRAVERSE_TAB_NEXT) {
            fireApplyEditorValue();
            deactivate();
            editor.getCursor().handleTraverse(e);
            editor.getCursor().edit();
        }
    }
}

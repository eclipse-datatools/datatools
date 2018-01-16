/**
 * Created on 2005-11-3
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.ui.tableviewer;

import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * 
 * This TableCellEditor supports tab function.
 * 
 * @author lihuang
 * 
 */
public class TableCellEditor extends TextCellEditor
{

    protected AccessibleTableViewer _accessibleTableViewer;

    /**
     * Creates a new text string cell editor parented under the given table control. The cell editor value is the string
     * itself, which is initially the empty string.
     * 
     * @param accessibleTableViewer AccessibleTableViewer
     */
    public TableCellEditor(AccessibleTableViewer accessibleTableViewer)
    {
        this(accessibleTableViewer, SWT.SINGLE);
    }

    /**
     * Creates a new text string cell editor parented under the given table control. The cell editor value is the string
     * itself, which is initially the empty string.
     * 
     * @param accessibleTableViewer AccessibleTableViewer
     * @param style the style bits
     */
    public TableCellEditor(AccessibleTableViewer accessibleTableViewer, int style)
    {
        super(accessibleTableViewer.getTable(), style);
        _accessibleTableViewer = accessibleTableViewer;
    }





    protected Control createControl(Composite parent)
    {
        Control c = super.createControl(parent);
        c.addTraverseListener(new TraverseListener()
        {
            public void keyTraversed(TraverseEvent e)
            {
                handleTraverse(e);

            }
        }
        );
        return c;
    }

    protected void handleTraverse(TraverseEvent e)
    {
        if (e.detail == SWT.TRAVERSE_TAB_PREVIOUS || e.detail == SWT.TRAVERSE_TAB_NEXT)
        {
            fireApplyEditorValue();
            deactivate();
            _accessibleTableViewer.getCursor().handleTraverse(e);
            _accessibleTableViewer.getCursor().edit();
        }
    }
}

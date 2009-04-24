/**
 * Created on 2005-12-22
 * 
 * Copyright (c) Sybase, Inc. 2004-2006 All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.ui.tableviewer;

import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;


/**
 * 
 * @author lihuang
 *
 */
public class TableCheckBoxCellEditor extends CheckboxCellEditor
{

    /**
     * Default CheckboxCellEditor style
     */
    private static final int defaultStyle = SWT.CHECK;

    private Button _check;
    protected AccessibleTableViewer _accessibleTableViewer;


    public TableCheckBoxCellEditor(AccessibleTableViewer accessibleTableViewer, int style)
    {
        super(accessibleTableViewer.getTable(), style);
        _accessibleTableViewer = accessibleTableViewer;
    }

    public TableCheckBoxCellEditor(AccessibleTableViewer accessibleTableViewer)
    {
        this(accessibleTableViewer, defaultStyle);
    }

    /*
      * create the Button control
      * 
      * @see org.eclipse.jface.viewers.CellEditor#createControl(org.eclipse.swt.widgets.Composite)
      */
    protected Control createControl(Composite parent)
    {
        _check = new Button(parent, getStyle());

        //when focus is lost, should save current value
        _check.addFocusListener(new FocusAdapter()
        {
            public void focusLost(FocusEvent event)
            {
                TableCheckBoxCellEditor.this.focusLost();
            }
        }
        );

        // should act properly when "Enter" or "ESC" key is pressed
        _check.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent e)
            {
                TableCheckBoxCellEditor.this.keyReleaseOccured(e);
            }
        }
        );

        //when "ESC" is pressed, should consume this event to prevent the main dialog from closing
        _check.addTraverseListener(new TraverseListener()
        {
            public void keyTraversed(TraverseEvent event)
            {
                handleTraverse(event);
            }
        }
        );
        return _check;
    }

    public void activate()
    {

    }

    /*
      * (non-Javadoc)
      * 
      * @see org.eclipse.jface.viewers.CellEditor#doGetValue()
      */
    protected Object doGetValue()
    {
        return new Boolean(_check.getSelection());
    }

    /*
      * (non-Javadoc)
      * 
      * @see org.eclipse.jface.viewers.CellEditor#doSetValue(java.lang.Object)
      */
    protected void doSetValue(Object value)
    {
        if (!(value instanceof Boolean))
        {
            return;
        }
        super.doSetValue(value);
        Boolean boolValue = (Boolean) value;
        _check.setSelection(boolValue.booleanValue());
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
        else if (e.detail == SWT.TRAVERSE_ESCAPE)
        {
            e.doit = false;
            fireCancelEditor();
            deactivate();
            // reset this tableitem image.
            _accessibleTableViewer.setTableItemImage();
        } 
        else if (e.detail == SWT.TRAVERSE_RETURN)
        {
            e.doit = false;
            fireApplyEditorValue();
            deactivate();
        }
    }

}

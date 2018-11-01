/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.views;

import org.eclipse.datatools.sqltools.sqlbuilder.util.LabelValuePair;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Layout;


public class ComboBoxCellEditor extends CellEditor {

    private Composite fEditor;
    private CCombo combo;
    private String fSelection;
    private Control focusControl = null;
    private ITextProvider fTextProvider;
    protected LabelValuePair[] fItems;
    protected Object fValue;

    public void createItems(LabelValuePair[] items) {
        fItems = items;
    }

    public LabelValuePair[] getLabelValuePairs() {
        return fItems;
    }

    /**
     * Creates a new combo box cell editor with the given choices.
     */
    public ComboBoxCellEditor(Composite parent, LabelValuePair[] items) {
        super(parent);
        fItems = items;
    }

    private class ComboCellLayout extends Layout {

        public void layout(Composite editor, boolean force) {
            Rectangle bounds = editor.getClientArea();
            getCombo().setBounds(0, 0, bounds.width, bounds.height);
        }

        public Point computeSize(Composite editor, int wHint, int hHint, boolean force) {
            if (wHint != SWT.DEFAULT && hHint != SWT.DEFAULT)
                return new Point(wHint, hHint);
            Point size = getCombo().computeSize(SWT.DEFAULT, SWT.DEFAULT, force);
            int width = 0;
            if (fItems != null) {
                GC gc = new GC(getCombo());
                for (int i = 0; i < fItems.length; i++) {
                    width = Math.max(width, gc.textExtent(fItems[i].fLabel).x);
                }
                width += gc.textExtent("M").x;
                gc.dispose();
            }
            size.x = width;
            return size;
        }
    }

    /**
     * Creates the actual UI repesentation.
     */
    protected Control createControl(Composite parent) {
        Font f = parent.getFont();

        fEditor = new Composite(parent, SWT.NONE);
        fEditor.setFont(f);
        fEditor.setLayout(new ComboCellLayout());

        combo = new CCombo(fEditor, SWT.READ_ONLY);
        combo.setFont(f);
        combo.setBackground(parent.getBackground());

        setValueValid(true);

        combo.addSelectionListener(new SelectionAdapter() {

            public void widgetDefaultSelected(SelectionEvent event) {
                // If item is selected, apply editor value
                if (getCombo().getSelectionIndex() >= 0)
                    fireApplyEditorValue();

                deactivate();
            }

            public void widgetSelected(SelectionEvent event) {
                //If an item has been selected, process 
                // otherwise skip.
                // This event doesn't close the editor, since the
                // selection may be the result of the arrow buttons
                // being used to navigate through the selections,
                // instead of the selection being made by a mouse
                // User must do an overt action to close editor 
                // (e.g. press Enter, click another object, etc.) 
                if (getCombo().getSelectionIndex() >= 0) {
                    setSelection( getCombo().getItem(getCombo().getSelectionIndex()) );

                    for (int i = 0; i < fItems.length; i++) {
                        if (fItems[i].fLabel.equals( getSelection() )) {
                            doSetValue(fItems[i].fValue);
                            break;
                        }
                    }
                }
            }
        });

        combo.addFocusListener(new FocusAdapter() {

            public void focusLost(FocusEvent e) {
                fireApplyEditorValue();
                forwardFocusLost();
            }

            public void focusGained(FocusEvent event) {
                refreshComboItems();
                fillCombo();
            }
        });

        combo.addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent e) {

                if (e.character == SWT.ESC) { // Escape key
                    fireCancelEditor();
                    keyReleaseOccured(e);
                }
                else if (e.character == SWT.CR) { // Enter/return key
                    if (getCombo().getSelectionIndex() >= 0) // selected: zero-based indexing
                    {
                        setSelection( getCombo().getItem(getCombo().getSelectionIndex()) );

                        for (int i = 0; i < fItems.length; i++) {
                            if (fItems[i].fLabel.equals( getSelection() )) {
                                doSetValue(fItems[i].fValue);
                                break;
                            }
                        }
                        fireApplyEditorValue();
                    }
                    keyReleaseOccured(e);
                }
            }
        });

        return fEditor;
    }

    protected void fireCancelEditor() {
        super.fireCancelEditor();
    }

    protected void fireApplyEditorValue() {
        super.fireApplyEditorValue();
    }

    protected void keyReleaseOccured(KeyEvent keyEvent) {
        super.keyReleaseOccured(keyEvent);
    }

    protected void refreshComboItems() // override this method
    {
    }

    /**
     * Returns the cell editor's value.
     */
    protected Object doGetValue() {
        return fValue;
    }

    /**
     * Set the focus to the cell editor's UI representation.
     */
    protected void doSetFocus() {
        combo.setFocus();
    }

    /**
     * Sets the value of the cell editor to the given value.
     */
    protected void doSetValue(Object value) {
        fValue = value;
    }

    protected void fillCombo() {
        if (fItems != null) {
            fSelection = null;

            String[] labels = new String[fItems.length];
            for (int i = 0; i < labels.length; i++) {
                LabelValuePair item = fItems[i];
                labels[i] = item.fLabel;
                /* If the current value matches one of the array items, then keep it as the selection. */
                if (fSelection == null && fValue != null) {
                    if (fValue.equals(item.fValue)) {
                        fSelection = item.fLabel;
                    }
                }
            }
            
            /* If there is a text provider, use that to get the text for the combo box text field. */
            ITextProvider textProvider = getTextProvider();
            if (textProvider != null) {
                String text = textProvider.getText(fValue);
                if (text != null) {
                    fSelection = text; 
                }
            }

            combo.setItems(labels);
            if (fSelection != null) {
                combo.setText(fSelection);
            }
        }
    }

    /**
     * Forwards a focus-lost event from the combo focus listener to this class.
     */
    public void forwardFocusLost() {
        focusLost();
    }
    
    public CCombo getCombo() {
        return combo;
    }
    
    public String getSelection() {
        return fSelection;
    }
    
    /**
     * Gets the current text provider.
     * 
     * @return the text provider or null if none was set
     */
    public ITextProvider getTextProvider() {
        return fTextProvider;
    }
    
    public void setSelection( String selection ) {
        fSelection = selection;
    }
    
    /**
     * Sets the text provider to the given object.
     * 
     * @param textProvider the text provider to set
     */
    public void setTextProvider(ITextProvider textProvider) {
        fTextProvider = textProvider;
    }
    
    public void deactivate() {
        super.deactivate();
        if (focusControl != null && !focusControl.isDisposed()) {
            focusControl.setFocus();
        }
    }

    public void activate() {
        if (Display.getCurrent() != null) {
            focusControl = Display.getCurrent().getFocusControl();
        }
        super.activate();
    }
}

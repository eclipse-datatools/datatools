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
package org.eclipse.datatools.sqltools.result.internal.ui.utils;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * UI related utility methods.
 * 
 * @author Dafan Yang
 */
public class UIUtil
{
    public static final int DEFAULT_BUTTON_WIDTH = 70;
    public static final int DEFAULT_COMBO_WIDTH = 100;
    public static final int DEFAULT_TEXTBOX_WIDTH = 100;
    public static final int DEFAULT_RADIO_FILL = GridData.HORIZONTAL_ALIGN_BEGINNING
        | GridData.VERTICAL_ALIGN_CENTER;
    
    /**
     * Returns the width in pixels given the characters number.
     * 
     * @param chars number of characters
     * @param control the widget which contains font information, should not be null
     * @return the width in pixels
     */
    public static int convertWidthInCharsToPixels(int chars, Control control)
    {
        if(control == null)
        {
            return 0;
        }
        GC gc = new GC(control);
        gc.setFont(JFaceResources.getDialogFont());
        FontMetrics fontMetrics = gc.getFontMetrics();
        gc.dispose();
        if(fontMetrics == null)
        {
            return 0;
        }
        return fontMetrics.getAverageCharWidth()*chars;
    }
    
    /**
     * Returns the height in pixels given the characters number.
     * 
     * @param chars number of characters
     * @param control the widget which contains font information, should not be null
     * @return the width in pixels
     */
    public static int convertHeightInCharsToPixels(int chars, Control control)
    {
        if(control == null)
        {
            return 0;
        }
        GC gc = new GC(control);
        gc.setFont(JFaceResources.getDialogFont());
        FontMetrics fontMetrics = gc.getFontMetrics();
        gc.dispose();
        if(fontMetrics == null)
        {
            return 0;
        }
        return fontMetrics.getHeight()*chars;
    }
    
    /**
     * Utility method that creates a label instance and sets the default layout
     * data.
     * 
     * @param parent
     *            the parent for the new label
     * @param text
     *            the text for the new label
     * @param numColumns
     *            the number of columns for the new composite
     * @param indent
     *            number of pixels to indent from the left
     * @return the new label
     */
    public static Label createLabel(Composite parent, String text,
        int numColumns, int indent) 
    {
        Label label = new Label(parent, SWT.LEFT);
        GridData data = new GridData();
        data.horizontalSpan = numColumns;
        data.horizontalAlignment = GridData.FILL;
        data.horizontalIndent = indent;
        label.setLayoutData(data);
        label.setText(text);
        return label;
    }
    
    /**
     * Utility method that creates a label instance and sets the default layout
     * data.
     * 
     * @param parent
     *            the parent for the new label
     * @param text
     *            the text for the new label
     * @param numColumns
     *            the number of columns for the new composite
     * @return the new label
     */
    public static Label createLabel(Composite parent, String text,
        int numColumns) 
    {
        return createLabel(parent, text, numColumns, 0);
    }
    
    public static Button createPushButton(Composite parent, String label) 
    {
        return createPushButton(parent, label, DEFAULT_BUTTON_WIDTH);
    }
    /**
     * Utility method that creates a push button instance and sets the default
     * layout data.
     * 
     * @param parent
     *            the parent for the new button
     * @param label
     *            the label for the new button
     * @param widthHint
     *            use this width for the button.
     * @return the newly-created button
     */
    public static Button createPushButton(Composite parent, String label,
        int widthHint) 
    {
        Button button = new Button(parent, SWT.PUSH);
        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL_HORIZONTAL;
        data.widthHint = widthHint;
        button.setLayoutData(data);
        button.setText(label);
        return button;
    }
    
    /**
     * Utility method that creates a group and sets the default layout data.
     * 
     * @param parent
     *            the parent for the new group
     * @param title
     *            the label for the new group
     * @param numColumns
     *            the number of columns for the new group
     * @return the newly created group
     */
    public static Group createGroup(Composite parent, String title,
        int numColumns) 
    {
        return createGroup(parent, title, numColumns, -1,
            GridData.FILL_HORIZONTAL);
    }
    /**
     * Utility method that creates a group and sets the default layout data.
     * 
     * @param parent
     *            the parent for the new group
     * @param title
     *            the label for the new group
     * @param numColumns
     *            the number of columns for the new group
     * @param horizontalSpan
     *            the number of columns this group should span on the parent
     *            composite.
     * @param fill
     *            the fill style of the new group -- set to for filling just
     *            around the object: GridData.BEGINNING | GridData.CENTER
     * @return the newly created group
     */
    public static Group createGroup(Composite parent, String title,
        int numColumns, int horizontalSpan, int gridDataFill) 
    {
        Group group = new Group(parent, SWT.SHADOW_ETCHED_IN);
        GridLayout layout = new GridLayout();
        layout.numColumns = numColumns;
        group.setLayout(layout);
        GridData data = new GridData(gridDataFill);
        if (horizontalSpan > 0) 
        {
            data.horizontalSpan = horizontalSpan;
        }
        group.setLayoutData(data);
        group.setText(title);
        return group;
    }
    
    /**
     * Creates a new checkbox and sets the default layout data.
     * 
     * @param parent
     *            the composite in which to create the checkbox
     * @param label
     *            the string to set into the checkbox
     * @param numColumns
     *            the number of columns the new checkbox is to occupy
     * @return the new checkbox
     */
    public static Button createCheckBox(Composite parent) 
    {
        return createCheckBox(parent, null, 1, 0);
    }
    /**
     * Creates a new checkbox and sets the default layout data.
     * 
     * @param parent
     *            the composite in which to create the checkbox
     * @param label
     *            the string to set into the checkbox
     * @param numColumns
     *            the number of columns the new checkbox is to occupy
     * @return the new checkbox
     */
    public static Button createCheckBox(Composite parent, String label,
        int numColumns) 
    {
        return createCheckBox(parent, label, numColumns, 0);
    }
    /**
     * Creates a new checkbox and sets the default layout data.
     * 
     * @param parent
     *            the composite in which to create the checkbox
     * @param label
     *            the string to set into the checkbox
     * @param numColumns
     *            the number of columns the new checkbox is to occupy
     * @param indent
     *            the number of pixels to indent from the left
     * @return the new checkbox
     */
    public static Button createCheckBox(Composite parent, String label,
        int numColumns, int indent) 
    {
        Button button = new Button(parent, SWT.CHECK | SWT.LEFT);
        if (label == null)
        button.setAlignment(SWT.CENTER);
        GridData data = new GridData(GridData.FILL);
        data.horizontalSpan = numColumns;
        data.horizontalIndent = indent;
        button.setLayoutData(data);
        if (label != null)
        button.setText(label);
        return button;
    }
    
    /**
     * Creates a text field
     * 
     * @param parent
     *            the parent of the new text field
     * @return the new text field
     */
    public static Text createTextBox(Composite parent) 
    {
        return createTextBox(parent, 1, DEFAULT_TEXTBOX_WIDTH);
    }
    /**
     * Creates a text field
     * 
     * @param parent
     *            the parent of the new text field
     * @return the new text field
     */
    public static Text createTextBox(Composite parent, String text) 
    {
        Text textbox = createTextBox(parent, 1);
        textbox.setText(text);
        return textbox;
    }
    /**
     * Creates a text field
     * 
     * @param parent
     *            the parent of the new text field
     * @param numColumns
     *            number of columns the text box is to occupy
     * @return the new text field
     */
    public static Text createTextBox(Composite parent, int numColumns) 
    {
        return createTextBox(parent, numColumns, DEFAULT_TEXTBOX_WIDTH);
    }
    /**
     * Creates a text field
     * 
     * @param parent
     *            the parent of the new text field
     * @param numColumns
     *            number of columns the text box is to occupy
     * @param minWidth
     *            minimum width of text field
     * @return the new text field
     */
    public static Text createTextBox(Composite parent, int numColumns,
        int minWidth) 
    {
        return createTextBox(parent, numColumns, minWidth, SWT.DEFAULT);
    }
    /**
     * Creates a text field
     * 
     * @param parent
     *            the parent of the new text field
     * @param numColumns
     *            number of columns the text box is to occupy
     * @param minWidth
     *            minimum width of text field
     * @return the new text field
     */
    public static Text createTextBox(Composite parent, int numColumns,
        int minWidth, int minHeight) 
    {
        Text text = new Text(parent, SWT.SINGLE | SWT.BORDER);
        GridData data = new GridData(GridData.FILL);
        data.horizontalSpan = numColumns;
        data.widthHint = minWidth;
        data.heightHint = minHeight;
        text.setLayoutData(data);
        return text;
    }
    
    /**
     * Creates a combo box and sets the default layout data.
     * 
     * @param parent
     *            the composite in which to create the combo
     * @param tokenString
     *            a tokenized string that will be split into the fields.
     * @param numColumns
     *            the number of columns the new combo is to occupy
     * @return the new combo box
     */
    public static Combo createCombo(Composite parent, String tokenString,
        int numColumns) 
    {
        return createCombo(parent, getTokenNames(tokenString), numColumns,
            DEFAULT_COMBO_WIDTH);
    }
    /**
     * Creates a combo box and sets the default layout data.
     * 
     * @param parent
     *            the composite in which to create the combo
     * @param tokenString
     *            a tokenized string that will be split into the fields.
     * @param numColumns
     *            the number of columns the new combo is to occupy
     * @param minWidth
     *            minimum width of combo box in pixels
     * @return the new combo box
     */
    public static Combo createCombo(Composite parent, String tokenString,
        int numColumns, int minWidth) 
    {
        return createCombo(parent, getTokenNames(tokenString), numColumns,
            minWidth);
    }
    /**
     * Creates a combo box and sets the default layout data.
     * 
     * @param parent
     *            the composite in which to create the combo
     * @param items
     *            the items in the combo
     * @param numColumns
     *            the number of columns the new combo is to occupy
     * @param minWidth
     *            minimum width of combo box in pixels
     * @return the new combo box
     */
    public static Combo createCombo(Composite parent, String[] items,
        int numColumns, int minWidth) 
    {
        return createCombo(parent, items, numColumns, minWidth, false);
    }
    /**
     * Creates a combo box and sets the default layout data.
     * 
     * @param parent
     *            the composite in which to create the combo
     * @param items
     *            the items in the combo
     * @param numColumns
     *            the number of columns the new combo is to occupy
     * @param minWidth
     *            minimum width of combo box in pixels
     * @param editable
     *            whether the items in the combo is editable
     * @return the new combo box
     */
    public static Combo createCombo(Composite parent, String tokenString,
        int numColumns, int minWidth, boolean editable) 
    {
        return createCombo(parent, getTokenNames(tokenString), numColumns,
            minWidth, editable);
    }
    /**
     * Creates a combo box and sets the default layout data.
     * 
     * @param parent
     *            the composite in which to create the combo
     * @param items
     *            the items in the combo
     * @param numColumns
     *            the number of columns the new combo is to occupy
     * @param minWidth
     *            minimum width of combo box in pixels
     * @param editable
     *            whether the items in the combo is editable
     * @return the new combo box
     */
    public static Combo createCombo(Composite parent, String[] items,
        int numColumns, int minWidth, boolean editable) 
    {
        Combo combo;
        GridData data;
        if (editable) 
        {
            combo = new Combo(parent, SWT.DROP_DOWN);
            data = new GridData(GridData.FILL_HORIZONTAL);
        }
        else 
        {
            combo = new Combo(parent, SWT.DROP_DOWN | SWT.READ_ONLY);
            data = new GridData(GridData.FILL);
        }
        data.horizontalSpan = numColumns;
        data.widthHint = minWidth;
        combo.setLayoutData(data);
        combo.setItems(items);
        return combo;
    }
    
    /**
     * Builds an array of strings from a token list string. The token separator
     * is a comma (',').
     * 
     * @param tokenString
     * @return String[]
     */
    public static String[] getTokenNames(String tokenString) 
    {
        if (tokenString == null) 
        {
            return new String[0];
        }
        return tokenString.split(",");
    }
}

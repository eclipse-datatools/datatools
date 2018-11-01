/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.common.ui.util;


import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.sqltools.common.ui.internal.Activator;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.PlatformUI;
/**
 * This utility class provides convenience methods in creating controls on
 * preference pages.
 * 
 * @author syu
 */
public class SWTUtils 
{
    // Defaults of controls
    public static final int DEFAULT_BUTTON_WIDTH = 70;
    public static final int DEFAULT_COMBO_WIDTH = 100;
    public static final int DEFAULT_TEXTBOX_WIDTH = 100;
    public static final int DEFAULT_RADIO_FILL = GridData.HORIZONTAL_ALIGN_BEGINNING
        | GridData.VERTICAL_ALIGN_CENTER;
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
     * Creates a combo box and sets the default layout data.
     * 
     * @param parent
     *            the composite in which to create the combo
     * @param items
     *            the items in the combo
     * @param numColumns
     *            the number of columns the new combo is to occupy
     * @return the new combo box
     */
    public static Combo createCombo(Composite parent, String[] items,
        int numColumns) 
    {
        return createCombo(parent, items, numColumns, DEFAULT_COMBO_WIDTH);
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
     * Creates composite control and sets the default layout data.
     * 
     * @param parent
     *            the parent of the new composite
     * @param numColumns
     *            the number of columns for the new composite
     * @return the newly-created coposite
     */
    public static Composite createComposite(Composite parent, int numColumns) 
    {
        return createComposite(parent, numColumns, -1, -1,
            GridData.FILL_HORIZONTAL, -1, -1, -1);
    }
    /**
     * Creates composite control and sets the default layout data.
     * 
     * @param parent
     *            the parent of the new composite
     * @param numColumns
     *            the number of columns for the new composite
     * @param verticalSpacing
     *            the spacing between rows.
     * @param horizontalSpan
     *            the span for this new composite over the original composite.
     * @return the newly-created coposite
     */
    public static Composite createComposite(Composite parent, int numColumns,
        int verticalSpacing, int horizontalSpan) 
    {
        return createComposite(parent, numColumns, verticalSpacing,
            horizontalSpan, GridData.FILL_HORIZONTAL, -1, -1, -1);
    }
    /**
     * Creates composite control and sets the default layout data.
     * 
     * @param parent
     *            the parent of the new composite
     * @param numColumns
     *            the number of columns for the new composite
     * @param verticalSpacing
     *            the spacing between rows.
     * @param horizontalSpan
     *            the span for this new composite over the original composite.
     * @param gridDataFill
     *            the fill to use for this composite.
     * @return the newly-created coposite
     */
    public static Composite createComposite(Composite parent, int numColumns,
        int verticalSpacing, int horizontalSpan, int gridDataFill) 
    {
        return createComposite(parent, numColumns, verticalSpacing,
            horizontalSpan, gridDataFill, -1, -1, -1);
    }
    /**
     * Creates composite control and sets the default layout data.
     * 
     * @param parent
     *            the parent of the new composite
     * @param numColumns
     *            the number of columns for the new composite
     * @param verticalSpacing
     *            the spacing between rows.
     * @param horizontalSpan
     *            the span for this new composite over the original composite.
     * @param gridDataFill
     *            the fill to use for this composite.
     * @param horizontalSpacing
     *            the spacing between objects.
     * @param _marginWidth
     *            the spacing at start and end of composite.
     * @param _marginHeight
     *            the spacing above and below composite.
     * @return the newly-created coposite
     */
    public static Composite createComposite(Composite parent, int numColumns,
        int verticalSpacing, int horizontalSpan, int gridDataFill,
        int horizontalSpacing, int marginWidth, int marginHeight) 
    {
        Composite composite = new Composite(parent, SWT.NULL);
        GridLayout layout = new GridLayout();
        layout.numColumns = numColumns;
        if (verticalSpacing >= 0) 
        {
            layout.verticalSpacing = verticalSpacing;
        }
        if (horizontalSpacing >= 0) 
        {
            layout.horizontalSpacing = horizontalSpacing;
        }
        if (marginWidth >= 0) 
        {
            layout.marginWidth = marginWidth;
        }
        if (marginHeight >= 0) 
        {
            layout.marginHeight = marginHeight;
        }
        composite.setLayout(layout);
        GridData gd = new GridData(gridDataFill);
        if (horizontalSpan > 0) 
        {
            gd.horizontalSpan = horizontalSpan;
        }
        composite.setLayoutData(gd);
        return composite;
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
     * Create a image label for sticking in a composite. The backgroud color is
     * optional. Because images can have "transparent" natures, you might want
     * to say the background is something other than the defaults composites
     * background.
     * 
     * NOTE: the caller is responsible for cleanup of the image and color
     * objects.
     * 
     * @param parent
     *            the parent for the new label
     * @param theImage
     *            the image for the new label
     * @param numColumns
     *            the number of columns for the new composite
     * @param background
     *            pass null to use the composites background.
     * @return the new label
     */
    public static Label createLableImage(Composite parent, Image theImage,
        int numColumns, Color background) 
    {
        Label label = new Label(parent, SWT.LEFT);
        GridData data = new GridData();
        data.horizontalSpan = numColumns;
        data.horizontalAlignment = GridData.FILL;
        label.setLayoutData(data);
        if (background != null) 
        {
            label.setBackground(background);
        }
        label.setImage(theImage);
        return label;
    }
    /**
     * Utility method that creates a push button instance and sets the default
     * layout data.
     * 
     * @param parent
     *            the parent for the new button
     * @param label
     *            the label for the new button
     * @return the newly-created button
     */
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
    public static Button createPushButton(Composite parent, Image theImage,
        int widthHint) 
    {
        Button button = new Button(parent, SWT.PUSH);
        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL_HORIZONTAL;
        data.widthHint = widthHint;
        button.setLayoutData(data);
        button.setImage(theImage);
        button.setAlignment(SWT.CENTER);
        return button;
    }
    /**
     * Utility method that creates a radio button instance and sets the default
     * layout data.
     * 
     * @param parent
     *            the parent for the new button
     * @param label
     *            the label for the new button
     * @return the newly-created button
     */
    public static Button createRadioButton(Composite parent, String label) 
    {
        return createRadioButton(parent, label, DEFAULT_RADIO_FILL);
    }
    /**
     * Utility method that creates a radio button instance and sets the default
     * layout data.
     * 
     * @param parent
     *            the parent for the new button
     * @param label
     *            the label for the new button
     * @return the newly-created button
     */
    public static Button createRadioButton(Composite parent, String label,
        int gridDataFill) 
    {
        return createRadioButton(parent, label, gridDataFill, 1);
    }
    /**
     * Utility method that creates a radio button instance and sets the default
     * layout data.
     * 
     * @param parent
     *            the parent for the new button
     * @param label
     *            the label for the new button
     * @param horizontalSpan
     *            number of columns occupied by button
     * @return the newly-created button
     */
    public static Button createRadioButton(Composite parent, String label,
        int gridDataFill, int horizontalSpan) 
    {
        Button button = new Button(parent, SWT.RADIO | SWT.LEFT);
        GridData data = new GridData(gridDataFill);
        data.horizontalSpan = horizontalSpan;
        button.setLayoutData(data);
        button.setText(label);
        return button;
    }
    /**
     * Utility method that creates an empty line
     * 
     * @param parent
     *            the parent for the new label
     * @param numColumns
     *            the number of columns for the new composite
     */
    public static void createSpacer(Composite parent, int numColumns) 
    {
        createSpacer(parent, numColumns, 0);
    }
    /**
     * Utility method that creates an empty line
     * 
     * @param parent
     *            the parent for the new label
     * @param numColumns
     *            the number of columns for the new composite
     * @param int
     *            minimum width of spacer
     */
    public static void createSpacer(Composite parent, int numColumns,
        int minWidth) 
    {
        Label label = new Label(parent, SWT.NONE);
        GridData data = new GridData();
        data.horizontalSpan = numColumns;
        data.widthHint = minWidth;
        label.setLayoutData(data);
    }
    /**
     * Create a table from a Composite object
     * 
     * @param composite
     *            the Composite this table is to be created from
     * @param tokenString
     *            A string containing names of the columns in the order that
     *            they should be displayed in the table with each column
     *            separated by a comma(',') or null if no columns need to be
     *            created.
     * @param tablewidth
     *            the minimum width for the table
     * @return the new table
     */
    public static Table createTable(Composite composite, String tokenString,
        int tablewidth, int tableHeight) 
    {
        // SINGLE, MULTI, CHECK, FULL_SELECTION, HIDE_SELECTION
        int style = SWT.V_SCROLL | SWT.H_SCROLL | SWT.SINGLE | SWT.BORDER
            | SWT.FULL_SELECTION;
        Table table = new Table(composite, style);
        GridData gridData = new GridData(GridData.FILL_BOTH);
        if (tablewidth > 0) 
        {
            gridData.widthHint = tablewidth;
        }
        if (tableHeight > 0) 
        {
            gridData.heightHint = tableHeight;
        }
        table.setLayoutData(gridData);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        if (tokenString != null) 
        {
            String[] columns = getTokenNames(tokenString);
            int columnSize = 50;
            if (tablewidth > 0) 
            {
                columnSize = tablewidth / columns.length;
            }
            for (int ii = 0; ii < columns.length; ii++) 
            {
                createTableColumn(table, columns[ii], ii,
                    columnSize);
            }
        }
        return table;
    }
    /**
     * Create a table from a Composite object
     * 
     * @param composite
     *            the Composite this table is to be created from
     * @param columns
     *            A string array containing names of the columns in the order
     *            that they should be displayed in the table, or null if no
     *            columns need to be created.
     * @param tablewidth
     *            the minimum width for the table
     * @return the new table
     */
    public static Table createTable(Composite composite, String[] columns,
        int tablewidth) 
    {
        Table table = new Table(composite, SWT.FULL_SELECTION);
        GridData gridData = new GridData(GridData.FILL_BOTH);
        gridData.widthHint = tablewidth;
        table.setLayoutData(gridData);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        if (columns != null) 
        {
            for (int i = 0; i < columns.length; i++) 
            {
                createTableColumn(table, columns[i], i);
            }
        }
        return table;
    }
    /**
     * Create a table column
     * 
     * @param parent
     *            the table that contains this column
     * @param name
     *            name of this column
     * @param index
     *            the column within the parent composite
     * @return the new table column
     */
    public static TableColumn createTableColumn(Table parent, String name,
        int index) 
    {
        TableColumn column = new TableColumn(parent, SWT.LEFT, index);
        column.setText(name);
        return column;
    }
    /**
     * Create a table column with the image and the width of the column is set
     * to the image width.
     * 
     * @param parent
     *            the table that contains this column
     * @param image
     *            iamge for this column
     * @return the new table column
     */
    public static TableColumn createTableColumn(Table parent, Image image,
        int index) 
    {
        TableColumn column = new TableColumn(parent, SWT.LEFT, index);
        column.setImage(image);
        column.setWidth(image.getBounds().width);
        column.setResizable(false);
        return column;
    }
    /**
     * Create a table column
     * 
     * @param parent
     *            the table that contains this column
     * @param name
     *            name of this column
     * @param index
     *            the column within the parent composite
     * @param tablewidth
     *            the width for the column
     * @return the new table column
     */
    public static TableColumn createTableColumn(Table parent, String name,
        int index, int tablewidth) 
    {
        TableColumn column = new TableColumn(parent, SWT.LEFT, index);
        column.setText(name);
        column.setWidth(tablewidth);
        return column;
    }
    /**
     * Create a text field
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
     * Create a text field
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
     * Create a text field
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
     * Create a text field
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
     * Create a text field
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
     * Create a text field that is scrollable.
     * 
     * @param parent
     *            the parent of the new text field
     * @param numColumns
     *            number of columns the text box is to occupy
     * @param minWidth
     *            minimum width of text field
     * @param minHeight
     *            minimum height of text field
     * @return the new text field
     */
    public static Text createTextBoxScrollable(Composite parent,
        int numColumns, int minWidth, int minHeight) 
    {
        Text text = new Text(parent, SWT.BORDER | SWT.V_SCROLL | SWT.WRAP);
        GridData data = new GridData((minWidth > 0)
            ? GridData.FILL
            : GridData.FILL_HORIZONTAL);
        data.horizontalSpan = numColumns;
        if (minWidth > 0) 
        {
            data.widthHint = minWidth;
        }
        data.heightHint = minHeight;
        text.setLayoutData(data);
        return text;
    }
    /**
     * Create a list with the items listed in it.
     * 
     * @param parent
     *            the parent of the new text field
     * @param numColumns
     *            number of columns the text box is to occupy
     * @param minWidth
     *            minimum width of text field
     * @param minHeight
     *            minimum height of text field
     * @param items
     *            the items in the list
     * @return the new list
     */
    public static List createList(Composite parent, int numColumns,
        int minWidth, int minHeight, String[] items) 
    {
        return createList(parent, numColumns, minWidth, minHeight, items, true);
    }
    /**
     * Create a list with the items listed in it.
     * 
     * @param parent
     *            the parent of the new list box
     * @param numColumns
     *            number of columns the list box is to occupy
     * @param minWidth
     *            minimum width of list box
     * @param minHeight
     *            minimum height of list box
     * @param items
     *            the items in the list
     * @param bmulti
     *            whether multiple item selection is allowed
     * @param verticalSpan
     *            the number of rows the list box is to occupy
     * @return the new list
     */
    public static List createList(Composite parent, int numColumns,
        int minWidth, int minHeight, String[] items, boolean bmulti) 
    {
        return createList(parent, numColumns, minWidth, minHeight, items,
            bmulti, 1);
    }
    /**
     * Create a list with the items listed in it.
     * 
     * @param parent
     *            the parent of the new list box
     * @param numColumns
     *            number of columns the list box is to occupy
     * @param minWidth
     *            minimum width of list box
     * @param minHeight
     *            minimum height of list box
     * @param items
     *            the items in the list
     * @param bmulti
     *            whether multiple item selection is allowed
     * @param verticalSpan
     *            the number of rows the list box is to occupy
     * @return the new list
     */
    public static List createList(Composite parent, int numColumns,
        int minWidth, int minHeight, String[] items, boolean bmulti,
        int verticalSpan) 
    {
        List theList;
        if (bmulti)
        theList = new List(parent, SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI
            | SWT.BORDER);
        else
        theList = new List(parent, SWT.V_SCROLL | SWT.H_SCROLL | SWT.SINGLE
            | SWT.BORDER);
        GridData data = new GridData(GridData.FILL_HORIZONTAL
            | GridData.VERTICAL_ALIGN_BEGINNING | GridData.FILL_VERTICAL);
        data.horizontalSpan = numColumns;
        data.widthHint = minWidth;
        data.heightHint = minHeight;
        data.verticalSpan = verticalSpan;
        theList.setLayoutData(data);
        if (items != null) 
        {
            theList.setItems(items);
        }
        return theList;
    }
    /**
     * Computes the size of the composite inside the scroll area so that scroll
     * bars show up correctly.
     * 
     * @param parentComposite
     * @param childComposite
     */
    public static void computeScrollArea(ScrolledComposite parentComposite,
        Composite childComposite) 
    {
        //Point pt = childComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT);
        //childComposite.setSize(pt);
        Point pt = childComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT);
        parentComposite.setExpandHorizontal(true);
        parentComposite.setExpandVertical(true);
        parentComposite.setMinWidth(pt.x);
        parentComposite.setMinHeight(pt.y);
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

    /**
     * register the listener recursively, except for the except component
     * @param container
     * @param listener
     * @param except
     */
    public static void listenModify(Composite container, Listener listener, Widget[] except) 
    {
        Control[] children = container.getChildren();
        if (children == null) return;

        for (int i=0; i<children.length; i++) 
        {
            if (except != null)
            {
                boolean found = false;
                for (int j = 0; j < except.length; j++)
                {
                    if (except[j] == children[i])
                    {
                        found = true;
                        break;
                    }
                }
                if (found)
                {
                    continue;
                }
            }
            //Combo is an instance of Composite
            if (children[i] instanceof Combo || children[i] instanceof Text || children[i] instanceof Spinner) 
            {
                children[i].addListener(SWT.Modify, listener);
                children[i].addListener(SWT.Selection, listener);
            }
            else if (children[i] instanceof Table) 
            {
                //TODO: it seems that modify doesn't work
                children[i].addListener(SWT.Modify, listener);
                children[i].addListener(SWT.Selection, listener);
            }
            else if (children[i] instanceof Label)
            {
                // skip
            }
            else if (children[i] instanceof Composite) 
            {
                listenModify((Composite)children[i], listener, except);
            }
            else
            {
                //button,...
                children[i].addListener(SWT.Selection, listener);
            }
        }
    }

    /**
     * Checks that a Text control is not null and not empty
     * @param text
     * @return
     */
    public static boolean notEmpty(Text text)
    {
        return text.getText() != null && !"".equals(text.getText().trim()); 
    }

    /**
     * Checks that a Combo control is not null and not empty
     * @param text
     * @return
     */
    public static boolean notEmpty(Combo text)
    {
        return text != null && text.getText() != null && !"".equals(text.getText().trim()); 
    }

    static final String ENABLEKEY = "com.sybase.stf.dmp.ui.originalEnable"; //$NON-NLS-1$
    /**
     * Recursively enable/disable a group of controls. When set to disable, it will try
     * to remember the original state of the control, so when recursive enable will only
     * restore to its original state.
     * 
     * @param control
     * @param enable
     */
    public static void recursiveEnable(Control control, boolean enable)
    {
        if (enable)
        {
            // restore its original status
            Object o = control.getData(ENABLEKEY);
            control.setData(ENABLEKEY, null);
            if (o instanceof Boolean) control.setEnabled(((Boolean)o).booleanValue());
            else control.setEnabled(true);
        }
        else
        {
            control.setData(ENABLEKEY, new Boolean(control.getEnabled()));
            control.setEnabled(false);
        }
        if (control instanceof Composite)
        {
            Control[] children = ((Composite)control).getChildren();
            for (int i=0; i<children.length; i++)
            {
                recursiveEnable(children[i], enable);
            }
        }
    }

    public static int getTableHeightHint(Table table, int rows)
    {
        if (table.getFont().equals(JFaceResources.getDefaultFont()))
        {
            table.setFont(JFaceResources.getDialogFont());
        }
        int result = table.getItemHeight() * rows + table.getHeaderHeight();
        if (table.getLinesVisible())
        {
            result += table.getGridLineWidth() * (rows - 1);
        }
        return result;
    }
    
    public static void processError(final String msg, final Exception error, final IStatus status)
    {
        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable()
        {
            public void run()
            {
                IStatus stat = status;
                if (stat == null)
                {
                    stat = new Status(IStatus.ERROR, Activator.PLUGIN_ID, 0, error.getMessage() == null ? "" : error //$NON-NLS-1$
                        .getMessage(), error);
                }
                String title = Messages.common_error;
                ErrorDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), title, msg, stat);
            }
        }
        );
        Activator.getDefault().log(msg, error); //$NON-NLS-1$
    }
}

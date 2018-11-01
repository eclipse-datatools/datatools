/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.common;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

/**
 * A text form entry used in form page
 * 
 * @author Idull
 */
public class TextFormEntry
{
    private Control     _nameLabel;
    private Text        _text;
    private Button      _button;
    private FormToolkit _toolkit;
    private int         _colSpan;

    public TextFormEntry(Composite parent, FormToolkit toolkit, int textStyle, String labelText, String buttonText,
            int colSpan)
    {
        _toolkit = toolkit;
        _colSpan = colSpan;
        createControl(parent, toolkit, textStyle, labelText, buttonText);
    }

    private void createControl(Composite parent, FormToolkit toolkit, int textStyle, String labelText, String buttonText)
    {
        _nameLabel = _toolkit.createLabel(parent, labelText);
        _nameLabel.setForeground(toolkit.getColors().getColor(FormColors.TITLE));
        _text = _toolkit.createText(parent, "", textStyle);
        boolean buttonCreated = false;
        if (buttonText != null && buttonText.trim().length() != 0)
        {
            _button = _toolkit.createButton(parent, buttonText, SWT.NONE);
            buttonCreated = true;
        }

        Layout layout = parent.getLayout();
        if (layout instanceof GridLayout)
        {
            GridLayout parentLayout = (GridLayout) layout;
            // numColumns should be greater than or equals to 2
            int numColumns = parentLayout.numColumns;
            if (numColumns < 2)
            {
                // how to?
                return;
            }
            if (!buttonCreated)
            {
                int numSpan = numColumns;
                if (_colSpan < numColumns && !(_colSpan < 2))
                {
                    numSpan = _colSpan;
                }
                int textSpan = numSpan - 1;
                GridData gd = new GridData();
                _nameLabel.setLayoutData(gd);

                gd = new GridData(GridData.FILL_HORIZONTAL);
                gd.horizontalSpan = textSpan;
                _text.setLayoutData(gd);
            }
            else
            {
                if (numColumns < 3)
                {
                    // how to?
                    return;
                }
                int numSpan = numColumns;
                if (_colSpan < numColumns && !(_colSpan < 3))
                {
                    numSpan = _colSpan;
                }
                int textSpan = numSpan - 2;
                GridData gd = new GridData();
                _nameLabel.setLayoutData(gd);

                gd = new GridData(GridData.FILL_HORIZONTAL);
                gd.horizontalSpan = textSpan;
                _text.setLayoutData(gd);

                gd = new GridData();
                _button.setLayoutData(gd);
            }
        }
        else if (layout instanceof TableWrapLayout)
        {
            TableWrapLayout parentLayout = (TableWrapLayout) layout;
            // numColumns should be greater than or equals to 2
            int numColumns = parentLayout.numColumns;
            if (numColumns < 2)
            {
                // how to?
                return;
            }
            if (!buttonCreated)
            {
                int numSpan = numColumns;
                if (_colSpan < numColumns && !(_colSpan < 2))
                {
                    numSpan = _colSpan;
                }
                int textSpan = numSpan - 1;
                TableWrapData td = new TableWrapData();
                td.valign = TableWrapData.MIDDLE;
                _nameLabel.setLayoutData(td);

                td = new TableWrapData(TableWrapData.FILL_GRAB);
                td.colspan = textSpan;
                td.valign = TableWrapData.MIDDLE;
                _text.setLayoutData(td);
            }
            else
            {
                if (numColumns < 3)
                {
                    // how to?
                    return;
                }
                int numSpan = numColumns;
                if (_colSpan < numColumns && !(_colSpan < 3))
                {
                    numSpan = _colSpan;
                }
                int textSpan = numSpan - 2;
                TableWrapData td = new TableWrapData();
                td.valign = TableWrapData.MIDDLE;
                _nameLabel.setLayoutData(td);

                td = new TableWrapData(TableWrapData.FILL_GRAB);
                td.colspan = textSpan;
                td.valign = TableWrapData.MIDDLE;
                _text.setLayoutData(td);

                td = new TableWrapData();
                _button.setLayoutData(td);
            }
        }
        _toolkit.paintBordersFor(parent);
    }

    public Button getButton()
    {
        return _button;
    }

    public Control getNameLabel()
    {
        return _nameLabel;
    }

    public Text getText()
    {
        return _text;
    }
}

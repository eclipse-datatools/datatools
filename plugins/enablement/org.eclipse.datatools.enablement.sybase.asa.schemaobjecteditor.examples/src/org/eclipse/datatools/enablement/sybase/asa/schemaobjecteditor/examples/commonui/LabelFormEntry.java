/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

/**
 * A label form entry, which contains the following widgets:
 * 
 * <pre>
 *    |Name_Label| |Value_Labe|
 * </pre>
 * 
 * @deprecated use <code>LabelButtonFormEntry</code> instead
 * @author Idull
 */
public class LabelFormEntry
{
    private Control     _nameLabel;
    private Label       _valueLabel;
    private FormToolkit _toolkit;
    private int         _colSpan;

    public LabelFormEntry(Composite parent, FormToolkit toolkit, int labelStyle, String labelText, int colSpan)
    {
        _toolkit = toolkit;
        _colSpan = colSpan;
        createControl(parent, toolkit, labelStyle, labelText);
    }

    private void createControl(Composite parent, FormToolkit toolkit, int labelStyle, String labelText)
    {
        _nameLabel = _toolkit.createLabel(parent, labelText);
        _nameLabel.setForeground(toolkit.getColors().getColor(FormColors.TITLE));
        _valueLabel = _toolkit.createLabel(parent, "", labelStyle);

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

            int numSpan = numColumns;
            if (_colSpan < numColumns && !(_colSpan < 2))
            {
                numSpan = _colSpan;
            }
            int valueLabelSpan = numSpan - 1;
            GridData gd = new GridData();
            gd.verticalAlignment = SWT.TOP;
            _nameLabel.setLayoutData(gd);

            gd = new GridData(GridData.FILL_HORIZONTAL);
            gd.horizontalSpan = valueLabelSpan;
            _valueLabel.setLayoutData(gd);
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

            int numSpan = numColumns;
            if (_colSpan < numColumns && !(_colSpan < 2))
            {
                numSpan = _colSpan;
            }
            int valueLabelSpan = numSpan - 1;
            TableWrapData td = new TableWrapData();
            td.valign = TableWrapData.MIDDLE;
            _nameLabel.setLayoutData(td);

            td = new TableWrapData(TableWrapData.FILL_GRAB);
            td.colspan = valueLabelSpan;
            _valueLabel.setLayoutData(td);
        }

    }

    public Control getNameLabel()
    {
        return _nameLabel;
    }

    public Label getValueLabel()
    {
        return _valueLabel;
    }
}

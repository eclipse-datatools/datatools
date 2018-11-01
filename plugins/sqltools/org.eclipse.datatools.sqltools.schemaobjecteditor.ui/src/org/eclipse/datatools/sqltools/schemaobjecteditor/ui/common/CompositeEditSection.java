/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.common;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

/**
 * A composite edit section, with a main control area and a button list. <br>
 * User of this class can create any widgets on the main control area, for example, viewer.
 * 
 * @author Idull
 */
public class CompositeEditSection extends CollapseableSection
{
    protected Button[]      _buttons;
    protected Composite     _left;
    protected Composite     _buttonComp;
    protected String[]      _buttonTexts;
    protected String        _despText;
    private int             _buttonWidth;
    public static final int BUTTON_WIDTH = 80;

    /**
     * Constructs a composite edit section
     * 
     * @param toolkit the form toolkit
     * @param title the title of this section
     * @param display the <code>Display</code> instance
     * @param isCollapseable whether the section is collapseable or not
     * @param isCollapsed the initial collapse status of the section
     * @param estyle the style of the section
     * @param buttonTexts the button texts
     * @param buttonWidth the button width
     */
    public CompositeEditSection(FormToolkit toolkit, String title, Display display, boolean isCollapseable,
            boolean isCollapsed, int estyle, String[] buttonTexts, int buttonWidth)
    {
        super(toolkit, title, display, isCollapseable, isCollapsed, estyle);
        _buttonTexts = buttonTexts;
        _buttonWidth = buttonWidth;
    }

    /**
     * Constructs a composite edit section
     * 
     * @param toolkit the form toolkit
     * @param title the title of this section
     * @param display the <code>Display</code> instance
     * @param isCollapseable whether the section is collapseable or not
     * @param isCollapsed the initial collapse status of the section
     * @param estyle the style of the section
     * @param buttonTexts the button texts
     * @param buttonWidth the button width
     * @param despText the description text of the section
     */
    public CompositeEditSection(FormToolkit toolkit, String title, Display display, boolean isCollapseable,
            boolean isCollapsed, int estyle, String[] buttonTexts, int buttonWidth, String despText)
    {
        this(toolkit, title, display, isCollapseable, isCollapsed, estyle
                | ((despText != null && !despText.trim().equals("")) ? Section.DESCRIPTION : SWT.NONE), buttonTexts,
                buttonWidth);
        _despText = despText;
    }

    protected void createSectionContent(Composite parent)
    {
        Assert.isNotNull(_buttonTexts);

        // Set layout for section
        getSection().setLayout(new GridLayout());

        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        layout.marginTop = 2;
        layout.marginBottom = 2;
        parent.setLayout(layout);
        parent.setLayoutData(new GridData(GridData.FILL_BOTH));

        if (_despText != null && !_despText.trim().equals(""))
        {
            getSection().setDescription(_despText);
        }
        _left = _toolkit.createComposite(parent, SWT.NONE);
        layout = new GridLayout();
        layout.marginHeight = 2;
        layout.marginWidth = 1;
        _left.setLayout(layout);
        _left.setLayoutData(new GridData(GridData.FILL_BOTH));

        _buttonComp = _toolkit.createComposite(parent);

        layout = new GridLayout();
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        _buttonComp.setLayout(layout);
        _buttonComp.setLayoutData(new GridData(GridData.FILL_VERTICAL));

        if (_buttonWidth < 0)
        {
            _buttonWidth = BUTTON_WIDTH;
        }
        List buttons = new ArrayList();
        for (int i = 0; i < _buttonTexts.length; i++)
        {
            if (_buttonTexts[i] == null)
            {
                continue;
            }
            Button b = _toolkit.createButton(_buttonComp, "", SWT.NONE);
            b.setText(_buttonTexts[i]);
            GridData gd = new GridData();
            gd.widthHint = _buttonWidth;
            b.setLayoutData(gd);
            b.setData(new Integer(i));
            b.addSelectionListener(new SelectionListener()
            {
                public void widgetDefaultSelected(SelectionEvent e)
                {
                    buttonSelected(e, ((Integer) e.widget.getData()).intValue());
                }

                public void widgetSelected(SelectionEvent e)
                {
                    buttonSelected(e, ((Integer) e.widget.getData()).intValue());
                }
            });
            buttons.add(b);
        }

        Composite blankComp = _toolkit.createComposite(_buttonComp, SWT.NONE);
        blankComp.setLayoutData(new GridData(GridData.FILL_BOTH));
        _toolkit.paintBordersFor(parent);
        _buttons = (Button[]) buttons.toArray(new Button[buttons.size()]);
    }

    public Composite getLeftComposite()
    {
        return _left;
    }

    /**
     * Returns the button list
     * 
     * @return
     */
    public Button[] getButtons()
    {
        return _buttons;
    }

    public Composite getButtonComp()
    {
        return _buttonComp;
    }

    public void enableButton(boolean enable, int buttonIndex)
    {
        if (_buttons == null || buttonIndex < 0 || buttonIndex > _buttons.length - 1)
        {
            return;
        }
        _buttons[buttonIndex].setEnabled(enable);
    }

    public boolean isButtonEnabled(boolean enable, int buttonIndex)
    {
        if (_buttons == null || buttonIndex < 0 || buttonIndex > _buttons.length - 1)
        {
            return false;
        }
        return _buttons[buttonIndex].isEnabled();
    }

    /**
     * Will be called when the button is selected
     * 
     * @param e the selection event
     * @param buttonIndex the index of the button
     */
    protected void buttonSelected(SelectionEvent e, int buttonIndex)
    {

    }
}

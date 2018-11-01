/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.commonui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * This class overrides some of the FormToolkit methods to create ordinary SWT controls. This is mainly to ease the UI
 * component reuse.
 * 
 * @author Hui Cao
 * 
 */
public class PseudoFormToolkit extends FormToolkit
{

    public PseudoFormToolkit(Display display)
    {
        super(display);
        // TODO Auto-generated constructor stub
    }

    public PseudoFormToolkit(FormColors colors)
    {
        super(colors);
        // TODO Auto-generated constructor stub
    }

    public Button createButton(Composite parent, String text, int style)
    {
        Button button = new Button(parent, style);
        if (text != null)
        {
            button.setText(text);
        }        
        return button;
    }

    public Composite createComposite(Composite parent, int style)
    {
        return new Composite(parent, style);
    }

    public Composite createComposite(Composite parent)
    {
        return new Composite(parent, SWT.NONE);
    }

    public Label createLabel(Composite parent, String text, int style)
    {
        Label l = new Label(parent, style);
        if (text != null)
        {
            l.setText(text);
        }
        return l;
    }

    public Label createLabel(Composite parent, String text)
    {
        return createLabel(parent, text, SWT.NONE);
    }

    public Table createTable(Composite parent, int style)
    {
        return new Table(parent, style);
    }

    public Text createText(Composite parent, String value, int style)
    {
        Text t = new Text(parent, style);
        if (value != null)
        {
            t.setText(value);
        }
        return t;
    }

    public Text createText(Composite parent, String value)
    {
        return createText(parent, value, SWT.NONE);
    }

    public Tree createTree(Composite parent, int style)
    {
        Tree t = new Tree(parent, style);
        return t;
    }

    public void paintBordersFor(Composite parent)
    {
        //DO Nothing
    }
}

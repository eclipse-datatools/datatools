/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.utils;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * SWT utils class for creation wizard and editor
 * 
 * @author Hui Wan
 */
public class SWTUtilsExt
{
    /**
     * Create Labels from specified label by parent or Managed form(for editor)
     * 
     * @param parent Parent composite
     * @param text the label text
     * @param style SWT style
     * @param form Managed form
     * @return Label
     */
    public static Label createLabel(Composite parent, String text, int style, IManagedForm form)
    {
        return createLabel(parent, text, style, form == null ? null : form.getToolkit());
    }
    
	/**
	 * Create label by form toolkit
     * 
     * @param parent
	 * @param text
	 * @param style
	 * @param toolkit
	 * @return
	 */
	public static Label createLabel(Composite parent, String text, int style, FormToolkit toolkit)
    {
        if (toolkit == null)
        {
            Label label = new Label(parent, style);
            label.setText(text);
            return label;
        }
        return toolkit.createLabel(parent, text, style);
    }
    
    /**
     * Create label by form toolkit
     * 
     * @param parent
     * @param text
     * @param style
     * @param toolkit
     * @param IsInputFormStyledLabel
     * @return
     */
    public static Label createLabel(Composite parent, String text, int style, FormToolkit toolkit, boolean IsInputFormStyledLabel)
    {
        if (toolkit == null)
        {
            Label label = new Label(parent, style);
            label.setText(text);
            return label;
        }
        Label label = toolkit.createLabel(parent, text, style);
        if (IsInputFormStyledLabel)
        {
            label.setForeground(toolkit.getColors().getColor(FormColors.TITLE));
        }
        toolkit.paintBordersFor(parent);
        return label;
    }
    
    /**
     * Create label by managed form
     * 
     * @param parent
     * @param text
     * @param style
     * @param form
     * @param IsInputFormStyledLabel
     * @return
     */
    public static Label createLabel(Composite parent, String text, int style, IManagedForm form, boolean IsInputFormStyledLabel)
    {
        return createLabel(parent, text, style, form == null ? null : form.getToolkit());       
    }
    
    /**
     * Create Button from specified label by parent or Managed form
     * 
     * @param parent Parent composite
     * @param style SWT style
     * @param form Managed form
     * @return Button
     */
    public static Button createButton(Composite parent, int style, FormToolkit toolkit)
    {
        if (toolkit == null)
        {
            return new Button(parent, style);
        }
        Button button  = toolkit.createButton(parent, "", style);
        toolkit.paintBordersFor(parent);
        return button; //$NON-NLS-1$
    }

    /**
     * Create Button from specified label by parent or Managed form
     * 
     * @param parent Parent composite
     * @param style SWT style
     * @param form Managed form
     * @return Button
     */
    public static Button createButton(Composite parent, int style, IManagedForm form)
    {
        return createButton(parent, style, form == null ? null:form.getToolkit());
    }
    
    /**
     * Create Text from specified label
     * 
     * @param parent Parent composite
     * @param style SWT style
     * @param form Managed form
     * @return Text
     */
    public static Text createText(Composite parent, int style, FormToolkit toolkit)
    {
        return createText(parent, "", style, toolkit); //$NON-NLS-1$
    }
    
    /**
     * Create Text by creation wizard or  toolkit
     * 
     * @param parent Parent composite
     * @param style SWT style
     * @param toolkit Tool kit
     * @return StyledText
     */
    public static Text createText(Composite parent, int style, IManagedForm form)
    {
        return createText(parent, "", style, form==null ? null:form.getToolkit()); //$NON-NLS-1$
    }

    /**
     * Create Text  by parent or Managed form
     * 
     * @param parent Parent composite
     * @param value Text's default value
     * @param style SWT style
     * @param form Managed form
     * @return Label
     */
    public static Text createText(Composite parent, String value, int style, IManagedForm form)
    {
        return createText(parent, value, style, form ==null ?null:form.getToolkit());
    }
    
    /**
     * Create Text by creation wizard or  toolkit
     * 
     * @param parent Parent composite
     * @param style SWT style
     * @param toolkit Tool kit
     * @return Text
     */
    public static Text createText(Composite parent, String value, int style, FormToolkit toolkit)
    {
        if (toolkit == null)
        {
            Text text = new Text(parent, style);
            text.setText(value);
            return text;
        }
        else
        {
            Text text = toolkit .createText(parent, value, style);
            toolkit.paintBordersFor(parent);
            return text;
        }
    }

    /**
     * Create Composite by parent or Managed form
     * 
     * @param parent Parent composite
     * @param style SWT style
     * @param form Managed form
     * @return Composite
     */
    public static Composite createComposite(Composite parent, int style, FormToolkit toolkit)
    {
        if (toolkit == null)
        {
            return new Composite(parent, style);
        }
        else
        {
            Composite comp = toolkit.createComposite(parent, style);
            toolkit.paintBordersFor(parent);
            return comp;
        }
       
    }
    
    /**
     * Create Composite by creation wizard or  toolkit
     * 
     * @param parent Parent composite
     * @param style SWT style
     * @param toolkit Tool kit
     * @return Composite
     */
    public static Composite createComposite(Composite parent, int style, IManagedForm form)
    {
        return createComposite(parent, style, form == null ? null : form.getToolkit());
    }
    
    /**
     * Create Combo from specified label by parent or Managed form
     * 
     * @param parent Parent composite
     * @param style SWT style
     * @param form Managed form
     * @return Composite
     */
    public static Combo createCombo(Composite parent, int style, IManagedForm form)
    {
        return createCombo(parent, style, form ==null? null:form.getToolkit());
    }
    
    /**
     * Create Combo from specified label by parent or Managed form
     * 
     * @param parent Parent composite
     * @param style SWT style
     * @param form Managed form
     * @return Composite
     */
    public static Combo createCombo(Composite parent, int style, FormToolkit toolkit)
    {
        if (toolkit == null)
        {
            return new Combo(parent, style);
        }        
        Combo combo = new Combo(parent, style);
        toolkit.adapt(combo, true,true);
        toolkit.paintBordersFor(parent);
        return combo;
    }
    
    /**
     * Create Spinner by parent or Managed form
     * 
     * @param parent Parent composite
     * @param style SWT style
     * @param form Managed form
     * @return Spinner
     */
    public static Spinner createSpinner(Composite parent, int style, IManagedForm form)
    {
        return createSpinner(parent, style, form == null ? null:form.getToolkit());
    }
    
    /**
     * Create Spinner by parent or Managed form
     * 
     * @param parent Parent composite
     * @param style SWT style
     * @param form Managed form
     * @return Spinner
     */
    public static Spinner createSpinner(Composite parent, int style, FormToolkit toolkit)
    {
        if (toolkit == null)
        {
            return new Spinner(parent, style);
        }
        final Spinner spinner = new Spinner(parent, style);
        toolkit.adapt(spinner, true,true);
        spinner.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
        toolkit.paintBordersFor(parent);
        return spinner;
    }
    
    /**
     * Create StyledText by parent or Managed form
     * 
     * @param parent Parent composite
     * @param style SWT style
     * @param form Managed form
     * @return StyledText
     */
    public static StyledText createStyledText(Composite parent, int style, IManagedForm form)
    {
        return createStyledText(parent, style, form == null? null:form.getToolkit());
    }
    
    /**
     * Create Styled text by creation wizard or  toolkit
     * 
     * @param parent Parent composite
     * @param style SWT style
     * @param toolkit Tool kit
     * @return StyledText
     */
    public static StyledText createStyledText(Composite parent, int style, FormToolkit toolkit)
    {
        if (toolkit == null)
        {
            return new StyledText(parent, style);
        }
        StyledText spinner = new StyledText(parent, style);
        toolkit.adapt(spinner, true, true);
        toolkit.paintBordersFor(parent);
        return spinner;
    }
    
    /**
     * Create StyledText by parent or Managed form
     * 
     * @param parent Parent composite
     * @param style SWT style
     * @param form Managed form
     * @return StyledText
     */
    public static Table createTable(Composite parent, int style, IManagedForm form)
    {
        return createTable(parent, style, form ==null ? null:form.getToolkit());
    }
    
    
    /**
     * Create table by creation wizard or  toolkit
     * 
     * @param parent Parent composite
     * @param style SWT style
     * @param toolkit Tool kit
     * @return Table
     */
    public static Table createTable(Composite parent, int style, FormToolkit toolkit)
    {
        if (toolkit == null)
        {
            return new Table(parent, style);
        }
        Table table = toolkit.createTable(parent, style);
        toolkit.paintBordersFor(parent);
        return table;
    }
    
    /**
     * Create CCombox by creation wizard or  Managed form
     * 
     * @param parent Parent composite
     * @param style SWT style
     * @param form Managed form
     * @return StyledText
     */
    public static CCombo createCCombo(Composite parent, int style, IManagedForm form)
    {
        return createCCombo(parent, style, form==null?null:form.getToolkit());
    }
    
    /**
     * Create CCombox by creation wizard or  toolkit
     * 
     * @param parent Parent composite
     * @param style SWT style
     * @param toolkit Tool kit
     * @return StyledText
     */
    public static CCombo createCCombo(Composite parent, int style, FormToolkit toolkit)
    {
        if (toolkit == null)
        {
            return new CCombo(parent, style);
        }
        CCombo cCombo = new CCombo(parent, style | SWT.FLAT);
        toolkit.adapt(cCombo, true, true);
        toolkit.paintBordersFor(parent);
        return cCombo;
    }
}

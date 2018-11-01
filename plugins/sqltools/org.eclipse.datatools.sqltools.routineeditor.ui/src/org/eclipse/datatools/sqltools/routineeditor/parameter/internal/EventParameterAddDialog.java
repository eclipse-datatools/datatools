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
package org.eclipse.datatools.sqltools.routineeditor.parameter.internal;

import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.core.dbitem.IEvent;
import org.eclipse.datatools.sqltools.routineeditor.parameter.EventParameter;
import org.eclipse.datatools.sqltools.sqleditor.internal.IHelpContextIds;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * A dialog to add parameters to a event call.
 * @author Zhi-hong(Bryan) Yang
 */
public class EventParameterAddDialog extends Dialog 
{
    private Combo _name;
    private Combo _value;
    private IEvent _event = null;
    private EventParameter _param = null;




    protected void okPressed()
    {
        String name = _name.getText().trim();
        String value = _value.getText().trim();

        if (!name.equals(""))//$NON-NLS-1$
        {
            _param = new EventParameter();
            _param.setName(name);
            _param.setValue(value);
        }
        super.okPressed();
    }

    public EventParameter getEventParameter()
    {
        return _param;
    }

    public EventParameterAddDialog(Shell parentShell,IEvent event) 
    {
        super(parentShell);
        _event= event;
    }
    protected void configureShell(Shell newShell) 
    {
        super.configureShell(newShell);
        newShell.setText(Messages.getString("EventParameterAddDialog.title")); //$NON-NLS-1$
    }

    protected Control createDialogArea(Composite parent) 
    {
        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent.getShell(), HelpUtil.getContextId(IHelpContextIds.EVENT_LAUNCH_CONFIGURATION_PARAMETERS, SQLEditorPlugin.getDefault().getBundle().getSymbolicName())); 

        int widthHint= convertWidthInCharsToPixels(60);
        int indent= convertWidthInCharsToPixels(4);
        GridData textData= new GridData();
        textData.widthHint= widthHint;
        textData.grabExcessHorizontalSpace= true;
        textData.horizontalIndent= indent;

        Composite container = (Composite) super.createDialogArea(parent);
        final GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        container.setLayout(gridLayout);
        container.setLayoutData(textData);
        final Label label = new Label(container, SWT.NONE);
        label.setText(Messages.getString("EventParameterAddDialog.label.name")); //$NON-NLS-1$

        _name = new Combo(container, SWT.NONE);
        _name.setItems(_event.getSupportedParameter(_event.getEventType()));
        _name.setLayoutData(new GridData(GridData.FILL_BOTH));
        _name.addSelectionListener(new SelectionAdapter() 
        {
            public void widgetDefaultSelected(SelectionEvent e) 
            {
            }
            public void widgetSelected(SelectionEvent e) 
            {
                _value.setItems(_event.getValidValues(_name.getText()));
            }
        }
        );
        final Label label_1 = new Label(container, SWT.NONE);
        label_1.setText(Messages.getString("EventParameterAddDialog.label.value")); //$NON-NLS-1$
        _value = new Combo(container, SWT.BORDER);
        _value.setLayoutData(new GridData(GridData.FILL_BOTH));
        //
        return container;
    }

    protected void createButtonsForButtonBar(Composite parent) 
    {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
            true);
        createButton(parent, IDialogConstants.CANCEL_ID,
            IDialogConstants.CANCEL_LABEL, false);
    }


}

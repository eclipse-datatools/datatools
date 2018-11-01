/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.internal.core;

import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.common.CollapseableSection;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.Messages;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.SchemaObjectEditorPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

/**
 * A page internally used to display some information to the end-user
 * 
 * @author Idull
 */
public class ErrorPage extends SchemaObjectEditorPage
{

    private String _errorMsg;

    public ErrorPage(String msg)
    {
        _errorMsg = msg == null ? "" : msg;
    }

    protected void createFormContent(IManagedForm managedForm)
    {
        super.createFormContent(managedForm);
        managedForm.getForm().setText(Messages.ErrorPage_error_msg);
        Composite comp = managedForm.getForm().getBody();
        Layout layout = new GridLayout();
        comp.setLayout(layout);

        Composite container = managedForm.getToolkit().createComposite(comp);
        layout = new GridLayout();
        container.setLayout(layout);
        GridData gd = new GridData(GridData.FILL_BOTH);
        container.setLayoutData(gd);

        CollapseableSection errorSection = new CollapseableSection(getEditor().getToolkit(), Messages.ErrorPage_error,
                container.getDisplay(), SWT.NONE)
        {
            public void createSectionContent(Composite parent)
            {
                getSection().setLayoutData(new GridData(GridData.FILL_BOTH));
                getSection().setLayout(new TableWrapLayout());

                parent.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
                FormToolkit toolkit = getEditor().getToolkit();
                TableWrapLayout layout = new TableWrapLayout();
                layout.makeColumnsEqualWidth = false;
                parent.setLayout(layout);

                FormText text = toolkit.createFormText(parent, false);
                text.setText(_errorMsg, false, false);
            }
        };
        errorSection.createControl(container, 1, null);
    }
}

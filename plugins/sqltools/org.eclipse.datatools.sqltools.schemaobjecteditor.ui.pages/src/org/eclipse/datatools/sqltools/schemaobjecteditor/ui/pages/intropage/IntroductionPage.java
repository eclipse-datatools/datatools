/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.pages.intropage;

import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.SchemaObjectEditorPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

/**
 * The introduction page
 * 
 * @author Idull
 */
public class IntroductionPage extends SchemaObjectEditorPage
{
    private String _contextHelpId = null;

    protected void createFormContent(IManagedForm managedForm)
    {

        super.createFormContent(managedForm);
        Composite comp = managedForm.getForm().getBody();
        managedForm.getForm().setText(Messages.IntroductionPage_page_title); //$NON-NLS-1$
        TableWrapLayout layout = new TableWrapLayout();
        comp.setLayout(layout);

        Composite container = managedForm.getToolkit().createComposite(comp);
        layout = new TableWrapLayout();
        container.setLayout(layout);
        TableWrapData td = new TableWrapData(TableWrapData.FILL_GRAB, TableWrapData.FILL_GRAB);
        container.setLayoutData(td);

        String pageExtensionId = getPageDescriptor().getPageExtensionId();

        IntroductionSection introSection = new IntroductionSection(pageExtensionId, getEditor().getToolkit(),
                Messages.IntroductionPage_section_title, container.getDisplay(), SWT.NONE); //$NON-NLS-1$
        _contextHelpId = introSection.getContextHelpId();
        introSection.setPage(this);
        introSection.createControl(container, 1, _contextHelpId);
        introSection.setPluginId(getEditorDescriptor().getPluginId());
        if (introSection.getPageName() != null && introSection.getPageName().trim().length() != 0)
        {
            managedForm.getForm().setText(introSection.getPageName());
        }
    }

    public void createPartControl(Composite parent)
    {
        super.createPartControl(parent);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent,
                HelpUtil.getContextId(_contextHelpId, getEditorDescriptor().getPluginId()));
    }

    public String getContextHelpId()
    {
        return _contextHelpId;
    }
}

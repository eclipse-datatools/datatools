/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.pages.privileges;

import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.PrivilegesBlock;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.PrivilegesSection;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.ASATableSchemaEditorPage;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectEditModel;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.IErrorItem;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;


/**
 * "Privileges" page for ASA table schema editor.
 * 
 * @author Idull
 * 
 */
public class ASAPrivilegesEditorPage extends ASATableSchemaEditorPage implements ISchemaObjectEditorPage
{

    private PrivilegesSection _section;

    class ASAPrivilegesSection extends PrivilegesSection
    {

        public ASAPrivilegesSection(FormToolkit toolkit, String title, Display display, boolean isCollapseable,
                boolean isCollapsed, int estyle, IManagedForm managedForm, ISchemaObjectEditorPage page)
        {
            super(toolkit, title, display, isCollapseable, isCollapsed, estyle, managedForm, page);
        }

        protected synchronized PrivilegesBlock getOrCreatePermissionsBlock(Composite parent,
                ISchemaObjectEditModel model, ISchemaObjectEditorPage page)
        {
            if (_permissionsBlock == null)
            {
                _permissionsBlock = new ASATablePrivilegesBlock(parent, model, ASAPrivilegesEditorPage.this);
            }
            return _permissionsBlock;
        }

    }

    public ASAPrivilegesEditorPage()
    {
    }

    public ASAPrivilegesEditorPage(FormEditor editor, String id, String title)
    {
        super(editor, id, title);
    }

    public ASAPrivilegesEditorPage(String id, String title)
    {
        super(id, title);
    }

    protected void createFormContent(IManagedForm managedForm)
    {

        super.createFormContent(managedForm);
        managedForm.getForm().setText(Messages.ASAPrivilegesEditorPage_privileges);
        Composite parent = managedForm.getForm().getBody();
        parent.setLayout(new GridLayout());
        FormToolkit toolKit = managedForm.getToolkit();
        GridLayout layout = new GridLayout();
        parent.setLayout(layout);

        Composite outter = toolKit.createComposite(parent);
        outter.setLayout(new GridLayout());
        outter.setLayoutData(new GridData(GridData.FILL_BOTH));

        _section = new ASAPrivilegesSection(toolKit, Messages.ASAPrivilegesEditorPage_permission_detail, parent.getDisplay(), false, false, SWT.NONE,
                managedForm, ASAPrivilegesEditorPage.this);
        _section.createControl(outter, 1, null);

        //managedForm.getForm().reflow(true);
    }

    public IErrorItem[] validate()
    {
        return null;
    }

    public void modelRegenerated()
    {
        super.modelRegenerated();
        if (!isPageOpened())
        {
            return;
        }
        _section.getPermissionsBlock().refreshDetailPages();
    }

    public void refresh()
    {
        if (!isPageOpened())
        {
            return;
        }
        super.refresh();
        PrivilegesBlock block = _section.getPermissionsBlock();
        if (block != null)
        {
            block.refresh();
        }
    }

}
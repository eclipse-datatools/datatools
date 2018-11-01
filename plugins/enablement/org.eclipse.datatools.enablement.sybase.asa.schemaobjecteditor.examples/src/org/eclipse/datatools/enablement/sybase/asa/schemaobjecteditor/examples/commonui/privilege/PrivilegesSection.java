/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege;

import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectEditModel;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorInput;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorPage;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.common.CollapseableSection;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * The privileges section on "Privileges" page
 * 
 * @author Idull
 */
public abstract class PrivilegesSection extends CollapseableSection
{
    private IManagedForm            _managedForm;
    private ISchemaObjectEditorPage _page;
    protected PrivilegesBlock       _permissionsBlock;

    public PrivilegesSection(FormToolkit toolkit, String title, Display display, boolean isCollapseable,
            boolean isCollapsed, int estyle, IManagedForm managedForm, ISchemaObjectEditorPage page)
    {
        super(toolkit, title, display, isCollapseable, isCollapsed, estyle);
        _managedForm = managedForm;
        _page = page;
    }

    public void createSectionContent(Composite parent)
    {
        getSection().setLayoutData(new GridData(GridData.FILL_BOTH));
        getSection().setLayout(new GridLayout());

        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        parent.setLayout(layout);
        parent.setLayoutData(new GridData(GridData.FILL_BOTH));

        ISchemaObjectEditorInput input = (ISchemaObjectEditorInput) _page.getEditor().getEditorInput();

        PrivilegesBlock permissionBlock = getOrCreatePermissionsBlock(parent, input.getEditModelObject(), _page);
        permissionBlock.createContent(_managedForm);
    }

    protected abstract PrivilegesBlock getOrCreatePermissionsBlock(Composite parent, ISchemaObjectEditModel model,
            ISchemaObjectEditorPage page);

    public PrivilegesBlock getPermissionsBlock()
    {
        return _permissionsBlock;
    }
}

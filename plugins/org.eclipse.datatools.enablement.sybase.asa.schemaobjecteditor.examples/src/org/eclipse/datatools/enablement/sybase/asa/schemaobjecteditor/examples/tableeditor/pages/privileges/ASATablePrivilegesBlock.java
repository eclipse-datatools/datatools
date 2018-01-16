/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.pages.privileges;

import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.IPrivilegeState;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.PrivilegesDetailPage;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.PrivilegesTreeViewerInput;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.TablePrivilegesBlock;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectEditModel;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorInput;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.SchemaObjectEditorPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IDetailsPageProvider;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.TableWrapLayout;


/**
 * Privileges block for ASA table schema eiditor's privilege page
 * 
 * @author Idull
 * 
 */
public class ASATablePrivilegesBlock extends TablePrivilegesBlock
{
    private static final String FONT_STYLE = "Tahoma"; //$NON-NLS-1$
    private static final int    FONT_SIZE  = 9;

    public ASATablePrivilegesBlock(Composite parent, ISchemaObjectEditModel model, SchemaObjectEditorPage page)
    {
        super(parent, model, page);
    }

    protected void registerPages(DetailsPart detailsPart)
    {
        detailsPart.setPageProvider(new IDetailsPageProvider()
        {
            public IDetailsPage getPage(Object key)
            {
                ISchemaObjectEditorInput editorInput = (ISchemaObjectEditorInput) ((IEditorPart) _page.getEditor())
                        .getEditorInput();
                if (key instanceof Table)
                {
                    PrivilegesDetailPage page = new ASATablePrivilegesDetailPage(_managedForm.getToolkit(), _model,
                            (SQLObject) key, new ASATablePrivilegesDetailTableMetaData(),
                            new PrivilegesTreeViewerInput(_model), new ASAPrivilegeStateLookup());
                    _detailsPages.put(key, page);
                    return page;
                }
                else if (key instanceof Column)
                {
                    PrivilegesDetailPage page = new ASAColumnPrivilegesDetailPage(_managedForm.getToolkit(), _model,
                            (SQLObject) key, new ASAColumnPrivilegesDetailTableMetaData(),
                            new PrivilegesTreeViewerInput(_model), new ASAPrivilegeStateLookup());
                    _detailsPages.put(key, page);
                    return page;
                }
                return null;
            }

            public Object getPageKey(Object object)
            {
                if (object != null)
                {
                    return object;
                }
                return null;
            }
        });
    }

    /**
     * Creates the "Legend" part
     * 
     * @param toolkit
     * @parem parent
     */
    protected void createAdditionalPart(FormToolkit toolkit, Composite parent)
    {
        //toolkit.createLabel(parent, "");//$NON-NLS-1$
        toolkit.createLabel(parent, Messages.PrivilegePage_legends);

        Composite legendComp = toolkit.createComposite(parent);
        TableWrapLayout tLayout = new TableWrapLayout();
        tLayout.numColumns = 6;
        legendComp.setLayout(tLayout);
        GridData gd = new GridData();
        gd.horizontalIndent = 20;
        legendComp.setLayoutData(gd);

        Label g = toolkit.createLabel(legendComp, IPrivilegeState.PRIVILEGES_DISPLAY_NAME[IPrivilegeState.GRANTED_PRIVILEGE] + ":");
        FontData fd = new FontData(FONT_STYLE, FONT_SIZE, SWT.BOLD);
        Font font = new Font(g.getDisplay(), fd);
        g.setFont(font);

        toolkit.createLabel(legendComp, Messages.PrivilegePage_granted);
        Label go = toolkit.createLabel(legendComp, "     "+IPrivilegeState.PRIVILEGES_DISPLAY_NAME[IPrivilegeState.GRANTED_WITH_GRANTOPTION_PRIVILEGE] + ":");//$NON-NLS-1$
        go.setFont(font);

        toolkit.createLabel(legendComp, Messages.PrivilegePage_grant_with_option);
        Label i = toolkit.createLabel(legendComp, "     "+IPrivilegeState.PRIVILEGES_DISPLAY_NAME[IPrivilegeState.INHERITED_PRIVILEGE] + ":");
        i.setFont(font);

        toolkit.createLabel(legendComp, Messages.PrivilegePage_inherited);
    }
}

/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.pages.privilege;

import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.IPrivilegeStateLookup;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.PrivilegesTreeViewerInput;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectEditModel;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorPage;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.SchemaObjectEditorInput;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.SchemaObjectEditorPage;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.TableWrapLayout;


/**
 * 
 * @author Hui Cao
 * 
 */
public abstract class ProceduralObjectPrivilegePage extends SchemaObjectEditorPage implements ISchemaObjectEditorPage
{
    protected static final String FONT_STYLE = "Tahoma"; //$NON-NLS-1$
    protected static final int    FONT_SIZE  = 9;

    protected RoutinePrivilegesDetailPage _detailPage = null; 
    protected SQLObject _mainObject;
    protected ISchemaObjectEditModel _editModelObject;
    protected Schema _schema;
    protected Routine _oldRoutine;
    protected DatabaseDefinition _databaseDefinition;

    public ProceduralObjectPrivilegePage()
    {
        // TODO Auto-generated constructor stub
    }

    public ProceduralObjectPrivilegePage(FormEditor editor, String id, String title)
    {
        super(editor, id, title);
        // TODO Auto-generated constructor stub
    }

    public ProceduralObjectPrivilegePage(String id, String title)
    {
        super(id, title);
        // TODO Auto-generated constructor stub
    }
    
    public void init(IEditorSite site, IEditorInput input) throws PartInitException
    {
        super.init(site, input);
        setPageInput(input);
    }

    private void setPageInput(IEditorInput input)
    {
        if (input instanceof SchemaObjectEditorInput)
        {
            _editModelObject = ((SchemaObjectEditorInput)input).getEditModelObject();
            _mainObject = _editModelObject.getMainSQLObject();
            _oldRoutine = (Routine)_editModelObject.getSchemaObjectImmutableModel().getMainSQLObject();
            _schema = _oldRoutine.getSchema();
            _databaseDefinition = ModelUtil.getDatabaseDefinition(_oldRoutine);
        }
    }

    /**
     * Refreshes the model
     */
    public void refresh()
    {
        super.refresh();
        _mainObject = null;
        _editModelObject = null;
        setPageInput(getEditorInput());
        
        _detailPage.reInit(_editModelObject, _mainObject, new PrivilegesTreeViewerInput(_editModelObject, supportsRole()));
    }


    protected void createFormContent(IManagedForm managedForm)
    {
        super.createFormContent(managedForm);
        
        managedForm.getForm().setText(Messages.ProceduralObjectPrivilegePage_page_name);
        Composite parent = managedForm.getForm().getBody();
        parent.setLayout(new GridLayout());
        FormToolkit toolKit = managedForm.getToolkit();
        GridLayout layout = new GridLayout();
        parent.setLayout(layout);

        Composite outter = toolKit.createComposite(parent);
        layout = new GridLayout();
        layout.numColumns = 1;
        outter.setLayout(layout);
        outter.setLayoutData(new GridData(GridData.FILL_BOTH));

        Composite additionalComp = toolKit.createComposite(outter);
        layout = new GridLayout();
        layout.marginWidth = 0;
        additionalComp.setLayout(layout);
        additionalComp.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false));
        createAdditionalPart(toolKit, additionalComp);
        
        RoutinePrivilegesTableMetaData meta = new RoutinePrivilegesTableMetaData();
        PrivilegesTreeViewerInput input = new PrivilegesTreeViewerInput(_editModelObject, supportsRole());
        IPrivilegeStateLookup stateProvider = createRoutinePrivilegeStateLookup();
        _detailPage = createDetailsPage(meta, input, stateProvider);
        Composite treeComp = toolKit.createComposite(outter);
        layout = new GridLayout();
        layout.marginWidth = 0;
        treeComp.setLayout(layout);
        treeComp.setLayoutData(new GridData(GridData.FILL_BOTH));
        _detailPage.createContents(treeComp);
        managedForm.addPart(_detailPage);
        
        managedForm.getForm().reflow(true);
    }

    protected RoutinePrivilegesDetailPage createDetailsPage(RoutinePrivilegesTableMetaData meta,
            PrivilegesTreeViewerInput input, IPrivilegeStateLookup stateProvider)
    {
        return new RoutinePrivilegesDetailPage((ISchemaObjectEditor)getEditor(), getEditor().getToolkit(), _editModelObject, _mainObject, meta, input, stateProvider );
    }

    protected abstract IPrivilegeStateLookup createRoutinePrivilegeStateLookup();

    /**
     * Creates the "Legend" part
     * 
     * @param toolkit
     * @parem parent
     */
    protected void createAdditionalPart(FormToolkit toolkit, Composite parent)
    {
        toolkit.createLabel(parent, ""); //$NON-NLS-1$
        toolkit.createLabel(parent, Messages.ProceduralObjectPrivilegePage_legends);

        Composite legendComp = toolkit.createComposite(parent);
        TableWrapLayout tLayout = new TableWrapLayout();
        tLayout.numColumns = 4;
        legendComp.setLayout(tLayout);
        GridData gd = new GridData();
        gd.horizontalIndent = 20;
        legendComp.setLayoutData(gd);

        Label g = toolkit.createLabel(legendComp, "G:"); //$NON-NLS-1$
        FontData fd = new FontData(FONT_STYLE, FONT_SIZE, SWT.BOLD);
        Font font = new Font(g.getDisplay(), fd);
        g.setFont(font);

        toolkit.createLabel(legendComp, Messages.ProceduralObjectPrivilegePage_granted);
        Label i = toolkit.createLabel(legendComp, "I:"); //$NON-NLS-1$
        i.setFont(font);

        toolkit.createLabel(legendComp, Messages.ProceduralObjectPrivilegePage_inherited);
    }

    protected abstract boolean supportsRole();
    
    public boolean isDirty()
    {
        return _detailPage != null && _detailPage.isDirty();
    }
}

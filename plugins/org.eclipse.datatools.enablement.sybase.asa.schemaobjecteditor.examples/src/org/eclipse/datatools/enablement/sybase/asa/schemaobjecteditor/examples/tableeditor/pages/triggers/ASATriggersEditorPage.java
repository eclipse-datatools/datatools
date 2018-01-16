/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.pages.triggers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseActionTime;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTrigger;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.Constants;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.action.RefreshSchemaObjectsAction;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.commonui.ISchemaObjectsViewerMetaData;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.commonui.ListSchemaObjectsSection;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.commonui.SchemaObjectsViewerContentProvider;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorInput;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorPage;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.SchemaObjectEditorPage;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;


/**
 * "Triggers" page of ASA table schema editor
 * 
 * @author Idull
 */
public class ASATriggersEditorPage extends SchemaObjectEditorPage implements ISchemaObjectEditorPage
{
    ListSchemaObjectsSection _triggersSection;

    public ASATriggersEditorPage()
    {

    }

    public ASATriggersEditorPage(FormEditor editor, String id, String title)
    {
        super(editor, id, title);
    }

    public ASATriggersEditorPage(String id, String title)
    {
        super(id, title);
    }

    protected void createFormContent(IManagedForm managedForm)
    {
        ISchemaObjectEditorInput editorInput = (ISchemaObjectEditorInput) getEditorInput();
        SQLObject catalogObj = editorInput.getEditModelObject().getSchemaObjectImmutableModel().getMainSQLObject();
        IToolBarManager toolBar = managedForm.getForm().getToolBarManager();
        RefreshSchemaObjectsAction refreshAction = null;
        if (catalogObj instanceof ICatalogObject)
        {
            refreshAction = new RefreshSchemaObjectsAction((ICatalogObject) catalogObj);
            refreshAction.setText(Messages.ASATriggersEditorPage_refresh_all);
            toolBar.add(refreshAction);
        }
        managedForm.getForm().updateToolBar();
        super.createFormContent(managedForm);

        managedForm.getForm().setText(Messages.ASATriggersEditorPage_triggers);

        Composite parent = managedForm.getForm().getBody();
        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        layout.horizontalSpacing = 10;
        parent.setLayout(layout);

        FormToolkit toolKit = managedForm.getToolkit();
        _triggersSection = new TriggersSection(toolKit, Messages.ASATriggersEditorPage_triggers, parent.getDisplay(),
                SWT.NONE,
                "", //$NON-NLS-2$
                (ISchemaObjectEditor) getEditor(), new TriggersSectionMetaData(),
                SchemaObjectsViewerContentProvider.TRIGGERS_TYPE, new TriggersViewerLabelProvider(
                        getDatabaseIdentifier()));
        _triggersSection.createControl(parent, 1, null);
        if (refreshAction != null)
        {
            refreshAction.setSectionToBeRefreshed(_triggersSection);
        }
        toolKit.paintBordersFor(parent);
    }

    public void refresh()
    {
        if (!isPageOpened())
        {
            return;
        }
        super.refresh();
        _triggersSection.refresh();
    }

    public void revert()
    {
        super.revert();
    }

	public void setFocus(int itemType, Object item) 
	{
		_triggersSection.setSelection(item);
	}
}

class TriggersSection extends ListSchemaObjectsSection
{

    public TriggersSection(FormToolkit toolkit, String title, Display display, int estyle, String descString,
            ISchemaObjectEditor editor, ISchemaObjectsViewerMetaData meta, int objType,
            ITableLabelProvider labelProvider)
    {
        super(toolkit, title, display, estyle, descString, editor, meta, objType, labelProvider,
                Constants.ASA_TABLE_EDITOR_COLUMNS_PAGE_ID); //$NON-NLS-1$
    }

    protected IStructuredContentProvider getContentProvider(int objType)
    {
        SchemaObjectsViewerContentProvider contentProvider = new SchemaObjectsViewerContentProvider(objType)
        {
            public Object[] getElements(Object inputElement)
            {
                if (inputElement == null)
                {
                    return new Object[0];
                }
                if (inputElement instanceof Table)
                {
                    Table table = (Table) inputElement;
                    switch (_objsType)
                    {
                        case INDEXES_TYPE:
                            if (table.getIndex() != null)
                            {
                                return getSortedIndexes(table);
                            }
                            break;
                        case TRIGGERS_TYPE:
                            //Show watcom triggers together
                            List watcomTriggers = new ArrayList();
                            List tsqlTriggers = new ArrayList();
                            Object[] allTriggers = getSortedTriggers(table);
                            for (int i = 0; i < allTriggers.length; i++)
                            {
                                if (allTriggers[i] instanceof SybaseASABaseTrigger)
                                {
                                    SybaseASABaseTrigger trigger = (SybaseASABaseTrigger) allTriggers[i];
                                    if (trigger.getSybaseASABaseActionTime().getValue() == SybaseASABaseActionTime.ASE)
                                    {
                                        tsqlTriggers.add(trigger);
                                    }
                                    else
                                    {
                                        watcomTriggers.add(trigger);
                                    }
                                }
                            }
                            tsqlTriggers.addAll(watcomTriggers);
                            return tsqlTriggers.toArray(new Trigger[tsqlTriggers.size()]);
                        default:
                            break;
                    }
                }
                return new Object[0];
            }
        };
        return contentProvider;
    }
}
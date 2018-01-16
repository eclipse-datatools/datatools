/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.pages.indexes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.Constants;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.action.RefreshSchemaObjectsAction;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.commonui.ISchemaObjectsViewerMetaData;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.commonui.ListSchemaObjectsSection;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.commonui.SchemaObjectsViewerContentProvider;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.utils.SQLObjectComparator;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Table;
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
 * ASA table schema editor "Indexes" page
 * 
 * @author Idull
 */
public class ASAIndexesEditorPage extends SchemaObjectEditorPage implements ISchemaObjectEditorPage
{
    ListSchemaObjectsSection _indexesSection;

    public ASAIndexesEditorPage()
    {

    }

    public ASAIndexesEditorPage(FormEditor editor, String id, String title)
    {
        super(editor, id, title);
    }

    public ASAIndexesEditorPage(String id, String title)
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
            refreshAction.setText(Messages.ASAIndexesEditorPage_refresh_all);
            toolBar.add(refreshAction);
        }
        managedForm.getForm().updateToolBar();

        super.createFormContent(managedForm);

        managedForm.getForm().setText(Messages.ASAIndexesEditorPage_indexes);

        Composite parent = managedForm.getForm().getBody();
        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        layout.horizontalSpacing = 10;
        parent.setLayout(layout);

        FormToolkit toolKit = managedForm.getToolkit();
        _indexesSection = new IndexesSection(toolKit, Messages.ASAIndexesEditorPage_indexes, parent.getDisplay(), SWT.NONE, "", //$NON-NLS-2$
                (ISchemaObjectEditor) getEditor(), new IndexesSectionMetaData(),
                SchemaObjectsViewerContentProvider.INDEXES_TYPE, new IndexesViewerLabelProvider());
        _indexesSection.createControl(parent, 1, null);
        if(refreshAction != null)
        {
            refreshAction.setSectionToBeRefreshed(_indexesSection);
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
        _indexesSection.refresh();
    }

    public void revert()
    {
        super.revert();
    }
    
	public void setFocus(int itemType, Object item) 
	{
		_indexesSection.setSelection(item);
	}
}

class IndexesSection extends ListSchemaObjectsSection
{

    public IndexesSection(FormToolkit toolkit, String title, Display display, int estyle, String descString,
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
                            List indexes = new ArrayList();
                            if (table.getIndex() != null)
                            {
                                Iterator iter = table.getIndex().iterator();
                                while (iter.hasNext())
                                {
                                    Index i = (Index) iter.next();
                                    if (!i.isSystemGenerated())
                                    {
                                        indexes.add(i);
                                    }
                                }
                                Collections.sort(indexes, new SQLObjectComparator());
                                return indexes.toArray(new Index[indexes.size()]);
                            }
                            break;
                        case TRIGGERS_TYPE:
                            if (table.getTriggers() != null)
                            {
                                return getSortedTriggers(table);
                            }
                            break;
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

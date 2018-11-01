/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectEditModel;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectEditorModelListener;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorInput;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.SchemaObjectEditorPage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.forms.AbstractFormPart;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.MasterDetailsBlock;

/**
 * The privileges block on "Privileges" page
 * 
 * @author Idull
 */
public class PrivilegesBlock extends MasterDetailsBlock
{
    private Composite                        _parent;
    protected ISchemaObjectEditModel         _model;
    protected IManagedForm                   _managedForm;
    protected SchemaObjectEditorPage         _page;
    /**
     * Store all the detail pages, the key is the object itself.<br>
     * After saving the editor, these detail pages SHOULD be refreshed. 
     */
    protected Map                            _detailsPages;
    protected IFormPart                      _masterPart = new AbstractFormPart()
                                                         {
                                                             public void refresh()
                                                             {
                                                                 super.refresh();
                                                                 PrivilegesBlock.this.refresh();
                                                             }
                                                         };
    /**
     * The listener which listens to the model change event, when the event source is constraint, refresh the master
     * part
     */
    private ISchemaObjectEditorModelListener _listener   = new ISchemaObjectEditorModelListener()
                                                         {
                                                             public void notifyChanged(Notification msg)
                                                             {
                                                                 PrivilegesBlock.this.notifyChanged(msg);
                                                             }
                                                         };

    public PrivilegesBlock(Composite parent, ISchemaObjectEditModel model, SchemaObjectEditorPage page)
    {
        super();
        _parent = parent;
        _model = model;
        _page = page;
        _detailsPages = new HashMap();
    }

    /**
     * Refreshes the master part
     *
     */
    public void refresh()
    {
        
    }

    /**
     * Subclass can override this method to do something when the model changes
     * 
     * @param msg
     */
    public void notifyChanged(Notification msg)
    {

    }

    protected void createMasterPart(final IManagedForm managedForm, Composite parent)
    {
        ((ISchemaObjectEditor) _page.getEditor()).getEditorHandler().getNotifier().addModelListener(_listener);
    }

    protected void createToolBarActions(IManagedForm managedForm)
    {
        if (_parent != null)
        {
            sashForm.setParent(_parent);
        }
        sashForm.setOrientation(SWT.VERTICAL);
        managedForm.getForm().reflow(true);
        sashForm.setWeights(new int[]
        {
            15, 85
        });
    }

    /**
     * Subclass should override this method to provide pages
     */
    protected void registerPages(DetailsPart detailsPart)
    {

    }
    
    /**
	 * When the model is regenerated, the detail pages should be refresh to make
	 * things consistence. For table, since all the instances are changed
	 * (table, column), all the detail pages will be recreated.<br>
	 * 
	 */ 
    public void refreshDetailPages()
    {
        Iterator detailPageIter = _detailsPages.values().iterator();
        while (detailPageIter.hasNext())
        {
            Object obj = detailPageIter.next();
            if (obj instanceof PrivilegesDetailPage)
            {
                PrivilegesDetailPage page = (PrivilegesDetailPage) obj;
                ISchemaObjectEditorInput editorInput = (ISchemaObjectEditorInput) ((IEditorPart) _page.getEditor())
                        .getEditorInput();

                page.setSqlObj(editorInput.getEditModelObject().getMainSQLObject());
                IPrivilegesTreeViewerInput input = page.getInput();
                input.refresh();
                page.getTableTreeViewer().refresh();
            }
        }
    }
}

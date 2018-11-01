/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core;

import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;

/**
 * A schema object editor page whose content is a nested editor
 * 
 * @author Idull
 */
public abstract class NestedEditorPage extends SchemaObjectEditorPage
{
    protected Control _control = null;
    
    public NestedEditorPage()
    {
        super();
    }

    public NestedEditorPage(FormEditor editor, String id, String title)
    {
        super(editor, id, title);
    }

    public NestedEditorPage(String id, String title)
    {
        super(id, title);
    }

    protected void createFormContent(IManagedForm managedForm)
    {
        //do nothing as we're using the nested editor's control
    }

    
    public Control getPartControl()
    {
        return _control;
    }
    
    public void setPartControl(Control control)
    {
        _control = control;
    }
    /**
     * Subclass should implement this method to reutrn the editor part in this page
     * 
     * @return
     */
    public abstract IEditorPart getNestedEditor();

    /**
     * Subclass should implement this method to reutrn the editor input for the nested editor
     * 
     * @return
     */
    public abstract IEditorInput getNestedEditorInput();
    
    public void init(IEditorSite site, IEditorInput input) throws PartInitException
    {
        super.init(site, input);
        getNestedEditor().init(site, input);
    }
    
    
    public boolean isEditor()
    {
        return true;
    }
    
    
    public boolean isDirty()
    {
        IEditorPart editor = getNestedEditor();
        return (editor != null && editor.isDirty());
    }
    
    /**
     * Notifies this page that a page switch event has occurred. This is to workaround the problem that in FormEditor's
     * pageChange implementation, setActive(true) is called before setActive(false).
     * 
     */
    public void aboutToLeave()
    {
        //by default do nothing
    }
}

/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.action;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditor;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.ISchemaObjectEditorHandler;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.util.Images;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPropertyListener;

/**
 * Reverts the editor to the begining status
 * 
 * @author Idull
 */
public class RevertSchemaEditorAction extends Action implements IPropertyListener, IHandler
{
    protected ISchemaObjectEditor _editor;

    public RevertSchemaEditorAction()
    {
        super();
        setText(Messages.RevertSchemaEditorAction_revert);
        setImageDescriptor(Images.DESC_REVERT);
    }

    public void run()
    {
        if (_editor == null)
        {
            return;
        }
        ISchemaObjectEditorHandler handler = _editor.getEditorHandler();
        if (handler == null)
        {
            return;
        }
        handler.revert();
    }

    public void setEditor(ISchemaObjectEditor editor)
    {
        this._editor = editor;
    }

    public void propertyChanged(Object source, int propId)
    {
        if (source != _editor || propId != IEditorPart.PROP_DIRTY)
        {
            return;
        }
        if (_editor.isDirty())
        {
            setEnabled(true);
        }
        else
        {
            setEnabled(false);
        }
    }

    /*
     * Since this class will be casted in HandlerProxy.loadHandler(), it should implements IHandler. The four methods
     * blow are the implementation of IHandler's.
     */
    public void addHandlerListener(IHandlerListener handlerListener)
    {

    }

    public void dispose()
    {

    }

    public Object execute(ExecutionEvent event) throws ExecutionException
    {
        return null;
    }

    public void removeHandlerListener(IHandlerListener handlerListener)
    {

    }
}

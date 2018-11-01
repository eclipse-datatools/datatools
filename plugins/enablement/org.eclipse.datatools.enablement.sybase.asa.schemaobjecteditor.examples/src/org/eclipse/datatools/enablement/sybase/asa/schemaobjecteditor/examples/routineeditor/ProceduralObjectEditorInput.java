/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor;

import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.model.ProceduralObjectEditModel;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.routineeditor.ui.ProcEditorInput;
import org.eclipse.datatools.sqltools.schemaobjecteditor.model.ISchemaObjectEditModel;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core.SchemaObjectEditorInput;
import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.extensions.IEditorDescriptor;


/**
 * 
 * @author Hui Cao
 * 
 */
public class ProceduralObjectEditorInput extends SchemaObjectEditorInput
{

    private String defaultPageId = null;

    public ProceduralObjectEditorInput(IEditorDescriptor editor, ISchemaObjectEditModel modelObject, DatabaseIdentifier identifier)
    {
        super(editor, modelObject, identifier);
        // TODO Auto-generated constructor stub
    }


    public Object getAdapter(Class adapter)
    {
        if (adapter == ProcEditorInput.class)
        {
            ProcIdentifier procId = ((ProceduralObjectEditModel)getEditModelObject()).getProcIdentifier();
            if (procId != null)
            {
                return new ProcEditorInput(procId);
            }
        }
        else if(adapter == ProcIdentifier.class)
        {
        	return ((ProceduralObjectEditModel)getEditModelObject()).getProcIdentifier();
        }
        return super.getAdapter(adapter);
    }
    
    public String getName()
    {
        return ((ProceduralObjectEditModel)getEditModelObject()).getProcIdentifier().getDisplayString();
    }
    
    public String getToolTipText()
    {
        return ((ProceduralObjectEditModel)getEditModelObject()).getProcIdentifier().getLongDisplayString();
    }


    public String getDefaultPageId()
    {
        return defaultPageId;
    }


    public void setDefaultPageId(String defaultPageId)
    {
        this.defaultPageId = defaultPageId;
    }
    
    
    
}

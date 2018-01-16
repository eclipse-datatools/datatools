/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.pages.privilege;

import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.commonui.privilege.IPrivilegeStateLookup;
import org.eclipse.ui.forms.editor.FormEditor;


/**
 * 
 * @author Hui Cao
 * 
 */
public class ASARoutinePrivilegePage extends ProceduralObjectPrivilegePage
{

    public ASARoutinePrivilegePage()
    {
        // TODO Auto-generated constructor stub
    }

    public ASARoutinePrivilegePage(FormEditor editor, String id, String title)
    {
        super(editor, id, title);
        // TODO Auto-generated constructor stub
    }

    public ASARoutinePrivilegePage(String id, String title)
    {
        super(id, title);
        // TODO Auto-generated constructor stub
    }

    protected IPrivilegeStateLookup createRoutinePrivilegeStateLookup()
    {
        return new ASARoutinePrivilegeStateLookup();
    }

    protected boolean supportsRole()
    {
        return false;
    }

}

/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.pages.source;

import org.eclipse.datatools.sqltools.routineeditor.ui.RoutineEditor;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorActionConstants;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.part.MultiPageEditorSite;


/**
 * This class is a RoutineEditor for ASA/IQ routine source editor
 * @author songj
 */
public class ASARoutineSourceEditor extends RoutineEditor
{
    public static final String SCHEMA_EDITOR_NESTED_ASA_PROC_SOURCE_ID = "org.eclipse.datatools.sqltools.schemaobjecteditor.nested.asa.procedural.source.editor";

    private IEditorSite        _editorSite;

    public ASARoutineSourceEditor()
    {
        setDocumentProvider(new ASARoutineSourceDocumentProvider());
    }

    protected void fillContextMenu(IMenuManager menu)
    {
        super.fillContextMenu(menu);
        menu.remove(ISQLEditorActionConstants.DEBUG_ACTION_ID);
    }

    protected void initializeEditor()
    {
        super.initializeEditor();
        //set a new contextMenuId to the source Editor to disable the setting breakpoint action in context menu.
        setRulerContextMenuId("#ASARoutineEditorRulerContext");
    }

    public IEditorSite getEditorSite()
    {
        //Set a new id to editorSite for the source Editor to disable the action of setting breakpoint by double clicking.
        if (_editorSite == null)
        {
            IEditorPart parentPart = getParentEditor();

            if (parentPart != null && parentPart instanceof MultiPageEditorPart)
            {
                _editorSite = new MultiPageEditorSite((MultiPageEditorPart) parentPart, (IEditorPart) this)
                {
                    public String getId()
                    {
                        return SCHEMA_EDITOR_NESTED_ASA_PROC_SOURCE_ID;
                    }
                };
            }
        }
        if (_editorSite == null)
        {
            _editorSite = super.getEditorSite();
        }
        return _editorSite;
    }
    
    public boolean isDebugEditor()
    {
        return false;
    }
    
//    public ISourceViewer getSV()
//    {
//    	ISourceViewer sv = super.getSV();
//    	sv.setEditable(false);
//    	return sv;
//    }
}

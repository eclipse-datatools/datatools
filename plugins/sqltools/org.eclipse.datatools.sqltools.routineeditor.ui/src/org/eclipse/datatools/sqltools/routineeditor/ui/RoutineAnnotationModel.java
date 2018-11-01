/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.routineeditor.ui;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.sqleditor.EditorConstants;
import org.eclipse.datatools.sqltools.sqleditor.internal.editor.SQLUpdater;
import org.eclipse.ui.texteditor.ResourceMarkerAnnotationModel;

/**
 * This is the default annotation model for routine objects. 
 * @author Hui Cao
 * 
 */
public class RoutineAnnotationModel extends ResourceMarkerAnnotationModel
{

    protected ProcIdentifier             _procIdentifier;
    protected IWorkspace                 _workspace;

    /**
     * 
     */
    public RoutineAnnotationModel(ProcIdentifier procid)
    {
        super(ResourcesPlugin.getWorkspace().getRoot());
        _workspace = ResourcesPlugin.getWorkspace();
        this._procIdentifier = procid;
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.texteditor.AbstractMarkerAnnotationModel#isAcceptable(org.eclipse.core.resources.IMarker)
     */
    protected boolean isAcceptable(IMarker marker)
    {
        boolean acceptable = super.isAcceptable(marker);
        if (acceptable)
        {
            try
            {
                String type = marker.getType();
                if (EditorConstants.SYNTAX_MARKER_TYPE.equals(type)
                || EditorConstants.PORTABILITY_MARKER_TYPE.equals(type))
                {
                    String str = marker.getAttribute(SQLUpdater.SOURCE_ID, ""); //$NON-NLS-1$
                    String id = "(" + _procIdentifier.encode() + ")";
                    return str.indexOf(id) >= 0;
                }
            }
            catch (CoreException ex)
            {
            	RoutineEditorUIActivator.getDefault().log(ex);
            }
        }
        return false;
    }

    protected boolean isAffected(IMarkerDelta markerDelta)
    {
        return isAcceptable(markerDelta.getMarker());
    }

}

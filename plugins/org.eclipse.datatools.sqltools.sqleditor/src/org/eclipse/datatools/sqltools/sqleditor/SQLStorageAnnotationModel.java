/** Created on 2006-1-10
 *
 * Copyright (c) Sybase, Inc. 2004-2006   
 * All rights reserved.                                    
 */
package org.eclipse.datatools.sqltools.sqleditor;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.ui.texteditor.ResourceMarkerAnnotationModel;



/**
 * @author Hui Cao
 */
public class SQLStorageAnnotationModel extends ResourceMarkerAnnotationModel
{

    SQLEditorStorage _storage;
    IWorkspace                 _workspace;
    public static final String ATTR_STORAGE_ID         = SQLEditorPlugin.PLUGIN_ID + ".storageId";               //$NON-NLS-1$

    //TODO remove EditorConstantst.PORTABILITY_MARKER_TYPE
    public static final String PORTABILITY_MARKER_TYPE = SQLEditorPlugin.PLUGIN_ID + ".portabilitytask";

    public static final String SYNTAX_MARKER_TYPE = SQLEditorPlugin.PLUGIN_ID + ".syntaxproblem";

    /**
     * 
     */
    public SQLStorageAnnotationModel(SQLEditorStorage storage)
    {
        super(ResourcesPlugin.getWorkspace().getRoot());
        _workspace = ResourcesPlugin.getWorkspace();
        _storage = storage;
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
                if (SQLStorageAnnotationModel.SYNTAX_MARKER_TYPE.equals(type)
                || SQLStorageAnnotationModel.PORTABILITY_MARKER_TYPE.equals(type))
                {
                    String str = marker.getAttribute(ATTR_STORAGE_ID, ""); //$NON-NLS-1$
                    return _storage.getName() != null && _storage.getName().equals(str);
                }
            }
            catch (CoreException ex)
            {
            	SQLEditorPlugin.getDefault().log(ex);
            }
        }
        return false;
    }

    protected boolean isAffected(IMarkerDelta markerDelta)
    {
        return isAcceptable(markerDelta.getMarker());
    }

}

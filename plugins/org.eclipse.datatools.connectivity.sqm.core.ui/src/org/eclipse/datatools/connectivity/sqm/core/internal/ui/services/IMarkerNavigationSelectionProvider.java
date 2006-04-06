package org.eclipse.datatools.connectivity.sqm.core.internal.ui.services;

import org.eclipse.core.resources.IMarker;
import org.eclipse.ui.IEditorPart;

/**
 * @author ljulien
 */
public interface IMarkerNavigationSelectionProvider
{
    /**
     * Clients should used the "com.ibm.datatools.core.ui.markerNavigationProvider" extension point
     * Will be implemented by clients of wants to provide selection for Marker Navigation
     * @param the object associated with the Marker that should be selected
     * @return true if the client did select an object
     */
    public boolean provides (IEditorPart editor, IMarker marker);
    public void doGotoMarker(IMarker marker);
}

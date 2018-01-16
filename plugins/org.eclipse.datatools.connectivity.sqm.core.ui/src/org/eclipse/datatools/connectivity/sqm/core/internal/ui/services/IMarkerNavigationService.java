package org.eclipse.datatools.connectivity.sqm.core.internal.ui.services;

import org.eclipse.core.resources.IMarker;
import org.eclipse.ui.IEditorPart;

/**
 * @author ljulien
 */
public interface IMarkerNavigationService
{
    public void gotoMarker (IEditorPart editor, IMarker marker);
}

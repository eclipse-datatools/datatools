package org.eclipse.datatools.connectivity.sqm.core.internal.ui.services;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.IEditorPart;

/**
 * @author ljulien
 */
public class MarkerNavigationService implements IMarkerNavigationService
{
    private static List markerNavigationService = new LinkedList ();
    
    private void initializeProviders ()
    {
		try
        {
            IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
            IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint("org.eclipse.datatools.connectivity.sqm.core.ui", "markerNavigationProvider");  //$NON-NLS-1$//$NON-NLS-2$
            IExtension[] extensions = extensionPoint.getExtensions();
            for (int i=0; i<extensions.length; ++i) 
            {
            	IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
            	for (int j=0; j<configElements.length; ++j) 
            	{
            		if (configElements[j].getName().equals("provider")) //$NON-NLS-1$
            		{
            		    markerNavigationService.add(configElements[j].createExecutableExtension("class")); //$NON-NLS-1$
            		}
            	}
            }
        }
        catch (CoreException e)
        {
        }
    }
    
    private void selectWithProviders (IEditorPart editor, IMarker marker)
    {
        for (Iterator iterator = markerNavigationService.iterator(); iterator.hasNext ();)
        {
            IMarkerNavigationSelectionProvider provider = (IMarkerNavigationSelectionProvider) iterator.next();
            if (provider.provides(editor, marker))
            {
                provider.doGotoMarker(marker);
                break;
            }
        }
    }
    
    public MarkerNavigationService ()
    {
        initializeProviders();
    }
    
    public void gotoMarker(IEditorPart editor, IMarker marker)
    {
        try
        {
        	this.selectWithProviders(editor, marker);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

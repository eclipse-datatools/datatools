/**
 * Author: dpchou
 * Purpose: As demo code for EclipseWorld 2005, not liable for any consequences
 * as the result of using this code. 
 */
package org.eclipse.datatools.enablement.sybase.asa.virtual;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.VirtualNodeServiceFactory;
import org.eclipse.datatools.enablement.sybase.asa.providers.IDBEventsFolder;
import org.eclipse.datatools.enablement.sybase.asa.providers.IDataTypesFolder;
import org.eclipse.datatools.enablement.sybase.asa.providers.IVirtualNodeServiceFactory;
import org.eclipse.datatools.enablement.sybase.asa.providers.IWebServicesFolder;



public class NodeFactory extends VirtualNodeServiceFactory implements IVirtualNodeServiceFactory
	{
	    public IDBEventsFolder makeDBEventsFolder(String name, String displayName, Object parent)
	    {
	        return new DBEventsFolder (name, displayName, parent);
	    }

		public IWebServicesFolder makeWebServicesFolder(String name, String displayName, Object parent) {
	        return new WebServicesFolder (name, displayName, parent);
		}

		public IDataTypesFolder makeDataTypesFolder(String name, String displayName, Object parent) {
	        return new DataTypesFolder (name, displayName, parent);
		}
}

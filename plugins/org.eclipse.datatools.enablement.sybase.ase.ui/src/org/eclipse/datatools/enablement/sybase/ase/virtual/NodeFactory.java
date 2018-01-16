/**
 * Author: dpchou
 * Purpose: As demo code for EclipseWorld 2005, not liable for any consequences
 * as the result of using this code. 
 */
package org.eclipse.datatools.enablement.sybase.ase.virtual;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.providers.content.virtual.VirtualNodeServiceFactory;
import org.eclipse.datatools.enablement.sybase.ase.providers.ICatalogsFolder;
import org.eclipse.datatools.enablement.sybase.ase.providers.IDBEventsFolder;
import org.eclipse.datatools.enablement.sybase.ase.providers.IDataTypesFolder;
import org.eclipse.datatools.enablement.sybase.ase.providers.IVirtualNodeServiceFactory;
import org.eclipse.datatools.enablement.sybase.ase.providers.IWebServicesFolder;



public class NodeFactory extends VirtualNodeServiceFactory implements IVirtualNodeServiceFactory
	{

		public IWebServicesFolder makeWebServicesFolder(String name, String displayName, Object parent) {
	        return new WebServicesFolder (name, displayName, parent);
		}

		public IDataTypesFolder makeDataTypesFolder(String name, String displayName, Object parent) {
	        return new DataTypesFolder (name, displayName, parent);
		}

		public ICatalogsFolder makeCatalogsFolder(String name, String displayName, Object parent) {
	        return new CatalogsFolder (name, displayName, parent);
		}
}

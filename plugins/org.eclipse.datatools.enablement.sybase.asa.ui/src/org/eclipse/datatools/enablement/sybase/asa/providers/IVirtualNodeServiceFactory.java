/**
 * Author: dpchou
 * Purpose: As demo code for EclipseWorld 2005, not liable for any consequences
 * as the result of using this code. 
 */
package org.eclipse.datatools.enablement.sybase.asa.providers;

import org.eclipse.datatools.enablement.sybase.asa.virtual.NodeFactory;

public interface IVirtualNodeServiceFactory {
    public static final IVirtualNodeServiceFactory INSTANCE = new NodeFactory();
    
    public IDBEventsFolder makeDBEventsFolder(String name, String displayName, Object parent);
    public IWebServicesFolder makeWebServicesFolder (String name, String displayName, Object parent);
    public IDataTypesFolder makeDataTypesFolder (String name, String displayName, Object parent);
}

/**
 * Author: dpchou
 * Purpose: As demo code for EclipseWorld 2005, not liable for any consequences
 * as the result of using this code. 
 */
package org.eclipse.datatools.enablement.sybase.ase.providers;

import org.eclipse.datatools.enablement.sybase.ase.virtual.NodeFactory;

public interface IVirtualNodeServiceFactory {
    public static final IVirtualNodeServiceFactory INSTANCE = new NodeFactory();
    
    public IWebServicesFolder makeWebServicesFolder (String name, String displayName, Object parent);
    public IDataTypesFolder makeDataTypesFolder (String name, String displayName, Object parent);
    public ICatalogsFolder makeCatalogsFolder (String name, String displayName, Object parent);
}

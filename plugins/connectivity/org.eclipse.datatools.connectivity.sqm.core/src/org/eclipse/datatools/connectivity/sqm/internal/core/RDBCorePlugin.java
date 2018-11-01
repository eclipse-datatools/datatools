/*******************************************************************************
 * Copyright (c) 2001, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Actuate Corporation - added use of default DatabaseRecognizer (BZ 253523),
 *              plus OSGi stop and restart usage support
 *     Actuate Corporation - support for OSGi-less platform (Bugzilla 338997)
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.internal.core;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.datatools.connectivity.services.PluginResourceLocator;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentService;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinitionRegistry;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinitionRegistryImpl;
import org.eclipse.datatools.connectivity.sqm.internal.core.util.GenericCatalogMessages;
import org.eclipse.datatools.connectivity.sqm.internal.core.util.RDBCorePluginConstants;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;


public class RDBCorePlugin extends Plugin {
	public static String FK_MODELING_RELATIONSHIP = "FK_MODELING_RELATIONSHIP"; //$NON-NLS-1$
	public static String FK_PARENT_ROLE_NAME = "FK_PARENT_ROLE_NAME"; //$NON-NLS-1$
	public static String FK_CHILD_ROLE_NAME = "FK_CHILD_ROLE_NAME"; //$NON-NLS-1$
	public static String FK_PARENT_MULTIPLICITY = "FK_PARENT_MULTIPLICITY"; //$NON-NLS-1$
	public static String FK_CHILD_MULTIPLICITY = "FK_CHILD_MULTIPLICITY"; //$NON-NLS-1$
	public static String FK_PARENT_BY_QUALIFIED_NAME = "FK_PARENT_BY_QUALIFIED_NAME"; //$NON-NLS-1$
	public static String FK_IS_IDENTIFYING_RELATIONSHIP = "FK_IS_IDENTIFYING_RELATIONSHIP"; //$NON-NLS-1$
	public static String ZERO = "0"; //$NON-NLS-1$
	public static String ZERO_TO_ONE = "0..1"; //$NON-NLS-1$
	public static String ONE = "1"; //$NON-NLS-1$
	public static String ZERO_TO_MANY = "0..*"; //$NON-NLS-1$
	public static String ONE_TO_MANY = "1..*"; //$NON-NLS-1$
	public static String MANY = "*"; //$NON-NLS-1$
	
    public static final String PLUGIN_ID = "org.eclipse.datatools.connectivity.sqm.core";   //$NON-NLS-1$
	private static RDBCorePlugin plugin;
    private static IPath defaultWorkspace;

	public RDBCorePlugin() {
		plugin = this;
	}
	
	public static RDBCorePlugin getDefault() {
        if( plugin == null )
        {
            synchronized( RDBCorePlugin.class )
            {
                if( plugin == null )
                    new RDBCorePlugin();
            }
        }
        return plugin;

	}

	/* (non-Javadoc)
     * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop( BundleContext context ) throws Exception {
        DatabaseDefinitionRegistryImpl.releaseInstance();
        
        super.stop( context );
        plugin = null;
    }

    public DatabaseDefinitionRegistry getDatabaseDefinitionRegistry() {
		return DatabaseDefinitionRegistryImpl.getInstance();
	}
	
	public ContainmentService getContainmentService() {
		return ContainmentServiceImpl.INSTANCE;
	}

//	public ConnectionManager getConnectionManager() {
//		return ConnectionManagerImpl.INSTANCE;
//	}	
	
	protected void initializeDefaultPluginPreferences() {
	    getPluginPreferences().setDefault(RDBCorePluginConstants.LIMIT_ROWS_RETRIEVED, true);
	    getPluginPreferences().setDefault(RDBCorePluginConstants.MAX_ROW_RETRIEVED, 50);
	    getPluginPreferences().setDefault(RDBCorePluginConstants.MAX_LOB_LENGTH, 100);  
	}

    public static String getSymbolicName() {
        Bundle theBundle = getDefault().getBundle();
        return theBundle != null ? 
                theBundle.getSymbolicName() : 
                PLUGIN_ID;
    }
    
    /**
     * Returns the default workspace location of this plug-in.
     * @return the path of this plug-in's default workspace location.
     */
    public static IPath getDefaultStateLocation() {
        if( defaultWorkspace == null )
        {
            IPath wsPath = PluginResourceLocator.getPluginStateLocation( PLUGIN_ID );
            if( wsPath == null )
            {
                String errorMsg = GenericCatalogMessages.RDBCorePlugin_NO_DEFAULT_WORKSPACE;
                throw new IllegalStateException( errorMsg );
            }
            
            synchronized( RDBCorePlugin.class )
            {
                if( defaultWorkspace == null )
                    defaultWorkspace = wsPath;
            }
        }
        return defaultWorkspace;
    }

}

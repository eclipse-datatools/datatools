/*******************************************************************************
 * Copyright (c) 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentService;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinitionRegistry;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;

public class IBMPluginActivator extends Plugin {

    private static IBMPluginActivator instance;
    public static final String PLUGIN_ID = "org.eclipse.datatools.enablement.ibm"; //$NON-NLS-1$
    
    public static final String PRIVILEGE_PROPERTY = "PRIVILEGE_PROPERTY"; //$NON-NLS-1$
    public static final String PRIVILEGE_SYSTEM_GRANT = "PRIVILEGE_SYSTEM_GRANT"; //$NON-NLS-1$
    
    public static IBMPluginActivator getInstance() { return instance; }

    public IBMPluginActivator() {
        super();
        instance = this;
    }
    
    public DatabaseDefinitionRegistry getDatabaseDefinitionRegistry()
    {
        return RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry();
    }
    
    public ContainmentService getContainmentService()
    {
        return RDBCorePlugin.getDefault().getContainmentService();
    }
    
    public static void log( Throwable e )
    {
        getInstance().getLog().log( (e instanceof CoreException)
                ? ((CoreException)e).getStatus()
                : new Status( IStatus.ERROR, PLUGIN_ID, IStatus.OK, e.getMessage(), e ) );
    }
    
    /**
     * Method writeLog.
     * @param severity - the severity; one of IStatus.OK, IStatus.ERROR, IStatus.INFO, or IStatus.WARNING
     * @param code - the plug-in-specific status code, or OK
     * @param message - a human-readable message, localized to the current locale
     * @param exception a low-level exception, or null if not applicable
     */
    public void writeLog(int severity, int code, String message, Throwable exception) {
       if (message == null)
          message = ""; //$NON-NLS-1$

       getLog().log(
             new Status(severity, getBundle().getSymbolicName(), code, message, exception));
    }
}

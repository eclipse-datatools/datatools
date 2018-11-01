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
package org.eclipse.datatools.enablement.ibm.db2.luw;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;

public class DB2LUWPluginActivator extends Plugin {
    private static DB2LUWPluginActivator instance;
    public static final String PLUGIN_ID = "org.eclipse.datatools.enablement.ibm.db2.luw"; //$NON-NLS-1$
    
    public static DB2LUWPluginActivator getInstance() { return instance; }

    public DB2LUWPluginActivator() {
        super();
        instance = this;
    }
    
    /**
     * Convenience method for <code>log(String, int, Throwable, boolean)</code>
     * {
     * 
     * @param message
     *            message, if <code>null</code> the message from the throwable
     *            is used
     * @param throwable
     *            optional throwable as details
     */
    public void log(String message, Throwable throwable) {
        if (message == null) {
            message = throwable.getLocalizedMessage();
        }
        log(message, IStatus.ERROR, throwable);
    }

    /**
     * Create and add a status message to the eclipse log.
     * 
     * @param message
     *            message
     * @param severity
     *            either IStatus.OK, IStatus.CANCEL, IStatus.ERROR,
     *            IStatus.WARNING or IStatus.INFO
     * @param throwable
     *            optional throwable as details
     * @see org.eclipse.core.runtime.IStatus
     */
    public void log(String message, int severity, Throwable throwable) {
        IStatus status = new Status(severity, instance.getBundle()
                .getSymbolicName(), IStatus.OK, message, throwable);
        instance.getLog().log(status);
    }

}

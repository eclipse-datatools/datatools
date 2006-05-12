/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.internal;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLCodeScanner;
import org.eclipse.datatools.sqltools.sqleditor.internal.sql.SQLPartitionScanner;
import org.eclipse.datatools.sqltools.sqleditor.internal.utils.SQLColorProvider;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class.
 */
public class SQLEditorPlugin extends AbstractUIPlugin {
    private static final int INTERNAL_ERROR = 0;

	public static final String PLUGIN_ID = "org.eclipse.datatools.sqltools.sqleditor";
    
    private static SQLEditorPlugin fgInstance;
    private SQLPartitionScanner    fPartitionScanner;
    private SQLColorProvider       fColorProvider;
    private SQLCodeScanner         fCodeScanner;

    /**
     * Constructs an instance of this class.  This is the default constructor.
     */
    public SQLEditorPlugin() {
        super();
        fgInstance = this;
    }

    /**
     * Handles plug-in activation.
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext) 
     */
    public void start( BundleContext context ) throws Exception {
        super.start( context );
    }

    /**
     * Handles plug-in deactivation.
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
     */
    public void stop( BundleContext context ) throws Exception {
        super.stop( context );
    }

    /**
     * Returns the shared instance of this plug-in.
     * 
     * @return the shared instance of this plug-in
     */
    public static SQLEditorPlugin getDefault() {
        return fgInstance;
    }

    /**
     * Get a scanner for creating SQL partitions.
     * 
     * @return the SQL partition scanner
     */
     public SQLPartitionScanner getSQLPartitionScanner() {
        if (fPartitionScanner == null)
            fPartitionScanner = new SQLPartitionScanner();
        return fPartitionScanner;
    }
    
    /**
     * Gets a SQL code scanner.
     * 
     * @return the SQL code scanner
     */
     public RuleBasedScanner getSQLCodeScanner() {
        if (fCodeScanner == null)
            fCodeScanner = new SQLCodeScanner( getSQLColorProvider() );
        return fCodeScanner;
    }
    
    /**
     * Gets a SQL color provider.
     * 
     * @return the SQL color provider
     */
     public SQLColorProvider getSQLColorProvider() {
        if (fColorProvider == null)
            fColorProvider = new SQLColorProvider();
        return fColorProvider;
    }
     
 	/**
 	 * Logs runtime status.
 	 * 
 	 * @param status Runtime status.
 	 */
 	public void log(IStatus status) {
 		getLog().log(status);
 	}

 	/**
 	 * Logs error message.
 	 * 
 	 * @param message Error message.
 	 */
 	public void log(String message) {
 		log(createErrorStatus(message));
 	}

    /**
     * Logs an error message with an exception.
     * 
     * @param e Exception.
     */
    public void log(String message, Throwable e) {
        log(createErrorStatus(message, e));
    }


 	/**
 	 * Logs and exception.
 	 * 
 	 * @param e Exception.
 	 */
 	public void log(Throwable e) {
 		log(createErrorStatus(e));
 	}

 	public IStatus createErrorStatus(String message) {
 		return new Status(IStatus.ERROR, getBundle().getSymbolicName(),
 				INTERNAL_ERROR, message, null);
 	}

 	public IStatus createErrorStatus(Throwable e) {
 		return new Status(IStatus.ERROR, getBundle().getSymbolicName(),
 				INTERNAL_ERROR, SQLEditorResources.plugin_internal_error, e); 
 	}

    public IStatus createErrorStatus(String message, Throwable e) {
        return new Status(IStatus.ERROR, getBundle().getSymbolicName(),
                INTERNAL_ERROR, message, e);
    }

} // end class

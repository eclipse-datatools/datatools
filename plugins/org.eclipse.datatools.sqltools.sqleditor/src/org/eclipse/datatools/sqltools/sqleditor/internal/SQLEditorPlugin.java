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
    public final static String SQL_PARTITIONING= "__sql_partitioning";   //$NON-NLS-1$
    
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
     
} // end class

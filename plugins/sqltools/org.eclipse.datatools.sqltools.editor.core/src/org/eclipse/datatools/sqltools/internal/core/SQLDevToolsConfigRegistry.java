/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.internal.core;

import java.util.Collection;

import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;

/**
 * Registry for <code>SQLDevToolsConfiguration</code>s contributed to SQL tools framework.
 * @author Hui Cao
 *  
 */
public interface SQLDevToolsConfigRegistry
{
    /**
     * Returns the product names.
     * @return product names
     */
    public Collection getProducts();

    /**
     * Returns all the <code>SQLDevToolsConfiguration</code>s
     * @return <code>SQLDevToolsConfiguration</code> collection
     */
    public Collection getConfigurations();

    /**
     * Returns all the version strings for the given product name
     * @param product product name
     * @return versions for the given product name
     */
    public Collection getVersions(String product);

    /**
     * Returns the <code>SQLDevToolsConfiguration</code> object corresponding to the product name and version.
     * @param product
     * @param version
     * @return <code>SQLDevToolsConfiguration</code> object
     */
    public SQLDevToolsConfiguration getConfiguration(String product, String version);

    /**
     * Returns the <code>SQLDevToolsConfiguration</code> object by the <code>DatabaseVendorDefinitionId</code> object
     * @param id <code>DatabaseVendorDefinitionId</code> object represented by product name and version
     * @return <code>SQLDevToolsConfiguration</code> object
     */
    public SQLDevToolsConfiguration getConfigurationByVendorIdentifier(DatabaseVendorDefinitionId id);

    /**
     * Returns the <code>SQLDevToolsConfiguration</code> object by the unique identifier defined in
     * org.eclipse.datatools.sqltools.editor.core.dbFactories extension point.
     * 
     * @param id <code>SQLDevToolsConfiguration</code> id
     * @return <code>SQLDevToolsConfiguration</code> object
     */
    public SQLDevToolsConfiguration getConfigurationById(String id);

    /**
     * Returns the <code>SQLDevToolsConfiguration</code> object by the database definition name.
     * 
     * @param name database definition name, which is product name appended by "_" and version.
     * @return <code>SQLDevToolsConfiguration</code> object
     */
    public SQLDevToolsConfiguration getConfigurationByName(String name);

    /**
     * Returns the the <code>SQLDevToolsConfiguration</code> objects supporting debugging.
     * @return <code>IDBDebuggerFactory</code> collection
     */
    public Collection getDebuggerConfigurations();

	public void addConfigurationRegistryListener(IConfigurationRegistryListener listener);
    
}

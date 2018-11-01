/*******************************************************************************
 * Copyright (c) 2004, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.editor.ui.internal.core;

import java.util.Collection;

import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLDevToolsUIConfiguration;

/**
 * Registry for <code>SQLDevToolsUIConfiguration</code>s contributed to SQL tools framework.
 * @author linsong
 *  
 */
public interface SQLDevToolsUIConfigRegistry
{
    /**
     * Returns the product names.
     * @return product names
     */
    public Collection getProducts();

    /**
     * Returns all the <code>SQLDevToolsUIConfiguration</code>s
     * @return <code>SQLDevToolsUIConfiguration</code> collection
     */
    public Collection getConfigurations();

    /**
     * Returns all the version strings for the given product name
     * @param product product name
     * @return versions for the given product name
     */
    public Collection getVersions(String product);

    /**
     * Returns the <code>SQLDevToolsUIConfiguration</code> object corresponding to the product name and version.
     * @param product
     * @param version
     * @return <code>SQLDevToolsUIConfiguration</code> object
     */
    public SQLDevToolsUIConfiguration getConfiguration(String product, String version);

    /**
     * Returns the <code>SQLDevToolsUIConfiguration</code> object by the <code>DatabaseVendorDefinitionId</code> object
     * @param id <code>DatabaseVendorDefinitionId</code> object represented by product name and version
     * @return <code>SQLDevToolsUIConfiguration</code> object
     */
    public SQLDevToolsUIConfiguration getConfigurationByVendorIdentifier(DatabaseVendorDefinitionId id);

    /**
     * Returns the <code>SQLDevToolsUIConfiguration</code> object by the unique identifier defined in
     * org.eclipse.datatools.sqltools.editor.core.dbFactories extension point.
     * 
     * @param id <code>SQLDevToolsUIConfiguration</code> id
     * @return <code>SQLDevToolsUIConfiguration</code> object
     */
    public SQLDevToolsUIConfiguration getConfigurationById(String id);

    /**
     * Returns the <code>SQLDevToolsUIConfiguration</code> object by the database definition name.
     * 
     * @param name database definition name, which is product name appended by "_" and version.
     * @return <code>SQLDevToolsUIConfiguration</code> object
     */
    public SQLDevToolsUIConfiguration getConfigurationByName(String name);

}

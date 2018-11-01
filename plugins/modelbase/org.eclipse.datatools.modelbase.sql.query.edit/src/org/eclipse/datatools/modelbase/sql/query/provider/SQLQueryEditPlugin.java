/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.modelbase.sql.query.provider;

import org.eclipse.datatools.modelbase.sql.schema.provider.SqlmodelEditPlugin;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.provider.EcoreEditPlugin;


/**
 * This is the central singleton for the SQLQueryModel edit plugin.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public final class SQLQueryEditPlugin extends EMFPlugin {
    /**
     * Keep track of the singleton.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public static final SQLQueryEditPlugin INSTANCE = new SQLQueryEditPlugin();

    /**
     * Keep track of the singleton.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  private static Implementation plugin;

    /**
     * Create the instance.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public SQLQueryEditPlugin() {
        super
          (new ResourceLocator [] {
             SqlmodelEditPlugin.INSTANCE,
             EcoreEditPlugin.INSTANCE,
           });
    }

    /**
     * Returns the singleton instance of the Eclipse plugin.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the singleton instance.
     * @generated
     */
  public ResourceLocator getPluginResourceLocator() {
        return plugin;
    }

    /**
     * Returns the singleton instance of the Eclipse plugin.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @return the singleton instance.
     * @generated
     */
  public static Implementation getPlugin() {
        return plugin;
    }

    /**
     * The actual implementation of the Eclipse <b>Plugin</b>.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @generated
     */
  public static class Implementation extends EclipsePlugin {
        /**
         * Creates an instance.
         * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
         * @generated
         */
    public Implementation() {
            super();

            // Remember the static instance.
            //
            plugin = this;
        }
    }

}

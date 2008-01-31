/*
 * Licensed Materials - Property of IBM
 * com.ibm.db.models.sql.query.edit
 * (C) Copyright IBM Corporation 2005. All Rights Reserved.
 *
 * Note to U.S. Government Users Restricted Rights:
 *   Use, duplication or disclosure restricted 
 *   by GSA ADP Schedule Contract with IBM Corp.
 *
 * $Id: SQLQueryEditPlugin.java,v 1.1 2007/09/25 23:18:02 bpayton Exp $
 */
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

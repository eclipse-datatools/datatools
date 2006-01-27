/**
 *************************************************************************
 * Copyright (c) 2005, 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 *
 * $Id: DataAccessDesign.java,v 1.1 2005/12/29 04:17:56 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The design of how to access a data set.  Its model is a design tree that starts with a top-level dataSetDesign, which contains its corresponding dataSourceDesign.  The top-level data set may contain nested data set designs.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataAccessDesign#getDataSetDesign <em>Data Set Design</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataAccessDesign()
 * @model 
 * @generated
 */
public interface DataAccessDesign extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the ODA data source element ID
     * that supports this data set's access.  
     * Could be null, if no data set design is defined.
     */
    public String getOdaExtensionDataSourceId();

    /**
     * Returns the value of the '<em><b>Data Set Design</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Data Set Design</em>' containment reference.
     * @see #setDataSetDesign(DataSetDesign)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataAccessDesign_DataSetDesign()
     * @model containment="true" resolveProxies="false"
     * @generated
     */
    DataSetDesign getDataSetDesign();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataAccessDesign#getDataSetDesign <em>Data Set Design</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Data Set Design</em>' containment reference.
     * @see #getDataSetDesign()
     * @generated
     */
    void setDataSetDesign( DataSetDesign value );

} // DataAccessDesign

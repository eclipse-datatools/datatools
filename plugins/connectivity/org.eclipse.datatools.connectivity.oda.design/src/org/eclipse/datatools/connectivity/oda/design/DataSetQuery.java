/**
 *************************************************************************
 * Copyright (c) 2005, 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 *
 * $Id: DataSetQuery.java,v 1.1 2005/12/29 04:17:55 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Defines the query of a data set.  Includes the query text, and any additional query spec.  Future: may include a filterSpec for dynamicFiltering. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataSetQuery#getQueryText <em>Query Text</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSetQuery()
 * @model extendedMetaData="name='DataSetQuery' kind='elementOnly'"
 * @generated
 */
public interface DataSetQuery extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2007 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Query Text</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The query command text to execute at runtime to retrieve data for this data set.  Retrieved data could be for one or more result sets and/or output parameters, as defined in this data set design.  The query syntax is specific to a data source; could be an empty string.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Query Text</em>' attribute.
     * @see #setQueryText(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataSetQuery_QueryText()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='element' name='queryText' namespace='##targetNamespace'"
     * @generated
     */
    String getQueryText();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataSetQuery#getQueryText <em>Query Text</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Query Text</em>' attribute.
     * @see #getQueryText()
     * @generated
     */
    void setQueryText( String value );

} // DataSetQuery

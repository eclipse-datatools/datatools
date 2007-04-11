/**
 *************************************************************************
 * Copyright (c) 2005, 2007 Actuate Corporation.
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
 * $Id: ResultSetDefinition.java,v 1.1 2005/12/29 04:17:55 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition of a single homogeneous result set returned by a data set query.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ResultSetDefinition#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ResultSetDefinition#getResultSetColumns <em>Result Set Columns</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getResultSetDefinition()
 * @model extendedMetaData="name='ResultSetDefinition' kind='elementOnly'"
 * @generated
 */
public interface ResultSetDefinition extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2007 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * A name that uniquely identifies a result set at runtime.  If not specified, a result set is identified by its sequence in the resultSetDefinitions.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getResultSetDefinition_Name()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     *        extendedMetaData="kind='element' name='name' namespace='##targetNamespace'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ResultSetDefinition#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName( String value );

    /**
     * Returns the value of the '<em><b>Result Set Columns</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Collection of data columns for this result set.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Result Set Columns</em>' containment reference.
     * @see #setResultSetColumns(ResultSetColumns)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getResultSetDefinition_ResultSetColumns()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='resultSetColumns' namespace='##targetNamespace'"
     * @generated
     */
    ResultSetColumns getResultSetColumns();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ResultSetDefinition#getResultSetColumns <em>Result Set Columns</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Result Set Columns</em>' containment reference.
     * @see #getResultSetColumns()
     * @generated
     */
    void setResultSetColumns( ResultSetColumns value );

} // ResultSetDefinition

/**
 *************************************************************************
 * Copyright (c) 2005, 2009 Actuate Corporation.
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
 * $Id: ResultSetDefinition.java,v 1.3 2009/03/13 05:19:46 lchan Exp $
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
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ResultSetDefinition#getCriteria <em>Criteria</em>}</li>
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
    String copyright = "Copyright (c) 2005, 2009 Actuate Corporation"; //$NON-NLS-1$

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
     * @model dataType="org.eclipse.emf.ecore.xml.type.String"
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

    /**
     * Returns the value of the '<em><b>Criteria</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Criteria</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Criteria</em>' containment reference.
     * @see #setCriteria(ResultSetCriteria)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getResultSetDefinition_Criteria()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='criteria' namespace='##targetNamespace'"
     * @generated
     * @since 3.3 (DTP 1.8)
     */
    ResultSetCriteria getCriteria();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ResultSetDefinition#getCriteria <em>Criteria</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Criteria</em>' containment reference.
     * @see #getCriteria()
     * @generated
     * @since 3.3 (DTP 1.8)
     */
    void setCriteria( ResultSetCriteria value );

} // ResultSetDefinition

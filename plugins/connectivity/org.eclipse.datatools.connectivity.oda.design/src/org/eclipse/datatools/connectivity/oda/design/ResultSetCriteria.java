/**
 *************************************************************************
 * Copyright (c) 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 *
 * $Id: ResultSetCriteria.java,v 1.2 2009/07/23 21:43:17 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Result Set Criteria</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Specification of a result set criteria or characteristics.
 * Applying the criteria may impact the data retrieved in a result set, in addition to any specification expressed in a query text.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ResultSetCriteria#getFilterSpecification <em>Filter Specification</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ResultSetCriteria#getRowOrdering <em>Row Ordering</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getResultSetCriteria()
 * @since 3.3 (DTP 1.8)
 * @model extendedMetaData="name='ResultSetCriteria' kind='elementOnly'"
 * @generated
 */
public interface ResultSetCriteria extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Filter Specification</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Pre-defined filter specification  of a result set. It may be any concrete type of FilterExpression.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Filter Specification</em>' containment reference.
     * @see #setFilterSpecification(FilterExpression)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getResultSetCriteria_FilterSpecification()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='filterSpecification' namespace='##targetNamespace'"
     * @generated
     */
    FilterExpression getFilterSpecification();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ResultSetCriteria#getFilterSpecification <em>Filter Specification</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Filter Specification</em>' containment reference.
     * @see #getFilterSpecification()
     * @generated
     */
    void setFilterSpecification( FilterExpression value );

    /**
     * Returns the value of the '<em><b>Row Ordering</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Specifies the ordering of rows in a result set.  An empty collection explicitly indicates that the result set has no sort keys defined.  
     * Absence of this optional element, on the other hand, indicates that its row ordering specification is unknown or not available. 
     * <!-- end-model-doc -->
     * @return the value of the '<em>Row Ordering</em>' containment reference.
     * @see #setRowOrdering(SortSpecification)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getResultSetCriteria_RowOrdering()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='rowOrdering' namespace='##targetNamespace'"
     * @generated
     */
    SortSpecification getRowOrdering();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ResultSetCriteria#getRowOrdering <em>Row Ordering</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Row Ordering</em>' containment reference.
     * @see #getRowOrdering()
     * @generated
     */
    void setRowOrdering( SortSpecification value );

    /**
     * Adds the specified {@link SortKey} to the specification of ordering the result set rows.
     * @param sortKey
     * @generated NOT
     */
    void addRowSortKey( SortKey sortKey );

} // ResultSetCriteria

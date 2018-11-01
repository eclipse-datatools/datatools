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
 * $Id: ColumnDefinition.java,v 1.1 2005/12/29 04:17:55 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition of a result set column.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ColumnDefinition#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ColumnDefinition#getUsageHints <em>Usage Hints</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ColumnDefinition#getMultiDimensionAttributes <em>Multi Dimension Attributes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getColumnDefinition()
 * @model extendedMetaData="name='ColumnDefinition' kind='elementOnly'"
 * @generated
 */
public interface ColumnDefinition extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2007 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Attributes</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Attributes</em>' containment reference.
     * @see #setAttributes(DataElementAttributes)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getColumnDefinition_Attributes()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='attributes' namespace='##targetNamespace'"
     * @generated
     */
    DataElementAttributes getAttributes();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ColumnDefinition#getAttributes <em>Attributes</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Attributes</em>' containment reference.
     * @see #getAttributes()
     * @generated
     */
    void setAttributes( DataElementAttributes value );

    /**
     * Returns the value of the '<em><b>Usage Hints</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Usage Hints</em>' containment reference.
     * @see #setUsageHints(OutputElementAttributes)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getColumnDefinition_UsageHints()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='usageHints' namespace='##targetNamespace'"
     * @generated
     */
    OutputElementAttributes getUsageHints();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ColumnDefinition#getUsageHints <em>Usage Hints</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Usage Hints</em>' containment reference.
     * @see #getUsageHints()
     * @generated
     */
    void setUsageHints( OutputElementAttributes value );

    /**
     * Returns the value of the '<em><b>Multi Dimension Attributes</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The attributes of a multi-dimensional data element.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Multi Dimension Attributes</em>' containment reference.
     * @see #setMultiDimensionAttributes(AxisAttributes)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getColumnDefinition_MultiDimensionAttributes()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='multiDimensionAttributes' namespace='##targetNamespace'"
     * @generated
     */
    AxisAttributes getMultiDimensionAttributes();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ColumnDefinition#getMultiDimensionAttributes <em>Multi Dimension Attributes</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Multi Dimension Attributes</em>' containment reference.
     * @see #getMultiDimensionAttributes()
     * @generated
     */
    void setMultiDimensionAttributes( AxisAttributes value );

} // ColumnDefinition

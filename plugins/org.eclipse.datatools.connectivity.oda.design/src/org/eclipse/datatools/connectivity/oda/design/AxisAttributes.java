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
 * $Id$
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Multi-dimensional attributes of a data element, such as a result set column.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.AxisAttributes#getAxisType <em>Axis Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.AxisAttributes#isOnColumnLayout <em>On Column Layout</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getAxisAttributes()
 * @model 
 * @generated
 */
public interface AxisAttributes extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Axis Type</b></em>' attribute.
     * The default value is <code>"Measure"</code>.
     * The literals are from the enumeration {@link org.eclipse.datatools.connectivity.oda.design.AxisType}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The axis type of the data element.  This provides hints to a consumer application on how the column should be used.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Axis Type</em>' attribute.
     * @see org.eclipse.datatools.connectivity.oda.design.AxisType
     * @see #isSetAxisType()
     * @see #unsetAxisType()
     * @see #setAxisType(AxisType)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getAxisAttributes_AxisType()
     * @model default="Measure" unique="false" unsettable="true" required="true"
     * @generated
     */
    AxisType getAxisType();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.AxisAttributes#getAxisType <em>Axis Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Axis Type</em>' attribute.
     * @see org.eclipse.datatools.connectivity.oda.design.AxisType
     * @see #isSetAxisType()
     * @see #unsetAxisType()
     * @see #getAxisType()
     * @generated
     */
    void setAxisType( AxisType value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.AxisAttributes#getAxisType <em>Axis Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetAxisType()
     * @see #getAxisType()
     * @see #setAxisType(AxisType)
     * @generated
     */
    void unsetAxisType();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.AxisAttributes#getAxisType <em>Axis Type</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Axis Type</em>' attribute is set.
     * @see #unsetAxisType()
     * @see #getAxisType()
     * @see #setAxisType(AxisType)
     * @generated
     */
    boolean isSetAxisType();

    /**
     * Returns the value of the '<em><b>On Column Layout</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * A hint on how the dimension data element should be layout on column or row.  It is normally used by presentation element such as a crosstab to design a default layout.
     * <!-- end-model-doc -->
     * @return the value of the '<em>On Column Layout</em>' attribute.
     * @see #isSetOnColumnLayout()
     * @see #unsetOnColumnLayout()
     * @see #setOnColumnLayout(boolean)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getAxisAttributes_OnColumnLayout()
     * @model default="true" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     * @generated
     */
    boolean isOnColumnLayout();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.AxisAttributes#isOnColumnLayout <em>On Column Layout</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>On Column Layout</em>' attribute.
     * @see #isSetOnColumnLayout()
     * @see #unsetOnColumnLayout()
     * @see #isOnColumnLayout()
     * @generated
     */
    void setOnColumnLayout( boolean value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.AxisAttributes#isOnColumnLayout <em>On Column Layout</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetOnColumnLayout()
     * @see #isOnColumnLayout()
     * @see #setOnColumnLayout(boolean)
     * @generated
     */
    void unsetOnColumnLayout();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.AxisAttributes#isOnColumnLayout <em>On Column Layout</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>On Column Layout</em>' attribute is set.
     * @see #unsetOnColumnLayout()
     * @see #isOnColumnLayout()
     * @see #setOnColumnLayout(boolean)
     * @generated
     */
    boolean isSetOnColumnLayout();

} // AxisAttributes

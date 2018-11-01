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
 * $Id: FilterExpression.java,v 1.1 2009/01/30 00:23:57 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Filter Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Abstract base class for all filter expressions defined by an ODA designer.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.FilterExpression#isNegatable <em>Negatable</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getFilterExpression()
 * @since 3.3 (DTP 1.8)
 * @model abstract="true"
 *        extendedMetaData="name='FilterExpression' kind='empty'"
 * @generated
 */
public interface FilterExpression extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Negatable</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Indicates whether the expression can be negated, i.e. applied with a NotExpression.  Default is false.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Negatable</em>' attribute.
     * @see #isSetNegatable()
     * @see #unsetNegatable()
     * @see #setNegatable(boolean)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getFilterExpression_Negatable()
     * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='attribute' name='negatable'"
     * @generated
     */
    boolean isNegatable();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.FilterExpression#isNegatable <em>Negatable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Negatable</em>' attribute.
     * @see #isSetNegatable()
     * @see #unsetNegatable()
     * @see #isNegatable()
     * @generated
     */
    void setNegatable( boolean value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.FilterExpression#isNegatable <em>Negatable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetNegatable()
     * @see #isNegatable()
     * @see #setNegatable(boolean)
     * @generated
     */
    void unsetNegatable();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.FilterExpression#isNegatable <em>Negatable</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Negatable</em>' attribute is set.
     * @see #unsetNegatable()
     * @see #isNegatable()
     * @see #setNegatable(boolean)
     * @generated
     */
    boolean isSetNegatable();

} // FilterExpression

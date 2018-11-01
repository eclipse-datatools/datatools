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
 * $Id: NotExpression.java,v 1.1 2009/01/30 00:23:57 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Not Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A built-in negated filter expression.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.NotExpression#getNegatingExpression <em>Negating Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getNotExpression()
 * @since 3.3 (DTP 1.8)
 * @model extendedMetaData="name='NotExpression' kind='elementOnly'"
 * @generated
 */
public interface NotExpression extends FilterExpression
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Negating Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Negating Expression</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Negating Expression</em>' containment reference.
     * @see #setNegatingExpression(FilterExpression)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getNotExpression_NegatingExpression()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='negatingExpression' namespace='##targetNamespace'"
     * @generated
     */
    FilterExpression getNegatingExpression();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.NotExpression#getNegatingExpression <em>Negating Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Negating Expression</em>' containment reference.
     * @see #getNegatingExpression()
     * @generated
     */
    void setNegatingExpression( FilterExpression value );

} // NotExpression

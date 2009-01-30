/**
 *************************************************************************
 * Copyright (c) 2009 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 *
 * $Id$
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * <p>
 * <strong>EXPERIMENTAL</strong>.
 * </p>
 * A representation of the model object '<em><b>Atomic Expression Context</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The context within a basic, indivisible unit of filter expression that can be evaluated by itself.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.AtomicExpressionContext#getVariable <em>Variable</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.AtomicExpressionContext#getArguments <em>Arguments</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getAtomicExpressionContext()
 * @since 3.2 (DTP 1.7)
 * @model extendedMetaData="name='AtomicExpressionContext' kind='elementOnly'"
 * @generated
 */
public interface AtomicExpressionContext extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Variable</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Variable</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Variable</em>' containment reference.
     * @see #setVariable(FilterExpressionVariable)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getAtomicExpressionContext_Variable()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='variable' namespace='##targetNamespace'"
     * @generated
     */
    FilterExpressionVariable getVariable();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.AtomicExpressionContext#getVariable <em>Variable</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Variable</em>' containment reference.
     * @see #getVariable()
     * @generated
     */
    void setVariable( FilterExpressionVariable value );

    /**
     * Returns the value of the '<em><b>Arguments</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Defines the expression's argument(s) and each of its associated value.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Arguments</em>' containment reference.
     * @see #setArguments(FilterExpressionArguments)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getAtomicExpressionContext_Arguments()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='arguments' namespace='##targetNamespace'"
     * @generated
     */
    FilterExpressionArguments getArguments();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.AtomicExpressionContext#getArguments <em>Arguments</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Arguments</em>' containment reference.
     * @see #getArguments()
     * @generated
     */
    void setArguments( FilterExpressionArguments value );

} // AtomicExpressionContext

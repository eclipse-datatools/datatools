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
 * $Id: AtomicExpressionContext.java,v 1.3 2009/04/14 02:13:18 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Atomic Expression Context</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The context within a basic, indivisible unit of expression that can be evaluated by itself.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.AtomicExpressionContext#isOptional <em>Optional</em>}</li>
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
     * Returns the value of the '<em><b>Optional</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Indicates whether this atomic expression can be excluded at runtime.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Optional</em>' attribute.
     * @see #isSetOptional()
     * @see #unsetOptional()
     * @see #setOptional(boolean)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getAtomicExpressionContext_Optional()
     * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
     *        extendedMetaData="kind='element' name='optional' namespace='##targetNamespace'"
     * @generated
     */
    boolean isOptional();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.AtomicExpressionContext#isOptional <em>Optional</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Optional</em>' attribute.
     * @see #isSetOptional()
     * @see #unsetOptional()
     * @see #isOptional()
     * @generated
     */
    void setOptional( boolean value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.AtomicExpressionContext#isOptional <em>Optional</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetOptional()
     * @see #isOptional()
     * @see #setOptional(boolean)
     * @generated
     */
    void unsetOptional();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.AtomicExpressionContext#isOptional <em>Optional</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Optional</em>' attribute is set.
     * @see #unsetOptional()
     * @see #isOptional()
     * @see #setOptional(boolean)
     * @generated
     */
    boolean isSetOptional();

    /**
     * Returns the value of the '<em><b>Variable</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Variable</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Variable</em>' containment reference.
     * @see #setVariable(ExpressionVariable)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getAtomicExpressionContext_Variable()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='variable' namespace='##targetNamespace'"
     * @generated
     */
    ExpressionVariable getVariable();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.AtomicExpressionContext#getVariable <em>Variable</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Variable</em>' containment reference.
     * @see #getVariable()
     * @generated
     */
    void setVariable( ExpressionVariable value );

    /**
     * Returns the value of the '<em><b>Arguments</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Defines the expression's argument(s) and each of its associated value(s).
     * <!-- end-model-doc -->
     * @return the value of the '<em>Arguments</em>' containment reference.
     * @see #setArguments(ExpressionArguments)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getAtomicExpressionContext_Arguments()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='arguments' namespace='##targetNamespace'"
     * @generated
     */
    ExpressionArguments getArguments();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.AtomicExpressionContext#getArguments <em>Arguments</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Arguments</em>' containment reference.
     * @see #getArguments()
     * @generated
     */
    void setArguments( ExpressionArguments value );

} // AtomicExpressionContext

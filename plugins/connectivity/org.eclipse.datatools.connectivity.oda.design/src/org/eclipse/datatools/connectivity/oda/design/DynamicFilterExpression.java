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
 * $Id: DynamicFilterExpression.java,v 1.2 2009/10/23 20:17:26 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dynamic Filter Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A built-in filter expression designed with a pre-defined variable.  Its specific type of expression(s) to apply and corresponding argument value(s) to collect will be dynamically specified at runtime.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DynamicFilterExpression#getContext <em>Context</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DynamicFilterExpression#getDefaultType <em>Default Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDynamicFilterExpression()
 * @since 3.3 (DTP 1.8)
 * @model extendedMetaData="name='DynamicFilterExpression' kind='elementOnly'"
 * @generated
 */
public interface DynamicFilterExpression extends FilterExpression
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Context</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Context</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Context</em>' containment reference.
     * @see #setContext(AtomicExpressionContext)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDynamicFilterExpression_Context()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='context' namespace='##targetNamespace'"
     * @generated
     */
    AtomicExpressionContext getContext();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DynamicFilterExpression#getContext <em>Context</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Context</em>' containment reference.
     * @see #getContext()
     * @generated
     */
    void setContext( AtomicExpressionContext value );

    /**
     * Returns the value of the '<em><b>Default Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The default type of filter expression to apply at runtime.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Default Type</em>' containment reference; may be null.
     * @see #setDefaultType(FilterExpressionType)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDynamicFilterExpression_DefaultType()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='defaultType' namespace='##targetNamespace'"
     * @since 3.2.2 (DTP 1.7.2)
     * @generated
     */
    FilterExpressionType getDefaultType();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DynamicFilterExpression#getDefaultType <em>Default Type</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Default Type</em>' containment reference.
     * @see #getDefaultType()
     * @since 3.2.2 (DTP 1.7.2)
     * @generated
     */
    void setDefaultType( FilterExpressionType value );

    /**
     * Indicates whether this filter expression can be excluded at runtime.
     * @return  true if this can be excluded at runtime; false otherwise
     * @generated NOT
     */
    boolean isOptional();

    /**
     * Specifies whether this filter expression can be excluded at runtime.
     * @param isOptional    true if this can be excluded at runtime; false otherwise
     * @generated NOT
     */
    void setIsOptional( boolean isOptional );

    /**
     * Returns the expression variable design in the '<em><b>Context</b></em>' containment reference.
     * @return  the variable design in the '<em><b>Context</b></em>' containment reference, 
     *          or null if none is specified
     * @generated NOT
     */
    ExpressionVariable getContextVariable();

    /**
     * Sets the expression variable design in the '<em><b>Context</b></em>' containment reference.
     * @param variable  the variable design in the '<em><b>Context</b></em>' containment reference
     * @generated NOT
     */
    void setContextVariable( ExpressionVariable variable );

    /**
     * Returns the expression arguments design in the '<em><b>Context</b></em>' containment reference.
     * @return  the arguments design in the '<em><b>Context</b></em>' containment reference, 
     *          or null if none is specified
     * @generated NOT
     */
    ExpressionArguments getContextArguments();

    /**
     * Sets the expression arguments design in the '<em><b>Context</b></em>' containment reference.
     * @param arguments   the arguments design in the '<em><b>Context</b></em>' containment reference
     * @generated NOT
     */
    void setContextArguments( ExpressionArguments arguments );

} // DynamicFilterExpression

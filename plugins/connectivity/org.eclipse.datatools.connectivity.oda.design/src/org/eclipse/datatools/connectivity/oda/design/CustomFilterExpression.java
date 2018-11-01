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
 * $Id: CustomFilterExpression.java,v 1.2 2009/10/23 20:17:26 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Custom Filter Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The definition of a custom atomic filter expression contributed by an extension of the org.eclipse.datatools.connectivity.oda.dynamicResultSet extension point.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.CustomFilterExpression#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.CustomFilterExpression#getContext <em>Context</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getCustomFilterExpression()
 * @since 3.3 (DTP 1.8)
 * @model extendedMetaData="name='CustomFilterExpression' kind='elementOnly'"
 * @generated
 */
public interface CustomFilterExpression extends FilterExpression
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Type</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' containment reference.
     * @see #setType(FilterExpressionType)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getCustomFilterExpression_Type()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='type' namespace='##targetNamespace'"
     * @generated
     */
    FilterExpressionType getType();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.CustomFilterExpression#getType <em>Type</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' containment reference.
     * @see #getType()
     * @generated
     */
    void setType( FilterExpressionType value );

    /**
     * Returns the value of the '<em><b>Declaring Extension Id</b></em>' attribute
     * of this type of filter expression.
     * <!-- begin-user-doc -->
     * Preserved method for backward compatibility.
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The id of the ODA dynamicResultSet extension that declares this custom expression.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Declaring Extension Id</em>' attribute.
     * @see #getType()
     * @see #setDeclaringExtensionId(String)
     * @generated NOT
     */
    String getDeclaringExtensionId();

    /**
     * Sets the value of the '<em><b>Declaring Extension Id</b></em>' attribute
     * of this type of filter expression.
     * <!-- begin-user-doc -->
     * Preserved method for backward compatibility.
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Declaring Extension Id</em>' attribute.
     * @see #setType(FilterExpressionType)
     * @see #getDeclaringExtensionId()
     * @generated NOT
     */
    void setDeclaringExtensionId( String value );

    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute
     * of this type of filter expression.
     * <!-- begin-user-doc -->
     * Preserved method for backward compatibility.
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * An id that uniquely identifies  a custom expression within an ODA dynamicResultSet extension.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Id</em>' attribute.
     * @see #getType()
     * @see #setId(String)
     * @generated NOT
     */
    String getId();

    /**
     * Sets the value of the '<em><b>Id</b></em>' attribute
     * of this type of filter expression.
     * <!-- begin-user-doc -->
     * Preserved method for backward compatibility.
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #setType(FilterExpressionType)
     * @see #getId()
     * @generated NOT
     */
    void setId( String value );

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
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getCustomFilterExpression_Context()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='context' namespace='##targetNamespace'"
     * @generated
     */
    AtomicExpressionContext getContext();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.CustomFilterExpression#getContext <em>Context</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Context</em>' containment reference.
     * @see #getContext()
     * @generated
     */
    void setContext( AtomicExpressionContext value );

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

} // CustomFilterExpression

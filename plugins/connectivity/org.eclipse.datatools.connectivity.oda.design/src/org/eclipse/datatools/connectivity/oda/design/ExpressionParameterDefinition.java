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
 * $Id: ExpressionParameterDefinition.java,v 1.1 2009/03/03 07:42:07 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expression Parameter Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The definition of a parameter for the value of an expression's argument.  An expression argument parameter design may specify either static argument value(s), or a data set input parameter.  Static values are pre-defined values of an expression argument, and are not exposed as a data set parameter.  Alternatively, a data set input parameter may be defined to dynamically collect user input value(s).  A data set input parameter definiiton here may be a nested new instance, or may reference one of the existing DataSetParameters.  The dynamic input parameter, if specified, overrides any static values defined.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ExpressionParameterDefinition#getStaticValues <em>Static Values</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ExpressionParameterDefinition#getDynamicInputParameter <em>Dynamic Input Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getExpressionParameterDefinition()
 * @since 3.3 (DTP 1.8)
 * @model extendedMetaData="name='ExpressionParameterDefinition' kind='elementOnly'"
 * @generated
 */
public interface ExpressionParameterDefinition extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Static Values</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Static Values</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Static Values</em>' containment reference.
     * @see #setStaticValues(StaticValues)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getExpressionParameterDefinition_StaticValues()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='staticValues' namespace='##targetNamespace'"
     * @generated
     */
    StaticValues getStaticValues();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ExpressionParameterDefinition#getStaticValues <em>Static Values</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Static Values</em>' containment reference.
     * @see #getStaticValues()
     * @generated
     */
    void setStaticValues( StaticValues value );

    /**
     * Returns the value of the '<em><b>Dynamic Input Parameter</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Dynamic Input Parameter</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Dynamic Input Parameter</em>' containment reference.
     * @see #setDynamicInputParameter(ParameterDefinition)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getExpressionParameterDefinition_DynamicInputParameter()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='dynamicInputParameter' namespace='##targetNamespace'"
     * @generated
     */
    ParameterDefinition getDynamicInputParameter();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ExpressionParameterDefinition#getDynamicInputParameter <em>Dynamic Input Parameter</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Dynamic Input Parameter</em>' containment reference.
     * @see #getDynamicInputParameter()
     * @generated
     */
    void setDynamicInputParameter( ParameterDefinition value );

    /**
     * Indicates whether this has defined an input parameter design to dynamically collect its value.
     * @return  true if a dynamic input parameter design is defined; false otherwise
     * @generated NOT
     */
    boolean hasDynamicInputParameter();

    /**
     * Indicates whether this has static values that are effective.
     * Static values may be defined but not effective if this also has a dynamic input parameter defined.
     * @return  true if this has effective static value(s); false otherwise
     * @generated NOT
     */
    boolean hasEffectiveStaticValues();

    /**
     * Gets the number of effective static values defined.
     * Static values may be defined but not effective if this also has a dynamic input parameter defined.
     * @return  number of effective static values
     * @generated NOT
     */
    int getEffectiveStaticValueCount();

    /**
     * Appends the specified value to the list of static values.
     * It is the responsibility of the caller to ensure compatible type of value object
     * is added to the list.
     * @param aValue    a value to add; may be null
     * @generated NOT
     */
    void addStaticValue( Object aValue );

} // ExpressionParameterDefinition

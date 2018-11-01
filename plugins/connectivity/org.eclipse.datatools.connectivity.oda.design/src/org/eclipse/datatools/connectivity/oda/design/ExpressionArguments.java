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
 * $Id: ExpressionArguments.java,v 1.3 2009/09/02 00:55:33 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expression Arguments</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition of a filter expression's argument(s).
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ExpressionArguments#getExpressionParameters <em>Expression Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getExpressionArguments()
 * @since 3.3 (DTP 1.8)
 * @model extendedMetaData="name='ExpressionArguments' kind='elementOnly'"
 * @generated
 */
public interface ExpressionArguments extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Expression Parameters</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * A collection of parameters used for collecting an expression's argument values.  An expression argument may define one corresponding expression parameter that takes multiple input values sharing the same parameter attributes.  Or it may define multiple expression parameters, with each taking a single input value and has own set of parameter attributes.  Each expression parameter may define either static value(s), or an input parameter to dynamically collect user input value(s).
     * <!-- end-model-doc -->
     * @return the value of the '<em>Expression Parameters</em>' containment reference.
     * @see #setExpressionParameters(ExpressionParameters)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getExpressionArguments_ExpressionParameters()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='expressionParameters' namespace='##targetNamespace'"
     * @generated
     */
    ExpressionParameters getExpressionParameters();

    /**
     * Returns the collection of parameter definitions in the '<em><b>Expression Parameters</b></em>' containment reference.
     * The list contents are of type {@link org.eclipse.datatools.connectivity.oda.design.ExpressionParameterDefinition}.
     * <!-- begin-user-doc -->
     * <p>
     * An empty collection is returned if none is specified.
     * </p>
     * <!-- end-user-doc -->
     * @return collection of expression parameter definitions
     * @generated NOT
     */
    EList<ExpressionParameterDefinition> getExpressionParameterDefinitions();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ExpressionArguments#getExpressionParameters <em>Expression Parameters</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Expression Parameters</em>' containment reference.
     * @see #getExpressionParameters()
     * @generated
     */
    void setExpressionParameters( ExpressionParameters value );

    /**
     * Appends a new {@link ExpressionParameterDefinition} with the specified static value to 
     * the '<em>Expression Parameters</em>' containment reference.
     * @param aStaticValue     a static argument value of a filter expression
     * @return  the new ExpressionParameterDefinition appended
     * @generated NOT
     */
    ExpressionParameterDefinition addStaticParameter( Object aStaticValue );
    
    /**
     * Appends a new {@link ExpressionParameterDefinition} with the specified 
     * dynamic input parameter definition to 
     * the '<em>Expression Parameters</em>' containment reference.
     * @param inputParam    the definition of an input parameter
     * @return  the new ExpressionParameterDefinition appended
     * @generated NOT
     */
    ExpressionParameterDefinition addDynamicParameter( ParameterDefinition inputParam );

} // ExpressionArguments

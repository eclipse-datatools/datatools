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
 * $Id: ParameterDefinition.java,v 1.3 2006/11/09 00:50:27 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Top-level parameter definition.  Parameter could be scalar or complex type.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ParameterDefinition#getInOutMode <em>In Out Mode</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ParameterDefinition#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ParameterDefinition#getInputAttributes <em>Input Attributes</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ParameterDefinition#getOutputUsageHints <em>Output Usage Hints</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ParameterDefinition#getFields <em>Fields</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getParameterDefinition()
 * @model 
 * @generated
 */
public interface ParameterDefinition extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Indicates whether this parameter is defined to take input value.
     * @return	true if this parameter is defined to take input value; 
     * 			false otherwise.
     * @see #getInOutMode()
     * @generated NOT
     */
    boolean isInput();
    
    /**
     * Indicates whether this parameter is defined to have output value.
     * @return 	true if this parameter is defined to have output value;
     * 			false otherwise.
     * @see #getInOutMode()
     * @generated NOT
     */
    boolean isOutput();
    
    /**
     * Indicates whether this is a scalar parameter.
     * @return	true if this parameter has a scalar data type;
     * 			false if it is a complex parameter with nested fields.
     * @see #getFields()
     * @generated NOT
     */
    boolean isScalar();
    
    /**
     * A convenience method to return this parameter's default input value.
     * @return the value of the parameter InputElementAttributes '<em>Default Scalar Value</em>' attribute;
     *         returns null if this is not a scalar input parameter.
     * @see #setDefaultScalarValue(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getInputElementAttributes_DefaultScalarValue()
     * @since 3.0.3
     * @generated NOT
     */
    String getDefaultScalarValue();
    
    /**
     * A convenience method to set this parameter's default input value.
     * The specified value is applied only if this is defined as
     * a scalar input parameter.
     * @param value the new value of the parameter InputElementAttributes '<em>Default Scalar Value</em>' attribute.
     * @see #getDefaultScalarValue()
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getInputElementAttributes_DefaultScalarValue()
     * @since 3.0.3
     * @generated NOT
     */
    void setDefaultScalarValue( String value );

    /**
     * Returns the value of the '<em><b>In Out Mode</b></em>' attribute.
     * The default value is <code>"In"</code>.
     * The literals are from the enumeration {@link org.eclipse.datatools.connectivity.oda.design.ParameterMode}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>In Out Mode</em>' attribute.
     * @see org.eclipse.datatools.connectivity.oda.design.ParameterMode
     * @see #isSetInOutMode()
     * @see #unsetInOutMode()
     * @see #setInOutMode(ParameterMode)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getParameterDefinition_InOutMode()
     * @model default="In" unique="false" unsettable="true" required="true"
     * @generated
     */
    ParameterMode getInOutMode();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ParameterDefinition#getInOutMode <em>In Out Mode</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>In Out Mode</em>' attribute.
     * @see org.eclipse.datatools.connectivity.oda.design.ParameterMode
     * @see #isSetInOutMode()
     * @see #unsetInOutMode()
     * @see #getInOutMode()
     * @generated
     */
    void setInOutMode( ParameterMode value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ParameterDefinition#getInOutMode <em>In Out Mode</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetInOutMode()
     * @see #getInOutMode()
     * @see #setInOutMode(ParameterMode)
     * @generated
     */
    void unsetInOutMode();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ParameterDefinition#getInOutMode <em>In Out Mode</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>In Out Mode</em>' attribute is set.
     * @see #unsetInOutMode()
     * @see #getInOutMode()
     * @see #setInOutMode(ParameterMode)
     * @generated
     */
    boolean isSetInOutMode();

    /**
     * Returns the value of the '<em><b>Attributes</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Attributes applicable to both input and output parameter mode.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Attributes</em>' containment reference.
     * @see #setAttributes(DataElementAttributes)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getParameterDefinition_Attributes()
     * @model containment="true" resolveProxies="false" required="true"
     * @generated
     */
    DataElementAttributes getAttributes();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ParameterDefinition#getAttributes <em>Attributes</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Attributes</em>' containment reference.
     * @see #getAttributes()
     * @generated
     */
    void setAttributes( DataElementAttributes value );

    /**
     * Returns the value of the '<em><b>Input Attributes</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Required for input parameter.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Input Attributes</em>' containment reference.
     * @see #setInputAttributes(InputParameterAttributes)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getParameterDefinition_InputAttributes()
     * @model containment="true" resolveProxies="false"
     * @generated
     */
    InputParameterAttributes getInputAttributes();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ParameterDefinition#getInputAttributes <em>Input Attributes</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Input Attributes</em>' containment reference.
     * @see #getInputAttributes()
     * @generated
     */
    void setInputAttributes( InputParameterAttributes value );

    /**
     * Returns the value of the '<em><b>Output Usage Hints</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Optional for an output parameter.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Output Usage Hints</em>' containment reference.
     * @see #setOutputUsageHints(OutputElementAttributes)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getParameterDefinition_OutputUsageHints()
     * @model containment="true" resolveProxies="false"
     * @generated
     */
    OutputElementAttributes getOutputUsageHints();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ParameterDefinition#getOutputUsageHints <em>Output Usage Hints</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Output Usage Hints</em>' containment reference.
     * @see #getOutputUsageHints()
     * @generated
     */
    void setOutputUsageHints( OutputElementAttributes value );

    /**
     * Returns the value of the '<em><b>Fields</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Applicable to complex parameter only.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Fields</em>' containment reference.
     * @see #setFields(ParameterFields)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getParameterDefinition_Fields()
     * @model containment="true" resolveProxies="false"
     * @generated
     */
    ParameterFields getFields();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ParameterDefinition#getFields <em>Fields</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Fields</em>' containment reference.
     * @see #getFields()
     * @generated
     */
    void setFields( ParameterFields value );

} // ParameterDefinition

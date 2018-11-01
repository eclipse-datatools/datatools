/**
 *************************************************************************
 * Copyright (c) 2005, 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 *
 * $Id: ParameterFieldDefinition.java,v 1.2 2006/02/07 05:52:28 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The definition of each field in a complex parameter.  A parameter field is of scalar type.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ParameterFieldDefinition#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ParameterFieldDefinition#getInputAttributes <em>Input Attributes</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ParameterFieldDefinition#getOutputUsageHints <em>Output Usage Hints</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getParameterFieldDefinition()
 * @model extendedMetaData="name='ParameterFieldDefinition' kind='elementOnly'"
 * @generated
 */
public interface ParameterFieldDefinition extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2007 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Indicates whether this parameter field is part of a complex input parameter.
     * @return	true if this parameter field is part of a complex input parameter; 
     * 			false otherwise.
     * @generated NOT
     */
    boolean isInput();

    /**
     * Indicates whether this parameter field is part of a complex output parameter.
     * @return 	true if this parameter field is part of a complex output parameter;
     * 			false otherwise.
     * @generated NOT
     */
    boolean isOutput();

    /**
     * Returns the value of the '<em><b>Attributes</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Attributes applicable to both input and output parameter mode.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Attributes</em>' containment reference.
     * @see #setAttributes(DataElementAttributes)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getParameterFieldDefinition_Attributes()
     * @model containment="true" required="true"
     *        extendedMetaData="kind='element' name='attributes' namespace='##targetNamespace'"
     * @generated
     */
    DataElementAttributes getAttributes();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ParameterFieldDefinition#getAttributes <em>Attributes</em>}' containment reference.
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
     * Required for the field of an input parameter.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Input Attributes</em>' containment reference.
     * @see #setInputAttributes(InputElementAttributes)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getParameterFieldDefinition_InputAttributes()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='inputAttributes' namespace='##targetNamespace'"
     * @generated
     */
    InputElementAttributes getInputAttributes();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ParameterFieldDefinition#getInputAttributes <em>Input Attributes</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Input Attributes</em>' containment reference.
     * @see #getInputAttributes()
     * @generated
     */
    void setInputAttributes( InputElementAttributes value );

    /**
     * Returns the value of the '<em><b>Output Usage Hints</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Optional for the field of an output parameter.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Output Usage Hints</em>' containment reference.
     * @see #setOutputUsageHints(OutputElementAttributes)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getParameterFieldDefinition_OutputUsageHints()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='outputUsageHints' namespace='##targetNamespace'"
     * @generated
     */
    OutputElementAttributes getOutputUsageHints();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ParameterFieldDefinition#getOutputUsageHints <em>Output Usage Hints</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Output Usage Hints</em>' containment reference.
     * @see #getOutputUsageHints()
     * @generated
     */
    void setOutputUsageHints( OutputElementAttributes value );

} // ParameterFieldDefinition

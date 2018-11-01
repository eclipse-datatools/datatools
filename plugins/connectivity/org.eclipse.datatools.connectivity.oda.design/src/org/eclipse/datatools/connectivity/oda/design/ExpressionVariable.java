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
 * $Id: ExpressionVariable.java,v 1.1 2009/03/03 07:42:07 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expression Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Definition of a filter expression's variable.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ExpressionVariable#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ExpressionVariable#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.ExpressionVariable#getNativeDataTypeCode <em>Native Data Type Code</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getExpressionVariable()
 * @since 3.3 (DTP 1.8)
 * @model extendedMetaData="name='ExpressionVariable' kind='elementOnly'"
 * @generated
 */
public interface ExpressionVariable extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2009 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * The default value is <code>"ResultSetColumn"</code>.
     * The literals are from the enumeration {@link org.eclipse.datatools.connectivity.oda.design.ExpressionVariableType}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The type of variable.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see org.eclipse.datatools.connectivity.oda.design.ExpressionVariableType
     * @see #isSetType()
     * @see #unsetType()
     * @see #setType(ExpressionVariableType)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getExpressionVariable_Type()
     * @model default="ResultSetColumn" unsettable="true"
     *        extendedMetaData="kind='element' name='type' namespace='##targetNamespace'"
     * @generated
     */
    ExpressionVariableType getType();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ExpressionVariable#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see org.eclipse.datatools.connectivity.oda.design.ExpressionVariableType
     * @see #isSetType()
     * @see #unsetType()
     * @see #getType()
     * @generated
     */
    void setType( ExpressionVariableType value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ExpressionVariable#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetType()
     * @see #getType()
     * @see #setType(ExpressionVariableType)
     * @generated
     */
    void unsetType();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ExpressionVariable#getType <em>Type</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Type</em>' attribute is set.
     * @see #unsetType()
     * @see #getType()
     * @see #setType(ExpressionVariableType)
     * @generated
     */
    boolean isSetType();

    /**
     * Returns the value of the '<em><b>Identifier</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * A name or expression that identifies the variable used to evaluate an expression.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Identifier</em>' attribute.
     * @see #setIdentifier(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getExpressionVariable_Identifier()
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     *        extendedMetaData="kind='element' name='identifier' namespace='##targetNamespace'"
     * @generated
     */
    String getIdentifier();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ExpressionVariable#getIdentifier <em>Identifier</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Identifier</em>' attribute.
     * @see #getIdentifier()
     * @generated
     */
    void setIdentifier( String value );

    /**
     * Returns the value of the '<em><b>Native Data Type Code</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Native data type code of the identified variable.  If none or unknown value (0) is specified, the data element is mapped to an ODA String data type by default.  The native type code value is implementation-specific to individual ODA driver, which must support its mapping to one or more ODA data types.  The supported native-ODA data type mapping must be defined in the ODA dataSource extension.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Native Data Type Code</em>' attribute.
     * @see #isSetNativeDataTypeCode()
     * @see #unsetNativeDataTypeCode()
     * @see #setNativeDataTypeCode(int)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getExpressionVariable_NativeDataTypeCode()
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Int"
     *        extendedMetaData="kind='element' name='nativeDataTypeCode' namespace='##targetNamespace'"
     * @generated
     */
    int getNativeDataTypeCode();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ExpressionVariable#getNativeDataTypeCode <em>Native Data Type Code</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Native Data Type Code</em>' attribute.
     * @see #isSetNativeDataTypeCode()
     * @see #unsetNativeDataTypeCode()
     * @see #getNativeDataTypeCode()
     * @generated
     */
    void setNativeDataTypeCode( int value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ExpressionVariable#getNativeDataTypeCode <em>Native Data Type Code</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetNativeDataTypeCode()
     * @see #getNativeDataTypeCode()
     * @see #setNativeDataTypeCode(int)
     * @generated
     */
    void unsetNativeDataTypeCode();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.ExpressionVariable#getNativeDataTypeCode <em>Native Data Type Code</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Native Data Type Code</em>' attribute is set.
     * @see #unsetNativeDataTypeCode()
     * @see #getNativeDataTypeCode()
     * @see #setNativeDataTypeCode(int)
     * @generated
     */
    boolean isSetNativeDataTypeCode();

} // ExpressionVariable

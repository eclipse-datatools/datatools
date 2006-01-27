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
 * $Id: DataElementAttributes.java,v 1.1 2005/12/29 04:17:56 lchan Exp $
 */
package org.eclipse.datatools.connectivity.oda.design;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Common attributes for a data element.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#getPosition <em>Position</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#getNativeDataTypeCode <em>Native Data Type Code</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#getPrecision <em>Precision</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#getScale <em>Scale</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#getNullability <em>Nullability</em>}</li>
 *   <li>{@link org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#getUiHints <em>Ui Hints</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataElementAttributes()
 * @model 
 * @generated
 */
public interface DataElementAttributes extends EObject
{
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String copyright = "Copyright (c) 2005, 2006 Actuate Corporation"; //$NON-NLS-1$

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#getPrecision <em>Precision</em>}' attribute
     * that is applicable for the given ODA data type.
     * This provides the application logic on setting the applicable value
     * for the given data type, and would return
     * the applicable value in <code>getPrecision</code> method. 
     * @param value the new value of the '<em>Precision</em>' attribute.
     * @param odaDataType   the ODA data type of this data element
     * @see #setPrecision()
     * @see #isSetPrecision()
     * @see #unsetPrecision()
     * @see #getPrecision()
     */
    void setApplicablePrecision( short value, OdaScalarDataType odaDataType );

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#getScale <em>Scale</em>}' attribute
     * that is applicable for the given ODA data type.
     * This provides the application logic on setting the applicable value
     * for the given data type, and would return
     * the applicable value in <code>getScale</code> method. 
     * @param value the new value of the '<em>Scale</em>' attribute.
     * @param odaDataType   the ODA data type of this data element
     * @see #setScale()
     * @see #isSetScale()
     * @see #unsetScale()
     * @see #getScale()
     */
    void setApplicableScale( short value, OdaScalarDataType odaDataType );
    
    /**
     * Indicates whether this data element is defined to allow a null value.
     * Defaults to false if its nullability is unknown.
     * @return	true if the data element allows a null value;
     * 			false otherwise.
     */
    boolean allowsNull();

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * A name that uniquely identifies this data element.  If a data element can only be identified by position, this name may be empty.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataElementAttributes_Name()
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName( String value );

    /**
     * Returns the value of the '<em><b>Position</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * The 1-based index position (left-to-right order) within a collection of data elements.  The position is defined per the underlying data source, and is not necessarily sequential.  For example, a query may access 3 columns in a result set, but the data access design may choose to expose only column 2.  In this case, only one column element with postion 2 is defined in the result set definition.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Position</em>' attribute.
     * @see #isSetPosition()
     * @see #unsetPosition()
     * @see #setPosition(int)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataElementAttributes_Position()
     * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.UnsignedShort"
     * @generated
     */
    int getPosition();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#getPosition <em>Position</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Position</em>' attribute.
     * @see #isSetPosition()
     * @see #unsetPosition()
     * @see #getPosition()
     * @generated
     */
    void setPosition( int value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#getPosition <em>Position</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetPosition()
     * @see #getPosition()
     * @see #setPosition(int)
     * @generated
     */
    void unsetPosition();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#getPosition <em>Position</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Position</em>' attribute is set.
     * @see #unsetPosition()
     * @see #getPosition()
     * @see #setPosition(int)
     * @generated
     */
    boolean isSetPosition();

    /**
     * Returns the value of the '<em><b>Native Data Type Code</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Native data type code of the data element.  If none is specified, the data element is mapped to an ODA String data type by default.  The native type code value is implementation-specific to individual ODA driver, which must support its mapping to one or more ODA data types.  The supported native-ODA data type mapping must be defined in the ODA extension's plugin.xml .
     * <!-- end-model-doc -->
     * @return the value of the '<em>Native Data Type Code</em>' attribute.
     * @see #isSetNativeDataTypeCode()
     * @see #unsetNativeDataTypeCode()
     * @see #setNativeDataTypeCode(short)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataElementAttributes_NativeDataTypeCode()
     * @model unique="false" unsettable="true" dataType="org.eclipse.datatools.connectivity.oda.design.NativeDataTypeCode"
     * @generated
     */
    short getNativeDataTypeCode();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#getNativeDataTypeCode <em>Native Data Type Code</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Native Data Type Code</em>' attribute.
     * @see #isSetNativeDataTypeCode()
     * @see #unsetNativeDataTypeCode()
     * @see #getNativeDataTypeCode()
     * @generated
     */
    void setNativeDataTypeCode( short value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#getNativeDataTypeCode <em>Native Data Type Code</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetNativeDataTypeCode()
     * @see #getNativeDataTypeCode()
     * @see #setNativeDataTypeCode(short)
     * @generated
     */
    void unsetNativeDataTypeCode();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#getNativeDataTypeCode <em>Native Data Type Code</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Native Data Type Code</em>' attribute is set.
     * @see #unsetNativeDataTypeCode()
     * @see #getNativeDataTypeCode()
     * @see #setNativeDataTypeCode(short)
     * @generated
     */
    boolean isSetNativeDataTypeCode();

    /**
     * Returns the value of the '<em><b>Precision</b></em>' attribute.
     * The default value is <code>"-1"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Maximum number of decimal digits in a numeric value, or the length of a non-numeric value.  
     * A value of -1 indicates this attribute is not applicable.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Precision</em>' attribute.
     * @see #isSetPrecision()
     * @see #unsetPrecision()
     * @see #setPrecision(short)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataElementAttributes_Precision()
     * @model default="-1" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Short"
     * @generated
     */
    short getPrecision();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#getPrecision <em>Precision</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Precision</em>' attribute.
     * @see #isSetPrecision()
     * @see #unsetPrecision()
     * @see #getPrecision()
     * @generated
     */
    void setPrecision( short value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#getPrecision <em>Precision</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetPrecision()
     * @see #getPrecision()
     * @see #setPrecision(short)
     * @generated
     */
    void unsetPrecision();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#getPrecision <em>Precision</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Precision</em>' attribute is set.
     * @see #unsetPrecision()
     * @see #getPrecision()
     * @see #setPrecision(short)
     * @generated
     */
    boolean isSetPrecision();

    /**
     * Returns the value of the '<em><b>Scale</b></em>' attribute.
     * The default value is <code>"-1"</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * Maximum number of digits to the right of the decimal point.  A value of -1 indicates this attribute is not applicable.
     * <!-- end-model-doc -->
     * @return the value of the '<em>Scale</em>' attribute.
     * @see #isSetScale()
     * @see #unsetScale()
     * @see #setScale(short)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataElementAttributes_Scale()
     * @model default="-1" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Short"
     * @generated
     */
    short getScale();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#getScale <em>Scale</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Scale</em>' attribute.
     * @see #isSetScale()
     * @see #unsetScale()
     * @see #getScale()
     * @generated
     */
    void setScale( short value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#getScale <em>Scale</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetScale()
     * @see #getScale()
     * @see #setScale(short)
     * @generated
     */
    void unsetScale();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#getScale <em>Scale</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Scale</em>' attribute is set.
     * @see #unsetScale()
     * @see #getScale()
     * @see #setScale(short)
     * @generated
     */
    boolean isSetScale();

    /**
     * Returns the value of the '<em><b>Nullability</b></em>' attribute.
     * The default value is <code>"Unknown"</code>.
     * The literals are from the enumeration {@link org.eclipse.datatools.connectivity.oda.design.ElementNullability}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Nullability</em>' attribute.
     * @see org.eclipse.datatools.connectivity.oda.design.ElementNullability
     * @see #isSetNullability()
     * @see #unsetNullability()
     * @see #setNullability(ElementNullability)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataElementAttributes_Nullability()
     * @model default="Unknown" unique="false" unsettable="true"
     * @generated
     */
    ElementNullability getNullability();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#getNullability <em>Nullability</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Nullability</em>' attribute.
     * @see org.eclipse.datatools.connectivity.oda.design.ElementNullability
     * @see #isSetNullability()
     * @see #unsetNullability()
     * @see #getNullability()
     * @generated
     */
    void setNullability( ElementNullability value );

    /**
     * Unsets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#getNullability <em>Nullability</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isSetNullability()
     * @see #getNullability()
     * @see #setNullability(ElementNullability)
     * @generated
     */
    void unsetNullability();

    /**
     * Returns whether the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#getNullability <em>Nullability</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Nullability</em>' attribute is set.
     * @see #unsetNullability()
     * @see #getNullability()
     * @see #setNullability(ElementNullability)
     * @generated
     */
    boolean isSetNullability();

    /**
     * Returns the value of the '<em><b>Ui Hints</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Ui Hints</em>' containment reference.
     * @see #setUiHints(DataElementUIHints)
     * @see org.eclipse.datatools.connectivity.oda.design.DesignPackage#getDataElementAttributes_UiHints()
     * @model containment="true" resolveProxies="false"
     * @generated
     */
    DataElementUIHints getUiHints();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.connectivity.oda.design.DataElementAttributes#getUiHints <em>Ui Hints</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ui Hints</em>' containment reference.
     * @see #getUiHints()
     * @generated
     */
    void setUiHints( DataElementUIHints value );

} // DataElementAttributes

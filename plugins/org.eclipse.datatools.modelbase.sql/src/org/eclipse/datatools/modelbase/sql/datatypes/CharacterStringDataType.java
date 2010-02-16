/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.modelbase.sql.datatypes;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Character String Data Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Reference: 5WD-02-Foundation-2002-12 4.2 Character strings
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType#getLength <em>Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType#getCoercibility <em>Coercibility</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType#isFixedLength <em>Fixed Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType#getCollationName <em>Collation Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType#getCharacterSet <em>Character Set</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getCharacterStringDataType()
 * @model
 * @generated
 */
public interface CharacterStringDataType extends PredefinedDataType {
	/**
	 * Returns the value of the '<em><b>Length</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Length</em>' attribute.
	 * @see #setLength(int)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getCharacterStringDataType_Length()
	 * @model default="1"
	 * @generated
	 */
	int getLength();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType#getLength <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Length</em>' attribute.
	 * @see #getLength()
	 * @generated
	 */
	void setLength(int value);

	/**
	 * Returns the value of the '<em><b>Coercibility</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.datatypes.CoercibilityType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Coercibility</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Coercibility</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.CoercibilityType
	 * @see #setCoercibility(CoercibilityType)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getCharacterStringDataType_Coercibility()
	 * @model
	 * @generated
	 */
	CoercibilityType getCoercibility();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType#getCoercibility <em>Coercibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Coercibility</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.CoercibilityType
	 * @see #getCoercibility()
	 * @generated
	 */
	void setCoercibility(CoercibilityType value);

	/**
	 * Returns the value of the '<em><b>Fixed Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fixed Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fixed Length</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getCharacterStringDataType_FixedLength()
	 * @model changeable="false"
	 * @generated
	 */
	boolean isFixedLength();

	/**
	 * Returns the value of the '<em><b>Collation Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Collation Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Collation Name</em>' attribute.
	 * @see #setCollationName(String)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getCharacterStringDataType_CollationName()
	 * @model
	 * @generated
	 */
	String getCollationName();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType#getCollationName <em>Collation Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Collation Name</em>' attribute.
	 * @see #getCollationName()
	 * @generated
	 */
	void setCollationName(String value);

	/**
	 * Returns the value of the '<em><b>Character Set</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet#getCharacterStringDataType <em>Character String Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Character Set</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Character Set</em>' reference.
	 * @see #setCharacterSet(CharacterSet)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getCharacterStringDataType_CharacterSet()
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet#getCharacterStringDataType
	 * @model opposite="CharacterStringDataType" required="true"
	 * @generated
	 */
	CharacterSet getCharacterSet();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType#getCharacterSet <em>Character Set</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Character Set</em>' reference.
	 * @see #getCharacterSet()
	 * @generated
	 */
	void setCharacterSet(CharacterSet value);

} // CharacterStringDataType

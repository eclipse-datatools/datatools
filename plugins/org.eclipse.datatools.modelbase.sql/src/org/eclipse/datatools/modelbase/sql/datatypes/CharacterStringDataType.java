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
 * 4.1 Data types
 * 
 * For reference purposes:
 *  - The data types CHARACTER , CHARACTER VARYING , and CHARACTER LARGE OBJECT are collectively referred to as character string types.
 *  - The data type BINARY LARGE OBJECT is referred to as the binary string type and the values of binary string types are referred to as binary strings.
 *  - The data types CHARACTER LARGE OBJECT and BINARY LARGE OBJECT are collectively referred to as large object string types and the values of large object string types are referred to as large object strings.
 *  - Character string types and binary string types are collectively referred to as string types and values of string types are referred to as strings.
 * 
 * 4.2 Character strings
 * 
 * A character string is a sequence of characters. All the characters in a character string are taken from a single character set. A character string has a length, which is the number of characters in the sequence. The length is 0 (zero) or a positive integer. A character string type is described by a character string type descriptor.
 * 
 * A character string type descriptor contains:
 *  - The name of the specific character string type (CHARACTER , CHARACTER VARYING , and CHARACTER LARGE OBJECT ; NATIONAL CHARACTER , NATIONAL CHARACTER VARYING , and NATIONAL CHARACTER LARGE OBJECT are represented as CHARACTER , CHARACTER VARYING , and CHARACTER LARGE OBJECT , respectively).
 *  - The length or maximum length in characters of the character string type.
 *  - The catalog name, schema name, and character set name of the character set of the character string type.
 *  - The catalog name, schema name, and collation name of the collation of the character string type.
 * 
 * The character set of a character string type may be specified explicitly or implicitly.
 * 
 * The <key word>s NATIONAL CHARACTER are used to specify an implementation-defined character set. Special syntax (N'string') is provided for representing literals in that character set. With two exceptions, a character string expression is assignable only to sites of a character string type whose character set is the same. The exceptions are as specified in Subclause 4.2.7, "Universal character sets", and such other cases as may be implementation-defined. If a store assignment would result in the loss of non-<space> characters due to truncation, then an exception condition is raised. If a retrieval assignment or evaluation of a <cast specification> would result in the loss of characters due to truncation, then a warning condition is raised.
 * 
 * Character sets fall into three categories: those defined by national or international standards, those defined by SQL-implementations, and those defined by applications. The character sets defined by ISO/IEC 10646 and The Unicode Standard are known as Universal Character Sets (UCS) and their treatment is described in Subclause 4.2.7, "Universal character sets". Every character set contains the <space> character (equivalent to U+0020). An application defines a character set by assigning a new name to a character set from one of the first two categories. They can be defined to "reside" in any schema chosen by the application. Character sets defined by standards or by SQL-implementations reside in the Information Schema (named INFORMATION_SCHEMA) in each catalog, as do collations defined by standards and collations, transliterations, and transcodings defined by SQL-implementations.
 * 
 * NOTE 9 : The Information Schema is defined in ISO/IEC 9075-11.
 * 
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
public interface CharacterStringDataType extends PredefinedDataType{
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
	 * @model changeable="false" derived="true"
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
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Character Set</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Character Set</em>' reference.
	 * @see #setCharacterSet(CharacterSet)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getCharacterStringDataType_CharacterSet()
	 * @model required="true"
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

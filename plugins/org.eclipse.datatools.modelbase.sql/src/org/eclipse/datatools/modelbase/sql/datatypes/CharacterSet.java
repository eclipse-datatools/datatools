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

import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Character Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 4.2.6 Character sets
 * 
 * An SQL <character set specification> allows a reference to a character set name defined by a standard, an SQLimplementation,
 * or a user. A character set is described by a character set descriptor. A character set descriptor includes:
 *  - The name of the character set.
 *  - The name of the character repertoire for the character set.
 *  - The name of the character encoding form for the character set.
 *  - The name of the default collation for the character set.
 * 
 * The following SQL supported character set names are specified as part of ISO/IEC 9075:
 *  - SQL_CHARACTER is a character set whose repertoire is SQL_CHARACTER and whose character encoding form is SQL_CHARACTER. The name of its default collation is SQL_CHARACTER.
 *  - GRAPHIC_IRV is a character set whose repertoire is GRAPHIC_IRV and whose character encoding form is GRAPHIC_IRV. The name of its default collation is GRAPHIC_IRV.
 *  - ASCII_GRAPHIC is a synonym for GRAPHIC_IRV.
 *  - LATIN1 is a character set whose repertoire is LATIN1 and whose character encoding form is LATIN1. The name of its default collation is LATIN1.
 *  - ISO8BIT is a character set whose repertoire is ISO8BIT and whose character encoding form is ISO8BIT. The name of its default collation is ISO8BIT.
 *  - ASCII_FULL is a synonym for ISO8BIT.
 *  - UTF32 is a character set whose repertoire is UCS and whose character encoding form is UTF32. It is implementation-defined whether the name of its default collation is UCS_BASIC or UNICODE.
 *  - UTF16 is a character set whose repertoire is UCS and whose character encoding form is UTF16. It is implementation-defined whether the name of its default collation is UCS_BASIC or UNICODE.
 *  - UTF8 is the name of a character set whose repertoire is UCS and whose character encoding form is UTF8. It is implementation-defined whether the name of its default collation is UCS_BASIC or UNICODE.
 *  - SQL_TEXT is a character set whose repertoire is SQL_TEXT and whose character encoding form is SQL_TEXT. The name of its default collation is SQL_TEXT.
 *  - SQL_IDENTIFIER is a character set whose repertoire is SQL_IDENTIFIER and whose character encoding form is SQL_IDENTIFIER. The name of its default collation is SQL_IDENTIFIER.
 * 
 * 
 * 4.2.7 Universal character sets
 * 
 * A UCS string is a character string whose character repertoire is UCS and whose character encoding form is one of UTF8, UTF16, or UTF32. Any two UCS strings are comparable.
 * An SQL-implementation may assume that all UCS strings are normalized in Normalization Form C (NFC), as specified by [Unicode15]. With the exception of <normalize function> and <normalized predicate> , the result of any operation on an unnormalized UCS string is implementation-defined.
 * 
 * Conversion of UCS strings from one character set to another is automatic. Detection of a noncharacter in a UCS-string causes an exception condition to be raised. The detection of an unassigned code point does not.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet#getRepertoire <em>Repertoire</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet#getDefaultCollation <em>Default Collation</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet#getEncoding <em>Encoding</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet#getSchema <em>Schema</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getCharacterSet()
 * @model 
 * @generated
 */
public interface CharacterSet extends SQLObject{
	/**
	 * Returns the value of the '<em><b>Repertoire</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Repertoire</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Repertoire</em>' attribute.
	 * @see #setRepertoire(String)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getCharacterSet_Repertoire()
	 * @model 
	 * @generated
	 */
	String getRepertoire();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet#getRepertoire <em>Repertoire</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Repertoire</em>' attribute.
	 * @see #getRepertoire()
	 * @generated
	 */
	void setRepertoire(String value);

	/**
	 * Returns the value of the '<em><b>Default Collation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Collation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Collation</em>' attribute.
	 * @see #setDefaultCollation(String)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getCharacterSet_DefaultCollation()
	 * @model 
	 * @generated
	 */
	String getDefaultCollation();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet#getDefaultCollation <em>Default Collation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Collation</em>' attribute.
	 * @see #getDefaultCollation()
	 * @generated
	 */
	void setDefaultCollation(String value);

	/**
	 * Returns the value of the '<em><b>Encoding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Encoding</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Encoding</em>' attribute.
	 * @see #setEncoding(String)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getCharacterSet_Encoding()
	 * @model 
	 * @generated
	 */
	String getEncoding();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet#getEncoding <em>Encoding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Encoding</em>' attribute.
	 * @see #getEncoding()
	 * @generated
	 */
	void setEncoding(String value);

	/**
	 * Returns the value of the '<em><b>Schema</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getCharSets <em>Char Sets</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Schema</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Schema</em>' reference.
	 * @see #setSchema(Schema)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getCharacterSet_Schema()
	 * @see org.eclipse.datatools.modelbase.sql.schema.Schema#getCharSets
	 * @model opposite="charSets" required="true"
	 * @generated
	 */
	Schema getSchema();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet#getSchema <em>Schema</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Schema</em>' reference.
	 * @see #getSchema()
	 * @generated
	 */
	void setSchema(Schema value);

} // CharacterSet

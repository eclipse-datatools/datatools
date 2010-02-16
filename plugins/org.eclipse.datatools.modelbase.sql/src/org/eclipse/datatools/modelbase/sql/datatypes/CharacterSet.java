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
 * Reference: 5WD-02-Foundation-2002-12 4.2.6 Character sets, 5WD-02-Foundation-2002-12 4.2.7 Universal character sets
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet#getRepertoire <em>Repertoire</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet#getDefaultCollation <em>Default Collation</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet#getEncoding <em>Encoding</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet#getCharacterStringDataType <em>Character String Data Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet#getSchema <em>Schema</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getCharacterSet()
 * @model
 * @generated
 */
public interface CharacterSet extends SQLObject {
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
	 * Returns the value of the '<em><b>Character String Data Type</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType#getCharacterSet <em>Character Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Character String Data Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Character String Data Type</em>' reference.
	 * @see #setCharacterStringDataType(CharacterStringDataType)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getCharacterSet_CharacterStringDataType()
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType#getCharacterSet
	 * @model opposite="characterSet" required="true"
	 * @generated
	 */
	CharacterStringDataType getCharacterStringDataType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet#getCharacterStringDataType <em>Character String Data Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Character String Data Type</em>' reference.
	 * @see #getCharacterStringDataType()
	 * @generated
	 */
	void setCharacterStringDataType(CharacterStringDataType value);

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

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

import org.eclipse.datatools.modelbase.sql.schema.Schema;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>User Defined Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 4.7 User-defined types
 * 
 * A user-defined type is a schema object, identified by a <user-defined type name> . The definition of a userdefined type specifies a number of components, including in particular a list of attribute definitions. Although the attribute definitions are said to define the representation of the user-defined type, in fact they implicitly define certain functions (observers and mutators) that are part of the interface of the user-defined type; physical representations of user-defined type values are implementation-dependent.
 * 
 * The representation of a user-defined type is expressed either as a single data type (some predefined data type, called the source type), in which case the user-defined type is said to be a distinct type, or as a list of attribute definitions, in which case it is said to be a structured type.
 * 
 * A user-defined type is described by a user-defined type descriptor. A user-defined type descriptor contains:
 *  - The name of the user-defined type (<user-defined type name> ). This is the type designator of that type, used in type precedence lists (see Subclause 9.5, "Type precedence list determination").
 *  - An indication of whether the user-defined type is a structured type or a distinct type.
 *  - The ordering form for the user-defined type (EQUALS , FULL , or NONE ).
 *  - The ordering category for the user-defined type (RELATIVE , MAP , or STATE ).
 *  - A <specific routine designator> identifying the ordering function, depending on the ordering category.
 *  - If the user-defined type is a direct subtype of another user-defined type, then the name of that user-defined type.
 *  - If the representation is a predefined data type, then the descriptor of that type; otherwise the attribute descriptor of every originally-defined attribute and every inherited attribute of the user-defined type.
 *  - An indication of whether the user-defined type is instantiable or not instantiable.
 *  - An indication of whether the user-defined type is final or not final.
 *  - The transform descriptor of the user-defined type.
 *  - If the user-defined type is a structured type, then:
 *  - Whether the referencing type of the structured type has a user-defined representation, a derived representation, or a system-defined representation.
 *  - If user-defined representation is specified, then the type descriptor of the representation type of the referencing type of the structured type; otherwise, if derived representation is specified, then the list of attributes.
 * NOTE 15 - "user-defined representation", "derived representation", and "system-defined representation" of a reference type are defined in Subclause 4.9, "Reference types".
 *  - If the <method specification list> is specified, then for each <method specification> contained in <method specification list> , a method specification descriptor that includes:
 *  - The <method name>.
 *  - The <specific method name>.
 *  - The <SQL parameter declaration list> augmented to include the implicit first parameter with parameter name SELF.
 *  - The <language name>.
 *  - If the <language name> is not SQL, then the <parameter style>.
 *  - The <returns data type>.
 *  - The <result cast from type> , if any.
 *  - An indication as to whether the <method specification> is an <original method specification> or an <overriding method specification> .
 *  - If the <method specification> is an <original method specification> , then an indication of whether STATIC or CONSTRUCTOR is specified.
 *  - An indication whether the method is deterministic.
 *  - An indication whether the method possibly writes SQL data, possibly reads SQL data, possibly contains SQL, or does not possibly contain SQL.
 *  - An indication whether the method should not be invoked if any argument is the null value, in which case the value of the method is the null value.
 * NOTE 16 - The characteristics of an <overriding method specification> other than the <method name> , <SQL parameter declaration list> , and <returns data type> are the same as the characteristics for the corresponding <original method specification> .
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType#getSchema <em>Schema</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType#getOrdering <em>Ordering</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getUserDefinedType()
 * @model abstract="true"
 * @generated
 */
public interface UserDefinedType extends DataType{
	/**
	 * Returns the value of the '<em><b>Schema</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.schema.Schema#getUserDefinedTypes <em>User Defined Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Schema</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Schema</em>' reference.
	 * @see #setSchema(Schema)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getUserDefinedType_Schema()
	 * @see org.eclipse.datatools.modelbase.sql.schema.Schema#getUserDefinedTypes
	 * @model opposite="userDefinedTypes" required="true"
	 * @generated
	 */
	Schema getSchema();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType#getSchema <em>Schema</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Schema</em>' reference.
	 * @see #getSchema()
	 * @generated
	 */
	void setSchema(Schema value);

	/**
	 * Returns the value of the '<em><b>Ordering</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ordering</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ordering</em>' containment reference.
	 * @see #setOrdering(UserDefinedTypeOrdering)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getUserDefinedType_Ordering()
	 * @model containment="true"
	 * @generated
	 */
	UserDefinedTypeOrdering getOrdering();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType#getOrdering <em>Ordering</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ordering</em>' containment reference.
	 * @see #getOrdering()
	 * @generated
	 */
	void setOrdering(UserDefinedTypeOrdering value);

} // UserDefinedType

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
 * A representation of the model object '<em><b>Predefined Data Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 4.1 Data types
 * 
 * A data type is a set of representable values. Every representable value belongs to at least one data type and some belong to several data types. [...]
 * 
 * Every predefined data type is a subtype of itself and of no other data types. It follows that every predefined data type is a supertype of itself and of no other data types. The predefined data types are individually described in each of Subclause 4.2, "Character strings", through Subclause 4.6, "Datetimes and intervals". Row types, reference types and collection types are described in Subclause 4.8, "Row types", Subclause 4.9, "Reference types", Subclause 4.10, "Collection types", respectively.
 * 
 * SQL defines predefined data types named by the following <key word> s: CHARACTER , CHARACTER VARYING , CHARACTER LARGE OBJECT , BINARY LARGE OBJECT , NUMERIC , DECIMAL , SMALLINT , INTEGER , BIGINT , FLOAT , REAL , DOUBLE PRECISION , BOOLEAN , DATE , TIME , TIMESTAMP , and INTERVAL . These names are used in the type designators that constitute the type precedence lists specified in Subclause 9.5, "Type precedence list determination".
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType#getPrimitiveType <em>Primitive Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getPredefinedDataType()
 * @model abstract="true"
 * @generated
 */
public interface PredefinedDataType extends SQLDataType{
	/**
	 * Returns the value of the '<em><b>Primitive Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Primitive Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Primitive Type</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType
	 * @see #setPrimitiveType(PrimitiveType)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getPredefinedDataType_PrimitiveType()
	 * @model 
	 * @generated
	 */
	PrimitiveType getPrimitiveType();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType#getPrimitiveType <em>Primitive Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Primitive Type</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType
	 * @see #getPrimitiveType()
	 * @generated
	 */
	void setPrimitiveType(PrimitiveType value);

} // PredefinedDataType

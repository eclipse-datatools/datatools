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
 * A representation of the model object '<em><b>Fixed Precision Data Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 4.1 Data types
 * 
 * For reference purposes:
 * [...]
 *  - The data types NUMERIC , DECIMAL , SMALLINT , INTEGER , and BIGINT are collectively referred to as exact numeric types.
 *  - The data types FLOAT , REAL , and DOUBLE PRECISION are collectively referred to as approximate numeric types.
 *  - Exact numeric types and approximate numeric types are collectively referred to as numeric types. Values of numeric types are referred to as numbers.
 * 
 * 4.4.1 Characteristics of numbers
 * 
 * An exact numeric type has a precision P and a scale S. P is a positive integer that determines the number of significant digits in a particular radix R, where R is either 2 or 10. S is a non-negative integer. Every value of an exact numeric type of scale S is of the form n x 10-S, where n is an integer such that -RP <= n < RP.
 * NOTE 13 - Not every value in that range is necessarily a value of the type in question.
 * 
 * An approximate numeric value consists of a mantissa and an exponent. The mantissa is a signed numeric value, and the exponent is a signed integer that specifies the magnitude of the mantissa. An approximate numeric value has a precision. The precision is a positive integer that specifies the number of significant binary digits in the mantissa. The value of an approximate numeric value is the mantissa multiplied by a factor determined by the exponent.
 * 
 * An <approximate numeric literal> ANL consists of an <exact numeric literal> (called the <mantissa> ), the letter 'E' or 'e', and a <signed integer> (called the <exponent> ). If M is the value of the <mantissa> and E is the value of the <exponent> , then M * 10E is the apparent value of ANL. The actual value of ANL is approximately the apparent value of ANL, according to implementation-defined rules.
 * 
 * <!-- end-model-doc -->
 *
 *
 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getFixedPrecisionDataType()
 * @model 
 * @generated
 */
public interface FixedPrecisionDataType extends ExactNumericDataType{
} // FixedPrecisionDataType

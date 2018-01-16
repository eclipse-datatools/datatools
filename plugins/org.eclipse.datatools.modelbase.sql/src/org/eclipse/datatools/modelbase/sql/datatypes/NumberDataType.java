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
 * A representation of the model object '<em><b>Number Data Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 4.4 Numbers
 * 
 * A number is either an exact numeric value or an approximate numeric value. Any two numbers are comparable.
 * A numeric type is described by a numeric type descriptor. A numeric type descriptor contains:
 *  - The name of the specific numeric type (NUMERIC , DECIMAL , SMALLINT , INTEGER , BIGINT , FLOAT , REAL , or DOUBLE PRECISION).
 *  - The precision of the numeric type.
 *  - The scale of the numeric type, if it is an exact numeric type.
 *  - An indication of whether the precision (and scale) are expressed in decimal or binary terms.
 * 
 * An SQL-implementation is permitted to regard certain <exact numeric type> s as equivalent, if they have the same precision, scale, and radix, as permitted by the Syntax Rules of Subclause 6.1, "<data type> ". When two or more <exact numeric type> s are equivalent, the SQL-implementation chooses one of these equivalent <exact numeric type> s as the normal form representing that equivalence class of <exact numeric type> s. The normal form determines the name of the exact numeric type in the numeric type descriptor.
 * 
 * Similarly, an SQL-implementation is permitted to regard certain <approximate numeric type> s as equivalent, as permitted the Syntax Rules of Subclause 6.1, "<data type> ", in which case the SQL-implementation chooses a normal form to represent each equivalence class of <approximate numeric type> and the normal form determines the name of the approximate numeric type.
 * 
 * For every numeric type, the least value is less than zero and the greatest value is greater than zero.
 * 
 * 4.4.1 Characteristics of numbers
 * 
 * An exact numeric type has a precision P and a scale S. P is a positive integer that determines the number of significant digits in a particular radix R, where R is either 2 or 10. S is a non-negative integer. Every value of an exact numeric type of scale S is of the form n ? 10-S, where n is an integer such that -RP ? n < RP.
 * NOTE 13 - Not every value in that range is necessarily a value of the type in question.
 * 
 * An approximate numeric value consists of a mantissa and an exponent. The mantissa is a signed numeric value, and the exponent is a signed integer that specifies the magnitude of the mantissa. An approximate numeric value has a precision. The precision is a positive integer that specifies the number of significant binary digits in the mantissa. The value of an approximate numeric value is the mantissa multiplied by a factor determined by the exponent.
 * 
 * An <approximate numeric literal> ANL consists of an <exact numeric literal> (called the <mantissa> ), the letter 'E' or 'e', and a <signed integer> (called the <exponent> ). If M is the value of the <mantissa> and E is the value of the <exponent> , then M * 10E is the apparent value of ANL. The actual value of ANL is approximately the apparent value of ANL, according to implementation-defined rules.
 * 
 * A number is assignable only to sites of numeric type. If an assignment of some number would result in a loss of its most significant digit, an exception condition is raised. If least significant digits are lost, implementationdefined rounding or truncating occurs, with no exception condition being raised. The rules for arithmetic are specified in Subclause 6.26, "<numeric value expression> ".
 * 
 * Whenever an exact or approximate numeric value is assigned to an exact numeric value site, an approximation of its value that preserves leading significant digits after rounding or truncating is represented in the declared type of the target. The value is converted to have the precision and scale of the target. The choice of whether to truncate or round is implementation-defined.
 * 
 * An approximation obtained by truncation of a numeric value N for an <exact numeric type> T is a value V in T such that N is not closer to zero than is V and there is no value in T between V and N. An approximation obtained by rounding of a numeric value N for an <exact numeric type> T is a value V in T such that the absolute value of the difference between N and the numeric value of V is not greater than half the absolute value of the difference between two successive numeric values in T. If there is more than one such value V, then it is implementation-defined which one is taken.
 * 
 * All numeric values between the smallest and the largest value, inclusive, in a given exact numeric type have an approximation obtained by rounding or truncation for that type; it is implementation-defined which other numeric values have such approximations. An approximation obtained by truncation or rounding of a numeric value N for an <approximate numeric type> T is a value V in T such that there is no numeric value in T and distinct from that of V that lies between the numeric value of V and N, inclusive.
 * 
 * If there is more than one such value V then it is implementation-defined which one is taken. It is implementationdefined which numeric values have approximations obtained by rounding or truncation for a given approximate numeric type. Whenever an exact or approximate numeric value is assigned to an approximate numeric value site, an approximation of its value is represented in the declared type of the target. The value is converted to have the precision of the target.
 * 
 * Operations on numbers are performed according to the normal rules of arithmetic, within implementationdefined limits, except as provided for in Subclause 6.26, "<numeric value expression> ".
 * 
 * <!-- end-model-doc -->
 *
 *
 * @see com.ibm.db.models.sql.datatypes.SQLDataTypesPackage#getNumberDataType()
 * @model abstract="true"
 * @generated
 */
public interface NumberDataType extends PredefinedDataType{
} // NumberDataType

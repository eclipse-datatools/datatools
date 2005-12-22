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
 * A representation of the model object '<em><b>Interval Data Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 4.6 Datetimes and intervals
 * 
 * An interval data type is described by an interval data type descriptor.
 * 
 * An interval data type descriptor contains:
 *  - The name of the interval data type (INTERVAL ).
 *  - An indication of whether the interval data type is a year-month interval or a day-time interval.
 *  - The <interval qualifier> that describes the precision of the interval data type.
 * 
 * A value described by an interval data type descriptor is always signed. Every datetime or interval data type has an implied length in positions. Let D denote a value in some datetime or interval data type DT. The length in positions of DT is constant for all D. The length in positions is the number of characters from the character set SQL_TEXT that it would take to represent any value in a given datetime or interval data type.
 * 
 * An approximation obtained by rounding of a datetime or interval value D for a <datetime type> or <interval type> T is a value V in T such that the absolute value of the difference between D and the numeric value of V is not greater than half the absolute value of the difference between two successive datetime or interval values in T. If there is more than one such value V, then it is implementation-defined which one is taken.
 * 
 * 4.6.2 Intervals
 * 
 * There are two classes of intervals. One class, called year-month intervals, has an express or implied datetime precision that includes no fields other than YEAR and MONTH , though not both are required. The other class, called day-time intervals, has an express or implied interval precision that can include any fields other than YEAR or MONTH .
 * 
 * The actual subset of fields that comprise a value of either type of interval is defined by an <interval qualifier> and this subset is known as the precision of the value. Within a value of type interval, the first field is constrained only by the <interval leading field precision> of the associated <interval qualifier> . Table 6, "Valid values for fields in INTERVAL values", specifies the constraints on subsequent field values.
 * 
 * Values in interval fields other than SECOND are integers and have precision 2 when not the first field. SECOND, however, can be defined to have an <interval fractional seconds precision> that indicates the number of decimal digits maintained following the decimal point in the seconds value. When not the first field, SECOND has a precision of 2 places before the decimal point.
 * 
 * Fields comprising an item of type interval are also constrained by the definition of the Gregorian calendar. Year-month intervals are comparable only with other year-month intervals. If two year-month intervals have different interval precisions, they are, for the purpose of any operations between them, effectively converted to the same precision by appending new <primary datetime field> s to either the most significant end of one interval, the least significant end of one interval, or both. New least significant <primary datetime field> s are assigned a value of 0 (zero). When it is necessary to add new most significant datetime fields, the associated value is effectively converted to the new precision in a manner obeying the natural rules for dates and times associated with the Gregorian calendar.
 * 
 * Day-time intervals are comparable only with other day-time intervals. If two day-time intervals have different interval precisions, they are, for the purpose of any operations between them, effectively converted to the same precision by appending new <primary datetime field> s to either the most significant end of one interval or the least significant end of one interval, or both. New least significant <primary datetime field> s are assigned a value of 0 (zero). When it is necessary to add new most significant datetime fields, the associated value is effectively converted to the new precision in a manner obeying the natural rules for dates and times associated with the Gregorian calendar.
 * 
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType#getLeadingQualifier <em>Leading Qualifier</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType#getTrailingQualifier <em>Trailing Qualifier</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType#getLeadingFieldPrecision <em>Leading Field Precision</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType#getTrailingFieldPrecision <em>Trailing Field Precision</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType#getFractionalSecondsPrecision <em>Fractional Seconds Precision</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getIntervalDataType()
 * @model
 * @generated
 */
public interface IntervalDataType extends PredefinedDataType{
	/**
	 * Returns the value of the '<em><b>Leading Qualifier</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalQualifierType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Leading Qualifier</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Leading Qualifier</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.IntervalQualifierType
	 * @see #setLeadingQualifier(IntervalQualifierType)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getIntervalDataType_LeadingQualifier()
	 * @model
	 * @generated
	 */
	IntervalQualifierType getLeadingQualifier();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType#getLeadingQualifier <em>Leading Qualifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Leading Qualifier</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.IntervalQualifierType
	 * @see #getLeadingQualifier()
	 * @generated
	 */
	void setLeadingQualifier(IntervalQualifierType value);

	/**
	 * Returns the value of the '<em><b>Trailing Qualifier</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalQualifierType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trailing Qualifier</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trailing Qualifier</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.IntervalQualifierType
	 * @see #setTrailingQualifier(IntervalQualifierType)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getIntervalDataType_TrailingQualifier()
	 * @model
	 * @generated
	 */
	IntervalQualifierType getTrailingQualifier();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType#getTrailingQualifier <em>Trailing Qualifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trailing Qualifier</em>' attribute.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.IntervalQualifierType
	 * @see #getTrailingQualifier()
	 * @generated
	 */
	void setTrailingQualifier(IntervalQualifierType value);

	/**
	 * Returns the value of the '<em><b>Leading Field Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Leading Field Precision</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Leading Field Precision</em>' attribute.
	 * @see #setLeadingFieldPrecision(int)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getIntervalDataType_LeadingFieldPrecision()
	 * @model
	 * @generated
	 */
	int getLeadingFieldPrecision();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType#getLeadingFieldPrecision <em>Leading Field Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Leading Field Precision</em>' attribute.
	 * @see #getLeadingFieldPrecision()
	 * @generated
	 */
	void setLeadingFieldPrecision(int value);

	/**
	 * Returns the value of the '<em><b>Trailing Field Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trailing Field Precision</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trailing Field Precision</em>' attribute.
	 * @see #setTrailingFieldPrecision(int)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getIntervalDataType_TrailingFieldPrecision()
	 * @model
	 * @generated
	 */
	int getTrailingFieldPrecision();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType#getTrailingFieldPrecision <em>Trailing Field Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trailing Field Precision</em>' attribute.
	 * @see #getTrailingFieldPrecision()
	 * @generated
	 */
	void setTrailingFieldPrecision(int value);

	/**
	 * Returns the value of the '<em><b>Fractional Seconds Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fractional Seconds Precision</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fractional Seconds Precision</em>' attribute.
	 * @see #setFractionalSecondsPrecision(int)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getIntervalDataType_FractionalSecondsPrecision()
	 * @model
	 * @generated
	 */
	int getFractionalSecondsPrecision();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType#getFractionalSecondsPrecision <em>Fractional Seconds Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fractional Seconds Precision</em>' attribute.
	 * @see #getFractionalSecondsPrecision()
	 * @generated
	 */
	void setFractionalSecondsPrecision(int value);

} // IntervalDataType

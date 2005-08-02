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
 * A representation of the model object '<em><b>Time Data Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 4.6.1 Datetimes
 * 
 * Table 2, "Fields in datetime values" , specifies the fields that can make up a datetime value; a datetime value is made up of a subset of those fields. Not all of the fields shown are required to be in the subset, but every field that appears in the table between the first included primary field and the last included primary field shall also be included. If either time zone field is in the subset, then both of them shall be included.
 * 
 * There is an ordering of the significance of <primary datetime field> s. This is, from most significant to least significant: YEAR , MONTH , DAY , HOUR , MINUTE , and SECOND .
 * 
 * The <primary datetime field> s other than SECOND contain non-negative integer values, constrained by the natural rules for dates using the Gregorian calendar. SECOND , however, can be defined to have a <time fractional seconds precision> that indicates the number of decimal digits maintained following the decimal point in the seconds value, a non-negative exact numeric value.
 * 
 * There are three classes of datetime data types defined within this part of ISO/IEC 9075:
 *  - DATE: contains the <primary datetime field> s YEAR , MONTH , and DAY .
 *  - TIME: contains the <primary datetime field> s HOUR , MINUTE , and SECOND .
 *  - TIMESTAMP: contains the <primary datetime field> s YEAR , MONTH , DAY , HOUR , MINUTE , and SECOND .
 * 
 * Items of type datetime are comparable only if they have the same <primary datetime field> s. A datetime data type that specifies WITH TIME ZONE is a data type that is datetime with time zone, while a datetime data type that specifies WITHOUT TIME ZONE is a data type that is datetime without time zone. The surface of the earth is divided into zones, called time zones, in which every correct clock tells the same time, known as local time. Local time is equal to UTC (Coordinated Universal Time) plus the time zone displacement, which is an interval value that ranges between INTERVAL '-12:59' HOUR TO MINUTE and INTERVAL '+14:00' HOUR TO MINUTE . The time zone displacement is constant throughout a time zone, changing at the beginning and end of Daylight Time, where applicable.
 * 
 * A datetime value, of data type TIME WITHOUT TIME ZONE or TIMESTAMP WITHOUT TIME ZONE , may represent a local time, whereas a datetime value of data type TIME WITH TIME ZONE or TIMESTAMP WITH TIME ZONE represents UTC.
 * 
 * On occasion, UTC is adjusted by the omission of a second or the insertion of a "leap second" in order to maintain synchronization with sidereal time. This implies that sometimes, but very rarely, a particular minute will contain exactly 59, 61, or 62 seconds. Whether an SQL-implementation supports leap seconds, and the consequences of such support for date and interval arithmetic, is implementation-defined.
 * For the convenience of users, whenever a datetime value with time zone is to be implicitly derived from one without (for example, in a simple assignment operation), SQL assumes the value without time zone to be local, subtracts the current default time zone displacement of the SQL-session from it to give UTC, and associates that time zone displacement with the result. Conversely, whenever a datetime value without time zone is to be implicitly derived from one with, SQL assumes the value with time zone to be UTC, adds the time zone displacement to it to give local time, and the result, without any time zone displacement, is local. The preceding principles, as implemented by <cast specification> , result in data type conversions between the various datetime data types, as summarized in Table 3, "Datetime data type conversions".
 * 
 * A datetime is assignable to a site only if the source and target of the assignment are both of type DATE , or both of type TIME (regardless whether WITH TIME ZONE or WITHOUT TIME ZONE is specified or implicit), or both of type TIMESTAMP (regardless whether WITH TIME ZONE or WITHOUT TIME ZONE is specified or implicit).
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.TimeDataType#getFractionalSecondsPrecision <em>Fractional Seconds Precision</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.datatypes.TimeDataType#isTimeZone <em>Time Zone</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getTimeDataType()
 * @model 
 * @generated
 */
public interface TimeDataType extends PredefinedDataType{
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
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getTimeDataType_FractionalSecondsPrecision()
	 * @model 
	 * @generated
	 */
	int getFractionalSecondsPrecision();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.TimeDataType#getFractionalSecondsPrecision <em>Fractional Seconds Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fractional Seconds Precision</em>' attribute.
	 * @see #getFractionalSecondsPrecision()
	 * @generated
	 */
	void setFractionalSecondsPrecision(int value);

	/**
	 * Returns the value of the '<em><b>Time Zone</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time Zone</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Zone</em>' attribute.
	 * @see #setTimeZone(boolean)
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#getTimeDataType_TimeZone()
	 * @model default="false"
	 * @generated
	 */
	boolean isTimeZone();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.datatypes.TimeDataType#isTimeZone <em>Time Zone</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Zone</em>' attribute.
	 * @see #isTimeZone()
	 * @generated
	 */
	void setTimeZone(boolean value);

} // TimeDataType

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
 * Reference: 5WD-02-Foundation-2002-12 4.6.1 Datetimes
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
public interface TimeDataType extends PredefinedDataType {
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

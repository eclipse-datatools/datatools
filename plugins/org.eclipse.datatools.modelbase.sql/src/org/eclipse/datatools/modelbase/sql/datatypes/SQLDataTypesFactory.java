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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage
 * @generated
 */
public interface SQLDataTypesFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SQLDataTypesFactory eINSTANCE = org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Character String Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Character String Data Type</em>'.
	 * @generated
	 */
	CharacterStringDataType createCharacterStringDataType();

	/**
	 * Returns a new object of class '<em>Row Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Row Data Type</em>'.
	 * @generated
	 */
	RowDataType createRowDataType();

	/**
	 * Returns a new object of class '<em>Boolean Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Boolean Data Type</em>'.
	 * @generated
	 */
	BooleanDataType createBooleanDataType();

	/**
	 * Returns a new object of class '<em>Interval Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Interval Data Type</em>'.
	 * @generated
	 */
	IntervalDataType createIntervalDataType();

	/**
	 * Returns a new object of class '<em>Binary String Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Binary String Data Type</em>'.
	 * @generated
	 */
	BinaryStringDataType createBinaryStringDataType();

	/**
	 * Returns a new object of class '<em>Character Set</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Character Set</em>'.
	 * @generated
	 */
	CharacterSet createCharacterSet();

	/**
	 * Returns a new object of class '<em>Time Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Time Data Type</em>'.
	 * @generated
	 */
	TimeDataType createTimeDataType();

	/**
	 * Returns a new object of class '<em>Distinct User Defined Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Distinct User Defined Type</em>'.
	 * @generated
	 */
	DistinctUserDefinedType createDistinctUserDefinedType();

	/**
	 * Returns a new object of class '<em>Structured User Defined Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Structured User Defined Type</em>'.
	 * @generated
	 */
	StructuredUserDefinedType createStructuredUserDefinedType();

	/**
	 * Returns a new object of class '<em>Attribute Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Attribute Definition</em>'.
	 * @generated
	 */
	AttributeDefinition createAttributeDefinition();

	/**
	 * Returns a new object of class '<em>Fixed Precision Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Fixed Precision Data Type</em>'.
	 * @generated
	 */
	FixedPrecisionDataType createFixedPrecisionDataType();

	/**
	 * Returns a new object of class '<em>Domain</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Domain</em>'.
	 * @generated
	 */
	Domain createDomain();

	/**
	 * Returns a new object of class '<em>Field</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Field</em>'.
	 * @generated
	 */
	Field createField();

	/**
	 * Returns a new object of class '<em>Data Link Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Data Link Data Type</em>'.
	 * @generated
	 */
	DataLinkDataType createDataLinkDataType();

	/**
	 * Returns a new object of class '<em>User Defined Type Ordering</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>User Defined Type Ordering</em>'.
	 * @generated
	 */
	UserDefinedTypeOrdering createUserDefinedTypeOrdering();

	/**
	 * Returns a new object of class '<em>Date Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Date Data Type</em>'.
	 * @generated
	 */
	DateDataType createDateDataType();

	/**
	 * Returns a new object of class '<em>Approximate Numeric Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Approximate Numeric Data Type</em>'.
	 * @generated
	 */
	ApproximateNumericDataType createApproximateNumericDataType();

	/**
	 * Returns a new object of class '<em>Integer Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Integer Data Type</em>'.
	 * @generated
	 */
	IntegerDataType createIntegerDataType();

	/**
	 * Returns a new object of class '<em>XML Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>XML Data Type</em>'.
	 * @generated
	 */
	XMLDataType createXMLDataType();

	/**
	 * Returns a new object of class '<em>Element Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Element Type</em>'.
	 * @generated
	 */
	ElementType createElementType();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	SQLDataTypesPackage getSQLDataTypesPackage();

} //SQLDataTypesFactory

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
package org.eclipse.datatools.modelbase.sql.datatypes.impl;

import org.eclipse.datatools.modelbase.sql.datatypes.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SQLDataTypesFactoryImpl extends EFactoryImpl implements SQLDataTypesFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SQLDataTypesFactory init() {
		try {
			SQLDataTypesFactory theSQLDataTypesFactory = (SQLDataTypesFactory)EPackage.Registry.INSTANCE.getEFactory("http:///org/eclipse/datatools/modelbase/sql/datatypes.ecore"); //$NON-NLS-1$ 
			if (theSQLDataTypesFactory != null) {
				return theSQLDataTypesFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SQLDataTypesFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SQLDataTypesFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case SQLDataTypesPackage.CHARACTER_STRING_DATA_TYPE: return createCharacterStringDataType();
			case SQLDataTypesPackage.ROW_DATA_TYPE: return createRowDataType();
			case SQLDataTypesPackage.BOOLEAN_DATA_TYPE: return createBooleanDataType();
			case SQLDataTypesPackage.INTERVAL_DATA_TYPE: return createIntervalDataType();
			case SQLDataTypesPackage.BINARY_STRING_DATA_TYPE: return createBinaryStringDataType();
			case SQLDataTypesPackage.CHARACTER_SET: return createCharacterSet();
			case SQLDataTypesPackage.TIME_DATA_TYPE: return createTimeDataType();
			case SQLDataTypesPackage.DISTINCT_USER_DEFINED_TYPE: return createDistinctUserDefinedType();
			case SQLDataTypesPackage.STRUCTURED_USER_DEFINED_TYPE: return createStructuredUserDefinedType();
			case SQLDataTypesPackage.ATTRIBUTE_DEFINITION: return createAttributeDefinition();
			case SQLDataTypesPackage.FIXED_PRECISION_DATA_TYPE: return createFixedPrecisionDataType();
			case SQLDataTypesPackage.DOMAIN: return createDomain();
			case SQLDataTypesPackage.FIELD: return createField();
			case SQLDataTypesPackage.DATA_LINK_DATA_TYPE: return createDataLinkDataType();
			case SQLDataTypesPackage.USER_DEFINED_TYPE_ORDERING: return createUserDefinedTypeOrdering();
			case SQLDataTypesPackage.DATE_DATA_TYPE: return createDateDataType();
			case SQLDataTypesPackage.APPROXIMATE_NUMERIC_DATA_TYPE: return createApproximateNumericDataType();
			case SQLDataTypesPackage.INTEGER_DATA_TYPE: return createIntegerDataType();
			case SQLDataTypesPackage.XML_DATA_TYPE: return createXMLDataType();
			case SQLDataTypesPackage.ELEMENT_TYPE: return createElementType();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case SQLDataTypesPackage.COERCIBILITY_TYPE:
				return createCoercibilityTypeFromString(eDataType, initialValue);
			case SQLDataTypesPackage.INTERVAL_QUALIFIER_TYPE:
				return createIntervalQualifierTypeFromString(eDataType, initialValue);
			case SQLDataTypesPackage.ORDERING_TYPE:
				return createOrderingTypeFromString(eDataType, initialValue);
			case SQLDataTypesPackage.ORDERING_CATEGORY_TYPE:
				return createOrderingCategoryTypeFromString(eDataType, initialValue);
			case SQLDataTypesPackage.PRIMITIVE_TYPE:
				return createPrimitiveTypeFromString(eDataType, initialValue);
			case SQLDataTypesPackage.LINK_CONTROL_OPTION:
				return createLinkControlOptionFromString(eDataType, initialValue);
			case SQLDataTypesPackage.INTEGRITY_CONTROL_OPTION:
				return createIntegrityControlOptionFromString(eDataType, initialValue);
			case SQLDataTypesPackage.READ_PERMISSION_OPTION:
				return createReadPermissionOptionFromString(eDataType, initialValue);
			case SQLDataTypesPackage.WRITE_PERMISSION_OPTION:
				return createWritePermissionOptionFromString(eDataType, initialValue);
			case SQLDataTypesPackage.UNLINK_OPTION:
				return createUnlinkOptionFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case SQLDataTypesPackage.COERCIBILITY_TYPE:
				return convertCoercibilityTypeToString(eDataType, instanceValue);
			case SQLDataTypesPackage.INTERVAL_QUALIFIER_TYPE:
				return convertIntervalQualifierTypeToString(eDataType, instanceValue);
			case SQLDataTypesPackage.ORDERING_TYPE:
				return convertOrderingTypeToString(eDataType, instanceValue);
			case SQLDataTypesPackage.ORDERING_CATEGORY_TYPE:
				return convertOrderingCategoryTypeToString(eDataType, instanceValue);
			case SQLDataTypesPackage.PRIMITIVE_TYPE:
				return convertPrimitiveTypeToString(eDataType, instanceValue);
			case SQLDataTypesPackage.LINK_CONTROL_OPTION:
				return convertLinkControlOptionToString(eDataType, instanceValue);
			case SQLDataTypesPackage.INTEGRITY_CONTROL_OPTION:
				return convertIntegrityControlOptionToString(eDataType, instanceValue);
			case SQLDataTypesPackage.READ_PERMISSION_OPTION:
				return convertReadPermissionOptionToString(eDataType, instanceValue);
			case SQLDataTypesPackage.WRITE_PERMISSION_OPTION:
				return convertWritePermissionOptionToString(eDataType, instanceValue);
			case SQLDataTypesPackage.UNLINK_OPTION:
				return convertUnlinkOptionToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CharacterStringDataType createCharacterStringDataType() {
		CharacterStringDataTypeImpl characterStringDataType = new CharacterStringDataTypeImpl();
		return characterStringDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RowDataType createRowDataType() {
		RowDataTypeImpl rowDataType = new RowDataTypeImpl();
		return rowDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanDataType createBooleanDataType() {
		BooleanDataTypeImpl booleanDataType = new BooleanDataTypeImpl();
		return booleanDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntervalDataType createIntervalDataType() {
		IntervalDataTypeImpl intervalDataType = new IntervalDataTypeImpl();
		return intervalDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BinaryStringDataType createBinaryStringDataType() {
		BinaryStringDataTypeImpl binaryStringDataType = new BinaryStringDataTypeImpl();
		return binaryStringDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CharacterSet createCharacterSet() {
		CharacterSetImpl characterSet = new CharacterSetImpl();
		return characterSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimeDataType createTimeDataType() {
		TimeDataTypeImpl timeDataType = new TimeDataTypeImpl();
		return timeDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DistinctUserDefinedType createDistinctUserDefinedType() {
		DistinctUserDefinedTypeImpl distinctUserDefinedType = new DistinctUserDefinedTypeImpl();
		return distinctUserDefinedType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructuredUserDefinedType createStructuredUserDefinedType() {
		StructuredUserDefinedTypeImpl structuredUserDefinedType = new StructuredUserDefinedTypeImpl();
		return structuredUserDefinedType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AttributeDefinition createAttributeDefinition() {
		AttributeDefinitionImpl attributeDefinition = new AttributeDefinitionImpl();
		return attributeDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FixedPrecisionDataType createFixedPrecisionDataType() {
		FixedPrecisionDataTypeImpl fixedPrecisionDataType = new FixedPrecisionDataTypeImpl();
		return fixedPrecisionDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Domain createDomain() {
		DomainImpl domain = new DomainImpl();
		return domain;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Field createField() {
		FieldImpl field = new FieldImpl();
		return field;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataLinkDataType createDataLinkDataType() {
		DataLinkDataTypeImpl dataLinkDataType = new DataLinkDataTypeImpl();
		return dataLinkDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserDefinedTypeOrdering createUserDefinedTypeOrdering() {
		UserDefinedTypeOrderingImpl userDefinedTypeOrdering = new UserDefinedTypeOrderingImpl();
		return userDefinedTypeOrdering;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DateDataType createDateDataType() {
		DateDataTypeImpl dateDataType = new DateDataTypeImpl();
		return dateDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ApproximateNumericDataType createApproximateNumericDataType() {
		ApproximateNumericDataTypeImpl approximateNumericDataType = new ApproximateNumericDataTypeImpl();
		return approximateNumericDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerDataType createIntegerDataType() {
		IntegerDataTypeImpl integerDataType = new IntegerDataTypeImpl();
		return integerDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XMLDataType createXMLDataType() {
		XMLDataTypeImpl xmlDataType = new XMLDataTypeImpl();
		return xmlDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ElementType createElementType() {
		ElementTypeImpl elementType = new ElementTypeImpl();
		return elementType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CoercibilityType createCoercibilityTypeFromString(EDataType eDataType, String initialValue) {
		CoercibilityType result = CoercibilityType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCoercibilityTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntervalQualifierType createIntervalQualifierTypeFromString(EDataType eDataType, String initialValue) {
		IntervalQualifierType result = IntervalQualifierType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertIntervalQualifierTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrderingType createOrderingTypeFromString(EDataType eDataType, String initialValue) {
		OrderingType result = OrderingType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOrderingTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrderingCategoryType createOrderingCategoryTypeFromString(EDataType eDataType, String initialValue) {
		OrderingCategoryType result = OrderingCategoryType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOrderingCategoryTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveType createPrimitiveTypeFromString(EDataType eDataType, String initialValue) {
		PrimitiveType result = PrimitiveType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPrimitiveTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LinkControlOption createLinkControlOptionFromString(EDataType eDataType, String initialValue) {
		LinkControlOption result = LinkControlOption.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLinkControlOptionToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegrityControlOption createIntegrityControlOptionFromString(EDataType eDataType, String initialValue) {
		IntegrityControlOption result = IntegrityControlOption.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertIntegrityControlOptionToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReadPermissionOption createReadPermissionOptionFromString(EDataType eDataType, String initialValue) {
		ReadPermissionOption result = ReadPermissionOption.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertReadPermissionOptionToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WritePermissionOption createWritePermissionOptionFromString(EDataType eDataType, String initialValue) {
		WritePermissionOption result = WritePermissionOption.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertWritePermissionOptionToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnlinkOption createUnlinkOptionFromString(EDataType eDataType, String initialValue) {
		UnlinkOption result = UnlinkOption.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertUnlinkOptionToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SQLDataTypesPackage getSQLDataTypesPackage() {
		return (SQLDataTypesPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static SQLDataTypesPackage getPackage() {
		return SQLDataTypesPackage.eINSTANCE;
	}

} //SQLDataTypesFactoryImpl

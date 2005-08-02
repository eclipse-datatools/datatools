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
import org.eclipse.emf.ecore.impl.EFactoryImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SQLDataTypesFactoryImpl extends EFactoryImpl implements SQLDataTypesFactory {
	/**
	 * Creates and instance of the factory.
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
			case SQLDataTypesPackage.COERCIBILITY_TYPE: {
				CoercibilityType result = CoercibilityType.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return result;
			}
			case SQLDataTypesPackage.INTERVAL_QUALIFIER_TYPE: {
				IntervalQualifierType result = IntervalQualifierType.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return result;
			}
			case SQLDataTypesPackage.ORDERING_TYPE: {
				OrderingType result = OrderingType.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return result;
			}
			case SQLDataTypesPackage.ORDERING_CATEGORY_TYPE: {
				OrderingCategoryType result = OrderingCategoryType.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return result;
			}
			case SQLDataTypesPackage.PRIMITIVE_TYPE: {
				PrimitiveType result = PrimitiveType.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return result;
			}
			case SQLDataTypesPackage.LINK_CONTROL_OPTION: {
				LinkControlOption result = LinkControlOption.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return result;
			}
			case SQLDataTypesPackage.INTEGRITY_CONTROL_OPTION: {
				IntegrityControlOption result = IntegrityControlOption.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return result;
			}
			case SQLDataTypesPackage.READ_PERMISSION_OPTION: {
				ReadPermissionOption result = ReadPermissionOption.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return result;
			}
			case SQLDataTypesPackage.WRITE_PERMISSION_OPTION: {
				WritePermissionOption result = WritePermissionOption.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return result;
			}
			case SQLDataTypesPackage.UNLINK_OPTION: {
				UnlinkOption result = UnlinkOption.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return result;
			}
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
				return instanceValue == null ? null : instanceValue.toString();
			case SQLDataTypesPackage.INTERVAL_QUALIFIER_TYPE:
				return instanceValue == null ? null : instanceValue.toString();
			case SQLDataTypesPackage.ORDERING_TYPE:
				return instanceValue == null ? null : instanceValue.toString();
			case SQLDataTypesPackage.ORDERING_CATEGORY_TYPE:
				return instanceValue == null ? null : instanceValue.toString();
			case SQLDataTypesPackage.PRIMITIVE_TYPE:
				return instanceValue == null ? null : instanceValue.toString();
			case SQLDataTypesPackage.LINK_CONTROL_OPTION:
				return instanceValue == null ? null : instanceValue.toString();
			case SQLDataTypesPackage.INTEGRITY_CONTROL_OPTION:
				return instanceValue == null ? null : instanceValue.toString();
			case SQLDataTypesPackage.READ_PERMISSION_OPTION:
				return instanceValue == null ? null : instanceValue.toString();
			case SQLDataTypesPackage.WRITE_PERMISSION_OPTION:
				return instanceValue == null ? null : instanceValue.toString();
			case SQLDataTypesPackage.UNLINK_OPTION:
				return instanceValue == null ? null : instanceValue.toString();
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

/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.modelbase.dbdefinition.impl;

import java.util.Collection;

import org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage;
import org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.LanguageType;
import org.eclipse.datatools.modelbase.dbdefinition.DefaultValueType;
import org.eclipse.datatools.modelbase.dbdefinition.LengthUnit;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Predefined Data Type Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#getLeadingFieldQualifierDefinition <em>Leading Field Qualifier Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#getTrailingFieldQualifierDefinition <em>Trailing Field Qualifier Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#getDefaultTrailingFieldQualifierDefinition <em>Default Trailing Field Qualifier Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#getDefaultLeadingFieldQualifierDefinition <em>Default Leading Field Qualifier Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#isLengthSupported <em>Length Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#isScaleSupported <em>Scale Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#isPrecisionSupported <em>Precision Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#isKeyConstraintSupported <em>Key Constraint Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#isIdentitySupported <em>Identity Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#isMultipleColumnsSupported <em>Multiple Columns Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#isNullableSupported <em>Nullable Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#isDefaultSupported <em>Default Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#isClusteringSupported <em>Clustering Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#isFillFactorSupported <em>Fill Factor Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#isBitDataSupported <em>Bit Data Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#getMaximumValue <em>Maximum Value</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#getMinimumValue <em>Minimum Value</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#getMaximumLength <em>Maximum Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#getMaximumPrecision <em>Maximum Precision</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#getMaximumScale <em>Maximum Scale</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#getMinimumScale <em>Minimum Scale</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#getDefaultValueTypes <em>Default Value Types</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#getPrimitiveType <em>Primitive Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#getJdbcEnumType <em>Jdbc Enum Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#getCharacterSet <em>Character Set</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#getEncodingScheme <em>Encoding Scheme</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#getCharacterSetSuffix <em>Character Set Suffix</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#getEncodingSchemeSuffix <em>Encoding Scheme Suffix</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#getJavaClassName <em>Java Class Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#getDefaultLength <em>Default Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#getDefaultPrecision <em>Default Precision</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#getDefaultScale <em>Default Scale</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#getCutoffPrecision <em>Cutoff Precision</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#getLengthUnit <em>Length Unit</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#isOrderingSupported <em>Ordering Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#isGroupingSupported <em>Grouping Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#isDisplayNameSupported <em>Display Name Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#isLeadingFieldQualifierSupported <em>Leading Field Qualifier Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#isTrailingFieldQualifierSupported <em>Trailing Field Qualifier Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#getFieldQualifierSeparator <em>Field Qualifier Separator</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#isLargeValueSpecifierSupported <em>Large Value Specifier Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#getLargeValueSpecifierName <em>Large Value Specifier Name</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#getLargeValueSpecifierLength <em>Large Value Specifier Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#isLengthSemanticSupported <em>Length Semantic Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#getLengthSemantic <em>Length Semantic</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.PredefinedDataTypeDefinitionImpl#getLanguageType <em>Language Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PredefinedDataTypeDefinitionImpl extends EObjectImpl implements PredefinedDataTypeDefinition {
	/**
	 * The cached value of the '{@link #getLeadingFieldQualifierDefinition() <em>Leading Field Qualifier Definition</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeadingFieldQualifierDefinition()
	 * @generated
	 * @ordered
	 */
	protected EList leadingFieldQualifierDefinition;

	/**
	 * The cached value of the '{@link #getTrailingFieldQualifierDefinition() <em>Trailing Field Qualifier Definition</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrailingFieldQualifierDefinition()
	 * @generated
	 * @ordered
	 */
	protected EList trailingFieldQualifierDefinition;

	/**
	 * The cached value of the '{@link #getDefaultTrailingFieldQualifierDefinition() <em>Default Trailing Field Qualifier Definition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultTrailingFieldQualifierDefinition()
	 * @generated
	 * @ordered
	 */
	protected FieldQualifierDefinition defaultTrailingFieldQualifierDefinition;

	/**
	 * The cached value of the '{@link #getDefaultLeadingFieldQualifierDefinition() <em>Default Leading Field Qualifier Definition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultLeadingFieldQualifierDefinition()
	 * @generated
	 * @ordered
	 */
	protected FieldQualifierDefinition defaultLeadingFieldQualifierDefinition;

	/**
	 * The default value of the '{@link #isLengthSupported() <em>Length Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLengthSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LENGTH_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isLengthSupported() <em>Length Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLengthSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean lengthSupported = LENGTH_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isScaleSupported() <em>Scale Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isScaleSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SCALE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isScaleSupported() <em>Scale Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isScaleSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean scaleSupported = SCALE_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isPrecisionSupported() <em>Precision Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPrecisionSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PRECISION_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isPrecisionSupported() <em>Precision Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPrecisionSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean precisionSupported = PRECISION_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isKeyConstraintSupported() <em>Key Constraint Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isKeyConstraintSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean KEY_CONSTRAINT_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isKeyConstraintSupported() <em>Key Constraint Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isKeyConstraintSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean keyConstraintSupported = KEY_CONSTRAINT_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isIdentitySupported() <em>Identity Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIdentitySupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IDENTITY_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIdentitySupported() <em>Identity Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIdentitySupported()
	 * @generated
	 * @ordered
	 */
	protected boolean identitySupported = IDENTITY_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isMultipleColumnsSupported() <em>Multiple Columns Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMultipleColumnsSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MULTIPLE_COLUMNS_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMultipleColumnsSupported() <em>Multiple Columns Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMultipleColumnsSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean multipleColumnsSupported = MULTIPLE_COLUMNS_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isNullableSupported() <em>Nullable Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNullableSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NULLABLE_SUPPORTED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isNullableSupported() <em>Nullable Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNullableSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean nullableSupported = NULLABLE_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isDefaultSupported() <em>Default Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDefaultSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DEFAULT_SUPPORTED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isDefaultSupported() <em>Default Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDefaultSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean defaultSupported = DEFAULT_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isClusteringSupported() <em>Clustering Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isClusteringSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CLUSTERING_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isClusteringSupported() <em>Clustering Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isClusteringSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean clusteringSupported = CLUSTERING_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isFillFactorSupported() <em>Fill Factor Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFillFactorSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FILL_FACTOR_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFillFactorSupported() <em>Fill Factor Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFillFactorSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean fillFactorSupported = FILL_FACTOR_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isBitDataSupported() <em>Bit Data Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBitDataSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean BIT_DATA_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isBitDataSupported() <em>Bit Data Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBitDataSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean bitDataSupported = BIT_DATA_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaximumValue() <em>Maximum Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumValue()
	 * @generated
	 * @ordered
	 */
	protected static final long MAXIMUM_VALUE_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getMaximumValue() <em>Maximum Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumValue()
	 * @generated
	 * @ordered
	 */
	protected long maximumValue = MAXIMUM_VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMinimumValue() <em>Minimum Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinimumValue()
	 * @generated
	 * @ordered
	 */
	protected static final long MINIMUM_VALUE_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getMinimumValue() <em>Minimum Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinimumValue()
	 * @generated
	 * @ordered
	 */
	protected long minimumValue = MINIMUM_VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaximumLength() <em>Maximum Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumLength()
	 * @generated
	 * @ordered
	 */
	protected static final int MAXIMUM_LENGTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaximumLength() <em>Maximum Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumLength()
	 * @generated
	 * @ordered
	 */
	protected int maximumLength = MAXIMUM_LENGTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaximumPrecision() <em>Maximum Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumPrecision()
	 * @generated
	 * @ordered
	 */
	protected static final int MAXIMUM_PRECISION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaximumPrecision() <em>Maximum Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumPrecision()
	 * @generated
	 * @ordered
	 */
	protected int maximumPrecision = MAXIMUM_PRECISION_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaximumScale() <em>Maximum Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumScale()
	 * @generated
	 * @ordered
	 */
	protected static final int MAXIMUM_SCALE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaximumScale() <em>Maximum Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumScale()
	 * @generated
	 * @ordered
	 */
	protected int maximumScale = MAXIMUM_SCALE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMinimumScale() <em>Minimum Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinimumScale()
	 * @generated
	 * @ordered
	 */
	protected static final int MINIMUM_SCALE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMinimumScale() <em>Minimum Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMinimumScale()
	 * @generated
	 * @ordered
	 */
	protected int minimumScale = MINIMUM_SCALE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDefaultValueTypes() <em>Default Value Types</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultValueTypes()
	 * @generated
	 * @ordered
	 */
	protected EList defaultValueTypes;

	/**
	 * The default value of the '{@link #getPrimitiveType() <em>Primitive Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrimitiveType()
	 * @generated
	 * @ordered
	 */
	protected static final PrimitiveType PRIMITIVE_TYPE_EDEFAULT = PrimitiveType.CHARACTER_LITERAL;

	/**
	 * The cached value of the '{@link #getPrimitiveType() <em>Primitive Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrimitiveType()
	 * @generated
	 * @ordered
	 */
	protected PrimitiveType primitiveType = PRIMITIVE_TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected EList name;

	/**
	 * The default value of the '{@link #getJdbcEnumType() <em>Jdbc Enum Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJdbcEnumType()
	 * @generated
	 * @ordered
	 */
	protected static final int JDBC_ENUM_TYPE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getJdbcEnumType() <em>Jdbc Enum Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJdbcEnumType()
	 * @generated
	 * @ordered
	 */
	protected int jdbcEnumType = JDBC_ENUM_TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCharacterSet() <em>Character Set</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCharacterSet()
	 * @generated
	 * @ordered
	 */
	protected EList characterSet;

	/**
	 * The cached value of the '{@link #getEncodingScheme() <em>Encoding Scheme</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEncodingScheme()
	 * @generated
	 * @ordered
	 */
	protected EList encodingScheme;

	/**
	 * The default value of the '{@link #getCharacterSetSuffix() <em>Character Set Suffix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCharacterSetSuffix()
	 * @generated
	 * @ordered
	 */
	protected static final String CHARACTER_SET_SUFFIX_EDEFAULT = ""; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getCharacterSetSuffix() <em>Character Set Suffix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCharacterSetSuffix()
	 * @generated
	 * @ordered
	 */
	protected String characterSetSuffix = CHARACTER_SET_SUFFIX_EDEFAULT;

	/**
	 * The default value of the '{@link #getEncodingSchemeSuffix() <em>Encoding Scheme Suffix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEncodingSchemeSuffix()
	 * @generated
	 * @ordered
	 */
	protected static final String ENCODING_SCHEME_SUFFIX_EDEFAULT = ""; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getEncodingSchemeSuffix() <em>Encoding Scheme Suffix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEncodingSchemeSuffix()
	 * @generated
	 * @ordered
	 */
	protected String encodingSchemeSuffix = ENCODING_SCHEME_SUFFIX_EDEFAULT;

	/**
	 * The default value of the '{@link #getJavaClassName() <em>Java Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJavaClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String JAVA_CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getJavaClassName() <em>Java Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJavaClassName()
	 * @generated
	 * @ordered
	 */
	protected String javaClassName = JAVA_CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDefaultLength() <em>Default Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultLength()
	 * @generated
	 * @ordered
	 */
	protected static final int DEFAULT_LENGTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getDefaultLength() <em>Default Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultLength()
	 * @generated
	 * @ordered
	 */
	protected int defaultLength = DEFAULT_LENGTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getDefaultPrecision() <em>Default Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultPrecision()
	 * @generated
	 * @ordered
	 */
	protected static final int DEFAULT_PRECISION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getDefaultPrecision() <em>Default Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultPrecision()
	 * @generated
	 * @ordered
	 */
	protected int defaultPrecision = DEFAULT_PRECISION_EDEFAULT;

	/**
	 * The default value of the '{@link #getDefaultScale() <em>Default Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultScale()
	 * @generated
	 * @ordered
	 */
	protected static final int DEFAULT_SCALE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getDefaultScale() <em>Default Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultScale()
	 * @generated
	 * @ordered
	 */
	protected int defaultScale = DEFAULT_SCALE_EDEFAULT;

	/**
	 * The default value of the '{@link #getCutoffPrecision() <em>Cutoff Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCutoffPrecision()
	 * @generated
	 * @ordered
	 */
	protected static final int CUTOFF_PRECISION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCutoffPrecision() <em>Cutoff Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCutoffPrecision()
	 * @generated
	 * @ordered
	 */
	protected int cutoffPrecision = CUTOFF_PRECISION_EDEFAULT;

	/**
	 * The default value of the '{@link #getLengthUnit() <em>Length Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLengthUnit()
	 * @generated
	 * @ordered
	 */
	protected static final LengthUnit LENGTH_UNIT_EDEFAULT = LengthUnit.DECIMAL_LITERAL;

	/**
	 * The cached value of the '{@link #getLengthUnit() <em>Length Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLengthUnit()
	 * @generated
	 * @ordered
	 */
	protected LengthUnit lengthUnit = LENGTH_UNIT_EDEFAULT;

	/**
	 * The default value of the '{@link #isOrderingSupported() <em>Ordering Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOrderingSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ORDERING_SUPPORTED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isOrderingSupported() <em>Ordering Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOrderingSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean orderingSupported = ORDERING_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isGroupingSupported() <em>Grouping Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isGroupingSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean GROUPING_SUPPORTED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isGroupingSupported() <em>Grouping Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isGroupingSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean groupingSupported = GROUPING_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #getDisplayName() <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayName()
	 * @generated
	 * @ordered
	 */
	protected static final String DISPLAY_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDisplayName() <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayName()
	 * @generated
	 * @ordered
	 */
	protected String displayName = DISPLAY_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #isDisplayNameSupported() <em>Display Name Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDisplayNameSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DISPLAY_NAME_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDisplayNameSupported() <em>Display Name Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDisplayNameSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean displayNameSupported = DISPLAY_NAME_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isLeadingFieldQualifierSupported() <em>Leading Field Qualifier Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLeadingFieldQualifierSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LEADING_FIELD_QUALIFIER_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isLeadingFieldQualifierSupported() <em>Leading Field Qualifier Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLeadingFieldQualifierSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean leadingFieldQualifierSupported = LEADING_FIELD_QUALIFIER_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isTrailingFieldQualifierSupported() <em>Trailing Field Qualifier Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTrailingFieldQualifierSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TRAILING_FIELD_QUALIFIER_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isTrailingFieldQualifierSupported() <em>Trailing Field Qualifier Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTrailingFieldQualifierSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean trailingFieldQualifierSupported = TRAILING_FIELD_QUALIFIER_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #getFieldQualifierSeparator() <em>Field Qualifier Separator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFieldQualifierSeparator()
	 * @generated
	 * @ordered
	 */
	protected static final String FIELD_QUALIFIER_SEPARATOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFieldQualifierSeparator() <em>Field Qualifier Separator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFieldQualifierSeparator()
	 * @generated
	 * @ordered
	 */
	protected String fieldQualifierSeparator = FIELD_QUALIFIER_SEPARATOR_EDEFAULT;

	/**
	 * The default value of the '{@link #isLargeValueSpecifierSupported() <em>Large Value Specifier Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLargeValueSpecifierSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LARGE_VALUE_SPECIFIER_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isLargeValueSpecifierSupported() <em>Large Value Specifier Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLargeValueSpecifierSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean largeValueSpecifierSupported = LARGE_VALUE_SPECIFIER_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #getLargeValueSpecifierName() <em>Large Value Specifier Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLargeValueSpecifierName()
	 * @generated
	 * @ordered
	 */
	protected static final String LARGE_VALUE_SPECIFIER_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLargeValueSpecifierName() <em>Large Value Specifier Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLargeValueSpecifierName()
	 * @generated
	 * @ordered
	 */
	protected String largeValueSpecifierName = LARGE_VALUE_SPECIFIER_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getLargeValueSpecifierLength() <em>Large Value Specifier Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLargeValueSpecifierLength()
	 * @generated
	 * @ordered
	 */
	protected static final int LARGE_VALUE_SPECIFIER_LENGTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLargeValueSpecifierLength() <em>Large Value Specifier Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLargeValueSpecifierLength()
	 * @generated
	 * @ordered
	 */
	protected int largeValueSpecifierLength = LARGE_VALUE_SPECIFIER_LENGTH_EDEFAULT;

	/**
	 * The default value of the '{@link #isLengthSemanticSupported() <em>Length Semantic Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLengthSemanticSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LENGTH_SEMANTIC_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isLengthSemanticSupported() <em>Length Semantic Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLengthSemanticSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean lengthSemanticSupported = LENGTH_SEMANTIC_SUPPORTED_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLengthSemantic() <em>Length Semantic</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLengthSemantic()
	 * @generated
	 * @ordered
	 */
	protected EList lengthSemantic;

	/**
	 * The cached value of the '{@link #getLanguageType() <em>Language Type</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLanguageType()
	 * @generated
	 * @ordered
	 */
	protected EList languageType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PredefinedDataTypeDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DatabaseDefinitionPackage.Literals.PREDEFINED_DATA_TYPE_DEFINITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getLeadingFieldQualifierDefinition() {
		if (leadingFieldQualifierDefinition == null) {
			leadingFieldQualifierDefinition = new EObjectContainmentEList(FieldQualifierDefinition.class, this, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LEADING_FIELD_QUALIFIER_DEFINITION);
		}
		return leadingFieldQualifierDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getTrailingFieldQualifierDefinition() {
		if (trailingFieldQualifierDefinition == null) {
			trailingFieldQualifierDefinition = new EObjectContainmentEList(FieldQualifierDefinition.class, this, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__TRAILING_FIELD_QUALIFIER_DEFINITION);
		}
		return trailingFieldQualifierDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FieldQualifierDefinition getDefaultTrailingFieldQualifierDefinition() {
		if (defaultTrailingFieldQualifierDefinition != null && defaultTrailingFieldQualifierDefinition.eIsProxy()) {
			InternalEObject oldDefaultTrailingFieldQualifierDefinition = (InternalEObject)defaultTrailingFieldQualifierDefinition;
			defaultTrailingFieldQualifierDefinition = (FieldQualifierDefinition)eResolveProxy(oldDefaultTrailingFieldQualifierDefinition);
			if (defaultTrailingFieldQualifierDefinition != oldDefaultTrailingFieldQualifierDefinition) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_TRAILING_FIELD_QUALIFIER_DEFINITION, oldDefaultTrailingFieldQualifierDefinition, defaultTrailingFieldQualifierDefinition));
			}
		}
		return defaultTrailingFieldQualifierDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FieldQualifierDefinition basicGetDefaultTrailingFieldQualifierDefinition() {
		return defaultTrailingFieldQualifierDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultTrailingFieldQualifierDefinition(FieldQualifierDefinition newDefaultTrailingFieldQualifierDefinition) {
		FieldQualifierDefinition oldDefaultTrailingFieldQualifierDefinition = defaultTrailingFieldQualifierDefinition;
		defaultTrailingFieldQualifierDefinition = newDefaultTrailingFieldQualifierDefinition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_TRAILING_FIELD_QUALIFIER_DEFINITION, oldDefaultTrailingFieldQualifierDefinition, defaultTrailingFieldQualifierDefinition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FieldQualifierDefinition getDefaultLeadingFieldQualifierDefinition() {
		if (defaultLeadingFieldQualifierDefinition != null && defaultLeadingFieldQualifierDefinition.eIsProxy()) {
			InternalEObject oldDefaultLeadingFieldQualifierDefinition = (InternalEObject)defaultLeadingFieldQualifierDefinition;
			defaultLeadingFieldQualifierDefinition = (FieldQualifierDefinition)eResolveProxy(oldDefaultLeadingFieldQualifierDefinition);
			if (defaultLeadingFieldQualifierDefinition != oldDefaultLeadingFieldQualifierDefinition) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_LEADING_FIELD_QUALIFIER_DEFINITION, oldDefaultLeadingFieldQualifierDefinition, defaultLeadingFieldQualifierDefinition));
			}
		}
		return defaultLeadingFieldQualifierDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FieldQualifierDefinition basicGetDefaultLeadingFieldQualifierDefinition() {
		return defaultLeadingFieldQualifierDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultLeadingFieldQualifierDefinition(FieldQualifierDefinition newDefaultLeadingFieldQualifierDefinition) {
		FieldQualifierDefinition oldDefaultLeadingFieldQualifierDefinition = defaultLeadingFieldQualifierDefinition;
		defaultLeadingFieldQualifierDefinition = newDefaultLeadingFieldQualifierDefinition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_LEADING_FIELD_QUALIFIER_DEFINITION, oldDefaultLeadingFieldQualifierDefinition, defaultLeadingFieldQualifierDefinition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isLengthSupported() {
		return lengthSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLengthSupported(boolean newLengthSupported) {
		boolean oldLengthSupported = lengthSupported;
		lengthSupported = newLengthSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LENGTH_SUPPORTED, oldLengthSupported, lengthSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isScaleSupported() {
		return scaleSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScaleSupported(boolean newScaleSupported) {
		boolean oldScaleSupported = scaleSupported;
		scaleSupported = newScaleSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__SCALE_SUPPORTED, oldScaleSupported, scaleSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isPrecisionSupported() {
		return precisionSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrecisionSupported(boolean newPrecisionSupported) {
		boolean oldPrecisionSupported = precisionSupported;
		precisionSupported = newPrecisionSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__PRECISION_SUPPORTED, oldPrecisionSupported, precisionSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isKeyConstraintSupported() {
		return keyConstraintSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKeyConstraintSupported(boolean newKeyConstraintSupported) {
		boolean oldKeyConstraintSupported = keyConstraintSupported;
		keyConstraintSupported = newKeyConstraintSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__KEY_CONSTRAINT_SUPPORTED, oldKeyConstraintSupported, keyConstraintSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIdentitySupported() {
		return identitySupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdentitySupported(boolean newIdentitySupported) {
		boolean oldIdentitySupported = identitySupported;
		identitySupported = newIdentitySupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__IDENTITY_SUPPORTED, oldIdentitySupported, identitySupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMultipleColumnsSupported() {
		return multipleColumnsSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMultipleColumnsSupported(boolean newMultipleColumnsSupported) {
		boolean oldMultipleColumnsSupported = multipleColumnsSupported;
		multipleColumnsSupported = newMultipleColumnsSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MULTIPLE_COLUMNS_SUPPORTED, oldMultipleColumnsSupported, multipleColumnsSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isNullableSupported() {
		return nullableSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNullableSupported(boolean newNullableSupported) {
		boolean oldNullableSupported = nullableSupported;
		nullableSupported = newNullableSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__NULLABLE_SUPPORTED, oldNullableSupported, nullableSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDefaultSupported() {
		return defaultSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultSupported(boolean newDefaultSupported) {
		boolean oldDefaultSupported = defaultSupported;
		defaultSupported = newDefaultSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_SUPPORTED, oldDefaultSupported, defaultSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isClusteringSupported() {
		return clusteringSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClusteringSupported(boolean newClusteringSupported) {
		boolean oldClusteringSupported = clusteringSupported;
		clusteringSupported = newClusteringSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__CLUSTERING_SUPPORTED, oldClusteringSupported, clusteringSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFillFactorSupported() {
		return fillFactorSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFillFactorSupported(boolean newFillFactorSupported) {
		boolean oldFillFactorSupported = fillFactorSupported;
		fillFactorSupported = newFillFactorSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__FILL_FACTOR_SUPPORTED, oldFillFactorSupported, fillFactorSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isBitDataSupported() {
		return bitDataSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBitDataSupported(boolean newBitDataSupported) {
		boolean oldBitDataSupported = bitDataSupported;
		bitDataSupported = newBitDataSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__BIT_DATA_SUPPORTED, oldBitDataSupported, bitDataSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getMaximumValue() {
		return maximumValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaximumValue(long newMaximumValue) {
		long oldMaximumValue = maximumValue;
		maximumValue = newMaximumValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MAXIMUM_VALUE, oldMaximumValue, maximumValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getMinimumValue() {
		return minimumValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinimumValue(long newMinimumValue) {
		long oldMinimumValue = minimumValue;
		minimumValue = newMinimumValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MINIMUM_VALUE, oldMinimumValue, minimumValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaximumLength() {
		return maximumLength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaximumLength(int newMaximumLength) {
		int oldMaximumLength = maximumLength;
		maximumLength = newMaximumLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MAXIMUM_LENGTH, oldMaximumLength, maximumLength));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaximumPrecision() {
		return maximumPrecision;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaximumPrecision(int newMaximumPrecision) {
		int oldMaximumPrecision = maximumPrecision;
		maximumPrecision = newMaximumPrecision;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MAXIMUM_PRECISION, oldMaximumPrecision, maximumPrecision));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaximumScale() {
		return maximumScale;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaximumScale(int newMaximumScale) {
		int oldMaximumScale = maximumScale;
		maximumScale = newMaximumScale;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MAXIMUM_SCALE, oldMaximumScale, maximumScale));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMinimumScale() {
		return minimumScale;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinimumScale(int newMinimumScale) {
		int oldMinimumScale = minimumScale;
		minimumScale = newMinimumScale;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MINIMUM_SCALE, oldMinimumScale, minimumScale));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDefaultValueTypes() {
		if (defaultValueTypes == null) {
			defaultValueTypes = new EDataTypeUniqueEList(String.class, this, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_VALUE_TYPES);
		}
		return defaultValueTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveType getPrimitiveType() {
		return primitiveType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrimitiveType(PrimitiveType newPrimitiveType) {
		PrimitiveType oldPrimitiveType = primitiveType;
		primitiveType = newPrimitiveType == null ? PRIMITIVE_TYPE_EDEFAULT : newPrimitiveType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__PRIMITIVE_TYPE, oldPrimitiveType, primitiveType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getName() {
		if (name == null) {
			name = new EDataTypeUniqueEList(String.class, this, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__NAME);
		}
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getJdbcEnumType() {
		return jdbcEnumType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJdbcEnumType(int newJdbcEnumType) {
		int oldJdbcEnumType = jdbcEnumType;
		jdbcEnumType = newJdbcEnumType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__JDBC_ENUM_TYPE, oldJdbcEnumType, jdbcEnumType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getCharacterSet() {
		if (characterSet == null) {
			characterSet = new EDataTypeUniqueEList(String.class, this, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__CHARACTER_SET);
		}
		return characterSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getEncodingScheme() {
		if (encodingScheme == null) {
			encodingScheme = new EDataTypeUniqueEList(String.class, this, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__ENCODING_SCHEME);
		}
		return encodingScheme;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCharacterSetSuffix() {
		return characterSetSuffix;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCharacterSetSuffix(String newCharacterSetSuffix) {
		String oldCharacterSetSuffix = characterSetSuffix;
		characterSetSuffix = newCharacterSetSuffix;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__CHARACTER_SET_SUFFIX, oldCharacterSetSuffix, characterSetSuffix));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEncodingSchemeSuffix() {
		return encodingSchemeSuffix;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEncodingSchemeSuffix(String newEncodingSchemeSuffix) {
		String oldEncodingSchemeSuffix = encodingSchemeSuffix;
		encodingSchemeSuffix = newEncodingSchemeSuffix;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__ENCODING_SCHEME_SUFFIX, oldEncodingSchemeSuffix, encodingSchemeSuffix));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getJavaClassName() {
		return javaClassName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJavaClassName(String newJavaClassName) {
		String oldJavaClassName = javaClassName;
		javaClassName = newJavaClassName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__JAVA_CLASS_NAME, oldJavaClassName, javaClassName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getDefaultLength() {
		return defaultLength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultLength(int newDefaultLength) {
		int oldDefaultLength = defaultLength;
		defaultLength = newDefaultLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_LENGTH, oldDefaultLength, defaultLength));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getDefaultPrecision() {
		return defaultPrecision;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultPrecision(int newDefaultPrecision) {
		int oldDefaultPrecision = defaultPrecision;
		defaultPrecision = newDefaultPrecision;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_PRECISION, oldDefaultPrecision, defaultPrecision));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getDefaultScale() {
		return defaultScale;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultScale(int newDefaultScale) {
		int oldDefaultScale = defaultScale;
		defaultScale = newDefaultScale;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_SCALE, oldDefaultScale, defaultScale));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCutoffPrecision() {
		return cutoffPrecision;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCutoffPrecision(int newCutoffPrecision) {
		int oldCutoffPrecision = cutoffPrecision;
		cutoffPrecision = newCutoffPrecision;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__CUTOFF_PRECISION, oldCutoffPrecision, cutoffPrecision));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LengthUnit getLengthUnit() {
		return lengthUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLengthUnit(LengthUnit newLengthUnit) {
		LengthUnit oldLengthUnit = lengthUnit;
		lengthUnit = newLengthUnit == null ? LENGTH_UNIT_EDEFAULT : newLengthUnit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LENGTH_UNIT, oldLengthUnit, lengthUnit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOrderingSupported() {
		return orderingSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrderingSupported(boolean newOrderingSupported) {
		boolean oldOrderingSupported = orderingSupported;
		orderingSupported = newOrderingSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__ORDERING_SUPPORTED, oldOrderingSupported, orderingSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isGroupingSupported() {
		return groupingSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroupingSupported(boolean newGroupingSupported) {
		boolean oldGroupingSupported = groupingSupported;
		groupingSupported = newGroupingSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__GROUPING_SUPPORTED, oldGroupingSupported, groupingSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDisplayName(String newDisplayName) {
		String oldDisplayName = displayName;
		displayName = newDisplayName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DISPLAY_NAME, oldDisplayName, displayName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDisplayNameSupported() {
		return displayNameSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDisplayNameSupported(boolean newDisplayNameSupported) {
		boolean oldDisplayNameSupported = displayNameSupported;
		displayNameSupported = newDisplayNameSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DISPLAY_NAME_SUPPORTED, oldDisplayNameSupported, displayNameSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isLeadingFieldQualifierSupported() {
		return leadingFieldQualifierSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLeadingFieldQualifierSupported(boolean newLeadingFieldQualifierSupported) {
		boolean oldLeadingFieldQualifierSupported = leadingFieldQualifierSupported;
		leadingFieldQualifierSupported = newLeadingFieldQualifierSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LEADING_FIELD_QUALIFIER_SUPPORTED, oldLeadingFieldQualifierSupported, leadingFieldQualifierSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isTrailingFieldQualifierSupported() {
		return trailingFieldQualifierSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTrailingFieldQualifierSupported(boolean newTrailingFieldQualifierSupported) {
		boolean oldTrailingFieldQualifierSupported = trailingFieldQualifierSupported;
		trailingFieldQualifierSupported = newTrailingFieldQualifierSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__TRAILING_FIELD_QUALIFIER_SUPPORTED, oldTrailingFieldQualifierSupported, trailingFieldQualifierSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFieldQualifierSeparator() {
		return fieldQualifierSeparator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFieldQualifierSeparator(String newFieldQualifierSeparator) {
		String oldFieldQualifierSeparator = fieldQualifierSeparator;
		fieldQualifierSeparator = newFieldQualifierSeparator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__FIELD_QUALIFIER_SEPARATOR, oldFieldQualifierSeparator, fieldQualifierSeparator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isLargeValueSpecifierSupported() {
		return largeValueSpecifierSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLargeValueSpecifierSupported(boolean newLargeValueSpecifierSupported) {
		boolean oldLargeValueSpecifierSupported = largeValueSpecifierSupported;
		largeValueSpecifierSupported = newLargeValueSpecifierSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LARGE_VALUE_SPECIFIER_SUPPORTED, oldLargeValueSpecifierSupported, largeValueSpecifierSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLargeValueSpecifierName() {
		return largeValueSpecifierName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLargeValueSpecifierName(String newLargeValueSpecifierName) {
		String oldLargeValueSpecifierName = largeValueSpecifierName;
		largeValueSpecifierName = newLargeValueSpecifierName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LARGE_VALUE_SPECIFIER_NAME, oldLargeValueSpecifierName, largeValueSpecifierName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLargeValueSpecifierLength() {
		return largeValueSpecifierLength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLargeValueSpecifierLength(int newLargeValueSpecifierLength) {
		int oldLargeValueSpecifierLength = largeValueSpecifierLength;
		largeValueSpecifierLength = newLargeValueSpecifierLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LARGE_VALUE_SPECIFIER_LENGTH, oldLargeValueSpecifierLength, largeValueSpecifierLength));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isLengthSemanticSupported() {
		return lengthSemanticSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLengthSemanticSupported(boolean newLengthSemanticSupported) {
		boolean oldLengthSemanticSupported = lengthSemanticSupported;
		lengthSemanticSupported = newLengthSemanticSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LENGTH_SEMANTIC_SUPPORTED, oldLengthSemanticSupported, lengthSemanticSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getLengthSemantic() {
		if (lengthSemantic == null) {
			lengthSemantic = new EDataTypeUniqueEList(String.class, this, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LENGTH_SEMANTIC);
		}
		return lengthSemantic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getLanguageType() {
		if (languageType == null) {
			languageType = new EDataTypeUniqueEList(LanguageType.class, this, DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LANGUAGE_TYPE);
		}
		return languageType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LEADING_FIELD_QUALIFIER_DEFINITION:
				return ((InternalEList)getLeadingFieldQualifierDefinition()).basicRemove(otherEnd, msgs);
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__TRAILING_FIELD_QUALIFIER_DEFINITION:
				return ((InternalEList)getTrailingFieldQualifierDefinition()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LEADING_FIELD_QUALIFIER_DEFINITION:
				return getLeadingFieldQualifierDefinition();
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__TRAILING_FIELD_QUALIFIER_DEFINITION:
				return getTrailingFieldQualifierDefinition();
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_TRAILING_FIELD_QUALIFIER_DEFINITION:
				if (resolve) return getDefaultTrailingFieldQualifierDefinition();
				return basicGetDefaultTrailingFieldQualifierDefinition();
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_LEADING_FIELD_QUALIFIER_DEFINITION:
				if (resolve) return getDefaultLeadingFieldQualifierDefinition();
				return basicGetDefaultLeadingFieldQualifierDefinition();
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LENGTH_SUPPORTED:
				return isLengthSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__SCALE_SUPPORTED:
				return isScaleSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__PRECISION_SUPPORTED:
				return isPrecisionSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__KEY_CONSTRAINT_SUPPORTED:
				return isKeyConstraintSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__IDENTITY_SUPPORTED:
				return isIdentitySupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MULTIPLE_COLUMNS_SUPPORTED:
				return isMultipleColumnsSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__NULLABLE_SUPPORTED:
				return isNullableSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_SUPPORTED:
				return isDefaultSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__CLUSTERING_SUPPORTED:
				return isClusteringSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__FILL_FACTOR_SUPPORTED:
				return isFillFactorSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__BIT_DATA_SUPPORTED:
				return isBitDataSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MAXIMUM_VALUE:
				return new Long(getMaximumValue());
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MINIMUM_VALUE:
				return new Long(getMinimumValue());
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MAXIMUM_LENGTH:
				return new Integer(getMaximumLength());
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MAXIMUM_PRECISION:
				return new Integer(getMaximumPrecision());
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MAXIMUM_SCALE:
				return new Integer(getMaximumScale());
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MINIMUM_SCALE:
				return new Integer(getMinimumScale());
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_VALUE_TYPES:
				return getDefaultValueTypes();
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__PRIMITIVE_TYPE:
				return getPrimitiveType();
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__NAME:
				return getName();
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__JDBC_ENUM_TYPE:
				return new Integer(getJdbcEnumType());
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__CHARACTER_SET:
				return getCharacterSet();
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__ENCODING_SCHEME:
				return getEncodingScheme();
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__CHARACTER_SET_SUFFIX:
				return getCharacterSetSuffix();
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__ENCODING_SCHEME_SUFFIX:
				return getEncodingSchemeSuffix();
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__JAVA_CLASS_NAME:
				return getJavaClassName();
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_LENGTH:
				return new Integer(getDefaultLength());
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_PRECISION:
				return new Integer(getDefaultPrecision());
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_SCALE:
				return new Integer(getDefaultScale());
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__CUTOFF_PRECISION:
				return new Integer(getCutoffPrecision());
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LENGTH_UNIT:
				return getLengthUnit();
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__ORDERING_SUPPORTED:
				return isOrderingSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__GROUPING_SUPPORTED:
				return isGroupingSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DISPLAY_NAME:
				return getDisplayName();
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DISPLAY_NAME_SUPPORTED:
				return isDisplayNameSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LEADING_FIELD_QUALIFIER_SUPPORTED:
				return isLeadingFieldQualifierSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__TRAILING_FIELD_QUALIFIER_SUPPORTED:
				return isTrailingFieldQualifierSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__FIELD_QUALIFIER_SEPARATOR:
				return getFieldQualifierSeparator();
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LARGE_VALUE_SPECIFIER_SUPPORTED:
				return isLargeValueSpecifierSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LARGE_VALUE_SPECIFIER_NAME:
				return getLargeValueSpecifierName();
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LARGE_VALUE_SPECIFIER_LENGTH:
				return new Integer(getLargeValueSpecifierLength());
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LENGTH_SEMANTIC_SUPPORTED:
				return isLengthSemanticSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LENGTH_SEMANTIC:
				return getLengthSemantic();
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LANGUAGE_TYPE:
				return getLanguageType();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LEADING_FIELD_QUALIFIER_DEFINITION:
				getLeadingFieldQualifierDefinition().clear();
				getLeadingFieldQualifierDefinition().addAll((Collection)newValue);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__TRAILING_FIELD_QUALIFIER_DEFINITION:
				getTrailingFieldQualifierDefinition().clear();
				getTrailingFieldQualifierDefinition().addAll((Collection)newValue);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_TRAILING_FIELD_QUALIFIER_DEFINITION:
				setDefaultTrailingFieldQualifierDefinition((FieldQualifierDefinition)newValue);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_LEADING_FIELD_QUALIFIER_DEFINITION:
				setDefaultLeadingFieldQualifierDefinition((FieldQualifierDefinition)newValue);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LENGTH_SUPPORTED:
				setLengthSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__SCALE_SUPPORTED:
				setScaleSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__PRECISION_SUPPORTED:
				setPrecisionSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__KEY_CONSTRAINT_SUPPORTED:
				setKeyConstraintSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__IDENTITY_SUPPORTED:
				setIdentitySupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MULTIPLE_COLUMNS_SUPPORTED:
				setMultipleColumnsSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__NULLABLE_SUPPORTED:
				setNullableSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_SUPPORTED:
				setDefaultSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__CLUSTERING_SUPPORTED:
				setClusteringSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__FILL_FACTOR_SUPPORTED:
				setFillFactorSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__BIT_DATA_SUPPORTED:
				setBitDataSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MAXIMUM_VALUE:
				setMaximumValue(((Long)newValue).longValue());
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MINIMUM_VALUE:
				setMinimumValue(((Long)newValue).longValue());
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MAXIMUM_LENGTH:
				setMaximumLength(((Integer)newValue).intValue());
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MAXIMUM_PRECISION:
				setMaximumPrecision(((Integer)newValue).intValue());
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MAXIMUM_SCALE:
				setMaximumScale(((Integer)newValue).intValue());
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MINIMUM_SCALE:
				setMinimumScale(((Integer)newValue).intValue());
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_VALUE_TYPES:
				getDefaultValueTypes().clear();
				getDefaultValueTypes().addAll((Collection)newValue);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__PRIMITIVE_TYPE:
				setPrimitiveType((PrimitiveType)newValue);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__NAME:
				getName().clear();
				getName().addAll((Collection)newValue);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__JDBC_ENUM_TYPE:
				setJdbcEnumType(((Integer)newValue).intValue());
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__CHARACTER_SET:
				getCharacterSet().clear();
				getCharacterSet().addAll((Collection)newValue);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__ENCODING_SCHEME:
				getEncodingScheme().clear();
				getEncodingScheme().addAll((Collection)newValue);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__CHARACTER_SET_SUFFIX:
				setCharacterSetSuffix((String)newValue);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__ENCODING_SCHEME_SUFFIX:
				setEncodingSchemeSuffix((String)newValue);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__JAVA_CLASS_NAME:
				setJavaClassName((String)newValue);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_LENGTH:
				setDefaultLength(((Integer)newValue).intValue());
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_PRECISION:
				setDefaultPrecision(((Integer)newValue).intValue());
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_SCALE:
				setDefaultScale(((Integer)newValue).intValue());
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__CUTOFF_PRECISION:
				setCutoffPrecision(((Integer)newValue).intValue());
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LENGTH_UNIT:
				setLengthUnit((LengthUnit)newValue);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__ORDERING_SUPPORTED:
				setOrderingSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__GROUPING_SUPPORTED:
				setGroupingSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DISPLAY_NAME:
				setDisplayName((String)newValue);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DISPLAY_NAME_SUPPORTED:
				setDisplayNameSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LEADING_FIELD_QUALIFIER_SUPPORTED:
				setLeadingFieldQualifierSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__TRAILING_FIELD_QUALIFIER_SUPPORTED:
				setTrailingFieldQualifierSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__FIELD_QUALIFIER_SEPARATOR:
				setFieldQualifierSeparator((String)newValue);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LARGE_VALUE_SPECIFIER_SUPPORTED:
				setLargeValueSpecifierSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LARGE_VALUE_SPECIFIER_NAME:
				setLargeValueSpecifierName((String)newValue);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LARGE_VALUE_SPECIFIER_LENGTH:
				setLargeValueSpecifierLength(((Integer)newValue).intValue());
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LENGTH_SEMANTIC_SUPPORTED:
				setLengthSemanticSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LENGTH_SEMANTIC:
				getLengthSemantic().clear();
				getLengthSemantic().addAll((Collection)newValue);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LANGUAGE_TYPE:
				getLanguageType().clear();
				getLanguageType().addAll((Collection)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LEADING_FIELD_QUALIFIER_DEFINITION:
				getLeadingFieldQualifierDefinition().clear();
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__TRAILING_FIELD_QUALIFIER_DEFINITION:
				getTrailingFieldQualifierDefinition().clear();
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_TRAILING_FIELD_QUALIFIER_DEFINITION:
				setDefaultTrailingFieldQualifierDefinition((FieldQualifierDefinition)null);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_LEADING_FIELD_QUALIFIER_DEFINITION:
				setDefaultLeadingFieldQualifierDefinition((FieldQualifierDefinition)null);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LENGTH_SUPPORTED:
				setLengthSupported(LENGTH_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__SCALE_SUPPORTED:
				setScaleSupported(SCALE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__PRECISION_SUPPORTED:
				setPrecisionSupported(PRECISION_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__KEY_CONSTRAINT_SUPPORTED:
				setKeyConstraintSupported(KEY_CONSTRAINT_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__IDENTITY_SUPPORTED:
				setIdentitySupported(IDENTITY_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MULTIPLE_COLUMNS_SUPPORTED:
				setMultipleColumnsSupported(MULTIPLE_COLUMNS_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__NULLABLE_SUPPORTED:
				setNullableSupported(NULLABLE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_SUPPORTED:
				setDefaultSupported(DEFAULT_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__CLUSTERING_SUPPORTED:
				setClusteringSupported(CLUSTERING_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__FILL_FACTOR_SUPPORTED:
				setFillFactorSupported(FILL_FACTOR_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__BIT_DATA_SUPPORTED:
				setBitDataSupported(BIT_DATA_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MAXIMUM_VALUE:
				setMaximumValue(MAXIMUM_VALUE_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MINIMUM_VALUE:
				setMinimumValue(MINIMUM_VALUE_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MAXIMUM_LENGTH:
				setMaximumLength(MAXIMUM_LENGTH_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MAXIMUM_PRECISION:
				setMaximumPrecision(MAXIMUM_PRECISION_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MAXIMUM_SCALE:
				setMaximumScale(MAXIMUM_SCALE_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MINIMUM_SCALE:
				setMinimumScale(MINIMUM_SCALE_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_VALUE_TYPES:
				getDefaultValueTypes().clear();
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__PRIMITIVE_TYPE:
				setPrimitiveType(PRIMITIVE_TYPE_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__NAME:
				getName().clear();
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__JDBC_ENUM_TYPE:
				setJdbcEnumType(JDBC_ENUM_TYPE_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__CHARACTER_SET:
				getCharacterSet().clear();
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__ENCODING_SCHEME:
				getEncodingScheme().clear();
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__CHARACTER_SET_SUFFIX:
				setCharacterSetSuffix(CHARACTER_SET_SUFFIX_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__ENCODING_SCHEME_SUFFIX:
				setEncodingSchemeSuffix(ENCODING_SCHEME_SUFFIX_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__JAVA_CLASS_NAME:
				setJavaClassName(JAVA_CLASS_NAME_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_LENGTH:
				setDefaultLength(DEFAULT_LENGTH_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_PRECISION:
				setDefaultPrecision(DEFAULT_PRECISION_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_SCALE:
				setDefaultScale(DEFAULT_SCALE_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__CUTOFF_PRECISION:
				setCutoffPrecision(CUTOFF_PRECISION_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LENGTH_UNIT:
				setLengthUnit(LENGTH_UNIT_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__ORDERING_SUPPORTED:
				setOrderingSupported(ORDERING_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__GROUPING_SUPPORTED:
				setGroupingSupported(GROUPING_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DISPLAY_NAME:
				setDisplayName(DISPLAY_NAME_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DISPLAY_NAME_SUPPORTED:
				setDisplayNameSupported(DISPLAY_NAME_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LEADING_FIELD_QUALIFIER_SUPPORTED:
				setLeadingFieldQualifierSupported(LEADING_FIELD_QUALIFIER_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__TRAILING_FIELD_QUALIFIER_SUPPORTED:
				setTrailingFieldQualifierSupported(TRAILING_FIELD_QUALIFIER_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__FIELD_QUALIFIER_SEPARATOR:
				setFieldQualifierSeparator(FIELD_QUALIFIER_SEPARATOR_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LARGE_VALUE_SPECIFIER_SUPPORTED:
				setLargeValueSpecifierSupported(LARGE_VALUE_SPECIFIER_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LARGE_VALUE_SPECIFIER_NAME:
				setLargeValueSpecifierName(LARGE_VALUE_SPECIFIER_NAME_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LARGE_VALUE_SPECIFIER_LENGTH:
				setLargeValueSpecifierLength(LARGE_VALUE_SPECIFIER_LENGTH_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LENGTH_SEMANTIC_SUPPORTED:
				setLengthSemanticSupported(LENGTH_SEMANTIC_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LENGTH_SEMANTIC:
				getLengthSemantic().clear();
				return;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LANGUAGE_TYPE:
				getLanguageType().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LEADING_FIELD_QUALIFIER_DEFINITION:
				return leadingFieldQualifierDefinition != null && !leadingFieldQualifierDefinition.isEmpty();
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__TRAILING_FIELD_QUALIFIER_DEFINITION:
				return trailingFieldQualifierDefinition != null && !trailingFieldQualifierDefinition.isEmpty();
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_TRAILING_FIELD_QUALIFIER_DEFINITION:
				return defaultTrailingFieldQualifierDefinition != null;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_LEADING_FIELD_QUALIFIER_DEFINITION:
				return defaultLeadingFieldQualifierDefinition != null;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LENGTH_SUPPORTED:
				return lengthSupported != LENGTH_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__SCALE_SUPPORTED:
				return scaleSupported != SCALE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__PRECISION_SUPPORTED:
				return precisionSupported != PRECISION_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__KEY_CONSTRAINT_SUPPORTED:
				return keyConstraintSupported != KEY_CONSTRAINT_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__IDENTITY_SUPPORTED:
				return identitySupported != IDENTITY_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MULTIPLE_COLUMNS_SUPPORTED:
				return multipleColumnsSupported != MULTIPLE_COLUMNS_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__NULLABLE_SUPPORTED:
				return nullableSupported != NULLABLE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_SUPPORTED:
				return defaultSupported != DEFAULT_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__CLUSTERING_SUPPORTED:
				return clusteringSupported != CLUSTERING_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__FILL_FACTOR_SUPPORTED:
				return fillFactorSupported != FILL_FACTOR_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__BIT_DATA_SUPPORTED:
				return bitDataSupported != BIT_DATA_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MAXIMUM_VALUE:
				return maximumValue != MAXIMUM_VALUE_EDEFAULT;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MINIMUM_VALUE:
				return minimumValue != MINIMUM_VALUE_EDEFAULT;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MAXIMUM_LENGTH:
				return maximumLength != MAXIMUM_LENGTH_EDEFAULT;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MAXIMUM_PRECISION:
				return maximumPrecision != MAXIMUM_PRECISION_EDEFAULT;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MAXIMUM_SCALE:
				return maximumScale != MAXIMUM_SCALE_EDEFAULT;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__MINIMUM_SCALE:
				return minimumScale != MINIMUM_SCALE_EDEFAULT;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_VALUE_TYPES:
				return defaultValueTypes != null && !defaultValueTypes.isEmpty();
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__PRIMITIVE_TYPE:
				return primitiveType != PRIMITIVE_TYPE_EDEFAULT;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__NAME:
				return name != null && !name.isEmpty();
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__JDBC_ENUM_TYPE:
				return jdbcEnumType != JDBC_ENUM_TYPE_EDEFAULT;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__CHARACTER_SET:
				return characterSet != null && !characterSet.isEmpty();
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__ENCODING_SCHEME:
				return encodingScheme != null && !encodingScheme.isEmpty();
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__CHARACTER_SET_SUFFIX:
				return CHARACTER_SET_SUFFIX_EDEFAULT == null ? characterSetSuffix != null : !CHARACTER_SET_SUFFIX_EDEFAULT.equals(characterSetSuffix);
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__ENCODING_SCHEME_SUFFIX:
				return ENCODING_SCHEME_SUFFIX_EDEFAULT == null ? encodingSchemeSuffix != null : !ENCODING_SCHEME_SUFFIX_EDEFAULT.equals(encodingSchemeSuffix);
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__JAVA_CLASS_NAME:
				return JAVA_CLASS_NAME_EDEFAULT == null ? javaClassName != null : !JAVA_CLASS_NAME_EDEFAULT.equals(javaClassName);
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_LENGTH:
				return defaultLength != DEFAULT_LENGTH_EDEFAULT;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_PRECISION:
				return defaultPrecision != DEFAULT_PRECISION_EDEFAULT;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_SCALE:
				return defaultScale != DEFAULT_SCALE_EDEFAULT;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__CUTOFF_PRECISION:
				return cutoffPrecision != CUTOFF_PRECISION_EDEFAULT;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LENGTH_UNIT:
				return lengthUnit != LENGTH_UNIT_EDEFAULT;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__ORDERING_SUPPORTED:
				return orderingSupported != ORDERING_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__GROUPING_SUPPORTED:
				return groupingSupported != GROUPING_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DISPLAY_NAME:
				return DISPLAY_NAME_EDEFAULT == null ? displayName != null : !DISPLAY_NAME_EDEFAULT.equals(displayName);
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__DISPLAY_NAME_SUPPORTED:
				return displayNameSupported != DISPLAY_NAME_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LEADING_FIELD_QUALIFIER_SUPPORTED:
				return leadingFieldQualifierSupported != LEADING_FIELD_QUALIFIER_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__TRAILING_FIELD_QUALIFIER_SUPPORTED:
				return trailingFieldQualifierSupported != TRAILING_FIELD_QUALIFIER_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__FIELD_QUALIFIER_SEPARATOR:
				return FIELD_QUALIFIER_SEPARATOR_EDEFAULT == null ? fieldQualifierSeparator != null : !FIELD_QUALIFIER_SEPARATOR_EDEFAULT.equals(fieldQualifierSeparator);
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LARGE_VALUE_SPECIFIER_SUPPORTED:
				return largeValueSpecifierSupported != LARGE_VALUE_SPECIFIER_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LARGE_VALUE_SPECIFIER_NAME:
				return LARGE_VALUE_SPECIFIER_NAME_EDEFAULT == null ? largeValueSpecifierName != null : !LARGE_VALUE_SPECIFIER_NAME_EDEFAULT.equals(largeValueSpecifierName);
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LARGE_VALUE_SPECIFIER_LENGTH:
				return largeValueSpecifierLength != LARGE_VALUE_SPECIFIER_LENGTH_EDEFAULT;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LENGTH_SEMANTIC_SUPPORTED:
				return lengthSemanticSupported != LENGTH_SEMANTIC_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LENGTH_SEMANTIC:
				return lengthSemantic != null && !lengthSemantic.isEmpty();
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION__LANGUAGE_TYPE:
				return languageType != null && !languageType.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (lengthSupported: "); //$NON-NLS-1$
		result.append(lengthSupported);
		result.append(", scaleSupported: "); //$NON-NLS-1$
		result.append(scaleSupported);
		result.append(", precisionSupported: "); //$NON-NLS-1$
		result.append(precisionSupported);
		result.append(", keyConstraintSupported: "); //$NON-NLS-1$
		result.append(keyConstraintSupported);
		result.append(", identitySupported: "); //$NON-NLS-1$
		result.append(identitySupported);
		result.append(", multipleColumnsSupported: "); //$NON-NLS-1$
		result.append(multipleColumnsSupported);
		result.append(", nullableSupported: "); //$NON-NLS-1$
		result.append(nullableSupported);
		result.append(", defaultSupported: "); //$NON-NLS-1$
		result.append(defaultSupported);
		result.append(", clusteringSupported: "); //$NON-NLS-1$
		result.append(clusteringSupported);
		result.append(", fillFactorSupported: "); //$NON-NLS-1$
		result.append(fillFactorSupported);
		result.append(", bitDataSupported: "); //$NON-NLS-1$
		result.append(bitDataSupported);
		result.append(", maximumValue: "); //$NON-NLS-1$
		result.append(maximumValue);
		result.append(", minimumValue: "); //$NON-NLS-1$
		result.append(minimumValue);
		result.append(", maximumLength: "); //$NON-NLS-1$
		result.append(maximumLength);
		result.append(", maximumPrecision: "); //$NON-NLS-1$
		result.append(maximumPrecision);
		result.append(", maximumScale: "); //$NON-NLS-1$
		result.append(maximumScale);
		result.append(", minimumScale: "); //$NON-NLS-1$
		result.append(minimumScale);
		result.append(", defaultValueTypes: "); //$NON-NLS-1$
		result.append(defaultValueTypes);
		result.append(", primitiveType: "); //$NON-NLS-1$
		result.append(primitiveType);
		result.append(", name: "); //$NON-NLS-1$
		result.append(name);
		result.append(", jdbcEnumType: "); //$NON-NLS-1$
		result.append(jdbcEnumType);
		result.append(", characterSet: "); //$NON-NLS-1$
		result.append(characterSet);
		result.append(", encodingScheme: "); //$NON-NLS-1$
		result.append(encodingScheme);
		result.append(", characterSetSuffix: "); //$NON-NLS-1$
		result.append(characterSetSuffix);
		result.append(", encodingSchemeSuffix: "); //$NON-NLS-1$
		result.append(encodingSchemeSuffix);
		result.append(", javaClassName: "); //$NON-NLS-1$
		result.append(javaClassName);
		result.append(", defaultLength: "); //$NON-NLS-1$
		result.append(defaultLength);
		result.append(", defaultPrecision: "); //$NON-NLS-1$
		result.append(defaultPrecision);
		result.append(", defaultScale: "); //$NON-NLS-1$
		result.append(defaultScale);
		result.append(", cutoffPrecision: "); //$NON-NLS-1$
		result.append(cutoffPrecision);
		result.append(", lengthUnit: "); //$NON-NLS-1$
		result.append(lengthUnit);
		result.append(", orderingSupported: "); //$NON-NLS-1$
		result.append(orderingSupported);
		result.append(", groupingSupported: "); //$NON-NLS-1$
		result.append(groupingSupported);
		result.append(", displayName: "); //$NON-NLS-1$
		result.append(displayName);
		result.append(", displayNameSupported: "); //$NON-NLS-1$
		result.append(displayNameSupported);
		result.append(", leadingFieldQualifierSupported: "); //$NON-NLS-1$
		result.append(leadingFieldQualifierSupported);
		result.append(", trailingFieldQualifierSupported: "); //$NON-NLS-1$
		result.append(trailingFieldQualifierSupported);
		result.append(", fieldQualifierSeparator: "); //$NON-NLS-1$
		result.append(fieldQualifierSeparator);
		result.append(", largeValueSpecifierSupported: "); //$NON-NLS-1$
		result.append(largeValueSpecifierSupported);
		result.append(", largeValueSpecifierName: "); //$NON-NLS-1$
		result.append(largeValueSpecifierName);
		result.append(", largeValueSpecifierLength: "); //$NON-NLS-1$
		result.append(largeValueSpecifierLength);
		result.append(", lengthSemanticSupported: "); //$NON-NLS-1$
		result.append(lengthSemanticSupported);
		result.append(", lengthSemantic: "); //$NON-NLS-1$
		result.append(lengthSemantic);
		result.append(", languageType: "); //$NON-NLS-1$
		result.append(languageType);
		result.append(')');
		return result.toString();
	}

} //PredefinedDataTypeDefinitionImpl

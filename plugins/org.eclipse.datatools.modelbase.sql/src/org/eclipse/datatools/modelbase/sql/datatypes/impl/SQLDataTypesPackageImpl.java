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

import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;
import org.eclipse.datatools.modelbase.sql.accesscontrol.impl.SQLAccessControlPackageImpl;
import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;
import org.eclipse.datatools.modelbase.sql.constraints.impl.SQLConstraintsPackageImpl;
import org.eclipse.datatools.modelbase.sql.datatypes.ApproximateNumericDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.ArrayDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.AttributeDefinition;
import org.eclipse.datatools.modelbase.sql.datatypes.BinaryStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.BooleanDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.CharacterSet;
import org.eclipse.datatools.modelbase.sql.datatypes.CharacterStringDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.CoercibilityType;
import org.eclipse.datatools.modelbase.sql.datatypes.CollectionDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.ConstructedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DataLinkDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DateDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.Domain;
import org.eclipse.datatools.modelbase.sql.datatypes.ElementType;
import org.eclipse.datatools.modelbase.sql.datatypes.ExactNumericDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.Field;
import org.eclipse.datatools.modelbase.sql.datatypes.FixedPrecisionDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.IntegerDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.IntegrityControlOption;
import org.eclipse.datatools.modelbase.sql.datatypes.IntervalDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.IntervalQualifierType;
import org.eclipse.datatools.modelbase.sql.datatypes.LinkControlOption;
import org.eclipse.datatools.modelbase.sql.datatypes.MultisetDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.NumericalDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.OrderingCategoryType;
import org.eclipse.datatools.modelbase.sql.datatypes.OrderingType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.PrimitiveType;
import org.eclipse.datatools.modelbase.sql.datatypes.ReadPermissionOption;
import org.eclipse.datatools.modelbase.sql.datatypes.ReferenceDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.RowDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesFactory;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;
import org.eclipse.datatools.modelbase.sql.datatypes.StructuredUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.TimeDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UnlinkOption;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedTypeOrdering;
import org.eclipse.datatools.modelbase.sql.datatypes.WritePermissionOption;
import org.eclipse.datatools.modelbase.sql.datatypes.XMLDataType;
import org.eclipse.datatools.modelbase.sql.expressions.SQLExpressionsPackage;
import org.eclipse.datatools.modelbase.sql.expressions.impl.SQLExpressionsPackageImpl;
import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;
import org.eclipse.datatools.modelbase.sql.routines.impl.SQLRoutinesPackageImpl;
import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;
import org.eclipse.datatools.modelbase.sql.schema.impl.SQLSchemaPackageImpl;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatementsPackage;
import org.eclipse.datatools.modelbase.sql.statements.impl.SQLStatementsPackageImpl;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.datatools.modelbase.sql.tables.impl.SQLTablesPackageImpl;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.impl.EcorePackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SQLDataTypesPackageImpl extends EPackageImpl implements SQLDataTypesPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass userDefinedTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass predefinedDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass collectionDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass numericalDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass characterStringDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rowDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass arrayDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass multisetDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass intervalDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass binaryStringDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass characterSetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass timeDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass distinctUserDefinedTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass structuredUserDefinedTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass attributeDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fixedPrecisionDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass domainEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fieldEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referenceDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constructedDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sqlDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataLinkDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass userDefinedTypeOrderingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dateDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass exactNumericDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass approximateNumericDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass xmlDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass elementTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum coercibilityTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum intervalQualifierTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum orderingTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum orderingCategoryTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum primitiveTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum linkControlOptionEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum integrityControlOptionEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum readPermissionOptionEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum writePermissionOptionEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum unlinkOptionEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private SQLDataTypesPackageImpl() {
		super(eNS_URI, SQLDataTypesFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this
	 * model, and for any others upon which it depends.  Simple
	 * dependencies are satisfied by calling this method on all
	 * dependent packages before doing anything else.  This method drives
	 * initialization for interdependent packages directly, in parallel
	 * with this package, itself.
	 * <p>Of this package and its interdependencies, all packages which
	 * have not yet been registered by their URI values are first created
	 * and registered.  The packages are then initialized in two steps:
	 * meta-model objects for all of the packages are created before any
	 * are initialized, since one package's meta-model objects may refer to
	 * those of another.
	 * <p>Invocation of this method will not affect any packages that have
	 * already been initialized.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static SQLDataTypesPackage init() {
		if (isInited) return (SQLDataTypesPackage)EPackage.Registry.INSTANCE.getEPackage(SQLDataTypesPackage.eNS_URI);

		// Obtain or create and register package
		SQLDataTypesPackageImpl theSQLDataTypesPackage = (SQLDataTypesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof SQLDataTypesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new SQLDataTypesPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		SQLSchemaPackageImpl theSQLSchemaPackage = (SQLSchemaPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SQLSchemaPackage.eNS_URI) instanceof SQLSchemaPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SQLSchemaPackage.eNS_URI) : SQLSchemaPackage.eINSTANCE);
		SQLConstraintsPackageImpl theSQLConstraintsPackage = (SQLConstraintsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SQLConstraintsPackage.eNS_URI) instanceof SQLConstraintsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SQLConstraintsPackage.eNS_URI) : SQLConstraintsPackage.eINSTANCE);
		SQLExpressionsPackageImpl theSQLExpressionsPackage = (SQLExpressionsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SQLExpressionsPackage.eNS_URI) instanceof SQLExpressionsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SQLExpressionsPackage.eNS_URI) : SQLExpressionsPackage.eINSTANCE);
		SQLRoutinesPackageImpl theSQLRoutinesPackage = (SQLRoutinesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SQLRoutinesPackage.eNS_URI) instanceof SQLRoutinesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SQLRoutinesPackage.eNS_URI) : SQLRoutinesPackage.eINSTANCE);
		SQLStatementsPackageImpl theSQLStatementsPackage = (SQLStatementsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SQLStatementsPackage.eNS_URI) instanceof SQLStatementsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SQLStatementsPackage.eNS_URI) : SQLStatementsPackage.eINSTANCE);
		SQLTablesPackageImpl theSQLTablesPackage = (SQLTablesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SQLTablesPackage.eNS_URI) instanceof SQLTablesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SQLTablesPackage.eNS_URI) : SQLTablesPackage.eINSTANCE);
		SQLAccessControlPackageImpl theSQLAccessControlPackage = (SQLAccessControlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SQLAccessControlPackage.eNS_URI) instanceof SQLAccessControlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SQLAccessControlPackage.eNS_URI) : SQLAccessControlPackage.eINSTANCE);

		// Create package meta-data objects
		theSQLDataTypesPackage.createPackageContents();
		theSQLSchemaPackage.createPackageContents();
		theSQLConstraintsPackage.createPackageContents();
		theSQLExpressionsPackage.createPackageContents();
		theSQLRoutinesPackage.createPackageContents();
		theSQLStatementsPackage.createPackageContents();
		theSQLTablesPackage.createPackageContents();
		theSQLAccessControlPackage.createPackageContents();

		// Initialize created meta-data
		theSQLDataTypesPackage.initializePackageContents();
		theSQLSchemaPackage.initializePackageContents();
		theSQLConstraintsPackage.initializePackageContents();
		theSQLExpressionsPackage.initializePackageContents();
		theSQLRoutinesPackage.initializePackageContents();
		theSQLStatementsPackage.initializePackageContents();
		theSQLTablesPackage.initializePackageContents();
		theSQLAccessControlPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theSQLDataTypesPackage.freeze();

		return theSQLDataTypesPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUserDefinedType() {
		return userDefinedTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUserDefinedType_Schema() {
		return (EReference)userDefinedTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUserDefinedType_Ordering() {
		return (EReference)userDefinedTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataType() {
		return dataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPredefinedDataType() {
		return predefinedDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataType_PrimitiveType() {
		return (EAttribute)predefinedDataTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCollectionDataType() {
		return collectionDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCollectionDataType_ElementType() {
		return (EReference)collectionDataTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNumericalDataType() {
		return numericalDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNumericalDataType_Precision() {
		return (EAttribute)numericalDataTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCharacterStringDataType() {
		return characterStringDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCharacterStringDataType_Length() {
		return (EAttribute)characterStringDataTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCharacterStringDataType_Coercibility() {
		return (EAttribute)characterStringDataTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCharacterStringDataType_FixedLength() {
		return (EAttribute)characterStringDataTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCharacterStringDataType_CollationName() {
		return (EAttribute)characterStringDataTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCharacterStringDataType_CharacterSet() {
		return (EReference)characterStringDataTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRowDataType() {
		return rowDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRowDataType_Fields() {
		return (EReference)rowDataTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArrayDataType() {
		return arrayDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArrayDataType_MaxCardinality() {
		return (EAttribute)arrayDataTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMultisetDataType() {
		return multisetDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBooleanDataType() {
		return booleanDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntervalDataType() {
		return intervalDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntervalDataType_LeadingQualifier() {
		return (EAttribute)intervalDataTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntervalDataType_TrailingQualifier() {
		return (EAttribute)intervalDataTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntervalDataType_LeadingFieldPrecision() {
		return (EAttribute)intervalDataTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntervalDataType_TrailingFieldPrecision() {
		return (EAttribute)intervalDataTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntervalDataType_FractionalSecondsPrecision() {
		return (EAttribute)intervalDataTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBinaryStringDataType() {
		return binaryStringDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBinaryStringDataType_Length() {
		return (EAttribute)binaryStringDataTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCharacterSet() {
		return characterSetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCharacterSet_Repertoire() {
		return (EAttribute)characterSetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCharacterSet_DefaultCollation() {
		return (EAttribute)characterSetEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCharacterSet_Encoding() {
		return (EAttribute)characterSetEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCharacterSet_CharacterStringDataType() {
		return (EReference)characterSetEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCharacterSet_Schema() {
		return (EReference)characterSetEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTimeDataType() {
		return timeDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimeDataType_FractionalSecondsPrecision() {
		return (EAttribute)timeDataTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimeDataType_TimeZone() {
		return (EAttribute)timeDataTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDistinctUserDefinedType() {
		return distinctUserDefinedTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDistinctUserDefinedType_PredefinedRepresentation() {
		return (EReference)distinctUserDefinedTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStructuredUserDefinedType() {
		return structuredUserDefinedTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStructuredUserDefinedType_Instantiable() {
		return (EAttribute)structuredUserDefinedTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStructuredUserDefinedType_Final() {
		return (EAttribute)structuredUserDefinedTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStructuredUserDefinedType_Super() {
		return (EReference)structuredUserDefinedTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStructuredUserDefinedType_Sub() {
		return (EReference)structuredUserDefinedTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStructuredUserDefinedType_Attributes() {
		return (EReference)structuredUserDefinedTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStructuredUserDefinedType_Methods() {
		return (EReference)structuredUserDefinedTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAttributeDefinition() {
		return attributeDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttributeDefinition_ScopeCheck() {
		return (EAttribute)attributeDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttributeDefinition_ScopeChecked() {
		return (EAttribute)attributeDefinitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttributeDefinition_DefaultValue() {
		return (EAttribute)attributeDefinitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFixedPrecisionDataType() {
		return fixedPrecisionDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDomain() {
		return domainEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDomain_Constraint() {
		return (EReference)domainEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDomain_DefaultValue() {
		return (EAttribute)domainEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getField() {
		return fieldEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getField_ScopeCheck() {
		return (EAttribute)fieldEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getField_ScopeChecked() {
		return (EAttribute)fieldEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReferenceDataType() {
		return referenceDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReferenceDataType_ScopeTable() {
		return (EReference)referenceDataTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getReferenceDataType_ReferencedType() {
		return (EReference)referenceDataTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstructedDataType() {
		return constructedDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSQLDataType() {
		return sqlDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataLinkDataType() {
		return dataLinkDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataLinkDataType_Length() {
		return (EAttribute)dataLinkDataTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataLinkDataType_LinkControl() {
		return (EAttribute)dataLinkDataTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataLinkDataType_IntegrityControl() {
		return (EAttribute)dataLinkDataTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataLinkDataType_ReadPermission() {
		return (EAttribute)dataLinkDataTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataLinkDataType_WritePermission() {
		return (EAttribute)dataLinkDataTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataLinkDataType_Recovery() {
		return (EAttribute)dataLinkDataTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataLinkDataType_Unlink() {
		return (EAttribute)dataLinkDataTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUserDefinedTypeOrdering() {
		return userDefinedTypeOrderingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUserDefinedTypeOrdering_OrderingForm() {
		return (EAttribute)userDefinedTypeOrderingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUserDefinedTypeOrdering_OrderingCategory() {
		return (EAttribute)userDefinedTypeOrderingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUserDefinedTypeOrdering_OrderingRoutine() {
		return (EReference)userDefinedTypeOrderingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDateDataType() {
		return dateDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExactNumericDataType() {
		return exactNumericDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExactNumericDataType_Scale() {
		return (EAttribute)exactNumericDataTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getApproximateNumericDataType() {
		return approximateNumericDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntegerDataType() {
		return integerDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getXMLDataType() {
		return xmlDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getElementType() {
		return elementTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElementType_CollectionDataType() {
		return (EReference)elementTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getCoercibilityType() {
		return coercibilityTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getIntervalQualifierType() {
		return intervalQualifierTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getOrderingType() {
		return orderingTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getOrderingCategoryType() {
		return orderingCategoryTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPrimitiveType() {
		return primitiveTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getLinkControlOption() {
		return linkControlOptionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getIntegrityControlOption() {
		return integrityControlOptionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getReadPermissionOption() {
		return readPermissionOptionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getWritePermissionOption() {
		return writePermissionOptionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getUnlinkOption() {
		return unlinkOptionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SQLDataTypesFactory getSQLDataTypesFactory() {
		return (SQLDataTypesFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		userDefinedTypeEClass = createEClass(USER_DEFINED_TYPE);
		createEReference(userDefinedTypeEClass, USER_DEFINED_TYPE__SCHEMA);
		createEReference(userDefinedTypeEClass, USER_DEFINED_TYPE__ORDERING);

		dataTypeEClass = createEClass(DATA_TYPE);

		predefinedDataTypeEClass = createEClass(PREDEFINED_DATA_TYPE);
		createEAttribute(predefinedDataTypeEClass, PREDEFINED_DATA_TYPE__PRIMITIVE_TYPE);

		collectionDataTypeEClass = createEClass(COLLECTION_DATA_TYPE);
		createEReference(collectionDataTypeEClass, COLLECTION_DATA_TYPE__ELEMENT_TYPE);

		numericalDataTypeEClass = createEClass(NUMERICAL_DATA_TYPE);
		createEAttribute(numericalDataTypeEClass, NUMERICAL_DATA_TYPE__PRECISION);

		characterStringDataTypeEClass = createEClass(CHARACTER_STRING_DATA_TYPE);
		createEAttribute(characterStringDataTypeEClass, CHARACTER_STRING_DATA_TYPE__LENGTH);
		createEAttribute(characterStringDataTypeEClass, CHARACTER_STRING_DATA_TYPE__COERCIBILITY);
		createEAttribute(characterStringDataTypeEClass, CHARACTER_STRING_DATA_TYPE__FIXED_LENGTH);
		createEAttribute(characterStringDataTypeEClass, CHARACTER_STRING_DATA_TYPE__COLLATION_NAME);
		createEReference(characterStringDataTypeEClass, CHARACTER_STRING_DATA_TYPE__CHARACTER_SET);

		rowDataTypeEClass = createEClass(ROW_DATA_TYPE);
		createEReference(rowDataTypeEClass, ROW_DATA_TYPE__FIELDS);

		arrayDataTypeEClass = createEClass(ARRAY_DATA_TYPE);
		createEAttribute(arrayDataTypeEClass, ARRAY_DATA_TYPE__MAX_CARDINALITY);

		multisetDataTypeEClass = createEClass(MULTISET_DATA_TYPE);

		booleanDataTypeEClass = createEClass(BOOLEAN_DATA_TYPE);

		intervalDataTypeEClass = createEClass(INTERVAL_DATA_TYPE);
		createEAttribute(intervalDataTypeEClass, INTERVAL_DATA_TYPE__LEADING_QUALIFIER);
		createEAttribute(intervalDataTypeEClass, INTERVAL_DATA_TYPE__TRAILING_QUALIFIER);
		createEAttribute(intervalDataTypeEClass, INTERVAL_DATA_TYPE__LEADING_FIELD_PRECISION);
		createEAttribute(intervalDataTypeEClass, INTERVAL_DATA_TYPE__TRAILING_FIELD_PRECISION);
		createEAttribute(intervalDataTypeEClass, INTERVAL_DATA_TYPE__FRACTIONAL_SECONDS_PRECISION);

		binaryStringDataTypeEClass = createEClass(BINARY_STRING_DATA_TYPE);
		createEAttribute(binaryStringDataTypeEClass, BINARY_STRING_DATA_TYPE__LENGTH);

		characterSetEClass = createEClass(CHARACTER_SET);
		createEAttribute(characterSetEClass, CHARACTER_SET__REPERTOIRE);
		createEAttribute(characterSetEClass, CHARACTER_SET__DEFAULT_COLLATION);
		createEAttribute(characterSetEClass, CHARACTER_SET__ENCODING);
		createEReference(characterSetEClass, CHARACTER_SET__CHARACTER_STRING_DATA_TYPE);
		createEReference(characterSetEClass, CHARACTER_SET__SCHEMA);

		timeDataTypeEClass = createEClass(TIME_DATA_TYPE);
		createEAttribute(timeDataTypeEClass, TIME_DATA_TYPE__FRACTIONAL_SECONDS_PRECISION);
		createEAttribute(timeDataTypeEClass, TIME_DATA_TYPE__TIME_ZONE);

		distinctUserDefinedTypeEClass = createEClass(DISTINCT_USER_DEFINED_TYPE);
		createEReference(distinctUserDefinedTypeEClass, DISTINCT_USER_DEFINED_TYPE__PREDEFINED_REPRESENTATION);

		structuredUserDefinedTypeEClass = createEClass(STRUCTURED_USER_DEFINED_TYPE);
		createEAttribute(structuredUserDefinedTypeEClass, STRUCTURED_USER_DEFINED_TYPE__INSTANTIABLE);
		createEAttribute(structuredUserDefinedTypeEClass, STRUCTURED_USER_DEFINED_TYPE__FINAL);
		createEReference(structuredUserDefinedTypeEClass, STRUCTURED_USER_DEFINED_TYPE__SUPER);
		createEReference(structuredUserDefinedTypeEClass, STRUCTURED_USER_DEFINED_TYPE__SUB);
		createEReference(structuredUserDefinedTypeEClass, STRUCTURED_USER_DEFINED_TYPE__ATTRIBUTES);
		createEReference(structuredUserDefinedTypeEClass, STRUCTURED_USER_DEFINED_TYPE__METHODS);

		attributeDefinitionEClass = createEClass(ATTRIBUTE_DEFINITION);
		createEAttribute(attributeDefinitionEClass, ATTRIBUTE_DEFINITION__SCOPE_CHECK);
		createEAttribute(attributeDefinitionEClass, ATTRIBUTE_DEFINITION__SCOPE_CHECKED);
		createEAttribute(attributeDefinitionEClass, ATTRIBUTE_DEFINITION__DEFAULT_VALUE);

		fixedPrecisionDataTypeEClass = createEClass(FIXED_PRECISION_DATA_TYPE);

		domainEClass = createEClass(DOMAIN);
		createEReference(domainEClass, DOMAIN__CONSTRAINT);
		createEAttribute(domainEClass, DOMAIN__DEFAULT_VALUE);

		fieldEClass = createEClass(FIELD);
		createEAttribute(fieldEClass, FIELD__SCOPE_CHECK);
		createEAttribute(fieldEClass, FIELD__SCOPE_CHECKED);

		referenceDataTypeEClass = createEClass(REFERENCE_DATA_TYPE);
		createEReference(referenceDataTypeEClass, REFERENCE_DATA_TYPE__SCOPE_TABLE);
		createEReference(referenceDataTypeEClass, REFERENCE_DATA_TYPE__REFERENCED_TYPE);

		constructedDataTypeEClass = createEClass(CONSTRUCTED_DATA_TYPE);

		sqlDataTypeEClass = createEClass(SQL_DATA_TYPE);

		dataLinkDataTypeEClass = createEClass(DATA_LINK_DATA_TYPE);
		createEAttribute(dataLinkDataTypeEClass, DATA_LINK_DATA_TYPE__LENGTH);
		createEAttribute(dataLinkDataTypeEClass, DATA_LINK_DATA_TYPE__LINK_CONTROL);
		createEAttribute(dataLinkDataTypeEClass, DATA_LINK_DATA_TYPE__INTEGRITY_CONTROL);
		createEAttribute(dataLinkDataTypeEClass, DATA_LINK_DATA_TYPE__READ_PERMISSION);
		createEAttribute(dataLinkDataTypeEClass, DATA_LINK_DATA_TYPE__WRITE_PERMISSION);
		createEAttribute(dataLinkDataTypeEClass, DATA_LINK_DATA_TYPE__RECOVERY);
		createEAttribute(dataLinkDataTypeEClass, DATA_LINK_DATA_TYPE__UNLINK);

		userDefinedTypeOrderingEClass = createEClass(USER_DEFINED_TYPE_ORDERING);
		createEAttribute(userDefinedTypeOrderingEClass, USER_DEFINED_TYPE_ORDERING__ORDERING_FORM);
		createEAttribute(userDefinedTypeOrderingEClass, USER_DEFINED_TYPE_ORDERING__ORDERING_CATEGORY);
		createEReference(userDefinedTypeOrderingEClass, USER_DEFINED_TYPE_ORDERING__ORDERING_ROUTINE);

		dateDataTypeEClass = createEClass(DATE_DATA_TYPE);

		exactNumericDataTypeEClass = createEClass(EXACT_NUMERIC_DATA_TYPE);
		createEAttribute(exactNumericDataTypeEClass, EXACT_NUMERIC_DATA_TYPE__SCALE);

		approximateNumericDataTypeEClass = createEClass(APPROXIMATE_NUMERIC_DATA_TYPE);

		integerDataTypeEClass = createEClass(INTEGER_DATA_TYPE);

		xmlDataTypeEClass = createEClass(XML_DATA_TYPE);

		elementTypeEClass = createEClass(ELEMENT_TYPE);
		createEReference(elementTypeEClass, ELEMENT_TYPE__COLLECTION_DATA_TYPE);

		// Create enums
		coercibilityTypeEEnum = createEEnum(COERCIBILITY_TYPE);
		intervalQualifierTypeEEnum = createEEnum(INTERVAL_QUALIFIER_TYPE);
		orderingTypeEEnum = createEEnum(ORDERING_TYPE);
		orderingCategoryTypeEEnum = createEEnum(ORDERING_CATEGORY_TYPE);
		primitiveTypeEEnum = createEEnum(PRIMITIVE_TYPE);
		linkControlOptionEEnum = createEEnum(LINK_CONTROL_OPTION);
		integrityControlOptionEEnum = createEEnum(INTEGRITY_CONTROL_OPTION);
		readPermissionOptionEEnum = createEEnum(READ_PERMISSION_OPTION);
		writePermissionOptionEEnum = createEEnum(WRITE_PERMISSION_OPTION);
		unlinkOptionEEnum = createEEnum(UNLINK_OPTION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		SQLSchemaPackage theSQLSchemaPackage = (SQLSchemaPackage)EPackage.Registry.INSTANCE.getEPackage(SQLSchemaPackage.eNS_URI);
		SQLRoutinesPackage theSQLRoutinesPackage = (SQLRoutinesPackage)EPackage.Registry.INSTANCE.getEPackage(SQLRoutinesPackage.eNS_URI);
		SQLConstraintsPackage theSQLConstraintsPackage = (SQLConstraintsPackage)EPackage.Registry.INSTANCE.getEPackage(SQLConstraintsPackage.eNS_URI);
		SQLTablesPackage theSQLTablesPackage = (SQLTablesPackage)EPackage.Registry.INSTANCE.getEPackage(SQLTablesPackage.eNS_URI);

		// Add supertypes to classes
		userDefinedTypeEClass.getESuperTypes().add(this.getDataType());
		dataTypeEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		predefinedDataTypeEClass.getESuperTypes().add(this.getSQLDataType());
		collectionDataTypeEClass.getESuperTypes().add(this.getConstructedDataType());
		numericalDataTypeEClass.getESuperTypes().add(this.getPredefinedDataType());
		characterStringDataTypeEClass.getESuperTypes().add(this.getPredefinedDataType());
		rowDataTypeEClass.getESuperTypes().add(this.getConstructedDataType());
		arrayDataTypeEClass.getESuperTypes().add(this.getCollectionDataType());
		multisetDataTypeEClass.getESuperTypes().add(this.getCollectionDataType());
		booleanDataTypeEClass.getESuperTypes().add(this.getPredefinedDataType());
		intervalDataTypeEClass.getESuperTypes().add(this.getPredefinedDataType());
		binaryStringDataTypeEClass.getESuperTypes().add(this.getPredefinedDataType());
		characterSetEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		timeDataTypeEClass.getESuperTypes().add(this.getPredefinedDataType());
		distinctUserDefinedTypeEClass.getESuperTypes().add(this.getUserDefinedType());
		structuredUserDefinedTypeEClass.getESuperTypes().add(this.getUserDefinedType());
		attributeDefinitionEClass.getESuperTypes().add(theSQLSchemaPackage.getTypedElement());
		fixedPrecisionDataTypeEClass.getESuperTypes().add(this.getExactNumericDataType());
		domainEClass.getESuperTypes().add(this.getDistinctUserDefinedType());
		fieldEClass.getESuperTypes().add(theSQLSchemaPackage.getTypedElement());
		referenceDataTypeEClass.getESuperTypes().add(this.getConstructedDataType());
		constructedDataTypeEClass.getESuperTypes().add(this.getDataType());
		sqlDataTypeEClass.getESuperTypes().add(this.getDataType());
		dataLinkDataTypeEClass.getESuperTypes().add(this.getPredefinedDataType());
		userDefinedTypeOrderingEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		dateDataTypeEClass.getESuperTypes().add(this.getPredefinedDataType());
		exactNumericDataTypeEClass.getESuperTypes().add(this.getNumericalDataType());
		approximateNumericDataTypeEClass.getESuperTypes().add(this.getNumericalDataType());
		integerDataTypeEClass.getESuperTypes().add(this.getExactNumericDataType());
		xmlDataTypeEClass.getESuperTypes().add(this.getPredefinedDataType());
		elementTypeEClass.getESuperTypes().add(theSQLSchemaPackage.getTypedElement());

		// Initialize classes and features; add operations and parameters
		initEClass(userDefinedTypeEClass, UserDefinedType.class, "UserDefinedType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getUserDefinedType_Schema(), theSQLSchemaPackage.getSchema(), theSQLSchemaPackage.getSchema_UserDefinedTypes(), "schema", null, 1, 1, UserDefinedType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getUserDefinedType_Ordering(), this.getUserDefinedTypeOrdering(), null, "ordering", null, 0, 1, UserDefinedType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(dataTypeEClass, DataType.class, "DataType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		EOperation op = addEOperation(dataTypeEClass, null, "setContainer"); //$NON-NLS-1$
		addEParameter(op, theSQLSchemaPackage.getTypedElement(), "newContainer", 0, 1); //$NON-NLS-1$

		initEClass(predefinedDataTypeEClass, PredefinedDataType.class, "PredefinedDataType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getPredefinedDataType_PrimitiveType(), this.getPrimitiveType(), "primitiveType", null, 0, 1, PredefinedDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(collectionDataTypeEClass, CollectionDataType.class, "CollectionDataType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getCollectionDataType_ElementType(), this.getElementType(), this.getElementType_CollectionDataType(), "elementType", null, 1, 1, CollectionDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(numericalDataTypeEClass, NumericalDataType.class, "NumericalDataType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getNumericalDataType_Precision(), ecorePackage.getEInt(), "precision", null, 0, 1, NumericalDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(characterStringDataTypeEClass, CharacterStringDataType.class, "CharacterStringDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getCharacterStringDataType_Length(), ecorePackage.getEInt(), "length", "1", 0, 1, CharacterStringDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getCharacterStringDataType_Coercibility(), this.getCoercibilityType(), "coercibility", null, 0, 1, CharacterStringDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getCharacterStringDataType_FixedLength(), ecorePackage.getEBoolean(), "fixedLength", null, 0, 1, CharacterStringDataType.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getCharacterStringDataType_CollationName(), ecorePackage.getEString(), "collationName", null, 0, 1, CharacterStringDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getCharacterStringDataType_CharacterSet(), this.getCharacterSet(), this.getCharacterSet_CharacterStringDataType(), "characterSet", null, 1, 1, CharacterStringDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(rowDataTypeEClass, RowDataType.class, "RowDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getRowDataType_Fields(), this.getField(), null, "fields", null, 1, -1, RowDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(arrayDataTypeEClass, ArrayDataType.class, "ArrayDataType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getArrayDataType_MaxCardinality(), ecorePackage.getEInt(), "maxCardinality", null, 0, 1, ArrayDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(multisetDataTypeEClass, MultisetDataType.class, "MultisetDataType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(booleanDataTypeEClass, BooleanDataType.class, "BooleanDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(intervalDataTypeEClass, IntervalDataType.class, "IntervalDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getIntervalDataType_LeadingQualifier(), this.getIntervalQualifierType(), "leadingQualifier", null, 0, 1, IntervalDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getIntervalDataType_TrailingQualifier(), this.getIntervalQualifierType(), "trailingQualifier", null, 0, 1, IntervalDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getIntervalDataType_LeadingFieldPrecision(), ecorePackage.getEInt(), "leadingFieldPrecision", null, 0, 1, IntervalDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getIntervalDataType_TrailingFieldPrecision(), ecorePackage.getEInt(), "trailingFieldPrecision", null, 0, 1, IntervalDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getIntervalDataType_FractionalSecondsPrecision(), ecorePackage.getEInt(), "fractionalSecondsPrecision", null, 0, 1, IntervalDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(binaryStringDataTypeEClass, BinaryStringDataType.class, "BinaryStringDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getBinaryStringDataType_Length(), ecorePackage.getEInt(), "length", null, 0, 1, BinaryStringDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		addEOperation(binaryStringDataTypeEClass, ecorePackage.getEBoolean(), "equals", 0, 1); //$NON-NLS-1$

		initEClass(characterSetEClass, CharacterSet.class, "CharacterSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getCharacterSet_Repertoire(), ecorePackage.getEString(), "repertoire", null, 0, 1, CharacterSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getCharacterSet_DefaultCollation(), ecorePackage.getEString(), "defaultCollation", null, 0, 1, CharacterSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getCharacterSet_Encoding(), ecorePackage.getEString(), "encoding", null, 0, 1, CharacterSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getCharacterSet_CharacterStringDataType(), this.getCharacterStringDataType(), this.getCharacterStringDataType_CharacterSet(), "CharacterStringDataType", null, 1, 1, CharacterSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getCharacterSet_Schema(), theSQLSchemaPackage.getSchema(), theSQLSchemaPackage.getSchema_CharSets(), "schema", null, 1, 1, CharacterSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(timeDataTypeEClass, TimeDataType.class, "TimeDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getTimeDataType_FractionalSecondsPrecision(), ecorePackage.getEInt(), "fractionalSecondsPrecision", null, 0, 1, TimeDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTimeDataType_TimeZone(), ecorePackage.getEBoolean(), "timeZone", "false", 0, 1, TimeDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(distinctUserDefinedTypeEClass, DistinctUserDefinedType.class, "DistinctUserDefinedType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getDistinctUserDefinedType_PredefinedRepresentation(), this.getPredefinedDataType(), null, "predefinedRepresentation", null, 1, 1, DistinctUserDefinedType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(structuredUserDefinedTypeEClass, StructuredUserDefinedType.class, "StructuredUserDefinedType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getStructuredUserDefinedType_Instantiable(), ecorePackage.getEBoolean(), "instantiable", "True", 0, 1, StructuredUserDefinedType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getStructuredUserDefinedType_Final(), ecorePackage.getEBoolean(), "final", null, 0, 1, StructuredUserDefinedType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getStructuredUserDefinedType_Super(), this.getStructuredUserDefinedType(), this.getStructuredUserDefinedType_Sub(), "super", null, 0, 1, StructuredUserDefinedType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getStructuredUserDefinedType_Sub(), this.getStructuredUserDefinedType(), this.getStructuredUserDefinedType_Super(), "sub", null, 0, -1, StructuredUserDefinedType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getStructuredUserDefinedType_Attributes(), this.getAttributeDefinition(), null, "attributes", null, 0, -1, StructuredUserDefinedType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getStructuredUserDefinedType_Methods(), theSQLRoutinesPackage.getMethod(), null, "methods", null, 0, -1, StructuredUserDefinedType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(attributeDefinitionEClass, AttributeDefinition.class, "AttributeDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getAttributeDefinition_ScopeCheck(), theSQLSchemaPackage.getReferentialActionType(), "scopeCheck", null, 0, 1, AttributeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getAttributeDefinition_ScopeChecked(), ecorePackage.getEBoolean(), "scopeChecked", null, 0, 1, AttributeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getAttributeDefinition_DefaultValue(), ecorePackage.getEString(), "defaultValue", null, 0, 1, AttributeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(fixedPrecisionDataTypeEClass, FixedPrecisionDataType.class, "FixedPrecisionDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(domainEClass, Domain.class, "Domain", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getDomain_Constraint(), theSQLConstraintsPackage.getCheckConstraint(), null, "constraint", null, 0, -1, Domain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDomain_DefaultValue(), ecorePackage.getEString(), "defaultValue", null, 0, 1, Domain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(fieldEClass, Field.class, "Field", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getField_ScopeCheck(), theSQLSchemaPackage.getReferentialActionType(), "scopeCheck", null, 0, 1, Field.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getField_ScopeChecked(), ecorePackage.getEBoolean(), "scopeChecked", null, 0, 1, Field.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(referenceDataTypeEClass, ReferenceDataType.class, "ReferenceDataType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getReferenceDataType_ScopeTable(), theSQLTablesPackage.getTable(), null, "scopeTable", null, 1, 1, ReferenceDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getReferenceDataType_ReferencedType(), this.getStructuredUserDefinedType(), null, "referencedType", null, 1, 1, ReferenceDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(constructedDataTypeEClass, ConstructedDataType.class, "ConstructedDataType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(sqlDataTypeEClass, SQLDataType.class, "SQLDataType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(dataLinkDataTypeEClass, DataLinkDataType.class, "DataLinkDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDataLinkDataType_Length(), ecorePackage.getEInt(), "length", null, 0, 1, DataLinkDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDataLinkDataType_LinkControl(), this.getLinkControlOption(), "linkControl", null, 0, 1, DataLinkDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDataLinkDataType_IntegrityControl(), this.getIntegrityControlOption(), "integrityControl", null, 0, 1, DataLinkDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDataLinkDataType_ReadPermission(), this.getReadPermissionOption(), "readPermission", null, 0, 1, DataLinkDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDataLinkDataType_WritePermission(), this.getWritePermissionOption(), "writePermission", null, 0, 1, DataLinkDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDataLinkDataType_Recovery(), ecorePackage.getEBoolean(), "recovery", null, 0, 1, DataLinkDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDataLinkDataType_Unlink(), this.getUnlinkOption(), "unlink", null, 0, 1, DataLinkDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(userDefinedTypeOrderingEClass, UserDefinedTypeOrdering.class, "UserDefinedTypeOrdering", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getUserDefinedTypeOrdering_OrderingForm(), this.getOrderingType(), "orderingForm", null, 0, 1, UserDefinedTypeOrdering.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getUserDefinedTypeOrdering_OrderingCategory(), this.getOrderingCategoryType(), "orderingCategory", null, 0, 1, UserDefinedTypeOrdering.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getUserDefinedTypeOrdering_OrderingRoutine(), theSQLRoutinesPackage.getRoutine(), null, "orderingRoutine", null, 1, 1, UserDefinedTypeOrdering.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(dateDataTypeEClass, DateDataType.class, "DateDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(exactNumericDataTypeEClass, ExactNumericDataType.class, "ExactNumericDataType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getExactNumericDataType_Scale(), ecorePackage.getEInt(), "scale", null, 0, 1, ExactNumericDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(approximateNumericDataTypeEClass, ApproximateNumericDataType.class, "ApproximateNumericDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(integerDataTypeEClass, IntegerDataType.class, "IntegerDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(xmlDataTypeEClass, XMLDataType.class, "XMLDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(elementTypeEClass, ElementType.class, "ElementType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getElementType_CollectionDataType(), this.getCollectionDataType(), this.getCollectionDataType_ElementType(), "CollectionDataType", null, 0, 1, ElementType.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		// Initialize enums and add enum literals
		initEEnum(coercibilityTypeEEnum, CoercibilityType.class, "CoercibilityType"); //$NON-NLS-1$
		addEEnumLiteral(coercibilityTypeEEnum, CoercibilityType.IMPLICIT_LITERAL);
		addEEnumLiteral(coercibilityTypeEEnum, CoercibilityType.EXPLICIT_LITERAL);
		addEEnumLiteral(coercibilityTypeEEnum, CoercibilityType.COERCIBILE_LITERAL);
		addEEnumLiteral(coercibilityTypeEEnum, CoercibilityType.NO_COLLATION_LITERAL);

		initEEnum(intervalQualifierTypeEEnum, IntervalQualifierType.class, "IntervalQualifierType"); //$NON-NLS-1$
		addEEnumLiteral(intervalQualifierTypeEEnum, IntervalQualifierType.YEAR_LITERAL);
		addEEnumLiteral(intervalQualifierTypeEEnum, IntervalQualifierType.MONTH_LITERAL);
		addEEnumLiteral(intervalQualifierTypeEEnum, IntervalQualifierType.DAY_LITERAL);
		addEEnumLiteral(intervalQualifierTypeEEnum, IntervalQualifierType.HOUR_LITERAL);
		addEEnumLiteral(intervalQualifierTypeEEnum, IntervalQualifierType.MINUTE_LITERAL);
		addEEnumLiteral(intervalQualifierTypeEEnum, IntervalQualifierType.SECOND_LITERAL);
		addEEnumLiteral(intervalQualifierTypeEEnum, IntervalQualifierType.FRACTION_LITERAL);

		initEEnum(orderingTypeEEnum, OrderingType.class, "OrderingType"); //$NON-NLS-1$
		addEEnumLiteral(orderingTypeEEnum, OrderingType.EQUALS_LITERAL);
		addEEnumLiteral(orderingTypeEEnum, OrderingType.FULL_LITERAL);

		initEEnum(orderingCategoryTypeEEnum, OrderingCategoryType.class, "OrderingCategoryType"); //$NON-NLS-1$
		addEEnumLiteral(orderingCategoryTypeEEnum, OrderingCategoryType.RELATIVE_LITERAL);
		addEEnumLiteral(orderingCategoryTypeEEnum, OrderingCategoryType.MAP_LITERAL);
		addEEnumLiteral(orderingCategoryTypeEEnum, OrderingCategoryType.STATE_LITERAL);

		initEEnum(primitiveTypeEEnum, PrimitiveType.class, "PrimitiveType"); //$NON-NLS-1$
		addEEnumLiteral(primitiveTypeEEnum, PrimitiveType.CHARACTER_LITERAL);
		addEEnumLiteral(primitiveTypeEEnum, PrimitiveType.CHARACTER_VARYING_LITERAL);
		addEEnumLiteral(primitiveTypeEEnum, PrimitiveType.CHARACTER_LARGE_OBJECT_LITERAL);
		addEEnumLiteral(primitiveTypeEEnum, PrimitiveType.NATIONAL_CHARACTER_LITERAL);
		addEEnumLiteral(primitiveTypeEEnum, PrimitiveType.NATIONAL_CHARACTER_VARYING_LITERAL);
		addEEnumLiteral(primitiveTypeEEnum, PrimitiveType.NATIONAL_CHARACTER_LARGE_OBJECT_LITERAL);
		addEEnumLiteral(primitiveTypeEEnum, PrimitiveType.BINARY_LITERAL);
		addEEnumLiteral(primitiveTypeEEnum, PrimitiveType.BINARY_VARYING_LITERAL);
		addEEnumLiteral(primitiveTypeEEnum, PrimitiveType.BINARY_LARGE_OBJECT_LITERAL);
		addEEnumLiteral(primitiveTypeEEnum, PrimitiveType.NUMERIC_LITERAL);
		addEEnumLiteral(primitiveTypeEEnum, PrimitiveType.DECIMAL_LITERAL);
		addEEnumLiteral(primitiveTypeEEnum, PrimitiveType.SMALLINT_LITERAL);
		addEEnumLiteral(primitiveTypeEEnum, PrimitiveType.INTEGER_LITERAL);
		addEEnumLiteral(primitiveTypeEEnum, PrimitiveType.BIGINT_LITERAL);
		addEEnumLiteral(primitiveTypeEEnum, PrimitiveType.FLOAT_LITERAL);
		addEEnumLiteral(primitiveTypeEEnum, PrimitiveType.REAL_LITERAL);
		addEEnumLiteral(primitiveTypeEEnum, PrimitiveType.DOUBLE_PRECISION_LITERAL);
		addEEnumLiteral(primitiveTypeEEnum, PrimitiveType.BOOLEAN_LITERAL);
		addEEnumLiteral(primitiveTypeEEnum, PrimitiveType.DATE_LITERAL);
		addEEnumLiteral(primitiveTypeEEnum, PrimitiveType.TIME_LITERAL);
		addEEnumLiteral(primitiveTypeEEnum, PrimitiveType.TIMESTAMP_LITERAL);
		addEEnumLiteral(primitiveTypeEEnum, PrimitiveType.INTERVAL_LITERAL);
		addEEnumLiteral(primitiveTypeEEnum, PrimitiveType.DATALINK_LITERAL);
		addEEnumLiteral(primitiveTypeEEnum, PrimitiveType.XML_TYPE_LITERAL);

		initEEnum(linkControlOptionEEnum, LinkControlOption.class, "LinkControlOption"); //$NON-NLS-1$
		addEEnumLiteral(linkControlOptionEEnum, LinkControlOption.FILE_LINK_CONTROL_LITERAL);
		addEEnumLiteral(linkControlOptionEEnum, LinkControlOption.NO_FILE_LINK_CONTROL_LITERAL);

		initEEnum(integrityControlOptionEEnum, IntegrityControlOption.class, "IntegrityControlOption"); //$NON-NLS-1$
		addEEnumLiteral(integrityControlOptionEEnum, IntegrityControlOption.ALL_LITERAL);
		addEEnumLiteral(integrityControlOptionEEnum, IntegrityControlOption.SELECTIVE_LITERAL);
		addEEnumLiteral(integrityControlOptionEEnum, IntegrityControlOption.NONE_LITERAL);

		initEEnum(readPermissionOptionEEnum, ReadPermissionOption.class, "ReadPermissionOption"); //$NON-NLS-1$
		addEEnumLiteral(readPermissionOptionEEnum, ReadPermissionOption.FS_LITERAL);
		addEEnumLiteral(readPermissionOptionEEnum, ReadPermissionOption.DB_LITERAL);

		initEEnum(writePermissionOptionEEnum, WritePermissionOption.class, "WritePermissionOption"); //$NON-NLS-1$
		addEEnumLiteral(writePermissionOptionEEnum, WritePermissionOption.FS_LITERAL);
		addEEnumLiteral(writePermissionOptionEEnum, WritePermissionOption.ADMIN_LITERAL);
		addEEnumLiteral(writePermissionOptionEEnum, WritePermissionOption.BLOCKED_LITERAL);

		initEEnum(unlinkOptionEEnum, UnlinkOption.class, "UnlinkOption"); //$NON-NLS-1$
		addEEnumLiteral(unlinkOptionEEnum, UnlinkOption.RESTORE_LITERAL);
		addEEnumLiteral(unlinkOptionEEnum, UnlinkOption.DELETE_LITERAL);
		addEEnumLiteral(unlinkOptionEEnum, UnlinkOption.NONE_LITERAL);

		// Create resource
		createResource(eNS_URI);
	}

} //SQLDataTypesPackageImpl

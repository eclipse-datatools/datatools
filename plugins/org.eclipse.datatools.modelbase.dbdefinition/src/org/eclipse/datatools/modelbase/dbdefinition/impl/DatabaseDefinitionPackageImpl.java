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
package org.eclipse.datatools.modelbase.dbdefinition.impl;

import org.eclipse.datatools.modelbase.dbdefinition.CheckOption;
import org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionFactory;
import org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage;
import org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.DefaultValueType;
import org.eclipse.datatools.modelbase.dbdefinition.ExtendedDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.LanguageType;
import org.eclipse.datatools.modelbase.dbdefinition.LengthUnit;
import org.eclipse.datatools.modelbase.dbdefinition.NicknameDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.ParameterStyle;
import org.eclipse.datatools.modelbase.dbdefinition.ParentDeleteDRIRuleType;
import org.eclipse.datatools.modelbase.dbdefinition.ParentUpdateDRIRuleType;
import org.eclipse.datatools.modelbase.dbdefinition.PercentFreeTerminology;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.ProcedureType;
import org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.SQLSyntaxDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.TableDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.TableSpaceType;
import org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.UserDefinedTypeDefinition;
import org.eclipse.datatools.modelbase.sql.accesscontrol.impl.SQLAccessControlPackageImpl;
import org.eclipse.datatools.modelbase.sql.constraints.impl.SQLConstraintsPackageImpl;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;
import org.eclipse.datatools.modelbase.sql.datatypes.impl.SQLDataTypesPackageImpl;
import org.eclipse.datatools.modelbase.sql.expressions.impl.SQLExpressionsPackageImpl;
import org.eclipse.datatools.modelbase.sql.routines.impl.SQLRoutinesPackageImpl;
import org.eclipse.datatools.modelbase.sql.schema.impl.SQLSchemaPackageImpl;
import org.eclipse.datatools.modelbase.sql.statements.impl.SQLStatementsPackageImpl;
import org.eclipse.datatools.modelbase.sql.tables.impl.SQLTablesPackageImpl;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.impl.EcorePackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DatabaseDefinitionPackageImpl extends EPackageImpl implements DatabaseDefinitionPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass databaseVendorDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass predefinedDataTypeDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tableSpaceDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass storedProcedureDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass triggerDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass columnDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constraintDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass indexDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass extendedDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tableDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sequenceDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass userDefinedTypeDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass queryDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sqlSyntaxDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nicknameDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum checkOptionEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum defaultValueTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum languageTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum parameterStyleEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum parentDeleteDRIRuleTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum parentUpdateDRIRuleTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum procedureTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum tableSpaceTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum percentFreeTerminologyEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum lengthUnitEEnum = null;

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
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private DatabaseDefinitionPackageImpl() {
		super(eNS_URI, DatabaseDefinitionFactory.eINSTANCE);
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
	public static DatabaseDefinitionPackage init() {
		if (isInited) return (DatabaseDefinitionPackage)EPackage.Registry.INSTANCE.getEPackage(DatabaseDefinitionPackage.eNS_URI);

		// Obtain or create and register package
		DatabaseDefinitionPackageImpl theDatabaseDefinitionPackage = (DatabaseDefinitionPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof DatabaseDefinitionPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new DatabaseDefinitionPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		SQLSchemaPackageImpl.init();
		SQLConstraintsPackageImpl.init();
		SQLDataTypesPackageImpl.init();
		SQLExpressionsPackageImpl.init();
		SQLRoutinesPackageImpl.init();
		SQLStatementsPackageImpl.init();
		SQLTablesPackageImpl.init();
		SQLAccessControlPackageImpl.init();
		EcorePackageImpl.init();

		// Create package meta-data objects
		theDatabaseDefinitionPackage.createPackageContents();

		// Initialize created meta-data
		theDatabaseDefinitionPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theDatabaseDefinitionPackage.freeze();

		return theDatabaseDefinitionPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDatabaseVendorDefinition() {
		return databaseVendorDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDatabaseVendorDefinition_Vendor() {
		return (EAttribute)databaseVendorDefinitionEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDatabaseVendorDefinition_Version() {
		return (EAttribute)databaseVendorDefinitionEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDatabaseVendorDefinition_ConstraintsSupported() {
		return (EAttribute)databaseVendorDefinitionEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDatabaseVendorDefinition_MaximumIdentifierLength() {
		return (EAttribute)databaseVendorDefinitionEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDatabaseVendorDefinition_TriggerSupported() {
		return (EAttribute)databaseVendorDefinitionEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDatabaseVendorDefinition_SnapshotViewSupported() {
		return (EAttribute)databaseVendorDefinitionEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDatabaseVendorDefinition_JoinSupported() {
		return (EAttribute)databaseVendorDefinitionEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDatabaseVendorDefinition_ViewTriggerSupported() {
		return (EAttribute)databaseVendorDefinitionEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDatabaseVendorDefinition_TablespacesSupported() {
		return (EAttribute)databaseVendorDefinitionEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDatabaseVendorDefinition_MaximumCommentLength() {
		return (EAttribute)databaseVendorDefinitionEClass.getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDatabaseVendorDefinition_SequenceSupported() {
		return (EAttribute)databaseVendorDefinitionEClass.getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDatabaseVendorDefinition_MQTSupported() {
		return (EAttribute)databaseVendorDefinitionEClass.getEStructuralFeatures().get(25);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDatabaseVendorDefinition_SchemaSupported() {
		return (EAttribute)databaseVendorDefinitionEClass.getEStructuralFeatures().get(26);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDatabaseVendorDefinition_AliasSupported() {
		return (EAttribute)databaseVendorDefinitionEClass.getEStructuralFeatures().get(27);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDatabaseVendorDefinition_SynonymSupported() {
		return (EAttribute)databaseVendorDefinitionEClass.getEStructuralFeatures().get(28);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDatabaseVendorDefinition_UserDefinedTypeSupported() {
		return (EAttribute)databaseVendorDefinitionEClass.getEStructuralFeatures().get(29);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDatabaseVendorDefinition_DomainSupported() {
		return (EAttribute)databaseVendorDefinitionEClass.getEStructuralFeatures().get(30);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDatabaseVendorDefinition_SQLStatementSupported() {
		return (EAttribute)databaseVendorDefinitionEClass.getEStructuralFeatures().get(31);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDatabaseVendorDefinition_NicknameSupported() {
		return (EAttribute)databaseVendorDefinitionEClass.getEStructuralFeatures().get(32);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDatabaseVendorDefinition_QuotedDMLSupported() {
		return (EAttribute)databaseVendorDefinitionEClass.getEStructuralFeatures().get(33);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDatabaseVendorDefinition_QuotedDDLSupported() {
		return (EAttribute)databaseVendorDefinitionEClass.getEStructuralFeatures().get(34);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDatabaseVendorDefinition_PredefinedDataTypeDefinitions() {
		return (EReference)databaseVendorDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDatabaseVendorDefinition_TableSpaceDefinition() {
		return (EReference)databaseVendorDefinitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDatabaseVendorDefinition_StoredProcedureDefinition() {
		return (EReference)databaseVendorDefinitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDatabaseVendorDefinition_TriggerDefinition() {
		return (EReference)databaseVendorDefinitionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDatabaseVendorDefinition_ColumnDefinition() {
		return (EReference)databaseVendorDefinitionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDatabaseVendorDefinition_ConstraintDefinition() {
		return (EReference)databaseVendorDefinitionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDatabaseVendorDefinition_ExtendedDefinitions() {
		return (EReference)databaseVendorDefinitionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDatabaseVendorDefinition_IndexDefinition() {
		return (EReference)databaseVendorDefinitionEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDatabaseVendorDefinition_TableDefinition() {
		return (EReference)databaseVendorDefinitionEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDatabaseVendorDefinition_SequenceDefinition() {
		return (EReference)databaseVendorDefinitionEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDatabaseVendorDefinition_UdtDefinition() {
		return (EReference)databaseVendorDefinitionEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDatabaseVendorDefinition_QueryDefinition() {
		return (EReference)databaseVendorDefinitionEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDatabaseVendorDefinition_SQLSyntaxDefinition() {
		return (EReference)databaseVendorDefinitionEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDatabaseVendorDefinition_NicknameDefinition() {
		return (EReference)databaseVendorDefinitionEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPredefinedDataTypeDefinition() {
		return predefinedDataTypeDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_LengthSupported() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_ScaleSupported() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_PrecisionSupported() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_KeyConstraintSupported() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_IdentitySupported() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_MultipleColumnsSupported() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_NullableSupported() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_DefaultSupported() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_ClusteringSupported() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_FillFactorSupported() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_BitDataSupported() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_MaximumValue() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_MinimumValue() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_MaximumLength() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_MaximumPrecision() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_MaximumScale() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_MinimumScale() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_DefaultValueTypes() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_PrimitiveType() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_Name() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_JdbcEnumType() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_CharacterSet() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_EncodingScheme() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_CharacterSetSuffix() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_EncodingSchemeSuffix() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_JavaClassName() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(25);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_DefaultLength() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(26);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_DefaultPrecision() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(27);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_DefaultScale() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(28);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_CutoffPrecision() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(29);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_LengthUnit() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(30);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_TrailingPrecision() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(31);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_DefaultTrailingPrecision() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(32);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_TrailingPrecisionSupported() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(33);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_LeadingPrecision() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(34);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_DefaultLeadingPrecision() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(35);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPredefinedDataTypeDefinition_LeadingPrecisionSupported() {
		return (EAttribute)predefinedDataTypeDefinitionEClass.getEStructuralFeatures().get(36);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTableSpaceDefinition() {
		return tableSpaceDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableSpaceDefinition_TypeSupported() {
		return (EAttribute)tableSpaceDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableSpaceDefinition_ExtentSizeSupported() {
		return (EAttribute)tableSpaceDefinitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableSpaceDefinition_PrefetchSizeSupported() {
		return (EAttribute)tableSpaceDefinitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableSpaceDefinition_ManagedBySupported() {
		return (EAttribute)tableSpaceDefinitionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableSpaceDefinition_PageSizeSupported() {
		return (EAttribute)tableSpaceDefinitionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableSpaceDefinition_BufferPoolSupported() {
		return (EAttribute)tableSpaceDefinitionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableSpaceDefinition_DefaultSupported() {
		return (EAttribute)tableSpaceDefinitionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableSpaceDefinition_ContainerMaximumSizeSupported() {
		return (EAttribute)tableSpaceDefinitionEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableSpaceDefinition_ContainerInitialSizeSupported() {
		return (EAttribute)tableSpaceDefinitionEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableSpaceDefinition_ContainerExtentSizeSupported() {
		return (EAttribute)tableSpaceDefinitionEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableSpaceDefinition_TableSpaceType() {
		return (EAttribute)tableSpaceDefinitionEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStoredProcedureDefinition() {
		return storedProcedureDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStoredProcedureDefinition_NullInputActionSupported() {
		return (EAttribute)storedProcedureDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStoredProcedureDefinition_PackageGenerationSupported() {
		return (EAttribute)storedProcedureDefinitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStoredProcedureDefinition_DetermininsticSupported() {
		return (EAttribute)storedProcedureDefinitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStoredProcedureDefinition_ReturnedNullSupported() {
		return (EAttribute)storedProcedureDefinitionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStoredProcedureDefinition_ReturnedTypeDeclarationConstraintSupported() {
		return (EAttribute)storedProcedureDefinitionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStoredProcedureDefinition_ParameterInitValueSupported() {
		return (EAttribute)storedProcedureDefinitionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStoredProcedureDefinition_ParameterStyleSupported() {
		return (EAttribute)storedProcedureDefinitionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStoredProcedureDefinition_ReturnTypeSupported() {
		return (EAttribute)storedProcedureDefinitionEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStoredProcedureDefinition_ParameterDeclarationConstraintSupported() {
		return (EAttribute)storedProcedureDefinitionEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStoredProcedureDefinition_MaximumActionBodyLength() {
		return (EAttribute)storedProcedureDefinitionEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStoredProcedureDefinition_ParameterStyle() {
		return (EAttribute)storedProcedureDefinitionEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStoredProcedureDefinition_LanguageType() {
		return (EAttribute)storedProcedureDefinitionEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStoredProcedureDefinition_FunctionLanguageType() {
		return (EAttribute)storedProcedureDefinitionEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStoredProcedureDefinition_ProcedureType() {
		return (EAttribute)storedProcedureDefinitionEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTriggerDefinition() {
		return triggerDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTriggerDefinition_MaximumReferencePartLength() {
		return (EAttribute)triggerDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTriggerDefinition_MaximumActionBodyLength() {
		return (EAttribute)triggerDefinitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTriggerDefinition_TypeSupported() {
		return (EAttribute)triggerDefinitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTriggerDefinition_WhenClauseSupported() {
		return (EAttribute)triggerDefinitionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTriggerDefinition_GranularitySupported() {
		return (EAttribute)triggerDefinitionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTriggerDefinition_ReferencesClauseSupported() {
		return (EAttribute)triggerDefinitionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTriggerDefinition_PerColumnUpdateTriggerSupported() {
		return (EAttribute)triggerDefinitionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTriggerDefinition_InsteadOfTriggerSupported() {
		return (EAttribute)triggerDefinitionEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTriggerDefinition_RowTriggerReferenceSupported() {
		return (EAttribute)triggerDefinitionEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTriggerDefinition_TableTriggerReferenceSupported() {
		return (EAttribute)triggerDefinitionEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getColumnDefinition() {
		return columnDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getColumnDefinition_IdentitySupported() {
		return (EAttribute)columnDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getColumnDefinition_ComputedSupported() {
		return (EAttribute)columnDefinitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getColumnDefinition_IdentityStartValueSupported() {
		return (EAttribute)columnDefinitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getColumnDefinition_IdentityIncrementSupported() {
		return (EAttribute)columnDefinitionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getColumnDefinition_IdentityMinimumSupported() {
		return (EAttribute)columnDefinitionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getColumnDefinition_IdentityMaximumSupported() {
		return (EAttribute)columnDefinitionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getColumnDefinition_IdentityCycleSupported() {
		return (EAttribute)columnDefinitionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstraintDefinition() {
		return constraintDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraintDefinition_DeferrableConstraintSupported() {
		return (EAttribute)constraintDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraintDefinition_InformationalConstraintSupported() {
		return (EAttribute)constraintDefinitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraintDefinition_ClusteredPrimaryKeySupported() {
		return (EAttribute)constraintDefinitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraintDefinition_ClusteredUniqueConstraintSupported() {
		return (EAttribute)constraintDefinitionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraintDefinition_PrimaryKeyNullable() {
		return (EAttribute)constraintDefinitionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraintDefinition_UniqueKeyNullable() {
		return (EAttribute)constraintDefinitionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraintDefinition_MaximumCheckExpressionLength() {
		return (EAttribute)constraintDefinitionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraintDefinition_ParentUpdateDRIRuleType() {
		return (EAttribute)constraintDefinitionEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraintDefinition_ParentDeleteDRIRuleType() {
		return (EAttribute)constraintDefinitionEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstraintDefinition_CheckOption() {
		return (EAttribute)constraintDefinitionEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIndexDefinition() {
		return indexDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIndexDefinition_PercentFreeTerminology() {
		return (EAttribute)indexDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIndexDefinition_PercentFreeChangeable() {
		return (EAttribute)indexDefinitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIndexDefinition_ClusteringSupported() {
		return (EAttribute)indexDefinitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIndexDefinition_ClusterChangeable() {
		return (EAttribute)indexDefinitionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIndexDefinition_FillFactorSupported() {
		return (EAttribute)indexDefinitionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIndexDefinition_IncludedColumnsSupported() {
		return (EAttribute)indexDefinitionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExtendedDefinition() {
		return extendedDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedDefinition_Name() {
		return (EAttribute)extendedDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExtendedDefinition_Value() {
		return (EAttribute)extendedDefinitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTableDefinition() {
		return tableDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableDefinition_AuditSupported() {
		return (EAttribute)tableDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableDefinition_DataCaptureSupported() {
		return (EAttribute)tableDefinitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableDefinition_EditProcSupported() {
		return (EAttribute)tableDefinitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableDefinition_EncodingSupported() {
		return (EAttribute)tableDefinitionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableDefinition_ValidProcSupported() {
		return (EAttribute)tableDefinitionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSequenceDefinition() {
		return sequenceDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSequenceDefinition_PredefinedDataTypeDefinitions() {
		return (EReference)sequenceDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSequenceDefinition_TypeEnumerationSupported() {
		return (EAttribute)sequenceDefinitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSequenceDefinition_CacheSupported() {
		return (EAttribute)sequenceDefinitionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSequenceDefinition_OrderSupported() {
		return (EAttribute)sequenceDefinitionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSequenceDefinition_NoMaximumValueString() {
		return (EAttribute)sequenceDefinitionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSequenceDefinition_NoMinimumValueString() {
		return (EAttribute)sequenceDefinitionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSequenceDefinition_NoCacheString() {
		return (EAttribute)sequenceDefinitionEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSequenceDefinition_CacheDefaultValue() {
		return (EAttribute)sequenceDefinitionEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUserDefinedTypeDefinition() {
		return userDefinedTypeDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUserDefinedTypeDefinition_DefaultValueSupported() {
		return (EAttribute)userDefinedTypeDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUserDefinedTypeDefinition_DistinctTypeSupported() {
		return (EAttribute)userDefinedTypeDefinitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUserDefinedTypeDefinition_StructuredTypeSupported() {
		return (EAttribute)userDefinedTypeDefinitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getQueryDefinition() {
		return queryDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQueryDefinition_IdentifierQuoteString() {
		return (EAttribute)queryDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQueryDefinition_HostVariableMarker() {
		return (EAttribute)queryDefinitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQueryDefinition_HostVariableMarkerSupported() {
		return (EAttribute)queryDefinitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQueryDefinition_CastExpressionSupported() {
		return (EAttribute)queryDefinitionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQueryDefinition_DefaultKeywordForInsertValueSupported() {
		return (EAttribute)queryDefinitionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQueryDefinition_ExtendedGroupingSupported() {
		return (EAttribute)queryDefinitionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQueryDefinition_TableAliasInDeleteSupported() {
		return (EAttribute)queryDefinitionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSQLSyntaxDefinition() {
		return sqlSyntaxDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSQLSyntaxDefinition_Keywords() {
		return (EAttribute)sqlSyntaxDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSQLSyntaxDefinition_Operators() {
		return (EAttribute)sqlSyntaxDefinitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNicknameDefinition() {
		return nicknameDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNicknameDefinition_ConstraintSupported() {
		return (EAttribute)nicknameDefinitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNicknameDefinition_IndexSupported() {
		return (EAttribute)nicknameDefinitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSequenceDefinition_DefaultDataTypeDefinition() {
		return (EReference)sequenceDefinitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getCheckOption() {
		return checkOptionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDefaultValueType() {
		return defaultValueTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getLanguageType() {
		return languageTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getParameterStyle() {
		return parameterStyleEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getParentDeleteDRIRuleType() {
		return parentDeleteDRIRuleTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getParentUpdateDRIRuleType() {
		return parentUpdateDRIRuleTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getProcedureType() {
		return procedureTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTableSpaceType() {
		return tableSpaceTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPercentFreeTerminology() {
		return percentFreeTerminologyEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getLengthUnit() {
		return lengthUnitEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DatabaseDefinitionFactory getDatabaseDefinitionFactory() {
		return (DatabaseDefinitionFactory)getEFactoryInstance();
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
		databaseVendorDefinitionEClass = createEClass(DATABASE_VENDOR_DEFINITION);
		createEReference(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__PREDEFINED_DATA_TYPE_DEFINITIONS);
		createEReference(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__TABLE_SPACE_DEFINITION);
		createEReference(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__STORED_PROCEDURE_DEFINITION);
		createEReference(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__TRIGGER_DEFINITION);
		createEReference(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__COLUMN_DEFINITION);
		createEReference(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__CONSTRAINT_DEFINITION);
		createEReference(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__EXTENDED_DEFINITIONS);
		createEReference(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__INDEX_DEFINITION);
		createEReference(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__TABLE_DEFINITION);
		createEReference(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__SEQUENCE_DEFINITION);
		createEReference(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__UDT_DEFINITION);
		createEReference(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__QUERY_DEFINITION);
		createEReference(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__SQL_SYNTAX_DEFINITION);
		createEReference(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__NICKNAME_DEFINITION);
		createEAttribute(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__VENDOR);
		createEAttribute(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__VERSION);
		createEAttribute(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__CONSTRAINTS_SUPPORTED);
		createEAttribute(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH);
		createEAttribute(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__TRIGGER_SUPPORTED);
		createEAttribute(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__SNAPSHOT_VIEW_SUPPORTED);
		createEAttribute(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__JOIN_SUPPORTED);
		createEAttribute(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__VIEW_TRIGGER_SUPPORTED);
		createEAttribute(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__TABLESPACES_SUPPORTED);
		createEAttribute(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__MAXIMUM_COMMENT_LENGTH);
		createEAttribute(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__SEQUENCE_SUPPORTED);
		createEAttribute(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__MQT_SUPPORTED);
		createEAttribute(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__SCHEMA_SUPPORTED);
		createEAttribute(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__ALIAS_SUPPORTED);
		createEAttribute(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__SYNONYM_SUPPORTED);
		createEAttribute(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__USER_DEFINED_TYPE_SUPPORTED);
		createEAttribute(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__DOMAIN_SUPPORTED);
		createEAttribute(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__SQL_STATEMENT_SUPPORTED);
		createEAttribute(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__NICKNAME_SUPPORTED);
		createEAttribute(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__QUOTED_DML_SUPPORTED);
		createEAttribute(databaseVendorDefinitionEClass, DATABASE_VENDOR_DEFINITION__QUOTED_DDL_SUPPORTED);

		predefinedDataTypeDefinitionEClass = createEClass(PREDEFINED_DATA_TYPE_DEFINITION);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__LENGTH_SUPPORTED);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__SCALE_SUPPORTED);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__PRECISION_SUPPORTED);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__KEY_CONSTRAINT_SUPPORTED);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__IDENTITY_SUPPORTED);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__MULTIPLE_COLUMNS_SUPPORTED);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__NULLABLE_SUPPORTED);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_SUPPORTED);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__CLUSTERING_SUPPORTED);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__FILL_FACTOR_SUPPORTED);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__BIT_DATA_SUPPORTED);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__MAXIMUM_VALUE);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__MINIMUM_VALUE);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__MAXIMUM_LENGTH);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__MAXIMUM_PRECISION);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__MAXIMUM_SCALE);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__MINIMUM_SCALE);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_VALUE_TYPES);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__PRIMITIVE_TYPE);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__NAME);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__JDBC_ENUM_TYPE);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__CHARACTER_SET);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__ENCODING_SCHEME);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__CHARACTER_SET_SUFFIX);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__ENCODING_SCHEME_SUFFIX);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__JAVA_CLASS_NAME);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_LENGTH);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_PRECISION);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_SCALE);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__CUTOFF_PRECISION);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__LENGTH_UNIT);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__TRAILING_PRECISION);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_TRAILING_PRECISION);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__TRAILING_PRECISION_SUPPORTED);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__LEADING_PRECISION);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__DEFAULT_LEADING_PRECISION);
		createEAttribute(predefinedDataTypeDefinitionEClass, PREDEFINED_DATA_TYPE_DEFINITION__LEADING_PRECISION_SUPPORTED);

		tableSpaceDefinitionEClass = createEClass(TABLE_SPACE_DEFINITION);
		createEAttribute(tableSpaceDefinitionEClass, TABLE_SPACE_DEFINITION__TYPE_SUPPORTED);
		createEAttribute(tableSpaceDefinitionEClass, TABLE_SPACE_DEFINITION__EXTENT_SIZE_SUPPORTED);
		createEAttribute(tableSpaceDefinitionEClass, TABLE_SPACE_DEFINITION__PREFETCH_SIZE_SUPPORTED);
		createEAttribute(tableSpaceDefinitionEClass, TABLE_SPACE_DEFINITION__MANAGED_BY_SUPPORTED);
		createEAttribute(tableSpaceDefinitionEClass, TABLE_SPACE_DEFINITION__PAGE_SIZE_SUPPORTED);
		createEAttribute(tableSpaceDefinitionEClass, TABLE_SPACE_DEFINITION__BUFFER_POOL_SUPPORTED);
		createEAttribute(tableSpaceDefinitionEClass, TABLE_SPACE_DEFINITION__DEFAULT_SUPPORTED);
		createEAttribute(tableSpaceDefinitionEClass, TABLE_SPACE_DEFINITION__CONTAINER_MAXIMUM_SIZE_SUPPORTED);
		createEAttribute(tableSpaceDefinitionEClass, TABLE_SPACE_DEFINITION__CONTAINER_INITIAL_SIZE_SUPPORTED);
		createEAttribute(tableSpaceDefinitionEClass, TABLE_SPACE_DEFINITION__CONTAINER_EXTENT_SIZE_SUPPORTED);
		createEAttribute(tableSpaceDefinitionEClass, TABLE_SPACE_DEFINITION__TABLE_SPACE_TYPE);

		storedProcedureDefinitionEClass = createEClass(STORED_PROCEDURE_DEFINITION);
		createEAttribute(storedProcedureDefinitionEClass, STORED_PROCEDURE_DEFINITION__NULL_INPUT_ACTION_SUPPORTED);
		createEAttribute(storedProcedureDefinitionEClass, STORED_PROCEDURE_DEFINITION__PACKAGE_GENERATION_SUPPORTED);
		createEAttribute(storedProcedureDefinitionEClass, STORED_PROCEDURE_DEFINITION__DETERMININSTIC_SUPPORTED);
		createEAttribute(storedProcedureDefinitionEClass, STORED_PROCEDURE_DEFINITION__RETURNED_NULL_SUPPORTED);
		createEAttribute(storedProcedureDefinitionEClass, STORED_PROCEDURE_DEFINITION__RETURNED_TYPE_DECLARATION_CONSTRAINT_SUPPORTED);
		createEAttribute(storedProcedureDefinitionEClass, STORED_PROCEDURE_DEFINITION__PARAMETER_INIT_VALUE_SUPPORTED);
		createEAttribute(storedProcedureDefinitionEClass, STORED_PROCEDURE_DEFINITION__PARAMETER_STYLE_SUPPORTED);
		createEAttribute(storedProcedureDefinitionEClass, STORED_PROCEDURE_DEFINITION__RETURN_TYPE_SUPPORTED);
		createEAttribute(storedProcedureDefinitionEClass, STORED_PROCEDURE_DEFINITION__PARAMETER_DECLARATION_CONSTRAINT_SUPPORTED);
		createEAttribute(storedProcedureDefinitionEClass, STORED_PROCEDURE_DEFINITION__MAXIMUM_ACTION_BODY_LENGTH);
		createEAttribute(storedProcedureDefinitionEClass, STORED_PROCEDURE_DEFINITION__PARAMETER_STYLE);
		createEAttribute(storedProcedureDefinitionEClass, STORED_PROCEDURE_DEFINITION__LANGUAGE_TYPE);
		createEAttribute(storedProcedureDefinitionEClass, STORED_PROCEDURE_DEFINITION__FUNCTION_LANGUAGE_TYPE);
		createEAttribute(storedProcedureDefinitionEClass, STORED_PROCEDURE_DEFINITION__PROCEDURE_TYPE);

		triggerDefinitionEClass = createEClass(TRIGGER_DEFINITION);
		createEAttribute(triggerDefinitionEClass, TRIGGER_DEFINITION__MAXIMUM_REFERENCE_PART_LENGTH);
		createEAttribute(triggerDefinitionEClass, TRIGGER_DEFINITION__MAXIMUM_ACTION_BODY_LENGTH);
		createEAttribute(triggerDefinitionEClass, TRIGGER_DEFINITION__TYPE_SUPPORTED);
		createEAttribute(triggerDefinitionEClass, TRIGGER_DEFINITION__WHEN_CLAUSE_SUPPORTED);
		createEAttribute(triggerDefinitionEClass, TRIGGER_DEFINITION__GRANULARITY_SUPPORTED);
		createEAttribute(triggerDefinitionEClass, TRIGGER_DEFINITION__REFERENCES_CLAUSE_SUPPORTED);
		createEAttribute(triggerDefinitionEClass, TRIGGER_DEFINITION__PER_COLUMN_UPDATE_TRIGGER_SUPPORTED);
		createEAttribute(triggerDefinitionEClass, TRIGGER_DEFINITION__INSTEAD_OF_TRIGGER_SUPPORTED);
		createEAttribute(triggerDefinitionEClass, TRIGGER_DEFINITION__ROW_TRIGGER_REFERENCE_SUPPORTED);
		createEAttribute(triggerDefinitionEClass, TRIGGER_DEFINITION__TABLE_TRIGGER_REFERENCE_SUPPORTED);

		columnDefinitionEClass = createEClass(COLUMN_DEFINITION);
		createEAttribute(columnDefinitionEClass, COLUMN_DEFINITION__IDENTITY_SUPPORTED);
		createEAttribute(columnDefinitionEClass, COLUMN_DEFINITION__COMPUTED_SUPPORTED);
		createEAttribute(columnDefinitionEClass, COLUMN_DEFINITION__IDENTITY_START_VALUE_SUPPORTED);
		createEAttribute(columnDefinitionEClass, COLUMN_DEFINITION__IDENTITY_INCREMENT_SUPPORTED);
		createEAttribute(columnDefinitionEClass, COLUMN_DEFINITION__IDENTITY_MINIMUM_SUPPORTED);
		createEAttribute(columnDefinitionEClass, COLUMN_DEFINITION__IDENTITY_MAXIMUM_SUPPORTED);
		createEAttribute(columnDefinitionEClass, COLUMN_DEFINITION__IDENTITY_CYCLE_SUPPORTED);

		constraintDefinitionEClass = createEClass(CONSTRAINT_DEFINITION);
		createEAttribute(constraintDefinitionEClass, CONSTRAINT_DEFINITION__DEFERRABLE_CONSTRAINT_SUPPORTED);
		createEAttribute(constraintDefinitionEClass, CONSTRAINT_DEFINITION__INFORMATIONAL_CONSTRAINT_SUPPORTED);
		createEAttribute(constraintDefinitionEClass, CONSTRAINT_DEFINITION__CLUSTERED_PRIMARY_KEY_SUPPORTED);
		createEAttribute(constraintDefinitionEClass, CONSTRAINT_DEFINITION__CLUSTERED_UNIQUE_CONSTRAINT_SUPPORTED);
		createEAttribute(constraintDefinitionEClass, CONSTRAINT_DEFINITION__PRIMARY_KEY_NULLABLE);
		createEAttribute(constraintDefinitionEClass, CONSTRAINT_DEFINITION__UNIQUE_KEY_NULLABLE);
		createEAttribute(constraintDefinitionEClass, CONSTRAINT_DEFINITION__MAXIMUM_CHECK_EXPRESSION_LENGTH);
		createEAttribute(constraintDefinitionEClass, CONSTRAINT_DEFINITION__PARENT_UPDATE_DRI_RULE_TYPE);
		createEAttribute(constraintDefinitionEClass, CONSTRAINT_DEFINITION__PARENT_DELETE_DRI_RULE_TYPE);
		createEAttribute(constraintDefinitionEClass, CONSTRAINT_DEFINITION__CHECK_OPTION);

		indexDefinitionEClass = createEClass(INDEX_DEFINITION);
		createEAttribute(indexDefinitionEClass, INDEX_DEFINITION__PERCENT_FREE_TERMINOLOGY);
		createEAttribute(indexDefinitionEClass, INDEX_DEFINITION__PERCENT_FREE_CHANGEABLE);
		createEAttribute(indexDefinitionEClass, INDEX_DEFINITION__CLUSTERING_SUPPORTED);
		createEAttribute(indexDefinitionEClass, INDEX_DEFINITION__CLUSTER_CHANGEABLE);
		createEAttribute(indexDefinitionEClass, INDEX_DEFINITION__FILL_FACTOR_SUPPORTED);
		createEAttribute(indexDefinitionEClass, INDEX_DEFINITION__INCLUDED_COLUMNS_SUPPORTED);

		extendedDefinitionEClass = createEClass(EXTENDED_DEFINITION);
		createEAttribute(extendedDefinitionEClass, EXTENDED_DEFINITION__NAME);
		createEAttribute(extendedDefinitionEClass, EXTENDED_DEFINITION__VALUE);

		tableDefinitionEClass = createEClass(TABLE_DEFINITION);
		createEAttribute(tableDefinitionEClass, TABLE_DEFINITION__AUDIT_SUPPORTED);
		createEAttribute(tableDefinitionEClass, TABLE_DEFINITION__DATA_CAPTURE_SUPPORTED);
		createEAttribute(tableDefinitionEClass, TABLE_DEFINITION__EDIT_PROC_SUPPORTED);
		createEAttribute(tableDefinitionEClass, TABLE_DEFINITION__ENCODING_SUPPORTED);
		createEAttribute(tableDefinitionEClass, TABLE_DEFINITION__VALID_PROC_SUPPORTED);

		sequenceDefinitionEClass = createEClass(SEQUENCE_DEFINITION);
		createEReference(sequenceDefinitionEClass, SEQUENCE_DEFINITION__PREDEFINED_DATA_TYPE_DEFINITIONS);
		createEReference(sequenceDefinitionEClass, SEQUENCE_DEFINITION__DEFAULT_DATA_TYPE_DEFINITION);
		createEAttribute(sequenceDefinitionEClass, SEQUENCE_DEFINITION__TYPE_ENUMERATION_SUPPORTED);
		createEAttribute(sequenceDefinitionEClass, SEQUENCE_DEFINITION__CACHE_SUPPORTED);
		createEAttribute(sequenceDefinitionEClass, SEQUENCE_DEFINITION__ORDER_SUPPORTED);
		createEAttribute(sequenceDefinitionEClass, SEQUENCE_DEFINITION__NO_MAXIMUM_VALUE_STRING);
		createEAttribute(sequenceDefinitionEClass, SEQUENCE_DEFINITION__NO_MINIMUM_VALUE_STRING);
		createEAttribute(sequenceDefinitionEClass, SEQUENCE_DEFINITION__NO_CACHE_STRING);
		createEAttribute(sequenceDefinitionEClass, SEQUENCE_DEFINITION__CACHE_DEFAULT_VALUE);

		userDefinedTypeDefinitionEClass = createEClass(USER_DEFINED_TYPE_DEFINITION);
		createEAttribute(userDefinedTypeDefinitionEClass, USER_DEFINED_TYPE_DEFINITION__DEFAULT_VALUE_SUPPORTED);
		createEAttribute(userDefinedTypeDefinitionEClass, USER_DEFINED_TYPE_DEFINITION__DISTINCT_TYPE_SUPPORTED);
		createEAttribute(userDefinedTypeDefinitionEClass, USER_DEFINED_TYPE_DEFINITION__STRUCTURED_TYPE_SUPPORTED);

		queryDefinitionEClass = createEClass(QUERY_DEFINITION);
		createEAttribute(queryDefinitionEClass, QUERY_DEFINITION__IDENTIFIER_QUOTE_STRING);
		createEAttribute(queryDefinitionEClass, QUERY_DEFINITION__HOST_VARIABLE_MARKER);
		createEAttribute(queryDefinitionEClass, QUERY_DEFINITION__HOST_VARIABLE_MARKER_SUPPORTED);
		createEAttribute(queryDefinitionEClass, QUERY_DEFINITION__CAST_EXPRESSION_SUPPORTED);
		createEAttribute(queryDefinitionEClass, QUERY_DEFINITION__DEFAULT_KEYWORD_FOR_INSERT_VALUE_SUPPORTED);
		createEAttribute(queryDefinitionEClass, QUERY_DEFINITION__EXTENDED_GROUPING_SUPPORTED);
		createEAttribute(queryDefinitionEClass, QUERY_DEFINITION__TABLE_ALIAS_IN_DELETE_SUPPORTED);

		sqlSyntaxDefinitionEClass = createEClass(SQL_SYNTAX_DEFINITION);
		createEAttribute(sqlSyntaxDefinitionEClass, SQL_SYNTAX_DEFINITION__KEYWORDS);
		createEAttribute(sqlSyntaxDefinitionEClass, SQL_SYNTAX_DEFINITION__OPERATORS);

		nicknameDefinitionEClass = createEClass(NICKNAME_DEFINITION);
		createEAttribute(nicknameDefinitionEClass, NICKNAME_DEFINITION__CONSTRAINT_SUPPORTED);
		createEAttribute(nicknameDefinitionEClass, NICKNAME_DEFINITION__INDEX_SUPPORTED);

		// Create enums
		checkOptionEEnum = createEEnum(CHECK_OPTION);
		defaultValueTypeEEnum = createEEnum(DEFAULT_VALUE_TYPE);
		languageTypeEEnum = createEEnum(LANGUAGE_TYPE);
		parameterStyleEEnum = createEEnum(PARAMETER_STYLE);
		parentDeleteDRIRuleTypeEEnum = createEEnum(PARENT_DELETE_DRI_RULE_TYPE);
		parentUpdateDRIRuleTypeEEnum = createEEnum(PARENT_UPDATE_DRI_RULE_TYPE);
		procedureTypeEEnum = createEEnum(PROCEDURE_TYPE);
		tableSpaceTypeEEnum = createEEnum(TABLE_SPACE_TYPE);
		percentFreeTerminologyEEnum = createEEnum(PERCENT_FREE_TERMINOLOGY);
		lengthUnitEEnum = createEEnum(LENGTH_UNIT);
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
		SQLDataTypesPackageImpl theSQLDataTypesPackage = (SQLDataTypesPackageImpl)EPackage.Registry.INSTANCE.getEPackage(SQLDataTypesPackage.eNS_URI);

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(databaseVendorDefinitionEClass, DatabaseVendorDefinition.class, "DatabaseVendorDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getDatabaseVendorDefinition_PredefinedDataTypeDefinitions(), this.getPredefinedDataTypeDefinition(), null, "predefinedDataTypeDefinitions", null, 0, -1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDatabaseVendorDefinition_TableSpaceDefinition(), this.getTableSpaceDefinition(), null, "tableSpaceDefinition", null, 1, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDatabaseVendorDefinition_StoredProcedureDefinition(), this.getStoredProcedureDefinition(), null, "storedProcedureDefinition", null, 1, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDatabaseVendorDefinition_TriggerDefinition(), this.getTriggerDefinition(), null, "triggerDefinition", null, 1, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDatabaseVendorDefinition_ColumnDefinition(), this.getColumnDefinition(), null, "columnDefinition", null, 1, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDatabaseVendorDefinition_ConstraintDefinition(), this.getConstraintDefinition(), null, "constraintDefinition", null, 1, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDatabaseVendorDefinition_ExtendedDefinitions(), this.getExtendedDefinition(), null, "extendedDefinitions", null, 0, -1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDatabaseVendorDefinition_IndexDefinition(), this.getIndexDefinition(), null, "indexDefinition", null, 1, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDatabaseVendorDefinition_TableDefinition(), this.getTableDefinition(), null, "tableDefinition", null, 1, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDatabaseVendorDefinition_SequenceDefinition(), this.getSequenceDefinition(), null, "sequenceDefinition", null, 1, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDatabaseVendorDefinition_UdtDefinition(), this.getUserDefinedTypeDefinition(), null, "udtDefinition", null, 1, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDatabaseVendorDefinition_QueryDefinition(), this.getQueryDefinition(), null, "queryDefinition", null, 1, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDatabaseVendorDefinition_SQLSyntaxDefinition(), this.getSQLSyntaxDefinition(), null, "SQLSyntaxDefinition", null, 1, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getDatabaseVendorDefinition_NicknameDefinition(), this.getNicknameDefinition(), null, "nicknameDefinition", null, 1, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDatabaseVendorDefinition_Vendor(), ecorePackage.getEString(), "vendor", null, 0, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDatabaseVendorDefinition_Version(), ecorePackage.getEString(), "version", null, 0, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDatabaseVendorDefinition_ConstraintsSupported(), ecorePackage.getEBoolean(), "constraintsSupported", "true", 0, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getDatabaseVendorDefinition_MaximumIdentifierLength(), ecorePackage.getEInt(), "maximumIdentifierLength", null, 0, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDatabaseVendorDefinition_TriggerSupported(), ecorePackage.getEBoolean(), "triggerSupported", "true", 0, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getDatabaseVendorDefinition_SnapshotViewSupported(), ecorePackage.getEBoolean(), "snapshotViewSupported", null, 0, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDatabaseVendorDefinition_JoinSupported(), ecorePackage.getEBoolean(), "joinSupported", null, 0, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDatabaseVendorDefinition_ViewTriggerSupported(), ecorePackage.getEBoolean(), "viewTriggerSupported", null, 0, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDatabaseVendorDefinition_TablespacesSupported(), ecorePackage.getEBoolean(), "tablespacesSupported", "true", 0, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getDatabaseVendorDefinition_MaximumCommentLength(), ecorePackage.getEInt(), "maximumCommentLength", null, 0, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDatabaseVendorDefinition_SequenceSupported(), ecorePackage.getEBoolean(), "sequenceSupported", null, 0, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDatabaseVendorDefinition_MQTSupported(), ecorePackage.getEBoolean(), "mQTSupported", null, 0, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDatabaseVendorDefinition_SchemaSupported(), ecorePackage.getEBoolean(), "schemaSupported", "true", 0, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getDatabaseVendorDefinition_AliasSupported(), ecorePackage.getEBoolean(), "aliasSupported", null, 0, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDatabaseVendorDefinition_SynonymSupported(), ecorePackage.getEBoolean(), "synonymSupported", null, 0, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDatabaseVendorDefinition_UserDefinedTypeSupported(), ecorePackage.getEBoolean(), "userDefinedTypeSupported", null, 0, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDatabaseVendorDefinition_DomainSupported(), ecorePackage.getEBoolean(), "domainSupported", null, 0, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDatabaseVendorDefinition_SQLStatementSupported(), ecorePackage.getEBoolean(), "SQLStatementSupported", null, 0, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDatabaseVendorDefinition_NicknameSupported(), ecorePackage.getEBoolean(), "nicknameSupported", null, 0, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDatabaseVendorDefinition_QuotedDMLSupported(), ecorePackage.getEBoolean(), "quotedDMLSupported", "true", 0, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getDatabaseVendorDefinition_QuotedDDLSupported(), ecorePackage.getEBoolean(), "quotedDDLSupported", "true", 0, 1, DatabaseVendorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(predefinedDataTypeDefinitionEClass, PredefinedDataTypeDefinition.class, "PredefinedDataTypeDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_LengthSupported(), ecorePackage.getEBoolean(), "lengthSupported", null, 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_ScaleSupported(), ecorePackage.getEBoolean(), "scaleSupported", null, 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_PrecisionSupported(), ecorePackage.getEBoolean(), "precisionSupported", null, 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_KeyConstraintSupported(), ecorePackage.getEBoolean(), "keyConstraintSupported", null, 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_IdentitySupported(), ecorePackage.getEBoolean(), "identitySupported", null, 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_MultipleColumnsSupported(), ecorePackage.getEBoolean(), "multipleColumnsSupported", null, 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_NullableSupported(), ecorePackage.getEBoolean(), "nullableSupported", "true", 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getPredefinedDataTypeDefinition_DefaultSupported(), ecorePackage.getEBoolean(), "defaultSupported", "true", 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getPredefinedDataTypeDefinition_ClusteringSupported(), ecorePackage.getEBoolean(), "clusteringSupported", null, 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_FillFactorSupported(), ecorePackage.getEBoolean(), "fillFactorSupported", null, 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_BitDataSupported(), ecorePackage.getEBoolean(), "bitDataSupported", null, 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_MaximumValue(), ecorePackage.getELong(), "maximumValue", null, 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_MinimumValue(), ecorePackage.getELong(), "minimumValue", null, 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_MaximumLength(), ecorePackage.getEInt(), "maximumLength", null, 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_MaximumPrecision(), ecorePackage.getEInt(), "maximumPrecision", null, 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_MaximumScale(), ecorePackage.getEInt(), "maximumScale", null, 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_MinimumScale(), ecorePackage.getEInt(), "minimumScale", null, 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_DefaultValueTypes(), this.getDefaultValueType(), "defaultValueTypes", null, 0, -1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_PrimitiveType(), theSQLDataTypesPackage.getPrimitiveType(), "primitiveType", null, 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_Name(), ecorePackage.getEString(), "name", null, 0, -1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_JdbcEnumType(), ecorePackage.getEInt(), "jdbcEnumType", null, 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_CharacterSet(), ecorePackage.getEString(), "characterSet", null, 0, -1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_EncodingScheme(), ecorePackage.getEString(), "encodingScheme", null, 0, -1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_CharacterSetSuffix(), ecorePackage.getEString(), "characterSetSuffix", "", 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getPredefinedDataTypeDefinition_EncodingSchemeSuffix(), ecorePackage.getEString(), "encodingSchemeSuffix", "", 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getPredefinedDataTypeDefinition_JavaClassName(), ecorePackage.getEString(), "javaClassName", null, 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_DefaultLength(), ecorePackage.getEInt(), "defaultLength", null, 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_DefaultPrecision(), ecorePackage.getEInt(), "defaultPrecision", null, 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_DefaultScale(), ecorePackage.getEInt(), "defaultScale", null, 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_CutoffPrecision(), ecorePackage.getEInt(), "cutoffPrecision", null, 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_LengthUnit(), this.getLengthUnit(), "lengthUnit", null, 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_TrailingPrecision(), ecorePackage.getEInt(), "trailingPrecision", null, 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_DefaultTrailingPrecision(), ecorePackage.getEInt(), "defaultTrailingPrecision", null, 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_TrailingPrecisionSupported(), ecorePackage.getEBoolean(), "trailingPrecisionSupported", null, 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_LeadingPrecision(), ecorePackage.getEInt(), "leadingPrecision", null, 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_DefaultLeadingPrecision(), ecorePackage.getEInt(), "defaultLeadingPrecision", null, 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPredefinedDataTypeDefinition_LeadingPrecisionSupported(), ecorePackage.getEBoolean(), "leadingPrecisionSupported", null, 0, 1, PredefinedDataTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(tableSpaceDefinitionEClass, TableSpaceDefinition.class, "TableSpaceDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getTableSpaceDefinition_TypeSupported(), ecorePackage.getEBoolean(), "typeSupported", null, 0, 1, TableSpaceDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTableSpaceDefinition_ExtentSizeSupported(), ecorePackage.getEBoolean(), "extentSizeSupported", null, 0, 1, TableSpaceDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTableSpaceDefinition_PrefetchSizeSupported(), ecorePackage.getEBoolean(), "prefetchSizeSupported", null, 0, 1, TableSpaceDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTableSpaceDefinition_ManagedBySupported(), ecorePackage.getEBoolean(), "managedBySupported", null, 0, 1, TableSpaceDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTableSpaceDefinition_PageSizeSupported(), ecorePackage.getEBoolean(), "pageSizeSupported", null, 0, 1, TableSpaceDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTableSpaceDefinition_BufferPoolSupported(), ecorePackage.getEBoolean(), "bufferPoolSupported", null, 0, 1, TableSpaceDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTableSpaceDefinition_DefaultSupported(), ecorePackage.getEBoolean(), "defaultSupported", null, 0, 1, TableSpaceDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTableSpaceDefinition_ContainerMaximumSizeSupported(), ecorePackage.getEBoolean(), "containerMaximumSizeSupported", null, 0, 1, TableSpaceDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTableSpaceDefinition_ContainerInitialSizeSupported(), ecorePackage.getEBoolean(), "containerInitialSizeSupported", null, 0, 1, TableSpaceDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTableSpaceDefinition_ContainerExtentSizeSupported(), ecorePackage.getEBoolean(), "containerExtentSizeSupported", null, 0, 1, TableSpaceDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTableSpaceDefinition_TableSpaceType(), this.getTableSpaceType(), "tableSpaceType", null, 0, -1, TableSpaceDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(storedProcedureDefinitionEClass, StoredProcedureDefinition.class, "StoredProcedureDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getStoredProcedureDefinition_NullInputActionSupported(), ecorePackage.getEBoolean(), "nullInputActionSupported", null, 0, 1, StoredProcedureDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getStoredProcedureDefinition_PackageGenerationSupported(), ecorePackage.getEBoolean(), "packageGenerationSupported", null, 0, 1, StoredProcedureDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getStoredProcedureDefinition_DetermininsticSupported(), ecorePackage.getEBoolean(), "determininsticSupported", null, 0, 1, StoredProcedureDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getStoredProcedureDefinition_ReturnedNullSupported(), ecorePackage.getEBoolean(), "returnedNullSupported", null, 0, 1, StoredProcedureDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getStoredProcedureDefinition_ReturnedTypeDeclarationConstraintSupported(), ecorePackage.getEBoolean(), "returnedTypeDeclarationConstraintSupported", null, 0, 1, StoredProcedureDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getStoredProcedureDefinition_ParameterInitValueSupported(), ecorePackage.getEBoolean(), "parameterInitValueSupported", null, 0, 1, StoredProcedureDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getStoredProcedureDefinition_ParameterStyleSupported(), ecorePackage.getEBoolean(), "parameterStyleSupported", null, 0, 1, StoredProcedureDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getStoredProcedureDefinition_ReturnTypeSupported(), ecorePackage.getEBoolean(), "returnTypeSupported", null, 0, 1, StoredProcedureDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getStoredProcedureDefinition_ParameterDeclarationConstraintSupported(), ecorePackage.getEBoolean(), "parameterDeclarationConstraintSupported", null, 0, 1, StoredProcedureDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getStoredProcedureDefinition_MaximumActionBodyLength(), ecorePackage.getEInt(), "maximumActionBodyLength", null, 0, 1, StoredProcedureDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getStoredProcedureDefinition_ParameterStyle(), this.getParameterStyle(), "parameterStyle", null, 0, -1, StoredProcedureDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getStoredProcedureDefinition_LanguageType(), this.getLanguageType(), "languageType", null, 0, -1, StoredProcedureDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getStoredProcedureDefinition_FunctionLanguageType(), this.getLanguageType(), "functionLanguageType", null, 0, -1, StoredProcedureDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getStoredProcedureDefinition_ProcedureType(), this.getProcedureType(), "procedureType", null, 0, -1, StoredProcedureDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(triggerDefinitionEClass, TriggerDefinition.class, "TriggerDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getTriggerDefinition_MaximumReferencePartLength(), ecorePackage.getEInt(), "maximumReferencePartLength", null, 0, 1, TriggerDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTriggerDefinition_MaximumActionBodyLength(), ecorePackage.getEInt(), "maximumActionBodyLength", null, 0, 1, TriggerDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTriggerDefinition_TypeSupported(), ecorePackage.getEBoolean(), "typeSupported", null, 0, 1, TriggerDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTriggerDefinition_WhenClauseSupported(), ecorePackage.getEBoolean(), "whenClauseSupported", "true", 0, 1, TriggerDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getTriggerDefinition_GranularitySupported(), ecorePackage.getEBoolean(), "granularitySupported", null, 0, 1, TriggerDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTriggerDefinition_ReferencesClauseSupported(), ecorePackage.getEBoolean(), "referencesClauseSupported", null, 0, 1, TriggerDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTriggerDefinition_PerColumnUpdateTriggerSupported(), ecorePackage.getEBoolean(), "perColumnUpdateTriggerSupported", null, 0, 1, TriggerDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTriggerDefinition_InsteadOfTriggerSupported(), ecorePackage.getEBoolean(), "insteadOfTriggerSupported", null, 0, 1, TriggerDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTriggerDefinition_RowTriggerReferenceSupported(), ecorePackage.getEBoolean(), "rowTriggerReferenceSupported", null, 0, 1, TriggerDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTriggerDefinition_TableTriggerReferenceSupported(), ecorePackage.getEBoolean(), "tableTriggerReferenceSupported", null, 0, 1, TriggerDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(columnDefinitionEClass, ColumnDefinition.class, "ColumnDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getColumnDefinition_IdentitySupported(), ecorePackage.getEBoolean(), "identitySupported", null, 0, 1, ColumnDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getColumnDefinition_ComputedSupported(), ecorePackage.getEBoolean(), "computedSupported", null, 0, 1, ColumnDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getColumnDefinition_IdentityStartValueSupported(), ecorePackage.getEBoolean(), "identityStartValueSupported", "false", 0, 1, ColumnDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getColumnDefinition_IdentityIncrementSupported(), ecorePackage.getEBoolean(), "identityIncrementSupported", "false", 0, 1, ColumnDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getColumnDefinition_IdentityMinimumSupported(), ecorePackage.getEBoolean(), "identityMinimumSupported", "false", 0, 1, ColumnDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getColumnDefinition_IdentityMaximumSupported(), ecorePackage.getEBoolean(), "identityMaximumSupported", "false", 0, 1, ColumnDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getColumnDefinition_IdentityCycleSupported(), ecorePackage.getEBoolean(), "identityCycleSupported", "false", 0, 1, ColumnDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(constraintDefinitionEClass, ConstraintDefinition.class, "ConstraintDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getConstraintDefinition_DeferrableConstraintSupported(), ecorePackage.getEBoolean(), "deferrableConstraintSupported", null, 0, 1, ConstraintDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getConstraintDefinition_InformationalConstraintSupported(), ecorePackage.getEBoolean(), "informationalConstraintSupported", null, 0, 1, ConstraintDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getConstraintDefinition_ClusteredPrimaryKeySupported(), ecorePackage.getEBoolean(), "clusteredPrimaryKeySupported", null, 0, 1, ConstraintDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getConstraintDefinition_ClusteredUniqueConstraintSupported(), ecorePackage.getEBoolean(), "clusteredUniqueConstraintSupported", null, 0, 1, ConstraintDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getConstraintDefinition_PrimaryKeyNullable(), ecorePackage.getEBoolean(), "primaryKeyNullable", null, 0, 1, ConstraintDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getConstraintDefinition_UniqueKeyNullable(), ecorePackage.getEBoolean(), "uniqueKeyNullable", null, 0, 1, ConstraintDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getConstraintDefinition_MaximumCheckExpressionLength(), ecorePackage.getEInt(), "maximumCheckExpressionLength", null, 0, 1, ConstraintDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getConstraintDefinition_ParentUpdateDRIRuleType(), this.getParentUpdateDRIRuleType(), "parentUpdateDRIRuleType", null, 0, -1, ConstraintDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getConstraintDefinition_ParentDeleteDRIRuleType(), this.getParentDeleteDRIRuleType(), "parentDeleteDRIRuleType", null, 0, -1, ConstraintDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getConstraintDefinition_CheckOption(), this.getCheckOption(), "checkOption", null, 0, -1, ConstraintDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(indexDefinitionEClass, IndexDefinition.class, "IndexDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getIndexDefinition_PercentFreeTerminology(), this.getPercentFreeTerminology(), "percentFreeTerminology", null, 0, 1, IndexDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getIndexDefinition_PercentFreeChangeable(), ecorePackage.getEBoolean(), "percentFreeChangeable", null, 0, 1, IndexDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getIndexDefinition_ClusteringSupported(), ecorePackage.getEBoolean(), "clusteringSupported", null, 0, 1, IndexDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getIndexDefinition_ClusterChangeable(), ecorePackage.getEBoolean(), "clusterChangeable", null, 0, 1, IndexDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getIndexDefinition_FillFactorSupported(), ecorePackage.getEBoolean(), "fillFactorSupported", null, 0, 1, IndexDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getIndexDefinition_IncludedColumnsSupported(), ecorePackage.getEBoolean(), "includedColumnsSupported", "false", 0, 1, IndexDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(extendedDefinitionEClass, ExtendedDefinition.class, "ExtendedDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getExtendedDefinition_Name(), ecorePackage.getEString(), "name", null, 0, 1, ExtendedDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getExtendedDefinition_Value(), ecorePackage.getEString(), "value", null, 0, 1, ExtendedDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(tableDefinitionEClass, TableDefinition.class, "TableDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getTableDefinition_AuditSupported(), ecorePackage.getEBoolean(), "auditSupported", null, 0, 1, TableDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTableDefinition_DataCaptureSupported(), ecorePackage.getEBoolean(), "dataCaptureSupported", null, 0, 1, TableDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTableDefinition_EditProcSupported(), ecorePackage.getEBoolean(), "editProcSupported", null, 0, 1, TableDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTableDefinition_EncodingSupported(), ecorePackage.getEBoolean(), "encodingSupported", null, 0, 1, TableDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTableDefinition_ValidProcSupported(), ecorePackage.getEBoolean(), "validProcSupported", null, 0, 1, TableDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(sequenceDefinitionEClass, SequenceDefinition.class, "SequenceDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSequenceDefinition_PredefinedDataTypeDefinitions(), this.getPredefinedDataTypeDefinition(), null, "predefinedDataTypeDefinitions", null, 1, -1, SequenceDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSequenceDefinition_DefaultDataTypeDefinition(), this.getPredefinedDataTypeDefinition(), null, "defaultDataTypeDefinition", null, 1, 1, SequenceDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSequenceDefinition_TypeEnumerationSupported(), ecorePackage.getEBoolean(), "typeEnumerationSupported", null, 0, 1, SequenceDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSequenceDefinition_CacheSupported(), ecorePackage.getEBoolean(), "cacheSupported", null, 0, 1, SequenceDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSequenceDefinition_OrderSupported(), ecorePackage.getEBoolean(), "orderSupported", null, 0, 1, SequenceDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSequenceDefinition_NoMaximumValueString(), ecorePackage.getEString(), "noMaximumValueString", null, 0, 1, SequenceDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSequenceDefinition_NoMinimumValueString(), ecorePackage.getEString(), "noMinimumValueString", null, 0, 1, SequenceDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSequenceDefinition_NoCacheString(), ecorePackage.getEString(), "noCacheString", null, 0, 1, SequenceDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSequenceDefinition_CacheDefaultValue(), ecorePackage.getEInt(), "cacheDefaultValue", null, 0, 1, SequenceDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(userDefinedTypeDefinitionEClass, UserDefinedTypeDefinition.class, "UserDefinedTypeDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getUserDefinedTypeDefinition_DefaultValueSupported(), ecorePackage.getEBoolean(), "defaultValueSupported", null, 0, 1, UserDefinedTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getUserDefinedTypeDefinition_DistinctTypeSupported(), ecorePackage.getEBoolean(), "distinctTypeSupported", null, 0, 1, UserDefinedTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getUserDefinedTypeDefinition_StructuredTypeSupported(), ecorePackage.getEBoolean(), "structuredTypeSupported", null, 0, 1, UserDefinedTypeDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(queryDefinitionEClass, QueryDefinition.class, "QueryDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getQueryDefinition_IdentifierQuoteString(), ecorePackage.getEString(), "identifierQuoteString", "\"", 0, 1, QueryDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		initEAttribute(getQueryDefinition_HostVariableMarker(), ecorePackage.getEString(), "hostVariableMarker", ":", 0, 1, QueryDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getQueryDefinition_HostVariableMarkerSupported(), ecorePackage.getEBoolean(), "hostVariableMarkerSupported", "false", 0, 1, QueryDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getQueryDefinition_CastExpressionSupported(), ecorePackage.getEBoolean(), "castExpressionSupported", "false", 0, 1, QueryDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getQueryDefinition_DefaultKeywordForInsertValueSupported(), ecorePackage.getEBoolean(), "defaultKeywordForInsertValueSupported", "false", 0, 1, QueryDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getQueryDefinition_ExtendedGroupingSupported(), ecorePackage.getEBoolean(), "extendedGroupingSupported", "false", 0, 1, QueryDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getQueryDefinition_TableAliasInDeleteSupported(), ecorePackage.getEBoolean(), "tableAliasInDeleteSupported", "false", 0, 1, QueryDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(sqlSyntaxDefinitionEClass, SQLSyntaxDefinition.class, "SQLSyntaxDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getSQLSyntaxDefinition_Keywords(), ecorePackage.getEString(), "keywords", null, 0, -1, SQLSyntaxDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSQLSyntaxDefinition_Operators(), ecorePackage.getEString(), "operators", null, 0, -1, SQLSyntaxDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(nicknameDefinitionEClass, NicknameDefinition.class, "NicknameDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getNicknameDefinition_ConstraintSupported(), ecorePackage.getEBoolean(), "constraintSupported", null, 0, 1, NicknameDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getNicknameDefinition_IndexSupported(), ecorePackage.getEBoolean(), "indexSupported", null, 0, 1, NicknameDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		// Initialize enums and add enum literals
		initEEnum(checkOptionEEnum, CheckOption.class, "CheckOption"); //$NON-NLS-1$
		addEEnumLiteral(checkOptionEEnum, CheckOption.NONE_LITERAL);
		addEEnumLiteral(checkOptionEEnum, CheckOption.CASCADE_LITERAL);
		addEEnumLiteral(checkOptionEEnum, CheckOption.LOCAL_LITERAL);

		initEEnum(defaultValueTypeEEnum, DefaultValueType.class, "DefaultValueType"); //$NON-NLS-1$
		addEEnumLiteral(defaultValueTypeEEnum, DefaultValueType.CURRENT_DATE_LITERAL);
		addEEnumLiteral(defaultValueTypeEEnum, DefaultValueType.CURRENT_ID_LITERAL);
		addEEnumLiteral(defaultValueTypeEEnum, DefaultValueType.CURRENT_PATH_LITERAL);
		addEEnumLiteral(defaultValueTypeEEnum, DefaultValueType.CURRENT_TIME_STAMP_LITERAL);
		addEEnumLiteral(defaultValueTypeEEnum, DefaultValueType.CURRENT_TIME_LITERAL);
		addEEnumLiteral(defaultValueTypeEEnum, DefaultValueType.CURRENT_USER_LITERAL);
		addEEnumLiteral(defaultValueTypeEEnum, DefaultValueType.EXPRESSION_LITERAL);
		addEEnumLiteral(defaultValueTypeEEnum, DefaultValueType.LITERAL_LITERAL);
		addEEnumLiteral(defaultValueTypeEEnum, DefaultValueType.NO_DEFAULT_LITERAL);
		addEEnumLiteral(defaultValueTypeEEnum, DefaultValueType.NULL_LITERAL);

		initEEnum(languageTypeEEnum, LanguageType.class, "LanguageType"); //$NON-NLS-1$
		addEEnumLiteral(languageTypeEEnum, LanguageType.SQL_LITERAL);
		addEEnumLiteral(languageTypeEEnum, LanguageType.JAVA_LITERAL);
		addEEnumLiteral(languageTypeEEnum, LanguageType.C_LITERAL);
		addEEnumLiteral(languageTypeEEnum, LanguageType.OLE_LITERAL);
		addEEnumLiteral(languageTypeEEnum, LanguageType.ASSEMBLY_LITERAL);
		addEEnumLiteral(languageTypeEEnum, LanguageType.COBOL_LITERAL);
		addEEnumLiteral(languageTypeEEnum, LanguageType.PLI_LITERAL);
		addEEnumLiteral(languageTypeEEnum, LanguageType.CPLUSPLUS_LITERAL);
		addEEnumLiteral(languageTypeEEnum, LanguageType.CL_LITERAL);
		addEEnumLiteral(languageTypeEEnum, LanguageType.COBOLLE_LITERAL);
		addEEnumLiteral(languageTypeEEnum, LanguageType.FORTRAN_LITERAL);
		addEEnumLiteral(languageTypeEEnum, LanguageType.REXX_LITERAL);
		addEEnumLiteral(languageTypeEEnum, LanguageType.RPG_LITERAL);
		addEEnumLiteral(languageTypeEEnum, LanguageType.RPGLE_LITERAL);

		initEEnum(parameterStyleEEnum, ParameterStyle.class, "ParameterStyle"); //$NON-NLS-1$
		addEEnumLiteral(parameterStyleEEnum, ParameterStyle.DB2SQL_LITERAL);
		addEEnumLiteral(parameterStyleEEnum, ParameterStyle.GENERAL_LITERAL);
		addEEnumLiteral(parameterStyleEEnum, ParameterStyle.GENERAL_WITH_NULLS_LITERAL);
		addEEnumLiteral(parameterStyleEEnum, ParameterStyle.DB2GENRL_LITERAL);
		addEEnumLiteral(parameterStyleEEnum, ParameterStyle.DB2DARI_LITERAL);
		addEEnumLiteral(parameterStyleEEnum, ParameterStyle.JAVA_LITERAL);
		addEEnumLiteral(parameterStyleEEnum, ParameterStyle.SQL_LITERAL);

		initEEnum(parentDeleteDRIRuleTypeEEnum, ParentDeleteDRIRuleType.class, "ParentDeleteDRIRuleType"); //$NON-NLS-1$
		addEEnumLiteral(parentDeleteDRIRuleTypeEEnum, ParentDeleteDRIRuleType.NO_ACTION_LITERAL);
		addEEnumLiteral(parentDeleteDRIRuleTypeEEnum, ParentDeleteDRIRuleType.RESTRICT_LITERAL);
		addEEnumLiteral(parentDeleteDRIRuleTypeEEnum, ParentDeleteDRIRuleType.CASCADE_LITERAL);
		addEEnumLiteral(parentDeleteDRIRuleTypeEEnum, ParentDeleteDRIRuleType.SET_NULL_LITERAL);
		addEEnumLiteral(parentDeleteDRIRuleTypeEEnum, ParentDeleteDRIRuleType.SET_DEFAULT_LITERAL);

		initEEnum(parentUpdateDRIRuleTypeEEnum, ParentUpdateDRIRuleType.class, "ParentUpdateDRIRuleType"); //$NON-NLS-1$
		addEEnumLiteral(parentUpdateDRIRuleTypeEEnum, ParentUpdateDRIRuleType.NO_ACTION_LITERAL);
		addEEnumLiteral(parentUpdateDRIRuleTypeEEnum, ParentUpdateDRIRuleType.RESTRICT_LITERAL);
		addEEnumLiteral(parentUpdateDRIRuleTypeEEnum, ParentUpdateDRIRuleType.CASCADE_LITERAL);
		addEEnumLiteral(parentUpdateDRIRuleTypeEEnum, ParentUpdateDRIRuleType.SET_NULL_LITERAL);
		addEEnumLiteral(parentUpdateDRIRuleTypeEEnum, ParentUpdateDRIRuleType.SET_DEFAULT_LITERAL);

		initEEnum(procedureTypeEEnum, ProcedureType.class, "ProcedureType"); //$NON-NLS-1$
		addEEnumLiteral(procedureTypeEEnum, ProcedureType.PROCEDURE_LITERAL);
		addEEnumLiteral(procedureTypeEEnum, ProcedureType.FUNCTION_LITERAL);

		initEEnum(tableSpaceTypeEEnum, TableSpaceType.class, "TableSpaceType"); //$NON-NLS-1$
		addEEnumLiteral(tableSpaceTypeEEnum, TableSpaceType.REGULAR_LITERAL);
		addEEnumLiteral(tableSpaceTypeEEnum, TableSpaceType.LOB_LITERAL);
		addEEnumLiteral(tableSpaceTypeEEnum, TableSpaceType.SYSTEM_TEMPORARY_LITERAL);
		addEEnumLiteral(tableSpaceTypeEEnum, TableSpaceType.USER_TEMPORARY_LITERAL);
		addEEnumLiteral(tableSpaceTypeEEnum, TableSpaceType.PERMANENT_LITERAL);
		addEEnumLiteral(tableSpaceTypeEEnum, TableSpaceType.TEMPORARY_LITERAL);
		addEEnumLiteral(tableSpaceTypeEEnum, TableSpaceType.LONG_LITERAL);
		addEEnumLiteral(tableSpaceTypeEEnum, TableSpaceType.LARGE_LITERAL);

		initEEnum(percentFreeTerminologyEEnum, PercentFreeTerminology.class, "PercentFreeTerminology"); //$NON-NLS-1$
		addEEnumLiteral(percentFreeTerminologyEEnum, PercentFreeTerminology.PERCENT_FREE_LITERAL);
		addEEnumLiteral(percentFreeTerminologyEEnum, PercentFreeTerminology.FILL_FACTOR_LITERAL);
		addEEnumLiteral(percentFreeTerminologyEEnum, PercentFreeTerminology.THRESHOLD_LITERAL);

		initEEnum(lengthUnitEEnum, LengthUnit.class, "LengthUnit"); //$NON-NLS-1$
		addEEnumLiteral(lengthUnitEEnum, LengthUnit.DECIMAL_LITERAL);
		addEEnumLiteral(lengthUnitEEnum, LengthUnit.BIT_LITERAL);
		addEEnumLiteral(lengthUnitEEnum, LengthUnit.BYTE_LITERAL);
		addEEnumLiteral(lengthUnitEEnum, LengthUnit.DOUBLE_BYTE_LITERAL);

		// Create resource
		createResource(eNS_URI);
	}

} //DatabaseDefinitionPackageImpl

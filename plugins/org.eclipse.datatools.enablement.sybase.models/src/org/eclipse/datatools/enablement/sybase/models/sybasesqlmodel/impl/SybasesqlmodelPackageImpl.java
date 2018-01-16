/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybasesqlmodelPackageImpl.java,v 1.1 2008/04/28 17:10:56 bfitzpatrick Exp $
 */
package org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.JDBCParameterType;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizationIdentifier;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizedObject;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseBaseTable;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseIndexMember;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasePrivilege;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseRoutine;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseViewTable;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelFactory;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage;

import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;

import org.eclipse.datatools.modelbase.sql.constraints.SQLConstraintsPackage;

import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataTypesPackage;

import org.eclipse.datatools.modelbase.sql.expressions.SQLExpressionsPackage;

import org.eclipse.datatools.modelbase.sql.routines.SQLRoutinesPackage;

import org.eclipse.datatools.modelbase.sql.schema.SQLSchemaPackage;

import org.eclipse.datatools.modelbase.sql.statements.SQLStatementsPackage;

import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SybasesqlmodelPackageImpl extends EPackageImpl implements SybasesqlmodelPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sybaseParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sybaseRoutineEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sybaseBaseTableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sybaseViewTableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sybaseAuthorizationIdentifierEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sybaseIndexMemberEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sybaseAuthorizedObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sybasePrivilegeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum jdbcParameterTypeEEnum = null;

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
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private SybasesqlmodelPackageImpl() {
		super(eNS_URI, SybasesqlmodelFactory.eINSTANCE);
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
	public static SybasesqlmodelPackage init() {
		if (isInited) return (SybasesqlmodelPackage)EPackage.Registry.INSTANCE.getEPackage(SybasesqlmodelPackage.eNS_URI);

		// Obtain or create and register package
		SybasesqlmodelPackageImpl theSybasesqlmodelPackage = (SybasesqlmodelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof SybasesqlmodelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new SybasesqlmodelPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		SQLSchemaPackage.eINSTANCE.eClass();
		SQLConstraintsPackage.eINSTANCE.eClass();
		SQLDataTypesPackage.eINSTANCE.eClass();
		SQLExpressionsPackage.eINSTANCE.eClass();
		SQLRoutinesPackage.eINSTANCE.eClass();
		SQLStatementsPackage.eINSTANCE.eClass();
		SQLTablesPackage.eINSTANCE.eClass();
		SQLAccessControlPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theSybasesqlmodelPackage.createPackageContents();

		// Initialize created meta-data
		theSybasesqlmodelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theSybasesqlmodelPackage.freeze();

		return theSybasesqlmodelPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSybaseParameter() {
		return sybaseParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSybaseParameter_Nullable() {
		return (EAttribute)sybaseParameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSybaseParameter_DefaultValue() {
		return (EAttribute)sybaseParameterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSybaseParameter_JDBCParameterType() {
		return (EAttribute)sybaseParameterEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSybaseRoutine() {
		return sybaseRoutineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSybaseBaseTable() {
		return sybaseBaseTableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSybaseViewTable() {
		return sybaseViewTableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSybaseAuthorizationIdentifier() {
		return sybaseAuthorizationIdentifierEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSybaseAuthorizationIdentifier_SqlContainer() {
		return (EReference)sybaseAuthorizationIdentifierEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSybaseIndexMember() {
		return sybaseIndexMemberEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSybaseIndexMember_ColumnExpression() {
		return (EAttribute)sybaseIndexMemberEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSybaseAuthorizedObject() {
		return sybaseAuthorizedObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSybasePrivilege() {
		return sybasePrivilegeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSybasePrivilege_Revoked() {
		return (EAttribute)sybasePrivilegeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getJDBCParameterType() {
		return jdbcParameterTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SybasesqlmodelFactory getSybasesqlmodelFactory() {
		return (SybasesqlmodelFactory)getEFactoryInstance();
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
		sybaseParameterEClass = createEClass(SYBASE_PARAMETER);
		createEAttribute(sybaseParameterEClass, SYBASE_PARAMETER__NULLABLE);
		createEAttribute(sybaseParameterEClass, SYBASE_PARAMETER__DEFAULT_VALUE);
		createEAttribute(sybaseParameterEClass, SYBASE_PARAMETER__JDBC_PARAMETER_TYPE);

		sybaseRoutineEClass = createEClass(SYBASE_ROUTINE);

		sybaseBaseTableEClass = createEClass(SYBASE_BASE_TABLE);

		sybaseViewTableEClass = createEClass(SYBASE_VIEW_TABLE);

		sybaseAuthorizationIdentifierEClass = createEClass(SYBASE_AUTHORIZATION_IDENTIFIER);
		createEReference(sybaseAuthorizationIdentifierEClass, SYBASE_AUTHORIZATION_IDENTIFIER__SQL_CONTAINER);

		sybaseIndexMemberEClass = createEClass(SYBASE_INDEX_MEMBER);
		createEAttribute(sybaseIndexMemberEClass, SYBASE_INDEX_MEMBER__COLUMN_EXPRESSION);

		sybaseAuthorizedObjectEClass = createEClass(SYBASE_AUTHORIZED_OBJECT);

		sybasePrivilegeEClass = createEClass(SYBASE_PRIVILEGE);
		createEAttribute(sybasePrivilegeEClass, SYBASE_PRIVILEGE__REVOKED);

		// Create enums
		jdbcParameterTypeEEnum = createEEnum(JDBC_PARAMETER_TYPE);
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
		SQLRoutinesPackage theSQLRoutinesPackage = (SQLRoutinesPackage)EPackage.Registry.INSTANCE.getEPackage(SQLRoutinesPackage.eNS_URI);
		SQLTablesPackage theSQLTablesPackage = (SQLTablesPackage)EPackage.Registry.INSTANCE.getEPackage(SQLTablesPackage.eNS_URI);
		SQLSchemaPackage theSQLSchemaPackage = (SQLSchemaPackage)EPackage.Registry.INSTANCE.getEPackage(SQLSchemaPackage.eNS_URI);
		SQLAccessControlPackage theSQLAccessControlPackage = (SQLAccessControlPackage)EPackage.Registry.INSTANCE.getEPackage(SQLAccessControlPackage.eNS_URI);
		SQLConstraintsPackage theSQLConstraintsPackage = (SQLConstraintsPackage)EPackage.Registry.INSTANCE.getEPackage(SQLConstraintsPackage.eNS_URI);
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Add supertypes to classes
		sybaseParameterEClass.getESuperTypes().add(theSQLRoutinesPackage.getParameter());
		sybaseRoutineEClass.getESuperTypes().add(theSQLRoutinesPackage.getRoutine());
		sybaseRoutineEClass.getESuperTypes().add(this.getSybaseAuthorizedObject());
		sybaseBaseTableEClass.getESuperTypes().add(theSQLTablesPackage.getBaseTable());
		sybaseBaseTableEClass.getESuperTypes().add(this.getSybaseAuthorizedObject());
		sybaseViewTableEClass.getESuperTypes().add(theSQLTablesPackage.getViewTable());
		sybaseViewTableEClass.getESuperTypes().add(this.getSybaseAuthorizedObject());
		sybaseAuthorizationIdentifierEClass.getESuperTypes().add(theSQLAccessControlPackage.getAuthorizationIdentifier());
		sybaseIndexMemberEClass.getESuperTypes().add(theSQLConstraintsPackage.getIndexMember());
		sybaseAuthorizedObjectEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		sybasePrivilegeEClass.getESuperTypes().add(theSQLAccessControlPackage.getPrivilege());

		// Initialize classes and features; add operations and parameters
		initEClass(sybaseParameterEClass, SybaseParameter.class, "SybaseParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSybaseParameter_Nullable(), ecorePackage.getEBoolean(), "nullable", "true", 0, 1, SybaseParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseParameter_DefaultValue(), ecorePackage.getEString(), "defaultValue", null, 0, 1, SybaseParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseParameter_JDBCParameterType(), this.getJDBCParameterType(), "JDBCParameterType", null, 0, 1, SybaseParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sybaseRoutineEClass, SybaseRoutine.class, "SybaseRoutine", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(sybaseRoutineEClass, null, "parseParameterDefaultValues");

		addEOperation(sybaseRoutineEClass, ecorePackage.getEBoolean(), "isSystem", 0, 1);

		initEClass(sybaseBaseTableEClass, SybaseBaseTable.class, "SybaseBaseTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(sybaseBaseTableEClass, ecorePackage.getEBoolean(), "isSystem", 0, 1);

		addEOperation(sybaseBaseTableEClass, theSQLSchemaPackage.getList(), "getCheckConstraints", 0, 1);

		initEClass(sybaseViewTableEClass, SybaseViewTable.class, "SybaseViewTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(sybaseViewTableEClass, ecorePackage.getEBoolean(), "isSystem", 0, 1);

		initEClass(sybaseAuthorizationIdentifierEClass, SybaseAuthorizationIdentifier.class, "SybaseAuthorizationIdentifier", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSybaseAuthorizationIdentifier_SqlContainer(), theSQLSchemaPackage.getSQLObject(), null, "sqlContainer", null, 1, 1, SybaseAuthorizationIdentifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sybaseIndexMemberEClass, SybaseIndexMember.class, "SybaseIndexMember", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSybaseIndexMember_ColumnExpression(), ecorePackage.getEString(), "columnExpression", "", 0, 1, SybaseIndexMember.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sybaseAuthorizedObjectEClass, SybaseAuthorizedObject.class, "SybaseAuthorizedObject", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(sybasePrivilegeEClass, SybasePrivilege.class, "SybasePrivilege", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSybasePrivilege_Revoked(), theEcorePackage.getEBoolean(), "revoked", null, 0, 1, SybasePrivilege.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(jdbcParameterTypeEEnum, JDBCParameterType.class, "JDBCParameterType");
		addEEnumLiteral(jdbcParameterTypeEEnum, JDBCParameterType.UNKNOWN_LITERAL);
		addEEnumLiteral(jdbcParameterTypeEEnum, JDBCParameterType.IN_LITERAL);
		addEEnumLiteral(jdbcParameterTypeEEnum, JDBCParameterType.IN_OUT_LITERAL);
		addEEnumLiteral(jdbcParameterTypeEEnum, JDBCParameterType.RESULT_LITERAL);
		addEEnumLiteral(jdbcParameterTypeEEnum, JDBCParameterType.OUT_LITERAL);
		addEEnumLiteral(jdbcParameterTypeEEnum, JDBCParameterType.RETURN_LITERAL);

		// Create resource
		createResource(eNS_URI);
	}

} //SybasesqlmodelPackageImpl

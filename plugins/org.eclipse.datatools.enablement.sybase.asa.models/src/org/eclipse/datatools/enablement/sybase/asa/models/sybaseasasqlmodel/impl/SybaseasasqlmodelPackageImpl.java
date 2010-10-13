/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseasasqlmodelPackageImpl.java,v 1.4 2008/03/27 07:35:08 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASADatabase;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASAForeignKey;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASAIndex;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASATable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseASATempTable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseasasqlmodelFactory;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseasasqlmodelPackage;
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
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SybaseasasqlmodelPackageImpl extends EPackageImpl implements SybaseasasqlmodelPackage 
{
    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sybaseASADatabaseEClass = null;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sybaseASATableEClass = null;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sybaseASAForeignKeyEClass = null;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sybaseASAIndexEClass = null;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sybaseASATempTableEClass = null;

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
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasasqlmodel.SybaseasasqlmodelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private SybaseasasqlmodelPackageImpl()
    {
		super(eNS_URI, SybaseasasqlmodelFactory.eINSTANCE);
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
	public static SybaseasasqlmodelPackage init()
    {
		if (isInited) return (SybaseasasqlmodelPackage)EPackage.Registry.INSTANCE.getEPackage(SybaseasasqlmodelPackage.eNS_URI);

		// Obtain or create and register package
		SybaseasasqlmodelPackageImpl theSybaseasasqlmodelPackage = (SybaseasasqlmodelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof SybaseasasqlmodelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new SybaseasasqlmodelPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		SybaseasabasesqlmodelPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theSybaseasasqlmodelPackage.createPackageContents();

		// Initialize created meta-data
		theSybaseasasqlmodelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theSybaseasasqlmodelPackage.freeze();

		return theSybaseasasqlmodelPackage;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSybaseASADatabase()
    {
		return sybaseASADatabaseEClass;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSybaseASADatabase_ASECompatible()
    {
		return (EAttribute)sybaseASADatabaseEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSybaseASATable()
    {
		return sybaseASATableEClass;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSybaseASATable_Pctfree()
    {
		return (EAttribute)sybaseASATableEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSybaseASAForeignKey()
    {
		return sybaseASAForeignKeyEClass;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSybaseASAForeignKey_CheckOnCommit()
    {
		return (EAttribute)sybaseASAForeignKeyEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSybaseASAForeignKey_Nullable()
    {
		return (EAttribute)sybaseASAForeignKeyEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSybaseASAIndex()
    {
		return sybaseASAIndexEClass;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSybaseASAIndex_Virtual()
    {
		return (EAttribute)sybaseASAIndexEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSybaseASATempTable()
    {
		return sybaseASATempTableEClass;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSybaseASATempTable_Pctfree()
    {
		return (EAttribute)sybaseASATempTableEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SybaseasasqlmodelFactory getSybaseasasqlmodelFactory()
    {
		return (SybaseasasqlmodelFactory)getEFactoryInstance();
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
	public void createPackageContents()
    {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		sybaseASADatabaseEClass = createEClass(SYBASE_ASA_DATABASE);
		createEAttribute(sybaseASADatabaseEClass, SYBASE_ASA_DATABASE__ASE_COMPATIBLE);

		sybaseASATableEClass = createEClass(SYBASE_ASA_TABLE);
		createEAttribute(sybaseASATableEClass, SYBASE_ASA_TABLE__PCTFREE);

		sybaseASAForeignKeyEClass = createEClass(SYBASE_ASA_FOREIGN_KEY);
		createEAttribute(sybaseASAForeignKeyEClass, SYBASE_ASA_FOREIGN_KEY__CHECK_ON_COMMIT);
		createEAttribute(sybaseASAForeignKeyEClass, SYBASE_ASA_FOREIGN_KEY__NULLABLE);

		sybaseASAIndexEClass = createEClass(SYBASE_ASA_INDEX);
		createEAttribute(sybaseASAIndexEClass, SYBASE_ASA_INDEX__VIRTUAL);

		sybaseASATempTableEClass = createEClass(SYBASE_ASA_TEMP_TABLE);
		createEAttribute(sybaseASATempTableEClass, SYBASE_ASA_TEMP_TABLE__PCTFREE);
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
	public void initializePackageContents()
    {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		SybaseasabasesqlmodelPackage theSybaseasabasesqlmodelPackage = (SybaseasabasesqlmodelPackage)EPackage.Registry.INSTANCE.getEPackage(SybaseasabasesqlmodelPackage.eNS_URI);
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Add supertypes to classes
		sybaseASADatabaseEClass.getESuperTypes().add(theSybaseasabasesqlmodelPackage.getSybaseASABaseDatabase());
		sybaseASATableEClass.getESuperTypes().add(theSybaseasabasesqlmodelPackage.getSybaseASABaseTable());
		sybaseASAForeignKeyEClass.getESuperTypes().add(theSybaseasabasesqlmodelPackage.getSybaseASABaseForeignKey());
		sybaseASAIndexEClass.getESuperTypes().add(theSybaseasabasesqlmodelPackage.getSybaseASABaseIndex());
		sybaseASATempTableEClass.getESuperTypes().add(theSybaseasabasesqlmodelPackage.getSybaseASABaseTempTable());

		// Initialize classes and features; add operations and parameters
		initEClass(sybaseASADatabaseEClass, SybaseASADatabase.class, "SybaseASADatabase", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSybaseASADatabase_ASECompatible(), ecorePackage.getEBoolean(), "ASECompatible", null, 0, 1, SybaseASADatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sybaseASATableEClass, SybaseASATable.class, "SybaseASATable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSybaseASATable_Pctfree(), ecorePackage.getEInt(), "pctfree", "-1", 0, 1, SybaseASATable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sybaseASAForeignKeyEClass, SybaseASAForeignKey.class, "SybaseASAForeignKey", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSybaseASAForeignKey_CheckOnCommit(), ecorePackage.getEBoolean(), "checkOnCommit", null, 0, 1, SybaseASAForeignKey.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseASAForeignKey_Nullable(), ecorePackage.getEBoolean(), "nullable", null, 0, 1, SybaseASAForeignKey.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sybaseASAIndexEClass, SybaseASAIndex.class, "SybaseASAIndex", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSybaseASAIndex_Virtual(), ecorePackage.getEBoolean(), "virtual", null, 0, 1, SybaseASAIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sybaseASATempTableEClass, SybaseASATempTable.class, "SybaseASATempTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSybaseASATempTable_Pctfree(), theEcorePackage.getEInt(), "pctfree", "-1", 0, 1, SybaseASATempTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //SybaseasasqlmodelPackageImpl

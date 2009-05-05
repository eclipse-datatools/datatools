/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseasabasesqlmodelPackageImpl.java,v 1.5 2009/04/24 07:03:20 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.AllowNullType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EncryptionInfo;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventCondition;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventLocationType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.IntervalUnitType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.JavaSupportType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.ParameterType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseActionTime;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumnCheckConstraint;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDBSpace;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseForeignKey;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseFunction;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseGroup;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseIndex;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseParameter;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABasePredefinedDataType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABasePrimaryKey;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseProcedure;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseProxyTable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseRemoteProcedure;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseSchema;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTempTable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTrigger;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUniqueConstraint;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUser;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUserDefinedType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseViewTable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASADefaultWrapper;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelFactory;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SystemDefinedDefaultType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.TransactionOption;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.TypeOfDefault;

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
import org.eclipse.emf.ecore.EOperation;
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
public class SybaseasabasesqlmodelPackageImpl extends EPackageImpl implements SybaseasabasesqlmodelPackage
{
    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASABaseEventEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASABaseDatabaseEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASAWebServiceEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass encryptionInfoEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASABaseUserDefinedTypeEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASABasePredefinedDataTypeEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASABaseTableEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASABaseColumnEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASABaseUniqueConstraintEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASABasePrimaryKeyEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASABaseForeignKeyEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASABaseIndexEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASABaseDBSpaceEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASABaseViewTableEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASABaseFunctionEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASABaseProcedureEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASABaseTempTableEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASABaseTriggerEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASABaseProxyTableEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASABaseColumnCheckConstraintEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass scheduleEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASABaseRemoteProcedureEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASABaseParameterEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASABaseGroupEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASABaseSchemaEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASABaseUserEClass = null;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sybaseASADefaultWrapperEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass eventConditionEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum transactionOptionEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum typeOfDefaultEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum sybaseASABaseActionTimeEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum eventTypeEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum javaSupportTypeEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum eventLocationTypeEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum intervalUnitTypeEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum systemDefinedDefaultTypeEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum allowNullTypeEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum parameterTypeEEnum = null;

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
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
    private SybaseasabasesqlmodelPackageImpl()
    {
		super(eNS_URI, SybaseasabasesqlmodelFactory.eINSTANCE);
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
    public static SybaseasabasesqlmodelPackage init()
    {
		if (isInited) return (SybaseasabasesqlmodelPackage)EPackage.Registry.INSTANCE.getEPackage(SybaseasabasesqlmodelPackage.eNS_URI);

		// Obtain or create and register package
		SybaseasabasesqlmodelPackageImpl theSybaseasabasesqlmodelPackage = (SybaseasabasesqlmodelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof SybaseasabasesqlmodelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new SybaseasabasesqlmodelPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		SybasesqlmodelPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theSybaseasabasesqlmodelPackage.createPackageContents();

		// Initialize created meta-data
		theSybaseasabasesqlmodelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theSybaseasabasesqlmodelPackage.freeze();

		return theSybaseasabasesqlmodelPackage;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASABaseEvent()
    {
		return sybaseASABaseEventEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseEvent_EventType()
    {
		return (EAttribute)sybaseASABaseEventEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASABaseEvent_EventCreator()
    {
		return (EReference)sybaseASABaseEventEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseEvent_Location()
    {
		return (EAttribute)sybaseASABaseEventEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASABaseEvent_Schedules()
    {
		return (EReference)sybaseASABaseEventEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASABaseEvent_ConditionDetails()
    {
		return (EReference)sybaseASABaseEventEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASABaseDatabase()
    {
		return sybaseASABaseDatabaseEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASABaseDatabase_DataTypes()
    {
		return (EReference)sybaseASABaseDatabaseEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASABaseDatabase_WebServices()
    {
		return (EReference)sybaseASABaseDatabaseEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASABaseDatabase_DbSpaces()
    {
		return (EReference)sybaseASABaseDatabaseEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseDatabase_DatabaseFileName()
    {
		return (EAttribute)sybaseASABaseDatabaseEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseDatabase_LogFileName()
    {
		return (EAttribute)sybaseASABaseDatabaseEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseDatabase_MirrorFileName()
    {
		return (EAttribute)sybaseASABaseDatabaseEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseDatabase_CaseSensitive()
    {
		return (EAttribute)sybaseASABaseDatabaseEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseDatabase_Collation()
    {
		return (EAttribute)sybaseASABaseDatabaseEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseDatabase_BlankPaddingOn()
    {
		return (EAttribute)sybaseASABaseDatabaseEClass.getEStructuralFeatures().get(8);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseDatabase_CheckSumOn()
    {
		return (EAttribute)sybaseASABaseDatabaseEClass.getEStructuralFeatures().get(9);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseDatabase_JConnectOn()
    {
		return (EAttribute)sybaseASABaseDatabaseEClass.getEStructuralFeatures().get(10);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseDatabase_PageSize()
    {
		return (EAttribute)sybaseASABaseDatabaseEClass.getEStructuralFeatures().get(11);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASABaseDatabase_EncryptionInfo()
    {
		return (EReference)sybaseASABaseDatabaseEClass.getEStructuralFeatures().get(12);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseDatabase_JavaSupport()
    {
		return (EAttribute)sybaseASABaseDatabaseEClass.getEStructuralFeatures().get(13);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseDatabase_PasswordCaseSensitive()
    {
		return (EAttribute)sybaseASABaseDatabaseEClass.getEStructuralFeatures().get(14);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASAWebService()
    {
		return sybaseASAWebServiceEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASAWebService_Service_id()
    {
		return (EAttribute)sybaseASAWebServiceEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASAWebService_Service_type()
    {
		return (EAttribute)sybaseASAWebServiceEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASAWebService_Auth_required()
    {
		return (EAttribute)sybaseASAWebServiceEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASAWebService_Secure_required()
    {
		return (EAttribute)sybaseASAWebServiceEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASAWebService_Url_path()
    {
		return (EAttribute)sybaseASAWebServiceEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASAWebService_User_name()
    {
		return (EAttribute)sybaseASAWebServiceEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASAWebService_Parameter()
    {
		return (EAttribute)sybaseASAWebServiceEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASAWebService_Statement()
    {
		return (EAttribute)sybaseASAWebServiceEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASAWebService_Database()
    {
		return (EReference)sybaseASAWebServiceEClass.getEStructuralFeatures().get(8);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getEncryptionInfo()
    {
		return encryptionInfoEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getEncryptionInfo_EncryptedTable()
    {
		return (EAttribute)encryptionInfoEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getEncryptionInfo_EncryptionKey()
    {
		return (EAttribute)encryptionInfoEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getEncryptionInfo_Algorithm()
    {
		return (EAttribute)encryptionInfoEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASABaseUserDefinedType()
    {
		return sybaseASABaseUserDefinedTypeEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseUserDefinedType_Nullable()
    {
		return (EAttribute)sybaseASABaseUserDefinedTypeEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseUserDefinedType_DefaultType()
    {
		return (EAttribute)sybaseASABaseUserDefinedTypeEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASABasePredefinedDataType()
    {
		return sybaseASABasePredefinedDataTypeEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASABasePredefinedDataType_Database()
    {
		return (EReference)sybaseASABasePredefinedDataTypeEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASABaseTable()
    {
		return sybaseASABaseTableEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASABaseTable_DbSpace()
    {
		return (EReference)sybaseASABaseTableEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASABaseColumn()
    {
		return sybaseASABaseColumnEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASABaseColumn_ColumnConstraint()
    {
		return (EReference)sybaseASABaseColumnEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseColumn_TypeOfDefault()
    {
		return (EAttribute)sybaseASABaseColumnEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseColumn_Unique()
    {
		return (EAttribute)sybaseASABaseColumnEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSybaseASABaseColumn_IsComputedColumn()
    {
		return (EAttribute)sybaseASABaseColumnEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASABaseUniqueConstraint()
    {
		return sybaseASABaseUniqueConstraintEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseUniqueConstraint_Clustered()
    {
		return (EAttribute)sybaseASABaseUniqueConstraintEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASABaseUniqueConstraint_SystemGenIndex()
    {
		return (EReference)sybaseASABaseUniqueConstraintEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASABasePrimaryKey()
    {
		return sybaseASABasePrimaryKeyEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASABaseForeignKey()
    {
		return sybaseASABaseForeignKeyEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseForeignKey_RoleName()
    {
		return (EAttribute)sybaseASABaseForeignKeyEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseForeignKey_Clustered()
    {
		return (EAttribute)sybaseASABaseForeignKeyEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASABaseIndex()
    {
		return sybaseASABaseIndexEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASABaseIndex_DbSpace()
    {
		return (EReference)sybaseASABaseIndexEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASABaseDBSpace()
    {
		return sybaseASABaseDBSpaceEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseDBSpace_FileName()
    {
		return (EAttribute)sybaseASABaseDBSpaceEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASABaseDBSpace_Database()
    {
		return (EReference)sybaseASABaseDBSpaceEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASABaseViewTable()
    {
		return sybaseASABaseViewTableEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseViewTable_WithCheckOption()
    {
		return (EAttribute)sybaseASABaseViewTableEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASABaseViewTable_Statement()
    {
		return (EReference)sybaseASABaseViewTableEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASABaseFunction()
    {
		return sybaseASABaseFunctionEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseFunction_OnExceptionResume()
    {
		return (EAttribute)sybaseASABaseFunctionEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASABaseProcedure()
    {
		return sybaseASABaseProcedureEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseProcedure_OnExceptionResume()
    {
		return (EAttribute)sybaseASABaseProcedureEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASABaseTempTable()
    {
		return sybaseASABaseTempTableEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseTempTable_TransactionOption()
    {
		return (EAttribute)sybaseASABaseTempTableEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASABaseTrigger()
    {
		return sybaseASABaseTriggerEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseTrigger_Order()
    {
		return (EAttribute)sybaseASABaseTriggerEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseTrigger_SybaseASABaseActionTime()
    {
		return (EAttribute)sybaseASABaseTriggerEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseTrigger_RemoteName()
    {
		return (EAttribute)sybaseASABaseTriggerEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseTrigger_UpdateColumnType()
    {
		return (EAttribute)sybaseASABaseTriggerEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASABaseProxyTable()
    {
		return sybaseASABaseProxyTableEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseProxyTable_RemoteObjectLocation()
    {
		return (EAttribute)sybaseASABaseProxyTableEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseProxyTable_Existing()
    {
		return (EAttribute)sybaseASABaseProxyTableEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASABaseColumnCheckConstraint()
    {
		return sybaseASABaseColumnCheckConstraintEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASABaseColumnCheckConstraint_Column()
    {
		return (EReference)sybaseASABaseColumnCheckConstraintEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSchedule()
    {
		return scheduleEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSchedule_Recurring()
    {
		return (EAttribute)scheduleEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSchedule_StartTime()
    {
		return (EAttribute)scheduleEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSchedule_StopTime()
    {
		return (EAttribute)scheduleEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSchedule_StartDate()
    {
		return (EAttribute)scheduleEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSchedule_DaysOfWeek()
    {
		return (EAttribute)scheduleEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSchedule_DaysOfMonth()
    {
		return (EAttribute)scheduleEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSchedule_IntervalUnit()
    {
		return (EAttribute)scheduleEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSchedule_IntervalMount()
    {
		return (EAttribute)scheduleEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSchedule_Event()
    {
		return (EReference)scheduleEClass.getEStructuralFeatures().get(8);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASABaseRemoteProcedure()
    {
		return sybaseASABaseRemoteProcedureEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseRemoteProcedure_Location()
    {
		return (EAttribute)sybaseASABaseRemoteProcedureEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASABaseParameter()
    {
		return sybaseASABaseParameterEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASABaseParameter_ParmType()
    {
		return (EAttribute)sybaseASABaseParameterEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASABaseGroup()
    {
		return sybaseASABaseGroupEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASABaseSchema()
    {
		return sybaseASABaseSchemaEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASABaseUser()
    {
		return sybaseASABaseUserEClass;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSybaseASADefaultWrapper()
    {
		return sybaseASADefaultWrapperEClass;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSybaseASADefaultWrapper_Value()
    {
		return (EAttribute)sybaseASADefaultWrapperEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSybaseASADefaultWrapper_IsLiteral()
    {
		return (EAttribute)sybaseASADefaultWrapperEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSybaseASADefaultWrapper_PartitionSize()
    {
		return (EAttribute)sybaseASADefaultWrapperEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSybaseASADefaultWrapper_Type()
    {
		return (EAttribute)sybaseASADefaultWrapperEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getEventCondition()
    {
		return eventConditionEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getEventCondition_Operator()
    {
		return (EAttribute)eventConditionEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getEventCondition_Value()
    {
		return (EAttribute)eventConditionEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getEventCondition_Event()
    {
		return (EReference)eventConditionEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getTransactionOption()
    {
		return transactionOptionEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getTypeOfDefault()
    {
		return typeOfDefaultEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getSybaseASABaseActionTime()
    {
		return sybaseASABaseActionTimeEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getEventType()
    {
		return eventTypeEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getJavaSupportType()
    {
		return javaSupportTypeEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getEventLocationType()
    {
		return eventLocationTypeEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getIntervalUnitType()
    {
		return intervalUnitTypeEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getSystemDefinedDefaultType()
    {
		return systemDefinedDefaultTypeEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getAllowNullType()
    {
		return allowNullTypeEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getParameterType()
    {
		return parameterTypeEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SybaseasabasesqlmodelFactory getSybaseasabasesqlmodelFactory()
    {
		return (SybaseasabasesqlmodelFactory)getEFactoryInstance();
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
		sybaseASABaseEventEClass = createEClass(SYBASE_ASA_BASE_EVENT);
		createEAttribute(sybaseASABaseEventEClass, SYBASE_ASA_BASE_EVENT__EVENT_TYPE);
		createEReference(sybaseASABaseEventEClass, SYBASE_ASA_BASE_EVENT__EVENT_CREATOR);
		createEAttribute(sybaseASABaseEventEClass, SYBASE_ASA_BASE_EVENT__LOCATION);
		createEReference(sybaseASABaseEventEClass, SYBASE_ASA_BASE_EVENT__SCHEDULES);
		createEReference(sybaseASABaseEventEClass, SYBASE_ASA_BASE_EVENT__CONDITION_DETAILS);

		sybaseASABaseDatabaseEClass = createEClass(SYBASE_ASA_BASE_DATABASE);
		createEReference(sybaseASABaseDatabaseEClass, SYBASE_ASA_BASE_DATABASE__DATA_TYPES);
		createEReference(sybaseASABaseDatabaseEClass, SYBASE_ASA_BASE_DATABASE__WEB_SERVICES);
		createEReference(sybaseASABaseDatabaseEClass, SYBASE_ASA_BASE_DATABASE__DB_SPACES);
		createEAttribute(sybaseASABaseDatabaseEClass, SYBASE_ASA_BASE_DATABASE__DATABASE_FILE_NAME);
		createEAttribute(sybaseASABaseDatabaseEClass, SYBASE_ASA_BASE_DATABASE__LOG_FILE_NAME);
		createEAttribute(sybaseASABaseDatabaseEClass, SYBASE_ASA_BASE_DATABASE__MIRROR_FILE_NAME);
		createEAttribute(sybaseASABaseDatabaseEClass, SYBASE_ASA_BASE_DATABASE__CASE_SENSITIVE);
		createEAttribute(sybaseASABaseDatabaseEClass, SYBASE_ASA_BASE_DATABASE__COLLATION);
		createEAttribute(sybaseASABaseDatabaseEClass, SYBASE_ASA_BASE_DATABASE__BLANK_PADDING_ON);
		createEAttribute(sybaseASABaseDatabaseEClass, SYBASE_ASA_BASE_DATABASE__CHECK_SUM_ON);
		createEAttribute(sybaseASABaseDatabaseEClass, SYBASE_ASA_BASE_DATABASE__JCONNECT_ON);
		createEAttribute(sybaseASABaseDatabaseEClass, SYBASE_ASA_BASE_DATABASE__PAGE_SIZE);
		createEReference(sybaseASABaseDatabaseEClass, SYBASE_ASA_BASE_DATABASE__ENCRYPTION_INFO);
		createEAttribute(sybaseASABaseDatabaseEClass, SYBASE_ASA_BASE_DATABASE__JAVA_SUPPORT);
		createEAttribute(sybaseASABaseDatabaseEClass, SYBASE_ASA_BASE_DATABASE__PASSWORD_CASE_SENSITIVE);

		sybaseASAWebServiceEClass = createEClass(SYBASE_ASA_WEB_SERVICE);
		createEAttribute(sybaseASAWebServiceEClass, SYBASE_ASA_WEB_SERVICE__SERVICE_ID);
		createEAttribute(sybaseASAWebServiceEClass, SYBASE_ASA_WEB_SERVICE__SERVICE_TYPE);
		createEAttribute(sybaseASAWebServiceEClass, SYBASE_ASA_WEB_SERVICE__AUTH_REQUIRED);
		createEAttribute(sybaseASAWebServiceEClass, SYBASE_ASA_WEB_SERVICE__SECURE_REQUIRED);
		createEAttribute(sybaseASAWebServiceEClass, SYBASE_ASA_WEB_SERVICE__URL_PATH);
		createEAttribute(sybaseASAWebServiceEClass, SYBASE_ASA_WEB_SERVICE__USER_NAME);
		createEAttribute(sybaseASAWebServiceEClass, SYBASE_ASA_WEB_SERVICE__PARAMETER);
		createEAttribute(sybaseASAWebServiceEClass, SYBASE_ASA_WEB_SERVICE__STATEMENT);
		createEReference(sybaseASAWebServiceEClass, SYBASE_ASA_WEB_SERVICE__DATABASE);

		encryptionInfoEClass = createEClass(ENCRYPTION_INFO);
		createEAttribute(encryptionInfoEClass, ENCRYPTION_INFO__ENCRYPTED_TABLE);
		createEAttribute(encryptionInfoEClass, ENCRYPTION_INFO__ENCRYPTION_KEY);
		createEAttribute(encryptionInfoEClass, ENCRYPTION_INFO__ALGORITHM);

		sybaseASABaseUserDefinedTypeEClass = createEClass(SYBASE_ASA_BASE_USER_DEFINED_TYPE);
		createEAttribute(sybaseASABaseUserDefinedTypeEClass, SYBASE_ASA_BASE_USER_DEFINED_TYPE__NULLABLE);
		createEAttribute(sybaseASABaseUserDefinedTypeEClass, SYBASE_ASA_BASE_USER_DEFINED_TYPE__DEFAULT_TYPE);

		sybaseASABasePredefinedDataTypeEClass = createEClass(SYBASE_ASA_BASE_PREDEFINED_DATA_TYPE);
		createEReference(sybaseASABasePredefinedDataTypeEClass, SYBASE_ASA_BASE_PREDEFINED_DATA_TYPE__DATABASE);

		sybaseASABaseTableEClass = createEClass(SYBASE_ASA_BASE_TABLE);
		createEReference(sybaseASABaseTableEClass, SYBASE_ASA_BASE_TABLE__DB_SPACE);

		sybaseASABaseColumnEClass = createEClass(SYBASE_ASA_BASE_COLUMN);
		createEReference(sybaseASABaseColumnEClass, SYBASE_ASA_BASE_COLUMN__COLUMN_CONSTRAINT);
		createEAttribute(sybaseASABaseColumnEClass, SYBASE_ASA_BASE_COLUMN__TYPE_OF_DEFAULT);
		createEAttribute(sybaseASABaseColumnEClass, SYBASE_ASA_BASE_COLUMN__UNIQUE);
		createEAttribute(sybaseASABaseColumnEClass, SYBASE_ASA_BASE_COLUMN__IS_COMPUTED_COLUMN);

		sybaseASABaseUniqueConstraintEClass = createEClass(SYBASE_ASA_BASE_UNIQUE_CONSTRAINT);
		createEAttribute(sybaseASABaseUniqueConstraintEClass, SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__CLUSTERED);
		createEReference(sybaseASABaseUniqueConstraintEClass, SYBASE_ASA_BASE_UNIQUE_CONSTRAINT__SYSTEM_GEN_INDEX);

		sybaseASABasePrimaryKeyEClass = createEClass(SYBASE_ASA_BASE_PRIMARY_KEY);

		sybaseASABaseForeignKeyEClass = createEClass(SYBASE_ASA_BASE_FOREIGN_KEY);
		createEAttribute(sybaseASABaseForeignKeyEClass, SYBASE_ASA_BASE_FOREIGN_KEY__ROLE_NAME);
		createEAttribute(sybaseASABaseForeignKeyEClass, SYBASE_ASA_BASE_FOREIGN_KEY__CLUSTERED);

		sybaseASABaseIndexEClass = createEClass(SYBASE_ASA_BASE_INDEX);
		createEReference(sybaseASABaseIndexEClass, SYBASE_ASA_BASE_INDEX__DB_SPACE);

		sybaseASABaseDBSpaceEClass = createEClass(SYBASE_ASA_BASE_DB_SPACE);
		createEAttribute(sybaseASABaseDBSpaceEClass, SYBASE_ASA_BASE_DB_SPACE__FILE_NAME);
		createEReference(sybaseASABaseDBSpaceEClass, SYBASE_ASA_BASE_DB_SPACE__DATABASE);

		sybaseASABaseViewTableEClass = createEClass(SYBASE_ASA_BASE_VIEW_TABLE);
		createEAttribute(sybaseASABaseViewTableEClass, SYBASE_ASA_BASE_VIEW_TABLE__WITH_CHECK_OPTION);
		createEReference(sybaseASABaseViewTableEClass, SYBASE_ASA_BASE_VIEW_TABLE__STATEMENT);

		sybaseASABaseFunctionEClass = createEClass(SYBASE_ASA_BASE_FUNCTION);
		createEAttribute(sybaseASABaseFunctionEClass, SYBASE_ASA_BASE_FUNCTION__ON_EXCEPTION_RESUME);

		sybaseASABaseProcedureEClass = createEClass(SYBASE_ASA_BASE_PROCEDURE);
		createEAttribute(sybaseASABaseProcedureEClass, SYBASE_ASA_BASE_PROCEDURE__ON_EXCEPTION_RESUME);

		sybaseASABaseTempTableEClass = createEClass(SYBASE_ASA_BASE_TEMP_TABLE);
		createEAttribute(sybaseASABaseTempTableEClass, SYBASE_ASA_BASE_TEMP_TABLE__TRANSACTION_OPTION);

		sybaseASABaseTriggerEClass = createEClass(SYBASE_ASA_BASE_TRIGGER);
		createEAttribute(sybaseASABaseTriggerEClass, SYBASE_ASA_BASE_TRIGGER__ORDER);
		createEAttribute(sybaseASABaseTriggerEClass, SYBASE_ASA_BASE_TRIGGER__SYBASE_ASA_BASE_ACTION_TIME);
		createEAttribute(sybaseASABaseTriggerEClass, SYBASE_ASA_BASE_TRIGGER__REMOTE_NAME);
		createEAttribute(sybaseASABaseTriggerEClass, SYBASE_ASA_BASE_TRIGGER__UPDATE_COLUMN_TYPE);

		sybaseASABaseProxyTableEClass = createEClass(SYBASE_ASA_BASE_PROXY_TABLE);
		createEAttribute(sybaseASABaseProxyTableEClass, SYBASE_ASA_BASE_PROXY_TABLE__REMOTE_OBJECT_LOCATION);
		createEAttribute(sybaseASABaseProxyTableEClass, SYBASE_ASA_BASE_PROXY_TABLE__EXISTING);

		sybaseASABaseColumnCheckConstraintEClass = createEClass(SYBASE_ASA_BASE_COLUMN_CHECK_CONSTRAINT);
		createEReference(sybaseASABaseColumnCheckConstraintEClass, SYBASE_ASA_BASE_COLUMN_CHECK_CONSTRAINT__COLUMN);

		scheduleEClass = createEClass(SCHEDULE);
		createEAttribute(scheduleEClass, SCHEDULE__RECURRING);
		createEAttribute(scheduleEClass, SCHEDULE__START_TIME);
		createEAttribute(scheduleEClass, SCHEDULE__STOP_TIME);
		createEAttribute(scheduleEClass, SCHEDULE__START_DATE);
		createEAttribute(scheduleEClass, SCHEDULE__DAYS_OF_WEEK);
		createEAttribute(scheduleEClass, SCHEDULE__DAYS_OF_MONTH);
		createEAttribute(scheduleEClass, SCHEDULE__INTERVAL_UNIT);
		createEAttribute(scheduleEClass, SCHEDULE__INTERVAL_MOUNT);
		createEReference(scheduleEClass, SCHEDULE__EVENT);

		sybaseASABaseRemoteProcedureEClass = createEClass(SYBASE_ASA_BASE_REMOTE_PROCEDURE);
		createEAttribute(sybaseASABaseRemoteProcedureEClass, SYBASE_ASA_BASE_REMOTE_PROCEDURE__LOCATION);

		sybaseASABaseParameterEClass = createEClass(SYBASE_ASA_BASE_PARAMETER);
		createEAttribute(sybaseASABaseParameterEClass, SYBASE_ASA_BASE_PARAMETER__PARM_TYPE);

		sybaseASABaseGroupEClass = createEClass(SYBASE_ASA_BASE_GROUP);

		sybaseASABaseUserEClass = createEClass(SYBASE_ASA_BASE_USER);

		sybaseASABaseSchemaEClass = createEClass(SYBASE_ASA_BASE_SCHEMA);

		sybaseASADefaultWrapperEClass = createEClass(SYBASE_ASA_DEFAULT_WRAPPER);
		createEAttribute(sybaseASADefaultWrapperEClass, SYBASE_ASA_DEFAULT_WRAPPER__VALUE);
		createEAttribute(sybaseASADefaultWrapperEClass, SYBASE_ASA_DEFAULT_WRAPPER__IS_LITERAL);
		createEAttribute(sybaseASADefaultWrapperEClass, SYBASE_ASA_DEFAULT_WRAPPER__PARTITION_SIZE);
		createEAttribute(sybaseASADefaultWrapperEClass, SYBASE_ASA_DEFAULT_WRAPPER__TYPE);

		eventConditionEClass = createEClass(EVENT_CONDITION);
		createEAttribute(eventConditionEClass, EVENT_CONDITION__OPERATOR);
		createEAttribute(eventConditionEClass, EVENT_CONDITION__VALUE);
		createEReference(eventConditionEClass, EVENT_CONDITION__EVENT);

		// Create enums
		transactionOptionEEnum = createEEnum(TRANSACTION_OPTION);
		typeOfDefaultEEnum = createEEnum(TYPE_OF_DEFAULT);
		sybaseASABaseActionTimeEEnum = createEEnum(SYBASE_ASA_BASE_ACTION_TIME);
		eventTypeEEnum = createEEnum(EVENT_TYPE);
		javaSupportTypeEEnum = createEEnum(JAVA_SUPPORT_TYPE);
		eventLocationTypeEEnum = createEEnum(EVENT_LOCATION_TYPE);
		intervalUnitTypeEEnum = createEEnum(INTERVAL_UNIT_TYPE);
		systemDefinedDefaultTypeEEnum = createEEnum(SYSTEM_DEFINED_DEFAULT_TYPE);
		allowNullTypeEEnum = createEEnum(ALLOW_NULL_TYPE);
		parameterTypeEEnum = createEEnum(PARAMETER_TYPE);
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
		SQLSchemaPackage theSQLSchemaPackage = (SQLSchemaPackage)EPackage.Registry.INSTANCE.getEPackage(SQLSchemaPackage.eNS_URI);
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
		SQLDataTypesPackage theSQLDataTypesPackage = (SQLDataTypesPackage)EPackage.Registry.INSTANCE.getEPackage(SQLDataTypesPackage.eNS_URI);
		SQLTablesPackage theSQLTablesPackage = (SQLTablesPackage)EPackage.Registry.INSTANCE.getEPackage(SQLTablesPackage.eNS_URI);
		SybasesqlmodelPackage theSybasesqlmodelPackage = (SybasesqlmodelPackage)EPackage.Registry.INSTANCE.getEPackage(SybasesqlmodelPackage.eNS_URI);
		SQLConstraintsPackage theSQLConstraintsPackage = (SQLConstraintsPackage)EPackage.Registry.INSTANCE.getEPackage(SQLConstraintsPackage.eNS_URI);
		SQLStatementsPackage theSQLStatementsPackage = (SQLStatementsPackage)EPackage.Registry.INSTANCE.getEPackage(SQLStatementsPackage.eNS_URI);
		SQLRoutinesPackage theSQLRoutinesPackage = (SQLRoutinesPackage)EPackage.Registry.INSTANCE.getEPackage(SQLRoutinesPackage.eNS_URI);
		SQLAccessControlPackage theSQLAccessControlPackage = (SQLAccessControlPackage)EPackage.Registry.INSTANCE.getEPackage(SQLAccessControlPackage.eNS_URI);

		// Add supertypes to classes
		sybaseASABaseEventEClass.getESuperTypes().add(theSQLSchemaPackage.getEvent());
		sybaseASABaseDatabaseEClass.getESuperTypes().add(theSQLSchemaPackage.getDatabase());
		sybaseASABaseDatabaseEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		sybaseASAWebServiceEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		sybaseASABaseUserDefinedTypeEClass.getESuperTypes().add(theSQLDataTypesPackage.getDomain());
		sybaseASABasePredefinedDataTypeEClass.getESuperTypes().add(theSQLDataTypesPackage.getPredefinedDataType());
		sybaseASABasePredefinedDataTypeEClass.getESuperTypes().add(theSQLDataTypesPackage.getSQLDataType());
		sybaseASABasePredefinedDataTypeEClass.getESuperTypes().add(theSQLDataTypesPackage.getDataType());
		sybaseASABasePredefinedDataTypeEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		sybaseASABaseTableEClass.getESuperTypes().add(theSQLTablesPackage.getPersistentTable());
		sybaseASABaseTableEClass.getESuperTypes().add(theSybasesqlmodelPackage.getSybaseBaseTable());
		sybaseASABaseColumnEClass.getESuperTypes().add(theSQLTablesPackage.getColumn());
		sybaseASABaseColumnEClass.getESuperTypes().add(theSybasesqlmodelPackage.getSybaseAuthorizedObject());
		sybaseASABaseUniqueConstraintEClass.getESuperTypes().add(theSQLConstraintsPackage.getUniqueConstraint());
		sybaseASABasePrimaryKeyEClass.getESuperTypes().add(this.getSybaseASABaseUniqueConstraint());
		sybaseASABasePrimaryKeyEClass.getESuperTypes().add(theSQLConstraintsPackage.getPrimaryKey());
		sybaseASABaseForeignKeyEClass.getESuperTypes().add(theSQLConstraintsPackage.getForeignKey());
		sybaseASABaseIndexEClass.getESuperTypes().add(theSQLConstraintsPackage.getIndex());
		sybaseASABaseDBSpaceEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		sybaseASABaseViewTableEClass.getESuperTypes().add(theSybasesqlmodelPackage.getSybaseViewTable());
		sybaseASABaseFunctionEClass.getESuperTypes().add(theSQLRoutinesPackage.getUserDefinedFunction());
		sybaseASABaseFunctionEClass.getESuperTypes().add(theSybasesqlmodelPackage.getSybaseRoutine());
		sybaseASABaseProcedureEClass.getESuperTypes().add(theSQLRoutinesPackage.getProcedure());
		sybaseASABaseProcedureEClass.getESuperTypes().add(theSybasesqlmodelPackage.getSybaseRoutine());
		sybaseASABaseTempTableEClass.getESuperTypes().add(theSQLTablesPackage.getTemporaryTable());
		sybaseASABaseTempTableEClass.getESuperTypes().add(theSybasesqlmodelPackage.getSybaseBaseTable());
		sybaseASABaseTriggerEClass.getESuperTypes().add(theSQLTablesPackage.getTrigger());
		sybaseASABaseProxyTableEClass.getESuperTypes().add(this.getSybaseASABaseTable());
		sybaseASABaseColumnCheckConstraintEClass.getESuperTypes().add(theSQLConstraintsPackage.getCheckConstraint());
		scheduleEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		sybaseASABaseRemoteProcedureEClass.getESuperTypes().add(this.getSybaseASABaseProcedure());
		sybaseASABaseParameterEClass.getESuperTypes().add(theSQLRoutinesPackage.getParameter());
		sybaseASABaseParameterEClass.getESuperTypes().add(theSybasesqlmodelPackage.getSybaseParameter());
		sybaseASABaseGroupEClass.getESuperTypes().add(theSQLAccessControlPackage.getUser());
		sybaseASABaseGroupEClass.getESuperTypes().add(theSQLAccessControlPackage.getGroup());
		sybaseASABaseGroupEClass.getESuperTypes().add(theSybasesqlmodelPackage.getSybaseAuthorizationIdentifier());
		sybaseASABaseUserEClass.getESuperTypes().add(theSQLAccessControlPackage.getUser());
		sybaseASABaseUserEClass.getESuperTypes().add(theSybasesqlmodelPackage.getSybaseAuthorizationIdentifier());
		sybaseASABaseSchemaEClass.getESuperTypes().add(theSQLSchemaPackage.getSchema());
		eventConditionEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());

		// Initialize classes and features; add operations and parameters
		initEClass(sybaseASABaseEventEClass, SybaseASABaseEvent.class, "SybaseASABaseEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSybaseASABaseEvent_EventType(), this.getEventType(), "eventType", null, 0, 1, SybaseASABaseEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSybaseASABaseEvent_EventCreator(), theSQLSchemaPackage.getSchema(), null, "eventCreator", null, 1, 1, SybaseASABaseEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseASABaseEvent_Location(), this.getEventLocationType(), "location", null, 0, 1, SybaseASABaseEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSybaseASABaseEvent_Schedules(), this.getSchedule(), this.getSchedule_Event(), "schedules", null, 0, -1, SybaseASABaseEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSybaseASABaseEvent_ConditionDetails(), this.getEventCondition(), this.getEventCondition_Event(), "conditionDetails", null, 0, -1, SybaseASABaseEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sybaseASABaseDatabaseEClass, SybaseASABaseDatabase.class, "SybaseASABaseDatabase", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSybaseASABaseDatabase_DataTypes(), this.getSybaseASABasePredefinedDataType(), this.getSybaseASABasePredefinedDataType_Database(), "dataTypes", null, 0, -1, SybaseASABaseDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSybaseASABaseDatabase_WebServices(), this.getSybaseASAWebService(), this.getSybaseASAWebService_Database(), "webServices", null, 0, -1, SybaseASABaseDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSybaseASABaseDatabase_DbSpaces(), this.getSybaseASABaseDBSpace(), this.getSybaseASABaseDBSpace_Database(), "dbSpaces", null, 1, -1, SybaseASABaseDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseASABaseDatabase_DatabaseFileName(), ecorePackage.getEString(), "databaseFileName", null, 1, 1, SybaseASABaseDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseASABaseDatabase_LogFileName(), ecorePackage.getEString(), "logFileName", null, 0, 1, SybaseASABaseDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseASABaseDatabase_MirrorFileName(), ecorePackage.getEString(), "mirrorFileName", null, 0, 1, SybaseASABaseDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseASABaseDatabase_CaseSensitive(), ecorePackage.getEBoolean(), "caseSensitive", null, 0, 1, SybaseASABaseDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseASABaseDatabase_Collation(), ecorePackage.getEString(), "collation", null, 0, 1, SybaseASABaseDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseASABaseDatabase_BlankPaddingOn(), ecorePackage.getEBoolean(), "blankPaddingOn", null, 0, 1, SybaseASABaseDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseASABaseDatabase_CheckSumOn(), ecorePackage.getEBoolean(), "checkSumOn", null, 0, 1, SybaseASABaseDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseASABaseDatabase_JConnectOn(), ecorePackage.getEBoolean(), "jConnectOn", "true", 0, 1, SybaseASABaseDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseASABaseDatabase_PageSize(), ecorePackage.getEInt(), "pageSize", null, 0, 1, SybaseASABaseDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSybaseASABaseDatabase_EncryptionInfo(), this.getEncryptionInfo(), null, "encryptionInfo", null, 0, 1, SybaseASABaseDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseASABaseDatabase_JavaSupport(), this.getJavaSupportType(), "javaSupport", null, 0, 1, SybaseASABaseDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseASABaseDatabase_PasswordCaseSensitive(), ecorePackage.getEBooleanObject(), "passwordCaseSensitive", null, 0, 1, SybaseASABaseDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(sybaseASABaseDatabaseEClass, ecorePackage.getEBoolean(), "isBaseOnASA10", 0, 1);

		initEClass(sybaseASAWebServiceEClass, SybaseASAWebService.class, "SybaseASAWebService", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSybaseASAWebService_Service_id(), ecorePackage.getELong(), "service_id", null, 0, 1, SybaseASAWebService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseASAWebService_Service_type(), ecorePackage.getEString(), "service_type", null, 0, 1, SybaseASAWebService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseASAWebService_Auth_required(), ecorePackage.getEString(), "auth_required", null, 0, 1, SybaseASAWebService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseASAWebService_Secure_required(), ecorePackage.getEString(), "secure_required", null, 0, 1, SybaseASAWebService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseASAWebService_Url_path(), ecorePackage.getEString(), "url_path", null, 0, 1, SybaseASAWebService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseASAWebService_User_name(), theEcorePackage.getEString(), "user_name", null, 0, 1, SybaseASAWebService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseASAWebService_Parameter(), ecorePackage.getEString(), "parameter", null, 0, 1, SybaseASAWebService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseASAWebService_Statement(), ecorePackage.getEString(), "statement", null, 0, 1, SybaseASAWebService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSybaseASAWebService_Database(), this.getSybaseASABaseDatabase(), this.getSybaseASABaseDatabase_WebServices(), "database", null, 1, 1, SybaseASAWebService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(encryptionInfoEClass, EncryptionInfo.class, "EncryptionInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEncryptionInfo_EncryptedTable(), ecorePackage.getEBoolean(), "encryptedTable", null, 0, 1, EncryptionInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEncryptionInfo_EncryptionKey(), ecorePackage.getEString(), "encryptionKey", null, 0, 1, EncryptionInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEncryptionInfo_Algorithm(), ecorePackage.getEString(), "algorithm", null, 0, 1, EncryptionInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sybaseASABaseUserDefinedTypeEClass, SybaseASABaseUserDefinedType.class, "SybaseASABaseUserDefinedType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSybaseASABaseUserDefinedType_Nullable(), this.getAllowNullType(), "nullable", null, 0, 1, SybaseASABaseUserDefinedType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseASABaseUserDefinedType_DefaultType(), this.getTypeOfDefault(), "defaultType", null, 0, 1, SybaseASABaseUserDefinedType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(sybaseASABaseUserDefinedTypeEClass, ecorePackage.getEBoolean(), "isLiteralDefault", 0, 1);

		addEOperation(sybaseASABaseUserDefinedTypeEClass, theEcorePackage.getEInt(), "getGlobalIncrementPartitionSize", 0, 1);

		initEClass(sybaseASABasePredefinedDataTypeEClass, SybaseASABasePredefinedDataType.class, "SybaseASABasePredefinedDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSybaseASABasePredefinedDataType_Database(), this.getSybaseASABaseDatabase(), this.getSybaseASABaseDatabase_DataTypes(), "database", null, 1, 1, SybaseASABasePredefinedDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sybaseASABaseTableEClass, SybaseASABaseTable.class, "SybaseASABaseTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSybaseASABaseTable_DbSpace(), this.getSybaseASABaseDBSpace(), null, "dbSpace", null, 1, 1, SybaseASABaseTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sybaseASABaseColumnEClass, SybaseASABaseColumn.class, "SybaseASABaseColumn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSybaseASABaseColumn_ColumnConstraint(), this.getSybaseASABaseColumnCheckConstraint(), this.getSybaseASABaseColumnCheckConstraint_Column(), "columnConstraint", null, 0, -1, SybaseASABaseColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseASABaseColumn_TypeOfDefault(), this.getTypeOfDefault(), "typeOfDefault", null, 0, 1, SybaseASABaseColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseASABaseColumn_Unique(), ecorePackage.getEBoolean(), "unique", null, 0, 1, SybaseASABaseColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseASABaseColumn_IsComputedColumn(), ecorePackage.getEBoolean(), "isComputedColumn", null, 0, 1, SybaseASABaseColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(sybaseASABaseColumnEClass, ecorePackage.getEBoolean(), "isLiteralDefault", 0, 1);

		addEOperation(sybaseASABaseColumnEClass, theEcorePackage.getEInt(), "getGlobalIncrementPartitionSize", 0, 1);

		initEClass(sybaseASABaseUniqueConstraintEClass, SybaseASABaseUniqueConstraint.class, "SybaseASABaseUniqueConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSybaseASABaseUniqueConstraint_Clustered(), ecorePackage.getEBoolean(), "clustered", null, 0, 1, SybaseASABaseUniqueConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSybaseASABaseUniqueConstraint_SystemGenIndex(), this.getSybaseASABaseIndex(), null, "systemGenIndex", null, 0, 1, SybaseASABaseUniqueConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sybaseASABasePrimaryKeyEClass, SybaseASABasePrimaryKey.class, "SybaseASABasePrimaryKey", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(sybaseASABaseForeignKeyEClass, SybaseASABaseForeignKey.class, "SybaseASABaseForeignKey", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSybaseASABaseForeignKey_RoleName(), theEcorePackage.getEString(), "roleName", null, 0, 1, SybaseASABaseForeignKey.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseASABaseForeignKey_Clustered(), ecorePackage.getEBoolean(), "clustered", null, 0, 1, SybaseASABaseForeignKey.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sybaseASABaseIndexEClass, SybaseASABaseIndex.class, "SybaseASABaseIndex", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSybaseASABaseIndex_DbSpace(), this.getSybaseASABaseDBSpace(), null, "dbSpace", null, 1, 1, SybaseASABaseIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sybaseASABaseDBSpaceEClass, SybaseASABaseDBSpace.class, "SybaseASABaseDBSpace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSybaseASABaseDBSpace_FileName(), ecorePackage.getEString(), "fileName", null, 0, 1, SybaseASABaseDBSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSybaseASABaseDBSpace_Database(), this.getSybaseASABaseDatabase(), this.getSybaseASABaseDatabase_DbSpaces(), "database", null, 1, 1, SybaseASABaseDBSpace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sybaseASABaseViewTableEClass, SybaseASABaseViewTable.class, "SybaseASABaseViewTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSybaseASABaseViewTable_WithCheckOption(), ecorePackage.getEBoolean(), "withCheckOption", "false", 0, 1, SybaseASABaseViewTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSybaseASABaseViewTable_Statement(), theSQLStatementsPackage.getSQLStatement(), null, "statement", null, 0, 1, SybaseASABaseViewTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sybaseASABaseFunctionEClass, SybaseASABaseFunction.class, "SybaseASABaseFunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSybaseASABaseFunction_OnExceptionResume(), ecorePackage.getEBoolean(), "onExceptionResume", null, 0, 1, SybaseASABaseFunction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sybaseASABaseProcedureEClass, SybaseASABaseProcedure.class, "SybaseASABaseProcedure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSybaseASABaseProcedure_OnExceptionResume(), ecorePackage.getEBoolean(), "onExceptionResume", null, 0, 1, SybaseASABaseProcedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sybaseASABaseTempTableEClass, SybaseASABaseTempTable.class, "SybaseASABaseTempTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSybaseASABaseTempTable_TransactionOption(), this.getTransactionOption(), "transactionOption", null, 0, 1, SybaseASABaseTempTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sybaseASABaseTriggerEClass, SybaseASABaseTrigger.class, "SybaseASABaseTrigger", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSybaseASABaseTrigger_Order(), ecorePackage.getEInt(), "order", "1", 0, 1, SybaseASABaseTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseASABaseTrigger_SybaseASABaseActionTime(), this.getSybaseASABaseActionTime(), "sybaseASABaseActionTime", null, 0, 1, SybaseASABaseTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseASABaseTrigger_RemoteName(), ecorePackage.getEString(), "remoteName", null, 0, 1, SybaseASABaseTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseASABaseTrigger_UpdateColumnType(), ecorePackage.getEBoolean(), "updateColumnType", null, 0, 1, SybaseASABaseTrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(sybaseASABaseTriggerEClass, ecorePackage.getEString(), "getOldName", 0, 1);

		addEOperation(sybaseASABaseTriggerEClass, ecorePackage.getEString(), "getNewName", 0, 1);

		EOperation op = addEOperation(sybaseASABaseTriggerEClass, null, "setOldName");
		addEParameter(op, theEcorePackage.getEString(), "oldName", 0, 1);

		op = addEOperation(sybaseASABaseTriggerEClass, null, "setNewName");
		addEParameter(op, theEcorePackage.getEString(), "newName", 0, 1);

		initEClass(sybaseASABaseProxyTableEClass, SybaseASABaseProxyTable.class, "SybaseASABaseProxyTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSybaseASABaseProxyTable_RemoteObjectLocation(), ecorePackage.getEString(), "remoteObjectLocation", null, 0, 1, SybaseASABaseProxyTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseASABaseProxyTable_Existing(), ecorePackage.getEBoolean(), "existing", "false", 1, 1, SybaseASABaseProxyTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sybaseASABaseColumnCheckConstraintEClass, SybaseASABaseColumnCheckConstraint.class, "SybaseASABaseColumnCheckConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSybaseASABaseColumnCheckConstraint_Column(), this.getSybaseASABaseColumn(), this.getSybaseASABaseColumn_ColumnConstraint(), "column", null, 1, 1, SybaseASABaseColumnCheckConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scheduleEClass, Schedule.class, "Schedule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSchedule_Recurring(), ecorePackage.getEBoolean(), "recurring", null, 0, 1, Schedule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSchedule_StartTime(), theEcorePackage.getEDate(), "startTime", null, 0, 1, Schedule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSchedule_StopTime(), theEcorePackage.getEDate(), "stopTime", null, 0, 1, Schedule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSchedule_StartDate(), theEcorePackage.getEDate(), "startDate", null, 0, 1, Schedule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSchedule_DaysOfWeek(), theEcorePackage.getEInt(), "daysOfWeek", null, 0, 1, Schedule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSchedule_DaysOfMonth(), ecorePackage.getEInt(), "daysOfMonth", null, 0, 1, Schedule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSchedule_IntervalUnit(), this.getIntervalUnitType(), "intervalUnit", null, 0, 1, Schedule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSchedule_IntervalMount(), theEcorePackage.getEInt(), "intervalMount", null, 0, 1, Schedule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSchedule_Event(), this.getSybaseASABaseEvent(), this.getSybaseASABaseEvent_Schedules(), "event", null, 1, 1, Schedule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sybaseASABaseRemoteProcedureEClass, SybaseASABaseRemoteProcedure.class, "SybaseASABaseRemoteProcedure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSybaseASABaseRemoteProcedure_Location(), ecorePackage.getEString(), "location", null, 0, 1, SybaseASABaseRemoteProcedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sybaseASABaseParameterEClass, SybaseASABaseParameter.class, "SybaseASABaseParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSybaseASABaseParameter_ParmType(), this.getParameterType(), "parmType", null, 0, 1, SybaseASABaseParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sybaseASABaseGroupEClass, SybaseASABaseGroup.class, "SybaseASABaseGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(sybaseASABaseUserEClass, SybaseASABaseUser.class, "SybaseASABaseUser", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(sybaseASABaseSchemaEClass, SybaseASABaseSchema.class, "SybaseASABaseSchema", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(sybaseASABaseSchemaEClass, theSQLSchemaPackage.getList(), "getNormalTables", 0, 1);

		addEOperation(sybaseASABaseSchemaEClass, theSQLSchemaPackage.getList(), "getTempTables", 0, 1);

		addEOperation(sybaseASABaseSchemaEClass, theSQLSchemaPackage.getList(), "getSystemTables", 0, 1);

		addEOperation(sybaseASABaseSchemaEClass, theSQLSchemaPackage.getList(), "getProxyTables", 0, 1);

		op = addEOperation(sybaseASABaseSchemaEClass, theSQLSchemaPackage.getList(), "getViewTables", 0, 1);
		addEParameter(op, ecorePackage.getEBoolean(), "systemFlag", 0, 1);

		addEOperation(sybaseASABaseSchemaEClass, theSQLSchemaPackage.getList(), "getSystemAndNormalTables", 0, 1);

		initEClass(sybaseASADefaultWrapperEClass, SybaseASADefaultWrapper.class, "SybaseASADefaultWrapper", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSybaseASADefaultWrapper_Value(), ecorePackage.getEString(), "value", null, 0, 1, SybaseASADefaultWrapper.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseASADefaultWrapper_IsLiteral(), ecorePackage.getEBoolean(), "isLiteral", null, 0, 1, SybaseASADefaultWrapper.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseASADefaultWrapper_PartitionSize(), ecorePackage.getEInt(), "partitionSize", "-1", 0, 1, SybaseASADefaultWrapper.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSybaseASADefaultWrapper_Type(), this.getTypeOfDefault(), "type", null, 0, 1, SybaseASADefaultWrapper.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(sybaseASADefaultWrapperEClass, null, "parse");

		initEClass(eventConditionEClass, EventCondition.class, "EventCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEventCondition_Operator(), theEcorePackage.getEString(), "operator", null, 0, 1, EventCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEventCondition_Value(), ecorePackage.getEInt(), "value", null, 0, 1, EventCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEventCondition_Event(), this.getSybaseASABaseEvent(), this.getSybaseASABaseEvent_ConditionDetails(), "event", null, 1, 1, EventCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(transactionOptionEEnum, TransactionOption.class, "TransactionOption");
		addEEnumLiteral(transactionOptionEEnum, TransactionOption.DELETE_LITERAL);
		addEEnumLiteral(transactionOptionEEnum, TransactionOption.PRESERVE_LITERAL);
		addEEnumLiteral(transactionOptionEEnum, TransactionOption.NOT_TRANSACTION_LITERAL);

		initEEnum(typeOfDefaultEEnum, TypeOfDefault.class, "TypeOfDefault");
		addEEnumLiteral(typeOfDefaultEEnum, TypeOfDefault.NO_DEFAULT_LITERAL);
		addEEnumLiteral(typeOfDefaultEEnum, TypeOfDefault.USER_DEFINED_LITERAL);
		addEEnumLiteral(typeOfDefaultEEnum, TypeOfDefault.SYSTEM_DEFINED_LITERAL);
		addEEnumLiteral(typeOfDefaultEEnum, TypeOfDefault.COMPUTED_VALUE_LITERAL);

		initEEnum(sybaseASABaseActionTimeEEnum, SybaseASABaseActionTime.class, "SybaseASABaseActionTime");
		addEEnumLiteral(sybaseASABaseActionTimeEEnum, SybaseASABaseActionTime.BEFORE_LITERAL);
		addEEnumLiteral(sybaseASABaseActionTimeEEnum, SybaseASABaseActionTime.AFTER_LITERAL);
		addEEnumLiteral(sybaseASABaseActionTimeEEnum, SybaseASABaseActionTime.RESOLVE_LITERAL);
		addEEnumLiteral(sybaseASABaseActionTimeEEnum, SybaseASABaseActionTime.ASE_LITERAL);

		initEEnum(eventTypeEEnum, EventType.class, "EventType");
		addEEnumLiteral(eventTypeEEnum, EventType.NOEVENTTYPE_LITERAL);
		addEEnumLiteral(eventTypeEEnum, EventType.BACKUPEND_LITERAL);
		addEEnumLiteral(eventTypeEEnum, EventType.CONNECT_LITERAL);
		addEEnumLiteral(eventTypeEEnum, EventType.CONNECTFAILED_LITERAL);
		addEEnumLiteral(eventTypeEEnum, EventType.DATABASESTART_LITERAL);
		addEEnumLiteral(eventTypeEEnum, EventType.DBDISKSPACE_LITERAL);
		addEEnumLiteral(eventTypeEEnum, EventType.DISCONEECT_LITERAL);
		addEEnumLiteral(eventTypeEEnum, EventType.GLOBALAUTOINCREMENT_LITERAL);
		addEEnumLiteral(eventTypeEEnum, EventType.GROWDB_LITERAL);
		addEEnumLiteral(eventTypeEEnum, EventType.GROWLOG_LITERAL);
		addEEnumLiteral(eventTypeEEnum, EventType.GROWTEMP_LITERAL);
		addEEnumLiteral(eventTypeEEnum, EventType.LOGDISKSPACE_LITERAL);
		addEEnumLiteral(eventTypeEEnum, EventType.RAISERROR_LITERAL);
		addEEnumLiteral(eventTypeEEnum, EventType.SERVERIDLE_LITERAL);
		addEEnumLiteral(eventTypeEEnum, EventType.TEMPDISKSPACE_LITERAL);

		initEEnum(javaSupportTypeEEnum, JavaSupportType.class, "JavaSupportType");
		addEEnumLiteral(javaSupportTypeEEnum, JavaSupportType.OFF_LITERAL);
		addEEnumLiteral(javaSupportTypeEEnum, JavaSupportType.ON_LITERAL);
		addEEnumLiteral(javaSupportTypeEEnum, JavaSupportType.JDK13_LITERAL);
		addEEnumLiteral(javaSupportTypeEEnum, JavaSupportType.JDK118_LITERAL);

		initEEnum(eventLocationTypeEEnum, EventLocationType.class, "EventLocationType");
		addEEnumLiteral(eventLocationTypeEEnum, EventLocationType.ALL_LITERAL);
		addEEnumLiteral(eventLocationTypeEEnum, EventLocationType.REMOTE_LITERAL);
		addEEnumLiteral(eventLocationTypeEEnum, EventLocationType.CONSOLIDATED_LITERAL);

		initEEnum(intervalUnitTypeEEnum, IntervalUnitType.class, "IntervalUnitType");
		addEEnumLiteral(intervalUnitTypeEEnum, IntervalUnitType.HOURS_LITERAL);
		addEEnumLiteral(intervalUnitTypeEEnum, IntervalUnitType.MINUTES_LITERAL);
		addEEnumLiteral(intervalUnitTypeEEnum, IntervalUnitType.SECONDS_LITERAL);

		initEEnum(systemDefinedDefaultTypeEEnum, SystemDefinedDefaultType.class, "SystemDefinedDefaultType");
		addEEnumLiteral(systemDefinedDefaultTypeEEnum, SystemDefinedDefaultType.AUTOINCREMENT_LITERAL);
		addEEnumLiteral(systemDefinedDefaultTypeEEnum, SystemDefinedDefaultType.CURRENT_DATABASE_LITERAL);
		addEEnumLiteral(systemDefinedDefaultTypeEEnum, SystemDefinedDefaultType.CURRENT_REMOTE_USER_LITERAL);
		addEEnumLiteral(systemDefinedDefaultTypeEEnum, SystemDefinedDefaultType.CURRENT_UTC_TIMESTAMP_LITERAL);
		addEEnumLiteral(systemDefinedDefaultTypeEEnum, SystemDefinedDefaultType.GLOBAL_AUTOINCREMENT_LITERAL);
		addEEnumLiteral(systemDefinedDefaultTypeEEnum, SystemDefinedDefaultType.NULL_LITERAL);
		addEEnumLiteral(systemDefinedDefaultTypeEEnum, SystemDefinedDefaultType.TIMESTAMP_LITERAL);
		addEEnumLiteral(systemDefinedDefaultTypeEEnum, SystemDefinedDefaultType.UTC_TIMESTAMP_LITERAL);
		addEEnumLiteral(systemDefinedDefaultTypeEEnum, SystemDefinedDefaultType.LAST_USER_LITERAL);
		addEEnumLiteral(systemDefinedDefaultTypeEEnum, SystemDefinedDefaultType.CURRENT_DATE_LITERAL);
		addEEnumLiteral(systemDefinedDefaultTypeEEnum, SystemDefinedDefaultType.CURRENT_TIME_LITERAL);
		addEEnumLiteral(systemDefinedDefaultTypeEEnum, SystemDefinedDefaultType.CURRENT_TIMESTAMP_LITERAL);
		addEEnumLiteral(systemDefinedDefaultTypeEEnum, SystemDefinedDefaultType.CURRENT_USER_LITERAL);
		addEEnumLiteral(systemDefinedDefaultTypeEEnum, SystemDefinedDefaultType.CURRENT_PUBLISHER_LITERAL);
		addEEnumLiteral(systemDefinedDefaultTypeEEnum, SystemDefinedDefaultType.USER_LITERAL);

		initEEnum(allowNullTypeEEnum, AllowNullType.class, "AllowNullType");
		addEEnumLiteral(allowNullTypeEEnum, AllowNullType.NULLABLE_LITERAL);
		addEEnumLiteral(allowNullTypeEEnum, AllowNullType.NOT_NULLABLE_LITERAL);
		addEEnumLiteral(allowNullTypeEEnum, AllowNullType.DATABASE_DEFAULT_LITERAL);

		initEEnum(parameterTypeEEnum, ParameterType.class, "ParameterType");
		addEEnumLiteral(parameterTypeEEnum, ParameterType.VARIABLE_LITERAL);
		addEEnumLiteral(parameterTypeEEnum, ParameterType.RESULT_LITERAL);
		addEEnumLiteral(parameterTypeEEnum, ParameterType.SQLSTATE_LITERAL);
		addEEnumLiteral(parameterTypeEEnum, ParameterType.SQLCODE_LITERAL);
		addEEnumLiteral(parameterTypeEEnum, ParameterType.RETURN_LITERAL);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// GenModel
		createGenModel_1Annotations();
	}

    /**
	 * Initializes the annotations for <b>GenModel</b>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected void createGenModel_1Annotations()
    {
		String source = "GenModel";														
		addAnnotation
		  (sybaseASABaseActionTimeEEnum, 
		   source, 
		   new String[] {
			 "document", "In Sybase ASA, there are 3 kinds of action time for trigger which are: BEFORE, AFTER and RESOLVE.\r\nThe predefined action time enumeration in sql model can not meet our requirement. And we can not \r\nextends the predefined one since it\'s declared as final.\r\nThe actual action time for \"ASE\" type is \"AFTER\", but ASA treats it as another action time."
		   });			
	}

} //SybaseasabasesqlmodelPackageImpl

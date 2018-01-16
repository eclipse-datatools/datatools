/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseasesqlmodelPackageImpl.java,v 1.1 2008/03/27 07:41:13 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.AccessRuleType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheInfo;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheStrategyType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.DeviceItem;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockPromotionInfo;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockingSchemaType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.ProxyTableExternalType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SegmentThreshold;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECache;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalogType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECheckConstraint;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumnCheckConstraint;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDefault;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEEncryptionKey;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEFuncBasedIndexMember;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEGroup;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEPredefinedDataType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEPrimaryKey;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEPrivilege;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProcedure;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProxyTable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERole;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESchema;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASETable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASETempTable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASETrigger;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUniqueConstraint;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUser;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEViewTable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebService;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebServiceTable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelFactory;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.TransactionModeType;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.impl.PartitionPackageImpl;

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
public class SybaseasesqlmodelPackageImpl extends EPackageImpl implements SybaseasesqlmodelPackage
{
    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASESchemaEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASEDatabaseEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASEWebServiceEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASEPredefinedDataTypeEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASECatalogEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASEProcedureEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASEDefaultEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASERuleEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASEIndexEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASESegmentEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASEFuncBasedIndexMemberEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASETableEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASEColumnCheckConstraintEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASEColumnEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASEUniqueConstraintEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASEPrimaryKeyEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass deviceItemEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass segmentThresholdEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass cacheInfoEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASEUserDefinedTypeEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASEEncryptionKeyEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass lockPromotionInfoEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASERoleEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASECacheEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASEViewTableEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASETempTableEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASEProxyTableEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASEWebServiceTableEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASEBaseTableEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASEUserEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASEGroupEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASEPrivilegeEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass sybaseASETriggerEClass = null;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sybaseASECheckConstraintEClass = null;

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum transactionModeTypeEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum cacheStrategyTypeEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum lockingSchemaTypeEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum proxyTableExternalTypeEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum accessRuleTypeEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum sybaseASECatalogTypeEEnum = null;

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
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
    private SybaseasesqlmodelPackageImpl()
    {
		super(eNS_URI, SybaseasesqlmodelFactory.eINSTANCE);
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
    public static SybaseasesqlmodelPackage init()
    {
		if (isInited) return (SybaseasesqlmodelPackage)EPackage.Registry.INSTANCE.getEPackage(SybaseasesqlmodelPackage.eNS_URI);

		// Obtain or create and register package
		SybaseasesqlmodelPackageImpl theSybaseasesqlmodelPackage = (SybaseasesqlmodelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof SybaseasesqlmodelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new SybaseasesqlmodelPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		SybasesqlmodelPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		PartitionPackageImpl thePartitionPackage = (PartitionPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PartitionPackage.eNS_URI) instanceof PartitionPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PartitionPackage.eNS_URI) : PartitionPackage.eINSTANCE);

		// Create package meta-data objects
		theSybaseasesqlmodelPackage.createPackageContents();
		thePartitionPackage.createPackageContents();

		// Initialize created meta-data
		theSybaseasesqlmodelPackage.initializePackageContents();
		thePartitionPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theSybaseasesqlmodelPackage.freeze();

		return theSybaseasesqlmodelPackage;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASESchema()
    {
		return sybaseASESchemaEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASESchema_Defaults()
    {
		return (EReference)sybaseASESchemaEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASESchema_Rules()
    {
		return (EReference)sybaseASESchemaEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASESchema_EncryptionKeys()
    {
		return (EReference)sybaseASESchemaEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASEDatabase()
    {
		return sybaseASEDatabaseEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASEDatabase_DataTypes()
    {
		return (EReference)sybaseASEDatabaseEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEDatabase_EncryptionKeyApplicable()
    {
		return (EAttribute)sybaseASEDatabaseEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASEDatabase_Roles()
    {
		return (EReference)sybaseASEDatabaseEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASEDatabase_Caches()
    {
		return (EReference)sybaseASEDatabaseEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASEDatabase_WebServices()
    {
		return (EReference)sybaseASEDatabaseEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEDatabase_WebserviceApplicable()
    {
		return (EAttribute)sybaseASEDatabaseEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEDatabase_SdsServer()
    {
		return (EAttribute)sybaseASEDatabaseEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSybaseASEDatabase_TempDBName() {
		return (EAttribute)sybaseASEDatabaseEClass.getEStructuralFeatures().get(7);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASEWebService()
    {
		return sybaseASEWebServiceEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEWebService_Service_id()
    {
		return (EAttribute)sybaseASEWebServiceEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEWebService_Service_type()
    {
		return (EAttribute)sybaseASEWebServiceEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEWebService_Auth_required()
    {
		return (EAttribute)sybaseASEWebServiceEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEWebService_Secure_required()
    {
		return (EAttribute)sybaseASEWebServiceEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEWebService_Url_path()
    {
		return (EAttribute)sybaseASEWebServiceEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEWebService_User_name()
    {
		return (EAttribute)sybaseASEWebServiceEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEWebService_Parameter()
    {
		return (EAttribute)sybaseASEWebServiceEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEWebService_Statement()
    {
		return (EAttribute)sybaseASEWebServiceEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEWebService_Remarks()
    {
		return (EAttribute)sybaseASEWebServiceEClass.getEStructuralFeatures().get(8);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASEWebService_Database()
    {
		return (EReference)sybaseASEWebServiceEClass.getEStructuralFeatures().get(9);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASEPredefinedDataType()
    {
		return sybaseASEPredefinedDataTypeEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASEPredefinedDataType_Database()
    {
		return (EReference)sybaseASEPredefinedDataTypeEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASECatalog()
    {
		return sybaseASECatalogEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASECatalog_Segments()
    {
		return (EReference)sybaseASECatalogEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASECatalog_DataDevices()
    {
		return (EReference)sybaseASECatalogEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASECatalog_LogDevices()
    {
		return (EReference)sybaseASECatalogEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASECatalog_Override()
    {
		return (EAttribute)sybaseASECatalogEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASECatalog_DefaultLocation()
    {
		return (EAttribute)sybaseASECatalogEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASECatalog_ForLoad()
    {
		return (EAttribute)sybaseASECatalogEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASECatalog_ForProxyUpdate()
    {
		return (EAttribute)sybaseASECatalogEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASECatalog_LogIOSize()
    {
		return (EAttribute)sybaseASECatalogEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASECatalog_RecoveryOrder()
    {
		return (EAttribute)sybaseASECatalogEClass.getEStructuralFeatures().get(8);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASECatalog_AuthorizationIds()
    {
		return (EReference)sybaseASECatalogEClass.getEStructuralFeatures().get(9);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASECatalog_Cache()
    {
		return (EReference)sybaseASECatalogEClass.getEStructuralFeatures().get(10);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASECatalog_CatalogType()
    {
		return (EAttribute)sybaseASECatalogEClass.getEStructuralFeatures().get(11);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASEProcedure()
    {
		return sybaseASEProcedureEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEProcedure_GroupNumber()
    {
		return (EAttribute)sybaseASEProcedureEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEProcedure_TransactionMode()
    {
		return (EAttribute)sybaseASEProcedureEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEProcedure_SystemProcedure()
    {
		return (EAttribute)sybaseASEProcedureEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEProcedure_WithRecompile()
    {
		return (EAttribute)sybaseASEProcedureEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASEDefault()
    {
		return sybaseASEDefaultEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASEDefault_Schema()
    {
		return (EReference)sybaseASEDefaultEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEDefault_Statement()
    {
		return (EAttribute)sybaseASEDefaultEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASERule()
    {
		return sybaseASERuleEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASERule_Schema()
    {
		return (EReference)sybaseASERuleEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASERule_Statement()
    {
		return (EAttribute)sybaseASERuleEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASERule_AccessRule()
    {
		return (EAttribute)sybaseASERuleEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASERule_AccessType()
    {
		return (EAttribute)sybaseASERuleEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASEIndex()
    {
		return sybaseASEIndexEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEIndex_MaxRowPerPage()
    {
		return (EAttribute)sybaseASEIndexEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEIndex_ReversePageGap()
    {
		return (EAttribute)sybaseASEIndexEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEIndex_IgnoreDuplicateKey()
    {
		return (EAttribute)sybaseASEIndexEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEIndex_SortedData()
    {
		return (EAttribute)sybaseASEIndexEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEIndex_IgnoreDuplicateRow()
    {
		return (EAttribute)sybaseASEIndexEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASEIndex_Segment()
    {
		return (EReference)sybaseASEIndexEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEIndex_LocalIndex()
    {
		return (EAttribute)sybaseASEIndexEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASEIndex_Partitions()
    {
		return (EReference)sybaseASEIndexEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEIndex_ConsumerNum()
    {
		return (EAttribute)sybaseASEIndexEClass.getEStructuralFeatures().get(8);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEIndex_StatisticsStep()
    {
		return (EAttribute)sybaseASEIndexEClass.getEStructuralFeatures().get(9);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEIndex_AllowDuplicateRow()
    {
		return (EAttribute)sybaseASEIndexEClass.getEStructuralFeatures().get(10);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEIndex_Suspect()
    {
		return (EAttribute)sybaseASEIndexEClass.getEStructuralFeatures().get(11);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASEIndex_CacheInfo()
    {
		return (EReference)sybaseASEIndexEClass.getEStructuralFeatures().get(12);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASESegment()
    {
		return sybaseASESegmentEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASESegment_Catalog()
    {
		return (EReference)sybaseASESegmentEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASESegment_DeviceNames()
    {
		return (EAttribute)sybaseASESegmentEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASESegment_Thresholds()
    {
		return (EReference)sybaseASESegmentEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASEFuncBasedIndexMember()
    {
		return sybaseASEFuncBasedIndexMemberEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASETable()
    {
		return sybaseASETableEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASEColumnCheckConstraint()
    {
		return sybaseASEColumnCheckConstraintEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASEColumnCheckConstraint_Column()
    {
		return (EReference)sybaseASEColumnCheckConstraintEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASEColumn()
    {
		return sybaseASEColumnEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASEColumn_ColumnCheck()
    {
		return (EReference)sybaseASEColumnEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASEColumn_BoundDefault()
    {
		return (EReference)sybaseASEColumnEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASEColumn_BoundRule()
    {
		return (EReference)sybaseASEColumnEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEColumn_Materialized()
    {
		return (EAttribute)sybaseASEColumnEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASEColumn_EncryptionKey()
    {
		return (EReference)sybaseASEColumnEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEColumn_BindDefaultInFutureOnly()
    {
		return (EAttribute)sybaseASEColumnEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEColumn_BindRuleInFutureOnly()
    {
		return (EAttribute)sybaseASEColumnEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEColumn_Hidden()
    {
		return (EAttribute)sybaseASEColumnEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASEUniqueConstraint()
    {
		return sybaseASEUniqueConstraintEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASEUniqueConstraint_SystemGenedIndex()
    {
		return (EReference)sybaseASEUniqueConstraintEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEUniqueConstraint_SystemGenedName()
    {
		return (EAttribute)sybaseASEUniqueConstraintEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASEPrimaryKey()
    {
		return sybaseASEPrimaryKeyEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASEPrimaryKey_AseUniqueConstraint()
    {
		return (EReference)sybaseASEPrimaryKeyEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getDeviceItem()
    {
		return deviceItemEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getDeviceItem_DeviceName()
    {
		return (EAttribute)deviceItemEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getDeviceItem_Size()
    {
		return (EAttribute)deviceItemEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSegmentThreshold()
    {
		return segmentThresholdEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSegmentThreshold_ProcedureName()
    {
		return (EAttribute)segmentThresholdEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSegmentThreshold_FreeSpace()
    {
		return (EAttribute)segmentThresholdEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getCacheInfo()
    {
		return cacheInfoEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCacheInfo_CacheStrategy()
    {
		return (EAttribute)cacheInfoEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getCacheInfo_Cache()
    {
		return (EReference)cacheInfoEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASEUserDefinedType()
    {
		return sybaseASEUserDefinedTypeEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASEUserDefinedType_BoundDefault()
    {
		return (EReference)sybaseASEUserDefinedTypeEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASEUserDefinedType_BoundRule()
    {
		return (EReference)sybaseASEUserDefinedTypeEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEUserDefinedType_BindDefaultInFutureOnly()
    {
		return (EAttribute)sybaseASEUserDefinedTypeEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEUserDefinedType_BindRuleInFutureOnly()
    {
		return (EAttribute)sybaseASEUserDefinedTypeEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEUserDefinedType_AllowNulls()
    {
		return (EAttribute)sybaseASEUserDefinedTypeEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEUserDefinedType_Identity()
    {
		return (EAttribute)sybaseASEUserDefinedTypeEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASEEncryptionKey()
    {
		return sybaseASEEncryptionKeyEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASEEncryptionKey_Schema()
    {
		return (EReference)sybaseASEEncryptionKeyEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getLockPromotionInfo()
    {
		return lockPromotionInfoEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLockPromotionInfo_RowLockPromotion()
    {
		return (EAttribute)lockPromotionInfoEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLockPromotionInfo_LWM()
    {
		return (EAttribute)lockPromotionInfoEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLockPromotionInfo_HWM()
    {
		return (EAttribute)lockPromotionInfoEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLockPromotionInfo_PCT()
    {
		return (EAttribute)lockPromotionInfoEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASERole()
    {
		return sybaseASERoleEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASECache()
    {
		return sybaseASECacheEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASECache_Database()
    {
		return (EReference)sybaseASECacheEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASEViewTable()
    {
		return sybaseASEViewTableEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEViewTable_WithCheckOption()
    {
		return (EAttribute)sybaseASEViewTableEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASETempTable()
    {
		return sybaseASETempTableEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASEProxyTable()
    {
		return sybaseASEProxyTableEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEProxyTable_ExternalType()
    {
		return (EAttribute)sybaseASEProxyTableEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEProxyTable_Existing()
    {
		return (EAttribute)sybaseASEProxyTableEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEProxyTable_ColumnDelimiter()
    {
		return (EAttribute)sybaseASEProxyTableEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEProxyTable_ExternalPath()
    {
		return (EAttribute)sybaseASEProxyTableEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASEWebServiceTable()
    {
		return sybaseASEWebServiceTableEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEWebServiceTable_Method()
    {
		return (EAttribute)sybaseASEWebServiceTableEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEWebServiceTable_WSDLURI()
    {
		return (EAttribute)sybaseASEWebServiceTableEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASEBaseTable()
    {
		return sybaseASEBaseTableEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEBaseTable_LockSchema()
    {
		return (EAttribute)sybaseASEBaseTableEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEBaseTable_FillFactor()
    {
		return (EAttribute)sybaseASEBaseTableEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEBaseTable_MaxRowPerPage()
    {
		return (EAttribute)sybaseASEBaseTableEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEBaseTable_ExpRowSize()
    {
		return (EAttribute)sybaseASEBaseTableEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEBaseTable_ReservePageGap()
    {
		return (EAttribute)sybaseASEBaseTableEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEBaseTable_IdentityGap()
    {
		return (EAttribute)sybaseASEBaseTableEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASEBaseTable_Segment()
    {
		return (EReference)sybaseASEBaseTableEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEBaseTable_ConcurrencyOptThreshold()
    {
		return (EAttribute)sybaseASEBaseTableEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASEBaseTable_PartitionCondition()
    {
		return (EReference)sybaseASEBaseTableEClass.getEStructuralFeatures().get(8);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASEBaseTable_TableOnlyCacheInfo()
    {
		return (EReference)sybaseASEBaseTableEClass.getEStructuralFeatures().get(9);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASEBaseTable_TextOnlyCacheInfo()
    {
		return (EReference)sybaseASEBaseTableEClass.getEStructuralFeatures().get(10);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASEBaseTable_LockPromotion()
    {
		return (EReference)sybaseASEBaseTableEClass.getEStructuralFeatures().get(11);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEBaseTable_Partitions()
    {
		return (EAttribute)sybaseASEBaseTableEClass.getEStructuralFeatures().get(12);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getSybaseASEBaseTable_TextImageSegment()
    {
		return (EReference)sybaseASEBaseTableEClass.getEStructuralFeatures().get(13);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEBaseTable_SystemTable()
    {
		return (EAttribute)sybaseASEBaseTableEClass.getEStructuralFeatures().get(14);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASEUser()
    {
		return sybaseASEUserEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASEUser_LoginName()
    {
		return (EAttribute)sybaseASEUserEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASEGroup()
    {
		return sybaseASEGroupEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASEPrivilege()
    {
		return sybaseASEPrivilegeEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSybaseASETrigger()
    {
		return sybaseASETriggerEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSybaseASETrigger_Enabled()
    {
		return (EAttribute)sybaseASETriggerEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSybaseASECheckConstraint() {
		return sybaseASECheckConstraintEClass;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSybaseASECheckConstraint_Creator() {
		return (EReference)sybaseASECheckConstraintEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getTransactionModeType()
    {
		return transactionModeTypeEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getCacheStrategyType()
    {
		return cacheStrategyTypeEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getLockingSchemaType()
    {
		return lockingSchemaTypeEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getProxyTableExternalType()
    {
		return proxyTableExternalTypeEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getAccessRuleType()
    {
		return accessRuleTypeEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getSybaseASECatalogType()
    {
		return sybaseASECatalogTypeEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SybaseasesqlmodelFactory getSybaseasesqlmodelFactory()
    {
		return (SybaseasesqlmodelFactory)getEFactoryInstance();
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
		sybaseASESchemaEClass = createEClass(SYBASE_ASE_SCHEMA);
		createEReference(sybaseASESchemaEClass, SYBASE_ASE_SCHEMA__DEFAULTS);
		createEReference(sybaseASESchemaEClass, SYBASE_ASE_SCHEMA__RULES);
		createEReference(sybaseASESchemaEClass, SYBASE_ASE_SCHEMA__ENCRYPTION_KEYS);

		sybaseASEDatabaseEClass = createEClass(SYBASE_ASE_DATABASE);
		createEReference(sybaseASEDatabaseEClass, SYBASE_ASE_DATABASE__DATA_TYPES);
		createEAttribute(sybaseASEDatabaseEClass, SYBASE_ASE_DATABASE__ENCRYPTION_KEY_APPLICABLE);
		createEReference(sybaseASEDatabaseEClass, SYBASE_ASE_DATABASE__ROLES);
		createEReference(sybaseASEDatabaseEClass, SYBASE_ASE_DATABASE__CACHES);
		createEReference(sybaseASEDatabaseEClass, SYBASE_ASE_DATABASE__WEB_SERVICES);
		createEAttribute(sybaseASEDatabaseEClass, SYBASE_ASE_DATABASE__WEBSERVICE_APPLICABLE);
		createEAttribute(sybaseASEDatabaseEClass, SYBASE_ASE_DATABASE__SDS_SERVER);
		createEAttribute(sybaseASEDatabaseEClass, SYBASE_ASE_DATABASE__TEMP_DB_NAME);

		sybaseASEWebServiceEClass = createEClass(SYBASE_ASE_WEB_SERVICE);
		createEAttribute(sybaseASEWebServiceEClass, SYBASE_ASE_WEB_SERVICE__SERVICE_ID);
		createEAttribute(sybaseASEWebServiceEClass, SYBASE_ASE_WEB_SERVICE__SERVICE_TYPE);
		createEAttribute(sybaseASEWebServiceEClass, SYBASE_ASE_WEB_SERVICE__AUTH_REQUIRED);
		createEAttribute(sybaseASEWebServiceEClass, SYBASE_ASE_WEB_SERVICE__SECURE_REQUIRED);
		createEAttribute(sybaseASEWebServiceEClass, SYBASE_ASE_WEB_SERVICE__URL_PATH);
		createEAttribute(sybaseASEWebServiceEClass, SYBASE_ASE_WEB_SERVICE__USER_NAME);
		createEAttribute(sybaseASEWebServiceEClass, SYBASE_ASE_WEB_SERVICE__PARAMETER);
		createEAttribute(sybaseASEWebServiceEClass, SYBASE_ASE_WEB_SERVICE__STATEMENT);
		createEAttribute(sybaseASEWebServiceEClass, SYBASE_ASE_WEB_SERVICE__REMARKS);
		createEReference(sybaseASEWebServiceEClass, SYBASE_ASE_WEB_SERVICE__DATABASE);

		sybaseASEPredefinedDataTypeEClass = createEClass(SYBASE_ASE_PREDEFINED_DATA_TYPE);
		createEReference(sybaseASEPredefinedDataTypeEClass, SYBASE_ASE_PREDEFINED_DATA_TYPE__DATABASE);

		sybaseASECatalogEClass = createEClass(SYBASE_ASE_CATALOG);
		createEReference(sybaseASECatalogEClass, SYBASE_ASE_CATALOG__SEGMENTS);
		createEReference(sybaseASECatalogEClass, SYBASE_ASE_CATALOG__DATA_DEVICES);
		createEReference(sybaseASECatalogEClass, SYBASE_ASE_CATALOG__LOG_DEVICES);
		createEAttribute(sybaseASECatalogEClass, SYBASE_ASE_CATALOG__OVERRIDE);
		createEAttribute(sybaseASECatalogEClass, SYBASE_ASE_CATALOG__DEFAULT_LOCATION);
		createEAttribute(sybaseASECatalogEClass, SYBASE_ASE_CATALOG__FOR_LOAD);
		createEAttribute(sybaseASECatalogEClass, SYBASE_ASE_CATALOG__FOR_PROXY_UPDATE);
		createEAttribute(sybaseASECatalogEClass, SYBASE_ASE_CATALOG__LOG_IO_SIZE);
		createEAttribute(sybaseASECatalogEClass, SYBASE_ASE_CATALOG__RECOVERY_ORDER);
		createEReference(sybaseASECatalogEClass, SYBASE_ASE_CATALOG__AUTHORIZATION_IDS);
		createEReference(sybaseASECatalogEClass, SYBASE_ASE_CATALOG__CACHE);
		createEAttribute(sybaseASECatalogEClass, SYBASE_ASE_CATALOG__CATALOG_TYPE);

		sybaseASEProcedureEClass = createEClass(SYBASE_ASE_PROCEDURE);
		createEAttribute(sybaseASEProcedureEClass, SYBASE_ASE_PROCEDURE__GROUP_NUMBER);
		createEAttribute(sybaseASEProcedureEClass, SYBASE_ASE_PROCEDURE__TRANSACTION_MODE);
		createEAttribute(sybaseASEProcedureEClass, SYBASE_ASE_PROCEDURE__SYSTEM_PROCEDURE);
		createEAttribute(sybaseASEProcedureEClass, SYBASE_ASE_PROCEDURE__WITH_RECOMPILE);

		sybaseASEDefaultEClass = createEClass(SYBASE_ASE_DEFAULT);
		createEReference(sybaseASEDefaultEClass, SYBASE_ASE_DEFAULT__SCHEMA);
		createEAttribute(sybaseASEDefaultEClass, SYBASE_ASE_DEFAULT__STATEMENT);

		sybaseASERuleEClass = createEClass(SYBASE_ASE_RULE);
		createEReference(sybaseASERuleEClass, SYBASE_ASE_RULE__SCHEMA);
		createEAttribute(sybaseASERuleEClass, SYBASE_ASE_RULE__STATEMENT);
		createEAttribute(sybaseASERuleEClass, SYBASE_ASE_RULE__ACCESS_RULE);
		createEAttribute(sybaseASERuleEClass, SYBASE_ASE_RULE__ACCESS_TYPE);

		sybaseASEIndexEClass = createEClass(SYBASE_ASE_INDEX);
		createEAttribute(sybaseASEIndexEClass, SYBASE_ASE_INDEX__MAX_ROW_PER_PAGE);
		createEAttribute(sybaseASEIndexEClass, SYBASE_ASE_INDEX__REVERSE_PAGE_GAP);
		createEAttribute(sybaseASEIndexEClass, SYBASE_ASE_INDEX__IGNORE_DUPLICATE_KEY);
		createEAttribute(sybaseASEIndexEClass, SYBASE_ASE_INDEX__SORTED_DATA);
		createEAttribute(sybaseASEIndexEClass, SYBASE_ASE_INDEX__IGNORE_DUPLICATE_ROW);
		createEReference(sybaseASEIndexEClass, SYBASE_ASE_INDEX__SEGMENT);
		createEAttribute(sybaseASEIndexEClass, SYBASE_ASE_INDEX__LOCAL_INDEX);
		createEReference(sybaseASEIndexEClass, SYBASE_ASE_INDEX__PARTITIONS);
		createEAttribute(sybaseASEIndexEClass, SYBASE_ASE_INDEX__CONSUMER_NUM);
		createEAttribute(sybaseASEIndexEClass, SYBASE_ASE_INDEX__STATISTICS_STEP);
		createEAttribute(sybaseASEIndexEClass, SYBASE_ASE_INDEX__ALLOW_DUPLICATE_ROW);
		createEAttribute(sybaseASEIndexEClass, SYBASE_ASE_INDEX__SUSPECT);
		createEReference(sybaseASEIndexEClass, SYBASE_ASE_INDEX__CACHE_INFO);

		sybaseASESegmentEClass = createEClass(SYBASE_ASE_SEGMENT);
		createEReference(sybaseASESegmentEClass, SYBASE_ASE_SEGMENT__CATALOG);
		createEAttribute(sybaseASESegmentEClass, SYBASE_ASE_SEGMENT__DEVICE_NAMES);
		createEReference(sybaseASESegmentEClass, SYBASE_ASE_SEGMENT__THRESHOLDS);

		sybaseASEFuncBasedIndexMemberEClass = createEClass(SYBASE_ASE_FUNC_BASED_INDEX_MEMBER);

		sybaseASETableEClass = createEClass(SYBASE_ASE_TABLE);

		sybaseASEColumnCheckConstraintEClass = createEClass(SYBASE_ASE_COLUMN_CHECK_CONSTRAINT);
		createEReference(sybaseASEColumnCheckConstraintEClass, SYBASE_ASE_COLUMN_CHECK_CONSTRAINT__COLUMN);

		sybaseASEColumnEClass = createEClass(SYBASE_ASE_COLUMN);
		createEReference(sybaseASEColumnEClass, SYBASE_ASE_COLUMN__COLUMN_CHECK);
		createEReference(sybaseASEColumnEClass, SYBASE_ASE_COLUMN__BOUND_DEFAULT);
		createEReference(sybaseASEColumnEClass, SYBASE_ASE_COLUMN__BOUND_RULE);
		createEAttribute(sybaseASEColumnEClass, SYBASE_ASE_COLUMN__MATERIALIZED);
		createEReference(sybaseASEColumnEClass, SYBASE_ASE_COLUMN__ENCRYPTION_KEY);
		createEAttribute(sybaseASEColumnEClass, SYBASE_ASE_COLUMN__BIND_DEFAULT_IN_FUTURE_ONLY);
		createEAttribute(sybaseASEColumnEClass, SYBASE_ASE_COLUMN__BIND_RULE_IN_FUTURE_ONLY);
		createEAttribute(sybaseASEColumnEClass, SYBASE_ASE_COLUMN__HIDDEN);

		sybaseASEUniqueConstraintEClass = createEClass(SYBASE_ASE_UNIQUE_CONSTRAINT);
		createEReference(sybaseASEUniqueConstraintEClass, SYBASE_ASE_UNIQUE_CONSTRAINT__SYSTEM_GENED_INDEX);
		createEAttribute(sybaseASEUniqueConstraintEClass, SYBASE_ASE_UNIQUE_CONSTRAINT__SYSTEM_GENED_NAME);

		sybaseASEPrimaryKeyEClass = createEClass(SYBASE_ASE_PRIMARY_KEY);
		createEReference(sybaseASEPrimaryKeyEClass, SYBASE_ASE_PRIMARY_KEY__ASE_UNIQUE_CONSTRAINT);

		deviceItemEClass = createEClass(DEVICE_ITEM);
		createEAttribute(deviceItemEClass, DEVICE_ITEM__DEVICE_NAME);
		createEAttribute(deviceItemEClass, DEVICE_ITEM__SIZE);

		segmentThresholdEClass = createEClass(SEGMENT_THRESHOLD);
		createEAttribute(segmentThresholdEClass, SEGMENT_THRESHOLD__PROCEDURE_NAME);
		createEAttribute(segmentThresholdEClass, SEGMENT_THRESHOLD__FREE_SPACE);

		cacheInfoEClass = createEClass(CACHE_INFO);
		createEAttribute(cacheInfoEClass, CACHE_INFO__CACHE_STRATEGY);
		createEReference(cacheInfoEClass, CACHE_INFO__CACHE);

		sybaseASEUserDefinedTypeEClass = createEClass(SYBASE_ASE_USER_DEFINED_TYPE);
		createEReference(sybaseASEUserDefinedTypeEClass, SYBASE_ASE_USER_DEFINED_TYPE__BOUND_DEFAULT);
		createEReference(sybaseASEUserDefinedTypeEClass, SYBASE_ASE_USER_DEFINED_TYPE__BOUND_RULE);
		createEAttribute(sybaseASEUserDefinedTypeEClass, SYBASE_ASE_USER_DEFINED_TYPE__BIND_DEFAULT_IN_FUTURE_ONLY);
		createEAttribute(sybaseASEUserDefinedTypeEClass, SYBASE_ASE_USER_DEFINED_TYPE__BIND_RULE_IN_FUTURE_ONLY);
		createEAttribute(sybaseASEUserDefinedTypeEClass, SYBASE_ASE_USER_DEFINED_TYPE__ALLOW_NULLS);
		createEAttribute(sybaseASEUserDefinedTypeEClass, SYBASE_ASE_USER_DEFINED_TYPE__IDENTITY);

		sybaseASEEncryptionKeyEClass = createEClass(SYBASE_ASE_ENCRYPTION_KEY);
		createEReference(sybaseASEEncryptionKeyEClass, SYBASE_ASE_ENCRYPTION_KEY__SCHEMA);

		lockPromotionInfoEClass = createEClass(LOCK_PROMOTION_INFO);
		createEAttribute(lockPromotionInfoEClass, LOCK_PROMOTION_INFO__ROW_LOCK_PROMOTION);
		createEAttribute(lockPromotionInfoEClass, LOCK_PROMOTION_INFO__LWM);
		createEAttribute(lockPromotionInfoEClass, LOCK_PROMOTION_INFO__HWM);
		createEAttribute(lockPromotionInfoEClass, LOCK_PROMOTION_INFO__PCT);

		sybaseASERoleEClass = createEClass(SYBASE_ASE_ROLE);

		sybaseASECacheEClass = createEClass(SYBASE_ASE_CACHE);
		createEReference(sybaseASECacheEClass, SYBASE_ASE_CACHE__DATABASE);

		sybaseASEViewTableEClass = createEClass(SYBASE_ASE_VIEW_TABLE);
		createEAttribute(sybaseASEViewTableEClass, SYBASE_ASE_VIEW_TABLE__WITH_CHECK_OPTION);

		sybaseASETempTableEClass = createEClass(SYBASE_ASE_TEMP_TABLE);

		sybaseASEProxyTableEClass = createEClass(SYBASE_ASE_PROXY_TABLE);
		createEAttribute(sybaseASEProxyTableEClass, SYBASE_ASE_PROXY_TABLE__EXTERNAL_TYPE);
		createEAttribute(sybaseASEProxyTableEClass, SYBASE_ASE_PROXY_TABLE__EXISTING);
		createEAttribute(sybaseASEProxyTableEClass, SYBASE_ASE_PROXY_TABLE__COLUMN_DELIMITER);
		createEAttribute(sybaseASEProxyTableEClass, SYBASE_ASE_PROXY_TABLE__EXTERNAL_PATH);

		sybaseASEWebServiceTableEClass = createEClass(SYBASE_ASE_WEB_SERVICE_TABLE);
		createEAttribute(sybaseASEWebServiceTableEClass, SYBASE_ASE_WEB_SERVICE_TABLE__METHOD);
		createEAttribute(sybaseASEWebServiceTableEClass, SYBASE_ASE_WEB_SERVICE_TABLE__WSDLURI);

		sybaseASEBaseTableEClass = createEClass(SYBASE_ASE_BASE_TABLE);
		createEAttribute(sybaseASEBaseTableEClass, SYBASE_ASE_BASE_TABLE__LOCK_SCHEMA);
		createEAttribute(sybaseASEBaseTableEClass, SYBASE_ASE_BASE_TABLE__FILL_FACTOR);
		createEAttribute(sybaseASEBaseTableEClass, SYBASE_ASE_BASE_TABLE__MAX_ROW_PER_PAGE);
		createEAttribute(sybaseASEBaseTableEClass, SYBASE_ASE_BASE_TABLE__EXP_ROW_SIZE);
		createEAttribute(sybaseASEBaseTableEClass, SYBASE_ASE_BASE_TABLE__RESERVE_PAGE_GAP);
		createEAttribute(sybaseASEBaseTableEClass, SYBASE_ASE_BASE_TABLE__IDENTITY_GAP);
		createEReference(sybaseASEBaseTableEClass, SYBASE_ASE_BASE_TABLE__SEGMENT);
		createEAttribute(sybaseASEBaseTableEClass, SYBASE_ASE_BASE_TABLE__CONCURRENCY_OPT_THRESHOLD);
		createEReference(sybaseASEBaseTableEClass, SYBASE_ASE_BASE_TABLE__PARTITION_CONDITION);
		createEReference(sybaseASEBaseTableEClass, SYBASE_ASE_BASE_TABLE__TABLE_ONLY_CACHE_INFO);
		createEReference(sybaseASEBaseTableEClass, SYBASE_ASE_BASE_TABLE__TEXT_ONLY_CACHE_INFO);
		createEReference(sybaseASEBaseTableEClass, SYBASE_ASE_BASE_TABLE__LOCK_PROMOTION);
		createEAttribute(sybaseASEBaseTableEClass, SYBASE_ASE_BASE_TABLE__PARTITIONS);
		createEReference(sybaseASEBaseTableEClass, SYBASE_ASE_BASE_TABLE__TEXT_IMAGE_SEGMENT);
		createEAttribute(sybaseASEBaseTableEClass, SYBASE_ASE_BASE_TABLE__SYSTEM_TABLE);

		sybaseASEUserEClass = createEClass(SYBASE_ASE_USER);
		createEAttribute(sybaseASEUserEClass, SYBASE_ASE_USER__LOGIN_NAME);

		sybaseASEGroupEClass = createEClass(SYBASE_ASE_GROUP);

		sybaseASEPrivilegeEClass = createEClass(SYBASE_ASE_PRIVILEGE);

		sybaseASETriggerEClass = createEClass(SYBASE_ASE_TRIGGER);
		createEAttribute(sybaseASETriggerEClass, SYBASE_ASE_TRIGGER__ENABLED);

		sybaseASECheckConstraintEClass = createEClass(SYBASE_ASE_CHECK_CONSTRAINT);
		createEReference(sybaseASECheckConstraintEClass, SYBASE_ASE_CHECK_CONSTRAINT__CREATOR);

		// Create enums
		transactionModeTypeEEnum = createEEnum(TRANSACTION_MODE_TYPE);
		cacheStrategyTypeEEnum = createEEnum(CACHE_STRATEGY_TYPE);
		lockingSchemaTypeEEnum = createEEnum(LOCKING_SCHEMA_TYPE);
		proxyTableExternalTypeEEnum = createEEnum(PROXY_TABLE_EXTERNAL_TYPE);
		accessRuleTypeEEnum = createEEnum(ACCESS_RULE_TYPE);
		sybaseASECatalogTypeEEnum = createEEnum(SYBASE_ASE_CATALOG_TYPE);
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
		PartitionPackage thePartitionPackage = (PartitionPackage)EPackage.Registry.INSTANCE.getEPackage(PartitionPackage.eNS_URI);
		SQLSchemaPackage theSQLSchemaPackage = (SQLSchemaPackage)EPackage.Registry.INSTANCE.getEPackage(SQLSchemaPackage.eNS_URI);
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
		SQLDataTypesPackage theSQLDataTypesPackage = (SQLDataTypesPackage)EPackage.Registry.INSTANCE.getEPackage(SQLDataTypesPackage.eNS_URI);
		SQLAccessControlPackage theSQLAccessControlPackage = (SQLAccessControlPackage)EPackage.Registry.INSTANCE.getEPackage(SQLAccessControlPackage.eNS_URI);
		SQLRoutinesPackage theSQLRoutinesPackage = (SQLRoutinesPackage)EPackage.Registry.INSTANCE.getEPackage(SQLRoutinesPackage.eNS_URI);
		SybasesqlmodelPackage theSybasesqlmodelPackage = (SybasesqlmodelPackage)EPackage.Registry.INSTANCE.getEPackage(SybasesqlmodelPackage.eNS_URI);
		SQLConstraintsPackage theSQLConstraintsPackage = (SQLConstraintsPackage)EPackage.Registry.INSTANCE.getEPackage(SQLConstraintsPackage.eNS_URI);
		SQLTablesPackage theSQLTablesPackage = (SQLTablesPackage)EPackage.Registry.INSTANCE.getEPackage(SQLTablesPackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(thePartitionPackage);

		// Add supertypes to classes
		sybaseASESchemaEClass.getESuperTypes().add(theSQLSchemaPackage.getSchema());
		sybaseASESchemaEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		sybaseASEDatabaseEClass.getESuperTypes().add(theSQLSchemaPackage.getDatabase());
		sybaseASEDatabaseEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		sybaseASEWebServiceEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		sybaseASEPredefinedDataTypeEClass.getESuperTypes().add(theSQLDataTypesPackage.getPredefinedDataType());
		sybaseASEPredefinedDataTypeEClass.getESuperTypes().add(theSQLDataTypesPackage.getSQLDataType());
		sybaseASEPredefinedDataTypeEClass.getESuperTypes().add(theSQLDataTypesPackage.getDataType());
		sybaseASEPredefinedDataTypeEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		sybaseASECatalogEClass.getESuperTypes().add(theSQLSchemaPackage.getCatalog());
		sybaseASECatalogEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		sybaseASEProcedureEClass.getESuperTypes().add(theSQLRoutinesPackage.getProcedure());
		sybaseASEProcedureEClass.getESuperTypes().add(theSybasesqlmodelPackage.getSybaseRoutine());
		sybaseASEDefaultEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		sybaseASERuleEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		sybaseASEIndexEClass.getESuperTypes().add(theSQLConstraintsPackage.getIndex());
		sybaseASEIndexEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		sybaseASESegmentEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		sybaseASEFuncBasedIndexMemberEClass.getESuperTypes().add(theSybasesqlmodelPackage.getSybaseIndexMember());
		sybaseASETableEClass.getESuperTypes().add(theSQLTablesPackage.getPersistentTable());
		sybaseASETableEClass.getESuperTypes().add(this.getSybaseASEBaseTable());
		sybaseASEColumnCheckConstraintEClass.getESuperTypes().add(this.getSybaseASECheckConstraint());
		sybaseASEColumnEClass.getESuperTypes().add(theSQLTablesPackage.getColumn());
		sybaseASEColumnEClass.getESuperTypes().add(theSybasesqlmodelPackage.getSybaseAuthorizedObject());
		sybaseASEUniqueConstraintEClass.getESuperTypes().add(theSQLConstraintsPackage.getUniqueConstraint());
		sybaseASEPrimaryKeyEClass.getESuperTypes().add(theSQLConstraintsPackage.getPrimaryKey());
		deviceItemEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		segmentThresholdEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		cacheInfoEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		sybaseASEUserDefinedTypeEClass.getESuperTypes().add(theSQLDataTypesPackage.getDistinctUserDefinedType());
		sybaseASEEncryptionKeyEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		lockPromotionInfoEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		sybaseASERoleEClass.getESuperTypes().add(theSQLAccessControlPackage.getRole());
		sybaseASERoleEClass.getESuperTypes().add(theSybasesqlmodelPackage.getSybaseAuthorizationIdentifier());
		sybaseASECacheEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());
		sybaseASEViewTableEClass.getESuperTypes().add(theSybasesqlmodelPackage.getSybaseViewTable());
		sybaseASETempTableEClass.getESuperTypes().add(theSQLTablesPackage.getTemporaryTable());
		sybaseASETempTableEClass.getESuperTypes().add(this.getSybaseASEBaseTable());
		sybaseASEProxyTableEClass.getESuperTypes().add(this.getSybaseASETable());
		sybaseASEWebServiceTableEClass.getESuperTypes().add(this.getSybaseASEProxyTable());
		sybaseASEBaseTableEClass.getESuperTypes().add(theSybasesqlmodelPackage.getSybaseBaseTable());
		sybaseASEUserEClass.getESuperTypes().add(theSQLAccessControlPackage.getUser());
		sybaseASEUserEClass.getESuperTypes().add(theSybasesqlmodelPackage.getSybaseAuthorizationIdentifier());
		sybaseASEGroupEClass.getESuperTypes().add(theSQLAccessControlPackage.getGroup());
		sybaseASEGroupEClass.getESuperTypes().add(theSybasesqlmodelPackage.getSybaseAuthorizationIdentifier());
		sybaseASEPrivilegeEClass.getESuperTypes().add(theSybasesqlmodelPackage.getSybasePrivilege());
		sybaseASETriggerEClass.getESuperTypes().add(theSQLTablesPackage.getTrigger());
		sybaseASECheckConstraintEClass.getESuperTypes().add(theSQLConstraintsPackage.getCheckConstraint());
		sybaseASECheckConstraintEClass.getESuperTypes().add(theSQLConstraintsPackage.getTableConstraint());
		sybaseASECheckConstraintEClass.getESuperTypes().add(theSQLConstraintsPackage.getConstraint());
		sybaseASECheckConstraintEClass.getESuperTypes().add(theSQLSchemaPackage.getSQLObject());

		// Initialize classes and features; add operations and parameters
		initEClass(sybaseASESchemaEClass, SybaseASESchema.class, "SybaseASESchema", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSybaseASESchema_Defaults(), this.getSybaseASEDefault(), this.getSybaseASEDefault_Schema(), "defaults", null, 0, -1, SybaseASESchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSybaseASESchema_Rules(), this.getSybaseASERule(), this.getSybaseASERule_Schema(), "rules", null, 0, -1, SybaseASESchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSybaseASESchema_EncryptionKeys(), this.getSybaseASEEncryptionKey(), this.getSybaseASEEncryptionKey_Schema(), "encryptionKeys", null, 0, -1, SybaseASESchema.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		addEOperation(sybaseASESchemaEClass, theSQLSchemaPackage.getList(), "getWebServicesAsTables", 0, 1); //$NON-NLS-1$

		addEOperation(sybaseASESchemaEClass, theSQLSchemaPackage.getList(), "getNormalTables", 0, 1); //$NON-NLS-1$

		addEOperation(sybaseASESchemaEClass, theSQLSchemaPackage.getList(), "getProxyTables", 0, 1); //$NON-NLS-1$

		addEOperation(sybaseASESchemaEClass, theSQLSchemaPackage.getList(), "getViewTables", 0, 1); //$NON-NLS-1$

		addEOperation(sybaseASESchemaEClass, theSQLSchemaPackage.getList(), "getSystemProcedures", 0, 1); //$NON-NLS-1$

		addEOperation(sybaseASESchemaEClass, theSQLSchemaPackage.getList(), "getSystemTables", 0, 1); //$NON-NLS-1$

		addEOperation(sybaseASESchemaEClass, theSQLSchemaPackage.getList(), "getSystemAndNormalTable", 0, 1); //$NON-NLS-1$

		initEClass(sybaseASEDatabaseEClass, SybaseASEDatabase.class, "SybaseASEDatabase", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSybaseASEDatabase_DataTypes(), this.getSybaseASEPredefinedDataType(), this.getSybaseASEPredefinedDataType_Database(), "dataTypes", null, 0, -1, SybaseASEDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEDatabase_EncryptionKeyApplicable(), ecorePackage.getEBoolean(), "encryptionKeyApplicable", null, 1, 1, SybaseASEDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSybaseASEDatabase_Roles(), this.getSybaseASERole(), null, "roles", null, 0, -1, SybaseASEDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSybaseASEDatabase_Caches(), this.getSybaseASECache(), this.getSybaseASECache_Database(), "caches", null, 1, -1, SybaseASEDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSybaseASEDatabase_WebServices(), this.getSybaseASEWebService(), this.getSybaseASEWebService_Database(), "webServices", null, 0, -1, SybaseASEDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEDatabase_WebserviceApplicable(), ecorePackage.getEBoolean(), "webserviceApplicable", null, 1, 1, SybaseASEDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEDatabase_SdsServer(), ecorePackage.getEString(), "sdsServer", null, 0, -1, SybaseASEDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEDatabase_TempDBName(), theEcorePackage.getEString(), "tempDBName", null, 0, 1, SybaseASEDatabase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		addEOperation(sybaseASEDatabaseEClass, theEcorePackage.getEBoolean(), "isComputedColApplicable", 0, 1); //$NON-NLS-1$

		addEOperation(sybaseASEDatabaseEClass, theEcorePackage.getEBoolean(), "isPartitionsApplicable", 0, 1); //$NON-NLS-1$

		addEOperation(sybaseASEDatabaseEClass, ecorePackage.getEBoolean(), "isFunctionalBasedIndexMemApplicable", 0, 1); //$NON-NLS-1$

		initEClass(sybaseASEWebServiceEClass, SybaseASEWebService.class, "SybaseASEWebService", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getSybaseASEWebService_Service_id(), ecorePackage.getELong(), "service_id", null, 0, 1, SybaseASEWebService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEWebService_Service_type(), ecorePackage.getEString(), "service_type", null, 0, 1, SybaseASEWebService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEWebService_Auth_required(), ecorePackage.getEString(), "auth_required", null, 0, 1, SybaseASEWebService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEWebService_Secure_required(), ecorePackage.getEString(), "secure_required", null, 0, 1, SybaseASEWebService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEWebService_Url_path(), ecorePackage.getEString(), "url_path", null, 0, 1, SybaseASEWebService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEWebService_User_name(), theEcorePackage.getEString(), "user_name", null, 0, 1, SybaseASEWebService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEWebService_Parameter(), ecorePackage.getEString(), "parameter", null, 0, 1, SybaseASEWebService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEWebService_Statement(), ecorePackage.getEString(), "statement", null, 0, 1, SybaseASEWebService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEWebService_Remarks(), ecorePackage.getEString(), "remarks", null, 0, 1, SybaseASEWebService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSybaseASEWebService_Database(), this.getSybaseASEDatabase(), this.getSybaseASEDatabase_WebServices(), "database", null, 1, 1, SybaseASEWebService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(sybaseASEPredefinedDataTypeEClass, SybaseASEPredefinedDataType.class, "SybaseASEPredefinedDataType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSybaseASEPredefinedDataType_Database(), this.getSybaseASEDatabase(), this.getSybaseASEDatabase_DataTypes(), "database", null, 0, 1, SybaseASEPredefinedDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(sybaseASECatalogEClass, SybaseASECatalog.class, "SybaseASECatalog", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSybaseASECatalog_Segments(), this.getSybaseASESegment(), this.getSybaseASESegment_Catalog(), "segments", null, 0, -1, SybaseASECatalog.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSybaseASECatalog_DataDevices(), this.getDeviceItem(), null, "dataDevices", null, 0, -1, SybaseASECatalog.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSybaseASECatalog_LogDevices(), this.getDeviceItem(), null, "logDevices", null, 0, -1, SybaseASECatalog.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASECatalog_Override(), theEcorePackage.getEBoolean(), "override", null, 0, 1, SybaseASECatalog.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASECatalog_DefaultLocation(), ecorePackage.getEString(), "defaultLocation", null, 0, 1, SybaseASECatalog.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASECatalog_ForLoad(), theEcorePackage.getEBoolean(), "forLoad", null, 0, 1, SybaseASECatalog.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASECatalog_ForProxyUpdate(), theEcorePackage.getEBoolean(), "forProxyUpdate", null, 0, 1, SybaseASECatalog.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASECatalog_LogIOSize(), ecorePackage.getEInt(), "logIOSize", null, 0, 1, SybaseASECatalog.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASECatalog_RecoveryOrder(), ecorePackage.getEInt(), "recoveryOrder", null, 0, 1, SybaseASECatalog.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSybaseASECatalog_AuthorizationIds(), theSQLAccessControlPackage.getAuthorizationIdentifier(), null, "authorizationIds", null, 0, -1, SybaseASECatalog.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSybaseASECatalog_Cache(), this.getSybaseASECache(), null, "cache", null, 0, 1, SybaseASECatalog.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASECatalog_CatalogType(), this.getSybaseASECatalogType(), "catalogType", null, 0, 1, SybaseASECatalog.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(sybaseASEProcedureEClass, SybaseASEProcedure.class, "SybaseASEProcedure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getSybaseASEProcedure_GroupNumber(), ecorePackage.getEInt(), "groupNumber", "-1", 0, 1, SybaseASEProcedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getSybaseASEProcedure_TransactionMode(), this.getTransactionModeType(), "transactionMode", null, 0, 1, SybaseASEProcedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEProcedure_SystemProcedure(), ecorePackage.getEBoolean(), "systemProcedure", null, 0, 1, SybaseASEProcedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEProcedure_WithRecompile(), ecorePackage.getEBoolean(), "withRecompile", null, 0, 1, SybaseASEProcedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(sybaseASEDefaultEClass, SybaseASEDefault.class, "SybaseASEDefault", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSybaseASEDefault_Schema(), this.getSybaseASESchema(), this.getSybaseASESchema_Defaults(), "schema", null, 1, 1, SybaseASEDefault.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEDefault_Statement(), ecorePackage.getEString(), "statement", null, 0, 1, SybaseASEDefault.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(sybaseASERuleEClass, SybaseASERule.class, "SybaseASERule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSybaseASERule_Schema(), this.getSybaseASESchema(), this.getSybaseASESchema_Rules(), "schema", null, 1, 1, SybaseASERule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASERule_Statement(), ecorePackage.getEString(), "statement", null, 0, 1, SybaseASERule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASERule_AccessRule(), theEcorePackage.getEBoolean(), "accessRule", null, 0, 1, SybaseASERule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASERule_AccessType(), this.getAccessRuleType(), "accessType", "DEF", 0, 1, SybaseASERule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(sybaseASEIndexEClass, SybaseASEIndex.class, "SybaseASEIndex", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getSybaseASEIndex_MaxRowPerPage(), ecorePackage.getEInt(), "maxRowPerPage", "-1", 0, 1, SybaseASEIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getSybaseASEIndex_ReversePageGap(), ecorePackage.getEInt(), "reversePageGap", "-1", 0, 1, SybaseASEIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getSybaseASEIndex_IgnoreDuplicateKey(), ecorePackage.getEBoolean(), "ignoreDuplicateKey", null, 0, 1, SybaseASEIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEIndex_SortedData(), ecorePackage.getEBoolean(), "sortedData", null, 0, 1, SybaseASEIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEIndex_IgnoreDuplicateRow(), ecorePackage.getEBoolean(), "ignoreDuplicateRow", null, 0, 1, SybaseASEIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSybaseASEIndex_Segment(), this.getSybaseASESegment(), null, "segment", null, 0, 1, SybaseASEIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEIndex_LocalIndex(), ecorePackage.getEBoolean(), "localIndex", null, 0, 1, SybaseASEIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSybaseASEIndex_Partitions(), thePartitionPackage.getPartitionSegmentPair(), null, "partitions", null, 0, -1, SybaseASEIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEIndex_ConsumerNum(), ecorePackage.getEInt(), "consumerNum", "-1", 0, 1, SybaseASEIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getSybaseASEIndex_StatisticsStep(), ecorePackage.getEInt(), "statisticsStep", "-1", 0, 1, SybaseASEIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getSybaseASEIndex_AllowDuplicateRow(), ecorePackage.getEBoolean(), "allowDuplicateRow", null, 0, 1, SybaseASEIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEIndex_Suspect(), ecorePackage.getEBoolean(), "suspect", null, 0, 1, SybaseASEIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSybaseASEIndex_CacheInfo(), this.getCacheInfo(), null, "cacheInfo", null, 0, 1, SybaseASEIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(sybaseASESegmentEClass, SybaseASESegment.class, "SybaseASESegment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSybaseASESegment_Catalog(), this.getSybaseASECatalog(), this.getSybaseASECatalog_Segments(), "catalog", null, 0, 1, SybaseASESegment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASESegment_DeviceNames(), ecorePackage.getEString(), "deviceNames", null, 1, -1, SybaseASESegment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSybaseASESegment_Thresholds(), this.getSegmentThreshold(), null, "thresholds", null, 0, -1, SybaseASESegment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(sybaseASEFuncBasedIndexMemberEClass, SybaseASEFuncBasedIndexMember.class, "SybaseASEFuncBasedIndexMember", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(sybaseASETableEClass, SybaseASETable.class, "SybaseASETable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(sybaseASEColumnCheckConstraintEClass, SybaseASEColumnCheckConstraint.class, "SybaseASEColumnCheckConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSybaseASEColumnCheckConstraint_Column(), this.getSybaseASEColumn(), this.getSybaseASEColumn_ColumnCheck(), "column", null, 1, 1, SybaseASEColumnCheckConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(sybaseASEColumnEClass, SybaseASEColumn.class, "SybaseASEColumn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSybaseASEColumn_ColumnCheck(), this.getSybaseASEColumnCheckConstraint(), this.getSybaseASEColumnCheckConstraint_Column(), "columnCheck", null, 0, 1, SybaseASEColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSybaseASEColumn_BoundDefault(), this.getSybaseASEDefault(), null, "boundDefault", null, 0, 1, SybaseASEColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSybaseASEColumn_BoundRule(), this.getSybaseASERule(), null, "boundRule", null, 0, 1, SybaseASEColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEColumn_Materialized(), theEcorePackage.getEBoolean(), "materialized", null, 0, 1, SybaseASEColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSybaseASEColumn_EncryptionKey(), this.getSybaseASEEncryptionKey(), null, "encryptionKey", null, 0, 1, SybaseASEColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEColumn_BindDefaultInFutureOnly(), theEcorePackage.getEBoolean(), "bindDefaultInFutureOnly", null, 0, 1, SybaseASEColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEColumn_BindRuleInFutureOnly(), theEcorePackage.getEBoolean(), "bindRuleInFutureOnly", null, 0, 1, SybaseASEColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEColumn_Hidden(), ecorePackage.getEBoolean(), "hidden", "false", 0, 1, SybaseASEColumn.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		addEOperation(sybaseASEColumnEClass, ecorePackage.getEBoolean(), "isComputedColumn", 0, 1); //$NON-NLS-1$

		initEClass(sybaseASEUniqueConstraintEClass, SybaseASEUniqueConstraint.class, "SybaseASEUniqueConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSybaseASEUniqueConstraint_SystemGenedIndex(), this.getSybaseASEIndex(), null, "systemGenedIndex", null, 1, 1, SybaseASEUniqueConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEUniqueConstraint_SystemGenedName(), theEcorePackage.getEBoolean(), "systemGenedName", null, 0, 1, SybaseASEUniqueConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(sybaseASEPrimaryKeyEClass, SybaseASEPrimaryKey.class, "SybaseASEPrimaryKey", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSybaseASEPrimaryKey_AseUniqueConstraint(), this.getSybaseASEUniqueConstraint(), null, "aseUniqueConstraint", null, 1, 1, SybaseASEPrimaryKey.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(deviceItemEClass, DeviceItem.class, "DeviceItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDeviceItem_DeviceName(), theEcorePackage.getEString(), "deviceName", null, 0, 1, DeviceItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getDeviceItem_Size(), ecorePackage.getEInt(), "size", "0", 1, 1, DeviceItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(segmentThresholdEClass, SegmentThreshold.class, "SegmentThreshold", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getSegmentThreshold_ProcedureName(), theEcorePackage.getEString(), "procedureName", null, 0, 1, SegmentThreshold.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSegmentThreshold_FreeSpace(), ecorePackage.getEInt(), "freeSpace", "0", 1, 1, SegmentThreshold.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(cacheInfoEClass, CacheInfo.class, "CacheInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getCacheInfo_CacheStrategy(), theEcorePackage.getEInt(), "cacheStrategy", null, 0, 1, CacheInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getCacheInfo_Cache(), this.getSybaseASECache(), null, "cache", null, 0, 1, CacheInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(sybaseASEUserDefinedTypeEClass, SybaseASEUserDefinedType.class, "SybaseASEUserDefinedType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSybaseASEUserDefinedType_BoundDefault(), this.getSybaseASEDefault(), null, "boundDefault", null, 0, 1, SybaseASEUserDefinedType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSybaseASEUserDefinedType_BoundRule(), this.getSybaseASERule(), null, "boundRule", null, 0, 1, SybaseASEUserDefinedType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEUserDefinedType_BindDefaultInFutureOnly(), theEcorePackage.getEBoolean(), "bindDefaultInFutureOnly", null, 0, 1, SybaseASEUserDefinedType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEUserDefinedType_BindRuleInFutureOnly(), theEcorePackage.getEBoolean(), "bindRuleInFutureOnly", null, 0, 1, SybaseASEUserDefinedType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEUserDefinedType_AllowNulls(), theEcorePackage.getEBoolean(), "allowNulls", null, 0, 1, SybaseASEUserDefinedType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEUserDefinedType_Identity(), theEcorePackage.getEBoolean(), "identity", null, 0, 1, SybaseASEUserDefinedType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(sybaseASEEncryptionKeyEClass, SybaseASEEncryptionKey.class, "SybaseASEEncryptionKey", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSybaseASEEncryptionKey_Schema(), this.getSybaseASESchema(), this.getSybaseASESchema_EncryptionKeys(), "schema", null, 0, 1, SybaseASEEncryptionKey.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(lockPromotionInfoEClass, LockPromotionInfo.class, "LockPromotionInfo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLockPromotionInfo_RowLockPromotion(), theEcorePackage.getEBoolean(), "rowLockPromotion", null, 0, 1, LockPromotionInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLockPromotionInfo_LWM(), ecorePackage.getEInt(), "LWM", null, 0, 1, LockPromotionInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLockPromotionInfo_HWM(), ecorePackage.getEInt(), "HWM", null, 0, 1, LockPromotionInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getLockPromotionInfo_PCT(), ecorePackage.getEInt(), "PCT", null, 0, 1, LockPromotionInfo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(sybaseASERoleEClass, SybaseASERole.class, "SybaseASERole", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		EOperation op = addEOperation(sybaseASERoleEClass, theSQLSchemaPackage.getList(), "getReceivedPrivileges", 0, 1); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEString(), "catalogName", 1, 1); //$NON-NLS-1$

		initEClass(sybaseASECacheEClass, SybaseASECache.class, "SybaseASECache", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSybaseASECache_Database(), this.getSybaseASEDatabase(), this.getSybaseASEDatabase_Caches(), "database", null, 1, 1, SybaseASECache.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(sybaseASEViewTableEClass, SybaseASEViewTable.class, "SybaseASEViewTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getSybaseASEViewTable_WithCheckOption(), ecorePackage.getEBoolean(), "withCheckOption", null, 0, 1, SybaseASEViewTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(sybaseASETempTableEClass, SybaseASETempTable.class, "SybaseASETempTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(sybaseASEProxyTableEClass, SybaseASEProxyTable.class, "SybaseASEProxyTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getSybaseASEProxyTable_ExternalType(), this.getProxyTableExternalType(), "externalType", null, 0, 1, SybaseASEProxyTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEProxyTable_Existing(), ecorePackage.getEBoolean(), "existing", null, 0, 1, SybaseASEProxyTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEProxyTable_ColumnDelimiter(), ecorePackage.getEString(), "columnDelimiter", null, 0, 1, SybaseASEProxyTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEProxyTable_ExternalPath(), ecorePackage.getEString(), "externalPath", null, 0, 1, SybaseASEProxyTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(sybaseASEWebServiceTableEClass, SybaseASEWebServiceTable.class, "SybaseASEWebServiceTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getSybaseASEWebServiceTable_Method(), theEcorePackage.getEString(), "method", "", 0, 1, SybaseASEWebServiceTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getSybaseASEWebServiceTable_WSDLURI(), theEcorePackage.getEString(), "WSDLURI", null, 0, 1, SybaseASEWebServiceTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(sybaseASEBaseTableEClass, SybaseASEBaseTable.class, "SybaseASEBaseTable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getSybaseASEBaseTable_LockSchema(), this.getLockingSchemaType(), "lockSchema", null, 0, 1, SybaseASEBaseTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEBaseTable_FillFactor(), ecorePackage.getEInt(), "fillFactor", "-1", 0, 1, SybaseASEBaseTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getSybaseASEBaseTable_MaxRowPerPage(), ecorePackage.getEInt(), "maxRowPerPage", "-1", 0, 1, SybaseASEBaseTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getSybaseASEBaseTable_ExpRowSize(), ecorePackage.getEInt(), "expRowSize", "-1", 0, 1, SybaseASEBaseTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getSybaseASEBaseTable_ReservePageGap(), ecorePackage.getEInt(), "reservePageGap", "-1", 0, 1, SybaseASEBaseTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getSybaseASEBaseTable_IdentityGap(), theEcorePackage.getEInt(), "identityGap", "-1", 0, 1, SybaseASEBaseTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getSybaseASEBaseTable_Segment(), this.getSybaseASESegment(), null, "segment", null, 1, 1, SybaseASEBaseTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEBaseTable_ConcurrencyOptThreshold(), ecorePackage.getEInt(), "concurrencyOptThreshold", null, 0, 1, SybaseASEBaseTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSybaseASEBaseTable_PartitionCondition(), thePartitionPackage.getSybaseASEPartition(), null, "partitionCondition", null, 0, 1, SybaseASEBaseTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSybaseASEBaseTable_TableOnlyCacheInfo(), this.getCacheInfo(), null, "tableOnlyCacheInfo", null, 0, 1, SybaseASEBaseTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSybaseASEBaseTable_TextOnlyCacheInfo(), this.getCacheInfo(), null, "textOnlyCacheInfo", null, 0, 1, SybaseASEBaseTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSybaseASEBaseTable_LockPromotion(), this.getLockPromotionInfo(), null, "lockPromotion", null, 0, -1, SybaseASEBaseTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEBaseTable_Partitions(), theEcorePackage.getEInt(), "partitions", "-1", 0, 1, SybaseASEBaseTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getSybaseASEBaseTable_TextImageSegment(), this.getSybaseASESegment(), null, "textImageSegment", null, 0, 1, SybaseASEBaseTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSybaseASEBaseTable_SystemTable(), ecorePackage.getEBoolean(), "systemTable", null, 0, 1, SybaseASEBaseTable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(sybaseASEUserEClass, SybaseASEUser.class, "SybaseASEUser", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getSybaseASEUser_LoginName(), ecorePackage.getEString(), "loginName", "", 0, 1, SybaseASEUser.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(sybaseASEGroupEClass, SybaseASEGroup.class, "SybaseASEGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(sybaseASEPrivilegeEClass, SybaseASEPrivilege.class, "SybaseASEPrivilege", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(sybaseASETriggerEClass, SybaseASETrigger.class, "SybaseASETrigger", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getSybaseASETrigger_Enabled(), ecorePackage.getEBoolean(), "enabled", null, 0, 1, SybaseASETrigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(sybaseASECheckConstraintEClass, SybaseASECheckConstraint.class, "SybaseASECheckConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSybaseASECheckConstraint_Creator(), theSQLSchemaPackage.getSchema(), null, "creator", null, 1, 1, SybaseASECheckConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		// Initialize enums and add enum literals
		initEEnum(transactionModeTypeEEnum, TransactionModeType.class, "TransactionModeType"); //$NON-NLS-1$
		addEEnumLiteral(transactionModeTypeEEnum, TransactionModeType.CHAINED_LITERAL);
		addEEnumLiteral(transactionModeTypeEEnum, TransactionModeType.UNCHAINED_LITERAL);
		addEEnumLiteral(transactionModeTypeEEnum, TransactionModeType.ANYMODE_LITERAL);

		initEEnum(cacheStrategyTypeEEnum, CacheStrategyType.class, "CacheStrategyType"); //$NON-NLS-1$
		addEEnumLiteral(cacheStrategyTypeEEnum, CacheStrategyType.PREFETCH_LITERAL);
		addEEnumLiteral(cacheStrategyTypeEEnum, CacheStrategyType.MRU_LITERAL);
		addEEnumLiteral(cacheStrategyTypeEEnum, CacheStrategyType.MRU_SERVER_DEFAULT_LITERAL);
		addEEnumLiteral(cacheStrategyTypeEEnum, CacheStrategyType.PREFETCH_SERVER_DEFAULT_LITERAL);

		initEEnum(lockingSchemaTypeEEnum, LockingSchemaType.class, "LockingSchemaType"); //$NON-NLS-1$
		addEEnumLiteral(lockingSchemaTypeEEnum, LockingSchemaType.LOCKDATAROWS_LITERAL);
		addEEnumLiteral(lockingSchemaTypeEEnum, LockingSchemaType.LOCKDATAPAGES_LITERAL);
		addEEnumLiteral(lockingSchemaTypeEEnum, LockingSchemaType.LOCKALLPAGES_LITERAL);
		addEEnumLiteral(lockingSchemaTypeEEnum, LockingSchemaType.SERVERDEFAULT_LITERAL);

		initEEnum(proxyTableExternalTypeEEnum, ProxyTableExternalType.class, "ProxyTableExternalType"); //$NON-NLS-1$
		addEEnumLiteral(proxyTableExternalTypeEEnum, ProxyTableExternalType.TABLE_LITERAL);
		addEEnumLiteral(proxyTableExternalTypeEEnum, ProxyTableExternalType.PROCEDURE_LITERAL);
		addEEnumLiteral(proxyTableExternalTypeEEnum, ProxyTableExternalType.FILE_LITERAL);
		addEEnumLiteral(proxyTableExternalTypeEEnum, ProxyTableExternalType.DIRECTORY_LITERAL);

		initEEnum(accessRuleTypeEEnum, AccessRuleType.class, "AccessRuleType"); //$NON-NLS-1$
		addEEnumLiteral(accessRuleTypeEEnum, AccessRuleType.DEF_LITERAL);
		addEEnumLiteral(accessRuleTypeEEnum, AccessRuleType.AND_LITERAL);
		addEEnumLiteral(accessRuleTypeEEnum, AccessRuleType.OR_LITERAL);

		initEEnum(sybaseASECatalogTypeEEnum, SybaseASECatalogType.class, "SybaseASECatalogType"); //$NON-NLS-1$
		addEEnumLiteral(sybaseASECatalogTypeEEnum, SybaseASECatalogType.PROXYCATALOG_LITERAL);
		addEEnumLiteral(sybaseASECatalogTypeEEnum, SybaseASECatalogType.TEMPCATALOG_LITERAL);
		addEEnumLiteral(sybaseASECatalogTypeEEnum, SybaseASECatalogType.USERCATALOG_LITERAL);

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
		String source = "GenModel"; //$NON-NLS-1$									
		addAnnotation
		  (sybaseASETempTableEClass, 
		   source, 
		   new String[] {
			 "documentation", "We don\'t want the temporary table to extends persistent table, as a result\r\nit can not inherit SybaseASETable. It\'s only used for nonsharable temp table." //$NON-NLS-1$ //$NON-NLS-2$
		   });		
		addAnnotation
		  (sybaseASEBaseTableEClass, 
		   source, 
		   new String[] {
			 "documentation", "Store attributes shared by SybaseASETable and SybaseASETempTable" //$NON-NLS-1$ //$NON-NLS-2$
		   });	
	}

} //SybaseasesqlmodelPackageImpl

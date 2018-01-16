/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseasesqlmodelFactoryImpl.java,v 1.15 2008/02/19 04:30:40 renj Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.*;

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
public class SybaseasesqlmodelFactoryImpl extends EFactoryImpl implements SybaseasesqlmodelFactory
{
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static SybaseasesqlmodelFactory init()
    {
        try
        {
            SybaseasesqlmodelFactory theSybaseasesqlmodelFactory = (SybaseasesqlmodelFactory)EPackage.Registry.INSTANCE.getEFactory("http:///org/eclipse/datatools/connectivity/sqm/sybase/ase/sybaseasesqlmodel.ecore"); //$NON-NLS-1$ 
            if (theSybaseasesqlmodelFactory != null)
            {
                return theSybaseasesqlmodelFactory;
            }
        }
        catch (Exception exception)
        {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new SybaseasesqlmodelFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseasesqlmodelFactoryImpl()
    {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EObject create(EClass eClass)
    {
        switch (eClass.getClassifierID())
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA: return createSybaseASESchema();
            case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE: return createSybaseASEDatabase();
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE: return createSybaseASEWebService();
            case SybaseasesqlmodelPackage.SYBASE_ASE_PREDEFINED_DATA_TYPE: return createSybaseASEPredefinedDataType();
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG: return createSybaseASECatalog();
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROCEDURE: return createSybaseASEProcedure();
            case SybaseasesqlmodelPackage.SYBASE_ASE_DEFAULT: return createSybaseASEDefault();
            case SybaseasesqlmodelPackage.SYBASE_ASE_RULE: return createSybaseASERule();
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX: return createSybaseASEIndex();
            case SybaseasesqlmodelPackage.SYBASE_ASE_SEGMENT: return createSybaseASESegment();
            case SybaseasesqlmodelPackage.SYBASE_ASE_FUNC_BASED_INDEX_MEMBER: return createSybaseASEFuncBasedIndexMember();
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE: return createSybaseASETable();
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN_CHECK_CONSTRAINT: return createSybaseASEColumnCheckConstraint();
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN: return createSybaseASEColumn();
            case SybaseasesqlmodelPackage.SYBASE_ASE_UNIQUE_CONSTRAINT: return createSybaseASEUniqueConstraint();
            case SybaseasesqlmodelPackage.SYBASE_ASE_PRIMARY_KEY: return createSybaseASEPrimaryKey();
            case SybaseasesqlmodelPackage.DEVICE_ITEM: return createDeviceItem();
            case SybaseasesqlmodelPackage.SEGMENT_THRESHOLD: return createSegmentThreshold();
            case SybaseasesqlmodelPackage.CACHE_INFO: return createCacheInfo();
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE: return createSybaseASEUserDefinedType();
            case SybaseasesqlmodelPackage.SYBASE_ASE_ENCRYPTION_KEY: return createSybaseASEEncryptionKey();
            case SybaseasesqlmodelPackage.LOCK_PROMOTION_INFO: return createLockPromotionInfo();
            case SybaseasesqlmodelPackage.SYBASE_ASE_ROLE: return createSybaseASERole();
            case SybaseasesqlmodelPackage.SYBASE_ASE_CACHE: return createSybaseASECache();
            case SybaseasesqlmodelPackage.SYBASE_ASE_VIEW_TABLE: return createSybaseASEViewTable();
            case SybaseasesqlmodelPackage.SYBASE_ASE_TEMP_TABLE: return createSybaseASETempTable();
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROXY_TABLE: return createSybaseASEProxyTable();
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE_TABLE: return createSybaseASEWebServiceTable();
            case SybaseasesqlmodelPackage.SYBASE_ASE_BASE_TABLE: return createSybaseASEBaseTable();
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER: return createSybaseASEUser();
            case SybaseasesqlmodelPackage.SYBASE_ASE_GROUP: return createSybaseASEGroup();
            case SybaseasesqlmodelPackage.SYBASE_ASE_PRIVILEGE: return createSybaseASEPrivilege();
            case SybaseasesqlmodelPackage.SYBASE_ASE_TRIGGER: return createSybaseASETrigger();
            case SybaseasesqlmodelPackage.SYBASE_ASE_CHECK_CONSTRAINT: return createSybaseASECheckConstraint();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object createFromString(EDataType eDataType, String initialValue)
    {
        switch (eDataType.getClassifierID())
        {
            case SybaseasesqlmodelPackage.TRANSACTION_MODE_TYPE:
                return createTransactionModeTypeFromString(eDataType, initialValue);
            case SybaseasesqlmodelPackage.CACHE_STRATEGY_TYPE:
                return createCacheStrategyTypeFromString(eDataType, initialValue);
            case SybaseasesqlmodelPackage.LOCKING_SCHEMA_TYPE:
                return createLockingSchemaTypeFromString(eDataType, initialValue);
            case SybaseasesqlmodelPackage.PROXY_TABLE_EXTERNAL_TYPE:
                return createProxyTableExternalTypeFromString(eDataType, initialValue);
            case SybaseasesqlmodelPackage.ACCESS_RULE_TYPE:
                return createAccessRuleTypeFromString(eDataType, initialValue);
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG_TYPE:
                return createSybaseASECatalogTypeFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertToString(EDataType eDataType, Object instanceValue)
    {
        switch (eDataType.getClassifierID())
        {
            case SybaseasesqlmodelPackage.TRANSACTION_MODE_TYPE:
                return convertTransactionModeTypeToString(eDataType, instanceValue);
            case SybaseasesqlmodelPackage.CACHE_STRATEGY_TYPE:
                return convertCacheStrategyTypeToString(eDataType, instanceValue);
            case SybaseasesqlmodelPackage.LOCKING_SCHEMA_TYPE:
                return convertLockingSchemaTypeToString(eDataType, instanceValue);
            case SybaseasesqlmodelPackage.PROXY_TABLE_EXTERNAL_TYPE:
                return convertProxyTableExternalTypeToString(eDataType, instanceValue);
            case SybaseasesqlmodelPackage.ACCESS_RULE_TYPE:
                return convertAccessRuleTypeToString(eDataType, instanceValue);
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG_TYPE:
                return convertSybaseASECatalogTypeToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASESchema createSybaseASESchema()
    {
        SybaseASESchemaImpl sybaseASESchema = new SybaseASESchemaImpl();
        return sybaseASESchema;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASEDatabase createSybaseASEDatabase()
    {
        SybaseASEDatabaseImpl sybaseASEDatabase = new SybaseASEDatabaseImpl();
        return sybaseASEDatabase;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASEWebService createSybaseASEWebService()
    {
        SybaseASEWebServiceImpl sybaseASEWebService = new SybaseASEWebServiceImpl();
        return sybaseASEWebService;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASEPredefinedDataType createSybaseASEPredefinedDataType()
    {
        SybaseASEPredefinedDataTypeImpl sybaseASEPredefinedDataType = new SybaseASEPredefinedDataTypeImpl();
        return sybaseASEPredefinedDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASECatalog createSybaseASECatalog()
    {
        SybaseASECatalogImpl sybaseASECatalog = new SybaseASECatalogImpl();
        return sybaseASECatalog;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASEProcedure createSybaseASEProcedure()
    {
        SybaseASEProcedureImpl sybaseASEProcedure = new SybaseASEProcedureImpl();
        return sybaseASEProcedure;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASEDefault createSybaseASEDefault()
    {
        SybaseASEDefaultImpl sybaseASEDefault = new SybaseASEDefaultImpl();
        return sybaseASEDefault;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASERule createSybaseASERule()
    {
        SybaseASERuleImpl sybaseASERule = new SybaseASERuleImpl();
        return sybaseASERule;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASEIndex createSybaseASEIndex()
    {
        SybaseASEIndexImpl sybaseASEIndex = new SybaseASEIndexImpl();
        return sybaseASEIndex;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASESegment createSybaseASESegment()
    {
        SybaseASESegmentImpl sybaseASESegment = new SybaseASESegmentImpl();
        return sybaseASESegment;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASEFuncBasedIndexMember createSybaseASEFuncBasedIndexMember()
    {
        SybaseASEFuncBasedIndexMemberImpl sybaseASEFuncBasedIndexMember = new SybaseASEFuncBasedIndexMemberImpl();
        return sybaseASEFuncBasedIndexMember;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASETable createSybaseASETable()
    {
        SybaseASETableImpl sybaseASETable = new SybaseASETableImpl();
        return sybaseASETable;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASEColumnCheckConstraint createSybaseASEColumnCheckConstraint()
    {
        SybaseASEColumnCheckConstraintImpl sybaseASEColumnCheckConstraint = new SybaseASEColumnCheckConstraintImpl();
        return sybaseASEColumnCheckConstraint;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASEColumn createSybaseASEColumn()
    {
        SybaseASEColumnImpl sybaseASEColumn = new SybaseASEColumnImpl();
        return sybaseASEColumn;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASEUniqueConstraint createSybaseASEUniqueConstraint()
    {
        SybaseASEUniqueConstraintImpl sybaseASEUniqueConstraint = new SybaseASEUniqueConstraintImpl();
        return sybaseASEUniqueConstraint;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASEPrimaryKey createSybaseASEPrimaryKey()
    {
        SybaseASEPrimaryKeyImpl sybaseASEPrimaryKey = new SybaseASEPrimaryKeyImpl();
        return sybaseASEPrimaryKey;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DeviceItem createDeviceItem()
    {
        DeviceItemImpl deviceItem = new DeviceItemImpl();
        return deviceItem;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SegmentThreshold createSegmentThreshold()
    {
        SegmentThresholdImpl segmentThreshold = new SegmentThresholdImpl();
        return segmentThreshold;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CacheInfo createCacheInfo()
    {
        CacheInfoImpl cacheInfo = new CacheInfoImpl();
        return cacheInfo;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASEUserDefinedType createSybaseASEUserDefinedType()
    {
        SybaseASEUserDefinedTypeImpl sybaseASEUserDefinedType = new SybaseASEUserDefinedTypeImpl();
        return sybaseASEUserDefinedType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASEEncryptionKey createSybaseASEEncryptionKey()
    {
        SybaseASEEncryptionKeyImpl sybaseASEEncryptionKey = new SybaseASEEncryptionKeyImpl();
        return sybaseASEEncryptionKey;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LockPromotionInfo createLockPromotionInfo()
    {
        LockPromotionInfoImpl lockPromotionInfo = new LockPromotionInfoImpl();
        return lockPromotionInfo;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASERole createSybaseASERole()
    {
        SybaseASERoleImpl sybaseASERole = new SybaseASERoleImpl();
        return sybaseASERole;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASECache createSybaseASECache()
    {
        SybaseASECacheImpl sybaseASECache = new SybaseASECacheImpl();
        return sybaseASECache;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASEViewTable createSybaseASEViewTable()
    {
        SybaseASEViewTableImpl sybaseASEViewTable = new SybaseASEViewTableImpl();
        return sybaseASEViewTable;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASETempTable createSybaseASETempTable()
    {
        SybaseASETempTableImpl sybaseASETempTable = new SybaseASETempTableImpl();
        return sybaseASETempTable;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASEProxyTable createSybaseASEProxyTable()
    {
        SybaseASEProxyTableImpl sybaseASEProxyTable = new SybaseASEProxyTableImpl();
        return sybaseASEProxyTable;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASEWebServiceTable createSybaseASEWebServiceTable()
    {
        SybaseASEWebServiceTableImpl sybaseASEWebServiceTable = new SybaseASEWebServiceTableImpl();
        return sybaseASEWebServiceTable;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASEBaseTable createSybaseASEBaseTable()
    {
        SybaseASEBaseTableImpl sybaseASEBaseTable = new SybaseASEBaseTableImpl();
        return sybaseASEBaseTable;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASEUser createSybaseASEUser()
    {
        SybaseASEUserImpl sybaseASEUser = new SybaseASEUserImpl();
        return sybaseASEUser;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASEGroup createSybaseASEGroup()
    {
        SybaseASEGroupImpl sybaseASEGroup = new SybaseASEGroupImpl();
        return sybaseASEGroup;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASEPrivilege createSybaseASEPrivilege()
    {
        SybaseASEPrivilegeImpl sybaseASEPrivilege = new SybaseASEPrivilegeImpl();
        return sybaseASEPrivilege;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASETrigger createSybaseASETrigger()
    {
        SybaseASETriggerImpl sybaseASETrigger = new SybaseASETriggerImpl();
        return sybaseASETrigger;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseASECheckConstraint createSybaseASECheckConstraint() {
        SybaseASECheckConstraintImpl sybaseASECheckConstraint = new SybaseASECheckConstraintImpl();
        return sybaseASECheckConstraint;
    }

				/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TransactionModeType createTransactionModeTypeFromString(EDataType eDataType, String initialValue)
    {
        TransactionModeType result = TransactionModeType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertTransactionModeTypeToString(EDataType eDataType, Object instanceValue)
    {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public CacheStrategyType createCacheStrategyTypeFromString(EDataType eDataType, String initialValue)
    {
        CacheStrategyType result = CacheStrategyType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertCacheStrategyTypeToString(EDataType eDataType, Object instanceValue)
    {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LockingSchemaType createLockingSchemaTypeFromString(EDataType eDataType, String initialValue)
    {
        LockingSchemaType result = LockingSchemaType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertLockingSchemaTypeToString(EDataType eDataType, Object instanceValue)
    {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ProxyTableExternalType createProxyTableExternalTypeFromString(EDataType eDataType, String initialValue)
    {
        ProxyTableExternalType result = ProxyTableExternalType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertProxyTableExternalTypeToString(EDataType eDataType, Object instanceValue)
    {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AccessRuleType createAccessRuleTypeFromString(EDataType eDataType, String initialValue)
    {
        AccessRuleType result = AccessRuleType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertAccessRuleTypeToString(EDataType eDataType, Object instanceValue)
    {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseASECatalogType createSybaseASECatalogTypeFromString(EDataType eDataType, String initialValue)
    {
        SybaseASECatalogType result = SybaseASECatalogType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertSybaseASECatalogTypeToString(EDataType eDataType, Object instanceValue)
    {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SybaseasesqlmodelPackage getSybaseasesqlmodelPackage()
    {
        return (SybaseasesqlmodelPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    public static SybaseasesqlmodelPackage getPackage()
    {
        return SybaseasesqlmodelPackage.eINSTANCE;
    }

} //SybaseasesqlmodelFactoryImpl

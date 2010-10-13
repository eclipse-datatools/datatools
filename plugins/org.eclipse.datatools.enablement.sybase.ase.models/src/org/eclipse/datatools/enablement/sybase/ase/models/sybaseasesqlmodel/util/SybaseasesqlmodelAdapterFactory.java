/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseasesqlmodelAdapterFactory.java,v 1.1 2008/03/27 07:41:14 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.util;

import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.*;

import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizationIdentifier;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizedObject;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheInfo;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.DeviceItem;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockPromotionInfo;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SegmentThreshold;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECache;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumnCheckConstraint;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDefault;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEEncryptionKey;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEFuncBasedIndexMember;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEPredefinedDataType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEPrimaryKey;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProcedure;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProxyTable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERole;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESchema;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASETable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASETempTable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUniqueConstraint;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEViewTable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebService;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebServiceTable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseBaseTable;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseIndexMember;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasePrivilege;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseRoutine;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseViewTable;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Group;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Role;
import org.eclipse.datatools.modelbase.sql.accesscontrol.User;

import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.Constraint;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.IndexMember;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.constraints.ReferenceConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.TableConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.schema.TypedElement;
import org.eclipse.datatools.modelbase.sql.tables.BaseTable;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.datatools.modelbase.sql.tables.DerivedTable;
import org.eclipse.datatools.modelbase.sql.tables.PersistentTable;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.datatools.modelbase.sql.tables.TemporaryTable;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.modelbase.sql.tables.ViewTable;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage
 * @generated
 */
public class SybaseasesqlmodelAdapterFactory extends AdapterFactoryImpl 
{
    /**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SybaseasesqlmodelPackage modelPackage;

    /**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SybaseasesqlmodelAdapterFactory()
    {
		if (modelPackage == null) {
			modelPackage = SybaseasesqlmodelPackage.eINSTANCE;
		}
	}

    /**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	public boolean isFactoryForType(Object object)
    {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

    /**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SybaseasesqlmodelSwitch modelSwitch =
        new SybaseasesqlmodelSwitch() {
			public Object caseSybaseASESchema(SybaseASESchema object) {
				return createSybaseASESchemaAdapter();
			}
			public Object caseSybaseASEDatabase(SybaseASEDatabase object) {
				return createSybaseASEDatabaseAdapter();
			}
			public Object caseSybaseASEWebService(SybaseASEWebService object) {
				return createSybaseASEWebServiceAdapter();
			}
			public Object caseSybaseASEPredefinedDataType(SybaseASEPredefinedDataType object) {
				return createSybaseASEPredefinedDataTypeAdapter();
			}
			public Object caseSybaseASECatalog(SybaseASECatalog object) {
				return createSybaseASECatalogAdapter();
			}
			public Object caseSybaseASEProcedure(SybaseASEProcedure object) {
				return createSybaseASEProcedureAdapter();
			}
			public Object caseSybaseASEDefault(SybaseASEDefault object) {
				return createSybaseASEDefaultAdapter();
			}
			public Object caseSybaseASERule(SybaseASERule object) {
				return createSybaseASERuleAdapter();
			}
			public Object caseSybaseASEIndex(SybaseASEIndex object) {
				return createSybaseASEIndexAdapter();
			}
			public Object caseSybaseASESegment(SybaseASESegment object) {
				return createSybaseASESegmentAdapter();
			}
			public Object caseSybaseASEFuncBasedIndexMember(SybaseASEFuncBasedIndexMember object) {
				return createSybaseASEFuncBasedIndexMemberAdapter();
			}
			public Object caseSybaseASETable(SybaseASETable object) {
				return createSybaseASETableAdapter();
			}
			public Object caseSybaseASEColumnCheckConstraint(SybaseASEColumnCheckConstraint object) {
				return createSybaseASEColumnCheckConstraintAdapter();
			}
			public Object caseSybaseASEColumn(SybaseASEColumn object) {
				return createSybaseASEColumnAdapter();
			}
			public Object caseSybaseASEUniqueConstraint(SybaseASEUniqueConstraint object) {
				return createSybaseASEUniqueConstraintAdapter();
			}
			public Object caseSybaseASEPrimaryKey(SybaseASEPrimaryKey object) {
				return createSybaseASEPrimaryKeyAdapter();
			}
			public Object caseDeviceItem(DeviceItem object) {
				return createDeviceItemAdapter();
			}
			public Object caseSegmentThreshold(SegmentThreshold object) {
				return createSegmentThresholdAdapter();
			}
			public Object caseCacheInfo(CacheInfo object) {
				return createCacheInfoAdapter();
			}
			public Object caseSybaseASEUserDefinedType(SybaseASEUserDefinedType object) {
				return createSybaseASEUserDefinedTypeAdapter();
			}
			public Object caseSybaseASEEncryptionKey(SybaseASEEncryptionKey object) {
				return createSybaseASEEncryptionKeyAdapter();
			}
			public Object caseLockPromotionInfo(LockPromotionInfo object) {
				return createLockPromotionInfoAdapter();
			}
			public Object caseSybaseASERole(SybaseASERole object) {
				return createSybaseASERoleAdapter();
			}
			public Object caseSybaseASECache(SybaseASECache object) {
				return createSybaseASECacheAdapter();
			}
			public Object caseSybaseASEViewTable(SybaseASEViewTable object) {
				return createSybaseASEViewTableAdapter();
			}
			public Object caseSybaseASETempTable(SybaseASETempTable object) {
				return createSybaseASETempTableAdapter();
			}
			public Object caseSybaseASEProxyTable(SybaseASEProxyTable object) {
				return createSybaseASEProxyTableAdapter();
			}
			public Object caseSybaseASEWebServiceTable(SybaseASEWebServiceTable object) {
				return createSybaseASEWebServiceTableAdapter();
			}
			public Object caseSybaseASEBaseTable(SybaseASEBaseTable object) {
				return createSybaseASEBaseTableAdapter();
			}
			public Object caseSybaseASEUser(SybaseASEUser object) {
				return createSybaseASEUserAdapter();
			}
			public Object caseSybaseASEGroup(SybaseASEGroup object) {
				return createSybaseASEGroupAdapter();
			}
			public Object caseSybaseASEPrivilege(SybaseASEPrivilege object) {
				return createSybaseASEPrivilegeAdapter();
			}
			public Object caseSybaseASETrigger(SybaseASETrigger object) {
				return createSybaseASETriggerAdapter();
			}
			public Object caseSybaseASECheckConstraint(SybaseASECheckConstraint object) {
				return createSybaseASECheckConstraintAdapter();
			}
			public Object caseEModelElement(EModelElement object) {
				return createEModelElementAdapter();
			}
			public Object caseENamedElement(ENamedElement object) {
				return createENamedElementAdapter();
			}
			public Object caseSQLObject(SQLObject object) {
				return createSQLObjectAdapter();
			}
			public Object caseSchema(Schema object) {
				return createSchemaAdapter();
			}
			public Object caseDatabase(Database object) {
				return createDatabaseAdapter();
			}
			public Object caseDataType(DataType object) {
				return createDataTypeAdapter();
			}
			public Object caseSQLDataType(SQLDataType object) {
				return createSQLDataTypeAdapter();
			}
			public Object casePredefinedDataType(PredefinedDataType object) {
				return createPredefinedDataTypeAdapter();
			}
			public Object caseCatalog(Catalog object) {
				return createCatalogAdapter();
			}
			public Object caseRoutine(Routine object) {
				return createRoutineAdapter();
			}
			public Object caseProcedure(Procedure object) {
				return createProcedureAdapter();
			}
			public Object caseSybaseAuthorizedObject(SybaseAuthorizedObject object) {
				return createSybaseAuthorizedObjectAdapter();
			}
			public Object caseSybaseRoutine(SybaseRoutine object) {
				return createSybaseRoutineAdapter();
			}
			public Object caseIndex(Index object) {
				return createIndexAdapter();
			}
			public Object caseIndexMember(IndexMember object) {
				return createIndexMemberAdapter();
			}
			public Object caseSybaseIndexMember(SybaseIndexMember object) {
				return createSybaseIndexMemberAdapter();
			}
			public Object caseTable(Table object) {
				return createTableAdapter();
			}
			public Object caseBaseTable(BaseTable object) {
				return createBaseTableAdapter();
			}
			public Object casePersistentTable(PersistentTable object) {
				return createPersistentTableAdapter();
			}
			public Object caseSybaseBaseTable(SybaseBaseTable object) {
				return createSybaseBaseTableAdapter();
			}
			public Object caseConstraint(Constraint object) {
				return createConstraintAdapter();
			}
			public Object caseTableConstraint(TableConstraint object) {
				return createTableConstraintAdapter();
			}
			public Object caseCheckConstraint(CheckConstraint object) {
				return createCheckConstraintAdapter();
			}
			public Object caseTypedElement(TypedElement object) {
				return createTypedElementAdapter();
			}
			public Object caseColumn(Column object) {
				return createColumnAdapter();
			}
			public Object caseReferenceConstraint(ReferenceConstraint object) {
				return createReferenceConstraintAdapter();
			}
			public Object caseUniqueConstraint(UniqueConstraint object) {
				return createUniqueConstraintAdapter();
			}
			public Object casePrimaryKey(PrimaryKey object) {
				return createPrimaryKeyAdapter();
			}
			public Object caseUserDefinedType(UserDefinedType object) {
				return createUserDefinedTypeAdapter();
			}
			public Object caseDistinctUserDefinedType(DistinctUserDefinedType object) {
				return createDistinctUserDefinedTypeAdapter();
			}
			public Object caseAuthorizationIdentifier(AuthorizationIdentifier object) {
				return createAuthorizationIdentifierAdapter();
			}
			public Object caseRole(Role object) {
				return createRoleAdapter();
			}
			public Object caseSybaseAuthorizationIdentifier(SybaseAuthorizationIdentifier object) {
				return createSybaseAuthorizationIdentifierAdapter();
			}
			public Object caseDerivedTable(DerivedTable object) {
				return createDerivedTableAdapter();
			}
			public Object caseViewTable(ViewTable object) {
				return createViewTableAdapter();
			}
			public Object caseSybaseViewTable(SybaseViewTable object) {
				return createSybaseViewTableAdapter();
			}
			public Object caseTemporaryTable(TemporaryTable object) {
				return createTemporaryTableAdapter();
			}
			public Object caseUser(User object) {
				return createUserAdapter();
			}
			public Object caseGroup(Group object) {
				return createGroupAdapter();
			}
			public Object casePrivilege(Privilege object) {
				return createPrivilegeAdapter();
			}
			public Object caseSybasePrivilege(SybasePrivilege object) {
				return createSybasePrivilegeAdapter();
			}
			public Object caseTrigger(Trigger object) {
				return createTriggerAdapter();
			}
			public Object defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

    /**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	public Adapter createAdapter(Notifier target)
    {
		return (Adapter)modelSwitch.doSwitch((EObject)target);
	}


    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESchema <em>Sybase ASE Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESchema
	 * @generated
	 */
	public Adapter createSybaseASESchemaAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase <em>Sybase ASE Database</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase
	 * @generated
	 */
	public Adapter createSybaseASEDatabaseAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebService <em>Sybase ASE Web Service</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebService
	 * @generated
	 */
	public Adapter createSybaseASEWebServiceAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEPredefinedDataType <em>Sybase ASE Predefined Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEPredefinedDataType
	 * @generated
	 */
	public Adapter createSybaseASEPredefinedDataTypeAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog <em>Sybase ASE Catalog</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECatalog
	 * @generated
	 */
	public Adapter createSybaseASECatalogAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProcedure <em>Sybase ASE Procedure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProcedure
	 * @generated
	 */
	public Adapter createSybaseASEProcedureAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDefault <em>Sybase ASE Default</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDefault
	 * @generated
	 */
	public Adapter createSybaseASEDefaultAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule <em>Sybase ASE Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule
	 * @generated
	 */
	public Adapter createSybaseASERuleAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex <em>Sybase ASE Index</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEIndex
	 * @generated
	 */
	public Adapter createSybaseASEIndexAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment <em>Sybase ASE Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment
	 * @generated
	 */
	public Adapter createSybaseASESegmentAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEFuncBasedIndexMember <em>Sybase ASE Func Based Index Member</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEFuncBasedIndexMember
	 * @generated
	 */
	public Adapter createSybaseASEFuncBasedIndexMemberAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASETable <em>Sybase ASE Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASETable
	 * @generated
	 */
	public Adapter createSybaseASETableAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumnCheckConstraint <em>Sybase ASE Column Check Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumnCheckConstraint
	 * @generated
	 */
	public Adapter createSybaseASEColumnCheckConstraintAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn <em>Sybase ASE Column</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn
	 * @generated
	 */
	public Adapter createSybaseASEColumnAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUniqueConstraint <em>Sybase ASE Unique Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUniqueConstraint
	 * @generated
	 */
	public Adapter createSybaseASEUniqueConstraintAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEPrimaryKey <em>Sybase ASE Primary Key</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEPrimaryKey
	 * @generated
	 */
	public Adapter createSybaseASEPrimaryKeyAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.DeviceItem <em>Device Item</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.DeviceItem
	 * @generated
	 */
	public Adapter createDeviceItemAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SegmentThreshold <em>Segment Threshold</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SegmentThreshold
	 * @generated
	 */
	public Adapter createSegmentThresholdAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheInfo <em>Cache Info</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheInfo
	 * @generated
	 */
	public Adapter createCacheInfoAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType <em>Sybase ASE User Defined Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUserDefinedType
	 * @generated
	 */
	public Adapter createSybaseASEUserDefinedTypeAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEEncryptionKey <em>Sybase ASE Encryption Key</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEEncryptionKey
	 * @generated
	 */
	public Adapter createSybaseASEEncryptionKeyAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockPromotionInfo <em>Lock Promotion Info</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockPromotionInfo
	 * @generated
	 */
	public Adapter createLockPromotionInfoAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERole <em>Sybase ASE Role</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERole
	 * @generated
	 */
	public Adapter createSybaseASERoleAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECache <em>Sybase ASE Cache</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECache
	 * @generated
	 */
	public Adapter createSybaseASECacheAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEViewTable <em>Sybase ASE View Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEViewTable
	 * @generated
	 */
	public Adapter createSybaseASEViewTableAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASETempTable <em>Sybase ASE Temp Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASETempTable
	 * @generated
	 */
	public Adapter createSybaseASETempTableAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProxyTable <em>Sybase ASE Proxy Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEProxyTable
	 * @generated
	 */
	public Adapter createSybaseASEProxyTableAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebServiceTable <em>Sybase ASE Web Service Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEWebServiceTable
	 * @generated
	 */
	public Adapter createSybaseASEWebServiceTableAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable <em>Sybase ASE Base Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable
	 * @generated
	 */
	public Adapter createSybaseASEBaseTableAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUser <em>Sybase ASE User</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUser
	 * @generated
	 */
    public Adapter createSybaseASEUserAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEGroup <em>Sybase ASE Group</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEGroup
	 * @generated
	 */
    public Adapter createSybaseASEGroupAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEPrivilege <em>Sybase ASE Privilege</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEPrivilege
	 * @generated
	 */
    public Adapter createSybaseASEPrivilegeAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASETrigger <em>Sybase ASE Trigger</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASETrigger
	 * @generated
	 */
    public Adapter createSybaseASETriggerAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECheckConstraint <em>Sybase ASE Check Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECheckConstraint
	 * @generated
	 */
	public Adapter createSybaseASECheckConstraintAdapter() {
		return null;
	}

				/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.ecore.EModelElement <em>EModel Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.ecore.EModelElement
	 * @generated
	 */
	public Adapter createEModelElementAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.emf.ecore.ENamedElement <em>ENamed Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.emf.ecore.ENamedElement
	 * @generated
	 */
	public Adapter createENamedElementAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.schema.SQLObject <em>SQL Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.schema.SQLObject
	 * @generated
	 */
	public Adapter createSQLObjectAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.schema.Schema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.schema.Schema
	 * @generated
	 */
	public Adapter createSchemaAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.schema.Database <em>Database</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.schema.Database
	 * @generated
	 */
	public Adapter createDatabaseAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.datatypes.DataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.DataType
	 * @generated
	 */
	public Adapter createDataTypeAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.datatypes.SQLDataType <em>SQL Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.SQLDataType
	 * @generated
	 */
	public Adapter createSQLDataTypeAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType <em>Predefined Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType
	 * @generated
	 */
	public Adapter createPredefinedDataTypeAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.schema.Catalog <em>Catalog</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.schema.Catalog
	 * @generated
	 */
	public Adapter createCatalogAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.routines.Routine <em>Routine</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Routine
	 * @generated
	 */
	public Adapter createRoutineAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.routines.Procedure <em>Procedure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Procedure
	 * @generated
	 */
	public Adapter createProcedureAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizedObject <em>Sybase Authorized Object</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizedObject
	 * @generated
	 */
    public Adapter createSybaseAuthorizedObjectAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseRoutine <em>Sybase Routine</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseRoutine
	 * @generated
	 */
    public Adapter createSybaseRoutineAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.constraints.Index <em>Index</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.Index
	 * @generated
	 */
	public Adapter createIndexAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.constraints.IndexMember <em>Index Member</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.IndexMember
	 * @generated
	 */
	public Adapter createIndexMemberAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseIndexMember <em>Sybase Index Member</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseIndexMember
	 * @generated
	 */
    public Adapter createSybaseIndexMemberAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.tables.Table <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Table
	 * @generated
	 */
	public Adapter createTableAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.tables.BaseTable <em>Base Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.tables.BaseTable
	 * @generated
	 */
	public Adapter createBaseTableAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.tables.PersistentTable <em>Persistent Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.tables.PersistentTable
	 * @generated
	 */
	public Adapter createPersistentTableAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseBaseTable <em>Sybase Base Table</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseBaseTable
	 * @generated
	 */
    public Adapter createSybaseBaseTableAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.constraints.Constraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.Constraint
	 * @generated
	 */
	public Adapter createConstraintAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.constraints.TableConstraint <em>Table Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.TableConstraint
	 * @generated
	 */
	public Adapter createTableConstraintAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint <em>Check Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint
	 * @generated
	 */
	public Adapter createCheckConstraintAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.schema.TypedElement <em>Typed Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.schema.TypedElement
	 * @generated
	 */
	public Adapter createTypedElementAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.tables.Column <em>Column</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Column
	 * @generated
	 */
	public Adapter createColumnAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.constraints.ReferenceConstraint <em>Reference Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.ReferenceConstraint
	 * @generated
	 */
	public Adapter createReferenceConstraintAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint <em>Unique Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint
	 * @generated
	 */
	public Adapter createUniqueConstraintAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey <em>Primary Key</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey
	 * @generated
	 */
	public Adapter createPrimaryKeyAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType <em>User Defined Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType
	 * @generated
	 */
	public Adapter createUserDefinedTypeAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType <em>Distinct User Defined Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType
	 * @generated
	 */
	public Adapter createDistinctUserDefinedTypeAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier <em>Authorization Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier
	 * @generated
	 */
	public Adapter createAuthorizationIdentifierAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Role <em>Role</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.Role
	 * @generated
	 */
	public Adapter createRoleAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizationIdentifier <em>Sybase Authorization Identifier</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizationIdentifier
	 * @generated
	 */
    public Adapter createSybaseAuthorizationIdentifierAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.tables.DerivedTable <em>Derived Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.tables.DerivedTable
	 * @generated
	 */
	public Adapter createDerivedTableAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.tables.ViewTable <em>View Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.tables.ViewTable
	 * @generated
	 */
	public Adapter createViewTableAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseViewTable <em>Sybase View Table</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseViewTable
	 * @generated
	 */
    public Adapter createSybaseViewTableAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.tables.TemporaryTable <em>Temporary Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.tables.TemporaryTable
	 * @generated
	 */
	public Adapter createTemporaryTableAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.User <em>User</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.User
	 * @generated
	 */
    public Adapter createUserAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Group <em>Group</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.Group
	 * @generated
	 */
    public Adapter createGroupAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege <em>Privilege</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.accesscontrol.Privilege
	 * @generated
	 */
    public Adapter createPrivilegeAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasePrivilege <em>Sybase Privilege</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasePrivilege
	 * @generated
	 */
    public Adapter createSybasePrivilegeAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.tables.Trigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.tables.Trigger
	 * @generated
	 */
    public Adapter createTriggerAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter()
    {
		return null;
	}

} //SybaseasesqlmodelAdapterFactory

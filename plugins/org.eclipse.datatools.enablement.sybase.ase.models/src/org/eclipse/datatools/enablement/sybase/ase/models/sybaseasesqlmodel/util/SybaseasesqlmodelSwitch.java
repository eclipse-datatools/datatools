/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseasesqlmodelSwitch.java,v 1.15 2008/02/19 04:30:41 renj Exp $
 */
package org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.util;

import java.util.List;

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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage
 * @generated
 */
public class SybaseasesqlmodelSwitch 
{
    /**
     * The cached model package
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected static SybaseasesqlmodelPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public SybaseasesqlmodelSwitch()
    {
        if (modelPackage == null)
        {
            modelPackage = SybaseasesqlmodelPackage.eINSTANCE;
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
	public Object doSwitch(EObject theEObject)
    {
        return doSwitch(theEObject.eClass(), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
	protected Object doSwitch(EClass theEClass, EObject theEObject)
    {
        if (theEClass.eContainer() == modelPackage)
        {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        }
        else
        {
            List eSuperTypes = theEClass.getESuperTypes();
            return
                eSuperTypes.isEmpty() ?
                    defaultCase(theEObject) :
                    doSwitch((EClass)eSuperTypes.get(0), theEObject);
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
	protected Object doSwitch(int classifierID, EObject theEObject)
    {
        switch (classifierID)
        {
            case SybaseasesqlmodelPackage.SYBASE_ASE_SCHEMA:
            {
                SybaseASESchema sybaseASESchema = (SybaseASESchema)theEObject;
                Object result = caseSybaseASESchema(sybaseASESchema);
                if (result == null) result = caseSchema(sybaseASESchema);
                if (result == null) result = caseSQLObject(sybaseASESchema);
                if (result == null) result = caseENamedElement(sybaseASESchema);
                if (result == null) result = caseEModelElement(sybaseASESchema);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.SYBASE_ASE_DATABASE:
            {
                SybaseASEDatabase sybaseASEDatabase = (SybaseASEDatabase)theEObject;
                Object result = caseSybaseASEDatabase(sybaseASEDatabase);
                if (result == null) result = caseDatabase(sybaseASEDatabase);
                if (result == null) result = caseSQLObject(sybaseASEDatabase);
                if (result == null) result = caseENamedElement(sybaseASEDatabase);
                if (result == null) result = caseEModelElement(sybaseASEDatabase);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE:
            {
                SybaseASEWebService sybaseASEWebService = (SybaseASEWebService)theEObject;
                Object result = caseSybaseASEWebService(sybaseASEWebService);
                if (result == null) result = caseSQLObject(sybaseASEWebService);
                if (result == null) result = caseENamedElement(sybaseASEWebService);
                if (result == null) result = caseEModelElement(sybaseASEWebService);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.SYBASE_ASE_PREDEFINED_DATA_TYPE:
            {
                SybaseASEPredefinedDataType sybaseASEPredefinedDataType = (SybaseASEPredefinedDataType)theEObject;
                Object result = caseSybaseASEPredefinedDataType(sybaseASEPredefinedDataType);
                if (result == null) result = casePredefinedDataType(sybaseASEPredefinedDataType);
                if (result == null) result = caseSQLDataType(sybaseASEPredefinedDataType);
                if (result == null) result = caseDataType(sybaseASEPredefinedDataType);
                if (result == null) result = caseSQLObject(sybaseASEPredefinedDataType);
                if (result == null) result = caseENamedElement(sybaseASEPredefinedDataType);
                if (result == null) result = caseEModelElement(sybaseASEPredefinedDataType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.SYBASE_ASE_CATALOG:
            {
                SybaseASECatalog sybaseASECatalog = (SybaseASECatalog)theEObject;
                Object result = caseSybaseASECatalog(sybaseASECatalog);
                if (result == null) result = caseCatalog(sybaseASECatalog);
                if (result == null) result = caseSQLObject(sybaseASECatalog);
                if (result == null) result = caseENamedElement(sybaseASECatalog);
                if (result == null) result = caseEModelElement(sybaseASECatalog);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROCEDURE:
            {
                SybaseASEProcedure sybaseASEProcedure = (SybaseASEProcedure)theEObject;
                Object result = caseSybaseASEProcedure(sybaseASEProcedure);
                if (result == null) result = caseProcedure(sybaseASEProcedure);
                if (result == null) result = caseSybaseRoutine(sybaseASEProcedure);
                if (result == null) result = caseRoutine(sybaseASEProcedure);
                if (result == null) result = caseSybaseAuthorizedObject(sybaseASEProcedure);
                if (result == null) result = caseSQLObject(sybaseASEProcedure);
                if (result == null) result = caseENamedElement(sybaseASEProcedure);
                if (result == null) result = caseEModelElement(sybaseASEProcedure);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.SYBASE_ASE_DEFAULT:
            {
                SybaseASEDefault sybaseASEDefault = (SybaseASEDefault)theEObject;
                Object result = caseSybaseASEDefault(sybaseASEDefault);
                if (result == null) result = caseSQLObject(sybaseASEDefault);
                if (result == null) result = caseENamedElement(sybaseASEDefault);
                if (result == null) result = caseEModelElement(sybaseASEDefault);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.SYBASE_ASE_RULE:
            {
                SybaseASERule sybaseASERule = (SybaseASERule)theEObject;
                Object result = caseSybaseASERule(sybaseASERule);
                if (result == null) result = caseSQLObject(sybaseASERule);
                if (result == null) result = caseENamedElement(sybaseASERule);
                if (result == null) result = caseEModelElement(sybaseASERule);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX:
            {
                SybaseASEIndex sybaseASEIndex = (SybaseASEIndex)theEObject;
                Object result = caseSybaseASEIndex(sybaseASEIndex);
                if (result == null) result = caseIndex(sybaseASEIndex);
                if (result == null) result = caseSQLObject(sybaseASEIndex);
                if (result == null) result = caseENamedElement(sybaseASEIndex);
                if (result == null) result = caseEModelElement(sybaseASEIndex);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.SYBASE_ASE_SEGMENT:
            {
                SybaseASESegment sybaseASESegment = (SybaseASESegment)theEObject;
                Object result = caseSybaseASESegment(sybaseASESegment);
                if (result == null) result = caseSQLObject(sybaseASESegment);
                if (result == null) result = caseENamedElement(sybaseASESegment);
                if (result == null) result = caseEModelElement(sybaseASESegment);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.SYBASE_ASE_FUNC_BASED_INDEX_MEMBER:
            {
                SybaseASEFuncBasedIndexMember sybaseASEFuncBasedIndexMember = (SybaseASEFuncBasedIndexMember)theEObject;
                Object result = caseSybaseASEFuncBasedIndexMember(sybaseASEFuncBasedIndexMember);
                if (result == null) result = caseSybaseIndexMember(sybaseASEFuncBasedIndexMember);
                if (result == null) result = caseIndexMember(sybaseASEFuncBasedIndexMember);
                if (result == null) result = caseSQLObject(sybaseASEFuncBasedIndexMember);
                if (result == null) result = caseENamedElement(sybaseASEFuncBasedIndexMember);
                if (result == null) result = caseEModelElement(sybaseASEFuncBasedIndexMember);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE:
            {
                SybaseASETable sybaseASETable = (SybaseASETable)theEObject;
                Object result = caseSybaseASETable(sybaseASETable);
                if (result == null) result = casePersistentTable(sybaseASETable);
                if (result == null) result = caseSybaseASEBaseTable(sybaseASETable);
                if (result == null) result = caseBaseTable(sybaseASETable);
                if (result == null) result = caseSybaseBaseTable(sybaseASETable);
                if (result == null) result = caseTable(sybaseASETable);
                if (result == null) result = caseSybaseAuthorizedObject(sybaseASETable);
                if (result == null) result = caseSQLObject(sybaseASETable);
                if (result == null) result = caseENamedElement(sybaseASETable);
                if (result == null) result = caseEModelElement(sybaseASETable);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN_CHECK_CONSTRAINT:
            {
                SybaseASEColumnCheckConstraint sybaseASEColumnCheckConstraint = (SybaseASEColumnCheckConstraint)theEObject;
                Object result = caseSybaseASEColumnCheckConstraint(sybaseASEColumnCheckConstraint);
                if (result == null) result = caseSybaseASECheckConstraint(sybaseASEColumnCheckConstraint);
                if (result == null) result = caseCheckConstraint(sybaseASEColumnCheckConstraint);
                if (result == null) result = caseTableConstraint(sybaseASEColumnCheckConstraint);
                if (result == null) result = caseConstraint(sybaseASEColumnCheckConstraint);
                if (result == null) result = caseSQLObject(sybaseASEColumnCheckConstraint);
                if (result == null) result = caseENamedElement(sybaseASEColumnCheckConstraint);
                if (result == null) result = caseEModelElement(sybaseASEColumnCheckConstraint);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.SYBASE_ASE_COLUMN:
            {
                SybaseASEColumn sybaseASEColumn = (SybaseASEColumn)theEObject;
                Object result = caseSybaseASEColumn(sybaseASEColumn);
                if (result == null) result = caseColumn(sybaseASEColumn);
                if (result == null) result = caseSybaseAuthorizedObject(sybaseASEColumn);
                if (result == null) result = caseTypedElement(sybaseASEColumn);
                if (result == null) result = caseSQLObject(sybaseASEColumn);
                if (result == null) result = caseENamedElement(sybaseASEColumn);
                if (result == null) result = caseEModelElement(sybaseASEColumn);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.SYBASE_ASE_UNIQUE_CONSTRAINT:
            {
                SybaseASEUniqueConstraint sybaseASEUniqueConstraint = (SybaseASEUniqueConstraint)theEObject;
                Object result = caseSybaseASEUniqueConstraint(sybaseASEUniqueConstraint);
                if (result == null) result = caseUniqueConstraint(sybaseASEUniqueConstraint);
                if (result == null) result = caseReferenceConstraint(sybaseASEUniqueConstraint);
                if (result == null) result = caseTableConstraint(sybaseASEUniqueConstraint);
                if (result == null) result = caseConstraint(sybaseASEUniqueConstraint);
                if (result == null) result = caseSQLObject(sybaseASEUniqueConstraint);
                if (result == null) result = caseENamedElement(sybaseASEUniqueConstraint);
                if (result == null) result = caseEModelElement(sybaseASEUniqueConstraint);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.SYBASE_ASE_PRIMARY_KEY:
            {
                SybaseASEPrimaryKey sybaseASEPrimaryKey = (SybaseASEPrimaryKey)theEObject;
                Object result = caseSybaseASEPrimaryKey(sybaseASEPrimaryKey);
                if (result == null) result = casePrimaryKey(sybaseASEPrimaryKey);
                if (result == null) result = caseUniqueConstraint(sybaseASEPrimaryKey);
                if (result == null) result = caseReferenceConstraint(sybaseASEPrimaryKey);
                if (result == null) result = caseTableConstraint(sybaseASEPrimaryKey);
                if (result == null) result = caseConstraint(sybaseASEPrimaryKey);
                if (result == null) result = caseSQLObject(sybaseASEPrimaryKey);
                if (result == null) result = caseENamedElement(sybaseASEPrimaryKey);
                if (result == null) result = caseEModelElement(sybaseASEPrimaryKey);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.DEVICE_ITEM:
            {
                DeviceItem deviceItem = (DeviceItem)theEObject;
                Object result = caseDeviceItem(deviceItem);
                if (result == null) result = caseSQLObject(deviceItem);
                if (result == null) result = caseENamedElement(deviceItem);
                if (result == null) result = caseEModelElement(deviceItem);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.SEGMENT_THRESHOLD:
            {
                SegmentThreshold segmentThreshold = (SegmentThreshold)theEObject;
                Object result = caseSegmentThreshold(segmentThreshold);
                if (result == null) result = caseSQLObject(segmentThreshold);
                if (result == null) result = caseENamedElement(segmentThreshold);
                if (result == null) result = caseEModelElement(segmentThreshold);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.CACHE_INFO:
            {
                CacheInfo cacheInfo = (CacheInfo)theEObject;
                Object result = caseCacheInfo(cacheInfo);
                if (result == null) result = caseSQLObject(cacheInfo);
                if (result == null) result = caseENamedElement(cacheInfo);
                if (result == null) result = caseEModelElement(cacheInfo);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER_DEFINED_TYPE:
            {
                SybaseASEUserDefinedType sybaseASEUserDefinedType = (SybaseASEUserDefinedType)theEObject;
                Object result = caseSybaseASEUserDefinedType(sybaseASEUserDefinedType);
                if (result == null) result = caseDistinctUserDefinedType(sybaseASEUserDefinedType);
                if (result == null) result = caseUserDefinedType(sybaseASEUserDefinedType);
                if (result == null) result = caseDataType(sybaseASEUserDefinedType);
                if (result == null) result = caseSQLObject(sybaseASEUserDefinedType);
                if (result == null) result = caseENamedElement(sybaseASEUserDefinedType);
                if (result == null) result = caseEModelElement(sybaseASEUserDefinedType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.SYBASE_ASE_ENCRYPTION_KEY:
            {
                SybaseASEEncryptionKey sybaseASEEncryptionKey = (SybaseASEEncryptionKey)theEObject;
                Object result = caseSybaseASEEncryptionKey(sybaseASEEncryptionKey);
                if (result == null) result = caseSQLObject(sybaseASEEncryptionKey);
                if (result == null) result = caseENamedElement(sybaseASEEncryptionKey);
                if (result == null) result = caseEModelElement(sybaseASEEncryptionKey);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.LOCK_PROMOTION_INFO:
            {
                LockPromotionInfo lockPromotionInfo = (LockPromotionInfo)theEObject;
                Object result = caseLockPromotionInfo(lockPromotionInfo);
                if (result == null) result = caseSQLObject(lockPromotionInfo);
                if (result == null) result = caseENamedElement(lockPromotionInfo);
                if (result == null) result = caseEModelElement(lockPromotionInfo);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.SYBASE_ASE_ROLE:
            {
                SybaseASERole sybaseASERole = (SybaseASERole)theEObject;
                Object result = caseSybaseASERole(sybaseASERole);
                if (result == null) result = caseRole(sybaseASERole);
                if (result == null) result = caseSybaseAuthorizationIdentifier(sybaseASERole);
                if (result == null) result = caseAuthorizationIdentifier(sybaseASERole);
                if (result == null) result = caseSQLObject(sybaseASERole);
                if (result == null) result = caseENamedElement(sybaseASERole);
                if (result == null) result = caseEModelElement(sybaseASERole);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.SYBASE_ASE_CACHE:
            {
                SybaseASECache sybaseASECache = (SybaseASECache)theEObject;
                Object result = caseSybaseASECache(sybaseASECache);
                if (result == null) result = caseSQLObject(sybaseASECache);
                if (result == null) result = caseENamedElement(sybaseASECache);
                if (result == null) result = caseEModelElement(sybaseASECache);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.SYBASE_ASE_VIEW_TABLE:
            {
                SybaseASEViewTable sybaseASEViewTable = (SybaseASEViewTable)theEObject;
                Object result = caseSybaseASEViewTable(sybaseASEViewTable);
                if (result == null) result = caseSybaseViewTable(sybaseASEViewTable);
                if (result == null) result = caseViewTable(sybaseASEViewTable);
                if (result == null) result = caseSybaseAuthorizedObject(sybaseASEViewTable);
                if (result == null) result = caseDerivedTable(sybaseASEViewTable);
                if (result == null) result = caseSQLObject(sybaseASEViewTable);
                if (result == null) result = caseTable(sybaseASEViewTable);
                if (result == null) result = caseENamedElement(sybaseASEViewTable);
                if (result == null) result = caseEModelElement(sybaseASEViewTable);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.SYBASE_ASE_TEMP_TABLE:
            {
                SybaseASETempTable sybaseASETempTable = (SybaseASETempTable)theEObject;
                Object result = caseSybaseASETempTable(sybaseASETempTable);
                if (result == null) result = caseTemporaryTable(sybaseASETempTable);
                if (result == null) result = caseSybaseASEBaseTable(sybaseASETempTable);
                if (result == null) result = caseBaseTable(sybaseASETempTable);
                if (result == null) result = caseSybaseBaseTable(sybaseASETempTable);
                if (result == null) result = caseTable(sybaseASETempTable);
                if (result == null) result = caseSybaseAuthorizedObject(sybaseASETempTable);
                if (result == null) result = caseSQLObject(sybaseASETempTable);
                if (result == null) result = caseENamedElement(sybaseASETempTable);
                if (result == null) result = caseEModelElement(sybaseASETempTable);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.SYBASE_ASE_PROXY_TABLE:
            {
                SybaseASEProxyTable sybaseASEProxyTable = (SybaseASEProxyTable)theEObject;
                Object result = caseSybaseASEProxyTable(sybaseASEProxyTable);
                if (result == null) result = caseSybaseASETable(sybaseASEProxyTable);
                if (result == null) result = casePersistentTable(sybaseASEProxyTable);
                if (result == null) result = caseSybaseASEBaseTable(sybaseASEProxyTable);
                if (result == null) result = caseBaseTable(sybaseASEProxyTable);
                if (result == null) result = caseSybaseBaseTable(sybaseASEProxyTable);
                if (result == null) result = caseTable(sybaseASEProxyTable);
                if (result == null) result = caseSybaseAuthorizedObject(sybaseASEProxyTable);
                if (result == null) result = caseSQLObject(sybaseASEProxyTable);
                if (result == null) result = caseENamedElement(sybaseASEProxyTable);
                if (result == null) result = caseEModelElement(sybaseASEProxyTable);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.SYBASE_ASE_WEB_SERVICE_TABLE:
            {
                SybaseASEWebServiceTable sybaseASEWebServiceTable = (SybaseASEWebServiceTable)theEObject;
                Object result = caseSybaseASEWebServiceTable(sybaseASEWebServiceTable);
                if (result == null) result = caseSybaseASEProxyTable(sybaseASEWebServiceTable);
                if (result == null) result = caseSybaseASETable(sybaseASEWebServiceTable);
                if (result == null) result = casePersistentTable(sybaseASEWebServiceTable);
                if (result == null) result = caseSybaseASEBaseTable(sybaseASEWebServiceTable);
                if (result == null) result = caseBaseTable(sybaseASEWebServiceTable);
                if (result == null) result = caseSybaseBaseTable(sybaseASEWebServiceTable);
                if (result == null) result = caseTable(sybaseASEWebServiceTable);
                if (result == null) result = caseSybaseAuthorizedObject(sybaseASEWebServiceTable);
                if (result == null) result = caseSQLObject(sybaseASEWebServiceTable);
                if (result == null) result = caseENamedElement(sybaseASEWebServiceTable);
                if (result == null) result = caseEModelElement(sybaseASEWebServiceTable);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.SYBASE_ASE_BASE_TABLE:
            {
                SybaseASEBaseTable sybaseASEBaseTable = (SybaseASEBaseTable)theEObject;
                Object result = caseSybaseASEBaseTable(sybaseASEBaseTable);
                if (result == null) result = caseSybaseBaseTable(sybaseASEBaseTable);
                if (result == null) result = caseBaseTable(sybaseASEBaseTable);
                if (result == null) result = caseSybaseAuthorizedObject(sybaseASEBaseTable);
                if (result == null) result = caseTable(sybaseASEBaseTable);
                if (result == null) result = caseSQLObject(sybaseASEBaseTable);
                if (result == null) result = caseENamedElement(sybaseASEBaseTable);
                if (result == null) result = caseEModelElement(sybaseASEBaseTable);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.SYBASE_ASE_USER:
            {
                SybaseASEUser sybaseASEUser = (SybaseASEUser)theEObject;
                Object result = caseSybaseASEUser(sybaseASEUser);
                if (result == null) result = caseUser(sybaseASEUser);
                if (result == null) result = caseSybaseAuthorizationIdentifier(sybaseASEUser);
                if (result == null) result = caseAuthorizationIdentifier(sybaseASEUser);
                if (result == null) result = caseSQLObject(sybaseASEUser);
                if (result == null) result = caseENamedElement(sybaseASEUser);
                if (result == null) result = caseEModelElement(sybaseASEUser);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.SYBASE_ASE_GROUP:
            {
                SybaseASEGroup sybaseASEGroup = (SybaseASEGroup)theEObject;
                Object result = caseSybaseASEGroup(sybaseASEGroup);
                if (result == null) result = caseGroup(sybaseASEGroup);
                if (result == null) result = caseSybaseAuthorizationIdentifier(sybaseASEGroup);
                if (result == null) result = caseAuthorizationIdentifier(sybaseASEGroup);
                if (result == null) result = caseSQLObject(sybaseASEGroup);
                if (result == null) result = caseENamedElement(sybaseASEGroup);
                if (result == null) result = caseEModelElement(sybaseASEGroup);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.SYBASE_ASE_PRIVILEGE:
            {
                SybaseASEPrivilege sybaseASEPrivilege = (SybaseASEPrivilege)theEObject;
                Object result = caseSybaseASEPrivilege(sybaseASEPrivilege);
                if (result == null) result = caseSybasePrivilege(sybaseASEPrivilege);
                if (result == null) result = casePrivilege(sybaseASEPrivilege);
                if (result == null) result = caseSQLObject(sybaseASEPrivilege);
                if (result == null) result = caseENamedElement(sybaseASEPrivilege);
                if (result == null) result = caseEModelElement(sybaseASEPrivilege);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.SYBASE_ASE_TRIGGER:
            {
                SybaseASETrigger sybaseASETrigger = (SybaseASETrigger)theEObject;
                Object result = caseSybaseASETrigger(sybaseASETrigger);
                if (result == null) result = caseTrigger(sybaseASETrigger);
                if (result == null) result = caseSQLObject(sybaseASETrigger);
                if (result == null) result = caseENamedElement(sybaseASETrigger);
                if (result == null) result = caseEModelElement(sybaseASETrigger);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case SybaseasesqlmodelPackage.SYBASE_ASE_CHECK_CONSTRAINT:
            {
                SybaseASECheckConstraint sybaseASECheckConstraint = (SybaseASECheckConstraint)theEObject;
                Object result = caseSybaseASECheckConstraint(sybaseASECheckConstraint);
                if (result == null) result = caseCheckConstraint(sybaseASECheckConstraint);
                if (result == null) result = caseTableConstraint(sybaseASECheckConstraint);
                if (result == null) result = caseConstraint(sybaseASECheckConstraint);
                if (result == null) result = caseSQLObject(sybaseASECheckConstraint);
                if (result == null) result = caseENamedElement(sybaseASECheckConstraint);
                if (result == null) result = caseEModelElement(sybaseASECheckConstraint);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE Schema</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE Schema</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSybaseASESchema(SybaseASESchema object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE Database</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE Database</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSybaseASEDatabase(SybaseASEDatabase object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE Web Service</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE Web Service</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSybaseASEWebService(SybaseASEWebService object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE Predefined Data Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE Predefined Data Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSybaseASEPredefinedDataType(SybaseASEPredefinedDataType object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE Catalog</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE Catalog</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSybaseASECatalog(SybaseASECatalog object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE Procedure</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE Procedure</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSybaseASEProcedure(SybaseASEProcedure object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE Default</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE Default</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSybaseASEDefault(SybaseASEDefault object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE Rule</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE Rule</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSybaseASERule(SybaseASERule object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE Index</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE Index</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSybaseASEIndex(SybaseASEIndex object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE Segment</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE Segment</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSybaseASESegment(SybaseASESegment object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE Func Based Index Member</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE Func Based Index Member</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSybaseASEFuncBasedIndexMember(SybaseASEFuncBasedIndexMember object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE Table</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE Table</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSybaseASETable(SybaseASETable object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE Column Check Constraint</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE Column Check Constraint</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSybaseASEColumnCheckConstraint(SybaseASEColumnCheckConstraint object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE Column</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE Column</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSybaseASEColumn(SybaseASEColumn object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE Unique Constraint</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE Unique Constraint</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSybaseASEUniqueConstraint(SybaseASEUniqueConstraint object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE Primary Key</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE Primary Key</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSybaseASEPrimaryKey(SybaseASEPrimaryKey object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Device Item</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Device Item</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseDeviceItem(DeviceItem object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Segment Threshold</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Segment Threshold</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSegmentThreshold(SegmentThreshold object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Cache Info</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Cache Info</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseCacheInfo(CacheInfo object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE User Defined Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE User Defined Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSybaseASEUserDefinedType(SybaseASEUserDefinedType object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE Encryption Key</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE Encryption Key</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSybaseASEEncryptionKey(SybaseASEEncryptionKey object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Lock Promotion Info</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Lock Promotion Info</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseLockPromotionInfo(LockPromotionInfo object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE Role</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE Role</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSybaseASERole(SybaseASERole object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE Cache</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE Cache</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSybaseASECache(SybaseASECache object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE View Table</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE View Table</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSybaseASEViewTable(SybaseASEViewTable object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE Temp Table</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE Temp Table</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSybaseASETempTable(SybaseASETempTable object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE Proxy Table</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE Proxy Table</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSybaseASEProxyTable(SybaseASEProxyTable object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE Web Service Table</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE Web Service Table</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSybaseASEWebServiceTable(SybaseASEWebServiceTable object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE Base Table</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE Base Table</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSybaseASEBaseTable(SybaseASEBaseTable object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE User</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE User</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSybaseASEUser(SybaseASEUser object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE Group</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE Group</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSybaseASEGroup(SybaseASEGroup object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE Privilege</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE Privilege</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSybaseASEPrivilege(SybaseASEPrivilege object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE Trigger</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE Trigger</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSybaseASETrigger(SybaseASETrigger object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase ASE Check Constraint</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase ASE Check Constraint</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSybaseASECheckConstraint(SybaseASECheckConstraint object) {
        return null;
    }

				/**
     * Returns the result of interpreting the object as an instance of '<em>EModel Element</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EModel Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseEModelElement(EModelElement object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>ENamed Element</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>ENamed Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseENamedElement(ENamedElement object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>SQL Object</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>SQL Object</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSQLObject(SQLObject object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Schema</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Schema</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSchema(Schema object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Database</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Database</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseDatabase(Database object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Data Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Data Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseDataType(DataType object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>SQL Data Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>SQL Data Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseSQLDataType(SQLDataType object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Predefined Data Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Predefined Data Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object casePredefinedDataType(PredefinedDataType object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Catalog</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Catalog</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseCatalog(Catalog object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Routine</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Routine</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseRoutine(Routine object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Procedure</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Procedure</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseProcedure(Procedure object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase Authorized Object</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase Authorized Object</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSybaseAuthorizedObject(SybaseAuthorizedObject object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase Routine</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase Routine</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSybaseRoutine(SybaseRoutine object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Index</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Index</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseIndex(Index object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Index Member</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Index Member</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseIndexMember(IndexMember object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase Index Member</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase Index Member</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSybaseIndexMember(SybaseIndexMember object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Table</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Table</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseTable(Table object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Base Table</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Base Table</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseBaseTable(BaseTable object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Persistent Table</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Persistent Table</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object casePersistentTable(PersistentTable object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase Base Table</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase Base Table</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSybaseBaseTable(SybaseBaseTable object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Constraint</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Constraint</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseConstraint(Constraint object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Table Constraint</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Table Constraint</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseTableConstraint(TableConstraint object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Check Constraint</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Check Constraint</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseCheckConstraint(CheckConstraint object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Typed Element</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Typed Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseTypedElement(TypedElement object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Column</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Column</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseColumn(Column object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Reference Constraint</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Reference Constraint</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseReferenceConstraint(ReferenceConstraint object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Unique Constraint</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Unique Constraint</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseUniqueConstraint(UniqueConstraint object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Primary Key</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Primary Key</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object casePrimaryKey(PrimaryKey object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>User Defined Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>User Defined Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseUserDefinedType(UserDefinedType object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Distinct User Defined Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Distinct User Defined Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseDistinctUserDefinedType(DistinctUserDefinedType object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Authorization Identifier</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Authorization Identifier</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseAuthorizationIdentifier(AuthorizationIdentifier object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Role</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Role</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseRole(Role object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase Authorization Identifier</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase Authorization Identifier</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSybaseAuthorizationIdentifier(SybaseAuthorizationIdentifier object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Derived Table</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Derived Table</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseDerivedTable(DerivedTable object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>View Table</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>View Table</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseViewTable(ViewTable object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase View Table</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase View Table</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSybaseViewTable(SybaseViewTable object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Temporary Table</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Temporary Table</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseTemporaryTable(TemporaryTable object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>User</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>User</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseUser(User object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Group</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Group</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseGroup(Group object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Privilege</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Privilege</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object casePrivilege(Privilege object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sybase Privilege</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sybase Privilege</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseSybasePrivilege(SybasePrivilege object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Trigger</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Trigger</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseTrigger(Trigger object)
    {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
	public Object defaultCase(EObject object)
    {
        return null;
    }

} //SybaseasesqlmodelSwitch

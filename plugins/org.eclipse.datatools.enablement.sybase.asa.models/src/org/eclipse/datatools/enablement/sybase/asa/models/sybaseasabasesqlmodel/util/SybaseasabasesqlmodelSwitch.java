/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseasabasesqlmodelSwitch.java,v 1.3 2008/03/27 07:35:08 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.util;

import java.util.List;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.*;

import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizationIdentifier;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseAuthorizedObject;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EncryptionInfo;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule;
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
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUserDefinedType;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseViewTable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseBaseTable;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseRoutine;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseViewTable;
import org.eclipse.datatools.modelbase.sql.accesscontrol.AuthorizationIdentifier;
import org.eclipse.datatools.modelbase.sql.accesscontrol.Group;
import org.eclipse.datatools.modelbase.sql.accesscontrol.User;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.Constraint;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.constraints.Index;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.constraints.ReferenceConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.TableConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.UniqueConstraint;
import org.eclipse.datatools.modelbase.sql.datatypes.DataType;
import org.eclipse.datatools.modelbase.sql.datatypes.DistinctUserDefinedType;
import org.eclipse.datatools.modelbase.sql.datatypes.Domain;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.SQLDataType;
import org.eclipse.datatools.modelbase.sql.datatypes.UserDefinedType;
import org.eclipse.datatools.modelbase.sql.routines.Function;
import org.eclipse.datatools.modelbase.sql.routines.Parameter;
import org.eclipse.datatools.modelbase.sql.routines.Procedure;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.routines.UserDefinedFunction;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Event;
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
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage
 * @generated
 */
public class SybaseasabasesqlmodelSwitch 
{
    /**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SybaseasabasesqlmodelPackage modelPackage;

    /**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SybaseasabasesqlmodelSwitch()
    {
		if (modelPackage == null) {
			modelPackage = SybaseasabasesqlmodelPackage.eINSTANCE;
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
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
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
		switch (classifierID) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_EVENT: {
				SybaseASABaseEvent sybaseASABaseEvent = (SybaseASABaseEvent)theEObject;
				Object result = caseSybaseASABaseEvent(sybaseASABaseEvent);
				if (result == null) result = caseEvent(sybaseASABaseEvent);
				if (result == null) result = caseSQLObject(sybaseASABaseEvent);
				if (result == null) result = caseENamedElement(sybaseASABaseEvent);
				if (result == null) result = caseEModelElement(sybaseASABaseEvent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE: {
				SybaseASABaseDatabase sybaseASABaseDatabase = (SybaseASABaseDatabase)theEObject;
				Object result = caseSybaseASABaseDatabase(sybaseASABaseDatabase);
				if (result == null) result = caseDatabase(sybaseASABaseDatabase);
				if (result == null) result = caseSQLObject(sybaseASABaseDatabase);
				if (result == null) result = caseENamedElement(sybaseASABaseDatabase);
				if (result == null) result = caseEModelElement(sybaseASABaseDatabase);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_WEB_SERVICE: {
				SybaseASAWebService sybaseASAWebService = (SybaseASAWebService)theEObject;
				Object result = caseSybaseASAWebService(sybaseASAWebService);
				if (result == null) result = caseSQLObject(sybaseASAWebService);
				if (result == null) result = caseENamedElement(sybaseASAWebService);
				if (result == null) result = caseEModelElement(sybaseASAWebService);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybaseasabasesqlmodelPackage.ENCRYPTION_INFO: {
				EncryptionInfo encryptionInfo = (EncryptionInfo)theEObject;
				Object result = caseEncryptionInfo(encryptionInfo);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_USER_DEFINED_TYPE: {
				SybaseASABaseUserDefinedType sybaseASABaseUserDefinedType = (SybaseASABaseUserDefinedType)theEObject;
				Object result = caseSybaseASABaseUserDefinedType(sybaseASABaseUserDefinedType);
				if (result == null) result = caseDomain(sybaseASABaseUserDefinedType);
				if (result == null) result = caseDistinctUserDefinedType(sybaseASABaseUserDefinedType);
				if (result == null) result = caseUserDefinedType(sybaseASABaseUserDefinedType);
				if (result == null) result = caseDataType(sybaseASABaseUserDefinedType);
				if (result == null) result = caseSQLObject(sybaseASABaseUserDefinedType);
				if (result == null) result = caseENamedElement(sybaseASABaseUserDefinedType);
				if (result == null) result = caseEModelElement(sybaseASABaseUserDefinedType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PREDEFINED_DATA_TYPE: {
				SybaseASABasePredefinedDataType sybaseASABasePredefinedDataType = (SybaseASABasePredefinedDataType)theEObject;
				Object result = caseSybaseASABasePredefinedDataType(sybaseASABasePredefinedDataType);
				if (result == null) result = casePredefinedDataType(sybaseASABasePredefinedDataType);
				if (result == null) result = caseSQLDataType(sybaseASABasePredefinedDataType);
				if (result == null) result = caseDataType(sybaseASABasePredefinedDataType);
				if (result == null) result = caseSQLObject(sybaseASABasePredefinedDataType);
				if (result == null) result = caseENamedElement(sybaseASABasePredefinedDataType);
				if (result == null) result = caseEModelElement(sybaseASABasePredefinedDataType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE: {
				SybaseASABaseTable sybaseASABaseTable = (SybaseASABaseTable)theEObject;
				Object result = caseSybaseASABaseTable(sybaseASABaseTable);
				if (result == null) result = casePersistentTable(sybaseASABaseTable);
				if (result == null) result = caseSybaseBaseTable(sybaseASABaseTable);
				if (result == null) result = caseBaseTable(sybaseASABaseTable);
				if (result == null) result = caseSybaseAuthorizedObject(sybaseASABaseTable);
				if (result == null) result = caseTable(sybaseASABaseTable);
				if (result == null) result = caseSQLObject(sybaseASABaseTable);
				if (result == null) result = caseENamedElement(sybaseASABaseTable);
				if (result == null) result = caseEModelElement(sybaseASABaseTable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN: {
				SybaseASABaseColumn sybaseASABaseColumn = (SybaseASABaseColumn)theEObject;
				Object result = caseSybaseASABaseColumn(sybaseASABaseColumn);
				if (result == null) result = caseColumn(sybaseASABaseColumn);
				if (result == null) result = caseSybaseAuthorizedObject(sybaseASABaseColumn);
				if (result == null) result = caseTypedElement(sybaseASABaseColumn);
				if (result == null) result = caseSQLObject(sybaseASABaseColumn);
				if (result == null) result = caseENamedElement(sybaseASABaseColumn);
				if (result == null) result = caseEModelElement(sybaseASABaseColumn);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_UNIQUE_CONSTRAINT: {
				SybaseASABaseUniqueConstraint sybaseASABaseUniqueConstraint = (SybaseASABaseUniqueConstraint)theEObject;
				Object result = caseSybaseASABaseUniqueConstraint(sybaseASABaseUniqueConstraint);
				if (result == null) result = caseUniqueConstraint(sybaseASABaseUniqueConstraint);
				if (result == null) result = caseReferenceConstraint(sybaseASABaseUniqueConstraint);
				if (result == null) result = caseTableConstraint(sybaseASABaseUniqueConstraint);
				if (result == null) result = caseConstraint(sybaseASABaseUniqueConstraint);
				if (result == null) result = caseSQLObject(sybaseASABaseUniqueConstraint);
				if (result == null) result = caseENamedElement(sybaseASABaseUniqueConstraint);
				if (result == null) result = caseEModelElement(sybaseASABaseUniqueConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PRIMARY_KEY: {
				SybaseASABasePrimaryKey sybaseASABasePrimaryKey = (SybaseASABasePrimaryKey)theEObject;
				Object result = caseSybaseASABasePrimaryKey(sybaseASABasePrimaryKey);
				if (result == null) result = caseSybaseASABaseUniqueConstraint(sybaseASABasePrimaryKey);
				if (result == null) result = casePrimaryKey(sybaseASABasePrimaryKey);
				if (result == null) result = caseUniqueConstraint(sybaseASABasePrimaryKey);
				if (result == null) result = caseReferenceConstraint(sybaseASABasePrimaryKey);
				if (result == null) result = caseTableConstraint(sybaseASABasePrimaryKey);
				if (result == null) result = caseConstraint(sybaseASABasePrimaryKey);
				if (result == null) result = caseSQLObject(sybaseASABasePrimaryKey);
				if (result == null) result = caseENamedElement(sybaseASABasePrimaryKey);
				if (result == null) result = caseEModelElement(sybaseASABasePrimaryKey);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY: {
				SybaseASABaseForeignKey sybaseASABaseForeignKey = (SybaseASABaseForeignKey)theEObject;
				Object result = caseSybaseASABaseForeignKey(sybaseASABaseForeignKey);
				if (result == null) result = caseForeignKey(sybaseASABaseForeignKey);
				if (result == null) result = caseReferenceConstraint(sybaseASABaseForeignKey);
				if (result == null) result = caseTableConstraint(sybaseASABaseForeignKey);
				if (result == null) result = caseConstraint(sybaseASABaseForeignKey);
				if (result == null) result = caseSQLObject(sybaseASABaseForeignKey);
				if (result == null) result = caseENamedElement(sybaseASABaseForeignKey);
				if (result == null) result = caseEModelElement(sybaseASABaseForeignKey);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX: {
				SybaseASABaseIndex sybaseASABaseIndex = (SybaseASABaseIndex)theEObject;
				Object result = caseSybaseASABaseIndex(sybaseASABaseIndex);
				if (result == null) result = caseIndex(sybaseASABaseIndex);
				if (result == null) result = caseSQLObject(sybaseASABaseIndex);
				if (result == null) result = caseENamedElement(sybaseASABaseIndex);
				if (result == null) result = caseEModelElement(sybaseASABaseIndex);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DB_SPACE: {
				SybaseASABaseDBSpace sybaseASABaseDBSpace = (SybaseASABaseDBSpace)theEObject;
				Object result = caseSybaseASABaseDBSpace(sybaseASABaseDBSpace);
				if (result == null) result = caseSQLObject(sybaseASABaseDBSpace);
				if (result == null) result = caseENamedElement(sybaseASABaseDBSpace);
				if (result == null) result = caseEModelElement(sybaseASABaseDBSpace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_VIEW_TABLE: {
				SybaseASABaseViewTable sybaseASABaseViewTable = (SybaseASABaseViewTable)theEObject;
				Object result = caseSybaseASABaseViewTable(sybaseASABaseViewTable);
				if (result == null) result = caseSybaseViewTable(sybaseASABaseViewTable);
				if (result == null) result = caseViewTable(sybaseASABaseViewTable);
				if (result == null) result = caseSybaseAuthorizedObject(sybaseASABaseViewTable);
				if (result == null) result = caseDerivedTable(sybaseASABaseViewTable);
				if (result == null) result = caseSQLObject(sybaseASABaseViewTable);
				if (result == null) result = caseTable(sybaseASABaseViewTable);
				if (result == null) result = caseENamedElement(sybaseASABaseViewTable);
				if (result == null) result = caseEModelElement(sybaseASABaseViewTable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FUNCTION: {
				SybaseASABaseFunction sybaseASABaseFunction = (SybaseASABaseFunction)theEObject;
				Object result = caseSybaseASABaseFunction(sybaseASABaseFunction);
				if (result == null) result = caseUserDefinedFunction(sybaseASABaseFunction);
				if (result == null) result = caseSybaseRoutine(sybaseASABaseFunction);
				if (result == null) result = caseFunction(sybaseASABaseFunction);
				if (result == null) result = caseRoutine(sybaseASABaseFunction);
				if (result == null) result = caseSybaseAuthorizedObject(sybaseASABaseFunction);
				if (result == null) result = caseSQLObject(sybaseASABaseFunction);
				if (result == null) result = caseENamedElement(sybaseASABaseFunction);
				if (result == null) result = caseEModelElement(sybaseASABaseFunction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PROCEDURE: {
				SybaseASABaseProcedure sybaseASABaseProcedure = (SybaseASABaseProcedure)theEObject;
				Object result = caseSybaseASABaseProcedure(sybaseASABaseProcedure);
				if (result == null) result = caseProcedure(sybaseASABaseProcedure);
				if (result == null) result = caseSybaseRoutine(sybaseASABaseProcedure);
				if (result == null) result = caseRoutine(sybaseASABaseProcedure);
				if (result == null) result = caseSybaseAuthorizedObject(sybaseASABaseProcedure);
				if (result == null) result = caseSQLObject(sybaseASABaseProcedure);
				if (result == null) result = caseENamedElement(sybaseASABaseProcedure);
				if (result == null) result = caseEModelElement(sybaseASABaseProcedure);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TEMP_TABLE: {
				SybaseASABaseTempTable sybaseASABaseTempTable = (SybaseASABaseTempTable)theEObject;
				Object result = caseSybaseASABaseTempTable(sybaseASABaseTempTable);
				if (result == null) result = caseTemporaryTable(sybaseASABaseTempTable);
				if (result == null) result = caseSybaseBaseTable(sybaseASABaseTempTable);
				if (result == null) result = caseBaseTable(sybaseASABaseTempTable);
				if (result == null) result = caseSybaseAuthorizedObject(sybaseASABaseTempTable);
				if (result == null) result = caseTable(sybaseASABaseTempTable);
				if (result == null) result = caseSQLObject(sybaseASABaseTempTable);
				if (result == null) result = caseENamedElement(sybaseASABaseTempTable);
				if (result == null) result = caseEModelElement(sybaseASABaseTempTable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER: {
				SybaseASABaseTrigger sybaseASABaseTrigger = (SybaseASABaseTrigger)theEObject;
				Object result = caseSybaseASABaseTrigger(sybaseASABaseTrigger);
				if (result == null) result = caseTrigger(sybaseASABaseTrigger);
				if (result == null) result = caseSQLObject(sybaseASABaseTrigger);
				if (result == null) result = caseENamedElement(sybaseASABaseTrigger);
				if (result == null) result = caseEModelElement(sybaseASABaseTrigger);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PROXY_TABLE: {
				SybaseASABaseProxyTable sybaseASABaseProxyTable = (SybaseASABaseProxyTable)theEObject;
				Object result = caseSybaseASABaseProxyTable(sybaseASABaseProxyTable);
				if (result == null) result = caseSybaseASABaseTable(sybaseASABaseProxyTable);
				if (result == null) result = casePersistentTable(sybaseASABaseProxyTable);
				if (result == null) result = caseSybaseBaseTable(sybaseASABaseProxyTable);
				if (result == null) result = caseBaseTable(sybaseASABaseProxyTable);
				if (result == null) result = caseSybaseAuthorizedObject(sybaseASABaseProxyTable);
				if (result == null) result = caseTable(sybaseASABaseProxyTable);
				if (result == null) result = caseSQLObject(sybaseASABaseProxyTable);
				if (result == null) result = caseENamedElement(sybaseASABaseProxyTable);
				if (result == null) result = caseEModelElement(sybaseASABaseProxyTable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN_CHECK_CONSTRAINT: {
				SybaseASABaseColumnCheckConstraint sybaseASABaseColumnCheckConstraint = (SybaseASABaseColumnCheckConstraint)theEObject;
				Object result = caseSybaseASABaseColumnCheckConstraint(sybaseASABaseColumnCheckConstraint);
				if (result == null) result = caseCheckConstraint(sybaseASABaseColumnCheckConstraint);
				if (result == null) result = caseTableConstraint(sybaseASABaseColumnCheckConstraint);
				if (result == null) result = caseConstraint(sybaseASABaseColumnCheckConstraint);
				if (result == null) result = caseSQLObject(sybaseASABaseColumnCheckConstraint);
				if (result == null) result = caseENamedElement(sybaseASABaseColumnCheckConstraint);
				if (result == null) result = caseEModelElement(sybaseASABaseColumnCheckConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybaseasabasesqlmodelPackage.SCHEDULE: {
				Schedule schedule = (Schedule)theEObject;
				Object result = caseSchedule(schedule);
				if (result == null) result = caseSQLObject(schedule);
				if (result == null) result = caseENamedElement(schedule);
				if (result == null) result = caseEModelElement(schedule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_REMOTE_PROCEDURE: {
				SybaseASABaseRemoteProcedure sybaseASABaseRemoteProcedure = (SybaseASABaseRemoteProcedure)theEObject;
				Object result = caseSybaseASABaseRemoteProcedure(sybaseASABaseRemoteProcedure);
				if (result == null) result = caseSybaseASABaseProcedure(sybaseASABaseRemoteProcedure);
				if (result == null) result = caseProcedure(sybaseASABaseRemoteProcedure);
				if (result == null) result = caseSybaseRoutine(sybaseASABaseRemoteProcedure);
				if (result == null) result = caseRoutine(sybaseASABaseRemoteProcedure);
				if (result == null) result = caseSybaseAuthorizedObject(sybaseASABaseRemoteProcedure);
				if (result == null) result = caseSQLObject(sybaseASABaseRemoteProcedure);
				if (result == null) result = caseENamedElement(sybaseASABaseRemoteProcedure);
				if (result == null) result = caseEModelElement(sybaseASABaseRemoteProcedure);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PARAMETER: {
				SybaseASABaseParameter sybaseASABaseParameter = (SybaseASABaseParameter)theEObject;
				Object result = caseSybaseASABaseParameter(sybaseASABaseParameter);
				if (result == null) result = caseParameter(sybaseASABaseParameter);
				if (result == null) result = caseSybaseParameter(sybaseASABaseParameter);
				if (result == null) result = caseTypedElement(sybaseASABaseParameter);
				if (result == null) result = caseSQLObject(sybaseASABaseParameter);
				if (result == null) result = caseENamedElement(sybaseASABaseParameter);
				if (result == null) result = caseEModelElement(sybaseASABaseParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_GROUP: {
				SybaseASABaseGroup sybaseASABaseGroup = (SybaseASABaseGroup)theEObject;
				Object result = caseSybaseASABaseGroup(sybaseASABaseGroup);
				if (result == null) result = caseUser(sybaseASABaseGroup);
				if (result == null) result = caseGroup(sybaseASABaseGroup);
				if (result == null) result = caseSybaseAuthorizationIdentifier(sybaseASABaseGroup);
				if (result == null) result = caseAuthorizationIdentifier(sybaseASABaseGroup);
				if (result == null) result = caseSQLObject(sybaseASABaseGroup);
				if (result == null) result = caseENamedElement(sybaseASABaseGroup);
				if (result == null) result = caseEModelElement(sybaseASABaseGroup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_USER: {
				SybaseASABaseUser sybaseASABaseUser = (SybaseASABaseUser)theEObject;
				Object result = caseSybaseASABaseUser(sybaseASABaseUser);
				if (result == null) result = caseUser(sybaseASABaseUser);
				if (result == null) result = caseSybaseAuthorizationIdentifier(sybaseASABaseUser);
				if (result == null) result = caseAuthorizationIdentifier(sybaseASABaseUser);
				if (result == null) result = caseSQLObject(sybaseASABaseUser);
				if (result == null) result = caseENamedElement(sybaseASABaseUser);
				if (result == null) result = caseEModelElement(sybaseASABaseUser);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_SCHEMA: {
				SybaseASABaseSchema sybaseASABaseSchema = (SybaseASABaseSchema)theEObject;
				Object result = caseSybaseASABaseSchema(sybaseASABaseSchema);
				if (result == null) result = caseSchema(sybaseASABaseSchema);
				if (result == null) result = caseSQLObject(sybaseASABaseSchema);
				if (result == null) result = caseENamedElement(sybaseASABaseSchema);
				if (result == null) result = caseEModelElement(sybaseASABaseSchema);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_DEFAULT_WRAPPER: {
				SybaseASADefaultWrapper sybaseASADefaultWrapper = (SybaseASADefaultWrapper)theEObject;
				Object result = caseSybaseASADefaultWrapper(sybaseASADefaultWrapper);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SybaseasabasesqlmodelPackage.EVENT_CONDITION: {
				EventCondition eventCondition = (EventCondition)theEObject;
				Object result = caseEventCondition(eventCondition);
				if (result == null) result = caseSQLObject(eventCondition);
				if (result == null) result = caseENamedElement(eventCondition);
				if (result == null) result = caseEModelElement(eventCondition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Base Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Base Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASABaseEvent(SybaseASABaseEvent object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Base Database</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Base Database</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASABaseDatabase(SybaseASABaseDatabase object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Encryption Info</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Encryption Info</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEncryptionInfo(EncryptionInfo object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Base User Defined Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Base User Defined Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASABaseUserDefinedType(SybaseASABaseUserDefinedType object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Base Predefined Data Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Base Predefined Data Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASABasePredefinedDataType(SybaseASABasePredefinedDataType object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Base Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Base Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASABaseTable(SybaseASABaseTable object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Base Column</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Base Column</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASABaseColumn(SybaseASABaseColumn object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Base Unique Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Base Unique Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASABaseUniqueConstraint(SybaseASABaseUniqueConstraint object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Base Primary Key</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Base Primary Key</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASABasePrimaryKey(SybaseASABasePrimaryKey object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Base Foreign Key</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Base Foreign Key</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASABaseForeignKey(SybaseASABaseForeignKey object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Base Index</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Base Index</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASABaseIndex(SybaseASABaseIndex object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Base DB Space</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Base DB Space</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASABaseDBSpace(SybaseASABaseDBSpace object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Base View Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Base View Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASABaseViewTable(SybaseASABaseViewTable object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Base Function</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Base Function</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASABaseFunction(SybaseASABaseFunction object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Base Procedure</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Base Procedure</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASABaseProcedure(SybaseASABaseProcedure object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Base Temp Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Base Temp Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASABaseTempTable(SybaseASABaseTempTable object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Base Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Base Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASABaseTrigger(SybaseASABaseTrigger object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Base Proxy Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Base Proxy Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASABaseProxyTable(SybaseASABaseProxyTable object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Base Column Check Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Base Column Check Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASABaseColumnCheckConstraint(SybaseASABaseColumnCheckConstraint object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Web Service</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Web Service</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASAWebService(SybaseASAWebService object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Schedule</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Schedule</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSchedule(Schedule object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Base Remote Procedure</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Base Remote Procedure</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASABaseRemoteProcedure(SybaseASABaseRemoteProcedure object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Base Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Base Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASABaseParameter(SybaseASABaseParameter object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Base Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Base Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASABaseGroup(SybaseASABaseGroup object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Base Schema</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Base Schema</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseSybaseASABaseSchema(SybaseASABaseSchema object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Base User</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Base User</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseSybaseASABaseUser(SybaseASABaseUser object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase ASA Default Wrapper</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase ASA Default Wrapper</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSybaseASADefaultWrapper(SybaseASADefaultWrapper object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Event Condition</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Event Condition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseEventCondition(EventCondition object)
    {
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
	 * Returns the result of interpreting the object as an instance of '<em>Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEvent(Event object)
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
	 * Returns the result of interpreting the object as an instance of '<em>Domain</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Domain</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDomain(Domain object)
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
	 * Returns the result of interpreting the object as an instance of '<em>Foreign Key</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Foreign Key</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseForeignKey(ForeignKey object)
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
	 * Returns the result of interpreting the object as an instance of '<em>Function</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Function</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseFunction(Function object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>User Defined Function</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>User Defined Function</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseUserDefinedFunction(UserDefinedFunction object)
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
	 * Returns the result of interpreting the object as an instance of '<em>Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseParameter(Parameter object)
    {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Sybase Parameter</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sybase Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseSybaseParameter(SybaseParameter object)
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

} //SybaseasabasesqlmodelSwitch

/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseasabasesqlmodelFactoryImpl.java,v 1.4 2008/03/27 07:35:08 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl;

import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.*;

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
public class SybaseasabasesqlmodelFactoryImpl extends EFactoryImpl implements SybaseasabasesqlmodelFactory
{
    /**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static SybaseasabasesqlmodelFactory init()
    {
		try {
			SybaseasabasesqlmodelFactory theSybaseasabasesqlmodelFactory = (SybaseasabasesqlmodelFactory)EPackage.Registry.INSTANCE.getEFactory("http:///org/eclipse/datatools/connectivity/sqm/sybase/asa/sybaseasabasesqlmodel.ecore"); 
			if (theSybaseasabasesqlmodelFactory != null) {
				return theSybaseasabasesqlmodelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SybaseasabasesqlmodelFactoryImpl();
	}

    /**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SybaseasabasesqlmodelFactoryImpl()
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
		switch (eClass.getClassifierID()) {
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_EVENT: return createSybaseASABaseEvent();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DATABASE: return createSybaseASABaseDatabase();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_WEB_SERVICE: return createSybaseASAWebService();
			case SybaseasabasesqlmodelPackage.ENCRYPTION_INFO: return createEncryptionInfo();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_USER_DEFINED_TYPE: return createSybaseASABaseUserDefinedType();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PREDEFINED_DATA_TYPE: return createSybaseASABasePredefinedDataType();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TABLE: return createSybaseASABaseTable();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN: return createSybaseASABaseColumn();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_UNIQUE_CONSTRAINT: return createSybaseASABaseUniqueConstraint();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PRIMARY_KEY: return createSybaseASABasePrimaryKey();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FOREIGN_KEY: return createSybaseASABaseForeignKey();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_INDEX: return createSybaseASABaseIndex();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_DB_SPACE: return createSybaseASABaseDBSpace();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_VIEW_TABLE: return createSybaseASABaseViewTable();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FUNCTION: return createSybaseASABaseFunction();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PROCEDURE: return createSybaseASABaseProcedure();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TEMP_TABLE: return createSybaseASABaseTempTable();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_TRIGGER: return createSybaseASABaseTrigger();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PROXY_TABLE: return createSybaseASABaseProxyTable();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_COLUMN_CHECK_CONSTRAINT: return createSybaseASABaseColumnCheckConstraint();
			case SybaseasabasesqlmodelPackage.SCHEDULE: return createSchedule();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_REMOTE_PROCEDURE: return createSybaseASABaseRemoteProcedure();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PARAMETER: return createSybaseASABaseParameter();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_GROUP: return createSybaseASABaseGroup();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_USER: return createSybaseASABaseUser();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_SCHEMA: return createSybaseASABaseSchema();
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_DEFAULT_WRAPPER: return createSybaseASADefaultWrapper();
			case SybaseasabasesqlmodelPackage.EVENT_CONDITION: return createEventCondition();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Object createFromString(EDataType eDataType, String initialValue)
    {
		switch (eDataType.getClassifierID()) {
			case SybaseasabasesqlmodelPackage.TRANSACTION_OPTION:
				return createTransactionOptionFromString(eDataType, initialValue);
			case SybaseasabasesqlmodelPackage.TYPE_OF_DEFAULT:
				return createTypeOfDefaultFromString(eDataType, initialValue);
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_ACTION_TIME:
				return createSybaseASABaseActionTimeFromString(eDataType, initialValue);
			case SybaseasabasesqlmodelPackage.EVENT_TYPE:
				return createEventTypeFromString(eDataType, initialValue);
			case SybaseasabasesqlmodelPackage.JAVA_SUPPORT_TYPE:
				return createJavaSupportTypeFromString(eDataType, initialValue);
			case SybaseasabasesqlmodelPackage.EVENT_LOCATION_TYPE:
				return createEventLocationTypeFromString(eDataType, initialValue);
			case SybaseasabasesqlmodelPackage.INTERVAL_UNIT_TYPE:
				return createIntervalUnitTypeFromString(eDataType, initialValue);
			case SybaseasabasesqlmodelPackage.SYSTEM_DEFINED_DEFAULT_TYPE:
				return createSystemDefinedDefaultTypeFromString(eDataType, initialValue);
			case SybaseasabasesqlmodelPackage.ALLOW_NULL_TYPE:
				return createAllowNullTypeFromString(eDataType, initialValue);
			case SybaseasabasesqlmodelPackage.PARAMETER_TYPE:
				return createParameterTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String convertToString(EDataType eDataType, Object instanceValue)
    {
		switch (eDataType.getClassifierID()) {
			case SybaseasabasesqlmodelPackage.TRANSACTION_OPTION:
				return convertTransactionOptionToString(eDataType, instanceValue);
			case SybaseasabasesqlmodelPackage.TYPE_OF_DEFAULT:
				return convertTypeOfDefaultToString(eDataType, instanceValue);
			case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_ACTION_TIME:
				return convertSybaseASABaseActionTimeToString(eDataType, instanceValue);
			case SybaseasabasesqlmodelPackage.EVENT_TYPE:
				return convertEventTypeToString(eDataType, instanceValue);
			case SybaseasabasesqlmodelPackage.JAVA_SUPPORT_TYPE:
				return convertJavaSupportTypeToString(eDataType, instanceValue);
			case SybaseasabasesqlmodelPackage.EVENT_LOCATION_TYPE:
				return convertEventLocationTypeToString(eDataType, instanceValue);
			case SybaseasabasesqlmodelPackage.INTERVAL_UNIT_TYPE:
				return convertIntervalUnitTypeToString(eDataType, instanceValue);
			case SybaseasabasesqlmodelPackage.SYSTEM_DEFINED_DEFAULT_TYPE:
				return convertSystemDefinedDefaultTypeToString(eDataType, instanceValue);
			case SybaseasabasesqlmodelPackage.ALLOW_NULL_TYPE:
				return convertAllowNullTypeToString(eDataType, instanceValue);
			case SybaseasabasesqlmodelPackage.PARAMETER_TYPE:
				return convertParameterTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SybaseASABaseEvent createSybaseASABaseEvent()
    {
		SybaseASABaseEventImpl sybaseASABaseEvent = new SybaseASABaseEventImpl();
		return sybaseASABaseEvent;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SybaseASABaseDatabase createSybaseASABaseDatabase()
    {
		SybaseASABaseDatabaseImpl sybaseASABaseDatabase = new SybaseASABaseDatabaseImpl();
		return sybaseASABaseDatabase;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SybaseASAWebService createSybaseASAWebService()
    {
		SybaseASAWebServiceImpl sybaseASAWebService = new SybaseASAWebServiceImpl();
		return sybaseASAWebService;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EncryptionInfo createEncryptionInfo()
    {
		EncryptionInfoImpl encryptionInfo = new EncryptionInfoImpl();
		return encryptionInfo;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SybaseASABaseUserDefinedType createSybaseASABaseUserDefinedType()
    {
		SybaseASABaseUserDefinedTypeImpl sybaseASABaseUserDefinedType = new SybaseASABaseUserDefinedTypeImpl();
		return sybaseASABaseUserDefinedType;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SybaseASABasePredefinedDataType createSybaseASABasePredefinedDataType()
    {
		SybaseASABasePredefinedDataTypeImpl sybaseASABasePredefinedDataType = new SybaseASABasePredefinedDataTypeImpl();
		return sybaseASABasePredefinedDataType;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SybaseASABaseTable createSybaseASABaseTable()
    {
		SybaseASABaseTableImpl sybaseASABaseTable = new SybaseASABaseTableImpl();
		return sybaseASABaseTable;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SybaseASABaseColumn createSybaseASABaseColumn()
    {
		SybaseASABaseColumnImpl sybaseASABaseColumn = new SybaseASABaseColumnImpl();
		return sybaseASABaseColumn;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SybaseASABaseUniqueConstraint createSybaseASABaseUniqueConstraint()
    {
		SybaseASABaseUniqueConstraintImpl sybaseASABaseUniqueConstraint = new SybaseASABaseUniqueConstraintImpl();
		return sybaseASABaseUniqueConstraint;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SybaseASABasePrimaryKey createSybaseASABasePrimaryKey()
    {
		SybaseASABasePrimaryKeyImpl sybaseASABasePrimaryKey = new SybaseASABasePrimaryKeyImpl();
		return sybaseASABasePrimaryKey;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SybaseASABaseForeignKey createSybaseASABaseForeignKey()
    {
		SybaseASABaseForeignKeyImpl sybaseASABaseForeignKey = new SybaseASABaseForeignKeyImpl();
		return sybaseASABaseForeignKey;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SybaseASABaseIndex createSybaseASABaseIndex()
    {
		SybaseASABaseIndexImpl sybaseASABaseIndex = new SybaseASABaseIndexImpl();
		return sybaseASABaseIndex;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SybaseASABaseDBSpace createSybaseASABaseDBSpace()
    {
		SybaseASABaseDBSpaceImpl sybaseASABaseDBSpace = new SybaseASABaseDBSpaceImpl();
		return sybaseASABaseDBSpace;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SybaseASABaseViewTable createSybaseASABaseViewTable()
    {
		SybaseASABaseViewTableImpl sybaseASABaseViewTable = new SybaseASABaseViewTableImpl();
		return sybaseASABaseViewTable;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SybaseASABaseFunction createSybaseASABaseFunction()
    {
		SybaseASABaseFunctionImpl sybaseASABaseFunction = new SybaseASABaseFunctionImpl();
		return sybaseASABaseFunction;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SybaseASABaseProcedure createSybaseASABaseProcedure()
    {
		SybaseASABaseProcedureImpl sybaseASABaseProcedure = new SybaseASABaseProcedureImpl();
		return sybaseASABaseProcedure;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SybaseASABaseTempTable createSybaseASABaseTempTable()
    {
		SybaseASABaseTempTableImpl sybaseASABaseTempTable = new SybaseASABaseTempTableImpl();
		return sybaseASABaseTempTable;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SybaseASABaseTrigger createSybaseASABaseTrigger()
    {
		SybaseASABaseTriggerImpl sybaseASABaseTrigger = new SybaseASABaseTriggerImpl();
		return sybaseASABaseTrigger;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SybaseASABaseProxyTable createSybaseASABaseProxyTable()
    {
		SybaseASABaseProxyTableImpl sybaseASABaseProxyTable = new SybaseASABaseProxyTableImpl();
		return sybaseASABaseProxyTable;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SybaseASABaseColumnCheckConstraint createSybaseASABaseColumnCheckConstraint()
    {
		SybaseASABaseColumnCheckConstraintImpl sybaseASABaseColumnCheckConstraint = new SybaseASABaseColumnCheckConstraintImpl();
		return sybaseASABaseColumnCheckConstraint;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Schedule createSchedule()
    {
		ScheduleImpl schedule = new ScheduleImpl();
		return schedule;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SybaseASABaseRemoteProcedure createSybaseASABaseRemoteProcedure()
    {
		SybaseASABaseRemoteProcedureImpl sybaseASABaseRemoteProcedure = new SybaseASABaseRemoteProcedureImpl();
		return sybaseASABaseRemoteProcedure;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SybaseASABaseParameter createSybaseASABaseParameter()
    {
		SybaseASABaseParameterImpl sybaseASABaseParameter = new SybaseASABaseParameterImpl();
		return sybaseASABaseParameter;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SybaseASABaseGroup createSybaseASABaseGroup()
    {
		SybaseASABaseGroupImpl sybaseASABaseGroup = new SybaseASABaseGroupImpl();
		return sybaseASABaseGroup;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SybaseASABaseSchema createSybaseASABaseSchema()
    {
		SybaseASABaseSchemaImpl sybaseASABaseSchema = new SybaseASABaseSchemaImpl();
		return sybaseASABaseSchema;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SybaseASABaseUser createSybaseASABaseUser()
    {
		SybaseASABaseUserImpl sybaseASABaseUser = new SybaseASABaseUserImpl();
		return sybaseASABaseUser;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SybaseASADefaultWrapper createSybaseASADefaultWrapper()
    {
		SybaseASADefaultWrapperImpl sybaseASADefaultWrapper = new SybaseASADefaultWrapperImpl();
		return sybaseASADefaultWrapper;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EventCondition createEventCondition()
    {
		EventConditionImpl eventCondition = new EventConditionImpl();
		return eventCondition;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public TransactionOption createTransactionOptionFromString(EDataType eDataType, String initialValue)
    {
		TransactionOption result = TransactionOption.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String convertTransactionOptionToString(EDataType eDataType, Object instanceValue)
    {
		return instanceValue == null ? null : instanceValue.toString();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public TypeOfDefault createTypeOfDefaultFromString(EDataType eDataType, String initialValue)
    {
		TypeOfDefault result = TypeOfDefault.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String convertTypeOfDefaultToString(EDataType eDataType, Object instanceValue)
    {
		return instanceValue == null ? null : instanceValue.toString();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SybaseASABaseActionTime createSybaseASABaseActionTimeFromString(EDataType eDataType, String initialValue)
    {
		SybaseASABaseActionTime result = SybaseASABaseActionTime.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String convertSybaseASABaseActionTimeToString(EDataType eDataType, Object instanceValue)
    {
		return instanceValue == null ? null : instanceValue.toString();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EventType createEventTypeFromString(EDataType eDataType, String initialValue)
    {
		EventType result = EventType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String convertEventTypeToString(EDataType eDataType, Object instanceValue)
    {
		return instanceValue == null ? null : instanceValue.toString();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public JavaSupportType createJavaSupportTypeFromString(EDataType eDataType, String initialValue)
    {
		JavaSupportType result = JavaSupportType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String convertJavaSupportTypeToString(EDataType eDataType, Object instanceValue)
    {
		return instanceValue == null ? null : instanceValue.toString();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EventLocationType createEventLocationTypeFromString(EDataType eDataType, String initialValue)
    {
		EventLocationType result = EventLocationType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String convertEventLocationTypeToString(EDataType eDataType, Object instanceValue)
    {
		return instanceValue == null ? null : instanceValue.toString();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public IntervalUnitType createIntervalUnitTypeFromString(EDataType eDataType, String initialValue)
    {
		IntervalUnitType result = IntervalUnitType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String convertIntervalUnitTypeToString(EDataType eDataType, Object instanceValue)
    {
		return instanceValue == null ? null : instanceValue.toString();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SystemDefinedDefaultType createSystemDefinedDefaultTypeFromString(EDataType eDataType, String initialValue)
    {
		SystemDefinedDefaultType result = SystemDefinedDefaultType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String convertSystemDefinedDefaultTypeToString(EDataType eDataType, Object instanceValue)
    {
		return instanceValue == null ? null : instanceValue.toString();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public AllowNullType createAllowNullTypeFromString(EDataType eDataType, String initialValue)
    {
		AllowNullType result = AllowNullType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String convertAllowNullTypeToString(EDataType eDataType, Object instanceValue)
    {
		return instanceValue == null ? null : instanceValue.toString();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ParameterType createParameterTypeFromString(EDataType eDataType, String initialValue)
    {
		ParameterType result = ParameterType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String convertParameterTypeToString(EDataType eDataType, Object instanceValue)
    {
		return instanceValue == null ? null : instanceValue.toString();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public SybaseasabasesqlmodelPackage getSybaseasabasesqlmodelPackage()
    {
		return (SybaseasabasesqlmodelPackage)getEPackage();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
    public static SybaseasabasesqlmodelPackage getPackage()
    {
		return SybaseasabasesqlmodelPackage.eINSTANCE;
	}

} //SybaseasabasesqlmodelFactoryImpl

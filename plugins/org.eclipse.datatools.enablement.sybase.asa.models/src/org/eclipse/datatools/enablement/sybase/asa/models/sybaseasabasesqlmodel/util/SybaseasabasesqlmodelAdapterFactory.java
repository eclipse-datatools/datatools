/**
 * <copyright>
 * </copyright>
 *
 * $Id: SybaseasabasesqlmodelAdapterFactory.java,v 1.3 2008/03/27 07:35:08 lsong Exp $
 */
package org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.util;

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
 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage
 * @generated
 */
public class SybaseasabasesqlmodelAdapterFactory extends AdapterFactoryImpl 
{
    /**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SybaseasabasesqlmodelPackage modelPackage;

    /**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SybaseasabasesqlmodelAdapterFactory()
    {
		if (modelPackage == null) {
			modelPackage = SybaseasabasesqlmodelPackage.eINSTANCE;
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
	protected SybaseasabasesqlmodelSwitch modelSwitch =
        new SybaseasabasesqlmodelSwitch() {
			public Object caseSybaseASABaseEvent(SybaseASABaseEvent object) {
				return createSybaseASABaseEventAdapter();
			}
			public Object caseSybaseASABaseDatabase(SybaseASABaseDatabase object) {
				return createSybaseASABaseDatabaseAdapter();
			}
			public Object caseSybaseASAWebService(SybaseASAWebService object) {
				return createSybaseASAWebServiceAdapter();
			}
			public Object caseEncryptionInfo(EncryptionInfo object) {
				return createEncryptionInfoAdapter();
			}
			public Object caseSybaseASABaseUserDefinedType(SybaseASABaseUserDefinedType object) {
				return createSybaseASABaseUserDefinedTypeAdapter();
			}
			public Object caseSybaseASABasePredefinedDataType(SybaseASABasePredefinedDataType object) {
				return createSybaseASABasePredefinedDataTypeAdapter();
			}
			public Object caseSybaseASABaseTable(SybaseASABaseTable object) {
				return createSybaseASABaseTableAdapter();
			}
			public Object caseSybaseASABaseColumn(SybaseASABaseColumn object) {
				return createSybaseASABaseColumnAdapter();
			}
			public Object caseSybaseASABaseUniqueConstraint(SybaseASABaseUniqueConstraint object) {
				return createSybaseASABaseUniqueConstraintAdapter();
			}
			public Object caseSybaseASABasePrimaryKey(SybaseASABasePrimaryKey object) {
				return createSybaseASABasePrimaryKeyAdapter();
			}
			public Object caseSybaseASABaseForeignKey(SybaseASABaseForeignKey object) {
				return createSybaseASABaseForeignKeyAdapter();
			}
			public Object caseSybaseASABaseIndex(SybaseASABaseIndex object) {
				return createSybaseASABaseIndexAdapter();
			}
			public Object caseSybaseASABaseDBSpace(SybaseASABaseDBSpace object) {
				return createSybaseASABaseDBSpaceAdapter();
			}
			public Object caseSybaseASABaseViewTable(SybaseASABaseViewTable object) {
				return createSybaseASABaseViewTableAdapter();
			}
			public Object caseSybaseASABaseFunction(SybaseASABaseFunction object) {
				return createSybaseASABaseFunctionAdapter();
			}
			public Object caseSybaseASABaseProcedure(SybaseASABaseProcedure object) {
				return createSybaseASABaseProcedureAdapter();
			}
			public Object caseSybaseASABaseTempTable(SybaseASABaseTempTable object) {
				return createSybaseASABaseTempTableAdapter();
			}
			public Object caseSybaseASABaseTrigger(SybaseASABaseTrigger object) {
				return createSybaseASABaseTriggerAdapter();
			}
			public Object caseSybaseASABaseProxyTable(SybaseASABaseProxyTable object) {
				return createSybaseASABaseProxyTableAdapter();
			}
			public Object caseSybaseASABaseColumnCheckConstraint(SybaseASABaseColumnCheckConstraint object) {
				return createSybaseASABaseColumnCheckConstraintAdapter();
			}
			public Object caseSchedule(Schedule object) {
				return createScheduleAdapter();
			}
			public Object caseSybaseASABaseRemoteProcedure(SybaseASABaseRemoteProcedure object) {
				return createSybaseASABaseRemoteProcedureAdapter();
			}
			public Object caseSybaseASABaseParameter(SybaseASABaseParameter object) {
				return createSybaseASABaseParameterAdapter();
			}
			public Object caseSybaseASABaseGroup(SybaseASABaseGroup object) {
				return createSybaseASABaseGroupAdapter();
			}
			public Object caseSybaseASABaseUser(SybaseASABaseUser object) {
				return createSybaseASABaseUserAdapter();
			}
			public Object caseSybaseASABaseSchema(SybaseASABaseSchema object) {
				return createSybaseASABaseSchemaAdapter();
			}
			public Object caseSybaseASADefaultWrapper(SybaseASADefaultWrapper object) {
				return createSybaseASADefaultWrapperAdapter();
			}
			public Object caseEventCondition(EventCondition object) {
				return createEventConditionAdapter();
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
			public Object caseEvent(Event object) {
				return createEventAdapter();
			}
			public Object caseDatabase(Database object) {
				return createDatabaseAdapter();
			}
			public Object caseDataType(DataType object) {
				return createDataTypeAdapter();
			}
			public Object caseUserDefinedType(UserDefinedType object) {
				return createUserDefinedTypeAdapter();
			}
			public Object caseDistinctUserDefinedType(DistinctUserDefinedType object) {
				return createDistinctUserDefinedTypeAdapter();
			}
			public Object caseDomain(Domain object) {
				return createDomainAdapter();
			}
			public Object caseSQLDataType(SQLDataType object) {
				return createSQLDataTypeAdapter();
			}
			public Object casePredefinedDataType(PredefinedDataType object) {
				return createPredefinedDataTypeAdapter();
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
			public Object caseSybaseAuthorizedObject(SybaseAuthorizedObject object) {
				return createSybaseAuthorizedObjectAdapter();
			}
			public Object caseSybaseBaseTable(SybaseBaseTable object) {
				return createSybaseBaseTableAdapter();
			}
			public Object caseTypedElement(TypedElement object) {
				return createTypedElementAdapter();
			}
			public Object caseColumn(Column object) {
				return createColumnAdapter();
			}
			public Object caseConstraint(Constraint object) {
				return createConstraintAdapter();
			}
			public Object caseTableConstraint(TableConstraint object) {
				return createTableConstraintAdapter();
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
			public Object caseForeignKey(ForeignKey object) {
				return createForeignKeyAdapter();
			}
			public Object caseIndex(Index object) {
				return createIndexAdapter();
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
			public Object caseRoutine(Routine object) {
				return createRoutineAdapter();
			}
			public Object caseFunction(Function object) {
				return createFunctionAdapter();
			}
			public Object caseUserDefinedFunction(UserDefinedFunction object) {
				return createUserDefinedFunctionAdapter();
			}
			public Object caseSybaseRoutine(SybaseRoutine object) {
				return createSybaseRoutineAdapter();
			}
			public Object caseProcedure(Procedure object) {
				return createProcedureAdapter();
			}
			public Object caseTemporaryTable(TemporaryTable object) {
				return createTemporaryTableAdapter();
			}
			public Object caseTrigger(Trigger object) {
				return createTriggerAdapter();
			}
			public Object caseCheckConstraint(CheckConstraint object) {
				return createCheckConstraintAdapter();
			}
			public Object caseParameter(Parameter object) {
				return createParameterAdapter();
			}
			public Object caseSybaseParameter(SybaseParameter object) {
				return createSybaseParameterAdapter();
			}
			public Object caseAuthorizationIdentifier(AuthorizationIdentifier object) {
				return createAuthorizationIdentifierAdapter();
			}
			public Object caseUser(User object) {
				return createUserAdapter();
			}
			public Object caseGroup(Group object) {
				return createGroupAdapter();
			}
			public Object caseSybaseAuthorizationIdentifier(SybaseAuthorizationIdentifier object) {
				return createSybaseAuthorizationIdentifierAdapter();
			}
			public Object caseSchema(Schema object) {
				return createSchemaAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent <em>Sybase ASA Base Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseEvent
	 * @generated
	 */
	public Adapter createSybaseASABaseEventAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase <em>Sybase ASA Base Database</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase
	 * @generated
	 */
	public Adapter createSybaseASABaseDatabaseAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EncryptionInfo <em>Encryption Info</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EncryptionInfo
	 * @generated
	 */
	public Adapter createEncryptionInfoAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUserDefinedType <em>Sybase ASA Base User Defined Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUserDefinedType
	 * @generated
	 */
	public Adapter createSybaseASABaseUserDefinedTypeAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABasePredefinedDataType <em>Sybase ASA Base Predefined Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABasePredefinedDataType
	 * @generated
	 */
	public Adapter createSybaseASABasePredefinedDataTypeAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTable <em>Sybase ASA Base Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTable
	 * @generated
	 */
	public Adapter createSybaseASABaseTableAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn <em>Sybase ASA Base Column</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumn
	 * @generated
	 */
	public Adapter createSybaseASABaseColumnAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUniqueConstraint <em>Sybase ASA Base Unique Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUniqueConstraint
	 * @generated
	 */
	public Adapter createSybaseASABaseUniqueConstraintAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABasePrimaryKey <em>Sybase ASA Base Primary Key</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABasePrimaryKey
	 * @generated
	 */
	public Adapter createSybaseASABasePrimaryKeyAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseForeignKey <em>Sybase ASA Base Foreign Key</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseForeignKey
	 * @generated
	 */
	public Adapter createSybaseASABaseForeignKeyAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseIndex <em>Sybase ASA Base Index</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseIndex
	 * @generated
	 */
	public Adapter createSybaseASABaseIndexAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDBSpace <em>Sybase ASA Base DB Space</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDBSpace
	 * @generated
	 */
	public Adapter createSybaseASABaseDBSpaceAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseViewTable <em>Sybase ASA Base View Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseViewTable
	 * @generated
	 */
	public Adapter createSybaseASABaseViewTableAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseFunction <em>Sybase ASA Base Function</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseFunction
	 * @generated
	 */
	public Adapter createSybaseASABaseFunctionAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseProcedure <em>Sybase ASA Base Procedure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseProcedure
	 * @generated
	 */
	public Adapter createSybaseASABaseProcedureAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTempTable <em>Sybase ASA Base Temp Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTempTable
	 * @generated
	 */
	public Adapter createSybaseASABaseTempTableAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTrigger <em>Sybase ASA Base Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseTrigger
	 * @generated
	 */
	public Adapter createSybaseASABaseTriggerAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseProxyTable <em>Sybase ASA Base Proxy Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseProxyTable
	 * @generated
	 */
	public Adapter createSybaseASABaseProxyTableAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumnCheckConstraint <em>Sybase ASA Base Column Check Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseColumnCheckConstraint
	 * @generated
	 */
	public Adapter createSybaseASABaseColumnCheckConstraintAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService <em>Sybase ASA Web Service</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASAWebService
	 * @generated
	 */
	public Adapter createSybaseASAWebServiceAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule <em>Schedule</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.Schedule
	 * @generated
	 */
	public Adapter createScheduleAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseRemoteProcedure <em>Sybase ASA Base Remote Procedure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseRemoteProcedure
	 * @generated
	 */
	public Adapter createSybaseASABaseRemoteProcedureAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseParameter <em>Sybase ASA Base Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseParameter
	 * @generated
	 */
	public Adapter createSybaseASABaseParameterAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseGroup <em>Sybase ASA Base Group</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseGroup
	 * @generated
	 */
	public Adapter createSybaseASABaseGroupAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseSchema <em>Sybase ASA Base Schema</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseSchema
	 * @generated
	 */
    public Adapter createSybaseASABaseSchemaAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUser <em>Sybase ASA Base User</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseUser
	 * @generated
	 */
    public Adapter createSybaseASABaseUserAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASADefaultWrapper <em>Sybase ASA Default Wrapper</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASADefaultWrapper
	 * @generated
	 */
	public Adapter createSybaseASADefaultWrapperAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventCondition <em>Event Condition</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.EventCondition
	 * @generated
	 */
    public Adapter createEventConditionAdapter()
    {
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
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.schema.Event <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.schema.Event
	 * @generated
	 */
	public Adapter createEventAdapter()
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
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.datatypes.Domain <em>Domain</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.datatypes.Domain
	 * @generated
	 */
	public Adapter createDomainAdapter()
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
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.constraints.ForeignKey <em>Foreign Key</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.constraints.ForeignKey
	 * @generated
	 */
	public Adapter createForeignKeyAdapter()
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
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.routines.Function <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Function
	 * @generated
	 */
	public Adapter createFunctionAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.routines.UserDefinedFunction <em>User Defined Function</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.routines.UserDefinedFunction
	 * @generated
	 */
    public Adapter createUserDefinedFunctionAdapter()
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
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.sql.routines.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.sql.routines.Parameter
	 * @generated
	 */
	public Adapter createParameterAdapter()
    {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter <em>Sybase Parameter</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybaseParameter
	 * @generated
	 */
    public Adapter createSybaseParameterAdapter()
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

} //SybaseasabasesqlmodelAdapterFactory

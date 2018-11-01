/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.modelbase.dbdefinition.util;

import org.eclipse.datatools.modelbase.dbdefinition.*;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;



/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage
 * @generated
 */
public class DatabaseDefinitionAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static DatabaseDefinitionPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DatabaseDefinitionAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = DatabaseDefinitionPackage.eINSTANCE;
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
	public boolean isFactoryForType(Object object) {
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
	protected DatabaseDefinitionSwitch modelSwitch =
		new DatabaseDefinitionSwitch() {
			public Object caseDatabaseVendorDefinition(DatabaseVendorDefinition object) {
				return createDatabaseVendorDefinitionAdapter();
			}
			public Object casePredefinedDataTypeDefinition(PredefinedDataTypeDefinition object) {
				return createPredefinedDataTypeDefinitionAdapter();
			}
			public Object caseTableSpaceDefinition(TableSpaceDefinition object) {
				return createTableSpaceDefinitionAdapter();
			}
			public Object caseStoredProcedureDefinition(StoredProcedureDefinition object) {
				return createStoredProcedureDefinitionAdapter();
			}
			public Object caseTriggerDefinition(TriggerDefinition object) {
				return createTriggerDefinitionAdapter();
			}
			public Object caseColumnDefinition(ColumnDefinition object) {
				return createColumnDefinitionAdapter();
			}
			public Object caseConstraintDefinition(ConstraintDefinition object) {
				return createConstraintDefinitionAdapter();
			}
			public Object caseIndexDefinition(IndexDefinition object) {
				return createIndexDefinitionAdapter();
			}
			public Object caseExtendedDefinition(ExtendedDefinition object) {
				return createExtendedDefinitionAdapter();
			}
			public Object caseTableDefinition(TableDefinition object) {
				return createTableDefinitionAdapter();
			}
			public Object caseSequenceDefinition(SequenceDefinition object) {
				return createSequenceDefinitionAdapter();
			}
			public Object caseUserDefinedTypeDefinition(UserDefinedTypeDefinition object) {
				return createUserDefinedTypeDefinitionAdapter();
			}
			public Object caseQueryDefinition(QueryDefinition object) {
				return createQueryDefinitionAdapter();
			}
			public Object caseSQLSyntaxDefinition(SQLSyntaxDefinition object) {
				return createSQLSyntaxDefinitionAdapter();
			}
			public Object caseNicknameDefinition(NicknameDefinition object) {
				return createNicknameDefinitionAdapter();
			}
			public Object caseSchemaDefinition(SchemaDefinition object) {
				return createSchemaDefinitionAdapter();
			}
			public Object caseViewDefinition(ViewDefinition object) {
				return createViewDefinitionAdapter();
			}
			public Object caseFieldQualifierDefinition(FieldQualifierDefinition object) {
				return createFieldQualifierDefinitionAdapter();
			}
			public Object caseDebuggerDefinition(DebuggerDefinition object) {
				return createDebuggerDefinitionAdapter();
			}
			public Object casePrivilegedElementDefinition(PrivilegedElementDefinition object) {
				return createPrivilegedElementDefinitionAdapter();
			}
			public Object casePrivilegeDefinition(PrivilegeDefinition object) {
				return createPrivilegeDefinitionAdapter();
			}
			public Object caseConstructedDataTypeDefinition(ConstructedDataTypeDefinition object) {
				return createConstructedDataTypeDefinitionAdapter();
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
	public Adapter createAdapter(Notifier target) {
		return (Adapter)modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition <em>Database Vendor Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition
	 * @generated
	 */
	public Adapter createDatabaseVendorDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition <em>Predefined Data Type Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition
	 * @generated
	 */
	public Adapter createPredefinedDataTypeDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition <em>Table Space Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition
	 * @generated
	 */
	public Adapter createTableSpaceDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition <em>Stored Procedure Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition
	 * @generated
	 */
	public Adapter createStoredProcedureDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition <em>Trigger Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition
	 * @generated
	 */
	public Adapter createTriggerDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition <em>Column Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition
	 * @generated
	 */
	public Adapter createColumnDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition <em>Constraint Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition
	 * @generated
	 */
	public Adapter createConstraintDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition <em>Index Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition
	 * @generated
	 */
	public Adapter createIndexDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.dbdefinition.ExtendedDefinition <em>Extended Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ExtendedDefinition
	 * @generated
	 */
	public Adapter createExtendedDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.dbdefinition.TableDefinition <em>Table Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.TableDefinition
	 * @generated
	 */
	public Adapter createTableDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition <em>Sequence Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition
	 * @generated
	 */
	public Adapter createSequenceDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.dbdefinition.UserDefinedTypeDefinition <em>User Defined Type Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.UserDefinedTypeDefinition
	 * @generated
	 */
	public Adapter createUserDefinedTypeDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition <em>Query Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition
	 * @generated
	 */
	public Adapter createQueryDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.dbdefinition.SQLSyntaxDefinition <em>SQL Syntax Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.SQLSyntaxDefinition
	 * @generated
	 */
	public Adapter createSQLSyntaxDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.dbdefinition.NicknameDefinition <em>Nickname Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.NicknameDefinition
	 * @generated
	 */
	public Adapter createNicknameDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.dbdefinition.SchemaDefinition <em>Schema Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.SchemaDefinition
	 * @generated
	 */
	public Adapter createSchemaDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.dbdefinition.ViewDefinition <em>View Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ViewDefinition
	 * @generated
	 */
	public Adapter createViewDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition <em>Field Qualifier Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.FieldQualifierDefinition
	 * @generated
	 */
	public Adapter createFieldQualifierDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.dbdefinition.DebuggerDefinition <em>Debugger Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DebuggerDefinition
	 * @generated
	 */
	public Adapter createDebuggerDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.dbdefinition.PrivilegedElementDefinition <em>Privileged Element Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PrivilegedElementDefinition
	 * @generated
	 */
	public Adapter createPrivilegedElementDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.dbdefinition.PrivilegeDefinition <em>Privilege Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.PrivilegeDefinition
	 * @generated
	 */
	public Adapter createPrivilegeDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.datatools.modelbase.dbdefinition.ConstructedDataTypeDefinition <em>Constructed Data Type Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.ConstructedDataTypeDefinition
	 * @generated
	 */
	public Adapter createConstructedDataTypeDefinitionAdapter() {
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
	public Adapter createEObjectAdapter() {
		return null;
	}

} //DatabaseDefinitionAdapterFactory

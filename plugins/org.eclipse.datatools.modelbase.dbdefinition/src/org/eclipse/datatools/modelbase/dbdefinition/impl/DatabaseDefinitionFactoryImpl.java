/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.modelbase.dbdefinition.impl;

import org.eclipse.datatools.modelbase.dbdefinition.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;



/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DatabaseDefinitionFactoryImpl extends EFactoryImpl implements DatabaseDefinitionFactory {
	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DatabaseDefinitionFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION: return createDatabaseVendorDefinition();
			case DatabaseDefinitionPackage.PREDEFINED_DATA_TYPE_DEFINITION: return createPredefinedDataTypeDefinition();
			case DatabaseDefinitionPackage.TABLE_SPACE_DEFINITION: return createTableSpaceDefinition();
			case DatabaseDefinitionPackage.STORED_PROCEDURE_DEFINITION: return createStoredProcedureDefinition();
			case DatabaseDefinitionPackage.TRIGGER_DEFINITION: return createTriggerDefinition();
			case DatabaseDefinitionPackage.COLUMN_DEFINITION: return createColumnDefinition();
			case DatabaseDefinitionPackage.CONSTRAINT_DEFINITION: return createConstraintDefinition();
			case DatabaseDefinitionPackage.INDEX_DEFINITION: return createIndexDefinition();
			case DatabaseDefinitionPackage.EXTENDED_DEFINITION: return createExtendedDefinition();
			case DatabaseDefinitionPackage.TABLE_DEFINITION: return createTableDefinition();
			case DatabaseDefinitionPackage.SEQUENCE_DEFINITION: return createSequenceDefinition();
			case DatabaseDefinitionPackage.USER_DEFINED_TYPE_DEFINITION: return createUserDefinedTypeDefinition();
			case DatabaseDefinitionPackage.QUERY_DEFINITION: return createQueryDefinition();
			case DatabaseDefinitionPackage.SQL_SYNTAX_DEFINITION: return createSQLSyntaxDefinition();
			case DatabaseDefinitionPackage.NICKNAME_DEFINITION: return createNicknameDefinition();
			case DatabaseDefinitionPackage.SCHEMA_DEFINITION: return createSchemaDefinition();
			case DatabaseDefinitionPackage.VIEW_DEFINITION: return createViewDefinition();
			case DatabaseDefinitionPackage.FIELD_QUALIFIER_DEFINITION: return createFieldQualifierDefinition();
			case DatabaseDefinitionPackage.DEBUGGER_DEFINITION: return createDebuggerDefinition();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case DatabaseDefinitionPackage.CHECK_OPTION: {
				CheckOption result = CheckOption.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return result;
			}
			case DatabaseDefinitionPackage.LANGUAGE_TYPE: {
				LanguageType result = LanguageType.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return result;
			}
			case DatabaseDefinitionPackage.PARAMETER_STYLE: {
				ParameterStyle result = ParameterStyle.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return result;
			}
			case DatabaseDefinitionPackage.PARENT_DELETE_DRI_RULE_TYPE: {
				ParentDeleteDRIRuleType result = ParentDeleteDRIRuleType.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return result;
			}
			case DatabaseDefinitionPackage.PARENT_UPDATE_DRI_RULE_TYPE: {
				ParentUpdateDRIRuleType result = ParentUpdateDRIRuleType.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return result;
			}
			case DatabaseDefinitionPackage.PROCEDURE_TYPE: {
				ProcedureType result = ProcedureType.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return result;
			}
			case DatabaseDefinitionPackage.TABLE_SPACE_TYPE: {
				TableSpaceType result = TableSpaceType.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return result;
			}
			case DatabaseDefinitionPackage.PERCENT_FREE_TERMINOLOGY: {
				PercentFreeTerminology result = PercentFreeTerminology.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return result;
			}
			case DatabaseDefinitionPackage.LENGTH_UNIT: {
				LengthUnit result = LengthUnit.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return result;
			}
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case DatabaseDefinitionPackage.CHECK_OPTION:
				return instanceValue == null ? null : instanceValue.toString();
			case DatabaseDefinitionPackage.LANGUAGE_TYPE:
				return instanceValue == null ? null : instanceValue.toString();
			case DatabaseDefinitionPackage.PARAMETER_STYLE:
				return instanceValue == null ? null : instanceValue.toString();
			case DatabaseDefinitionPackage.PARENT_DELETE_DRI_RULE_TYPE:
				return instanceValue == null ? null : instanceValue.toString();
			case DatabaseDefinitionPackage.PARENT_UPDATE_DRI_RULE_TYPE:
				return instanceValue == null ? null : instanceValue.toString();
			case DatabaseDefinitionPackage.PROCEDURE_TYPE:
				return instanceValue == null ? null : instanceValue.toString();
			case DatabaseDefinitionPackage.TABLE_SPACE_TYPE:
				return instanceValue == null ? null : instanceValue.toString();
			case DatabaseDefinitionPackage.PERCENT_FREE_TERMINOLOGY:
				return instanceValue == null ? null : instanceValue.toString();
			case DatabaseDefinitionPackage.LENGTH_UNIT:
				return instanceValue == null ? null : instanceValue.toString();
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DatabaseVendorDefinition createDatabaseVendorDefinition() {
		DatabaseVendorDefinitionImpl databaseVendorDefinition = new DatabaseVendorDefinitionImpl();
		return databaseVendorDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PredefinedDataTypeDefinition createPredefinedDataTypeDefinition() {
		PredefinedDataTypeDefinitionImpl predefinedDataTypeDefinition = new PredefinedDataTypeDefinitionImpl();
		return predefinedDataTypeDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TableSpaceDefinition createTableSpaceDefinition() {
		TableSpaceDefinitionImpl tableSpaceDefinition = new TableSpaceDefinitionImpl();
		return tableSpaceDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StoredProcedureDefinition createStoredProcedureDefinition() {
		StoredProcedureDefinitionImpl storedProcedureDefinition = new StoredProcedureDefinitionImpl();
		return storedProcedureDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TriggerDefinition createTriggerDefinition() {
		TriggerDefinitionImpl triggerDefinition = new TriggerDefinitionImpl();
		return triggerDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ColumnDefinition createColumnDefinition() {
		ColumnDefinitionImpl columnDefinition = new ColumnDefinitionImpl();
		return columnDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstraintDefinition createConstraintDefinition() {
		ConstraintDefinitionImpl constraintDefinition = new ConstraintDefinitionImpl();
		return constraintDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IndexDefinition createIndexDefinition() {
		IndexDefinitionImpl indexDefinition = new IndexDefinitionImpl();
		return indexDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExtendedDefinition createExtendedDefinition() {
		ExtendedDefinitionImpl extendedDefinition = new ExtendedDefinitionImpl();
		return extendedDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TableDefinition createTableDefinition() {
		TableDefinitionImpl tableDefinition = new TableDefinitionImpl();
		return tableDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SequenceDefinition createSequenceDefinition() {
		SequenceDefinitionImpl sequenceDefinition = new SequenceDefinitionImpl();
		return sequenceDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserDefinedTypeDefinition createUserDefinedTypeDefinition() {
		UserDefinedTypeDefinitionImpl userDefinedTypeDefinition = new UserDefinedTypeDefinitionImpl();
		return userDefinedTypeDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QueryDefinition createQueryDefinition() {
		QueryDefinitionImpl queryDefinition = new QueryDefinitionImpl();
		return queryDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SQLSyntaxDefinition createSQLSyntaxDefinition() {
		SQLSyntaxDefinitionImpl sqlSyntaxDefinition = new SQLSyntaxDefinitionImpl();
		return sqlSyntaxDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NicknameDefinition createNicknameDefinition() {
		NicknameDefinitionImpl nicknameDefinition = new NicknameDefinitionImpl();
		return nicknameDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SchemaDefinition createSchemaDefinition() {
		SchemaDefinitionImpl schemaDefinition = new SchemaDefinitionImpl();
		return schemaDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViewDefinition createViewDefinition() {
		ViewDefinitionImpl viewDefinition = new ViewDefinitionImpl();
		return viewDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FieldQualifierDefinition createFieldQualifierDefinition() {
		FieldQualifierDefinitionImpl fieldQualifierDefinition = new FieldQualifierDefinitionImpl();
		return fieldQualifierDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DebuggerDefinition createDebuggerDefinition() {
		DebuggerDefinitionImpl debuggerDefinition = new DebuggerDefinitionImpl();
		return debuggerDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DatabaseDefinitionPackage getDatabaseDefinitionPackage() {
		return (DatabaseDefinitionPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static DatabaseDefinitionPackage getPackage() {
		return DatabaseDefinitionPackage.eINSTANCE;
	}

} //DatabaseDefinitionFactoryImpl

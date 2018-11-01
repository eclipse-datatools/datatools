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
package org.eclipse.datatools.modelbase.dbdefinition.impl;

import org.eclipse.datatools.modelbase.dbdefinition.*;
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
public class DatabaseDefinitionFactoryImpl extends EFactoryImpl implements DatabaseDefinitionFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DatabaseDefinitionFactory init() {
		try {
			DatabaseDefinitionFactory theDatabaseDefinitionFactory = (DatabaseDefinitionFactory)EPackage.Registry.INSTANCE.getEFactory("http:///org/eclipse/datatools/modelbase/dbdefinition/dbdefinition.ecore"); //$NON-NLS-1$ 
			if (theDatabaseDefinitionFactory != null) {
				return theDatabaseDefinitionFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new DatabaseDefinitionFactoryImpl();
	}

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
			case DatabaseDefinitionPackage.PRIVILEGED_ELEMENT_DEFINITION: return createPrivilegedElementDefinition();
			case DatabaseDefinitionPackage.PRIVILEGE_DEFINITION: return createPrivilegeDefinition();
			case DatabaseDefinitionPackage.CONSTRUCTED_DATA_TYPE_DEFINITION: return createConstructedDataTypeDefinition();
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
			case DatabaseDefinitionPackage.CHECK_OPTION:
				return createCheckOptionFromString(eDataType, initialValue);
			case DatabaseDefinitionPackage.LANGUAGE_TYPE:
				return createLanguageTypeFromString(eDataType, initialValue);
			case DatabaseDefinitionPackage.PARAMETER_STYLE:
				return createParameterStyleFromString(eDataType, initialValue);
			case DatabaseDefinitionPackage.PARENT_DELETE_DRI_RULE_TYPE:
				return createParentDeleteDRIRuleTypeFromString(eDataType, initialValue);
			case DatabaseDefinitionPackage.PARENT_UPDATE_DRI_RULE_TYPE:
				return createParentUpdateDRIRuleTypeFromString(eDataType, initialValue);
			case DatabaseDefinitionPackage.PROCEDURE_TYPE:
				return createProcedureTypeFromString(eDataType, initialValue);
			case DatabaseDefinitionPackage.TABLE_SPACE_TYPE:
				return createTableSpaceTypeFromString(eDataType, initialValue);
			case DatabaseDefinitionPackage.PERCENT_FREE_TERMINOLOGY:
				return createPercentFreeTerminologyFromString(eDataType, initialValue);
			case DatabaseDefinitionPackage.LENGTH_UNIT:
				return createLengthUnitFromString(eDataType, initialValue);
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
				return convertCheckOptionToString(eDataType, instanceValue);
			case DatabaseDefinitionPackage.LANGUAGE_TYPE:
				return convertLanguageTypeToString(eDataType, instanceValue);
			case DatabaseDefinitionPackage.PARAMETER_STYLE:
				return convertParameterStyleToString(eDataType, instanceValue);
			case DatabaseDefinitionPackage.PARENT_DELETE_DRI_RULE_TYPE:
				return convertParentDeleteDRIRuleTypeToString(eDataType, instanceValue);
			case DatabaseDefinitionPackage.PARENT_UPDATE_DRI_RULE_TYPE:
				return convertParentUpdateDRIRuleTypeToString(eDataType, instanceValue);
			case DatabaseDefinitionPackage.PROCEDURE_TYPE:
				return convertProcedureTypeToString(eDataType, instanceValue);
			case DatabaseDefinitionPackage.TABLE_SPACE_TYPE:
				return convertTableSpaceTypeToString(eDataType, instanceValue);
			case DatabaseDefinitionPackage.PERCENT_FREE_TERMINOLOGY:
				return convertPercentFreeTerminologyToString(eDataType, instanceValue);
			case DatabaseDefinitionPackage.LENGTH_UNIT:
				return convertLengthUnitToString(eDataType, instanceValue);
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
	public PrivilegedElementDefinition createPrivilegedElementDefinition() {
		PrivilegedElementDefinitionImpl privilegedElementDefinition = new PrivilegedElementDefinitionImpl();
		return privilegedElementDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrivilegeDefinition createPrivilegeDefinition() {
		PrivilegeDefinitionImpl privilegeDefinition = new PrivilegeDefinitionImpl();
		return privilegeDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstructedDataTypeDefinition createConstructedDataTypeDefinition() {
		ConstructedDataTypeDefinitionImpl constructedDataTypeDefinition = new ConstructedDataTypeDefinitionImpl();
		return constructedDataTypeDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CheckOption createCheckOptionFromString(EDataType eDataType, String initialValue) {
		CheckOption result = CheckOption.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCheckOptionToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LanguageType createLanguageTypeFromString(EDataType eDataType, String initialValue) {
		LanguageType result = LanguageType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLanguageTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterStyle createParameterStyleFromString(EDataType eDataType, String initialValue) {
		ParameterStyle result = ParameterStyle.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertParameterStyleToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParentDeleteDRIRuleType createParentDeleteDRIRuleTypeFromString(EDataType eDataType, String initialValue) {
		ParentDeleteDRIRuleType result = ParentDeleteDRIRuleType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertParentDeleteDRIRuleTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParentUpdateDRIRuleType createParentUpdateDRIRuleTypeFromString(EDataType eDataType, String initialValue) {
		ParentUpdateDRIRuleType result = ParentUpdateDRIRuleType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertParentUpdateDRIRuleTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcedureType createProcedureTypeFromString(EDataType eDataType, String initialValue) {
		ProcedureType result = ProcedureType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertProcedureTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TableSpaceType createTableSpaceTypeFromString(EDataType eDataType, String initialValue) {
		TableSpaceType result = TableSpaceType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTableSpaceTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PercentFreeTerminology createPercentFreeTerminologyFromString(EDataType eDataType, String initialValue) {
		PercentFreeTerminology result = PercentFreeTerminology.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPercentFreeTerminologyToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LengthUnit createLengthUnitFromString(EDataType eDataType, String initialValue) {
		LengthUnit result = LengthUnit.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLengthUnitToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
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

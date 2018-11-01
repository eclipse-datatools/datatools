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
package org.eclipse.datatools.modelbase.dbdefinition;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Database Vendor Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getPredefinedDataTypeDefinitions <em>Predefined Data Type Definitions</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getTableSpaceDefinition <em>Table Space Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getStoredProcedureDefinition <em>Stored Procedure Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getTriggerDefinition <em>Trigger Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getColumnDefinition <em>Column Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getConstraintDefinition <em>Constraint Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getExtendedDefinitions <em>Extended Definitions</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getIndexDefinition <em>Index Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getTableDefinition <em>Table Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getSequenceDefinition <em>Sequence Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getUdtDefinition <em>Udt Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getQueryDefinition <em>Query Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getSQLSyntaxDefinition <em>SQL Syntax Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getNicknameDefinition <em>Nickname Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getSchemaDefinition <em>Schema Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getViewDefinition <em>View Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getDebuggerDefinition <em>Debugger Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getPrivilegedElementDefinitions <em>Privileged Element Definitions</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getConstructedDataTypeDefinition <em>Constructed Data Type Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getVendor <em>Vendor</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isConstraintsSupported <em>Constraints Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isTriggerSupported <em>Trigger Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isSnapshotViewSupported <em>Snapshot View Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isJoinSupported <em>Join Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isViewTriggerSupported <em>View Trigger Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isTablespacesSupported <em>Tablespaces Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getMaximumCommentLength <em>Maximum Comment Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isSequenceSupported <em>Sequence Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isMQTSupported <em>MQT Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isSchemaSupported <em>Schema Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isAliasSupported <em>Alias Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isSynonymSupported <em>Synonym Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isUserDefinedTypeSupported <em>User Defined Type Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isDomainSupported <em>Domain Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isSQLStatementSupported <em>SQL Statement Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isNicknameSupported <em>Nickname Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isQuotedDMLSupported <em>Quoted DML Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isQuotedDDLSupported <em>Quoted DDL Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isXmlSupported <em>Xml Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isMQTIndexSupported <em>MQT Index Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isEventSupported <em>Event Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isSqlUDFSupported <em>Sql UDF Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isStoredProcedureSupported <em>Stored Procedure Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isPackageSupported <em>Package Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isAuthorizationIdentifierSupported <em>Authorization Identifier Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isRoleSupported <em>Role Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isGroupSupported <em>Group Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isUserSupported <em>User Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isRoleAuthorizationSupported <em>Role Authorization Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isConstructedDataTypeSupported <em>Constructed Data Type Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isUDFSupported <em>UDF Supported</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition()
 * @model
 * @generated
 */
public interface DatabaseVendorDefinition extends EObject {
	/**
	 * Returns the value of the '<em><b>Vendor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Vendor</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vendor</em>' attribute.
	 * @see #setVendor(String)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_Vendor()
	 * @model
	 * @generated
	 */
	String getVendor();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getVendor <em>Vendor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Vendor</em>' attribute.
	 * @see #getVendor()
	 * @generated
	 */
	void setVendor(String value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_Version()
	 * @model
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

	/**
	 * Returns the value of the '<em><b>Constraints Supported</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constraints Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constraints Supported</em>' attribute.
	 * @see #setConstraintsSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_ConstraintsSupported()
	 * @model default="true"
	 * @generated
	 */
	boolean isConstraintsSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isConstraintsSupported <em>Constraints Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constraints Supported</em>' attribute.
	 * @see #isConstraintsSupported()
	 * @generated
	 */
	void setConstraintsSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Maximum Identifier Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maximum Identifier Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Maximum Identifier Length</em>' attribute.
	 * @see #setMaximumIdentifierLength(int)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_MaximumIdentifierLength()
	 * @model
	 * @generated
	 */
	int getMaximumIdentifierLength();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum Identifier Length</em>' attribute.
	 * @see #getMaximumIdentifierLength()
	 * @generated
	 */
	void setMaximumIdentifierLength(int value);

	/**
	 * Returns the value of the '<em><b>Trigger Supported</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trigger Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trigger Supported</em>' attribute.
	 * @see #setTriggerSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_TriggerSupported()
	 * @model default="true"
	 * @generated
	 */
	boolean isTriggerSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isTriggerSupported <em>Trigger Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trigger Supported</em>' attribute.
	 * @see #isTriggerSupported()
	 * @generated
	 */
	void setTriggerSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Snapshot View Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Snapshot View Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Snapshot View Supported</em>' attribute.
	 * @see #setSnapshotViewSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_SnapshotViewSupported()
	 * @model
	 * @generated
	 */
	boolean isSnapshotViewSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isSnapshotViewSupported <em>Snapshot View Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Snapshot View Supported</em>' attribute.
	 * @see #isSnapshotViewSupported()
	 * @generated
	 */
	void setSnapshotViewSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Join Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Join Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Join Supported</em>' attribute.
	 * @see #setJoinSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_JoinSupported()
	 * @model
	 * @generated
	 */
	boolean isJoinSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isJoinSupported <em>Join Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Join Supported</em>' attribute.
	 * @see #isJoinSupported()
	 * @generated
	 */
	void setJoinSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>View Trigger Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>View Trigger Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>View Trigger Supported</em>' attribute.
	 * @see #setViewTriggerSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_ViewTriggerSupported()
	 * @model
	 * @generated
	 */
	boolean isViewTriggerSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isViewTriggerSupported <em>View Trigger Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>View Trigger Supported</em>' attribute.
	 * @see #isViewTriggerSupported()
	 * @generated
	 */
	void setViewTriggerSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Tablespaces Supported</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tablespaces Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tablespaces Supported</em>' attribute.
	 * @see #setTablespacesSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_TablespacesSupported()
	 * @model default="true"
	 * @generated
	 */
	boolean isTablespacesSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isTablespacesSupported <em>Tablespaces Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tablespaces Supported</em>' attribute.
	 * @see #isTablespacesSupported()
	 * @generated
	 */
	void setTablespacesSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Maximum Comment Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maximum Comment Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Maximum Comment Length</em>' attribute.
	 * @see #setMaximumCommentLength(int)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_MaximumCommentLength()
	 * @model
	 * @generated
	 */
	int getMaximumCommentLength();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getMaximumCommentLength <em>Maximum Comment Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Maximum Comment Length</em>' attribute.
	 * @see #getMaximumCommentLength()
	 * @generated
	 */
	void setMaximumCommentLength(int value);

	/**
	 * Returns the value of the '<em><b>Sequence Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sequence Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sequence Supported</em>' attribute.
	 * @see #setSequenceSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_SequenceSupported()
	 * @model
	 * @generated
	 */
	boolean isSequenceSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isSequenceSupported <em>Sequence Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sequence Supported</em>' attribute.
	 * @see #isSequenceSupported()
	 * @generated
	 */
	void setSequenceSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>MQT Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>MQT Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>MQT Supported</em>' attribute.
	 * @see #setMQTSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_MQTSupported()
	 * @model
	 * @generated
	 */
	boolean isMQTSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isMQTSupported <em>MQT Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>MQT Supported</em>' attribute.
	 * @see #isMQTSupported()
	 * @generated
	 */
	void setMQTSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Schema Supported</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Schema Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Schema Supported</em>' attribute.
	 * @see #setSchemaSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_SchemaSupported()
	 * @model default="true"
	 * @generated
	 */
	boolean isSchemaSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isSchemaSupported <em>Schema Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Schema Supported</em>' attribute.
	 * @see #isSchemaSupported()
	 * @generated
	 */
	void setSchemaSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Alias Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Alias Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Alias Supported</em>' attribute.
	 * @see #setAliasSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_AliasSupported()
	 * @model
	 * @generated
	 */
	boolean isAliasSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isAliasSupported <em>Alias Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Alias Supported</em>' attribute.
	 * @see #isAliasSupported()
	 * @generated
	 */
	void setAliasSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Synonym Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Synonym Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Synonym Supported</em>' attribute.
	 * @see #setSynonymSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_SynonymSupported()
	 * @model
	 * @generated
	 */
	boolean isSynonymSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isSynonymSupported <em>Synonym Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Synonym Supported</em>' attribute.
	 * @see #isSynonymSupported()
	 * @generated
	 */
	void setSynonymSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>User Defined Type Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User Defined Type Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User Defined Type Supported</em>' attribute.
	 * @see #setUserDefinedTypeSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_UserDefinedTypeSupported()
	 * @model
	 * @generated
	 */
	boolean isUserDefinedTypeSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isUserDefinedTypeSupported <em>User Defined Type Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User Defined Type Supported</em>' attribute.
	 * @see #isUserDefinedTypeSupported()
	 * @generated
	 */
	void setUserDefinedTypeSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Domain Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Domain Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Domain Supported</em>' attribute.
	 * @see #setDomainSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_DomainSupported()
	 * @model
	 * @generated
	 */
	boolean isDomainSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isDomainSupported <em>Domain Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Domain Supported</em>' attribute.
	 * @see #isDomainSupported()
	 * @generated
	 */
	void setDomainSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>SQL Statement Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>SQL Statement Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>SQL Statement Supported</em>' attribute.
	 * @see #setSQLStatementSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_SQLStatementSupported()
	 * @model
	 * @generated
	 */
	boolean isSQLStatementSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isSQLStatementSupported <em>SQL Statement Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>SQL Statement Supported</em>' attribute.
	 * @see #isSQLStatementSupported()
	 * @generated
	 */
	void setSQLStatementSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Nickname Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nickname Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nickname Supported</em>' attribute.
	 * @see #setNicknameSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_NicknameSupported()
	 * @model
	 * @generated
	 */
	boolean isNicknameSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isNicknameSupported <em>Nickname Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nickname Supported</em>' attribute.
	 * @see #isNicknameSupported()
	 * @generated
	 */
	void setNicknameSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Quoted DML Supported</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Quoted DML Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Quoted DML Supported</em>' attribute.
	 * @see #setQuotedDMLSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_QuotedDMLSupported()
	 * @model default="true"
	 * @generated
	 */
	boolean isQuotedDMLSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isQuotedDMLSupported <em>Quoted DML Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quoted DML Supported</em>' attribute.
	 * @see #isQuotedDMLSupported()
	 * @generated
	 */
	void setQuotedDMLSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Quoted DDL Supported</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Quoted DDL Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Quoted DDL Supported</em>' attribute.
	 * @see #setQuotedDDLSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_QuotedDDLSupported()
	 * @model default="true"
	 * @generated
	 */
	boolean isQuotedDDLSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isQuotedDDLSupported <em>Quoted DDL Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quoted DDL Supported</em>' attribute.
	 * @see #isQuotedDDLSupported()
	 * @generated
	 */
	void setQuotedDDLSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Xml Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Xml Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Xml Supported</em>' attribute.
	 * @see #setXmlSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_XmlSupported()
	 * @model
	 * @generated
	 */
	boolean isXmlSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isXmlSupported <em>Xml Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Xml Supported</em>' attribute.
	 * @see #isXmlSupported()
	 * @generated
	 */
	void setXmlSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>MQT Index Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>MQT Index Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>MQT Index Supported</em>' attribute.
	 * @see #setMQTIndexSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_MQTIndexSupported()
	 * @model
	 * @generated
	 */
	boolean isMQTIndexSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isMQTIndexSupported <em>MQT Index Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>MQT Index Supported</em>' attribute.
	 * @see #isMQTIndexSupported()
	 * @generated
	 */
	void setMQTIndexSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Event Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event Supported</em>' attribute.
	 * @see #setEventSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_EventSupported()
	 * @model
	 * @generated
	 */
	boolean isEventSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isEventSupported <em>Event Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event Supported</em>' attribute.
	 * @see #isEventSupported()
	 * @generated
	 */
	void setEventSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Sql UDF Supported</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sql UDF Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sql UDF Supported</em>' attribute.
	 * @see #setSqlUDFSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_SqlUDFSupported()
	 * @model default="true"
	 * @generated
	 */
	boolean isSqlUDFSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isSqlUDFSupported <em>Sql UDF Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sql UDF Supported</em>' attribute.
	 * @see #isSqlUDFSupported()
	 * @generated
	 */
	void setSqlUDFSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Stored Procedure Supported</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stored Procedure Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stored Procedure Supported</em>' attribute.
	 * @see #setStoredProcedureSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_StoredProcedureSupported()
	 * @model default="true"
	 * @generated
	 */
	boolean isStoredProcedureSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isStoredProcedureSupported <em>Stored Procedure Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stored Procedure Supported</em>' attribute.
	 * @see #isStoredProcedureSupported()
	 * @generated
	 */
	void setStoredProcedureSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Package Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Package Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Package Supported</em>' attribute.
	 * @see #setPackageSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_PackageSupported()
	 * @model
	 * @generated
	 */
	boolean isPackageSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isPackageSupported <em>Package Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package Supported</em>' attribute.
	 * @see #isPackageSupported()
	 * @generated
	 */
	void setPackageSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Authorization Identifier Supported</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Authorization Identifier Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Authorization Identifier Supported</em>' attribute.
	 * @see #setAuthorizationIdentifierSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_AuthorizationIdentifierSupported()
	 * @model default="true"
	 * @generated
	 */
	boolean isAuthorizationIdentifierSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isAuthorizationIdentifierSupported <em>Authorization Identifier Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Authorization Identifier Supported</em>' attribute.
	 * @see #isAuthorizationIdentifierSupported()
	 * @generated
	 */
	void setAuthorizationIdentifierSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Role Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Role Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Role Supported</em>' attribute.
	 * @see #setRoleSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_RoleSupported()
	 * @model
	 * @generated
	 */
	boolean isRoleSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isRoleSupported <em>Role Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Role Supported</em>' attribute.
	 * @see #isRoleSupported()
	 * @generated
	 */
	void setRoleSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Group Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group Supported</em>' attribute.
	 * @see #setGroupSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_GroupSupported()
	 * @model
	 * @generated
	 */
	boolean isGroupSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isGroupSupported <em>Group Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Group Supported</em>' attribute.
	 * @see #isGroupSupported()
	 * @generated
	 */
	void setGroupSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>User Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User Supported</em>' attribute.
	 * @see #setUserSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_UserSupported()
	 * @model
	 * @generated
	 */
	boolean isUserSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isUserSupported <em>User Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User Supported</em>' attribute.
	 * @see #isUserSupported()
	 * @generated
	 */
	void setUserSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Role Authorization Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Role Authorization Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Role Authorization Supported</em>' attribute.
	 * @see #setRoleAuthorizationSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_RoleAuthorizationSupported()
	 * @model
	 * @generated
	 */
	boolean isRoleAuthorizationSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isRoleAuthorizationSupported <em>Role Authorization Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Role Authorization Supported</em>' attribute.
	 * @see #isRoleAuthorizationSupported()
	 * @generated
	 */
	void setRoleAuthorizationSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Constructed Data Type Supported</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constructed Data Type Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constructed Data Type Supported</em>' attribute.
	 * @see #setConstructedDataTypeSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_ConstructedDataTypeSupported()
	 * @model
	 * @generated
	 */
	boolean isConstructedDataTypeSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isConstructedDataTypeSupported <em>Constructed Data Type Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constructed Data Type Supported</em>' attribute.
	 * @see #isConstructedDataTypeSupported()
	 * @generated
	 */
	void setConstructedDataTypeSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>UDF Supported</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>UDF Supported</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>UDF Supported</em>' attribute.
	 * @see #setUDFSupported(boolean)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_UDFSupported()
	 * @model default="true"
	 * @generated
	 */
	boolean isUDFSupported();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#isUDFSupported <em>UDF Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>UDF Supported</em>' attribute.
	 * @see #isUDFSupported()
	 * @generated
	 */
	void setUDFSupported(boolean value);

	/**
	 * Returns the value of the '<em><b>Predefined Data Type Definitions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Predefined Data Type Definitions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Predefined Data Type Definitions</em>' containment reference list.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_PredefinedDataTypeDefinitions()
	 * @model type="org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition" containment="true"
	 * @generated
	 */
	EList getPredefinedDataTypeDefinitions();

	/**
	 * Returns the value of the '<em><b>Table Space Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Table Space Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Table Space Definition</em>' containment reference.
	 * @see #setTableSpaceDefinition(TableSpaceDefinition)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_TableSpaceDefinition()
	 * @model containment="true" required="true"
	 * @generated
	 */
	TableSpaceDefinition getTableSpaceDefinition();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getTableSpaceDefinition <em>Table Space Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Table Space Definition</em>' containment reference.
	 * @see #getTableSpaceDefinition()
	 * @generated
	 */
	void setTableSpaceDefinition(TableSpaceDefinition value);

	/**
	 * Returns the value of the '<em><b>Stored Procedure Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stored Procedure Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stored Procedure Definition</em>' containment reference.
	 * @see #setStoredProcedureDefinition(StoredProcedureDefinition)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_StoredProcedureDefinition()
	 * @model containment="true" required="true"
	 * @generated
	 */
	StoredProcedureDefinition getStoredProcedureDefinition();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getStoredProcedureDefinition <em>Stored Procedure Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stored Procedure Definition</em>' containment reference.
	 * @see #getStoredProcedureDefinition()
	 * @generated
	 */
	void setStoredProcedureDefinition(StoredProcedureDefinition value);

	/**
	 * Returns the value of the '<em><b>Trigger Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trigger Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trigger Definition</em>' containment reference.
	 * @see #setTriggerDefinition(TriggerDefinition)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_TriggerDefinition()
	 * @model containment="true" required="true"
	 * @generated
	 */
	TriggerDefinition getTriggerDefinition();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getTriggerDefinition <em>Trigger Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trigger Definition</em>' containment reference.
	 * @see #getTriggerDefinition()
	 * @generated
	 */
	void setTriggerDefinition(TriggerDefinition value);

	/**
	 * Returns the value of the '<em><b>Column Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Column Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Column Definition</em>' containment reference.
	 * @see #setColumnDefinition(ColumnDefinition)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_ColumnDefinition()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ColumnDefinition getColumnDefinition();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getColumnDefinition <em>Column Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Column Definition</em>' containment reference.
	 * @see #getColumnDefinition()
	 * @generated
	 */
	void setColumnDefinition(ColumnDefinition value);

	/**
	 * Returns the value of the '<em><b>Constraint Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constraint Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constraint Definition</em>' containment reference.
	 * @see #setConstraintDefinition(ConstraintDefinition)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_ConstraintDefinition()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ConstraintDefinition getConstraintDefinition();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getConstraintDefinition <em>Constraint Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constraint Definition</em>' containment reference.
	 * @see #getConstraintDefinition()
	 * @generated
	 */
	void setConstraintDefinition(ConstraintDefinition value);

	/**
	 * Returns the value of the '<em><b>Extended Definitions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.dbdefinition.ExtendedDefinition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extended Definitions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extended Definitions</em>' containment reference list.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_ExtendedDefinitions()
	 * @model type="org.eclipse.datatools.modelbase.dbdefinition.ExtendedDefinition" containment="true"
	 * @generated
	 */
	EList getExtendedDefinitions();

	/**
	 * Returns the value of the '<em><b>Index Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Index Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Index Definition</em>' containment reference.
	 * @see #setIndexDefinition(IndexDefinition)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_IndexDefinition()
	 * @model containment="true" required="true"
	 * @generated
	 */
	IndexDefinition getIndexDefinition();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getIndexDefinition <em>Index Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Index Definition</em>' containment reference.
	 * @see #getIndexDefinition()
	 * @generated
	 */
	void setIndexDefinition(IndexDefinition value);

	/**
	 * Returns the value of the '<em><b>Table Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Table Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Table Definition</em>' containment reference.
	 * @see #setTableDefinition(TableDefinition)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_TableDefinition()
	 * @model containment="true" required="true"
	 * @generated
	 */
	TableDefinition getTableDefinition();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getTableDefinition <em>Table Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Table Definition</em>' containment reference.
	 * @see #getTableDefinition()
	 * @generated
	 */
	void setTableDefinition(TableDefinition value);

	/**
	 * Returns the value of the '<em><b>Sequence Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sequence Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sequence Definition</em>' containment reference.
	 * @see #setSequenceDefinition(SequenceDefinition)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_SequenceDefinition()
	 * @model containment="true" required="true"
	 * @generated
	 */
	SequenceDefinition getSequenceDefinition();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getSequenceDefinition <em>Sequence Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sequence Definition</em>' containment reference.
	 * @see #getSequenceDefinition()
	 * @generated
	 */
	void setSequenceDefinition(SequenceDefinition value);

	/**
	 * Returns the value of the '<em><b>Udt Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Udt Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Udt Definition</em>' containment reference.
	 * @see #setUdtDefinition(UserDefinedTypeDefinition)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_UdtDefinition()
	 * @model containment="true" required="true"
	 * @generated
	 */
	UserDefinedTypeDefinition getUdtDefinition();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getUdtDefinition <em>Udt Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Udt Definition</em>' containment reference.
	 * @see #getUdtDefinition()
	 * @generated
	 */
	void setUdtDefinition(UserDefinedTypeDefinition value);

	/**
	 * Returns the value of the '<em><b>Query Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Query Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Query Definition</em>' containment reference.
	 * @see #setQueryDefinition(QueryDefinition)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_QueryDefinition()
	 * @model containment="true" required="true"
	 * @generated
	 */
	QueryDefinition getQueryDefinition();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getQueryDefinition <em>Query Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Query Definition</em>' containment reference.
	 * @see #getQueryDefinition()
	 * @generated
	 */
	void setQueryDefinition(QueryDefinition value);

	/**
	 * Returns the value of the '<em><b>SQL Syntax Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>SQL Syntax Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>SQL Syntax Definition</em>' containment reference.
	 * @see #setSQLSyntaxDefinition(SQLSyntaxDefinition)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_SQLSyntaxDefinition()
	 * @model containment="true" required="true"
	 * @generated
	 */
	SQLSyntaxDefinition getSQLSyntaxDefinition();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getSQLSyntaxDefinition <em>SQL Syntax Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>SQL Syntax Definition</em>' containment reference.
	 * @see #getSQLSyntaxDefinition()
	 * @generated
	 */
	void setSQLSyntaxDefinition(SQLSyntaxDefinition value);

	/**
	 * Returns the value of the '<em><b>Nickname Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nickname Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nickname Definition</em>' containment reference.
	 * @see #setNicknameDefinition(NicknameDefinition)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_NicknameDefinition()
	 * @model containment="true" required="true"
	 * @generated
	 */
	NicknameDefinition getNicknameDefinition();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getNicknameDefinition <em>Nickname Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nickname Definition</em>' containment reference.
	 * @see #getNicknameDefinition()
	 * @generated
	 */
	void setNicknameDefinition(NicknameDefinition value);

	/**
	 * Returns the value of the '<em><b>Schema Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Schema Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Schema Definition</em>' containment reference.
	 * @see #setSchemaDefinition(SchemaDefinition)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_SchemaDefinition()
	 * @model containment="true" required="true"
	 * @generated
	 */
	SchemaDefinition getSchemaDefinition();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getSchemaDefinition <em>Schema Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Schema Definition</em>' containment reference.
	 * @see #getSchemaDefinition()
	 * @generated
	 */
	void setSchemaDefinition(SchemaDefinition value);

	/**
	 * Returns the value of the '<em><b>View Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>View Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>View Definition</em>' containment reference.
	 * @see #setViewDefinition(ViewDefinition)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_ViewDefinition()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ViewDefinition getViewDefinition();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getViewDefinition <em>View Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>View Definition</em>' containment reference.
	 * @see #getViewDefinition()
	 * @generated
	 */
	void setViewDefinition(ViewDefinition value);

	/**
	 * Returns the value of the '<em><b>Debugger Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Debugger Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Debugger Definition</em>' containment reference.
	 * @see #setDebuggerDefinition(DebuggerDefinition)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_DebuggerDefinition()
	 * @model containment="true" required="true"
	 * @generated
	 */
	DebuggerDefinition getDebuggerDefinition();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getDebuggerDefinition <em>Debugger Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Debugger Definition</em>' containment reference.
	 * @see #getDebuggerDefinition()
	 * @generated
	 */
	void setDebuggerDefinition(DebuggerDefinition value);

	/**
	 * Returns the value of the '<em><b>Privileged Element Definitions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.datatools.modelbase.dbdefinition.PrivilegedElementDefinition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Privileged Element Definitions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Privileged Element Definitions</em>' containment reference list.
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_PrivilegedElementDefinitions()
	 * @model type="org.eclipse.datatools.modelbase.dbdefinition.PrivilegedElementDefinition" containment="true"
	 * @generated
	 */
	EList getPrivilegedElementDefinitions();

	/**
	 * Returns the value of the '<em><b>Constructed Data Type Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constructed Data Type Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constructed Data Type Definition</em>' containment reference.
	 * @see #setConstructedDataTypeDefinition(ConstructedDataTypeDefinition)
	 * @see org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage#getDatabaseVendorDefinition_ConstructedDataTypeDefinition()
	 * @model containment="true" required="true"
	 * @generated
	 */
	ConstructedDataTypeDefinition getConstructedDataTypeDefinition();

	/**
	 * Sets the value of the '{@link org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition#getConstructedDataTypeDefinition <em>Constructed Data Type Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constructed Data Type Definition</em>' containment reference.
	 * @see #getConstructedDataTypeDefinition()
	 * @generated
	 */
	void setConstructedDataTypeDefinition(ConstructedDataTypeDefinition value);

} // DatabaseVendorDefinition

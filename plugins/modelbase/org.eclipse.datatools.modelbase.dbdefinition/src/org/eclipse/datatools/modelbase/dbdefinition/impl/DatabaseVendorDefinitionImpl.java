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

import java.util.Collection;

import org.eclipse.datatools.modelbase.dbdefinition.ColumnDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.ConstraintDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.ConstructedDataTypeDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.DatabaseDefinitionPackage;
import org.eclipse.datatools.modelbase.dbdefinition.DatabaseVendorDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.DebuggerDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.ExtendedDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.IndexDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.NicknameDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.PredefinedDataTypeDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.PrivilegedElementDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.QueryDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.SQLSyntaxDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.SchemaDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.SequenceDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.StoredProcedureDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.TableDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.TableSpaceDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.TriggerDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.UserDefinedTypeDefinition;
import org.eclipse.datatools.modelbase.dbdefinition.ViewDefinition;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Database Vendor Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#getPredefinedDataTypeDefinitions <em>Predefined Data Type Definitions</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#getTableSpaceDefinition <em>Table Space Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#getStoredProcedureDefinition <em>Stored Procedure Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#getTriggerDefinition <em>Trigger Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#getColumnDefinition <em>Column Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#getConstraintDefinition <em>Constraint Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#getExtendedDefinitions <em>Extended Definitions</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#getIndexDefinition <em>Index Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#getTableDefinition <em>Table Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#getSequenceDefinition <em>Sequence Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#getUdtDefinition <em>Udt Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#getQueryDefinition <em>Query Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#getSQLSyntaxDefinition <em>SQL Syntax Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#getNicknameDefinition <em>Nickname Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#getSchemaDefinition <em>Schema Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#getViewDefinition <em>View Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#getDebuggerDefinition <em>Debugger Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#getPrivilegedElementDefinitions <em>Privileged Element Definitions</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#getConstructedDataTypeDefinition <em>Constructed Data Type Definition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#getVendor <em>Vendor</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#isConstraintsSupported <em>Constraints Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#getMaximumIdentifierLength <em>Maximum Identifier Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#isTriggerSupported <em>Trigger Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#isSnapshotViewSupported <em>Snapshot View Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#isJoinSupported <em>Join Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#isViewTriggerSupported <em>View Trigger Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#isTablespacesSupported <em>Tablespaces Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#getMaximumCommentLength <em>Maximum Comment Length</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#isSequenceSupported <em>Sequence Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#isMQTSupported <em>MQT Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#isSchemaSupported <em>Schema Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#isAliasSupported <em>Alias Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#isSynonymSupported <em>Synonym Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#isUserDefinedTypeSupported <em>User Defined Type Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#isDomainSupported <em>Domain Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#isSQLStatementSupported <em>SQL Statement Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#isNicknameSupported <em>Nickname Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#isQuotedDMLSupported <em>Quoted DML Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#isQuotedDDLSupported <em>Quoted DDL Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#isXmlSupported <em>Xml Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#isMQTIndexSupported <em>MQT Index Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#isEventSupported <em>Event Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#isSqlUDFSupported <em>Sql UDF Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#isStoredProcedureSupported <em>Stored Procedure Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#isPackageSupported <em>Package Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#isAuthorizationIdentifierSupported <em>Authorization Identifier Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#isRoleSupported <em>Role Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#isGroupSupported <em>Group Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#isUserSupported <em>User Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#isRoleAuthorizationSupported <em>Role Authorization Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#isConstructedDataTypeSupported <em>Constructed Data Type Supported</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.dbdefinition.impl.DatabaseVendorDefinitionImpl#isUDFSupported <em>UDF Supported</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DatabaseVendorDefinitionImpl extends EObjectImpl implements DatabaseVendorDefinition {
	/**
	 * The cached value of the '{@link #getPredefinedDataTypeDefinitions() <em>Predefined Data Type Definitions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPredefinedDataTypeDefinitions()
	 * @generated
	 * @ordered
	 */
	protected EList predefinedDataTypeDefinitions;

	/**
	 * The cached value of the '{@link #getTableSpaceDefinition() <em>Table Space Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTableSpaceDefinition()
	 * @generated
	 * @ordered
	 */
	protected TableSpaceDefinition tableSpaceDefinition;

	/**
	 * The cached value of the '{@link #getStoredProcedureDefinition() <em>Stored Procedure Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStoredProcedureDefinition()
	 * @generated
	 * @ordered
	 */
	protected StoredProcedureDefinition storedProcedureDefinition;

	/**
	 * The cached value of the '{@link #getTriggerDefinition() <em>Trigger Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTriggerDefinition()
	 * @generated
	 * @ordered
	 */
	protected TriggerDefinition triggerDefinition;

	/**
	 * The cached value of the '{@link #getColumnDefinition() <em>Column Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColumnDefinition()
	 * @generated
	 * @ordered
	 */
	protected ColumnDefinition columnDefinition;

	/**
	 * The cached value of the '{@link #getConstraintDefinition() <em>Constraint Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraintDefinition()
	 * @generated
	 * @ordered
	 */
	protected ConstraintDefinition constraintDefinition;

	/**
	 * The cached value of the '{@link #getExtendedDefinitions() <em>Extended Definitions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtendedDefinitions()
	 * @generated
	 * @ordered
	 */
	protected EList extendedDefinitions;

	/**
	 * The cached value of the '{@link #getIndexDefinition() <em>Index Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIndexDefinition()
	 * @generated
	 * @ordered
	 */
	protected IndexDefinition indexDefinition;

	/**
	 * The cached value of the '{@link #getTableDefinition() <em>Table Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTableDefinition()
	 * @generated
	 * @ordered
	 */
	protected TableDefinition tableDefinition;

	/**
	 * The cached value of the '{@link #getSequenceDefinition() <em>Sequence Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSequenceDefinition()
	 * @generated
	 * @ordered
	 */
	protected SequenceDefinition sequenceDefinition;

	/**
	 * The cached value of the '{@link #getUdtDefinition() <em>Udt Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUdtDefinition()
	 * @generated
	 * @ordered
	 */
	protected UserDefinedTypeDefinition udtDefinition;

	/**
	 * The cached value of the '{@link #getQueryDefinition() <em>Query Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQueryDefinition()
	 * @generated
	 * @ordered
	 */
	protected QueryDefinition queryDefinition;

	/**
	 * The cached value of the '{@link #getSQLSyntaxDefinition() <em>SQL Syntax Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSQLSyntaxDefinition()
	 * @generated
	 * @ordered
	 */
	protected SQLSyntaxDefinition sqlSyntaxDefinition;

	/**
	 * The cached value of the '{@link #getNicknameDefinition() <em>Nickname Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNicknameDefinition()
	 * @generated
	 * @ordered
	 */
	protected NicknameDefinition nicknameDefinition;

	/**
	 * The cached value of the '{@link #getSchemaDefinition() <em>Schema Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchemaDefinition()
	 * @generated
	 * @ordered
	 */
	protected SchemaDefinition schemaDefinition;

	/**
	 * The cached value of the '{@link #getViewDefinition() <em>View Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getViewDefinition()
	 * @generated
	 * @ordered
	 */
	protected ViewDefinition viewDefinition;

	/**
	 * The cached value of the '{@link #getDebuggerDefinition() <em>Debugger Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDebuggerDefinition()
	 * @generated
	 * @ordered
	 */
	protected DebuggerDefinition debuggerDefinition;

	/**
	 * The cached value of the '{@link #getPrivilegedElementDefinitions() <em>Privileged Element Definitions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrivilegedElementDefinitions()
	 * @generated
	 * @ordered
	 */
	protected EList privilegedElementDefinitions;

	/**
	 * The cached value of the '{@link #getConstructedDataTypeDefinition() <em>Constructed Data Type Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstructedDataTypeDefinition()
	 * @generated
	 * @ordered
	 */
	protected ConstructedDataTypeDefinition constructedDataTypeDefinition;

	/**
	 * The default value of the '{@link #getVendor() <em>Vendor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVendor()
	 * @generated
	 * @ordered
	 */
	protected static final String VENDOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVendor() <em>Vendor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVendor()
	 * @generated
	 * @ordered
	 */
	protected String vendor = VENDOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #isConstraintsSupported() <em>Constraints Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConstraintsSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONSTRAINTS_SUPPORTED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isConstraintsSupported() <em>Constraints Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConstraintsSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean constraintsSupported = CONSTRAINTS_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaximumIdentifierLength() <em>Maximum Identifier Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumIdentifierLength()
	 * @generated
	 * @ordered
	 */
	protected static final int MAXIMUM_IDENTIFIER_LENGTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaximumIdentifierLength() <em>Maximum Identifier Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumIdentifierLength()
	 * @generated
	 * @ordered
	 */
	protected int maximumIdentifierLength = MAXIMUM_IDENTIFIER_LENGTH_EDEFAULT;

	/**
	 * The default value of the '{@link #isTriggerSupported() <em>Trigger Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTriggerSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TRIGGER_SUPPORTED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isTriggerSupported() <em>Trigger Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTriggerSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean triggerSupported = TRIGGER_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isSnapshotViewSupported() <em>Snapshot View Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSnapshotViewSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SNAPSHOT_VIEW_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSnapshotViewSupported() <em>Snapshot View Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSnapshotViewSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean snapshotViewSupported = SNAPSHOT_VIEW_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isJoinSupported() <em>Join Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isJoinSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean JOIN_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isJoinSupported() <em>Join Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isJoinSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean joinSupported = JOIN_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isViewTriggerSupported() <em>View Trigger Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isViewTriggerSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean VIEW_TRIGGER_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isViewTriggerSupported() <em>View Trigger Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isViewTriggerSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean viewTriggerSupported = VIEW_TRIGGER_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isTablespacesSupported() <em>Tablespaces Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTablespacesSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TABLESPACES_SUPPORTED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isTablespacesSupported() <em>Tablespaces Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTablespacesSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean tablespacesSupported = TABLESPACES_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #getMaximumCommentLength() <em>Maximum Comment Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumCommentLength()
	 * @generated
	 * @ordered
	 */
	protected static final int MAXIMUM_COMMENT_LENGTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMaximumCommentLength() <em>Maximum Comment Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaximumCommentLength()
	 * @generated
	 * @ordered
	 */
	protected int maximumCommentLength = MAXIMUM_COMMENT_LENGTH_EDEFAULT;

	/**
	 * The default value of the '{@link #isSequenceSupported() <em>Sequence Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSequenceSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SEQUENCE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSequenceSupported() <em>Sequence Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSequenceSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean sequenceSupported = SEQUENCE_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isMQTSupported() <em>MQT Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMQTSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MQT_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMQTSupported() <em>MQT Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMQTSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean mQTSupported = MQT_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isSchemaSupported() <em>Schema Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSchemaSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SCHEMA_SUPPORTED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isSchemaSupported() <em>Schema Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSchemaSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean schemaSupported = SCHEMA_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isAliasSupported() <em>Alias Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAliasSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ALIAS_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAliasSupported() <em>Alias Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAliasSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean aliasSupported = ALIAS_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isSynonymSupported() <em>Synonym Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSynonymSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SYNONYM_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSynonymSupported() <em>Synonym Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSynonymSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean synonymSupported = SYNONYM_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isUserDefinedTypeSupported() <em>User Defined Type Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUserDefinedTypeSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean USER_DEFINED_TYPE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUserDefinedTypeSupported() <em>User Defined Type Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUserDefinedTypeSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean userDefinedTypeSupported = USER_DEFINED_TYPE_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isDomainSupported() <em>Domain Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDomainSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DOMAIN_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDomainSupported() <em>Domain Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDomainSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean domainSupported = DOMAIN_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isSQLStatementSupported() <em>SQL Statement Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSQLStatementSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SQL_STATEMENT_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSQLStatementSupported() <em>SQL Statement Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSQLStatementSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean sqlStatementSupported = SQL_STATEMENT_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isNicknameSupported() <em>Nickname Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNicknameSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NICKNAME_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNicknameSupported() <em>Nickname Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNicknameSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean nicknameSupported = NICKNAME_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isQuotedDMLSupported() <em>Quoted DML Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isQuotedDMLSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean QUOTED_DML_SUPPORTED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isQuotedDMLSupported() <em>Quoted DML Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isQuotedDMLSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean quotedDMLSupported = QUOTED_DML_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isQuotedDDLSupported() <em>Quoted DDL Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isQuotedDDLSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean QUOTED_DDL_SUPPORTED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isQuotedDDLSupported() <em>Quoted DDL Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isQuotedDDLSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean quotedDDLSupported = QUOTED_DDL_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isXmlSupported() <em>Xml Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isXmlSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean XML_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isXmlSupported() <em>Xml Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isXmlSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean xmlSupported = XML_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isMQTIndexSupported() <em>MQT Index Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMQTIndexSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MQT_INDEX_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMQTIndexSupported() <em>MQT Index Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMQTIndexSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean mQTIndexSupported = MQT_INDEX_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isEventSupported() <em>Event Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEventSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EVENT_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isEventSupported() <em>Event Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEventSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean eventSupported = EVENT_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isSqlUDFSupported() <em>Sql UDF Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSqlUDFSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SQL_UDF_SUPPORTED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isSqlUDFSupported() <em>Sql UDF Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSqlUDFSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean sqlUDFSupported = SQL_UDF_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isStoredProcedureSupported() <em>Stored Procedure Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStoredProcedureSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean STORED_PROCEDURE_SUPPORTED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isStoredProcedureSupported() <em>Stored Procedure Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStoredProcedureSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean storedProcedureSupported = STORED_PROCEDURE_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isPackageSupported() <em>Package Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPackageSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PACKAGE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isPackageSupported() <em>Package Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPackageSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean packageSupported = PACKAGE_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isAuthorizationIdentifierSupported() <em>Authorization Identifier Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAuthorizationIdentifierSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean AUTHORIZATION_IDENTIFIER_SUPPORTED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isAuthorizationIdentifierSupported() <em>Authorization Identifier Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAuthorizationIdentifierSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean authorizationIdentifierSupported = AUTHORIZATION_IDENTIFIER_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isRoleSupported() <em>Role Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRoleSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ROLE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRoleSupported() <em>Role Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRoleSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean roleSupported = ROLE_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isGroupSupported() <em>Group Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isGroupSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean GROUP_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isGroupSupported() <em>Group Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isGroupSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean groupSupported = GROUP_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isUserSupported() <em>User Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUserSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean USER_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUserSupported() <em>User Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUserSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean userSupported = USER_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isRoleAuthorizationSupported() <em>Role Authorization Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRoleAuthorizationSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ROLE_AUTHORIZATION_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isRoleAuthorizationSupported() <em>Role Authorization Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRoleAuthorizationSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean roleAuthorizationSupported = ROLE_AUTHORIZATION_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isConstructedDataTypeSupported() <em>Constructed Data Type Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConstructedDataTypeSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONSTRUCTED_DATA_TYPE_SUPPORTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isConstructedDataTypeSupported() <em>Constructed Data Type Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConstructedDataTypeSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean constructedDataTypeSupported = CONSTRUCTED_DATA_TYPE_SUPPORTED_EDEFAULT;

	/**
	 * The default value of the '{@link #isUDFSupported() <em>UDF Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUDFSupported()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UDF_SUPPORTED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isUDFSupported() <em>UDF Supported</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUDFSupported()
	 * @generated
	 * @ordered
	 */
	protected boolean uDFSupported = UDF_SUPPORTED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DatabaseVendorDefinitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DatabaseDefinitionPackage.Literals.DATABASE_VENDOR_DEFINITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVendor() {
		return vendor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVendor(String newVendor) {
		String oldVendor = vendor;
		vendor = newVendor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__VENDOR, oldVendor, vendor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isConstraintsSupported() {
		return constraintsSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstraintsSupported(boolean newConstraintsSupported) {
		boolean oldConstraintsSupported = constraintsSupported;
		constraintsSupported = newConstraintsSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__CONSTRAINTS_SUPPORTED, oldConstraintsSupported, constraintsSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaximumIdentifierLength() {
		return maximumIdentifierLength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaximumIdentifierLength(int newMaximumIdentifierLength) {
		int oldMaximumIdentifierLength = maximumIdentifierLength;
		maximumIdentifierLength = newMaximumIdentifierLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH, oldMaximumIdentifierLength, maximumIdentifierLength));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isTriggerSupported() {
		return triggerSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTriggerSupported(boolean newTriggerSupported) {
		boolean oldTriggerSupported = triggerSupported;
		triggerSupported = newTriggerSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TRIGGER_SUPPORTED, oldTriggerSupported, triggerSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSnapshotViewSupported() {
		return snapshotViewSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSnapshotViewSupported(boolean newSnapshotViewSupported) {
		boolean oldSnapshotViewSupported = snapshotViewSupported;
		snapshotViewSupported = newSnapshotViewSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SNAPSHOT_VIEW_SUPPORTED, oldSnapshotViewSupported, snapshotViewSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isJoinSupported() {
		return joinSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJoinSupported(boolean newJoinSupported) {
		boolean oldJoinSupported = joinSupported;
		joinSupported = newJoinSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__JOIN_SUPPORTED, oldJoinSupported, joinSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isViewTriggerSupported() {
		return viewTriggerSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setViewTriggerSupported(boolean newViewTriggerSupported) {
		boolean oldViewTriggerSupported = viewTriggerSupported;
		viewTriggerSupported = newViewTriggerSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__VIEW_TRIGGER_SUPPORTED, oldViewTriggerSupported, viewTriggerSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isTablespacesSupported() {
		return tablespacesSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTablespacesSupported(boolean newTablespacesSupported) {
		boolean oldTablespacesSupported = tablespacesSupported;
		tablespacesSupported = newTablespacesSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TABLESPACES_SUPPORTED, oldTablespacesSupported, tablespacesSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getMaximumCommentLength() {
		return maximumCommentLength;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMaximumCommentLength(int newMaximumCommentLength) {
		int oldMaximumCommentLength = maximumCommentLength;
		maximumCommentLength = newMaximumCommentLength;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__MAXIMUM_COMMENT_LENGTH, oldMaximumCommentLength, maximumCommentLength));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSequenceSupported() {
		return sequenceSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSequenceSupported(boolean newSequenceSupported) {
		boolean oldSequenceSupported = sequenceSupported;
		sequenceSupported = newSequenceSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SEQUENCE_SUPPORTED, oldSequenceSupported, sequenceSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMQTSupported() {
		return mQTSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMQTSupported(boolean newMQTSupported) {
		boolean oldMQTSupported = mQTSupported;
		mQTSupported = newMQTSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__MQT_SUPPORTED, oldMQTSupported, mQTSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSchemaSupported() {
		return schemaSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSchemaSupported(boolean newSchemaSupported) {
		boolean oldSchemaSupported = schemaSupported;
		schemaSupported = newSchemaSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SCHEMA_SUPPORTED, oldSchemaSupported, schemaSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAliasSupported() {
		return aliasSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAliasSupported(boolean newAliasSupported) {
		boolean oldAliasSupported = aliasSupported;
		aliasSupported = newAliasSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__ALIAS_SUPPORTED, oldAliasSupported, aliasSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSynonymSupported() {
		return synonymSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSynonymSupported(boolean newSynonymSupported) {
		boolean oldSynonymSupported = synonymSupported;
		synonymSupported = newSynonymSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SYNONYM_SUPPORTED, oldSynonymSupported, synonymSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUserDefinedTypeSupported() {
		return userDefinedTypeSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUserDefinedTypeSupported(boolean newUserDefinedTypeSupported) {
		boolean oldUserDefinedTypeSupported = userDefinedTypeSupported;
		userDefinedTypeSupported = newUserDefinedTypeSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__USER_DEFINED_TYPE_SUPPORTED, oldUserDefinedTypeSupported, userDefinedTypeSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDomainSupported() {
		return domainSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDomainSupported(boolean newDomainSupported) {
		boolean oldDomainSupported = domainSupported;
		domainSupported = newDomainSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__DOMAIN_SUPPORTED, oldDomainSupported, domainSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSQLStatementSupported() {
		return sqlStatementSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSQLStatementSupported(boolean newSQLStatementSupported) {
		boolean oldSQLStatementSupported = sqlStatementSupported;
		sqlStatementSupported = newSQLStatementSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SQL_STATEMENT_SUPPORTED, oldSQLStatementSupported, sqlStatementSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isNicknameSupported() {
		return nicknameSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNicknameSupported(boolean newNicknameSupported) {
		boolean oldNicknameSupported = nicknameSupported;
		nicknameSupported = newNicknameSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__NICKNAME_SUPPORTED, oldNicknameSupported, nicknameSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isQuotedDMLSupported() {
		return quotedDMLSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQuotedDMLSupported(boolean newQuotedDMLSupported) {
		boolean oldQuotedDMLSupported = quotedDMLSupported;
		quotedDMLSupported = newQuotedDMLSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__QUOTED_DML_SUPPORTED, oldQuotedDMLSupported, quotedDMLSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isQuotedDDLSupported() {
		return quotedDDLSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQuotedDDLSupported(boolean newQuotedDDLSupported) {
		boolean oldQuotedDDLSupported = quotedDDLSupported;
		quotedDDLSupported = newQuotedDDLSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__QUOTED_DDL_SUPPORTED, oldQuotedDDLSupported, quotedDDLSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isXmlSupported() {
		return xmlSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setXmlSupported(boolean newXmlSupported) {
		boolean oldXmlSupported = xmlSupported;
		xmlSupported = newXmlSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__XML_SUPPORTED, oldXmlSupported, xmlSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMQTIndexSupported() {
		return mQTIndexSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMQTIndexSupported(boolean newMQTIndexSupported) {
		boolean oldMQTIndexSupported = mQTIndexSupported;
		mQTIndexSupported = newMQTIndexSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__MQT_INDEX_SUPPORTED, oldMQTIndexSupported, mQTIndexSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEventSupported() {
		return eventSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEventSupported(boolean newEventSupported) {
		boolean oldEventSupported = eventSupported;
		eventSupported = newEventSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__EVENT_SUPPORTED, oldEventSupported, eventSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSqlUDFSupported() {
		return sqlUDFSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSqlUDFSupported(boolean newSqlUDFSupported) {
		boolean oldSqlUDFSupported = sqlUDFSupported;
		sqlUDFSupported = newSqlUDFSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SQL_UDF_SUPPORTED, oldSqlUDFSupported, sqlUDFSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isStoredProcedureSupported() {
		return storedProcedureSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStoredProcedureSupported(boolean newStoredProcedureSupported) {
		boolean oldStoredProcedureSupported = storedProcedureSupported;
		storedProcedureSupported = newStoredProcedureSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__STORED_PROCEDURE_SUPPORTED, oldStoredProcedureSupported, storedProcedureSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isPackageSupported() {
		return packageSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPackageSupported(boolean newPackageSupported) {
		boolean oldPackageSupported = packageSupported;
		packageSupported = newPackageSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__PACKAGE_SUPPORTED, oldPackageSupported, packageSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAuthorizationIdentifierSupported() {
		return authorizationIdentifierSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAuthorizationIdentifierSupported(boolean newAuthorizationIdentifierSupported) {
		boolean oldAuthorizationIdentifierSupported = authorizationIdentifierSupported;
		authorizationIdentifierSupported = newAuthorizationIdentifierSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__AUTHORIZATION_IDENTIFIER_SUPPORTED, oldAuthorizationIdentifierSupported, authorizationIdentifierSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRoleSupported() {
		return roleSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRoleSupported(boolean newRoleSupported) {
		boolean oldRoleSupported = roleSupported;
		roleSupported = newRoleSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__ROLE_SUPPORTED, oldRoleSupported, roleSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isGroupSupported() {
		return groupSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGroupSupported(boolean newGroupSupported) {
		boolean oldGroupSupported = groupSupported;
		groupSupported = newGroupSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__GROUP_SUPPORTED, oldGroupSupported, groupSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUserSupported() {
		return userSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUserSupported(boolean newUserSupported) {
		boolean oldUserSupported = userSupported;
		userSupported = newUserSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__USER_SUPPORTED, oldUserSupported, userSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isRoleAuthorizationSupported() {
		return roleAuthorizationSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRoleAuthorizationSupported(boolean newRoleAuthorizationSupported) {
		boolean oldRoleAuthorizationSupported = roleAuthorizationSupported;
		roleAuthorizationSupported = newRoleAuthorizationSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__ROLE_AUTHORIZATION_SUPPORTED, oldRoleAuthorizationSupported, roleAuthorizationSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isConstructedDataTypeSupported() {
		return constructedDataTypeSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstructedDataTypeSupported(boolean newConstructedDataTypeSupported) {
		boolean oldConstructedDataTypeSupported = constructedDataTypeSupported;
		constructedDataTypeSupported = newConstructedDataTypeSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__CONSTRUCTED_DATA_TYPE_SUPPORTED, oldConstructedDataTypeSupported, constructedDataTypeSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUDFSupported() {
		return uDFSupported;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUDFSupported(boolean newUDFSupported) {
		boolean oldUDFSupported = uDFSupported;
		uDFSupported = newUDFSupported;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__UDF_SUPPORTED, oldUDFSupported, uDFSupported));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__PREDEFINED_DATA_TYPE_DEFINITIONS:
				return ((InternalEList)getPredefinedDataTypeDefinitions()).basicRemove(otherEnd, msgs);
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TABLE_SPACE_DEFINITION:
				return basicSetTableSpaceDefinition(null, msgs);
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__STORED_PROCEDURE_DEFINITION:
				return basicSetStoredProcedureDefinition(null, msgs);
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TRIGGER_DEFINITION:
				return basicSetTriggerDefinition(null, msgs);
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__COLUMN_DEFINITION:
				return basicSetColumnDefinition(null, msgs);
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__CONSTRAINT_DEFINITION:
				return basicSetConstraintDefinition(null, msgs);
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__EXTENDED_DEFINITIONS:
				return ((InternalEList)getExtendedDefinitions()).basicRemove(otherEnd, msgs);
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__INDEX_DEFINITION:
				return basicSetIndexDefinition(null, msgs);
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TABLE_DEFINITION:
				return basicSetTableDefinition(null, msgs);
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SEQUENCE_DEFINITION:
				return basicSetSequenceDefinition(null, msgs);
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__UDT_DEFINITION:
				return basicSetUdtDefinition(null, msgs);
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__QUERY_DEFINITION:
				return basicSetQueryDefinition(null, msgs);
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SQL_SYNTAX_DEFINITION:
				return basicSetSQLSyntaxDefinition(null, msgs);
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__NICKNAME_DEFINITION:
				return basicSetNicknameDefinition(null, msgs);
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SCHEMA_DEFINITION:
				return basicSetSchemaDefinition(null, msgs);
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__VIEW_DEFINITION:
				return basicSetViewDefinition(null, msgs);
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__DEBUGGER_DEFINITION:
				return basicSetDebuggerDefinition(null, msgs);
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__PRIVILEGED_ELEMENT_DEFINITIONS:
				return ((InternalEList)getPrivilegedElementDefinitions()).basicRemove(otherEnd, msgs);
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__CONSTRUCTED_DATA_TYPE_DEFINITION:
				return basicSetConstructedDataTypeDefinition(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__PREDEFINED_DATA_TYPE_DEFINITIONS:
				return getPredefinedDataTypeDefinitions();
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TABLE_SPACE_DEFINITION:
				return getTableSpaceDefinition();
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__STORED_PROCEDURE_DEFINITION:
				return getStoredProcedureDefinition();
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TRIGGER_DEFINITION:
				return getTriggerDefinition();
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__COLUMN_DEFINITION:
				return getColumnDefinition();
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__CONSTRAINT_DEFINITION:
				return getConstraintDefinition();
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__EXTENDED_DEFINITIONS:
				return getExtendedDefinitions();
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__INDEX_DEFINITION:
				return getIndexDefinition();
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TABLE_DEFINITION:
				return getTableDefinition();
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SEQUENCE_DEFINITION:
				return getSequenceDefinition();
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__UDT_DEFINITION:
				return getUdtDefinition();
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__QUERY_DEFINITION:
				return getQueryDefinition();
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SQL_SYNTAX_DEFINITION:
				return getSQLSyntaxDefinition();
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__NICKNAME_DEFINITION:
				return getNicknameDefinition();
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SCHEMA_DEFINITION:
				return getSchemaDefinition();
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__VIEW_DEFINITION:
				return getViewDefinition();
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__DEBUGGER_DEFINITION:
				return getDebuggerDefinition();
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__PRIVILEGED_ELEMENT_DEFINITIONS:
				return getPrivilegedElementDefinitions();
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__CONSTRUCTED_DATA_TYPE_DEFINITION:
				return getConstructedDataTypeDefinition();
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__VENDOR:
				return getVendor();
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__VERSION:
				return getVersion();
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__CONSTRAINTS_SUPPORTED:
				return isConstraintsSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH:
				return new Integer(getMaximumIdentifierLength());
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TRIGGER_SUPPORTED:
				return isTriggerSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SNAPSHOT_VIEW_SUPPORTED:
				return isSnapshotViewSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__JOIN_SUPPORTED:
				return isJoinSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__VIEW_TRIGGER_SUPPORTED:
				return isViewTriggerSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TABLESPACES_SUPPORTED:
				return isTablespacesSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__MAXIMUM_COMMENT_LENGTH:
				return new Integer(getMaximumCommentLength());
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SEQUENCE_SUPPORTED:
				return isSequenceSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__MQT_SUPPORTED:
				return isMQTSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SCHEMA_SUPPORTED:
				return isSchemaSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__ALIAS_SUPPORTED:
				return isAliasSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SYNONYM_SUPPORTED:
				return isSynonymSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__USER_DEFINED_TYPE_SUPPORTED:
				return isUserDefinedTypeSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__DOMAIN_SUPPORTED:
				return isDomainSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SQL_STATEMENT_SUPPORTED:
				return isSQLStatementSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__NICKNAME_SUPPORTED:
				return isNicknameSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__QUOTED_DML_SUPPORTED:
				return isQuotedDMLSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__QUOTED_DDL_SUPPORTED:
				return isQuotedDDLSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__XML_SUPPORTED:
				return isXmlSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__MQT_INDEX_SUPPORTED:
				return isMQTIndexSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__EVENT_SUPPORTED:
				return isEventSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SQL_UDF_SUPPORTED:
				return isSqlUDFSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__STORED_PROCEDURE_SUPPORTED:
				return isStoredProcedureSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__PACKAGE_SUPPORTED:
				return isPackageSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__AUTHORIZATION_IDENTIFIER_SUPPORTED:
				return isAuthorizationIdentifierSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__ROLE_SUPPORTED:
				return isRoleSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__GROUP_SUPPORTED:
				return isGroupSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__USER_SUPPORTED:
				return isUserSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__ROLE_AUTHORIZATION_SUPPORTED:
				return isRoleAuthorizationSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__CONSTRUCTED_DATA_TYPE_SUPPORTED:
				return isConstructedDataTypeSupported() ? Boolean.TRUE : Boolean.FALSE;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__UDF_SUPPORTED:
				return isUDFSupported() ? Boolean.TRUE : Boolean.FALSE;
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__PREDEFINED_DATA_TYPE_DEFINITIONS:
				getPredefinedDataTypeDefinitions().clear();
				getPredefinedDataTypeDefinitions().addAll((Collection)newValue);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TABLE_SPACE_DEFINITION:
				setTableSpaceDefinition((TableSpaceDefinition)newValue);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__STORED_PROCEDURE_DEFINITION:
				setStoredProcedureDefinition((StoredProcedureDefinition)newValue);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TRIGGER_DEFINITION:
				setTriggerDefinition((TriggerDefinition)newValue);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__COLUMN_DEFINITION:
				setColumnDefinition((ColumnDefinition)newValue);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__CONSTRAINT_DEFINITION:
				setConstraintDefinition((ConstraintDefinition)newValue);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__EXTENDED_DEFINITIONS:
				getExtendedDefinitions().clear();
				getExtendedDefinitions().addAll((Collection)newValue);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__INDEX_DEFINITION:
				setIndexDefinition((IndexDefinition)newValue);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TABLE_DEFINITION:
				setTableDefinition((TableDefinition)newValue);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SEQUENCE_DEFINITION:
				setSequenceDefinition((SequenceDefinition)newValue);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__UDT_DEFINITION:
				setUdtDefinition((UserDefinedTypeDefinition)newValue);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__QUERY_DEFINITION:
				setQueryDefinition((QueryDefinition)newValue);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SQL_SYNTAX_DEFINITION:
				setSQLSyntaxDefinition((SQLSyntaxDefinition)newValue);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__NICKNAME_DEFINITION:
				setNicknameDefinition((NicknameDefinition)newValue);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SCHEMA_DEFINITION:
				setSchemaDefinition((SchemaDefinition)newValue);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__VIEW_DEFINITION:
				setViewDefinition((ViewDefinition)newValue);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__DEBUGGER_DEFINITION:
				setDebuggerDefinition((DebuggerDefinition)newValue);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__PRIVILEGED_ELEMENT_DEFINITIONS:
				getPrivilegedElementDefinitions().clear();
				getPrivilegedElementDefinitions().addAll((Collection)newValue);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__CONSTRUCTED_DATA_TYPE_DEFINITION:
				setConstructedDataTypeDefinition((ConstructedDataTypeDefinition)newValue);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__VENDOR:
				setVendor((String)newValue);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__VERSION:
				setVersion((String)newValue);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__CONSTRAINTS_SUPPORTED:
				setConstraintsSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH:
				setMaximumIdentifierLength(((Integer)newValue).intValue());
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TRIGGER_SUPPORTED:
				setTriggerSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SNAPSHOT_VIEW_SUPPORTED:
				setSnapshotViewSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__JOIN_SUPPORTED:
				setJoinSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__VIEW_TRIGGER_SUPPORTED:
				setViewTriggerSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TABLESPACES_SUPPORTED:
				setTablespacesSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__MAXIMUM_COMMENT_LENGTH:
				setMaximumCommentLength(((Integer)newValue).intValue());
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SEQUENCE_SUPPORTED:
				setSequenceSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__MQT_SUPPORTED:
				setMQTSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SCHEMA_SUPPORTED:
				setSchemaSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__ALIAS_SUPPORTED:
				setAliasSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SYNONYM_SUPPORTED:
				setSynonymSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__USER_DEFINED_TYPE_SUPPORTED:
				setUserDefinedTypeSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__DOMAIN_SUPPORTED:
				setDomainSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SQL_STATEMENT_SUPPORTED:
				setSQLStatementSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__NICKNAME_SUPPORTED:
				setNicknameSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__QUOTED_DML_SUPPORTED:
				setQuotedDMLSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__QUOTED_DDL_SUPPORTED:
				setQuotedDDLSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__XML_SUPPORTED:
				setXmlSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__MQT_INDEX_SUPPORTED:
				setMQTIndexSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__EVENT_SUPPORTED:
				setEventSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SQL_UDF_SUPPORTED:
				setSqlUDFSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__STORED_PROCEDURE_SUPPORTED:
				setStoredProcedureSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__PACKAGE_SUPPORTED:
				setPackageSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__AUTHORIZATION_IDENTIFIER_SUPPORTED:
				setAuthorizationIdentifierSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__ROLE_SUPPORTED:
				setRoleSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__GROUP_SUPPORTED:
				setGroupSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__USER_SUPPORTED:
				setUserSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__ROLE_AUTHORIZATION_SUPPORTED:
				setRoleAuthorizationSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__CONSTRUCTED_DATA_TYPE_SUPPORTED:
				setConstructedDataTypeSupported(((Boolean)newValue).booleanValue());
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__UDF_SUPPORTED:
				setUDFSupported(((Boolean)newValue).booleanValue());
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__PREDEFINED_DATA_TYPE_DEFINITIONS:
				getPredefinedDataTypeDefinitions().clear();
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TABLE_SPACE_DEFINITION:
				setTableSpaceDefinition((TableSpaceDefinition)null);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__STORED_PROCEDURE_DEFINITION:
				setStoredProcedureDefinition((StoredProcedureDefinition)null);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TRIGGER_DEFINITION:
				setTriggerDefinition((TriggerDefinition)null);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__COLUMN_DEFINITION:
				setColumnDefinition((ColumnDefinition)null);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__CONSTRAINT_DEFINITION:
				setConstraintDefinition((ConstraintDefinition)null);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__EXTENDED_DEFINITIONS:
				getExtendedDefinitions().clear();
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__INDEX_DEFINITION:
				setIndexDefinition((IndexDefinition)null);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TABLE_DEFINITION:
				setTableDefinition((TableDefinition)null);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SEQUENCE_DEFINITION:
				setSequenceDefinition((SequenceDefinition)null);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__UDT_DEFINITION:
				setUdtDefinition((UserDefinedTypeDefinition)null);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__QUERY_DEFINITION:
				setQueryDefinition((QueryDefinition)null);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SQL_SYNTAX_DEFINITION:
				setSQLSyntaxDefinition((SQLSyntaxDefinition)null);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__NICKNAME_DEFINITION:
				setNicknameDefinition((NicknameDefinition)null);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SCHEMA_DEFINITION:
				setSchemaDefinition((SchemaDefinition)null);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__VIEW_DEFINITION:
				setViewDefinition((ViewDefinition)null);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__DEBUGGER_DEFINITION:
				setDebuggerDefinition((DebuggerDefinition)null);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__PRIVILEGED_ELEMENT_DEFINITIONS:
				getPrivilegedElementDefinitions().clear();
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__CONSTRUCTED_DATA_TYPE_DEFINITION:
				setConstructedDataTypeDefinition((ConstructedDataTypeDefinition)null);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__VENDOR:
				setVendor(VENDOR_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__CONSTRAINTS_SUPPORTED:
				setConstraintsSupported(CONSTRAINTS_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH:
				setMaximumIdentifierLength(MAXIMUM_IDENTIFIER_LENGTH_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TRIGGER_SUPPORTED:
				setTriggerSupported(TRIGGER_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SNAPSHOT_VIEW_SUPPORTED:
				setSnapshotViewSupported(SNAPSHOT_VIEW_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__JOIN_SUPPORTED:
				setJoinSupported(JOIN_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__VIEW_TRIGGER_SUPPORTED:
				setViewTriggerSupported(VIEW_TRIGGER_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TABLESPACES_SUPPORTED:
				setTablespacesSupported(TABLESPACES_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__MAXIMUM_COMMENT_LENGTH:
				setMaximumCommentLength(MAXIMUM_COMMENT_LENGTH_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SEQUENCE_SUPPORTED:
				setSequenceSupported(SEQUENCE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__MQT_SUPPORTED:
				setMQTSupported(MQT_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SCHEMA_SUPPORTED:
				setSchemaSupported(SCHEMA_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__ALIAS_SUPPORTED:
				setAliasSupported(ALIAS_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SYNONYM_SUPPORTED:
				setSynonymSupported(SYNONYM_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__USER_DEFINED_TYPE_SUPPORTED:
				setUserDefinedTypeSupported(USER_DEFINED_TYPE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__DOMAIN_SUPPORTED:
				setDomainSupported(DOMAIN_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SQL_STATEMENT_SUPPORTED:
				setSQLStatementSupported(SQL_STATEMENT_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__NICKNAME_SUPPORTED:
				setNicknameSupported(NICKNAME_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__QUOTED_DML_SUPPORTED:
				setQuotedDMLSupported(QUOTED_DML_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__QUOTED_DDL_SUPPORTED:
				setQuotedDDLSupported(QUOTED_DDL_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__XML_SUPPORTED:
				setXmlSupported(XML_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__MQT_INDEX_SUPPORTED:
				setMQTIndexSupported(MQT_INDEX_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__EVENT_SUPPORTED:
				setEventSupported(EVENT_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SQL_UDF_SUPPORTED:
				setSqlUDFSupported(SQL_UDF_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__STORED_PROCEDURE_SUPPORTED:
				setStoredProcedureSupported(STORED_PROCEDURE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__PACKAGE_SUPPORTED:
				setPackageSupported(PACKAGE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__AUTHORIZATION_IDENTIFIER_SUPPORTED:
				setAuthorizationIdentifierSupported(AUTHORIZATION_IDENTIFIER_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__ROLE_SUPPORTED:
				setRoleSupported(ROLE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__GROUP_SUPPORTED:
				setGroupSupported(GROUP_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__USER_SUPPORTED:
				setUserSupported(USER_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__ROLE_AUTHORIZATION_SUPPORTED:
				setRoleAuthorizationSupported(ROLE_AUTHORIZATION_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__CONSTRUCTED_DATA_TYPE_SUPPORTED:
				setConstructedDataTypeSupported(CONSTRUCTED_DATA_TYPE_SUPPORTED_EDEFAULT);
				return;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__UDF_SUPPORTED:
				setUDFSupported(UDF_SUPPORTED_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__PREDEFINED_DATA_TYPE_DEFINITIONS:
				return predefinedDataTypeDefinitions != null && !predefinedDataTypeDefinitions.isEmpty();
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TABLE_SPACE_DEFINITION:
				return tableSpaceDefinition != null;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__STORED_PROCEDURE_DEFINITION:
				return storedProcedureDefinition != null;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TRIGGER_DEFINITION:
				return triggerDefinition != null;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__COLUMN_DEFINITION:
				return columnDefinition != null;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__CONSTRAINT_DEFINITION:
				return constraintDefinition != null;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__EXTENDED_DEFINITIONS:
				return extendedDefinitions != null && !extendedDefinitions.isEmpty();
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__INDEX_DEFINITION:
				return indexDefinition != null;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TABLE_DEFINITION:
				return tableDefinition != null;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SEQUENCE_DEFINITION:
				return sequenceDefinition != null;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__UDT_DEFINITION:
				return udtDefinition != null;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__QUERY_DEFINITION:
				return queryDefinition != null;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SQL_SYNTAX_DEFINITION:
				return sqlSyntaxDefinition != null;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__NICKNAME_DEFINITION:
				return nicknameDefinition != null;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SCHEMA_DEFINITION:
				return schemaDefinition != null;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__VIEW_DEFINITION:
				return viewDefinition != null;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__DEBUGGER_DEFINITION:
				return debuggerDefinition != null;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__PRIVILEGED_ELEMENT_DEFINITIONS:
				return privilegedElementDefinitions != null && !privilegedElementDefinitions.isEmpty();
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__CONSTRUCTED_DATA_TYPE_DEFINITION:
				return constructedDataTypeDefinition != null;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__VENDOR:
				return VENDOR_EDEFAULT == null ? vendor != null : !VENDOR_EDEFAULT.equals(vendor);
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__CONSTRAINTS_SUPPORTED:
				return constraintsSupported != CONSTRAINTS_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__MAXIMUM_IDENTIFIER_LENGTH:
				return maximumIdentifierLength != MAXIMUM_IDENTIFIER_LENGTH_EDEFAULT;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TRIGGER_SUPPORTED:
				return triggerSupported != TRIGGER_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SNAPSHOT_VIEW_SUPPORTED:
				return snapshotViewSupported != SNAPSHOT_VIEW_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__JOIN_SUPPORTED:
				return joinSupported != JOIN_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__VIEW_TRIGGER_SUPPORTED:
				return viewTriggerSupported != VIEW_TRIGGER_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TABLESPACES_SUPPORTED:
				return tablespacesSupported != TABLESPACES_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__MAXIMUM_COMMENT_LENGTH:
				return maximumCommentLength != MAXIMUM_COMMENT_LENGTH_EDEFAULT;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SEQUENCE_SUPPORTED:
				return sequenceSupported != SEQUENCE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__MQT_SUPPORTED:
				return mQTSupported != MQT_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SCHEMA_SUPPORTED:
				return schemaSupported != SCHEMA_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__ALIAS_SUPPORTED:
				return aliasSupported != ALIAS_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SYNONYM_SUPPORTED:
				return synonymSupported != SYNONYM_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__USER_DEFINED_TYPE_SUPPORTED:
				return userDefinedTypeSupported != USER_DEFINED_TYPE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__DOMAIN_SUPPORTED:
				return domainSupported != DOMAIN_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SQL_STATEMENT_SUPPORTED:
				return sqlStatementSupported != SQL_STATEMENT_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__NICKNAME_SUPPORTED:
				return nicknameSupported != NICKNAME_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__QUOTED_DML_SUPPORTED:
				return quotedDMLSupported != QUOTED_DML_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__QUOTED_DDL_SUPPORTED:
				return quotedDDLSupported != QUOTED_DDL_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__XML_SUPPORTED:
				return xmlSupported != XML_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__MQT_INDEX_SUPPORTED:
				return mQTIndexSupported != MQT_INDEX_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__EVENT_SUPPORTED:
				return eventSupported != EVENT_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SQL_UDF_SUPPORTED:
				return sqlUDFSupported != SQL_UDF_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__STORED_PROCEDURE_SUPPORTED:
				return storedProcedureSupported != STORED_PROCEDURE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__PACKAGE_SUPPORTED:
				return packageSupported != PACKAGE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__AUTHORIZATION_IDENTIFIER_SUPPORTED:
				return authorizationIdentifierSupported != AUTHORIZATION_IDENTIFIER_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__ROLE_SUPPORTED:
				return roleSupported != ROLE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__GROUP_SUPPORTED:
				return groupSupported != GROUP_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__USER_SUPPORTED:
				return userSupported != USER_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__ROLE_AUTHORIZATION_SUPPORTED:
				return roleAuthorizationSupported != ROLE_AUTHORIZATION_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__CONSTRUCTED_DATA_TYPE_SUPPORTED:
				return constructedDataTypeSupported != CONSTRUCTED_DATA_TYPE_SUPPORTED_EDEFAULT;
			case DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__UDF_SUPPORTED:
				return uDFSupported != UDF_SUPPORTED_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPredefinedDataTypeDefinitions() {
		if (predefinedDataTypeDefinitions == null) {
			predefinedDataTypeDefinitions = new EObjectContainmentEList(PredefinedDataTypeDefinition.class, this, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__PREDEFINED_DATA_TYPE_DEFINITIONS);
		}
		return predefinedDataTypeDefinitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TableSpaceDefinition getTableSpaceDefinition() {
		return tableSpaceDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTableSpaceDefinition(TableSpaceDefinition newTableSpaceDefinition, NotificationChain msgs) {
		TableSpaceDefinition oldTableSpaceDefinition = tableSpaceDefinition;
		tableSpaceDefinition = newTableSpaceDefinition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TABLE_SPACE_DEFINITION, oldTableSpaceDefinition, newTableSpaceDefinition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTableSpaceDefinition(TableSpaceDefinition newTableSpaceDefinition) {
		if (newTableSpaceDefinition != tableSpaceDefinition) {
			NotificationChain msgs = null;
			if (tableSpaceDefinition != null)
				msgs = ((InternalEObject)tableSpaceDefinition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TABLE_SPACE_DEFINITION, null, msgs);
			if (newTableSpaceDefinition != null)
				msgs = ((InternalEObject)newTableSpaceDefinition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TABLE_SPACE_DEFINITION, null, msgs);
			msgs = basicSetTableSpaceDefinition(newTableSpaceDefinition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TABLE_SPACE_DEFINITION, newTableSpaceDefinition, newTableSpaceDefinition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StoredProcedureDefinition getStoredProcedureDefinition() {
		return storedProcedureDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStoredProcedureDefinition(StoredProcedureDefinition newStoredProcedureDefinition, NotificationChain msgs) {
		StoredProcedureDefinition oldStoredProcedureDefinition = storedProcedureDefinition;
		storedProcedureDefinition = newStoredProcedureDefinition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__STORED_PROCEDURE_DEFINITION, oldStoredProcedureDefinition, newStoredProcedureDefinition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStoredProcedureDefinition(StoredProcedureDefinition newStoredProcedureDefinition) {
		if (newStoredProcedureDefinition != storedProcedureDefinition) {
			NotificationChain msgs = null;
			if (storedProcedureDefinition != null)
				msgs = ((InternalEObject)storedProcedureDefinition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__STORED_PROCEDURE_DEFINITION, null, msgs);
			if (newStoredProcedureDefinition != null)
				msgs = ((InternalEObject)newStoredProcedureDefinition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__STORED_PROCEDURE_DEFINITION, null, msgs);
			msgs = basicSetStoredProcedureDefinition(newStoredProcedureDefinition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__STORED_PROCEDURE_DEFINITION, newStoredProcedureDefinition, newStoredProcedureDefinition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TriggerDefinition getTriggerDefinition() {
		return triggerDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTriggerDefinition(TriggerDefinition newTriggerDefinition, NotificationChain msgs) {
		TriggerDefinition oldTriggerDefinition = triggerDefinition;
		triggerDefinition = newTriggerDefinition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TRIGGER_DEFINITION, oldTriggerDefinition, newTriggerDefinition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTriggerDefinition(TriggerDefinition newTriggerDefinition) {
		if (newTriggerDefinition != triggerDefinition) {
			NotificationChain msgs = null;
			if (triggerDefinition != null)
				msgs = ((InternalEObject)triggerDefinition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TRIGGER_DEFINITION, null, msgs);
			if (newTriggerDefinition != null)
				msgs = ((InternalEObject)newTriggerDefinition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TRIGGER_DEFINITION, null, msgs);
			msgs = basicSetTriggerDefinition(newTriggerDefinition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TRIGGER_DEFINITION, newTriggerDefinition, newTriggerDefinition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ColumnDefinition getColumnDefinition() {
		return columnDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetColumnDefinition(ColumnDefinition newColumnDefinition, NotificationChain msgs) {
		ColumnDefinition oldColumnDefinition = columnDefinition;
		columnDefinition = newColumnDefinition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__COLUMN_DEFINITION, oldColumnDefinition, newColumnDefinition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setColumnDefinition(ColumnDefinition newColumnDefinition) {
		if (newColumnDefinition != columnDefinition) {
			NotificationChain msgs = null;
			if (columnDefinition != null)
				msgs = ((InternalEObject)columnDefinition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__COLUMN_DEFINITION, null, msgs);
			if (newColumnDefinition != null)
				msgs = ((InternalEObject)newColumnDefinition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__COLUMN_DEFINITION, null, msgs);
			msgs = basicSetColumnDefinition(newColumnDefinition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__COLUMN_DEFINITION, newColumnDefinition, newColumnDefinition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstraintDefinition getConstraintDefinition() {
		return constraintDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConstraintDefinition(ConstraintDefinition newConstraintDefinition, NotificationChain msgs) {
		ConstraintDefinition oldConstraintDefinition = constraintDefinition;
		constraintDefinition = newConstraintDefinition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__CONSTRAINT_DEFINITION, oldConstraintDefinition, newConstraintDefinition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstraintDefinition(ConstraintDefinition newConstraintDefinition) {
		if (newConstraintDefinition != constraintDefinition) {
			NotificationChain msgs = null;
			if (constraintDefinition != null)
				msgs = ((InternalEObject)constraintDefinition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__CONSTRAINT_DEFINITION, null, msgs);
			if (newConstraintDefinition != null)
				msgs = ((InternalEObject)newConstraintDefinition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__CONSTRAINT_DEFINITION, null, msgs);
			msgs = basicSetConstraintDefinition(newConstraintDefinition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__CONSTRAINT_DEFINITION, newConstraintDefinition, newConstraintDefinition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getExtendedDefinitions() {
		if (extendedDefinitions == null) {
			extendedDefinitions = new EObjectContainmentEList(ExtendedDefinition.class, this, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__EXTENDED_DEFINITIONS);
		}
		return extendedDefinitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IndexDefinition getIndexDefinition() {
		return indexDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIndexDefinition(IndexDefinition newIndexDefinition, NotificationChain msgs) {
		IndexDefinition oldIndexDefinition = indexDefinition;
		indexDefinition = newIndexDefinition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__INDEX_DEFINITION, oldIndexDefinition, newIndexDefinition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIndexDefinition(IndexDefinition newIndexDefinition) {
		if (newIndexDefinition != indexDefinition) {
			NotificationChain msgs = null;
			if (indexDefinition != null)
				msgs = ((InternalEObject)indexDefinition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__INDEX_DEFINITION, null, msgs);
			if (newIndexDefinition != null)
				msgs = ((InternalEObject)newIndexDefinition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__INDEX_DEFINITION, null, msgs);
			msgs = basicSetIndexDefinition(newIndexDefinition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__INDEX_DEFINITION, newIndexDefinition, newIndexDefinition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TableDefinition getTableDefinition() {
		return tableDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTableDefinition(TableDefinition newTableDefinition, NotificationChain msgs) {
		TableDefinition oldTableDefinition = tableDefinition;
		tableDefinition = newTableDefinition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TABLE_DEFINITION, oldTableDefinition, newTableDefinition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTableDefinition(TableDefinition newTableDefinition) {
		if (newTableDefinition != tableDefinition) {
			NotificationChain msgs = null;
			if (tableDefinition != null)
				msgs = ((InternalEObject)tableDefinition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TABLE_DEFINITION, null, msgs);
			if (newTableDefinition != null)
				msgs = ((InternalEObject)newTableDefinition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TABLE_DEFINITION, null, msgs);
			msgs = basicSetTableDefinition(newTableDefinition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__TABLE_DEFINITION, newTableDefinition, newTableDefinition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SequenceDefinition getSequenceDefinition() {
		return sequenceDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSequenceDefinition(SequenceDefinition newSequenceDefinition, NotificationChain msgs) {
		SequenceDefinition oldSequenceDefinition = sequenceDefinition;
		sequenceDefinition = newSequenceDefinition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SEQUENCE_DEFINITION, oldSequenceDefinition, newSequenceDefinition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSequenceDefinition(SequenceDefinition newSequenceDefinition) {
		if (newSequenceDefinition != sequenceDefinition) {
			NotificationChain msgs = null;
			if (sequenceDefinition != null)
				msgs = ((InternalEObject)sequenceDefinition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SEQUENCE_DEFINITION, null, msgs);
			if (newSequenceDefinition != null)
				msgs = ((InternalEObject)newSequenceDefinition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SEQUENCE_DEFINITION, null, msgs);
			msgs = basicSetSequenceDefinition(newSequenceDefinition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SEQUENCE_DEFINITION, newSequenceDefinition, newSequenceDefinition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserDefinedTypeDefinition getUdtDefinition() {
		return udtDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUdtDefinition(UserDefinedTypeDefinition newUdtDefinition, NotificationChain msgs) {
		UserDefinedTypeDefinition oldUdtDefinition = udtDefinition;
		udtDefinition = newUdtDefinition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__UDT_DEFINITION, oldUdtDefinition, newUdtDefinition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUdtDefinition(UserDefinedTypeDefinition newUdtDefinition) {
		if (newUdtDefinition != udtDefinition) {
			NotificationChain msgs = null;
			if (udtDefinition != null)
				msgs = ((InternalEObject)udtDefinition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__UDT_DEFINITION, null, msgs);
			if (newUdtDefinition != null)
				msgs = ((InternalEObject)newUdtDefinition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__UDT_DEFINITION, null, msgs);
			msgs = basicSetUdtDefinition(newUdtDefinition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__UDT_DEFINITION, newUdtDefinition, newUdtDefinition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QueryDefinition getQueryDefinition() {
		return queryDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetQueryDefinition(QueryDefinition newQueryDefinition, NotificationChain msgs) {
		QueryDefinition oldQueryDefinition = queryDefinition;
		queryDefinition = newQueryDefinition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__QUERY_DEFINITION, oldQueryDefinition, newQueryDefinition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQueryDefinition(QueryDefinition newQueryDefinition) {
		if (newQueryDefinition != queryDefinition) {
			NotificationChain msgs = null;
			if (queryDefinition != null)
				msgs = ((InternalEObject)queryDefinition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__QUERY_DEFINITION, null, msgs);
			if (newQueryDefinition != null)
				msgs = ((InternalEObject)newQueryDefinition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__QUERY_DEFINITION, null, msgs);
			msgs = basicSetQueryDefinition(newQueryDefinition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__QUERY_DEFINITION, newQueryDefinition, newQueryDefinition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SQLSyntaxDefinition getSQLSyntaxDefinition() {
		return sqlSyntaxDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSQLSyntaxDefinition(SQLSyntaxDefinition newSQLSyntaxDefinition, NotificationChain msgs) {
		SQLSyntaxDefinition oldSQLSyntaxDefinition = sqlSyntaxDefinition;
		sqlSyntaxDefinition = newSQLSyntaxDefinition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SQL_SYNTAX_DEFINITION, oldSQLSyntaxDefinition, newSQLSyntaxDefinition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSQLSyntaxDefinition(SQLSyntaxDefinition newSQLSyntaxDefinition) {
		if (newSQLSyntaxDefinition != sqlSyntaxDefinition) {
			NotificationChain msgs = null;
			if (sqlSyntaxDefinition != null)
				msgs = ((InternalEObject)sqlSyntaxDefinition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SQL_SYNTAX_DEFINITION, null, msgs);
			if (newSQLSyntaxDefinition != null)
				msgs = ((InternalEObject)newSQLSyntaxDefinition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SQL_SYNTAX_DEFINITION, null, msgs);
			msgs = basicSetSQLSyntaxDefinition(newSQLSyntaxDefinition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SQL_SYNTAX_DEFINITION, newSQLSyntaxDefinition, newSQLSyntaxDefinition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NicknameDefinition getNicknameDefinition() {
		return nicknameDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNicknameDefinition(NicknameDefinition newNicknameDefinition, NotificationChain msgs) {
		NicknameDefinition oldNicknameDefinition = nicknameDefinition;
		nicknameDefinition = newNicknameDefinition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__NICKNAME_DEFINITION, oldNicknameDefinition, newNicknameDefinition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNicknameDefinition(NicknameDefinition newNicknameDefinition) {
		if (newNicknameDefinition != nicknameDefinition) {
			NotificationChain msgs = null;
			if (nicknameDefinition != null)
				msgs = ((InternalEObject)nicknameDefinition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__NICKNAME_DEFINITION, null, msgs);
			if (newNicknameDefinition != null)
				msgs = ((InternalEObject)newNicknameDefinition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__NICKNAME_DEFINITION, null, msgs);
			msgs = basicSetNicknameDefinition(newNicknameDefinition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__NICKNAME_DEFINITION, newNicknameDefinition, newNicknameDefinition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SchemaDefinition getSchemaDefinition() {
		return schemaDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSchemaDefinition(SchemaDefinition newSchemaDefinition, NotificationChain msgs) {
		SchemaDefinition oldSchemaDefinition = schemaDefinition;
		schemaDefinition = newSchemaDefinition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SCHEMA_DEFINITION, oldSchemaDefinition, newSchemaDefinition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSchemaDefinition(SchemaDefinition newSchemaDefinition) {
		if (newSchemaDefinition != schemaDefinition) {
			NotificationChain msgs = null;
			if (schemaDefinition != null)
				msgs = ((InternalEObject)schemaDefinition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SCHEMA_DEFINITION, null, msgs);
			if (newSchemaDefinition != null)
				msgs = ((InternalEObject)newSchemaDefinition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SCHEMA_DEFINITION, null, msgs);
			msgs = basicSetSchemaDefinition(newSchemaDefinition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__SCHEMA_DEFINITION, newSchemaDefinition, newSchemaDefinition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViewDefinition getViewDefinition() {
		return viewDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetViewDefinition(ViewDefinition newViewDefinition, NotificationChain msgs) {
		ViewDefinition oldViewDefinition = viewDefinition;
		viewDefinition = newViewDefinition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__VIEW_DEFINITION, oldViewDefinition, newViewDefinition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setViewDefinition(ViewDefinition newViewDefinition) {
		if (newViewDefinition != viewDefinition) {
			NotificationChain msgs = null;
			if (viewDefinition != null)
				msgs = ((InternalEObject)viewDefinition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__VIEW_DEFINITION, null, msgs);
			if (newViewDefinition != null)
				msgs = ((InternalEObject)newViewDefinition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__VIEW_DEFINITION, null, msgs);
			msgs = basicSetViewDefinition(newViewDefinition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__VIEW_DEFINITION, newViewDefinition, newViewDefinition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DebuggerDefinition getDebuggerDefinition() {
		return debuggerDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDebuggerDefinition(DebuggerDefinition newDebuggerDefinition, NotificationChain msgs) {
		DebuggerDefinition oldDebuggerDefinition = debuggerDefinition;
		debuggerDefinition = newDebuggerDefinition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__DEBUGGER_DEFINITION, oldDebuggerDefinition, newDebuggerDefinition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDebuggerDefinition(DebuggerDefinition newDebuggerDefinition) {
		if (newDebuggerDefinition != debuggerDefinition) {
			NotificationChain msgs = null;
			if (debuggerDefinition != null)
				msgs = ((InternalEObject)debuggerDefinition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__DEBUGGER_DEFINITION, null, msgs);
			if (newDebuggerDefinition != null)
				msgs = ((InternalEObject)newDebuggerDefinition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__DEBUGGER_DEFINITION, null, msgs);
			msgs = basicSetDebuggerDefinition(newDebuggerDefinition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__DEBUGGER_DEFINITION, newDebuggerDefinition, newDebuggerDefinition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPrivilegedElementDefinitions() {
		if (privilegedElementDefinitions == null) {
			privilegedElementDefinitions = new EObjectContainmentEList(PrivilegedElementDefinition.class, this, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__PRIVILEGED_ELEMENT_DEFINITIONS);
		}
		return privilegedElementDefinitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstructedDataTypeDefinition getConstructedDataTypeDefinition() {
		return constructedDataTypeDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConstructedDataTypeDefinition(ConstructedDataTypeDefinition newConstructedDataTypeDefinition, NotificationChain msgs) {
		ConstructedDataTypeDefinition oldConstructedDataTypeDefinition = constructedDataTypeDefinition;
		constructedDataTypeDefinition = newConstructedDataTypeDefinition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__CONSTRUCTED_DATA_TYPE_DEFINITION, oldConstructedDataTypeDefinition, newConstructedDataTypeDefinition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstructedDataTypeDefinition(ConstructedDataTypeDefinition newConstructedDataTypeDefinition) {
		if (newConstructedDataTypeDefinition != constructedDataTypeDefinition) {
			NotificationChain msgs = null;
			if (constructedDataTypeDefinition != null)
				msgs = ((InternalEObject)constructedDataTypeDefinition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__CONSTRUCTED_DATA_TYPE_DEFINITION, null, msgs);
			if (newConstructedDataTypeDefinition != null)
				msgs = ((InternalEObject)newConstructedDataTypeDefinition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__CONSTRUCTED_DATA_TYPE_DEFINITION, null, msgs);
			msgs = basicSetConstructedDataTypeDefinition(newConstructedDataTypeDefinition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatabaseDefinitionPackage.DATABASE_VENDOR_DEFINITION__CONSTRUCTED_DATA_TYPE_DEFINITION, newConstructedDataTypeDefinition, newConstructedDataTypeDefinition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (vendor: "); //$NON-NLS-1$
		result.append(vendor);
		result.append(", version: "); //$NON-NLS-1$
		result.append(version);
		result.append(", constraintsSupported: "); //$NON-NLS-1$
		result.append(constraintsSupported);
		result.append(", maximumIdentifierLength: "); //$NON-NLS-1$
		result.append(maximumIdentifierLength);
		result.append(", triggerSupported: "); //$NON-NLS-1$
		result.append(triggerSupported);
		result.append(", snapshotViewSupported: "); //$NON-NLS-1$
		result.append(snapshotViewSupported);
		result.append(", joinSupported: "); //$NON-NLS-1$
		result.append(joinSupported);
		result.append(", viewTriggerSupported: "); //$NON-NLS-1$
		result.append(viewTriggerSupported);
		result.append(", tablespacesSupported: "); //$NON-NLS-1$
		result.append(tablespacesSupported);
		result.append(", maximumCommentLength: "); //$NON-NLS-1$
		result.append(maximumCommentLength);
		result.append(", sequenceSupported: "); //$NON-NLS-1$
		result.append(sequenceSupported);
		result.append(", mQTSupported: "); //$NON-NLS-1$
		result.append(mQTSupported);
		result.append(", schemaSupported: "); //$NON-NLS-1$
		result.append(schemaSupported);
		result.append(", aliasSupported: "); //$NON-NLS-1$
		result.append(aliasSupported);
		result.append(", synonymSupported: "); //$NON-NLS-1$
		result.append(synonymSupported);
		result.append(", userDefinedTypeSupported: "); //$NON-NLS-1$
		result.append(userDefinedTypeSupported);
		result.append(", domainSupported: "); //$NON-NLS-1$
		result.append(domainSupported);
		result.append(", SQLStatementSupported: "); //$NON-NLS-1$
		result.append(sqlStatementSupported);
		result.append(", nicknameSupported: "); //$NON-NLS-1$
		result.append(nicknameSupported);
		result.append(", quotedDMLSupported: "); //$NON-NLS-1$
		result.append(quotedDMLSupported);
		result.append(", quotedDDLSupported: "); //$NON-NLS-1$
		result.append(quotedDDLSupported);
		result.append(", xmlSupported: "); //$NON-NLS-1$
		result.append(xmlSupported);
		result.append(", mQTIndexSupported: "); //$NON-NLS-1$
		result.append(mQTIndexSupported);
		result.append(", eventSupported: "); //$NON-NLS-1$
		result.append(eventSupported);
		result.append(", sqlUDFSupported: "); //$NON-NLS-1$
		result.append(sqlUDFSupported);
		result.append(", storedProcedureSupported: "); //$NON-NLS-1$
		result.append(storedProcedureSupported);
		result.append(", packageSupported: "); //$NON-NLS-1$
		result.append(packageSupported);
		result.append(", authorizationIdentifierSupported: "); //$NON-NLS-1$
		result.append(authorizationIdentifierSupported);
		result.append(", roleSupported: "); //$NON-NLS-1$
		result.append(roleSupported);
		result.append(", groupSupported: "); //$NON-NLS-1$
		result.append(groupSupported);
		result.append(", userSupported: "); //$NON-NLS-1$
		result.append(userSupported);
		result.append(", roleAuthorizationSupported: "); //$NON-NLS-1$
		result.append(roleAuthorizationSupported);
		result.append(", constructedDataTypeSupported: "); //$NON-NLS-1$
		result.append(constructedDataTypeSupported);
		result.append(", uDFSupported: "); //$NON-NLS-1$
		result.append(uDFSupported);
		result.append(')');
		return result.toString();
	}

} //DatabaseVendorDefinitionImpl

/*******************************************************************************
 * Copyright (c) 2003, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.ddl;

import org.eclipse.osgi.util.NLS;

 /**
 * @author chetabha
 *
 */
 
public final class DdlGenerationMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.datatools.enablement.ibm.ddl.DdlGenerationMessages";//$NON-NLS-1$

	private DdlGenerationMessages() {
		// Do not instantiate
	}

	public static String GENERATE_FULLY_QUALIFIED_NAME;
	public static String GENERATE_FULLY_QUALIFIED_NAME_DES;
	public static String GENERATE_FULLY_QUALIFIED_NAME_LABEL;
	public static String GENERATE_QUOTED_IDENTIFIER;
	public static String GENERATE_QUOTED_IDENTIFIER_DES;
	public static String GENERATE_QUOTED_IDENTIFIER_LABEL;
	public static String GENERATE_DROP_STATEMENTS;
	public static String GENERATE_DROP_STATEMENTS_DES;
	public static String GENERATE_DROP_STATEMENTS_LABEL;
	public static String GENERATE_CREATE_STATEMENTS;
	public static String GENERATE_CREATE_STATEMENTS_DES;
	public static String GENERATE_CREATE_STATEMENTS_LABEL;
	public static String GENERATE_COMMENTS;
	public static String GENERATE_COMMENTS_DES;
	public static String GENERATE_COMMENTS_LABEL;
	public static String GENERATE_LABELS;
	public static String GENERATE_LABELS_DES;
	public static String GENERATE_LABELS_LABEL;
	public static String GENERATE_IN_TABLESPACE_CLAUSE;
	public static String GENERATE_IN_TABLESPACE_CLAUSE_DES;
	public static String GENERATE_IN_TABLESPACE_CLAUSE_LABEL;
	public static String GENERATE_ON_FILEGROUP_CLAUSE;
	public static String GENERATE_ON_FILEGROUP_CLAUSE_DES;
	public static String GENERATE_ON_FILEGROUP_CLAUSE_LABEL;
	public static String GENERATE_USE_DOMAIN_IF_EXIST;
	public static String GENERATE_USE_DOMAIN_IF_EXIST_DES;
	public static String GENERATE_USE_DOMAIN_IF_EXIST_LABEL;
	public static String GENERATE_CREATE_OR_REPLACE;
	public static String GENERATE_CREATE_OR_REPLACE_DES;
	public static String GENERATE_CREATE_OR_REPLACE_LABEL;
	public static String GENERATE_TABLES;
	public static String GENERATE_TABLES_DES;
	public static String GENERATE_TABLES_LABEL;
	public static String GENERATE_TABLESPACES;
	public static String GENERATE_TABLESPACES_DES;
	public static String GENERATE_TABLESPACES_LABEL;
	//FILEGROUPS is the same as DB2 tablespace concept for MS SQL Server.
	public static String GENERATE_FILEGROUPS;
	public static String GENERATE_FILEGROUPS_DES;
	public static String GENERATE_FILEGROUPS_LABEL;
	public static String GENERATE_DOMAIN;
	public static String GENERATE_DOMAIN_TYPE_DES;
	public static String GENERATE_DOMAIN_TYPE_LABEL;
	public static String GENERATE_INDEX;
	public static String GENERATE_INDEX_DES;
	public static String GENERATE_INDEX_LABEL;
	public static String GENERATE_STOREDPROCEDURE;
	public static String GENERATE_STOREDPROCEDURE_DES;
	public static String GENERATE_STOREDPROCEDURE_LABEL;
	public static String GENERATE_FUNCTION;
	public static String GENERATE_FUNCTION_DES;
	public static String GENERATE_FUNCTION_LABEL;
	public static String GENERATE_VIEW;
	public static String GENERATE_VIEW_DES;
	public static String GENERATE_VIEW_LABEL;
	public static String GENERATE_TRIGGER;
	public static String GENERATE_TRIGGER_DES;
	public static String GENERATE_TRIGGER_LABEL;
	public static String GENERATE_PK_CONSTRAINTS;
	public static String GENERATE_PK_CONSTRAINTS_DES;
	public static String GENERATE_PK_CONSTRAINTS_LABEL;
	public static String GENERATE_FK_CONSTRAINTS;
	public static String GENERATE_FK_CONSTRAINTS_DES;
	public static String GENERATE_FK_CONSTRAINTS_LABEL;
	public static String GENERATE_CK_CONSTRAINTS;
	public static String GENERATE_CK_CONSTRAINTS_DES;
	public static String GENERATE_CK_CONSTRAINTS_LABEL;
	public static String GENERATE_SEQUENCE;
	public static String GENERATE_SEQUENCE_DES;
	public static String GENERATE_SEQUENCE_LABEL;
	public static String GENERATE_ALIAS;
	public static String GENERATE_ALIAS_DES;
	public static String GENERATE_ALIAS_LABEL;
	public static String GENERATE_SYNONYM;
	public static String GENERATE_SYNONYM_DES;
	public static String GENERATE_SYNONYM_LABEL;
	public static String GENERATE_MQT;
	public static String GENERATE_MQT_DES;
	public static String GENERATE_MQT_LABEL;
	public static String GENERATE_BUFFERPOOL;
	public static String GENERATE_BUFFERPOOL_DES;
	public static String GENERATE_BUFFERPOOL_LABEL;
	public static String GENERATE_DATABASE;
	public static String GENERATE_DATABASE_DES;
	public static String GENERATE_DATABASE_LABEL;
	public static String GENERATE_SCHEMA;
	public static String GENERATE_SCHEMA_DES;
	public static String GENERATE_SCHEMA_LABEL;
	public static String GENERATE_PARTITIONGROUP;
	public static String GENERATE_PARTITIONGROUP_DES;
	public static String GENERATE_PARTITIONGROUP_LABEL;
	public static String GENERATE_STORAGEGROUP;
	public static String GENERATE_STORAGEGROUP_DES;
	public static String GENERATE_STORAGEGROUP_LABEL;
	public static String GENERATE_USER_DEFINED_TYPE;
	public static String GENERATE_USER_DEFINED_TYPE_DES;
	public static String GENERATE_USER_DEFINED_TYPE_LABEL;
	public static String GENERATE_ENFORCED_CONSTRAINTS;
	public static String GENERATE_ENFORCED_CONSTRAINTS_DES;
	public static String GENERATE_ENFORCED_CONSTRAINTS_LABEL;
	public static String GENERATION_OPTIONS;
	public static String GENERATION_OPTIONS_DES;
	public static String GENERATION_OPTIONS_LABEL;
	public static String ADDITIONAL_ELEMENTS;
	public static String ADDITIONAL_ELEMENTS_DES;
	public static String ADDITIONAL_ELEMENTS_LABEL;
	public static String GENERATE_PRIVILEGE;
	public static String GENERATE_PRIVILEGE_DES;
	public static String GENERATE_PRIVILEGE_LABEL;
	public static String GENERATE_PACKAGE;
	public static String GENERATE_PACKAGE_DES;
	public static String GENERATE_PACKAGE_LABEL;
	public static String GENERATE_PACKAGE_BODY;
	public static String GENERATE_PACKAGE_BODY_DES;
	public static String GENERATE_PACKAGE_BODY_LABEL;
	public static String GENERATE_BACKUP_TABLE;
	public static String GENERATE_BACKUP_TABLE_DES;
	public static String GENERATE_BACKUP_TABLE_LABEL;
	public static String GENERATE_STATISTICS;
	public static String GENERATE_STATISTICS_DES;
	public static String GENERATE_STATISTICS_LABEL;
	public static String GENERATE_ROLE;
	public static String GENERATE_ROLE_DES;
	public static String GENERATE_ROLE_LABEL;
	public static String GENERATE_USER;
	public static String GENERATE_USER_DES;
	public static String GENERATE_USER_LABEL;
	public static String GENERATE_GROUP;
	public static String GENERATE_GROUP_DES;
	public static String GENERATE_GROUP_LABEL;
	public static String CHECK_MODEL;
	public static String CHECK_MODEL_DES;
	public static String CHECK_MODEL_LABEL;

	public static String GENERATE_MODULE;
	public static String GENERATE_MODULE_DES;
	public static String GENERATE_MODULE_LABEL;
	public static String GENERATE_MODULE_CONDITION;
	public static String GENERATE_MODULE_CONDITION_DES;
	public static String GENERATE_MODULE_CONDITION_LABEL;
	public static String GENERATE_GLOBAL_VARIABLE;
	public static String GENERATE_GLOBAL_VARIABLE_DES;
	public static String GENERATE_GLOBAL_VARIABLE_LABEL;
	public static String GENERATE_HISTORY_TABLE;
	public static String GENERATE_HISTORY_TABLE_DES;
	public static String GENERATE_HISTORY_TABLE_LABEL;
	public static String GENERATE_TEMPORARY_TABLE;
	public static String GENERATE_TEMPORARY_TABLE_DES;
	public static String GENERATE_TEMPORARY_TABLE_LABEL;
	public static String GENERATE_XMLSCHEMA;
	public static String GENERATE_XMLSCHEMA_DES;
	public static String GENERATE_XMLSCHEMA_LABEL;
	public static String GENERATE_SECURITY_POLICY;
	public static String GENERATE_SECURITY_POLICY_DES;
	public static String GENERATE_SECURITY_POLICY_LABEL;
	
	public static String LOADING_PRIVILEGES_PROGRESS;
	public static String GRANT_NOT_ALLOWED;
	public static String REVOKE_NOT_ALLOWED;
	
	public static String LOADING_SUBTASK;

	public static String DROP_RESTRICT;
	public static String DROP_RESTRICT_DES;
	public static String DROP_RESTRICT_LABEL;
	public static String GENERATE_ROW_PERMISSIONS;
	public static String GENERATE_ROW_PERMISSIONS_DES;
	public static String GENERATE_ROW_PERMISSIONS_LABEL;
	public static String GENERATE_COLUMN_MASKS;
	public static String GENERATE_COLUMN_MASKS_DES;
	public static String GENERATE_COLUMN_MASKS_LABEL;
	public static String FE_INVALID_MODEL;

	static {
		NLS.initializeMessages(BUNDLE_NAME, DdlGenerationMessages.class);
	}
}

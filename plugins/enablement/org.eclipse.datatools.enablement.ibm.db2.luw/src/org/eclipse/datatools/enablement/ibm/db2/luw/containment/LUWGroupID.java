/*******************************************************************************
 * Copyright (c) 2004, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.db2.luw.containment;

public interface LUWGroupID {
	public static final String FEDERATED_SERVER = "core.db2.luw.LUWServer";  //$NON-NLS-1$
	public static final String NICKNAME = "core.db2.luw.LUWNickname";  //$NON-NLS-1$
	public static final String BUFFER_POOL = "core.db2.luw.LUWBufferPool";  //$NON-NLS-1$
	public static final String PARTITION = "core.db2.luw.LUWDatabasePartition";  //$NON-NLS-1$
	public static final String PARTITION_GROUP = "core.db2.luw.LUWPartitionGroup";  //$NON-NLS-1$
	public static final String TABLESPACE = "core.db2.luw.LUWTableSpace";  //$NON-NLS-1$
	public static final String FEDERATED_STORED_PROCEDURES = "core.db2.luw.LUWFederatedSP";  //$NON-NLS-1$
	public static final String GLOBAL_VARIABLE = "core.db2.luw.LUWGlobalVariable";  //$NON-NLS-1$
	public static final String MODULE = "core.db2.luw.LUWModule";  //$NON-NLS-1$
	public static final String MODULE_OBJECT = "core.db2.luw.LUWModuleObject";  //$NON-NLS-1$
	public static final String MODULE_VARIABLE = "core.db2.luw.LUWModuleVariable";  //$NON-NLS-1$
	public static final String MODULE_CONDITION = "core.db2.luw.LUWModuleCondition";  //$NON-NLS-1$
	public static final String MODULE_TYPE = "core.db2.luw.LUWModuleType";  //$NON-NLS-1$
	public static final String MODULE_FUNCTION = "core.db2.luw.LUWModuleFunction";  //$NON-NLS-1$
	public static final String MODULE_PROCEDURE = "core.db2.luw.LUWModuleProcedure";  //$NON-NLS-1$
	public static final String PLSQL_PACKAGE = "core.db2.luw.PlsqlPackage";  //$NON-NLS-1$
	public static final String WRAPPER = "core.db2.luw.LUWWrapper"; //$NON-NLS-1$
	public static final String USER_MAPPING = "core.db2.luw.LUWUserMapping"; //$NON-NLS-1$
	public static final String FEDERATED_DATABASE_OBJECT = "core.db2.luw.LUWFederatedDatabaseObject"; //$NON-NLS-1$
	public static final String SECURITY_POLICY = "core.db2.luw.LUWSecurityPolicy"; //$NON-NLS-1$
	public static final String SECURITY_LABEL_COMPONENT = "core.db2.luw.LUWSecurityLabelComponent"; //$NON-NLS-1$
	public static final String SECURITY_LABEL_COMPONENT_ELEMENT = "core.db2.luw.LUWSecurityLabelComponentElement"; //$NON-NLS-1$
	public static final String SECURITY_LABEL = "core.db2.luw.LUWSecurityLabel"; //$NON-NLS-1$
	public static final String STORAGE_GROUP = "core.db2.luw.LUWStorageGroup";  //$NON-NLS-1$
}

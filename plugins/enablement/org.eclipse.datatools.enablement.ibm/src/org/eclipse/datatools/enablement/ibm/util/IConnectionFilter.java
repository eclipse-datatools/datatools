/*******************************************************************************
 * Copyright (c) 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ibm.util;

import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionFilter;

public interface IConnectionFilter extends ConnectionFilter{

	public static final String PROPS_FILTER_TAG = "[Props]";
	
	public static final String DATABASE_INSTANCE_FILTER = "DatatoolsDatabaseInstanceFilterPredicate"; //$NON-NLS-1$
	public static final String TABLESPACE_FILTER = "DatatoolsTablespaceFilterPredicate"; //$NON-NLS-1$
	public static final String FEDERATED_STORED_PROCEDURE_FILTER = "DatatoolsFSPFilterPredicate"; //$NON-NLS-1$
	public static final String SYNONYM_FILTER = "DatatoolsSynonymFilterPredicate"; //$NON-NLS-1$
	public static final String PACKAGE_FILTER = "DatatoolsPackageFilterPredicate"; //$NON-NLS-1$
	public static final String MODULE_FILTER = "DatatoolsModuleFilterPredicate"; //$NON-NLS-1$
	public static final String PLSQL_PACKAGE_FILTER = "DatatoolsPlsqlPacakgeFilterPredicate"; //$NON-NLS-1$
	public static final String TRIGGER_FILTER = "DatatoolsTriggerFilterPredicate"; //$NON-NLS-1$
	public static final String INDEX_FILTER = "DatatoolsIndexFilterPredicate"; //$NON-NLS-1$
	public static final String CONSTRAINT_FILTER = "DatatoolsConstraintFilterPredicate"; //$NON-NLS-1$
	
	
	public static final String TABLES_REQUIRED_LIST = "DatatoolsTablesRequiredList"; //$NON-NLS-1$
	public static final String VIEWS_REQUIRED_LIST = "DatatoolsViewsRequiredList"; //$NON-NLS-1$
	public static final String MQTS_REQUIRED_LIST = "DatatoolsMQTsRequiredList"; //$NON-NLS-1$
	public static final String ALIASES_REQUIRED_LIST = "DatatoolsAliasesRequiredList"; //$NON-NLS-1$
	public static final String TRIGGERS_REQUIRED_LIST = "DatatoolsTriggersRequiredList"; //$NON-NLS-1$
	public static final String STOREDPROCS_REQUIRED_LIST = "DatatoolsStoredProcsRequiredList"; //$NON-NLS-1$
	public static final String UDFS_REQUIRED_LIST = "DatatoolsUDFsRequiredList"; //$NON-NLS-1$
	public static final String UDTS_REQUIRED_LIST = "DatatoolsUDTsRequiredList"; //$NON-NLS-1$
	public static final String PACKAGES_REQUIRED_LIST = "DatatoolsPackagesRequiredList"; //$NON-NLS-1$
	public static final String SEQUENCE_REQUIRED_LIST = "DatatoolsSequencesRequiredList"; //$NON-NLS-1$
	public static final String SYNONYM_REQUIRED_LIST = "DatatoolsSynonymRequiredList"; //$NON-NLS-1$
	public static final String SCHEMA_REQUIRED_LIST = "DatatoolsSchemaRequiredList"; //$NON-NLS-1$
	public static final String INDEX_REQUIRED_LIST = "DatatoolsIndexRequiredList"; //$NON-NLS-1$
	public static final String CONSTRAINT_REQUIRED_LIST = "DatatoolsConstraintRequiredList"; //$NON-NLS-1$
	public static final String TABLESPACE_REQUIRED_LIST = "DatatoolsTableSpaceRequiredList"; //$NON-NLS-1$
	public static final String XMLSCHEMA_REQUIRED_LIST = "DatatoolsXMLSchemaRequiredList"; //$NON-NLS-1$
}

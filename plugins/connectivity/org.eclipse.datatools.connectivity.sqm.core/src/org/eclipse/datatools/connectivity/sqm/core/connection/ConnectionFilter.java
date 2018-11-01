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

package org.eclipse.datatools.connectivity.sqm.core.connection;

import java.io.Serializable;
import java.util.ArrayList;

import org.eclipse.datatools.connectivity.sqm.internal.core.connection.Predicate;

/**
 * Public ConnectionFilter interface, extended internally
 */
public interface ConnectionFilter extends Serializable{
	
	/**
	 * Return the filter expression.
	 */
	public String getPredicate();
	
	/**
	 * Return all the predicates
	 */
	public ArrayList getPredicatesCollection();
	
	/**
	 * Return if the filter expression is valid
	 */
	public boolean isValid(String predicate);
	
	/**
	 * Return only the pattern part of the filter expression
	 */
	public String getPattern();
		
	/**
	 * Return the elements of only the pattern part of the filter expression
	 */
	public String[] getPatternElements();

	/**
	 * Return whether or not the operator equals "IN"
	 */
	public boolean isOperatorInclusive();
	
	/**
	 * Return whether or not the operator equals "NOT IN"
	 */
	public boolean isOperatorExclusive();
	 
	/**
	 * Return only the operator part of the filter expression
	 */
	public String getOperator();
	
	/**
	 * Returns if the conditions are ORed or ANDed
	 */
	public boolean isMeetsAllConditions();
	
	/**
	 * @param name the name of the object to check
	 * 
	 * @return true if the object should be filtered
	 */
	public boolean isFiltered(String name);

	public static final String OPERATOR_LIKE = "LIKE"; //$NON-NLS-1$
	public static final String OPERATOR_NOT_LIKE = "NOT LIKE"; //$NON-NLS-1$
	public static final String OPERATOR_IN = "IN"; //$NON-NLS-1$
	public static final String OPERATOR_NOT_IN = "NOT IN"; //$NON-NLS-1$

	/**
	 * The profile extension ID used to store filter settings.
	 */
	public static final String FILTER_SETTINGS_PROFILE_EXTENSION_ID = "org.eclipse.datatools.connectivity.sqm.filterSettings"; //$NON-NLS-1$

	public static final String FILTER_SEPARATOR = "::"; //$NON-NLS-1$

	public static final String CATALOG_FILTER = "DatatoolsCatalogFilterPredicate"; //$NON-NLS-1$
	public static final String SCHEMA_FILTER = "DatatoolsSchemaFilterPredicate"; //$NON-NLS-1$
	public static final String TABLE_FILTER = "DatatoolsTableFilterPredicate"; //$NON-NLS-1$
	public static final String VIEW_FILTER = "DatatoolsViewFilterPredicate"; //$NON-NLS-1$
	public static final String ALIAS_FILTER = "DatatoolsAliasFilterPredicate"; //$NON-NLS-1$
	public static final String STORED_PROCEDURE_FILTER = "DatatoolsSPFilterPredicate"; //$NON-NLS-1$
	public static final String JAR_FILTER = "DatatoolsJarFilterPredicate"; //$NON-NLS-1$	
	public static final String USER_DEFINED_FUNCTION_FILTER = "DatatoolsUDFFilterPredicate"; //$NON-NLS-1$
	public static final String SEQUENCE_FILTER = "DatatoolsSequenceFilterPredicate"; //$NON-NLS-1$
	public static final String USER_DEFINED_TYPE_FILTER = "DatatoolsUDTFilterPredicate"; //$NON-NLS-1$
	public static final String NICKNAME_FILTER = "DatatoolsNicknameFilterPredicate"; //$NON-NLS-1$
	public static final String MQT_FILTER = "DatatoolsMQTFilterPredicate"; //$NON-NLS-1$
	public static final String REMOTE_SERVER_FILTER = "DatatoolsRemoteServerFilterPredicate"; //$NON-NLS-1$
	public static final String REMOTE_SCHEMA_FILTER = "DatatoolsRemoteSchemaFilterPredicate"; //$NON-NLS-1$
	public static final String REMOTE_TABLE_FILTER = "DatatoolsRemoteTableFilterPredicate"; //$NON-NLS-1$
	public static final String DISCOVERED_SERVER_FILTER = "DatatoolsDiscoveredServerFilterPredicate"; //$NON-NLS-1$
	public static final String XSR_OBJECTS_FILTER = "DatatoolsXMLSchemasFilterPredicate"; //$NON-NLS-1$
	public static final String XML_SCHEMA_DOC_FILTER = "DatatoolsXMLSchemaDocsFilterPredicate"; //$NON-NLS-1$
	public static final String REMOTE_STORED_PROCEDURE_FILTER = "DatatoolsRemoteStoredProcedureFilterPredicate"; //$NON-NLS-1$
	public static final String TABLESPACE_FILTER = "DatatoolsTablespaceFilterPredicate"; //$NON-NLS-1$
	public static final String DATABASE_FILTER = "DatatoolsDatabaseFilterPredicate"; //$NON-NLS-1$
	public static final String SYNONYM_FILTER = "DatatoolsSynonymFilterPredicate"; //$NON-NLS-1$
}

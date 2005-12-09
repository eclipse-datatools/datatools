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
package org.eclipse.datatools.connectivity.sqm.internal.core;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.datatools.connectivity.sqm.internal.core.containment.ContainmentService;
import org.eclipse.datatools.connectivity.sqm.internal.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinitionRegistry;
import org.eclipse.datatools.connectivity.sqm.internal.core.definition.DatabaseDefinitionRegistryImpl;
import org.eclipse.datatools.connectivity.sqm.internal.core.util.RDBCorePluginConstants;


public class RDBCorePlugin extends Plugin {
	public static String FK_MODELING_RELATIONSHIP = "FK_MODELING_RELATIONSHIP"; //$NON-NLS-1$
	public static String FK_PARENT_ROLE_NAME = "FK_PARENT_ROLE_NAME"; //$NON-NLS-1$
	public static String FK_CHILD_ROLE_NAME = "FK_CHILD_ROLE_NAME"; //$NON-NLS-1$
	public static String FK_PARENT_MULTIPLICITY = "FK_PARENT_MULTIPLICITY"; //$NON-NLS-1$
	public static String FK_CHILD_MULTIPLICITY = "FK_CHILD_MULTIPLICITY"; //$NON-NLS-1$
	public static String FK_PARENT_BY_QUALIFIED_NAME = "FK_PARENT_BY_QUALIFIED_NAME"; //$NON-NLS-1$
	public static String FK_IS_IDENTIFYING_RELATIONSHIP = "FK_IS_IDENTIFYING_RELATIONSHIP"; //$NON-NLS-1$
	public static String ZERO = "0"; //$NON-NLS-1$
	public static String ZERO_TO_ONE = "0..1"; //$NON-NLS-1$
	public static String ONE = "1"; //$NON-NLS-1$
	public static String ZERO_TO_MANY = "0..*"; //$NON-NLS-1$
	public static String ONE_TO_MANY = "1..*"; //$NON-NLS-1$
	public static String MANY = "*"; //$NON-NLS-1$
	
	private static RDBCorePlugin plugin;

	public RDBCorePlugin() {
		plugin = this;
	}
	
	public static RDBCorePlugin getDefault() {
		return plugin;
	}

	public DatabaseDefinitionRegistry getDatabaseDefinitionRegistry() {
		return DatabaseDefinitionRegistryImpl.INSTANCE;
	}
	
	public ContainmentService getContainmentService() {
		return ContainmentServiceImpl.INSTANCE;
	}

//	public ConnectionManager getConnectionManager() {
//		return ConnectionManagerImpl.INSTANCE;
//	}	
	
	protected void initializeDefaultPluginPreferences() {
	    getPluginPreferences().setDefault(RDBCorePluginConstants.LIMIT_ROWS_RETRIEVED, true);
	    getPluginPreferences().setDefault(RDBCorePluginConstants.MAX_ROW_RETRIEVED, 50);
	    getPluginPreferences().setDefault(RDBCorePluginConstants.MAX_LOB_LENGTH, 100);  
	}
}

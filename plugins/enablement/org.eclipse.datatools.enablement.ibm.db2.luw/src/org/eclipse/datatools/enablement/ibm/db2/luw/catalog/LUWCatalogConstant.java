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
package org.eclipse.datatools.enablement.ibm.db2.luw.catalog;

public class LUWCatalogConstant {

	//Database
	public static final String PRIVILEGE_BINADD = "BINDADD"; //$NON-NLS-1$
	public static final String PRIVILEGE_CONNECT = "CONNECT"; //$NON-NLS-1$
	public static final String PRIVILEGE_CREATETAB = "CREATETAB"; //$NON-NLS-1$
	public static final String PRIVILEGE_CREATE_EXTERNAL_ROUTINE = "CREATE_EXTERNAL_ROUTINE"; //$NON-NLS-1$
	public static final String PRIVILEGE_CREATE_NOT_FENCED_ROUTINE = "CREATE_NOT_FENCED_ROUTINE"; //$NON-NLS-1$
	public static final String PRIVILEGE_IMPLICIT_SCHEMA = "IMPLICIT_SCHEMA"; //$NON-NLS-1$
	public static final String PRIVILEGE_DBADM = "DBADM"; //$NON-NLS-1$
	public static final String PRIVILEGE_LOAD = "LOAD"; //$NON-NLS-1$
	public static final String PRIVILEGE_QUIESCE_CONNECT = "QUIESCE_CONNECT"; //$NON-NLS-1$
	public static final String PRIVILEGE_SECADM = "SECADM"; //$NON-NLS-1$
	
	//schema
	public static final String PRIVILEGE_ALTERIN = "ALTERIN"; //$NON-NLS-1$
	public static final String PRIVILEGE_CREATEIN = "CREATEIN"; //$NON-NLS-1$
	public static final String PRIVILEGE_DROPIN = "DROPIN"; //$NON-NLS-1$

	//Table
	public static final String PRIVILEGE_ALTER = "ALTER"; //$NON-NLS-1$
	public static final String PRIVILEGE_CONTROL = "CONTROL"; //$NON-NLS-1$
	public static final String PRIVILEGE_DELETE = "DELETE"; //$NON-NLS-1$
	public static final String PRIVILEGE_INDEX = "INDEX"; //$NON-NLS-1$
	public static final String PRIVILEGE_INSERT = "INSERT"; //$NON-NLS-1$
	public static final String PRIVILEGE_REFERENCES = "REFERENCES"; //$NON-NLS-1$
	public static final String PRIVILEGE_SELECT = "SELECT"; //$NON-NLS-1$
	public static final String PRIVILEGE_UPDATE = "UPDATE"; //$NON-NLS-1$

	//Routine, module/plsqlPackage
	public static final String PRIVILEGE_EXECUTE = "EXECUTE"; //$NON-NLS-1$

	//SEQUENCE
	public static final String PRIVILEGE_USAGE = "USAGE"; //$NON-NLS-1$
	
	//TABLESPACE
	public static final String PRIVILEGE_USE = "USE"; //$NON-NLS-1$

	//PACKAGFE
	public static final String PRIVILEGE_BIND = "BIND"; //$NON-NLS-1$

	//Global Variable
	public static final String PRIVILEGE_READ = "READ"; //$NON-NLS-1$
	public static final String PRIVILEGE_WRITE = "WRITE"; //$NON-NLS-1$
}

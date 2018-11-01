/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.model;

public interface SqlXmlConstant {

    public static final String URL = "url"; //$NON-NLS-1$
    public static final String DRIVER = "driver"; //$NON-NLS-1$
    public static final String USERID = "userid"; //$NON-NLS-1$
    public static final String PASSWORD = "password"; //$NON-NLS-1$

    public static final String INSERT_STATEMENT = "InsertStatement"; //$NON-NLS-1$
    public static final String UPDATE_STATEMENT = "UpdateStatement"; //$NON-NLS-1$
    public static final String DELETE_STATEMENT = "DeleteStatement"; //$NON-NLS-1$
    public static final String SELECT_STATEMENT = "SelectStatement"; //$NON-NLS-1$
    public static final String FULLSELECT_STATEMENT = "FullselectStatement"; //$NON-NLS-1$
    public static final String WITH_STATEMENT = "WithStatement"; //$NON-NLS-1$

    public static final String QUERY = "Query"; //$NON-NLS-1$
    public static final String SCHEMA = "Schema"; //$NON-NLS-1$

    public static final String DBRESOURCE = "DatabaseResource"; //$NON-NLS-1$
}


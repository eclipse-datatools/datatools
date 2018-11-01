/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.sybase.ddl;

public interface ISybaseDdlConstants
{
    public final static String IN                  = "IN";                 //$NON-NLS-1$
    public final static String OUT                 = "OUT";                //$NON-NLS-1$
    public final static String INOUT               = "INOUT";              //$NON-NLS-1$
    public final static String OUTPUT              = "OUTPUT";             //$NON-NLS-1$
//    public final static String RETURNS             = "RETURNS";            //$NON-NLS-1$
    public final static String FROM                = "FROM";               //$NON-NLS-1$
    public final static String COMMENT             = "COMMENT";            //$NON-NLS-1$
    public final static String IS                  = "IS";                 //$NON-NLS-1$
    public final static String COLUMN              = "COLUMN";             //$NON-NLS-1$
    public final static String EVENT               = "EVENT";
    public final static String ASC                 = "ASC";                //$NON-NLS-1$
    public final static String DESC                = "DESC";               //$NON-NLS-1$
    public final static String EQUAL               = "=";                   //$NON-NLS-1$
    public final static String CLUSTERED           = "CLUSTERED";          //$NON-NLS-1$
    public final static String NONCLUSTERED        = "NONCLUSTERED";       //$NON-NLS-1$
    public final static String AT                  = "AT";
    public final static String FILE                = "FILE";
    public final static String REMOVE              = "REMOVE";
    public final static String CHECKOPTION         = "WITH CHECK OPTION";
    public final static String CAST                = "CAST";               //$NON-NLS-1$
    public final static String LOCATOR             = "LOCATOR";            //$NON-NLS-1$
    public final static String EXTERNAL            = "EXTERNAL";           //$NON-NLS-1$
    public final static String COMMIT              = "COMMIT";              //$NON-NLS-1$
    public static final String BEGIN                = "BEGIN";             //$NON-NLS-1$
    public static final String END                  = "END";               //$NON-NLS-1$
    public static final String THEN                 = "THEN";              //$NON-NLS-1$
    public static final String END_IF               = "END IF";            //$NON-NLS-1$
    
    public final static String WITH_RECOMPILE      = "WITH RECOMPILE";     //$NON-NLS-1$
//    public final static String DETERMINISTIC       = "DETERMINISTIC";      //$NON-NLS-1$ 
//    public final static String LANGUAGE            = "LAUNGUAGE";          //$NON-NLS-1$
    public final static String EXTERNAL_NAME       = "EXTERNAL NAME";      //$NON-NLS-1$
//    public final static String PARAMETER_STYLE     = "PARAMETER STYLE";    //$NON-NLS-1$
    public final static String JAVA                = "JAVA";               //$NON-NLS-1$
//    public final static String DYNAMIC_RESULT_SETS = "DYNAMIC RESULT SETS"; //$NON-NLS-1$
    public final static String DATABASE            = "DATABASE"; //$NON-NLS-1$
//    public final static String TEMPORARY           = "TEMPORARY";           //$NON-NLS-1$
    public final static String MESSAGE             = "MESSAGE";             //$NON-NLS-1$    
    public final static String LOG                 = "LOG";                //$NON-NLS-1$    
    
    // For privilege SQL statement
    public final static String GRANT                = "GRANT";
    public final static String SELECT_PRIVILEGE     = "SELECT";
    public final static String INSERT_PRIVILEGE     = "INSERT";
    public final static String UPDATE_PRIVILEGE     = "UPDATE";
    public final static String DELETE_PRIVILEGE     = "DELETE";
    public final static String ALTER_PRIVILEGE      = "ALTER";
    public final static String REFERENCES_PRIVILEGE = "REFERENCES";
    public final static String REFERENCE_PRIVILEGE  = "REFERENCE";
    public final static String EXEC_PRIVILEGE       = "EXECUTE";
    public final static String TO                   = "TO";
    public final static String WITH_GRANT_OPTION    = "WITH GRANT OPTION";
    public final static String REVOKE               = "REVOKE";
    public final static String GO_FOR               = "GRANT OPTION FOR";
    
    public final static String GO                   = "GO";
    public final static String SEMICOLUMN           = ";";
    public final static String SP_RENAME            = "sp_rename";          //$NON-NLS-1$    
    public final static String RENAME               = "RENAME";             //$NON-NLS-1$
    
    public final static String IF                   = "IF";                 //$NON-NLS-1$
    public final static String EXISTS                   = "EXISTS";                 //$NON-NLS-1$
    
}

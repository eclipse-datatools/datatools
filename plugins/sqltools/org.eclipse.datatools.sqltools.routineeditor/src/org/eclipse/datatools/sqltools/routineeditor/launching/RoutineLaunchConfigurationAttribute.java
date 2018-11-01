/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.routineeditor.launching;

/**
 * Currently, we can have the following combinations.
 * 
 * 1. External ROUTINE_LAUNCH_PROFILENAME ROUTINE_LAUNCH_EXTERNALID
 * 
 * 2. SP/UDF ROUTINE_LAUNCH_PROFILENAME ROUTINE_LAUNCH_TYPE 0 ROUTINE_LAUNCH_PROCID ROUTINE_LAUNCH_PARAMETERS
 * 
 * 3. Event ROUTINE_LAUNCH_PROFILENAME ROUTINE_LAUNCH_TYPE 0 ROUTINE_LAUNCH_PROCID ROUTINE_LAUNCH_EVENTPARAM
 * 
 * 4. Adhoc SQL ROUTINE_LAUNCH_PROFILENAME ROUTINE_LAUNCH_TYPE 3 ROUTINE_LAUNCH_ADHOCSQL
 * 
 * 5. Trigger ROUTINE_LAUNCH_PROFILENAME ROUTINE_LAUNCH_TYPE 0 ROUTINE_LAUNCH_SQL
 * 
 * @author Samir Nigam
 * @author Yang Liu
 */
public interface RoutineLaunchConfigurationAttribute
{
    static final String ROUTINE_LAUNCH_CONFIGURATION_TYPE          = "org.eclipse.datatools.sqltools.routineeditor.launching.launchConfigurationType";

    // connection profile
    static final String ROUTINE_LAUNCH_PROFILENAME                 = "org.eclipse.datatools.sqltools.debug.launching.profileName";

    // database name
    static final String ROUTINE_LAUNCH_DATABASENAME                = "org.eclipse.datatools.sqltools.debug.launching.databasename";
    // external
    /**
     * when is ASA, this is the connId. When ASE, this is spid.
     */
    static final String ROUTINE_LAUNCH_EXTERNALID                  = "org.eclipse.datatools.sqltools.debug.launching.externalID";

    // type
    // 0. for proc (sp/udf/event)
    // 3. for adhoc sql.
    // see DmpConstants
    static final String ROUTINE_LAUNCH_TYPE                        = "org.eclipse.datatools.sqltools.debug.launching.type";

    static final String ROUTINE_LAUNCH_PROCID                      = "org.eclipse.datatools.sqltools.debug.launching.procid";

    static final String ROUTINE_LAUNCH_SQL                  = "org.eclipse.datatools.sqltools.debug.launching.triggersql";

    // parameter list for SP and UDF
    static final String ROUTINE_LAUNCH_PARAMETERS                  = "org.eclipse.datatools.sqltools.debug.launching.parameters";

    // parameter string for event trigger
    static final String ROUTINE_LAUNCH_EVENTPARAMS                 = "org.eclipse.datatools.sqltools.debug.launching.eventparams";

    // the final string that will be send to the server. composed from previous attributes.
    //    static final String ROUTINE_LAUNCH_FINAL = "org.eclipse.datatools.sqltools.debug.launching.final";

    //1: use default values 2: user customized
    static final String ROUTINE_LAUNCH_OPTION_TYPE                 = "org.eclipse.datatools.sqltools.debug.launching.optiontype";

    static final String ROUTINE_LAUNCH_CONFIGURATION_NUM      = "org.eclipse.datatools.sqltools.debug.launching.configuration.number";
    
    static final String ROUTINE_LAUNCH_CONFIGURATION_NAME     = "org.eclipse.datatools.sqltools.debug.launching.configuration";
}

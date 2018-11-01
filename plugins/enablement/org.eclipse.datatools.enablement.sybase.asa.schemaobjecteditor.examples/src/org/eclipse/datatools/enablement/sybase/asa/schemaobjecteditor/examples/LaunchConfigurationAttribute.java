/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples;

/**
 * TODO move vendor specific things out. Hui Cao Currently, we can have the following combinations.
 * 
 * 1. External DMP_LAUNCH_PROFILENAME DMP_LAUNCH_EXTERNALID
 * 
 * 2. SP/UDF DMP_LAUNCH_PROFILENAME DMP_LAUNCH_TYPE 0 DMP_LAUNCH_PROCID DMP_LAUNCH_PARAMETERS
 * 
 * 3. Event DMP_LAUNCH_PROFILENAME DMP_LAUNCH_TYPE 0 DMP_LAUNCH_PROCID DMP_LAUNCH_EVENTPARAM
 * 
 * 4. Adhoc SQL DMP_LAUNCH_PROFILENAME DMP_LAUNCH_TYPE 3 DMP_LAUNCH_ADHOCSQL
 * 
 * 5. Trigger DMP_LAUNCH_PROFILENAME DMP_LAUNCH_TYPE 0 DMP_LAUNCH_TRIGGERSQL
 * 
 * @author Samir Nigam
 * @author Yang Liu
 */
public interface LaunchConfigurationAttribute
{

    // for ASA connection level options
    static final String DMP_LAUNCH_ASA_CHAINED            = "com.sybase.stf.dmp.debug.launching.asa.chained";
    static final String DMP_LAUNCH_ASA_QUOTED_IDEN        = "com.sybase.stf.dmp.debug.launching.asa.quotediden";

}

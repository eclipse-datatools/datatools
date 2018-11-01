/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.pages.general;

import org.eclipse.osgi.util.NLS;

/**
 * 
 * @author Hui Cao
 * 
 */
public class Messages extends NLS
{
    private static final String BUNDLE_NAME = Messages.class.getPackage().getName() + ".messages"; //$NON-NLS-1$

    
    
    private Messages()
    {
    }

    public static String ProceduralObjectGeneralPage_page_name;
    public static String RoutineGeneralPage_add;
    public static String RoutineGeneralPage_delete;
    public static String RoutineGeneralPage_delete_all;
    public static String RoutineGeneralPage_down;
    public static String RoutineGeneralPage_parameters;
    public static String RoutineGeneralPage_parameter_update_error;
    public static String RoutineGeneralPage_up;
    public static String GeneralPage_warning;
    public static String GeneralPage_warning_msg;
    public static String TriggerGeneralPage_cannot_get_table;
    public static String TriggerGeneralPage_error;
    public static String TriggerGeneralPage_trigger_event_section;
    
    static
    {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }
    
}

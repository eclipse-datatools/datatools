/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.routineeditor.pages.source;

/**
 * @author Hui Cao
 */
import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
    private static final String BUNDLE_NAME = Messages.class.getPackage().getName() + ".messages"; //$NON-NLS-1$

    private Messages()
    {
    }

    static
    {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    public static String ASAEventGeneralPage_condition_section;
    public static String ASAEventGeneralPage_schedule_section;
    public static String ASAFunctionGeneralPage_deterministic;
    public static String ASAFunctionGeneralPage_on_exception_resume;
    public static String ASAFunctionGeneralPage_return_type;
    public static String ASAProcedureGeneralPage_add;
    public static String ASAProcedureGeneralPage_delete;
    public static String ASAProcedureGeneralPage_delete_all;
    public static String ASAProcedureGeneralPage_down;
    public static String ASAProcedureGeneralPage_on_exception_resume;
    public static String ASAProcedureGeneralPage_result_section;
    public static String ASAProcedureGeneralPage_up;
    public static String ASASourcePage_date_format;
    public static String ASASourcePage_time_format;
    public static String ASATriggerGeneralPage_columns_section;
    public static String ASATriggerGeneralPage_events_section;
    public static String ASATriggerGeneralPage_properties_section;
    public static String ParameterTypeCompositeProvider_param_sqlcode;
    public static String ParameterTypeCompositeProvider_param_sqlstate;
    public static String ParameterTypeCompositeProvider_param_user_defined;
    public static String ParameterTypeCompositeProvider_parameter_types;
    public static String ASADebugPage_page_name;
    
    
}

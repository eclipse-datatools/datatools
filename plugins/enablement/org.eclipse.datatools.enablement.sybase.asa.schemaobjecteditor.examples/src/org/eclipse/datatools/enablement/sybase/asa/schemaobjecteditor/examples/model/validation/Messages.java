/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.model.validation;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
    private static final String BUNDLE_NAME = Messages.class.getPackage().getName() + ".messages"; //$NON-NLS-1$

    private Messages()
    {
    }

    public static String SybaseASABaseColumnValidator_for_column;
    public static String SybaseASABaseColumnValidator_no_column_data_type;
    public static String SybaseASABaseColumnValidator_no_column_name;
    public static String SybaseASABasePrimaryKeyValidator_pk_column_nullable;
    public static String SybaseASABaseUniqueConstraintValidator_datatype_not_support_HG;
    public static String SybaseASABaseUniqueConstraintValidator_no_column;
    public static String SybaseASABaseTableValidator_for_table;
    public static String SybaseASABaseTableValidator_Duplicate_column_name;
    public static String SybaseASABaseTableValidator_No_column_defined;
    public static String SybaseASABaseTableValidator_No_table_name_present;
    public static String SybaseASATableValidator_invalid_pct_option;
    public static String SybaseASABaseEventValidator_for_event;
    public static String SybaseASABaseConstraintValidator_for_constraint;
    public static String Constraint_Duplicate_Name_Error;
    public static String Event_Duplicate_Name_Error;
    public static String Udt_No_Name_Present;
    public static String Udt_for_udt;
    public static String Udt_Duplicate_Name_Error;
    public static String Udt_Constraint_Definition_Error;
    public static String Udt_Constraint_Definition_Info;
    public static String Table_Duplicate_Name_Error;
    public static String Database_Error;
    public static String SybaseRoutineValidator_No_name_present;
    public static String SybaseRoutineValidator_for_routine;
    public static String SybaseTriggerValidator_No_name_present;
    public static String SybaseTriggerValidator_for_trigger;
    public static String Routine_Duplicate_Name_Error;
    public static String Trigger_Duplicate_Name_Error;
    public static String Validator_No_name_present;
    public static String Validator_No_trigger_events;
    public static String Validator_No_trigger_table;
    public static String Validator_duplicate_trigger_events;
    public static String Trigger_event_insert;
    public static String Trigger_event_update;
    public static String Trigger_event_delete;
    public static String Duplicate_Parameter_Name_Error;
    public static String Validator_No_trigger_column;
    public static String SybaseASAResultValidator_no_name;
    public static String SybaseASAResultValidator_no_type;
    public static String SybaseASAResultValidator_invalid_datatype;
    public static String Duplicate_Result_Name_Error;

    public static String IndexValidator_source_index_name;
    public static String IndexValidator_message_no_index_name;
    public static String IndexValidator_message_duplicate_index;
    public static String IndexValidator_source_index_column;
    public static String IndexValidator_message_no_column_select;
    public static String IndexValidator_message_index_name_invalid;
    public static String SybaseParameterValidator_no_name;
    public static String SybaseParameterValidator_no_type;
    public static String SybaseParameterValidator_invalid_datatype;
    public static String SybaseParameterValidator_not_available_datatype;
    public static String SybaseParameterValueOfflineValidator_message_value_not_quoted;
    public static String SybaseParameterValueOfflineValidator_message_value_not_quoted_warning;
    public static String For_constraint;
    public static String Duplicate_constraint_name_error;
    public static String For_index;

    public static String ASASQLDataOfflineValidator_invalid_default_value;
    public static String DatatypeFactory_invalid_type;
    public static String DatatypeFactory_no_type_definition;
    public static String EventCategoryCompositeProvider_condition;
    public static String EventCategoryCompositeProvider_event_type;
    public static String EventCategoryCompositeProvider_manual;
    public static String EventCategoryCompositeProvider_schedule;
    public static String EventPropertyCompositeProvider_disable_handler;
    public static String EventPropertyCompositeProvider_exec_location;
    public static String WTriggerEventsCompositeProvider_delete;
    public static String WTriggerEventsCompositeProvider_event;
    public static String WTriggerEventsCompositeProvider_insert;
    public static String WTriggerEventsCompositeProvider_time_after;
    public static String WTriggerEventsCompositeProvider_time_before;
    public static String WTriggerEventsCompositeProvider_time_resolve;
    public static String WTriggerEventsCompositeProvider_trigger_time;
    public static String WTriggerEventsCompositeProvider_update;
    public static String WTriggerEventsCompositeProvider_update_column;
    public static String WTriggerPropsCompositeProvider_level;
    public static String WTriggerPropsCompositeProvider_new_reference;
    public static String WTriggerPropsCompositeProvider_old_reference;
    public static String WTriggerPropsCompositeProvider_order;
    public static String WTriggerPropsCompositeProvider_referencing_section;
    public static String WTriggerPropsCompositeProvider_remote_reference;
    public static String WTriggerPropsCompositeProvider_row_level;
    public static String WTriggerPropsCompositeProvider_stmt_level;
    static
    {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

}

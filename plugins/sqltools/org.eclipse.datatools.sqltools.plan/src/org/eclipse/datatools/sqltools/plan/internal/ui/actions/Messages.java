/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.plan.internal.ui.actions;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS
{

    private static final String BUNDLE_NAME = "org.eclipse.datatools.sqltools.plan.internal.ui.actions.messages"; //$NON-NLS-1$

    private Messages()
    {
        // Do not instantiate
    }

    public static String PlanDropDownAction_previous_plans;
    public static String PlanDropDownAction_show_history;
    public static String ShowPlansAction_previous_plan;
    public static String ShowPlansAction_show_history;
    public static String RemovePlanAction_remove_plan_name;
    public static String RemovePlanAction_remove_plan_tooltip;
    public static String RemoveAllPlansAction_remove_all_plans_name;
    public static String RemoveAllPlansAction_remove_all_plans_name_tooltip;
    public static String SavePlanAction_saveplan_title;
    public static String SavePlanAction_saveplan_tooltip;
    public static String SavePlanAction_error;
    public static String SavePlanAction_error_info;
    public static String LoadPlanAction_title;
    public static String LoadPlanAction_tooltip;
    public static String LoadPlanAction_info;
    public static String LoadPlanAction_filenotfound;
    public static String LoadPlanAction_unknown_db;
    public static String LoadPlanAction_error;
    public static String LoadPlanAction_errorinfo;
    public static String SavePlanAction_overwrite_q;
    public static String SavePlanAction_question;
    public static String SavePlanAction_builder_error;
    public static String PlanTypeDropDownAction_text;
    public static String PlanTypeDropDownAction_tooltip;

    static
    {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }
}
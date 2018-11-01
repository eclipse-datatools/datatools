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
package org.eclipse.datatools.sqltools.plan.internal;


/**
 * The preference constants
 * @author Dafan Yang
 */
public interface PreferenceConstants
{
    public static final String PLAN_PREFERENCE_PAGE_ID                 = "org.eclipse.datatools.sqltools.plan.executionPlanPage";

    public static final String VERTICAL_LAYOUT_PLAN_VIEW               = PlanConstants.PLUGIN_ID
                                                                               + ".preferences.VerticalLayoutPlanView";
    public static final String HORIZONTAL_LAYOUT_PLAN_VIEW             = PlanConstants.PLUGIN_ID
                                                                               + ".preferences.HorizontalLayoutPlanView";    
    public static final String EXPORT_FORMAT_OTHER_ENCODEING           = PlanConstants.PLUGIN_ID
                                                                               + ".preferences.exportformat.other";
    public static final String EXPORT_FORMAT_OTHER_ENCODEING_SELECTION = PlanConstants.PLUGIN_ID
                                                                               + ".preferences.exportformat.other.selection";
    public static final String EXPORT_FORMAT_PREF_ENCODING             = PlanConstants.PLUGIN_ID
                                                                               + ".preferences.exportformat.pref_encoding";
    public static final String EXPORT_FORMAT_DEFAULT_ENCODEING         = PlanConstants.PLUGIN_ID
                                                                               + ".preferences.exportformat.defalut";
    
}

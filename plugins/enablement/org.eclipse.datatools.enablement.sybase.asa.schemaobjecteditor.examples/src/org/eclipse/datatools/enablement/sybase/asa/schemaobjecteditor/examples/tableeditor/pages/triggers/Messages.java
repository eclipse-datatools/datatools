/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.pages.triggers;

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

    public static String ASATriggersEditorPage_refresh_all;
    public static String ASATriggersEditorPage_triggers;
    public static String TriggersSectionMetaData_event;
    public static String TriggersSectionMetaData_level;
    public static String TriggersSectionMetaData_name;
    public static String TriggersSectionMetaData_trigger_time;
    public static String TriggersSectionMetaData_order;
    public static String TriggersViewerLabelProvider_delete;
    public static String TriggersViewerLabelProvider_insert;
    public static String TriggersViewerLabelProvider_update;
    public static String TriggersViewerLabelProvider_updateof;
}

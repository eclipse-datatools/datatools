/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.udteditor.pages.general;

import org.eclipse.osgi.util.NLS;

/**
 * @author renj
 */
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

    public static String NullIdentitySettings_default_allow_null;
    public static String NullIdentitySettings_allow_null;
    public static String NullIdentitySettings_not_allow_null;
    public static String ASAUDDEditorPage_owner;
    public static String ASAUDDEditorPage_name;
    public static String ASAUDDEditorPage_settings;
    public static String ASAUDDEditorPage_system_datatype;
    public static String ASAUDDEditorPage_length;
    public static String ASAUDDEditorPage_precision;
    public static String ASAUDDEditorPage_scale;
    public static String ASAUDDEditorPage_null_indentity;
    public static String ASAUDDEditorPage_check_constraint;
    public static String ASAUDDEditorPage_default_value;
    public static String ASAUDDEditorPage_general;
}

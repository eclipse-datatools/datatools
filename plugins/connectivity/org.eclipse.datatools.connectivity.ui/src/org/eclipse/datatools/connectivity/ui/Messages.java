/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase on 2008-3-14
 ******************************************************************************/
package org.eclipse.datatools.connectivity.ui;

import org.eclipse.osgi.util.NLS;

/**
 * @author renj
 */
public final class Messages extends NLS 
{

    private static final String BUNDLE_NAME = Messages.class.getPackage().getName()+".messages";	//$NON-NLS-1$

    private Messages() 
    {
        // Do not instantiate
    }

    public static String ConnectionProfileDetailsPage_Autoconnect_finish;
	public static String ConnectionProfileDetailsPage_Autoconnect_startup;
	public static String CPRepositoryContentExtension_CPExtensionName;
	public static String CPWizardSelectionPage_default_filter_text;
	public static String Datatooling_description;
	public static String DriverClassEditDialog_Browse_option_button;
	public static String DriverClassEditDialog_populate_classes_button;
	public static String DriverClassEditDialog_Type_option_button;
	public static String ProfileImageDescriptor_target_profile_id_not_null_msg;
	public static String ProfileImageDescriptor_target_profile_image_not_null;
    
    static 
    {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }
}

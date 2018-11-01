/*
 *************************************************************************
 * Copyright (c) 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.template.ui.nls;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
    private static final String BUNDLE_NAME = "org.eclipse.datatools.connectivity.oda.template.ui.nls.messages"; //$NON-NLS-1$


    public static String driverWizard_windowTitle;

    public static String designWizard_windowTitle;

    public static String baseSection_optLabel_packageName;
    public static String baseSection_optLabel_dataSetName;
    public static String baseSection_optLabel_dataSourceName;
    public static String baseSection_dataSetNameSuffix;
    public static String baseSection_dataSourceNameSuffix;
    public static String baseSection_illegaCharsForIdentifier;
    public static String baseSection_pageDesc;

    public static String designSection_optLabel_driverClass;
    public static String designSection_optLabel_driverPluginId;
    public static String designSection_optLabel_odaDataSetId;
    public static String designSection_optLabel_odaDataSourceId;
    public static String designSection_wizardPageTitle;

    public static String runtimeSection_optLabel_numSetProps;
    public static String runtimeSection_optLabel_numSourceProps;
    public static String runtimeSection_optLabel_odaDataSourceId;
    public static String runtimeSection_wizardPageTitle;

    public static String modeler_odaConnectionFactory;
    public static String modeler_propertyDisplayNamePrefix;
    
    static
    {
        // initialize resource bundle
        NLS.initializeMessages( BUNDLE_NAME, Messages.class );
    }

    private Messages()
    {
    }
}

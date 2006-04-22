/*
 *************************************************************************
 * Copyright (c) 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.design.ui.nls;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
    private static final String BUNDLE_NAME = "org.eclipse.datatools.connectivity.oda.design.ui.nls.messages"; //$NON-NLS-1$

    private Messages()
    {
    }

    static
    {
        // initialize resource bundle
        NLS.initializeMessages( BUNDLE_NAME, Messages.class );
    }

    public static String common_notInDesignSession;
    public static String common_nullArgument;
    public static String common_missingDataSourceDesign;
    public static String common_createClassFailed;
    public static String extension_mustInheritFromODAPage;
    public static String extension_mustInheritFromODAWizard;
    public static String extension_missingManifestElement;
    public static String extension_missingPropertyPage;
    public static String wizard_dataSource_defaultTitle;
    public static String wizard_missingDataSourceId;
    public static String wizard_invalidManifest;
    public static String page_defaultDataSourceTitle;
    public static String designSession_invalidArgument;
    public static String designSession_invalidProfileName;
    public static String designSession_missingDataSetUIElement;
    public static String manifest_missingAttributeValue;
    public static String manifest_missingDataSetElementId;
    public static String manifest_invalidDataSetElementId;
    public static String manifest_dataSetUi_missingPageId;
    public static String manifest_dataSetUi_invalidPageId;
    public static String manifest_dataSetUi_missingElement;
    public static String ui_defaultDataSourceTitle;
    public static String ui_requiredFieldsMissingValue;
    public static String ui_errorLabel;
    public static String ui_errorCreatingCustomCtrls;

}

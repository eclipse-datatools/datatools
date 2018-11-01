/*
 *************************************************************************
 * Copyright (c) 2006, 2013 Actuate Corporation.
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

    public static String adaptableDataSourceProfile_unableAccessProfile;
    public static String common_notInDesignSession;
    public static String common_nullArgument;
    public static String common_missingDataSourceDesign;
    public static String common_createClassFailed;
    public static String dbProfilePage_defaultPageMessage;
    public static String dbProfilePage_editPageMessage;
    public static String dbProfilePage_invalidDataSource;
    public static String dbProfilePage_noCustomPage;
    public static String dbProfilePage_notInDesignSession;
    public static String extension_mustInheritFromODAPage;
    public static String extension_mustInheritFromODAWizard;
    public static String extension_missingManifestElement;
    public static String extension_missingPropertyPage;
    public static String profilePage_browse_noselection_error;
    public static String profilePage_button_browse;
    public static String profilePage_button_browse_tooltip;
    public static String profilePage_button_browse_filter;
    public static String profilePage_button_browse_filter_tooltiptext;
    public static String profilePage_button_relativepath;
    public static String profilePage_button_absolutepath;
    public static String profilePage_button_new;
    public static String profilePage_checkboxLabel_maintainLink;
    public static String profilePage_checkboxLabel_useDefaultName;
    public static String profilePage_checkboxTooltip_maintainLink;
    public static String profilePage_deprecatedProfileDisplayName;
    public static String profilePage_error_emptyName;
    public static String profilePage_error_invalidName;
    public static String profilePage_error_invalidProfileStorePath;
    public static String profilePage_label_dataSourceName;
    public static String profilePage_label_profileStore;
    public static String profilePage_odaTreeName;
    public static String profilePage_pageLabel;
    public static String profilePage_pageTitle;
    public static String profilePage_resourcebaseuri_failure;        
    public static String profilePage_selectProfileDefaultMessage;
    public static String wizard_dataSource_defaultTitle;
    public static String wizard_missingDataSourceId;
    public static String wizard_invalidManifest;
    public static String page_defaultDataSourceTitle;
    public static String designSession_alreadyHasLinkedProfile;
    public static String designSession_flushFailed;
    public static String designSession_invalidArgument;
    public static String designSession_invalidEditApiCall;
    public static String designSession_invalidNewDesignApiCall;
    public static String designSession_invalidProfileName;
    public static String designSession_invalidProfileNameOrFilePath;
    public static String designSession_missingDataSetUIElement;
    public static String manifest_missingAttributeValue;
    public static String manifest_missingDataSetElementId;
    public static String manifest_invalidDataSetElementId;
    public static String manifest_dataSetUi_missingPageId;
    public static String manifest_dataSetUi_invalidPageId;
    public static String manifest_dataSetUi_missingElement;
    public static String ui_defaultDataSourceTitle;
    public static String ui_replaceFilePrompt;
    public static String ui_requiredFieldsMissingValue;
    public static String ui_errorLabel;
    public static String ui_errorCreatingCustomCtrls;
    public static String ui_saveAsTitle;
    public static String profileStoreCreationDialog_button_new;
    public static String profileStoreCreationDialog_title;
    public static String profileStoreCreationDialog_grouptext;
    public static String profileStoreCreationDialog_newbutton_tooltiptext;
    public static String profileStoreCreationDialog_fileext_error;
    public static String preferencesPage_openDataAccessDescription;
}

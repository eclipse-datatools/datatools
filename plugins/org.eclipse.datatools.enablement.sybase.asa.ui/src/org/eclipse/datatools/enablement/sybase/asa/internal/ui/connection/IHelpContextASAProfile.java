/**
 * Created on Mar 22, 2008
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.enablement.sybase.asa.internal.ui.connection;

public interface IHelpContextASAProfile
{

    /*
     * ASA_PROFILE_WIZARD_PAGE = the wizard page that collects ASA profile
     * details such as the path to the database, user id, password, etc.
     */
    public static final String ASA_PROFILE_WIZARD_PAGE = "ASA_PROFILE_WIZARD_PAGE";

    /*
     * ASA_PROFILE_PROPERTY_PAGE = the property page that collects and allows editing of
     * ASA profile details such as the path to the database, user id, password, etc.
     */
    public static final String ASA_PROFILE_PROPERTY_PAGE = "ASA_PROFILE_PROPERTY_PAGE";
    
    /*
     * ASA_PROFILE_WIZARD = the actual New ASA Profile wizard
     */
    public static final String ASA_PROFILE_WIZARD = "ASA_PROFILE_WIZARD";
    
    /*
     * ASA_MULTIDB_WIZARD_PAGE = the wizard page that collects ASA multi-database info
     */
    public static final String ASA_MULTIDB_WIZARD_PAGE = "ASA_MULTIDB_WIZARD_PAGE";
}

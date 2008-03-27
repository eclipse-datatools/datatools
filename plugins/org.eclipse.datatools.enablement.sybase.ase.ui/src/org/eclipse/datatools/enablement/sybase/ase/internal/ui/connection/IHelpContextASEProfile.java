/**
 * Created on Mar 22, 2008
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.enablement.sybase.ase.internal.ui.connection;

public interface IHelpContextASEProfile
{

    /*
     * ASE_PROFILE_WIZARD_PAGE = the wizard page that collects ASE profile
     * details such as the path to the database, user id, password, etc.
     */
    public static final String ASE_PROFILE_WIZARD_PAGE = "ASE_PROFILE_WIZARD_PAGE";

    /*
     * ASE_PROFILE_PROPERTY_PAGE = the property page that collects and allows editing of
     * ASE profile details such as the path to the database, user id, password, etc.
     */
    public static final String ASE_PROFILE_PROPERTY_PAGE = "ASE_PROFILE_PROPERTY_PAGE";
    
    /*
     * ASE_PROFILE_WIZARD = the actual New ASE Profile wizard
     */
    public static final String ASE_PROFILE_WIZARD = "ASE_PROFILE_WIZARD";
    
    /*
     * ASE_MULTIDB_WIZARD_PAGE = the wizard page that collects ASE multi-database info
     */
    public static final String ASE_MULTIDB_WIZARD_PAGE = "ASE_MULTIDB_WIZARD_PAGE";
}

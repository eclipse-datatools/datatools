/*
 *************************************************************************
 * Copyright (c) 2005, 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.profile;

import org.eclipse.datatools.connectivity.oda.profile.nls.Messages;

public class Constants
{
    public static String ODA_COMPONENT_NAME = Messages.constants_componentName;
    public static final String ODA_COMPONENT_VERSION = "3.0.6"; //$NON-NLS-1$
    
    public static final String EMPTY_STRING = ""; //$NON-NLS-1$
    
    /** 
     * The IPropertyProvider extension's application id that provides the 
     * service to use a connection profile for externalized properties.
     * A consumer application that wants to use this service must specify 
     * this application id as the value of the property key 
     * IPropertyProvider.ODA_CONSUMER_ID in a connection appContext map.
     * @since 3.0.4
     */
    public static final String CONN_PROFILE_APPL_ID =
        "org.eclipse.datatools.connectivity.oda.profile.connectionPropertyService"; //$NON-NLS-1$

    /** 
     * ODA parent category ID
     * @since 3.0.6
     */
    public static final String ODA_PARENT_CATEGORY_ID =
        "org.eclipse.datatools.connectivity.oda.profileCategory"; //$NON-NLS-1$

    /**
     * ODA extended profile property id to the base properties of JDBC DB profiles
     * @since 3.0.6
     */
    public static final String DB_PROFILE_PROVIDER_ID = 
        "org.eclipse.datatools.connectivity.oda.profile.db.provider.id"; //$NON-NLS-1$

    /**
     * Database connection profile category id.
     * TODO - should be defined in the connectivity component.
     */
    public static final String DATABASE_CATEGORY_ID = 
        "org.eclipse.datatools.connectivity.db.category"; //$NON-NLS-1$

}

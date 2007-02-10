/*
 *************************************************************************
 * Copyright (c) 2005, 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
    public static final String ODA_COMPONENT_VERSION = "3.0.4"; //$NON-NLS-1$
    
    public static final String EMPTY_STRING = ""; //$NON-NLS-1$
    
    /* The IPropertyProvider extension's application id that provides the 
     * service to use a connection profile for externalized properties.
     * A consumer application that wants to use this service must specify 
     * this application id as the value of the property key 
     * IPropertyProvider.ODA_CONSUMER_ID in a connection appContext map.
     * @since 3.0.4
     */
    public static final String CONN_PROFILE_APPL_ID =
        "org.eclipse.datatools.connectivity.oda.profile.connectionPropertyService"; //$NON-NLS-1$

}

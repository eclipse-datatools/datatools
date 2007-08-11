/*
 *************************************************************************
 * Copyright (c) 2007 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.consumer.services.impl;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.consumer.services.IPropertyProvider;
import org.eclipse.datatools.connectivity.oda.consumer.util.manifest.ExtensionExplorer;
import org.eclipse.datatools.connectivity.oda.consumer.util.manifest.PropertyProviderManifest;
import org.eclipse.datatools.connectivity.oda.util.logging.LogManager;
import org.eclipse.datatools.connectivity.oda.util.logging.Logger;

/**
 * Utility class for the providers of ODA consumer services.
 */
public class ProviderUtil
{
    private static final String sm_loggerName = "org.eclipse.datatools.connectivity.oda.consumer"; //$NON-NLS-1$

    
    /**
     * Instantiates and returns the property provider of the specified application
     * that implements the ODA consumer propertyProvider extension point.
     * @param applicationId     the identifier of an ODA consumer application; 
     *          its value must match the consumerApplicationId attribute of 
     *          a propertyProvider extension
     * @return  an instance of the providerClass implemented in the specified
     *          propertyProvider extension
     * @throws OdaException
     */
    public static IPropertyProvider createPropertyProvider( String applicationId )
        throws OdaException
    {
        final String methodName = "ProviderUtil.getPropertyProvider( String )\t"; //$NON-NLS-1$
    
        if( applicationId == null || applicationId.length() == 0 )
        {
            log( methodName, "No application id specified in method argument." ); //$NON-NLS-1$
            return null;    // no consumer application id specified in appContext
        }
        
        PropertyProviderManifest providerManifest = 
            ExtensionExplorer.getInstance().getPropertyProviderManifest( applicationId );
        if( providerManifest == null )
        {
            log( methodName, "No IPropertyProvider found for " + applicationId + "." ); //$NON-NLS-1$ //$NON-NLS-2$
            return null;    // no extension defined by application
        }
          
        IPropertyProvider extnProvider = providerManifest.createProvider();
        log( methodName, "Returning IPropertyProvider instance (" + extnProvider  //$NON-NLS-1$
                            + ") of application id (" + applicationId + ")." ); //$NON-NLS-1$ //$NON-NLS-2$
        return extnProvider;
    }

    private static void log( String context, String msg )
    {
        Logger logger = getLogger();
        if( logger != null )
            logger.fine( context + msg );
    }
    
    private static Logger getLogger()
    {
        return LogManager.getLogger( sm_loggerName );
    }

}

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

package org.eclipse.datatools.connectivity.oda.consumer.services;

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 * The interface of the provider of run-time data source properties.
 * An ODA consumer application that implements the 
 * <code>org.eclipse.datatools.connectivity.oda.consumer.propertyProvider</code> 
 * extension point must provide a concrete class implementation
 * of this interface.
 */
public interface IPropertyProvider
{
	// ODA consumer extension keys to include in the application context Map
	// specified in the call to IDriver.setAppContext method
	public static final String ODA_CONSUMER_ID = "OdaConsumerId"; //$NON-NLS-1$
    public static final String ODA_CONN_PROP_CONTEXT = "OdaConnPropertyContext"; //$NON-NLS-1$

    /**
     * Provides the effective property values to use at runtime to open
     * a connection to a data source.
     * @param candidateProperties  the set of candidate property name-value pairs 
     *              specified by an ODA consumer application
     *              in its call to an ODA driver's IConnection.open method.
     * 				It may contain a configuration id or key 
     * 				specific to the consumer application
     * 				to uniquely identify a set of externalized property values.
     * 				Such use is entirely implementation dependent.
     * @param appContext   the application context value provided by an 
     *          ODA consumer application in its call to an ODA driver's
     *          IDriver.setAppContext method, associated with
     *          the ODA_CONN_PROP_CONTEXT key.
     *          <br>The provider may use this application-specific context to
     *          assist in its lookup and processing of its externalized 
     *          configuration framework.  May be null.
     * @return  the set of effective property name-value pairs to use
     * @throws OdaException     if provider error occurs
     */
    Properties getDataSourceProperties( Properties candidateProperties, 
    									Object appContext )
        throws OdaException;
    
}

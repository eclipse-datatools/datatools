/*
 *************************************************************************
 * Copyright (c) 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.consumer.test.impl;

import org.eclipse.datatools.connectivity.oda.LogConfiguration;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.consumer.helper.OdaDriver;
import org.eclipse.datatools.connectivity.oda.util.logging.Logger;

/**
 * Tester OdaDriver for testing behavior of the ThreadLocal variable
 * and obtaining odaconsumer logger instance.
 * It has very specific implementation for verifying test cases 
 * in {@link LoggerInstanceTest}, should not be used by general test cases.
 */
public class LoggerAccessor extends OdaDriver
{

    public static final String HANDLES_LOG_CONFIG = "HANDLES_LOG_CONFIG"; //$NON-NLS-1$

    public LoggerAccessor( String driverConfig ) throws OdaException
    {
        super( driverConfig );
    }

    /**
     * Exposes the protected method of base class.
     */
    public Logger getLogger()
    {
        return super.getLogger();
    }

    /**
     * Exposes the protected method of base class.
     */
    public void setLogger( Logger logger )
    {
        super.setLogger( logger );
    }

    /**
     * This method intercepts calls to super.setLogConfiguration.
     * It stops logger creation while instantiating an OdaDriver in
     * ODA Consumer helper.  It is intended for verifying very specific 
     * test cases in {@link LoggerInstanceTest}.
     */
    public void setLogConfiguration( LogConfiguration logConfig )
            throws OdaException
    {
        if( logConfig != null )
        {
            if( logConfig.getFormatterClassName() != null
                    && !logConfig.getFormatterClassName().equals( HANDLES_LOG_CONFIG ) )
            {
                logConfig = null;   // do not handle the specified logConfig
            }
        }
        super.setLogConfiguration( logConfig );
    }
    
}

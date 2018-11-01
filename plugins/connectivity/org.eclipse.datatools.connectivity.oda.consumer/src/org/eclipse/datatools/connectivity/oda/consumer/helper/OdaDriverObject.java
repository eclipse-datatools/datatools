/*
 *************************************************************************
 * Copyright (c) 2004, 2006 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.consumer.helper;

import org.eclipse.datatools.connectivity.oda.IClob;
import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 * OdaDriverObject is the base class for all Oda wrapper objects that 
 * needs a reference to its corresponding Oda connection wrapper object. 
 * This also extends some of the OdaObject error handling capabilities.
 */
class OdaDriverObject extends OdaObject
{
	private OdaConnection m_connection;
	
	OdaDriverObject( Object obj, OdaConnection connection, 
	                 boolean switchContextClassloader,
					 ClassLoader driverClassLoader )
	{
		super( obj, switchContextClassloader, driverClassLoader, 
                connection.getOriginalContextClassLoader() );
		m_connection = connection;
	}
	
	protected OdaConnection getOdaConnection()
	{
		return m_connection;
	}
	
	protected void handleError( OdaException exception ) throws OdaException
	{
		m_connection.handleError( exception );
	}

    protected String getClobAsStringImpl( IClob clobObj, String context )
        throws OdaException
    {
        String ret = null;
        if( clobObj == null ) 
            return ret;

        try
        {
            int len = (int) clobObj.length();
            ret = clobObj.getSubString( 1, len );
        }
        catch( UnsupportedOperationException ex )
        {
            handleUnsupportedOp( ex, context );
        }
        catch( RuntimeException rtException )
        {
            handleError( rtException );
        }
        catch( OdaException odaException )
        {
            handleError( odaException );
        }
        return ret;
    }

}

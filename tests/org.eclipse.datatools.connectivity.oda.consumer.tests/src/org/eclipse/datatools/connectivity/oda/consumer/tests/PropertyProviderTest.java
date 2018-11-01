/*
 *****************************************************************************
 * Copyright (c) 2006, 2007 Actuate Corporation.
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
 ******************************************************************************
 */ 

package org.eclipse.datatools.connectivity.oda.consumer.tests;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDriver;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.consumer.helper.OdaDriver;
import org.eclipse.datatools.connectivity.oda.consumer.services.IPropertyProvider;
import org.eclipse.datatools.connectivity.oda.consumer.services.impl.PropertyProviderImpl;
import org.eclipse.datatools.connectivity.oda.flatfile.CommonConstants;

import junit.framework.TestCase;

/**
 * Test cases for the consumer extension point - propertyProvider
 */
public class PropertyProviderTest extends TestCase
{
	private static final String TEST_CONSUMER_ID = 
		PropertyProviderTest.class.getPackage().getName();
	private static final String TEST_FLATFILE_ID = 
		"org.eclipse.datatools.connectivity.oda.flatfile"; //$NON-NLS-1$

	private static IDriver sm_odaDriver;
	private static IConnection sm_odaConn;
	private static HashMap sm_appContext;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception
	{
		super.setUp();
		
		if( sm_odaDriver == null )
		{
			sm_odaDriver = new OdaDriver( TEST_FLATFILE_ID );
			sm_odaDriver.setAppContext( setupAppContext() );
		}
		if( sm_odaConn == null )
			sm_odaConn = sm_odaDriver.getConnection( TEST_FLATFILE_ID );
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception
	{
		super.tearDown();
		
		if( sm_odaConn != null )
			sm_odaConn.close();
	}

	public final void testProviderWithContextAsFolder() throws OdaException
	{				
		IConnection myConn = sm_odaConn;
		assertFalse( myConn.isOpen() );
		
		// expects to trigger call to ExternalizedPropertyProvider
		// and uses appContext as folder property to open connection
		Properties connProps = new Properties();
        connProps.setProperty( PropertyProviderImpl.DEFAULT_ODA_CONFIGURATION_ID_PROP_NAME, "dummyId" );

        myConn.open( connProps );	
		assertTrue( myConn.isOpen() );
	}
    
    public final void testMergeExternalizedProperties() throws OdaException
    {
        IConnection myConn = sm_odaConn;
        assertFalse( myConn.isOpen() );

        // expects to trigger call to ExternalizedPropertyProvider
        // to override this property with folder in context
        Properties connProps = new Properties();
        connProps.setProperty( PropertyProviderImpl.DEFAULT_ODA_CONFIGURATION_ID_PROP_NAME, "dummyId" );
        connProps.setProperty( CommonConstants.CONN_HOME_DIR_PROP, "nonExistDir" );  //$NON-NLS-1$

        myConn.open( connProps );   
        assertTrue( myConn.isOpen() );
    }

	public final void testProviderUsingCandidateValues() throws OdaException
	{
		IConnection myConn = sm_odaConn;
		assertFalse( myConn.isOpen() );

		// pass in non-exist folder name in connection properties; 
        // since config id was not specified in connection properties, 
        // this non-exist folder name should get used, and
		// expects OdaException
		Properties connProps = new Properties();
		connProps.setProperty( CommonConstants.CONN_HOME_DIR_PROP, "./dummyName" );  //$NON-NLS-1$
		
		boolean caughtExpectedException = false;
		try
		{
			myConn.open( connProps );
		}
		catch( OdaException ex )
		{
            caughtExpectedException = true;
		}
        catch( RuntimeException ex )
        {
            fail( "Unexpected exception: " + ex.toString() );
        }
		assertTrue( caughtExpectedException );
	}
	
	public final void testEmptyConsumerId() throws OdaException
	{
		Map appContext = new HashMap();
		appContext.put( IPropertyProvider.ODA_CONSUMER_ID, "" ); //$NON-NLS-1$

        IDriver odaDriver = new OdaDriver( TEST_FLATFILE_ID );
        odaDriver.setAppContext( appContext );
		
		IConnection myConn = odaDriver.getConnection( TEST_FLATFILE_ID );
		
		// pass in empty property collection; 
		// since consumer id was not specified in appContext, 
		// this empty connProps should get used, and
		// expects Exception for missing home folder property
		Properties connProps = new Properties();
		
		boolean caughtExpectedException = false;
		try
		{
			myConn.open( connProps );
		}
		catch( RuntimeException ex )
		{
            caughtExpectedException = true;
		}
        catch( OdaException ex )
        {
            caughtExpectedException = true;
        }
		assertTrue( caughtExpectedException );
	}

    public final void testNoAppContext() throws OdaException
    {               
        IDriver odaDriver = new OdaDriver( TEST_FLATFILE_ID );        
        IConnection myConn = odaDriver.getConnection( TEST_FLATFILE_ID );
        assertFalse( myConn.isOpen() );
        
        // expects no propertyProvider is used, and given properties are used as is
        Properties connProps = new Properties();
        connProps.setProperty( CommonConstants.CONN_HOME_DIR_PROP, "./" );  //$NON-NLS-1$

        myConn.open( connProps );   
        assertTrue( myConn.isOpen() );
        
        myConn.close();
    }

    public final void testNullConnProperties() throws OdaException
    {
        IConnection myConn = sm_odaConn;
        assertFalse( myConn.isOpen() );

        // pass in null connection properties, which should get used, and
        // expects OdaException for null properties
        
        boolean caughtExpectedException = false;
        try
        {
            myConn.open( null );
        }
        catch( OdaException ex )
        {
            caughtExpectedException = true;
        }
        catch( RuntimeException ex )
        {
            fail( "Unexpected exception: " + ex.toString() );
        }
        assertTrue( caughtExpectedException );
    }
    
	private Object setupAppContext()
	{
		if( sm_appContext == null )
		{		
			sm_appContext = new HashMap();
			sm_appContext.put( IPropertyProvider.ODA_CONSUMER_ID, TEST_CONSUMER_ID );
			String folderPathContext = "./"; 	//$NON-NLS-1$
			sm_appContext.put( IPropertyProvider.ODA_CONN_PROP_CONTEXT, folderPathContext );
		}
		return sm_appContext;
	}
}

/*
 *************************************************************************
 * Copyright (c) 2004, 2010 Actuate Corporation.
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

import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.IAdvancedQuery;
import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDataSetMetaData;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.consumer.nls.Messages;

import com.ibm.icu.util.ULocale;

/**
 * OdaConnection is the Oda wrapper for connections.
 */
public class OdaConnection extends OdaObject 
						   implements IConnection
{	
    private static final String MSG_LINE_SEPARATOR = " )\t"; //$NON-NLS-1$

    private int 		m_errorNumber;
	private String 		m_errorMessage;

	// We'll use a hashtable to keep track of the count for opened 
	// connections in each provider's classloader.
	private static Hashtable
						sm_clsLdrOpenedConnCountMap;

	private static int	sm_maxOpenConnections = 0;	// default to no limit
	
	// reference count of opened statements for this connection.  this will 
	// be used for determining whether we've reached the max statements
	private int 		m_numOfOpenedStatements;
	
	// cache holding onto data set type name and its associated metadata
	private Hashtable	m_dsMetaDataCollection;
	
	// holds onto the default data set type metadata ( i.e. only one data set 
	// type in odaconfig.xml )
	private OdaDataSetMetaData
						m_defaultDSMetaData;
	
	private Locale m_locale;
	private Object m_driverAppContext;
	private Object m_connAppContext;
    private ConnectionPropertyHandler m_propertyHandler;
	
	protected OdaConnection( IConnection connection,
	        				 boolean switchContextClassloader,
				   			 ClassLoader driverClassLoader,
                             ClassLoader originalClassLoader )
	{
		super( connection, switchContextClassloader, driverClassLoader, originalClassLoader );
		
		final String context = "OdaConnection.OdaConnection( " + //$NON-NLS-1$
						 connection + ")\t"; //$NON-NLS-1$
		logMethodCalled( context );

		clearDriverError();
		m_numOfOpenedStatements = 0;
		
		logMethodExitWithReturn( context, this );
	}
	
	static void setMaxConnections( int maxOpenConnections )
	{
	    sm_maxOpenConnections = maxOpenConnections;
	}
	
	private Hashtable getDSMetaDataCollection()
	{
		if( m_dsMetaDataCollection == null )
			m_dsMetaDataCollection = new Hashtable();
		
		return m_dsMetaDataCollection;
	}
	
	private IConnection getConnection()
	{
		return (IConnection) getObject();
	}

	protected void handleError( OdaException exception ) throws OdaException
	{
		super.handleError( exception );
		
		int errorNumber = exception.getErrorCode();
		String errorMessage = exception.toString();
		setDriverError( errorNumber, errorMessage );
		throw exception;
	}

	void setDriverError( int errorNumber, String errorMessage )
	{
		m_errorNumber = errorNumber;
		m_errorMessage = errorMessage;
	}
	
	public int getDriverErrorNumber()
	{
		return m_errorNumber;
	}
	
	public String getDriverErrorMessage()
	{
		return m_errorMessage;
	}
	
	public void clearDriverError()
	{
		m_errorNumber = 0;
		m_errorMessage = ""; //$NON-NLS-1$
	}

	boolean canSupportMoreOpenedStatements() throws OdaException
	{
		int maxStatements = getMaxQueries();
		return( maxStatements == 0 || 
				m_numOfOpenedStatements < maxStatements );
	}
	
	public int getMaxQueries() throws OdaException
	{
	    final String context = "OdaConnection.getMaxQueries()\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			int ret = getConnection().getMaxQueries();
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetZero( uoException,
												  "IConnection.getMaxQueries()" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			return handleErrorAndReturnNegOne( rtException );
		}
		catch( OdaException odaException )
		{
			return handleErrorAndReturnNegOne( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
	}
	
	void addOpenStatement( OdaQuery statement )
	{
		// will increment the count only if the statement was prepared
		// successfully
		if( statement.isPreparedSuccessfully() )
			m_numOfOpenedStatements++;
	}
	
	void removeOpenStatement( OdaQuery statement )
	{
		// will decrement the count only if the statement was prepared
		// successfully
		if( statement.isPreparedSuccessfully() )
			m_numOfOpenedStatements--;
	}
	
	Locale getLocale()
	{
		return m_locale;
	}

	void setDriverAppContext( Object context )
	{
	    m_driverAppContext = context;
	}
	
	private Object getDriverAppContext()
	{
	    return m_driverAppContext;
	}
	
	//------------------------------------------------------------------
	//	IConnection interface methods
	//------------------------------------------------------------------
	
	public void open( Properties connProperties ) throws OdaException
	{
		final String context = "OdaConnection.open( " + connProperties + MSG_LINE_SEPARATOR; //$NON-NLS-1$
		logMethodCalled( context );
		
		final String unsupportedOpContext = "IConnection.open( Properties connProperties )"; //$NON-NLS-1$

		// pass-thru driver context to the underlying connection
		// before attempt to call open()
		if( getAppContext() == null )     // no connection level context is set directly
		    setAppContext( getDriverAppContext() );
 
        OdaException propHandlerException = null;
		try
		{	
			setContextClassloader();
			
			// check if this is already opened
			if( checkIsOpen() )
			{
			    log( context, "The ODA connection is already open; skip call to the IConnection.open method." ); //$NON-NLS-1$
				logMethodExit( context );
				return;
			}
			
			// check whether we've reached the maximum number of connections.
			if( sm_maxOpenConnections != 0 && getOpenedConnCount() >= sm_maxOpenConnections )
				throw newOdaException( Messages.helper_maxConcurrentConnectionsReached );

			Properties effectiveConnProps = null;
            try
            {
                effectiveConnProps = getEffectiveProperties( connProperties );
            }
            catch( OdaException ex )
            {
                // preserve the exception and continue with the specified connProperties, 
                // which might be sufficient for the underlying oda driver
                propHandlerException = ex;
                effectiveConnProps = connProperties;
            }

			getConnection().open( effectiveConnProps );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException, unsupportedOpContext );
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
		}
		catch( OdaException odaException )
		{
		    if( propHandlerException != null )
		        odaException.setNextException( propHandlerException );
			handleError( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
		
		// neither the old or new open() threw an exception, therefore the 
		// connection was successfully opened.
		incrOpenedConnCount();
		logMethodExit( context );
	}

	Object getAppContext()
	{
	    return m_connAppContext;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.datatools.connectivity.oda.IConnection#setAppContext(java.lang.Object)
	 */
	public void setAppContext( Object context ) throws OdaException
	{
		final String methodName = "OdaConnection.setAppContext()\t"; //$NON-NLS-1$
		final String contextObjInfo = ( context == null ) ? "null" : context.getClass().getName(); //$NON-NLS-1$
		logMethodCalled( methodName );

		if( m_connAppContext == context )	// already set
		{
		    log( methodName, "Same pass-thru application context object: " + contextObjInfo ); //$NON-NLS-1$
			logMethodExit( methodName );
		    return;		// nothing to do
		}
		
		processConsumerAppContext( context );

		try
		{
			setContextClassloader();
			
		    log( methodName, "Passing thru application context to underlying ODA connection: " + contextObjInfo ); //$NON-NLS-1$
			getConnection().setAppContext( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			// log, and ignore exception
			logUnsupportedOp( uoException, "IConnection.setAppContext" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
		
		// if no exception with passing thru to the underlying connection,
		// hold on to context for subsequent pass-thru to each of its
		// queries before prepare
		m_connAppContext = context;
		
		logMethodExit( methodName );
	}

	public void close() throws OdaException
	{
		final String context = "OdaConnection.close()\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
            // check if underlying connection is not open
            if( ! checkIsOpen() )
            {
                log( context, "The ODA connection is not open; skip call to the IConnection.close method." ); //$NON-NLS-1$
                logMethodExit( context );
                return;
            }
            
			getConnection().close();
			clearDriverError();
			
			m_numOfOpenedStatements = 0;

			// do not clean up the connection and data source metadata
			// cache since it's possible that this connection could be used
			// again, due to connection re-use for some ODA connections.  Let the 
			// garbage collection take care of it.
			
			// now that the connection is closed, it'll no longer count
			// against the number of opened connections for the driver.
			decrOpenedConnCount();
			
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IConnection.close()" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
	}

	private int getOpenedConnCount()
	{
		// using new classloader scheme
		Hashtable counts = getClsLdrOpenedConnMap();
		Object value = counts.get( getConnection().getClass().getClassLoader() );
		int i = ( value == null ) ? 0 : ( (Integer) value ).intValue();
		return i;
	}
	
	private void incrOpenedConnCount()
	{
		incrOrDecrOpenedConnectionCountMap( true /* increment */ );
	}
	
	private void decrOpenedConnCount()
	{
		incrOrDecrOpenedConnectionCountMap( false /* increment */ );
	}
	
	private void incrOrDecrOpenedConnectionCountMap( boolean increment )
	{
		Hashtable counts = getClsLdrOpenedConnMap();
		ClassLoader driverClassLoader = getConnection().getClass().getClassLoader();
		Object value = counts.get( driverClassLoader );
		int i = ( value == null ) ? 0 : ( (Integer) value ).intValue();
        if( increment )
            ++i;
        else    // decrement
        {
            if( i > 0 ) // decrement down to the floor of 0 count
                --i;
        }
		Integer newValue = new Integer( i );
		counts.put( driverClassLoader, newValue );
	}
	
	/*
	 * Lazily instantiate our hashtable.
	 */
	private Hashtable getClsLdrOpenedConnMap()
	{
		if( sm_clsLdrOpenedConnCountMap == null )
			sm_clsLdrOpenedConnCountMap = new Hashtable();
		
		return sm_clsLdrOpenedConnCountMap;
	}

	public boolean isOpen() throws OdaException
	{
		final String context = "OdaConnection.isOpen()\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			boolean ret = checkIsOpen();
			
			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			return handleUnsupportedOpAndRetFalse( uoException,
												   "IConnection.isOpen()" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			return handleErrorAndReturnFalse( rtException );
		}
		catch( OdaException odaException )
		{
			return handleErrorAndReturnFalse( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
	}
	
	private boolean checkIsOpen() throws OdaException
	{
		return getConnection().isOpen();
	}

	public IDataSetMetaData getMetaData( String dataSetType )
		throws OdaException
	{
		final String context = "OdaConnection.getMetaData( " + dataSetType + MSG_LINE_SEPARATOR; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();

			OdaDataSetMetaData odaDSMetaData = doGetMetaData( dataSetType );
			if( odaDSMetaData == null )
			    throw new UnsupportedOperationException();
			
			logMethodExitWithReturn( context, odaDSMetaData );
			return odaDSMetaData;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IConnection.getMetaData( String dataSetType )" ); //$NON-NLS-1$
			return null;
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
			return null;
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
			return null;
		}
		finally
		{
			resetContextClassloader();
		}
	}
	
	OdaDataSetMetaData doGetMetaData( String dataSetType ) throws OdaException
	{
		if( dataSetType == null )
		{
			if( m_defaultDSMetaData != null )
				return m_defaultDSMetaData;
		}
		else
		{
			// see if the metadata is already cached
			if( getDSMetaDataCollection().containsKey( dataSetType ) )
			{	
				OdaDataSetMetaData ret = 
					(OdaDataSetMetaData) getDSMetaDataCollection().get( dataSetType );
				
				return ret;
			}
		}
		
		IDataSetMetaData metadata = getConnection().getMetaData( dataSetType );
		
		if( metadata == null )
			return null;
			
		// create new ds metadata, add it to the cache
		OdaDataSetMetaData odaDSMetaData = new OdaDataSetMetaData( metadata, this,
		                                                           switchContextClassloader(),
																   getDriverClassLoader() );
		
		// check whether we're saving the default data set metadata or caching 
		// it based on the data set name
		if( dataSetType == null )
			m_defaultDSMetaData = odaDSMetaData;
		else
			getDSMetaDataCollection().put( dataSetType, odaDSMetaData );
		
		return odaDSMetaData;
	}

	public IQuery newQuery( String dataSetType )
		throws OdaException
	{
		final String context = "OdaConnection.newQuery( " + //$NON-NLS-1$
						 dataSetType + MSG_LINE_SEPARATOR; 
		logMethodCalled( context );
		final String unsupportedOpContext = "IConnection.newQuery( String )"; //$NON-NLS-1$
		
		try
		{
			setContextClassloader();

			// ensure that the established connection is still active 
	        if( ! checkIsOpen() )
	            throw newOdaException( Messages.helper_connectionIsInactive );
			
			if( ! canSupportMoreOpenedStatements() )
				throw newOdaException( Messages.helper_maxConcurrentStatementsReached );
			
			IQuery statement = getConnection().newQuery( dataSetType );
			
			if( statement == null )
			{	
				logMethodExit( context );
				throw new UnsupportedOperationException();
			}
			
			// IAdvancedQuery will use a different wrapper than IQuery objects.
			IQuery ret = ( statement instanceof IAdvancedQuery ) ?
			        		 newAdvancedQueryHelper( (IAdvancedQuery) statement, dataSetType ) :
				   			 newQueryHelper( statement, dataSetType );

			logMethodExitWithReturn( context, ret );
			return ret;
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException, unsupportedOpContext );
			return null;
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
			return null;
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
			return null;
		}
		finally
		{
			resetContextClassloader();
		}
	}
	
	protected OdaQuery newQueryHelper( IQuery statement, String dataSetType )
	{
		return new OdaQuery( statement, this, dataSetType,
	   			                 switchContextClassloader(),
								 getDriverClassLoader() );
	}
	
	protected OdaAdvancedQuery newAdvancedQueryHelper( IAdvancedQuery advancedQuery, 
												  String dataSetType )
	{
		return new OdaAdvancedQuery( advancedQuery, this, dataSetType,
   						   			 switchContextClassloader(),
									 getDriverClassLoader() );
	}

	public void commit() throws OdaException
	{
	    final String context = "OdaConnection.commit()\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			getConnection().commit();
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IConnection.commit()" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
	}

	public void rollback() throws OdaException
	{
	    final String context = "OdaConnection.rollback()\t"; //$NON-NLS-1$
		logMethodCalled( context );
		
		try
		{
			setContextClassloader();
			
			getConnection().rollback();
			logMethodExit( context );
		}
		catch( UnsupportedOperationException uoException )
		{
			handleUnsupportedOp( uoException,
								 "IConnection.rollback()" ); //$NON-NLS-1$
		}
		catch( RuntimeException rtException )
		{
			handleError( rtException );
		}
		catch( OdaException odaException )
		{
			handleError( odaException );
		}
		finally
		{
			resetContextClassloader();
		}
	}
	
	public void setLocale( String localeString ) throws Throwable
	{	
	    final String context = "OdaConnection.setLocale( " + //$NON-NLS-1$
						 localeString + MSG_LINE_SEPARATOR; 
		logMethodCalled( context );
		
		if( localeString == null || localeString.length( ) != 5 )
			m_locale = Locale.getDefault( );
		else
		{
			String language = localeString.substring( 0, 2 );
			String country  = localeString.substring( 3, 5 );
			m_locale = new Locale( language, country );
		}
		
		try
		{
			Class[] parameterTypes = new Class[1];
			parameterTypes[0] = m_locale.getClass();
			Object[] arguments = new Object[1];
			arguments[0] = m_locale;
			findAndInvokeMethod( "setLocale", parameterTypes, arguments ); //$NON-NLS-1$
		}
		catch( InvocationTargetException ex )
		{
			Throwable cause = ex.getTargetException();
			
			if( cause instanceof RuntimeException )
			{
				handleError( (RuntimeException) cause );
				return;
			}
			
			if( cause instanceof OdaException )
			{
				handleError( (OdaException) cause );
				return;
			}
			
			throw cause;
		}
		catch( NoSuchMethodException ex )
		{
			// ignore, the underlying connection doesn't support this
		}
		catch( IllegalAccessException ex )
		{
			// ignore, the underlying connection doesn't support this
		}
		
		logMethodExit( context );
	}

    /* (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.oda.IConnection#setLocale(com.ibm.icu.util.ULocale)
     */
    public void setLocale( ULocale locale ) throws OdaException
    {
        final String context = "OdaConnection.setLocale( " + locale + MSG_LINE_SEPARATOR;  //$NON-NLS-1$
        final String unsupportedOpContext = "IConnection.setLocale( ULocale )"; //$NON-NLS-1$
        logMethodCalled( context );
        
        try
        {
            setContextClassloader();
            
            if( locale == null )
                locale = ULocale.getDefault( );

            getConnection().setLocale( locale );
            logMethodExit( context );
        }
        catch( AbstractMethodError err )
        {
            // this occurs because the underlying driver has not upgraded
            // to implement this ODA 3.2 method
            String msg = formatMethodNotImplementedMsg( unsupportedOpContext );
            log( context, msg );
            
            handleUnsupportedOp( new UnsupportedOperationException( msg ), msg );
        }
        catch( UnsupportedOperationException uoException )
        {
            handleUnsupportedOp( uoException, unsupportedOpContext );
        }
        catch( RuntimeException rtException )
        {
            handleError( rtException );
        }
        catch( OdaException odaException )
        {
            handleError( odaException );
        }
        finally
        {
            resetContextClassloader();
        }
    }
	
	/**
	 * Processes the consumer application entries from the
	 * specified application context.
	 * @param context	application context set by the consumer application
	 */
	private void processConsumerAppContext( Object context )
	{
	    m_propertyHandler = new ConnectionPropertyHandler( context );
	    
	    // try pass the connection level locale, if set in the context, 
	    // to the underlying provider's connection via #setLocale;
	    // this is done before passing the context object to underlying provider in case
	    // it uses the context for setting its own locale, which can then override #setLocale
	    try
        {
            if( m_propertyHandler.getAppLocale() != null )
                setLocale( m_propertyHandler.getAppLocale() );
        }
        catch( UnsupportedOperationException uoException )
        {
            // ignore
        }
        catch( OdaException ex )
        {
            // ignore
        }
	}
	
    /**
     * Returns the instance of property handler.
     */
    protected ConnectionPropertyHandler getPropertyHandler()
    {
        if( m_propertyHandler == null )
            m_propertyHandler = new ConnectionPropertyHandler( null );
        return m_propertyHandler;
    }
    
    /**
     * Uses the extension implementation of IPropertyProvider, if available, 
     * to obtain and return the effective property names and values 
     * for opening a connection.
     */
    private Properties getEffectiveProperties( Properties candidateProperties )
        throws OdaException
    {
        return getPropertyHandler().getEffectiveProperties( candidateProperties );
    }
    
}

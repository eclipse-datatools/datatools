package org.eclipse.datatools.connectivity.oda.flatfile.tests.performance;

import java.util.Properties;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.flatfile.CommonConstants;
import org.eclipse.datatools.connectivity.oda.flatfile.Connection;
import org.eclipse.datatools.connectivity.oda.flatfile.tests.TestUtil;

import junit.framework.TestCase;


public class PerformanceTest extends TestCase
{
	private static final String TEST_FILE = "huge.csv";
	private IQuery query;
	private IConnection connection;
	
	private String queryText = "select \"pagebreak.visibility\", \"pagebreak\", \"header.1\", "
		+ "\"header.1.1.1\", \"header.1.1.2\", \"header.1.1.3\", \"header.1.1.4\", \"header.1.1.5\", "
		+ "\"header.1.1.6\", \"header.1.1.7\", \"header.1.2.1\", \"header.1.2.2\", \"header.1.2.3\", "
		+ "\"header.1.5.1\", \"header.1.5.2\", \"header.1.5.3\", \"header.1.5.4\", \"header.1.5.5\", "
		+ "\"header.1.5.6\", \"header.1.5.7\", \"header.1.5.8\", \"header.1.5.9\", \"header.1.5.10\", "
		+ "\"header.1.5.11\", \"detail.1\", \"detail.1.1.1\", \"detail.1.1.2\", \"detail.1.1.3\", "
		+ "\"detail.1.1.4\", \"detail.1.1.5\", \"detail.1.1.6\", \"detail.1.1.7\", \"detail.1.1.8\", "
		+ "\"detail.1.1.9\", \"detail.1.1.10\", \"detail.1.1.11\" "
		+ "from " + TEST_FILE + " "
		+ ": {\"pagebreak.visibility\",\"pagebreak.visibility\",STRING;"
		+ "\"pagebreak\",\"pagebreak\",STRING;\"header.1\",\"header.1\",STRING;"
		+ "\"header.1.1.1\",\"header.1.1.1\",STRING;\"header.1.1.2\",\"header.1.1.2\",STRING;"
		+ "\"header.1.1.3\",\"header.1.1.3\",STRING;\"header.1.1.4\",\"header.1.1.4\",STRING;"
		+ "\"header.1.1.5\",\"header.1.1.5\",STRING;\"header.1.1.6\",\"header.1.1.6\",STRING;"
		+ "\"header.1.1.7\",\"header.1.1.7\",STRING;\"header.1.2.1\",\"header.1.2.1\",STRING;"
		+ "\"header.1.2.2\",\"header.1.2.2\",STRING;\"header.1.2.3\",\"header.1.2.3\",STRING;"
		+ "\"header.1.5.1\",\"header.1.5.1\",STRING;\"header.1.5.2\",\"header.1.5.2\",STRING;"
		+ "\"header.1.5.3\",\"header.1.5.3\",STRING;\"header.1.5.4\",\"header.1.5.4\",STRING;"
		+ "\"header.1.5.5\",\"header.1.5.5\",STRING;\"header.1.5.6\",\"header.1.5.6\",STRING;"
		+ "\"header.1.5.7\",\"header.1.5.7\",STRING;\"header.1.5.8\",\"header.1.5.8\",STRING;"
		+ "\"header.1.5.9\",\"header.1.5.9\",STRING;\"header.1.5.10\",\"header.1.5.10\",STRING;"
		+ "\"header.1.5.11\",\"header.1.5.11\",STRING;\"detail.1\",\"detail.1\",STRING;"
		+ "\"detail.1.1.1\",\"detail.1.1.1\",STRING;\"detail.1.1.2\",\"detail.1.1.2\",STRING;"
		+ "\"detail.1.1.3\",\"detail.1.1.3\",STRING;\"detail.1.1.4\",\"detail.1.1.4\",STRING;"
		+ "\"detail.1.1.5\",\"detail.1.1.5\",STRING;\"detail.1.1.6\",\"detail.1.1.6\",STRING;"
		+ "\"detail.1.1.7\",\"detail.1.1.7\",STRING;\"detail.1.1.8\",\"detail.1.1.8\",STRING;"
		+ "\"detail.1.1.9\",\"detail.1.1.9\",STRING;\"detail.1.1.10\",\"detail.1.1.10\",STRING;"
		+ "\"detail.1.1.11\",\"detail.1.1.11\",STRING}";
	
	protected void setUp( ) throws Exception
	{
		super.setUp( );
		connection = new Connection( );
		Properties prop = new Properties( );
		prop.setProperty( CommonConstants.CONN_HOME_DIR_PROP, FileLocator.resolve( this.getClass( ).getResource( "golden" )).getFile( ));
		prop.setProperty( CommonConstants.CONN_CHARSET_PROP, TestUtil.DATASET );
		prop.setProperty( CommonConstants.CONN_DELIMITER_TYPE,
				CommonConstants.DELIMITER_COMMA );
		prop.setProperty( CommonConstants.CONN_INCLCOLUMNNAME_PROP, CommonConstants.INC_COLUMN_NAME_YES );
		prop.setProperty( CommonConstants.CONN_INCLTYPELINE_PROP, CommonConstants.INC_TYPE_LINE_NO );
		prop.setProperty( CommonConstants.CONN_TRAILNULLCOLS_PROP, CommonConstants.TRAIL_NULL_COLS_YES );
		connection.open( prop );
		query = connection.newQuery( "FLATFILE" );
	}


	protected void tearDown( ) throws Exception
	{
		query.close( );
		connection.close( );
		super.tearDown( );
	}
	
	public void testPerformance() throws OdaException
	{
		System.out.println("Begin performance test");
		long begin = System.currentTimeMillis( );
		query.prepare( queryText );
		IResultSet resultSet = query.executeQuery( );
		IResultSetMetaData metaData = resultSet.getMetaData( );
		metaData.getColumnCount( );
		while ( resultSet.next( ) )
		{
			resultSet.next( );
		}
		resultSet.close( );
		long end = System.currentTimeMillis( );
		System.out.println("Consumed Time: " + (end - begin) + "(ms)");
		System.out.println("End performance test");
	}
	
}

package org.eclipse.datatools.enablement.oda.xml;


import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.xml.impl.Connection;
import org.eclipse.datatools.enablement.oda.xml.test.util.TestConstants;



public class PerformanceTest extends BaseTest
{
	
	protected void setUp( ) throws Exception
	{
		super.setUp( );
	}
	
	public void testPerformance() throws OdaException
	{
		String queryText =	"table0#-TNAME-#"
				+ "table0#:#[/Report/Details]#:#"
				+ "{pagebreak.visibility;STRING;/pagebreak.visibility},"
				+ "{pagebreak;STRING;/pagebreak},"
				+ "{detail.1;STRING;/detail.1},"
				+ "{detail.1.1.1;STRING;/detail.1.1.1},"
				+ "{detail.1.1.2;STRING;/detail.1.1.2},"
				+ "{detail.1.1.3;STRING;/detail.1.1.3}," 
				+ "{detail.1.1.4;STRING;/detail.1.1.4}," 
				+ "{detail.1.1.5;STRING;/detail.1.1.5}," 
				+ "{detail.1.1.6;STRING;/detail.1.1.6}," 
				+ "{detail.1.1.7;STRING;/detail.1.1.7},"
				+ "{detail.1.1.8;STRING;/detail.1.1.8},"
				+ "{detail.1.1.9;STRING;/detail.1.1.9},"
				+ "{detail.1.1.10;STRING;/detail.1.1.10}," 
				+ "{detail.1.1.11;STRING;/detail.1.1.11},"
				+ "{header.1;STRING;/header.1},"
				+ "{header.1.1.1;STRING;/header.1.1.1}," 
				+ "{header.1.1.2;STRING;/header.1.1.2}," 
				+ "{header.1.1.3;STRING;/header.1.1.3}," 
				+ "{header.1.1.4;STRING;/header.1.1.4}," 
				+ "{header.1.1.5;STRING;/header.1.1.5}," 
				+ "{header.1.1.6;STRING;/header.1.1.6}," 
				+ "{header.1.1.7;STRING;/header.1.1.7}," 
				+ "{header.1.2.1;STRING;/header.1.2.1}," 
				+ "{header.1.2.2;STRING;/header.1.2.2}," 
				+ "{header.1.2.3;STRING;/header.1.2.3}," 
				+ "{header.1.5.1;STRING;/header.1.5.1}," 
				+ "{header.1.5.2;STRING;/header.1.5.2}," 
				+ "{header.1.5.3;STRING;/header.1.5.3}," 
				+ "{header.1.5.4;STRING;/header.1.5.4}," 
				+ "{header.1.5.5;STRING;/header.1.5.5}," 
				+ "{header.1.5.6;STRING;/header.1.5.6}," 
				+ "{header.1.5.7;STRING;/header.1.5.7}," 
				+ "{header.1.5.8;STRING;/header.1.5.8}," 
				+ "{header.1.5.9;STRING;/header.1.5.9}," 
				+ "{header.1.5.10;STRING;/header.1.5.10}," 
				+ "{header.1.5.11;STRING;/header.1.5.11}," 
				+ "{header.1.6.1;STRING;/header.1.6.1}," 
				+ "{header.1.6.2;STRING;/header.1.6.2}," 
				+ "{header.1.6.3;STRING;/header.1.6.3}," 
				+ "{header.1.6.4;STRING;/header.1.6.4}," 
				+ "{header.1.6.5;STRING;/header.1.6.5}," 
				+ "{header.1.6.6;STRING;/header.1.6.6}," 
				+ "{header.1.6.7;STRING;/header.1.6.7}," 
				+ "{header.1.6.8;STRING;/header.1.6.8}," 
				+ "{header.1.6.9;STRING;/header.1.6.9}," 
				+ "{header.1.6.10;STRING;/header.1.6.10}," 
				+ "{header.1.6.11;STRING;/header.1.6.11}," 
				+ "{header.1.7.1;STRING;/header.1.7.1},   ";
		System.out.println("Begin performance test");
		double totalTime = 0; 
		int repeats = 20;
		for ( int i = 1; i <= repeats; i++ )
		{
			Connection conn = new Connection();
			Properties prop = new Properties();
			prop.put(Constants.CONST_PROP_FILELIST, TestConstants.HUGE_XML_FOR_PERFORMANCE);
			conn.open(prop);
			IQuery query = conn.newQuery( null );
			query.prepare( queryText );
			long begin = System.currentTimeMillis( );
			IResultSet rs = query.executeQuery( );
			int count = 0;
			while (rs.next( ))
			{
				count++;
			}
			long end = System.currentTimeMillis( );
			totalTime += (end - begin);
			assert count == 6608;

			
			rs.close( );
			query.close( );
			conn.close( );		
		}
		System.out.println("Row Count:" + 6608);
		System.out.println("Consumed Time: " + totalTime/repeats + "(ms)");
		System.out.println("End performance test");
	}
	
}


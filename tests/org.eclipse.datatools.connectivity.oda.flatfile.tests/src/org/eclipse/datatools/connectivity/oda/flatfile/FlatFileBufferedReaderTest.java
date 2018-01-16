package org.eclipse.datatools.connectivity.oda.flatfile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.flatfile.FlatFileQuery.FlatFileBufferedReader;

import junit.framework.TestCase;


public class FlatFileBufferedReaderTest extends TestCase
{

	private final static char SEPARATOR = ',';
	
	public void testReadLine( ) throws OdaException, IOException
	{
		FlatFileBufferedReader ffbr = null;
		List line = null;
		
		//empty file
		ffbr = getFlatFileReader("");
		line = ffbr.readLine( );
		assertTrue( line == null );
		ffbr.close( );
		
		//trim
		ffbr = getFlatFileReader(" ");
		line = ffbr.readLine( );
		assertTrue( line.size( ) == 1 && line.get( 0 ).equals( "" ) );
		line = ffbr.readLine( );
		assertTrue( line == null );
		ffbr.close( );
		
		//empty lines
		ffbr = getFlatFileReader("\n\n\n ");
		line = ffbr.readLine( );
		assertTrue( line.size( ) == 0);
		line = ffbr.readLine( );
		assertTrue( line.size( ) == 0);
		line = ffbr.readLine( );
		assertTrue( line.size( ) == 0);
		line = ffbr.readLine( );
		assertTrue( line.size( ) == 1 && line.get( 0 ).equals( "" ) );
		line = ffbr.readLine( );
		assertTrue( line == null );
		ffbr.close( );
		
		//trim
		ffbr = getFlatFileReader(" rr\r\t ");
		line = ffbr.readLine( );
		assertTrue( line.size( ) == 1 && line.get( 0 ).equals( "rr" ));
		line = ffbr.readLine( );
		assertTrue( line == null );
		ffbr.close( );
		
		//multilines withoud separator
		ffbr = getFlatFileReader(" rr\r\t \npp");
		line = ffbr.readLine( );
		assertTrue( line.size( ) == 1 && line.get( 0 ).equals( "rr" ));
		line = ffbr.readLine( );
		assertTrue( line.size( ) == 1 && line.get( 0 ).equals( "pp" ));
		line = ffbr.readLine( );
		assertTrue( line == null );
		ffbr.close( );
		
		//separator
		ffbr = getFlatFileReader(",,");
		line = ffbr.readLine( );
		assertTrue( line.size( ) == 3 && line.get( 0 ).equals( "" )
				&& line.get( 0 ).equals( line.get( 1 ) )
				&& line.get( 0 ).equals( line.get( 2 ) ));
		line = ffbr.readLine( );
		assertTrue( line == null );
		ffbr.close( );
		
		//separator
		ffbr = getFlatFileReader("99, tt , 90");
		line = ffbr.readLine( );
		assertTrue( line.size( ) == 3 && line.get( 0 ).equals( "99" )
				&& line.get( 1 ).equals( "tt" )
				&& line.get( 2 ).equals( "90" ));
		line = ffbr.readLine( );
		assertTrue( line == null );
		ffbr.close( );
		
		//separator
		ffbr = getFlatFileReader("99, tt , 90 \r\n");
		line = ffbr.readLine( );
		assertTrue( line.size( ) == 3 && line.get( 0 ).equals( "99" )
				&& line.get( 1 ).equals( "tt" )
				&& line.get( 2 ).equals( "90" ));
		line = ffbr.readLine( );
		assertTrue( line == null );
		ffbr.close( );
		
		//multilines
		ffbr = getFlatFileReader("99, tt , 90 \r\n,yy,8 0,  ");
		line = ffbr.readLine( );
		assertTrue( line.size( ) == 3 && line.get( 0 ).equals( "99" )
				&& line.get( 1 ).equals( "tt" )
				&& line.get( 2 ).equals( "90" ));
		line = ffbr.readLine( );
		assertTrue( line.size( ) == 4 && line.get( 0 ).equals( "" )
				&& line.get( 1 ).equals( "yy" )
				&& line.get( 2 ).equals( "8 0" )
				&& line.get( 3 ).equals( "" ));
		line = ffbr.readLine( );
		assertTrue( line == null);
		ffbr.close( );
		
		
		//double quoted
		ffbr = getFlatFileReader("5\"pp\"");
		try 
		{
			line = ffbr.readLine( );
			assertTrue( false ); //format is invalid
		} 
		catch ( Exception e )
		{
			
		}
		ffbr.close( );
		
		//double quoted
		ffbr = getFlatFileReader("\"pp\" tt");
		try 
		{
			line = ffbr.readLine( );
			assertTrue( false ); //format is invalid
		} 
		catch ( Exception e )
		{
			
		}
		ffbr.close( );
		
		//double quoted
		ffbr = getFlatFileReader("\"pp\"\"");
		try 
		{
			line = ffbr.readLine( );
			assertTrue( false ); //format is invalid
		} 
		catch ( Exception e )
		{
			
		}
		ffbr.close( );
		
		//double quoted
		ffbr = getFlatFileReader(" \t\"pp\"\"\"\",\n\t ui\", \" \",");
		line = ffbr.readLine( );
		assertTrue( line.size( ) == 3 && line.get( 0 ).equals( "pp\"\",\n\t ui" )
				&& line.get( 1 ).equals( " " )
				&& line.get( 2 ).equals( "" ));
		line = ffbr.readLine( );
		assertTrue( line == null);
		ffbr.close( );
		
		//double quoted
		ffbr = getFlatFileReader(" \t\"pp\"\"\"\",\n\t ui\", \" \" ");
		line = ffbr.readLine( );
		assertTrue( line.size( ) == 2 && line.get( 0 ).equals( "pp\"\",\n\t ui" )
				&& line.get( 1 ).equals( " " ) );
		line = ffbr.readLine( );
		assertTrue( line == null);
		ffbr.close( );
		
		//double quoted
		ffbr = getFlatFileReader(" \t\"pp\"\"\"\",\n\t ui\", \" \" \r\n");
		line = ffbr.readLine( );
		assertTrue( line.size( ) == 2 && line.get( 0 ).equals( "pp\"\",\n\t ui" )
				&& line.get( 1 ).equals( " " ) );
		line = ffbr.readLine( );
		assertTrue( line == null);
		ffbr.close( );
		
		//double quoted, multilines
		ffbr = getFlatFileReader(" \t\"pp\"\"\"\",\n\t ui\", \" \" \r\n\"\"\"\",");
		line = ffbr.readLine( );
		assertTrue( line.size( ) == 2 && line.get( 0 ).equals( "pp\"\",\n\t ui" )
				&& line.get( 1 ).equals( " " ) );
		line = ffbr.readLine( );
		assertTrue( line.size( ) == 2 && line.get( 0 ).equals( "\"" )
				&& line.get( 1 ).equals( "" ) );
		line = ffbr.readLine( );
		assertTrue( line == null);
		ffbr.close( );
		
		//UTF-8 BOM
		byte[] bomHeader = {(byte)0xEF, (byte)0xBB, (byte)0xBF};
		byte[] content = new byte[0]; // an empty file
		byte[] bom = new byte[bomHeader.length + content.length];
		System.arraycopy( bomHeader, 0, bom, 0, bomHeader.length );
		System.arraycopy( content, 0, bom, bomHeader.length, content.length );
		ffbr = getFlatFileReader( bom );
		line = ffbr.readLine( );
		assertTrue( line == null);
		ffbr.close( );
		
		content = " \t\"pp\"\"\"\",\n\t ui\", \" \" \r\n\"\"\"\",".getBytes( );
		bom = new byte[bomHeader.length + content.length];
		System.arraycopy( bomHeader, 0, bom, 0, bomHeader.length );
		System.arraycopy( content, 0, bom, bomHeader.length, content.length );
		ffbr = getFlatFileReader( bom );
		line = ffbr.readLine( );
		assertTrue( line.size( ) == 2 && line.get( 0 ).equals( "pp\"\",\n\t ui" )
				&& line.get( 1 ).equals( " " ) );
		line = ffbr.readLine( );
		assertTrue( line.size( ) == 2 && line.get( 0 ).equals( "\"" )
				&& line.get( 1 ).equals( "" ) );
		line = ffbr.readLine( );
		assertTrue( line == null);
		ffbr.close( );
		
	}
	
	private FlatFileBufferedReader getFlatFileReader( String s ) throws IOException
	{
		return new FlatFileBufferedReader( new ByteArrayInputStream( s.getBytes( ) ), 
				"UTF-8",
				SEPARATOR );
	}

	private FlatFileBufferedReader getFlatFileReader( byte[] bytes ) throws IOException
	{
		return new FlatFileBufferedReader( new ByteArrayInputStream( bytes ), 
				"UTF-8",
				SEPARATOR );
	}
}

/*******************************************************************************
 * Copyright (c) 2004, 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.xml.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 * This is a helper class used by SaxParserConsumer to generate nested xml columns related
 * information.
 */
public class SaxParserNestedQueryHelper implements ISaxParserConsumer
{	
	/* (non-Javadoc)
	 * @see org.eclipse.datatools.enablement.oda.xml.util.ISaxParserConsumer#finish()
	 */
	public void finish( )
	{
	}

	//The table name
	private String tableName;
	
	//The RelationInformation instance which defines the table.
	private RelationInformation relationInfo;
	
	
	//The sax parser instance.
	private SaxParser sp;
	private Thread spThread;

	//TODO: need to be cached in a file for huge data??
	Map indexPathValuesMap = new HashMap();
	
	private SaxParserConsumer consumer;
	
	private MappingPathElementTree mappingPathElementTree;
	
	
	/**
	 * @param rinfo
	 * @param fileName
	 * @param tName
	 * @throws OdaException 
	 */
	protected SaxParserNestedQueryHelper( SaxParserConsumer consumer, RelationInformation rinfo, IXMLSource xmlSource, String tName) throws OdaException
	{
		this.tableName = tName;
		this.relationInfo = rinfo;
		this.consumer = consumer;
		mappingPathElementTree = relationInfo.getTableMappingPathElementTree( tableName );
		this.sp = new SaxParser( xmlSource, this, rinfo.containsNamespace( ) );
		this.spThread = new Thread( sp );
		this.spThread.start();
		try
		{
			//wait until spThread dies
			this.spThread.join( );
		}
		catch ( InterruptedException e )
		{
			throw new OdaException( e );
		}
	}
	

	public void manipulateData( XMLPath path, String value )
	{
		if (mappingPathElementTree != null)
		{
			int[] indexes = mappingPathElementTree.getMatchedColumnIndexs( path );
			for (int i = 0; i < indexes.length; i++)
			{
				if (mappingPathElementTree.isNestedColumn( indexes[i] ))
				{
					addPathValue( indexes[i], path, value );
				}
			}
		}
	}
	
	
	public String getNestedColumnValue(XMLPath tablePath, int index, HashMap filters )
	{
		if (mappingPathElementTree != null)
		{
			Set pathValues = (Set)(indexPathValuesMap.get( Integer.valueOf( index )));
			if (pathValues == null)
			{
				return null;
			}
			Iterator itr = pathValues.iterator( );
			while (itr.hasNext( ))
			{
				NestedColumnPathValue pathValue = (NestedColumnPathValue)itr.next( );
				if (mappingPathElementTree.isValidNestedColumn( index, tablePath, pathValue.getPath( )))
				{
					boolean matchFilters = true;
					if ( filters != null )
					{
						Iterator it = filters.keySet( ).iterator( );
						while ( matchFilters && it.hasNext( ) )
						{
							matchFilters = false;
							Object filterColumnName = it.next( );
							Object value = filters.get( filterColumnName );
							int filterColumnIndex = consumer.getColumnIndex( filterColumnName.toString( ) );
							Set filterPathValues = (Set)(indexPathValuesMap.get( Integer.valueOf( filterColumnIndex )));
							if ( filterPathValues != null )
							{
								Iterator iter = filterPathValues.iterator( );
								while ( iter.hasNext( ) )
								{
									NestedColumnPathValue filterPathValue = ( NestedColumnPathValue ) iter.next( );
									if ( filterPathValue.getPath( ).getPathString( ).startsWith( pathValue.getPath( ).getPathString( ) ) 
											&& SaxParserUtil.isTwoValueMatch( filterPathValue.getValue( ), value ))
									{
										matchFilters = true;
									}
								}
							}
						}
					} //end if ( filters != null )
					if ( matchFilters )
					{
						return pathValue.getValue( );
					}
				} //end if (mappingPathElementTree.isValidNestedColumn( index, tablePath, pathValue.getPath( ) ))
			} //end while (itr.hasNext( ))
		} // end if 
		return null;
	}
	
	private void addPathValue(int index, XMLPath path, String value)
	{
		NestedColumnPathValue pathValue = new NestedColumnPathValue(path, value);
		Set pathValues = (Set)indexPathValuesMap.get( Integer.valueOf(index) );
		if (pathValues == null)
		{
			//A sorted set
			pathValues = new TreeSet();
			indexPathValuesMap.put( Integer.valueOf(index), pathValues );
		}
		pathValues.add( pathValue );
	}
	
	private static class NestedColumnPathValue implements Comparable
	{
		XMLPath path;
		String value;
		
		public NestedColumnPathValue( XMLPath path, String value )
		{
			this.path = path;
			this.value = value;
		}

		protected XMLPath getPath( )
		{
			return path;
		}
		
		protected String getValue( )
		{
			return value;
		}

		public int compareTo( Object o )
		{
			NestedColumnPathValue other = (NestedColumnPathValue)o;
			return path.getPathString( ).compareTo( other.path.getPathString( ) );
		}
	}

	public void endElement( XMLPath path )
	{
		// TODO Auto-generated method stub
		
	}


	public void startElement( XMLPath path )
	{
		// TODO Auto-generated method stub
		
	}
}




/*******************************************************************************
 * Copyright (c) 2004, 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.xml.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.xml.Constants;
import org.eclipse.datatools.enablement.oda.xml.i18n.Messages;


/**
 * 
 */
public class MappedTables
{
	//Map<String, Table>, tableName to table map
	private Map tables = new HashMap( );
	
	private String nameSpacePart;
	
	private String[] parameters;
	/**
	 * @param tableMappings: the original table mappings part of query text without applying parameter values
	 * 
	 */
	public MappedTables( String tableMappings ) throws OdaException 
	{
		assert tableMappings != null;
		String[] mappings = tableMappings.split( Constants.CONST_TABLE_DELIMITER );
		
		//fetch name space part
		if ( mappings.length > 0 )
		{
			//currently, name space, if exists, saved with the last table mapping part
			String last = mappings[mappings.length-1].trim( );
			String[] temp = last.split( Constants.CONST_TABLE_COLUMN_DELIMITER );
			if ( temp.length == 4 )
			{
				String nameSpace = temp[3].trim( );
				if ( !nameSpace.startsWith( Constants.CONST_NAMESPACE_START ) || !nameSpace.endsWith( Constants.CONST_NAMESPACE_END ))
				{
					throw new org.eclipse.datatools.connectivity.oda.OdaException( Messages.getString("Query.InvalidQueryText") ); //$NON-NLS-1$
				}
				nameSpacePart = nameSpace.substring( 1,
						nameSpace.length( ) - 1 );
			}
		}
		
		List temp = new ArrayList( );//List<String>
		Set detected = new HashSet( ); //Set<String>
		for ( int i = 0; i < mappings.length; i++ )
		{
			Table t = new Table( mappings[i] );
			tables.put( t.tableName, t );
			String[] ps = t.detectParameters( );
			MappedTables.addParameters( temp, detected, ps);
		}
		parameters = new String[temp.size( )];
		for ( int i=0; i<parameters.length; i++ )
		{
			parameters[i] = (String)temp.get( i );
		}
	}
	
	
	public String[] getParameters( )
	{
		return parameters;
	}
	
	public void setParameterValue( String paraName, String value )
	{
		Iterator itr = this.tables.values( ).iterator( );
		while( itr.hasNext( ) )
		{
			Table t = (Table)itr.next( );
			t.setParameterValue( paraName, value );
		}
	}
	
	public int getColumnCount( String tableName ) throws OdaException
	{
		Table t = (Table)tables.get( tableName );
		if ( t != null )
		{
			return t.columns.length;
		}
		return 0;
	}
	
	/**
	 * 
	 * @param tableName
	 * @param index: 1-based
	 * @return
	 * @throws OdaException
	 */
	public String getColumnName( String tableName, int index ) throws OdaException
	{
		Table t = (Table)tables.get( tableName );
		if ( t != null )
		{
			return t.columns[index-1].name;
		}
		return null;
	}
	
	/**
	 * 
	 * @param tableName
	 * @param index: 1-based
	 * @return
	 * @throws OdaException
	 */
	public String getColumnType( String tableName, int index ) throws OdaException
	{
		Table t = (Table)tables.get( tableName );
		if ( t != null )
		{
			return t.columns[index-1].type;
		}
		return null;
	}
	
	/**
	 * 
	 * @param tableName
	 * @param columnName
	 * @return 1-based index
	 * @throws OdaException
	 */
	public int getColumnIndex( String tableName, String columnName ) throws OdaException
	{
		Table t = (Table)tables.get( tableName );
		if ( t != null )
		{
			return t.getColumnIndex( columnName );
		}
		return -1;
	}
	
	
	/**
	 * @return the nameSpacePart
	 */
	public String getNameSpacePart( )
	{
		return nameSpacePart;
	}

	
	public Collection getTables( )
	{
		return tables.values( );
	}
	

	public static class Table
	{
		private String tableName;
		private String rowPath;
		private Column[] columns;
		
		//1-based
		private Map columnNameIndexMap = new HashMap( );
		
		
		public Table( String mappingText ) throws OdaException
		{
			List cs = new ArrayList( ); //List<Column>
			String[] temp = mappingText.trim( ).split( Constants.CONST_TABLE_COLUMN_DELIMITER );
			if ( temp.length < 2 )
			{
				throw new org.eclipse.datatools.connectivity.oda.OdaException( Messages.getString("Query.InvalidQueryText") ); //$NON-NLS-1$
			}
			tableName = temp[0].trim( );
			rowPath = temp[1].trim( );
			if ( !rowPath.startsWith( Constants.CONST_ROW_START ) 
					|| !rowPath.endsWith( Constants.CONST_ROW_END ))
			{
				throw new org.eclipse.datatools.connectivity.oda.OdaException( Messages.getString("Query.InvalidQueryText") ); //$NON-NLS-1$
			}
			rowPath = rowPath.substring( 1, rowPath.length( )-1 );
			
			String[] columnMappings = new String[0];
			if ( temp.length >= 3 && temp[2].trim( ).length( ) > 0 )
			{
				columnMappings = temp[2].trim( ).split( Constants.CONST_COLUMN_DELIMITER );
			}
			for ( int i=0; i<columnMappings.length; i++)
			{
				String trimedColumn = columnMappings[i].trim( );
				
				if ( !trimedColumn.startsWith( Constants.CONST_COLUMN_START ) 
						|| !trimedColumn.endsWith( Constants.CONST_COLUMN_END ))
				{
					throw new org.eclipse.datatools.connectivity.oda.OdaException( Messages.getString("Query.InvalidQueryText") ); //$NON-NLS-1$
				}
				// remove column info delimiter "{" and "}"
				String[] columnInfos = trimedColumn.substring( 1,
						trimedColumn.length( ) - 1 )
						.split( Constants.CONST_COLUMN_METAINFO_DELIMITER );
				
				if ( columnInfos.length < 2 )
				{
					throw new org.eclipse.datatools.connectivity.oda.OdaException( Messages.getString("Query.InvalidQueryText") ); //$NON-NLS-1$
				}
				String columnPath = "";
				if ( columnInfos.length >= 3 )
				{
					columnPath = columnInfos[2].trim( );
				}
				Column c = new Column( columnInfos[0].trim( ), columnInfos[1].trim( ), columnPath);
				cs.add( c );
			}
			columns = new Column[cs.size( )];
			cs.toArray( columns );
			for ( int i=0; i<columns.length; i++ )
			{
				columnNameIndexMap.put( columns[i].name, Integer.valueOf(i+1) );
			}
		}
		
		public int getColumnIndex( String columnName )
		{
			Integer index = (Integer)columnNameIndexMap.get( columnName );
			return index == null ? -1 : index.intValue( );
		}

		
		/**
		 * @return the tableName
		 */
		public String getName( )
		{
			return tableName;
		}

		
		/**
		 * @return the columns
		 */
		public Column[] getColumns( )
		{
			return columns;
		}

		
		/**
		 * @return the rowPath
		 */
		public String getRowPath( )
		{
			return rowPath;
		}
		
		public String[] detectParameters( )
		{
			List temp = new ArrayList( );//List<String>
			Set detected = new HashSet( ); //Set<String>
			String[] ps = MappedTables.detectParameters( this.getRowPath( ) );
			MappedTables.addParameters( temp, detected, ps);
			for ( int i=0; i<this.getColumns( ).length; i++ )
			{
				ps = MappedTables.detectParameters( this.getColumns( )[i].getPath( ) );
				MappedTables.addParameters( temp, detected, ps);
			}
			String[] result = new String[temp.size( )];
			for ( int i=0; i<result.length; i++ )
			{
				result[i] = (String)temp.get( i );
			}
			return result;
		}
		
		public void setParameterValue( String paraName, String value )
		{
			rowPath = MappedTables.setParameterValue( rowPath, paraName, value );
			for ( int i=0; i<columns.length; i++ )
			{
				columns[i].setParameterValue( paraName, value );
			}
		}

	}
	
	public static class Column
	{
		private String name;
		private String type;
		private String path;
		
		public Column( String name, String type, String path )
		{
			this.name = name;
			this.type = type;
			this.path = path;
		}

		
		/**
		 * @return the name
		 */
		public String getName( )
		{
			return name;
		}

		
		/**
		 * @return the type
		 */
		public String getType( )
		{
			return type;
		}

		
		/**
		 * @return the path
		 */
		public String getPath( )
		{
			return path;
		}
		
		public void setParameterValue( String paraName, String value )
		{
			path = MappedTables.setParameterValue( path, paraName, value );
		}
		
	}
	
	private static void addParameters( List result, Set added, String[] ps )
	{
		for ( int i=0; i<ps.length; i++)
		{
			String p = ps[i];
			if ( !added.contains( p ))
			{
				added.add( p );
				result.add( p );
			}
		}
	}
	
	public static String[] detectParameters( String path )
	{
		assert path != null;
		
		int beginFlagIndex = path.indexOf( Constants.CONST_PARAMETER_START );
		if ( beginFlagIndex < 0 )
		{
			return new String[0];
		}
		int endFlagIndex = path.indexOf( Constants.CONST_PARAMETER_END, beginFlagIndex + Constants.CONST_PARAMETER_START.length( ) );
		if ( endFlagIndex < 0 )
		{
			return new String[0];
		}
		String p = path.substring( beginFlagIndex + Constants.CONST_PARAMETER_START.length( ), endFlagIndex ).trim( );
		if ( p.length( ) > 0 )
		{
			if ( endFlagIndex + Constants.CONST_PARAMETER_END.length( ) < path.length( ) )
			{
				List temp = new ArrayList( ); //List<String>
				temp.add( p );
				String[] others = detectParameters( path.substring( endFlagIndex + Constants.CONST_PARAMETER_END.length( ) ));
				for ( int i=0; i<others.length; i++)
				{
					if ( !p.equals( others[i] ))
					{
						temp.add( others[i] );
					}
				}
				String[] result = new String[temp.size( )];
				for ( int i=0; i<result.length; i++)
				{
					result[i] = (String)temp.get( i );
				}
				return result;
			}
			else
			{
				return new String[]{p};
			}
		}
		else
		{
			if ( endFlagIndex + Constants.CONST_PARAMETER_END.length( ) < path.length( ) )
			{
				return detectParameters( path.substring( endFlagIndex + Constants.CONST_PARAMETER_END.length( ) ));
			}
			else
			{
				return new String[0];
			}
		}
	}
	
	public static String setParameterValue( String originalPath, String paraName, String value )
	{
		String regex = "\\Q" + Constants.CONST_PARAMETER_START + "\\E *" 
					+ "\\Q" + paraName + "\\E" 
					+ " *\\Q" + Constants.CONST_PARAMETER_END + "\\E";
		return originalPath.replaceAll( regex, value );
	}
}

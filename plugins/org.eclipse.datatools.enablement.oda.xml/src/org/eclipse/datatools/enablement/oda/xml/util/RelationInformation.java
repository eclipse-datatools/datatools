/*******************************************************************************
 * Copyright (c) 2004, 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.xml.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.xml.i18n.Messages;
import org.eclipse.datatools.enablement.oda.xml.impl.DataTypes;

/**
 * This class is used to dealing with the strings which are parsed as arguments to 
 * create an XML data source connection.The structure of string must follow the given rule:
 * TableName1#:#[TableRootPath]#:#{columnName1;Type;RelativeXPath},{columnName2;Type;RelativeXPath}...
 * #-#TableName2#:#[TableRootPath]#:#{columnName1;Type;RelativeXpath}.....
 * 
 */
public class RelationInformation
{
	//
	public static final String CONST_TABLE_DELIMITER = "#-#";
	public static final String CONST_TABLE_COLUMN_DELIMITER = "#:#";
	public static final String CONST_COLUMN_METAINFO_DELIMITER = ";";
	public static final String CONST_COLUMN_DELIMITER = ",";
	
	//
	private HashMap tableInfos;

	/**
	 * 
	 * @param relationString
	 * @throws OdaException
	 */
	public RelationInformation( String relationString ) throws OdaException
	{
		this.tableInfos = new HashMap( );
		initialize( relationString.trim( ) );
	}

	/**
	 * Initialize tableInfos by analyzing the input string.
	 * @param relationString
	 * @throws OdaException 
	 */
	private void initialize( String relationString ) throws OdaException
	{	
		if( relationString == null|| relationString.length() == 0)
			throw new OdaException( Messages.getString("RelationInformation.InputStringCannotBeNull"));
		
		String[] tables = relationString.split( CONST_TABLE_DELIMITER );
		for ( int i = 0; i < tables.length; i++ )
		{
			List filterColumnInfos = new ArrayList();
			String[] temp = tables[i].trim( )
					.split( CONST_TABLE_COLUMN_DELIMITER );
			assert ( temp.length == 3 );
			
			// //////////////////////////////
			String tableName = temp[0].trim();
			String tableRawRoot = temp[1].substring( 1, temp[1].length( ) - 1 ).trim( );
			TableInfo tableInfo = new TableInfo( tableName,
					tableRawRoot );
			
			// ////////////////////////////////
			String[] columns = temp[2].trim( ).split( CONST_COLUMN_DELIMITER );
			
			for ( int j = 0; j < columns.length; j++ )
			{
				String trimedColumn = columns[j].trim( );
				// remove column info delimiter "{" and "}"
			
				String[] columnInfos = trimedColumn.substring( 1,
						trimedColumn.length( ) - 1 )
						.split( CONST_COLUMN_METAINFO_DELIMITER );
				
				//columnInfos[0]: column name
				//columnInfos[1]: column type
				//columnInfos[2]: column XPath
				String columnXpath = "";
				for ( int m = 0; m < columnInfos.length; m++ )
					columnInfos[m] = columnInfos[m].trim( );
				if( columnInfos.length == 3 )
				{
					columnXpath = columnInfos[2];
				}
				HashMap map = null;
				//if it is a filter expression
				if ( columnXpath.matches( ".*\\Q[@\\E.*\\Q=\\E.*" ) )
				{
					map = populateFilterInfo(filterColumnInfos, tableInfo,
							columns, columnXpath);
				}
				tableInfo.addColumn( new ColumnInfo( j + 1,
						columnInfos[0],
						columnInfos[1],
						columnXpath, map));
			}
			
			for( int j = 0; j < filterColumnInfos.size( ); j++ )
			{
				tableInfo.addColumn( (ColumnInfo )filterColumnInfos.get( j ));
			}
			
			if ( tableRawRoot.matches(".*\\Q[@\\E.*\\Q=\\E.*")) 
			{
				String tableRootWithFilter = SaxParserUtil.removeRedundantParentAxis( tableRawRoot );
				
				String value = RelationInformation.getFilterValue( tableRootWithFilter );

				String filterColumnXpath = tableRootWithFilter.replaceAll(
						"\\Q=\\E.*", "]");
				int backRef = tableRootWithFilter.split( "/" ).length - filterColumnXpath.split( "/" ).length;
				String tableFilterPart = tableRawRoot.replaceAll( ".*\\Q[\\E", "" ).replaceAll( "\\Q=\\E.*", "" );
				for( int n = 0; n < backRef; n++ )
				{
					tableFilterPart = "../"+tableFilterPart;
				}
				String tempColumnName = SaxParserUtil.createTableRootTempColumnNameForFilter( );

				// TODO support multiple filters in one column.
				tableInfo.addFilter(tempColumnName, value);

				tableInfo.addColumn(new ColumnInfo( columns.length + filterColumnInfos.size( ) + 1, tempColumnName,
						"String", tableFilterPart,
						null));
			}
			tableInfo.bulidMappingPathTree( );
			this.tableInfos.put( temp[0].trim( ), tableInfo );
		}
	}

	/**
	 * 
	 * @param filterColumnInfos
	 * @param tableInfo
	 * @param columns
	 * @param columnXpath
	 * @param originalColumnXpath
	 * @return
	 * @throws OdaException
	 */
	private static HashMap populateFilterInfo(List filterColumnInfos,
			TableInfo tableInfo, String[] columns, String columnXpath)
			throws OdaException 
	{
		HashMap map = null;
		//get the filter value
		String value = getFilterValue(columnXpath);
		
		columnXpath = columnXpath.replaceAll( "\\Q=\\E.*","" );
		//Replace the last "[a" with "/a"
		int index = columnXpath.lastIndexOf( "[@" );
		String before = columnXpath.substring( 0,  index);
		String after = columnXpath.substring( index + 1 );
		String relativePath = before + "/" + after;
		

		String tempColumnName = SaxParserUtil
				.createTempColumnName(filterColumnInfos.size() + 1);

		//TODO support multiple filters in one column.
		//tableInfo.addFilter( tempColumnName, value );
		map = new HashMap();
		map.put(tempColumnName, value);
				
		//
		filterColumnInfos.add( new ColumnInfo( columns.length
				+ filterColumnInfos.size( ) + 1,
				tempColumnName,
				"String",
				relativePath,
				null) );
		return map;
	}

	/**
	 * 
	 * @param columnXpath
	 * @return
	 * @throws OdaException 
	 */
	static String getFilterValue(String columnXpath) throws OdaException 
	{
		String value = columnXpath.replaceAll( ".*\\Q[@\\E.*\\Q=\\E",
				"" )
				.trim( );
		value = value.replaceAll( "\\Q]\\E.*", "" ).trim( );
		
		//by now, the value should be something like "ABC" or 'ABC'
		if ( ( value.startsWith( "'" ) && value.endsWith( "'" ) )
				|| ( value.startsWith( "\"" ) && value.endsWith( "\"" ) ) )
			value = value.substring( 1, value.length( ) - 1 );
		else 
			throw new OdaException( Messages.getString( "RelationInformation.InvalidFilterDefinition" ));
		return value;
	}


	
	/**
	 * Return the path of a column in certain table.
	 * 
	 * @param tableName
	 * @param columnName
	 * @return
	 */
	public String getTableOriginalColumnPath( String tableName, String columnName )
	{
		Object tableInfo = this.tableInfos.get( tableName == null ? "":tableName.trim( ) );
		if( tableInfo != null )
			return ( (TableInfo) tableInfo ).getOriginalPath( columnName == null? "":columnName.trim( ) );
		else
			return null;
	}
	
	/**
	 * Return the type of a column in certain table.
	 * 
	 * @param tableName
	 * @param columnName
	 * @return
	 */
	public String getTableColumnType( String tableName, String columnName )
	{
		Object tableInfo = this.tableInfos.get( tableName == null ? "":tableName.trim( ) );
		if( tableInfo!= null )
			return ( (TableInfo)tableInfo ).getType( columnName == null? "":columnName.trim( ) );
		else
			return null;
	}

	/**
	 * Return the array of column names of certain table.
	 *  
	 * @param tableName
	 * @return
	 */
	public String[] getTableColumnNames( String tableName )
	{
		Object tableInfo = this.tableInfos.get( tableName == null ? "":tableName.trim( ) );
		if( tableInfo!= null )
			return ( (TableInfo)tableInfo ).getColumnNames( );
		else
			return new String[0];
	}

	/**
	 *  
	 * @param tableName
	 * @return user defined columns and system generated tmp columns 
	 */
	String[] getTableRealColumnNames( String tableName )
	{
		Object tableInfo = this.tableInfos.get( tableName == null ? "":tableName.trim( ) );
		if( tableInfo!= null )
			return ( (TableInfo)tableInfo ).getRealColumnNames( );
		else
			return new String[0];
	}
	
	/**
	 * Return the array of nested column names of certain table.
	 *  
	 * @param tableName
	 * @return
	 */
	public String[] getTableNestedXMLColumnNames( String tableName )
	{
		Object tableInfo = this.tableInfos.get( tableName == null ? "":tableName.trim( ) );
		if( tableInfo!= null )
			return ( (TableInfo)tableInfo ).getNestedXMLColumnNames( );
		else
			return new String[0];
	}
	
	/**
	 * Return the mapping path element tree  of certain table.
	 *  
	 * @param tableName
	 * @return
	 */
	public MappingPathElementTree getTableMappingPathElementTree( String tableName )
	{
		Object tableInfo = this.tableInfos.get( tableName == null ? "":tableName.trim( ) );
		if( tableInfo!= null )
			return ( (TableInfo)tableInfo ).getMappingPathTree( );
		else
			return null;
	}
	
	/**
	 * Return the table root path.
	 * 
	 * @param tableName
	 * @return
	 */
	public String getTableRootPath( String tableName )
	{
		Object tableInfo = this.tableInfos.get( tableName == null ? "":tableName.trim( ) );
		if( tableInfo!= null )
			return ( (TableInfo)tableInfo ).getRootPath( );
		else
			return null;
	}
	
	/**
	 * Return the table original root path.
	 * 
	 * @param tableName
	 * @return
	 */
	public String getTableOriginalRootPath( String tableName )
	{
		Object tableInfo = this.tableInfos.get( tableName == null ? "":tableName.trim( ) );
		if( tableInfo!= null )
			return ( (TableInfo)tableInfo ).getOriginalRootPath( );
		else
			return null;
	}
	
	/**
	 * Return the table filter.
	 * 
	 * @param tableName
	 * @return
	 */
	public HashMap getTableFilter( String tableName )
	{
		Object tableInfo = this.tableInfos.get( tableName == null ? "":tableName.trim( ) );
		if( tableInfo!= null )
			return ( (TableInfo)tableInfo ).getFilter( );
		else
			return null;
	}
	
	/**
	 * @return
	 */
	public Iterator getTableNames()
	{
		return this.tableInfos.keySet( ).iterator( );
	}
	
	/**
	 * 
	 * @param tableName
	 * @param columnName
	 * @return
	 */
	public HashMap getTableColumnFilter( String tableName, String columnName )
	{
		Object tableInfo = this.tableInfos.get( tableName == null ? "":tableName.trim( ) );
		if( tableInfo!= null )
			return ( (TableInfo)tableInfo ).getColumnFilters(columnName);
		else
			return null;
	}
}


/**
 * The instance of this class describe a table.
 *
 */
class TableInfo
{
	//The name of the table.
	private String tableName;
	
	//The hashmap which host columnInfos
	private HashMap columnInfos;
	
	//The hashmap which host filterInfos
	private HashMap filterInfos;

	//The original root path of this table
	private String originalRootPath;
	
	//The root path of this table
	private String rootPath;
	
	//includes the original columns and created temp columns 
	private String[] realColumnNames = null;
	
	//the original columns
	private String[] columnNames = null;
	
	private MappingPathElementTree mappingPathElementTree;
	
	private String[] nestedXMLColumnNames = null;
	
	public TableInfo( String tableName, String rootPath )
	{
		this.tableName = tableName;
		this.originalRootPath = rootPath;
		this.rootPath = SaxParserUtil.removeRedundantParentAxis( originalRootPath.replaceAll("\\Q[@\\E.*\\Q=\\E.*\\Q]\\E", ""));
		this.columnInfos = new HashMap( );
		this.filterInfos = new HashMap( );
	}

	/**
	 * Return the name of the table.
	 * 
	 * @return
	 */
	public String getTableName( )
	{
		return this.tableName;
	}
	
	/**
	 * Return the original path of certain column.
	 * 
	 * @param columnName
	 * @return
	 */
	public String getOriginalPath( String columnName )
	{
		return ( (ColumnInfo) this.columnInfos.get( columnName ) ).getColumnOriginalPath();
	}

	/**
	 * Return the path of certain column.
	 * 
	 * @param columnName
	 * @return
	 */
	public String getPath( String columnName )
	{
		return ( (ColumnInfo) this.columnInfos.get( columnName ) ).getColumnPath( );
	}

	
	/**
	 * Return the defined data type of certain column.
	 * 
	 * @param columnName
	 * @return
	 */
	public String getType( String columnName )
	{
		return ( (ColumnInfo) this.columnInfos.get( columnName ) ).getColumnType( );
	}

	/**
	 * Return the hash map which defines the table filters.
	 * 
	 * @return
	 */
	public HashMap getFilter( )
	{
		return this.filterInfos;
	}

	/**
	 * 
	 * @param columnName
	 * @return
	 */
	public HashMap getColumnFilters( String columnName )
	{
		return ((ColumnInfo)(this.columnInfos.get( columnName ))).getFilters();
	}

	
	/**
	 * Add a column to a table.
	 * 
	 * @param ci
	 */
	public void addColumn( ColumnInfo ci )
	{
		this.columnInfos.put( ci.getColumnName( ), ci );
	}

	/**
	 * Add a filter to a table.
	 * 
	 * @param columnName
	 * @param value
	 */
	public void addFilter( String columnName, String value )
	{
		this.filterInfos.put( columnName, value );
	}

	/**
	 * Return the column name array.
	 * 
	 * @return
	 */
	public String[] getColumnNames( )
	{
		if (columnNames == null)
		{
			String[] temp = this.getRealColumnNames( );
			
			List l = new ArrayList();
			for( int i = 0; i < temp.length; i ++ )
			{
				if( SaxParserUtil.isTempColumn(temp[i]))
					// all the temp columns are placed after the original columns
					break;
				else
					l.add( temp[i] );
			}
			
			columnNames = getStringArrayFromList(l);
		}
		return columnNames;
	}

	/**
	 * Return the column name array.
	 * 
	 * @return
	 */
	public String[] getRealColumnNames( )
	{
		if (realColumnNames == null)
		{
			Object[] names = this.columnInfos.keySet( ).toArray( );
			realColumnNames = new String[names.length];
			for ( int i = 0; i < names.length; i++ )
			{
				realColumnNames[( (ColumnInfo) columnInfos.get( names[i] ) ).getColumnIndex( ) - 1] = names[i].toString( );
			}
		}
		return realColumnNames;
	}

	/**
	 * The complex nested xml columnNames are the names of columns which relative path start with ".."
	 * @return
	 */
	public String[] getNestedXMLColumnNames( )
	{
		if (nestedXMLColumnNames != null)
		{
			return nestedXMLColumnNames;
		}
		if (mappingPathElementTree != null)
		{
			String[] columnNames = getRealColumnNames();
			int[] indexes = mappingPathElementTree.getNestedColumnIndexes( );
			nestedXMLColumnNames = new String[indexes.length]; 
			for(int i = 0; i < indexes.length; i++)
			{
				nestedXMLColumnNames[i] = columnNames[indexes[i]];
			}
		}
		else
		{
			nestedXMLColumnNames = new String[0];
		}
		return nestedXMLColumnNames;
	}

	/**
	 * @param temp
	 * @return
	 */
	private String[] getStringArrayFromList( List temp )
	{
		String[] result = new String[temp.size()];
		temp.toArray( result );
		return result;
	}
	
	/**
	 * Return the original root path of this table.
	 * 
	 * @return
	 */
	public String getOriginalRootPath( )
	{
		return this.originalRootPath;
	}
	
	/**
	 * Return the root path of that table.
	 * 
	 * @return
	 */
	public String getRootPath( )
	{
		return this.rootPath;
	}
	
	void bulidMappingPathTree()
	{
		if (!MappingPathElementTree.isValidTableMappingPath( this.getRootPath( ) ))
		{
			//table mapping path invalid, needless to construct MappingPathElementTree
			mappingPathElementTree = null;
			return;
		}
		String[] columnNames = this.getRealColumnNames( );
		String[] columnPaths = new String[columnNames.length];
		for (int i = 0; i < columnPaths.length; i++)
		{
			columnPaths[i] = ((ColumnInfo)columnInfos.get( columnNames[i] )).getColumnPath( );
		}
		mappingPathElementTree = new MappingPathElementTree(this.getRootPath( ), columnPaths);
	}
	
	protected MappingPathElementTree getMappingPathTree( )
	{
		return mappingPathElementTree;
	}
}

/**
 * The instance of this class describe a single column.
 *
 */
class ColumnInfo
{
	//
	private int index;
	private String name;
	private String type;
	private String path;
	private String originalPath;

	private HashMap filters;
	
	/**
	 * 
	 * @param index
	 * @param name
	 * @param type
	 * @param originalPath: originally provided mapping path
	 * @param filters: <tempColumnName, userProvidedStardantValue> map if attribution filter exists for this column
	 * @throws OdaException
	 */
	public ColumnInfo( int index, String name, String type, String originalPath, HashMap filters ) throws OdaException
	{
		this.index = index;
		this.name = name;
		this.type = type;
		if ( !DataTypes.isValidType( type ) )
			throw new OdaException( Messages.getString( "RelationInformation.InvalidDataTypeName" ) );
		this.originalPath = originalPath;
		
		//the result of removing attribution filters
		String purePath = originalPath.replaceAll( "\\Q[@\\E.*\\Q=\\E.*\\Q]\\E", "" )
								   .replaceAll( "\\Q[@\\E.*\\Q]\\E", "" ).trim( );
		
		this.path = SaxParserUtil.removeRedundantParentAxis(  purePath );
		this.filters = filters;
	}


	/**
	 * Return the columnName.
	 * 
	 * @return
	 */
	public String getColumnName( )
	{
		return this.name;
	}

	/**
	 * Return the columnType.
	 * 
	 * @return
	 */
	public String getColumnType( )
	{
		return this.type;
	}

	/**
	 * Return the original path of the column. The original path of a column is the path
	 * directly get from relation information String without building it to an absolute path.
	 * This method is mainly used by UI.
	 * 
	 * @return
	 */
	public String getColumnOriginalPath()
	{
		return this.originalPath;
	}
	
	/**
	 * Return the column relative xPath.
	 * 
	 * @return
	 */
	public String getColumnPath( )
	{
		return this.path;
	}

	/**
	 * Return the column index.
	 * 
	 * @return
	 */
	public int getColumnIndex( )
	{
		return this.index;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public HashMap getFilters( )
	{
		return this.filters;
	}
}

/*******************************************************************************
 * Copyright (c) 2004, 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.xml.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.enablement.oda.xml.Constants;
import org.eclipse.datatools.enablement.oda.xml.i18n.Messages;
import org.eclipse.datatools.enablement.oda.xml.impl.DataTypes;
import org.eclipse.datatools.enablement.oda.xml.util.MappedTables.Column;
import org.eclipse.datatools.enablement.oda.xml.util.MappedTables.Table;

/**
 * This class is used to dealing with the strings which are parsed as arguments to 
 * create an XML data source connection.The structure of string must follow the given rule:
 * TableName1#:#[TableRootPath]#:#{columnName1;Type;RelativeXPath},{columnName2;Type;RelativeXPath}...
 * #-#TableName2#:#[TableRootPath]#:#{columnName1;Type;RelativeXpath}.....
 * 
 */
public class RelationInformation
{
	private static final String EMPTY_STRING = "";     //$NON-NLS-1$
    private static final String DOUBLE_QUOTE = "\"";    //$NON-NLS-1$
    private static final String SINGLE_QUOTE = "'";     //$NON-NLS-1$
    private static final String FORWARD_SLASH = "/";    //$NON-NLS-1$
    //
	private HashMap tableInfos;
	private boolean containsNamespace = false;

	/**
	 * 
	 * @param relationString
	 * @throws OdaException
	 */
	public RelationInformation( String relationString ) throws OdaException
	{
		if( relationString == null|| relationString.length() == 0)
			throw new OdaException( Messages.getString("RelationInformation.InputStringCannotBeNull"));//$NON-NLS-1$
		initialize( new MappedTables( relationString ), false );	
	}
	
	public RelationInformation( MappedTables mt, boolean useNamespace ) throws OdaException
	{
		initialize( mt, useNamespace );		
	}

	private void initialize( MappedTables mt, boolean useNamespace ) throws OdaException
	{	
		this.tableInfos = new HashMap( );
		Map prefixMap = new HashMap( );
		if ( useNamespace && mt.getNameSpacePart( ) != null )
		{
			containsNamespace = true;
			String[] names = mt.getNameSpacePart( ).split( Constants.CONST_COLUMN_METAINFO_DELIMITER );
			for ( int k = 0; k < names.length; k++ )
			{
				String[] entry = names[k].split( Constants.CONST_COLUMN_DELIMITER );
				if ( entry.length == 2
						&& entry[0].trim( ).matches( "\\Q\"\\E.*\\Q\"\\E" )
						&& entry[1].trim( ).matches( "\\Q\"\\E.*\\Q\"\\E" ) )
				{
					prefixMap.put( entry[0].trim( ).substring( 1,
							entry[0].trim( ).length( ) - 1 ),
							entry[1].trim( ).substring( 1,
									entry[1].trim( ).length( ) - 1 ) );
				}
			}
			
		}
		Iterator tablesItr = mt.getTables( ).iterator( );
		while ( tablesItr.hasNext( ) )
		{
			Table t = (Table)tablesItr.next( );
			List filterColumnInfos = new ArrayList();

			String tableName = t.getName( );
			String tableRawRoot = t.getRowPath( );
			if ( useNamespace )
				tableRawRoot = getValueWithNameSpace( tableRawRoot, prefixMap );
			TableInfo tableInfo = new TableInfo( tableName,
					tableRawRoot );
			
			for ( int j = 0; j < t.getColumns( ).length; j++ )
			{
				Column c = t.getColumns( )[j];

			
				String columnXpath = c.getPath( );
	
				if ( useNamespace )
				{
					columnXpath = getValueWithNameSpace( columnXpath, prefixMap );
				}
				HashMap map = null;
				//if it is a filter expression
				if ( columnXpath.matches( ".*\\Q[@\\E.*\\Q=\\E.*" ) )//$NON-NLS-1$
				{
					map = populateFilterInfo(filterColumnInfos, tableInfo,
							t.getColumns( ).length, columnXpath);
				}
				tableInfo.addColumn( new ColumnInfo( j + 1,
						c.getName( ),
						c.getType( ),
						columnXpath, map));
			}
			
			for( int j = 0; j < filterColumnInfos.size( ); j++ )
			{
				tableInfo.addColumn( (ColumnInfo )filterColumnInfos.get( j ));
			}
			
			if ( tableRawRoot.matches(".*\\Q[@\\E.*\\Q=\\E.*")) //$NON-NLS-1$
			{
				String tableRootWithFilter = SaxParserUtil.removeRedundantParentAxis( tableRawRoot );
				
				String value = RelationInformation.getFilterValue( tableRootWithFilter );

				String filterColumnXpath = tableRootWithFilter.replaceAll(
						"\\Q=\\E.*", "]"); //$NON-NLS-1$//$NON-NLS-2$
				int backRef = tableRootWithFilter.split( FORWARD_SLASH ).length - filterColumnXpath.split( FORWARD_SLASH ).length;
				String tableFilterPart = tableRawRoot.replaceAll( ".*\\Q[\\E", EMPTY_STRING ).replaceAll( "\\Q=\\E.*", EMPTY_STRING );//$NON-NLS-1$//$NON-NLS-2$
				for( int n = 0; n < backRef; n++ )
				{
					tableFilterPart = "../"+tableFilterPart;//$NON-NLS-1$
				}
				String tempColumnName = SaxParserUtil.createTableRootTempColumnNameForFilter( );

				// TODO support multiple filters in one column.
				tableInfo.addFilter(tempColumnName, value);

				tableInfo.addColumn(new ColumnInfo( t.getColumns( ).length + filterColumnInfos.size( ) + 1, tempColumnName,
						"String", tableFilterPart, //$NON-NLS-1$
						null));
			}
			tableInfo.bulidMappingPathTree( );
			this.tableInfos.put( t.getName( ), tableInfo );
		}

	}

	public boolean containsNamespace( )
	{
		return containsNamespace;
	}
	
	private String getValueWithNameSpace( String rawInfo, Map prefixMap )
	{
		if ( rawInfo.indexOf( Constants.COLON_MARK ) < 0 )
		{
			return rawInfo; //no name space
		}
		Iterator itr = prefixMap.entrySet( ).iterator( );
		while ( itr.hasNext( ) )
		{
			Map.Entry entry = (Map.Entry)itr.next( );
			String key = (String)entry.getKey( );
			String value = (String)entry.getValue( );
			String regex = "/\\Q" + key + "\\E" + "\\Q" + Constants.COLON_MARK + "\\E";
			rawInfo = rawInfo.replaceAll( regex, "/" + value + Constants.COLON_MARK );
		}
		return rawInfo;
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
			TableInfo tableInfo, int columnCount, String columnXpath)
			throws OdaException 
	{
		HashMap map = null;
		//get the filter value
		String value = getFilterValue(columnXpath);
		
		columnXpath = columnXpath.replaceAll( "\\Q=\\E.*", EMPTY_STRING ); //$NON-NLS-1$
		//Replace the last "[a" with "/a"
		int index = columnXpath.lastIndexOf( "[@" ); //$NON-NLS-1$
		String before = columnXpath.substring( 0,  index);
		String after = columnXpath.substring( index + 1 );
		String relativePath = before + FORWARD_SLASH + after;
		

		String tempColumnName = SaxParserUtil
				.createTempColumnName(filterColumnInfos.size() + 1);

		//TODO support multiple filters in one column.
		//tableInfo.addFilter( tempColumnName, value );
		map = new HashMap();
		map.put(tempColumnName, value);
				
		//
		filterColumnInfos.add( new ColumnInfo( columnCount
				+ filterColumnInfos.size( ) + 1,
				tempColumnName,
				"String", //$NON-NLS-1$
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
		String value = columnXpath.replaceAll( ".*\\Q[@\\E.*\\Q=\\E", //$NON-NLS-1$
				EMPTY_STRING )
				.trim( );
		value = value.replaceAll( "\\Q]\\E.*", EMPTY_STRING ).trim( ); //$NON-NLS-1$
		
		//by now, the value should be something like "ABC" or 'ABC'
		if ( ( value.startsWith( SINGLE_QUOTE ) && value.endsWith( SINGLE_QUOTE ) )
				|| ( value.startsWith( DOUBLE_QUOTE ) && value.endsWith( DOUBLE_QUOTE ) ) )
			value = value.substring( 1, value.length( ) - 1 );
		else 
			throw new OdaException( Messages.getString( "RelationInformation.InvalidFilterDefinition" ));//$NON-NLS-1$
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
		Object tableInfo = this.tableInfos.get( tableName == null ? EMPTY_STRING:tableName.trim( ) );
		if( tableInfo != null )
			return ( (TableInfo) tableInfo ).getOriginalPath( columnName == null? EMPTY_STRING:columnName.trim( ) );
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
		Object tableInfo = this.tableInfos.get( tableName == null ? EMPTY_STRING:tableName.trim( ) );
		if( tableInfo!= null )
			return ( (TableInfo)tableInfo ).getType( columnName == null? EMPTY_STRING:columnName.trim( ) );
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
		Object tableInfo = this.tableInfos.get( tableName == null ? EMPTY_STRING:tableName.trim( ) );
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
		Object tableInfo = this.tableInfos.get( tableName == null ? EMPTY_STRING:tableName.trim( ) );
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
		Object tableInfo = this.tableInfos.get( tableName == null ? EMPTY_STRING:tableName.trim( ) );
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
		Object tableInfo = this.tableInfos.get( tableName == null ? EMPTY_STRING:tableName.trim( ) );
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
		Object tableInfo = this.tableInfos.get( tableName == null ? EMPTY_STRING:tableName.trim( ) );
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
		Object tableInfo = this.tableInfos.get( tableName == null ? EMPTY_STRING:tableName.trim( ) );
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
		Object tableInfo = this.tableInfos.get( tableName == null ? EMPTY_STRING:tableName.trim( ) );
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
		Object tableInfo = this.tableInfos.get( tableName == null ? EMPTY_STRING:tableName.trim( ) );
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
	private static final String EMPTY_STRING = ""; //$NON-NLS-1$

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
		this.rootPath = SaxParserUtil.removeRedundantParentAxis( originalRootPath.replaceAll("\\Q[@\\E.*\\Q=\\E.*\\Q]\\E", EMPTY_STRING));//$NON-NLS-1$
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
	private static final String EMPTY_STRING = "";	//$NON-NLS-1$

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
			throw new OdaException( Messages.getString( "RelationInformation.InvalidDataTypeName" ) );//$NON-NLS-1$
		this.originalPath = originalPath;
		
		//the result of removing attribution filters
		String purePath = originalPath.replaceAll( "\\Q[@\\E.*\\Q=\\E.*?\\Q]\\E", EMPTY_STRING )//$NON-NLS-1$
								   .replaceAll( "\\Q[@\\E.*\\Q]\\E", EMPTY_STRING ).trim( );//$NON-NLS-1$
		
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

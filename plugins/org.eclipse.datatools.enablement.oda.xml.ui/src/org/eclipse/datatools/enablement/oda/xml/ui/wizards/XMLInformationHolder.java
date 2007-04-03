/*******************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.xml.ui.wizards;

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.Property;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.XMLRelationInfoUtil;

/**
 * This class is to hold the public properties for xml data set and data source.
 * This information could shared by xml dataset pages. It supplied some
 * convenient method to get or set these properties information
 */
public class XMLInformationHolder
{

	private static Properties prop;

	/**
	 * 
	 * @param newProp
	 */
	public static void saveProperties( Properties newProp )
	{
		if ( prop == null )
			prop = new Properties( );
		prop.putAll( newProp );
	}

	/**
	 * 
	 * @return
	 */
	public static Properties getProperties( )
	{
		return prop;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public static String getPropertyValue( String key )
	{
		if ( prop == null )
			return null;
		return prop.getProperty( key );
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public static void setPropertyValue( String key, String value )
	{
		if ( prop == null )
		{
			prop = new Properties( );;
		}
		if ( value != null )
			prop.setProperty( key, value );
	}

	/**
	 * 
	 * @param dataSetDesign
	 */
	public static void start( DataSetDesign dataSetDesign )
	{
		if ( dataSetDesign == null )
			return;

		if ( dataSetDesign.getDataSourceDesign( )
				.getOdaExtensionId( )
				.equals( Constants.WS_DATA_SOURCE_ID ) )
		{
			wsStart( dataSetDesign );
			return;
		}

		String queryText = dataSetDesign.getQueryText( );
		if ( queryText != null && queryText.trim( ).length( ) > 0 )
		{
			setPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION,
					queryText );
			String tableName = XMLRelationInfoUtil.getTableName( queryText );
			setPropertyValue( Constants.CONST_PROP_TABLE_NAME, tableName );

			String xpath = XMLRelationInfoUtil.getXPathExpression( queryText,
					tableName );
			setPropertyValue( Constants.CONST_PROP_XPATH, xpath );
		}
		if ( dataSetDesign.getPrivateProperties( ) != null )
		{
			Property xmlFile = dataSetDesign.getPrivateProperties( )
				.findProperty( Constants.CONST_PROP_XML_FILE );
			
			setPropertyValue( Constants.CONST_PROP_XML_FILE,
					xmlFile == null?"":xmlFile.getValue( ));
			Property maxRow = dataSetDesign.getPrivateProperties( )
					.findProperty( Constants.CONST_PROP_MAX_ROW );
					
			setPropertyValue( Constants.CONST_PROP_MAX_ROW, maxRow != null
					? maxRow.getValue( ) : "-1" );

		}
		// backward compatibility. should be removed when Model has done the
		// backward.
		else if ( dataSetDesign.getPublicProperties( ) != null )
		{
			Property xmlFile = dataSetDesign.getPublicProperties( )
				.findProperty( Constants.CONST_PROP_XML_FILE );
			setPropertyValue( Constants.CONST_PROP_XML_FILE,
					xmlFile == null?"":xmlFile.getValue( ) );
			
			Property maxRow = dataSetDesign.getPublicProperties( )
					.findProperty( Constants.CONST_PROP_MAX_ROW );
			
			setPropertyValue( Constants.CONST_PROP_MAX_ROW, maxRow != null
					? maxRow.getValue( ) : "-1" );
			
			dataSetDesign.getPublicProperties( )
					.unsetProperty( Constants.CONST_PROP_MAX_ROW );
			dataSetDesign.getPublicProperties( )
					.unsetProperty( Constants.CONST_PROP_XML_FILE );
		}
		if ( dataSetDesign.getDataSourceDesign( ) != null )
		{
			DataSourceDesign dataSourceDesign = dataSetDesign.getDataSourceDesign( );
			
			Property schema = dataSourceDesign.getPublicProperties( )
					.findProperty( Constants.CONST_PROP_SCHEMA_FILELIST );
			setPropertyValue( Constants.CONST_PROP_SCHEMA_FILELIST,
						schema == null ? "" : schema.getValue( ) );
			
			Property xmlFile  = dataSourceDesign.getPublicProperties( )
					.findProperty( Constants.CONST_PROP_FILELIST );
					
			setPropertyValue( Constants.CONST_PROP_FILELIST, xmlFile == null ? ""
					: xmlFile.getValue( ) );
		}
	}

	/*
	 * temporarily for ws driver
	 */
	private static void wsStart( DataSetDesign dataSetDesign )
	{
		if ( dataSetDesign.getPrivateProperties( ) != null )
		{
			String queryText = dataSetDesign.getPrivateProperties( )
					.findProperty( Constants.XML_QUERYTEXT )
					.getValue( );

			if ( queryText != null && queryText.trim( ).length( ) > 0 )
			{
				setPropertyValue( Constants.CONST_PROP_RELATIONINFORMATION,
						queryText );
				String tableName = XMLRelationInfoUtil.getTableName( queryText );
				setPropertyValue( Constants.CONST_PROP_TABLE_NAME, tableName );

				String xpath = XMLRelationInfoUtil.getXPathExpression( queryText,
						tableName );
				setPropertyValue( Constants.CONST_PROP_XPATH, xpath );
			}
		}
		if ( dataSetDesign.getPublicProperties( ) != null )
		{
			Property xmlFile = dataSetDesign.getPublicProperties( )
					.findProperty( Constants.XML_FILE_URI );
			setPropertyValue( Constants.CONST_PROP_XML_FILE, xmlFile == null
					? "" : xmlFile.getValue( ) );
			setPropertyValue( Constants.CONST_PROP_FILELIST, xmlFile == null
					? "" : xmlFile.getValue( ) );

			Property schema = dataSetDesign.getPublicProperties( )
					.findProperty( Constants.XSD_FILE_URI );
			setPropertyValue( Constants.CONST_PROP_SCHEMA_FILELIST,
					schema == null ? "" : schema.getValue( ) );

			Property maxRow = dataSetDesign.getPublicProperties( )
					.findProperty( Constants.CONST_PROP_MAX_ROW );
			setPropertyValue( Constants.CONST_PROP_MAX_ROW, maxRow != null
					? maxRow.getValue( ) : "-1" );
		}
	}

	/**
	 * destory the holder class
	 *
	 */
	public static void destory( )
	{
		prop = null;
	}
}

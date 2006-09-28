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
		if ( dataSetDesign.getPublicProperties( ) != null )
		{
			setPropertyValue( Constants.CONST_PROP_XML_FILE,
					dataSetDesign.getPublicProperties( )
							.findProperty( Constants.CONST_PROP_XML_FILE )
							.getValue( ) );
			String maxRow = dataSetDesign.getPublicProperties( )
					.findProperty( Constants.CONST_PROP_MAX_ROW )
					.getValue( );
			if ( maxRow != null )
				setPropertyValue( Constants.CONST_PROP_MAX_ROW, maxRow );
			else
				setPropertyValue( Constants.CONST_PROP_MAX_ROW, "-1" );
		}
		if ( dataSetDesign.getDataSourceDesign( ) != null )
		{
			DataSourceDesign dataSourceDesign = dataSetDesign.getDataSourceDesign( );
			String value = dataSourceDesign.getPublicProperties( )
					.findProperty( Constants.CONST_PROP_SCHEMA_FILELIST )
					.getValue( );
			if ( value != null )
				setPropertyValue( Constants.CONST_PROP_SCHEMA_FILELIST,
						value == null ? "" : value );
			value = dataSourceDesign.getPublicProperties( )
					.findProperty( Constants.CONST_PROP_FILELIST )
					.getValue( );
			setPropertyValue( Constants.CONST_PROP_FILELIST, value == null ? ""
					: value );
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

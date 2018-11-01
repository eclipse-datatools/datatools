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
package org.eclipse.datatools.enablement.oda.xml.ui.wizards;

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.DataSetDesign;
import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.ui.designsession.DesignSessionUtil;
import org.eclipse.datatools.enablement.oda.xml.Constants;
import org.eclipse.datatools.enablement.oda.xml.ui.utils.XMLRelationInfoUtil;

/**
 * This class is to hold the public properties for xml data set and data source.
 * This information could shared by xml dataset pages. It supplied some
 * convenient method to get or set these properties information
 */
public class XMLInformationHolder
{

    private static Properties prop;
	private static final String EMPTY_STRING = "";   //$NON-NLS-1$
	private static final String MINUS_ONE = "-1";  //$NON-NLS-1$

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
		if ( dataSetDesign.getPrivateProperties( ) != null )
		{
			String xmlFile = dataSetDesign.getPrivateProperties( )
					.getProperty( Constants.CONST_PROP_XML_FILE );

			setPropertyValue( Constants.CONST_PROP_XML_FILE, xmlFile == null
					? EMPTY_STRING : xmlFile );

			String xmlEncoding = dataSetDesign.getPrivateProperties( )
					.getProperty( Constants.CONST_PROP_XML_ENCODING );
			setPropertyValue( Constants.CONST_PROP_XML_ENCODING,
					xmlEncoding == null ? EMPTY_STRING : xmlEncoding );

			String maxRow = dataSetDesign.getPrivateProperties( )
					.getProperty( Constants.CONST_PROP_MAX_ROW );

			setPropertyValue( Constants.CONST_PROP_MAX_ROW, maxRow != null
					? maxRow : MINUS_ONE );
		}
		if ( dataSetDesign.getDataSourceDesign( ) != null )
		{
			DataSourceDesign dataSourceDesign = dataSetDesign.getDataSourceDesign( );
			java.util.Properties dataSourceProp = null;
			try
			{
				dataSourceProp = DesignSessionUtil.getEffectiveDataSourceProperties( dataSourceDesign );
			}
			catch ( OdaException e )
			{
				dataSourceProp = new java.util.Properties( );
			}

			String schema = dataSourceProp.getProperty( Constants.CONST_PROP_SCHEMA_FILELIST,
					EMPTY_STRING );
			setPropertyValue( Constants.CONST_PROP_SCHEMA_FILELIST, schema );

			String xmlFile = dataSourceProp.getProperty( Constants.CONST_PROP_FILELIST,
					EMPTY_STRING );
			setPropertyValue( Constants.CONST_PROP_FILELIST, xmlFile );

			String xmlEncoding = dataSourceProp.getProperty( Constants.CONST_PROP_ENCODINGLIST,
					EMPTY_STRING );
			setPropertyValue( Constants.CONST_PROP_ENCODINGLIST, xmlEncoding );
		}
	}

	/**
	 * destroy the holder class
	 * 
	 */
	public static void destory( )
	{
		prop = null;
	}

	/**
	 * 
	 * @return
	 */
	public static boolean hasDestroyed( )
	{
		return prop == null;
	}
}

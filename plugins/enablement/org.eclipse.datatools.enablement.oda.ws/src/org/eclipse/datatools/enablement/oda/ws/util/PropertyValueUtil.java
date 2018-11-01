/*******************************************************************************
 * Copyright (c) 2007, 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.oda.ws.util;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.util.manifest.DataSetType;
import org.eclipse.datatools.connectivity.oda.util.manifest.ExtensionManifest;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestExplorer;
import org.eclipse.datatools.connectivity.oda.util.manifest.Property;

/**
 * This utility class to to get the qualified value for the dataset/datasource
 * property.
 * 
 */
public class PropertyValueUtil
{
	private static ExtensionManifest em;
	
	static
	{		
		try
		{
			em = ManifestExplorer.getInstance( ).getExtensionManifest(  Constants.DATA_SOURCE_ID );
		}
		catch ( OdaException e )
		{
		}
	}

	/**
	 * 
	 * @param value
	 * @param propName
	 * @return
	 */
	public static String getQualifiedValueForDataSource( String value, String propName )
	{
		if ( value != null
				&& value.trim( ).length( ) == 0
				&& allowsEmptyValueAsNull( propName ) )
		{
			return null;
		}
		else
			return value;
	}
	
	/**
	 * 
	 * @param value
	 * @param propName
	 * @param dataSetType
	 * @return
	 */
	public static String getQualifiedValueForDataSet( String value,
			String propName, String dataSetType )
	{
		
		if ( value != null
				&& value.trim( ).length( ) == 0
				&& allowsEmptyValueAsNull( propName, dataSetType ) )
		{
			return null;
		}
		else
			return value;
	}
	
	private static boolean allowsEmptyValueAsNull( String propName )
	{
		boolean allowsEmptyValueAsNull = true;

		if ( em != null )
		{
			Property prop = em.getProperty( propName );
			if ( prop != null )
			{
				return prop.allowsEmptyValueAsNull( );
			}
		}
		return allowsEmptyValueAsNull;
	}
	
	private static boolean allowsEmptyValueAsNull( String propName,
			String dataSetType )
	{
		boolean allowsEmptyValueAsNull = true;

		if ( em != null )
		{
			DataSetType dataType = null;
			try
			{
				dataType = em.getDataSetType( dataSetType );
			}
			catch ( OdaException e )
			{
			}
			if ( dataType != null )
			{
				Property prop = dataType.getProperty( propName );
				if ( prop != null )
				{
					return prop.allowsEmptyValueAsNull( );
				}
			}
		}
		return allowsEmptyValueAsNull;
	}
}

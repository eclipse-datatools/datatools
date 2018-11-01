/*******************************************************************************
 * Copyright (c) 2004, 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.enablement.oda.xml.impl;

import java.sql.Types;
import java.util.Locale;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.util.manifest.DataTypeMapping;
import org.eclipse.datatools.connectivity.oda.util.manifest.ExtensionManifest;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestExplorer;
import org.eclipse.datatools.enablement.oda.xml.i18n.Messages;

/**
 * This class hosts the information of data types that are supported by flat
 * file driver
 */
public final class DataTypes
{
	public static final int INT = Types.INTEGER;
	public static final int DOUBLE = Types.DOUBLE;
	public static final int STRING = Types.VARCHAR;
	public static final int DATE = Types.DATE;
	public static final int TIME = Types.TIME;
	public static final int TIMESTAMP = Types.TIMESTAMP;
	public static final int BLOB = Types.BLOB;
	public static final int BIGDECIMAL = Types.NUMERIC; 
	public static final int BOOLEAN = Types.BOOLEAN;
	
	private static final String XML_DATA_SOURCE_ID = 
	        "org.eclipse.datatools.enablement.oda.xml"; //$NON-NLS-1$

	/**
	 * Return the int which stands for the type specified by input argument
	 * 
	 * @param typeName
	 *            the String value of a Type
	 * @return the int which stands for the type specified by input typeName
	 * @throws OdaException
	 *             Once the input arguement is not a valid type name
	 */
	public static int getType( String typeName ) throws OdaException
	{
		  if ( typeName == null || typeName.trim().length() == 0 )
    		return STRING;
		  
	      String preparedTypeName = typeName.trim( ).toUpperCase( Locale.ENGLISH );
	        
	      // get the data type definition from my plugin manifest for all other types
	      DataTypeMapping typeMapping = getManifest().getDataSetType( null )
	                                        .getDataTypeMapping( preparedTypeName );
	      if( typeMapping != null )
	           return typeMapping.getNativeTypeCode();

	      throw new OdaException( Messages
	                .getString( "dataTypes.typeNameInvalid" ) + typeName ); //$NON-NLS-1$
	}
	
	/**
	 * Return the String which stands for the type specified by input argument
	 * 
	 * @param typeName
	 *            the int value of a Type
	 * @return the String which stands for the type specified by input typeName
	 * @throws OdaException
	 *             Once the input arguement is not a valid type name
	 */
	public static String getTypeString( int type ) throws OdaException
	{
		 // get the data type definition from my plugin manifest for all other types
	     DataTypeMapping typeMapping = getManifest().getDataSetType( null )
	                                        .getDataTypeMapping( type );
	        if( typeMapping != null )
	            return typeMapping.getNativeType();

	        throw new OdaException( Messages
	                .getString( "dataTypes.typeNameInvalid" ) + type ); //$NON-NLS-1$
	}
	/**
	 * Evalute whether an input String is a valid type that is supported by flat
	 * file driver
	 * 
	 * @param typeName
	 * @return
	 */
	public static boolean isValidType( String typeName )
	{
        String preparedTypeName = typeName.trim( ).toUpperCase( Locale.ENGLISH );
        
        // check the data type definition in my plugin manifest for all other types
        DataTypeMapping typeMapping = null;
        try
        {
            typeMapping = getManifest().getDataSetType( null )
                                            .getDataTypeMapping( preparedTypeName );
        }
        catch( OdaException e )
        {
            // ignore
        }
        
        return( typeMapping != null );
	}

	 /**
     * Returns the object that represents this extension's manifest.
     * @throws OdaException
     */
    private static ExtensionManifest getManifest()
        throws OdaException
    {
        return ManifestExplorer.getInstance()
                .getExtensionManifest( XML_DATA_SOURCE_ID );
    }
    
	private DataTypes( )
	{
	}
	
}

/*
 *******************************************************************************
 * Copyright (c) 2004, 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *******************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.flatfile;

import java.sql.Types;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.flatfile.i18n.Messages;
import org.eclipse.datatools.connectivity.oda.util.manifest.DataTypeMapping;
import org.eclipse.datatools.connectivity.oda.util.manifest.ExtensionManifest;
import org.eclipse.datatools.connectivity.oda.util.manifest.ManifestExplorer;

/**
 * Defines the data types that are supported by this driver.
 */

public final class DataTypes
{

    static final int STRING = Types.VARCHAR;
    static final int NULL = Types.NULL;
    static final String NULL_LITERAL = "NULL"; //$NON-NLS-1$

    private static final String FLATFILE_DATA_SOURCE_ID = 
        "org.eclipse.datatools.connectivity.oda.flatfile"; //$NON-NLS-1$

    /**
     * Returns the data type code that represents the given type name.
     * @param typeName a data type name
     * @return the data type code that represents the given type name
     * @throws OdaException If the given data type name is invalid
     */
    public static int getTypeCode( String typeName ) throws OdaException
    {
        if( typeName == null || typeName.trim().length() == 0 )
            return STRING;      // default data type
              
        String preparedTypeName = typeName.trim().toUpperCase();
        
        if( preparedTypeName.equals( NULL_LITERAL ))
            return NULL;
        
        // get the data type definition from my plugin manifest for all other types
        DataTypeMapping typeMapping = getManifest().getDataSetType( null )
                                        .getDataTypeMapping( preparedTypeName );
        if( typeMapping != null )
            return typeMapping.getNativeTypeCode();

        throw new OdaException( Messages
                .getString( "dataTypes_TYPE_NAME_INVALID" ) + typeName ); //$NON-NLS-1$
    }

    /**
     * Evaluates whether the given data type name is a valid type supported by
     * this driver.
     * @param typeName	a data type name
     * @return	true if the given data type name is supported by the driver
     */
    public static boolean isValidType( String typeName )
    {
        String preparedTypeName = typeName.trim().toUpperCase();
        
        if( preparedTypeName.equals( NULL_LITERAL ))
            return true;
        
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

    private DataTypes()
    {
    }
    
    /**
     * Returns the object that represents this extension's manifest.
     * @throws OdaException
     */
    static ExtensionManifest getManifest()
        throws OdaException
    {
        return ManifestExplorer.getInstance()
                .getExtensionManifest( FLATFILE_DATA_SOURCE_ID );
    }

}

/*
 *******************************************************************************
 * Copyright (c) 2004, 2005 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial API and implementation
 *******************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.flatfile;

import java.sql.Types;
import java.util.HashMap;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.flatfile.i18n.Messages;

/**
 * Defines the data types that are supported by this driver.
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
    public static final int CLOB = Types.CLOB;
    public static final int BIGDECIMAL = Types.NUMERIC;
    public static final int NULL = Types.NULL;

    private static HashMap typeStringIntPair = new HashMap();

    static
    {
        typeStringIntPair.put( "INT", new Integer( INT ) ); //$NON-NLS-1$
        typeStringIntPair.put( "DOUBLE", new Integer( DOUBLE ) ); //$NON-NLS-1$
        typeStringIntPair.put( "STRING", new Integer( STRING ) ); //$NON-NLS-1$
        typeStringIntPair.put( "DATE", new Integer( DATE ) ); //$NON-NLS-1$
        typeStringIntPair.put( "TIME", new Integer( TIME ) ); //$NON-NLS-1$
        typeStringIntPair.put( "TIMESTAMP", new Integer( TIMESTAMP ) ); //$NON-NLS-1$
        typeStringIntPair.put( "BLOB", new Integer( BLOB ) ); //$NON-NLS-1$
        typeStringIntPair.put( "CLOB", new Integer( CLOB ) ); //$NON-NLS-1$
        typeStringIntPair.put( "BIGDECIMAL", new Integer( BIGDECIMAL ) ); //$NON-NLS-1$
        typeStringIntPair.put( "NULL", new Integer( NULL ) ); //$NON-NLS-1$
    }

    /**
     * Returns the data type code that represents the given type name.
     * @param typeName a data type name
     * @return the data type code that represents the given type name
     * @throws OdaException If the given data type name is invalid
     */
    public static int getTypeCode( String typeName ) throws OdaException
    {
        String preparedTypeName = typeName.trim().toUpperCase();
        if( typeStringIntPair.containsKey( preparedTypeName ) )
            return ( (Integer) typeStringIntPair.get( preparedTypeName ) )
                    .intValue();
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
        return typeStringIntPair.containsKey( typeName.trim().toUpperCase() );
    }

    private DataTypes()
    {
    }
}
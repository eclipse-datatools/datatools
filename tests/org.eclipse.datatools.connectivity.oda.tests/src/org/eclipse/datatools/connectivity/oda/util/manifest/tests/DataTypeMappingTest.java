/*
 *************************************************************************
 * Copyright (c) 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.util.manifest.tests;

import java.sql.Types;
import junit.framework.TestCase;
import org.eclipse.datatools.connectivity.oda.util.manifest.DataTypeMapping;

public class DataTypeMappingTest extends TestCase
{

    /**
     * Test DataTypeMapping.toOdaDataTypeCode( String );
     */
    public void testToOdaDataTypeCode( )
    {
        assertEquals( Types.CHAR, 
                DataTypeMapping.toOdaDataTypeCode( "String" ) );
        assertEquals( Types.CHAR, 
                DataTypeMapping.toOdaDataTypeCode( "STRING" ) );
        assertEquals( Types.INTEGER, 
                DataTypeMapping.toOdaDataTypeCode( "Integer" ) );
        assertEquals( Types.INTEGER, 
                DataTypeMapping.toOdaDataTypeCode( "integer" ) );
        assertEquals( Types.DOUBLE, 
                DataTypeMapping.toOdaDataTypeCode( "Double" ) );
        assertEquals( Types.DOUBLE, 
                DataTypeMapping.toOdaDataTypeCode( "DOUBLE" ) );
        assertEquals( Types.DECIMAL, 
                DataTypeMapping.toOdaDataTypeCode( "Decimal" ) );
        assertEquals( Types.DATE, 
                DataTypeMapping.toOdaDataTypeCode( "Date" ) );
        assertEquals( Types.TIME, 
                DataTypeMapping.toOdaDataTypeCode( "Time" ) );
        assertEquals( Types.TIMESTAMP, 
                DataTypeMapping.toOdaDataTypeCode( "Timestamp" ) );
        assertEquals( Types.BLOB, 
                DataTypeMapping.toOdaDataTypeCode( "Blob" ) );
        assertEquals( Types.CLOB, 
                DataTypeMapping.toOdaDataTypeCode( "Clob" ) );
    }
    
    /**
     * Test DataTypeMapping.toOdaDataTypeCode( String )
     * handling of invalid input value.
     */
    public void testInvalidOdaDataTypeLiteral( )
    {
        assertEquals( Types.NULL, 
                DataTypeMapping.toOdaDataTypeCode( null ) );
        assertEquals( Types.NULL, 
                DataTypeMapping.toOdaDataTypeCode( "" ) );
        assertEquals( Types.NULL, 
                DataTypeMapping.toOdaDataTypeCode( "  " ) );
        assertEquals( Types.NULL, 
                DataTypeMapping.toOdaDataTypeCode( "invalid" ) );
    }

}

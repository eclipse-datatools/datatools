/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.data.internal.core.common.data;

import java.sql.Timestamp;
import java.sql.Types;
import java.text.DateFormat;
import java.text.NumberFormat;





public class DataDeserializer {

    public static Object deserialize(String val, int type)
    {
        if (val==null) {
            return null;
        }

        NumberFormat intf = NumberFormat.getIntegerInstance();
        intf.setGroupingUsed(false);
        NumberFormat ff = NumberFormat.getNumberInstance();
        ff.setGroupingUsed(false);
        DateFormat df = DateFormat.getInstance();
        
        try {
            if (type==Types.TINYINT)
                return new Byte( (byte)intf.parse(val).intValue() );
            else if (type==Types.SMALLINT)
                return new Short( (short)intf.parse(val).intValue() );
            else if (type==Types.INTEGER)
                return new Integer( intf.parse(val).intValue() );
            else if (type==Types.BIGINT)            	
                return BigIntegerFormat.getInstance().parse(val);
            else if (type==Types.REAL)
                return new Float( ff.parse(val).floatValue() );
            else if (type==Types.FLOAT)
                return new Double( ff.parse(val).doubleValue() );
            else if (type==Types.DOUBLE)
                return new Double( ff.parse(val).doubleValue() );
            else if (type==Types.DECIMAL)
            	return BigDecimalFormat.getInstance().parse(val);
            else if (type==Types.NUMERIC)
            	return BigDecimalFormat.getInstance().parse(val);
            else if (type==Types.BIT)
                return new Boolean(val);
            else if (type==Types.CHAR)
                return val;
            else if (type==Types.VARCHAR)
                return val;
            else if (type==Types.LONGVARCHAR)
                return val;
            else if (type==Types.BINARY)
                return deserializeBytes(val);
            else if (type==Types.VARBINARY)
                return deserializeBytes(val);
            else if (type==Types.LONGVARBINARY)
                return deserializeBytes(val);
            else if (type==Types.DATE) {
                java.sql.Date d = new java.sql.Date(0);
                d.setTime( DateFormat.getDateInstance(DateFormat.SHORT).parse(val).getTime());
                return d;
            } else if (type==Types.TIME) {
                java.sql.Time t = new java.sql.Time(0);
                t.setTime( DateFormat.getTimeInstance(DateFormat.MEDIUM).parse(val).getTime());
                return t;
            } else if (type==Types.TIMESTAMP) {
                Timestamp t = new Timestamp(0);
                t.setTime( DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).parse(val).getTime());
                return t;
            } else if (type==Types.CLOB)
                return val;
            else if (type==Types.BLOB)
                return deserializeBytes(val);
            else if (type==Types.OTHER)
            	return val;
            else if (type==Types.ARRAY || type==Types.REF || type==Types.STRUCT || type==Types.JAVA_OBJECT)
            	return null;
            else if (type==Types.DISTINCT)
                return val;
            else
                return null;
        } catch (Exception ex) {
        	return null;   
        }
    }
    
    protected static byte[] deserializeBytes(String s)
    {
        if (s.length()%2==1)
            throw new IllegalArgumentException("Invalid binary data"); //$NON-NLS-1$
        
        byte[] bytes = new byte[s.length()/2];
        for (int i=0; i<bytes.length; ++i)
            bytes[i] = (byte)Integer.parseInt(s.substring(2*i, 2*i+2), 16);
            
        return bytes;
    }
    
}

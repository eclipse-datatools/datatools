/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.data.internal.core.common.data;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DateFormat;
import java.text.NumberFormat;

import org.eclipse.datatools.sqltools.data.internal.core.DataCorePlugin;

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
            	Date d = getDateForDate(val);
            	if (d == null)
            	{
            		return val;
            	}
            	else
            	{
            		return d;
            	}
                //java.sql.Date d = new java.sql.Date(0);
                //d.setTime( DateFormat.getDateInstance(DateFormat.SHORT).parse(val).getTime());
                //return d;
            } else if (type==Types.TIME) {
                //java.sql.Time t = new java.sql.Time(0);
                //t.setTime( DateFormat.getTimeInstance(DateFormat.MEDIUM).parse(val).getTime());
                //eturn t;
            	Time time = getDateForTime(val);
            	if (time == null)
            	{
            		return val;
            	}
            	else
            	{
            		return time;
            	}
            } else if (type==Types.TIMESTAMP) {
                //Timestamp t = new Timestamp(0);
                //t.setTime( DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).parse(val).getTime());
                //return t;
            	Timestamp timeStamp = getDateForDateTime(val);
            	if (timeStamp == null)
            	{
            		return val;
            	}
            	else
            	{
            		return timeStamp;
            	}
            } else if (type==Types.CLOB)
                return val;
            else if (type==Types.BLOB)
                return deserializeBytes(val);
            else if (type==Types.OTHER || type==DataCorePlugin.Types_SQLXML)
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
    
    /**
     * Parses the provided date value and return the Date object
     * @param val the input date value
     * @return the java.sql.Date object created or null if parse is unsuccessful
     */
    private static Date getDateForDate(String val)
    {
    	Date sqlDate = null;
    	DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
    	try
    	{
    		sqlDate = Date.valueOf(val);
    	}
    	catch(Exception ex)
    	{
    		// ignore
    	}
    	return sqlDate;
    }
    
    /**
     * Parses the provided time value and return the Time object
     * @param val the input time value
     * @return the java.sql.Time object created or null if parse is unsuccessful
     */
    private static Time getDateForTime(String val)
    {
    	Time sqlTime = null;
    	DateFormat format = DateFormat.getTimeInstance(DateFormat.MEDIUM);
    	try
    	{
    		sqlTime = Time.valueOf(val);    		
    	}
    	catch(Exception ex)
    	{
    		// ignore
    	}
    	return sqlTime;
    }
    
    /**
     * Parses the provided date/time value and return the Timestamp object
     * @param val the input date value
     * @return the java.sql.Timestamp object created or null if parse is unsuccessful
     */
    private static Timestamp getDateForDateTime(String val)
    {
    	Timestamp sqlTimestamp = null;
    	DateFormat format = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM);
    	try
    	{
    		sqlTimestamp = Timestamp.valueOf(val);    		
    	}
    	catch(Exception ex)
    	{
    		// ignore
    	}
    	return sqlTimestamp;
    }
    
}

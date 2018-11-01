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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Types;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.eclipse.datatools.sqltools.data.internal.core.DataCorePlugin;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.NumberFormat;


public class DataSerializer {

    protected static final int[] NEEDS_QUOTE = {Types.BIT, Types.BLOB, Types.CLOB,
            Types.CHAR, Types.VARCHAR, Types.LONGVARCHAR, Types.BINARY, Types.VARBINARY,
            Types.LONGVARBINARY, Types.DATE, Types.TIME, Types.TIMESTAMP, Types.OTHER, DataCorePlugin.Types_SQLXML};
    
        
    public static String serialize(Object val, int type)
    {
        if (val==null)
            return null;
        
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        NumberFormat intf = NumberFormat.getIntegerInstance();
        intf.setGroupingUsed(false);
        
        if (type==Types.TINYINT)
            return intf.format(val);
        else if (type==Types.SMALLINT)
            return intf.format(val);
        else if (type==Types.INTEGER)
            return intf.format(val);
        else if (type==Types.BIGINT)
            if (val instanceof BigInteger)
            	return BigIntegerFormat.getInstance().format((BigInteger)val);
            else 
            	return intf.format(val);
        else if (type==Types.REAL)
            return val.toString();
        else if (type==Types.FLOAT)
            return val.toString();
        else if (type==Types.DOUBLE)
            return val.toString();        	
        else if (type==Types.DECIMAL)
            if (val instanceof BigDecimal)
            	return BigDecimalFormat.getInstance().format((BigDecimal)val);
            else 
            	return val.toString();
        else if (type==Types.NUMERIC)
            if (val instanceof BigDecimal)
            	return BigDecimalFormat.getInstance().format((BigDecimal)val);
            else 
            	return val.toString();
        else if (type==Types.BIT)
            return val.toString();
        else if (type==Types.CHAR)
            return val.toString();
        else if (type==Types.VARCHAR)
            return val.toString();
        else if (type==Types.LONGVARCHAR)
            return val.toString();
        else if (type==Types.BINARY)
            return serializeBytes((byte[])val);
        else if (type==Types.VARBINARY)
            return serializeBytes((byte[])val);
        else if (type==Types.LONGVARBINARY)
            return serializeBytes((byte[])val);
        else if (type==Types.DATE)            
        	return formatDate(val);
        else if (type==Types.TIME)            
        	return formatTime(val);
        else if (type==Types.TIMESTAMP)            
        	return formatTimestamp(val);
        else if (type==Types.CLOB)
            return val.toString();
        else if (type==Types.BLOB)
            return serializeBytes((byte[])val);
        else if (type==Types.OTHER || type==DataCorePlugin.Types_SQLXML)
        	return val.toString();
        else if (type==Types.ARRAY || type==Types.REF || type==Types.STRUCT || type==Types.JAVA_OBJECT)
            return null;
        else
            return val.toString();
    }
    
    public static String write(Object val, int type, String stringDelim) {
        String s = serialize(val, type);
        if (s!=null && needsQuote(type))
            s = stringDelim + doubleStringDelim(s, stringDelim)  + stringDelim;
        return s;
    }
    
    public static String doubleStringDelim(String s, String delim)  // from DataCorePlugin
	{
        if (delim==null||delim.length()==0)
            return s;
        
        int i = s.indexOf(delim);
        if(i != -1) {
            StringBuffer sb = new StringBuffer(s);            
            int j;
            for(; i != -1; i = sb.toString().indexOf(delim, j)) {
                sb = sb.insert(i, delim);
                j = i + 2*delim.length();
            }
            return sb.toString();
        } else
            return s;
	}
    
    protected static String serializeBytes(byte[] bytes)
    {
        StringBuffer sb = new StringBuffer(bytes.length*2);
        for (int i=0; i<bytes.length; ++i) {
            int n = bytes[i];
            if (n<0)
                n += 256;
            sb.append(Integer.toString(n/16, 16));
        	sb.append(Integer.toString(n%16, 16));
        }
        return sb.toString();
    }    
    
    public static boolean needsQuote(int type)
    {
        for (int i=0; i<NEEDS_QUOTE.length; ++i)
            if (NEEDS_QUOTE[i]==type)
                return true;
        return false;
    }
    
    /**
     * Formats the supplied Date object to the proper date format
     * @param val the supplied value
     * @return the formated Date value, or echo the value if it is invalid
     */
    private static String formatDate(Object val)
    {
    	if (val == null)
    	{
    		return null;
    	}
    	if (val instanceof Date)
    	{
    		return ((Date)val).toString();
    	}
    	return val.toString();
    }
    
    /**
     * Formats the supplied Time object to the proper time format
     * @param val the supplied value
     * @return the formated Time value, or echo the value if it is invalid
     */
    private static String formatTime(Object val)
    {
    	if (val == null)
    	{
    		return null;
    	}
    	if (val instanceof Time)
    	{
    		return ((Time)val).toString();    		
    	}
    	return val.toString();
    }
    
    /**
     * Formats the supplied Date object to the proper time format
     * @param val the supplied value
     * @return the formated Date value, or echo the value if it is invalid
     */
    private static String formatTimestamp(Object val)
    {
    	if (val == null)
    	{
    		return null;
    	}
    	if (val instanceof Timestamp)
    	{
    		return ((Timestamp)val).toString();
    	}
    	return val.toString();
    }
    
}

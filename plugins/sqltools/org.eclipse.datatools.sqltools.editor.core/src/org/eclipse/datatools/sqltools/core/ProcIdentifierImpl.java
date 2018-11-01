/*******************************************************************************
 * Copyright (c) 2004, 2005, 2011 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *     Teradata Corporation - implementation of ProcIdentifier2 interface
 *******************************************************************************/
package org.eclipse.datatools.sqltools.core;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;
import org.eclipse.datatools.sqltools.internal.core.Messages;
import org.eclipse.osgi.util.NLS;

import com.ibm.icu.util.StringTokenizer;

/**
 * This interface is used to uniquely identifies a stored procedure, or trigger, or event handler, etc. Different
 * database type may use different way to identify that, such as an ID.
 * 
 * Client application should not directly create an ProcIdentifierImpl, should create it through the corresponding
 * factory.
 * 
 * @author Yang Liu
 */
public class ProcIdentifierImpl implements ProcIdentifier2
{
    private DatabaseIdentifier _database;
    private int			   	   _type;

    Map		propertyMap;

    /**
     * @param type
     * @param db
     * @param map
     */
    public ProcIdentifierImpl(int type, DatabaseIdentifier db, Map map)
    {
        this._type = type;
        this._database = db;
        if (map == null)
        {
            throw new IllegalArgumentException(Messages.ProcIdentifierImpl_map_cant_be_null); 
        }
        propertyMap = map;
    }

    public int getType()
    {
        return _type;
    }

    /**
     * get the procedure or trigger or event handler's name
     * 
     * @return
     */
    public String getProcName()
    {
        return (String) propertyMap.get(PROP_NAME);
    }

    /**
     * get the procedure or trigger or event handler's specific name
     * 
     * @return
     */
	public String getProcSpecificName() {
		/*
		 * This method was added to support overloaded routines whose specificName
		 * distinguishes routines with the same name.
		 * See BZ 171718.
		 */
		return (String) propertyMap.get(PROP_SPECIFIC_NAME);
	}

	/**
     * get owner name of the database object.
     * @return
     */
    public String getOwnerName()
    {
        return (String) propertyMap.get(PROP_OWNER);
    }

    public DatabaseIdentifier getDatabaseIdentifier()
    {
        return _database;
    }

    public String getDatabaseName()
    {
        return _database.getDBname();
    }

    public String getProfileName()
    {
        return _database.getProfileName();
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.core.ProcIdentifier#getNumber()
     */
    public int getNumber()
    {
        try
        {
            String n = (String)propertyMap.get(PROP_NUMBER);
            return Integer.parseInt(n);
        }
        catch(Exception ex)
        {
            return 0;
        }
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.core.ProcIdentifier#getTableName()
     */
    public String getTableName()
    {
        return (String) propertyMap.get(PROP_TABLENAME);
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.core.ProcIdentifier#getTableOwnerName()
     */
    public String getTableOwnerName()
    {
    	return (String) propertyMap.get(PROP_TABLEOWNERNAME);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    public int hashCode()
    {
        return getProfileName().hashCode() + getProcName().hashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj)
    {
        if (obj instanceof ProcIdentifierImpl)
        {
            ProcIdentifierImpl p = (ProcIdentifierImpl) obj;
            boolean e =
                getDatabaseIdentifier().equals(p.getDatabaseIdentifier())
                && (getType() == p.getType()) 
                && propertyMap.equals(p.propertyMap);
            return e;
        }
        return false;
    }

    /**
     * Returns whether the given object equals to this ProcIdentifier object. 
     * If the profile names are different, the comparison will base on the
     * server url.
     * @param obj
     * @return
     */
    public boolean equalsByServer(Object obj)
    {
        if (obj instanceof ProcIdentifierImpl)
        {
            ProcIdentifierImpl p = (ProcIdentifierImpl) obj;
            boolean e =
                getDatabaseIdentifier().equals(p.getDatabaseIdentifier());
                if (! e)
                {
                	ServerIdentifier si1 = ProfileUtil.getServerIdentifier(getDatabaseIdentifier());
                	ServerIdentifier si2 = ProfileUtil.getServerIdentifier(p.getDatabaseIdentifier());
                	e = si1.equals(si2);
                }
                e = e && (getType() == p.getType()) 
                && propertyMap.equals(p.propertyMap);
            return e;
        }
        return false;
    }
    
    public String toString()
    {
        return encode();
    }

    /**
     * assumes that keys don't contain '=' and '/', and values don't contain '/'
     * @param buffer
     * @param map
     */
    public static void encodeMap(StringBuffer buffer, Map map)
    {
        for (Iterator iter = map.keySet().iterator(); iter.hasNext();)
        {
            String key = (String) iter.next();
            String value = (String) map.get(key);
            buffer.append(key).append('=').append(value).append('/');
        }
    }

    public static Map decodeMap(String encoded)
    {
        Map result = new HashMap();
        StringTokenizer tokenizer = new StringTokenizer(encoded, "/");
        while (tokenizer.hasMoreTokens())
        {
            String pair = tokenizer.nextToken();
            int index = pair.indexOf('=');
            if (index != -1)
            {
                result.put(pair.substring(0,index), pair.substring(index+1));
            }
        }
        return result;
    }

    public String encode()
    {
        // TODO: currently this method is hard coded. Need to be more extendable.
        StringBuffer buffer = new StringBuffer();
        Map temp = new HashMap();
        temp.put(PROP_PROFILE, _database.getProfileName());
        temp.put(PROP_DBNAME, _database.getDBname());
        temp.put(PROP_TYPE, String.valueOf(_type));

        encodeMap(buffer, temp);
        encodeMap(buffer, propertyMap);

        return buffer.toString();
    }

    public static ProcIdentifier decode(String encoded) throws ParseException
    {
        Map map = decodeMap(encoded);
        String profile = (String)map.remove(PROP_PROFILE);
        String dbname = (String) map.remove(PROP_DBNAME);
        String typestr = (String) map.remove(PROP_TYPE);

        if (profile == null || dbname == null || typestr == null)
        {
            throw new ParseException(Messages.ProcIdentifierImpl_invalid_identifier_string, -1); 
        }

        try
        {
            int type = Integer.parseInt(typestr);
            SQLDevToolsConfiguration config = SQLToolsFacade.getConfigurationByProfileName(profile);
	        if (config != null)
	        {
	        	DBHelper h = config.getDBHelper();
	        	return h.getProcIdentifier(new DatabaseIdentifier(profile, dbname), type, map);  
	        }
            return new ProcIdentifierImpl(type, new DatabaseIdentifier(profile, dbname), map);
        }
        catch(NumberFormatException ex)
        {
            throw new ParseException(Messages.ProcIdentifierImpl_invalid_identifier_string, -1); 
        }
    }

    public static ProcIdentifier decodeWithNewProfile(String encoded,String profile) throws ParseException
    {
        Map map = decodeMap(encoded);
        map.remove(PROP_PROFILE);
        String dbname = (String) map.remove(PROP_DBNAME);
        String typestr = (String) map.remove(PROP_TYPE);

        if (profile == null || dbname == null || typestr == null)
        {
            throw new ParseException(Messages.ProcIdentifierImpl_invalid_identifier_string, -1); 
        }

        try
        {
            int type = Integer.parseInt(typestr);
            SQLDevToolsConfiguration config = SQLToolsFacade.getConfigurationByProfileName(profile);
	        if (config != null)
	        {
	        	DBHelper h = config.getDBHelper();
	            String tableOwnerName = (String)map.get(PROP_TABLEOWNERNAME);
	            if (tableOwnerName == null && type == ProcIdentifier.TYPE_TRIGGER)
	            {
	            	//assume they are the same
	            	tableOwnerName = (String)map.get(PROP_OWNER);
	            }
	        	
	        	return h.getProcIdentifier(new DatabaseIdentifier(profile, dbname), (String)map.get(PROP_NAME), type, (String)map.get(PROP_TABLENAME), (String)map.get(PROP_OWNER), tableOwnerName);  
	        }
            return new ProcIdentifierImpl(type, new DatabaseIdentifier(profile, dbname), map);
        }
        catch(NumberFormatException ex)
        {
            throw new ParseException(Messages.ProcIdentifierImpl_invalid_identifier_string, -1); 
        }
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.core.ProcIdentifier#getDisplayString()
     */
    public String getDisplayString()
    {
        int number = getNumber();
        if (number > 1) return getProcName()+";"+number;
        else return getProcName();
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.core.ProcIdentifier#getLongDisplayString()
     */
    public String getLongDisplayString()
    {
        String s = "";

        if (getType() == TYPE_TRIGGER && getTableName() != null && getTableName().length() > 0)
        {
        	String tableOwner = getTableOwnerName();
        	if (tableOwner == null)
        	{
        		tableOwner = getOwnerName();
        	}
        	s = NLS.bind(Messages.ProcIdentifierImpl_trigger_long_display_string, new Object[]{getProfileName(), getDatabaseName(), tableOwner, getTableName(), getDisplayString()});
        }
        else
        {
            s = NLS.bind(Messages.ProcIdentifierImpl_long_display_string, new Object[]{getProfileName(), getDatabaseName(), getOwnerName(), getDisplayString()});
        }
        return s;
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.core.ProcIdentifier#getCallableStringWithoutGroupNumber()
     */
    public String getCallableStringWithoutGroupNumber()
    {
        return getCallableStringWithoutGroupNumber(true);
    }

    /**
     * @return
     */
    public String getCallableString()
    {
        return getCallableString(true);
    }

    public void setProfileName(String profileName)
    {
        _database.setProfileName(profileName);
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.core.ProcIdentifier#getCallableString(boolean)
     */
    public String getCallableString(boolean quoted_id)
    {
        String s = getCallableStringWithoutGroupNumber(quoted_id);
        int number = getNumber();
        if (number > 0)
        {
            return s + ";" + number;

        }
        else
        {
            return s;
        }
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.core.ProcIdentifier#getCallableStringWithoutGroupNumber(boolean)
     */
    public String getCallableStringWithoutGroupNumber(boolean quoted_id)
    {
        /**
         * modify this method in CR378414 since user can configure the "quoted_identifier" option
         */
        String ownerName = null;
        String procName = null;
        String db = _database.getDBname();
		db = SQLDevToolsUtil.quoteWhenNecessary(db, new DatabaseIdentifier(_database.getProfileName(), db));
        if (quoted_id)
        {
            ownerName = SQLDevToolsUtil.quoteWhenNecessary(getOwnerName(), _database);
            procName = SQLDevToolsUtil.quoteWhenNecessary(getProcName(), _database);
        }
        else
        {
            ownerName = getOwnerName();
            procName= getProcName();
        }

        if (db != null && db.length() > 0)
        {
            return db + "." + ownerName + "." + procName;
        }
        else if (ownerName != null && ownerName.length() > 0)
        {
            return ownerName + "." + procName;
        }
        else
        {
            return procName;
        }
    }

}

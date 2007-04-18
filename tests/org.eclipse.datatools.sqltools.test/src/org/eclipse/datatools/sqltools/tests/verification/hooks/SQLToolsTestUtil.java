
/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.datatools.sqltools.tests.verification.hooks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

import org.eclipse.datatools.connectivity.ConnectionProfileConstants;
import org.eclipse.datatools.connectivity.ConnectionProfileException;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.ProfileManager;
import org.eclipse.datatools.connectivity.drivers.DriverInstance;
import org.eclipse.datatools.connectivity.drivers.DriverManager;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;

/**
 * @author Hui Cao
 */
public class SQLToolsTestUtil 
{

	public final static int      DEFAULT_FILE_SIZE                = 15 * 1024;
	
    public static void createProfile(Properties props) 
    {

        String dbUrl = getProperties(props,"url");
        DriverInstance driver = DriverManager.getInstance().createNewDriverInstance(
            getProperties(props,"driver_template_id"),getProperties(props,"driver_name"),getProperties(props,"driver_jar_list"));

        Properties prop = new Properties();

        prop.setProperty(ConnectionProfileConstants.PROP_DRIVER_DEFINITION_ID, driver.getId());
        prop.setProperty(ProfileUtil.DRIVERCLASS, getProperties(props,"driver_class"));
        prop.setProperty(ProfileUtil.PROP_DB_CONN_PROPS, getProperties(props,"profile_id"));
        prop.setProperty(ProfileUtil.UID,getProperties(props,"username"));
        prop.setProperty(ProfileUtil.PWD, getProperties(props,"password"));
        prop.setProperty(ProfileUtil.URL, dbUrl);
        prop.setProperty(ProfileUtil.DATABASENAME, getProperties(props,"databasename"));
        prop.setProperty(ProfileUtil.DRIVER_DB_VERSION, getProperties(props,"version"));
        prop.setProperty(ProfileUtil.DRIVER_DB_VENDOR_NAME, getProperties(props,"vendor"));
        prop.setProperty(ProfileUtil.PROP_DB_CONN_PROPS, getProperties(props,"connection_prop"));
        try 
        {
        	//create a profile with above properties
        	ProfileManager.getInstance().createProfile(
        			getProperties(props,"Profile"),
        			getProperties(props,"profiledesc"),
        			getProperties(props,"profile_id"), prop);
        	ProfileManager.getInstance().getProfileByName(getProperties(props,"Profile")).connect();
        }
        catch (ConnectionProfileException e) 
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static String[] getProperties(Properties props,String key, String delimiter) 
    {

        String value = props.getProperty(key);
        if (value == null) 
        {
            System.out.println("No value defined for key  " + key);
            System.out.println("Pls check the Props file");
            return null;
        }
        else 
        {
            return value.split(delimiter);
        }

    }

    public static String getProperties(Properties props,String key) 
    {
        String value = props.getProperty(key);
        if (value == null) 
        {
            System.out.println("No value defined for key  " + key);
            System.out.println("Pls check the Props file");
        }
        return value;
    }

    public static void loadProps(URL url,Properties props) throws Exception 
    {
        InputStream inputStream = null;
        if (url != null) 
        {
            inputStream = url.openStream();
        }
        else 
        {
            System.out.println("Props File not Found");
        }
        props.load(inputStream);
    }

    public static void deleteProfile(String profileName)
    {
        IConnectionProfile profile = ProfileManager.getInstance().getProfileByName(profileName);
        Messages.log("delete profile check point 1:" + profileName);
        if (profile != null)
        {
            profile.disconnect();
            Messages.log("delete profile check point 2:" + "disconnected");
            try 
            {
            	//TODO temparorily comment out this line to avoid deadlock
                //ProfileManager.getInstance().deleteProfile(profile);
                Messages.log("delete profile check point 3: deleted");
            }
            catch (Exception e) 
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        Messages.log("delete profile check point 4: return");
    }
    
    public static String readFile(URL file)
    {
    	BufferedReader in = null;
    	try {
			in = new BufferedReader(new InputStreamReader(file.openStream()), DEFAULT_FILE_SIZE);
			StringBuffer buffer = new StringBuffer(DEFAULT_FILE_SIZE);
			char[] readBuffer = new char[2048];
			int n = in.read(readBuffer);
			while (n > 0)
			{
			    buffer.append(readBuffer, 0, n);
			    n = in.read(readBuffer);
			}
			return buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			if (in != null)
			{
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return "";
    }
}

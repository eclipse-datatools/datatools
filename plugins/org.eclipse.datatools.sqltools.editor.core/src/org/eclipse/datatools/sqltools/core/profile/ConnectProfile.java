/*
 * Created on Mar 17, 2004 To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.datatools.sqltools.core.profile;

import java.util.Properties;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;

/**
 * Connection profile is used to describe how to connect to a particular database. It contains a DatabaseIdentifier to
 * identify which database to connect, and username/password information for connecting to that database.
 * TODO CONN check CP constants
 * @author Yang Liu
 */
public class ConnectProfile
{
    String _serverType; // ASA or ASE
    String _name;
    String _host;
    int    _port;
    String _dbname;

    String _user;
    String _pass;
    String _url;
    String _providerId;

    /**
     * constructor.
     * 
     * @param di can't be null
     * @param user
     * @param pass
     */
    public ConnectProfile(String serverType, String host, int port, String dbname, String user, String pass)
    {
        _serverType = serverType;
        _host = host;
        _port = port;
        _dbname = dbname;
        _user = user;
        _pass = pass;
    }

    public ConnectProfile(IConnectionProfile profile)
    {
        Properties props = profile.getBaseProperties();
        _serverType = props.getProperty(ProfileUtil.DRIVERDEFINITIONID);
        _name = profile.getName();
//        _host = props.getProperty(ProfileUtil.HOST);
//        try
//        {
//            _port = Integer.parseInt(props.getProperty(ProfileUtil.PORT));
//        }
//        catch (Exception ex)
//        {
//            _port = 0;
//            //do nothing for now, PB plugin should give us a valid string which we can convert to number
//        }
        _dbname = props.getProperty(ProfileUtil.DATABASENAME);
        _user = props.getProperty(ProfileUtil.UID);
        _pass = props.getProperty(ProfileUtil.PWD);
        _url = props.getProperty(ProfileUtil.URL);
        _providerId = profile.getProviderId();
    }

    /**
     * to which database
     * 
     * @return
     */
    public String getServerType()
    {
        return _serverType;
    }

    public String getDbName()
    {
        return _dbname;
    }

    /**
     * get password
     * 
     * @return
     */
    public String getPass()
    {
        return _pass;
    }

    /**
     * get user name
     * 
     * @return
     */
    public String getUser()
    {
        return _user;
    }

    /**
     * @return
     */
    public int getPort()
    {
        return _port;
    }

    public String getHost()
    {
        return _host;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object profile)
    {
        if (!(profile instanceof ConnectProfile))
        {
            return false;
        }
        ConnectProfile p = (ConnectProfile) profile;
        //a generic jdbc profile
//        if (this._providerId.equals(ProfileUtil.PROVIDERID)
//        || p.getProviderId().equals(ProfileUtil.PROVIDERID))
//        {
//            if (p.getProviderId().equals(this._providerId) && p.getUrl().equals(this._url)
//            && p.getUser().equals(this._user) && p.getName().equals(this._name))
//            {
//                return true;
//            }
//            else
//            {
//                return false;
//            }
//        }


        try{
	        //If profile type is database(ASE, ASA, ASIQ), database name will be compared.
	        //Other profiles (for example, Replication Server) doesn't need to compare database name.
	        //Modified by Daniel Huang
	        boolean equals = p.getProviderId().equals(this._providerId);
	        equals = equals && ((p.getHost() == null && this._host == null) || (p.getHost() != null && p.getHost().equals(this._host)));
	        equals = equals && (p.getPort() == this._port);
	        equals = equals && ((p.getServerType() == null && this._serverType == null) || (p.getServerType() != null && p.getServerType().equals(this._serverType)));
	        equals = equals && ((p.getUser() == null && this._user == null) || (p.getUser() != null && p.getUser().equals(this._user)));
	        equals = equals && (p.getName().equals(_name));
	        if (equals)
	        {
	            if (ProfileUtil.isDatabaseProfile(p)||ProfileUtil.isDatabaseProfile(this))
	            {
	
	                if (_dbname != null && this._dbname.equals(p.getDbName()))
	                {
	                    return true;
	                }
	                else if (_dbname == null && p.getDbName() == null)
	                {
	                    return true;
	                }
	                else
	                {
	                    return false;
	                }
	            }
	            return true;
	        }
        }catch(Exception e)
        {
        	EditorCorePlugin.getDefault().log(e);
        }
        return false;
    }

    /**
     * @return Returns the _name.
     */
    public String getName()
    {
        return _name;
    }

    /**
     * @param _name The _name to set.
     */
    public void setName(String name)
    {
        this._name = name;
    }

    public boolean isOnlyNameChanged(ConnectProfile p)
    {
        if (!(this._name.equals(p.getName())))
        {
//            if (this._providerId.equals(ProfileUtil.PROVIDERID)
//            || p.getProviderId().equals(ProfileUtil.PROVIDERID))
//            {
//                if (p.getProviderId().equals(this._providerId) && p.getUrl().equals(this._url)
//                && p.getUser().equals(this._user))
//                {
//                    return true;
//                }
//                else
//                {
//                    return false;
//                }
//            }

            //If profile type is database(ASE, ASA, ASIQ), database name will be compared.
            //Other profiles (for example, Replication Server) doesn't need to compare database name.
            //Modified by Daniel Huang
            boolean equals = p.getProviderId().equals(this._providerId);
            equals = equals && ((p.getHost() == null && this._host == null) || (p.getHost() != null && p.getHost().equals(this._host)));
            equals = equals && (p.getPort() == this._port);
            equals = equals && ((p.getServerType() == null && this._serverType == null) || (p.getServerType() != null && p.getServerType().equals(this._serverType)));
            equals = equals && ((p.getUser() == null && this._user == null) || (p.getUser() != null && p.getUser().equals(this._user)));
            if (equals)
            {
                if (ProfileUtil.isDatabaseProfile(p)||ProfileUtil.isDatabaseProfile(this))
                {
                    if(p.getDbName() == null)
                    {
                        if(this._dbname == null)
                        {
                            return true;
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else
                    {
                        if (p.getDbName().equals(this._dbname))
                        {
                            return true;
                        }
                        else
                        {
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }
    /**
	 * @return Returns the _url.
	 */
    public String getUrl()
    {
        return _url;
    }
    /**
     * @param _url The _url to set.
     */
    public void setUrl(String url)
    {
        this._url = url;
    }
    /**
     * @return Returns the _providerId.
     */
    public String getProviderId()
    {
        return _providerId;
    }
    /**
     * @param id The _providerId to set.
     */
    public void setProviderId(String id)
    {
        _providerId = id;
    }
}

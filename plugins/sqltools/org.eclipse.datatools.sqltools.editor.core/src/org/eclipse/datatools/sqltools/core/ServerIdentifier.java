/*
 * Created on 2004-6-24 Copyright Sybase, Inc. All rights reserved.
 */
package org.eclipse.datatools.sqltools.core;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.eclipse.core.runtime.Assert;


/**
 * A server identifier is used to uniquely identify a server.
 * 
 * @author Yang Liu
 * @author Hui Cao
 */
public class ServerIdentifier
{
    private DatabaseVendorDefinitionId _dbIdentifier;
    private String            _host;
    private String            _port;
    private String            _url;
    private String            _protocol;

    public ServerIdentifier(String host, String port, String url, DatabaseVendorDefinitionId dbIdentifier)
    {
        Assert.isTrue(url != null && url.matches(".*:.*"));
        ArrayList result = parseUrl(url);

        // the given parameters may be null
        _host = host != null ? host.toLowerCase() : null;
        _port = port;

        // since we don't support all databases, the result may be null
        if (result != null)
        {
            if (host == null || port == null)
            {
                _host = (String) result.get(1);
                _port = (String) result.get(2);
            }
            _protocol = (String)result.get(0);
        }
        else
        {
            _protocol = "";
        }

        // make sure they are not null
        _host = _host == null ? "" : _host;
        _port = _port == null ? "" : _port;
        this._url = url;
        this._dbIdentifier = dbIdentifier;
    }

    public String getHost()
    {
        return _host;
    }

    public String getPort()
    {
        return _port;
    }

    public String getUrl()
    {
        return _url;
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof ServerIdentifier)
        {
            ServerIdentifier si = (ServerIdentifier) obj;
            String host1 = _host;
            String host2 = si._host;

            String port1 = _port;
            String port2 = si._port;

            String protocol1 = _protocol;
            String protocol2 = si._protocol;

            try
            {
                InetAddress addr1 = InetAddress.getByName(host1);
                InetAddress addr2 = InetAddress.getByName(host2);

                // If the loop back address is specified, we should get the IP address
                if (addr1.isLoopbackAddress())
                {
                    addr1 = InetAddress.getLocalHost();
                }
                if (addr2.isLoopbackAddress())
                {
                    addr2 = InetAddress.getLocalHost();
                }

                // convert to IP address
                host1 = addr1.getHostAddress();
                host2 = addr2.getHostAddress();
            }
            catch (UnknownHostException e)
            {
            	//EditorCorePlugin.getDefault().log(e);
            }

            if (protocol1.equals(protocol2) && host1.equals(host2) && port1.trim().equals(port2.trim()))
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
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    public int hashCode()
    {
        try
        {
            InetAddress addr1 = InetAddress.getByName(_host);

            // If the loop back address is specified, we should get the IP address
            if (addr1.isLoopbackAddress())
            {
                addr1 = InetAddress.getLocalHost();
            }

            // convert to IP address
            String host1 = addr1.getHostAddress();
            return host1.hashCode(); 
        }
        catch (UnknownHostException e)
        {
        	//EditorCorePlugin.getDefault().log(e);
        }
        return _host.hashCode();
    }

    public DatabaseVendorDefinitionId getDatabaseVendorDefinitionId()
    {
        return _dbIdentifier;
    }

    /**
     * Parses the url and put protocol, host and port into an ArrayList. Since different database may have different
     * format of url, the default implementation only works with the common url format: protocol:host:port/database?properties
     * 
     * @param url for Sybase databases, it is in the form of: protocol:host:port/database?properties, notice that
     *            protocol itself can contain semicolons
     */
    public ArrayList parseUrl(String url1)
    {
        Assert.isTrue(url1 != null && url1.matches(".*:.*"));
        try
        {
            ArrayList result = new ArrayList();
            // ignore properties
            if (url1.indexOf("?") > 0)
            {
                url1 = url1.substring(0, url1.indexOf("?"));
            }
            int end = url1.length();
            if (url1.indexOf('/') > 0)
            {
                end = url1.lastIndexOf('/');
            }
            String port1 = "";
            try
            {
                int lastColon = url1.lastIndexOf(':') + 1;
                if (lastColon < end)
                {
                    port1 = url1.substring(lastColon, end);
                }
                Integer.parseInt(port1);
                url1 = url1.substring(0, url1.lastIndexOf(':'));
            }
            catch (NumberFormatException e)
            {
                port1 = "";
            }

            String host1 = url1.substring(url1.lastIndexOf(':') + 1);

            if (host1.startsWith("//"))
            {
                host1 = host1.substring(2);
            }
            String protocol1 = url1.substring(0, url1.lastIndexOf(':'));
            result.add(protocol1);
            result.add(host1);
            result.add(port1);
            return result;
        }
        catch (Exception e)
        {
        	EditorCorePlugin.getDefault().log(e);
            return null;
        }
    }
    
}

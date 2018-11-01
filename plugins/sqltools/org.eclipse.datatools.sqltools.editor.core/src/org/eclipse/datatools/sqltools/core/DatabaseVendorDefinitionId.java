/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.core;

import java.util.Comparator;

import org.eclipse.core.runtime.Assert;

/**
 * A class to uniquely identify a database definition, represented by product name and version.
 * 
 * @author Hui Cao
 * 
 */
public class DatabaseVendorDefinitionId implements Comparable
{
    /**
     * A string comparator which is aware of version number
     */
    public static class VersionComparator implements Comparator
    {
        public int compare(Object left, Object right)
        {
            // we regard unknown versions as the latest ones
            if (left == null && right == null)
            {
                return 0;
            }
            else if (left == null)
            {
                return 1;
            }
            else if (right == null)
            {
                return -1;
            }

            String leftString = ((String) left).toLowerCase();
            String rightString = ((String) right).toLowerCase();

            int result = 0;

            String[] ls = leftString.split("\\Q.\\E");
            String[] rs = rightString.split("\\Q.\\E");
            int length = Math.min(ls.length, rs.length);
            for (int i = 0; i < length; i++)
            {
                if (ls[i].indexOf("x") >= 0 || rs[i].indexOf("x") >= 0)
                {
                    return 0;
                }
                else
                {
                    result = ls[i].compareTo(rs[i]);
                    if (result != 0)
                    {
                        // regard "12.0" and "12.0.1" as equal
                        break;
                    }
                }
            }

            return result;
        }
    }

    private static VersionComparator _versionComparator = new VersionComparator();
    private String                   _vendorName;
    private String                   _version;
    //TODO: Add productDisplayString and versionDisplayString attributes

    /**
     * Constructs the <code>DatabaseVendorDefinitionId</code> object by product name and version.
     */
    public DatabaseVendorDefinitionId(String productName, String version)
    {
        super();
        this._vendorName = productName;
        this._version = version;
    }

    /**
     * Constructs the <code>DatabaseVendorDefinitionId</code> object by product name + "_" + version.
     * @param dbDefName product name + "_" + version.
     */
    public DatabaseVendorDefinitionId(String dbDefName)
    {
    	Assert.isTrue(dbDefName != null );
    	int underscore = dbDefName.lastIndexOf('_');
    	if (underscore > 0)
    	{
        	this._vendorName = dbDefName.substring(0, dbDefName.lastIndexOf('_'));
        	this._version = dbDefName.substring(dbDefName.lastIndexOf('_')+1);
    	}
    	else
    	{
        	this._vendorName = dbDefName;
        	this._version = "";
    	}
    }
    
    /**
     * Returns the product name
     */
    public String getProductName()
    {
        return _vendorName;
    }

    /**
     * Returns the version
     */
    public String getVersion()
    {
        return _version;
    }


    /**
     * Override hashCode to make this object be able to serve as key in a HashMap. 
     */
    public int hashCode()
    {
        //since version may have different ways of expression, only use _vendorName's hash code.
        return _vendorName.hashCode();
    }

    /**
     * Returns true if the product names are equal and versions are compatible.
     */
    public boolean equals(Object obj)
    {
        if (!(obj instanceof DatabaseVendorDefinitionId))
        {
            return false;
        }
        DatabaseVendorDefinitionId v2 = (DatabaseVendorDefinitionId) obj;
        boolean sameName = (getProductName() == v2.getProductName())
            || (getProductName() != null && getProductName().equals(v2.getProductName()));
        return sameName && _versionComparator.compare(getVersion(), v2.getVersion()) == 0;
    }

    /**
     * name + "_" + version
     */
    public String toString()
    {
        if (_version != null && !_version.equals(""))
        {
            return _vendorName + "_" + _version;
        }
        return _vendorName;
    }

    /**
     * Override compareTo to make this object be able to serve as key in a TreeMap. 
     */
    public int compareTo(Object o)
    {
        DatabaseVendorDefinitionId v2 = (DatabaseVendorDefinitionId) o;
        int result = 0;
        if (getProductName() != null && v2.getProductName() != null)
        {
            result = getProductName().compareTo(v2.getProductName());
        }
        if (result == 0)
        {
            result = _versionComparator.compare(getVersion(), v2.getVersion());
        }
        return result;
    }


}

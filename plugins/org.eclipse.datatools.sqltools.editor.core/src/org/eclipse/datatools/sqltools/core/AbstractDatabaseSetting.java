/*
 * Created on 2005-3-15
 * 
 * Copyright (c) Sybase, Inc. 2004 All rights reserved.
 */
package org.eclipse.datatools.sqltools.core;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;

/**
 * @author Hui Cao
 *  
 */
public abstract class AbstractDatabaseSetting implements IDatabaseSetting
{
    private DatabaseIdentifier          _databaseIdentifier = null;
    //some properties can be cached, subclasses are responsible to store them in this field
    protected HashMap       _cache       = new HashMap();

    /**
     * @param name
     */
    public AbstractDatabaseSetting(DatabaseIdentifier databaseIdentifier)
    {
        super();
        _databaseIdentifier = databaseIdentifier;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.core.IDatabaseSetting#getProperty(java.lang.String)
     */
    public Object getProperty(String propName) throws NotSupportedSettingException
    {
        if (_cache.get(propName) != null)
        {
            return _cache.get(propName);
        }

        Connection connection = null;
        Statement stmt = null;
        try
        {
			connection = ProfileUtil.getReusableConnection(_databaseIdentifier);
            stmt = prepareStatement(connection, propName);
            return handleStatement(stmt, propName);
        }
        catch (NotSupportedSettingException e)
        {
            throw e;
        }
        catch (SQLException ex)
        {
        	EditorCorePlugin.getDefault().log(ex);
            return getDefaultValue(stmt, propName);
        }
        catch (Exception e)
        {
        	EditorCorePlugin.getDefault().log(e);
            return getDefaultValue(stmt, propName);
        }
        finally
        {
            if (stmt != null)
            {
                try
                {
                    stmt.close();
                }
                catch (Exception e)
                {
                    // ignore
                    
                }
            }

        }
    }

    protected abstract Statement prepareStatement(Connection connection, String propName) throws SQLException,
    NotSupportedSettingException;

    protected abstract Object handleStatement(Statement stmt, String propName) throws SQLException,
    NotSupportedSettingException;

    protected Object getDefaultValue(Statement stmt, String propName)
    {
        return null;
    }

}

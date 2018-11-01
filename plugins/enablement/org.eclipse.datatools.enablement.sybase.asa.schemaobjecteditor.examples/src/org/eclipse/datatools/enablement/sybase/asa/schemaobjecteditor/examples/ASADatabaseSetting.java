/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.sqltools.core.AbstractDatabaseSetting;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.IDatabaseSetting;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.jface.preference.IPreferenceStore;


/**
 * @author Hui Cao
 *  
 */
public class ASADatabaseSetting extends AbstractDatabaseSetting implements IDatabaseSetting
{
    /**
     * @param databaseIdentifier
     */
    public ASADatabaseSetting(DatabaseIdentifier databaseIdentifier)
    {
        super(databaseIdentifier);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.core.AbstractDatabaseSetting#prepareStatement(java.sql.Connection,
     *      java.lang.String)
     */
    protected Statement prepareStatement(Connection connection, String propName) throws SQLException,
        NotSupportedSettingException
    {
        if (P_CASE_SENSITIVE.equals(propName))
        {
            PreparedStatement stmt = connection.prepareStatement("SELECT DB_PROPERTY( ? )");
            stmt.setString(1, "CaseSensitive");
            return stmt;
        }
        throw new NotSupportedSettingException(propName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.core.AbstractDatabaseSetting#handleStatement(java.sql.Statement, java.lang.String)
     */
    protected Object handleStatement(Statement stmt, String propName) throws SQLException,
        NotSupportedSettingException
    {
        if (stmt instanceof PreparedStatement)
        {
            if (P_CASE_SENSITIVE.equals(propName))
            {
                boolean result = ((PreparedStatement) stmt).execute();
                //get the 1st resultset
                if (result)
                {
                    ResultSet rs = stmt.getResultSet();
                    try
                    {
                        rs.next();
                        String on = rs.getString(1);
                        Object ret = new Boolean(on.equals("ON"));
                        _cache.put(propName, ret);
                        return ret;
                    }
                    finally
                    {
                        if (rs != null)
                        {
                            rs.close();
                        }
                    }
                }
            }

        }
        throw new NotSupportedSettingException(propName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.sybase.stf.dmp.core.AbstractDatabaseSetting#getDefaultValue(java.sql.Statement, java.lang.String)
     */
    protected Object getDefaultValue(Statement stmt, String propName)
    {
        if (P_CASE_SENSITIVE.equals(propName))
        {
            return Boolean.FALSE;
        }
        return super.getDefaultValue(stmt, propName);
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.core.IDatabaseSetting#getProperty(org.eclipse.debug.internal.core.LaunchConfiguration, java.lang.String)
     */
    public Object getLaunchConfigProperty(ILaunchConfiguration lc, String propName) throws NotSupportedSettingException
    {
        try
        {
            if (C_QUOTED_IDENTIFIER.equals(propName))
            {
                boolean defValue = false;
                Boolean conValue = (Boolean)getConnectionConfigProperty(propName);
                if (conValue != null)
                {
                    defValue = conValue.booleanValue();
                }
                return new Boolean(lc.getAttribute(LaunchConfigurationAttribute.DMP_LAUNCH_ASA_QUOTED_IDEN, defValue));
            }
            if (C_CHAINED_MODE.equals(propName))
            {
                boolean defValue = false;
                Boolean conValue = (Boolean)getConnectionConfigProperty(propName);
                if (conValue != null)
                {
                    defValue = conValue.booleanValue();
                }
                return new Boolean(lc.getAttribute(LaunchConfigurationAttribute.DMP_LAUNCH_ASA_CHAINED, defValue));
            }
        }
        catch (CoreException e)
        {
        	ExamplePlugin.getDefault().getLog().log(new Status(IStatus.ERROR, ExamplePlugin.PLUGIN_ID, Messages.NotSupportedSettingException_cause));
            throw new NotSupportedSettingException(propName);
        }
        return null;
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.core.IDatabaseSetting#getConnectionConfigProperty(java.lang.String)
     */
    public Object getConnectionConfigProperty(String propName) throws NotSupportedSettingException
    {
        IPreferenceStore store = ExamplePlugin.getDefault().getPreferenceStore();
        if (C_QUOTED_IDENTIFIER.equals(propName))
        {
            return new Boolean(store.getBoolean(LaunchConfigurationAttribute.DMP_LAUNCH_ASA_QUOTED_IDEN));
        }
        if (C_CHAINED_MODE.equals(propName))
        {
            return new Boolean(store.getBoolean(LaunchConfigurationAttribute.DMP_LAUNCH_ASA_CHAINED));
        }
        return null;
    }

}

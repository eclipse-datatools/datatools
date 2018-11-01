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

import org.eclipse.datatools.sqltools.internal.core.Messages;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.osgi.util.NLS;

/**
 * Holds database configuration properties. Database level configurations will be retrieved from server by using
 * getProperty(String propName) and are named after "P_"; Launch level configurations will be retrieved from an eclipse
 * launch configuration by using getLaunchConfigProperty(LaunchConfiguration lc, String propName) and are named after
 * "C_";Connection level configurations will be retrieved from an eclipse preference store by using
 * getConnectionConfigProperty( String propName) and are named after "C_".
 * 
 * @author Hui Cao
 * 
 */
public interface IDatabaseSetting
{
    public static class NotSupportedSettingException extends Exception
    {

        /**
         * 
         */
        private static final long serialVersionUID = 3604871803701398008L;

        /**
         * @param propName the unsupported configuration name
         */
        public NotSupportedSettingException(String propName)
        {
            super(NLS.bind(Messages.NotSupportedSettingException_cause, propName));
        }
    }

    // TODO: add more properties

    /**
     * Whether the database is configured as case sensitive( boolean value )
     */
    public static final String P_CASE_SENSITIVE    = "P_CASE_SENSITIVE";

    /**
     * Whether double string is regarded as quoted identifier in connection level
     */
    public static final String C_QUOTED_IDENTIFIER = "C_QUOTED_IDENTIFIER";
    public static final String C_CHAINED_MODE      = "C_CHAINED_MODE";
    
    /**
     * Retrieves the database level configuration value
     * 
     * @param propName the property name
     * @return the database level configuration value
     * @throws NotSupportedSettingException
     */
    public Object getProperty(String propName) throws NotSupportedSettingException;

    /**
     * Return launch level configuration value, using getConnectionConfigProperty(String propName) as default
     * 
     * @param lc the launch configuration used to get properties
     * @param propName the property name
     * @return property value
     * @throws NotSupportedSettingException
     */
    public Object getLaunchConfigProperty(ILaunchConfiguration lc, String propName)
            throws NotSupportedSettingException;

    /**
     * Return connection level configuration value
     * 
     * @param propName the property name
     * @return property value
     * @throws NotSupportedSettingException
     */
    public Object getConnectionConfigProperty(String propName) throws NotSupportedSettingException;

}

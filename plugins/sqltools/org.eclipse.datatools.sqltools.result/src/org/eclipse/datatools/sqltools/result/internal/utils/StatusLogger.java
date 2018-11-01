/*******************************************************************************
 * Copyright (c) 2005 Sybase, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.result.internal.utils;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * Using Eclipse logger mechanism to log
 * 
 * @author Dafan Yang
 */
public class StatusLogger implements ILogger
{
    private ILog           _log;
    private ResourceBundle _rb;
    private String         _pluginId;
    
    public StatusLogger(ILog log, String pluginId, ResourceBundle bundle)
    {
        _log = log;
        _pluginId = pluginId;
        _rb = bundle;
    }
    
    public void fatal(Object message)
    {
        fatal(message, null); 
    }

    public void fatal(Object message, Throwable tt)
    {
        IStatus status = new Status(IStatus.ERROR, _pluginId, IStatus.OK, (String)message, tt);
        _log.log(status);  
    }
    
    public void fatal(String key)
    {
        IStatus status = new Status(IStatus.ERROR, _pluginId, IStatus.OK, getResourceBundleString(key), null);
        _log.log(status);        
    }

    public void fatal(String key, Throwable tt)
    {
        IStatus status = new Status(IStatus.ERROR, _pluginId, IStatus.OK, getResourceBundleString(key), tt);
        _log.log(status);   
    }

    public void fatal(String key, Object arg0)
    {
        error(key, arg0, null);
    }

    public void fatal(String key, Object arg0, Throwable tt)
    {
        Object[] args = new Object[1];
        args[0] = arg0;
        MessageFormat formatter = new MessageFormat(getResourceBundleString(key));        
        IStatus status = new Status(IStatus.ERROR, _pluginId, IStatus.OK, formatter.format(args), tt);
        _log.log(status);  
    }

    public void fatal(String key, Object arg0, Object arg1)
    {
        error(key, arg0, arg1, null);
    }

    public void fatal(String key, Object arg0, Object arg1, Throwable tt)
    {
        Object[] args = new Object[2];
        args[0] = arg0;
        args[1] = arg1;
        MessageFormat formatter = new MessageFormat(getResourceBundleString(key));        
        IStatus status = new Status(IStatus.ERROR, _pluginId, IStatus.OK, formatter.format(args), tt);
        _log.log(status);  
    }

    public void fatal(String key, Object arg0, Object arg1, Object arg2)
    {   
        error(key, arg0, arg1, arg2, null);
    }

    public void fatal(String key, Object arg0, Object arg1, Object arg2, Throwable tt)
    {
        Object[] args = new Object[3];
        args[0] = arg0;
        args[1] = arg1;
        args[2] = arg2;
        MessageFormat formatter = new MessageFormat(getResourceBundleString(key));        
        IStatus status = new Status(IStatus.ERROR, _pluginId, IStatus.OK, formatter.format(args), tt);
        _log.log(status);  
    }

    public void fatal(String key, Object arg0, Object arg1, Object arg2, Object arg3)
    {
        error(key, arg0, arg1, arg2, arg3, null);
    }

    public void fatal(String key, Object arg0, Object arg1, Object arg2, Object arg3, Throwable tt)
    {
        Object[] args = new Object[4];
        args[0] = arg0;
        args[1] = arg1;
        args[2] = arg2;
        args[3] = arg3;
        MessageFormat formatter = new MessageFormat(getResourceBundleString(key));        
        IStatus status = new Status(IStatus.ERROR, _pluginId, IStatus.OK, formatter.format(args), tt);
        _log.log(status); 
    }

    public void fatal(String key, Object arg0, Object arg1, Object arg2, Object arg3, Object arg4)
    {
        error(key, arg0, arg1, arg2, arg3, arg4, null);
    }

    public void fatal(String key, Object arg0, Object arg1, Object arg2, Object arg3, Object arg4, Throwable tt)
    {
        Object[] args = new Object[5];
        args[0] = arg0;
        args[1] = arg1;
        args[2] = arg2;
        args[3] = arg3;
        args[4] = arg4;
        MessageFormat formatter = new MessageFormat(getResourceBundleString(key));        
        IStatus status = new Status(IStatus.ERROR, _pluginId, IStatus.OK, formatter.format(args), tt);
        _log.log(status); 
    }

    public void fatal(String key, Object[] args)
    {
        error(key, args, null);
    }

    public void fatal(String key, Object[] args, Throwable tt)
    {
        MessageFormat formatter = new MessageFormat(getResourceBundleString(key));        
        IStatus status = new Status(IStatus.ERROR, _pluginId, IStatus.OK, formatter.format(args), tt);
        _log.log(status);
    }

    public void error(Object message)
    {
        fatal(message, null); 
    }

    public void error(Object message, Throwable tt)
    {
        IStatus status = new Status(IStatus.ERROR, _pluginId, IStatus.OK, (String)message, tt);
        _log.log(status);  
    }

    public void error(String key)
    {
        IStatus status = new Status(IStatus.ERROR, _pluginId, IStatus.OK, getResourceBundleString(key), null);
        _log.log(status);        
    }

    public void error(String key, Throwable tt)
    {
        IStatus status = new Status(IStatus.ERROR, _pluginId, IStatus.OK, getResourceBundleString(key), tt);
        _log.log(status);   
    }

    public void error(String key, Object arg0)
    {
        error(key, arg0, null);
    }

    public void error(String key, Object arg0, Throwable tt)
    {
        Object[] args = new Object[1];
        args[0] = arg0;
        MessageFormat formatter = new MessageFormat(getResourceBundleString(key));        
        IStatus status = new Status(IStatus.ERROR, _pluginId, IStatus.OK, formatter.format(args), tt);
        _log.log(status);  
    }

    public void error(String key, Object arg0, Object arg1)
    {
        error(key, arg0, arg1, null);
    }

    public void error(String key, Object arg0, Object arg1, Throwable tt)
    {
        Object[] args = new Object[2];
        args[0] = arg0;
        args[1] = arg1;
        MessageFormat formatter = new MessageFormat(getResourceBundleString(key));        
        IStatus status = new Status(IStatus.ERROR, _pluginId, IStatus.OK, formatter.format(args), tt);
        _log.log(status);  
    }

    public void error(String key, Object arg0, Object arg1, Object arg2)
    {   
        error(key, arg0, arg1, arg2, null);
    }

    public void error(String key, Object arg0, Object arg1, Object arg2, Throwable tt)
    {
        Object[] args = new Object[3];
        args[0] = arg0;
        args[1] = arg1;
        args[2] = arg2;
        MessageFormat formatter = new MessageFormat(getResourceBundleString(key));        
        IStatus status = new Status(IStatus.ERROR, _pluginId, IStatus.OK, formatter.format(args), tt);
        _log.log(status);  
    }

    public void error(String key, Object arg0, Object arg1, Object arg2, Object arg3)
    {
        error(key, arg0, arg1, arg2, arg3, null);
    }

    public void error(String key, Object arg0, Object arg1, Object arg2, Object arg3, Throwable tt)
    {
        Object[] args = new Object[4];
        args[0] = arg0;
        args[1] = arg1;
        args[2] = arg2;
        args[3] = arg3;
        MessageFormat formatter = new MessageFormat(getResourceBundleString(key));        
        IStatus status = new Status(IStatus.ERROR, _pluginId, IStatus.OK, formatter.format(args), tt);
        _log.log(status); 
    }

    public void error(String key, Object arg0, Object arg1, Object arg2, Object arg3, Object arg4)
    {
        error(key, arg0, arg1, arg2, arg3, arg4, null);
    }

    public void error(String key, Object arg0, Object arg1, Object arg2, Object arg3, Object arg4, Throwable tt)
    {
        Object[] args = new Object[5];
        args[0] = arg0;
        args[1] = arg1;
        args[2] = arg2;
        args[3] = arg3;
        args[4] = arg4;
        MessageFormat formatter = new MessageFormat(getResourceBundleString(key));        
        IStatus status = new Status(IStatus.ERROR, _pluginId, IStatus.OK, formatter.format(args), tt);
        _log.log(status); 
    }

    public void error(String key, Object[] args)
    {
        error(key, args, null);
    }

    public void error(String key, Object[] args, Throwable tt)
    {
        MessageFormat formatter = new MessageFormat(getResourceBundleString(key));        
        IStatus status = new Status(IStatus.ERROR, _pluginId, IStatus.OK, formatter.format(args), tt);
        _log.log(status);
    }

    public void warn(Object message)
    {
        fatal(message, null); 
    }

    public void warn(Object message, Throwable tt)
    {
        IStatus status = new Status(IStatus.WARNING, _pluginId, IStatus.OK, (String)message, tt);
        _log.log(status);  
    }

    public void warn(String key)
    {
        IStatus status = new Status(IStatus.WARNING, _pluginId, IStatus.OK, getResourceBundleString(key), null);
        _log.log(status);        
    }

    public void warn(String key, Throwable tt)
    {
        IStatus status = new Status(IStatus.WARNING, _pluginId, IStatus.OK, getResourceBundleString(key), tt);
        _log.log(status);   
    }

    public void warn(String key, Object arg0)
    {
        warn(key, arg0, null);
    }

    public void warn(String key, Object arg0, Throwable tt)
    {
        Object[] args = new Object[1];
        args[0] = arg0;
        MessageFormat formatter = new MessageFormat(getResourceBundleString(key));        
        IStatus status = new Status(IStatus.WARNING, _pluginId, IStatus.OK, formatter.format(args), tt);
        _log.log(status);  
    }

    public void warn(String key, Object arg0, Object arg1)
    {
        warn(key, arg0, arg1, null);
    }

    public void warn(String key, Object arg0, Object arg1, Throwable tt)
    {
        Object[] args = new Object[2];
        args[0] = arg0;
        args[1] = arg1;
        MessageFormat formatter = new MessageFormat(getResourceBundleString(key));        
        IStatus status = new Status(IStatus.WARNING, _pluginId, IStatus.OK, formatter.format(args), tt);
        _log.log(status);  
    }

    public void warn(String key, Object arg0, Object arg1, Object arg2)
    {   
        warn(key, arg0, arg1, arg2, null);
    }

    public void warn(String key, Object arg0, Object arg1, Object arg2, Throwable tt)
    {
        Object[] args = new Object[3];
        args[0] = arg0;
        args[1] = arg1;
        args[2] = arg2;
        MessageFormat formatter = new MessageFormat(getResourceBundleString(key));        
        IStatus status = new Status(IStatus.WARNING, _pluginId, IStatus.OK, formatter.format(args), tt);
        _log.log(status);  
    }

    public void warn(String key, Object arg0, Object arg1, Object arg2, Object arg3)
    {
        warn(key, arg0, arg1, arg2, arg3, null);
    }

    public void warn(String key, Object arg0, Object arg1, Object arg2, Object arg3, Throwable tt)
    {
        Object[] args = new Object[4];
        args[0] = arg0;
        args[1] = arg1;
        args[2] = arg2;
        args[3] = arg3;
        MessageFormat formatter = new MessageFormat(getResourceBundleString(key));        
        IStatus status = new Status(IStatus.WARNING, _pluginId, IStatus.OK, formatter.format(args), tt);
        _log.log(status); 
    }

    public void warn(String key, Object arg0, Object arg1, Object arg2, Object arg3, Object arg4)
    {
        warn(key, arg0, arg1, arg2, arg3, arg4, null);
    }

    public void warn(String key, Object arg0, Object arg1, Object arg2, Object arg3, Object arg4, Throwable tt)
    {
        Object[] args = new Object[5];
        args[0] = arg0;
        args[1] = arg1;
        args[2] = arg2;
        args[3] = arg3;
        args[4] = arg4;
        MessageFormat formatter = new MessageFormat(getResourceBundleString(key));        
        IStatus status = new Status(IStatus.WARNING, _pluginId, IStatus.OK, formatter.format(args), tt);
        _log.log(status); 
    }

    public void warn(String key, Object[] args)
    {
        warn(key, args, null);
    }

    public void warn(String key, Object[] args, Throwable tt)
    {
        MessageFormat formatter = new MessageFormat(getResourceBundleString(key));        
        IStatus status = new Status(IStatus.WARNING, _pluginId, IStatus.OK, formatter.format(args), tt);
        _log.log(status);
    }

    public void info(Object message)
    {
        fatal(message, null); 
    }

    public void info(Object message, Throwable tt)
    {
        IStatus status = new Status(IStatus.INFO, _pluginId, IStatus.OK, (String)message, tt);
        _log.log(status);  
    }

    public void info(String key)
    {
        IStatus status = new Status(IStatus.INFO, _pluginId, IStatus.OK, getResourceBundleString(key), null);
        _log.log(status);        
    }

    public void info(String key, Throwable tt)
    {
        IStatus status = new Status(IStatus.INFO, _pluginId, IStatus.OK, getResourceBundleString(key), tt);
        _log.log(status);   
    }

    public void info(String key, Object arg0)
    {
        info(key, arg0, null);
    }

    public void info(String key, Object arg0, Throwable tt)
    {
        Object[] args = new Object[1];
        args[0] = arg0;
        MessageFormat formatter = new MessageFormat(getResourceBundleString(key));        
        IStatus status = new Status(IStatus.INFO, _pluginId, IStatus.OK, formatter.format(args), tt);
        _log.log(status);  
    }

    public void info(String key, Object arg0, Object arg1)
    {
        info(key, arg0, arg1, null);
    }

    public void info(String key, Object arg0, Object arg1, Throwable tt)
    {
        Object[] args = new Object[2];
        args[0] = arg0;
        args[1] = arg1;
        MessageFormat formatter = new MessageFormat(getResourceBundleString(key));        
        IStatus status = new Status(IStatus.INFO, _pluginId, IStatus.OK, formatter.format(args), tt);
        _log.log(status);  
    }

    public void info(String key, Object arg0, Object arg1, Object arg2)
    {   
        info(key, arg0, arg1, arg2, null);
    }

    public void info(String key, Object arg0, Object arg1, Object arg2, Throwable tt)
    {
        Object[] args = new Object[3];
        args[0] = arg0;
        args[1] = arg1;
        args[2] = arg2;
        MessageFormat formatter = new MessageFormat(getResourceBundleString(key));        
        IStatus status = new Status(IStatus.INFO, _pluginId, IStatus.OK, formatter.format(args), tt);
        _log.log(status);  
    }

    public void info(String key, Object arg0, Object arg1, Object arg2, Object arg3)
    {
        info(key, arg0, arg1, arg2, arg3, null);
    }

    public void info(String key, Object arg0, Object arg1, Object arg2, Object arg3, Throwable tt)
    {
        Object[] args = new Object[4];
        args[0] = arg0;
        args[1] = arg1;
        args[2] = arg2;
        args[3] = arg3;
        MessageFormat formatter = new MessageFormat(getResourceBundleString(key));        
        IStatus status = new Status(IStatus.INFO, _pluginId, IStatus.OK, formatter.format(args), tt);
        _log.log(status); 
    }

    public void info(String key, Object arg0, Object arg1, Object arg2, Object arg3, Object arg4)
    {
        info(key, arg0, arg1, arg2, arg3, arg4, null);
    }

    public void info(String key, Object arg0, Object arg1, Object arg2, Object arg3, Object arg4, Throwable tt)
    {
        Object[] args = new Object[5];
        args[0] = arg0;
        args[1] = arg1;
        args[2] = arg2;
        args[3] = arg3;
        args[4] = arg4;
        MessageFormat formatter = new MessageFormat(getResourceBundleString(key));        
        IStatus status = new Status(IStatus.INFO, _pluginId, IStatus.OK, formatter.format(args), tt);
        _log.log(status); 
    }

    public void info(String key, Object[] args)
    {
        info(key, args, null);
    }

    public void info(String key, Object[] args, Throwable tt)
    {
        MessageFormat formatter = new MessageFormat(getResourceBundleString(key));        
        IStatus status = new Status(IStatus.INFO, _pluginId, IStatus.OK, formatter.format(args), tt);
        _log.log(status);
    }

    public void debug(Object message)
    {
        fatal(message, null); 
    }

    public void debug(Object message, Throwable tt)
    {
        IStatus status = new Status(IStatus.INFO, _pluginId, IStatus.OK, (String)message, tt);
        _log.log(status);  
    }

    public void debug(String key)
    {
        IStatus status = new Status(IStatus.INFO, _pluginId, IStatus.OK, getResourceBundleString(key), null);
        _log.log(status);        
    }

    public void debug(String key, Throwable tt)
    {
        IStatus status = new Status(IStatus.INFO, _pluginId, IStatus.OK, getResourceBundleString(key), tt);
        _log.log(status);   
    }

    public void debug(String key, Object arg0)
    {
        debug(key, arg0, null);
    }

    public void debug(String key, Object arg0, Throwable tt)
    {
        Object[] args = new Object[1];
        args[0] = arg0;
        MessageFormat formatter = new MessageFormat(getResourceBundleString(key));        
        IStatus status = new Status(IStatus.INFO, _pluginId, IStatus.OK, formatter.format(args), tt);
        _log.log(status);  
    }

    public void debug(String key, Object arg0, Object arg1)
    {
        debug(key, arg0, arg1, null);
    }

    public void debug(String key, Object arg0, Object arg1, Throwable tt)
    {
        Object[] args = new Object[2];
        args[0] = arg0;
        args[1] = arg1;
        MessageFormat formatter = new MessageFormat(getResourceBundleString(key));        
        IStatus status = new Status(IStatus.INFO, _pluginId, IStatus.OK, formatter.format(args), tt);
        _log.log(status);  
    }

    public void debug(String key, Object arg0, Object arg1, Object arg2)
    {   
        debug(key, arg0, arg1, arg2, null);
    }

    public void debug(String key, Object arg0, Object arg1, Object arg2, Throwable tt)
    {
        Object[] args = new Object[3];
        args[0] = arg0;
        args[1] = arg1;
        args[2] = arg2;
        MessageFormat formatter = new MessageFormat(getResourceBundleString(key));        
        IStatus status = new Status(IStatus.INFO, _pluginId, IStatus.OK, formatter.format(args), tt);
        _log.log(status);  
    }

    public void debug(String key, Object arg0, Object arg1, Object arg2, Object arg3)
    {
        debug(key, arg0, arg1, arg2, arg3, null);
    }

    public void debug(String key, Object arg0, Object arg1, Object arg2, Object arg3, Throwable tt)
    {
        Object[] args = new Object[4];
        args[0] = arg0;
        args[1] = arg1;
        args[2] = arg2;
        args[3] = arg3;
        MessageFormat formatter = new MessageFormat(getResourceBundleString(key));        
        IStatus status = new Status(IStatus.INFO, _pluginId, IStatus.OK, formatter.format(args), tt);
        _log.log(status); 
    }

    public void debug(String key, Object arg0, Object arg1, Object arg2, Object arg3, Object arg4)
    {
        debug(key, arg0, arg1, arg2, arg3, arg4, null);
    }

    public void debug(String key, Object arg0, Object arg1, Object arg2, Object arg3, Object arg4, Throwable tt)
    {
        Object[] args = new Object[5];
        args[0] = arg0;
        args[1] = arg1;
        args[2] = arg2;
        args[3] = arg3;
        args[4] = arg4;
        MessageFormat formatter = new MessageFormat(getResourceBundleString(key));        
        IStatus status = new Status(IStatus.INFO, _pluginId, IStatus.OK, formatter.format(args), tt);
        _log.log(status); 
    }

    public void debug(String key, Object[] args)
    {
        debug(key, args, null);
    }

    public void debug(String key, Object[] args, Throwable tt)
    {
        MessageFormat formatter = new MessageFormat(getResourceBundleString(key));        
        IStatus status = new Status(IStatus.INFO, _pluginId, IStatus.OK, formatter.format(args), tt);
        _log.log(status);
    }

    public void debugStr(String str)
    {
        debug((Object)str);
    }

    protected String getResourceBundleString(String key)
    {
        if (_rb == null)
        {
            IStatus status = new Status(IStatus.ERROR, _pluginId, IStatus.OK, Messages.StatusLogger_no_bundle, null); 
            _log.log(status);
            return key + Messages.StatusLogger_possible_args; 
        }
        else
        {
            try
            {
                return _rb.getString(key);
            }
            catch (MissingResourceException ee)
            {
                IStatus status = new Status(IStatus.ERROR, _pluginId, IStatus.OK, Messages.StatusLogger_no_bundle_1 + key + "\".", null); 
                _log.log(status);
                return key + Messages.StatusLogger_possible_args_1; 
            }
        }
    }
}

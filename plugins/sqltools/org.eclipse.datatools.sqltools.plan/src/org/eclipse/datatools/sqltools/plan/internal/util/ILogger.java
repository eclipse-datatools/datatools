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
package org.eclipse.datatools.sqltools.plan.internal.util;

/**
 * Interface for logger
 * 
 * @author Li Huang
 */
public interface ILogger
{
    public abstract void fatal(Object message);

    /**
     * This method should NOT be used since it doesn't use the resource bundle to localize the message. It is provided
     * here as a case when a resource bundle can't be used.
     */
    public abstract void fatal(Object message, Throwable tt);

    /**
     * For fatal logging with no arguments
     */
    public abstract void fatal(String key);

    /**
     * For fatal logging with no arguments but a stack trace
     */
    public abstract void fatal(String key, Throwable tt);

    /**
     * For fatal logging with one argument
     */
    public abstract void fatal(String key, Object arg0);

    /**
     * For fatal logging with one argument and a stack trace
     */
    public abstract void fatal(String key, Object arg0, Throwable tt);

    /**
     * For fatal logging with 2 arguments
     */
    public abstract void fatal(String key, Object arg0, Object arg1);

    /**
     * For fatal logging with 2 arguments and a stack trace
     */
    public abstract void fatal(String key, Object arg0, Object arg1, Throwable tt);

    /**
     * For fatal logging with 3 arguments
     */
    public abstract void fatal(String key, Object arg0, Object arg1, Object arg2);

    /**
     * For fatal logging with 3 arguments and a stack trace
     */
    public abstract void fatal(String key, Object arg0, Object arg1, Object arg2, Throwable tt);

    /**
     * For fatal logging with 4 arguments
     */
    public abstract void fatal(String key, Object arg0, Object arg1, Object arg2, Object arg3);

    /**
     * For fatal logging with 4 arguments and a stack trace
     */
    public abstract void fatal(String key, Object arg0, Object arg1, Object arg2, Object arg3, Throwable tt);

    /**
     * For fatal logging with 5 arguments
     */
    public abstract void fatal(String key, Object arg0, Object arg1, Object arg2, Object arg3, Object arg4);

    /**
     * For fatal logging with 5 arguments and a stack trace
     */
    public abstract void fatal(String key, Object arg0, Object arg1, Object arg2, Object arg3, Object arg4, Throwable tt);

    /**
     * For fatal logging methods where the number of args is greater than the methods provided.
     */
    public abstract void fatal(String key, Object[] args);

    /**
     * For fatal logging methods where the number of args is greater than the methods provided.
     */
    public abstract void fatal(String key, Object[] args, Throwable tt);

    public abstract void error(Object message);

    /**
     * This method should NOT be used since it doesn't use the resource bundle to localize the message. It is provided
     * here as a case when a resource bundle can't be used.
     */
    public abstract void error(Object message, Throwable tt);

    /**
     * For error logging with no arguments
     */
    public abstract void error(String key);

    /**
     * For error logging with no arguments but a stack trace
     */
    public abstract void error(String key, Throwable tt);

    /**
     * For error logging with one argument
     */
    public abstract void error(String key, Object arg0);

    /**
     * For error logging with one argument and a stack trace
     */
    public abstract void error(String key, Object arg0, Throwable tt);

    /**
     * For error logging with 2 arguments
     */
    public abstract void error(String key, Object arg0, Object arg1);

    /**
     * For error logging with 2 arguments and a stack trace
     */
    public abstract void error(String key, Object arg0, Object arg1, Throwable tt);

    /**
     * For error logging with 3 arguments
     */
    public abstract void error(String key, Object arg0, Object arg1, Object arg2);

    /**
     * For error logging with 3 arguments and a stack trace
     */
    public abstract void error(String key, Object arg0, Object arg1, Object arg2, Throwable tt);

    /**
     * For error logging with 4 arguments
     */
    public abstract void error(String key, Object arg0, Object arg1, Object arg2, Object arg3);

    /**
     * For error logging with 4 arguments and a stack trace
     */
    public abstract void error(String key, Object arg0, Object arg1, Object arg2, Object arg3, Throwable tt);

    /**
     * For error logging with 5 arguments
     */
    public abstract void error(String key, Object arg0, Object arg1, Object arg2, Object arg3, Object arg4);

    /**
     * For error logging with 5 arguments and a stack trace
     */
    public abstract void error(String key, Object arg0, Object arg1, Object arg2, Object arg3, Object arg4, Throwable tt);

    /**
     * For error logging methods where the number of args is greater than the methods provided.
     */
    public abstract void error(String key, Object[] args);

    /**
     * For error logging methods where the number of args is greater than the methods provided.
     */
    public abstract void error(String key, Object[] args, Throwable tt);

    public abstract void warn(Object message);

    /**
     * This method should NOT be used since it doesn't use the resource bundle to localize the message. It is provided
     * here as a case when a resource bundle can't be used.
     */
    public abstract void warn(Object message, Throwable tt);

    /**
     * For warn logging with no arguments
     */
    public abstract void warn(String key);

    /**
     * For warn logging with no arguments but a stack trace
     */
    public abstract void warn(String key, Throwable tt);

    /**
     * For warn logging with one argument
     */
    public abstract void warn(String key, Object arg0);

    /**
     * For warn logging with one argument and a stack trace
     */
    public abstract void warn(String key, Object arg0, Throwable tt);

    /**
     * For warn logging with 2 arguments
     */
    public abstract void warn(String key, Object arg0, Object arg1);

    /**
     * For warn logging with 2 arguments and a stack trace
     */
    public abstract void warn(String key, Object arg0, Object arg1, Throwable tt);

    /**
     * For warn logging with 3 arguments
     */
    public abstract void warn(String key, Object arg0, Object arg1, Object arg2);

    /**
     * For warn logging with 3 arguments and a stack trace
     */
    public abstract void warn(String key, Object arg0, Object arg1, Object arg2, Throwable tt);

    /**
     * For warn logging with 4 arguments
     */
    public abstract void warn(String key, Object arg0, Object arg1, Object arg2, Object arg3);

    /**
     * For warn logging with 4 arguments and a stack trace
     */
    public abstract void warn(String key, Object arg0, Object arg1, Object arg2, Object arg3, Throwable tt);

    /**
     * For warn logging with 5 arguments
     */
    public abstract void warn(String key, Object arg0, Object arg1, Object arg2, Object arg3, Object arg4);

    /**
     * For warn logging with 5 arguments and a stack trace
     */
    public abstract void warn(String key, Object arg0, Object arg1, Object arg2, Object arg3, Object arg4, Throwable tt);

    /**
     * For warn logging methods where the number of args is greater than the methods provided.
     */
    public abstract void warn(String key, Object[] args);

    /**
     * For warn logging methods where the number of args is greater than the methods provided.
     */
    public abstract void warn(String key, Object[] args, Throwable tt);

    /**
     * This method should NOT be used since it doesn't use the resource bundle to localize the message. It is provided
     * here as a case when a resource bundle can't be used.
     */
    public abstract void info(Object message);

    /**
     * This method should NOT be used since it doesn't use the resource bundle to localize the message. It is provided
     * here as a case when a resource bundle can't be used.
     */
    public abstract void info(Object message, Throwable tt);

    /**
     * For info logging with no arguments
     */
    public abstract void info(String key);

    /**
     * For info logging with no arguments but a stack trace
     */
    public abstract void info(String key, Throwable tt);

    /**
     * For info logging with one argument
     */
    public abstract void info(String key, Object arg0);

    /**
     * For info logging with one argument and a stack trace
     */
    public abstract void info(String key, Object arg0, Throwable tt);

    /**
     * For info logging with 2 arguments
     */
    public abstract void info(String key, Object arg0, Object arg1);

    /**
     * For info logging with 2 arguments and a stack trace
     */
    public abstract void info(String key, Object arg0, Object arg1, Throwable tt);

    /**
     * For info logging with 3 arguments
     */
    public abstract void info(String key, Object arg0, Object arg1, Object arg2);

    /**
     * For info logging with 3 arguments and a stack trace
     */
    public abstract void info(String key, Object arg0, Object arg1, Object arg2, Throwable tt);

    /**
     * For info logging with 4 arguments
     */
    public abstract void info(String key, Object arg0, Object arg1, Object arg2, Object arg3);

    /**
     * For info logging with 4 arguments and a stack trace
     */
    public abstract void info(String key, Object arg0, Object arg1, Object arg2, Object arg3, Throwable tt);

    /**
     * For info logging with 5 arguments
     */
    public abstract void info(String key, Object arg0, Object arg1, Object arg2, Object arg3, Object arg4);

    /**
     * For info logging with 5 arguments and a stack trace
     */
    public abstract void info(String key, Object arg0, Object arg1, Object arg2, Object arg3, Object arg4, Throwable tt);

    /**
     * For info logging methods where the number of args is greater than the methods provided.
     */
    public abstract void info(String key, Object[] args);

    /**
     * For info logging methods where the number of args is greater than the methods provided.
     */
    public abstract void info(String key, Object[] args, Throwable tt);

    /**
     * This method should NOT be used since it doesn't use the resource bundle to localize the message. It is provided
     * here as a case when a resource bundle can't be used.
     */
    public abstract void debug(Object message);

    /**
     * This method should NOT be used since it doesn't use the resource bundle to localize the message. It is provided
     * here as a case when a resource bundle can't be used.
     */
    public abstract void debug(Object message, Throwable tt);

    /**
     * For debug logging with no arguments
     */
    public abstract void debug(String key);

    /**
     * For debug logging with no arguments but a stack trace
     */
    public abstract void debug(String key, Throwable tt);

    /**
     * For debug logging with one argument
     */
    public abstract void debug(String key, Object arg0);

    /**
     * For debug logging with one argument and a stack trace
     */
    public abstract void debug(String key, Object arg0, Throwable tt);

    /**
     * For debug logging with 2 arguments
     */
    public abstract void debug(String key, Object arg0, Object arg1);

    /**
     * For debug logging with 2 arguments and a stack trace
     */
    public abstract void debug(String key, Object arg0, Object arg1, Throwable tt);

    /**
     * For debug logging with 3 arguments
     */
    public abstract void debug(String key, Object arg0, Object arg1, Object arg2);

    /**
     * For debug logging with 3 arguments and a stack trace
     */
    public abstract void debug(String key, Object arg0, Object arg1, Object arg2, Throwable tt);

    /**
     * For debug logging with 4 arguments
     */
    public abstract void debug(String key, Object arg0, Object arg1, Object arg2, Object arg3);

    /**
     * For debug logging with 4 arguments and a stack trace
     */
    public abstract void debug(String key, Object arg0, Object arg1, Object arg2, Object arg3, Throwable tt);

    /**
     * For debug logging with 5 arguments
     */
    public abstract void debug(String key, Object arg0, Object arg1, Object arg2, Object arg3, Object arg4);

    /**
     * For debug logging with 5 arguments and a stack trace
     */
    public abstract void debug(String key, Object arg0, Object arg1, Object arg2, Object arg3, Object arg4, Throwable tt);

    /**
     * For debug logging methods where the number of args is greater than the methods provided.
     */
    public abstract void debug(String key, Object[] args);

    /**
     * For debug logging methods where the number of args is greater than the methods provided.
     */
    public abstract void debug(String key, Object[] args, Throwable tt);

    public abstract void debugStr(String str);
}

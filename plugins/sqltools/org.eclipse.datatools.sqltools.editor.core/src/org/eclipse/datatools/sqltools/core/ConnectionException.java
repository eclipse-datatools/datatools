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

/**
 * Exceptions related to IControlConnection
 * @author Yang Liu
 * @author Hui Cao
 */
public class ConnectionException extends Exception
{
    /**
     * 
     */
    private static final long serialVersionUID = -3971506992161391380L;
    
    /**
     * Error code constant that indicates a debuggee is attached more than once. 
     */
    public static final int DUPLICATE_DEBUGGEE = -1;
    
    /**
     * Error code constant that indicates the internal id of the debuggee connection can't be obtained. 
     */
    public static final int CONVERTCONNID_FAIL = -2;

    int _code;

    /**
     * @param code error code
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ConnectionException(int code, String message)
    {
        super(message);
        _code = code;
    }

    /**
     * @param code error code
     * @param cause the cause (which is saved for later retrieval by the getCause() method). (A null value is permitted,
     *            and indicates that the cause is nonexistent or unknown.)
     */
    public ConnectionException(int code, Throwable cause)
    {
        super(cause);
        _code = code;
    }

    /**
     * @param code error code
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     * @param cause the cause (which is saved for later retrieval by the getCause() method). (A null value is permitted,
     *            and indicates that the cause is nonexistent or unknown.)
     */
    public ConnectionException(int code, String message, Throwable cause)
    {
        super(message, cause);
        _code = code;
    }

    public int getCode()
    {
        return _code;
    }
}

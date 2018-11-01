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
package org.eclipse.datatools.sqltools.result.model;

import java.io.Serializable;

import org.eclipse.datatools.sqltools.result.IResultSetObject;

/**
 * This class represents a result item. A result item can be one of the following types: update count, plain text,
 * status text, result set.
 * 
 * @author Dafan Yang
 */
public class ResultItem implements Serializable
{
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    public static final int   UPDATE_COUNT     = 1;
    public static final int   PLAIN_TEXT       = 2;
    public static final int   RESULT_SET       = 3;
    public static final int   STATUS_TEXT      = 4;

    private static String     LINE_SEPARATOR   = System.getProperty("line.separator");
    /* the result item's type, can be one of the four types */
    private int               _resultType;
    /* the result item object */
    private Object            _resultObject;

    /**
     * Constructs an instance of <code>ResultItem</code> wrapping a result set object.
     * 
     * @see IResultSetObject
     * @param rs the result set object
     */
    public ResultItem(IResultSetObject rs)
    {
        _resultType = RESULT_SET;
        _resultObject = rs;
    }

    /**
     * Constructs an instance of <code>ResultItem</code> wrapping an update count message.
     * 
     * @param updatecount the update count
     */
    public ResultItem(int updatecount)
    {
        _resultType = UPDATE_COUNT;
        _resultObject = new Integer(updatecount);
    }

    /**
     * Constructs an instance of <code>ResultItem</code> wrapping a plain/status message.
     * 
     * @param message the message
     * @param isPlainText is this message a plain message
     */
    public ResultItem(String message, boolean isPlainText)
    {
        if (!message.endsWith("\n"))
        {
            message = message + LINE_SEPARATOR;
        }
        if (isPlainText)
        {
            _resultType = PLAIN_TEXT;
        }
        else
        {
            _resultType = STATUS_TEXT;
        }
        _resultObject = message;
    }

    /**
     * Returns result item's type.
     * 
     * @see #UPDATE_COUNT
     * @see #PLAIN_TEXT
     * @see #RESULT_SET
     * @see #STATUS_TEXT
     * @return the result item's type
     */
    public int getResultType()
    {
        return _resultType;
    }

    /**
     * Returns the result object. The returning object's type depends on the result type if is <code>UPDATE_COUNT</code>,
     * then is an <code>Integer</code>. if is <code>PLAIN_TEXT</code>, then is an <code>String</code> if is
     * <code>STATUS_TEXT</code>, then is an <code>String</code>,if is <code>RESULT_SET</code>, then is
     * <code>IResultSetObject</code>
     * 
     * @return the result item object
     * @see IResultSetObject
     */
    public Object getResultObject()
    {
        return _resultObject;
    }
}

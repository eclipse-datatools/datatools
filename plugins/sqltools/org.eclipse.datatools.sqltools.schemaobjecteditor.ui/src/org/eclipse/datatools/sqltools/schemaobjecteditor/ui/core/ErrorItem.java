/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core;

import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.IErrorItem;

/**
 * 
 * @author Idull
 */
public class ErrorItem implements IErrorItem
{
    private Object _source;
    private String _message;
    public int     _severity = ERROR;

    public ErrorItem(Object source, String message)
    {
        this(source, message, ERROR);
    }

    public ErrorItem(Object source, String message, int severity)
    {
        super();
        this._source = source;
        this._message = message;
        this._severity = severity;
    }

    public String getMessage()
    {
        return _message;
    }

    public Object getSource()
    {
        return _source;
    }

    public int getSeverity()
    {
        return _severity;
    }

    public void setSeverity(int severity)
    {
        this._severity = severity;
    }
}

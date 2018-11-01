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
package org.eclipse.datatools.sqltools.result.internal.export;

import org.eclipse.datatools.sqltools.result.export.AbstractOutputter;

/**
 * The standard implementation for <code>IOutputterDescriptor</code>
 * 
 * @author Dafan Yang
 */
public class OutputterDescriptor implements IOutputterDescriptor
{
    private String            _typeId;
    private String            _typeName;
    private String            _fileExtension;
    private boolean           _supportDelimiter;
    private boolean           _supportXMLResult;
    private AbstractOutputter _outputter;
    private String            _extensionFilterString;

    public OutputterDescriptor(String id, String name, String extension, boolean delimiter, AbstractOutputter _outputter, boolean supportXMLResult, String extensionFilter)
    {
        super();
        _typeId = id;
        _typeName = name;
        _fileExtension = extension;
        _supportDelimiter = delimiter;
        this._outputter = _outputter;
        _supportXMLResult = supportXMLResult;
        _extensionFilterString = extensionFilter;
    }

    public String getFileExtension()
    {
        return _fileExtension;
    }

    public AbstractOutputter getOutputter()
    {
        return _outputter;
    }

    public String getTypeId()
    {
        return _typeId;
    }

    public String getTypeName()
    {
        return _typeName;
    }

    public boolean supportDelimiter()
    {
        return _supportDelimiter;
    }

    public boolean supportXMLResult()
    {
        return _supportXMLResult;
    }
    
    public String getDisplayString()
    {
        StringBuffer dspString = new StringBuffer();
        dspString.append(_typeName == null ? "" : _typeName).append(" (*.").append(
                _fileExtension == null ? "*" : _fileExtension).append(")");

        return dspString.toString();
    }

    public String getExtFilterString()
    {
        if (_extensionFilterString != null && _extensionFilterString.trim().length() > 0)
        {
            return _extensionFilterString;
        }
        String ext = "*.";
        if (_fileExtension != null)
        {
            ext += _fileExtension;
        }
        else
        {
            ext += "*";
        }
        return ext;
    }

    public String getExtensionFilterDisplayString()
    {
        StringBuffer dspString = new StringBuffer();
        dspString.append(_typeName == null ? "" : _typeName).append(" (").append(getExtFilterString()).append(")");

        return dspString.toString();
    }
}

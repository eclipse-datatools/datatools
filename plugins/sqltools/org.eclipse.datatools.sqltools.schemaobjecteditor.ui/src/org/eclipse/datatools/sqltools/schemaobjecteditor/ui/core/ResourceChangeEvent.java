/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui.core;

import org.eclipse.datatools.sqltools.schemaobjecteditor.ui.IResourceChangeEvent;

/**
 * 
 * @author Idull
 */
public class ResourceChangeEvent implements IResourceChangeEvent
{
    private Object _source;
    private int    _type;

    public ResourceChangeEvent(Object _source, int _type)
    {
        super();
        this._source = _source;
        this._type = _type;
    }

    public Object getSource()
    {
        return _source;
    }

    public int getType()
    {
        return _type;
    }

}

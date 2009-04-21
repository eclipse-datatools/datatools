/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.routineeditor.ui.launching;

import java.util.List;

/**
 * @author lihuang
 */
public class LaunchObject
{

    private boolean _b;

    private List    _list;

    /**
     * set if the event trigger parameter is configured.
     * 
     * @param b
     */
    public void setConfigEventParameters(boolean b)
    {
        _b = b;
    }

    /**
     * return if the event trigger parameter is configured.
     * 
     * @return
     */
    public boolean hasConfigEventParameters()
    {
        return _b;
    }

    /**
     * Get config parameter.
     * 
     * @return
     */
    public List getConfigParameter()
    {
        return _list;
    }

    /**
     * Return config parameter.
     * 
     * @param list
     */
    public void setConfigParameter(List list)
    {
        _list = list;
    }
}
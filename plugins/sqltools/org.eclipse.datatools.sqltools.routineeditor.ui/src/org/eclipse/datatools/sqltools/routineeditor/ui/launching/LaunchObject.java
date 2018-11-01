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
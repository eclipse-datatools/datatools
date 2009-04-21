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
package org.eclipse.datatools.sqltools.debugger.actions;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.debug.ui.actions.IToggleBreakpointsTarget;

/**
 * @author Yang Liu
 */
public class RetargettableActionAdapterFactory implements IAdapterFactory
{
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.runtime.IAdapterFactory#getAdapter(java.lang.Object, java.lang.Class)
     */
    public Object getAdapter(Object adaptableObject, Class adapterType)
    {
        //		if (adapterType == IRunToLineTarget.class) {
        //			return new RunToLineAdapter();
        //		}
        if (adapterType == IToggleBreakpointsTarget.class) 
        {
            return new ToggleBreakpointAdapter(); 
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.runtime.IAdapterFactory#getAdapterList()
     */
    public Class[] getAdapterList()
    {
        //IRunToLineTarget.class,
        return new Class[] 
        {
            (IToggleBreakpointsTarget.class)
        }
        ;
    }
}

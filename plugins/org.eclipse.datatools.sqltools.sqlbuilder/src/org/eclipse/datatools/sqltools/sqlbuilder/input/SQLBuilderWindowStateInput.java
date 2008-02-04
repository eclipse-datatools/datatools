/**************************************************************************
 * Copyright (c) 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 **************************************************************************/

package org.eclipse.datatools.sqltools.sqlbuilder.input;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.datatools.sqltools.sqlbuilder.model.IWindowStateInfo;

/**
 * The class defines the SQL Query Builder's window state input, 
 * which must contain an IWindowStateInfo instance.
 */

public class SQLBuilderWindowStateInput implements IAdaptable
{

	private IWindowStateInfo _windowStateInfo;

	public SQLBuilderWindowStateInput( IWindowStateInfo windowStateInfo )
	{
	    setWindowStateInfo( windowStateInfo );
	}
	
	/**
	 * Returns the window state information.
	 * @return an IWindowStateInfo instance
	 */
    public IWindowStateInfo getWindowStateInfo(){
    	return _windowStateInfo;
    }
    
    /**
     * Sets the window state information.
     * @param windowStateInfo   an IWindowStateInfo instance; cannot be null.
     * @throws NullPointerException if a null IWindowStateInfo is specified
     */
    public void setWindowStateInfo( IWindowStateInfo windowStateInfo ) {
        if( windowStateInfo == null )
            throw new NullPointerException( "setWindowStateInfo( null )" );

    	_windowStateInfo = windowStateInfo;
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     */
    public Object getAdapter( Class adapter ) {
        return null;
    }

}

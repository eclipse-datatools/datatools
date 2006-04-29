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

package org.eclipse.datatools.sqltools.core.profile;

import org.eclipse.osgi.util.NLS;


/**
 * An exception indicates a connection profile does not exist for the given name.
 * @author Yang Liu
 */
public class NoSuchProfileException extends Exception
{
    /**
     * 
     */
    private static final long serialVersionUID = 3786738163283355914L;

    /**
     * Constructs a NoSuchProfileException using the given connection profile name.
     * @param name connection profile name
     */
    public NoSuchProfileException(String name)
    {
        super(NLS.bind(Messages.NoSuchProfileException_cant_find_profile, (new Object[]{name}))); 
    }

}

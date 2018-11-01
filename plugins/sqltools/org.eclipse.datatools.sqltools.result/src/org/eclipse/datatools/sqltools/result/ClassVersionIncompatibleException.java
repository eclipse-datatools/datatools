/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.result;

/**
 * Thrown when the version of ResultInstance class in history file is incompatible.
 * 
 * @author juewu
 */
public class ClassVersionIncompatibleException extends ClassNotFoundException
{
    /**
     * 
     */
    private static final long serialVersionUID = 1259772479878395031L;

    public ClassVersionIncompatibleException()
    {
        super();
    }

    public ClassVersionIncompatibleException(String s, Throwable ex)
    {
        super(s, ex);
    }

    public ClassVersionIncompatibleException(String s)
    {
        super(s);
    }

    public ClassVersionIncompatibleException(String oldVersion, String currentVersion)
    {
        this("Can't convert version from " + oldVersion + " file to " + currentVersion + ".");
    }
}

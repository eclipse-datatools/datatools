/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.sqltools.schemaobjecteditor.ui;

/**
 * Instance of this interface is used to describe an error item on an editor page.
 * 
 * @author Idull
 */
public interface IErrorItem
{
    public static final int OK      = 0;
    public static final int INFO    = 1;
    public static final int WARNING = 2;
    public static final int ERROR   = 4;
    public static final int CANCEL  = 8;

    /**
     * Returns the source object on which the error occurs. (For future use)
     * 
     * @return
     */
    public Object getSource();

    /**
     * Returns the error message
     * 
     * @return
     */
    public String getMessage();

    /**
     * Returns the severity. @see Diagnostic
     * 
     * @return
     */
    public int getSeverity();
}

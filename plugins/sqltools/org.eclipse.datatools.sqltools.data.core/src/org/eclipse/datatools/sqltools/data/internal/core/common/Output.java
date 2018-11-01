/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.data.internal.core.common;

/**
 * @author groux
 */
public interface Output {

    //  Status (match constants in OutputItem)
 /*   public static final int STATUS_SUCCESS = 2;
    public static final int STATUS_WARNING = 3;
    public static final int STATUS_FAILURE = 4;
    public static final int STATUS_CRITICAL_ERROR = 6; */
    
    // Status (match constants in OutputItem)
    public static final int   STATUS_CRITICAL_ERROR = 7;
    public static final int   STATUS_FAILED         = 6;
    public static final int   STATUS_TERMINATED     = 5;
    public static final int   STATUS_WARNING        = 4;
    public static final int   STATUS_SUCCEEDED      = 3;
    public static final int   STATUS_RUNNING        = 2;
    public static final int   STATUS_STARTED        = 1;
    
    public void write(String s);
    
}

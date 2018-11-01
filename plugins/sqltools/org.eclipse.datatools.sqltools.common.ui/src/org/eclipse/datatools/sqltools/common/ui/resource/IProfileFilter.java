/*******************************************************************************
 * Copyright (c) 2005 -- 2007 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.sqltools.common.ui.resource;

/**
 * 
 * @author Hui Cao
 *  
 */
public interface IProfileFilter
{
    /**
     * Since the filtering operation may involve database and is time consuming, subclasses can set up teh fixture here
     * to improve performance. for example, open a network connection. This method is called before a test is executed.
     */
    public void setUp();

    public boolean accept(String profileName);

    /**
     * Tears down the fixture, for example, close a network connection. This method is called after a test is executed.
     */
    public void tearDown();
}

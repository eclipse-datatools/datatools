/*
 * Created on 2005-3-25
 * 
 * Copyright (c) Sybase, Inc. 2004 All rights reserved.
 */
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

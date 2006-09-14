/*
 * Created on 2005-3-11
 *
 * Copyright (c) Sybase, Inc. 2004   
 * All rights reserved.                                    
 */
package org.eclipse.datatools.sqltools.sqleditor;


/**
 * @author Hui Cao
 *
 */
public interface IConnectionProfileAttachListener
{
    /**
     * this method will be called when the editor is open or user switches connection profile
     * @param sqlEditor
     */
    public void connectionProfileAttached(SQLEditor sqlEditor);
}

/**
 * Created on 2007-4-18
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.sqltools.common.ui.helpsystem;

import org.eclipse.datatools.help.ContextProviderDelegate;

/**
 * 
 * @author lihuang
 */
public class CommonContextProvider extends ContextProviderDelegate
{

    public CommonContextProvider()
    {
        this("");
    }
    
    public CommonContextProvider(String contextHelpPluginID)
    {
        super(contextHelpPluginID);
    }

}

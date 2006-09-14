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
package org.eclipse.datatools.sqltools.sqleditor.internal;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.sqltools.sqleditor.ISQLEditorActionContributorExtension;

/**
 * 
 * @author Hui Cao
 * 
 */
public class SQLEditorContributorExtensionRegistry {

	private static SQLEditorContributorExtensionRegistry instance = null;
	private ArrayList _extensions = new ArrayList();
	
	private SQLEditorContributorExtensionRegistry() {
		init();
	}
	
	public static SQLEditorContributorExtensionRegistry getInstance()
	{
		if (instance == null)
		{
			instance = new SQLEditorContributorExtensionRegistry();
		}
		return instance;
	}
	
	private void init()
	{
        IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
        IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint(SQLEditorPlugin.PLUGIN_ID, "actionExtensions"); //$NON-NLS-1$ //$NON-NLS-2$
        IExtension[] extensions = extensionPoint.getExtensions();
        for (int i = 0; i < extensions.length; ++i)
        {
            IConfigurationElement[] configElements = extensions[i].getConfigurationElements();
            for (int j = 0; j < configElements.length; ++j)
            {
                if (configElements[j].getName().equals("actionExtension")) 
                {
                    try
                    {
                        ISQLEditorActionContributorExtension factory = (ISQLEditorActionContributorExtension) configElements[j].createExecutableExtension("class"); //$NON-NLS-1$
                        _extensions.add(factory);
                    }
                    catch (Exception e)
                    {
                    	try
                    	{
                    		SQLEditorPlugin.getDefault().log(e);
                    	}
                    	catch (Exception ee)
                        {
                    		ee.printStackTrace();
                        }
                    }
                }
            }
        }		
	}
	
	public Collection getExtensions()
	{
		return _extensions;
	}
}

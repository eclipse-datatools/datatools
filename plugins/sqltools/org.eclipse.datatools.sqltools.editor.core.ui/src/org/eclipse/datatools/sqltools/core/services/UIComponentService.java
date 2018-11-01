/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.core.services;

import java.util.HashMap;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.internal.ui.wizards.NewCPWizard;
import org.eclipse.datatools.connectivity.internal.ui.wizards.NewCPWizardCategoryFilter;
import org.eclipse.datatools.sqltools.sql.ui.dialogs.SQLPainterDlg;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.dbitem.ParameterWrapper;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * @author Hui Cao
 * 
 */
public class UIComponentService {

	/**
	 * Statement types
	 */
	public static final String SELECT = "query";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";
	public static final String INSERT = "insert";

	/**
	 * Parameter types
	 */
	public static final String VARIABLE = "variable";
	public static final String PARAMETER = "parameter";
	
	/**
	 * Key for file entry in the <code>Hashmap info</code> parameter when creating
	 * a DMLDialog through this UIComponentService.
	 */
	public static final String KEY_FILE = "file";

	private UIComponentService _sqlBuilderComponentService;
	
	public SQLPainterDlg getDMLDialog(Shell parentShell, String statementType,
			String statement, String profileName, String database,
			String parametersType, String parameter, String table, HashMap info)
	{
		if (getSQLBuilderComponentService() != null)
		{
			return _sqlBuilderComponentService.getDMLDialog(parentShell, statementType, statement, profileName, database, parametersType, parameter, table, info);
		}
		else 
		{
			return null;
		}
	}
	
	public boolean supportsDMLDialog()
	{
		if (getSQLBuilderComponentService() != null)
		{
			return _sqlBuilderComponentService.supportsDMLDialog();
		}
		else 
		{
			return false;
		}
	}
	
	public Dialog getParameterTableDialog(Shell parentShell, ParameterWrapper[] wrappers, ILaunchConfiguration configuration)
	{
		return null;
	}
	
    /**
     * Return a connection profile wizard which is used to create a new connection profile
     * TODO: remove this method and use connectivity layer directly. 
     * @return
     */
    public IWizard getProfileWizard()
    {
    	ViewerFilter filter = new NewCPWizardCategoryFilter(ProfileUtil.DATABASE_CATEGORY_ID);
    	NewCPWizard wizard = new NewCPWizard(filter, null);
		return wizard;
        
    }

	/*
	 *  Get the implementation of the SQLBuilderUIComponentService extension
	 *  which acts as a proxy for this UIComponentService
	 */
    private UIComponentService getSQLBuilderComponentService()
	{
    	if (_sqlBuilderComponentService == null)
    	{
		    try 
		    {
		    	IExtensionRegistry reg = Platform.getExtensionRegistry();
		    	IExtensionPoint ep = reg.getExtensionPoint("org.eclipse.datatools.sqltools.editor.core.SQLBuilderUIComponentService");//$NON-NLS-1$
		    	IExtension[] extensions = ep.getExtensions();
		    	// Get the first and only extension
		      	if (extensions.length > 0) 
		      	{
		    		IExtension ext = extensions[0];
		    		IConfigurationElement[] ces =ext.getConfigurationElements(); 
		    		if (ces.length > 0 && "service".equals(ces[0].getName()))
		    		{
		    			Object obj = ces[0].createExecutableExtension("class");//$NON-NLS-1$
		    			if( obj instanceof UIComponentService)
		    			{
		    				_sqlBuilderComponentService =  (UIComponentService) obj;
		    			}
					}
		    	}
		    }
		    catch (Exception e)
		    {
		    	EditorCorePlugin.getDefault().log(e);
		    }
    	}
	    return _sqlBuilderComponentService;
	}

}

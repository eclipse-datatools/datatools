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
package org.eclipse.datatools.sqltools.editor.ui.core;

import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.services.ActionService;
import org.eclipse.datatools.sqltools.core.services.SQLEditorUIService;
import org.eclipse.datatools.sqltools.core.services.SQLUIService;
import org.eclipse.datatools.sqltools.core.services.UIComponentService;
import org.eclipse.datatools.sqltools.editor.template.ITemplateService;
import org.eclipse.datatools.sqltools.plan.IPlanService;
import org.eclipse.datatools.sqltools.plan.PlanServiceRegistry;

public class SQLDevToolsUIConfiguration 
{
	static public String DefaultDBFactory_vendor = "Undefined";
	
	private DatabaseVendorDefinitionId _dbdefinitionId = null;
	
	private static SQLDevToolsUIConfiguration _instance = new SQLDevToolsUIConfiguration();

	protected SQLDevToolsUIConfiguration() {
		_dbdefinitionId = new DatabaseVendorDefinitionId(DefaultDBFactory_vendor, "");
	}

	public static SQLDevToolsUIConfiguration getDefaultInstance() {
		return _instance;
	}
	
	/**
	 * Returns the associated <code>DatabaseVendorDefinitionId</code> object.
	 * A <code>SQLDevToolsConfiguration</code> is attached to a certain
	 * <code>DatabaseVendorDefinitionId</code>.
	 * <code>DatabaseVendorDefinitionId</code> specifies the database specific
	 * capabilities, while a <code>SQLDevToolsUIConfiguration</code>
	 * encapsulates database specific algorithms.
	 * 
	 * @return The associated <code>DatabaseDefinition</code> object.
	 */
	public DatabaseVendorDefinitionId getDatabaseVendorDefinitionId() {
		return _dbdefinitionId;
	}

	/**
	 * Associcates this factory with a particular
	 * <code>DatabaseVendorDefinitionId</code>. This method should only be
	 * called once by the <code>SQLDevToolsUIConfigRegistry</code>.
	 * 
	 * @param dbdefinition
	 *            the associated <code>DatabaseDefinition</code> object.
	 */
	public void setDatabaseVendorDefinitionId(
			DatabaseVendorDefinitionId dbdefinitionId) {
		this._dbdefinitionId = dbdefinitionId;
	}
	
	/**
	 * Returns the UI service associated with this database definition
	 * 
	 */
	public UIComponentService getUIComponentService() 
	{
		return new UIComponentService();
	}
	

	/**
	 * Returns the SQL Editor service associated with this database definition
	 * 
	 */
	public SQLEditorUIService getSQLEditorUIService() {
		return new SQLEditorUIService();
	}
	

	/**
	 * Returns the query execution plan service associated with this database
	 * definition
	 * 
	 */
	public IPlanService getPlanService() {
		return PlanServiceRegistry.getInstance().getPlanService(this.getDatabaseVendorDefinitionId().toString());
	}
	
    
    public ITemplateService getTemplateService()
    {
        return null;
    }
    

	/**
	 * Returns the SQL service associated with this database definition
	 */
	public SQLUIService getSQLUIService() {
		return new SQLUIService();
	}
	
    
    /**
     * Returns the Action service
     * 
     */
    public ActionService getActionService() {
        return new ActionService();
    }
}

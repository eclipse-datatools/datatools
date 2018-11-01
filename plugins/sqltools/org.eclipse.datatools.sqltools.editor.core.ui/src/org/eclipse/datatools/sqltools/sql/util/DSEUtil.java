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
package org.eclipse.datatools.sqltools.sql.util;

import org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.ConnectionInfoImpl;
import org.eclipse.datatools.connectivity.sqm.internal.core.connection.DatabaseConnectionRegistry;
import org.eclipse.datatools.modelbase.sql.routines.Routine;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Event;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.tables.Trigger;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.SQLToolsFacade;
import org.eclipse.datatools.sqltools.core.services.ActionService;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLDevToolsUIConfiguration;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLToolsUIFacade;
import org.eclipse.datatools.sqltools.internal.SQLDevToolsUtil;

/**
 * Provides utility methods to navigate through the Data Source Explorer and find the desired information.
 * @author Hui Cao
 * 
 */
public class DSEUtil {
	

	public static String getProfileName(IVirtualNode _node)
	{
		ConnectionInfo conInfo = _node.getParentConnection();
		Object parent = _node.getParent();
		if(!(conInfo instanceof ConnectionInfoImpl) && parent instanceof ICatalogObject)
		{
			Database db = ((ICatalogObject)parent).getCatalogDatabase();
			conInfo = DatabaseConnectionRegistry.getInstance()
			.getConnectionForDatabase(db);
			if (conInfo == null)
			{
				EditorCorePlugin.getDefault().log(Messages.error_cannot_get_connection_profile);
				return null;
			}
		}
		String profileName = ((ConnectionInfoImpl)conInfo).getConnectionProfile().getName();
		return profileName;
	}
	
	/**
	 * Refreshes the parent object. Only handles procedural objects now.
	 * TODO are there any straight forward way to get the parent node of an object?
	 * @param element
	 */
	public static void refreshParent(SQLObject element)
	{
//		Object parent = IServicesManager.INSTANCE
//				.getServerExplorerNavigationService().getParent(element);
//		while (parent != null && !(parent instanceof ICatalogObject)) {
//			parent = IServicesManager.INSTANCE
//					.getServerExplorerNavigationService().getParent(parent);
//		}
		SQLObject parent = null;
		if (element instanceof Trigger)
		{
			parent = ((Trigger)element).getSubjectTable();
		}
		else if (element instanceof Routine)
		{
			parent = ((Routine)element).getSchema();
		}
		else if (element instanceof Event)
		{
			parent = ((Event)element).getDatabase();
		}
		if (parent instanceof ICatalogObject)
		{
			((ICatalogObject) parent).refresh();
		}
	}
    
    
    /**
     * Get the ActionService by SQLObject
     * 
     * @param object the sql model
     * @return the UIComponentService
     */
    public static ActionService getActionService(SQLObject object)
    {
        ProcIdentifier procIdentifier = SQLDevToolsUtil.getProcIdentifier(object);
        return getActionService(procIdentifier.getProfileName());
    }
    
    
    /**
     * Get the ActionService by virtual node
     * 
     * @param node the virtual node
     * @return the ActionService
     */
    public static ActionService getActionService(IVirtualNode node)
    {
        String profileName = ((ConnectionInfoImpl) node.getParentConnection()).getConnectionProfile().getName();
        return getActionService(profileName);
    }

    /**
     * Get the ActionService by profile name. Clients need to check whether the profile exists before calling this method.
     * 
     * @param profileName profile name
     * @return the UIComponentService
     */
    public static ActionService getActionService(String profileName)
    {
        SQLDevToolsUIConfiguration conf = SQLToolsUIFacade.getConfigurationByProfileName(profileName);
        return conf.getActionService();
    }


    /**
     * Get the ActionService by DatabaseVendorDefinitionId
     * 
     * @param vendorId 
     * @return the UIComponentService
     */
    public static ActionService getActionService(DatabaseVendorDefinitionId vendorId)
    {
        SQLDevToolsUIConfiguration conf = SQLToolsUIFacade.getConfigurationByVendorIdentifier(vendorId);
        return conf.getActionService();
    }
    
}

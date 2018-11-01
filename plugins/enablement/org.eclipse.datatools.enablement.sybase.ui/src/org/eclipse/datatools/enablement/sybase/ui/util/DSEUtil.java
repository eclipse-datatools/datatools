/***********************************************************************************************************************
 * Copyright (c) 2004, 2005,2008 Sybase, Inc. and others.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.ui.util;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sqm.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode;
import org.eclipse.datatools.connectivity.sqm.server.internal.ui.explorer.loading.LoadingNode;
import org.eclipse.datatools.enablement.sybase.ui.SybaseDatabaseProfileSettingManager;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.sqltools.internal.refresh.ICatalogObject2;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;

/**
 * 
 * @author Hao-yue
 * 
 */
public class DSEUtil
{
    public static final String DSEID = "org.eclipse.datatools.connectivity.DataSourceExplorerNavigator";

    public static final String EEID  = "com.sybase.stf.servers.ui.views.suadeserversview";

    // get the DSE View
    public static CommonNavigator getDSEView(IViewReference[] reference)
    {
        for (int i = 0; i < reference.length; i++)
        {
            if (reference[i].getId().equals(DSEID) || reference[i].getId().equals(EEID))
            {
                CommonNavigator view = (CommonNavigator) reference[i].getView(true);                
                return view;
            }
        }
        return null;
    }
    
    public static IConnectionProfile findParentProfileByElement(Object element){
        IConnectionProfile profile = null;
        CommonViewer viewer = ((CommonNavigator)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(DSEID)).getCommonViewer();
        if(element instanceof IConnectionProfile){
            profile = (IConnectionProfile)element;
        }
        else{
            TreeItem[] treeItems = viewer.getTree().getItems();
            TreeItem treeItem = null;
            for(int i=0; i<treeItems.length; i++){
                treeItem = findItem(element, treeItems[i]);
                if(treeItem!=null) break;
            }
            if(treeItem != null){
                TreeItem parentItem = treeItem.getParentItem();
                if(parentItem == null) return null; 
                while(!(parentItem.getData() instanceof IConnectionProfile)){
                    parentItem = parentItem.getParentItem();
                    if(parentItem == null) return null; 
                }
                profile = (IConnectionProfile)parentItem.getData();
            }
        }
        return profile;        
    }
    
    public static IConnectionProfile findParentProfileBySelectionElement(){
        CommonViewer viewer = ((CommonNavigator)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(DSEID)).getCommonViewer();
        Object selectedElement = ((IStructuredSelection)viewer.getSelection()).getFirstElement();
        return findParentProfileByElement(selectedElement);
    }
    
    public static TreeItem findTreeItem(Object targetData){
    	CommonViewer viewer = ((CommonNavigator)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(DSEID)).getCommonViewer();
		TreeItem[] treeItems = viewer.getTree().getItems();
		TreeItem treeItem = null;
		for(int i=0; i<treeItems.length; i++){
			treeItem = findItem(targetData, treeItems[i]);
			if(treeItem!=null) break;
		}
		return treeItem;
    }
    
    private static TreeItem findItem(Object target, TreeItem startItem){
    	if(startItem.getData() == target){
    		return startItem;
    	}
    	else{
    		TreeItem[] childItems = startItem.getItems();
    		if(childItems!=null && childItems.length>0){
    			for(int i=0; i<childItems.length; i++){
    				TreeItem item = findItem(target, childItems[i]);
    				if(item!=null) return item;
    			}
    		}
    	}
    	return null;
    }
    
    public static void refreshItem(Object item){
        IViewReference[] viewReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getViewReferences();
        CommonNavigator navigator = getDSEView(viewReferences);
        navigator.getCommonViewer().refresh(item, true);
    }
    
    public static void setSelection(ISelection selection){
        IViewReference[] viewReferences = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getViewReferences();
        CommonNavigator navigator = getDSEView(viewReferences);
        navigator.getCommonViewer().setSelection(selection);        
    }
    
    public static boolean checkIsShowSchema(Object element){
        IConnectionProfile profile = findConnectionProfileByChild(element);
        // When a database object was dropped, it's schema was set to null,
        // so there is no chance to locate it's parent connection profile by Model way,
        // at the situation, we locate the profile by UI way.
        if (profile == null)
        {
            profile = findParentProfileByElement(element);
        }
        return SybaseDatabaseProfileSettingManager.getInstance().isShowSchema(profile == null ? "" : profile.getInstanceID(), findDatabaseNameByChild(element));  
    }
      
    public static boolean checkIsShowOwner(Object element){
        IConnectionProfile profile = findConnectionProfileByChild(element);
        // When a database object was dropped, it's schema was set to null,
        // so there is no chance to locate it's parent connection profile by Model way,
        // at the situation, we locate the profile by UI way.
        if (profile == null)
        {
            profile = findParentProfileByElement(element);
        }
        return SybaseDatabaseProfileSettingManager.getInstance().isShowOwner(profile == null ? "" : profile.getInstanceID(), findDatabaseNameByChild(element));
    }
    
    public static IConnectionProfile findConnectionProfileByChild(Object child)
    {
        if(child instanceof IConnectionProfile){
            return (IConnectionProfile)child;
        }
        else if (child instanceof ConnectionInfo)
        {
            return ((ConnectionInfo) child).getConnectionProfile();
        }
        else if (child instanceof Catalog)
        {
            return ModelUtil.getConnectionProfile(((Catalog) child).getDatabase());
        }
        else if (child instanceof Database)
        {
            return ModelUtil.getConnectionProfile((Database) child);
        }
        else if (child instanceof SQLObject)
        {
        	SQLObject sqlObject = (SQLObject) child;
            Catalog catalog = ModelUtil.getCatalog(sqlObject);
            IConnectionProfile connectionProfile = ModelUtil.getConnectionProfile(catalog.getDatabase());
            return connectionProfile;
        }
        else if (child instanceof LoadingNode) {
        	return null;
        }
        else
        {
            return ((IVirtualNode) child).getParentConnection().getConnectionProfile();
        }
    }
    
    public static String findDatabaseNameByChild(Object child)
    {
        if (child instanceof ConnectionInfo)
        {
            return ((ConnectionInfo) child).getDatabaseName();
        }
        else if (child instanceof Catalog)
        {
            return ((Catalog) child).getName();
        }
        else if (child instanceof Database)
        {
            return ((Database) child).getName();
        }
        else if (child instanceof SQLObject)
        {
            return ModelUtil.getDatabaseName((SQLObject) child);
        }
        else
        {
            if (child instanceof IVirtualNode && ((IVirtualNode) child).getParent() instanceof SQLObject){
                return findDatabaseNameByChild((SQLObject)((IVirtualNode) child).getParent());
            }
            return null;
        }
    }    
    
    public static Database findDatabaseByChild(Object child)
    {
    	if (child instanceof ConnectionInfo)
        {
            return ((ConnectionInfo) child).getSharedDatabase();
        }
    	else if (child instanceof SQLObject)
        {
            return getDatabase((SQLObject)child);
        }
        else
        {
            if (child instanceof IVirtualNode && ((IVirtualNode) child).getParent() instanceof SQLObject){
                return findDatabaseByChild((SQLObject)((IVirtualNode) child).getParent());
            }
            return null;
        }
    }
    
    private static Database getDatabase(EObject obj)
    {
    	EObject container = obj;
        while (container != null)
        {
            obj = container;
            if (obj instanceof Database)
            {
                return ((Database)obj);
            }
            container = ContainmentServiceImpl.INSTANCE.getContainer(obj);
        }
        return null;
    }

    public static void refreshObjectBySchema(Schema schema, Object object){
        IConnectionProfile profile = DSEUtil.findConnectionProfileByChild(schema);
        String databaseName = DSEUtil.findDatabaseNameByChild(schema);
        boolean isShowSchema = SybaseDatabaseProfileSettingManager.getInstance().isShowSchema(profile.getInstanceID(),
                databaseName);
        if (isShowSchema)
        {
            if (schema instanceof ICatalogObject2)
            {
                ICatalogObject2 cataObj = (ICatalogObject2) schema;
                cataObj.refresh(cataObj.getRefreshContext(object));
            }
            else if (schema instanceof ICatalogObject)
            {
                ICatalogObject cataObj = (ICatalogObject) schema;
                cataObj.refresh();
            }
        }
        else
        {
            Catalog catalog = ModelUtil.getCatalog(schema);
            if (catalog.getDatabase().getVendor().indexOf("ASE") == -1) //$NON-NLS-1$
            {
                ((ICatalogObject) catalog.getDatabase()).refresh();
            }
            else
            {
                ((ICatalogObject) catalog).refresh();
            }
        }        
    }
}

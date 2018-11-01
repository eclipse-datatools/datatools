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
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.util;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.sqm.core.containment.ContainmentServiceImpl;
import org.eclipse.datatools.connectivity.sqm.core.ui.explorer.virtual.IVirtualNode;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.SQLObject;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.SQLFilePreferenceConstants;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.SqlscrapbookPlugin;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.editor.ScrapbookEditorConnectionInfo;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.preferences.PreferenceConstants;
import org.eclipse.datatools.sqltools.sql.util.ModelUtil;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorConnectionInfo;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;

/**
 * 
 * @author Hui Cao
 * 
 */
public class SQLFileUtil {

	public static ISQLEditorConnectionInfo getConnectionInfo(IFile file) {
		String encodedConnection = null;
		encodedConnection = getEncodedConnectionInfo(file);
		ISQLEditorConnectionInfo connectionInfo;
		if ((encodedConnection != null)
				&& (!encodedConnection.trim().equals(""))) {
			connectionInfo = SQLEditorConnectionInfo.decode(encodedConnection);
		} else {
			connectionInfo = SQLEditorConnectionInfo.DEFAULT_SQLEDITOR_CONNECTION_INFO;
		}
		return connectionInfo;
	}

	   /**
     * Adapt other kind ConnectionInfo to ScrapbookEditorConnectionInfo.
     * 
     * @param connInfo An instance of ISQLEditorConnectionInfo
     * @return An ISQLEditorConnectionInfo which is an instance of ScrapbookEditorConnectionInfo.
     */
    public static ISQLEditorConnectionInfo getConnectionInfo4Scrapbook(ISQLEditorConnectionInfo connInfo)
    {
        ScrapbookEditorConnectionInfo scrapbookConnInfo = new ScrapbookEditorConnectionInfo(connInfo);
        scrapbookConnInfo.setAutoCommit(SqlscrapbookPlugin.getDefault().getPreferenceStore().getInt(SQLFilePreferenceConstants.CONNECTION_COMMIT_MODE) == 0);
        return scrapbookConnInfo;
    }

	
	public static String getEncodedConnectionInfo(IFile file) {
		String encodedConnection = null;
		if (file != null) {
			try {
				encodedConnection = file
						.getPersistentProperty(new QualifiedName(
								SqlscrapbookPlugin.PLUGIN_ID,
								"encodedConnection"));
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return encodedConnection;
	}

	public static void setEncodedConnectionInfo(IFile fileResource, String encodedConnection)
	{
		try {
			// Save PersistentProperty encodedConnection for not *.sqlpage
			if (fileResource.exists()) {
				fileResource.setPersistentProperty(new QualifiedName(
						SqlscrapbookPlugin.PLUGIN_ID, "encodedConnection"),
						encodedConnection);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the default connection info. This method first checks the current selection
	 * in DSE view, if not, get it from preference. If the preference value
	 * is not set, SQLEditorConnectionInfo.DEFAULT_SQLEDITOR_CONNECTION_INFO is returned.
	 * @return
	 */
    public static ISQLEditorConnectionInfo getDefaultConnectionInfo()
    {
    	ISQLEditorConnectionInfo editorConnectionInfo = getSelectedConnectionInfo();
    	if (editorConnectionInfo == null)
    	{
    		String dftConn = SqlscrapbookPlugin.getDefault().getPreferenceStore().getString(PreferenceConstants.SQLEDITOR_CONNECTION_INFO);
    		if (dftConn != null && !dftConn.equals(""))
    		{
    			editorConnectionInfo = SQLEditorConnectionInfo.decode(dftConn);
    		}
    	}
    	if (editorConnectionInfo == null)
    	{
    		return SQLEditorConnectionInfo.DEFAULT_SQLEDITOR_CONNECTION_INFO;
    	}
    	return editorConnectionInfo;
    }
	
    public static final String DSEID = "org.eclipse.datatools.connectivity.DataSourceExplorerNavigator";


    /**
     * 
     * @return
     */
    public static ISQLEditorConnectionInfo getSelectedConnectionInfo()
    {
    	IViewReference[] reference = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getViewReferences();
        for (int i = 0; i < reference.length; i++)
        {
            if (reference[i].getId().equals(DSEID))
            {
                CommonNavigator view = (CommonNavigator) reference[i].getView(true);
                IStructuredSelection sel = (IStructuredSelection)view.getCommonViewer().getSelection();
                Object obj = sel.getFirstElement();
                
                
                //select profile
                if (obj instanceof IConnectionProfile && ProfileUtil.isSupportedProfile((IConnectionProfile)obj))
                {
                	return new SQLEditorConnectionInfo(null, ((IConnectionProfile)obj).getName(), "");
                }
                //select virtual node
                if (obj instanceof IVirtualNode)
                {
                	obj = ((IVirtualNode)obj).getParent();
                }
                //select model
                if (obj instanceof SQLObject)
                {
                	String dbName = ModelUtil.getDatabaseName((SQLObject)obj);
                	Object database = ContainmentServiceImpl.INSTANCE.getRootElement((SQLObject)obj);
                	if (database instanceof Database)
                	{
                		IConnectionProfile profile = ModelUtil.getConnectionProfile((Database)database);
                		if (profile != null)
                		{
                			return new SQLEditorConnectionInfo(null, profile.getName(), dbName);
                		}
                	}
                }
                return null;
            }
        }
        return null;
    }
}

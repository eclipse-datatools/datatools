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
package org.eclipse.datatools.sqltools.sqleditor.internal.profile;

import org.eclipse.datatools.connectivity.ConnectEvent;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.sqltools.core.profile.ConnectProfile;
import org.eclipse.datatools.sqltools.core.profile.ISQLToolsProfileListener;
import org.eclipse.datatools.sqltools.editor.core.connection.ISQLToolsConnectListener;
import org.eclipse.datatools.sqltools.sqleditor.EditorConstants;
import org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorPlugin;
import org.eclipse.datatools.sqltools.sqleditor.internal.utils.EditorUtil;

/**
 * 
 * @author Hui Cao
 * 
 */
public class SQLEditorProfileListener implements ISQLToolsProfileListener, ISQLToolsConnectListener {

	public void profileAdded(IConnectionProfile profile) {
		// TODO Auto-generated method stub

	}

	public void profileChanged(IConnectionProfile profile, String oldName,
			String oldDesc, Boolean oldAutoConnect, boolean onlyNameChanged, ConnectProfile oldProfile) {
        //editors
        if(onlyNameChanged)
        {
            EditorUtil.renameEditorsProfileName(oldName, profile.getName());
        }
        else
        {
        	EditorUtil.setEditorsProfileStatus(profile.getName(), EditorConstants.CP_STATUS_PROP_CHANGED);    
            boolean success = EditorUtil.closeAllEditors(profile.getName());
        }


	}

	public void profileDeleted(IConnectionProfile profile) {
        //close all non-dirty editors
		EditorUtil.setEditorsProfileStatus(profile.getName(), EditorConstants.CP_STATUS_DELETED);    
        boolean success = EditorUtil.closeAllEditors(profile.getName());
	}

	public void closeConnection(ConnectEvent event) {
		IConnectionProfile profile = event.getConnectionProfile();
        //mark all editors with disconnect so that when they get focus, proper action will take place
		EditorUtil.setEditorsProfileStatus(profile.getName(), EditorConstants.CP_STATUS_DISCONNECTED);    
        boolean success = EditorUtil.closeAllEditors(profile.getName());
	}

	public boolean okToClose(ConnectEvent event) {
		String profileName = event.getConnectionProfile().getName();
		return EditorUtil.okToCloseEditors(profileName);
	}

	public void aboutToClose(ConnectEvent event) {
		// TODO Auto-generated method stub
		
	}

	public void profileConnected(ConnectEvent event) {
		final IConnectionProfile profile = event.getConnectionProfile();
        // If profile is connected, we should reset all related editors connection status
		// why profile.isConnected() == false here?
		final int status = EditorConstants.CP_STATUS_CONNECTED;
    	SQLEditorPlugin.getDisplay().asyncExec(new Runnable()
        {
            public void run()
            {
                EditorUtil.setEditorsProfileStatus(profile.getName(), status);
            }
        }
        );

	}

}

/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Exadel Inc - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.views.execute;

import org.eclipse.datatools.connectivity.core.internal.ui.explorer.virtual.IConnectionNode;
import org.eclipse.datatools.connectivity.internal.core.connection.ConnectionInfo;
import org.eclipse.datatools.connectivity.server.internal.ui.services.IConnectedServerDialog;
import org.eclipse.datatools.connectivity.server.internal.ui.services.IServicesManager;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.SqlscrapbookPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.Window;

/**
 * Select connection for SQL scrapbook editor from list of available connections. 
 */
public class SQLScrapbookSelectConnectionAction extends Action {

    public static final String CONNECTION = "Connection"; //$NON-NLS-1$

    private ConnectionInfo connectionInfo = null;

    public SQLScrapbookSelectConnectionAction() {
        super(SqlscrapbookPlugin.getResourceString("_UI_MENU_SELECT_CONNECTION"));
    }

    public void setConnectionInfo(ConnectionInfo connectionInfo) {
        this.connectionInfo = connectionInfo;
    }

    public void run() {
        IConnectedServerDialog dialog = IServicesManager.INSTANCE.getConnectedServerDialog();
        int result = dialog.open();
        if (result == Window.OK) {
            IConnectionNode node = dialog.getUserSelection();
            if (node != null) {
                ConnectionInfo newConnectionInfo = node.getConnectionInfo();
                if (newConnectionInfo != null) {
                    firePropertyChange(CONNECTION, connectionInfo, newConnectionInfo);
                    connectionInfo = newConnectionInfo;
                }
            }
        }
    }

}

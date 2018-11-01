/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Exadel Inc - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.internal.sqlscrapbook.views.execute;

import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.internal.sqlscrapbook.SqlscrapbookPlugin;
import org.eclipse.jface.action.Action;

/**
 * Select connection for SQL scrapbook editor from list of available connections. 
 */
public class SQLScrapbookSelectConnectionAction extends Action {

    public static final String CONNECTION = "Connection"; //$NON-NLS-1$

    private ISQLEditorConnectionInfo connectionInfo = null;

    public SQLScrapbookSelectConnectionAction() {
        super(SqlscrapbookPlugin.getResourceString("_UI_MENU_SELECT_CONNECTION"));
    }

    public void setConnectionInfo(ISQLEditorConnectionInfo connectionInfo) {
        this.connectionInfo = connectionInfo;
    }

    public void run() {
        //TODO: 
//        IConnectedServerDialog dialog = IServicesManager.INSTANCE.getConnectedServerDialog();
//        int result = dialog.open();
//        if (result == Window.OK) {
//            IConnectionNode node = dialog.getUserSelection();
//            if (node != null) {
//                ISQLEditorConnectionInfo newISQLEditorConnectionInfo = node.getConnectionInfo();
//                if (newISQLEditorConnectionInfo != null) {
//                    firePropertyChange(CONNECTION, connectionInfo, newISQLEditorConnectionInfo);
//                    connectionInfo = newISQLEditorConnectionInfo;
//                }
//            }
//        }
    }

}

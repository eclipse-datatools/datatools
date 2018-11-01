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
package org.eclipse.datatools.sqltools.debugger.core;

import java.sql.Connection;
import java.sql.SQLException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.ServerIdentifier;
import org.eclipse.datatools.sqltools.core.profile.NoSuchProfileException;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.debugger.core.internal.DebuggerCorePlugin;
import org.eclipse.datatools.sqltools.debugger.core.internal.DebuggerMessages;
import org.eclipse.datatools.sqltools.debugger.core.ui.DebuggerCoreUIPlugin;
import org.eclipse.jface.dialogs.MessageDialog;

/**
 * @author Yang Liu
 */
public class AutoAttachUtil
{
    /**
     * If can't get connection id, may put "" as connection id.
     * So caller should check.
     * 
     * @param controlcon
     * @param autoattach
     * @param connid
     * @return
     * @throws SQLException
     * @throws CoreException
     * @throws NoSuchProfileException
     */
    public static Connection createConnection(IControlConnection controlcon, boolean autoattach, String[] connid) throws SQLException, CoreException, NoSuchProfileException
    {
        IControlConnectionExtension extension = (IControlConnectionExtension)controlcon.getAdapter(IControlConnectionExtension.class);
        Connection conn = null;
        if (extension != null)
        {
            conn = extension.createConnection(autoattach, connid);
        }
        else
        {
            conn = controlcon.createConnection(connid);
        }
        ServerIdentifier serverId = ProfileUtil.getServerIdentifier(controlcon.getDatabaseIdentifier());
        EditorCorePlugin.getControlConnectionManager().registerSkippedConnection(serverId, Integer.parseInt(connid[0]));
        return conn;
    }

    /**
     * @param controlCon
     */
    public static void enableAutoAttach(final IControlConnection controlCon)
    {
        IControlConnectionExtension extension = (IControlConnectionExtension)controlCon.getAdapter(IControlConnectionExtension.class);
        if (extension != null)
        {
            if (extension.getAutoAttachEnabled()) return;	// already enabled
            extension.setAutoAttachEnabled(true);
            DebuggerCoreUIPlugin.getDisplay().asyncExec(new Runnable()
            {
                public void run()
                {
                    MessageDialog.openInformation(DebuggerCoreUIPlugin.getActiveWorkbenchShell(),DebuggerMessages.AutoAttachUtil_information,DebuggerMessages.AutoAttachUtil_auto_attach_for_profile
                    +controlCon.getDatabaseIdentifier()+DebuggerMessages.AutoAttachUtil_enabled_by_system); 
                }
            }
            );
        }
    }
}

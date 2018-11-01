/*******************************************************************************
 * Copyright © 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.util;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilderPlugin;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Shell;


public class WindowUtility {

    public static Object getSelection(ISelection selection) {
        if (selection == null) {
            return null;
        } // end of if ()

        Object result = null;
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection es = (IStructuredSelection) selection;
            Iterator i = es.iterator();
            if (i.hasNext()) {
                result = i.next();
            }
        }
        return result;
    }

    public static List getSelectionList(ISelection selection) {
        List result = null;
        if (selection != null) {
            if (selection instanceof IStructuredSelection) {
                IStructuredSelection es = (IStructuredSelection) selection;
                result = new Vector();
                for (Iterator i = es.iterator(); i.hasNext();) {
                    result.add(i.next());
                }
            }
        }
        return result;
    }

    public static void openErrorCreatingFile(Shell shell, IResource resource) {
        String title = null;
        String briefMessage = null;
        String reason = null;
        String details = null;
        title = Messages._UI_ERROR_CREATING_FILE_TITLE;
        briefMessage = NLS.bind(Messages._UI_ERROR_CREATING_FILE_SHORT_DESC, resource.getName());
        details = NLS.bind(Messages._UI_ERROR_CREATING_FILE_LONG_DESC, resource.getLocation().toOSString());

        IResource parent = resource.getParent();
        if (parent != null) {
            if (parent.isReadOnly()) {
                reason = NLS.bind(Messages._UI_PARENT_FOLDER_IS_READ_ONLY, parent.getName());
            }
            else {
                // on windows the isReadOnly() = false for read only shared
                // directory... so we give a hint
                reason = NLS.bind(Messages._UI_UNKNOWN_ERROR_WITH_HINT, parent.getName());
            }
        }

        if (reason == null) {
            reason = Messages._UI_UNKNOWN_ERROR;
        }

        openError(shell, title, briefMessage, reason, details);
    }

    public static void openError(Shell shell, String title, String briefMessage, 
            String reason, String detailedMessage) {
        ErrorDialog.openError(shell, title, briefMessage, createStatus(reason, detailedMessage));
    }

    private static IStatus createStatus(String reason, String msg) {
        String pluginId = SQLBuilderPlugin.getPlugin().getBundle().getSymbolicName();
        MultiStatus multiStatus = new MultiStatus(pluginId, 0, reason, null);
        Status status = new Status(IStatus.ERROR, pluginId, 0, msg, null);
        multiStatus.add(status);
        return multiStatus;
    }
}
/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqleditor.internal.actions;

import java.util.ResourceBundle;

import org.eclipse.datatools.sqltools.editor.core.connection.ISQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditor;
import org.eclipse.ui.texteditor.ResourceAction;

/**
 * This class implements the "Disconnect" action for the SQL Editor.
 * 
 * @see org.eclipse.datatools.sqltools.sqleditor.internal.SQLEditorActionContributor
 * @see org.eclipse.ui.texteditor.ResourceAction
 */
public class SQLDisconnectAction extends ResourceAction {

    private SQLEditor fSQLEditor;
    
    /**
     * Creates an instance of this class using the given resource bundle
     * and prefix of a set of resources within the bundle.
     * 
     * @param bundle the resource bundle to use
     * @param prefix the resource prefix to use
     */
    public SQLDisconnectAction( ResourceBundle bundle, String prefix ) {
        super( bundle, prefix );
    }

    /**
     * Gets the SQLEditor instance associated with this action.
     * 
     * @return the SQLEditor instance for this action
     */
    public SQLEditor getSQLEditor() {
        return fSQLEditor;
    }

    /**
     * Runs this action.  Disconnects the editor from the currently connected database.
     * 
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run() {
        SQLEditor editor = getSQLEditor();
        if (editor != null) {
            ISQLEditorConnectionInfo connInfo = editor.getConnectionInfo();
            if (connInfo != null) {
                firePropertyChange( SQLConnectAction.CONNECTION, connInfo, null );
            }
        }
    }

    /**
     * Sets the SQLEditor instance associated with this action to the given object.
     * 
     * @param sqlEditor the SQLEditor instance for this action
     */
    public void setSQLEditor( SQLEditor sqlEditor ) {
        fSQLEditor = sqlEditor;
    }

} // end class

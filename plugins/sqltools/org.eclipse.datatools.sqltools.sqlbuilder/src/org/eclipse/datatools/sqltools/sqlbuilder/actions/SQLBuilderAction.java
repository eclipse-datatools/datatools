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
package org.eclipse.datatools.sqltools.sqlbuilder.actions;

import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilder;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Shell;


/**
 * This abstract class provides a base class for actions that need to associate
 * themselves with the SQLBuilder.
 * 
 * @author bpayton
 */
public abstract class SQLBuilderAction extends Action {

    /** The SQLBuilder associated with the action. */
    private SQLBuilder _sqlBuilder;
    private Shell fShell;

    /**
     * Constructs an instance of this class with the given action label text.
     * 
     * @param label the label text for the action
     */
    public SQLBuilderAction(String label) {
        super(label);
    }

    /**
     * Gets the SQLBuilder associated with this action.
     * 
     * @return the active SQLBuilder
     */
    public SQLBuilder getSQLBuilder() {
        return _sqlBuilder;
    }
    
    /**
     * Gets the display shell associated with this action.  The shell is used
     * for displaying dialogs.
     *  
     * @return the shell object for this action
     */
    public Shell getShell() {
        return fShell;
    }
    
    /**
     * Sets the SQLBuilder associated with this action to the given SQLBuilder.
     * 
     * @param sqlBuilder the SQLBuilder to set
     */
    public void setSQLBuilder( SQLBuilder sqlBuilder ) {
        _sqlBuilder = sqlBuilder;
    }
    
    /**
     * Sets the display shell associated with this action to the given shell.  The
     * shell is used for displaying dialogs.
     * 
     * @param shell the display shell to set
     */
    public void setShell( Shell shell ) {
        fShell = shell;
    }
}

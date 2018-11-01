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

import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.views.source.SQLSourceViewer;


/**
 * This class implements an action which reverts the current statement to its
 * template form.
 */
public class RevertToDefaultAction extends SQLBuilderAction {

    /**
     * Constructs an instance of this class.  This is the default constructor.
     */
    public RevertToDefaultAction() {
        this(Messages._UI_CLEAR_TO_TEMPLATE);
    }
    
    /**
     * Constructs an instance of this class with the given action label.
     * 
     * @param label the action label to use
     */
    public RevertToDefaultAction( String label ) {
        super( label );
    }
    
    /**
     * Runs the action.
     */
	public void run() {
        if (getSQLBuilder() != null){
            SQLSourceViewer sourceViewer = getSQLBuilder().getSourceViewer();
            if (sourceViewer != null) {
                sourceViewer.revertToDefaultSource();
            }
        }
	}
    
} // end class
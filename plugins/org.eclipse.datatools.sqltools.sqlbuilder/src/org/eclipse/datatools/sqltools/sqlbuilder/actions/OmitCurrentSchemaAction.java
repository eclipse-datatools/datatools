/*******************************************************************************
 * Copyright © 2000, 2007 Sybase, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.sqlbuilder.actions;

import org.eclipse.datatools.sqltools.sqlbuilder.Messages;
import org.eclipse.datatools.sqltools.sqlbuilder.SQLBuilder;
import org.eclipse.datatools.sqltools.sqlbuilder.OmitSchemaInfo;
import org.eclipse.datatools.sqltools.sqlbuilder.dialogs.OmitCurrentSchemaDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IEditorPart;

public class OmitCurrentSchemaAction extends EditorAction {

    /**
     * Constructs an instance of this class.  This is the default constructor.
     */
    public OmitCurrentSchemaAction() {
        this(Messages._UI_OMIT_CURRENT_SCHEMA);
    }

    /**
     * Constructs an instance of this class with the given action label.
     * 
     * @param label the action label to use
     */
    public OmitCurrentSchemaAction(String label) {
        super(label);
    }
    
    public void run() {

        IEditorPart activeEditor = getActiveEditor();
        if (activeEditor instanceof SQLBuilder) {
            SQLBuilder sqlBuilder = (SQLBuilder) activeEditor;
            OmitSchemaInfo omitSchemaInfo = sqlBuilder.getDomainModel().getOmitSchemaInfo();
            String userName = sqlBuilder.getDomainModel().getUserName();
            
            OmitSchemaInfo tmpOmitSchemaInfo = new OmitSchemaInfo();
            tmpOmitSchemaInfo.copyOmitSchemaInfo(omitSchemaInfo);
            OmitCurrentSchemaDialog dialog = new OmitCurrentSchemaDialog(getShell(), tmpOmitSchemaInfo, userName);

            dialog.create();

            dialog.setBlockOnOpen(true);
            int value = dialog.open();
            if (value == Window.CANCEL){
            	return;
            }
            else {
            	omitSchemaInfo.copyOmitSchemaInfo(tmpOmitSchemaInfo);
            }
        }
    }
}

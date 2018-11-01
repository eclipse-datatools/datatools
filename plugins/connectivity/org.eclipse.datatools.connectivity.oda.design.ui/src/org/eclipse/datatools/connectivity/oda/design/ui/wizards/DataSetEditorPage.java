/*
 *************************************************************************
 * Copyright (c) 2006 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.design.ui.wizards;

import org.eclipse.datatools.connectivity.oda.design.internal.ui.DataSetEditorPageCore;

/**
 * The base class of a customized ODA Data Set Editor Page
 * provided by the ODA design UI framework,  
 * to allow an user to edit
 * an extended ODA data set design instance.
 * <br>It adapts from a data set wizard page contributed
 * by an ODA design ui extension, to a PropertyPage
 * for use in an ODA host designer's preference dialog.
 */
public class DataSetEditorPage extends DataSetEditorPageCore
{
    /*
     * Constructor to contribute the control of 
     * a custom data set wizard page 
     * to a property page for editing a data set design.
     */
    public DataSetEditorPage( DataSetWizardPage page )
    {
        super( page );
    }

}

/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * @author quyon
 */
public class DataPreferencePage extends org.eclipse.jface.preference.PreferencePage implements IWorkbenchPreferencePage
{

    public DataPreferencePage() {
		super();
		noDefaultAndApplyButton();
	}

	protected Control createContents(Composite parent)
    {
        return null;
    }

    public void init(IWorkbench workbench)
    {
    }

}

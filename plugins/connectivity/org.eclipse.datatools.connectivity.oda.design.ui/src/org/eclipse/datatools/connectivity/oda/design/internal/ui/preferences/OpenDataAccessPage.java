/*
 *************************************************************************
 * Copyright (c) 2006, 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  Sybase, Inc. - implemented initial page contents
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.design.internal.ui.preferences;

import org.eclipse.datatools.connectivity.oda.design.ui.nls.Messages;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Preference page for the Open Data Access framework.
 */
public class OpenDataAccessPage extends PreferencePage implements
        IWorkbenchPreferencePage
{

    public OpenDataAccessPage()
    {
        super();
        noDefaultAndApplyButton();
    }

    public OpenDataAccessPage( String title )
    {
        super( title );
    }

    public OpenDataAccessPage( String title, ImageDescriptor image )
    {
        super( title, image );
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
     */
    protected Control createContents( Composite parent )
    {
		Composite comp = new Composite( parent, SWT.NONE );
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		comp.setLayout( layout );

		Label descLabel = new Label( comp, SWT.NONE );
		descLabel.setText( Messages.preferencesPage_openDataAccessDescription );

		return comp;
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init( IWorkbench workbench )
    {
        // do nothing
    }

}

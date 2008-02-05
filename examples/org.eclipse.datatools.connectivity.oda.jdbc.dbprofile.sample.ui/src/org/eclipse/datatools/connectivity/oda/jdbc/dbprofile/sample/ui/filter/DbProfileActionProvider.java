/*
 *************************************************************************
 * Copyright (c) 2008 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation - initial API and implementation
 *  
 *************************************************************************
 */

package org.eclipse.datatools.connectivity.oda.jdbc.dbprofile.sample.ui.filter;

import org.eclipse.datatools.connectivity.ui.navigator.actions.ProfileActionsActionProvider;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;

/**
 * Extends the DTP Data Source Explorer's Default Profile Action Provider
 * to provide own extended action(s).
 */
public class DbProfileActionProvider extends
        ProfileActionsActionProvider
{
    public void init(ICommonActionExtensionSite aConfig)
    {
        super.init(  aConfig );

        // override with own AddProfileViewAction
        setAddProfileViewAction( getAddDbProfileViewAction() );
    }

    protected AddDbProfileViewAction getAddDbProfileViewAction()
    {
        return new AddDbProfileViewAction();
    }

}

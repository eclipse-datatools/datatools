/*
 *************************************************************************
 * Copyright (c) 2007 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.design.ui.profile.db.wizards;

import org.eclipse.datatools.connectivity.oda.design.internal.ui.profile.db.DbProfilePropertyPage;

/**
 *  The default ODA data source editor page that wraps the driver-contributed 
 *  property page of a Database connection profile type.
 *  It may be used directly as the page class in an org.eclipse.ui.propertyPages
 *  extension for an ODA data source type.
 */
public class DbProfileEditorPage extends DbProfilePropertyPage
{
    public DbProfileEditorPage()
    {
        super();
    }

}

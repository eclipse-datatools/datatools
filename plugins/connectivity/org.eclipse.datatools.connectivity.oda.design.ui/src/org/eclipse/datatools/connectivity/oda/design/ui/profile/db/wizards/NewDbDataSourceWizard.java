/*
 *************************************************************************
 * Copyright (c) 2007 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.design.ui.profile.db.wizards;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.internal.ui.profile.db.NewDbDataSourceWizardBase;

/**
 *  The default ODA data source wizard that manages the driver-contributed 
 *  wizard page of a Database connection profile type.
 *  It may be used directly as the newWizard class in an 
 *  org.eclipse.datatools.connectivity.connectionProfile extension for 
 *  an ODA data source type.
 */
public class NewDbDataSourceWizard extends NewDbDataSourceWizardBase
{

    public NewDbDataSourceWizard( String odaDataSourceId ) throws OdaException
    {
        super( odaDataSourceId );
    }

    public NewDbDataSourceWizard()
    {
        super();
    }
    
}

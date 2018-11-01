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

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.design.internal.ui.NewDataSourceWizardBase;

/**
 * The ODA data source wizard's public class that 
 * may be used directly or serves as the base class of 
 * the newWizard.class attribute defined in the
 * <code>org.eclipse.datatools.connectivity.connectionProfile</code> extension point.
 * <br>An ODA data source extension must also
 * implement the <code>org.eclipse.datatools.connectivity.oda.design.ui.dataSource</code>
 * extension point to provide its custom wizard page. 
 * <br>All inherited methods are internal API.
 */
public class NewDataSourceWizard extends NewDataSourceWizardBase
{
    public NewDataSourceWizard( String odaDataSourceId )
        throws OdaException
    {
        super( odaDataSourceId );
    }

    public NewDataSourceWizard()
    {
        super();
    }

}

/*
 *************************************************************************
 * Copyright (c) 2006 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.design.ui.wizards;

import org.eclipse.datatools.connectivity.oda.design.internal.ui.NewDataSourceWizardBase;

/**
 * The ODA data source wizard's public class that 
 * may be specified directly
 * in the newWizard.class attribute
 * of the <code>org.eclipse.datatools.connectivity.connectionProfile</code> extension point.
 * <br>An ODA data source extension must also
 * implement the <code>org.eclipse.datatools.connectivity.oda.design.ui.dataSource</code>
 * extension point to provide custom wizard page. 
 * <br>All inherited methods are internal API.
 */
public class NewDataSourceWizard extends NewDataSourceWizardBase
{

    public NewDataSourceWizard()
    {
        super();
    }

}

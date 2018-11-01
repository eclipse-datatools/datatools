/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase
 ******************************************************************************/
package org.eclipse.datatools.enablement.ase.containment;

import org.eclipse.datatools.connectivity.sqm.core.containment.AbstractContainmentProvider;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDefault;
import org.eclipse.emf.ecore.EObject;

/**
 * Sybase ASE Default containment provider
 * 
 * @author renj
 */
public class SybaseASEDefaultContainmentProvider extends AbstractContainmentProvider
{
    public EObject getContainer(EObject obj)
    {
        return ((SybaseASEDefault) obj).getSchema();
    }

    public String getGroupId(EObject obj)
    {
        return DBEventGroupID.ASEDEFAULT;
    }
}

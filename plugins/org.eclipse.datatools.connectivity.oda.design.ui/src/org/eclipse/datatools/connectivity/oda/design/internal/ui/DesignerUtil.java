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

package org.eclipse.datatools.connectivity.oda.design.internal.ui;

import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 *  Internal utility class to provide services
 *  to the ODA Designer packages.
 */
public class DesignerUtil
{
    public static OdaException newOdaException( Throwable cause )
    {
        OdaException odaEx = new OdaException();
        odaEx.initCause( cause );
        return odaEx;
    }

}

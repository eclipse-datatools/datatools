/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.tableeditor.utils;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS
{

    private static final String BUNDLE_NAME = Messages.class.getPackage().getName() + ".messages"; //$NON-NLS-1$

    private Messages()
    {
        // Do not instantiate
    }

    public static String debugger_DmpImages_malformedURLException;
    public static String ConstraintNameTextDefault;

    static
    {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

}

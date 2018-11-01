/*******************************************************************************
 * Copyright (c) 2001, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.connectivity.sqm.core.internal.ui.explorer.helpers;

import java.text.MessageFormat;

import org.eclipse.datatools.connectivity.sqm.core.internal.ui.util.resources.ResourceLoader;
import org.eclipse.datatools.modelbase.sql.constraints.Index;

/**
 * @author ljulien
 */
public class IndexHelper
{
    public static final IndexHelper INSTANCE = new IndexHelper ();

    private static final String FORMAT = " [{0}]"; //$NON-NLS-1$
    private static final String BLANK = ""; //$NON-NLS-1$
    
    private static final ResourceLoader resource = ResourceLoader.getResourceLoader();
    private static final String UNIQUE = resource.queryString("DATATOOLS.CORE.UI.INDEX_UNIQUE.DECORATION"); //$NON-NLS-1$
 
    private IndexHelper () 
    {
    }
    
    public String getDecoration (Index index)
    {
        if (index.isUnique())
        {
            return MessageFormat.format(FORMAT, new String [] {UNIQUE});
        }
        return BLANK;
    }
}

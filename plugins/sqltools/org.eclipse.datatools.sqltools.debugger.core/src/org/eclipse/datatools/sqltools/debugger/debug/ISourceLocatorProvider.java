/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.sqltools.debugger.debug;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.IPersistableSourceLocator;

/**
 * This interface gives the extender an opportunity to change the default source locator used
 * by the sql debugger framework. e.g. clients may contribute a specific source locator and
 * model presentation to open a multipage form editor instead of the standalone sql editor.
 * @author Hui Cao
 * 
 */
public interface ISourceLocatorProvider
{

    /**
     * Returns an <code>ISourceLocator</code> object based on the configuration. Returns null if the configuration is
     * not supported. To open the source in a particular editor, let the source locator object implement
     * ISourcePresentation as well.
     * 
     * @param configuration
     * @return
     */
    public IPersistableSourceLocator getSourceLocator(ILaunchConfiguration configuration);
}

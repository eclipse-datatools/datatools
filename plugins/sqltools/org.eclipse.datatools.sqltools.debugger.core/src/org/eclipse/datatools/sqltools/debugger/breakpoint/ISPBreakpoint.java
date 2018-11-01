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
package org.eclipse.datatools.sqltools.debugger.breakpoint;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.debug.core.model.IBreakpoint;


/**
 * Defines a breakpoint associated with a <code>Routine</code>.
 * 
 * @author Yang Liu
 */
public interface ISPBreakpoint extends IBreakpoint
{
    /**
     * Returns the associated <code>Routine</code> identifier.
     * 
     * @return <code>ProcIdentifier</code> representing a <code>Routine</code>
     */
    public ProcIdentifier getProcIdentifier() throws CoreException;
}

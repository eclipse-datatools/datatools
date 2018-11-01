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
package org.eclipse.datatools.sqltools.debugger.core.internal;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.debugger.core.IControlConnectionExtension;
import org.eclipse.datatools.sqltools.internal.core.AbstractControlConnection;
import org.eclipse.datatools.sqltools.routineeditor.launching.LaunchHelper;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;

/**
 * @author Yang Liu
 */
public abstract class AbstractControlConnectionExtension implements IControlConnectionExtension
{
    Set							_skipSet = new HashSet();
    boolean						_enabled = false;
    AbstractControlConnection	_controlConnection;

    /**
     * 
     */
    public AbstractControlConnectionExtension(AbstractControlConnection controlcon)
    {
        _controlConnection = controlcon;
    }

    public IControlConnection getControlConnection()
    {
        return _controlConnection;
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.core.IControlConnectionExtension#getAutoAttachEnabled()
     */
    public boolean getAutoAttachEnabled()
    {
        return _enabled;
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.core.IControlConnectionExtension#setAutoAttachEnabled(boolean)
     */
    public void setAutoAttachEnabled(boolean b)
    {
        if (_enabled == b) return;
        _enabled = b;
        _controlConnection.fireChange();
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.core.IControlConnectionExtension#registerSkip(java.lang.Object)
     */
    protected void registerSkip(Object id)
    {
        synchronized(_skipSet)
        {
            _skipSet.add(id);
        }
    }

    /* (non-Javadoc)
     * @see com.sybase.stf.dmp.core.IControlConnectionExtension#unregisterSkip(java.lang.Object)
     */
    protected void unregisterSkip(Object id)
    {
        synchronized(_skipSet)
        {
            _skipSet.remove(id);
        }
    }

    protected Set getSkipSet()
    {
        return _skipSet;
    }

    protected void attachConnection(String connId) throws CoreException
    {
        ILaunchConfiguration config = LaunchHelper.createExternalClientConfiguration(_controlConnection.getDatabaseIdentifier(), connId);
        // XXX: here we are not using DebugUITools.launch(), because we don't want to any dialog popup during the launch,
        // and also we don't want to depend on UI in this class.
        config.launch(ILaunchManager.DEBUG_MODE, new NullProgressMonitor());
    }
}

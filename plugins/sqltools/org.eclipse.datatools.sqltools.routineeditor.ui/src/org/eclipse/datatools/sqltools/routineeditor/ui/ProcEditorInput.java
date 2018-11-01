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
package org.eclipse.datatools.sqltools.routineeditor.ui;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.sqltools.core.EditorCorePlugin;
import org.eclipse.datatools.sqltools.core.IControlConnection;
import org.eclipse.datatools.sqltools.core.ProcIdentifier;
import org.eclipse.datatools.sqltools.core.profile.NoSuchProfileException;
import org.eclipse.datatools.sqltools.core.profile.ProfileUtil;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorConnectionInfo;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorStorage;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorStorageEditorInput;
import org.eclipse.datatools.sqltools.sqleditor.SQLEditorStringStorage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPersistableElement;


/**
 * The editor input object for routine editor.
 * @author Yang Liu
 */
public class ProcEditorInput extends SQLEditorStorageEditorInput
{
    ProcIdentifier _procIdentifier;
    boolean        _isEditable = true;

    /**
     *   
     */
    public ProcEditorInput(ProcIdentifier procid)
    {
    	super(procid.getDisplayString(), "");
        this._procIdentifier = procid;
        String code = "";
        try
        {
        	IControlConnection controlConnection = EditorCorePlugin.getControlConnectionManager().getOrCreateControlConnection(procid.getDatabaseIdentifier());
			code = controlConnection.getProcSource(procid);
        }
        catch(Exception e)
        {
        	RoutineEditorUIActivator.getDefault().log(e);
        }
        setStorage(new SQLEditorStringStorage(_procIdentifier.getDisplayString(), code));
        setConnectionInfo(new SQLEditorConnectionInfo(ProfileUtil.getDatabaseVendorDefinitionId(procid.getProfileName()), procid.getProfileName(), procid.getDatabaseName(), procid.getOwnerName()));
     }

    public ProcIdentifier getProcIdentifier()
    {
        return _procIdentifier;
    }

    /**
     * Judges whether this IEditorInput is editable
     * 
     * @author hpgu
     * @return _isEditable
     */
    public boolean isEditable()
    {
        return _isEditable;
    }

    /**
     * Set isEditable status
     * 
     * @author hpgu
     * @param isEditable
     */
    public void setEditable(boolean isEditable)
    {
        _isEditable = isEditable;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IEditorInput#exists()
     */
    public boolean exists()
    {
        // XXX:
        // this is for whether to show in recent file list.
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IEditorInput#getImageDescriptor()
     */
    public ImageDescriptor getImageDescriptor()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IEditorInput#getName()
     */
    public String getName()
    {
        return _procIdentifier.getDisplayString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IEditorInput#getPersistable()
     */
    public IPersistableElement getPersistable()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IEditorInput#getToolTipText()
     */
    public String getToolTipText()
    {
        return _procIdentifier.getLongDisplayString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     */
    public Object getAdapter(Class adapter)
    {
        if (adapter == ProcIdentifier.class)
        {
            return this._procIdentifier;
        }
        else if (adapter == IConnectionProfile.class)
        {
        	try {
				return ProfileUtil.getProfile(_procIdentifier.getProfileName());
			} catch (NoSuchProfileException e) {
				return null;
			}
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj)
    {
        if (obj instanceof ProcEditorInput)
        {
            ProcEditorInput input = (ProcEditorInput) obj;
            return this._procIdentifier.equals(input._procIdentifier);
        }
        else
        {
            return false;
        }
    }


    /**
     * Convenience method for ((SQLEditorStorage)getStorage()).getContentsString() 
     * @return
     */
    public String getSourceCode()
    {
        return ((SQLEditorStorage)getStorage()).getContentsString();
    }

    /**
     * Convenience method for setStorage(new SQLEditorStorage(_procIdentifier.getDisplayString(), code))
     * @param code
     */
    public void setSourceCode(String code)
    {
    	setStorage(new SQLEditorStringStorage(_procIdentifier.getDisplayString(), code));
    }
    
    public boolean isConnectionRequired()
    {
        return true;
    }

    public String getId()
    {
        return getClass().getName() + "(" + _procIdentifier.encode() + ")";
    }
    
}

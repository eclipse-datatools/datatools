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

package org.eclipse.datatools.connectivity.ui.templates;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.pde.core.plugin.IPluginReference;

/**
 * Reference of a generated plug-in that uses the compatible match rule. 
 */
class CompatiblePluginReference implements IPluginReference
{
    private String m_id;
    private String m_version;
    private int m_match = COMPATIBLE;

    public CompatiblePluginReference( String id, String version )
    {
        m_id = id;
        m_version = version;
    }

    /* (non-Javadoc)
     * @see org.eclipse.pde.core.IIdentifiable#getId()
     */
    public String getId()
    {
        return m_id;
    }

    /* (non-Javadoc)
     * @see org.eclipse.pde.core.plugin.IPluginReference#getVersion()
     */
    public String getVersion()
    {
        return m_version;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.pde.core.plugin.IPluginReference#getMatch()
     */
    public int getMatch()
    {
        return m_match;
    }

    /* (non-Javadoc)
     * @see org.eclipse.pde.core.IIdentifiable#setId(java.lang.String)
     */
    public void setId( String id ) throws CoreException
    {
        m_id = id;
    }

    /* (non-Javadoc)
     * @see org.eclipse.pde.core.plugin.IPluginReference#setVersion(java.lang.String)
     */
    public void setVersion( String version ) throws CoreException
    {
        m_version = version;
    }

    /* (non-Javadoc)
     * @see org.eclipse.pde.core.plugin.IPluginReference#setMatch(int)
     */
    public void setMatch( int match ) throws CoreException
    {
        m_match = match;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals( Object obj )
    {
        if ( ! ( obj instanceof IPluginReference ) )
            return false;

        IPluginReference other = (IPluginReference) obj;
        if( m_match != other.getMatch() )
            return false;
        
        if( m_id == null && other.getId() != null )
            return false;
        if( m_id != null && other.getId() == null )
            return false;
        if( m_id != null &&
            ! m_id.equals( other.getId() ))
            return false;
        
        if( m_version == null && other.getVersion() != null )
            return false;
        if( m_version != null && other.getVersion() == null )
            return false;
        if( m_version != null &&
            ! m_version.equals( other.getVersion() ))
            return false;

        // all three attributes are an exact match
        return true;
    }

}

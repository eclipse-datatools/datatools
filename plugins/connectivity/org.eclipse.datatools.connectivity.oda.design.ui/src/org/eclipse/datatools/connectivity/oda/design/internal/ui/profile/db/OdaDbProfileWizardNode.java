/*
 *************************************************************************
 * Copyright (c) 2007 Actuate Corporation.
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

package org.eclipse.datatools.connectivity.oda.design.internal.ui.profile.db;

import org.eclipse.datatools.connectivity.internal.ui.wizards.CPWizardNode;
import org.eclipse.datatools.connectivity.ui.wizards.IProfileWizardProvider;
import org.eclipse.datatools.connectivity.ui.wizards.NewConnectionProfileWizard;
import org.eclipse.jface.wizard.IWizard;

/**
 * An extended Connection Profile Wizard Node which modifies
 * the behavior of a DB connection profile node's wizard.
 */
public class OdaDbProfileWizardNode extends CPWizardNode
{        
    private NewDbDataSourceWizardBase m_sessionWizard;
    private NewConnectionProfileWizard.IFinishTask m_sessionFinishTask;
    
    public OdaDbProfileWizardNode( IProfileWizardProvider provider, NewDbDataSourceWizardBase wizard )
    {
        super( provider );
        
        assert( m_sessionWizard != null );
        m_sessionWizard = wizard;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.datatools.connectivity.internal.ui.wizards.CPWizardNode#createWizard()
     */
    protected IWizard createWizard() 
    {
        IWizard nodeWizard = super.createWizard();
        if( nodeWizard instanceof NewConnectionProfileWizard )
        {
            NewConnectionProfileWizard cpWizard = (NewConnectionProfileWizard) nodeWizard;
            if( m_sessionWizard.isInOdaDesignSession() )
            {
                // profile name is provided by the ODA host, instead of from the profile wizard
                cpWizard.setProfileName( m_sessionWizard.getProfileName() );
                cpWizard.setSkipProfileNamePage( true );
            }
            cpWizard.delegatesTask( getOdaSessionFinishTask() );
        }
        else
        {
            // TODO log warning about possible side-effect
        }
        
        return nodeWizard;
    }
    
    private NewConnectionProfileWizard.IFinishTask getOdaSessionFinishTask()
    {
        if( m_sessionFinishTask == null )
        {
            m_sessionFinishTask = 
                new NewConnectionProfileWizard.IFinishTask()
                {
                    public boolean performFinish( NewConnectionProfileWizard delegatingWizard )
                    {
                        if( m_sessionWizard == null )  
                            // TODO log warning
                            return false;   // not able to delegate
                        
                        // if current wizard takes user input values in its profile page,
                        // copy its page values to the session wizard's profile page for use at finish
                        if( ! delegatingWizard.isProfileNamePageSkippable() ) 
                        {
                            m_sessionWizard.setProfilePageProperties( 
                                    delegatingWizard.getProfileName(), 
                                    delegatingWizard.getProfileDescription(), 
                                    Boolean.valueOf( delegatingWizard.getProfileIsAutoConnect() ), 
                                    null );     
                        }
                        
                        return m_sessionWizard.performFinish();
                    }
                };
        }
        return m_sessionFinishTask;
    }
    
}

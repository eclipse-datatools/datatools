/***********************************************************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.ase.internal.ui.connection;

import org.eclipse.datatools.connectivity.ui.wizards.ExtensibleNewConnectionProfileWizard;
import org.eclipse.datatools.enablement.ase.JDBCASEProfileMessages;
import org.eclipse.datatools.enablement.sybase.ase.ui.ASEUIPlugin;
import org.eclipse.datatools.enablement.sybase.ase.ui.IHelpConstants;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.help.IContext;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;

/**
 * @see Wizard
 */
public class NewASEConnectionProfileWizard extends ExtensibleNewConnectionProfileWizard
{

    private ContextProviderDelegate contextProviderDelegate           = new ContextProviderDelegate(ASEUIPlugin
                                                                              .getDefault().getBundle()
                                                                              .getSymbolicName());

    public static String            ASE_CONNECTION_PROPERTY_PAGE_NAME = "aseWizPage";             //$NON-NLS-1$

    public IContext getContext(Object target)
    {
        return contextProviderDelegate.getContext(target);
    }

    public int getContextChangeMask()
    {
        return contextProviderDelegate.getContextChangeMask();
    }

    public String getSearchExpression(Object target)
    {
        return contextProviderDelegate.getSearchExpression(target);
    }

    public NewASEConnectionProfileWizard()
    {
        super(new ASEDBProfileDetailsWizardPage(ASE_CONNECTION_PROPERTY_PAGE_NAME));
        setWindowTitle(JDBCASEProfileMessages.getString("NewConnectionProfileWizard.ASE.title")); //$NON-NLS-1$
    }

    public void createPageControls(Composite pageContainer)
    {
        super.createPageControls(pageContainer);

        getShell().setData(HelpUtil.CONTEXT_PROVIDER_KEY, this);
        HelpUtil.setHelp(pageContainer.getShell(), IHelpConstants.ASE_PROFILE_WIZARD_PAGE);
    }

}
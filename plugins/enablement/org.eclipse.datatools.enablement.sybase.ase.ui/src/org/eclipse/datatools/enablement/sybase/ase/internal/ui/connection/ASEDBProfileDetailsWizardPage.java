/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.sybase.ase.internal.ui.connection;

import org.eclipse.datatools.connectivity.ui.wizards.ExtensibleProfileDetailsWizardPage;
import org.eclipse.datatools.enablement.sybase.ase.internal.connection.IASEConnectionProfileConstants;
import org.eclipse.datatools.enablement.sybase.ase.ui.ASEUIPlugin;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.help.IContext;
import org.eclipse.swt.widgets.Composite;

public class ASEDBProfileDetailsWizardPage extends ExtensibleProfileDetailsWizardPage
{

    public ASEDBProfileDetailsWizardPage(String wizardPageName)
    {
        super(wizardPageName, IASEConnectionProfileConstants.ASE_CATEGORY_ID);
    }

    private ContextProviderDelegate contextProviderDelegate = new ContextProviderDelegate(
            ASEUIPlugin.getDefault().getBundle().getSymbolicName());

    public IContext getContext(Object target) {
        return contextProviderDelegate.getContext(target);
    }

    public int getContextChangeMask() {
        return contextProviderDelegate.getContextChangeMask();
    }

    public String getSearchExpression(Object target) {
        return contextProviderDelegate.getSearchExpression(target);
    }

    public void createControl(Composite parent) {
        super.createControl(parent);
        getShell().setData(HelpUtil.CONTEXT_PROVIDER_KEY, this);
        HelpUtil.setHelp(getControl(), HelpUtil.getContextId(
                IHelpContextASEProfile.ASE_PROFILE_WIZARD_PAGE,
                ASEUIPlugin.getDefault().getBundle().getSymbolicName()));
    }
}

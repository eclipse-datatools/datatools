/**
 * Created on Mar 22, 2008
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.enablement.sybase.asa.internal.ui.connection;

import org.eclipse.datatools.connectivity.ui.wizards.ExtensibleProfileDetailsWizardPage;
import org.eclipse.datatools.enablement.sybase.asa.internal.connection.IASAConnectionProfileConstants;
import org.eclipse.datatools.enablement.sybase.asa.ui.ASAUIPlugin;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.help.IContext;
import org.eclipse.swt.widgets.Composite;

public class ASADBProfileDetailsWizardPage extends ExtensibleProfileDetailsWizardPage
{

    public ASADBProfileDetailsWizardPage(String wizardPageName)
    {
        super(wizardPageName, IASAConnectionProfileConstants.ASA_CATEGORY_ID);
    }

    private ContextProviderDelegate contextProviderDelegate = new ContextProviderDelegate(
            ASAUIPlugin.getDefault().getBundle().getSymbolicName());

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
                IHelpContextASAProfile.ASA_PROFILE_WIZARD_PAGE,
                ASAUIPlugin.getDefault().getBundle().getSymbolicName()));
    }
}

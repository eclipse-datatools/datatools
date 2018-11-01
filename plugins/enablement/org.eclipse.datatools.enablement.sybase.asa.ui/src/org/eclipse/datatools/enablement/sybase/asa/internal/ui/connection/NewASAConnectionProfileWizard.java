/***********************************************************************************************************************
 * Copyright (c) 2004-2005 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: brianf - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.internal.ui.connection;

import java.util.Properties;

import org.eclipse.datatools.connectivity.ui.wizards.ExtensibleNewConnectionProfileWizard;
import org.eclipse.datatools.enablement.sybase.ISybaseJDBCConnectionProfileConstants;
import org.eclipse.datatools.enablement.sybase.asa.JDBCASAProfileMessages;
import org.eclipse.datatools.enablement.sybase.asa.ui.ASAUIPlugin;
import org.eclipse.datatools.enablement.sybase.asa.ui.IHelpConstants;
import org.eclipse.datatools.help.ContextProviderDelegate;
import org.eclipse.datatools.help.HelpUtil;
import org.eclipse.help.IContext;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;

/**
 * @see Wizard
 */
public class NewASAConnectionProfileWizard extends ExtensibleNewConnectionProfileWizard
{

    private ContextProviderDelegate contextProviderDelegate           = new ContextProviderDelegate(ASAUIPlugin
                                                                              .getDefault().getBundle()
                                                                              .getSymbolicName());

    public static String            ASA_CONNECTION_PROPERTY_PAGE_NAME = "asaWizPage";             //$NON-NLS-1$

    public static String            MULTI_DATABASE_PROPERTY_PAGE_NAME = "multiPage";              //$NON-NLS-1$

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

    protected MultiASAWizardPage mMultiPage;

    public NewASAConnectionProfileWizard()
    {
        super(new ASADBProfileDetailsWizardPage(ASA_CONNECTION_PROPERTY_PAGE_NAME));
        mMultiPage = new MultiASAWizardPage(MULTI_DATABASE_PROPERTY_PAGE_NAME);
        setWindowTitle(JDBCASAProfileMessages.getString("NewConnectionProfileWizard.ASA.title")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.datatools.connectivity.ui.wizards.NewASAConnectionProfileWizard#addCustomPages()
     */
    public void addCustomPages()
    {
        super.addCustomPages();

        addPage(mMultiPage);
    }

    public boolean performFinish()
    {
        String[] dbnames = mMultiPage.getDBNames();
        if (dbnames == null || dbnames.length == 0)
        {
            return super.performFinish();
        }
        Properties props = getProfileProperties();
        String dbname = props.getProperty(ISybaseJDBCConnectionProfileConstants.PROP_DB_NAME);
        if (dbname == null || dbname.length() == 0)
        {
            if (dbnames != null && dbnames.length > 0)
            {
                for (int i = 0; i < dbnames.length; i++)
                {
                    String iter_dbname = (String) dbnames[i];
                    props.setProperty(ISybaseJDBCConnectionProfileConstants.PROP_DB_NAME, iter_dbname);
                    if (!super.performFinish())
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void createPageControls(Composite pageContainer)
    {
        super.createPageControls(pageContainer);

        getShell().setData(HelpUtil.CONTEXT_PROVIDER_KEY, this);
        HelpUtil.setHelp(pageContainer.getShell(), IHelpConstants.ASA_PROFILE_WIZARD_PAGE);
    }

}
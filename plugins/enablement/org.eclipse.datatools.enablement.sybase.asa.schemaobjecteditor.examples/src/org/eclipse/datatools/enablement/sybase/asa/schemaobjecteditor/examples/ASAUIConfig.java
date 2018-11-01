/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples;

import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.services.ASAActionService;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.services.ActionService;
import org.eclipse.datatools.sqltools.editor.ui.core.SQLDevToolsUIConfiguration;

/**
 * 
 * 
 * @author juewu
 */
public class ASAUIConfig extends SQLDevToolsUIConfiguration
{
    private static ASAUIConfig   _instance              = null;
    public static final String[] ASA_ALIASES            = new String[]
                                                        {
        "Sybase_ASA", "Adaptive Server Anywhere", "SQL Anywhere"
                                                        };
    public static final String[] _ALSO_SUPPORT_VERSIONS = new String[]
                                                        {
                                                            "10.x"
                                                        };

    public ASAUIConfig()
    {
        _instance = this;
    }

    public static ASAUIConfig getInstance()
    {
        if (_instance == null)
        {
            return new ASAUIConfig();
        }
        return _instance;
    }

    public ActionService getActionService()
    {
        return new ASAActionService();
    }

    public DatabaseVendorDefinitionId[] alsoSupport()
    {
        DatabaseVendorDefinitionId targetids[] = new DatabaseVendorDefinitionId[getAlsoSuppportVersions().length];
        for (int i = 0; i < getAlsoSuppportVersions().length; i++)
        {
            targetids[i] = new DatabaseVendorDefinitionId(ASA_ALIASES[0], getAlsoSuppportVersions()[i]);
        }
        return targetids;
    }

    protected String[] getAlsoSuppportVersions()
    {
        return ASAConfig._ALSO_SUPPORT_VERSIONS;
    }
}

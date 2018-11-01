/***********************************************************************************************************************
 * Copyright (c) 2009 Sybase, Inc. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License 2.0 which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase, Inc. - initial API and implementation
 **********************************************************************************************************************/
package org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples;

import org.eclipse.datatools.enablement.sybase.asa.IJDBCASAConnectionProfileConstants;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.services.ASASQLDataService;
import org.eclipse.datatools.enablement.sybase.asa.schemaobjecteditor.examples.services.ASASQLService;
import org.eclipse.datatools.sqltools.core.DatabaseIdentifier;
import org.eclipse.datatools.sqltools.core.DatabaseVendorDefinitionId;
import org.eclipse.datatools.sqltools.core.IDatabaseSetting;
import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.services.SQLDataService;
import org.eclipse.datatools.sqltools.core.services.SQLService;


/**
 * 
 * @author hcao
 * 
 */
public class ASAConfig extends SQLDevToolsConfiguration
{
    private static ASAConfig _instance = null;
    public static final String[] ASA_ALIASES = new String[]
    {
        "Sybase_ASA","Adaptive Server Anywhere","SQL Anywhere"
    }
    ;
    public static final String[]   _ALSO_SUPPORT_VERSIONS = new String[]{"10.x"};
    
  // for eclipse to load this class, we must declare it as public
    public ASAConfig()
    {
        _instance = this;
    }

    public static ASAConfig getInstance()
    {
        if (_instance == null)
        {
            return new ASAConfig();
        }
        return _instance;
    }
    
    public IDatabaseSetting createDbSetting(DatabaseIdentifier databaseIdentifier)
    {
        return new ASADatabaseSetting(databaseIdentifier);
    }
    
    public boolean recognize(String product, String version)
    {
    	boolean productMatch = false;
    	
        for (int i = 0; i < ASA_ALIASES.length; i++) 
        {
            if (ASA_ALIASES[i].equals(product))
            {
            	productMatch = true;
            	break;
            }
        }
        if (productMatch)
        {
            DatabaseVendorDefinitionId targetid = new DatabaseVendorDefinitionId(product, version);
            DatabaseVendorDefinitionId supportid1 = new DatabaseVendorDefinitionId(product, getDatabaseVendorDefinitionId().getVersion());
            //even if we don't recognize ASA10 or ASA11, it'll still be regarded as a rough match in SQLToolsFacade.getCanonicalDatabaseVendorDefinitionId,
            //which will use the configuration class belonging to the same server.
            if(supportid1.equals(targetid))
            {
            	return true;
            }
            
            for(int i = 0; i<getAlsoSuppportVersions().length; i++)
            {
            	DatabaseVendorDefinitionId supportid2 = new DatabaseVendorDefinitionId(product, getAlsoSuppportVersions()[i]);
            	if(supportid2.equals(targetid))
            	{
            		return true;
            	}
            }
        }
        return false;
    }

    public String[] getAssociatedConnectionProfileType()
    {
        return new String[]
        {
            IJDBCASAConnectionProfileConstants.PROVIDER_ID
        }
        ;
    }
    
    public DatabaseVendorDefinitionId[] alsoSupport()
    {
    	DatabaseVendorDefinitionId targetids[] = new DatabaseVendorDefinitionId[getAlsoSuppportVersions().length];
    	for(int i = 0; i<getAlsoSuppportVersions().length; i++)
    	{
    		targetids[i] = new DatabaseVendorDefinitionId(ASA_ALIASES[0], getAlsoSuppportVersions()[i]);
    	}
        return targetids;
    }
    
    protected String[] getAlsoSuppportVersions()
    {
        return ASAConfig._ALSO_SUPPORT_VERSIONS;
    }
    
    public SQLService getSQLService()
    {
        return new ASASQLService();
    }
    
    public SQLDataService getSQLDataService()
    {
        return new ASASQLDataService();
    }
}

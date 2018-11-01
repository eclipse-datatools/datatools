/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    linsong - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.ase.catalog;

import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.enablement.ase.ddl.SybaseASEDdlParser;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.AccessRuleType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASERuleImpl;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASECatalogRule extends SybaseASERuleImpl implements ICatalogObject,IAdaptable
{
    private static final long serialVersionUID = 1595704824083450799L;

    public Database getCatalogDatabase()
    {
        return this.getSchema().getCatalog().getDatabase();
    }

    public Connection getConnection()
    {
        SybaseASECatalog catalog = (SybaseASECatalog) this.getSchema().getCatalog();
        return catalog.getConnection();
    }

    public void refresh()
    {
        if(isNeedRefresh())
        {
            synchronized (this.isStatementLoaded)
            {
                this.isStatementLoaded = Boolean.FALSE;
                super.setStatement(null);
            }
            synchronized (this.isAccessRuleLoaded)
            {
                this.isAccessRuleLoaded = Boolean.FALSE;
                super.setAccessRule(ACCESS_RULE_EDEFAULT);
            }
            synchronized (this.isAccessTypeLoaded)
            {
                this.isAccessTypeLoaded = Boolean.FALSE;
                super.setAccessType(null);
            }
            RefreshManager.getInstance().referesh(this);
        }
    }

    public String getStatement()
    {
    	synchronized (this.isStatementLoaded) {
    		if (!this.isStatementLoaded.booleanValue())
            {
                loadRuleStatement();
            }
		}
        return super.getStatement();
    }
    
    public boolean isAccessRule()
    {
        synchronized (this.isAccessRuleLoaded)
        {
            if (!this.isAccessRuleLoaded.booleanValue())
            {
                loadRuleAccess();
            }
        }
        return super.isAccessRule();
    }

    public AccessRuleType getAccessType()
    {
        synchronized (this.isAccessTypeLoaded)
        {
            if (!this.isAccessTypeLoaded.booleanValue())
            {
                loadRuleAccessType();
            }
        }
        return super.getAccessType();
    }

    public boolean eIsSet(EStructuralFeature eFeature)
    {
        int id = eDerivedStructuralFeatureID(eFeature);
        if (id == SybaseasesqlmodelPackage.SYBASE_ASE_RULE__STATEMENT)
        {
            this.getStatement();
        }
        else if (id == SybaseasesqlmodelPackage.SYBASE_ASE_RULE__ACCESS_RULE)
        {
            this.isAccessRule();
        }
        else if (id == SybaseasesqlmodelPackage.SYBASE_ASE_RULE__ACCESS_TYPE)
        {
            this.getAccessType();
        }
        return super.eIsSet(eFeature);
    }

    private void loadRuleStatement()
    {
        if (this.isStatementLoaded.booleanValue())
            return;

        Connection connection = this.getConnection();

        boolean deliver = this.eDeliver();
        this.eSetDeliver(false);

        String text = SybaseASECatalogUtils.getCompiledObjectText(this, connection, this.getSchema().getCatalog().getName());
        this.setStatement(new SybaseASEDdlParser().parseDefaultRuleStatement(text));

        this.eSetDeliver(deliver);
        
        this.isStatementLoaded = Boolean.TRUE;
    }
    
    private void loadRuleAccess()
    {
        if (this.isAccessRuleLoaded.booleanValue())
        {
            return;
        }

        boolean deliver = this.eDeliver();
        eSetDeliver(false);

        String stmt = SybaseASECatalogUtils.getCompiledObjectText(this, this.getConnection(), this.getSchema().getCatalog().getName());
        Pattern pattern = Pattern.compile(this.PARSE_ACCESS_RULE, Pattern.CASE_INSENSITIVE
                | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(stmt);
        if (matcher.matches())
        {
            if (matcher.group(1) != null)
            {
                setAccessRule(true);
            }
            else
            {
                setAccessRule(false);
            }
        }

        eSetDeliver(deliver);

        this.isAccessRuleLoaded = Boolean.TRUE;
    }
    
    private void loadRuleAccessType()
    {
        if (this.isAccessTypeLoaded.booleanValue())
        {
            return;
        }

        boolean deliver = this.eDeliver();
        eSetDeliver(false);

        String stmt = SybaseASECatalogUtils.getCompiledObjectText(this, this.getConnection(), this.getSchema()
                .getCatalog().getName());
        Pattern pattern = Pattern.compile(this.PARSE_ACCESS_RULE, Pattern.CASE_INSENSITIVE
                | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(stmt);
        if (matcher.matches())
        {
            if (matcher.group(1) != null)
            {
                pattern = Pattern.compile("((AND[\\s]+?)|(OR[\\s]+?))??ACCESS", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
                matcher = pattern.matcher(matcher.group(1));
                if (matcher.matches())
                {
                    if (matcher.group(1) == null)
                    {
                        setAccessType(AccessRuleType.DEF_LITERAL);
                    }
                    else if (matcher.group(1).trim().equalsIgnoreCase("AND"))
                    {
                        setAccessType(AccessRuleType.AND_LITERAL);
                    }
                    else if (matcher.group(1).trim().equalsIgnoreCase("OR"))
                    {
                        setAccessType(AccessRuleType.OR_LITERAL);
                    }
                }
            }
        }

        eSetDeliver(deliver);

        this.isAccessTypeLoaded = Boolean.TRUE;
    }

    public Object getAdapter(Class adapter) {
		Object adapterObject=Platform.getAdapterManager().getAdapter(this, adapter);
		if(adapterObject==null){
			adapterObject=Platform.getAdapterManager().loadAdapter(this, adapter.getName());
		}
		return adapterObject;
	}

	private Boolean isStatementLoaded  = Boolean.FALSE;
	private Boolean isAccessRuleLoaded = Boolean.FALSE;
    private Boolean isAccessTypeLoaded = Boolean.FALSE;
    
    private static final String PARSE_ACCESS_RULE = "CREATE[\\s]+?(((AND[\\s]+?)|(OR[\\s]+?))??ACCESS).*";
	
	private boolean isNeedRefresh()
	{
	    return this.isStatementLoaded.booleanValue() || this.isAccessRuleLoaded.booleanValue()
                || this.isAccessTypeLoaded.booleanValue();
	}

}

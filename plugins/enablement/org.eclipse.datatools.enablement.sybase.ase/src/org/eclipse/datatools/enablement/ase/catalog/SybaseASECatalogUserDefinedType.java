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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.enablement.ase.JDBCASEPlugin;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDefault;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASERule;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESchema;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEUserDefinedTypeImpl;
import org.eclipse.datatools.modelbase.sql.datatypes.PredefinedDataType;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;

public class SybaseASECatalogUserDefinedType extends SybaseASEUserDefinedTypeImpl implements ICatalogObject,IAdaptable
{
    public static final int BATCH_LOAD_THRESHHOLD = 10;
    
    private static final long serialVersionUID      = 9187374515403919324L;
    private Boolean           typeInfoLoaded = Boolean.FALSE;

    public void refresh()
    {
        if(isNeedRefresh())
        {
            synchronized (typeInfoLoaded)
            {
                if (typeInfoLoaded.booleanValue())
                {
                    typeInfoLoaded = Boolean.FALSE;
                    super.setBoundDefault(null);
                    super.setBoundRule(null);
                }
            }
            RefreshManager.getInstance().referesh(this);            
        }
    }

    public Connection getConnection()
    {
    	Database db = this.getCatalogDatabase();
		if (db instanceof ICatalogObject) {
			return ((ICatalogObject) db).getConnection();
		}
		return null;
    }

    public Database getCatalogDatabase()
    {
        return this.getSchema().getCatalog().getDatabase();
    }

    public SybaseASEDefault getBoundDefault()
    {
    	synchronized (typeInfoLoaded) {
    		if (!typeInfoLoaded.booleanValue())
            {
                loadTypeInfo();
            }			
		}
        return super.getBoundDefault();
    }
    
    public SybaseASERule getBoundRule()
    {
    	synchronized (typeInfoLoaded) {
    		if (!typeInfoLoaded.booleanValue())
            {
                loadTypeInfo();
            }			
		}
        return super.getBoundRule();
    }
    
    public boolean isAllowNulls()
    {
    	synchronized (typeInfoLoaded) {
    		if (!typeInfoLoaded.booleanValue())
            {
                loadTypeInfo();
            }			
		}
        return super.isAllowNulls();
    }
    
    public boolean isIdentity()
    {
    	synchronized (typeInfoLoaded) {
    		if (!typeInfoLoaded.booleanValue())
            {
                loadTypeInfo();
            }			
		}
        return super.isIdentity();
    }

    public PredefinedDataType getPredefinedRepresentation()
    {
        synchronized (typeInfoLoaded) {
            if (!typeInfoLoaded.booleanValue())
            {
                loadTypeInfo();
            }           
        }
        return super.getPredefinedRepresentation();
    }

    private void loadTypeInfo()
    {
        if (typeInfoLoaded.booleanValue())
            return;

        int notLoaded = 0;
        boolean batch = false;
        ArrayList allUDTs = new ArrayList();
        for (Iterator it = this.getSchema().getCatalog().getSchemas().iterator(); it.hasNext();)
        {
            Schema s = (Schema) it.next();
            allUDTs.addAll(s.getUserDefinedTypes());
        }
        for (Iterator it = allUDTs.iterator(); it.hasNext();)
        {
            SybaseASECatalogUserDefinedType udt = (SybaseASECatalogUserDefinedType) it.next();
            if (!udt.typeInfoLoaded.booleanValue())
            {
                notLoaded ++;
                if (notLoaded >= BATCH_LOAD_THRESHHOLD)
                {
                    batch = true;
                    break;
                }
            }
        }

        Connection conn = this.getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        try
        {
            oldCatalog = conn.getCatalog();
            Catalog catalog = this.getSchema().getCatalog();
            String catalogName = catalog.getName();

            conn.setCatalog(catalogName);
            String query = batch? ASESQLs.ALL_USER_DEFINED_DATATYPE_INFO_QUERY: ASESQLs.USER_DEFINED_DATATYPE_INFO_QUERY;
            stmt = conn.prepareStatement(query);
            stmt.setString(1, catalogName);
            stmt.setString(2, catalogName);
            if (!batch)
            {
                stmt.setString(3, this.getName());
            }
            rs = stmt.executeQuery();
            while (rs.next())
            {
                String udtName = rs.getString(2);
                String udtSysName = rs.getString(3);
                int udtLength = rs.getString(4) == null ? 0 : Integer.valueOf(rs.getString(4)).intValue();
                int udtPrecision = rs.getString(5) == null ? 0 : Integer.valueOf(rs.getString(5)).intValue();
                int udtScale = rs.getString(6) == null ? 0 : Integer.valueOf(rs.getString(6)).intValue();

                boolean isAllowNulls = rs.getBoolean(7);
                boolean isIdentity = rs.getBoolean(8);
                String defaultOwner = rs.getString(9);
                String defaultName = rs.getString(10);
                String ruleOwner = rs.getString(11);
                String ruleName = rs.getString(12);

                SybaseASECatalogUserDefinedType udt = (SybaseASECatalogUserDefinedType) ASEUtil.getSQLObject(allUDTs, udtName);
                if (udt == null || udt.typeInfoLoaded.booleanValue())
                {
                    continue;
                }
                
                boolean deliver = udt.eDeliver();
                udt.eSetDeliver(false);

                if (udtSysName != null)
                {
                    PredefinedDataType type = SybaseASECatalogUtils.getASEPredefinedType(udtLength, udtPrecision, udtScale, 0, udtSysName, RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(
                            udt.getSchema().getDatabase()));
                    udt.setPredefinedRepresentation(type);
                }
                
                // find binddefault
                if(defaultOwner != null && defaultName != null)
                {
                    SybaseASESchema defaultSchema = (SybaseASESchema) ASEUtil.getSQLObject(catalog.getSchemas(),
                            defaultOwner);
                    SybaseASEDefault bindDefault = (SybaseASEDefault) ASEUtil.getSQLObject(defaultSchema.getDefaults()
                            , defaultName);
                    /*
                     * TODO: When quoted_identifier is on, a Default object rename by sp_rename, the name in sysobjects table will change to a name with double quotation marks.
                             But defaultName has no quotation, so it can be found by getSQLObject() method.
                     */ 
                    if (bindDefault == null) {
                        bindDefault = (SybaseASEDefault) ASEUtil.getSQLObject(defaultSchema.getDefaults(), "\"" + defaultName + "\"");
                    }
                    udt.setBoundDefault(bindDefault);
                }
                // find bindrule
                if(ruleOwner != null &&ruleName != null)
                {
                    SybaseASESchema ruleSchema = (SybaseASESchema) ASEUtil.getSQLObject(catalog.getSchemas(),
                            ruleOwner);
                    SybaseASERule bindRule = (SybaseASERule) ASEUtil.getSQLObject(ruleSchema.getRules(),
                            ruleName);
                    /*
                     * TODO: When quoted_identifier is on, a Rule object rename by sp_rename, the name in sysobjects table will change to a name with double quotation marks.
                             But ruleName has no quotation, so it can be found by getSQLObject() method.
                     */ 
                    if (bindRule == null) {
                        bindRule = (SybaseASERule) ASEUtil.getSQLObject(ruleSchema.getRules(), "\"" + ruleName + "\"");
                    }
                    udt.setBoundRule(bindRule);
                }
                udt.setAllowNulls(isAllowNulls);
                udt.setIdentity(isIdentity);

                udt.eSetDeliver(deliver);
                udt.typeInfoLoaded = Boolean.TRUE;
            }
        }
        catch (SQLException e)
        {
            JDBCASEPlugin.getDefault().log(e);
        }
        finally
        {
        	SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
        }

    }

	public Object getAdapter(Class adapter) {
		Object adapterObject=Platform.getAdapterManager().getAdapter(this, adapter);
		if(adapterObject==null){
			adapterObject=Platform.getAdapterManager().loadAdapter(this, adapter.getName());
		}
		return adapterObject;
	}
    
	private boolean isNeedRefresh()
	{
	    return typeInfoLoaded.booleanValue();
	}
}

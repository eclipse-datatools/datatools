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

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.enablement.ase.JDBCASEPlugin;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SegmentThreshold;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASESegmentImpl;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASECatalogSegment extends SybaseASESegmentImpl implements ICatalogObject,IAdaptable
{

    private static final long serialVersionUID = 6484703933132732304L;

    public Database getCatalogDatabase()
    {
        return this.getCatalog().getDatabase();
    }

    public Connection getConnection()
    {
        SybaseASECatalog catalog = (SybaseASECatalog) this.getCatalog();
        return catalog.getConnection();
    }

    public void refresh()
    {
    	synchronized (isThresholdLoaded) {
    		this.isThresholdLoaded = Boolean.FALSE;
    		super.getThresholds().clear();
		}
        RefreshManager.getInstance().referesh(this);
    }

    public boolean isSystemObject()
    {
        return ASEUtil.SYSTEM_DEFINED_SEGMENTS.contains(this.getName());
    }
    
    public boolean eIsSet(EStructuralFeature eFeature)
    {
    	int id = eDerivedStructuralFeatureID(eFeature);
         if (id == SybaseasesqlmodelPackage.SYBASE_ASE_SEGMENT__THRESHOLDS)
         {
        	 this.getThresholds();
         }	
    	return super.eIsSet(eFeature);
    }

    public EList getThresholds()
    {
    	synchronized (isThresholdLoaded) {
        if (!isThresholdLoaded.booleanValue()) {
				this.loadThreshold();
			}
		}
        return super.getThresholds();
    }

    private void loadThreshold()
    {
        if (isThresholdLoaded.booleanValue())
            return;

        boolean deliver = this.eDeliver();
        this.eSetDeliver(false);

        EList thresholdList = this.getThresholds();
        thresholdList.clear();
        
        Connection conn = this.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        try
        {
        	oldCatalog = conn.getCatalog();
        	conn.setCatalog(getCatalog().getName());
            stmt = conn.prepareStatement(ASESQLs.SEGMENT_THRESHOLD_QUERY);
            stmt.setString(1, this.getName());

            rs = stmt.executeQuery();
            while (rs.next())
            {
                int freeSpace = rs.getInt(2);
                String procName = rs.getString(3);

                Database database = this.getCatalogDatabase();
                final DatabaseDefinition databaseDefinition = RDBCorePlugin.getDefault()
                        .getDatabaseDefinitionRegistry().getDefinition(database);
                final DataModelElementFactory factory = databaseDefinition.getDataModelElementFactory();

                SegmentThreshold threshold = (SegmentThreshold) factory.create(SybaseasesqlmodelPackage.eINSTANCE
                        .getSegmentThreshold());
                threshold.setFreeSpace(freeSpace);
                threshold.setProcedureName(procName);
                thresholdList.add(threshold);
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
        isThresholdLoaded = Boolean.TRUE;
        eSetDeliver(deliver);
    }

    public Object getAdapter(Class adapter) {
		Object adapterObject=Platform.getAdapterManager().getAdapter(this, adapter);
		if(adapterObject==null){
			adapterObject=Platform.getAdapterManager().loadAdapter(this, adapter.getName());
		}
		return adapterObject;
	}

	private Boolean isThresholdLoaded = Boolean.FALSE;
    
}

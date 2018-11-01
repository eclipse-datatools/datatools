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

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.enablement.ase.ISybaseASECatalogTable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheInfo;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockingSchemaType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASETableImpl;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEPartition;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASECatalogTable extends SybaseASETableImpl implements ICatalogObject, ISybaseASECatalogTable, ICatalogTable , IAdaptable{

	private static final long serialVersionUID = 3257854259579074868L;

	private SybaseASEBaseTableLoader tableLoader;
	
	boolean isTriggerNeedClear = false;
    
    public SybaseASECatalogTable()
    {
        super();
        tableLoader = new SybaseASEBaseTableLoader(this);
    }
    
	public Connection getConnection() {
		return tableLoader.getConnection();
	}
	
	public Database getCatalogDatabase() {
		return tableLoader.getCatalogDatabase();	
	}
	
	public boolean eIsSet(EStructuralFeature eFeature) 
	{
		switch (eDerivedStructuralFeatureID(eFeature)) {
		case SQLTablesPackage.PERSISTENT_TABLE__COLUMNS:
			getColumns();
			break;
		case SQLTablesPackage.PERSISTENT_TABLE__CONSTRAINTS:
			getConstraints();
			break;
		case SQLTablesPackage.PERSISTENT_TABLE__INDEX:
			getIndex();
			break;
		case SQLTablesPackage.TABLE__TRIGGERS:
			getTriggers();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__CONCURRENCY_OPT_THRESHOLD:
			getConcurrencyOptThreshold();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__EXP_ROW_SIZE:
			getExpRowSize();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__FILL_FACTOR:
			getFillFactor();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__IDENTITY_GAP:
			getIdentityGap();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__LOCK_PROMOTION:
			getLockPromotion();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__LOCK_SCHEMA:
			getLockSchema();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__MAX_ROW_PER_PAGE:
			getMaxRowPerPage();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__PARTITION_CONDITION:
			getPartitionCondition();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__PARTITIONS:
			getPartitions();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__RESERVE_PAGE_GAP:
			getReservePageGap();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__SEGMENT:
			getSegment();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TABLE_ONLY_CACHE_INFO:
			getTableOnlyCacheInfo();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TEXT_ONLY_CACHE_INFO:
			getTextOnlyCacheInfo();
			break;
		case SybaseasesqlmodelPackage.SYBASE_ASE_TABLE__TEXT_IMAGE_SEGMENT:
			getTextImageSegment();
			break;
        case SybasesqlmodelPackage.SYBASE_BASE_TABLE__PRIVILEGES:
            getPrivileges();
            break;
		}
		return super.eIsSet(eFeature);
	}

	public void refresh() 
	{
		tableLoader.refresh();
		isTriggerNeedClear = false;
	}

	public int getTableId()
	{
        return tableLoader.getTableId();
	}
	
	public int getStatus()
	{
		return tableLoader.getStatus();
	}
	

	public EList getColumns(){
		return tableLoader.getColumns();
	}
	
	public EList getConstraints() {
		return tableLoader.getConstraints();
	}
	
	public EList getIndex() {
		return tableLoader.getIndex();
	}
	
	public SybaseASESegment getTextImageSegment() {
		return tableLoader.getTextImageSegment();
	}
	
	public EList getTriggers() {
		return tableLoader.getTriggers();
	}
	
	public LockingSchemaType getLockSchema()
    {
		return tableLoader.getLockSchema();
    }

    public int getFillFactor()
    {
    	return tableLoader.getFillFactor();
    }

    public int getMaxRowPerPage()
    {
    	return tableLoader.getMaxRowPerPage();
    }

    public int getExpRowSize()
    {
        return tableLoader.getExpRowSize();
    }

    public int getReservePageGap()
    {
        return tableLoader.getReservePageGap();
    }

    public int getIdentityGap()
    {
        return tableLoader.getIdentityGap();
    }

    public SybaseASESegment getSegment()
    {
        return tableLoader.getSegment();
    }

    public CacheInfo getTableOnlyCacheInfo()
    {
        return tableLoader.getTableOnlyCacheInfo();
    }

    public CacheInfo getTextOnlyCacheInfo()
    {
        return tableLoader.getTextOnlyCacheInfo();
    }

    public int getConcurrencyOptThreshold()
    {
        return tableLoader.getConcurrencyOptThreshold();
    }

    public int getPartitions()
    {
        return tableLoader.getPartitions();
    }

    public SybaseASEPartition getPartitionCondition()
    {
        return tableLoader.getPartitionCondition();
    }

    public EList getLockPromotion()
    {
        return tableLoader.getLockPromotion();
    }
    
    public EList getPrivileges() {
        return tableLoader.getPrivileges();
    }

    public EList getColumnsSuper()
    {
        return super.getColumns();
    }

    public EList getConstraintsSuper()
    {
        return super.getConstraints();
    }

    public EList getIndexSuper()
    {
        return super.getIndex();
    }

    public SybaseASESegment getTextImageSegmentSuper()
    {
        return super.getTextImageSegment();
    }

    public EList getTriggersSuper()
    {
        return super.getTriggers();
    }

    public LockingSchemaType getLockSchemaSuper()
    {
        return super.getLockSchema();
    }

    public int getConcurrencyOptThresholdSuper()
    {
        return super.getConcurrencyOptThreshold();
    }

    public int getExpRowSizeSuper()
    {
        return super.getExpRowSize();
    }

    public int getFillFactorSuper()
    {
        return super.getFillFactor();
    }

    public int getIdentityGapSuper()
    {
        return super.getIdentityGap();
    }

    public int getMaxRowPerPageSuper()
    {
        return super.getMaxRowPerPage();
    }

    public int getPartitionsSuper()
    {
        return super.getPartitions();
    }

    public int getReservePageGapSuper()
    {
        return super.getReservePageGap();
    }

    public CacheInfo getTableOnlyCacheInfoSuper()
    {
        return super.getTableOnlyCacheInfo();
    }

    public CacheInfo getTextOnlyCacheInfoSuper()
    {
        return super.getTableOnlyCacheInfo();
    }

    public SybaseASESegment getSegmentSuper()
    {
        return super.getSegment();
    }

    public SybaseASEPartition getPartitionConditionSuper()
    {
        return super.getPartitionCondition();
    }

    public EList getLockPromotionSuper()
    {
        return super.getLockPromotion();
    }

    public EList getPrivilegesSuper()
    {
        return super.getPrivileges();
    }
    
    public SybaseASEBaseTableLoader getTableLoader()
    {
        return this.tableLoader;
    }

    public Object getAdapter(Class adapter) {
        Object adapterObject=Platform.getAdapterManager().getAdapter(this, adapter);
        if(adapterObject==null){
            adapterObject=Platform.getAdapterManager().loadAdapter(this, adapter.getName());
        }
        return adapterObject;
    }

}

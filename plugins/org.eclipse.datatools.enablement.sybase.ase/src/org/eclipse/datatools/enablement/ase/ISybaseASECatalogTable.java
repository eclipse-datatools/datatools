/**
 * Created on 2007-6-14
 * 
 * Copyright (c) Sybase, Inc. 2004-2006. All rights reserved.
 */
package org.eclipse.datatools.enablement.ase;

import org.eclipse.datatools.enablement.ase.catalog.SybaseASEBaseTableLoader;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheInfo;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockingSchemaType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEPartition;
import org.eclipse.emf.common.util.EList;

/**
 * This interface is defined for ase table catalog loader. In the framework, the catalog loader object inherits the BaseTableImpl, 
 * while all the get*() method is overrided and delegated to a <code>SybaseASEBaseTableLoader</code> object so that the code 
 * for loading table information can be reused. 
 * <p>
 * However, as a result, it's impossible for the <code>SybaseASEBaseTableLoader</code> object to call the super method of the
 * catalog loader object. To resolve this problem, all the ase table catalog loader should implements this interface and expose it's
 * super class's get*() methods.
 * 
 * @author Hao wang
 */
public interface ISybaseASECatalogTable
{
    public EList getColumnsSuper();
    public EList getConstraintsSuper();
    public EList getIndexSuper();
    public SybaseASESegment getTextImageSegmentSuper();
    public SybaseASESegment getSegmentSuper();
    public EList getTriggersSuper();
    public LockingSchemaType getLockSchemaSuper();
    public int getFillFactorSuper();
    public int getMaxRowPerPageSuper();
    public int getExpRowSizeSuper();
    public int getReservePageGapSuper();
    public int getIdentityGapSuper();
    public CacheInfo getTableOnlyCacheInfoSuper();
    public CacheInfo getTextOnlyCacheInfoSuper();
    public int getConcurrencyOptThresholdSuper();
    public int getPartitionsSuper();
    public SybaseASEPartition getPartitionConditionSuper();
    public EList getLockPromotionSuper();
    public EList getPrivilegesSuper();
    public SybaseASEBaseTableLoader getTableLoader();
}

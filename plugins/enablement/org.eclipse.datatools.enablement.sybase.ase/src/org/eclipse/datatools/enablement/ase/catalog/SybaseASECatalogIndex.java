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
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheInfo;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheStrategyType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECache;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEFuncBasedIndexMember;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEIndexImpl;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionSegmentPair;
import org.eclipse.datatools.modelbase.sql.constraints.IndexMember;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.tables.Table;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASECatalogIndex extends SybaseASEIndexImpl implements ICatalogObject, IAdaptable
{
    private static final long serialVersionUID            = 3906363835747938352L;

    static public final int   IDX_STATUS_IGNORE_DUPKEYS   = 0x0001;
    static public final int   IDX_STATUS_ABORT_DUPROWS    = 0x0004;
    static public final int   IDX_STATUS_CLUSTERED        = 0x0010;
    static public final int   IDX_STATUS_UNIQUE           = 0x0002;
    static public final int   IDX_STATUS_ALLOW_DUPROWS    = 0x0040;
    static public final int   IDX_STATUS_SORTED           = 0x0200;
    static public final int   IDX_STATUS_SORTEDCLUSTERED  = 0x0080;
    static public final int   IDX_STATUS_SUSPECT          = 0x8000;
    static public final int   IDX_STATUS2_SUSPECT         = 0x2000;
    static public final int   IDX_STATUS2_DATA_CLUSTERED  = 0x0200;
    static public final int   IDX_STATUS2_PREFETCH_UNABLE = 0x10;
    static public final int   IDX_STATUS2_MRU_UNABLE      = 0x20;

    public static final int   COL_STATUS3_FUNC_EXPRESSION = 0x0001;

    public static final int   PT_LOCAL_STATUS             = 0x0008;
    public static final int   PT_NAME_DEFINED             = 0x0004;

    public void refresh()
    {
    	synchronized (isIndexInfoLoaded) {
    		isIndexInfoLoaded = Boolean.FALSE;
		}

        RefreshManager.getInstance().referesh(this);
    }

    public boolean isSystemObject()
    {
        return this.isSystemGenerated();
    }

    public Connection getConnection()
    {
        return ((ICatalogObject)this.getTable()).getConnection();
    }

    public Database getCatalogDatabase()
    {
        return this.getTable().getSchema().getCatalog().getDatabase();
    }

    public EList getMembers()
    {
    	synchronized (isIndexInfoLoaded) {
    		if (!isIndexInfoLoaded.booleanValue())
            {
                this.loadIndexInfo();
            }
		}
        return super.getMembers();
    }

    public boolean isLocalIndex()
    {
    	synchronized (isIndexInfoLoaded) {
    		if (!isIndexInfoLoaded.booleanValue())
            {
                this.loadIndexInfo();
            }
		}
        return super.isLocalIndex();
    }

    public CacheInfo getCacheInfo()
    {
    	synchronized (isIndexInfoLoaded) {
    		if (!isIndexInfoLoaded.booleanValue())
            {
                this.loadIndexInfo();
            }
		}
        return super.getCacheInfo();
    }

    public EList getPartitions()
    {
    	synchronized (isIndexInfoLoaded) {
    		if (!isIndexInfoLoaded.booleanValue())
            {
                this.loadIndexInfo();
            }
		}
        return super.getPartitions();
    }
    
    public boolean isAllowDuplicateRow() {
    	synchronized (isIndexInfoLoaded) {
    		if (!isIndexInfoLoaded.booleanValue())
            {
                this.loadIndexInfo();
            }
		}
    	return super.isAllowDuplicateRow();
    }
    
    public boolean isClustered() {
    	synchronized (isIndexInfoLoaded) {
    		if (!isIndexInfoLoaded.booleanValue())
            {
                this.loadIndexInfo();
            }
		}
    	return super.isClustered();
    }
    
    public int getFillFactor() {
    	synchronized (isIndexInfoLoaded) {
    		if (!isIndexInfoLoaded.booleanValue())
            {
                this.loadIndexInfo();
            }
		}
    	return super.getFillFactor();
    }
    
    public boolean isIgnoreDuplicateKey() {
    	synchronized (isIndexInfoLoaded) {
    		if (!isIndexInfoLoaded.booleanValue())
            {
                this.loadIndexInfo();
            }
		}
    	return super.isIgnoreDuplicateKey();
    }
    
    public boolean isIgnoreDuplicateRow() {
    	synchronized (isIndexInfoLoaded) {
    		if (!isIndexInfoLoaded.booleanValue())
            {
                this.loadIndexInfo();
            }
		}
    	return super.isIgnoreDuplicateRow();
    }
    
    public int getMaxRowPerPage() {
    	synchronized (isIndexInfoLoaded) {
    		if (!isIndexInfoLoaded.booleanValue())
            {
                this.loadIndexInfo();
            }
		}
    	return super.getMaxRowPerPage();
    }
    
    public SybaseASESegment getSegment() {
    	synchronized (isIndexInfoLoaded) {
    		if (!isIndexInfoLoaded.booleanValue())
            {
                this.loadIndexInfo();
            }
		}
    	return super.getSegment();
    }
    
    public boolean isSortedData() {
    	synchronized (isIndexInfoLoaded) {
    		if (!isIndexInfoLoaded.booleanValue())
            {
                this.loadIndexInfo();
            }
		}
    	return super.isSortedData();
    }
    
    public boolean isSuspect() {
    	synchronized (isIndexInfoLoaded) {
    		if (!isIndexInfoLoaded.booleanValue())
            {
                this.loadIndexInfo();
            }
		}
    	return super.isSuspect();
    }
    
//    public boolean isSystemGenerated() {
//    	synchronized (isIndexInfoLoaded) {
//    		if (!isIndexInfoLoaded.booleanValue())
//            {
//                this.loadIndexInfo();
//            }
//		}
//    	return super.isSystemGenerated();
//    }
    
    public boolean isUnique() {
    	synchronized (isIndexInfoLoaded) {
    		if (!isIndexInfoLoaded.booleanValue())
            {
                this.loadIndexInfo();
            }
		}
    	return super.isUnique();
    }
    
    public boolean eIsSet(EStructuralFeature eFeature)
    {
        switch(eDerivedStructuralFeatureID(eFeature))
        {
        case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__MEMBERS:
            this.getMembers();
            break;
        case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__CACHE_INFO:
        	this.getCacheInfo();
        	break;
        case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__LOCAL_INDEX:
            this.isLocalIndex();
            break;
        case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__PARTITIONS:
            this.getPartitions();
            break;
        case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__ALLOW_DUPLICATE_ROW:
        	this.isAllowDuplicateRow();
        	break;
        case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__CLUSTERED:
        	this.isClustered();
        	break;
        case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__FILL_FACTOR:
        	this.getFillFactor();
        	break;
        case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__IGNORE_DUPLICATE_KEY:
        	this.isIgnoreDuplicateKey();
        	break;
        case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__IGNORE_DUPLICATE_ROW:
        	this.isIgnoreDuplicateRow();
        	break;
        case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__MAX_ROW_PER_PAGE:
        	this.getMaxRowPerPage();
        	break;
        case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__SEGMENT:
        	this.getSegment();
        	break;
        case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__SORTED_DATA:
        	this.isSortedData();
        	break;
        case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__SUSPECT:
        	this.isSuspect();
        	break;
        case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__SYSTEM_GENERATED:
        	this.isSystemGenerated();
        	break;
        case SybaseasesqlmodelPackage.SYBASE_ASE_INDEX__UNIQUE:
        	this.isUnique();
        	break;
        }
        return super.eIsSet(eFeature);
    }
    
    private void loadIndexInfo()
    {
    	boolean deliver = this.eDeliver();
		this.eSetDeliver(false);
    	Connection conn = this.getConnection();
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	String oldCatalog = null;
    	try
    	{
    		oldCatalog = conn.getCatalog();
    		conn.setCatalog(this.getTable().getSchema().getCatalog().getName());
    		stmt = conn.prepareStatement(ASESQLs.INDEX_INFO_QUERY);
    		stmt.setInt(1, ((ICatalogTable)this.getTable()).getTableId());
    		stmt.setString(2, this.getName());
    		rs = stmt.executeQuery();
    		while(rs.next())
    		{
    			int indid = rs.getInt(1);
    			int status = rs.getInt(2);
    			int keycnt = rs.getInt(3);
    			int maxrowsperpage = rs.getInt(4);
    			int fill_factor = rs.getInt(5);
    			int res_page_gap = rs.getInt(6);
    			String segmentName = rs.getString(7);
    			int status2 = rs.getInt(8); 
    			
    		    super.setMaxRowPerPage(maxrowsperpage);
    		    super.setFillFactor(fill_factor);
    		    super.setReversePageGap(res_page_gap);
    		
//    		    boolean isSystemGenedIndex = (status2 & 2) == 2;
    		
    		    boolean ignoreDuplicateKeys = (status & SybaseASECatalogIndex.IDX_STATUS_IGNORE_DUPKEYS) != 0;
    		    boolean isAbortDuplicateRows = (status & SybaseASECatalogIndex.IDX_STATUS_ABORT_DUPROWS) != 0;
    		    boolean isUnique = (status & SybaseASECatalogIndex.IDX_STATUS_UNIQUE) != 0;
    		    boolean isClustered = (status & SybaseASECatalogIndex.IDX_STATUS_CLUSTERED) != 0
    		            || (status2 & SybaseASECatalogIndex.IDX_STATUS2_DATA_CLUSTERED) != 0;
    		    boolean isAllowDuplicateRows = (status & SybaseASECatalogIndex.IDX_STATUS_ALLOW_DUPROWS) != 0
    		            || (!isUnique && isClustered && indid > 1);
    		    boolean isSortedData = (status & SybaseASECatalogIndex.IDX_STATUS_SORTED) != 0;
    		    boolean isSuspect = (status & SybaseASECatalogIndex.IDX_STATUS_SUSPECT) != 0
    		            || (status2 & SybaseASECatalogIndex.IDX_STATUS2_SUSPECT) != 0;
    		
    		    super.setIgnoreDuplicateKey(ignoreDuplicateKeys);
    		    super.setAllowDuplicateRow(isAllowDuplicateRows);
    		    super.setIgnoreDuplicateRow(isAbortDuplicateRows);
    		    super.setUnique(isUnique);
    		    super.setClustered(isClustered);
    		    super.setSortedData(isSortedData);
    		    super.setSuspect(isSuspect);
//    		    super.setSystemGenerated(isSystemGenedIndex);
    		
    		    SybaseASESegment segment = getSegment(segmentName);
    		    super.setSegment(segment);
    			
    			loadIndexColumns(indid, keycnt, conn);
    			loadIndexCache(status2, indid, conn);
    			if(((SybaseASEDatabase)this.getCatalogDatabase()).isPartitionsApplicable())
    				loadPartitionInfo();
    		}
    	}
    	catch(SQLException e)
    	{
    		JDBCASEPlugin.getDefault().log(e);
    	}
    	finally
    	{
    		SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
        }
    	
        isIndexInfoLoaded = Boolean.TRUE;
         this.eSetDeliver(deliver);
         
    }

    private void loadIndexColumns(int indid, int keyCount, Connection conn)
    {
        DatabaseDefinition dbDef = this.getDatabaseDefinition();

        boolean deliver = this.eDeliver();
        this.eSetDeliver(false);
        
        EList indexMemberList = super.getMembers();
        indexMemberList.clear();

        /*
         * if the index is clustered on an all pages locked table key count is equal to the the number of indexed
         * columns otherwise key count is equal to number of indexed columns plus 1
         */
        if (indid != 1)
        {
            keyCount--;
        }

        Table parent = this.getTable();
	    for (int i = 0; i < keyCount; i++)
	    {
	    	ResultSet rs = null;
	    	PreparedStatement stmt = null;
	    	String oldCatalog = null;
	    	try{
	    		oldCatalog = conn.getCatalog();
	    		conn.setCatalog(this.getTable().getSchema().getCatalog().getName());
	    		String fullTableName = ASEUtil.getFullQuatifiedName(parent);
	            stmt = conn.prepareStatement(ASESQLs.INDEX_COL_QUERY);
	            stmt.setString(1, fullTableName);
	            stmt.setInt(2, indid);
	            stmt.setInt(3, i + 1);
	            stmt.setString(4, fullTableName);
	            stmt.setInt(5, indid);
	            stmt.setInt(6, i + 1);
	            rs = stmt.executeQuery();
	            while (rs.next())
	            {
	                String colName = rs.getString(1);
	                String order = rs.getString(2);
	                if(colName!=null)
	                {
		                IndexMember im = SybaseASECatalogUtils.getIndexMember(dbDef, this, colName, order, conn);
		                if (im.getColumn() == null
	                            && (im instanceof SybaseASEFuncBasedIndexMember && ((SybaseASEFuncBasedIndexMember) im)
	                                    .getColumnExpression() == null))
		                {
		                	throw new UnsupportedOperationException("Fail to retrieve index column info");
		                }
		                indexMemberList.add(im);
	                }
	            }
	    	}
	    	catch(SQLException e)
	    	{
	    		JDBCASEPlugin.getDefault().log(e);
	    	}
	    	finally {
				SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
	    	}
	    }
        this.eSetDeliver(deliver);
    }

    private void loadIndexCache(int status2, int idxid, Connection connection)
    {

        boolean deliver = this.eDeliver();
        this.eSetDeliver(false);

        String cacheName = SybaseASECatalogUtils.getIndexCache(this, idxid, connection);
        if (cacheName != null)
        {
            DataModelElementFactory factory = getDatabaseDefinition().getDataModelElementFactory();
            CacheInfo cacheInfo = (CacheInfo) factory.create(SybaseasesqlmodelPackage.eINSTANCE.getCacheInfo());
            SybaseASECache cache = (SybaseASECache)ASEUtil.getSQLObject(((SybaseASEDatabase)this.getCatalogDatabase()).getCaches(), cacheName);
            cacheInfo.setCache(cache);
            int cacheStrategy = this.getIndexCacheStrategy(status2);
            cacheInfo.setCacheStrategy(cacheStrategy);
            this.setCacheInfo(cacheInfo);
        }

        this.eSetDeliver(deliver);
    }

    private void loadPartitionInfo()
    {
        EList partitionList = super.getPartitions();
        partitionList.clear();

        Connection conn = this.getConnection();

        boolean deliver = this.eDeliver();
        this.eSetDeliver(false);

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        try
        {
        	oldCatalog = conn.getCatalog();
        	conn.setCatalog(this.getTable().getSchema().getCatalog().getName());
            stmt = conn.prepareStatement(ASESQLs.PARTITION_QUERY);
            stmt.setString(1, ASEUtil.getFullQuatifiedName(this.getTable()));
            stmt.setString(2, this.getName());
            rs = stmt.executeQuery();

            boolean isLocalIndex = false;
            boolean isPartitionNameSet = false;
            while (rs.next())
            {
                int status3 = rs.getInt(3);
                isLocalIndex = (status3 & PT_LOCAL_STATUS) != 0;
                if (isLocalIndex)
                {
                    int status = rs.getInt(4);
                    isPartitionNameSet = (status & PT_NAME_DEFINED) != 0;
                    if (isPartitionNameSet)
                    {
                        String partitionName = rs.getString(1);
                        SybaseASESegment segment = getSegment(rs.getString(2));

                        DataModelElementFactory factory = getDatabaseDefinition().getDataModelElementFactory();
                        PartitionSegmentPair partitionInfo = (PartitionSegmentPair) factory
                                .create(PartitionPackage.eINSTANCE.getPartitionSegmentPair());
                        partitionInfo.setPartitionName(partitionName);
                        partitionInfo.setSegment(segment);
                        partitionList.add(partitionInfo);
                    }
                }
            }

            this.setLocalIndex(isLocalIndex);
        }
        catch (SQLException e)
        {
            JDBCASEPlugin.getDefault().log(e);
        }
        finally
        {
        	SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
        }

        this.eSetDeliver(deliver);
    }

    private SybaseASESegment getSegment(String segmentName)
    {
        SybaseASECatalog catalog = (SybaseASECatalog) ((SybaseASECatalogSchema) this.getTable().getSchema())
                .getCatalog();
        EList segmentList = catalog.getSegments();
        for (int i = 0; i < segmentList.size(); i++)
        {
            SybaseASESegment seg = (SybaseASESegment) segmentList.get(i);
            if (seg.getName().equals(segmentName))
            {
                return seg;
            }
        }
        return null;
    }

    private DatabaseDefinition getDatabaseDefinition()
    {
        Database d = this.getTable().getSchema().getCatalog().getDatabase();
        DatabaseDefinition dbDef = RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(d);
        return dbDef;
    }

    private int getIndexCacheStrategy(int status2)
    {
        boolean isPrefetchUnable = (status2 & SybaseASECatalogIndex.IDX_STATUS2_PREFETCH_UNABLE) != 0;
        boolean isMRUUnable = (status2 & SybaseASECatalogIndex.IDX_STATUS2_MRU_UNABLE) != 0;

        int cacheStrategy = 0;
        if (!isPrefetchUnable)
        {
            cacheStrategy = cacheStrategy | CacheStrategyType.PREFETCH_LITERAL.getValue();
        }
        if (!isMRUUnable)
        {
            cacheStrategy = cacheStrategy | CacheStrategyType.MRU_LITERAL.getValue();
        }
        return cacheStrategy;
    }

	public Object getAdapter(Class adapter) {
		Object adapterObject=Platform.getAdapterManager().getAdapter(this, adapter);
		if(adapterObject==null){
			adapterObject=Platform.getAdapterManager().loadAdapter(this, adapter.getName());
		}
		return adapterObject;
	}
	
    private Boolean isIndexInfoLoaded = Boolean.FALSE;

}

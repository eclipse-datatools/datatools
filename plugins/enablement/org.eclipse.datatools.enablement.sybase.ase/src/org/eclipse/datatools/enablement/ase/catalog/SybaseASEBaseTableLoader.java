/*******************************************************************************
 * Copyright (c) 2008 Sybase, Inc.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which
 * accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: Sybase - initial API and implementation
 ******************************************************************************/
package org.eclipse.datatools.enablement.ase.catalog;

import java.lang.ref.SoftReference;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.datatools.connectivity.sqm.core.definition.DataModelElementFactory;
import org.eclipse.datatools.connectivity.sqm.core.definition.DatabaseDefinition;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.internal.core.RDBCorePlugin;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableColumnLoader;
import org.eclipse.datatools.enablement.ase.ISybaseASECatalogTable;
import org.eclipse.datatools.enablement.ase.JDBCASEPlugin;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheInfo;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheStrategyType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockPromotionInfo;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockingSchemaType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEBaseTable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASECache;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEColumn;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEDatabase;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEPrimaryKey;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASEUniqueConstraint;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseasesqlmodelFactoryImpl;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.ListRangePartitionItem;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionPackage;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.PartitionSegmentPair;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEHashPartition;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEListPartition;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEPartition;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASERangePartition;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASERoundrobinPartition;
import org.eclipse.datatools.modelbase.sql.constraints.CheckConstraint;
import org.eclipse.datatools.modelbase.sql.constraints.PrimaryKey;
import org.eclipse.datatools.modelbase.sql.schema.Catalog;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.emf.common.util.EList;

/**
 * The loader is responsible for loading table's information such as columns, constraints and so on.
 *  
 * @author Hao wang
 */
public class SybaseASEBaseTableLoader
{
    /**
     * The _masterObj is the table whose information should be loaded.
     */
    protected SybaseASEBaseTable _masterObj;
    
    private Boolean columnsLoaded = Boolean.FALSE;
    private SoftReference columnLoaderRef;
    private Boolean indexesLoaded = Boolean.FALSE;
    private Boolean constraintsLoaded = Boolean.FALSE;
    private Boolean textImageSegmentLoaded = Boolean.FALSE;
    private Boolean triggerLoaded = Boolean.FALSE;
    protected Boolean tableInfoLoaded = Boolean.FALSE;
    private Boolean lockPromotionLoaded = Boolean.FALSE;
    private Boolean partitionInfoLoaded = Boolean.FALSE;
    private Boolean cacheInfoLoaded = Boolean.FALSE;
    private Boolean proxyTableInfoLoaded = Boolean.FALSE;
    private Boolean tableTypeIdStatusLoaded = Boolean.FALSE;
    private Boolean privilegesLoaded = Boolean.FALSE;
    
    private int tableId;
    private int status                 = -1;
    
    private List                tableCheckConstraintList              = new ArrayList();
    private List                uniqueConstraintList                  = new ArrayList();
    private List                foreignKeyList                        = new ArrayList();
    private PrimaryKey          pk                                    = null;

    public static final int     DEFAULT_CONCURRENCY_THRESHOLD         = 15;
    
    // Partition Strategy Types
    public final static String  HASH                                  = "hash";
    public final static String  LIST                                  = "list";
    public final static String  RANGE                                 = "range";
    public final static String  ROUND_ROBIN                           = "roundrobin";
    
    public SybaseASEBaseTableLoader(SybaseASEBaseTable masterObj)
    {
        _masterObj = masterObj;
    }
    
    public Connection getConnection() {
        Database db = this.getCatalogDatabase();
        if (db instanceof ICatalogObject) {
            return ((ICatalogObject) db).getConnection();
        }
        return null;
    }
    
    public Database getCatalogDatabase() {
        return _masterObj.getSchema().getCatalog().getDatabase();     
    }
    
    public void refresh() {
        if(isNeedRefresh())
        {
            synchronized (columnsLoaded) {
                if (columnsLoaded.booleanValue()) {
                    columnsLoaded = Boolean.FALSE;
                }
            }
            synchronized (constraintsLoaded) {
                if (constraintsLoaded.booleanValue()) {
                    constraintsLoaded = Boolean.FALSE;
                }
            }
            synchronized (indexesLoaded) {
                if (indexesLoaded.booleanValue()) {
                    indexesLoaded = Boolean.FALSE;
                }
            }
            synchronized (triggerLoaded) {
                if (triggerLoaded.booleanValue()) {
                    triggerLoaded = Boolean.FALSE;
                }
            }
            synchronized (textImageSegmentLoaded) {
                if(textImageSegmentLoaded.booleanValue())
                    textImageSegmentLoaded = Boolean.FALSE;
            }
            synchronized (tableInfoLoaded) {
                if(tableInfoLoaded.booleanValue())
                    tableInfoLoaded = Boolean.FALSE;
            }
            synchronized (lockPromotionLoaded) {
                if(lockPromotionLoaded.booleanValue())
                    lockPromotionLoaded = Boolean.FALSE;
            }
            synchronized (partitionInfoLoaded) {
                if(partitionInfoLoaded.booleanValue())
                    partitionInfoLoaded = Boolean.FALSE;
            }
            synchronized (cacheInfoLoaded) {
                if(cacheInfoLoaded.booleanValue())
                    cacheInfoLoaded = Boolean.FALSE;
            }
            synchronized (proxyTableInfoLoaded) {
                if(proxyTableInfoLoaded.booleanValue())
                    proxyTableInfoLoaded = Boolean.FALSE;
            }
            synchronized (tableTypeIdStatusLoaded) {
                if(tableTypeIdStatusLoaded.booleanValue())
                    tableTypeIdStatusLoaded = Boolean.FALSE;
            }
            synchronized (privilegesLoaded) {
                if (privilegesLoaded.booleanValue()) {
                    privilegesLoaded = Boolean.FALSE;
                }
            }
            if(_masterObj instanceof ICatalogObject)
            {
                RefreshManager.getInstance().referesh((ICatalogObject)_masterObj);            
            }
        }
    }
    
    public int getTableId()
    {
        synchronized (tableTypeIdStatusLoaded) {
            if(!tableTypeIdStatusLoaded.booleanValue())
                loadTableIdStat();
        }
        return tableId;
    }
    
    public int getStatus()
    {
        synchronized (tableTypeIdStatusLoaded) {
            if(!tableTypeIdStatusLoaded.booleanValue())
                loadTableIdStat();
        }
        return status;
    }
    
    synchronized private void loadTableIdStat() {
        boolean deliver = _masterObj.eDeliver();
        _masterObj.eSetDeliver(false);
        
        Connection conn = this.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        try{
            oldCatalog = conn.getCatalog();
            conn.setCatalog(_masterObj.getSchema().getCatalog().getName());
            stmt = conn.prepareStatement(ASESQLs.QUERY_TABLE_TYPE_INFO);
            stmt.setString(1, _masterObj.getSchema().getName());
            stmt.setString(2, _masterObj.getName());
            rs = stmt.executeQuery();
            while(rs.next())
            {
                int id = rs.getInt(1);
                int status2 = rs.getInt(3);
                this.tableId = id;
                this.status = status2;
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
        
        _masterObj.eSetDeliver(deliver);
        this.tableTypeIdStatusLoaded = Boolean.TRUE;
    }
    
    public EList getColumns()
    {
        synchronized (columnsLoaded) {
            if (!columnsLoaded.booleanValue())
                loadColumns();
        }
        return ((ISybaseASECatalogTable)_masterObj).getColumnsSuper();
    }
    
    public EList getConstraints() {
        synchronized (constraintsLoaded) {
            if(!constraintsLoaded.booleanValue())
                loadConstraints();
        }
        return ((ISybaseASECatalogTable)_masterObj).getConstraintsSuper();
    }
    
    public EList getIndex() {
        synchronized (indexesLoaded) {
            if(!indexesLoaded.booleanValue())
                loadIndices();
        }
        return ((ISybaseASECatalogTable)_masterObj).getIndexSuper();
    }
    
    public SybaseASESegment getTextImageSegment() {
        synchronized (textImageSegmentLoaded) {
            if(!textImageSegmentLoaded.booleanValue())
                loadTextImageSegment();
        }
        return ((ISybaseASECatalogTable)_masterObj).getTextImageSegmentSuper();
    }
    
    public EList getTriggers() {
        synchronized (triggerLoaded) {
            if (!triggerLoaded.booleanValue()) {
                loadTriggers();
            }
        }
        return ((ISybaseASECatalogTable)_masterObj).getTriggersSuper();
    }
    
    public LockingSchemaType getLockSchema()
    {
        synchronized (tableInfoLoaded) {
            if (!this.tableInfoLoaded.booleanValue())
            {
                loadTableInfo();
            }
        }
        return ((ISybaseASECatalogTable)_masterObj).getLockSchemaSuper();
    }

    public int getFillFactor()
    {
        synchronized (tableInfoLoaded) {
            if (!this.tableInfoLoaded.booleanValue())
            {
                loadTableInfo();
            }
        }
        return ((ISybaseASECatalogTable)_masterObj).getFillFactorSuper();
    }

    public int getMaxRowPerPage()
    {
        synchronized (tableInfoLoaded) {
            if (!this.tableInfoLoaded.booleanValue())
            {
                loadTableInfo();
            }
        }
        return ((ISybaseASECatalogTable)_masterObj).getMaxRowPerPageSuper();
    }

    public int getExpRowSize()
    {
        synchronized (tableInfoLoaded) {
            if (!this.tableInfoLoaded.booleanValue())
            {
                loadTableInfo();
            }
        }
        return ((ISybaseASECatalogTable)_masterObj).getExpRowSizeSuper();
    }

    public int getReservePageGap()
    {
        synchronized (tableInfoLoaded) {
            if (!this.tableInfoLoaded.booleanValue())
            {
                loadTableInfo();
            }
        }
        return ((ISybaseASECatalogTable)_masterObj).getReservePageGapSuper();
    }

    public int getIdentityGap()
    {
        synchronized (tableInfoLoaded) {
            if (!this.tableInfoLoaded.booleanValue())
            {
                loadTableInfo();
            }
        }
        return ((ISybaseASECatalogTable)_masterObj).getIdentityGapSuper();
    }

    public SybaseASESegment getSegment()
    {
        synchronized (tableInfoLoaded) {
            if (!this.tableInfoLoaded.booleanValue())
            {
                loadTableInfo();
            }
        }
        return ((ISybaseASECatalogTable)_masterObj).getSegmentSuper();
    }

    public CacheInfo getTableOnlyCacheInfo()
    {
        synchronized (cacheInfoLoaded) {
            if (!this.cacheInfoLoaded.booleanValue())
            {
                loadCacheInfo();
            }
        }
        return ((ISybaseASECatalogTable)_masterObj).getTableOnlyCacheInfoSuper();
    }

    public CacheInfo getTextOnlyCacheInfo()
    {
        synchronized (cacheInfoLoaded) {
            if (!this.cacheInfoLoaded.booleanValue())
            {
                loadCacheInfo();
            }
        }
        return ((ISybaseASECatalogTable)_masterObj).getTextOnlyCacheInfoSuper();
    }

    public int getConcurrencyOptThreshold()
    {
        synchronized (tableInfoLoaded) {
            if (!this.tableInfoLoaded.booleanValue())
            {
                loadTableInfo();
            }
        }
        return ((ISybaseASECatalogTable)_masterObj).getConcurrencyOptThresholdSuper();
    }

    public int getPartitions()
    {
        if (getCatalogDatabase().getVersion().compareTo(ASEUtil.VERSION_15) >= 0)
        {
            // only for ASE version < 15
            return -1;
        }
        synchronized (tableInfoLoaded) {
            if (!this.tableInfoLoaded.booleanValue())
            {
                loadTableInfo();
            }
        }
        return ((ISybaseASECatalogTable)_masterObj).getPartitionsSuper();
    }
    
    public SybaseASEPartition getPartitionCondition()
    {
        if (getCatalogDatabase().getVersion().compareTo(ASEUtil.VERSION_15) < 0)
        {
            // only for ASE version >= 15
            return null;
        }
        synchronized (tableInfoLoaded) {
            if (!this.tableInfoLoaded.booleanValue())
            {
                loadPartitionInfo();
            }
        }
        return ((ISybaseASECatalogTable)_masterObj).getPartitionConditionSuper();
    }
    
    public EList getLockPromotion()
    {
        synchronized (lockPromotionLoaded) {
            if (!this.lockPromotionLoaded.booleanValue())
                loadLockPromotion();            
        }
        return ((ISybaseASECatalogTable)_masterObj).getLockPromotionSuper();
    }
    
    public EList getPrivileges() {
        synchronized (privilegesLoaded) {
            if (!privilegesLoaded.booleanValue())
            {
                boolean deliver = _masterObj.eDeliver();
                _masterObj.eSetDeliver(false);
                
                loadPrivileges();

                _masterObj.eSetDeliver(deliver);
                privilegesLoaded = Boolean.TRUE;
            }
        }
        return ((ISybaseASECatalogTable)_masterObj).getPrivilegesSuper();
    }
    
    protected void loadPrivileges() {
        ((ISybaseASECatalogTable)_masterObj).getPrivilegesSuper().clear();
        SybaseASECatalog catalog = (SybaseASECatalog)_masterObj.getSchema().getCatalog();
        List privileges = SybaseASECatalogUtils.getPrivileges(_masterObj, catalog);
        ((ISybaseASECatalogTable)_masterObj).getPrivilegesSuper().addAll(privileges);
    }
    
    private void loadLockPromotion()
    {
        if (this.lockPromotionLoaded.booleanValue())
            return;
        EList lpList = ((ISybaseASECatalogTable)_masterObj).getLockPromotionSuper();
        lpList.clear();

        DataModelElementFactory factory = getDatabaseDefinition().getDataModelElementFactory();
        Connection conn = this.getConnection();

        boolean deliver = _masterObj.eDeliver();
        _masterObj.eSetDeliver(false);

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        try
        {
            oldCatalog = conn.getCatalog();
            conn.setCatalog(_masterObj.getSchema().getCatalog().getName());
            stmt = conn.prepareStatement(ASESQLs.LOCK_PROMOTE);
            stmt.setInt(1, this.getTableId());
            rs = stmt.executeQuery();
            while (rs.next())
            {
                int iLWM = rs.getInt(1); // Low water mark
                int iHWM = rs.getInt(2); // High water mark
                int iPCT = rs.getInt(3); // pct
                int iAtt = rs.getInt(4); // 0 for PAGE, 1 for ROW

                LockPromotionInfo lpInfo = (LockPromotionInfo) factory.create(SybaseasesqlmodelPackage.eINSTANCE
                        .getLockPromotionInfo());
                lpInfo.setRowLockPromotion(iAtt == 1);
                lpInfo.setLWM(iLWM);
                lpInfo.setHWM(iHWM);
                lpInfo.setPCT(iPCT);

                lpList.add(lpInfo);
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

        lockPromotionLoaded = Boolean.TRUE;
        _masterObj.eSetDeliver(deliver);
    }
    
    private void loadPartitionInfo()
    {
        if (partitionInfoLoaded.booleanValue())
            return;

        Connection conn = this.getConnection();

        boolean deliver = _masterObj.eDeliver();
        _masterObj.eSetDeliver(false);

        DataModelElementFactory factory = getDatabaseDefinition().getDataModelElementFactory();
        SybaseASEPartition partition = null;

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String partitionStrategy = "";
        String[] partitionCols = new String[0];
        List partitionSegmentPairs = null;
        List partitionValueList = null;
        int noPartition = 0;
        String oldCatalog = null;
        try
        {
            oldCatalog = conn.getCatalog();
            conn.setCatalog(_masterObj.getSchema().getCatalog().getName());
            stmt = conn.prepareStatement(ASESQLs.TABLE_PARTITION_INFO);
            stmt.setInt(1, this.getTableId());
            rs = stmt.executeQuery();
            while (rs.next())
            {
                partitionStrategy = rs.getString(2);
                noPartition = rs.getInt(1);
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

        // load partition columns
        if (!partitionStrategy.equals(ROUND_ROBIN))
        {
            partitionCols = loadPartitionColumn(conn);
        }

        // load partition segment pair for Hash/Round robin
        if (partitionStrategy.equals(HASH) || partitionStrategy.equals(ROUND_ROBIN))
        {
            partitionSegmentPairs = getPartitionSegmentPairs(partitionStrategy, conn);
        }
        // load List/Range partion items
        else
        {
            partitionValueList = getPartitionValueList(conn);
        }

        if (partitionStrategy.equals(ROUND_ROBIN))
        {
            partition = (SybaseASEPartition) factory.create(PartitionPackage.eINSTANCE
                    .getSybaseASERoundrobinPartition());
            ((SybaseASERoundrobinPartition) partition).getPartitionSegmentPairs().addAll(partitionSegmentPairs);
        }
        else if (partitionStrategy.equals(HASH))
        {
            partition = (SybaseASEPartition) factory.create(PartitionPackage.eINSTANCE.getSybaseASEHashPartition());
            for(int i = 0; i<partitionCols.length; i++)
            {
                Column col = (Column)ASEUtil.getSQLObject(this.getColumns(), partitionCols[i]);
                ((SybaseASEHashPartition) partition).getColumns().add(col);
            }
            ((SybaseASEHashPartition) partition).getPartitionSegmentPairs().addAll(partitionSegmentPairs);
        }
        else if (partitionStrategy.equals(LIST))
        {
            partition = (SybaseASEPartition) factory.create(PartitionPackage.eINSTANCE.getSybaseASEListPartition());
            ((SybaseASEListPartition) partition)
                    .setColumn((SybaseASEColumn) ASEUtil.getSQLObject(this
                            .getColumns(), partitionCols[0]));
            ((SybaseASEListPartition) partition).getListPartitionItems().addAll(partitionValueList);
        }
        else
        {
            partition = (SybaseASEPartition) factory.create(PartitionPackage.eINSTANCE.getSybaseASERangePartition());
            for(int i = 0; i<partitionCols.length; i++)
            {
                Column col = (Column)ASEUtil.getSQLObject(this.getColumns(), partitionCols[i]);
                ((SybaseASERangePartition) partition).getColumns().add(col);
            }
            ((SybaseASERangePartition) partition).getRangePartitionItems().addAll(partitionValueList);
        }

        _masterObj.setPartitionCondition(partition);

        this.partitionInfoLoaded = Boolean.TRUE;
        _masterObj.eSetDeliver(deliver);
    }
    
    
    private String[] loadPartitionColumn(Connection conn)
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List colnameList = new ArrayList();
        String oldCatalog = null;
        try
        {
            oldCatalog = conn.getCatalog();
            conn.setCatalog(_masterObj.getSchema().getCatalog().getName());
            stmt = conn.prepareStatement(ASESQLs.TABLE_PARTITION_COLS);
            stmt.setInt(1, this.getTableId());
            stmt.setInt(2, this.getTableId());
            rs = stmt.executeQuery();
            while (rs.next())
            {
                colnameList.add(rs.getString(1));
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
        return (String[]) colnameList.toArray(new String[colnameList.size()]);
    }
    
    private List getPartitionSegmentPairs(String partitionStrategy, Connection conn)
    {
        DataModelElementFactory factory = getDatabaseDefinition().getDataModelElementFactory();

        List results = new ArrayList();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        try
        {
            oldCatalog = conn.getCatalog();
            conn.setCatalog(_masterObj.getSchema().getCatalog().getName());
            if (partitionStrategy.equals(HASH))
            {
                stmt = conn.prepareStatement(ASESQLs.SELECT_HASH_PARTITIONS);
            }
            else
            {
                stmt = conn.prepareStatement(ASESQLs.SELECT_ROUND_PARTITIONS);
            }
            stmt.setInt(1, this.getTableId());
            rs = stmt.executeQuery();
            while (rs.next())
            {
                // int status= rs.getInt(5);
                // right now ASE does not store information whether
                // partitiona name was user supplied or system
                // generated, we will always generate partition name
                String partitionName = rs.getString(1);
                String segmentName = rs.getString(2);
                PartitionSegmentPair psp = (PartitionSegmentPair) factory.create(PartitionPackage.eINSTANCE
                        .getPartitionSegmentPair());
                psp.setPartitionName(partitionName);
                psp.setSegment(getSegment(segmentName));
                results.add(psp);
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
        return results;
    }
    
    private List getPartitionValueList(Connection conn)
    {
        DataModelElementFactory factory = getDatabaseDefinition().getDataModelElementFactory();

        List results = new ArrayList();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        try
        {
            oldCatalog = conn.getCatalog();
            conn.setCatalog(_masterObj.getSchema().getCatalog().getName());
            stmt = conn.prepareStatement(ASESQLs.SELECT_RANGE_LIST_PARTITIONS);
            stmt.setInt(1, this.getTableId());
            rs = stmt.executeQuery();
            while (rs.next())
            {
                // int status= rs.getInt(5);
                // right now ASE does not store information whether
                // partitiona name was user supplied or system
                // generated, we will always generate partition name
                String partitionName = rs.getString(1);
                String segmentName = rs.getString(2);
                String condition = rs.getString(3);
                ListRangePartitionItem lrpi = (ListRangePartitionItem) factory.create(PartitionPackage.eINSTANCE
                        .getListRangePartitionItem());
                lrpi.setPartitionName(partitionName);
                lrpi.setSegment(getSegment(segmentName));
                lrpi.getValues().addAll(getConditionValues(condition));
                results.add(lrpi);
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
        return results;
    }
    
    private Collection getConditionValues(String condition)
    {
        List values = new ArrayList();
        String strValues = condition.substring(condition.indexOf('(') + 1, condition.lastIndexOf(')'));
        StringTokenizer st = new StringTokenizer(strValues, ",");
        while (st.hasMoreTokens())
        {
            String strVal = ((String) st.nextElement()).trim();
            values.add(strVal);
        }
        return values;
    }
    
    private void loadColumns() {
        try {
            boolean deliver = _masterObj.eDeliver();
            _masterObj.eSetDeliver(false);            
            EList columns = ((ISybaseASECatalogTable)_masterObj).getColumnsSuper();
            List existingCols = new ArrayList(columns);
            getColumnLoader().clearColumns(columns);
            ((ASETableColumnLoader)getColumnLoader()).loadColumns(columns, existingCols);
            
            _masterObj.eSetDeliver(deliver);
            columnsLoaded = Boolean.TRUE;
        }
        catch(Exception e) {
            JDBCASEPlugin.getDefault().log(e);
        }
    }
    
    protected final JDBCTableColumnLoader getColumnLoader() {
        if (columnLoaderRef == null || columnLoaderRef.get() == null) {
            columnLoaderRef = new SoftReference(createColumnLoader());
        }
        return (JDBCTableColumnLoader) columnLoaderRef.get();
    }
    
    protected JDBCTableColumnLoader createColumnLoader() {
        return new ASETableColumnLoader((ICatalogObject)_masterObj);
    }
    
    // load check constraints(table/column)/unique constraints(pk)/Foreign key
    private void loadConstraints()
    {
        if (this.constraintsLoaded.booleanValue())
            return;
        
        EList constraintList = ((ISybaseASECatalogTable)_masterObj).getConstraintsSuper();
        Connection connection = this.getConnection();

        boolean deliver = _masterObj.eDeliver();
        _masterObj.eSetDeliver(false);
        constraintList.clear();
        
        loadForeignKey(constraintList, connection);
        loadUniqueConstraintAndPriamryKey(constraintList, connection);
        loadTableCheckConstraint(constraintList, connection);
        loadColumnCheckConstraint(constraintList);

        this.constraintsLoaded = Boolean.TRUE;
        _masterObj.eSetDeliver(deliver);
    }
    
    private void loadColumnCheckConstraint(EList constraintList)
    {
        EList colList = _masterObj.getColumns();
        for (int i = 0; i < colList.size(); i++)
        {
            SybaseASECatalogColumn col = (SybaseASECatalogColumn) colList.get(i);
            CheckConstraint inlineRule = col.getColumnCheck();
            if (inlineRule != null)
                constraintList.add(inlineRule);
        }
    }
    
    private void loadForeignKey(EList constraintList, Connection conn)
    {
        List tempList = new ArrayList();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        try
        {
            oldCatalog = conn.getCatalog();
            conn.setCatalog(_masterObj.getSchema().getCatalog().getName());
            stmt = conn.prepareStatement(ASESQLs.QUERY_FOREIGN_KEY_NAME);
            stmt.setInt(1, this.getTableId());
            rs = stmt.executeQuery();

            while (rs.next())
            {
                String constrName = rs.getString(1);
                SybaseASECatalogForeignKey fk = (SybaseASECatalogForeignKey)ASEUtil.getSQLObject(this.foreignKeyList, constrName);
                if(fk != null)
                {
                    constraintList.add(fk);
                    fk.refresh();
                }
                else
                {
                    fk = new SybaseASECatalogForeignKey();
                    fk.setName(constrName);
                    constraintList.add(fk);
                }

                tempList.add(fk);
            }
            this.foreignKeyList = tempList;
        }
        catch (Exception e)
        {
            JDBCASEPlugin.getDefault().log(e);
        }
        finally
        {
            SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
        }
    }
    
    private void loadUniqueConstraintAndPriamryKey(EList constraintList, Connection conn)
    {
        List tempList = new ArrayList();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        try
        {
            oldCatalog = conn.getCatalog();
            conn.setCatalog(_masterObj.getSchema().getCatalog().getName());
            stmt = conn.prepareStatement(ASESQLs.UNIQUE_PK_QUERY);
            stmt.setInt(1, this.getTableId());
            rs = stmt.executeQuery();
            while (rs.next())
            {
                String name = rs.getString(2);
                int status = rs.getInt(4);
                int status2 = rs.getInt(5);

                boolean systemGenedName = false;
                if ((status2 & 8) == 8)
                {
                    systemGenedName = true;
                }

                SybaseASEUniqueConstraint uniqConstr = null;
                SybaseASEPrimaryKey primarykey = null;
                
                // Find if it is a Primary Key or a Unique Constriant
                if ((status & 2048) == 2048)
                {
                    if(this.pk != null && this.pk.getName().equals(name))
                    {
                        primarykey = (SybaseASEPrimaryKey)pk;
                        constraintList.add(primarykey);
                        ((ICatalogObject)primarykey).refresh();
                        uniqConstr = primarykey.getAseUniqueConstraint();
                    }
                    else 
                    {
                        primarykey = new SybaseASECatalogPrimaryKey();
                        primarykey.setName(name);
                        uniqConstr = SybaseasesqlmodelFactoryImpl.init().createSybaseASEUniqueConstraint();
                        primarykey.setAseUniqueConstraint(uniqConstr);
                        uniqConstr.setName(name);
                    }
                }
                else
                {
                    uniqConstr = (SybaseASEUniqueConstraint)ASEUtil.getSQLObject(this.uniqueConstraintList, name);
                    if(uniqConstr != null)
                    {
                        constraintList.add(uniqConstr);
                        ((ICatalogObject)uniqConstr).refresh();
                    }
                    else
                    {
                        uniqConstr = new SybaseASECatalogUniqueConstraint();
                        uniqConstr.setName(name);
                    }
                }
                uniqConstr.setSystemGenedName(systemGenedName);
                
                if(primarykey != null)
                {
                    this.pk = primarykey;
                    constraintList.add(primarykey);         
                }
                else
                {
                    tempList.add(uniqConstr);
                    constraintList.add(uniqConstr);
                }
            }
            this.uniqueConstraintList = tempList;
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
    
    private void loadTableCheckConstraint(EList constraintList, Connection conn)
    {
        List tempList = new ArrayList();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        try
        {
            oldCatalog = conn.getCatalog();
            conn.setCatalog(_masterObj.getSchema().getCatalog().getName());
            stmt = conn.prepareStatement(ASESQLs.QUERY_CHECK_CONSTRAINTS);
            stmt.setInt(1, this.getTableId());
            stmt.setInt(2, this.getTableId());
            rs = stmt.executeQuery();
            while (rs.next())
            {
                String checkName = rs.getString(1);
                SybaseASECatalogCheckConstraint check = (SybaseASECatalogCheckConstraint) ASEUtil
                        .getSQLObject(this.tableCheckConstraintList, checkName); 
                if(check != null)
                {
                    constraintList.add(check);
                    check.refresh();
                }
                else
                {
                    check = new SybaseASECatalogCheckConstraint();
                    check.setName(checkName);
                    constraintList.add(check);
                }
                tempList.add(check);
            }
            this.tableCheckConstraintList = tempList;
        }
        catch (Exception e)
        {
            JDBCASEPlugin.getDefault().log(e);
        }
        finally
        {
            SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
        }
    }
    
    private void loadIndices()
    {
        if (this.indexesLoaded.booleanValue())
            return;

        boolean deliver = _masterObj.eDeliver();
        _masterObj.eSetDeliver(false);
        
        EList indexList = ((ISybaseASECatalogTable)_masterObj).getIndexSuper();
        List existingIndices = new ArrayList(indexList);
        indexList.clear();

        String catalogName = ((SybaseASECatalogSchema) _masterObj.getSchema()).getCatalog().getName();
        
        Connection conn = this.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        try
        {
            oldCatalog = conn.getCatalog();
            conn.setCatalog(catalogName);
            stmt = conn.prepareStatement(ASESQLs.INDEX_NAME_QUERY);
            stmt.setInt(1, this.getTableId());

            rs = stmt.executeQuery();
            while (rs.next())
            {
                String indexName = rs.getString(1);
                boolean isSysGen = (rs.getInt(2) & 2) == 2;
                Object element = ASEUtil.getSQLObject(existingIndices, indexName);
                SybaseASECatalogIndex index = null;

                if (element != null)
                {
                    index = (SybaseASECatalogIndex) element;
                    indexList.add(index);
                    ((SybaseASECatalogSchema)_masterObj.getSchema()).getSuperIndices().add(index);
                    ((ICatalogObject) element).refresh();
                }
                else
                {
                    index = new SybaseASECatalogIndex();
                    index.setName(indexName);
                    indexList.add(index);
                    ((SybaseASECatalogSchema)_masterObj.getSchema()).getSuperIndices().add(index);
                }
                index.setSystemGenerated(isSysGen);
            }
        }
        catch (Exception e)
        {
            JDBCASEPlugin.getDefault().log(e);
        }
        finally
        {
            SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
        }
        
        this.indexesLoaded = Boolean.TRUE;
        _masterObj.eSetDeliver(deliver);
    }
    
    private void loadTextImageSegment() {
        if(textImageSegmentLoaded.booleanValue())
            return;
        boolean deliver = _masterObj.eDeliver();
        _masterObj.eSetDeliver(false);
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection conn = this.getConnection();
        String oldCatalog = null;
        try
        {
            oldCatalog = conn.getCatalog();
            conn.setCatalog(_masterObj.getSchema().getCatalog().getName());
            stmt = conn.prepareStatement(ASESQLs.PLACE_OBJECT_OF_TABLE_QUERY);
            stmt.setInt(1, this.getTableId());
            rs = stmt.executeQuery();
            while (rs.next())
            {
                String segmentName = rs.getString(2);
                if (segmentName != null && !segmentName.equals(""))
                {
                    SybaseASESegment s = getSegment(segmentName);
                    _masterObj.setTextImageSegment(s);
                }
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
        _masterObj.eSetDeliver(deliver);
        textImageSegmentLoaded = Boolean.TRUE;
    }
    
    private void loadTriggers() {
        if (this.triggerLoaded.booleanValue())
            return;
        
        boolean deliver = _masterObj.eDeliver();
        _masterObj.eSetDeliver(false);
        
        EList triggerList = ((ISybaseASECatalogTable)_masterObj).getTriggersSuper();
        List existingTriggers = new ArrayList(triggerList);
        triggerList.clear();
        
        Connection conn = this.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        try
        {
            oldCatalog = conn.getCatalog();
            conn.setCatalog(_masterObj.getSchema().getCatalog().getName());
            stmt = conn.prepareStatement(ASESQLs.TRIGGER_QUERY);
            stmt.setInt(1, getTableId()); // table id
            rs = stmt.executeQuery();
            while (rs.next())
            {
                SybaseASECatalogTrigger trigger;
                String trigName = rs.getString(1);
                String creatorName = rs.getString(2);
                boolean enableTrigger = !(rs.getInt(3) == 1);
                if (trigName == null)
                {
                    continue;
                }
                trigger = (SybaseASECatalogTrigger)ASEUtil.getSQLObject(existingTriggers, trigName);
                Schema creator = (Schema)ASEUtil.getSQLObject(_masterObj.getSchema().getCatalog().getSchemas(), creatorName);
                if(trigger != null)
                {
                    triggerList.add(trigger);
                    ((SybaseASECatalogSchema)creator).getSuperTriggers().add(trigger);
                    trigger.refresh();
                }
                else
                {
                    trigger = new SybaseASECatalogTrigger();
                    trigger.setName(trigName);
                    ((SybaseASECatalogSchema)creator).getSuperTriggers().add(trigger);
                    triggerList.add(trigger);
                }
                trigger.setEnabled(enableTrigger);
            }
        }
        catch (Exception e)
        {
            JDBCASEPlugin.getDefault().log(e);
        }
        finally
        {
            SybaseASECatalogUtils.cleanupJDBCResouce(rs, stmt, oldCatalog, conn);
        }
        this.triggerLoaded = Boolean.TRUE;
        _masterObj.eSetDeliver(deliver);
    }
    
    protected void loadTableInfo()
    {
        if (tableInfoLoaded.booleanValue())
            return;

        Connection conn = this.getConnection();

        boolean deliver = _masterObj.eDeliver();
        _masterObj.eSetDeliver(false);

        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        try
        {
            oldCatalog = conn.getCatalog();
            conn.setCatalog(_masterObj.getSchema().getCatalog().getName());
            stmt = this.getConnection().prepareStatement(ASESQLs.TABLE_INFO_QUERY);
            stmt.setString(1, _masterObj.getSchema().getName());
            stmt.setString(2, _masterObj.getName());

            rs = stmt.executeQuery();
            while (rs.next())
            {
                int status = rs.getInt(1);
                String segmentName = rs.getString(2);
                int maxRowPerPage = rs.getInt(3);
                int expRowSize = rs.getInt(4);
                int resPageGap = rs.getInt(5);
                int identityGap = rs.getInt(6);
                int fillFactor = rs.getInt(7);
                int partitionNumb = rs.getInt(8);

                // Space management properties and locking scheme
                //
                // ================================================
                // Parameter allpages datapages datarows
                // ================= ======== ========= ========
                // max_rows_per_page    Y           N       N
                // reservepagegap       Y           Y       Y
                // fillfactor           Y           Y       Y
                // exp_row_size         N           Y       Y
                // ================================================

                // Specify the locking mechanism and the segment of this table
                // Find the locking mechanism
                boolean isAPL = false; // All pages locked scheme
                LockingSchemaType lockingType = null;
                if ((status & 32768) == 32768) // Indicates datarows locking scheme
                {
                    lockingType = LockingSchemaType.LOCKDATAROWS_LITERAL;
                    maxRowPerPage = 0; // Though non-zero value is stored, it is never used, see table above
                }
                else if ((status & 16384) == 16384) // Indicates datapages locking scheme
                {
                    lockingType = LockingSchemaType.LOCKDATAPAGES_LITERAL;
                    maxRowPerPage = 0; // Though non-zero value is stored, it is never used, see table above
                }
                else
                // Indicates allpages locking scheme Even if the status is not 8192, since this info may be missing
                {
                    lockingType = LockingSchemaType.LOCKALLPAGES_LITERAL;
                    isAPL = true;
                    expRowSize = 0; // Though non-zero value is stored, it is never used, see table above
                }

                _masterObj.setMaxRowPerPage(maxRowPerPage);
                _masterObj.setExpRowSize(expRowSize);
                _masterObj.setReservePageGap(resPageGap);
                _masterObj.setIdentityGap(identityGap);
                _masterObj.setFillFactor(fillFactor);
                _masterObj.setSegment(getSegment(segmentName));
                _masterObj.setLockSchema(lockingType);
                _masterObj.setPartitions(partitionNumb);

                if (!isAPL)
                {
                    int concurrencyThreshold = getConcurrencyThreshold(conn);
                    _masterObj.setConcurrencyOptThreshold(concurrencyThreshold);
                }
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

        this.tableInfoLoaded = Boolean.TRUE;
        _masterObj.eSetDeliver(deliver);
    }
    
    private int getConcurrencyThreshold(Connection conn)
    {

        int concThreshold = DEFAULT_CONCURRENCY_THRESHOLD;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String oldCatalog = null;
        try
        {
            oldCatalog = conn.getCatalog();
            conn.setCatalog(_masterObj.getSchema().getCatalog().getName());
            stmt = conn.prepareStatement(ASESQLs.CONCURRENCY_THRESHOLD_QUERY);
            stmt.setInt(1, this.getTableId());
            rs = stmt.executeQuery();

            if (rs.next())
            {
                concThreshold = rs.getInt(1);
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
        return concThreshold;
    }
    
    private void loadCacheInfo()
    {
        if (cacheInfoLoaded.booleanValue())
            return;

        boolean deliver = _masterObj.eDeliver();
        _masterObj.eSetDeliver(false);

        _masterObj.setTableOnlyCacheInfo(null);
        _masterObj.setTextOnlyCacheInfo(null);

        CacheInfo tableOnlyCache = null;
        CacheInfo textOnlyCache = null;

        DataModelElementFactory factory = getDatabaseDefinition().getDataModelElementFactory();

        Connection conn = this.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        // load table only/text only cache name
        String oldCatalog = null;
        try
        {
            oldCatalog = conn.getCatalog();
            conn.setCatalog(_masterObj.getSchema().getCatalog().getName());
            stmt = conn.prepareStatement(ASESQLs.TABLE_CACHE_INFO_QUERY);
            stmt.setInt(1, this.getTableId());
            rs = stmt.executeQuery();
            while (rs.next())
            {
                String cacheName = rs.getString(1);
                String cacheType = rs.getString(2);
                boolean isTextOnly = cacheType.equals("T");

                CacheInfo cacheInfo = (CacheInfo) factory.create(SybaseasesqlmodelPackage.eINSTANCE.getCacheInfo());
                SybaseASECache cache = (SybaseASECache)ASEUtil.getSQLObject(((SybaseASEDatabase)this.getCatalogDatabase()).getCaches(), cacheName);
                cacheInfo.setCache(cache);
                if (isTextOnly)
                {
                    textOnlyCache = cacheInfo;
                }
                else
                {
                    tableOnlyCache = cacheInfo;
                }
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

        // load table only/text only cache strategy
        try
        {
            conn.setCatalog(_masterObj.getSchema().getCatalog().getName());
            stmt = conn.prepareStatement(ASESQLs.TABLE_CACHE_STRATEGY_QUERY);
            stmt.setInt(1, this.getTableId());
            rs = stmt.executeQuery();
            while (rs.next())
            {
                int idxId = rs.getInt(2);
                int status2 = rs.getInt(3);

                if (idxId == 0)
                {
                    if (tableOnlyCache == null)
                    {
                        tableOnlyCache = (CacheInfo) factory.create(SybaseasesqlmodelPackage.eINSTANCE.getCacheInfo());
                    }
                    tableOnlyCache.setCacheStrategy(getTableCacheStrategy(status2));
                }
                else
                {
                    if (textOnlyCache == null)
                    {
                        textOnlyCache = (CacheInfo) factory.create(SybaseasesqlmodelPackage.eINSTANCE.getCacheInfo());
                    }
                    textOnlyCache.setCacheStrategy(getTableCacheStrategy(status2));
                }
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

        _masterObj.setTextOnlyCacheInfo(textOnlyCache);
        _masterObj.setTableOnlyCacheInfo(tableOnlyCache);

        this.cacheInfoLoaded = Boolean.TRUE;
        _masterObj.eSetDeliver(deliver);
    }
    
    private int getTableCacheStrategy(int status2)
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
    
    private DatabaseDefinition getDatabaseDefinition()
    {
        Database d = _masterObj.getSchema().getCatalog().getDatabase();
        return RDBCorePlugin.getDefault().getDatabaseDefinitionRegistry().getDefinition(d);
    }
    
    private SybaseASESegment getSegment(String segmentName)
    {
        Catalog catalog = _masterObj.getSchema().getCatalog();
        EList segmentList = ((SybaseASECatalog)catalog).getSegments();
        for (int i = 0; i < segmentList.size(); i++)
        {
            SybaseASESegment segment = (SybaseASESegment) segmentList.get(i);
            if (segment.getName().equals(segmentName))
            {
                return segment;
            }
        }
        return null;
    }
    
    public boolean isNeedRefresh()
    {
        if(columnsLoaded.booleanValue()||
               indexesLoaded.booleanValue()||
               constraintsLoaded.booleanValue()||
               textImageSegmentLoaded.booleanValue()||
               triggerLoaded.booleanValue()||
               tableInfoLoaded.booleanValue()||
               lockPromotionLoaded.booleanValue()||
               partitionInfoLoaded.booleanValue()||
               cacheInfoLoaded.booleanValue()||
               proxyTableInfoLoaded.booleanValue()||
               tableTypeIdStatusLoaded.booleanValue()||
               privilegesLoaded.booleanValue())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

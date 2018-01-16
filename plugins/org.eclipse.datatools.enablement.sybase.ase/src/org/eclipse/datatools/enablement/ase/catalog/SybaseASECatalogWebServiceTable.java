package org.eclipse.datatools.enablement.ase.catalog;

import java.sql.Connection;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.enablement.ase.ISybaseASECatalogWebServiceTable;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.CacheInfo;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.LockingSchemaType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.ProxyTableExternalType;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseASESegment;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.SybaseasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.impl.SybaseASEWebServiceTableImpl;
import org.eclipse.datatools.enablement.sybase.ase.models.sybaseasesqlmodel.partition.SybaseASEPartition;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.tables.SQLTablesPackage;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASECatalogWebServiceTable extends SybaseASEWebServiceTableImpl implements ICatalogObject,ICatalogTable,IAdaptable,ISybaseASECatalogWebServiceTable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2869623489895796579L;
    private SybaseASEBaseTableLoader tableLoader;

    public SybaseASECatalogWebServiceTable()
    {
        super();
        tableLoader = new SybaseASEWebServiceTableLoader(this);
    }

    public Connection getConnection()
    {
        return tableLoader.getConnection();
    }

    public Database getCatalogDatabase()
    {
        return tableLoader.getCatalogDatabase();
    }

    public boolean eIsSet(EStructuralFeature eFeature)
    {
        switch (eDerivedStructuralFeatureID(eFeature))
        {
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
    }

    public int getTableId()
    {
        return tableLoader.getTableId();
    }

    public int getStatus()
    {
        return tableLoader.getStatus();
    }

    public EList getColumns()
    {
        return tableLoader.getColumns();
    }

    public EList getConstraints()
    {
        return tableLoader.getConstraints();
    }

    public EList getIndex()
    {
        return tableLoader.getIndex();
    }

    public SybaseASESegment getTextImageSegment()
    {
        return tableLoader.getTextImageSegment();
    }

    public EList getTriggers()
    {
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

    public EList getPrivileges()
    {
        return tableLoader.getPrivileges();
    }

    public ProxyTableExternalType getExternalType()
    {
        return ((SybaseASEProxyTableLoader) tableLoader).getExternalType();
    }

    public boolean isExisting()
    {
        return ((SybaseASEProxyTableLoader) tableLoader).isExisting();
    }

    public String getFileDelimiter()
    {
        return ((SybaseASEProxyTableLoader) tableLoader).getFileDelimiter();
    }

    public String getExternalPath()
    {
        return ((SybaseASEProxyTableLoader) tableLoader).getExternalPath();
    }

    public String getMethod()
    {
        return ((SybaseASEWebServiceTableLoader) tableLoader).getMethod();
    }

    public String getWSDLURI()
    {
        return ((SybaseASEWebServiceTableLoader) tableLoader).getWSDLURI();
    }
    public Object getAdapter(Class adapter)
    {
        Object adapterObject = Platform.getAdapterManager().getAdapter(this, adapter);
        if (adapterObject == null)
        {
            adapterObject = Platform.getAdapterManager().loadAdapter(this, adapter.getName());
        }
        return adapterObject;
    }

    public String getExternalPathSuper()
    {
        return super.getExternalPath();
    }

    public ProxyTableExternalType getExternalTypeSuper()
    {
        return super.getExternalType();
    }

    public String getFileDelimiterSuper()
    {
        return super.getColumnDelimiter();
    }

    public boolean isExistingSuper()
    {
        return super.isExisting();
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

    public String getMethodSuper()
    {
        return super.getMethod();
    }

    public String getWSDLURISuper()
    {
        return super.getWSDLURI();
    }

    public SybaseASEBaseTableLoader getTableLoader()
    {
        return this.tableLoader;
    }
}

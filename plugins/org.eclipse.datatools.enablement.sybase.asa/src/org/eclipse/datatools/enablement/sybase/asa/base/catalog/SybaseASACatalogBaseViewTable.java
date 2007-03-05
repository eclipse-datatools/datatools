package org.eclipse.datatools.enablement.sybase.asa.base.catalog;

import java.lang.ref.SoftReference;
import java.sql.Connection;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.enablement.sybase.asa.baseloaders.SybaseASABaseViewTableLoader;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseViewTableImpl;
import org.eclipse.datatools.modelbase.sql.expressions.QueryExpression;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatement;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASACatalogBaseViewTable extends SybaseASABaseViewTableImpl implements ICatalogObject
{
	private static final long serialVersionUID = -7148203043326077984L;
	
	protected Boolean viewInfoLoaded = Boolean.FALSE;
	protected Boolean columnsLoaded = Boolean.FALSE;
	
	private SoftReference tableLoaderRef = null;

	public Database getCatalogDatabase() {
		return this.getSchema().getDatabase();
	}

	public Connection getConnection() {
		return ((ICatalogObject)getCatalogDatabase()).getConnection();
	}

	public void refresh() {
		synchronized (columnsLoaded) {
			if(columnsLoaded.booleanValue())
			{
				columnsLoaded = Boolean.FALSE;
			}
		}
        synchronized (viewInfoLoaded) {
            if(viewInfoLoaded.booleanValue())
            {
                viewInfoLoaded = Boolean.FALSE;
            }
        }

		RefreshManager.getInstance().referesh(this);
	}
	
	protected SybaseASABaseViewTableLoader getTableLoader()
	{
		SybaseASABaseViewTableLoader loader = tableLoaderRef == null ? null
				: (SybaseASABaseViewTableLoader) tableLoaderRef.get();
		if(loader == null)
		{
			loader = createViewLoader();
			tableLoaderRef = new SoftReference(loader);
		}
		return loader;
	}
	
	protected SybaseASABaseViewTableLoader createViewLoader()
	{
		return new SybaseASABaseViewTableLoader(this);
	}
	
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch(eDerivedStructuralFeatureID(eFeature))
		{
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_VIEW_TABLE__COLUMNS:
			getColumns();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_VIEW_TABLE__STATEMENT:
			getStatement();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_VIEW_TABLE__QUERY_EXPRESSION:
			getQueryExpression();
			break;
        case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_VIEW_TABLE__DESCRIPTION:
            getDescription();
            break;
		}
		return super.eIsSet(eFeature);
	}
	
	public EList getColumns() {
		synchronized (columnsLoaded) {
			if(!columnsLoaded.booleanValue())
			{
				getTableLoader().loadColumns(super.getColumns());
				columnsLoaded = Boolean.TRUE;
			}
		}
		return super.getColumns();
	}

	public SQLStatement getStatement() {
		synchronized (viewInfoLoaded) {
			if(!viewInfoLoaded.booleanValue())
			{
				getTableLoader().loadViewInfo();
				viewInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getStatement();
	}
	
	public QueryExpression getQueryExpression() {
		synchronized (viewInfoLoaded) {
			if(!viewInfoLoaded.booleanValue())
			{
				getTableLoader().loadViewInfo();
				viewInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getQueryExpression();
	}
    
    public String getDescription() {
        synchronized (viewInfoLoaded) {
            if(!viewInfoLoaded.booleanValue())
            {
                getTableLoader().loadViewInfo();
                viewInfoLoaded = Boolean.TRUE;
            }
        }
        return super.getDescription();
    }
}

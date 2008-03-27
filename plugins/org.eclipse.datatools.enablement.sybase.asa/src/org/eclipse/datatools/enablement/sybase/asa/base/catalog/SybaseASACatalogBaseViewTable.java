package org.eclipse.datatools.enablement.sybase.asa.base.catalog;

import java.lang.ref.SoftReference;
import java.sql.Connection;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.enablement.sybase.asa.base.catalog.SybaseASACatalogBaseColumn.ISybaseASACatalogBaseColumnOwner;
import org.eclipse.datatools.enablement.sybase.asa.baseloaders.AuthorizedObjectPrivilegeASALoader;
import org.eclipse.datatools.enablement.sybase.asa.baseloaders.SybaseASABaseViewTableLoader;
import org.eclipse.datatools.enablement.sybase.asa.baseloaders.TableASABaseLoader.IASABaseLoaderTable;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseViewTableImpl;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage;
import org.eclipse.datatools.modelbase.sql.expressions.QueryExpression;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.statements.SQLStatement;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASACatalogBaseViewTable extends SybaseASABaseViewTableImpl implements ICatalogObject, IAdaptable,
        ISybaseASACatalogBaseColumnOwner, IASABaseLoaderTable
{
	private static final long serialVersionUID = -7148203043326077984L;
	
	protected Boolean viewInfoLoaded = Boolean.FALSE;
	protected Boolean columnsLoaded = Boolean.FALSE;
	protected Boolean columnInfoLoaded = Boolean.FALSE;
    protected Boolean colConstraintInfoLoaded = Boolean.FALSE;
    protected Boolean colPrivilegesLoaded = Boolean.FALSE;
	
	protected Boolean privilegesLoaded = Boolean.FALSE;

    private SoftReference tableLoaderRef = null;
    private SoftReference authIdLoaderRef;

	public Database getCatalogDatabase() {
		return this.getSchema().getDatabase();
	}

	public Connection getConnection() {
		return ((ICatalogObject)getCatalogDatabase()).getConnection();
	}

	public void refresh() {
	    if(isNeedRefresh())
	    {
	        synchronized (columnsLoaded) {
	            if(columnsLoaded.booleanValue())
	            {
	                columnsLoaded = Boolean.FALSE;
	            }
	        }
	        synchronized (columnInfoLoaded) {
                if(columnInfoLoaded.booleanValue())
                {
                    columnInfoLoaded = Boolean.FALSE;
                }
            }
            synchronized (colConstraintInfoLoaded) {
                if(colConstraintInfoLoaded.booleanValue())
                {
                    colConstraintInfoLoaded = Boolean.FALSE;
                }
            }
            synchronized (colPrivilegesLoaded) {
                if(colPrivilegesLoaded.booleanValue())
                {
                    colPrivilegesLoaded = Boolean.FALSE;
                }
            }
	        synchronized (viewInfoLoaded) {
	            if(viewInfoLoaded.booleanValue())
	            {
	                viewInfoLoaded = Boolean.FALSE;
	            }
	        }
	        synchronized (privilegesLoaded) {
	            if(privilegesLoaded.booleanValue())
	            {
	                privilegesLoaded = Boolean.FALSE;
	            }
	        }
	        
	        RefreshManager.getInstance().referesh(this);
	    }
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
        case SybasesqlmodelPackage.SYBASE_BASE_TABLE__PRIVILEGES:
            getPrivileges();
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
    
    public EList getPrivileges()
    {
        synchronized (privilegesLoaded) {
            if(!privilegesLoaded.booleanValue())
            {
                getPrivilegeLoader().loadPrivilegs(super.getPrivileges());
                privilegesLoaded = Boolean.TRUE;
            }
        }
        return super.getPrivileges();
    }

    private AuthorizedObjectPrivilegeASALoader getPrivilegeLoader()
    {
        AuthorizedObjectPrivilegeASALoader loader = authIdLoaderRef == null ? null
                : (AuthorizedObjectPrivilegeASALoader) authIdLoaderRef.get();
        
        if(loader == null)
        {
            loader = createPrivilegeLoader();
            authIdLoaderRef = new SoftReference(loader);
        }
        
        return loader;
    }
    
    private AuthorizedObjectPrivilegeASALoader createPrivilegeLoader()
    {
        return new AuthorizedObjectPrivilegeASALoader(this);
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
	    if (viewInfoLoaded.booleanValue() || columnsLoaded.booleanValue() || privilegesLoaded.booleanValue()
                || columnInfoLoaded.booleanValue() || colPrivilegesLoaded.booleanValue()
                || colConstraintInfoLoaded.booleanValue())
	    {
	        return true;
	    }
	    else
	    {
	        return false;
	    }
	}
	
	public Boolean isColumnConstraintLoaded()
    {
        return colConstraintInfoLoaded;
    }

    public Boolean isColumnInfoLoaded()
    {
        return columnInfoLoaded;
    }

    public Boolean isColumnPrivilegeLoaded()
    {
        return colPrivilegesLoaded;
    }

    public void setColumnConstraintLoaded(Boolean loaded)
    {
        this.colConstraintInfoLoaded = loaded;
    }

    public void setColumnInfoLoaded(Boolean loaded)
    {
        this.columnInfoLoaded = loaded;
    }

    public void setColumnPrivilegeLoaded(Boolean loaded)
    {
        this.colPrivilegesLoaded = loaded;
    }
    
    //reversed
    public Boolean isIndexLoaded()
    {
        return Boolean.TRUE;
    }

    public Boolean isTriggerLoaded()
    {
        return Boolean.TRUE;
    }

    public void setIndexLoaded(Boolean loaded)
    {
    }

    public void setTriggerLoaded(Boolean loaded)
    {
    }

    public EList getIndexSupper()
    {
        return super.getIndex();
    }

    public EList getTriggerSuper()
    {
        return super.getTriggers();
    }
}

package org.eclipse.datatools.enablement.sybase.asa.base.catalog;

import java.lang.ref.SoftReference;
import java.sql.Connection;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.enablement.sybase.asa.baseloaders.AuthorizedObjectPrivilegeASALoader;
import org.eclipse.datatools.enablement.sybase.asa.baseloaders.RoutineInfoASALoader;
import org.eclipse.datatools.enablement.sybase.asa.baseloaders.SybaseASABaseParameterLoader;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseasabasesqlmodelPackage;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseFunctionImpl;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage;
import org.eclipse.datatools.modelbase.sql.routines.Source;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASACatalogBaseUserDefinedFunction extends SybaseASABaseFunctionImpl implements ICatalogObject,IAdaptable{

	private static final long serialVersionUID = -100302511500251535L;

	protected Boolean functionInfoLoaded = Boolean.FALSE;
	protected Boolean parameterInfoLoaded = Boolean.FALSE;
    protected Boolean privilegesLoaded = Boolean.FALSE;
    private SoftReference authIdLoaderRef;
	
	public Database getCatalogDatabase() {
		return this.getSchema().getDatabase();
	}

	public Connection getConnection() {
		return ((ICatalogObject)getCatalogDatabase()).getConnection();
	}

   public boolean needsRefresh()
    {
        if(functionInfoLoaded.booleanValue()||parameterInfoLoaded.booleanValue()
                ||privilegesLoaded.booleanValue())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
	   
	public void refresh() {
	    if (needsRefresh())
	    {
	        synchronized (functionInfoLoaded) {
	            if(functionInfoLoaded.booleanValue())
	            {
	                functionInfoLoaded = Boolean.FALSE;
	            }
	        }
	        synchronized (parameterInfoLoaded) {
	            if(parameterInfoLoaded.booleanValue())
	            {
	                parameterInfoLoaded = Boolean.FALSE;
	            }
	        }
	        synchronized (privilegesLoaded) {
	            if(privilegesLoaded.booleanValue())
	                privilegesLoaded = Boolean.FALSE;
	        }
	        
	        RefreshManager.getInstance().referesh(this);
	    }
	}
	
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch(eDerivedStructuralFeatureID(eFeature))
		{
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FUNCTION__DESCRIPTION:
			getDescription();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FUNCTION__SOURCE:
			getSource();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_FUNCTION__PARAMETERS:
			getParameters();
			break;
        case SybasesqlmodelPackage.SYBASE_AUTHORIZED_OBJECT__PRIVILEGES:
            getPrivileges();
            break;
		}
		return super.eIsSet(eFeature);
	}
	
	public String getDescription() {
		synchronized (functionInfoLoaded) {
			if(!functionInfoLoaded.booleanValue())
			{
				loadFunctionInfo();
				functionInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getDescription();
	}
	
	public Source getSource() {
		synchronized (functionInfoLoaded) {
			if(!functionInfoLoaded.booleanValue())
			{
				loadFunctionInfo();
				functionInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getSource();
	}
	
	public EList getParameters() {
		synchronized (parameterInfoLoaded) {
			if(!parameterInfoLoaded.booleanValue())
			{
				loadParameterInfo();
				parameterInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getParameters();
	}

	protected void loadFunctionInfo() {
		new RoutineInfoASALoader(this).loadRoutineInfo();
	}

	protected void loadParameterInfo()
	{
		new SybaseASABaseParameterLoader(this).loadParameterInfo(super.getParameters());
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
}

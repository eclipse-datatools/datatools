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
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseProcedureImpl;
import org.eclipse.datatools.enablement.sybase.models.sybasesqlmodel.SybasesqlmodelPackage;
import org.eclipse.datatools.modelbase.sql.routines.Source;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASACatalogBaseProcedure extends SybaseASABaseProcedureImpl implements ICatalogObject,IAdaptable{

	private static final long serialVersionUID = 6507002865454988134L;

	protected Boolean procedureInfoLoaded = Boolean.FALSE;
	protected Boolean parameterInfoLoaded = Boolean.FALSE;
	protected Boolean privilegesLoaded = Boolean.FALSE;
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
	        synchronized (procedureInfoLoaded) {
	            if(procedureInfoLoaded.booleanValue())
	            {
	                procedureInfoLoaded = Boolean.FALSE;
	            }
	        }
	        synchronized (parameterInfoLoaded) {
	            if(parameterInfoLoaded.booleanValue())
	            {
	                parameterInfoLoaded = Boolean.FALSE;
	                //the result set is retrieved from parameters
	                if (resultSet != null)
	                {
	                    resultSet.clear();
	                }
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
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PROCEDURE__DESCRIPTION:
			getDescription();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PROCEDURE__SOURCE:
			getSource();
			break;
		case SybaseasabasesqlmodelPackage.SYBASE_ASA_BASE_PROCEDURE__PARAMETERS:
			getParameters();
			break;
		case SybasesqlmodelPackage.SYBASE_AUTHORIZED_OBJECT__PRIVILEGES:
		    getPrivileges();
		    break;
		}
		return super.eIsSet(eFeature);
	}
	
	public String getDescription() {
		synchronized (procedureInfoLoaded) {
			if(!procedureInfoLoaded.booleanValue())
			{
				loadProcedureInfo();
				procedureInfoLoaded = Boolean.TRUE;
			}
		}
		return super.getDescription();
	}
	
	public Source getSource() {
		synchronized (procedureInfoLoaded) {
			if(!procedureInfoLoaded.booleanValue())
			{
				loadProcedureInfo();
				procedureInfoLoaded = Boolean.TRUE;
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

	protected void loadProcedureInfo() {
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
	
	public boolean isNeedRefresh()
	{
	    if(procedureInfoLoaded.booleanValue()||parameterInfoLoaded.booleanValue()
	            ||privilegesLoaded.booleanValue())
	    {
	        return true;
	    }
	    else
	    {
	        return false;
	    }
	}
}

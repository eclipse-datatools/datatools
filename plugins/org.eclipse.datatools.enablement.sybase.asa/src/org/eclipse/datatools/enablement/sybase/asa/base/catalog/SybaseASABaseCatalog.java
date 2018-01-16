package org.eclipse.datatools.enablement.sybase.asa.base.catalog;

import java.lang.ref.SoftReference;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCSchemaLoader;
import org.eclipse.datatools.enablement.sybase.VirtualNodeAdapter;
import org.eclipse.datatools.enablement.sybase.asa.JDBCASAPlugin;
import org.eclipse.datatools.enablement.sybase.asa.containment.DBEventGroupID;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.SybaseASABaseDatabase;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Event;
import org.eclipse.datatools.modelbase.sql.schema.impl.CatalogImpl;
import org.eclipse.datatools.sqltools.internal.refresh.ICatalogObject2;
import org.eclipse.datatools.sqltools.internal.refresh.RefreshManager2;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

abstract public class SybaseASABaseCatalog extends CatalogImpl implements ICatalogObject, ICatalogObject2, IAdaptable
{

	private static final long serialVersionUID = 3372967146783478978L;

	protected Boolean schemaLoaded = Boolean.FALSE;
	private SoftReference schemaLoaderRef;
	
	public Database getCatalogDatabase() {
		return getDatabase();
	}

	public Connection getConnection() 
	{
		return ((ICatalogObject)getCatalogDatabase()).getConnection();
	}

	public void refresh() {
		synchronized (schemaLoaded) {
			if(schemaLoaded.booleanValue())
				schemaLoaded = Boolean.FALSE;
		}
		RefreshManager.getInstance().referesh(this);
	}

	public boolean eIsSet(EStructuralFeature eFeature) {
		return super.eIsSet(eFeature);
	}	
	
	public EList getSchemas() {
		synchronized (schemaLoaded) {
			if(!this.schemaLoaded.booleanValue()) 
			{
				loadSchemas(super.getSchemas());
				this.schemaLoaded = Boolean.TRUE;
			}
		}
		return super.getSchemas();
	}
	
	public void loadSchemas(EList schemaConstainmentList) {
		try {
			boolean deliver1 = database.eDeliver();
			boolean deliver2 = this.eDeliver();
			database.eSetDeliver(false);
			this.eSetDeliver(deliver2);
			
			List existingSchemas = new ArrayList(schemaConstainmentList.size());
			existingSchemas.addAll(schemaConstainmentList);
			getSchemaLoader().clearSchemas(schemaConstainmentList);
			getSchemaLoader().loadSchemas(schemaConstainmentList, existingSchemas);
			
			((SybaseASABaseDatabase)database).getDatabaseSchemas().clear();
            ((SybaseASABaseDatabase)database).getDatabaseSchemas().addAll(super.getSchemas());

			database.eSetDeliver(deliver1);
			this.eSetDeliver(deliver2);
		}
		catch (Exception e) {
			JDBCASAPlugin.getDefault().log(e);
		}
	}
	
	private JDBCSchemaLoader getSchemaLoader() {
		if (schemaLoaderRef == null || schemaLoaderRef.get() == null) {
			schemaLoaderRef = new SoftReference(createSchemaLoader());
		}
		return (JDBCSchemaLoader) schemaLoaderRef.get();
	}
	
	abstract protected JDBCSchemaLoader createSchemaLoader();
	
	//only event supported[488803] while user drop event, this method will be called. 
	public String getRefreshContext(Object obj) 
	{
		if(obj instanceof Event)
		{
			return DBEventGroupID.DBEVENT;
		}
		else if(obj instanceof IAdaptable)
		{
			VirtualNodeAdapter adapter = (VirtualNodeAdapter)((IAdaptable)obj).getAdapter(VirtualNodeAdapter.class);
			if (adapter != null)
			{
				return adapter.getGroupId();
			}
		}
		
		return null;
	}

	public boolean needsRefresh(String context) 
	{
		if(context != null && context.equals(DBEventGroupID.DBEVENT))
			return true;
		else
			return false;
	}

	public void refresh(String context) 
	{
		if(needsRefresh(context))
		{
			((ISybaseASABaseCatalogDatabase)getCatalogDatabase()).refreshEvent();
		}
		
		RefreshManager2.getInstance().referesh(this, context);
	}
	
	public static interface ISybaseASABaseCatalogDatabase
	{
		public void refreshEvent();
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
}

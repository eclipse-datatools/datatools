package org.eclipse.datatools.enablement.sybase.asa.base.catalog;

import java.lang.ref.SoftReference;
import java.sql.Connection;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.enablement.sybase.asa.baseloaders.AuthorizationIdentifierASALoader;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogUtils;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseUserImpl;
import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASACatalogBaseUser extends SybaseASABaseUserImpl implements ICatalogObject
{
	private static final long serialVersionUID = -9099341886539383531L;
	
	protected Boolean privilegesLoaded = Boolean.FALSE;
    protected Boolean userInfoLoaded = Boolean.FALSE;
    protected Boolean ownedSchemaLoaded = Boolean.FALSE;
	
	private SoftReference groupLoaderRef;

	private Database database;
	
	public SybaseASACatalogBaseUser(ICatalogObject catalogDatabase)
	{
		this.database = (Database)catalogDatabase;
	}
	
	public void refresh() {
		synchronized (privilegesLoaded) {
			if(privilegesLoaded.booleanValue())
			{
				privilegesLoaded = Boolean.FALSE;
			}	
		}
        synchronized (userInfoLoaded)
        {
            if(userInfoLoaded.booleanValue())
            {
                userInfoLoaded = Boolean.FALSE;
            }
        }
        synchronized (ownedSchemaLoaded)
        {
            if(ownedSchemaLoaded.booleanValue())
            {
                ownedSchemaLoaded = Boolean.FALSE;
            }
        }
		RefreshManager.getInstance().referesh(this);
	}
	
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch(eDerivedStructuralFeatureID(eFeature))
		{
		case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__RECEIVED_PRIVILEGE:
			getReceivedPrivilege();
			break;
		case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__DESCRIPTION:
			getDescription();
			break;
        case SQLAccessControlPackage.AUTHORIZATION_IDENTIFIER__OWNED_SCHEMA:
            getOwnedSchema();
            break;
		}
		return super.eIsSet(eFeature);
	}
	
	public Database getCatalogDatabase() {
		return database;
	}

	public Connection getConnection() {
		return ((ICatalogObject)database).getConnection();
	}

	public EList getReceivedPrivilege()
	{
		synchronized (privilegesLoaded) {
			if(!privilegesLoaded.booleanValue())
			{
				//getAuthIdLoader().loadPrivilegs(super.getReceivedPrivilege());
				privilegesLoaded = Boolean.TRUE;
			}
		}
		return super.getReceivedPrivilege();
	}
	
	private AuthorizationIdentifierASALoader getAuthIdLoader()
	{
		AuthorizationIdentifierASALoader loader = groupLoaderRef == null ? null
				: (AuthorizationIdentifierASALoader) groupLoaderRef.get();
		
		if(loader == null)
		{
			loader = createAuthIdLoader();
			groupLoaderRef = new SoftReference(loader);
		}
		
		return loader;
	}
	
	private AuthorizationIdentifierASALoader createAuthIdLoader()
	{
		return new AuthorizationIdentifierASALoader(this);
	}
    
    public EList getOwnedSchema()
    {
        synchronized (ownedSchemaLoaded)
        {
            if (!ownedSchemaLoaded.booleanValue())
            {
                EList ownedSchemas = super.getOwnedSchema();
                ownedSchemas.clear();
                Schema schema = (Schema) SybaseASACatalogUtils.findElement(database.getSchemas(), getName());
                ownedSchemas.add(schema);
                ownedSchemaLoaded = Boolean.TRUE;
            }
        }
        return super.getOwnedSchema();
    }
    
    public String getDescription()
    {
        synchronized (userInfoLoaded)
        {
            if (!userInfoLoaded.booleanValue())
            {
                getAuthIdLoader().loadAuthInfo();
                userInfoLoaded = Boolean.TRUE;
            }
        }
        return super.getDescription();
    }
}

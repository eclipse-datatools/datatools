package org.eclipse.datatools.enablement.sybase.asa.base.catalog;

import java.lang.ref.SoftReference;
import java.sql.Connection;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.core.rte.RefreshManager;
import org.eclipse.datatools.enablement.sybase.asa.baseloaders.GroupASALoader;
import org.eclipse.datatools.enablement.sybase.asa.catalog.SybaseASACatalogUtils;
import org.eclipse.datatools.enablement.sybase.asa.models.sybaseasabasesqlmodel.impl.SybaseASABaseGroupImpl;
import org.eclipse.datatools.modelbase.sql.accesscontrol.SQLAccessControlPackage;
import org.eclipse.datatools.modelbase.sql.schema.Database;
import org.eclipse.datatools.modelbase.sql.schema.Schema;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;

public class SybaseASACatalogBaseGroup extends SybaseASABaseGroupImpl implements ICatalogObject {

	private static final long serialVersionUID = -9099341886539383531L;

	protected Boolean groupMemberLoaded = Boolean.FALSE;
	protected Boolean privilegesLoaded = Boolean.FALSE;
    protected Boolean ownedSchemaLoaded = Boolean.FALSE;
    protected Boolean userInfoLoaded = Boolean.FALSE;
	
	private SoftReference groupLoaderRef;

	private Database database;
	
	public SybaseASACatalogBaseGroup(ICatalogObject catalogDatabase)
	{
		this.database = catalogDatabase.getCatalogDatabase();
	}
	
	public void refresh() {
		synchronized (groupMemberLoaded) {
			if(groupMemberLoaded.booleanValue())
			{
				groupMemberLoaded = Boolean.FALSE;
			}	
		}
		synchronized (privilegesLoaded) {
			if(privilegesLoaded.booleanValue())
			{
				privilegesLoaded = Boolean.FALSE;
			}	
		}
        synchronized (ownedSchemaLoaded) {
            if(ownedSchemaLoaded.booleanValue())
            {
                ownedSchemaLoaded = Boolean.FALSE;
            }   
        }
        synchronized (userInfoLoaded) {
            if(userInfoLoaded.booleanValue())
            {
                userInfoLoaded = Boolean.FALSE;
            }   
        }
		RefreshManager.getInstance().referesh(this);
	}
	
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch(eDerivedStructuralFeatureID(eFeature))
		{
		case SQLAccessControlPackage.GROUP__USER:
			getUser();
			break;
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

	public EList getUser() {
		synchronized (groupMemberLoaded) {
			if(!groupMemberLoaded.booleanValue())
			{
				getGroupLoader().loadGroupUsers(super.getUser());
				groupMemberLoaded = Boolean.TRUE;
			}
		}
		return super.getUser();
	}

	public EList getReceivedPrivilege()
	{
		synchronized (privilegesLoaded) {
			if(!privilegesLoaded.booleanValue())
			{
				//getGroupLoader().loadPrivilegs(super.getReceivedPrivilege());
				privilegesLoaded = Boolean.TRUE;
			}
		}
		return super.getReceivedPrivilege();
	}
	
	private GroupASALoader getGroupLoader()
	{
		GroupASALoader loader = groupLoaderRef == null ? null
				: (GroupASALoader) groupLoaderRef.get();
		
		if(loader == null)
		{
			loader = createGroupLoader();
			groupLoaderRef = new SoftReference(loader);
		}
		
		return loader;
	}
	
	protected GroupASALoader createGroupLoader()
	{
		return new GroupASALoader(this);
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
                getGroupLoader().loadAuthInfo();
                userInfoLoaded = Boolean.TRUE;
            }
        }
        return super.getDescription();
    }
}
